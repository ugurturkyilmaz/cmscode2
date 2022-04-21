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

import {openModal} from 'frontend-js-web';

import {openShareFormModal} from './components/share-form/openShareFormModal.es';

const ACTIONS = {
	delete({deleteFormInstanceURL}) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(document.hrefFm, deleteFormInstanceURL);
		}
	},

	exportForm({exportFormURL}) {
		Liferay.fire('openExportFormModal', {exportFormURL});
	},

	permissions({permissionsFormInstanceURL}) {
		openModal({
			title: Liferay.Language.get('permissions'),
			url: permissionsFormInstanceURL,
		});
	},

	shareForm({
		autocompleteUserURL,
		localizedName,
		portletNamespace,
		shareFormInstanceURL,
		url,
	}) {
		openShareFormModal({
			autocompleteUserURL,
			localizedName,
			portletNamespace,
			shareFormInstanceURL,
			spritemap: `${Liferay.ThemeDisplay.getPathThemeImages()}/clay/icons.svg`,
			url,
		});
	},
};

export default function propsTransformer({items: groups, ...props}) {
	return {
		...props,
		items: groups.map((group) => {
			return {
				...group,
				items: group.items?.map((item) => ({
					...item,
					onClick(event) {
						const action = item.data?.action;

						if (action) {
							event.preventDefault();

							ACTIONS[action](item.data);
						}
					},
				})),
			};
		}),
	};
}
