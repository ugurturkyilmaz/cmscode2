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

package com.liferay.frontend.taglib.clay.servlet.taglib;

import com.liferay.frontend.taglib.clay.internal.servlet.taglib.BaseContainerTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.TagResourceBundleUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Kresimir Coko
 */
public class SelectTag extends BaseContainerTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		if (Validator.isNotNull(getLabel())) {
			setDynamicAttribute(StringPool.BLANK, "aria-label", getLabel());
		}

		return super.doStartTag();
	}

	public String getContainerCssClass() {
		return _containerCssClass;
	}

	public String getLabel() {
		return LanguageUtil.get(
			TagResourceBundleUtil.getResourceBundle(pageContext), _label);
	}

	public String getName() {
		if (isUseNamespace()) {
			return getNamespace() + _name;
		}

		return _name;
	}

	public List<SelectOption> getOptions() {
		return _options;
	}

	public boolean isDisabled() {
		return _disabled;
	}

	public boolean isMultiple() {
		return _multiple;
	}

	public boolean isUseNamespace() {
		return _useNamespace;
	}

	public void setContainerCssClass(String containerCssClass) {
		_containerCssClass = containerCssClass;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setMultiple(boolean multiple) {
		_multiple = multiple;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOptions(List<SelectOption> options) {
		_options = options;
	}

	public void setUseNamespace(boolean useNamespace) {
		_useNamespace = useNamespace;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_containerCssClass = null;
		_disabled = false;
		_label = null;
		_multiple = false;
		_name = null;
		_options = null;
		_useNamespace = true;
	}

	@Override
	protected String getHydratedModuleName() {
		if ((!_disabled && (getAdditionalProps() != null)) ||
			(getPropsTransformer() != null)) {

			return "frontend-taglib-clay/Select";
		}

		return null;
	}

	@Override
	protected Map<String, Object> prepareProps(Map<String, Object> props) {
		props.put("containerCssClass", _containerCssClass);
		props.put("disabled", _disabled);

		if (Validator.isNotNull(_label)) {
			props.put("label", getLabel());
		}

		props.put("multiple", _multiple);
		props.put("name", getName());
		props.put("options", _options);

		return super.prepareProps(props);
	}

	@Override
	protected String processCssClasses(Set<String> cssClasses) {
		cssClasses.add("form-control");

		return super.processCssClasses(cssClasses);
	}

	@Override
	protected int processStartTag() throws Exception {
		super.processStartTag();

		JspWriter jspWriter = pageContext.getOut();

		if (Validator.isNotNull(_label)) {
			jspWriter.write("<label>");
			jspWriter.write(getLabel());
			jspWriter.write("</label>");
		}

		jspWriter.write("<select ");

		super.writeDynamicAttributes();

		super.writeCssClassAttribute();

		if (_disabled) {
			jspWriter.write(" disabled");
		}

		if (Validator.isNotNull(getId())) {
			super.writeIdAttribute();
		}

		if (_multiple) {
			jspWriter.write(" multiple");
		}

		if (Validator.isNotNull(_name)) {
			jspWriter.write(" name=\"");
			jspWriter.write(getName());
			jspWriter.write("\"");
		}

		jspWriter.write(">");

		for (SelectOption option : _options) {
			jspWriter.write("<option value=\"");
			jspWriter.write(option.getValue());
			jspWriter.write("\"");

			if (option.isSelected()) {
				jspWriter.write(" selected");
			}

			jspWriter.write(">");
			jspWriter.write(option.getLabel());
			jspWriter.write("</option>");
		}

		jspWriter.write("</select>");

		return SKIP_BODY;
	}

	@Override
	protected void writeCssClassAttribute() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write(" class=\"form-group ");

		if (Validator.isNotNull(_containerCssClass)) {
			jspWriter.write(_containerCssClass);
		}

		jspWriter.write("\"");
	}

	@Override
	protected void writeDynamicAttributes() {
	}

	@Override
	protected void writeIdAttribute() {
	}

	private static final String _ATTRIBUTE_NAMESPACE = "clay:select:";

	private String _containerCssClass;
	private boolean _disabled;
	private String _label;
	private boolean _multiple;
	private String _name;
	private List<SelectOption> _options;
	private boolean _useNamespace = true;

}