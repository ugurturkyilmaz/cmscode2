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

package com.liferay.portal.kernel.scheduler.config;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class PluginSchedulingConfigurator {

	public void afterPropertiesSet() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try (SafeCloseable safeCloseable =
				ProxyModeThreadLocal.setWithSafeCloseable(true)) {

			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			currentThread.setContextClassLoader(portalClassLoader);

			for (SchedulerEntry schedulerEntry : _schedulerEntries) {
				try {
					MessageListener messageListener =
						(MessageListener)InstanceFactory.newInstance(
							PortletClassLoaderUtil.getClassLoader(),
							schedulerEntry.getEventListenerClass());

					SchedulerEngineHelperUtil.register(
						messageListener, schedulerEntry,
						DestinationNames.SCHEDULER_DISPATCH);

					_messageListeners.put(
						schedulerEntry.getEventListenerClass(),
						messageListener);
				}
				catch (Exception exception) {
					_log.error(
						"Unable to schedule " + schedulerEntry, exception);
				}
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void destroy() {
		for (MessageListener messageListener : _messageListeners.values()) {
			SchedulerEngineHelperUtil.unregister(messageListener);
		}

		_messageListeners.clear();
		_schedulerEntries.clear();
	}

	public void setSchedulerEntries(List<SchedulerEntry> schedulerEntries) {
		_schedulerEntries = schedulerEntries;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PluginSchedulingConfigurator.class);

	private final Map<String, MessageListener> _messageListeners =
		new HashMap<>();
	private List<SchedulerEntry> _schedulerEntries = Collections.emptyList();

}