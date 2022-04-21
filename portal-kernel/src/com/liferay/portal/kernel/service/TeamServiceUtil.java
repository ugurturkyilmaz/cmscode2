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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for Team. This utility wraps
 * <code>com.liferay.portal.service.impl.TeamServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TeamService
 * @generated
 */
public class TeamServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.TeamServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Team addTeam(
			long groupId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		return getService().addTeam(groupId, name, description, serviceContext);
	}

	public static void deleteTeam(long teamId) throws PortalException {
		getService().deleteTeam(teamId);
	}

	public static List<Team> getGroupTeams(long groupId)
		throws PortalException {

		return getService().getGroupTeams(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Team getTeam(long teamId) throws PortalException {
		return getService().getTeam(teamId);
	}

	public static Team getTeam(long groupId, String name)
		throws PortalException {

		return getService().getTeam(groupId, name);
	}

	public static List<Team> getUserTeams(long userId) throws PortalException {
		return getService().getUserTeams(userId);
	}

	public static List<Team> getUserTeams(long userId, long groupId)
		throws PortalException {

		return getService().getUserTeams(userId, groupId);
	}

	public static boolean hasUserTeam(long userId, long teamId)
		throws PortalException {

		return getService().hasUserTeam(userId, teamId);
	}

	public static List<Team> search(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator<Team> orderByComparator) {

		return getService().search(
			groupId, name, description, params, start, end, orderByComparator);
	}

	public static int searchCount(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params) {

		return getService().searchCount(groupId, name, description, params);
	}

	public static Team updateTeam(long teamId, String name, String description)
		throws PortalException {

		return getService().updateTeam(teamId, name, description);
	}

	public static TeamService getService() {
		return _service;
	}

	private static volatile TeamService _service;

}