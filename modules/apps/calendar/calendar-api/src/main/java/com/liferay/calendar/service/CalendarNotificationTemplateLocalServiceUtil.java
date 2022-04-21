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

package com.liferay.calendar.service;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CalendarNotificationTemplate. This utility wraps
 * <code>com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateLocalService
 * @generated
 */
public class CalendarNotificationTemplateLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the calendar notification template to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was added
	 */
	public static CalendarNotificationTemplate addCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		return getService().addCalendarNotificationTemplate(
			calendarNotificationTemplate);
	}

	public static CalendarNotificationTemplate addCalendarNotificationTemplate(
			long userId, long calendarId,
			com.liferay.calendar.notification.NotificationType notificationType,
			String notificationTypeSettings,
			com.liferay.calendar.notification.NotificationTemplateType
				notificationTemplateType,
			String subject, String body,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCalendarNotificationTemplate(
			userId, calendarId, notificationType, notificationTypeSettings,
			notificationTemplateType, subject, body, serviceContext);
	}

	/**
	 * Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	 *
	 * @param calendarNotificationTemplateId the primary key for the new calendar notification template
	 * @return the new calendar notification template
	 */
	public static CalendarNotificationTemplate
		createCalendarNotificationTemplate(
			long calendarNotificationTemplateId) {

		return getService().createCalendarNotificationTemplate(
			calendarNotificationTemplateId);
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
	 * Deletes the calendar notification template from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was removed
	 */
	public static CalendarNotificationTemplate
		deleteCalendarNotificationTemplate(
			CalendarNotificationTemplate calendarNotificationTemplate) {

		return getService().deleteCalendarNotificationTemplate(
			calendarNotificationTemplate);
	}

	/**
	 * Deletes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws PortalException if a calendar notification template with the primary key could not be found
	 */
	public static CalendarNotificationTemplate
			deleteCalendarNotificationTemplate(
				long calendarNotificationTemplateId)
		throws PortalException {

		return getService().deleteCalendarNotificationTemplate(
			calendarNotificationTemplateId);
	}

	public static void deleteCalendarNotificationTemplates(long calendarId) {
		getService().deleteCalendarNotificationTemplates(calendarId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
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

	public static CalendarNotificationTemplate
		fetchCalendarNotificationTemplate(long calendarNotificationTemplateId) {

		return getService().fetchCalendarNotificationTemplate(
			calendarNotificationTemplateId);
	}

	public static CalendarNotificationTemplate
		fetchCalendarNotificationTemplate(
			long calendarId,
			com.liferay.calendar.notification.NotificationType notificationType,
			com.liferay.calendar.notification.NotificationTemplateType
				notificationTemplateType) {

		return getService().fetchCalendarNotificationTemplate(
			calendarId, notificationType, notificationTemplateType);
	}

	/**
	 * Returns the calendar notification template matching the UUID and group.
	 *
	 * @param uuid the calendar notification template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	public static CalendarNotificationTemplate
		fetchCalendarNotificationTemplateByUuidAndGroupId(
			String uuid, long groupId) {

		return getService().fetchCalendarNotificationTemplateByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the calendar notification template with the primary key.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws PortalException if a calendar notification template with the primary key could not be found
	 */
	public static CalendarNotificationTemplate getCalendarNotificationTemplate(
			long calendarNotificationTemplateId)
		throws PortalException {

		return getService().getCalendarNotificationTemplate(
			calendarNotificationTemplateId);
	}

	/**
	 * Returns the calendar notification template matching the UUID and group.
	 *
	 * @param uuid the calendar notification template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar notification template
	 * @throws PortalException if a matching calendar notification template could not be found
	 */
	public static CalendarNotificationTemplate
			getCalendarNotificationTemplateByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return getService().getCalendarNotificationTemplateByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the calendar notification templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @return the range of calendar notification templates
	 */
	public static List<CalendarNotificationTemplate>
		getCalendarNotificationTemplates(int start, int end) {

		return getService().getCalendarNotificationTemplates(start, end);
	}

	/**
	 * Returns all the calendar notification templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar notification templates
	 * @param companyId the primary key of the company
	 * @return the matching calendar notification templates, or an empty list if no matches were found
	 */
	public static List<CalendarNotificationTemplate>
		getCalendarNotificationTemplatesByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().getCalendarNotificationTemplatesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of calendar notification templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar notification templates
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching calendar notification templates, or an empty list if no matches were found
	 */
	public static List<CalendarNotificationTemplate>
		getCalendarNotificationTemplatesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CalendarNotificationTemplate> orderByComparator) {

		return getService().getCalendarNotificationTemplatesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of calendar notification templates.
	 *
	 * @return the number of calendar notification templates
	 */
	public static int getCalendarNotificationTemplatesCount() {
		return getService().getCalendarNotificationTemplatesCount();
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
	 * Updates the calendar notification template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was updated
	 */
	public static CalendarNotificationTemplate
		updateCalendarNotificationTemplate(
			CalendarNotificationTemplate calendarNotificationTemplate) {

		return getService().updateCalendarNotificationTemplate(
			calendarNotificationTemplate);
	}

	public static CalendarNotificationTemplate
			updateCalendarNotificationTemplate(
				long calendarNotificationTemplateId,
				String notificationTypeSettings, String subject, String body,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCalendarNotificationTemplate(
			calendarNotificationTemplateId, notificationTypeSettings, subject,
			body, serviceContext);
	}

	public static CalendarNotificationTemplateLocalService getService() {
		return _service;
	}

	private static volatile CalendarNotificationTemplateLocalService _service;

}