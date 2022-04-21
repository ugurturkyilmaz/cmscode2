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

package com.liferay.configuration.admin.web.internal.search;

import com.liferay.configuration.admin.web.internal.model.ConfigurationModel;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiServiceUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import java.util.Collection;

import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.BundleTracker;

/**
 * @author Tina Tian
 */
@Component(
	immediate = true,
	service = {
		ClusterConfigurationModelIndexer.class, IdentifiableOSGiService.class
	}
)
public class ClusterConfigurationModelIndexer
	implements IdentifiableOSGiService {

	@Override
	public String getOSGiServiceIdentifier() {
		return ClusterConfigurationModelIndexer.class.getName();
	}

	public void initialize() throws PortletException {
		if (_initialized) {
			return;
		}

		synchronized (this) {
			if (_initialized) {
				return;
			}

			if (_clusterMasterExecutor.isMaster()) {
				try (SafeCloseable safeCloseable =
						ProxyModeThreadLocal.setWithSafeCloseable(true)) {

					_bundleTracker = _configurationModelIndexer.initialize();
				}
			}
			else {
				NoticeableFuture<Void> noticeableFuture =
					_clusterMasterExecutor.executeOnMaster(
						new MethodHandler(
							_initializeMethodKey, getOSGiServiceIdentifier()));

				try {
					noticeableFuture.get();
				}
				catch (Exception exception) {
					throw new PortletException(
						"Unable to initialize configuration model index",
						exception);
				}
			}

			_initialized = true;
		}
	}

	@Activate
	protected void activate() {
		if (_clusterExecutor.isEnabled()) {
			_configurationModelsClusterMasterTokenTransitionListener =
				new ConfigurationModelsClusterMasterTokenTransitionListener();

			_clusterMasterExecutor.addClusterMasterTokenTransitionListener(
				_configurationModelsClusterMasterTokenTransitionListener);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_configurationModelsClusterMasterTokenTransitionListener != null) {
			_clusterMasterExecutor.removeClusterMasterTokenTransitionListener(
				_configurationModelsClusterMasterTokenTransitionListener);
		}

		_stopBundleTracker();
	}

	private static void _initialize(String osgiServiceIdentifier)
		throws Exception {

		ClusterConfigurationModelIndexer clusterConfigurationModelIndexer =
			(ClusterConfigurationModelIndexer)
				IdentifiableOSGiServiceUtil.getIdentifiableOSGiService(
					osgiServiceIdentifier);

		clusterConfigurationModelIndexer.initialize();
	}

	private static void _reset(String osgiServiceIdentifier) {
		ClusterConfigurationModelIndexer clusterConfigurationModelIndexer =
			(ClusterConfigurationModelIndexer)
				IdentifiableOSGiServiceUtil.getIdentifiableOSGiService(
					osgiServiceIdentifier);

		clusterConfigurationModelIndexer._initialized = false;
	}

	private synchronized void _stopBundleTracker() {
		if (_bundleTracker != null) {
			try (SafeCloseable safeCloseable =
					ProxyModeThreadLocal.setWithSafeCloseable(true)) {

				_bundleTracker.close();
			}

			_bundleTracker = null;
		}
	}

	private static final MethodKey _initializeMethodKey = new MethodKey(
		ClusterConfigurationModelIndexer.class, "_initialize", String.class);
	private static final MethodKey _resetMethodKey = new MethodKey(
		ClusterConfigurationModelIndexer.class, "_reset", String.class);

	private BundleTracker<Collection<ConfigurationModel>> _bundleTracker;

	@Reference
	private ClusterExecutor _clusterExecutor;

	@Reference
	private ClusterMasterExecutor _clusterMasterExecutor;

	@Reference
	private ConfigurationModelIndexer _configurationModelIndexer;

	private ConfigurationModelsClusterMasterTokenTransitionListener
		_configurationModelsClusterMasterTokenTransitionListener;
	private volatile boolean _initialized;

	private class ConfigurationModelsClusterMasterTokenTransitionListener
		implements ClusterMasterTokenTransitionListener {

		@Override
		public void masterTokenAcquired() {
			_initialized = false;

			ClusterRequest clusterRequest =
				ClusterRequest.createMulticastRequest(
					new MethodHandler(
						_resetMethodKey, getOSGiServiceIdentifier()),
					true);

			clusterRequest.setFireAndForget(true);

			_clusterExecutor.execute(clusterRequest);
		}

		@Override
		public void masterTokenReleased() {
			_stopBundleTracker();
		}

	}

}