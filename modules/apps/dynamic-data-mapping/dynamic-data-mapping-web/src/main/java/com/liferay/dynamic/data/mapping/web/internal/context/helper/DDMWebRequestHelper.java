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

package com.liferay.dynamic.data.mapping.web.internal.context.helper;

import com.liferay.dynamic.data.mapping.configuration.DDMGroupServiceConfiguration;
import com.liferay.dynamic.data.mapping.configuration.DDMWebConfiguration;
import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.portal.kernel.display.context.helper.BaseRequestHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lino Alves
 */
public class DDMWebRequestHelper extends BaseRequestHelper {

	public DDMWebRequestHelper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	public DDMGroupServiceConfiguration getDDMGroupServiceConfiguration() {
		try {
			if (_ddmGroupServiceConfiguration == null) {
				_ddmGroupServiceConfiguration = _getConfiguration(
					DDMGroupServiceConfiguration.class);
			}

			return _ddmGroupServiceConfiguration;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	public DDMWebConfiguration getDDMWebConfiguration() {
		try {
			if (_ddmWebConfiguration == null) {
				_ddmWebConfiguration = _getConfiguration(
					DDMWebConfiguration.class);
			}

			return _ddmWebConfiguration;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	private <T> T _getConfiguration(Class<T> clazz)
		throws ConfigurationException {

		if (Validator.isNotNull(getPortletResource())) {
			HttpServletRequest httpServletRequest = getRequest();

			return (T)ConfigurationProviderUtil.getConfiguration(
				clazz,
				new ParameterMapSettingsLocator(
					httpServletRequest.getParameterMap(),
					new GroupServiceSettingsLocator(
						getSiteGroupId(), DDMConstants.SERVICE_NAME)));
		}

		return (T)ConfigurationProviderUtil.getConfiguration(
			clazz,
			new GroupServiceSettingsLocator(
				getSiteGroupId(), DDMConstants.SERVICE_NAME));
	}

	private DDMGroupServiceConfiguration _ddmGroupServiceConfiguration;
	private DDMWebConfiguration _ddmWebConfiguration;

}