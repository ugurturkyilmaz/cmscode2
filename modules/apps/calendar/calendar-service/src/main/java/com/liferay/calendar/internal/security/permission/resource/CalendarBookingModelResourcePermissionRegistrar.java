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

package com.liferay.calendar.internal.security.permission.resource;

import com.liferay.calendar.constants.CalendarConstants;
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = {})
public class CalendarBookingModelResourcePermissionRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistration = bundleContext.registerService(
			(Class<ModelResourcePermission<CalendarBooking>>)
				(Class<?>)ModelResourcePermission.class,
			ModelResourcePermissionFactory.create(
				CalendarBooking.class, CalendarBooking::getCalendarBookingId,
				_calendarBookingLocalService::getCalendarBooking,
				_portletResourcePermission,
				(modelResourcePermission, consumer) -> {
					consumer.accept(
						new CalendarBookingInheritanceModelResourcePermissionLogic());
					consumer.accept(
						new StagedModelPermissionLogic<>(
							_stagingPermission, CalendarPortletKeys.CALENDAR,
							CalendarBooking::getCalendarBookingId));
				}),
			HashMapDictionaryBuilder.<String, Object>put(
				"model.class.name", CalendarBooking.class.getName()
			).build());
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

	@Reference
	private CalendarBookingLocalService _calendarBookingLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.Calendar)"
	)
	private ModelResourcePermission<Calendar> _calendarModelResourcePermission;

	@Reference(
		target = "(resource.name=" + CalendarConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

	private ServiceRegistration<ModelResourcePermission<CalendarBooking>>
		_serviceRegistration;

	@Reference
	private StagingPermission _stagingPermission;

	private class CalendarBookingInheritanceModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<CalendarBooking> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				CalendarBooking calendarBooking, String actionId)
			throws PortalException {

			if (!actionId.equals(ActionKeys.VIEW)) {
				return null;
			}

			return _calendarModelResourcePermission.contains(
				permissionChecker, calendarBooking.getCalendar(), actionId);
		}

	}

}