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

package com.liferay.template.info.field.transformer;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.templateparser.TemplateNode;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;

/**
 * @author Lourdes Fernández Besada
 */
public abstract class BaseTemplateNodeTransformer
	implements TemplateNodeTransformer {

	protected TemplateNode getDefaultTemplateNode(
		InfoFieldValue<Object> infoFieldValue, ThemeDisplay themeDisplay) {

		InfoField<?> infoField = infoFieldValue.getInfoField();

		Object data = infoFieldValue.getValue(themeDisplay.getLocale());

		if (Validator.isNull(data)) {
			return new TemplateNode(
				themeDisplay, infoField.getName(), StringPool.BLANK,
				StringPool.BLANK, Collections.emptyMap());
		}

		return new TemplateNode(
			themeDisplay, infoField.getName(), String.valueOf(data),
			StringPool.BLANK, Collections.emptyMap());
	}

}