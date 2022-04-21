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
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import React, {useState} from 'react';

import Lang from '../utils/lang';

export default function RatingsSelectStars({
	averageScore,
	disabled,
	getSrAverageMessage,
	getTitle,
	numberOfStars,
	onVote,
	score,
	starScores,
	totalEntries,
}) {
	const [isDropdownOpen, setIsDropdownOpen] = useState(false);

	const handleOnClick = (index) => {
		setIsDropdownOpen(false);
		onVote(index);
	};

	return (
		<ClayLayout.ContentRow
			className="ratings-stars"
			noGutters
			verticalAlign="center"
		>
			<ClayLayout.ContentCol>
				<ClayDropDown
					active={isDropdownOpen}
					menuElementAttrs={{
						className: 'ratings-stars-dropdown',
					}}
					onActiveChange={(isActive) => setIsDropdownOpen(isActive)}
					trigger={
						<ClayButton
							aria-pressed={!!score}
							borderless
							className="ratings-stars-dropdown-toggle"
							disabled={disabled}
							displayType="secondary"
							small
							title={getTitle()}
							value={score}
						>
							<span className="inline-item inline-item-before">
								<ClayIcon symbol={score ? 'star' : 'star-o'} />
							</span>

							<span className="inline-item ratings-stars-button-text">
								{score || '-'}
							</span>
						</ClayButton>
					}
				>
					<ClayDropDown.ItemList>
						{starScores.map(({label}, index) => {
							const srMessage =
								index === 0
									? Liferay.Language.get(
											'rate-this-x-star-out-of-x'
									  )
									: Liferay.Language.get(
											'rate-this-x-stars-out-of-x'
									  );

							return (
								<ClayDropDown.Item
									active={label === score}
									key={index}
									onClick={() => {
										handleOnClick(index);
									}}
								>
									{label}

									<span className="sr-only">
										{Lang.sub(srMessage, [
											index + 1,
											numberOfStars,
										])}
									</span>
								</ClayDropDown.Item>
							);
						})}

						<ClayDropDown.Item
							disabled={score === 0}
							onClick={handleOnClick}
						>
							{Liferay.Language.get('delete')}
						</ClayDropDown.Item>
					</ClayDropDown.ItemList>
				</ClayDropDown>
			</ClayLayout.ContentCol>

			<ClayLayout.ContentCol>
				<span className="ratings-stars-average">
					<span className="inline-item inline-item-before">
						<ClayIcon
							className="ratings-stars-average-icon"
							symbol="star"
						/>
					</span>

					<span className="inline-item ratings-stars-average-text">
						{averageScore.toFixed(1)}

						{!!totalEntries &&
							` (${totalEntries} ${
								totalEntries === 1
									? Liferay.Language.get('vote')
									: Liferay.Language.get('votes')
							})`}
					</span>

					<span className="sr-only">{getSrAverageMessage()}</span>
				</span>
			</ClayLayout.ContentCol>
		</ClayLayout.ContentRow>
	);
}
