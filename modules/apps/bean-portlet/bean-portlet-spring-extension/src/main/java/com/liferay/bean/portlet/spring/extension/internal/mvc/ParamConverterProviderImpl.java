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

package com.liferay.bean.portlet.spring.extension.internal.mvc;

import com.liferay.portal.kernel.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.function.Function;

import javax.annotation.ManagedBean;

import javax.portlet.PortletRequest;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Neil Griffin
 */
@ManagedBean
public class ParamConverterProviderImpl implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(
		Class<T> clazz, Type genericType, Annotation[] annotations) {

		if (clazz == null) {
			return null;
		}

		if (clazz.equals(Integer.class) || clazz.equals(Integer.TYPE) ||
			clazz.equals(Long.class) || clazz.equals(Long.TYPE) ||
			clazz.equals(Double.class) || clazz.equals(Double.TYPE) ||
			clazz.equals(Float.class) || clazz.equals(Float.TYPE) ||
			clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE)) {

			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					if (value == null) {
						throw new IllegalArgumentException(
							"Unable to convert a null parameter value");
					}

					if (clazz.equals(Integer.class) ||
						clazz.equals(Integer.TYPE)) {

						return _getNumber(value, number -> number.intValue());
					}
					else if (clazz.equals(Long.class) ||
							 clazz.equals(Long.TYPE)) {

						return _getNumber(value, number -> number.longValue());
					}
					else if (clazz.equals(Double.class) ||
							 clazz.equals(Double.TYPE)) {

						return _getNumber(
							value, number -> number.doubleValue());
					}
					else if (clazz.equals(Float.class) ||
							 clazz.equals(Float.TYPE)) {

						return _getNumber(value, number -> number.floatValue());
					}
					else if (clazz.equals(Boolean.class) ||
							 clazz.equals(Boolean.TYPE)) {

						value = value.trim();
						value = StringUtil.toLowerCase(value);

						if (Boolean.valueOf(value)) {
							return clazz.cast(Boolean.TRUE);
						}

						return clazz.cast(value.equals("on"));
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

					NumberFormat numberFormat = NumberFormat.getInstance(
						_portletRequest.getLocale());

					try {
						Number number = numberFormat.parse(value);

						return clazz.cast(getNumberFunction.apply(number));
					}
					catch (ParseException parseException) {
						throw new IllegalArgumentException(parseException);
					}
				}

			};
		}

		return null;
	}

	@Autowired
	private PortletRequest _portletRequest;

}