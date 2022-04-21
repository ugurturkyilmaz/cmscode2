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

package com.liferay.portal.search.web.internal.folder.facet.portlet.shared.search;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.facet.folder.FolderFacetSearchContributor;
import com.liferay.portal.search.web.internal.folder.facet.constants.FolderFacetPortletKeys;
import com.liferay.portal.search.web.internal.folder.facet.portlet.FolderFacetPortletPreferences;
import com.liferay.portal.search.web.internal.folder.facet.portlet.FolderFacetPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Arrays;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lino Alves
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + FolderFacetPortletKeys.FOLDER_FACET,
	service = PortletSharedSearchContributor.class
)
public class FolderFacetPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		FolderFacetPortletPreferences folderFacetPortletPreferences =
			new FolderFacetPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		folderFacetSearchContributor.contribute(
			portletSharedSearchSettings.getSearchRequestBuilder(),
			siteFacetBuilder -> siteFacetBuilder.aggregationName(
				portletSharedSearchSettings.getPortletId()
			).frequencyThreshold(
				folderFacetPortletPreferences.getFrequencyThreshold()
			).maxTerms(
				folderFacetPortletPreferences.getMaxTerms()
			).selectedFolderIds(
				_toLongArray(
					portletSharedSearchSettings.getParameterValues(
						folderFacetPortletPreferences.getParameterName()))
			));
	}

	@Reference
	protected FolderFacetSearchContributor folderFacetSearchContributor;

	private long[] _toLongArray(String[] parameterValues) {
		if (!ArrayUtil.isEmpty(parameterValues)) {
			return ListUtil.toLongArray(
				Arrays.asList(parameterValues), GetterUtil::getLong);
		}

		return new long[0];
	}

}