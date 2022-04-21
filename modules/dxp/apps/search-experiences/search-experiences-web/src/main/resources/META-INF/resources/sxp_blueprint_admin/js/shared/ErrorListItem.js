/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayAlert from '@clayui/alert';
import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import ClayLayout from '@clayui/layout';
import getCN from 'classnames';
import React, {useState} from 'react';

import {TEST_IDS} from '../utils/testIds';

const CONFIGURATION_FIELD_NAME = {
	advanced_configuration: Liferay.Language.get('advanced-configuration'),
	aggregation_configuration: Liferay.Language.get(
		'aggregation-configuration'
	),
	highlight_configuration: Liferay.Language.get('highlight-configuration'),
	parameter_configuration: Liferay.Language.get('parameter-configuration'),
	query_configuration: Liferay.Language.get('query-configuration'),
	sort_configuration: Liferay.Language.get('sort-configuration'),
	sxpElementTemplateJSON: Liferay.Language.get('element-template'),
	uiConfigurationJSON: Liferay.Language.get('ui-configuration'),
};

// `error` object properties that should not be displayed.

const ERROR_OMIT_KEYS = [
	'className',
	'sxpElementId',
	'localizedMessage',
	'msg',
	'throwable',
	'severity',
];

// Types from search-experiences-blueprints-api/src/main/java/com/liferay/search/experiences/blueprints/message/Severity.java

const SEVERITY_DISPLAY_TYPE = {
	ERROR: 'danger',
	INFO: 'info',
	WARN: 'warning',
};

/**
 * Gets the user-friendly field name for the given JSON property key.
 *
 * For example:
 * getConfigurationFieldName('advanced_configuration')
 * => 'Advanced Configuration'
 *
 * @param {string} rootProperty The blueprint configuration JSON property key.
 * @returns {string}
 */
function getConfigurationFieldName(rootProperty) {
	const configName = Object.keys(CONFIGURATION_FIELD_NAME).find((key) =>
		rootProperty?.includes(key)
	);

	return configName ? CONFIGURATION_FIELD_NAME[configName] : '';
}

const prettyPrint = (value) => {
	return JSON.stringify(value, null, 2);
};

/**
 * Displays an alert depending on the `error` object properties.
 * Used in the preview sidebar.
 */
function ErrorListItem({error, onFocusSXPElement}) {
	const [collapse, setCollapse] = useState(true);

	const _getDescription = () => {
		const configurationFieldName = getConfigurationFieldName(
			error.rootProperty
		);

		return configurationFieldName
			? `${error.msg} (${configurationFieldName})`
			: error.msg;
	};

	const _handleCollapse = () => {
		setCollapse(!collapse);
	};

	const _handleFocusSXPElement = () => {
		onFocusSXPElement(error.sxpElementId);
	};

	const _isCollapsible = () => {
		return Object.keys(error).some(
			(property) => error[property] && !ERROR_OMIT_KEYS.includes(property)
		);
	};

	return (
		<ClayAlert
			className={getCN('error-list-item', {
				collapsible: _isCollapsible(),
			})}
			data-testid={TEST_IDS.ERROR_LIST_ITEM}
			displayType={SEVERITY_DISPLAY_TYPE[error.severity] || 'danger'}
		>
			<span className="message" onClick={_handleCollapse}>
				<span className="title">
					{error.localizedMessage || Liferay.Language.get('error')}
				</span>

				{error.msg && (
					<span className="description">{_getDescription()}</span>
				)}
			</span>

			{!!error.sxpElementId && (
				<div className="scroll-button">
					<ClayButton alert onClick={_handleFocusSXPElement} small>
						{Liferay.Language.get('view-element')}
					</ClayButton>
				</div>
			)}

			{_isCollapsible() && (
				<ClayButtonWithIcon
					aria-label={
						collapse
							? Liferay.Language.get('expand')
							: Liferay.Language.get('collapse')
					}
					borderless
					className="collapse-button text-danger"
					displayType="unstyled"
					onClick={_handleCollapse}
					small
					symbol={collapse ? 'angle-right' : 'angle-down'}
				/>
			)}

			{!collapse && _isCollapsible() && (
				<ClayAlert.Footer>
					{Object.keys(error).map(
						(property) =>
							error[property] &&
							!ERROR_OMIT_KEYS.includes(property) && (
								<ClayLayout.Row justify="start" key={property}>
									<ClayLayout.Col
										className="property"
										size={3}
									>
										{property}
									</ClayLayout.Col>

									<ClayLayout.Col size={9}>
										<code>
											{typeof error[property] === 'object'
												? prettyPrint(error[property])
												: error[property]}
										</code>
									</ClayLayout.Col>
								</ClayLayout.Row>
							)
					)}
				</ClayAlert.Footer>
			)}
		</ClayAlert>
	);
}

export default ErrorListItem;
