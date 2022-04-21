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

package com.liferay.site.internal.initializer;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SiteInitializerRegistry.class)
public class SiteInitializerRegistryImpl implements SiteInitializerRegistry {

	@Override
	public SiteInitializer getSiteInitializer(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		SiteInitializer siteInitializer = _serviceTrackerMap.getService(key);

		if (siteInitializer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No site initializer registered with key " + key);
			}

			return null;
		}

		return siteInitializer;
	}

	@Override
	public List<SiteInitializer> getSiteInitializers(long companyId) {
		return getSiteInitializers(companyId, false);
	}

	@Override
	public List<SiteInitializer> getSiteInitializers(
		long companyId, boolean activeOnly) {

		if (!activeOnly) {
			return new ArrayList<>(_serviceTrackerMap.values());
		}

		List<SiteInitializer> siteInitializers = new ArrayList<>();

		for (SiteInitializer siteInitializer : _serviceTrackerMap.values()) {
			if (siteInitializer.isActive(companyId)) {
				siteInitializers.add(siteInitializer);
			}
		}

		return siteInitializers;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, SiteInitializer.class, "site.initializer.key");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteInitializerRegistryImpl.class);

	private ServiceTrackerMap<String, SiteInitializer> _serviceTrackerMap;

}