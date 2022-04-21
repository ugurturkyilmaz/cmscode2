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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoTaskAssignmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentLocalService
 * @generated
 */
public class KaleoTaskAssignmentLocalServiceWrapper
	implements KaleoTaskAssignmentLocalService,
			   ServiceWrapper<KaleoTaskAssignmentLocalService> {

	public KaleoTaskAssignmentLocalServiceWrapper() {
		this(null);
	}

	public KaleoTaskAssignmentLocalServiceWrapper(
		KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService) {

		_kaleoTaskAssignmentLocalService = kaleoTaskAssignmentLocalService;
	}

	/**
	 * Adds the kaleo task assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was added
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
		addKaleoTaskAssignment(
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
				kaleoTaskAssignment) {

		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
			kaleoTaskAssignment);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
			addKaleoTaskAssignment(
				String kaleoClassName, long kaleoClassPK,
				long kaleoDefinitionId, long kaleoDefinitionVersionId,
				com.liferay.portal.workflow.kaleo.definition.Assignment
					assignment,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
			kaleoClassName, kaleoClassPK, kaleoDefinitionId,
			kaleoDefinitionVersionId, assignment, serviceContext);
	}

	/**
	 * Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	 *
	 * @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	 * @return the new kaleo task assignment
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
		createKaleoTaskAssignment(long kaleoTaskAssignmentId) {

		return _kaleoTaskAssignmentLocalService.createKaleoTaskAssignment(
			kaleoTaskAssignmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteCompanyKaleoTaskAssignments(long companyId) {
		_kaleoTaskAssignmentLocalService.deleteCompanyKaleoTaskAssignments(
			companyId);
	}

	@Override
	public void deleteKaleoDefinitionVersionKaleoTaskAssignments(
		long kaleoDefinitionVersionId) {

		_kaleoTaskAssignmentLocalService.
			deleteKaleoDefinitionVersionKaleoTaskAssignments(
				kaleoDefinitionVersionId);
	}

	/**
	 * Deletes the kaleo task assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
		deleteKaleoTaskAssignment(
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
				kaleoTaskAssignment) {

		return _kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(
			kaleoTaskAssignment);
	}

	/**
	 * Deletes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws PortalException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
			deleteKaleoTaskAssignment(long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(
			kaleoTaskAssignmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kaleoTaskAssignmentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kaleoTaskAssignmentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoTaskAssignmentLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _kaleoTaskAssignmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _kaleoTaskAssignmentLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _kaleoTaskAssignmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _kaleoTaskAssignmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
		fetchKaleoTaskAssignment(long kaleoTaskAssignmentId) {

		return _kaleoTaskAssignmentLocalService.fetchKaleoTaskAssignment(
			kaleoTaskAssignmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _kaleoTaskAssignmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _kaleoTaskAssignmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kaleo task assignment with the primary key.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws PortalException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
			getKaleoTaskAssignment(long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignment(
			kaleoTaskAssignmentId);
	}

	/**
	 * Returns a range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of kaleo task assignments
	 */
	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>
			getKaleoTaskAssignments(int start, int end) {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>
			getKaleoTaskAssignments(long kaleoTaskId) {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(
			kaleoTaskId);
	}

	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>
			getKaleoTaskAssignments(
				long kaleoTaskId, String assigneeClassName) {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(
			kaleoTaskId, assigneeClassName);
	}

	@Override
	public java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>
			getKaleoTaskAssignments(String kaleoClassName, long kaleoClassPK) {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(
			kaleoClassName, kaleoClassPK);
	}

	/**
	 * Returns the number of kaleo task assignments.
	 *
	 * @return the number of kaleo task assignments
	 */
	@Override
	public int getKaleoTaskAssignmentsCount() {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount();
	}

	@Override
	public int getKaleoTaskAssignmentsCount(long kaleoTaskId) {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(
			kaleoTaskId);
	}

	@Override
	public int getKaleoTaskAssignmentsCount(
		long kaleoTaskId, String assigneeClassName) {

		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(
			kaleoTaskId, assigneeClassName);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kaleoTaskAssignmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kaleoTaskAssignmentLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the kaleo task assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was updated
	 */
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
		updateKaleoTaskAssignment(
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment
				kaleoTaskAssignment) {

		return _kaleoTaskAssignmentLocalService.updateKaleoTaskAssignment(
			kaleoTaskAssignment);
	}

	@Override
	public KaleoTaskAssignmentLocalService getWrappedService() {
		return _kaleoTaskAssignmentLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService) {

		_kaleoTaskAssignmentLocalService = kaleoTaskAssignmentLocalService;
	}

	private KaleoTaskAssignmentLocalService _kaleoTaskAssignmentLocalService;

}