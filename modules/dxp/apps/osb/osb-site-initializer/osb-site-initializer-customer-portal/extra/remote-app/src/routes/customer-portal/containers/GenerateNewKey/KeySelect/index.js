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

import {ClayInput} from '@clayui/form';
import {Select} from '../../../../../common/components';

const KeySelect = ({
	avaliableKeysMaximumCount,
	minAvaliableKeysCount,
	selectedClusterNodes,
}) => {
	const emptyOption = {
		disabled: true,
		label: 'Select the option',
		value: '',
	};

	const options = [...Array(minAvaliableKeysCount)].map((_, index) => ({
		label: index + 1,
		value: index + 1,
	}));

	return (
		<ClayInput.Group className="m-0">
			<ClayInput.GroupItem className="m-0">
				<Select
					label={`Cluster Nodes${
						+selectedClusterNodes === +avaliableKeysMaximumCount
							? ' (Maximum)'
							: ''
					}`}
					name="maxClusterNodes"
					options={[emptyOption, ...options]}
					required
				/>

				<h6 className="font-weight-normal ml-3 mt-1">
					{!selectedClusterNodes
						? `Cluster nodes may not exceed the maximum number of Activation Keys available (${avaliableKeysMaximumCount})`
						: `This is the description area`}
				</h6>
			</ClayInput.GroupItem>
		</ClayInput.Group>
	);
};

export default KeySelect;
