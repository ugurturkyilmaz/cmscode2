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

package com.liferay.bean.portlet.cdi.extension.internal.mvc;

import com.liferay.bean.portlet.cdi.extension.internal.scope.ScopedBeanManager;
import com.liferay.bean.portlet.cdi.extension.internal.scope.ScopedBeanManagerThreadLocal;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.function.Function;

import javax.portlet.PortletRequest;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

/**
 * @author Neil Griffin
 */
public class ParamConverterProviderImpl implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(
		Class<T> rawType, Type genericType, Annotation[] annotations) {

		if (rawType == null) {
			return null;
		}

		if (!rawType.equals(Integer.class) && !rawType.equals(Integer.TYPE) &&
			!rawType.equals(Long.class) && !rawType.equals(Long.TYPE) &&
			!rawType.equals(Double.class) && !rawType.equals(Double.TYPE) &&
			!rawType.equals(Float.class) && !rawType.equals(Float.TYPE) &&
			!rawType.equals(Boolean.class) && !rawType.equals(Boolean.TYPE)) {

			return null;
		}

		return new ParamConverter<T>() {

			@Override
			public T fromString(String value) {
				if (value == null) {
					throw new IllegalArgumentException(
						"Unable to convert a null parameter value");
				}

				if (rawType.equals(Integer.class) ||
					rawType.equals(Integer.TYPE)) {

					return _getNumber(value, number -> number.intValue());
				}
				else if (rawType.equals(Long.class) ||
						 rawType.equals(Long.TYPE)) {

					return _getNumber(value, number -> number.longValue());
				}
				else if (rawType.equals(Double.class) ||
						 rawType.equals(Double.TYPE)) {

					return _getNumber(value, number -> number.doubleValue());
				}
				else if (rawType.equals(Float.class) ||
						 rawType.equals(Float.TYPE)) {

					return _getNumber(value, number -> number.floatValue());
				}
				else if (rawType.equals(Boolean.class) ||
						 rawType.equals(Boolean.TYPE)) {

					value = value.trim();
					value = StringUtil.toLowerCase(value);

					if (Boolean.valueOf(value)) {
						return rawType.cast(Boolean.TRUE);
					}

					return rawType.cast(value.equals("on"));
				}

				return null;
			}

			@Override
			public String toString(T value) {
				if (value == null) {
					return "";
				}

				return value.toString();
			}

			private T _getNumber(
				String value, Function<Number, ?> getNumberFunction) {

				ScopedBeanManager scopedBeanManager =
					ScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

				PortletRequest portletRequest =
					scopedBeanManager.getPortletRequest();

				NumberFormat numberFormat = NumberFormat.getInstance(
					portletRequest.getLocale());

				try {
					Number number = numberFormat.parse(value);

					return rawType.cast(getNumberFunction.apply(number));
				}
				catch (ParseException parseException) {
					throw new IllegalArgumentException(parseException);
				}
			}

		};
	}

}