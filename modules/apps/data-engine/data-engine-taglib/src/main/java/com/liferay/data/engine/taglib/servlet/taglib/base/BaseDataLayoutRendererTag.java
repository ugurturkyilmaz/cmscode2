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

package com.liferay.data.engine.taglib.servlet.taglib.base;

import com.liferay.data.engine.taglib.internal.servlet.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Jeyvison Nascimento
 * @author Leonardo Barros
 * @generated
 */
public abstract class BaseDataLayoutRendererTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getContainerId() {
		return _containerId;
	}

	public java.lang.Long getDataDefinitionId() {
		return _dataDefinitionId;
	}

	public java.lang.Long getDataLayoutId() {
		return _dataLayoutId;
	}

	public java.lang.Long getDataRecordId() {
		return _dataRecordId;
	}

	public java.util.Map<java.lang.String, java.lang.Object> getDataRecordValues() {
		return _dataRecordValues;
	}

	public java.lang.String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public java.lang.String getLanguageId() {
		return _languageId;
	}

	public java.lang.String getNamespace() {
		return _namespace;
	}

	public boolean getPersistDefaultValues() {
		return _persistDefaultValues;
	}

	public boolean getPersisted() {
		return _persisted;
	}

	public boolean getReadOnly() {
		return _readOnly;
	}

	public boolean getSubmittable() {
		return _submittable;
	}

	public void setContainerId(java.lang.String containerId) {
		_containerId = containerId;
	}

	public void setDataDefinitionId(java.lang.Long dataDefinitionId) {
		_dataDefinitionId = dataDefinitionId;
	}

	public void setDataLayoutId(java.lang.Long dataLayoutId) {
		_dataLayoutId = dataLayoutId;
	}

	public void setDataRecordId(java.lang.Long dataRecordId) {
		_dataRecordId = dataRecordId;
	}

	public void setDataRecordValues(java.util.Map<java.lang.String, java.lang.Object> dataRecordValues) {
		_dataRecordValues = dataRecordValues;
	}

	public void setDefaultLanguageId(java.lang.String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	public void setLanguageId(java.lang.String languageId) {
		_languageId = languageId;
	}

	public void setNamespace(java.lang.String namespace) {
		_namespace = namespace;
	}

	public void setPersistDefaultValues(boolean persistDefaultValues) {
		_persistDefaultValues = persistDefaultValues;
	}

	public void setPersisted(boolean persisted) {
		_persisted = persisted;
	}

	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;
	}

	public void setSubmittable(boolean submittable) {
		_submittable = submittable;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_containerId = null;
		_dataDefinitionId = null;
		_dataLayoutId = null;
		_dataRecordId = null;
		_dataRecordValues = null;
		_defaultLanguageId = null;
		_languageId = null;
		_namespace = null;
		_persistDefaultValues = false;
		_persisted = false;
		_readOnly = false;
		_submittable = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "containerId", _containerId);
		setNamespacedAttribute(request, "dataDefinitionId", _dataDefinitionId);
		setNamespacedAttribute(request, "dataLayoutId", _dataLayoutId);
		setNamespacedAttribute(request, "dataRecordId", _dataRecordId);
		setNamespacedAttribute(request, "dataRecordValues", _dataRecordValues);
		setNamespacedAttribute(request, "defaultLanguageId", _defaultLanguageId);
		setNamespacedAttribute(request, "languageId", _languageId);
		setNamespacedAttribute(request, "namespace", _namespace);
		setNamespacedAttribute(request, "persistDefaultValues", _persistDefaultValues);
		setNamespacedAttribute(request, "persisted", _persisted);
		setNamespacedAttribute(request, "readOnly", _readOnly);
		setNamespacedAttribute(request, "submittable", _submittable);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "liferay-data-engine:data-layout-renderer:";

	private static final String _PAGE =
		"/data_layout_renderer/page.jsp";

	private java.lang.String _containerId = null;
	private java.lang.Long _dataDefinitionId = null;
	private java.lang.Long _dataLayoutId = null;
	private java.lang.Long _dataRecordId = null;
	private java.util.Map<java.lang.String, java.lang.Object> _dataRecordValues = null;
	private java.lang.String _defaultLanguageId = null;
	private java.lang.String _languageId = null;
	private java.lang.String _namespace = null;
	private boolean _persistDefaultValues = false;
	private boolean _persisted = false;
	private boolean _readOnly = false;
	private boolean _submittable = true;

}