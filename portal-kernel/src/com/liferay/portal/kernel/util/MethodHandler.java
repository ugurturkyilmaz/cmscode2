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

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class MethodHandler implements Serializable {

	public MethodHandler(Method method, Object... arguments) {
		this(new MethodKey(method), arguments);
	}

	public MethodHandler(MethodKey methodKey, Object... arguments) {
		_methodKey = methodKey;
		_arguments = arguments;
	}

	public Object[] getArguments() {
		Object[] arguments = new Object[_arguments.length];

		System.arraycopy(_arguments, 0, arguments, 0, _arguments.length);

		return arguments;
	}

	public MethodKey getMethodKey() {
		return _methodKey;
	}

	public Object invoke() throws Exception {
		Method method = _methodKey.getMethod();

		Object targetObject = null;

		if (!Modifier.isStatic(method.getModifiers())) {
			Class<?> targetClass = _methodKey.getDeclaringClass();

			targetObject = targetClass.newInstance();
		}

		return method.invoke(targetObject, _arguments);
	}

	public Object invoke(Object target) throws Exception {
		Method method = _methodKey.getMethod();

		return method.invoke(target, _arguments);
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{arguments=", Arrays.toString(_arguments), ", methodKey=",
			_methodKey, "}");
	}

	private final Object[] _arguments;
	private final MethodKey _methodKey;

}