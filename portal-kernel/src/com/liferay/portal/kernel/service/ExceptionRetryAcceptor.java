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

package com.liferay.portal.kernel.service;

import java.util.Map;

/**
 * @author Matthew Tambara
 */
public class ExceptionRetryAcceptor implements RetryAcceptor {

	public static final String EXCEPTION_NAME = "EXCEPTION_NAME";

	@Override
	public boolean acceptException(
		Throwable throwable, Map<String, String> propertyMap) {

		String name = propertyMap.get(EXCEPTION_NAME);

		if (name == null) {
			throw new IllegalArgumentException(
				"Missing property " + EXCEPTION_NAME);
		}

		while (true) {
			Class<?> clazz = throwable.getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			if (classLoader == null) {
				classLoader = ClassLoader.getSystemClassLoader();
			}

			try {
				Class<?> exceptionClass = classLoader.loadClass(name);

				if (exceptionClass.isInstance(throwable)) {
					return true;
				}
			}
			catch (ClassNotFoundException classNotFoundException) {
			}

			Throwable causeThrowable = throwable.getCause();

			if ((throwable == causeThrowable) || (causeThrowable == null)) {
				break;
			}

			throwable = causeThrowable;
		}

		return false;
	}

	@Override
	public boolean acceptResult(
		Object returnValue, Map<String, String> propertyMap) {

		return false;
	}

}