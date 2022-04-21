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

package com.liferay.portal.search.test.util.document;

import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.document.Document;

import java.util.Map;

/**
 * @author André de Oliveira
 */
public class DocumentTranslator {

	public com.liferay.portal.kernel.search.Document toLegacyDocument(
		Document document) {

		DocumentImpl documentImpl = new DocumentImpl();

		Map<String, com.liferay.portal.search.document.Field> fields =
			document.getFields();

		fields.forEach(
			(key, field) -> documentImpl.add(
				new Field(key, String.valueOf(field.getValue()))));

		return documentImpl;
	}

}