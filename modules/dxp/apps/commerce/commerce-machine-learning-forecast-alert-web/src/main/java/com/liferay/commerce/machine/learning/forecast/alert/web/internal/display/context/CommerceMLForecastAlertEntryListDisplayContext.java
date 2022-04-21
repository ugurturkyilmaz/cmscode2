/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.machine.learning.forecast.alert.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertActionKeys;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryService;
import com.liferay.commerce.machine.learning.forecast.alert.web.internal.display.context.helper.CommerceMLForecastAlertEntryRequestHelper;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLForecastAlertEntryListDisplayContext {

	public CommerceMLForecastAlertEntryListDisplayContext(
		CommerceAccountLocalService commerceAccountLocalService,
		CommerceMLForecastAlertEntryService commerceMLForecastAlertEntryService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest) {

		_commerceAccountLocalService = commerceAccountLocalService;
		_commerceMLForecastAlertEntryService =
			commerceMLForecastAlertEntryService;
		_portletResourcePermission = portletResourcePermission;

		_commerceMLForecastAlertEntryRequestHelper =
			new CommerceMLForecastAlertEntryRequestHelper(renderRequest);
	}

	public CommerceAccount getCommerceAccount(long commerceAccountId) {
		try {
			return _commerceAccountLocalService.getCommerceAccount(
				commerceAccountId);
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return null;
		}
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceMLForecastAlertEntryRequestHelper.
				getLiferayPortletResponse();

		return liferayPortletResponse.createRenderURL();
	}

	public SearchContainer<CommerceMLForecastAlertEntry> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_commerceMLForecastAlertEntryRequestHelper.
				getLiferayPortletRequest(),
			getPortletURL(), null,
			"there-are-no-forecast-alert-entries-to-display");

		_searchContainer.setResultsAndTotal(
			() ->
				_commerceMLForecastAlertEntryService.
					getBelowThresholdCommerceMLForecastAlertEntries(
						_commerceMLForecastAlertEntryRequestHelper.
							getCompanyId(),
						_commerceMLForecastAlertEntryRequestHelper.getUserId(),
						CommerceMLForecastAlertConstants.STATUS_NEW, 0.0,
						_searchContainer.getStart(), _searchContainer.getEnd()),
			_commerceMLForecastAlertEntryService.
				getBelowThresholdCommerceMLForecastAlertEntriesCount(
					_commerceMLForecastAlertEntryRequestHelper.getCompanyId(),
					_commerceMLForecastAlertEntryRequestHelper.getUserId(),
					CommerceMLForecastAlertConstants.STATUS_NEW, 0.0));

		return _searchContainer;
	}

	public boolean hasUpdatePermission() {
		return _portletResourcePermission.contains(
			_commerceMLForecastAlertEntryRequestHelper.getPermissionChecker(),
			GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.MANAGE_ALERT_STATUS);
	}

	public boolean hasViewPermission() {
		return _portletResourcePermission.contains(
			_commerceMLForecastAlertEntryRequestHelper.getPermissionChecker(),
			GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceMLForecastAlertEntryListDisplayContext.class);

	private final CommerceAccountLocalService _commerceAccountLocalService;
	private final CommerceMLForecastAlertEntryRequestHelper
		_commerceMLForecastAlertEntryRequestHelper;
	private final CommerceMLForecastAlertEntryService
		_commerceMLForecastAlertEntryService;
	private final PortletResourcePermission _portletResourcePermission;
	private SearchContainer<CommerceMLForecastAlertEntry> _searchContainer;

}