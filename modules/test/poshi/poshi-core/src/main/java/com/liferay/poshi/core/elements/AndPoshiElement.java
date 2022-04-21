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
public class AndPoshiElement extends PoshiElement {

	@Override
	public PoshiElement clone(Element element) {
		if (isElementType(_ELEMENT_NAME, element)) {
			return new AndPoshiElement(element);
		}

		return null;
	}

	@Override
	public PoshiElement clone(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		if (_isElementType(parentPoshiElement, poshiScript)) {
			return new AndPoshiElement(parentPoshiElement, poshiScript);
		}

		return null;
	}

	@Override
	public void parsePoshiScript(String poshiScript)
		throws PoshiScriptParserException {

		for (String nestedCondition : getNestedConditions(poshiScript, "&&")) {
			nestedCondition = nestedCondition.trim();

			if (nestedCondition.endsWith(")") &&
				nestedCondition.startsWith("(")) {

				nestedCondition = getParentheticalContent(nestedCondition);
			}

			add(PoshiNodeFactory.newPoshiNode(this, nestedCondition));
		}
	}

	@Override
	public String toPoshiScript() {
		StringBuilder sb = new StringBuilder();

		List<PoshiElement> poshiElements = toPoshiElements(elements());

		for (PoshiElement poshiElement : poshiElements) {
			String poshiScript = poshiElement.toPoshiScript();

			if (poshiScript.startsWith("(") || poshiScript.startsWith("!(") ||
				poshiScript.startsWith("isSet(") ||
				poshiScript.startsWith("contains(")) {

				sb.append(poshiScript);
			}
			else {
				sb.append("(");
				sb.append(poshiScript);
				sb.append(")");
			}

			sb.append(" && ");
		}

		sb.setLength(sb.length() - 4);

		PoshiElement parentPoshiElement = (PoshiElement)getParent();

		if ((poshiElements.size() > 1) &&
			!(parentPoshiElement instanceof NotPoshiElement)) {

			sb.insert(0, "(");
			sb.append(")");
		}

		return sb.toString();
	}

	protected AndPoshiElement() {
		super(_ELEMENT_NAME);
	}

	protected AndPoshiElement(Element element) {
		super(_ELEMENT_NAME, element);
	}

	protected AndPoshiElement(List<Attribute> attributes, List<Node> nodes) {
		super(_ELEMENT_NAME, attributes, nodes);
	}

	protected AndPoshiElement(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		super(_ELEMENT_NAME, parentPoshiElement, poshiScript);
	}

	@Override
	protected String getBlockName() {
		return "and";
	}

	@Override
	protected Pattern getConditionPattern() {
		return _conditionPattern;
	}

	private boolean _isElementType(
		PoshiElement parentPoshiElement, String poshiScript) {

		if (!isConditionElementType(parentPoshiElement, poshiScript)) {
			return false;
		}

		List<String> nestedConditions = getNestedConditions(poshiScript, "&&");

		return !nestedConditions.isEmpty();
	}

	private static final String _ELEMENT_NAME = "and";

	private static final Pattern _conditionPattern = Pattern.compile(
		"^(?!else)[\\s\\S]*&&[\\s\\S]*$");

}