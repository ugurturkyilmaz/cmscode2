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

package com.liferay.portal.db.partition.internal.messaging;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.db.partition.internal.configuration.DBPartitionConfiguration;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusInterceptor;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alberto Chaparro
 */
@Component(
	configurationPid = "com.liferay.portal.db.partition.internal.configuration.DBPartitionConfiguration",
	immediate = true, service = MessageBusInterceptor.class
)
public class DBPartitionMessageBusInterceptor implements MessageBusInterceptor {

	@Override
	public boolean intercept(
		MessageBus messageBus, String destinationName, Message message) {

		if (_databasePartitionEnabled &&
			(message.getLong("companyId") == CompanyConstants.SYSTEM) &&
			!_excludedMessageBusDestinationNames.contains(destinationName) &&
			!_excludedSchedulerJobNames.contains(
				message.getString(SchedulerEngine.JOB_NAME))) {

			List<Long> companyIds = new ArrayList<>();

			_companyLocalService.forEachCompany(
				company -> {
					if (!company.isActive()) {
						return;
					}

					companyIds.add(company.getCompanyId());
				});

			message.remove("companyId");

			message.put("companyIds", companyIds.toArray(new Long[0]));
		}

		return false;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_databasePartitionEnabled = GetterUtil.getBoolean(
			_props.get("database.partition.enabled"));

		modified(properties);
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		DBPartitionConfiguration dbPartitionConfiguration =
			ConfigurableUtil.createConfigurable(
				DBPartitionConfiguration.class, properties);

		_excludedMessageBusDestinationNames = SetUtil.fromArray(
			dbPartitionConfiguration.excludedMessageBusDestinationNames());
		_excludedSchedulerJobNames = SetUtil.fromArray(
			dbPartitionConfiguration.excludedSchedulerJobNames());
	}

	private static boolean _databasePartitionEnabled;

	@Reference
	private CompanyLocalService _companyLocalService;

	private volatile Set<String> _excludedMessageBusDestinationNames;
	private volatile Set<String> _excludedSchedulerJobNames;

	@Reference
	private Props _props;

}