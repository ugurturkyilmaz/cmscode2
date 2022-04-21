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

package com.liferay.portal.workflow.metrics.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WorkflowMetricsSLADefinition. This utility wraps
 * <code>com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowMetricsSLADefinitionLocalService
 * @generated
 */
public class WorkflowMetricsSLADefinitionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WorkflowMetricsSLADefinition addWorkflowMetricsSLADefinition(
			String calendarKey, String description, long duration, String name,
			String[] pauseNodeKeys, long processId, String[] startNodeKeys,
			String[] stopNodeKeys,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWorkflowMetricsSLADefinition(
			calendarKey, description, duration, name, pauseNodeKeys, processId,
			startNodeKeys, stopNodeKeys, serviceContext);
	}

	/**
	 * Adds the workflow metrics sla definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was added
	 */
	public static WorkflowMetricsSLADefinition addWorkflowMetricsSLADefinition(
		WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		return getService().addWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinition);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new workflow metrics sla definition with the primary key. Does not add the workflow metrics sla definition to the database.
	 *
	 * @param workflowMetricsSLADefinitionId the primary key for the new workflow metrics sla definition
	 * @return the new workflow metrics sla definition
	 */
	public static WorkflowMetricsSLADefinition
		createWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId) {

		return getService().createWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId);
	}

	public static void deactivateWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().deactivateWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the workflow metrics sla definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionId the primary key of the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was removed
	 * @throws PortalException if a workflow metrics sla definition with the primary key could not be found
	 */
	public static WorkflowMetricsSLADefinition
			deleteWorkflowMetricsSLADefinition(
				long workflowMetricsSLADefinitionId)
		throws PortalException {

		return getService().deleteWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId);
	}

	/**
	 * Deletes the workflow metrics sla definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was removed
	 */
	public static WorkflowMetricsSLADefinition
		deleteWorkflowMetricsSLADefinition(
			WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		return getService().deleteWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinition);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static WorkflowMetricsSLADefinition
		fetchWorkflowMetricsSLADefinition(long workflowMetricsSLADefinitionId) {

		return getService().fetchWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId);
	}

	/**
	 * Returns the workflow metrics sla definition matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition, or <code>null</code> if a matching workflow metrics sla definition could not be found
	 */
	public static WorkflowMetricsSLADefinition
		fetchWorkflowMetricsSLADefinitionByUuidAndGroupId(
			String uuid, long groupId) {

		return getService().fetchWorkflowMetricsSLADefinitionByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the workflow metrics sla definition with the primary key.
	 *
	 * @param workflowMetricsSLADefinitionId the primary key of the workflow metrics sla definition
	 * @return the workflow metrics sla definition
	 * @throws PortalException if a workflow metrics sla definition with the primary key could not be found
	 */
	public static WorkflowMetricsSLADefinition getWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId)
		throws PortalException {

		return getService().getWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId);
	}

	public static WorkflowMetricsSLADefinition getWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId, boolean active)
		throws PortalException {

		return getService().getWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId, active);
	}

	/**
	 * Returns the workflow metrics sla definition matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition
	 * @throws PortalException if a matching workflow metrics sla definition could not be found
	 */
	public static WorkflowMetricsSLADefinition
			getWorkflowMetricsSLADefinitionByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return getService().getWorkflowMetricsSLADefinitionByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the workflow metrics sla definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow metrics sla definitions
	 * @param end the upper bound of the range of workflow metrics sla definitions (not inclusive)
	 * @return the range of workflow metrics sla definitions
	 */
	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitions(int start, int end) {

		return getService().getWorkflowMetricsSLADefinitions(start, end);
	}

	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitions(
			long companyId, boolean active, long processId, int status,
			int start, int end,
			OrderByComparator<WorkflowMetricsSLADefinition> orderByComparator) {

		return getService().getWorkflowMetricsSLADefinitions(
			companyId, active, processId, status, start, end,
			orderByComparator);
	}

	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitions(
			long companyId, boolean active, long processId,
			String processVersion, int status) {

		return getService().getWorkflowMetricsSLADefinitions(
			companyId, active, processId, processVersion, status);
	}

	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitions(long companyId, int status) {

		return getService().getWorkflowMetricsSLADefinitions(companyId, status);
	}

	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitions(
			long companyId, String name, long processId) {

		return getService().getWorkflowMetricsSLADefinitions(
			companyId, name, processId);
	}

	/**
	 * Returns all the workflow metrics sla definitions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definitions
	 * @param companyId the primary key of the company
	 * @return the matching workflow metrics sla definitions, or an empty list if no matches were found
	 */
	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of workflow metrics sla definitions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definitions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of workflow metrics sla definitions
	 * @param end the upper bound of the range of workflow metrics sla definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching workflow metrics sla definitions, or an empty list if no matches were found
	 */
	public static List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<WorkflowMetricsSLADefinition> orderByComparator) {

		return getService().getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of workflow metrics sla definitions.
	 *
	 * @return the number of workflow metrics sla definitions
	 */
	public static int getWorkflowMetricsSLADefinitionsCount() {
		return getService().getWorkflowMetricsSLADefinitionsCount();
	}

	public static int getWorkflowMetricsSLADefinitionsCount(
		long companyId, boolean active, long processId) {

		return getService().getWorkflowMetricsSLADefinitionsCount(
			companyId, active, processId);
	}

	public static int getWorkflowMetricsSLADefinitionsCount(
		long companyId, boolean active, long processId, int status) {

		return getService().getWorkflowMetricsSLADefinitionsCount(
			companyId, active, processId, status);
	}

	public static WorkflowMetricsSLADefinition
			updateWorkflowMetricsSLADefinition(
				long workflowMetricsSLADefinitionId, String calendarKey,
				String description, long duration, String name,
				String[] pauseNodeKeys, String[] startNodeKeys,
				String[] stopNodeKeys, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinitionId, calendarKey, description, duration,
			name, pauseNodeKeys, startNodeKeys, stopNodeKeys, status,
			serviceContext);
	}

	/**
	 * Updates the workflow metrics sla definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was updated
	 */
	public static WorkflowMetricsSLADefinition
		updateWorkflowMetricsSLADefinition(
			WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		return getService().updateWorkflowMetricsSLADefinition(
			workflowMetricsSLADefinition);
	}

	public static WorkflowMetricsSLADefinitionLocalService getService() {
		return _service;
	}

	private static volatile WorkflowMetricsSLADefinitionLocalService _service;

}