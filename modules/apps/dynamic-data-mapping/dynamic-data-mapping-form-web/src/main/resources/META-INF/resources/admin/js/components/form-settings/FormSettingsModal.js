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
import ClayModal, {useModal} from '@clayui/modal';
import {
	EVENT_TYPES as CORE_EVENT_TYPES,
	PagesVisitor,
	useConfig,
	useForm,
	useFormState,
} from 'data-engine-js-components-web';
import React, {useEffect} from 'react';

const FormSettingsModal = ({
	children,
	onCloseFormSettings,
	prevPagesRef,
	serializedSettingsContext,
	undoPagesRef,
	visibleFormSettings,
}) => {
	const {spritemap} = useConfig();
	const {pages} = useFormState();
	const dispatch = useForm();

	useEffect(() => {
		if (serializedSettingsContext.value) {
			const pages = JSON.parse(serializedSettingsContext.value).pages;

			dispatch({
				payload: pages,
				type: CORE_EVENT_TYPES.PAGE.UPDATE,
			});

			prevPagesRef.current = pages;
		}

		serializedSettingsContext.value = JSON.stringify({
			pages: prevPagesRef.current,
		});
	}, [dispatch, prevPagesRef, serializedSettingsContext]);

	const {observer, onClose} = useModal({
		onClose: () => {
			if (undoPagesRef.current) {
				dispatch({
					payload: prevPagesRef.current,
					type: CORE_EVENT_TYPES.PAGE.UPDATE,
				});

				serializedSettingsContext.value = JSON.stringify({
					pages: prevPagesRef.current,
				});
			}
			else {
				serializedSettingsContext.value = JSON.stringify({
					pages,
				});
			}

			const visitor = new PagesVisitor(pages);

			const showPartialResultsToRespondents = visitor.findField(
				({fieldName}) => fieldName === 'showPartialResultsToRespondents'
			)?.value;

			const alertElement = document.querySelector(
				'.lfr-ddm__show-partial-results-alert'
			);

			if (showPartialResultsToRespondents) {
				alertElement.classList.remove(
					'lfr-ddm__show-partial-results-alert--hidden'
				);
			}
			else {
				alertElement.classList.add(
					'lfr-ddm__show-partial-results-alert--hidden'
				);
			}

			undoPagesRef.current = true;
			onCloseFormSettings();
		},
	});

	return (
		<>
			{visibleFormSettings && (
				<ClayModal
					className="ddm-form-settings-modal"
					observer={observer}
					spritemap={spritemap}
				>
					<ClayModal.Header>
						{Liferay.Language.get('settings')}
					</ClayModal.Header>

					<ClayModal.Body>{children}</ClayModal.Body>

					<ClayModal.Footer
						last={
							<ClayButton.Group key={1} spaced>
								<ClayButton
									displayType="secondary"
									onClick={() => {
										undoPagesRef.current = true;

										onClose();
									}}
								>
									{Liferay.Language.get('cancel')}
								</ClayButton>

								<ClayButton
									displayType="primary"
									onClick={() => {
										prevPagesRef.current = pages;
										undoPagesRef.current = false;

										onClose();
									}}
								>
									{Liferay.Language.get('done')}
								</ClayButton>
							</ClayButton.Group>
						}
					/>
				</ClayModal>
			)}
		</>
	);
};

export default FormSettingsModal;
