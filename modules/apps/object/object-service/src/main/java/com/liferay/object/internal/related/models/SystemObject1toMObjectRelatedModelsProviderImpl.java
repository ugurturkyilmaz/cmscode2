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

package com.liferay.object.internal.related.models;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.exception.RequiredObjectRelationshipException;
import com.liferay.object.internal.petra.sql.dsl.DynamicObjectDefinitionTable;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.models.ObjectRelatedModelsProvider;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.system.SystemObjectDefinitionMetadata;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

/**
 * @author Marco Leo
 * @author Brian Wing Shun Chan
 */
public class SystemObject1toMObjectRelatedModelsProviderImpl
	<T extends BaseModel<T>>
		implements ObjectRelatedModelsProvider<T> {

	public SystemObject1toMObjectRelatedModelsProviderImpl(
		ObjectDefinition objectDefinition,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectRelationshipLocalService objectRelationshipLocalService,
		PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry,
		SystemObjectDefinitionMetadata systemObjectDefinitionMetadata) {

		_objectEntryLocalService = objectEntryLocalService;
		_objectFieldLocalService = objectFieldLocalService;
		_objectRelationshipLocalService = objectRelationshipLocalService;
		_persistedModelLocalServiceRegistry =
			persistedModelLocalServiceRegistry;
		_systemObjectDefinitionMetadata = systemObjectDefinitionMetadata;

		_dynamicObjectDefinitionTable = new DynamicObjectDefinitionTable(
			objectDefinition,
			_objectFieldLocalService.getObjectFields(
				objectDefinition.getObjectDefinitionId(),
				objectDefinition.getExtensionDBTableName()),
			objectDefinition.getExtensionDBTableName());
		_table = systemObjectDefinitionMetadata.getTable();
	}

	@Override
	public void deleteRelatedModel(
			long userId, long groupId, long objectRelationshipId,
			long primaryKey)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipLocalService.getObjectRelationship(
				objectRelationshipId);

		List<T> relatedModels = getRelatedModels(
			groupId, objectRelationshipId, primaryKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		if (relatedModels.isEmpty()) {
			return;
		}

		if (Objects.equals(
				objectRelationship.getDeletionType(),
				ObjectRelationshipConstants.DELETION_TYPE_CASCADE)) {

			PersistedModelLocalService persistedModelLocalService =
				_persistedModelLocalServiceRegistry.
					getPersistedModelLocalService(
						_systemObjectDefinitionMetadata.getModelClassName());

			for (BaseModel<T> baseModel : relatedModels) {
				persistedModelLocalService.deletePersistedModel(
					(PersistedModel)baseModel);
			}
		}
		else if (Objects.equals(
					objectRelationship.getDeletionType(),
					ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE)) {

			ObjectField objectField = _objectFieldLocalService.getObjectField(
				objectRelationship.getObjectFieldId2());

			for (BaseModel<T> baseModel : relatedModels) {
				_objectEntryLocalService.insertIntoOrUpdateExtensionTable(
					objectRelationship.getObjectDefinitionId2(),
					GetterUtil.getLong(baseModel.getPrimaryKeyObj()),
					HashMapBuilder.<String, Serializable>put(
						objectField.getName(), 0
					).build());
			}
		}
		else if (Objects.equals(
					objectRelationship.getDeletionType(),
					ObjectRelationshipConstants.DELETION_TYPE_PREVENT)) {

			throw new RequiredObjectRelationshipException();
		}
	}

	@Override
	public void disassociateRelatedModels(
			long userId, long objectRelationshipId, long primaryKey1,
			long primaryKey2)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipLocalService.getObjectRelationship(
				objectRelationshipId);

		_objectEntryLocalService.insertIntoOrUpdateExtensionTable(
			objectRelationship.getObjectDefinitionId2(),
			GetterUtil.getLong(primaryKey1),
			HashMapBuilder.<String, Serializable>put(
				() -> {
					ObjectField objectField =
						_objectFieldLocalService.getObjectField(
							objectRelationship.getObjectFieldId2());

					return objectField.getName();
				},
				0
			).build());
	}

	@Override
	public String getClassName() {
		return _systemObjectDefinitionMetadata.getModelClassName();
	}

	@Override
	public String getObjectRelationshipType() {
		return ObjectRelationshipConstants.TYPE_ONE_TO_MANY;
	}

	@Override
	public List<T> getRelatedModels(
			long groupId, long objectRelationshipId, long primaryKey, int start,
			int end)
		throws PortalException {

		PersistedModelLocalService persistedModelLocalService =
			_persistedModelLocalServiceRegistry.getPersistedModelLocalService(
				_systemObjectDefinitionMetadata.getModelClassName());

		DSLQuery dslQuery = _getGroupByStep(
			groupId, objectRelationshipId, primaryKey,
			DSLQueryFactoryUtil.selectDistinct(_table)
		).limit(
			start, end
		);

		return persistedModelLocalService.dslQuery(dslQuery);
	}

	@Override
	public int getRelatedModelsCount(
			long groupId, long objectRelationshipId, long primaryKey)
		throws PortalException {

		PersistedModelLocalService persistedModelLocalService =
			_persistedModelLocalServiceRegistry.getPersistedModelLocalService(
				_systemObjectDefinitionMetadata.getModelClassName());

		DSLQuery dslQuery = _getGroupByStep(
			groupId, objectRelationshipId, primaryKey,
			DSLQueryFactoryUtil.selectDistinct(_table));

		return persistedModelLocalService.dslQueryCount(dslQuery);
	}

	private GroupByStep _getGroupByStep(
			long groupId, long objectRelationshipId, long primaryKey,
			FromStep fromStep)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipLocalService.getObjectRelationship(
				objectRelationshipId);

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectRelationship.getObjectFieldId2());

		Column<?, Long> primaryKeyColumn = null;

		if (Objects.equals(objectField.getDBTableName(), _table)) {
			primaryKeyColumn = (Column<?, Long>)_table.getColumn(
				objectField.getDBColumnName());
		}
		else {
			primaryKeyColumn =
				(Column<DynamicObjectDefinitionTable, Long>)
					_dynamicObjectDefinitionTable.getColumn(
						objectField.getDBColumnName());
		}

		return fromStep.from(
			_table
		).innerJoinON(
			_dynamicObjectDefinitionTable,
			_dynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				_systemObjectDefinitionMetadata.getPrimaryKeyColumn()
			)
		).where(
			primaryKeyColumn.eq(
				primaryKey
			).and(
				() -> {
					Column<?, Long> groupIdColumn = _table.getColumn("groupId");

					if (groupIdColumn == null) {
						return null;
					}

					return groupIdColumn.eq(groupId);
				}
			).and(
				() -> {
					Column<?, Long> companyIdColumn = _table.getColumn(
						"companyId");

					if (companyIdColumn == null) {
						return null;
					}

					return companyIdColumn.eq(objectField.getCompanyId());
				}
			)
		);
	}

	private final DynamicObjectDefinitionTable _dynamicObjectDefinitionTable;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectFieldLocalService _objectFieldLocalService;
	private final ObjectRelationshipLocalService
		_objectRelationshipLocalService;
	private final PersistedModelLocalServiceRegistry
		_persistedModelLocalServiceRegistry;
	private final SystemObjectDefinitionMetadata
		_systemObjectDefinitionMetadata;
	private final Table _table;

}