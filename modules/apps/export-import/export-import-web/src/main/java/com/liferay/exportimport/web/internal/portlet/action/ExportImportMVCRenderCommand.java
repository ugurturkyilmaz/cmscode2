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

package com.liferay.exportimport.web.internal.portlet.action;

import com.liferay.exportimport.constants.ExportImportPortletKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ExportImportPortletKeys.EXPORT_IMPORT,
		"mvc.command.name=/export_import/export_import"
	},
	service = MVCRenderCommand.class
)
public class ExportImportMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		Portlet portlet = null;

		try {
			portlet = ActionUtil.getPortlet(renderRequest);
		}
		catch (PrincipalException principalException) {
			SessionErrors.add(renderRequest, principalException.getClass());

			return "/error.jsp";
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}

		try {
			renderResponse.setTitle(
				ActionUtil.getTitle(portlet, renderRequest));

			renderRequest = ActionUtil.getWrappedRenderRequest(
				renderRequest, null);

			return "/export_import.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/error.jsp";
		}
	}

}