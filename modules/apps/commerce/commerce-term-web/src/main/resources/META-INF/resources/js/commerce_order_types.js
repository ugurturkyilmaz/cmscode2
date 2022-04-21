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

import ServiceProvider from 'commerce-frontend-js/ServiceProvider/index';
import itemFinder from 'commerce-frontend-js/components/item_finder/entry';
import {UPDATE_DATASET_DISPLAY} from 'commerce-frontend-js/utilities/eventsDefinitions';
import {openToast} from 'frontend-js-web';

export default function ({
	datasetId,
	rootPortletId,
	termExternalReferenceCode,
	termId,
}) {
	const termOrderTypesResource = ServiceProvider.AdminOrderAPI('v1');

	function selectItem(orderType) {
		const orderTypeData = {
			orderTypeExternalReferenceCode: orderType.externalReferenceCode,
			orderTypeId: orderType.id,
			termExternalReferenceCode,
			termId,
		};

		return termOrderTypesResource
			.addTermOrderType(termId, orderTypeData)
			.then(() => {
				Liferay.fire(UPDATE_DATASET_DISPLAY, {
					id: datasetId,
				});
			})
			.catch((error) => {
				const errorsMap = {
					'the-qualifier-is-already-linked': Liferay.Language.get(
						'the-qualifier-is-already-linked'
					),
				};

				openToast({
					message:
						errorsMap[error.message] ||
						Liferay.Language.get('an-unexpected-error-occurred'),
					title: Liferay.Language.get('error'),
					type: 'danger',
				});
			});
	}

	itemFinder('itemFinder', 'item-finder-root-order-types', {
		apiUrl: '/o/headless-commerce-admin-order/v1.0/order-types/',
		getSelectedItems: () => Promise.resolve([]),
		inputPlaceholder: Liferay.Language.get('find-an-order-type'),
		itemCreation: false,
		itemSelectedMessage: Liferay.Language.get('order-type-selected'),
		itemsKey: 'id',
		linkedDatasetsId: [datasetId],
		onItemSelected: selectItem,
		pageSize: 10,
		panelHeaderLabel: Liferay.Language.get('add-order-types'),
		portletId: rootPortletId,
		schema: [
			{
				fieldName: ['name', 'LANG'],
			},
		],
		titleLabel: Liferay.Language.get('add-existing-order-type'),
	});
}
