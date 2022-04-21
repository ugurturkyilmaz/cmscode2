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

import {PortletBase, openSelectionModal} from 'frontend-js-web';
import {Config} from 'metal-state';

/**
 * @class SelectFolderButton
 * It adds a listener to a #selectFolderButton DOM element
 * and allows selecting a directory with itemSelector
 * @review
 */
class SelectFolderButton extends PortletBase {

	/**
	 * @inheritdoc
	 * @review
	 */
	created() {
		const selectFolderButton = document.getElementById(
			this.ns('selectFolderButton')
		);

		if (selectFolderButton) {
			this._handleSelectFolderButtonClick = this._handleSelectFolderButtonClick.bind(
				this
			);

			selectFolderButton.addEventListener(
				'click',
				this._handleSelectFolderButtonClick
			);
		}
	}

	/**
	 * @inheritdoc
	 * @review
	 */
	disposed() {
		const selectFolderButton = document.getElementById(
			this.ns('selectFolderButton')
		);

		if (selectFolderButton) {
			selectFolderButton.removeEventListener(
				'click',
				this._handleSelectFolderButtonClick
			);
		}
	}

	/**
	 * @private
	 * @review
	 */
	_handleSelectFolderButtonClick() {
		openSelectionModal({
			iframeBodyCssClass: '',
			onSelect: (selectedItem) => {
				if (selectedItem) {
					var folderData = {
						idString: this.inputName,
						idValue: selectedItem.folderId,
						nameString: 'folderName',
						nameValue: selectedItem.folderName,
					};

					Liferay.Util.selectFolder(
						folderData,
						this.namespace || this.portletNamespace
					);
				}
			},
			selectEventName: this.ns('selectFolder'),
			title: Liferay.Language.get('select-folder'),
			url: this.selectFolderURL,
		});
	}
}

/**
 * @memberof MoveEntries
 * @review
 * @static
 */
SelectFolderButton.STATE = {

	/**
	 * @default undefined
	 * @memberof inputName
	 * @required
	 * @review
	 * @type {string}
	 */
	inputName: Config.string().required(),

	/**
	 * @default undefined
	 * @memberof SelectFolderButton
	 * @required
	 * @review
	 * @type {string}
	 */
	selectFolderURL: Config.string().required(),
};

export default SelectFolderButton;
