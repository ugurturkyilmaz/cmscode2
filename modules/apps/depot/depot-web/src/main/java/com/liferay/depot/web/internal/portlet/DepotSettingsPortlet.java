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

package com.liferay.depot.web.internal.portlet;

import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryService;
import com.liferay.depot.web.internal.application.controller.DepotApplicationController;
import com.liferay.depot.web.internal.constants.DepotAdminWebKeys;
import com.liferay.depot.web.internal.constants.DepotPortletKeys;
import com.liferay.depot.web.internal.display.context.DepotAdminDetailsDisplayContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.DynamicRenderRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-depot-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Depot",
		"javax.portlet.init-param.always-send-redirect=true",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/edit_depot_entry.jsp",
		"javax.portlet.name=" + DepotPortletKeys.DEPOT_SETTINGS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class DepotSettingsPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			renderRequest.setAttribute(
				DepotAdminWebKeys.DEPOT_ADMIN_DETAILS_DISPLAY_CONTEXT,
				new DepotAdminDetailsDisplayContext(
					_depotApplicationController, renderRequest));

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			DepotEntry depotEntry = _depotEntryService.getGroupDepotEntry(
				themeDisplay.getScopeGroupId());

			renderRequest.setAttribute(
				DepotAdminWebKeys.DEPOT_ENTRY, depotEntry);

			renderRequest.setAttribute(
				DepotAdminWebKeys.ITEM_SELECTOR, _itemSelector);
			renderRequest.setAttribute(
				DepotAdminWebKeys.SHOW_BREADCRUMB, Boolean.TRUE);

			super.doView(
				new DynamicRenderRequest(
					renderRequest,
					HashMapBuilder.put(
						"depotEntryId",
						new String[] {
							String.valueOf(depotEntry.getDepotEntryId())
						}
					).build()),
				renderResponse);
		}
		catch (PortalException portalException) {
			throw new PortletException(portalException);
		}
	}

	@Reference
	private DepotApplicationController _depotApplicationController;

	@Reference
	private DepotEntryService _depotEntryService;

	@Reference
	private ItemSelector _itemSelector;

}