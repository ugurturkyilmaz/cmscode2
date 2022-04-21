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
import ClayProgressBar from '@clayui/progress-bar';
import PropTypes from 'prop-types';
import React from 'react';

const ProgressWrapper = ({fileName, onCancel, progressData, progressValue}) => (
	<div className="progress-wrapper">
		<p className="file-name">{fileName}</p>

		<ClayProgressBar className="progressbar" value={progressValue} />

		<p
			className="progress-data size"
			dangerouslySetInnerHTML={{
				__html: progressData,
			}}
		/>

		<ClayButton displayType="primary" onClick={onCancel}>
			{Liferay.Language.get('cancel')}
		</ClayButton>
	</div>
);

ProgressWrapper.propTypes = {
	fileName: PropTypes.string,
	onCancel: PropTypes.func.isRequired,
};

export default ProgressWrapper;
