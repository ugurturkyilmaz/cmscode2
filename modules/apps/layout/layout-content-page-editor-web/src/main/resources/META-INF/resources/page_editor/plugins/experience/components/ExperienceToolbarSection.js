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

import React, {useEffect, useMemo} from 'react';

import togglePermissions from '../../../app/actions/togglePermission';
import {config} from '../../../app/config/index';
import {useDispatch, useSelector} from '../../../app/contexts/StoreContext';
import selectSegmentsExperienceId from '../../../app/selectors/selectSegmentsExperienceId';
import ExperienceSelector from './ExperienceSelector';

// TODO: show how to colocate CSS with plugins (may use loaders)

export default function ExperienceToolbarSection({selectId}) {
	const availableSegmentsExperiences = useSelector(
		(state) => state.availableSegmentsExperiences
	);
	const dispatch = useDispatch();
	const segmentsExperienceId = useSelector(selectSegmentsExperienceId);

	const experiences = useMemo(
		() =>
			Object.values(availableSegmentsExperiences)
				.sort((a, b) => b.priority - a.priority)
				.map((experience, _, experiences) => {
					const segmentsEntryName =
						config.availableSegmentsEntries[
							experience.segmentsEntryId
						].name;

					const firstExperience = experiences.find(
						(exp) =>
							exp.segmentsEntryId ===
								experience.segmentsEntryId ||
							exp.segmentsEntryId ===
								config.defaultSegmentsEntryId
					);

					return {
						...experience,
						active:
							firstExperience.segmentsExperienceId ===
							experience.segmentsExperienceId,
						segmentsEntryName,
					};
				}),
		[availableSegmentsExperiences]
	);
	const segments = useMemo(
		() => Object.values(config.availableSegmentsEntries),
		[]
	);

	const selectedExperience =
		availableSegmentsExperiences[segmentsExperienceId];

	useEffect(() => {
		dispatch(
			togglePermissions(
				'LOCKED_SEGMENTS_EXPERIMENT',
				selectedExperience.hasLockedSegmentsExperiment
			)
		);
	}, [dispatch, selectedExperience.hasLockedSegmentsExperiment]);

	return (
		<div className="page-editor__toolbar-experience">
			<label className="d-lg-block d-none mr-2" htmlFor={selectId}>
				{Liferay.Language.get('experience')}
			</label>

			<ExperienceSelector
				editSegmentsEntryURL={config.editSegmentsEntryURL}
				experiences={experiences}
				segments={segments}
				selectId={selectId}
				selectedExperience={selectedExperience}
			/>
		</div>
	);
}
