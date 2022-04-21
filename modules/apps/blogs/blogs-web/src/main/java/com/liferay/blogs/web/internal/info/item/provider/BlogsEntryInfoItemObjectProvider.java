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

package com.liferay.blogs.web.internal.info.item.provider;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.provider.InfoItemObjectProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = {
		"info.item.identifier=com.liferay.info.item.ClassPKInfoItemIdentifier",
		"info.item.identifier=com.liferay.info.item.GroupUrlTitleInfoItemIdentifier",
		"service.ranking:Integer=100"
	},
	service = InfoItemObjectProvider.class
)
public class BlogsEntryInfoItemObjectProvider
	implements InfoItemObjectProvider<BlogsEntry> {

	@Override
	public BlogsEntry getInfoItem(InfoItemIdentifier infoItemIdentifier)
		throws NoSuchInfoItemException {

		if (!(infoItemIdentifier instanceof ClassPKInfoItemIdentifier) &&
			!(infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier)) {

			throw new NoSuchInfoItemException(
				"Unsupported info item identifier type " + infoItemIdentifier);
		}

		BlogsEntry blogsEntry = null;

		if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
			ClassPKInfoItemIdentifier classPKInfoItemIdentifier =
				(ClassPKInfoItemIdentifier)infoItemIdentifier;

			blogsEntry = _blogsEntryLocalService.fetchBlogsEntry(
				classPKInfoItemIdentifier.getClassPK());
		}
		else if (infoItemIdentifier instanceof
					GroupUrlTitleInfoItemIdentifier) {

			GroupUrlTitleInfoItemIdentifier groupURLTitleInfoItemIdentifier =
				(GroupUrlTitleInfoItemIdentifier)infoItemIdentifier;

			blogsEntry = _blogsEntryLocalService.fetchEntry(
				groupURLTitleInfoItemIdentifier.getGroupId(),
				groupURLTitleInfoItemIdentifier.getUrlTitle());
		}

		if ((blogsEntry == null) || blogsEntry.isDraft() ||
			blogsEntry.isInTrash()) {

			throw new NoSuchInfoItemException(
				"Unable to get blogs entry with info item identifier " +
					infoItemIdentifier);
		}

		return blogsEntry;
	}

	@Override
	public BlogsEntry getInfoItem(long classPK) throws NoSuchInfoItemException {
		InfoItemIdentifier infoItemIdentifier = new ClassPKInfoItemIdentifier(
			classPK);

		return getInfoItem(infoItemIdentifier);
	}

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

}