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

package com.liferay.remote.app.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.remote.app.model.RemoteAppEntry;
import com.liferay.remote.app.service.RemoteAppEntryLocalService;
import com.liferay.remote.app.uad.constants.RemoteAppUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the remote app entry UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link RemoteAppEntryUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRemoteAppEntryUADAnonymizer
	extends DynamicQueryUADAnonymizer<RemoteAppEntry> {

	@Override
	public void autoAnonymize(
			RemoteAppEntry remoteAppEntry, long userId, User anonymousUser)
		throws PortalException {

		if (remoteAppEntry.getUserId() == userId) {
			remoteAppEntry.setUserId(anonymousUser.getUserId());
			remoteAppEntry.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(remoteAppEntry, anonymousUser);
		}

		if (remoteAppEntry.getStatusByUserId() == userId) {
			remoteAppEntry.setStatusByUserId(anonymousUser.getUserId());
			remoteAppEntry.setStatusByUserName(anonymousUser.getFullName());
		}

		remoteAppEntryLocalService.updateRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public void delete(RemoteAppEntry remoteAppEntry) throws PortalException {
		remoteAppEntryLocalService.deleteRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public Class<RemoteAppEntry> getTypeClass() {
		return RemoteAppEntry.class;
	}

	protected void autoAnonymizeAssetEntry(
		RemoteAppEntry remoteAppEntry, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(remoteAppEntry);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return remoteAppEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return RemoteAppUADConstants.USER_ID_FIELD_NAMES_REMOTE_APP_ENTRY;
	}

	protected AssetEntry fetchAssetEntry(RemoteAppEntry remoteAppEntry) {
		return assetEntryLocalService.fetchEntry(
			RemoteAppEntry.class.getName(),
			remoteAppEntry.getRemoteAppEntryId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected RemoteAppEntryLocalService remoteAppEntryLocalService;

}