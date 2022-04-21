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

package com.liferay.user.associated.data.web.internal.display;

/**
 * @author Drew Brokke
 */
public class DummyEntryUADDisplay extends BaseDummyUADDisplay<DummyEntry> {

	public DummyEntryUADDisplay(DummyService<DummyEntry> dummyEntryService) {
		_dummyEntryService = dummyEntryService;
	}

	@Override
	public Class<DummyEntry> getTypeClass() {
		return DummyEntry.class;
	}

	@Override
	public boolean isInTrash(DummyEntry dummyEntry) {
		return false;
	}

	@Override
	protected DummyService<DummyEntry> getDummyService() {
		return _dummyEntryService;
	}

	private final DummyService<DummyEntry> _dummyEntryService;

}