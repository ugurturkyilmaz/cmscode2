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

package com.liferay.dispatch.internal.messaging;

import com.liferay.dispatch.constants.DispatchConstants;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorRegistry;
import com.liferay.dispatch.executor.DispatchTaskStatus;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.DispatchLogLocalService;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(
	property = "destination.name=" + DispatchConstants.EXECUTOR_DESTINATION_NAME,
	service = MessageListener.class
)
public class DispatchMessageListener extends BaseMessageListener {

	@Override
	public void doReceive(Message message) throws Exception {
		String payload = (String)message.getPayload();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

		long dispatchTriggerId = jsonObject.getLong("dispatchTriggerId");

		DispatchTrigger dispatchTrigger =
			_dispatchTriggerLocalService.getDispatchTrigger(dispatchTriggerId);

		if (!dispatchTrigger.isOverlapAllowed()) {
			DispatchLog dispatchLog =
				_dispatchLogLocalService.fetchLatestDispatchLog(
					dispatchTriggerId, DispatchTaskStatus.IN_PROGRESS);

			if (dispatchLog != null) {
				Date date = new Date();

				_dispatchLogLocalService.addDispatchLog(
					dispatchTrigger.getUserId(),
					dispatchTrigger.getDispatchTriggerId(), date,
					"Only one instance in progress is allowed", null, date,
					DispatchTaskStatus.CANCELED);

				return;
			}
		}

		_execute(dispatchTrigger);
	}

	private void _execute(DispatchTrigger dispatchTrigger) throws Exception {
		DispatchTaskExecutor dispatchTaskExecutor =
			_dispatchTaskExecutorRegistry.fetchDispatchTaskExecutor(
				dispatchTrigger.getDispatchTaskExecutorType());

		if (dispatchTaskExecutor != null) {
			dispatchTaskExecutor.execute(
				dispatchTrigger.getDispatchTriggerId());

			return;
		}

		String message =
			"Unable to find dispatch task executor of type " +
				dispatchTrigger.getDispatchTaskExecutorType();

		if (_log.isWarnEnabled()) {
			_log.warn(message);
		}

		Date date = new Date();

		_dispatchLogLocalService.addDispatchLog(
			dispatchTrigger.getUserId(), dispatchTrigger.getDispatchTriggerId(),
			date, message, null, date, DispatchTaskStatus.CANCELED);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DispatchMessageListener.class);

	@Reference
	private DispatchLogLocalService _dispatchLogLocalService;

	@Reference
	private DispatchTaskExecutorRegistry _dispatchTaskExecutorRegistry;

	@Reference
	private DispatchTriggerLocalService _dispatchTriggerLocalService;

}