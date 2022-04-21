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

package com.liferay.poshi.core.elements;

import com.liferay.poshi.core.script.PoshiScriptParserException;

import java.util.List;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * @author Kenji Heigel
 */
public class ElsePoshiElement extends ThenPoshiElement {

	@Override
	public PoshiElement clone(Element element) {
		if (isElementType(_ELEMENT_NAME, element)) {
			return new ElsePoshiElement(element);
		}

		return null;
	}

	@Override
	public PoshiElement clone(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		if (_isElementType(parentPoshiElement, poshiScript)) {
			return new ElsePoshiElement(parentPoshiElement, poshiScript);
		}

		return null;
	}

	@Override
	public String getPoshiLogDescriptor() {
		return getBlockName();
	}

	@Override
	public String toPoshiScript() {
		return createPoshiScriptBlock(getPoshiNodes());
	}

	protected ElsePoshiElement() {
		super(_ELEMENT_NAME);
	}

	protected ElsePoshiElement(Element element) {
		super("else", element);
	}

	protected ElsePoshiElement(List<Attribute> attributes, List<Node> nodes) {
		super(_ELEMENT_NAME, attributes, nodes);
	}

	protected ElsePoshiElement(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		super("else", parentPoshiElement, poshiScript);
	}

	@Override
	protected String getBlockName() {
		return "else";
	}

	protected static final Pattern blockNamePattern;

	private boolean _isElementType(
		PoshiElement parentPoshiElement, String poshiScript) {

		if (!(parentPoshiElement instanceof IfPoshiElement)) {
			return false;
		}

		return isValidPoshiScriptBlock(blockNamePattern, poshiScript);
	}

	private static final String _ELEMENT_NAME = "else";

	private static final String _POSHI_SCRIPT_KEYWORD;

	static {
		_POSHI_SCRIPT_KEYWORD = _ELEMENT_NAME;

		blockNamePattern = Pattern.compile("^" + _POSHI_SCRIPT_KEYWORD + "$");
	}

}