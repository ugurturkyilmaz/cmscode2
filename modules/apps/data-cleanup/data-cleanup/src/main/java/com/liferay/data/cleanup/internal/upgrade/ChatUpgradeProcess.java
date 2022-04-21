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

package com.liferay.data.cleanup.internal.upgrade;

/**
 * @author Preston Crary
 */
public class ChatUpgradeProcess extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		removePortletData(
			new String[] {"com.liferay.chat.web"}, null,
			new String[] {"com_liferay_chat_web_portlet_ChatPortlet"});

		removeServiceData(
			"Chat", new String[] {"com.liferay.chat.service"},
			new String[] {
				"com.liferay.chat.model.Entry", "com.liferay.chat.model.Status"
			},
			new String[] {"Chat_Entry", "Chat_Status"});
	}

}