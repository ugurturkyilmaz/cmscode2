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

import React, {useContext, useState} from 'react';

const SelectedMenuItemIdContext = React.createContext(null);
const SetSelectedMenuItemIdContext = React.createContext(() => {});

export function useSetSelectedMenuItemId() {
	return useContext(SetSelectedMenuItemIdContext);
}

export function useSelectedMenuItemId() {
	return useContext(SelectedMenuItemIdContext);
}

export function SelectedMenuItemIdProvider({children}) {
	const [selectedMenuItemId, setSelectedMenuItemId] = useState(null);

	return (
		<SetSelectedMenuItemIdContext.Provider value={setSelectedMenuItemId}>
			<SelectedMenuItemIdContext.Provider value={selectedMenuItemId}>
				{children}
			</SelectedMenuItemIdContext.Provider>
		</SetSelectedMenuItemIdContext.Provider>
	);
}
