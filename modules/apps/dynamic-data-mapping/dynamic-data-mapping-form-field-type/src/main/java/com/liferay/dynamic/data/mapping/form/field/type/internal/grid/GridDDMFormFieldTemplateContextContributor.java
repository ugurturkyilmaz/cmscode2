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

package com.liferay.dynamic.data.mapping.form.field.type.internal.grid;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.form.field.type.internal.grid.helper.GridDDMFormFieldContextHelper;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pedro Queiroz
 */
@Component(
	immediate = true,
	property = "ddm.form.field.type.name=" + DDMFormFieldTypeConstants.GRID,
	service = {
		DDMFormFieldTemplateContextContributor.class,
		GridDDMFormFieldTemplateContextContributor.class
	}
)
public class GridDDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		return HashMapBuilder.<String, Object>put(
			"columns",
			_getOptions("columns", ddmFormField, ddmFormFieldRenderingContext)
		).put(
			"rows",
			_getOptions("rows", ddmFormField, ddmFormFieldRenderingContext)
		).put(
			"value",
			() -> {
				String value = ddmFormFieldRenderingContext.getValue();

				if (Validator.isNull(value)) {
					value = "{}";
				}

				return jsonFactory.looseDeserialize(value);
			}
		).build();
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		String optionType, DDMFormField ddmFormField) {

		return (DDMFormFieldOptions)ddmFormField.getProperty(optionType);
	}

	@Reference
	protected JSONFactory jsonFactory;

	private List<Object> _getOptions(
		String key, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		GridDDMFormFieldContextHelper gridDDMFormFieldContextHelper =
			new GridDDMFormFieldContextHelper(
				getDDMFormFieldOptions(key, ddmFormField),
				ddmFormFieldRenderingContext.getLocale());

		return gridDDMFormFieldContextHelper.getOptions(
			ddmFormFieldRenderingContext);
	}

}