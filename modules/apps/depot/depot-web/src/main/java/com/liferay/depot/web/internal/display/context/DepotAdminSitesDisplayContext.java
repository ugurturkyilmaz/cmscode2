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

package com.liferay.depot.web.internal.display.context;

import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.service.DepotEntryGroupRelLocalServiceUtil;
import com.liferay.depot.web.internal.constants.DepotAdminWebKeys;
import com.liferay.depot.web.internal.util.DepotEntryURLUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionURL;
import javax.portlet.PortletURL;

/**
 * @author Cristina González
 */
public class DepotAdminSitesDisplayContext {

	public DepotAdminSitesDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		_currentURL = PortletURLUtil.getCurrent(
			_liferayPortletRequest, _liferayPortletResponse);
	}

	public DropdownItemList getConnectedSiteDropdownItems(
		DepotEntryGroupRel depotEntryGroupRel) {

		return DropdownItemListBuilder.add(
			dropdownItem -> {
				ActionURL updateSearchableActionURL =
					DepotEntryURLUtil.getUpdateSearchableActionURL(
						depotEntryGroupRel.getDepotEntryGroupRelId(),
						!depotEntryGroupRel.isSearchable(),
						_currentURL.toString(), _liferayPortletResponse);

				dropdownItem.setHref(updateSearchableActionURL.toString());

				dropdownItem.setLabel(
					LanguageUtil.get(
						PortalUtil.getHttpServletRequest(
							_liferayPortletRequest),
						_getUpdateSearchableKey(depotEntryGroupRel)));
			}
		).add(
			dropdownItem -> {
				ActionURL updateDDMStructuresAvailableActionURL =
					DepotEntryURLUtil.getUpdateDDMStructuresAvailableActionURL(
						depotEntryGroupRel.getDepotEntryGroupRelId(),
						!depotEntryGroupRel.isDdmStructuresAvailable(),
						_currentURL.toString(), _liferayPortletResponse);

				dropdownItem.setData(
					HashMapBuilder.<String, Object>put(
						"action", "shareWebContentStructures"
					).put(
						"shared", depotEntryGroupRel.isDdmStructuresAvailable()
					).put(
						"url", updateDDMStructuresAvailableActionURL.toString()
					).build());

				dropdownItem.setLabel(
					LanguageUtil.get(
						PortalUtil.getHttpServletRequest(
							_liferayPortletRequest),
						_getUpdateDDMStructuresAvailableKey(
							depotEntryGroupRel)));
			}
		).add(
			dropdownItem -> {
				dropdownItem.setData(
					HashMapBuilder.<String, Object>put(
						"action", "disconnect"
					).put(
						"url",
						() -> {
							ActionURL disconnectSiteActionURL =
								DepotEntryURLUtil.getDisconnectSiteActionURL(
									depotEntryGroupRel.
										getDepotEntryGroupRelId(),
									_currentURL.toString(),
									_liferayPortletResponse);

							return disconnectSiteActionURL.toString();
						}
					).build());

				dropdownItem.setDisabled(
					depotEntryGroupRel.isDdmStructuresAvailable());
				dropdownItem.setLabel(
					LanguageUtil.get(
						PortalUtil.getHttpServletRequest(
							_liferayPortletRequest),
						"disconnect"));
			}
		).build();
	}

	public List<DepotEntryGroupRel> getDepotEntryGroupRels() {
		return DepotEntryGroupRelLocalServiceUtil.getDepotEntryGroupRels(
			_getDepotEntry());
	}

	public PortletURL getItemSelectorURL() {
		ItemSelector itemSelector =
			(ItemSelector)_liferayPortletRequest.getAttribute(
				DepotAdminWebKeys.ITEM_SELECTOR);

		ItemSelectorCriterion itemSelectorCriterion =
			new SiteItemSelectorCriterion();

		itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new URLItemSelectorReturnType());

		return itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_liferayPortletRequest),
			_liferayPortletResponse.getNamespace() + "selectSite",
			itemSelectorCriterion);
	}

	public String getSiteName(DepotEntryGroupRel depotEntryGroupRel)
		throws PortalException {

		Locale locale = LocaleUtil.fromLanguageId(
			LanguageUtil.getLanguageId(_liferayPortletRequest));

		Group group = GroupServiceUtil.getGroup(
			depotEntryGroupRel.getToGroupId());

		return group.getDescriptiveName(locale);
	}

	public boolean isLiveDepotEntry() throws PortalException {
		DepotEntry depotEntry = _getDepotEntry();

		StagingGroupHelper stagingGroupHelper =
			StagingGroupHelperUtil.getStagingGroupHelper();

		return stagingGroupHelper.isLiveGroup(depotEntry.getGroup());
	}

	private DepotEntry _getDepotEntry() {
		return (DepotEntry)_liferayPortletRequest.getAttribute(
			DepotAdminWebKeys.DEPOT_ENTRY);
	}

	private String _getUpdateDDMStructuresAvailableKey(
		DepotEntryGroupRel depotEntryGroupRel) {

		if (!depotEntryGroupRel.isDdmStructuresAvailable()) {
			return "make-structures-available";
		}

		return "make-structures-unavailable";
	}

	private String _getUpdateSearchableKey(
		DepotEntryGroupRel depotEntryGroupRel) {

		if (depotEntryGroupRel.isSearchable()) {
			return "make-unsearchable";
		}

		return "make-searchable";
	}

	private final PortletURL _currentURL;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;

}