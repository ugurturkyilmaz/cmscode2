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

import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

import {logError} from '../utils/logError';
import TooltipTextRenderer from './TooltipTextRenderer';

const Wrapper = ({options, ...props}) => {
	return options?.truncate ? (
		<span
			className={classNames(
				'default-renderer__text-truncate',
				'text-truncate'
			)}
		>
			{props.children}
		</span>
	) : (
		<>{props.children}</>
	);
};
function DefaultRenderer({options, value}) {
	if (
		typeof value === 'number' ||
		typeof value === 'string' ||
		value === undefined ||
		value === null
	) {
		return <Wrapper options={options}>{value ?? ''}</Wrapper>;
	}
	else if (value.icon) {
		return <ClayIcon symbol={value.icon} />;
	}
	else if (!!value.iconSymbol && !!value.text) {
		return <TooltipTextRenderer value={value} />;
	}
	else if (value) {
		return <Wrapper options={options}>{value}</Wrapper>;
	}

	logError(
		`The object ${JSON.stringify(value)} doesn't match the template schema`
	);

	return null;
}

DefaultRenderer.propTypes = {
	value: PropTypes.oneOfType([
		PropTypes.string,
		PropTypes.number,
		PropTypes.shape({
			label: PropTypes.string,
		}),
		PropTypes.shape({
			icon: PropTypes.string,
		}),
		PropTypes.shape({
			iconSymbol: PropTypes.string,
			text: PropTypes.string,
		}),
	]),
};

export default DefaultRenderer;
