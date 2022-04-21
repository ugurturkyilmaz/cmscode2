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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.PortletURLListener;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLListenerImpl implements PortletURLListener {

	public PortletURLListenerImpl(
		String listenerClass, int ordinal, PortletApp portletApp) {

		_listenerClass = listenerClass;
		_ordinal = ordinal;
		_portletApp = portletApp;
	}

	@Override
	public String getListenerClass() {
		return _listenerClass;
	}

	@Override
	public int getOrdinal() {
		return _ordinal;
	}

	@Override
	public PortletApp getPortletApp() {
		return _portletApp;
	}

	@Override
	public void setListenerClass(String listenerClass) {
		_listenerClass = listenerClass;
	}

	@Override
	public void setOrdinal(int ordinal) {
		_ordinal = ordinal;
	}

	@Override
	public void setPortletApp(PortletApp portletApp) {
		_portletApp = portletApp;
	}

	private String _listenerClass;
	private int _ordinal;
	private PortletApp _portletApp;

}