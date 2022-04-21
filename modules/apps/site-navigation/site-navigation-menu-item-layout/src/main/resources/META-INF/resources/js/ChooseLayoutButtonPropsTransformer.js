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

import {openSelectionModal} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {eventName, itemSelectorURL},
	portletNamespace,
	...props
}) {
	const url = new URL(itemSelectorURL);

	return {
		...props,
		onClick() {
			const groupIdInput = document.getElementById(
				`${portletNamespace}groupId`
			);

			const layoutItemRemoveButton = document.getElementById(
				`${portletNamespace}layoutItemRemove`
			);

			const layoutNameInput = document.getElementById(
				`${portletNamespace}layoutNameInput`
			);

			const layoutUuidInput = document.getElementById(
				`${portletNamespace}layoutUuid`
			);

			const privateLayoutInput = document.getElementById(
				`${portletNamespace}privateLayout`
			);

			openSelectionModal({
				buttonAddLabel: Liferay.Language.get('select'),
				multiple: true,
				onSelect: (selectedItem) => {
					if (selectedItem) {
						groupIdInput.value = selectedItem.groupId;
						layoutUuidInput.value = selectedItem.id;
						layoutNameInput.textContent = selectedItem.name;
						privateLayoutInput.value = selectedItem.privateLayout;

						url.searchParams.set(
							`${Liferay.Util.getPortletNamespace(
								Liferay.PortletKeys.ITEM_SELECTOR
							)}layoutUuid`,
							selectedItem.id
						);

						layoutItemRemoveButton.classList.remove('hide');
					}
				},
				selectEventName: eventName,
				title: Liferay.Language.get('select-layout'),
				url: url.href,
			});
		},
	};
}
