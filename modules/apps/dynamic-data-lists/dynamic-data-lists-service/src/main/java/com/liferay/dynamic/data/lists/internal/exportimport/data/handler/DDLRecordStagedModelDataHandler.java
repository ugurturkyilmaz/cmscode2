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

package com.liferay.dynamic.data.lists.internal.exportimport.data.handler;

import com.liferay.dynamic.data.lists.internal.exportimport.staged.model.repository.DDLRecordStagedModelRepository;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class DDLRecordStagedModelDataHandler
	extends BaseStagedModelDataHandler<DDLRecord> {

	public static final String[] CLASS_NAMES = {DDLRecord.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(DDLRecord record) {
		return record.getUuid();
	}

	protected DDMFormValues deserialize(String content, DDMForm ddmForm) {
		DDMFormValuesDeserializerDeserializeRequest.Builder builder =
			DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(
				content, ddmForm);

		DDMFormValuesDeserializerDeserializeResponse
			ddmFormValuesDeserializerDeserializeResponse =
				_jsonDDMFormValuesDeserializer.deserialize(builder.build());

		return ddmFormValuesDeserializerDeserializeResponse.getDDMFormValues();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, DDLRecord record)
		throws Exception {

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, record, record.getRecordSet(),
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element recordElement = portletDataContext.getExportDataElement(record);

		_exportDDMFormValues(portletDataContext, record, recordElement);

		portletDataContext.addClassedModel(
			recordElement, ExportImportPathUtil.getModelPath(record), record);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long recordId)
		throws Exception {

		DDLRecord existingRecord = fetchMissingReference(uuid, groupId);

		if (existingRecord == null) {
			return;
		}

		Map<Long, Long> recordIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDLRecord.class);

		recordIds.put(recordId, existingRecord.getRecordId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, DDLRecord record)
		throws Exception {

		Map<Long, Long> recordSetIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDLRecordSet.class);

		long recordSetId = MapUtil.getLong(
			recordSetIds, record.getRecordSetId(), record.getRecordSetId());

		Element recordElement = portletDataContext.getImportDataElement(record);

		DDMFormValues ddmFormValues = _getImportDDMFormValues(
			portletDataContext, recordElement, recordSetId);

		DDLRecord importedRecord = (DDLRecord)record.clone();

		importedRecord.setGroupId(portletDataContext.getScopeGroupId());
		importedRecord.setRecordSetId(recordSetId);

		DDLRecord existingRecord =
			_ddlRecordStagedModelRepository.fetchStagedModelByUuidAndGroupId(
				record.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingRecord == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			importedRecord = _ddlRecordStagedModelRepository.addStagedModel(
				portletDataContext, importedRecord, ddmFormValues);
		}
		else {
			importedRecord.setMvccVersion(existingRecord.getMvccVersion());
			importedRecord.setRecordId(existingRecord.getRecordId());

			importedRecord = _ddlRecordStagedModelRepository.updateStagedModel(
				portletDataContext, importedRecord, ddmFormValues);
		}

		portletDataContext.importClassedModel(record, importedRecord);
	}

	@Override
	protected StagedModelRepository<DDLRecord> getStagedModelRepository() {
		return _ddlRecordStagedModelRepository;
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetLocalService(
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.lists.model.DDLRecord)",
		unbind = "-"
	)
	protected void setDDLRecordStagedModelRepository(
		DDLRecordStagedModelRepository ddlRecordStagedModelRepository) {

		_ddlRecordStagedModelRepository = ddlRecordStagedModelRepository;
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.mapping.storage.DDMFormValues)",
		unbind = "-"
	)
	protected void setDDMFormValuesExportImportContentProcessor(
		ExportImportContentProcessor<DDMFormValues>
			ddmFormValuesExportImportContentProcessor) {

		_ddmFormValuesExportImportContentProcessor =
			ddmFormValuesExportImportContentProcessor;
	}

	@Reference(unbind = "-")
	protected void setStorageEngine(StorageEngine storageEngine) {
		_storageEngine = storageEngine;
	}

	@Override
	protected void validateExport(
			PortletDataContext portletDataContext, DDLRecord record)
		throws PortletDataException {

		int status = WorkflowConstants.STATUS_ANY;

		try {
			status = record.getStatus();
		}
		catch (Exception exception) {
			throw new PortletDataException(exception);
		}

		if (!portletDataContext.isInitialPublication() &&
			!ArrayUtil.contains(getExportableStatuses(), status)) {

			PortletDataException portletDataException =
				new PortletDataException(
					PortletDataException.STATUS_UNAVAILABLE);

			portletDataException.setStagedModelDisplayName(record.getUuid());
			portletDataException.setStagedModelClassName(
				record.getModelClassName());
			portletDataException.setStagedModelClassPK(
				GetterUtil.getString(record.getRecordId()));

			throw portletDataException;
		}
	}

	private void _exportDDMFormValues(
			PortletDataContext portletDataContext, DDLRecord record,
			Element recordElement)
		throws Exception {

		String ddmFormValuesPath = ExportImportPathUtil.getModelPath(
			record, "ddm-form-values.json");

		recordElement.addAttribute("ddm-form-values-path", ddmFormValuesPath);

		DDMFormValues ddmFormValues = _storageEngine.getDDMFormValues(
			record.getDDMStorageId());

		ddmFormValues =
			_ddmFormValuesExportImportContentProcessor.
				replaceExportContentReferences(
					portletDataContext, record, ddmFormValues, true, false);

		portletDataContext.addZipEntry(
			ddmFormValuesPath, _serialize(ddmFormValues));
	}

	private DDMFormValues _getImportDDMFormValues(
			PortletDataContext portletDataContext, Element recordElement,
			long recordSetId)
		throws Exception {

		DDLRecordSet recordSet = _ddlRecordSetLocalService.getRecordSet(
			recordSetId);

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		String ddmFormValuesPath = recordElement.attributeValue(
			"ddm-form-values-path");

		String serializedDDMFormValues = portletDataContext.getZipEntryAsString(
			ddmFormValuesPath);

		DDMFormValues ddmFormValues = deserialize(
			serializedDDMFormValues, ddmStructure.getDDMForm());

		return _ddmFormValuesExportImportContentProcessor.
			replaceImportContentReferences(
				portletDataContext, ddmStructure, ddmFormValues);
	}

	private String _serialize(DDMFormValues ddmFormValues) {
		DDMFormValuesSerializerSerializeRequest.Builder builder =
			DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
				ddmFormValues);

		DDMFormValuesSerializerSerializeResponse
			ddmFormValuesSerializerSerializeResponse =
				_jsonDDMFormValuesSerializer.serialize(builder.build());

		return ddmFormValuesSerializerSerializeResponse.getContent();
	}

	private DDLRecordSetLocalService _ddlRecordSetLocalService;
	private DDLRecordStagedModelRepository _ddlRecordStagedModelRepository;
	private ExportImportContentProcessor<DDMFormValues>
		_ddmFormValuesExportImportContentProcessor;

	@Reference(target = "(ddm.form.values.deserializer.type=json)")
	private DDMFormValuesDeserializer _jsonDDMFormValuesDeserializer;

	@Reference(target = "(ddm.form.values.serializer.type=json)")
	private DDMFormValuesSerializer _jsonDDMFormValuesSerializer;

	private StorageEngine _storageEngine;

}