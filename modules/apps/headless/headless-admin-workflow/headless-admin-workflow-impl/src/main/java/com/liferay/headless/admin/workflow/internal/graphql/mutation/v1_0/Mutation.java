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

package com.liferay.headless.admin.workflow.internal.graphql.mutation.v1_0;

import com.liferay.headless.admin.workflow.dto.v1_0.ChangeTransition;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowDefinition;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowInstance;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowInstanceSubmit;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTask;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskAssignToMe;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskAssignToRole;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskAssignToUser;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskAssignableUsers;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskIds;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskTransitions;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTasksBulkSelection;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowDefinitionResource;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowInstanceResource;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowTaskAssignableUsersResource;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowTaskResource;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowTaskTransitionsResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setWorkflowDefinitionResourceComponentServiceObjects(
		ComponentServiceObjects<WorkflowDefinitionResource>
			workflowDefinitionResourceComponentServiceObjects) {

		_workflowDefinitionResourceComponentServiceObjects =
			workflowDefinitionResourceComponentServiceObjects;
	}

	public static void setWorkflowInstanceResourceComponentServiceObjects(
		ComponentServiceObjects<WorkflowInstanceResource>
			workflowInstanceResourceComponentServiceObjects) {

		_workflowInstanceResourceComponentServiceObjects =
			workflowInstanceResourceComponentServiceObjects;
	}

	public static void setWorkflowTaskResourceComponentServiceObjects(
		ComponentServiceObjects<WorkflowTaskResource>
			workflowTaskResourceComponentServiceObjects) {

		_workflowTaskResourceComponentServiceObjects =
			workflowTaskResourceComponentServiceObjects;
	}

	public static void
		setWorkflowTaskAssignableUsersResourceComponentServiceObjects(
			ComponentServiceObjects<WorkflowTaskAssignableUsersResource>
				workflowTaskAssignableUsersResourceComponentServiceObjects) {

		_workflowTaskAssignableUsersResourceComponentServiceObjects =
			workflowTaskAssignableUsersResourceComponentServiceObjects;
	}

	public static void
		setWorkflowTaskTransitionsResourceComponentServiceObjects(
			ComponentServiceObjects<WorkflowTaskTransitionsResource>
				workflowTaskTransitionsResourceComponentServiceObjects) {

		_workflowTaskTransitionsResourceComponentServiceObjects =
			workflowTaskTransitionsResourceComponentServiceObjects;
	}

	@GraphQLField
	public WorkflowDefinition createWorkflowDefinitionDeploy(
			@GraphQLName("workflowDefinition") WorkflowDefinition
				workflowDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowDefinitionResource ->
				workflowDefinitionResource.postWorkflowDefinitionDeploy(
					workflowDefinition));
	}

	@GraphQLField
	public WorkflowDefinition createWorkflowDefinitionSave(
			@GraphQLName("workflowDefinition") WorkflowDefinition
				workflowDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowDefinitionResource ->
				workflowDefinitionResource.postWorkflowDefinitionSave(
					workflowDefinition));
	}

	@GraphQLField
	public boolean deleteWorkflowDefinitionUndeploy(
			@GraphQLName("name") String name,
			@GraphQLName("version") String version)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_workflowDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowDefinitionResource ->
				workflowDefinitionResource.deleteWorkflowDefinitionUndeploy(
					name, version));

		return true;
	}

	@GraphQLField
	public WorkflowDefinition createWorkflowDefinitionUpdateActive(
			@GraphQLName("active") Boolean active,
			@GraphQLName("name") String name,
			@GraphQLName("version") String version)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowDefinitionResource ->
				workflowDefinitionResource.postWorkflowDefinitionUpdateActive(
					active, name, version));
	}

	@GraphQLField
	public WorkflowInstance createWorkflowInstanceSubmit(
			@GraphQLName("workflowInstanceSubmit") WorkflowInstanceSubmit
				workflowInstanceSubmit)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowInstanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowInstanceResource ->
				workflowInstanceResource.postWorkflowInstanceSubmit(
					workflowInstanceSubmit));
	}

	@GraphQLField
	public boolean deleteWorkflowInstance(
			@GraphQLName("workflowInstanceId") Long workflowInstanceId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_workflowInstanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowInstanceResource ->
				workflowInstanceResource.deleteWorkflowInstance(
					workflowInstanceId));

		return true;
	}

	@GraphQLField
	public WorkflowInstance createWorkflowInstanceChangeTransition(
			@GraphQLName("workflowInstanceId") Long workflowInstanceId,
			@GraphQLName("changeTransition") ChangeTransition changeTransition)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowInstanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowInstanceResource ->
				workflowInstanceResource.postWorkflowInstanceChangeTransition(
					workflowInstanceId, changeTransition));
	}

	@GraphQLField
	public java.util.Collection<WorkflowTask> createWorkflowTasksPage(
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString,
			@GraphQLName("workflowTasksBulkSelection")
				WorkflowTasksBulkSelection workflowTasksBulkSelection)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource -> {
				Page paginationPage =
					workflowTaskResource.postWorkflowTasksPage(
						Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							workflowTaskResource, sortsString),
						workflowTasksBulkSelection);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	public boolean patchWorkflowTaskAssignToUser(
			@GraphQLName("workflowTaskAssignToUsers") WorkflowTaskAssignToUser[]
				workflowTaskAssignToUsers)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.patchWorkflowTaskAssignToUser(
					workflowTaskAssignToUsers));

		return true;
	}

	@GraphQLField
	public boolean patchWorkflowTaskChangeTransition(
			@GraphQLName("changeTransitions") ChangeTransition[]
				changeTransitions)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.patchWorkflowTaskChangeTransition(
					changeTransitions));

		return true;
	}

	@GraphQLField
	public boolean patchWorkflowTaskUpdateDueDate(
			@GraphQLName("workflowTaskAssignToMes") WorkflowTaskAssignToMe[]
				workflowTaskAssignToMes)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.patchWorkflowTaskUpdateDueDate(
					workflowTaskAssignToMes));

		return true;
	}

	@GraphQLField
	public WorkflowTask createWorkflowTaskAssignToMe(
			@GraphQLName("workflowTaskId") Long workflowTaskId,
			@GraphQLName("workflowTaskAssignToMe") WorkflowTaskAssignToMe
				workflowTaskAssignToMe)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.postWorkflowTaskAssignToMe(
					workflowTaskId, workflowTaskAssignToMe));
	}

	@GraphQLField
	public WorkflowTask createWorkflowTaskAssignToRole(
			@GraphQLName("workflowTaskId") Long workflowTaskId,
			@GraphQLName("workflowTaskAssignToRole") WorkflowTaskAssignToRole
				workflowTaskAssignToRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.postWorkflowTaskAssignToRole(
					workflowTaskId, workflowTaskAssignToRole));
	}

	@GraphQLField
	public WorkflowTask createWorkflowTaskAssignToUser(
			@GraphQLName("workflowTaskId") Long workflowTaskId,
			@GraphQLName("workflowTaskAssignToUser") WorkflowTaskAssignToUser
				workflowTaskAssignToUser)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.postWorkflowTaskAssignToUser(
					workflowTaskId, workflowTaskAssignToUser));
	}

	@GraphQLField
	public WorkflowTask createWorkflowTaskChangeTransition(
			@GraphQLName("workflowTaskId") Long workflowTaskId,
			@GraphQLName("changeTransition") ChangeTransition changeTransition)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.postWorkflowTaskChangeTransition(
					workflowTaskId, changeTransition));
	}

	@GraphQLField
	public WorkflowTask createWorkflowTaskUpdateDueDate(
			@GraphQLName("workflowTaskId") Long workflowTaskId,
			@GraphQLName("workflowTaskAssignToMe") WorkflowTaskAssignToMe
				workflowTaskAssignToMe)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskResource ->
				workflowTaskResource.postWorkflowTaskUpdateDueDate(
					workflowTaskId, workflowTaskAssignToMe));
	}

	@GraphQLField
	public WorkflowTaskAssignableUsers createWorkflowTaskAssignableUser(
			@GraphQLName("workflowTaskIds") WorkflowTaskIds workflowTaskIds)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskAssignableUsersResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskAssignableUsersResource ->
				workflowTaskAssignableUsersResource.
					postWorkflowTaskAssignableUser(workflowTaskIds));
	}

	@GraphQLField
	public WorkflowTaskTransitions createWorkflowTaskTransition(
			@GraphQLName("workflowTaskIds") WorkflowTaskIds workflowTaskIds)
		throws Exception {

		return _applyComponentServiceObjects(
			_workflowTaskTransitionsResourceComponentServiceObjects,
			this::_populateResourceContext,
			workflowTaskTransitionsResource ->
				workflowTaskTransitionsResource.postWorkflowTaskTransition(
					workflowTaskIds));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			WorkflowDefinitionResource workflowDefinitionResource)
		throws Exception {

		workflowDefinitionResource.setContextAcceptLanguage(_acceptLanguage);
		workflowDefinitionResource.setContextCompany(_company);
		workflowDefinitionResource.setContextHttpServletRequest(
			_httpServletRequest);
		workflowDefinitionResource.setContextHttpServletResponse(
			_httpServletResponse);
		workflowDefinitionResource.setContextUriInfo(_uriInfo);
		workflowDefinitionResource.setContextUser(_user);
		workflowDefinitionResource.setGroupLocalService(_groupLocalService);
		workflowDefinitionResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WorkflowInstanceResource workflowInstanceResource)
		throws Exception {

		workflowInstanceResource.setContextAcceptLanguage(_acceptLanguage);
		workflowInstanceResource.setContextCompany(_company);
		workflowInstanceResource.setContextHttpServletRequest(
			_httpServletRequest);
		workflowInstanceResource.setContextHttpServletResponse(
			_httpServletResponse);
		workflowInstanceResource.setContextUriInfo(_uriInfo);
		workflowInstanceResource.setContextUser(_user);
		workflowInstanceResource.setGroupLocalService(_groupLocalService);
		workflowInstanceResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WorkflowTaskResource workflowTaskResource)
		throws Exception {

		workflowTaskResource.setContextAcceptLanguage(_acceptLanguage);
		workflowTaskResource.setContextCompany(_company);
		workflowTaskResource.setContextHttpServletRequest(_httpServletRequest);
		workflowTaskResource.setContextHttpServletResponse(
			_httpServletResponse);
		workflowTaskResource.setContextUriInfo(_uriInfo);
		workflowTaskResource.setContextUser(_user);
		workflowTaskResource.setGroupLocalService(_groupLocalService);
		workflowTaskResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WorkflowTaskAssignableUsersResource
				workflowTaskAssignableUsersResource)
		throws Exception {

		workflowTaskAssignableUsersResource.setContextAcceptLanguage(
			_acceptLanguage);
		workflowTaskAssignableUsersResource.setContextCompany(_company);
		workflowTaskAssignableUsersResource.setContextHttpServletRequest(
			_httpServletRequest);
		workflowTaskAssignableUsersResource.setContextHttpServletResponse(
			_httpServletResponse);
		workflowTaskAssignableUsersResource.setContextUriInfo(_uriInfo);
		workflowTaskAssignableUsersResource.setContextUser(_user);
		workflowTaskAssignableUsersResource.setGroupLocalService(
			_groupLocalService);
		workflowTaskAssignableUsersResource.setRoleLocalService(
			_roleLocalService);
	}

	private void _populateResourceContext(
			WorkflowTaskTransitionsResource workflowTaskTransitionsResource)
		throws Exception {

		workflowTaskTransitionsResource.setContextAcceptLanguage(
			_acceptLanguage);
		workflowTaskTransitionsResource.setContextCompany(_company);
		workflowTaskTransitionsResource.setContextHttpServletRequest(
			_httpServletRequest);
		workflowTaskTransitionsResource.setContextHttpServletResponse(
			_httpServletResponse);
		workflowTaskTransitionsResource.setContextUriInfo(_uriInfo);
		workflowTaskTransitionsResource.setContextUser(_user);
		workflowTaskTransitionsResource.setGroupLocalService(
			_groupLocalService);
		workflowTaskTransitionsResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<WorkflowDefinitionResource>
		_workflowDefinitionResourceComponentServiceObjects;
	private static ComponentServiceObjects<WorkflowInstanceResource>
		_workflowInstanceResourceComponentServiceObjects;
	private static ComponentServiceObjects<WorkflowTaskResource>
		_workflowTaskResourceComponentServiceObjects;
	private static ComponentServiceObjects<WorkflowTaskAssignableUsersResource>
		_workflowTaskAssignableUsersResourceComponentServiceObjects;
	private static ComponentServiceObjects<WorkflowTaskTransitionsResource>
		_workflowTaskTransitionsResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}