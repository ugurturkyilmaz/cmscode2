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

package com.liferay.translation.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.translation.constants.TranslationPortletKeys;
import com.liferay.translation.model.TranslationEntry;
import com.liferay.translation.service.TranslationEntryLocalService;
import com.liferay.translation.web.internal.display.context.ViewDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + TranslationPortletKeys.TRANSLATION,
		"mvc.command.name=/", "mvc.command.name=/translation/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		renderRequest.setAttribute(
			ViewDisplayContext.class.getName(),
			new ViewDisplayContext(
				_portal.getHttpServletRequest(renderRequest),
				_portal.getLiferayPortletRequest(renderRequest),
				_portal.getLiferayPortletResponse(renderResponse),
				_modelResourcePermission, _translationEntryLocalService));

		return "/view.jsp";
	}

	@Reference(
		target = "(model.class.name=com.liferay.translation.model.TranslationEntry)"
	)
	private ModelResourcePermission<TranslationEntry> _modelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference
	private TranslationEntryLocalService _translationEntryLocalService;

}