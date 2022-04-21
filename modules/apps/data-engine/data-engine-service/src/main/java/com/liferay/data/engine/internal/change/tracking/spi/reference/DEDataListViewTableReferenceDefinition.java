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

package com.liferay.data.engine.internal.change.tracking.spi.reference;

import com.liferay.change.tracking.spi.reference.TableReferenceDefinition;
import com.liferay.change.tracking.spi.reference.builder.ChildTableReferenceInfoBuilder;
import com.liferay.change.tracking.spi.reference.builder.ParentTableReferenceInfoBuilder;
import com.liferay.data.engine.model.DEDataDefinitionFieldLinkTable;
import com.liferay.data.engine.model.DEDataListView;
import com.liferay.data.engine.model.DEDataListViewTable;
import com.liferay.data.engine.service.persistence.DEDataListViewPersistence;
import com.liferay.dynamic.data.mapping.model.DDMStructureTable;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = TableReferenceDefinition.class)
public class DEDataListViewTableReferenceDefinition
	implements TableReferenceDefinition<DEDataListViewTable> {

	@Override
	public void defineChildTableReferences(
		ChildTableReferenceInfoBuilder<DEDataListViewTable>
			childTableReferenceInfoBuilder) {

		childTableReferenceInfoBuilder.classNameReference(
			DEDataListViewTable.INSTANCE.deDataListViewId,
			DEDataDefinitionFieldLinkTable.INSTANCE.classPK,
			DEDataListView.class);
	}

	@Override
	public void defineParentTableReferences(
		ParentTableReferenceInfoBuilder<DEDataListViewTable>
			parentTableReferenceInfoBuilder) {

		parentTableReferenceInfoBuilder.groupedModel(
			DEDataListViewTable.INSTANCE
		).singleColumnReference(
			DEDataListViewTable.INSTANCE.ddmStructureId,
			DDMStructureTable.INSTANCE.structureId
		);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _deDataListViewPersistence;
	}

	@Override
	public DEDataListViewTable getTable() {
		return DEDataListViewTable.INSTANCE;
	}

	@Reference
	private DEDataListViewPersistence _deDataListViewPersistence;

}