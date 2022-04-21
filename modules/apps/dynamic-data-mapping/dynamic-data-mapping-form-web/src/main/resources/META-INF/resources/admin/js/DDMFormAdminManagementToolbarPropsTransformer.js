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

import {postForm} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {deleteFormInstanceURL, deleteStructureURL},
	portletNamespace,
	...otherProps
}) {
	const deleteFormInstances = () => {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			const form = document.getElementById(
				`${portletNamespace}searchContainerForm`
			);

			const searchContainer = document.getElementById(
				otherProps.searchContainerId
			);

			if (form && searchContainer) {
				postForm(form, {
					data: {
						deleteFormInstanceIds: Liferay.Util.listCheckedExcept(
							searchContainer,
							`${portletNamespace}allRowIds`
						),
					},
					url: deleteFormInstanceURL,
				});
			}
		}
	};

	const deleteStructures = () => {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			const form = document.getElementById(
				`${portletNamespace}searchContainerForm`
			);

			const searchContainer = document.getElementById(
				otherProps.searchContainerId
			);

			if (form && searchContainer) {
				postForm(form, {
					data: {
						deleteStructureIds: Liferay.Util.listCheckedExcept(
							searchContainer,
							`${portletNamespace}allRowIds`
						),
					},
					url: deleteStructureURL,
				});
			}
		}
	};

	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			const action = item?.data?.action;

			if (action === 'deleteFormInstances') {
				deleteFormInstances();
			}
			else if (action === 'deleteStructures') {
				deleteStructures();
			}
		},
	};
}
