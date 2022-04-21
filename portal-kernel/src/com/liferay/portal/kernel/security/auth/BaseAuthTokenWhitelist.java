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

package com.liferay.portal.kernel.security.auth;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Tomas Polesovsky
 */
public abstract class BaseAuthTokenWhitelist implements AuthTokenWhitelist {

	@Override
	public boolean isOriginCSRFWhitelisted(long companyId, String origin) {
		return false;
	}

	@Override
	public boolean isPortletCSRFWhitelisted(
		HttpServletRequest httpServletRequest, Portlet portlet) {

		return false;
	}

	@Override
	public boolean isPortletInvocationWhitelisted(
		HttpServletRequest httpServletRequest, Portlet portlet) {

		return false;
	}

	@Override
	public boolean isPortletURLCSRFWhitelisted(
		LiferayPortletURL liferayPortletURL) {

		return false;
	}

	@Override
	public boolean isPortletURLPortletInvocationWhitelisted(
		LiferayPortletURL liferayPortletURL) {

		return false;
	}

	@Override
	public boolean isValidSharedSecret(String sharedSecret) {
		return false;
	}

	protected void destroy() {
		serviceRegistrations.forEach(ServiceRegistration::unregister);

		for (ServiceTracker<?, ?> serviceTracker : serviceTrackers) {
			serviceTracker.close();
		}
	}

	protected void registerPortalProperty(String key) {
		String[] values = PropsUtil.getArray(key);

		if (values.length > 0) {
			serviceRegistrations.add(
				_bundleContext.registerService(
					Object.class, new Object(),
					MapUtil.singletonDictionary(key, values)));
		}
	}

	protected void trackWhitelistServices(
		String whitelistName, Set<String> whiteList) {

		ServiceTracker<Object, Object> serviceTracker = new ServiceTracker<>(
			SystemBundleUtil.getBundleContext(),
			SystemBundleUtil.createFilter("(" + whitelistName + "=*)"),
			new TokenWhitelistTrackerCustomizer(whitelistName, whiteList));

		serviceTracker.open();

		serviceTrackers.add(serviceTracker);
	}

	protected final List<ServiceRegistration<?>> serviceRegistrations =
		new ArrayList<>();
	protected final List<ServiceTracker<Object, Object>> serviceTrackers =
		new ArrayList<>();

	private final BundleContext _bundleContext =
		SystemBundleUtil.getBundleContext();

	private static class TokenWhitelistTrackerCustomizer
		implements ServiceTrackerCustomizer<Object, Object> {

		public TokenWhitelistTrackerCustomizer(
			String whitelistName, Set<String> whitelist) {

			_whitelistName = whitelistName;
			_whitelist = whitelist;
		}

		@Override
		public Object addingService(ServiceReference<Object> serviceReference) {
			List<String> authTokenIgnoreActions = StringUtil.asList(
				serviceReference.getProperty(_whitelistName));

			_whitelist.addAll(authTokenIgnoreActions);

			return authTokenIgnoreActions;
		}

		@Override
		public void modifiedService(
			ServiceReference<Object> serviceReference, Object object) {

			removedService(serviceReference, object);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<Object> serviceReference, Object object) {

			Collection<String> authTokenIgnoreActions =
				(Collection<String>)object;

			_whitelist.removeAll(authTokenIgnoreActions);
		}

		private final Set<String> _whitelist;
		private final String _whitelistName;

	}

}