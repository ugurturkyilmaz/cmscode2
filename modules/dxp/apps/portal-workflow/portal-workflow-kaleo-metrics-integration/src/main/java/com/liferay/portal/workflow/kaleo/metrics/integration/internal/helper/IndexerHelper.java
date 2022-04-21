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

package com.liferay.portal.workflow.kaleo.metrics.integration.internal.helper;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.metrics.model.AddNodeRequest;
import com.liferay.portal.workflow.metrics.model.AddProcessRequest;
import com.liferay.portal.workflow.metrics.model.AddTaskRequest;
import com.liferay.portal.workflow.metrics.model.AddTransitionRequest;
import com.liferay.portal.workflow.metrics.model.Assignment;
import com.liferay.portal.workflow.metrics.model.DeleteProcessRequest;
import com.liferay.portal.workflow.metrics.model.DeleteTransitionRequest;
import com.liferay.portal.workflow.metrics.model.RoleAssignment;
import com.liferay.portal.workflow.metrics.model.UpdateProcessRequest;
import com.liferay.portal.workflow.metrics.model.UserAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = IndexerHelper.class)
public class IndexerHelper {

	public AddNodeRequest createAddNodeRequest(
		KaleoDefinitionVersion kaleoDefinitionVersion, KaleoNode kaleoNode) {

		AddNodeRequest.Builder builder = new AddNodeRequest.Builder();

		return builder.companyId(
			kaleoNode.getCompanyId()
		).createDate(
			kaleoNode.getCreateDate()
		).initial(
			kaleoNode.isInitial()
		).modifiedDate(
			kaleoNode.getModifiedDate()
		).name(
			kaleoNode.getName()
		).nodeId(
			kaleoNode.getKaleoNodeId()
		).processId(
			kaleoNode.getKaleoDefinitionId()
		).processVersion(
			kaleoDefinitionVersion.getVersion()
		).terminal(
			kaleoNode.isTerminal()
		).type(
			kaleoNode.getType()
		).build();
	}

	public AddNodeRequest createAddNodeRequest(
		KaleoDefinitionVersion kaleoDefinitionVersion, KaleoTask kaleoTask) {

		AddNodeRequest.Builder builder = new AddNodeRequest.Builder();

		return builder.companyId(
			kaleoTask.getCompanyId()
		).createDate(
			kaleoTask.getCreateDate()
		).initial(
			false
		).modifiedDate(
			kaleoTask.getModifiedDate()
		).name(
			kaleoTask.getName()
		).nodeId(
			kaleoTask.getKaleoTaskId()
		).processId(
			kaleoTask.getKaleoDefinitionId()
		).processVersion(
			kaleoDefinitionVersion.getVersion()
		).terminal(
			false
		).type(
			NodeType.TASK.name()
		).build();
	}

	public AddProcessRequest createAddProcessRequest(
		long companyId, KaleoDefinition kaleoDefinition) {

		AddProcessRequest.Builder builder = new AddProcessRequest.Builder();

		builder.active(
			kaleoDefinition.isActive()
		).companyId(
			kaleoDefinition.getCompanyId()
		).createDate(
			kaleoDefinition.getCreateDate()
		).description(
			kaleoDefinition.getDescription()
		).modifiedDate(
			kaleoDefinition.getModifiedDate()
		).name(
			kaleoDefinition.getName()
		).processId(
			kaleoDefinition.getKaleoDefinitionId()
		).title(
			kaleoDefinition.getTitle(
				LocalizationUtil.getDefaultLanguageId(
					kaleoDefinition.getTitle()))
		).titleMap(
			kaleoDefinition.getTitleMap()
		);

		String version = StringBundler.concat(
			kaleoDefinition.getVersion(), CharPool.PERIOD, 0);

		builder.version(version);

		try {
			List<KaleoDefinitionVersion> kaleoDefinitionVersions =
				_kaleoDefinitionVersionLocalService.getKaleoDefinitionVersions(
					companyId, kaleoDefinition.getName());

			if (kaleoDefinitionVersions != null) {
				return builder.versions(
					Stream.of(
						kaleoDefinitionVersions
					).flatMap(
						List::stream
					).map(
						KaleoDefinitionVersion::getVersion
					).toArray(
						String[]::new
					)
				).build();
			}
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}
		}

		return builder.versions(
			new String[] {version}
		).build();
	}

	public AddTaskRequest createAddTaskRequest(
		KaleoInstance kaleoInstance,
		KaleoTaskInstanceToken kaleoTaskInstanceToken, String processVersion) {

		AddTaskRequest.Builder builder = new AddTaskRequest.Builder();

		builder.assetTitleMap(
			createAssetTitleLocalizationMap(
				kaleoTaskInstanceToken.getClassName(),
				kaleoTaskInstanceToken.getClassPK(),
				kaleoTaskInstanceToken.getGroupId())
		).assetTypeMap(
			createAssetTypeLocalizationMap(
				kaleoTaskInstanceToken.getClassName(),
				kaleoTaskInstanceToken.getGroupId())
		).assignments(
			() -> toAssignments(
				_kaleoTaskAssignmentInstanceLocalService.
					getKaleoTaskAssignmentInstances(
						kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()))
		).className(
			kaleoTaskInstanceToken.getClassName()
		).classPK(
			kaleoTaskInstanceToken.getClassPK()
		).companyId(
			kaleoTaskInstanceToken.getCompanyId()
		).completed(
			kaleoTaskInstanceToken.isCompleted()
		).completionDate(
			kaleoTaskInstanceToken.getCompletionDate()
		).completionUserId(
			kaleoTaskInstanceToken.getCompletionUserId()
		).createDate(
			kaleoTaskInstanceToken.getCreateDate()
		);

		if (kaleoInstance != null) {
			builder.instanceCompleted(
				kaleoInstance.isCompleted()
			).instanceCompletionDate(
				kaleoInstance.getCompletionDate()
			);
		}

		return builder.instanceId(
			kaleoTaskInstanceToken.getKaleoInstanceId()
		).modifiedDate(
			kaleoTaskInstanceToken.getModifiedDate()
		).name(
			kaleoTaskInstanceToken.getKaleoTaskName()
		).nodeId(
			kaleoTaskInstanceToken.getKaleoTaskId()
		).processId(
			kaleoTaskInstanceToken.getKaleoDefinitionId()
		).processVersion(
			processVersion
		).taskId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()
		).userId(
			kaleoTaskInstanceToken.getUserId()
		).build();
	}

	public AddTransitionRequest createAddTransitionRequest(
			KaleoTransition kaleoTransition, String processVersion)
		throws PortalException {

		AddTransitionRequest.Builder builder =
			new AddTransitionRequest.Builder();

		return builder.companyId(
			kaleoTransition.getCompanyId()
		).createDate(
			kaleoTransition.getCreateDate()
		).modifiedDate(
			kaleoTransition.getModifiedDate()
		).name(
			kaleoTransition.getName()
		).nodeId(
			_getNodeId(kaleoTransition.getKaleoNodeId())
		).processId(
			kaleoTransition.getKaleoDefinitionId()
		).processVersion(
			processVersion
		).sourceNodeId(
			_getNodeId(kaleoTransition.getSourceKaleoNodeId())
		).sourceNodeName(
			kaleoTransition.getSourceKaleoNodeName()
		).targetNodeId(
			_getNodeId(kaleoTransition.getTargetKaleoNodeId())
		).targetNodeName(
			kaleoTransition.getTargetKaleoNodeName()
		).transitionId(
			kaleoTransition.getKaleoTransitionId()
		).userId(
			kaleoTransition.getUserId()
		).build();
	}

	public Map<Locale, String> createAssetTitleLocalizationMap(
		String className, long classPK, long groupId) {

		AssetRenderer<?> assetRenderer = _getAssetRenderer(className, classPK);

		if (assetRenderer != null) {
			AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
				assetRenderer.getClassName(), assetRenderer.getClassPK());

			if (assetEntry != null) {
				return LocalizationUtil.populateLocalizationMap(
					assetEntry.getTitleMap(), assetEntry.getDefaultLanguageId(),
					assetEntry.getGroupId());
			}
		}

		WorkflowHandler<?> workflowHandler =
			WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

		if (workflowHandler != null) {
			Map<Locale, String> localizationMap = new HashMap<>();

			for (Locale availableLocale :
					LanguageUtil.getAvailableLocales(groupId)) {

				localizationMap.put(
					availableLocale,
					workflowHandler.getTitle(classPK, availableLocale));
			}

			return localizationMap;
		}

		return Collections.emptyMap();
	}

	public Map<Locale, String> createAssetTypeLocalizationMap(
		String className, long groupId) {

		Map<Locale, String> localizationMap = new HashMap<>();

		for (Locale availableLocale :
				LanguageUtil.getAvailableLocales(groupId)) {

			localizationMap.put(
				availableLocale,
				ResourceActionsUtil.getModelResource(
					availableLocale, className));
		}

		return localizationMap;
	}

	public DeleteProcessRequest createDeleteProcessRequest(
		KaleoDefinition kaleoDefinition) {

		DeleteProcessRequest.Builder builder =
			new DeleteProcessRequest.Builder();

		return builder.companyId(
			kaleoDefinition.getCompanyId()
		).processId(
			kaleoDefinition.getKaleoDefinitionId()
		).build();
	}

	public DeleteTransitionRequest createDeleteTransitionRequest(
		KaleoTransition kaleoTransition) {

		DeleteTransitionRequest.Builder builder =
			new DeleteTransitionRequest.Builder();

		return builder.companyId(
			kaleoTransition.getCompanyId()
		).transitionId(
			kaleoTransition.getKaleoTransitionId()
		).build();
	}

	public UpdateProcessRequest createUpdateProcessRequest(
		KaleoDefinition kaleoDefinition) {

		UpdateProcessRequest.Builder builder =
			new UpdateProcessRequest.Builder();

		return builder.active(
			kaleoDefinition.isActive()
		).companyId(
			kaleoDefinition.getCompanyId()
		).description(
			kaleoDefinition.getDescription()
		).modifiedDate(
			kaleoDefinition.getModifiedDate()
		).processId(
			kaleoDefinition.getKaleoDefinitionId()
		).title(
			kaleoDefinition.getTitle(
				LocalizationUtil.getDefaultLanguageId(
					kaleoDefinition.getTitle()))
		).titleMap(
			kaleoDefinition.getTitleMap()
		).version(
			StringBundler.concat(
				kaleoDefinition.getVersion(), CharPool.PERIOD, 0)
		).build();
	}

	public List<Assignment> toAssignments(
		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {

		List<Assignment> assignments = new ArrayList<>();

		if (ListUtil.isEmpty(kaleoTaskAssignmentInstances)) {
			return assignments;
		}

		KaleoTaskAssignmentInstance firstKaleoTaskAssignmentInstance =
			kaleoTaskAssignmentInstances.get(0);

		if (Objects.equals(
				firstKaleoTaskAssignmentInstance.getAssigneeClassName(),
				User.class.getName())) {

			User user = _userLocalService.fetchUser(
				firstKaleoTaskAssignmentInstance.getAssigneeClassPK());

			assignments.add(
				new UserAssignment(
					firstKaleoTaskAssignmentInstance.getAssigneeClassPK(),
					user.getFullName()));
		}
		else {
			Stream.of(
				kaleoTaskAssignmentInstances
			).flatMap(
				List::stream
			).collect(
				Collectors.groupingBy(
					KaleoTaskAssignmentInstance::getAssigneeClassPK,
					Collectors.mapping(
						KaleoTaskAssignmentInstance::getGroupId,
						Collectors.toList()))
			).forEach(
				(assignmentId, assignmentGroupIds) -> assignments.add(
					new RoleAssignment(assignmentId, assignmentGroupIds))
			);
		}

		return assignments;
	}

	private AssetRenderer<?> _getAssetRenderer(String className, long classPK) {
		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory != null) {
			try {
				return assetRendererFactory.getAssetRenderer(classPK);
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}

		return null;
	}

	private long _getNodeId(long kaleoNodeId) throws PortalException {
		KaleoNode kaleoNode = _kaleoNodeLocalService.fetchKaleoNode(
			kaleoNodeId);

		if ((kaleoNode == null) ||
			!Objects.equals(kaleoNode.getType(), NodeType.TASK.name())) {

			return kaleoNodeId;
		}

		KaleoTask kaleoTask = _kaleoTaskLocalService.getKaleoNodeKaleoTask(
			kaleoNode.getKaleoNodeId());

		return kaleoTask.getKaleoTaskId();
	}

	private static final Log _log = LogFactoryUtil.getLog(IndexerHelper.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private KaleoDefinitionVersionLocalService
		_kaleoDefinitionVersionLocalService;

	@Reference
	private KaleoNodeLocalService _kaleoNodeLocalService;

	@Reference
	private KaleoTaskAssignmentInstanceLocalService
		_kaleoTaskAssignmentInstanceLocalService;

	@Reference
	private KaleoTaskLocalService _kaleoTaskLocalService;

	@Reference
	private UserLocalService _userLocalService;

}