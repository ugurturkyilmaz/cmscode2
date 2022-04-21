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

package com.liferay.source.formatter.checkstyle.check;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.check.util.SourceUtil;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

import java.io.File;

import java.util.List;
import java.util.Objects;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public class SystemEventCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.CLASS_DEF, TokenTypes.ENUM_DEF, TokenTypes.INTERFACE_DEF
		};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		_entityElement = null;

		DetailAST parentDetailAST = detailAST.getParent();

		if (parentDetailAST != null) {
			return;
		}

		if (detailAST.getType() == TokenTypes.CLASS_DEF) {
			_checkSystemEventConstants(detailAST);
		}
	}

	private void _checkSystemEventConstants(DetailAST detailAST) {
		if (AnnotationUtil.containsAnnotation(detailAST, "Deprecated")) {
			return;
		}

		String className = getName(detailAST);

		if (!className.endsWith("LocalServiceImpl")) {
			return;
		}

		String entityName = StringUtil.removeSubstring(
			className, "LocalServiceImpl");

		DetailAST objBlockDetailAST = detailAST.findFirstToken(
			TokenTypes.OBJBLOCK);

		List<DetailAST> methodDefDetailASTList = getAllChildTokens(
			objBlockDetailAST, false, TokenTypes.METHOD_DEF);

		for (DetailAST methodDefDetailAST : methodDefDetailASTList) {
			DetailAST modifiersDetailAST = methodDefDetailAST.findFirstToken(
				TokenTypes.MODIFIERS);

			if (!modifiersDetailAST.branchContains(TokenTypes.LITERAL_PUBLIC) ||
				AnnotationUtil.containsAnnotation(
					methodDefDetailAST, "Deprecated")) {

				continue;
			}

			String methodName = getName(methodDefDetailAST);

			if (_hasSystemDeleteEventAnnotation(methodDefDetailAST)) {
				if (!entityName.equals(
						_getFirstParameterTypeName(methodDefDetailAST))) {

					log(
						methodDefDetailAST, _MSG_UNNEEDED_SYSTEM_EVENT_1,
						methodName, entityName);
				}

				Element entityElement = _getEntityElement(entityName);

				if (entityElement == null) {
					return;
				}

				if (!_implementsClassedModel(entityElement)) {
					log(
						methodDefDetailAST, _MSG_UNNEEDED_SYSTEM_EVENT_2,
						methodName, entityName);
				}

				continue;
			}

			if (!methodName.startsWith("delete") ||
				!_hasDeleteFromPersistence(
					methodDefDetailASTList, methodDefDetailAST, entityName,
					methodName) ||
				!entityName.equals(
					_getFirstParameterTypeName(methodDefDetailAST))) {

				continue;
			}

			Element entityElement = _getEntityElement(entityName);

			if (entityElement == null) {
				return;
			}

			if (_implementsClassedModel(entityElement)) {
				log(methodDefDetailAST, _MSG_MISSING_SYSTEM_EVENT, methodName);
			}
		}
	}

	private DetailAST _getCalledMethodDefDetailAST(
		DetailAST methodDefDetailAST, String methodName,
		List<DetailAST> methodDefDetailASTList) {

		DetailAST matchingMethodDefDetailAST = null;

		for (DetailAST curMethodDefDetailAST : methodDefDetailASTList) {
			if (methodDefDetailAST.getLineNo() ==
					curMethodDefDetailAST.getLineNo()) {

				continue;
			}

			DetailAST identDetailAST = curMethodDefDetailAST.findFirstToken(
				TokenTypes.IDENT);

			if (!methodName.equals(identDetailAST.getText())) {
				continue;
			}

			DetailAST parametersDetailAST =
				curMethodDefDetailAST.findFirstToken(TokenTypes.PARAMETERS);

			List<DetailAST> parameterDefDetailASTList = getAllChildTokens(
				parametersDetailAST, false, TokenTypes.PARAMETER_DEF);

			if (parameterDefDetailASTList.size() != 1) {
				continue;
			}

			if (matchingMethodDefDetailAST == null) {
				matchingMethodDefDetailAST = curMethodDefDetailAST;
			}
			else {
				return null;
			}
		}

		return matchingMethodDefDetailAST;
	}

	private Element _getEntityElement(String entityName) {
		if (_entityElement != null) {
			return _entityElement;
		}

		String absolutePath = getAbsolutePath();

		File serviceXMLFile = null;

		int pos = absolutePath.length();

		while (true) {
			pos = absolutePath.lastIndexOf("/", pos - 1);

			if (pos == -1) {
				return null;
			}

			serviceXMLFile = new File(
				absolutePath.substring(0, pos + 1) + "service.xml");

			if (serviceXMLFile.exists()) {
				break;
			}
		}

		try {
			Document document = SourceUtil.readXML(serviceXMLFile);

			Element rootElement = document.getRootElement();

			for (Element entityElement :
					(List<Element>)rootElement.elements("entity")) {

				if (entityName.equals(entityElement.attributeValue("name"))) {
					_entityElement = entityElement;

					return _entityElement;
				}
			}
		}
		catch (DocumentException documentException) {
		}

		return null;
	}

	private String _getFirstParameterTypeName(DetailAST methodDefDetailAST) {
		DetailAST parametersDetailAST = methodDefDetailAST.findFirstToken(
			TokenTypes.PARAMETERS);

		List<DetailAST> parameterDefDetailASTList = getAllChildTokens(
			parametersDetailAST, false, TokenTypes.PARAMETER_DEF);

		if (parameterDefDetailASTList.isEmpty()) {
			return null;
		}

		DetailAST firstParameterDefDetailAST = parameterDefDetailASTList.get(0);

		DetailAST typeDetailAST = firstParameterDefDetailAST.findFirstToken(
			TokenTypes.TYPE);

		DetailAST firstChildDetailAST = typeDetailAST.getFirstChild();

		if (firstChildDetailAST.getType() == TokenTypes.IDENT) {
			return firstChildDetailAST.getText();
		}

		return null;
	}

	private String _getPrimaryKeyType(Element entityElement) {
		for (Element columnElement :
				(List<Element>)entityElement.elements("column")) {

			if (GetterUtil.getBoolean(
					columnElement.attributeValue("primary"))) {

				return columnElement.attributeValue("type");
			}
		}

		return null;
	}

	/*
	private boolean _requiresSystemDeleteEventAnnotation(
		DetailAST methodDefDetailAST, String entityName,
		Element entityElement) {

		if (!entityName.equals(
				_getFirstParameterTypeName(methodDefDetailAST))) {

			return false;
		}

		String primaryKeyType = _getPrimaryKeyType(entityElement);

		if (Validator.isNull(primaryKeyType) ||
			!primaryKeyType.equals("long") ||
			!_hasColumn(entityElement, "companyId") ||
			!_hasColumn(entityElement, "createDate") ||
			!_hasColumn(entityElement, "modifiedDate") ||
			!_hasColumn(entityElement, "userId") ||
			!_hasColumn(entityElement, "userName")) {

			return false;
		}

		return true;
	}

	*/

	private boolean _hasColumn(Element entityElement, String name) {
		for (Element columnElement :
				(List<Element>)entityElement.elements("column")) {

			if (name.equals(columnElement.attributeValue("name"))) {
				return true;
			}
		}

		return false;
	}

	private boolean _hasDeleteFromPersistence(
		List<DetailAST> methodDefDetailASTList, DetailAST methodDefDetailAST,
		String entityName, String methodName) {

		if (methodDefDetailAST == null) {
			return false;
		}

		List<DetailAST> methodCallDetailASTList = getAllChildTokens(
			methodDefDetailAST, true, TokenTypes.METHOD_CALL);

		for (DetailAST methodCallDetailAST : methodCallDetailASTList) {
			DetailAST firstChildDetailAST = methodCallDetailAST.getFirstChild();

			if (firstChildDetailAST.getType() == TokenTypes.DOT) {
				FullIdent fullIdent = FullIdent.createFullIdent(
					firstChildDetailAST);

				String methodCall = fullIdent.getText();

				if (methodCall.matches(
						"(?i)" + entityName + "Persistence\\.remove.*") ||
					methodCall.equals("super." + methodName)) {

					return true;
				}
			}
			else if ((firstChildDetailAST.getType() == TokenTypes.IDENT) &&
					 methodName.equals(firstChildDetailAST.getText())) {

				DetailAST elistDetailAST = methodCallDetailAST.findFirstToken(
					TokenTypes.ELIST);

				List<DetailAST> exprDetailASTList = getAllChildTokens(
					elistDetailAST, false, TokenTypes.EXPR);

				if (exprDetailASTList.size() == 1) {
					DetailAST calledMethodDefDetailAST =
						_getCalledMethodDefDetailAST(
							methodDefDetailAST, methodName,
							methodDefDetailASTList);

					if (_hasDeleteFromPersistence(
							methodDefDetailASTList, calledMethodDefDetailAST,
							methodName, entityName)) {

						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean _hasSystemDeleteEventAnnotation(
		DetailAST methodDefDetailAST) {

		DetailAST annotationDetailAST = AnnotationUtil.getAnnotation(
			methodDefDetailAST, "SystemEvent");

		if (annotationDetailAST == null) {
			return false;
		}

		List<DetailAST> annotationMemberValuePairDetailASTList =
			getAllChildTokens(
				annotationDetailAST, false,
				TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR);

		for (DetailAST annotationMemberValuePairDetailAST :
				annotationMemberValuePairDetailASTList) {

			DetailAST identDetailAST =
				annotationMemberValuePairDetailAST.findFirstToken(
					TokenTypes.IDENT);

			if (!Objects.equals(identDetailAST.getText(), "type")) {
				continue;
			}

			DetailAST exprDetailAST =
				annotationMemberValuePairDetailAST.findFirstToken(
					TokenTypes.EXPR);

			FullIdent fullIdent = FullIdent.createFullIdentBelow(exprDetailAST);

			if (Objects.equals(
					fullIdent.getText(), "SystemEventConstants.TYPE_DELETE")) {

				return true;
			}
		}

		return false;
	}

	private boolean _implementsClassedModel(Element entityElement) {
		String primaryKeyType = _getPrimaryKeyType(entityElement);

		if (Validator.isNull(primaryKeyType) ||
			!primaryKeyType.equals("long") ||
			!_hasColumn(entityElement, "companyId") ||
			!_hasColumn(entityElement, "createDate") ||
			!_hasColumn(entityElement, "modifiedDate") ||
			!_hasColumn(entityElement, "userId") ||
			!_hasColumn(entityElement, "userName")) {

			return false;
		}

		return true;
	}

	private static final String _MSG_MISSING_SYSTEM_EVENT =
		"system.event.missing";

	private static final String _MSG_UNNEEDED_SYSTEM_EVENT_1 =
		"system.event.unneeded.1";

	private static final String _MSG_UNNEEDED_SYSTEM_EVENT_2 =
		"system.event.unneeded.2";

	private Element _entityElement;

}