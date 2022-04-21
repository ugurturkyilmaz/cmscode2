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

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import ClayEmptyState from '@clayui/empty-state';
import {useMutation, useQuery} from 'graphql-hooks';
import React, {useContext, useState} from 'react';
import {withRouter} from 'react-router-dom';

import {AppContext} from '../../AppContext.es';
import Alert from '../../components/Alert.es';
import DeleteQuestion from '../../components/DeleteQuestion.es';
import Link from '../../components/Link.es';
import QuestionRow from '../../components/QuestionRow.es';
import {
	getSubscriptionsQuery,
	unsubscribeMyUserAccountQuery,
} from '../../utils/client.es';
import {historyPushWithSlug} from '../../utils/utils.es';

export default withRouter(({history}) => {
	const [info, setInfo] = useState({});
	const [questionToDelete, setQuestionToDelete] = useState({});

	const context = useContext(AppContext);

	const {data: threads, refetch: refetchThread} = useQuery(
		getSubscriptionsQuery,
		{
			variables: {
				contentType: 'MessageBoardThread',
			},
		}
	);

	const {data: topics, refetch: refetchTopics} = useQuery(
		getSubscriptionsQuery,
		{
			variables: {
				contentType: 'MessageBoardSection',
			},
		}
	);

	const [unsubscribe] = useMutation(unsubscribeMyUserAccountQuery);

	const [showDeleteModalPanel, setShowDeleteModalPanel] = useState(false);

	const historyPushParser = historyPushWithSlug(history.push);

	const actions = (data) => {
		const question = data.graphQLNode;

		const actions = [
			{
				label: 'Unsubscribe',
				onClick: () => {
					unsubscribe({
						variables: {
							subscriptionId: data.id,
						},
					}).then(() => {
						refetchThread();
						refetchTopics();
						setInfo({
							title:
								'You have unsubscribed from this asset successfully.',
						});
					});
				},
			},
		];

		if (question.actions && question.actions.delete) {
			actions.push({
				label: 'Delete',
				onClick: () => {
					setQuestionToDelete(question);
					setShowDeleteModalPanel(true);
				},
			});
		}

		if (question.actions && question.actions.replace) {
			actions.push({
				label: 'Edit',
				onClick: () => {
					historyPushParser(
						`/questions/${
							context.useTopicNamesInURL
								? question.messageBoardSection.title
								: question.messageBoardSection.id
						}/${data.graphQLNode.friendlyUrlPath}/edit`
					);
				},
			});
		}

		if (question.headline) {
			actions.push({
				label: 'Reply',
				onClick: () => {
					historyPushParser(
						`/questions/${
							context.useTopicNamesInURL
								? question.messageBoardSection.title
								: question.messageBoardSection.id
						}/${question.friendlyUrlPath}`
					);
				},
			});
		}

		return actions;
	};

	return (
		<section className="questions-section questions-section-list">
			<div className="c-p-5 questions-container row">
				<div className="col-xl-8 offset-xl-2">
					<h2 className="sheet-subtitle">Topics</h2>

					{topics &&
						topics.myUserAccountSubscriptions.items &&
						!topics.myUserAccountSubscriptions.items.length && (
							<ClayEmptyState
								title={Liferay.Language.get(
									'there-are-no-results'
								)}
							/>
						)}

					<div className="row">
						{topics &&
							topics.myUserAccountSubscriptions.items &&
							topics.myUserAccountSubscriptions.items.map(
								(data) => (
									<div
										className="col-md-4 question-tags"
										key={data.graphQLNode.id}
									>
										<div className="card card-interactive card-interactive-primary card-type-template template-card-horizontal">
											<div className="card-body">
												<div className="card-row">
													<div className="autofit-col autofit-col-expand">
														<Link
															title={
																data.graphQLNode
																	.title
															}
															to={`/questions/${
																context.useTopicNamesInURL
																	? data
																			.graphQLNode
																			.title
																	: data
																			.graphQLNode
																			.id
															}`}
														>
															<div className="autofit-section">
																<div className="card-title">
																	<span className="text-truncate">
																		{
																			data
																				.graphQLNode
																				.title
																		}
																	</span>
																</div>
															</div>
														</Link>
													</div>

													<div className="autofit-col">
														<ClayDropDownWithItems
															items={actions(
																data
															)}
															trigger={
																<ClayButtonWithIcon
																	displayType="unstyled"
																	small
																	symbol="ellipsis-v"
																/>
															}
														/>
													</div>
												</div>
											</div>
										</div>
									</div>
								)
							)}
					</div>

					<h2 className="mt-5 sheet-subtitle">Questions</h2>

					<div>
						{threads &&
							threads.myUserAccountSubscriptions.items &&
							!threads.myUserAccountSubscriptions.items
								.length && (
								<ClayEmptyState
									title={Liferay.Language.get(
										'there-are-no-results'
									)}
								/>
							)}

						{threads &&
							threads.myUserAccountSubscriptions.items &&
							threads.myUserAccountSubscriptions.items.map(
								(data) => (
									<div key={data.id}>
										<QuestionRow
											currentSection={
												context.useTopicNamesInURL
													? data.graphQLNode
															.messageBoardSection &&
													  data.graphQLNode
															.messageBoardSection
															.title
													: (data.graphQLNode
															.messageBoardSection &&
															data.graphQLNode
																.messageBoardSection
																.id) ||
													  context.rootTopicId
											}
											items={actions(data)}
											question={data.graphQLNode}
											showSectionLabel={true}
										/>
									</div>
								)
							)}

						<DeleteQuestion
							deleteModalVisibility={showDeleteModalPanel}
							question={questionToDelete}
							setDeleteModalVisibility={setShowDeleteModalPanel}
						/>
					</div>
				</div>
			</div>

			<Alert displayType="success" info={info} />
		</section>
	);
});
