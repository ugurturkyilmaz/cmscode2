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

package com.liferay.fragment.internal.exportimport.content.processor;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(
	property = "content.processor.type=FragmentEntryLinkEditableValues",
	service = ExportImportContentProcessor.class
)
public class EditableValuesCategoryTreeNodeSelectorExportImportContentProcessor
	extends BaseEditableValuesConfigurationExportImportContentProcessor {

	@Override
	protected String getConfigurationType() {
		return "categoryTreeNodeSelector";
	}

	@Override
	protected FragmentEntryConfigurationParser
		getFragmentEntryConfigurationParser() {

		return _fragmentEntryConfigurationParser;
	}

	@Override
	protected void replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel referrerStagedModel,
			JSONObject configurationValueJSONObject,
			boolean exportReferencedContent)
		throws Exception {

		long assetCategoryTreeNodeId = GetterUtil.getLong(
			configurationValueJSONObject.getString("categoryTreeNodeId"));

		if (assetCategoryTreeNodeId == 0) {
			return;
		}

		StagedModel stagedModel = null;

		String assetCategoryTreeNodeType =
			configurationValueJSONObject.getString("categoryTreeNodeType");

		if (assetCategoryTreeNodeType.equals("Vocabulary")) {
			stagedModel = _assetVocabularyLocalService.fetchAssetVocabulary(
				assetCategoryTreeNodeId);
		}
		else if (assetCategoryTreeNodeType.equals("Category")) {
			stagedModel = _assetCategoryLocalService.fetchAssetCategory(
				assetCategoryTreeNodeId);
		}

		if (stagedModel == null) {
			return;
		}

		if (exportReferencedContent) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, referrerStagedModel, stagedModel,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
		}
		else {
			Element entityElement = portletDataContext.getExportDataElement(
				referrerStagedModel);

			portletDataContext.addReferenceElement(
				referrerStagedModel, entityElement, stagedModel,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}
	}

	@Override
	protected void replaceImportContentReferences(
		PortletDataContext portletDataContext,
		JSONObject configurationValueJSONObject) {

		long assetCategoryTreeNodeId = GetterUtil.getLong(
			configurationValueJSONObject.getString("categoryTreeNodeId"));

		if (assetCategoryTreeNodeId == 0) {
			return;
		}

		String assetCategoryTreeNodeType =
			configurationValueJSONObject.getString("categoryTreeNodeType");

		if (assetCategoryTreeNodeType.equals("Vocabulary")) {
			Map<Long, Long> assetVocabularyNewPrimaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					AssetVocabulary.class.getName());

			configurationValueJSONObject.put(
				"categoryTreeNodeId",
				assetVocabularyNewPrimaryKeys.getOrDefault(
					assetCategoryTreeNodeId, 0L));
		}
		else if (assetCategoryTreeNodeType.equals("Category")) {
			Map<Long, Long> assetVocabularyNewPrimaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					AssetCategory.class.getName());

			configurationValueJSONObject.put(
				"categoryTreeNodeId",
				assetVocabularyNewPrimaryKeys.getOrDefault(
					assetCategoryTreeNodeId, 0L));
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

}