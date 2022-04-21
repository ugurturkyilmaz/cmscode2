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

package com.liferay.directory.web.internal.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.util.PortletKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Ryan Park
 */
@Component(service = OpenSearch.class)
public class DirectoryOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String TITLE = "Liferay Directory Search: ";

	@Override
	public String getClassName() {
		return User.class.getName();
	}

	@Override
	public Indexer<User> getIndexer() {
		return IndexerRegistryUtil.getIndexer(User.class);
	}

	@Override
	public String getSearchPath() {
		return StringPool.BLANK;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

	@Override
	protected PortletURL getPortletURL(
			HttpServletRequest httpServletRequest, String portletId,
			long scopeGroupId)
		throws Exception {

		return super.getPortletURL(
			httpServletRequest, PortletKeys.DIRECTORY, scopeGroupId);
	}

}