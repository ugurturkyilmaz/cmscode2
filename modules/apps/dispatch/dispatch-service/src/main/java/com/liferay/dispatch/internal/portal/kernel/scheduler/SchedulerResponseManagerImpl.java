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

package com.liferay.dispatch.internal.portal.kernel.scheduler;

import com.liferay.dispatch.scheduler.SchedulerResponseManager;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(service = SchedulerResponseManager.class)
public class SchedulerResponseManagerImpl implements SchedulerResponseManager {

	@Override
	public Date getNextFireDate(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException {

		return _schedulerEngineHelper.getNextFireTime(
			jobName, groupName, storageType);
	}

	@Override
	public List<SchedulerResponse> getSchedulerResponses(int start, int end) {
		List<SchedulerResponse> schedulerResponses = new ArrayList<>();

		try {
			schedulerResponses = _schedulerEngineHelper.getScheduledJobs();
		}
		catch (SchedulerException schedulerException) {
			_log.error("Unable to get scheduler responses", schedulerException);

			return schedulerResponses;
		}

		schedulerResponses = ListUtil.filter(
			schedulerResponses,
			schedulerResponse -> {
				String jobName = schedulerResponse.getJobName();

				return !jobName.startsWith("DISPATCH_JOB_");
			});

		Collections.sort(
			schedulerResponses,
			Comparator.comparing(
				schedulerResponse -> getSimpleJobName(
					schedulerResponse.getJobName())));

		return schedulerResponses.subList(
			start, Math.min(end, schedulerResponses.size()));
	}

	@Override
	public int getSchedulerResponsesCount() {
		List<SchedulerResponse> schedulerResponses = null;

		try {
			schedulerResponses = _schedulerEngineHelper.getScheduledJobs();
		}
		catch (SchedulerException schedulerException) {
			_log.error("Unable to get scheduler responses", schedulerException);

			return 0;
		}

		return ListUtil.count(
			schedulerResponses,
			schedulerResponse -> {
				String jobName = schedulerResponse.getJobName();

				return !jobName.startsWith("DISPATCH_JOB_");
			});
	}

	@Override
	public String getSimpleJobName(String jobName) {
		return jobName.substring(jobName.lastIndexOf(StringPool.PERIOD) + 1);
	}

	@Override
	public TriggerState getTriggerState(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException {

		return _schedulerEngineHelper.getJobState(
			jobName, groupName, storageType);
	}

	@Override
	public void pause(String jobName, String groupName, StorageType storageType)
		throws SchedulerException {

		_schedulerEngineHelper.pause(jobName, groupName, storageType);
	}

	@Override
	public void resume(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException {

		_schedulerEngineHelper.resume(jobName, groupName, storageType);
	}

	@Override
	public void run(String jobName, String groupName, StorageType storageType)
		throws SchedulerException {

		SchedulerResponse schedulerResponse =
			_schedulerEngineHelper.getScheduledJob(
				jobName, groupName, storageType);

		_messageBus.sendMessage(
			schedulerResponse.getDestinationName(),
			schedulerResponse.getMessage());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SchedulerResponseManagerImpl.class);

	@Reference
	private MessageBus _messageBus;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

}