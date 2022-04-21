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

import ClayIcon from '@clayui/icon';
import ClayTable from '@clayui/table';
import React, {useCallback, useMemo} from 'react';

import {Autocomplete} from '../../../../../shared/components/autocomplete/Autocomplete.es';
import {useFetch} from '../../../../../shared/hooks/useFetch.es';

function AssigneeInput({setAssigneeId, taskId}) {
	const {data, fetchData} = useFetch({
		admin: true,
		params: {page: -1, pageSize: -1},
		url: `/workflow-tasks/${taskId}/assignable-users`,
	});

	const handleSelect = useCallback(
		(newAssignee) => {
			const assigneeId = newAssignee ? newAssignee.id : undefined;

			setAssigneeId(assigneeId);
		},
		[setAssigneeId]
	);

	const promises = useMemo(
		() => [fetchData()],

		// eslint-disable-next-line react-hooks/exhaustive-deps
		[]
	);

	return (
		<Autocomplete
			items={data?.items}
			onSelect={handleSelect}
			promises={promises}
		/>
	);
}

function Item({
	assigneePerson = {name: Liferay.Language.get('unassigned')},
	id,
	objectReviewed: {assetTitle, assetType},
	setAssigneeId,
	label,
	workflowInstanceId,
}) {
	return (
		<ClayTable.Row>
			<ClayTable.Cell style={{fontWeight: 'bold'}}>
				{workflowInstanceId}
			</ClayTable.Cell>

			<ClayTable.Cell>{`${assetType}: ${assetTitle}`} </ClayTable.Cell>

			<ClayTable.Cell>{label}</ClayTable.Cell>

			<ClayTable.Cell>{assigneePerson.name}</ClayTable.Cell>

			<ClayTable.Cell>
				<Table.AssigneeInput
					setAssigneeId={setAssigneeId}
					taskId={id}
				/>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

function Table({items, setAssigneeId}) {
	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row>
					<ClayTable.Cell
						headingCell
						style={{
							color: 'inherit',
							fontWeight: 'bold',
							width: '10%',
						}}
					>
						{Liferay.Language.get('id')}
					</ClayTable.Cell>

					<ClayTable.Cell
						headingCell
						style={{
							color: 'inherit',
							fontWeight: 'bold',
							width: '25%',
						}}
					>
						{Liferay.Language.get('item-subject')}
					</ClayTable.Cell>

					<ClayTable.Cell
						headingCell
						style={{
							color: 'inherit',
							fontWeight: 'bold',
							width: '20%',
						}}
					>
						{Liferay.Language.get('process-step')}
					</ClayTable.Cell>

					<ClayTable.Cell
						style={{
							color: 'inherit',
							fontWeight: 'bold',
							width: '20%',
						}}
					>
						{Liferay.Language.get('current-assignee')}
					</ClayTable.Cell>

					<ClayTable.Cell
						style={{
							color: 'inherit',
							fontWeight: 'bold',
							width: '25%',
						}}
					>
						{`${Liferay.Language.get('new-assignee')}`}

						<span
							className="ml-1 workflow-tooltip"
							data-tooltip-align="top"
							data-tooltip-delay="0"
							title={Liferay.Language.get(
								'possible-assignees-must-have-permissions-to-be-assigned-to-the-corresponding-step'
							)}
						>
							<ClayIcon symbol="question-circle-full" />
						</span>
					</ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>

			<ClayTable.Body>
				{items &&
					items.length > 0 &&
					items.map((item, index) => (
						<Table.Item
							{...item}
							key={index}
							setAssigneeId={setAssigneeId}
						/>
					))}
			</ClayTable.Body>
		</ClayTable>
	);
}

Table.AssigneeInput = AssigneeInput;
Table.Item = Item;

export default Table;
