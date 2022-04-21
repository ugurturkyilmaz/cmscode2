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

package com.liferay.portal.security.wedeploy.auth.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WeDeployAuthApp. This utility wraps
 * <code>com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthAppLocalService
 * @generated
 */
public class WeDeployAuthAppLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WeDeployAuthApp addWeDeployAuthApp(
			long userId, String name, String redirectURI,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWeDeployAuthApp(
			userId, name, redirectURI, serviceContext);
	}

	/**
	 * Adds the we deploy auth app to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was added
	 */
	public static WeDeployAuthApp addWeDeployAuthApp(
		WeDeployAuthApp weDeployAuthApp) {

		return getService().addWeDeployAuthApp(weDeployAuthApp);
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
	 * Creates a new we deploy auth app with the primary key. Does not add the we deploy auth app to the database.
	 *
	 * @param weDeployAuthAppId the primary key for the new we deploy auth app
	 * @return the new we deploy auth app
	 */
	public static WeDeployAuthApp createWeDeployAuthApp(
		long weDeployAuthAppId) {

		return getService().createWeDeployAuthApp(weDeployAuthAppId);
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
	 * Deletes the we deploy auth app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthAppId the primary key of the we deploy auth app
	 * @return the we deploy auth app that was removed
	 * @throws PortalException if a we deploy auth app with the primary key could not be found
	 */
	public static WeDeployAuthApp deleteWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		return getService().deleteWeDeployAuthApp(weDeployAuthAppId);
	}

	/**
	 * Deletes the we deploy auth app from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was removed
	 */
	public static WeDeployAuthApp deleteWeDeployAuthApp(
		WeDeployAuthApp weDeployAuthApp) {

		return getService().deleteWeDeployAuthApp(weDeployAuthApp);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
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

	public static WeDeployAuthApp fetchWeDeployAuthApp(long weDeployAuthAppId) {
		return getService().fetchWeDeployAuthApp(weDeployAuthAppId);
	}

	public static WeDeployAuthApp fetchWeDeployAuthApp(
		String redirectURI, String clientId) {

		return getService().fetchWeDeployAuthApp(redirectURI, clientId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the we deploy auth app with the primary key.
	 *
	 * @param weDeployAuthAppId the primary key of the we deploy auth app
	 * @return the we deploy auth app
	 * @throws PortalException if a we deploy auth app with the primary key could not be found
	 */
	public static WeDeployAuthApp getWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		return getService().getWeDeployAuthApp(weDeployAuthAppId);
	}

	/**
	 * Returns a range of all the we deploy auth apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of we deploy auth apps
	 * @param end the upper bound of the range of we deploy auth apps (not inclusive)
	 * @return the range of we deploy auth apps
	 */
	public static List<WeDeployAuthApp> getWeDeployAuthApps(
		int start, int end) {

		return getService().getWeDeployAuthApps(start, end);
	}

	/**
	 * Returns the number of we deploy auth apps.
	 *
	 * @return the number of we deploy auth apps
	 */
	public static int getWeDeployAuthAppsCount() {
		return getService().getWeDeployAuthAppsCount();
	}

	/**
	 * Updates the we deploy auth app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was updated
	 */
	public static WeDeployAuthApp updateWeDeployAuthApp(
		WeDeployAuthApp weDeployAuthApp) {

		return getService().updateWeDeployAuthApp(weDeployAuthApp);
	}

	public static WeDeployAuthAppLocalService getService() {
		return _service;
	}

	private static volatile WeDeployAuthAppLocalService _service;

}