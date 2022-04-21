/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classnames from 'classnames';
import {useMutation} from 'graphql-hooks';
import React, {useCallback, useEffect, useState} from 'react';
import {withRouter} from 'react-router-dom';

import {
	deleteMessageQuery,
	markAsAnswerMessageBoardMessageQuery,
} from '../utils/client.es';
import lang from '../utils/lang.es';
import ArticleBodyRenderer from './ArticleBodyRenderer.es';
import Comments from './Comments.es';
import Link from './Link.es';
import Modal from './Modal.es';
import Rating from './Rating.es';
import UserRow from './UserRow.es';

export default withRouter(
	({
		answer,
		answerChange,
		canMarkAsAnswer,
		deleteAnswer,
		editable = true,
		match: {url},
	}) => {
		const [comments, setComments] = useState(
			answer.messageBoardMessages.items
		);
		const [dateModified, setDateModified] = useState('');
		const [showAsAnswer, setShowAsAnswer] = useState(answer.showAsAnswer);
		const [showNewComment, setShowNewComment] = useState(false);
		const [showDeleteAnswerModal, setShowDeleteAnswerModal] = useState(
			false
		);

		const [deleteMessage] = useMutation(deleteMessageQuery);

		const _commentsChange = useCallback((comments) => {
			setComments([...comments]);
		}, []);

		const [markAsAnswerMessageBoardMessage] = useMutation(
			markAsAnswerMessageBoardMessageQuery
		);

		useEffect(() => {
			setShowAsAnswer(answer.showAsAnswer);
		}, [answer.showAsAnswer]);

		useEffect(() => {
			setDateModified(new Date(answer.dateModified).toLocaleDateString());
		}, [answer.dateModified]);

		return (
			<>
				<div
					className={classnames('questions-answer c-p-3', {
						'questions-answer-success': showAsAnswer,
					})}
					data-testid="mark-as-answer-style"
				>
					<div className="d-flex row">
						<div className="c-ml-auto c-ml-md-1 c-ml-sm-auto order-1 order-md-0 text-md-center text-right">
							<Rating
								aggregateRating={answer.aggregateRating}
								disabled={!editable}
								entityId={answer.id}
								myRating={
									answer.myRating &&
									answer.myRating.ratingValue
								}
								type="Message"
							/>
						</div>

						<div className="c-mb-4 c-mb-md-0 c-ml-3 col-lg-11 col-md-10 col-sm-12 col-xl-11">
							{showAsAnswer && (
								<p
									className="c-mb-0 font-weight-bold text-success"
									data-testid="mark-as-answer-check"
								>
									<ClayIcon symbol="check-circle-full" />

									<span className="c-ml-3">
										{Liferay.Language.get('chosen-answer')}
									</span>
								</p>
							)}

							<span className="text-secondary">
								{lang.sub(Liferay.Language.get('answered-x'), [
									dateModified,
								])}
							</span>

							{answer.status && answer.status !== 'approved' && (
								<span className="c-ml-2 text-secondary">
									<ClayLabel displayType="info">
										{answer.status}
									</ClayLabel>
								</span>
							)}

							<div className="c-mt-2">
								<ArticleBodyRenderer {...answer} />
							</div>

							<div className="d-flex justify-content-between">
								<div>
									{editable && (
										<ClayButton.Group
											className="font-weight-bold text-secondary"
											spaced={true}
										>
											{answer.actions[
												'reply-to-message'
											] &&
												answer.status !== 'pending' && (
													<ClayButton
														className="text-reset"
														displayType="unstyled"
														onClick={() =>
															setShowNewComment(
																true
															)
														}
													>
														{Liferay.Language.get(
															'reply'
														)}
													</ClayButton>
												)}

											{answer.actions.delete && (
												<>
													<ClayButton
														className="text-reset"
														displayType="unstyled"
														onClick={() => {
															setShowDeleteAnswerModal(
																true
															);
														}}
													>
														{Liferay.Language.get(
															'delete'
														)}
													</ClayButton>
													<Modal
														body={Liferay.Language.get(
															'do-you-want-to-delete–this-answer'
														)}
														callback={() => {
															deleteMessage({
																variables: {
																	messageBoardMessageId:
																		answer.id,
																},
															}).then(() => {
																deleteAnswer(
																	answer
																);
															});
														}}
														onClose={() => {
															setShowDeleteAnswerModal(
																false
															);
														}}
														status="warning"
														textPrimaryButton={Liferay.Language.get(
															'delete'
														)}
														title={Liferay.Language.get(
															'delete-answer'
														)}
														visible={
															showDeleteAnswerModal
														}
													/>
												</>
											)}

											{canMarkAsAnswer && (
												<ClayButton
													className="text-reset"
													data-testid="mark-as-answer-button"
													displayType="unstyled"
													onClick={() => {
														markAsAnswerMessageBoardMessage(
															{
																variables: {
																	messageBoardMessageId:
																		answer.id,
																	showAsAnswer: !showAsAnswer,
																},
															}
														).then(() => {
															setShowAsAnswer(
																!showAsAnswer
															);
															if (answerChange) {
																answerChange(
																	answer.id
																);
															}
														});
													}}
												>
													{Liferay.Language.get(
														showAsAnswer
															? 'Unmark as answer'
															: 'Mark as answer'
													)}
												</ClayButton>
											)}

											{/* this is an extra double check, remove it without creating 2 clay-group-item */}

											{answer.actions.replace && (
												<ClayButton
													className="text-reset"
													displayType="unstyled"
												>
													<Link
														className="text-reset"
														to={`${url}/answers/${answer.friendlyUrlPath}/edit`}
													>
														{Liferay.Language.get(
															'edit'
														)}
													</Link>
												</ClayButton>
											)}
										</ClayButton.Group>
									)}
								</div>

								<div className="c-ml-md-auto c-ml-sm-2 c-mr-lg-2 c-mr-md-4 c-mr-xl-2">
									<UserRow
										creator={answer.creator}
										statistics={answer.creatorStatistics}
									/>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div className="row">
					<div className="col-md-9 offset-md-1">
						<Comments
							comments={comments}
							commentsChange={_commentsChange}
							editable={editable}
							entityId={answer.id}
							showNewComment={showNewComment}
							showNewCommentChange={(value) =>
								setShowNewComment(value)
							}
						/>
					</div>
				</div>
			</>
		);
	}
);
