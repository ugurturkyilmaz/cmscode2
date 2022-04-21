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

package com.liferay.segments.web.internal.portlet.action;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.odata.retriever.ODataRetriever;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryService;
import com.liferay.segments.web.internal.constants.SegmentsWebKeys;
import com.liferay.segments.web.internal.display.context.PreviewSegmentsEntryUsersDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SegmentsPortletKeys.SEGMENTS,
		"mvc.command.name=/segments/preview_segments_entry_users"
	},
	service = MVCRenderCommand.class
)
public class PreviewSegmentsEntryUsersMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		if (ParamUtil.getBoolean(httpServletRequest, "clearSessionCriteria")) {
			PortletSession portletSession = renderRequest.getPortletSession();

			portletSession.removeAttribute(
				SegmentsWebKeys.PREVIEW_SEGMENTS_ENTRY_CRITERIA);
		}

		ODataRetriever<User> userODataRetriever = _serviceTrackerMap.getService(
			User.class.getName());

		PreviewSegmentsEntryUsersDisplayContext
			previewSegmentsEntryUsersDisplayContext =
				new PreviewSegmentsEntryUsersDisplayContext(
					httpServletRequest, renderRequest, renderResponse,
					_segmentsEntryProviderRegistry, _segmentsEntryService,
					userODataRetriever, _userLocalService);

		renderRequest.setAttribute(
			SegmentsWebKeys.PREVIEW_SEGMENTS_ENTRY_USERS_DISPLAY_CONTEXT,
			previewSegmentsEntryUsersDisplayContext);

		return "/preview_segments_entry_users.jsp";
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<ODataRetriever<User>>)(Class<?>)ODataRetriever.class,
			"model.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference
	private SegmentsEntryService _segmentsEntryService;

	private ServiceTrackerMap<String, ODataRetriever<User>> _serviceTrackerMap;

	@Reference
	private UserLocalService _userLocalService;

}