/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.metrics.integration.internal.search.index.reindexer;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.metrics.integration.internal.helper.IndexerHelper;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService;
import com.liferay.portal.workflow.metrics.search.background.task.WorkflowMetricsReindexStatusMessageSender;
import com.liferay.portal.workflow.metrics.search.index.InstanceWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.reindexer.WorkflowMetricsReindexer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true, property = "workflow.metrics.index.entity.name=instance",
	service = WorkflowMetricsReindexer.class
)
public class InstanceWorkflowMetricsReindexer
	implements WorkflowMetricsReindexer {

	@Override
	public void reindex(long companyId) throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_kaleoInstanceLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property companyIdProperty = PropertyFactoryUtil.forName(
					"companyId");

				dynamicQuery.add(companyIdProperty.eq(companyId));
			});

		long total = actionableDynamicQuery.performCount();

		AtomicInteger atomicCounter = new AtomicInteger(0);

		actionableDynamicQuery.setPerformActionMethod(
			(KaleoInstance kaleoInstance) -> {
				KaleoDefinitionVersion kaleoDefinitionVersion =
					_kaleoDefinitionVersionLocalService.
						fetchKaleoDefinitionVersion(
							kaleoInstance.getKaleoDefinitionVersionId());

				if (Objects.isNull(kaleoDefinitionVersion)) {
					return;
				}

				_instanceWorkflowMetricsIndexer.addInstance(
					_indexerHelper.createAssetTitleLocalizationMap(
						kaleoInstance.getClassName(),
						kaleoInstance.getClassPK(), kaleoInstance.getGroupId()),
					_indexerHelper.createAssetTypeLocalizationMap(
						kaleoInstance.getClassName(),
						kaleoInstance.getGroupId()),
					kaleoInstance.getClassName(), kaleoInstance.getClassPK(),
					companyId, kaleoInstance.getCompletionDate(),
					kaleoInstance.getCreateDate(),
					kaleoInstance.getKaleoInstanceId(),
					kaleoInstance.getModifiedDate(),
					kaleoInstance.getKaleoDefinitionId(),
					kaleoDefinitionVersion.getVersion(),
					kaleoInstance.getUserId(), kaleoInstance.getUserName());

				_workflowMetricsReindexStatusMessageSender.sendStatusMessage(
					atomicCounter.incrementAndGet(), total, "instance");
			});

		actionableDynamicQuery.performActions();
	}

	@Reference
	private IndexerHelper _indexerHelper;

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference
	private KaleoDefinitionVersionLocalService
		_kaleoDefinitionVersionLocalService;

	@Reference
	private KaleoInstanceLocalService _kaleoInstanceLocalService;

	@Reference
	private WorkflowMetricsReindexStatusMessageSender
		_workflowMetricsReindexStatusMessageSender;

}