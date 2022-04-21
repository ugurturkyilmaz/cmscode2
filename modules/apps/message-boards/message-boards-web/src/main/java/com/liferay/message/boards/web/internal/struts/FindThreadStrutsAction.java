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

package com.liferay.message.boards.web.internal.struts;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.FindStrutsAction;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"path=/message_boards/find_thread"
	},
	service = StrutsAction.class
)
public class FindThreadStrutsAction extends FindStrutsAction {

	@Override
	public long getGroupId(long primaryKey) throws Exception {
		MBThread thread = _mbThreadLocalService.getThread(primaryKey);

		return thread.getGroupId();
	}

	@Override
	public String getPrimaryKeyParameterName() {
		return "threadId";
	}

	@Override
	public PortletURL processPortletURL(
			HttpServletRequest httpServletRequest, PortletURL portletURL)
		throws Exception {

		long threadId = ParamUtil.getLong(
			httpServletRequest, getPrimaryKeyParameterName());

		MBThread thread = _mbThreadLocalService.getThread(threadId);

		portletURL.setParameter(
			"messageId", String.valueOf(thread.getRootMessageId()));

		return portletURL;
	}

	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey) {
	}

	@Override
	protected void addRequiredParameters(
		HttpServletRequest httpServletRequest, String portletId,
		PortletURL portletURL) {

		portletURL.setParameter(
			"mvcRenderCommandName", "/message_boards/view_message");
	}

	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return _portletPageFinder;
	}

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBThread)"
	)
	private PortletLayoutFinder _portletPageFinder;

}