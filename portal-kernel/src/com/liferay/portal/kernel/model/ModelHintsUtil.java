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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.util.Tuple;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ModelHintsUtil {

	public static String buildCustomValidatorName(String validatorName) {
		return _modelHints.buildCustomValidatorName(validatorName);
	}

	public static Map<String, String> getDefaultHints(String model) {
		return _modelHints.getDefaultHints(model);
	}

	public static Object getFieldsElement(String model, String field) {
		return _modelHints.getFieldsElement(model, field);
	}

	public static Map<String, String> getHints(String model, String field) {
		return _modelHints.getHints(model, field);
	}

	public static int getMaxLength(String model, String field) {
		return _modelHints.getMaxLength(model, field);
	}

	public static ModelHints getModelHints() {
		return _modelHints;
	}

	public static List<String> getModels() {
		return _modelHints.getModels();
	}

	public static Tuple getSanitizeTuple(String model, String field) {
		return _modelHints.getSanitizeTuple(model, field);
	}

	public static List<Tuple> getSanitizeTuples(String model) {
		return _modelHints.getSanitizeTuples(model);
	}

	public static String getType(String model, String field) {
		return _modelHints.getType(model, field);
	}

	public static List<Tuple> getValidators(String model, String field) {
		return _modelHints.getValidators(model, field);
	}

	public static String getValue(
		String model, String field, String name, String defaultValue) {

		return _modelHints.getValue(model, field, name, defaultValue);
	}

	public static boolean hasField(String model, String field) {
		return _modelHints.hasField(model, field);
	}

	public static boolean isCustomValidator(String validatorName) {
		return _modelHints.isCustomValidator(validatorName);
	}

	public static boolean isLocalized(String model, String field) {
		return _modelHints.isLocalized(model, field);
	}

	public static void read(ClassLoader classLoader, InputStream inputStream)
		throws Exception {

		_modelHints.read(classLoader, inputStream);
	}

	public static void read(ClassLoader classLoader, String source)
		throws Exception {

		_modelHints.read(classLoader, source);
	}

	public static String trimString(String model, String field, String value) {
		return _modelHints.trimString(model, field, value);
	}

	public void setModelHints(ModelHints modelHints) {
		_modelHints = modelHints;
	}

	private static ModelHints _modelHints;

}