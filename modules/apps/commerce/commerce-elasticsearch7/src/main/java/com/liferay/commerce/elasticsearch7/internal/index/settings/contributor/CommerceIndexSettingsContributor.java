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

package com.liferay.commerce.elasticsearch7.internal.index.settings.contributor;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.settings.IndexSettingsContributor;
import com.liferay.portal.search.spi.settings.IndexSettingsHelper;
import com.liferay.portal.search.spi.settings.TypeMappingsHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	enabled = false, immediate = true, service = IndexSettingsContributor.class
)
public class CommerceIndexSettingsContributor
	implements IndexSettingsContributor {

	@Override
	public void contribute(
		String indexName, TypeMappingsHelper typeMappingsHelper) {

		String typeMappings = StringUtil.read(
			getClass(), "dependencies/additional-type-mappings.json");

		typeMappingsHelper.addTypeMappings(indexName, typeMappings);
	}

	@Override
	public void populate(IndexSettingsHelper indexSettingsHelper) {
	}

}