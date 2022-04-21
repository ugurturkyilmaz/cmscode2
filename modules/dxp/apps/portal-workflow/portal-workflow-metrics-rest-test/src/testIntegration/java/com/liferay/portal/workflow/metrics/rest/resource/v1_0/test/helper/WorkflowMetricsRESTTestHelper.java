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

package com.liferay.portal.workflow.metrics.rest.resource.v1_0.test.helper;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.DocumentBuilder;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.portal.workflow.metrics.model.AddNodeRequest;
import com.liferay.portal.workflow.metrics.model.AddProcessRequest;
import com.liferay.portal.workflow.metrics.model.AddTaskRequest;
import com.liferay.portal.workflow.metrics.model.Assignment;
import com.liferay.portal.workflow.metrics.model.CompleteTaskRequest;
import com.liferay.portal.workflow.metrics.model.DeleteNodeRequest;
import com.liferay.portal.workflow.metrics.model.DeleteProcessRequest;
import com.liferay.portal.workflow.metrics.model.RoleAssignment;
import com.liferay.portal.workflow.metrics.model.UpdateProcessRequest;
import com.liferay.portal.workflow.metrics.model.UpdateTaskRequest;
import com.liferay.portal.workflow.metrics.model.UserAssignment;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Assignee;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Creator;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Instance;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Node;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.NodeMetric;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Process;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.ProcessMetric;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.SLAResult;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Task;
import com.liferay.portal.workflow.metrics.search.index.InstanceWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.NodeWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.ProcessWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.TaskWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import org.junit.Assert;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = WorkflowMetricsRESTTestHelper.class)
public class WorkflowMetricsRESTTestHelper {

	public Instance addInstance(
			long companyId, boolean completed, long processId)
		throws Exception {

		Instance instance = new Instance();

		instance.setCreator(
			new Creator() {
				{
					id = RandomTestUtil.nextLong();
					name = RandomTestUtil.randomString();
				}
			});
		instance.setCompleted(completed);

		if (completed) {
			instance.setDateCompletion(RandomTestUtil.nextDate());
			instance.setDuration(1000L);
		}

		instance.setId(RandomTestUtil.randomLong());
		instance.setProcessId(processId);

		return addInstance(companyId, instance);
	}

	public Instance addInstance(long companyId, Instance instance)
		throws Exception {

		Creator creator = instance.getCreator();

		_instanceWorkflowMetricsIndexer.addInstance(
			_createLocalizationMap(instance.getAssetTitle()),
			_createLocalizationMap(instance.getAssetType()), StringPool.BLANK,
			GetterUtil.getLong(instance.getClassPK()), companyId, null,
			Optional.ofNullable(
				instance.getDateCreated()
			).orElseGet(
				Date::new
			),
			instance.getId(),
			Optional.ofNullable(
				instance.getDateModified()
			).orElseGet(
				Date::new
			),
			instance.getProcessId(), instance.getProcessVersion(),
			creator.getId(), creator.getName());

		_assertCount(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"active", true, "companyId", companyId, "deleted", false,
			"instanceId", instance.getId(), "processId",
			instance.getProcessId());

		return instance;
	}

	public Instance addInstance(long companyId, long processId)
		throws Exception {

		Instance instance = new Instance();

		instance.setCreator(
			new Creator() {
				{
					id = RandomTestUtil.randomLong();
					name = RandomTestUtil.randomString();
				}
			});
		instance.setId(RandomTestUtil.randomLong());
		instance.setProcessId(processId);

		return addInstance(companyId, instance);
	}

	public Node addNode(long companyId, long processId, String version)
		throws Exception {

		Node node = new Node();

		node.setId(RandomTestUtil.randomLong());
		node.setName(RandomTestUtil.randomString());
		node.setProcessVersion(version);

		return addNode(companyId, node, processId, version);
	}

	public Node addNode(
			long companyId, Node node, long processId, String version)
		throws Exception {

		AddNodeRequest.Builder builder = new AddNodeRequest.Builder();

		_nodeWorkflowMetricsIndexer.addNode(
			builder.companyId(
				companyId
			).createDate(
				Optional.ofNullable(
					node.getDateCreated()
				).orElseGet(
					Date::new
				)
			).initial(
				false
			).modifiedDate(
				Optional.ofNullable(
					node.getDateModified()
				).orElseGet(
					Date::new
				)
			).name(
				node.getName()
			).nodeId(
				node.getId()
			).processId(
				processId
			).processVersion(
				version
			).terminal(
				false
			).type(
				Optional.ofNullable(
					node.getType()
				).orElse(
					"TASK"
				)
			).build());

		_assertCount(
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "name", node.getName(),
			"processId", processId, "version", version);

		return node;
	}

	public NodeMetric addNodeMetric(
			Assignee assignee, long companyId,
			UnsafeSupplier<Instance, Exception> instanceSuplier, long processId,
			String status, User user)
		throws Exception {

		String randomString = RandomTestUtil.randomString();

		return addNodeMetric(
			assignee, companyId, instanceSuplier,
			new NodeMetric() {
				{
					durationAvg =
						Objects.equals(status, "COMPLETED") ? 1000L : 0L;
					instanceCount = 1L;
					node = new Node() {
						{
							id = RandomTestUtil.randomLong();
							label = randomString;
							name = randomString;
						}
					};
					onTimeInstanceCount = 0L;
					overdueInstanceCount = 0L;
				}
			},
			processId, status, user, "1.0");
	}

	public NodeMetric addNodeMetric(
			Assignee assignee, long companyId,
			UnsafeSupplier<Instance, Exception> instanceSuplier,
			NodeMetric nodeMetric, long processId, String status, User user,
			String version)
		throws Exception {

		Node node = addNode(
			companyId, nodeMetric.getNode(), processId, version);

		Long onTimeInstanceCount = nodeMetric.getOnTimeInstanceCount();
		Long overdueInstanceCount = nodeMetric.getOverdueInstanceCount();

		for (int i = 0; i < nodeMetric.getInstanceCount(); i++) {
			Instance instance = instanceSuplier.get();
			Long taskId = RandomTestUtil.nextLong();

			if (onTimeInstanceCount > 0) {
				addSLATaskResult(
					assignee.getId(), false, companyId, instance, node.getId(),
					true, status, taskId, node.getName());

				onTimeInstanceCount--;
			}
			else if (overdueInstanceCount > 0) {
				addSLATaskResult(
					assignee.getId(), true, companyId, instance, node.getId(),
					false, status, taskId, node.getName());

				overdueInstanceCount--;
			}

			List<Assignment> assignments = new ArrayList<>();

			for (long roleId : user.getRoleIds()) {
				assignments.add(
					new RoleAssignment(roleId, Collections.emptyList()));
			}

			addTask(
				assignee, assignments, companyId, nodeMetric.getDurationAvg(),
				instance, node.getName(), node.getId(), processId, version,
				taskId);

			if (instance.getCompleted()) {
				completeInstance(companyId, instance);
			}
		}

		_assertCount(
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "name", node.getName(),
			"processId", processId);

		return nodeMetric;
	}

	public Process addProcess(long companyId) throws Exception {
		Process process = new Process() {
			{
				id = RandomTestUtil.randomLong();
				title = RandomTestUtil.randomString();
				version = "1.0";
			}
		};

		return addProcess(companyId, process);
	}

	public Process addProcess(long companyId, Process process)
		throws Exception {

		AddProcessRequest.Builder builder = new AddProcessRequest.Builder();

		_processWorkflowMetricsIndexer.addProcess(
			builder.active(
				Optional.ofNullable(
					process.getActive()
				).orElseGet(
					() -> Boolean.TRUE
				)
			).companyId(
				companyId
			).createDate(
				Optional.ofNullable(
					process.getDateCreated()
				).orElseGet(
					Date::new
				)
			).description(
				process.getDescription()
			).modifiedDate(
				Optional.ofNullable(
					process.getDateModified()
				).orElseGet(
					Date::new
				)
			).name(
				process.getName()
			).processId(
				process.getId()
			).title(
				process.getTitle()
			).titleMap(
				LocalizedMapUtil.getLocalizedMap(process.getTitle_i18n())
			).version(
				process.getVersion()
			).versions(
				new String[] {process.getVersion()}
			).build());

		_assertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "processId",
			process.getId());

		return process;
	}

	public ProcessMetric addProcessMetric(long companyId) throws Exception {
		return addProcessMetric(companyId, "1.0");
	}

	public ProcessMetric addProcessMetric(
			long companyId, ProcessMetric processMetric)
		throws Exception {

		Process process = addProcess(companyId, processMetric.getProcess());

		Long onTimeInstanceCount = processMetric.getOnTimeInstanceCount();
		Long overdueInstanceCount = processMetric.getOverdueInstanceCount();

		for (int i = 0; i < processMetric.getInstanceCount(); i++) {
			Instance instance = addInstance(companyId, false, process.getId());

			if (onTimeInstanceCount > 0) {
				addSLAInstanceResults(
					companyId, instance,
					new SLAResult() {
						{
							dateModified = DateUtils.truncate(
								RandomTestUtil.nextDate(), Calendar.SECOND);
							dateOverdue = DateUtils.truncate(
								RandomTestUtil.nextDate(), Calendar.SECOND);
							id = RandomTestUtil.randomLong();
							name = null;
							onTime = true;
							remainingTime = 1L;
							status = null;
						}
					});

				onTimeInstanceCount--;
			}
			else if (overdueInstanceCount > 0) {
				addSLAInstanceResults(
					companyId, instance,
					new SLAResult() {
						{
							dateModified = DateUtils.truncate(
								RandomTestUtil.nextDate(), Calendar.SECOND);
							dateOverdue = DateUtils.truncate(
								RandomTestUtil.nextDate(), Calendar.SECOND);
							id = RandomTestUtil.randomLong();
							name = null;
							onTime = false;
							remainingTime = -1L;
							status = null;
						}
					});

				overdueInstanceCount--;
			}
		}

		return processMetric;
	}

	public ProcessMetric addProcessMetric(long companyId, String version)
		throws Exception {

		ProcessMetric processMetric = new ProcessMetric() {
			{
				instanceCount = 0L;
				onTimeInstanceCount = 0L;
				overdueInstanceCount = 0L;

				setProcess(
					() -> {
						Process process = new Process();

						process.setId(RandomTestUtil.randomLong());
						process.setTitle(RandomTestUtil.randomString());
						process.setVersion(version);

						return process;
					});

				untrackedInstanceCount = 0L;
			}
		};

		return addProcessMetric(companyId, processMetric);
	}

	public void addSLAInstanceResults(
			long companyId, Instance instance, SLAResult... slaResults)
		throws Exception {

		for (SLAResult slaResult : slaResults) {
			_invokeAddDocument(
				_getIndexer(_CLASS_NAME_SLA_INSTANCE_RESULT_INDEXER),
				_creatWorkflowMetricsSLAInstanceResultDocument(
					companyId, instance, slaResult));

			_assertCount(
				_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
					companyId),
				"blocked", false, "companyId", companyId, "deleted", false,
				"instanceCompleted",
				Objects.nonNull(instance.getDateCompletion()), "instanceId",
				instance.getId(), "onTime", slaResult.getOnTime(), "processId",
				instance.getProcessId(), "remainingTime",
				slaResult.getRemainingTime(), "slaDefinitionId",
				slaResult.getId());
		}

		_updateInstance(companyId, instance, slaResults);
	}

	public void addSLATaskResult(
			long assigneeId, boolean breached, long companyId,
			Instance instance, long nodeId, boolean onTime, String status,
			long taskId, String taskName)
		throws Exception {

		long slaDefinitionId = RandomTestUtil.randomLong();

		_invokeAddDocument(
			_getIndexer(_CLASS_NAME_SLA_TASK_RESULT_INDEXER),
			_creatWorkflowMetricsSLATaskResultDocument(
				assigneeId, breached, companyId,
				Objects.nonNull(instance.getDateCompletion()), instance.getId(),
				nodeId, onTime, instance.getProcessId(), slaDefinitionId,
				status, taskId, taskName));

		_assertCount(
			_slaTaskResultWorkflowMetricsIndexNameBuilder.getIndexName(
				companyId),
			"breached", breached, "assigneeIds", assigneeId, "assigneeType",
			User.class.getName(), "companyId", companyId, "deleted", false,
			"instanceCompleted", Objects.nonNull(instance.getDateCompletion()),
			"instanceId", instance.getId(), "onTime", onTime, "processId",
			instance.getProcessId(), "slaDefinitionId", slaDefinitionId,
			"taskId", taskId, "taskName", taskName);
	}

	public Task addTask(
			Assignee assignee, List<Assignment> assignments, long companyId,
			Instance instance)
		throws Exception {

		return addTask(
			assignee, assignments, companyId, 0L, instance,
			RandomTestUtil.randomString(), RandomTestUtil.randomLong(),
			instance.getProcessId(), "1.0", RandomTestUtil.randomLong());
	}

	public Task addTask(
			Assignee assignee, List<Assignment> assignments, long companyId,
			long durationAvg, Instance instance, String name, long nodeId,
			long processId, String processVersion, long taskId)
		throws Exception {

		if ((assignee != null) && (assignee.getId() != null) &&
			(assignee.getId() != -1L)) {

			assignments = new ArrayList<>();

			User user = _userLocalService.fetchUser(assignee.getId());

			assignments.add(
				new UserAssignment(assignee.getId(), user.getFullName()));
		}

		AddTaskRequest.Builder addTaskRequestBuilder =
			new AddTaskRequest.Builder();

		addTaskRequestBuilder.assetTitleMap(
			_createLocalizationMap(
				StringUtil.toLowerCase(RandomTestUtil.randomString()))
		).assetTypeMap(
			_createLocalizationMap(
				StringUtil.toLowerCase(RandomTestUtil.randomString()))
		).assignments(
			assignments
		).className(
			StringPool.BLANK
		).classPK(
			GetterUtil.getLong(instance.getClassPK())
		).companyId(
			companyId
		);

		if (assignments.get(0) instanceof UserAssignment) {
			addTaskRequestBuilder.completed(
				durationAvg > 0
			).completionDate(
				(durationAvg > 0) ? new Date() : null
			);

			UserAssignment userAssignment = (UserAssignment)assignments.get(0);

			addTaskRequestBuilder.completionUserId(
				() ->
					(durationAvg > 0) ? userAssignment.getAssignmentId() :
						null);
		}

		AddTaskRequest addTaskRequest = addTaskRequestBuilder.createDate(
			new Date()
		).instanceCompleted(
			instance.getCompleted()
		).instanceCompletionDate(
			instance.getDateCompletion()
		).instanceId(
			instance.getId()
		).modifiedDate(
			new Date()
		).name(
			name
		).nodeId(
			nodeId
		).processId(
			processId
		).processVersion(
			processVersion
		).taskId(
			taskId
		).userId(
			0L
		).build();

		_taskWorkflowMetricsIndexer.addTask(addTaskRequest);

		_assertCount(
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(
				addTaskRequest.getCompanyId()),
			"active", true, "companyId", addTaskRequest.getCompanyId(),
			"deleted", false, "instanceId", instance.getId(), "processId",
			addTaskRequest.getProcessId(), "nodeId", addTaskRequest.getNodeId(),
			"name", addTaskRequest.getName(), "taskId",
			addTaskRequest.getTaskId());

		if (!addTaskRequest.isCompleted()) {
			_assertCount(
				booleanQuery -> booleanQuery.addMustQueryClauses(
					_queries.nested(
						"tasks",
						_queries.term(
							"tasks.taskId", addTaskRequest.getTaskId()))),
				1,
				_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
					addTaskRequest.getCompanyId()),
				"companyId", addTaskRequest.getCompanyId(), "deleted", false,
				"instanceId", instance.getId(), "processId",
				addTaskRequest.getProcessId());
		}

		if (ListUtil.isNotEmpty(addTaskRequest.getAssignments())) {
			UpdateTaskRequest.Builder updateTaskRequestBuilder =
				new UpdateTaskRequest.Builder();

			_taskWorkflowMetricsIndexer.updateTask(
				updateTaskRequestBuilder.assetTitleMap(
					addTaskRequest.getAssetTitleMap()
				).assetTypeMap(
					addTaskRequest.getAssetTypeMap()
				).assignments(
					addTaskRequest.getAssignments()
				).companyId(
					addTaskRequest.getCompanyId()
				).modifiedDate(
					new Date()
				).taskId(
					addTaskRequest.getTaskId()
				).userId(
					0
				).build());

			Assignment assignment = assignments.get(0);

			String assignmentType = Role.class.getName();

			if (assignment instanceof UserAssignment) {
				assignmentType = User.class.getName();
			}

			_assertCount(
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
				"assigneeIds", assignment.getAssignmentId(), "assigneeType",
				assignmentType, "companyId", addTaskRequest.getCompanyId(),
				"deleted", false, "instanceId", instance.getId(), "processId",
				addTaskRequest.getProcessId(), "nodeId",
				addTaskRequest.getNodeId(), "name", addTaskRequest.getName(),
				"taskId", addTaskRequest.getTaskId());
		}

		if (addTaskRequest.isCompleted()) {
			CompleteTaskRequest.Builder completeTaskRequestBuilder =
				new CompleteTaskRequest.Builder();

			_taskWorkflowMetricsIndexer.completeTask(
				completeTaskRequestBuilder.companyId(
					addTaskRequest.getCompanyId()
				).completionDate(
					addTaskRequest.getCompletionDate()
				).completionUserId(
					addTaskRequest.getCompletionUserId()
				).duration(
					durationAvg
				).modifiedDate(
					addTaskRequest.getModifiedDate()
				).taskId(
					addTaskRequest.getTaskId()
				).userId(
					0
				).build());

			_assertCount(
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					addTaskRequest.getCompanyId()),
				"companyId", addTaskRequest.getCompanyId(), "completed", true,
				"completionUserId", addTaskRequest.getCompletionUserId(),
				"deleted", false, "duration", durationAvg, "instanceId",
				addTaskRequest.getInstanceId(), "processId",
				addTaskRequest.getProcessId(), "nodeId",
				addTaskRequest.getNodeId(), "name", addTaskRequest.getName(),
				"taskId", addTaskRequest.getTaskId());
		}

		return _toTask(addTaskRequest, durationAvg);
	}

	public Task addTask(
			Assignee assignee, long companyId, Instance instance, User user)
		throws Exception {

		List<Assignment> assignments = new ArrayList<>();

		for (long roleId : user.getRoleIds()) {
			assignments.add(
				new RoleAssignment(roleId, Collections.emptyList()));
		}

		return addTask(
			assignee, assignments, companyId, 0L, instance,
			RandomTestUtil.randomString(), RandomTestUtil.randomLong(),
			instance.getProcessId(), "1.0", RandomTestUtil.randomLong());
	}

	public Task addTask(long companyId, Instance instance, Task task, User user)
		throws Exception {

		List<Assignment> assignments = new ArrayList<>();
		String assigneeType = Role.class.getName();

		Assignee assignee = task.getAssignee();

		if ((assignee != null) && (assignee.getId() != null)) {
			assigneeType = User.class.getName();
			assignments.add(
				new UserAssignment(assignee.getId(), user.getFullName()));
		}
		else {
			for (Long roleId : user.getRoleIds()) {
				assignments.add(
					new RoleAssignment(roleId, Collections.emptyList()));
			}
		}

		AddTaskRequest.Builder addTaskRequestBuilder =
			new AddTaskRequest.Builder();

		_taskWorkflowMetricsIndexer.addTask(
			addTaskRequestBuilder.assetTitleMap(
				_createLocalizationMap(task.getAssetTitle())
			).assetTypeMap(
				_createLocalizationMap(task.getAssetType())
			).assignments(
				assignments
			).className(
				task.getClassName()
			).classPK(
				task.getClassPK()
			).companyId(
				companyId
			).createDate(
				task.getDateCreated()
			).instanceId(
				instance.getId()
			).modifiedDate(
				task.getDateModified()
			).name(
				task.getName()
			).nodeId(
				task.getNodeId()
			).processId(
				task.getProcessId()
			).processVersion(
				task.getProcessVersion()
			).taskId(
				task.getId()
			).userId(
				0
			).build());

		_assertCount(
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "instanceId",
			instance.getId(), "processId", task.getProcessId(), "nodeId",
			task.getNodeId(), "name", task.getName(), "taskId", task.getId());

		_assertCount(
			booleanQuery -> booleanQuery.addMustQueryClauses(
				_queries.nested(
					"tasks", _queries.term("tasks.taskId", task.getId()))),
			1, _instanceWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "instanceId",
			instance.getId(), "processId", task.getProcessId());

		if (!assignments.isEmpty()) {
			UpdateTaskRequest.Builder updateTaskRequestBuilder =
				new UpdateTaskRequest.Builder();

			_taskWorkflowMetricsIndexer.updateTask(
				updateTaskRequestBuilder.assetTitleMap(
					_createLocalizationMap(task.getAssetTitle())
				).assetTypeMap(
					_createLocalizationMap(task.getAssetType())
				).assignments(
					assignments
				).companyId(
					companyId
				).modifiedDate(
					new Date()
				).taskId(
					task.getId()
				).userId(
					0
				).build());

			Assignment assignment = assignments.get(0);

			_assertCount(
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
				"assigneeIds", assignment.getAssignmentId(), "assigneeType",
				assigneeType, "companyId", companyId, "deleted", false,
				"instanceId", instance.getId(), "processId",
				task.getProcessId(), "nodeId", task.getNodeId(), "name",
				task.getName(), "taskId", task.getId());
		}

		if (task.getCompleted()) {
			CompleteTaskRequest.Builder completeTaskRequestBuilder =
				new CompleteTaskRequest.Builder();

			_taskWorkflowMetricsIndexer.completeTask(
				completeTaskRequestBuilder.companyId(
					companyId
				).completionDate(
					task.getDateCompletion()
				).completionUserId(
					task.getCompletionUserId()
				).duration(
					task.getDuration()
				).modifiedDate(
					task.getDateModified()
				).taskId(
					task.getId()
				).userId(
					0
				).build());

			_assertCount(
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
				"companyId", companyId, "completed", true, "completionUserId",
				task.getCompletionUserId(), "deleted", false, "duration",
				task.getDuration(), "instanceId", instance.getId(), "processId",
				task.getProcessId(), "nodeId", task.getNodeId(), "name",
				task.getName(), "taskId", task.getId());
		}

		return task;
	}

	public void blockSLAInstanceResults(
			long companyId, long processId, long slaDefinitionId)
		throws Exception {

		Object indexer = _getIndexer(_CLASS_NAME_SLA_INSTANCE_RESULT_INDEXER);

		Class<?> indexerClass = indexer.getClass();

		Method method = null;

		while ((indexerClass != Object.class) && (method == null)) {
			try {
				method = ReflectionUtil.getDeclaredMethod(
					indexerClass, "blockDocuments", long.class, long.class,
					long.class);
			}
			catch (NoSuchMethodException noSuchMethodException) {
			}

			indexerClass = indexerClass.getSuperclass();
		}

		method.invoke(indexer, companyId, processId, slaDefinitionId);

		_assertCount(
			_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
				companyId),
			"blocked", true, "companyId", companyId, "deleted", false,
			"processId", processId, "slaDefinitionId", slaDefinitionId);
	}

	public void completeInstance(long companyId, Instance instance)
		throws Exception {

		_instanceWorkflowMetricsIndexer.completeInstance(
			companyId,
			Optional.ofNullable(
				instance.getDateCompletion()
			).orElseGet(
				Date::new
			),
			Optional.ofNullable(
				instance.getDuration()
			).orElse(
				1000L
			),
			instance.getId(),
			Optional.ofNullable(
				instance.getDateModified()
			).orElseGet(
				Date::new
			));

		_assertCount(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "completed", true, "deleted", false,
			"instanceId", instance.getId(), "processId",
			instance.getProcessId());
	}

	public void deleteInstance(long companyId, Instance instance)
		throws Exception {

		_instanceWorkflowMetricsIndexer.deleteInstance(
			companyId, instance.getId());

		_assertCount(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", true, "instanceId",
			instance.getId(), "processId", instance.getProcessId());
	}

	public void deleteNode(long companyId, Node node, long processId)
		throws Exception {

		DeleteNodeRequest.Builder builder = new DeleteNodeRequest.Builder();

		_nodeWorkflowMetricsIndexer.deleteNode(
			builder.companyId(
				companyId
			).nodeId(
				node.getId()
			).build());

		_assertCount(
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", true, "name", node.getName(),
			"processId", processId);
	}

	public void deleteProcess(long companyId, long processId) throws Exception {
		DeleteProcessRequest.Builder builder =
			new DeleteProcessRequest.Builder();

		_processWorkflowMetricsIndexer.deleteProcess(
			builder.companyId(
				companyId
			).processId(
				processId
			).build());

		_assertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", true, "processId", processId);
	}

	public void deleteProcess(long companyId, Process process)
		throws Exception {

		deleteProcess(companyId, process.getId());
	}

	public void deleteSLATaskResults(long companyId, long processId)
		throws Exception {

		_deleteDocuments(
			_slaTaskResultWorkflowMetricsIndexNameBuilder.getIndexName(
				companyId),
			"WorkflowMetricsSLATaskResultType", "companyId", companyId,
			"processId", processId);
	}

	public void deleteTasks(long companyId, long processId) throws Exception {
		_deleteDocuments(
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"WorkflowMetricsTaskType", "companyId", companyId, "processId",
			processId);
	}

	public Document[] getDocuments(long companyId) throws Exception {
		if (_searchEngineAdapter == null) {
			return new Document[0];
		}

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(companyId));

		BooleanQuery booleanQuery = _queries.booleanQuery();

		searchSearchRequest.setQuery(
			booleanQuery.addMustQueryClauses(
				_queries.term("companyId", companyId),
				_queries.term("deleted", Boolean.FALSE)));

		searchSearchRequest.setSize(10000);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		return Stream.of(
			searchSearchResponse.getSearchHits()
		).map(
			SearchHits::getSearchHits
		).flatMap(
			List::stream
		).map(
			SearchHit::getDocument
		).toArray(
			Document[]::new
		);
	}

	public void restoreProcess(Document document) throws Exception {
		AddProcessRequest.Builder builder = new AddProcessRequest.Builder();

		builder.active(
			document.getBoolean("active")
		).companyId(
			document.getLong("companyId")
		).createDate(
			_parseDate(document.getDate("createDate"))
		).description(
			document.getString("description")
		).modifiedDate(
			_parseDate(document.getDate("modifiedDate"))
		).name(
			document.getString("name")
		).processId(
			document.getLong("processId")
		).title(
			document.getString("title")
		).titleMap(
			_createLocalizationMap(document.getString("title"))
		);

		String version = StringBundler.concat(
			document.getString("version"), CharPool.PERIOD, 0);

		_processWorkflowMetricsIndexer.addProcess(
			builder.version(
				version
			).versions(
				new String[] {version}
			).build());

		_assertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				document.getLong("companyId")),
			"companyId", document.getLong("companyId"), "deleted", false,
			"processId", document.getLong("processId"));
	}

	public void updateProcess(long companyId, long processId, String version)
		throws Exception {

		UpdateProcessRequest.Builder builder =
			new UpdateProcessRequest.Builder();

		_processWorkflowMetricsIndexer.updateProcess(
			builder.active(
				null
			).companyId(
				companyId
			).description(
				null
			).modifiedDate(
				new Date()
			).processId(
				processId
			).title(
				null
			).titleMap(
				null
			).version(
				version
			).build());

		_assertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			"companyId", companyId, "deleted", false, "processId", processId,
			"version", version);
	}

	private void _assertCount(
			Consumer<BooleanQuery> booleanQueryConsumer, long expectedCount,
			String indexName, Object... parameters)
		throws Exception {

		if ((_searchEngineAdapter == null) || (parameters == null)) {
			return;
		}

		if ((parameters.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Parameters length is not an even number");
		}

		CountSearchRequest countSearchRequest = new CountSearchRequest();

		countSearchRequest.setIndexNames(indexName);

		BooleanQuery booleanQuery = _queries.booleanQuery();

		for (int i = 0; i < parameters.length; i = i + 2) {
			booleanQuery.addMustQueryClauses(
				_queries.term(
					String.valueOf(parameters[i]), parameters[i + 1]));
		}

		booleanQueryConsumer.accept(booleanQuery);

		countSearchRequest.setQuery(booleanQuery);

		CountSearchResponse countSearchResponse = _searchEngineAdapter.execute(
			countSearchRequest);

		Assert.assertEquals(expectedCount, countSearchResponse.getCount());
	}

	private void _assertCount(
			long expectedCount, String indexName, Object... parameters)
		throws Exception {

		_assertCount(
			booleanQuery -> {
			},
			expectedCount, indexName, parameters);
	}

	private void _assertCount(String indexName, Object... parameters)
		throws Exception {

		_assertCount(1, indexName, parameters);
	}

	private Map<Locale, String> _createLocalizationMap(String value) {
		Map<Locale, String> localizationMap = new HashMap<>();

		for (Locale availableLocale : LanguageUtil.getAvailableLocales()) {
			localizationMap.put(availableLocale, value);
		}

		return localizationMap;
	}

	private Document _creatWorkflowMetricsSLAInstanceResultDocument(
		long companyId, Instance instance, SLAResult slaResult) {

		DocumentBuilder documentBuilder = _documentBuilderFactory.builder();

		documentBuilder.setValue(
			"active", true
		).setValue(
			"blocked", false
		).setValue(
			"companyId", companyId
		).setValue(
			"deleted", false
		).setValue(
			"elapsedTime", slaResult.getOnTime() ? 1000 : -1000
		).setValue(
			"instanceCompleted", Objects.nonNull(instance.getDateCompletion())
		).setValue(
			"instanceId", instance.getId()
		).setValue(
			"modifiedDate", _getDateString(slaResult.getDateModified())
		).setValue(
			"onTime", slaResult.getOnTime()
		).setValue(
			"overdueDate", _getDateString(slaResult.getDateOverdue())
		).setValue(
			"processId", instance.getProcessId()
		).setValue(
			"remainingTime", slaResult.getRemainingTime()
		).setValue(
			"slaDefinitionId", slaResult.getId()
		);

		if (slaResult.getStatus() != null) {
			SLAResult.Status status = slaResult.getStatus();

			documentBuilder.setValue("status", status.getValue());
		}
		else {
			documentBuilder.setValue(
				"status", SLAResult.Status.RUNNING.getValue());
		}

		documentBuilder.setString(
			"uid",
			_digest(
				"WorkflowMetricsSLAInstanceResult", companyId, instance.getId(),
				instance.getProcessId(), slaResult.getId()));

		return documentBuilder.build();
	}

	private Document _creatWorkflowMetricsSLATaskResultDocument(
		long assigneeId, boolean breached, long companyId,
		boolean instanceCompleted, long instanceId, long nodeId, boolean onTime,
		long processId, long slaDefinitionId, String status, long taskId,
		String taskName) {

		DocumentBuilder documentBuilder = _documentBuilderFactory.builder();

		documentBuilder.setValue(
			"active", true
		).setValue(
			"assigneeIds", assigneeId
		).setValue(
			"assigneeType", User.class.getName()
		).setValue(
			"breached", breached
		).setValue(
			"companyId", companyId
		);

		if (Objects.equals(status, "COMPLETED")) {
			documentBuilder.setDate(
				"completionDate", _getDateString(new Date())
			).setValue(
				"completionUserId", assigneeId
			);
		}

		documentBuilder.setValue(
			"deleted", false
		).setValue(
			"elapsedTime", onTime ? 1000 : -1000
		).setValue(
			"instanceCompleted", instanceCompleted
		).setValue(
			"instanceId", instanceId
		).setValue(
			"nodeId", nodeId
		).setValue(
			"onTime", onTime
		).setValue(
			"processId", processId
		).setValue(
			"slaDefinitionId", slaDefinitionId
		).setValue(
			"status", status
		).setValue(
			"taskId", taskId
		).setValue(
			"taskName", taskName
		).setString(
			"uid",
			_digest(
				"WorkflowMetricsSLATaskResult", companyId, instanceId,
				processId, slaDefinitionId, taskId)
		);

		return documentBuilder.build();
	}

	private void _deleteDocuments(
			String indexName, String indexType, Object... parameters)
		throws Exception {

		if (_searchEngineAdapter == null) {
			return;
		}

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames(indexName);

		BooleanQuery booleanQuery = _queries.booleanQuery();

		for (int j = 0; j < parameters.length; j = j + 2) {
			booleanQuery.addMustQueryClauses(
				_queries.term(
					String.valueOf(parameters[j]), parameters[j + 1]));
		}

		searchSearchRequest.setQuery(booleanQuery);

		searchSearchRequest.setSize(10000);
		searchSearchRequest.setTypes(indexType);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		Stream.of(
			searchSearchResponse
		).map(
			SearchSearchResponse::getSearchHits
		).map(
			SearchHits::getSearchHits
		).flatMap(
			List::stream
		).map(
			SearchHit::getDocument
		).map(
			_documentBuilderFactory::builder
		).map(
			documentBuilder -> {
				documentBuilder.setValue("deleted", true);

				return documentBuilder.build();
			}
		).forEach(
			document -> {
				UpdateDocumentRequest updateDocumentRequest =
					new UpdateDocumentRequest(
						indexName, document.getString("uid"), document);

				updateDocumentRequest.setRefresh(true);
				updateDocumentRequest.setType(indexType);
				updateDocumentRequest.setUpsert(true);

				_searchEngineAdapter.execute(updateDocumentRequest);
			}
		);

		_assertCount(
			searchSearchResponse.getCount(), indexName,
			ArrayUtil.append(new Object[] {"deleted", true}, parameters));
	}

	private String _digest(String indexNamePrefix, Serializable... parts) {
		StringBundler sb = new StringBundler();

		for (Serializable part : parts) {
			sb.append(part);
		}

		return indexNamePrefix + DigestUtils.sha256Hex(sb.toString());
	}

	private String _getDateString(Date date) {
		try {
			return DateUtil.getDate(
				date, "yyyyMMddHHmmss", LocaleUtil.getDefault());
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}

			return null;
		}
	}

	private Object _getIndexer(String className) throws Exception {
		if (_indexers.containsKey(className)) {
			return _indexers.get(className);
		}

		Bundle bundle = FrameworkUtil.getBundle(
			WorkflowMetricsRESTTestHelper.class);

		BundleContext bundleContext = bundle.getBundleContext();

		int count = 0;

		ServiceReference<?> serviceReference = null;

		do {
			ServiceReference<?>[] serviceReferences =
				bundleContext.getServiceReferences(
					className, "(objectClass=" + className + ")");

			if (ArrayUtil.isEmpty(serviceReferences)) {
				count++;

				if (count >= 5) {
					throw new IllegalStateException(
						"Unable to get reference to " + className);
				}

				Thread.sleep(500);
			}

			serviceReference = serviceReferences[0];
		}
		while (serviceReference == null);

		Object indexer = bundleContext.getService(serviceReference);

		_indexers.put(className, indexer);

		return indexer;
	}

	private void _invokeAddDocument(Object indexer, Document document)
		throws Exception {

		_invokeMethod(indexer, "addDocument", document);
	}

	private void _invokeMethod(
			Object indexer, String methodName, Document document)
		throws Exception {

		Class<?> indexerClass = indexer.getClass();

		Method method = null;

		while ((indexerClass != Object.class) && (method == null)) {
			try {
				method = ReflectionUtil.getDeclaredMethod(
					indexerClass, methodName, Document.class);
			}
			catch (NoSuchMethodException noSuchMethodException) {
			}

			indexerClass = indexerClass.getSuperclass();
		}

		method.invoke(indexer, document);
	}

	private Date _parseDate(String dateString) {
		try {
			return DateUtil.parseDate(
				"yyyyMMddHHmmss", dateString, LocaleUtil.getDefault());
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}

			return new Date();
		}
	}

	private Task _toTask(AddTaskRequest addTaskRequest, Long duration) {
		Task task = new Task();

		task.setAssignee(
			new Assignee() {
				{
					id = -1L;
				}
			});

		List<Assignment> assignments = addTaskRequest.getAssignments();

		if (assignments.get(0) instanceof UserAssignment) {
			UserAssignment userAssignment = (UserAssignment)assignments.get(0);

			task.setAssignee(
				new Assignee() {
					{
						id = userAssignment.getAssignmentId();
						name = userAssignment.getName();
					}
				});
		}

		task.setClassName(addTaskRequest.getClassName());
		task.setClassPK(addTaskRequest.getInstanceId());
		task.setCompleted(addTaskRequest.isCompleted());
		task.setDateCompletion(addTaskRequest.getCompletionDate());
		task.setCompletionUserId(addTaskRequest.getCompletionUserId());
		task.setDateCreated(addTaskRequest.getCreateDate());
		task.setDateModified(addTaskRequest.getModifiedDate());
		task.setDuration(duration);
		task.setId(addTaskRequest.getTaskId());
		task.setInstanceId(addTaskRequest.getInstanceId());
		task.setName(addTaskRequest.getName());
		task.setNodeId(addTaskRequest.getNodeId());
		task.setProcessId(addTaskRequest.getProcessId());
		task.setProcessVersion(addTaskRequest.getProcessVersion());

		return task;
	}

	private void _updateInstance(
			long companyId, Instance instance, SLAResult... slaResults)
		throws Exception {

		DocumentBuilder documentBuilder = _documentBuilderFactory.builder();

		Document document = documentBuilder.setValue(
			"slaResults",
			Stream.of(
				slaResults
			).map(
				slaResult -> HashMapBuilder.put(
					"onTime", String.valueOf(slaResult.getOnTime())
				).put(
					"overdueDate", _getDateString(slaResult.getDateOverdue())
				).put(
					"remainingTime",
					String.valueOf(slaResult.getRemainingTime())
				).put(
					"slaDefinitionId", String.valueOf(slaResult.getId())
				).put(
					"status", slaResult.getStatusAsString()
				).build()
			).toArray()
		).setString(
			"uid",
			_digest("WorkflowMetricsInstance", companyId, instance.getId())
		).build();

		UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(companyId),
			document.getString("uid"), document);

		updateDocumentRequest.setRefresh(true);

		_searchEngineAdapter.execute(updateDocumentRequest);

		for (SLAResult slaResult : slaResults) {
			_assertCount(
				booleanQuery -> booleanQuery.addMustQueryClauses(
					_queries.nested(
						"slaResults",
						_queries.term(
							"slaResults.overdueDate",
							_getDateString(slaResult.getDateOverdue())))),
				1,
				_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
					companyId),
				"companyId", companyId, "deleted", false, "instanceId",
				instance.getId(), "processId", instance.getProcessId());
		}
	}

	private static final String _CLASS_NAME_SLA_INSTANCE_RESULT_INDEXER =
		"com.liferay.portal.workflow.metrics.internal.search.index." +
			"SLAInstanceResultWorkflowMetricsIndexer";

	private static final String _CLASS_NAME_SLA_TASK_RESULT_INDEXER =
		"com.liferay.portal.workflow.metrics.internal.search.index." +
			"SLATaskResultWorkflowMetricsIndexer";

	private static final Log _log = LogFactoryUtil.getLog(
		WorkflowMetricsRESTTestHelper.class);

	@Reference
	private DocumentBuilderFactory _documentBuilderFactory;

	private final Map<String, Object> _indexers = new HashMap<>();

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=instance)")
	private WorkflowMetricsIndexNameBuilder
		_instanceWorkflowMetricsIndexNameBuilder;

	@Reference
	private NodeWorkflowMetricsIndexer _nodeWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=node)")
	private WorkflowMetricsIndexNameBuilder
		_nodeWorkflowMetricsIndexNameBuilder;

	@Reference
	private ProcessWorkflowMetricsIndexer _processWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=process)")
	private WorkflowMetricsIndexNameBuilder
		_processWorkflowMetricsIndexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(search.engine.impl=Elasticsearch)"
	)
	private volatile SearchEngineAdapter _searchEngineAdapter;

	@Reference(
		target = "(workflow.metrics.index.entity.name=sla-instance-result)"
	)
	private WorkflowMetricsIndexNameBuilder
		_slaInstanceResultWorkflowMetricsIndexNameBuilder;

	@Reference(target = "(workflow.metrics.index.entity.name=sla-task-result)")
	private WorkflowMetricsIndexNameBuilder
		_slaTaskResultWorkflowMetricsIndexNameBuilder;

	@Reference
	private TaskWorkflowMetricsIndexer _taskWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=task)")
	private WorkflowMetricsIndexNameBuilder
		_taskWorkflowMetricsIndexNameBuilder;

	@Reference
	private UserLocalService _userLocalService;

}