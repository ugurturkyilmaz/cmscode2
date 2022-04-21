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

package com.liferay.portal.workflow.metrics.demo.data.creator.internal;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.workflow.metrics.demo.data.creator.WorkflowMetricsSLADefinitionDemoDataCreator;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Node;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.util.NodeUtil;
import com.liferay.portal.workflow.metrics.rest.spi.resource.SPINodeResource;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(service = WorkflowMetricsSLADefinitionDemoDataCreator.class)
public class WorkflowMetricsSLADefinitionDemoDataCreatorImpl
	implements WorkflowMetricsSLADefinitionDemoDataCreator {

	@Override
	public void create(
			long companyId, long userId, Date createDate,
			long workflowDefinitionId)
		throws Exception {

		_create(
			companyId, userId, createDate, 172800000, "Review",
			workflowDefinitionId,
			_toStringArray(
				_getNodeKey(
					"enter",
					_getNodeId(
						companyId, "underwriter-final-review",
						workflowDefinitionId)),
				_getNodeKey(
					"enter",
					_getNodeId(
						companyId, "underwriter-review",
						workflowDefinitionId))),
			_toStringArray(
				_getNodeKey(
					"leave",
					_getNodeId(
						companyId, "underwriter-final-review",
						workflowDefinitionId)),
				_getNodeKey(
					"leave",
					_getNodeId(
						companyId, "underwriter-review",
						workflowDefinitionId))));
		_create(
			companyId, userId, createDate, 259200000, "Payment",
			workflowDefinitionId,
			_toStringArray(
				_getNodeKey(
					"enter",
					_getNodeId(
						companyId, "insurance-payment", workflowDefinitionId)),
				_getNodeKey(
					"enter",
					_getNodeId(
						companyId, "manual-billing-process",
						workflowDefinitionId))),
			_toStringArray(
				_getNodeKey(
					"leave",
					_getNodeId(
						companyId, "insurance-payment", workflowDefinitionId)),
				_getNodeKey(
					"leave",
					_getNodeId(
						companyId, "manual-billing-process",
						workflowDefinitionId))));
		_create(
			companyId, userId, createDate, 86400000, "Questions",
			workflowDefinitionId,
			_toStringArray(
				_getNodeKey(
					"enter",
					_getNodeId(
						companyId, "additional-rating-questions",
						workflowDefinitionId))),
			_toStringArray(
				_getNodeKey(
					"leave",
					_getNodeId(
						companyId, "additional-rating-questions",
						workflowDefinitionId))));
		_create(
			companyId, userId, createDate, 604800000, "Application",
			workflowDefinitionId,
			_toStringArray(
				_getNodeKey(
					"begin",
					_getNodeId(
						companyId, "start-online-application",
						workflowDefinitionId))),
			_toStringArray(
				_getNodeKey(
					"end",
					_getNodeId(
						companyId, "application-completed",
						workflowDefinitionId)),
				_getNodeKey(
					"end",
					_getNodeId(
						companyId, "application-canceled",
						workflowDefinitionId))));
	}

	@Override
	public void delete() throws PortalException {
		for (Long workflowMetricsSLADefinitionId :
				_workflowMetricsSLADefinitionIds) {

			WorkflowMetricsSLADefinition workflowMetricsSLADefinition =
				_workflowMetricsSLADefinitionLocalService.
					fetchWorkflowMetricsSLADefinition(
						workflowMetricsSLADefinitionId);

			if (workflowMetricsSLADefinition != null) {
				_workflowMetricsSLADefinitionLocalService.
					deleteWorkflowMetricsSLADefinition(
						workflowMetricsSLADefinition);
			}
		}
	}

	private void _create(
			long companyId, long userId, Date createDate, long duration,
			String name, long processId, String[] startNodeKeys,
			String[] stopNodeKeys)
		throws Exception {

		WorkflowMetricsSLADefinition workflowMetricsSLADefinition =
			_getWorkflowMetricsSLADefinition(
				companyId, userId, duration, name, processId, startNodeKeys,
				stopNodeKeys);

		workflowMetricsSLADefinition.setCreateDate(createDate);

		_workflowMetricsSLADefinitionLocalService.
			updateWorkflowMetricsSLADefinition(workflowMetricsSLADefinition);

		_workflowMetricsSLADefinitionIds.add(
			workflowMetricsSLADefinition.getWorkflowMetricsSLADefinitionId());
	}

	private long _getNodeId(
			long companyId, String name, long workflowDefinitionId)
		throws Exception {

		SPINodeResource<Node> spiNodeResource = _getSPINodeResource(companyId);

		Page<Node> nodePage = spiNodeResource.getProcessNodesPage(
			workflowDefinitionId);

		Collection<Node> nodes = nodePage.getItems();

		for (Node node : nodes) {
			if (Objects.equals(node.getName(), name)) {
				return node.getId();
			}
		}

		return 0L;
	}

	private String _getNodeKey(String action, long nodeId) {
		return StringBundler.concat(nodeId, CharPool.COLON, action);
	}

	private SPINodeResource<Node> _getSPINodeResource(long companyId) {
		return new SPINodeResource<>(
			companyId, _nodeWorkflowMetricsIndexNameBuilder,
			_processWorkflowMetricsIndexNameBuilder, _queries,
			_searchRequestExecutor,
			document -> NodeUtil.toNode(
				document, _language,
				ResourceBundleUtil.getModuleAndPortalResourceBundle(
					LocaleUtil.getMostRelevantLocale(),
					WorkflowMetricsSLADefinitionDemoDataCreatorImpl.class)));
	}

	private WorkflowMetricsSLADefinition _getWorkflowMetricsSLADefinition(
			long companyId, long userId, long duration, String name,
			long processId, String[] startNodeKeys, String[] stopNodeKeys)
		throws Exception {

		List<WorkflowMetricsSLADefinition> workflowMetricsSLADefinitions =
			_workflowMetricsSLADefinitionLocalService.
				getWorkflowMetricsSLADefinitions(companyId, name, processId);

		if (ListUtil.isNotEmpty(workflowMetricsSLADefinitions)) {
			return workflowMetricsSLADefinitions.get(
				workflowMetricsSLADefinitions.size() - 1);
		}

		return _workflowMetricsSLADefinitionLocalService.
			addWorkflowMetricsSLADefinition(
				null, null, duration, name, null, processId, startNodeKeys,
				stopNodeKeys,
				new ServiceContext() {
					{
						setCompanyId(companyId);
						setUserId(userId);
					}
				});
	}

	private String[] _toStringArray(String... nodeKeys) {
		if (nodeKeys == null) {
			return new String[0];
		}

		return nodeKeys;
	}

	@Reference
	private Language _language;

	@Reference(target = "(workflow.metrics.index.entity.name=node)")
	private WorkflowMetricsIndexNameBuilder
		_nodeWorkflowMetricsIndexNameBuilder;

	@Reference(target = "(workflow.metrics.index.entity.name=process)")
	private WorkflowMetricsIndexNameBuilder
		_processWorkflowMetricsIndexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

	private final List<Long> _workflowMetricsSLADefinitionIds =
		new CopyOnWriteArrayList<>();

	@Reference
	private WorkflowMetricsSLADefinitionLocalService
		_workflowMetricsSLADefinitionLocalService;

}