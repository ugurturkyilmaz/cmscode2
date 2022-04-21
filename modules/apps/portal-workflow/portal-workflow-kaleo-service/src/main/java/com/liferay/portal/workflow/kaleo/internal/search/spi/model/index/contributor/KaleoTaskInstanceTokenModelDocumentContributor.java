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

package com.liferay.portal.workflow.kaleo.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.workflow.kaleo.internal.search.KaleoTaskInstanceTokenField;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
	service = ModelDocumentContributor.class
)
public class KaleoTaskInstanceTokenModelDocumentContributor
	extends BaseKaleoModelDocumentContributor
	implements ModelDocumentContributor<KaleoTaskInstanceToken> {

	@Override
	public void contribute(
		Document document, KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		Set<Long> assigneeClassNameIds = new HashSet<>();
		Set<Long> assigneeClassPKs = new HashSet<>();
		Set<Long> assigneeGroupIds = new HashSet<>();

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
				kaleoTaskAssignmentInstances) {

			assigneeClassNameIds.add(
				portal.getClassNameId(
					kaleoTaskAssignmentInstance.getAssigneeClassName()));
			assigneeClassPKs.add(
				kaleoTaskAssignmentInstance.getAssigneeClassPK());
			assigneeGroupIds.add(kaleoTaskAssignmentInstance.getGroupId());
		}

		document.addKeyword(
			KaleoTaskInstanceTokenField.ASSIGNEE_CLASS_NAME_IDS,
			assigneeClassNameIds.toArray(new Long[0]));
		document.addKeyword(
			KaleoTaskInstanceTokenField.ASSIGNEE_CLASS_PKS,
			assigneeClassPKs.toArray(new Long[0]));
		document.addKeyword(
			KaleoTaskInstanceTokenField.ASSIGNEE_GROUP_IDS,
			assigneeGroupIds.toArray(new Long[0]));
		document.addKeyword(
			KaleoTaskInstanceTokenField.CLASS_NAME,
			kaleoTaskInstanceToken.getClassName());
		document.addKeyword(
			Field.CLASS_NAME_ID,
			portal.getClassNameId(kaleoTaskInstanceToken.getClassName()));
		document.addKeyword(
			Field.CLASS_PK, kaleoTaskInstanceToken.getClassPK());
		document.addKeywordSortable(
			KaleoTaskInstanceTokenField.COMPLETED,
			kaleoTaskInstanceToken.isCompleted());
		document.addDateSortable(
			KaleoTaskInstanceTokenField.COMPLETION_DATE,
			kaleoTaskInstanceToken.getCompletionDate());
		document.addDateSortable(
			Field.CREATE_DATE, kaleoTaskInstanceToken.getCreateDate());
		document.addDateSortable(
			KaleoTaskInstanceTokenField.DUE_DATE,
			kaleoTaskInstanceToken.getDueDate());

		try {
			KaleoDefinitionVersion kaleoDefinitionVersion =
				kaleoDefinitionVersionLocalService.getKaleoDefinitionVersion(
					kaleoTaskInstanceToken.getKaleoDefinitionVersionId());

			KaleoDefinition kaleoDefinition =
				kaleoDefinitionVersion.getKaleoDefinition();

			document.addKeyword(
				KaleoTaskInstanceTokenField.KALEO_DEFINITION_ID,
				kaleoDefinition.getKaleoDefinitionId());
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}
		}

		document.addNumberSortable(
			KaleoTaskInstanceTokenField.KALEO_INSTANCE_ID,
			kaleoTaskInstanceToken.getKaleoInstanceId());
		document.addNumberSortable(
			KaleoTaskInstanceTokenField.KALEO_TASK_ID,
			kaleoTaskInstanceToken.getKaleoTaskId());
		document.addNumberSortable(
			KaleoTaskInstanceTokenField.KALEO_TASK_INSTANCE_TOKEN_ID,
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		document.addDateSortable(
			Field.MODIFIED_DATE, kaleoTaskInstanceToken.getModifiedDate());
		document.addKeywordSortable(
			KaleoTaskInstanceTokenField.TASK_NAME,
			kaleoTaskInstanceToken.getKaleoTaskName());
		document.addNumberSortable(
			Field.USER_ID, kaleoTaskInstanceToken.getUserId());

		addAssetEntryAttributes(
			assetEntry -> {
				document.addKeyword(
					KaleoTaskInstanceTokenField.ASSET_CLASS_NAME_ID,
					assetEntry.getClassNameId());
				document.addKeyword(
					KaleoTaskInstanceTokenField.ASSET_CLASS_PK,
					assetEntry.getClassPK());
			},
			kaleoTaskInstanceToken.getClassName(),
			kaleoTaskInstanceToken.getClassPK(), document,
			kaleoTaskInstanceToken.getGroupId());
	}

	protected String[] getLanguageIds(
		String defaultLanguageId, String content) {

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			content);

		if (languageIds.length == 0) {
			languageIds = new String[] {defaultLanguageId};
		}

		return languageIds;
	}

	@Reference
	protected ClassNameLocalService classNameLocalService;

	@Reference
	protected KaleoDefinitionVersionLocalService
		kaleoDefinitionVersionLocalService;

	@Reference
	protected Portal portal;

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoTaskInstanceTokenModelDocumentContributor.class);

}