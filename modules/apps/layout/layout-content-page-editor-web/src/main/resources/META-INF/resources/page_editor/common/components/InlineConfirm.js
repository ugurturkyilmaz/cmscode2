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
import {useIsMounted} from '@liferay/frontend-js-react-web';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import Button from './Button';

export default function InlineConfirm({
	cancelButtonLabel,
	confirmButtonLabel,
	message,
	onCancelButtonClick,
	onConfirmButtonClick,
}) {
	const [performingAction, setPerformingAction] = useState(false);
	const wrapperRef = useRef(null);
	const isMounted = useIsMounted();

	const _handleConfirmButtonClick = () => {
		if (wrapperRef.current) {
			wrapperRef.current.focus();
		}

		setPerformingAction(true);

		onConfirmButtonClick().then(() => {
			if (isMounted()) {
				setPerformingAction(false);
			}
		});
	};

	useEffect(() => {
		if (wrapperRef.current) {
			wrapperRef.current.focus();
		}
	}, []);

	useEffect(() => {
		if (wrapperRef.current) {
			const confirmButton = wrapperRef.current.querySelector(
				'page-editor__inline-confirm-button'
			);

			if (confirmButton) {
				confirmButton.focus();
			}
		}

		const _handleDocumentFocusOut = () => {
			requestAnimationFrame(() => {
				if (wrapperRef.current && !performingAction) {
					if (
						!wrapperRef.current.contains(document.activeElement) &&
						wrapperRef.current !== document.activeElement
					) {
						onCancelButtonClick();
					}
				}
			});
		};

		document.addEventListener('focusout', _handleDocumentFocusOut, true);

		return () =>
			window.removeEventListener(
				'focusout',
				_handleDocumentFocusOut,
				true
			);
	}, [performingAction, onCancelButtonClick]);

	return (
		<div
			className="page-editor__inline-confirm"
			onKeyDown={(event) =>
				event.key === 'Escape' && onCancelButtonClick()
			}
			ref={wrapperRef}
			role="alertdialog"
			tabIndex="-1"
		>
			<p className="text-center text-secondary">
				<strong>{message}</strong>
			</p>

			<ClayButton.Group spaced>
				<Button
					className="page-editor__inline-confirm-button"
					disabled={performingAction}
					displayType="primary"
					loading={performingAction}
					onClick={_handleConfirmButtonClick}
					small
				>
					{confirmButtonLabel}
				</Button>

				<Button
					disabled={performingAction}
					displayType="secondary"
					onClick={onCancelButtonClick}
					small
					type="button"
				>
					{cancelButtonLabel}
				</Button>
			</ClayButton.Group>
		</div>
	);
}

InlineConfirm.propTypes = {
	cancelButtonLabel: PropTypes.string,
	confirmButtonLabel: PropTypes.string,
	message: PropTypes.string,
	onCancelButtonClick: PropTypes.func,
	onConfirmButtonClick: PropTypes.func,
};
