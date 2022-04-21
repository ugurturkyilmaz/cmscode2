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

package com.liferay.dynamic.data.lists.web.internal.display.context;

import com.liferay.dynamic.data.lists.constants.DDLActionKeys;
import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.constants.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.constants.DDLWebKeys;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.lists.util.DDL;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetCreateDateComparator;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetModifiedDateComparator;
import com.liferay.dynamic.data.lists.util.comparator.DDLRecordSetNameComparator;
import com.liferay.dynamic.data.lists.web.internal.configuration.DDLWebConfiguration;
import com.liferay.dynamic.data.lists.web.internal.display.context.helper.DDLRequestHelper;
import com.liferay.dynamic.data.lists.web.internal.search.RecordSetSearch;
import com.liferay.dynamic.data.lists.web.internal.security.permission.resource.DDLPermission;
import com.liferay.dynamic.data.lists.web.internal.security.permission.resource.DDLRecordSetPermission;
import com.liferay.dynamic.data.lists.web.internal.security.permission.resource.DDMTemplatePermission;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.security.permission.DDMPermissionSupport;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMDisplayRegistry;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenuBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemListBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marcellus Tavares
 */
public class DDLDisplayContext {

	public DDLDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse, DDL ddl,
			DDLRecordSetLocalService ddlRecordSetLocalService,
			DDLWebConfiguration ddlWebConfiguration,
			DDMDisplayRegistry ddmDisplayRegistry,
			DDMPermissionSupport ddmPermissionSupport,
			DDMTemplateLocalService ddmTemplateLocalService,
			StorageEngine storageEngine)
		throws PortalException {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_ddl = ddl;
		_ddlRecordSetLocalService = ddlRecordSetLocalService;
		_ddlWebConfiguration = ddlWebConfiguration;
		_ddmDisplayRegistry = ddmDisplayRegistry;
		_ddmPermissionSupport = ddmPermissionSupport;
		_ddmTemplateLocalService = ddmTemplateLocalService;
		_storageEngine = storageEngine;

		_ddlRequestHelper = new DDLRequestHelper(
			PortalUtil.getHttpServletRequest(_renderRequest));

		if (Validator.isNotNull(_getPortletResource())) {
			return;
		}

		if ((getRecordSet() == null) || !_hasViewPermission()) {
			renderRequest.setAttribute(
				WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		}
	}

	public boolean changeableDefaultLanguage() {
		return _ddlWebConfiguration.changeableDefaultLanguage();
	}

	public List<DropdownItem> getActionItemsDropdownItems() {
		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.putData("action", "deleteRecordSets");
				dropdownItem.setIcon("times-circle");
				dropdownItem.setLabel(
					LanguageUtil.get(_ddlRequestHelper.getRequest(), "delete"));
				dropdownItem.setQuickAction(true);
			}
		).build();
	}

	public String getAddDDMTemplateTitle() throws PortalException {
		DDMDisplay ddmDisplay = _getDDMDisplay();

		return ddmDisplay.getEditTemplateTitle(
			_recordSet.getDDMStructure(), null, getLocale());
	}

	public String getAddRecordLabel() throws PortalException {
		DDLRecordSet recordSet = getRecordSet();

		String structureName = StringPool.BLANK;

		if (recordSet != null) {
			DDMStructure ddmStructure = recordSet.getDDMStructure();

			structureName = ddmStructure.getName(_ddlRequestHelper.getLocale());
		}

		return LanguageUtil.format(
			_ddlRequestHelper.getRequest(), "add-x",
			HtmlUtil.escape(structureName), false);
	}

	public String getClearResultsURL() throws PortletException {
		return PortletURLBuilder.create(
			PortletURLUtil.clone(getPortletURL(), _renderResponse)
		).setKeywords(
			StringPool.BLANK
		).buildString();
	}

	public CreationMenu getCreationMenu() {
		if (!isShowAddRecordSetIcon()) {
			return null;
		}

		return CreationMenuBuilder.addPrimaryDropdownItem(
			dropdownItem -> {
				dropdownItem.setHref(
					_renderResponse.createRenderURL(), "mvcPath",
					"/edit_record_set.jsp", "redirect",
					PortalUtil.getCurrentURL(_ddlRequestHelper.getRequest()));

				dropdownItem.setLabel(
					LanguageUtil.get(_ddlRequestHelper.getRequest(), "add"));
			}
		).build();
	}

	public String getCSVExport() {
		return _ddlWebConfiguration.csvExport();
	}

	public OrderByComparator<DDLRecordSet> getDDLRecordSetOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<DDLRecordSet> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new DDLRecordSetCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new DDLRecordSetModifiedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new DDLRecordSetNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public DDMFormValues getDDMFormValues(long classPK) throws PortalException {
		return _storageEngine.getDDMFormValues(classPK);
	}

	public long getDisplayDDMTemplateId() {
		return PrefsParamUtil.getLong(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "displayDDMTemplateId");
	}

	public String getDisplayStyle() {
		if (_ddlRecordDisplayStyle == null) {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					_ddlRequestHelper.getRenderRequest());

			_ddlRecordDisplayStyle = ParamUtil.getString(
				_ddlRequestHelper.getRenderRequest(), "displayStyle");

			if (Validator.isNull(_ddlRecordDisplayStyle)) {
				_ddlRecordDisplayStyle = portalPreferences.getValue(
					DDLPortletKeys.DYNAMIC_DATA_LISTS, "display-style",
					_ddlWebConfiguration.defaultDisplayView());
			}
			else if (ArrayUtil.contains(
						getDisplayViews(), _ddlRecordDisplayStyle)) {

				portalPreferences.setValue(
					DDLPortletKeys.DYNAMIC_DATA_LISTS, "display-style",
					_ddlRecordDisplayStyle);
			}

			if (!ArrayUtil.contains(
					getDisplayViews(), _ddlRecordDisplayStyle)) {

				_ddlRecordDisplayStyle = getDisplayViews()[0];
			}
		}

		return _ddlRecordDisplayStyle;
	}

	public String[] getDisplayViews() {
		return _DISPLAY_VIEWS;
	}

	public String getEditDisplayDDMTemplateTitle() throws PortalException {
		DDLRecordSet recordSet = getRecordSet();

		if (recordSet == null) {
			return StringPool.BLANK;
		}

		DDMDisplay ddmDisplay = _getDDMDisplay();

		return ddmDisplay.getEditTemplateTitle(
			recordSet.getDDMStructure(), _fetchDisplayDDMTemplate(),
			getLocale());
	}

	public String getEditFormDDMTemplateTitle() throws PortalException {
		DDLRecordSet recordSet = getRecordSet();

		if (recordSet == null) {
			return LanguageUtil.get(getLocale(), "add-list");
		}

		DDMDisplay ddmDisplay = _getDDMDisplay();

		return ddmDisplay.getEditTemplateTitle(
			recordSet.getDDMStructure(), _fetchFormDDMTemplate(), getLocale());
	}

	public List<DropdownItem> getFilterItemsDropdownItems() {
		HttpServletRequest httpServletRequest = _ddlRequestHelper.getRequest();

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					getFilterNavigationDropdownItems());
				dropdownGroupItem.setLabel(
					LanguageUtil.get(
						httpServletRequest, "filter-by-navigation"));
			}
		).addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(getOrderByDropdownItems());
				dropdownGroupItem.setLabel(
					LanguageUtil.get(httpServletRequest, "order-by"));
			}
		).build();
	}

	public long getFormDDMTemplateId() {
		return PrefsParamUtil.getLong(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "formDDMTemplateId");
	}

	public List<NavigationItem> getNavigationItems() {
		return NavigationItemListBuilder.add(
			navigationItem -> {
				navigationItem.setActive(true);

				HttpServletRequest httpServletRequest =
					_ddlRequestHelper.getRequest();

				navigationItem.setLabel(
					HtmlUtil.escape(
						LanguageUtil.get(httpServletRequest, "lists")));
			}
		).build();
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_renderRequest, DDLPortletKeys.DYNAMIC_DATA_LISTS, "modified-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_renderRequest, DDLPortletKeys.DYNAMIC_DATA_LISTS, "asc");

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			_renderResponse
		).setMVCPath(
			"/view.jsp"
		).setKeywords(
			() -> {
				String keywords = getKeywords();

				if (Validator.isNotNull(keywords)) {
					return keywords;
				}

				return null;
			}
		).setParameter(
			"delta",
			() -> {
				String delta = ParamUtil.getString(_renderRequest, "delta");

				if (Validator.isNotNull(delta)) {
					return delta;
				}

				return null;
			}
		).setParameter(
			"displayStyle",
			() -> {
				String displayStyle = ParamUtil.getString(
					_renderRequest, "displayStyle");

				if (Validator.isNotNull(displayStyle)) {
					return getDisplayStyle();
				}

				return null;
			}
		).setParameter(
			"orderByCol",
			() -> {
				String orderByCol = getOrderByCol();

				if (Validator.isNotNull(orderByCol)) {
					return orderByCol;
				}

				return null;
			}
		).setParameter(
			"orderByType",
			() -> {
				String orderByType = getOrderByType();

				if (Validator.isNotNull(orderByType)) {
					return orderByType;
				}

				return null;
			}
		).buildPortletURL();
	}

	public DDLRecordSet getRecordSet() {
		if (_recordSet != null) {
			return _recordSet;
		}

		RenderRequest renderRequest = _ddlRequestHelper.getRenderRequest();

		_recordSet = (DDLRecordSet)renderRequest.getAttribute(
			DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD_SET);

		if (_recordSet != null) {
			return _recordSet;
		}

		_recordSet = _ddlRecordSetLocalService.fetchDDLRecordSet(
			getRecordSetId());

		return _recordSet;
	}

	public long getRecordSetId() {
		return PrefsParamUtil.getLong(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "recordSetId");
	}

	public JSONArray getRecordSetJSONArray(
			DDLRecordSet recordSet, Locale locale)
		throws Exception {

		return _ddl.getRecordSetJSONArray(recordSet, locale);
	}

	public JSONArray getRecordsJSONArray(
			List<DDLRecord> records, boolean latestRecordVersion, Locale locale)
		throws Exception {

		return _ddl.getRecordsJSONArray(records, latestRecordVersion, locale);
	}

	public SearchContainer<?> getSearch() {
		PortletURL portletURL = PortletURLBuilder.create(
			getPortletURL()
		).setParameter(
			"displayStyle", getDisplayStyle()
		).buildPortletURL();

		RecordSetSearch recordSetSearch = new RecordSetSearch(
			_renderRequest, portletURL);

		if (recordSetSearch.isSearch()) {
			recordSetSearch.setEmptyResultsMessage("no-lists-were-found");
		}
		else {
			recordSetSearch.setEmptyResultsMessage("there-are-no-lists");
		}

		recordSetSearch.setOrderByCol(getOrderByCol());
		recordSetSearch.setOrderByComparator(
			getDDLRecordSetOrderByComparator(
				getOrderByCol(), getOrderByType()));
		recordSetSearch.setOrderByType(getOrderByType());
		recordSetSearch.setResultsAndTotal(
			() -> _ddlRecordSetLocalService.search(
				_ddlRequestHelper.getCompanyId(),
				_ddlRequestHelper.getScopeGroupId(), getKeywords(),
				DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS,
				recordSetSearch.getStart(), recordSetSearch.getEnd(),
				recordSetSearch.getOrderByComparator()),
			_ddlRecordSetLocalService.searchCount(
				_ddlRequestHelper.getCompanyId(),
				_ddlRequestHelper.getScopeGroupId(), getKeywords(),
				DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS));

		return recordSetSearch;
	}

	public String getSearchContainerId() {
		return "ddlRecordSet";
	}

	public String getSortingURL() throws Exception {
		return PortletURLBuilder.create(
			PortletURLUtil.clone(getPortletURL(), _renderResponse)
		).setParameter(
			"orderByType",
			() -> {
				String orderByType = ParamUtil.getString(
					_renderRequest, "orderByType");

				if (orderByType.equals("asc")) {
					return "desc";
				}

				return "asc";
			}
		).buildString();
	}

	public int getTotalItems() {
		SearchContainer<?> searchContainer = getSearch();

		return searchContainer.getTotal();
	}

	public List<ViewTypeItem> getViewTypesItems() throws Exception {
		PortletURL portletURL = PortletURLUtil.clone(
			getPortletURL(), _renderResponse);

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
			{
				if (ArrayUtil.contains(getDisplayViews(), "descriptive")) {
					addListViewTypeItem();
				}

				if (ArrayUtil.contains(getDisplayViews(), "list")) {
					addTableViewTypeItem();
				}
			}
		};
	}

	public boolean isAdminPortlet() {
		String portletName = getPortletName();

		return portletName.equals(DDLPortletKeys.DYNAMIC_DATA_LISTS);
	}

	public boolean isDisabledManagementBar() {
		if (hasResults() || isSearch()) {
			return false;
		}

		return true;
	}

	public boolean isDisplayPortlet() {
		return !isAdminPortlet();
	}

	public boolean isEditable() {
		if (isAdminPortlet()) {
			return true;
		}

		return PrefsParamUtil.getBoolean(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "editable", true);
	}

	public boolean isFormView() {
		return PrefsParamUtil.getBoolean(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "formView");
	}

	public boolean isShowAddDDMDisplayTemplateIcon() throws PortalException {
		if (_isShowAddDDMTemplateIcon() && !isFormView()) {
			return true;
		}

		return false;
	}

	public boolean isShowAddDDMFormTemplateIcon() throws PortalException {
		return _isShowAddDDMTemplateIcon();
	}

	public boolean isShowAddRecordButton() throws PortalException {
		if (isFormView() || isSpreadsheet()) {
			return false;
		}

		if (isEditable() && _hasAddRecordPermission()) {
			return true;
		}

		return false;
	}

	public boolean isShowAddRecordSetIcon() {
		if (_hasAddRecordSetPermission != null) {
			return _hasAddRecordSetPermission;
		}

		_hasAddRecordSetPermission = DDLPermission.contains(
			getPermissionChecker(), _getScopeGroupId(),
			DDLActionKeys.ADD_RECORD_SET);

		return _hasAddRecordSetPermission;
	}

	public boolean isShowCancelButton() {
		if (isFormView()) {
			return false;
		}

		return true;
	}

	public boolean isShowConfigurationIcon() throws PortalException {
		if (_showConfigurationIcon != null) {
			return _showConfigurationIcon;
		}

		_showConfigurationIcon = PortletPermissionUtil.contains(
			getPermissionChecker(), _getLayout(), _getPortletId(),
			ActionKeys.CONFIGURATION);

		return _showConfigurationIcon;
	}

	public boolean isShowEditDisplayDDMTemplateIcon() throws PortalException {
		if (_hasEditDisplayDDMTemplatePermission != null) {
			return _hasEditDisplayDDMTemplatePermission;
		}

		_hasEditDisplayDDMTemplatePermission = Boolean.FALSE;

		DDMTemplate displayDDMTemplate = _fetchDisplayDDMTemplate();

		if (displayDDMTemplate == null) {
			return _hasEditDisplayDDMTemplatePermission;
		}

		_hasEditDisplayDDMTemplatePermission = DDMTemplatePermission.contains(
			getPermissionChecker(), getDisplayDDMTemplateId(),
			ActionKeys.UPDATE);

		return _hasEditDisplayDDMTemplatePermission;
	}

	public boolean isShowEditFormDDMTemplateIcon() throws PortalException {
		if (_hasEditFormDDMTemplatePermission != null) {
			return _hasEditFormDDMTemplatePermission;
		}

		_hasEditFormDDMTemplatePermission = Boolean.FALSE;

		if (getFormDDMTemplateId() == 0) {
			return _hasEditFormDDMTemplatePermission;
		}

		_hasEditFormDDMTemplatePermission = DDMTemplatePermission.contains(
			getPermissionChecker(), getFormDDMTemplateId(), ActionKeys.UPDATE);

		return _hasEditFormDDMTemplatePermission;
	}

	public boolean isShowEditRecordSetIcon() throws PortalException {
		DDLRecordSet recordSet = getRecordSet();

		if (recordSet == null) {
			return false;
		}

		return DDLRecordSetPermission.contains(
			getPermissionChecker(), recordSet, ActionKeys.UPDATE);
	}

	public boolean isShowIconsActions() throws PortalException {
		if (isSpreadsheet()) {
			return false;
		}

		if (_hasShowIconsActionPermission != null) {
			return _hasShowIconsActionPermission;
		}

		_hasShowIconsActionPermission = Boolean.FALSE;

		ThemeDisplay themeDisplay = getThemeDisplay();

		if (!themeDisplay.isSignedIn()) {
			return _hasShowIconsActionPermission;
		}

		Layout layout = themeDisplay.getLayout();

		if (layout.isLayoutPrototypeLinkActive()) {
			return _hasShowIconsActionPermission;
		}

		if (isShowConfigurationIcon() || isShowAddDDMDisplayTemplateIcon() ||
			isShowAddDDMFormTemplateIcon() ||
			isShowEditDisplayDDMTemplateIcon() ||
			isShowEditFormDDMTemplateIcon()) {

			_hasShowIconsActionPermission = Boolean.TRUE;
		}

		return _hasShowIconsActionPermission;
	}

	public boolean isShowPublishRecordButton() throws PortalException {
		if (isEditable() && _hasAddRecordPermission()) {
			return true;
		}

		return false;
	}

	public boolean isShowSaveRecordButton() throws PortalException {
		if (isFormView()) {
			return false;
		}

		if (isEditable() && _hasAddRecordPermission()) {
			return true;
		}

		return false;
	}

	public boolean isSpreadsheet() {
		return PrefsParamUtil.getBoolean(
			_ddlRequestHelper.getPortletPreferences(),
			_ddlRequestHelper.getRenderRequest(), "spreadsheet");
	}

	protected List<DropdownItem> getFilterNavigationDropdownItems() {
		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.setActive(true);

				dropdownItem.setHref(getPortletURL(), "navigation", "all");

				dropdownItem.setLabel(
					LanguageUtil.get(_ddlRequestHelper.getRequest(), "all"));
			}
		).build();
	}

	protected String getKeywords() {
		return ParamUtil.getString(_renderRequest, "keywords");
	}

	protected Locale getLocale() {
		return _ddlRequestHelper.getLocale();
	}

	protected UnsafeConsumer<DropdownItem, Exception> getOrderByDropdownItem(
		String orderByCol) {

		return dropdownItem -> {
			dropdownItem.setActive(orderByCol.equals(getOrderByCol()));
			dropdownItem.setHref(getPortletURL(), "orderByCol", orderByCol);
			dropdownItem.setLabel(
				LanguageUtil.get(_ddlRequestHelper.getRequest(), orderByCol));
		};
	}

	protected List<DropdownItem> getOrderByDropdownItems() {
		return DropdownItemListBuilder.add(
			getOrderByDropdownItem("create-date")
		).add(
			getOrderByDropdownItem("modified-date")
		).add(
			getOrderByDropdownItem("name")
		).build();
	}

	protected PermissionChecker getPermissionChecker() {
		return _ddlRequestHelper.getPermissionChecker();
	}

	protected String getPortletName() {
		return _ddlRequestHelper.getPortletName();
	}

	protected ThemeDisplay getThemeDisplay() {
		return _ddlRequestHelper.getThemeDisplay();
	}

	protected boolean hasResults() {
		if (getTotalItems() > 0) {
			return true;
		}

		return false;
	}

	protected boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	private DDMTemplate _fetchDisplayDDMTemplate() {
		if (_displayDDMTemplate != null) {
			return _displayDDMTemplate;
		}

		_displayDDMTemplate = _ddmTemplateLocalService.fetchDDMTemplate(
			getDisplayDDMTemplateId());

		return _displayDDMTemplate;
	}

	private DDMTemplate _fetchFormDDMTemplate() {
		if (_formDDMTemplate != null) {
			return _formDDMTemplate;
		}

		_formDDMTemplate = _ddmTemplateLocalService.fetchDDMTemplate(
			getFormDDMTemplateId());

		return _formDDMTemplate;
	}

	private DDMDisplay _getDDMDisplay() {
		return _ddmDisplayRegistry.getDDMDisplay(
			DDLPortletKeys.DYNAMIC_DATA_LISTS);
	}

	private Layout _getLayout() {
		return _ddlRequestHelper.getLayout();
	}

	private String _getPortletId() {
		return _ddlRequestHelper.getPortletId();
	}

	private String _getPortletResource() {
		return _ddlRequestHelper.getPortletResource();
	}

	private long _getScopeGroupId() {
		return _ddlRequestHelper.getScopeGroupId();
	}

	private long _getStructureTypeClassNameId() {
		DDMDisplay ddmDisplay = _getDDMDisplay();

		return PortalUtil.getClassNameId(ddmDisplay.getStructureType());
	}

	private boolean _hasAddRecordPermission() throws PortalException {
		if (_hasAddRecordPermission != null) {
			return _hasAddRecordPermission;
		}

		_hasAddRecordPermission = false;

		DDLRecordSet recordSet = getRecordSet();

		if (recordSet != null) {
			_hasAddRecordPermission = DDLRecordSetPermission.contains(
				getPermissionChecker(), recordSet, DDLActionKeys.ADD_RECORD);
		}

		return _hasAddRecordPermission;
	}

	private boolean _hasViewPermission() throws PortalException {
		if (_hasViewPermission != null) {
			return _hasViewPermission;
		}

		_hasViewPermission = true;

		if (_recordSet != null) {
			_hasViewPermission = DDLRecordSetPermission.contains(
				getPermissionChecker(), _recordSet, ActionKeys.VIEW);
		}

		return _hasViewPermission;
	}

	private boolean _isShowAddDDMTemplateIcon() throws PortalException {
		if (_hasAddDDMTemplatePermission != null) {
			return _hasAddDDMTemplatePermission;
		}

		_hasAddDDMTemplatePermission = Boolean.FALSE;

		DDLRecordSet recordSet = getRecordSet();

		if (recordSet == null) {
			return _hasAddDDMTemplatePermission;
		}

		_hasAddDDMTemplatePermission =
			_ddmPermissionSupport.containsAddTemplatePermission(
				getPermissionChecker(), _getScopeGroupId(),
				_getStructureTypeClassNameId(), _getStructureTypeClassNameId());

		return _hasAddDDMTemplatePermission;
	}

	private static final String[] _DISPLAY_VIEWS = {"descriptive", "list"};

	private final DDL _ddl;
	private String _ddlRecordDisplayStyle;
	private final DDLRecordSetLocalService _ddlRecordSetLocalService;
	private final DDLRequestHelper _ddlRequestHelper;
	private final DDLWebConfiguration _ddlWebConfiguration;
	private final DDMDisplayRegistry _ddmDisplayRegistry;
	private final DDMPermissionSupport _ddmPermissionSupport;
	private final DDMTemplateLocalService _ddmTemplateLocalService;
	private DDMTemplate _displayDDMTemplate;
	private DDMTemplate _formDDMTemplate;
	private Boolean _hasAddDDMTemplatePermission;
	private Boolean _hasAddRecordPermission;
	private Boolean _hasAddRecordSetPermission;
	private Boolean _hasEditDisplayDDMTemplatePermission;
	private Boolean _hasEditFormDDMTemplatePermission;
	private Boolean _hasShowIconsActionPermission;
	private Boolean _hasViewPermission;
	private String _orderByCol;
	private String _orderByType;
	private DDLRecordSet _recordSet;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private Boolean _showConfigurationIcon;
	private final StorageEngine _storageEngine;

}