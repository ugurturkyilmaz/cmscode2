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

package com.liferay.portal.reports.engine.console.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.reports.engine.console.model.Definition;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for Definition. This utility wraps
 * <code>com.liferay.portal.reports.engine.console.service.impl.DefinitionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionService
 * @generated
 */
public class DefinitionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.reports.engine.console.service.impl.DefinitionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Definition addDefinition(
			long groupId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addDefinition(
			groupId, nameMap, descriptionMap, sourceId, reportParameters,
			fileName, inputStream, serviceContext);
	}

	public static Definition deleteDefinition(long definitionId)
		throws PortalException {

		return getService().deleteDefinition(definitionId);
	}

	public static Definition getDefinition(long definitionId)
		throws PortalException {

		return getService().getDefinition(definitionId);
	}

	public static List<Definition> getDefinitions(
			long groupId, String definitionName, String description,
			String sourceId, String reportName, boolean andSearch, int start,
			int end, OrderByComparator<Definition> orderByComparator)
		throws PortalException {

		return getService().getDefinitions(
			groupId, definitionName, description, sourceId, reportName,
			andSearch, start, end, orderByComparator);
	}

	public static int getDefinitionsCount(
		long groupId, String definitionName, String description,
		String sourceId, String reportName, boolean andSearch) {

		return getService().getDefinitionsCount(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Definition updateDefinition(
			long definitionId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateDefinition(
			definitionId, nameMap, descriptionMap, sourceId, reportParameters,
			fileName, inputStream, serviceContext);
	}

	public static DefinitionService getService() {
		return _service;
	}

	private static volatile DefinitionService _service;

}