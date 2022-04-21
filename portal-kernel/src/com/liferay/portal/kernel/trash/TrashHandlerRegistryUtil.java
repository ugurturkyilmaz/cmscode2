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

package com.liferay.portal.kernel.trash;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;

/**
 * @author Alexander Chow
 */
public class TrashHandlerRegistryUtil {

	public static TrashHandler getTrashHandler(String className) {
		return _trashHandlers.getService(className);
	}

	public static List<TrashHandler> getTrashHandlers() {
		return new ArrayList<>(_trashHandlers.values());
	}

	private static final BundleContext _bundleContext =
		SystemBundleUtil.getBundleContext();

	private static final ServiceTrackerMap<String, TrashHandler>
		_trashHandlers = ServiceTrackerMapFactory.openSingleValueMap(
			_bundleContext, TrashHandler.class, null,
			(serviceReference, emitter) -> {
				TrashHandler trashHandler = _bundleContext.getService(
					serviceReference);

				emitter.emit(trashHandler.getClassName());

				_bundleContext.ungetService(serviceReference);
			});

}