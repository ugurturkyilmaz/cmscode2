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

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamFinderUtil {

	public static int countByG_N_D(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params) {

		return getFinder().countByG_N_D(groupId, name, description, params);
	}

	public static int filterCountByG_N_D(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params) {

		return getFinder().filterCountByG_N_D(
			groupId, name, description, params);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Team>
		filterFindByG_N_D(
			long groupId, String name, String description,
			java.util.LinkedHashMap<String, Object> params, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return getFinder().filterFindByG_N_D(
			groupId, name, description, params, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Team>
		findByG_U(
			long groupId, long userId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return getFinder().findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Team>
		findByG_N_D(
			long groupId, String name, String description,
			java.util.LinkedHashMap<String, Object> params, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return getFinder().findByG_N_D(
			groupId, name, description, params, start, end, orderByComparator);
	}

	public static TeamFinder getFinder() {
		if (_finder == null) {
			_finder = (TeamFinder)PortalBeanLocatorUtil.locate(
				TeamFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(TeamFinder finder) {
		_finder = finder;
	}

	private static TeamFinder _finder;

}