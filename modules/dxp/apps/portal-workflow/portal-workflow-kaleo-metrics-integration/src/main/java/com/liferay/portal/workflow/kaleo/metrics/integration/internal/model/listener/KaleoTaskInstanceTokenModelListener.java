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

package com.liferay.portal.workflow.kaleo.metrics.integration.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.workflow.kaleo.metrics.integration.internal.helper.IndexerHelper;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.metrics.model.CompleteTaskRequest;
import com.liferay.portal.workflow.metrics.model.DeleteTaskRequest;
import com.liferay.portal.workflow.metrics.model.UpdateTaskRequest;
import com.liferay.portal.workflow.metrics.search.index.TaskWorkflowMetricsIndexer;

import java.time.Duration;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoTaskInstanceTokenModelListener
	extends BaseKaleoModelListener<KaleoTaskInstanceToken> {

	@Override
	public void onAfterCreate(KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws ModelListenerException {

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				KaleoDefinitionVersion kaleoDefinitionVersion =
					getKaleoDefinitionVersion(
						kaleoTaskInstanceToken.getKaleoDefinitionVersionId());

				if (Objects.isNull(kaleoDefinitionVersion)) {
					return null;
				}

				_taskWorkflowMetricsIndexer.addTask(
					_indexerHelper.createAddTaskRequest(
						null, kaleoTaskInstanceToken,
						kaleoDefinitionVersion.getVersion()));

				return null;
			});
	}

	@Override
	public void onAfterUpdate(
			KaleoTaskInstanceToken originalKaleoTaskInstanceToken,
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws ModelListenerException {

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
					_kaleoTaskAssignmentInstanceLocalService.
						getKaleoTaskAssignmentInstances(
							kaleoTaskInstanceToken.
								getKaleoTaskInstanceTokenId());

				if (!kaleoTaskAssignmentInstances.isEmpty()) {
					UpdateTaskRequest.Builder updateTaskRequestBuilder =
						new UpdateTaskRequest.Builder();

					_taskWorkflowMetricsIndexer.updateTask(
						updateTaskRequestBuilder.assetTitleMap(
							_indexerHelper.createAssetTitleLocalizationMap(
								kaleoTaskInstanceToken.getClassName(),
								kaleoTaskInstanceToken.getClassPK(),
								kaleoTaskInstanceToken.getGroupId())
						).assetTypeMap(
							_indexerHelper.createAssetTypeLocalizationMap(
								kaleoTaskInstanceToken.getClassName(),
								kaleoTaskInstanceToken.getGroupId())
						).assignments(
							_indexerHelper.toAssignments(
								kaleoTaskAssignmentInstances)
						).companyId(
							kaleoTaskInstanceToken.getCompanyId()
						).modifiedDate(
							kaleoTaskInstanceToken.getModifiedDate()
						).taskId(
							kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()
						).userId(
							kaleoTaskInstanceToken.getUserId()
						).build());
				}

				return null;
			});
	}

	@Override
	public void onBeforeRemove(KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws ModelListenerException {

		DeleteTaskRequest.Builder deleteTaskRequestBuilder =
			new DeleteTaskRequest.Builder();

		_taskWorkflowMetricsIndexer.deleteTask(
			deleteTaskRequestBuilder.companyId(
				kaleoTaskInstanceToken.getCompanyId()
			).taskId(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()
			).build());
	}

	@Override
	public void onBeforeUpdate(
			KaleoTaskInstanceToken originalKaleoTaskInstanceToken,
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws ModelListenerException {

		KaleoTaskInstanceToken currentKaleoTaskInstanceToken =
			_kaleoTaskInstanceTokenLocalService.fetchKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		if (currentKaleoTaskInstanceToken.isCompleted() ||
			!kaleoTaskInstanceToken.isCompleted()) {

			return;
		}

		CompleteTaskRequest.Builder completeTaskRequestBuilder =
			new CompleteTaskRequest.Builder();

		_taskWorkflowMetricsIndexer.completeTask(
			completeTaskRequestBuilder.companyId(
				kaleoTaskInstanceToken.getCompanyId()
			).completionDate(
				kaleoTaskInstanceToken.getCompletionDate()
			).completionUserId(
				kaleoTaskInstanceToken.getCompletionUserId()
			).duration(
				() -> {
					Date createDate = kaleoTaskInstanceToken.getCreateDate();
					Date completionDate =
						kaleoTaskInstanceToken.getCompletionDate();

					Duration duration = Duration.between(
						createDate.toInstant(), completionDate.toInstant());

					return duration.toMillis();
				}
			).modifiedDate(
				kaleoTaskInstanceToken.getModifiedDate()
			).taskId(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()
			).userId(
				kaleoTaskInstanceToken.getUserId()
			).build());
	}

	@Reference
	private IndexerHelper _indexerHelper;

	@Reference
	private KaleoTaskAssignmentInstanceLocalService
		_kaleoTaskAssignmentInstanceLocalService;

	@Reference
	private KaleoTaskInstanceTokenLocalService
		_kaleoTaskInstanceTokenLocalService;

	@Reference
	private TaskWorkflowMetricsIndexer _taskWorkflowMetricsIndexer;

}