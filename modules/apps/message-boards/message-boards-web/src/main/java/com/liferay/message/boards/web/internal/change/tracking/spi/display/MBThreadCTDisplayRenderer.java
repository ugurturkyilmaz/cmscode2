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

package com.liferay.message.boards.web.internal.change.tracking.spi.display;

import com.liferay.change.tracking.spi.display.BaseCTDisplayRenderer;
import com.liferay.change.tracking.spi.display.CTDisplayRenderer;
import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBThread;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cheryl Tang
 */
@Component(immediate = true, service = CTDisplayRenderer.class)
public class MBThreadCTDisplayRenderer extends BaseCTDisplayRenderer<MBThread> {

	@Override
	public String getEditURL(
			HttpServletRequest httpServletRequest, MBThread mbThread)
		throws PortalException {

		Group group = _groupLocalService.getGroup(mbThread.getGroupId());

		if (group.isCompany()) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			group = themeDisplay.getScopeGroup();
		}

		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				httpServletRequest, group, MBPortletKeys.MESSAGE_BOARDS, 0, 0,
				PortletRequest.RENDER_PHASE)
		).setMVCRenderCommandName(
			"/message_boards/edit_message"
		).setRedirect(
			_portal.getCurrentURL(httpServletRequest)
		).setParameter(
			"messageId", mbThread.getRootMessageId()
		).buildString();
	}

	@Override
	public Class<MBThread> getModelClass() {
		return MBThread.class;
	}

	@Override
	public String getTitle(Locale locale, MBThread mbThread) {
		return mbThread.getTitle();
	}

	@Override
	protected void buildDisplay(DisplayBuilder<MBThread> displayBuilder) {
		MBThread mbThread = displayBuilder.getModel();

		displayBuilder.display(
			"title", mbThread.getTitle()
		).display(
			"created-by",
			() -> {
				String userName = mbThread.getUserName();

				if (Validator.isNotNull(userName)) {
					return userName;
				}

				return null;
			}
		).display(
			"create-date", mbThread.getCreateDate()
		).display(
			"last-modified", mbThread.getModifiedDate()
		).display(
			"last-post-date", mbThread.getLastPostDate()
		).display(
			"category",
			() -> {
				MBCategory mbCategory = mbThread.getCategory();

				return mbCategory.getName();
			}
		).display(
			"message-count", mbThread.getMessageCount()
		).display(
			"question", mbThread.isQuestion()
		).display(
			"locked", mbThread.isLocked()
		);
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}