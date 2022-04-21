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

package com.liferay.sharing.web.internal.servlet.taglib.ui;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.servlet.taglib.ui.SharingEntryMenuItemContributor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true, service = SharingEntryMenuItemContributorRegistry.class
)
public class SharingEntryMenuItemContributorRegistry {

	public SharingEntryMenuItemContributor getSharingEntryMenuItemContributor(
			long classNameId)
		throws PortalException {

		ClassName className = _classNameLocalService.getClassName(classNameId);

		return new CompositeSharingEntryMenuItemContributor(
			_serviceTrackerMap.getService(className.getClassName()));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, SharingEntryMenuItemContributor.class,
			"model.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private ServiceTrackerMap<String, List<SharingEntryMenuItemContributor>>
		_serviceTrackerMap;

	private static final class CompositeSharingEntryMenuItemContributor
		implements SharingEntryMenuItemContributor {

		public CompositeSharingEntryMenuItemContributor(
			List<SharingEntryMenuItemContributor>
				sharingEntryMenuItemContributors) {

			_sharingEntryMenuItemContributors =
				sharingEntryMenuItemContributors;
		}

		@Override
		public Collection<MenuItem> getSharingEntryMenuItems(
			SharingEntry sharingEntry, ThemeDisplay themeDisplay) {

			if (ListUtil.isEmpty(_sharingEntryMenuItemContributors)) {
				return Collections.emptyList();
			}

			List<MenuItem> menuItems = new ArrayList<>();

			for (SharingEntryMenuItemContributor
					sharingEntryMenuItemContributor :
						_sharingEntryMenuItemContributors) {

				menuItems.addAll(
					sharingEntryMenuItemContributor.getSharingEntryMenuItems(
						sharingEntry, themeDisplay));
			}

			return menuItems;
		}

		private final List<SharingEntryMenuItemContributor>
			_sharingEntryMenuItemContributors;

	}

}