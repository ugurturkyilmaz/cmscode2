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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {useDispatch} from '../../../app/contexts/StoreContext';
import selectExperience from '../thunks/selectExperience';
import {ExperienceType} from '../types';
import ExperienceItem from './ExperienceItem';

const ExperiencesList = ({
	activeExperienceId,
	canUpdateExperiences,
	defaultExperienceId,
	experiences,
	onDeleteExperience,
	onDuplicateExperience,
	onEditExperience,
	onPriorityDecrease,
	onPriorityIncrease,
}) => {
	const dispatch = useDispatch();

	const handleExperienceSelection = (id) => dispatch(selectExperience({id}));

	/* We cannot increase priority if the experiencie above has an experimente running and it's 
	   for the same audience or the audience of experience is anyone */
	const calculateIfLockedIncreasePriority = (experience, i) => {
		return (
			experiences[i - 1].hasLockedSegmentsExperiment &&
			(experience.segmentsEntryId ===
				experiences[i - 1].segmentsEntryId ||
				experience.segmentsEntryId === '0')
		);
	};

	return (
		<ClayList className="mt-3">
			{experiences.map((experience, i) => {
				const active =
					experience.segmentsExperienceId === activeExperienceId;
				const lockedDecreasePriority = experiences.length - 1 === i;
				const lockedIncreasePriority =
					i === 0 || calculateIfLockedIncreasePriority(experience, i);

				const editable =
					canUpdateExperiences &&
					experience.segmentsExperienceId !== defaultExperienceId &&
					!experience.hasLockedSegmentsExperiment;

				return (
					<ExperienceItem
						active={active}
						editable={editable}
						experience={experience}
						key={experience.segmentsExperienceId}
						lockedDecreasePriority={lockedDecreasePriority}
						lockedIncreasePriority={lockedIncreasePriority}
						onDeleteExperience={onDeleteExperience}
						onDuplicateExperience={onDuplicateExperience}
						onEditExperience={onEditExperience}
						onPriorityDecrease={onPriorityDecrease}
						onPriorityIncrease={onPriorityIncrease}
						onSelect={handleExperienceSelection}
					/>
				);
			})}
		</ClayList>
	);
};

ExperiencesList.propTypes = {
	activeExperienceId: PropTypes.string.isRequired,
	canUpdateExperiences: PropTypes.bool.isRequired,
	defaultExperienceId: PropTypes.string.isRequired,
	experiences: PropTypes.arrayOf(PropTypes.shape(ExperienceType)).isRequired,
	onDeleteExperience: PropTypes.func.isRequired,
	onDuplicateExperience: PropTypes.func.isRequired,
	onEditExperience: PropTypes.func.isRequired,
	onPriorityDecrease: PropTypes.func.isRequired,
	onPriorityIncrease: PropTypes.func.isRequired,
};

export default ExperiencesList;
