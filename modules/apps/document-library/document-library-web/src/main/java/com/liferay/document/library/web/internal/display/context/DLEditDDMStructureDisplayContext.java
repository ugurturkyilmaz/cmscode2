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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cristina González
 */
public class DLEditDDMStructureDisplayContext {

	public DLEditDDMStructureDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;
	}

	public List<Map<String, Object>> getAdditionalPanels(
		String npmResolvedPackageName) {

		return ListUtil.fromArray(
			HashMapBuilder.<String, Object>put(
				"icon", "cog"
			).put(
				"label", LanguageUtil.get(_httpServletRequest, "properties")
			).put(
				"pluginEntryPoint",
				npmResolvedPackageName +
					"/document_library/js/data-engine/panels/index.es"
			).put(
				"sidebarPanelId", "properties"
			).put(
				"url",
				() -> PortletURLBuilder.createRenderURL(
					_liferayPortletResponse
				).setMVCPath(
					"/document_library/ddm/basic_info_data_engine_editor.jsp"
				).setParameter(
					"ddmStructureId", getDDMStructureId()
				).setWindowState(
					LiferayWindowState.EXCLUSIVE
				).buildString()
			).build());
	}

	public Map<String, Object> getComponentContext() {
		return HashMapBuilder.<String, Object>put(
			"contentTitle", "name"
		).put(
			"defaultLanguageId", getDefaultLanguageId()
		).build();
	}

	public DDMStructure getDDMStructure() {
		if (_ddmStructure != null) {
			return _ddmStructure;
		}

		_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			getDDMStructureId());

		return _ddmStructure;
	}

	public long getDDMStructureId() {
		if (_ddmStructureId != null) {
			return _ddmStructureId;
		}

		_ddmStructureId = ParamUtil.getLong(
			_httpServletRequest, "ddmStructureId");

		return _ddmStructureId;
	}

	public String getDefaultLanguageId() {
		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure == null) {
			return LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault());
		}

		return ddmStructure.getDefaultLanguageId();
	}

	public String getFields() throws PortalException {
		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure == null) {
			return StringPool.BLANK;
		}

		JSONArray fieldsJSONArray = DDMUtil.getDDMFormFieldsJSONArray(
			ddmStructure.getLatestStructureVersion(), getScript());

		if (fieldsJSONArray != null) {
			return fieldsJSONArray.toString();
		}

		return StringPool.BLANK;
	}

	public long getParentDDMStructureId() {
		if (_parentDDMStructureId != null) {
			return _parentDDMStructureId;
		}

		long defaultParentDDMStructureId =
			DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID;

		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure != null) {
			defaultParentDDMStructureId = ddmStructure.getParentStructureId();
		}

		_parentDDMStructureId = ParamUtil.getLong(
			_httpServletRequest, "parentDDMStructureId",
			defaultParentDDMStructureId);

		return _parentDDMStructureId;
	}

	public String getParentDDMStructureName() {
		if (_parentDDMStructureName != null) {
			return _parentDDMStructureName;
		}

		DDMStructure parentDDMStructure =
			DDMStructureLocalServiceUtil.fetchStructure(
				getParentDDMStructureId());

		if (parentDDMStructure != null) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_parentDDMStructureName = parentDDMStructure.getName(
				themeDisplay.getLocale());
		}

		return _parentDDMStructureName;
	}

	public String getScript() throws PortalException {
		if (_script != null) {
			return _script;
		}

		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure != null) {
			_script = BeanParamUtil.getString(
				ddmStructure.getLatestStructureVersion(), _httpServletRequest,
				"definition");
		}
		else {
			_script = ParamUtil.getString(_httpServletRequest, "definition");
		}

		return _script;
	}

	public String getSelectedLanguageId() {
		if (Validator.isNotNull(_defaultLanguageId)) {
			return _defaultLanguageId;
		}

		_defaultLanguageId = ParamUtil.getString(
			_httpServletRequest, "languageId");

		if (Validator.isNotNull(_defaultLanguageId)) {
			return _defaultLanguageId;
		}

		_defaultLanguageId = getDefaultLanguageId();

		return _defaultLanguageId;
	}

	private DDMStructure _ddmStructure;
	private Long _ddmStructureId;
	private String _defaultLanguageId;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private Long _parentDDMStructureId;
	private String _parentDDMStructureName;
	private String _script;

}