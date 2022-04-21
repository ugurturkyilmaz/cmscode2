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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.servlet.taglib.util.EmptyResultMessageKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class EmptyResultMessageTag extends IncludeTag {

	public List<DropdownItem> getActionDropdownItems() {
		return _actionDropdownItems;
	}

	public Map<String, Object> getAdditionalProps() {
		return _additionalProps;
	}

	public EmptyResultMessageKeys.AnimationType getAnimationType() {
		return _animationType;
	}

	public String getButtonCssClass() {
		return _buttonCssClass;
	}

	public String getButtonPropsTransformer() {
		return _buttonPropsTransformer;
	}

	public String getComponentId() {
		return _componentId;
	}

	public String getDefaultEventHandler() {
		return _defaultEventHandler;
	}

	public String getDescription() {
		return _description;
	}

	public String getElementType() {
		return _elementType;
	}

	public String getPropsTransformer() {
		return _propsTransformer;
	}

	public String getTitle() {
		return _title;
	}

	public void setActionDropdownItems(List<DropdownItem> actionDropdownItems) {
		_actionDropdownItems = actionDropdownItems;
	}

	public void setAdditionalProps(Map<String, Object> additionalProps) {
		_additionalProps = additionalProps;
	}

	public void setAnimationType(
		EmptyResultMessageKeys.AnimationType animationType) {

		_animationType = animationType;
	}

	public void setButtonCssClass(String buttonCssClass) {
		_buttonCssClass = buttonCssClass;
	}

	public void setButtonPropsTransformer(String buttonPropsTransformer) {
		_buttonPropsTransformer = buttonPropsTransformer;
	}

	public void setComponentId(String componentId) {
		_componentId = componentId;
	}

	public void setDefaultEventHandler(String defaultEventHandler) {
		_defaultEventHandler = defaultEventHandler;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setElementType(String elementType) {
		_elementType = elementType;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	public void setPropsTransformer(String propsTransformer) {
		_propsTransformer = propsTransformer;
	}

	public void setPropsTransformerServletContext(
		ServletContext propsTransformerServletContext) {

		_propsTransformerServletContext = propsTransformerServletContext;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_actionDropdownItems = null;
		_additionalProps = null;
		_animationType = EmptyResultMessageKeys.AnimationType.EMPTY;
		_buttonCssClass = "primary";
		_buttonPropsTransformer = null;
		_componentId = null;
		_defaultEventHandler = null;
		_description = null;
		_elementType = null;
		_propsTransformer = null;
		_propsTransformerServletContext = null;
		_title = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	protected ServletContext getPropsTransformerServletContext() {
		if (_propsTransformerServletContext != null) {
			return _propsTransformerServletContext;
		}

		return getServletContext();
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:actionDropdownItems",
			_actionDropdownItems);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:additionalProps",
			_additionalProps);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:animationTypeCssClass",
			EmptyResultMessageKeys.getAnimationTypeCssClass(_animationType));
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:buttonCssClass",
			_buttonCssClass);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:buttonPropsTransformer",
			_buttonPropsTransformer);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:componentId", _componentId);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:defaultEventHandler",
			_defaultEventHandler);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:description", _description);

		if (Validator.isNull(_elementType)) {
			_elementType = LanguageUtil.get(httpServletRequest, "element");
		}

		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:elementType", _elementType);

		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:propsTransformer",
			_propsTransformer);
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:" +
				"propsTransformerServletContext",
			getPropsTransformerServletContext());
		httpServletRequest.setAttribute(
			"liferay-frontend:empty-result-message:title", _title);
	}

	private static final String _PAGE = "/empty_result_message/page.jsp";

	private List<DropdownItem> _actionDropdownItems;
	private Map<String, Object> _additionalProps;
	private EmptyResultMessageKeys.AnimationType _animationType =
		EmptyResultMessageKeys.AnimationType.EMPTY;
	private String _buttonCssClass = "primary";
	private String _buttonPropsTransformer;
	private String _componentId;
	private String _defaultEventHandler;
	private String _description;
	private String _elementType;
	private String _propsTransformer;
	private ServletContext _propsTransformerServletContext;
	private String _title;

}