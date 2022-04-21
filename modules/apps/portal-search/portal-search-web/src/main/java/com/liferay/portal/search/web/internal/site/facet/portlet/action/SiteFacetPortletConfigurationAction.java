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

package com.liferay.portal.search.web.internal.site.facet.portlet.action;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.internal.facet.display.context.builder.ScopeSearchFacetDisplayContextBuilder;
import com.liferay.portal.search.web.internal.site.facet.constants.SiteFacetPortletKeys;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lino Alves
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SiteFacetPortletKeys.SITE_FACET,
	service = ConfigurationAction.class
)
public class SiteFacetPortletConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/site/facet/configuration.jsp";
	}

	@Override
	public void include(
			PortletConfig portletConfig, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		RenderRequest renderRequest =
			(RenderRequest)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		ScopeSearchFacetDisplayContextBuilder
			scopeSearchFacetDisplayContextBuilder =
				_createScopeSearchFacetDisplayContextBuilder(renderRequest);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			scopeSearchFacetDisplayContextBuilder.build());

		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}

	private ScopeSearchFacetDisplayContextBuilder
		_createScopeSearchFacetDisplayContextBuilder(
			RenderRequest renderRequest) {

		try {
			return new ScopeSearchFacetDisplayContextBuilder(renderRequest);
		}
		catch (ConfigurationException configurationException) {
			throw new RuntimeException(configurationException);
		}
	}

}