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

package com.liferay.message.boards.web.internal.display.context;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Map;

import javax.portlet.ResourceURL;

/**
 * @author Ambrín Chaudhary
 */
public class MBEditMessageDisplayContext {

	public MBEditMessageDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, MBMessage message) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_message = message;
	}

	public Map<String, Object> getMBPortletComponentContext() throws Exception {
		long messageId = BeanParamUtil.getLong(
			_message, _liferayPortletRequest, "messageId");

		Map<String, Object> taglibContext = HashMapBuilder.<String, Object>put(
			"constants",
			HashMapBuilder.<String, Object>put(
				"ACTION_PUBLISH", WorkflowConstants.ACTION_PUBLISH
			).put(
				"ACTION_SAVE_DRAFT", WorkflowConstants.ACTION_SAVE_DRAFT
			).put(
				"CMD", Constants.CMD
			).build()
		).put(
			"currentAction",
			(_message == null) ? Constants.ADD : Constants.UPDATE
		).put(
			"messageId", (_message != null) ? messageId : null
		).put(
			"rootNodeId",
			_liferayPortletResponse.getNamespace() + "mbEditPageContainer"
		).build();

		if (_message != null) {
			ResourceURL getAttachmentsURL =
				_liferayPortletResponse.createResourceURL();

			getAttachmentsURL.setParameter(
				"messageId", String.valueOf(_message.getMessageId()));
			getAttachmentsURL.setResourceID("/message_boards/get_attachments");

			taglibContext.put(
				"getAttachmentsURL", getAttachmentsURL.toString());

			taglibContext.put(
				"viewTrashAttachmentsURL",
				PortletURLBuilder.createRenderURL(
					_liferayPortletResponse
				).setMVCRenderCommandName(
					"/message_boards/view_deleted_message_attachments"
				).setRedirect(
					PortletURLUtil.getCurrent(
						_liferayPortletRequest, _liferayPortletResponse)
				).setParameter(
					"messageId", _message.getMessageId()
				).setWindowState(
					LiferayWindowState.POP_UP
				).buildString());
		}

		return taglibContext;
	}

	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private MBMessage _message;

}