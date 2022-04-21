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

import React, {useMemo} from 'react';

import Filter from '../../shared/components/filter/Filter.es';
import {useFilterName} from '../../shared/components/filter/hooks/useFilterName.es';
import {useFilterStatic} from '../../shared/components/filter/hooks/useFilterStatic.es';
import filterConstants from '../../shared/components/filter/util/filterConstants.es';
import {getVelocityUnits} from './util/velocityUnitUtil.es';

export default function VelocityUnitFilter({
	disabled,
	className,
	filterKey = filterConstants.velocityUnit.key,
	options = {},
	prefixKey = '',
	timeRange,
}) {
	options = {
		hideControl: true,
		multiple: false,
		withSelectionTitle: true,
		withoutRouteParams: false,
		...options,
	};
	const velocityUnits = useMemo(() => getVelocityUnits(timeRange), [
		timeRange,
	]);

	const {items, selectedItems} = useFilterStatic({
		filterKey,
		prefixKey,
		staticItems: velocityUnits,
		...options,
	});

	const defaultItem = useMemo(
		() => items.find((item) => item.defaultVelocityUnit) || items[0],
		[items]
	);

	if (defaultItem && options.withSelectionTitle && !selectedItems.length) {
		selectedItems[0] = defaultItem;
	}

	const filterName = useFilterName(
		options.multiple,
		selectedItems,
		Liferay.Language.get('velocity-unit'),
		options.withSelectionTitle
	);

	return (
		<Filter
			defaultItem={defaultItem}
			disabled={disabled}
			elementClasses={className}
			filterKey={filterKey}
			items={items}
			name={filterName}
			prefixKey={prefixKey}
			{...options}
		/>
	);
}
