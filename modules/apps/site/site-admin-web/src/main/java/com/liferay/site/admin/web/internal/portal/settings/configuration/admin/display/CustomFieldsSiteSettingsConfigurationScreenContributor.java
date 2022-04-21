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

package com.liferay.site.admin.web.internal.portal.settings.configuration.admin.display;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.site.settings.configuration.admin.display.SiteSettingsConfigurationScreenContributor;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = SiteSettingsConfigurationScreenContributor.class)
public class CustomFieldsSiteSettingsConfigurationScreenContributor
	implements SiteSettingsConfigurationScreenContributor {

	@Override
	public String getCategoryKey() {
		return "other";
	}

	@Override
	public String getJspPath() {
		return "/site_settings/custom_fields.jsp";
	}

	@Override
	public String getKey() {
		return "site-configuration-custom-fields";
	}

	@Override
	public String getName(Locale locale) {
		return LanguageUtil.get(locale, "custom-fields");
	}

	@Override
	public String getSaveMVCActionCommandName() {
		return "/site_admin/edit_custom_fields";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public boolean isVisible(Group group) {
		if (group.isCompany()) {
			return false;
		}

		boolean hasCustomAttributesAvailable = false;

		try {
			hasCustomAttributesAvailable =
				CustomAttributesUtil.hasCustomAttributes(
					group.getCompanyId(), Group.class.getName(),
					group.getGroupId(), null);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		if (!hasCustomAttributesAvailable) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CustomFieldsSiteSettingsConfigurationScreenContributor.class);

	@Reference(target = "(osgi.web.symbolicname=com.liferay.site.admin.web)")
	private ServletContext _servletContext;

}