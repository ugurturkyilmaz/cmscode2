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

import ClayLayout from '@clayui/layout';
import React, {useContext} from 'react';

import {AutocompleteMultiSelect} from '../../../../shared/components/autocomplete/AutocompleteMultiSelect.es';
import FieldLabel from '../../../../shared/components/form/FieldLabel.es';
import FormGroupWithStatus from '../../../../shared/components/form/FormGroupWithStatus.es';
import {
	ALERT_MESSAGE,
	PAUSE_NODE_KEYS,
	START_NODE_KEYS,
	STOP_NODE_KEYS,
} from '../SLAFormConstants.es';
import {SLAFormContext} from '../SLAFormPageProvider.es';
import {validateNodeKeys} from '../util/slaFormUtil.es';

export default function TimeFrameSection() {
	const {
		changeNodesKeys,
		changePauseNodes,
		errors,
		getNodeTags,
		getPauseNodeTags,
		getPauseNodes,
		getStartNodes,
		getStopNodes,
		nodes,
		setErrors,
		sla: {
			pauseNodeKeys: {nodeKeys: pauseNodeKeys},
			startNodeKeys: {nodeKeys: startNodeKeys},
			stopNodeKeys: {nodeKeys: stopNodeKeys},
		},
	} = useContext(SLAFormContext);

	const checkNodeErrors = (type) => (filteredNodeKeys) => {
		if (type !== PAUSE_NODE_KEYS) {
			errors[type] = validateNodeKeys(filteredNodeKeys);
		}

		setErrors({...errors, [ALERT_MESSAGE]: ''});
	};

	return (
		<>
			<h3 className="sheet-subtitle">
				<FieldLabel
					htmlFor="slaTimeStart"
					text={Liferay.Language.get('time-frame').toUpperCase()}
				/>
			</h3>

			<div className="sheet-text">
				{Liferay.Language.get(
					'define-when-time-should-be-tracked-based-on-workflow-steps'
				)}
			</div>

			<ClayLayout.Row>
				<ClayLayout.Col>
					<FormGroupWithStatus
						className="form-group"
						error={errors[START_NODE_KEYS]}
						htmlFor="slaTimeStart"
						label={Liferay.Language.get('start')}
						requiredLabel
					>
						<div className="form-text">
							{Liferay.Language.get(
								'time-will-begin-counting-when'
							)}
						</div>

						<AutocompleteMultiSelect
							fieldId="compositeId"
							fieldName="desc"
							id="start"
							items={getStartNodes(pauseNodeKeys, stopNodeKeys)}
							onChange={changeNodesKeys(
								START_NODE_KEYS,
								nodes,
								checkNodeErrors(START_NODE_KEYS)
							)}
							selectedItems={getNodeTags(
								getStartNodes(pauseNodeKeys, stopNodeKeys),
								startNodeKeys
							)}
						/>
					</FormGroupWithStatus>
				</ClayLayout.Col>
			</ClayLayout.Row>
			<ClayLayout.Row>
				<ClayLayout.Col>
					<FormGroupWithStatus
						className="form-group"
						htmlFor="slaTimePause"
						label={Liferay.Language.get('pause')}
					>
						<div className="form-text">
							{Liferay.Language.get(
								'time-wont-be-considered-when'
							)}
						</div>

						<AutocompleteMultiSelect
							fieldId="compositeId"
							fieldName="desc"
							id="pause"
							items={getPauseNodes(startNodeKeys, stopNodeKeys)}
							onChange={changePauseNodes(
								getPauseNodes(startNodeKeys, stopNodeKeys),
								checkNodeErrors(PAUSE_NODE_KEYS)
							)}
							selectedItems={getPauseNodeTags(
								getPauseNodes(startNodeKeys, stopNodeKeys),
								pauseNodeKeys
							)}
						/>
					</FormGroupWithStatus>
				</ClayLayout.Col>
			</ClayLayout.Row>
			<ClayLayout.Row>
				<ClayLayout.Col>
					<FormGroupWithStatus
						className="form-group"
						error={errors[STOP_NODE_KEYS]}
						htmlFor="slaTimeStop"
						label={Liferay.Language.get('stop')}
						requiredLabel
					>
						<div className="form-text">
							{Liferay.Language.get(
								'time-will-stop-counting-when'
							)}
						</div>

						<AutocompleteMultiSelect
							fieldId="compositeId"
							fieldName="desc"
							id="stop"
							items={getStopNodes(pauseNodeKeys, startNodeKeys)}
							onChange={changeNodesKeys(
								STOP_NODE_KEYS,
								nodes,
								checkNodeErrors(STOP_NODE_KEYS)
							)}
							selectedItems={getNodeTags(
								getStopNodes(pauseNodeKeys, startNodeKeys),
								stopNodeKeys
							)}
						/>
					</FormGroupWithStatus>
				</ClayLayout.Col>
			</ClayLayout.Row>
		</>
	);
}
