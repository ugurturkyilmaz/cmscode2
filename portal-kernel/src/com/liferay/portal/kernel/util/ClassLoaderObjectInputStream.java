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

import com.liferay.petra.lang.ClassResolverUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ClassLoaderObjectInputStream extends ObjectInputStream {

	public ClassLoaderObjectInputStream(
			InputStream inputStream, ClassLoader classLoader)
		throws IOException {

		super(inputStream);

		_classLoader = classLoader;
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException {

		return ClassResolverUtil.resolve(
			objectStreamClass.getName(), _classLoader);
	}

	private final ClassLoader _classLoader;

}