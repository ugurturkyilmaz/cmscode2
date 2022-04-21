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
import ClayForm, {ClayInput} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

const KeyValueRow = ({
	id,
	onChange = () => {},
	onDelete = () => {},
	showLabels = false,
	valueObject = {key: '', value: ''},
}) => {
	const {key, value} = valueObject;

	return (
		<ClayForm.Group className="key-value-row">
			<ClayInput.Group>
				<ClayInput.GroupItem>
					{showLabels && (
						<label htmlFor={`key-${id}`}>
							{Liferay.Language.get('key')}
						</label>
					)}

					<ClayInput
						id={`key-${id}`}
						onChange={({target: {value: newValue}}) =>
							onChange({key: newValue, value})
						}
						type="text"
						value={key}
					/>
				</ClayInput.GroupItem>

				<ClayInput.GroupItem>
					{showLabels && (
						<label htmlFor={`value-${id}`}>
							{Liferay.Language.get('value')}
						</label>
					)}

					<ClayInput
						id={`value-${id}`}
						onChange={({target: {value: newValue}}) =>
							onChange({key, value: newValue})
						}
						type="text"
						value={value}
					/>
				</ClayInput.GroupItem>

				<ClayInput.GroupItem
					className="delete-button-input-group-item"
					shrink
				>
					<ClayButton
						aria-label={Liferay.Language.get('delete')}
						borderless
						displayType="secondary"
						monospaced
						onClick={onDelete}
					>
						<ClayIcon symbol="times-circle" />
					</ClayButton>
				</ClayInput.GroupItem>
			</ClayInput.Group>
		</ClayForm.Group>
	);
};

const PreviewAttributesModal = ({
	children,
	title = Liferay.Language.get('attributes'),
	description,
	onSubmit = () => {},
}) => {
	const [attributes, setAttributes] = useState([{key: '', value: ''}]);
	const [visible, setVisible] = useState(false);
	const {observer, onClose: handleClose} = useModal({
		onClose: () => setVisible(false),
	});

	const _handleAddRow = () => {
		setAttributes([...attributes, {key: '', value: ''}]);
	};

	/**
	 * Replaces the attribute object at the given index of the attributes array.
	 * @param {number} index The index of the attribute to replace.
	 * @param {object} newAttribute The new attribute object.
	 */
	const _handleChange = (index, newAttribute) => {
		setAttributes(
			attributes.map((attribute, i) => {
				return index === i ? newAttribute : attribute;
			})
		);
	};

	const _handleSubmit = () => {
		onSubmit(attributes);

		handleClose();
	};

	return (
		<>
			{visible && (
				<ClayModal
					className="preview-attributes-modal"
					observer={observer}
					size="md"
				>
					<ClayModal.Header>{title}</ClayModal.Header>

					<ClayModal.Body>
						{description && (
							<p className="text-secondary">{description}</p>
						)}

						{attributes.map((attribute, index) => (
							<KeyValueRow
								id={index}
								key={index}
								onChange={(newValue) =>
									_handleChange(index, newValue)
								}
								onDelete={() =>
									setAttributes(
										attributes.filter((_, i) => index !== i)
									)
								}
								showLabels={index === 0}
								valueObject={attribute}
							/>
						))}

						<ClayForm.Group>
							<ClayButton.Group spaced>
								<ClayButton
									aria-label={Liferay.Language.get(
										'add-field'
									)}
									displayType="secondary"
									monospaced
									onClick={_handleAddRow}
								>
									<ClayIcon symbol="plus" />
								</ClayButton>
							</ClayButton.Group>
						</ClayForm.Group>
					</ClayModal.Body>

					<ClayModal.Footer
						last={
							<ClayButton.Group spaced>
								<ClayButton
									displayType="secondary"
									onClick={handleClose}
								>
									{Liferay.Language.get('cancel')}
								</ClayButton>

								<ClayButton
									displayType="primary"
									onClick={_handleSubmit}
								>
									{Liferay.Language.get('done')}
								</ClayButton>
							</ClayButton.Group>
						}
					/>
				</ClayModal>
			)}

			<span onClick={() => setVisible(!visible)}>{children}</span>
		</>
	);
};

PreviewAttributesModal.propTypes = {
	onSubmit: PropTypes.func,
};

export default PreviewAttributesModal;
