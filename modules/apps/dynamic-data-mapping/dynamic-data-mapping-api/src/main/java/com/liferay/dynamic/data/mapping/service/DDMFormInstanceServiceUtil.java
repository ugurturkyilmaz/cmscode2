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

package com.liferay.dynamic.data.mapping.service;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for DDMFormInstance. This utility wraps
 * <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceService
 * @generated
 */
public class DDMFormInstanceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static DDMFormInstance addFormInstance(
			long groupId, long ddmStructureId,
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFormInstance(
			groupId, ddmStructureId, nameMap, descriptionMap,
			settingsDDMFormValues, serviceContext);
	}

	public static DDMFormInstance addFormInstance(
			long groupId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFormInstance(
			groupId, nameMap, descriptionMap, ddmForm, ddmFormLayout,
			settingsDDMFormValues, serviceContext);
	}

	public static DDMFormInstance copyFormInstance(
			long groupId, Map<java.util.Locale, String> nameMap,
			DDMFormInstance ddmFormInstance,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().copyFormInstance(
			groupId, nameMap, ddmFormInstance, settingsDDMFormValues,
			serviceContext);
	}

	public static void deleteFormInstance(long ddmFormInstanceId)
		throws PortalException {

		getService().deleteFormInstance(ddmFormInstanceId);
	}

	public static DDMFormInstance fetchFormInstance(long ddmFormInstanceId)
		throws PortalException {

		return getService().fetchFormInstance(ddmFormInstanceId);
	}

	public static DDMFormInstance getFormInstance(long ddmFormInstanceId)
		throws PortalException {

		return getService().getFormInstance(ddmFormInstanceId);
	}

	public static List<DDMFormInstance> getFormInstances(
		long companyId, long groupId, int start, int end) {

		return getService().getFormInstances(companyId, groupId, start, end);
	}

	public static int getFormInstancesCount(long companyId, long groupId) {
		return getService().getFormInstancesCount(companyId, groupId);
	}

	public static int getFormInstancesCount(String uuid)
		throws PortalException {

		return getService().getFormInstancesCount(uuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<DDMFormInstance> search(
		long companyId, long groupId, String keywords, int status, int start,
		int end, OrderByComparator<DDMFormInstance> orderByComparator) {

		return getService().search(
			companyId, groupId, keywords, status, start, end,
			orderByComparator);
	}

	public static List<DDMFormInstance> search(
		long companyId, long groupId, String keywords, int start, int end,
		OrderByComparator<DDMFormInstance> orderByComparator) {

		return getService().search(
			companyId, groupId, keywords, start, end, orderByComparator);
	}

	public static List<DDMFormInstance> search(
		long companyId, long groupId, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMFormInstance> orderByComparator) {

		return getService().search(
			companyId, groupId, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	public static int searchCount(
		long companyId, long groupId, String keywords) {

		return getService().searchCount(companyId, groupId, keywords);
	}

	public static int searchCount(
		long companyId, long groupId, String keywords, int status) {

		return getService().searchCount(companyId, groupId, keywords, status);
	}

	public static int searchCount(
		long companyId, long groupId, String[] names, String[] descriptions,
		boolean andOperator) {

		return getService().searchCount(
			companyId, groupId, names, descriptions, andOperator);
	}

	public static void sendEmail(
			long formInstanceId, String message, String subject,
			String[] toEmailAddresses)
		throws Exception {

		getService().sendEmail(
			formInstanceId, message, subject, toEmailAddresses);
	}

	/**
	 * Updates the the record set's settings.
	 *
	 * @param formInstanceId the primary key of the form instance
	 * @param settingsDDMFormValues the record set's settings. For more
	 information see <code>DDMFormValues</code> in the
	 <code>dynamic.data.mapping.api</code> module.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDMFormInstance updateFormInstance(
			long formInstanceId,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues)
		throws PortalException {

		return getService().updateFormInstance(
			formInstanceId, settingsDDMFormValues);
	}

	public static DDMFormInstance updateFormInstance(
			long ddmFormInstanceId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap,
			com.liferay.dynamic.data.mapping.model.DDMForm ddmForm,
			com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFormInstance(
			ddmFormInstanceId, nameMap, descriptionMap, ddmForm, ddmFormLayout,
			settingsDDMFormValues, serviceContext);
	}

	public static DDMFormInstanceService getService() {
		return _service;
	}

	private static volatile DDMFormInstanceService _service;

}