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

package com.liferay.layout.page.template.admin.web.internal.headless.delivery.dto.v1_0.structure.importer;

import com.liferay.headless.delivery.dto.v1_0.PageElement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Jürgen Kappler
 */
@Component(service = LayoutStructureItemImporterTracker.class)
public class LayoutStructureItemImporterTracker {

	public LayoutStructureItemImporter getLayoutStructureItemImporter(
		PageElement.Type type) {

		return _layoutStructureItemImporters.get(type);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setLayoutStructureItemImporter(
		LayoutStructureItemImporter layoutStructureItemImporter) {

		_layoutStructureItemImporters.put(
			layoutStructureItemImporter.getPageElementType(),
			layoutStructureItemImporter);
	}

	protected void unsetLayoutStructureItemImporter(
		LayoutStructureItemImporter layoutStructureItemImporter) {

		_layoutStructureItemImporters.remove(layoutStructureItemImporter);
	}

	private final Map<PageElement.Type, LayoutStructureItemImporter>
		_layoutStructureItemImporters = new ConcurrentHashMap<>();

}