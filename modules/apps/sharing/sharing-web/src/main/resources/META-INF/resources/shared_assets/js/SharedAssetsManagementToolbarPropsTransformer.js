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

import {addParams, navigate, openSelectionModal} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {selectAssetTypeURL, viewAssetTypeURL},
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onFilterDropdownItemClick(event, {item}) {
			if (item?.data?.action === 'openAssetTypesSelector') {
				openSelectionModal({
					onSelect: (selectedItem) => {
						if (selectedItem) {
							const uri = addParams(
								`${portletNamespace}className=${selectedItem.value}`,
								viewAssetTypeURL
							);

							navigate(uri);
						}
					},
					selectEventName: `${portletNamespace}selectAssetType`,
					title: Liferay.Language.get('select-asset-type'),
					url: selectAssetTypeURL,
				});
			}
		},
	};
}
