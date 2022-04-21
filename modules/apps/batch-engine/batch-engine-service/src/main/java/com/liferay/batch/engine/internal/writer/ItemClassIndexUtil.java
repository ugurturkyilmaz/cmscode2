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

package com.liferay.batch.engine.internal.writer;

import com.liferay.petra.concurrent.ConcurrentReferenceKeyHashMap;
import com.liferay.petra.concurrent.ConcurrentReferenceValueHashMap;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.petra.string.CharPool;

import java.lang.ref.Reference;
import java.lang.reflect.Field;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class ItemClassIndexUtil {

	public static Map<String, Field> index(Class<?> itemClass) {
		return _fieldsMap.computeIfAbsent(
			itemClass,
			clazz -> {
				Map<String, Field> fieldMap = new HashMap<>();

				while (clazz != Object.class) {
					for (Field field : clazz.getDeclaredFields()) {
						Class<?> valueClass = field.getType();

						if (!valueClass.isPrimitive() &&
							!_objectTypes.contains(valueClass) &&
							!Enum.class.isAssignableFrom(valueClass)) {

							continue;
						}

						field.setAccessible(true);

						String name = field.getName();

						if (name.charAt(0) == CharPool.UNDERLINE) {
							name = name.substring(1);
						}

						fieldMap.put(name, field);
					}

					clazz = clazz.getSuperclass();
				}

				return fieldMap;
			});
	}

	private static final Map<Class<?>, Map<String, Field>> _fieldsMap =
		new ConcurrentReferenceKeyHashMap<>(
			new ConcurrentReferenceValueHashMap
				<Reference<Class<?>>, Map<String, Field>>(
					FinalizeManager.WEAK_REFERENCE_FACTORY),
			FinalizeManager.WEAK_REFERENCE_FACTORY);
	private static final List<Class<?>> _objectTypes = Arrays.asList(
		Boolean.class, BigDecimal.class, BigInteger.class, Byte.class,
		Date.class, Double.class, Float.class, Integer.class, Long.class,
		Map.class, String.class);

}