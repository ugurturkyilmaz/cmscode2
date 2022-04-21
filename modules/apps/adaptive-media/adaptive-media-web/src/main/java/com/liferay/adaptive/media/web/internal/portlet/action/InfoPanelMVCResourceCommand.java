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
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ambrín Chaudhary
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AMPortletKeys.ADAPTIVE_MEDIA,
		"mvc.command.name=/adaptive_media/info_panel"
	},
	service = MVCResourceCommand.class
)
public class InfoPanelMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		resourceRequest.setAttribute(
			AMWebKeys.SELECTED_CONFIGURATION_ENTRIES,
			_getSelectedAMImageConfigurationEntries(resourceRequest));

		resourceRequest.setAttribute(
			AMWebKeys.CONFIGURATION_ENTRIES_LIST,
			_getAMImageConfigurationEntries(resourceRequest));

		include(
			resourceRequest, resourceResponse,
			"/adaptive_media/info_panel.jsp");
	}

	private List<AMImageConfigurationEntry> _getAMImageConfigurationEntries(
		ResourceRequest resourceRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Collection<AMImageConfigurationEntry> amImageConfigurationEntries =
			_amImageConfigurationHelper.getAMImageConfigurationEntries(
				themeDisplay.getCompanyId(), amImageConfigurationEntry -> true);

		return new ArrayList<>(amImageConfigurationEntries);
	}

	private List<AMImageConfigurationEntry>
		_getSelectedAMImageConfigurationEntries(
			ResourceRequest resourceRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] rowIdsAMImageConfigurationEntry = ParamUtil.getStringValues(
			resourceRequest, "rowIdsAMImageConfigurationEntry");

		List<AMImageConfigurationEntry> amImageConfigurationEntries =
			new ArrayList<>();

		for (String entryUuid : rowIdsAMImageConfigurationEntry) {
			Optional<AMImageConfigurationEntry>
				amImageConfigurationEntryOptional =
					_amImageConfigurationHelper.getAMImageConfigurationEntry(
						themeDisplay.getCompanyId(), entryUuid);

			amImageConfigurationEntryOptional.ifPresent(
				amImageConfigurationEntries::add);
		}

		return amImageConfigurationEntries;
	}

	@Reference
	private AMImageConfigurationHelper _amImageConfigurationHelper;

}