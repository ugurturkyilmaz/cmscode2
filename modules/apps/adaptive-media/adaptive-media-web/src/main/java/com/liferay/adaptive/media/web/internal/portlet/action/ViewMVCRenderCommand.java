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

package com.liferay.adaptive.media.web.internal.portlet.action;

import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
import com.liferay.adaptive.media.image.configuration.AMImageConfigurationHelper;
import com.liferay.adaptive.media.web.internal.constants.AMPortletKeys;
import com.liferay.adaptive.media.web.internal.constants.AMWebKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AMPortletKeys.ADAPTIVE_MEDIA,
		"mvc.command.name=/", "mvc.command.name=/adaptive_media/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		Collection<AMImageConfigurationEntry> amImageConfigurationEntries =
			_getAMImageConfigurationEntries(renderRequest);

		renderRequest.setAttribute(
			AMWebKeys.CONFIGURATION_ENTRIES_LIST,
			new ArrayList<>(amImageConfigurationEntries));

		return "/adaptive_media/view.jsp";
	}

	private Collection<AMImageConfigurationEntry>
		_getAMImageConfigurationEntries(RenderRequest renderRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String entriesNavigation = ParamUtil.getString(
			renderRequest, "entriesNavigation", "all");

		Predicate<AMImageConfigurationEntry> predicate = null;

		if (entriesNavigation.equals("enabled")) {
			predicate =
				amImageConfigurationEntry ->
					amImageConfigurationEntry.isEnabled();
		}
		else if (entriesNavigation.equals("disabled")) {
			predicate =
				amImageConfigurationEntry ->
					!amImageConfigurationEntry.isEnabled();
		}
		else {
			predicate = amImageConfigurationEntry -> true;
		}

		return _amImageConfigurationHelper.getAMImageConfigurationEntries(
			themeDisplay.getCompanyId(), predicate);
	}

	@Reference
	private AMImageConfigurationHelper _amImageConfigurationHelper;

}