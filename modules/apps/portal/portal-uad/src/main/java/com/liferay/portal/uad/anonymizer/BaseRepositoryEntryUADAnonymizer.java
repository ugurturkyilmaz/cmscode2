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

package com.liferay.portal.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RepositoryEntryLocalService;
import com.liferay.portal.uad.constants.PortalUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the repository entry UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link RepositoryEntryUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRepositoryEntryUADAnonymizer
	extends DynamicQueryUADAnonymizer<RepositoryEntry> {

	@Override
	public void autoAnonymize(
			RepositoryEntry repositoryEntry, long userId, User anonymousUser)
		throws PortalException {

		if (repositoryEntry.getUserId() == userId) {
			repositoryEntry.setUserId(anonymousUser.getUserId());
			repositoryEntry.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(repositoryEntry, anonymousUser);
		}

		repositoryEntryLocalService.updateRepositoryEntry(repositoryEntry);
	}

	@Override
	public void delete(RepositoryEntry repositoryEntry) throws PortalException {
		repositoryEntryLocalService.deleteRepositoryEntry(repositoryEntry);
	}

	@Override
	public Class<RepositoryEntry> getTypeClass() {
		return RepositoryEntry.class;
	}

	protected void autoAnonymizeAssetEntry(
		RepositoryEntry repositoryEntry, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(repositoryEntry);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return repositoryEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return PortalUADConstants.USER_ID_FIELD_NAMES_REPOSITORY_ENTRY;
	}

	protected AssetEntry fetchAssetEntry(RepositoryEntry repositoryEntry) {
		return assetEntryLocalService.fetchEntry(
			RepositoryEntry.class.getName(),
			repositoryEntry.getRepositoryEntryId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected RepositoryEntryLocalService repositoryEntryLocalService;

}