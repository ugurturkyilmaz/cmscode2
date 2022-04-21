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

import ClayButton from '@clayui/button';
import ClayForm from '@clayui/form';
import ClayIcon from '@clayui/icon';
import React from 'react';

import FieldRow from './FieldRow';

function FieldListInput({
	disabled,
	id,
	indexFields,
	name,
	setFieldTouched,
	setFieldValue,
	showBoost,
	value,
}) {
	const _handleBlur = () => {
		setFieldTouched(name);
	};

	const _handleChange = (index) => (newValue) => {
		setFieldValue(`${name}[${index}]`, {...value[index], ...newValue});
	};

	const _handleFieldRowAdd = () => {
		setFieldValue(name, [...value, {field: ''}]);
	};

	const _handleFieldRowDelete = (index) => () => {
		_handleBlur();

		setFieldValue(
			name,
			value.filter((_, i) => index !== i)
		);
	};

	return (
		<div className="field">
			{value?.map((item, index) => (
				<FieldRow
					boost={item.boost}
					disabled={disabled}
					field={item.field}
					id={index === 0 ? id : `${id}_${index}`}
					index={index}
					indexFields={indexFields}
					key={index}
					languageIdPosition={item.languageIdPosition}
					locale={item.locale}
					onBlur={_handleBlur}
					onChange={_handleChange(index)}
					onDelete={_handleFieldRowDelete(index)}
					showBoost={showBoost}
				/>
			))}

			<ClayForm.Group className="add-remove-field">
				<ClayButton.Group spaced>
					<ClayButton
						aria-label={Liferay.Language.get('add-field')}
						disabled={disabled}
						displayType="secondary"
						monospaced
						onClick={_handleFieldRowAdd}
						small
					>
						<ClayIcon symbol="plus" />
					</ClayButton>
				</ClayButton.Group>
			</ClayForm.Group>
		</div>
	);
}

export default React.memo(FieldListInput);
