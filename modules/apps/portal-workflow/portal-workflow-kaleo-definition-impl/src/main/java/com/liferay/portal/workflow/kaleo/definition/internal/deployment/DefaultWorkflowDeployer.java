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

package com.liferay.portal.workflow.kaleo.definition.internal.deployment;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.workflow.configuration.WorkflowDefinitionConfiguration;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.deployment.WorkflowDeployer;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.workflow.configuration.WorkflowDefinitionConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = WorkflowDeployer.class
)
public class DefaultWorkflowDeployer implements WorkflowDeployer {

	@Override
	public WorkflowDefinition deploy(
			String title, String name, String scope, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		_checkPermissions(serviceContext);

		KaleoDefinition kaleoDefinition = _addOrUpdateKaleoDefinition(
			title, name, scope, definition, serviceContext);

		KaleoDefinitionVersion kaleoDefinitionVersion =
			_kaleoDefinitionVersionLocalService.
				fetchLatestKaleoDefinitionVersion(
					kaleoDefinition.getCompanyId(), kaleoDefinition.getName());

		long kaleoDefinitionVersionId =
			kaleoDefinitionVersion.getKaleoDefinitionVersionId();

		Collection<Node> nodes = definition.getNodes();

		Map<String, KaleoNode> kaleoNodesMap = new HashMap<>();

		for (Node node : nodes) {
			KaleoNode kaleoNode = _kaleoNodeLocalService.addKaleoNode(
				kaleoDefinition.getKaleoDefinitionId(),
				kaleoDefinitionVersionId, node, serviceContext);

			kaleoNodesMap.put(node.getName(), kaleoNode);

			NodeType nodeType = node.getNodeType();

			if (nodeType.equals(NodeType.TASK)) {
				Task task = (Task)node;

				_kaleoTaskLocalService.addKaleoTask(
					kaleoDefinition.getKaleoDefinitionId(),
					kaleoDefinitionVersionId, kaleoNode.getKaleoNodeId(), task,
					serviceContext);
			}
			else if (nodeType.equals(NodeType.CONDITION)) {
				Condition condition = (Condition)node;

				_kaleoConditionLocalService.addKaleoCondition(
					kaleoDefinition.getKaleoDefinitionId(),
					kaleoDefinitionVersionId, kaleoNode.getKaleoNodeId(),
					condition, serviceContext);
			}
		}

		for (Node node : nodes) {
			KaleoNode kaleoNode = kaleoNodesMap.get(node.getName());

			for (Transition transition : node.getOutgoingTransitionsList()) {
				Node sourceNode = transition.getSourceNode();

				KaleoNode sourceKaleoNode = kaleoNodesMap.get(
					sourceNode.getName());

				if (sourceKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetSourceNode(sourceNode.getName());
				}

				Node targetNode = transition.getTargetNode();

				KaleoNode targetKaleoNode = kaleoNodesMap.get(
					targetNode.getName());

				if (targetKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetTargetNode(targetNode.getName());
				}

				_kaleoTransitionLocalService.addKaleoTransition(
					kaleoNode.getKaleoDefinitionId(),
					kaleoNode.getKaleoDefinitionVersionId(),
					kaleoNode.getKaleoNodeId(), transition, sourceKaleoNode,
					targetKaleoNode, serviceContext);
			}
		}

		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new KaleoDefinitionValidationException.
				MustSetInitialStateNode();
		}

		String startKaleoNodeName = initialState.getName();

		KaleoNode kaleoNode = kaleoNodesMap.get(startKaleoNodeName);

		_kaleoDefinitionLocalService.activateKaleoDefinition(
			kaleoDefinition.getKaleoDefinitionId(), kaleoDefinitionVersionId,
			kaleoNode.getKaleoNodeId(), serviceContext);

		return _kaleoWorkflowModelConverter.toWorkflowDefinition(
			_kaleoDefinitionLocalService.getKaleoDefinition(
				kaleoDefinition.getKaleoDefinitionId()));
	}

	@Override
	public WorkflowDefinition save(
			String title, String name, String scope, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition = _addOrUpdateKaleoDefinition(
			title, name, scope, definition, serviceContext);

		return _kaleoWorkflowModelConverter.toWorkflowDefinition(
			kaleoDefinition);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		WorkflowDefinitionConfiguration workflowDefinitionConfiguration =
			ConfigurableUtil.createConfigurable(
				WorkflowDefinitionConfiguration.class, properties);

		_companyAdministratorCanPublish =
			workflowDefinitionConfiguration.companyAdministratorCanPublish();
	}

	private KaleoDefinition _addOrUpdateKaleoDefinition(
			String title, String name, String scope, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			_kaleoDefinitionLocalService.fetchKaleoDefinition(
				name, serviceContext);

		if (kaleoDefinition == null) {
			kaleoDefinition = _kaleoDefinitionLocalService.addKaleoDefinition(
				name, title, definition.getDescription(),
				definition.getContent(), scope, 1, serviceContext);
		}
		else {
			kaleoDefinition =
				_kaleoDefinitionLocalService.updatedKaleoDefinition(
					kaleoDefinition.getKaleoDefinitionId(), title,
					definition.getDescription(), definition.getContent(),
					serviceContext);
		}

		return kaleoDefinition;
	}

	private void _checkPermissions(ServiceContext serviceContext)
		throws PrincipalException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((permissionChecker == null) ||
			!GetterUtil.getBoolean(
				serviceContext.getAttribute("checkPermission"), true)) {

			return;
		}

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException.MustBeCompanyAdmin(
				permissionChecker.getUserId());
		}

		if (_companyAdministratorCanPublish) {
			return;
		}

		if (!permissionChecker.isOmniadmin()) {
			throw new PrincipalException.MustBeOmniadmin(
				permissionChecker.getUserId());
		}
	}

	private volatile boolean _companyAdministratorCanPublish;

	@Reference
	private KaleoConditionLocalService _kaleoConditionLocalService;

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference
	private KaleoDefinitionVersionLocalService
		_kaleoDefinitionVersionLocalService;

	@Reference
	private KaleoNodeLocalService _kaleoNodeLocalService;

	@Reference
	private KaleoTaskLocalService _kaleoTaskLocalService;

	@Reference
	private KaleoTransitionLocalService _kaleoTransitionLocalService;

	@Reference
	private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

}