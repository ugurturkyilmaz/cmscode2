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

package com.liferay.portal.workflow.kaleo.runtime.internal;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskDueDateException;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskForm;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.TaskManager;
import com.liferay.portal.workflow.kaleo.runtime.action.KaleoActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.form.FormDefinitionRetriever;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationHelper;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = AopService.class)
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.REQUIRED,
	rollbackFor = Exception.class
)
public class DefaultTaskManagerImpl
	extends BaseKaleoBean implements AopService, TaskManager {

	@Override
	public WorkflowTask assignWorkflowTaskToRole(
			long workflowTaskId, long roleId, String comment, Date dueDate,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return _assignWorkflowTask(
				workflowTaskId, Role.class.getName(), roleId, comment, dueDate,
				workflowContext, serviceContext);
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

	@Override
	public WorkflowTask assignWorkflowTaskToUser(
			long workflowTaskId, long assigneeUserId, String comment,
			Date dueDate, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return _assignWorkflowTask(
				workflowTaskId, User.class.getName(), assigneeUserId, comment,
				dueDate, workflowContext, serviceContext);
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

	@Override
	public WorkflowTask completeWorkflowTask(
			long workflowTaskId, long workflowTaskFormId, String formValues,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		return completeWorkflowTask(
			workflowTaskId, workflowTaskFormId, formValues, null,
			workflowContext, serviceContext);
	}

	@Override
	public WorkflowTask completeWorkflowTask(
			long workflowTaskId, long workflowTaskFormId, String formValues,
			String transitionName, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(
					workflowTaskId);

			kaleoTaskFormInstanceLocalService.addKaleoTaskFormInstance(
				kaleoTaskInstanceToken.getGroupId(), workflowTaskFormId,
				formValues, kaleoTaskInstanceToken, serviceContext);

			if (kaleoTaskInstanceTokenLocalService.hasPendingKaleoTaskForms(
					workflowTaskId)) {

				return _kaleoWorkflowModelConverter.toWorkflowTask(
					kaleoTaskInstanceToken, workflowContext);
			}

			if (Validator.isNull(transitionName)) {
				KaleoTask kaleoTask = kaleoTaskLocalService.getKaleoTask(
					kaleoTaskInstanceToken.getKaleoTaskId());

				KaleoNode kaleoNode = kaleoTask.getKaleoNode();

				List<KaleoTransition> kaleoTransitions =
					kaleoNode.getKaleoTransitions();

				if (kaleoTransitions.size() == 1) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Automatically completing form task: " +
								kaleoTask.getName());
					}

					KaleoTransition kaleoTransition = kaleoTransitions.get(0);

					transitionName = kaleoTransition.getName();
				}
			}

			return _completeWorkflowTask(
				workflowTaskId, transitionName, null, workflowContext,
				serviceContext);
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

	@Override
	public WorkflowTask completeWorkflowTask(
			long workflowTaskId, String transitionName, String comment,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return _completeWorkflowTask(
				workflowTaskId, transitionName, comment, workflowContext,
				serviceContext);
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

	@Override
	public List<String> getWorkflowTaskFormDefinitions(
			long workflowTaskId, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(
					workflowTaskId);

			List<KaleoTaskForm> kaleoTaskForms =
				kaleoTaskFormLocalService.getKaleoTaskForms(
					kaleoTaskInstanceToken.getKaleoTaskId());

			List<String> kaleoTaskFormDefinitions = new ArrayList<>(
				kaleoTaskForms.size());

			for (KaleoTaskForm kaleoTaskForm : kaleoTaskForms) {
				String kaleoFormDefinition = kaleoTaskForm.getFormDefinition();

				if (Validator.isNull(kaleoFormDefinition)) {
					FormDefinitionRetriever formDefinitionRetriever =
						_getFormDefinitionRetriever();

					if (formDefinitionRetriever != null) {
						kaleoFormDefinition =
							formDefinitionRetriever.getFormDefinition(
								kaleoTaskForm, kaleoTaskInstanceToken);
					}
					else {
						if (_log.isWarnEnabled()) {
							_log.warn("No form definition retriever defined");
						}
					}
				}

				if (Validator.isNotNull(kaleoFormDefinition)) {
					kaleoTaskFormDefinitions.add(kaleoFormDefinition);
				}
			}

			return kaleoTaskFormDefinitions;
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (PortalException portalException) {
			throw new WorkflowException(portalException);
		}
	}

	@Override
	public WorkflowTask updateDueDate(
			long workflowTaskId, String comment, Date dueDate,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(
					workflowTaskId);

			if (kaleoTaskInstanceToken.isCompleted()) {
				throw new WorkflowException(
					"Cannot update due date for completed task " +
						workflowTaskId);
			}

			if (dueDate != null) {
				Date createDate = kaleoTaskInstanceToken.getCreateDate();

				if (createDate.after(dueDate)) {
					throw new WorkflowTaskDueDateException();
				}

				kaleoTaskInstanceToken =
					kaleoTaskInstanceTokenLocalService.updateDueDate(
						workflowTaskId, dueDate, serviceContext);
			}

			Map<String, Serializable> workflowContext =
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext());

			kaleoLogLocalService.addTaskUpdateKaleoLog(
				kaleoTaskInstanceToken, comment, workflowContext,
				serviceContext);

			return _kaleoWorkflowModelConverter.toWorkflowTask(
				kaleoTaskInstanceToken, workflowContext);
		}
		catch (WorkflowException workflowException) {
			throw workflowException;
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

	private WorkflowTask _assignWorkflowTask(
			long workflowTaskId, String assigneeClassName, long assigneeClassPK,
			String comment, Date dueDate,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(
				workflowTaskId);

		List<KaleoTaskAssignmentInstance> previousTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		workflowContext = _updateWorkflowContext(
			workflowContext, kaleoTaskInstanceToken);

		if (kaleoTaskInstanceToken.isCompleted()) {
			throw new WorkflowException(
				"Cannot reassign a completed task " + workflowTaskId);
		}

		if (dueDate != null) {
			kaleoTaskInstanceToken =
				kaleoTaskInstanceTokenLocalService.updateDueDate(
					workflowTaskId, dueDate, serviceContext);
		}

		kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.assignKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				assigneeClassName, assigneeClassPK, workflowContext,
				serviceContext);

		workflowContext.put(WorkflowConstants.CONTEXT_TASK_COMMENTS, comment);

		ExecutionContext executionContext = new ExecutionContext(
			kaleoTaskInstanceToken.getKaleoInstanceToken(),
			kaleoTaskInstanceToken, workflowContext, serviceContext);

		KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

		_kaleoActionExecutor.executeKaleoActions(
			KaleoNode.class.getName(), kaleoTask.getKaleoNodeId(),
			ExecutionType.ON_ASSIGNMENT, executionContext);

		boolean selfAssignment = false;

		if (assigneeClassName.equals(User.class.getName()) &&
			(assigneeClassPK == serviceContext.getUserId())) {

			selfAssignment = true;
		}

		if (!selfAssignment) {
			_notificationHelper.sendKaleoNotifications(
				KaleoNode.class.getName(), kaleoTask.getKaleoNodeId(),
				ExecutionType.ON_ASSIGNMENT, executionContext);
		}

		kaleoLogLocalService.addTaskAssignmentKaleoLogs(
			previousTaskAssignmentInstances, kaleoTaskInstanceToken, comment,
			workflowContext, serviceContext);

		return _kaleoWorkflowModelConverter.toWorkflowTask(
			kaleoTaskInstanceToken, workflowContext);
	}

	private WorkflowTask _completeWorkflowTask(
			long workflowTaskId, String transitionName, String comment,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(
				workflowTaskId);

		if (Validator.isNotNull(transitionName)) {

			// Validate that the transition actually exists before moving
			// forward

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			KaleoNode currentKaleoNode = kaleoTask.getKaleoNode();

			currentKaleoNode.getKaleoTransition(transitionName);
		}

		workflowContext = _updateWorkflowContext(
			workflowContext, kaleoTaskInstanceToken);

		if (kaleoTaskInstanceToken.isCompleted()) {
			throw new WorkflowException(
				StringBundler.concat(
					"Cannot complete an already completed task ",
					workflowTaskId, " for user ", serviceContext.getUserId()));
		}

		serviceContext.setScopeGroupId(kaleoTaskInstanceToken.getGroupId());

		kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenLocalService.completeKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				serviceContext);

		kaleoLogLocalService.addTaskCompletionKaleoLog(
			kaleoTaskInstanceToken, comment, workflowContext, serviceContext);

		return _kaleoWorkflowModelConverter.toWorkflowTask(
			kaleoTaskInstanceToken, workflowContext);
	}

	private FormDefinitionRetriever _getFormDefinitionRetriever() {
		return _serviceTracker.getService();
	}

	private Map<String, Serializable> _updateWorkflowContext(
			Map<String, Serializable> workflowContext,
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws Exception {

		KaleoInstance kaleoInstance =
			kaleoInstanceLocalService.getKaleoInstance(
				kaleoTaskInstanceToken.getKaleoInstanceId());

		if (workflowContext == null) {
			workflowContext = WorkflowContextUtil.convert(
				kaleoInstance.getWorkflowContext());
		}
		else {
			Map<String, Serializable> storedWorkflowContext =
				WorkflowContextUtil.convert(kaleoInstance.getWorkflowContext());

			for (Map.Entry<String, Serializable> entry :
					storedWorkflowContext.entrySet()) {

				String key = entry.getKey();

				if (!workflowContext.containsKey(key)) {
					workflowContext.put(key, entry.getValue());
				}
			}
		}

		return workflowContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultTaskManagerImpl.class);

	private static final ServiceTracker
		<FormDefinitionRetriever, FormDefinitionRetriever> _serviceTracker =
			ServiceTrackerFactory.open(
				FrameworkUtil.getBundle(DefaultTaskManagerImpl.class),
				FormDefinitionRetriever.class);

	@Reference
	private KaleoActionExecutor _kaleoActionExecutor;

	@Reference
	private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

	@Reference
	private NotificationHelper _notificationHelper;

}