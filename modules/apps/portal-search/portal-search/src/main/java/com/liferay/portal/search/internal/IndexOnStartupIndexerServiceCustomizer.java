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

package com.liferay.portal.search.internal;

import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.internal.instance.lifecycle.IndexOnStartupPortalInstanceLifecycleListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = ServiceTrackerCustomizer.class)
public class IndexOnStartupIndexerServiceCustomizer
	implements ServiceTrackerCustomizer<Indexer<?>, Indexer<?>> {

	@Override
	public Indexer<?> addingService(
		ServiceReference<Indexer<?>> serviceReference) {

		Indexer<?> indexer = _bundleContext.getService(serviceReference);

		boolean indexerIndexOnStartup = GetterUtil.getBoolean(
			serviceReference.getProperty(PropsKeys.INDEX_ON_STARTUP),
			GetterUtil.getBoolean(_props.get(PropsKeys.INDEX_ON_STARTUP)));

		String className = indexer.getClassName();

		if (!indexerIndexOnStartup || Validator.isNull(className) ||
			_isBaseSearcher(indexer.getClass())) {

			return indexer;
		}

		synchronized (_serviceRegistrations) {
			if (_serviceRegistrations.containsKey(className)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Skip duplicate service registration for " + className);
				}

				return indexer;
			}

			PortalInstanceLifecycleListener portalInstanceLifecycleListener =
				new IndexOnStartupPortalInstanceLifecycleListener(
					_indexWriterHelper, className);

			ServiceRegistration<PortalInstanceLifecycleListener>
				serviceRegistration = _bundleContext.registerService(
					PortalInstanceLifecycleListener.class,
					portalInstanceLifecycleListener,
					new HashMapDictionary<String, Object>());

			_serviceRegistrations.put(className, serviceRegistration);
		}

		return indexer;
	}

	@Override
	public void modifiedService(
		ServiceReference<Indexer<?>> serviceReference, Indexer<?> indexer) {
	}

	@Override
	public void removedService(
		ServiceReference<Indexer<?>> serviceReference, Indexer<?> indexer) {

		synchronized (_serviceRegistrations) {
			ServiceRegistration<PortalInstanceLifecycleListener>
				serviceRegistration = _serviceRegistrations.remove(
					indexer.getClassName());

			if (serviceRegistration != null) {
				serviceRegistration.unregister();
			}
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		long indexOnStartupDelay = GetterUtil.getInteger(
			_props.get(PropsKeys.INDEX_ON_STARTUP_DELAY));

		ScheduledExecutorService scheduledExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		scheduledExecutorService.schedule(
			() -> {
				if (_bundleContext != null) {
					_serviceTracker = new ServiceTracker<>(
						_bundleContext,
						(Class<Indexer<?>>)(Class<?>)Indexer.class, this);

					_serviceTracker.open();
				}
			},
			indexOnStartupDelay, TimeUnit.SECONDS);

		scheduledExecutorService.shutdown();
	}

	@Deactivate
	protected void deactivate() {
		_bundleContext = null;

		Set<String> removedIndexerClassNames = new HashSet<>();

		synchronized (_serviceRegistrations) {
			for (Map.Entry
					<String,
					 ServiceRegistration<PortalInstanceLifecycleListener>>
						entry : _serviceRegistrations.entrySet()) {

				ServiceRegistration<?> serviceRegistration = entry.getValue();

				serviceRegistration.unregister();

				removedIndexerClassNames.add(entry.getKey());
			}

			for (String removedIndexerClassName : removedIndexerClassNames) {
				_serviceRegistrations.remove(removedIndexerClassName);
			}
		}

		if (_serviceTracker != null) {
			_serviceTracker.close();
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(target = "(search.engine.id=SYSTEM_ENGINE)", unbind = "-")
	protected void setSearchEngine(SearchEngine searchEngine) {
	}

	private boolean _isBaseSearcher(Class<?> indexerClass) {
		while ((indexerClass != null) && !Object.class.equals(indexerClass)) {
			if (indexerClass.equals(BaseSearcher.class)) {
				return true;
			}

			indexerClass = indexerClass.getSuperclass();
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndexOnStartupIndexerServiceCustomizer.class);

	@Reference
	private BackgroundTaskManager _backgroundTaskManager;

	private BundleContext _bundleContext;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

	@Reference
	private Props _props;

	private final Map
		<String, ServiceRegistration<PortalInstanceLifecycleListener>>
			_serviceRegistrations = new HashMap<>();
	private ServiceTracker<Indexer<?>, Indexer<?>> _serviceTracker;

}