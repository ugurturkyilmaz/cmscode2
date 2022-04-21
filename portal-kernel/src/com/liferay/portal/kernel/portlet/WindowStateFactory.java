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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 */
public class WindowStateFactory {

	public static WindowState getWindowState(String name) {
		return getWindowState(name, 2);
	}

	public static WindowState getWindowState(
		String name, int portletMajorVersion) {

		if (Validator.isNull(name)) {
			if (portletMajorVersion < 3) {
				return WindowState.NORMAL;
			}

			return WindowState.UNDEFINED;
		}

		WindowState windowState = _windowStates.get(name);

		if (windowState == null) {
			windowState = new WindowState(name);
		}

		return windowState;
	}

	private static final Map<String, WindowState> _windowStates =
		new HashMap<String, WindowState>() {
			{
				try {
					for (Field field : LiferayWindowState.class.getFields()) {
						if (Modifier.isStatic(field.getModifiers()) &&
							(field.getType() == WindowState.class)) {

							WindowState windowState = (WindowState)field.get(
								null);

							put(windowState.toString(), windowState);
						}
					}
				}
				catch (IllegalAccessException illegalAccessException) {
					throw new ExceptionInInitializerError(
						illegalAccessException);
				}
			}
		};

}