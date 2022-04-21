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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.source.formatter.util.SourceFormatterUtil;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Hugo Huijser
 */
public class UpgradeDeprecatedAPICheck extends DeprecatedAPICheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.CLASS_DEF, TokenTypes.ENUM_DEF, TokenTypes.INTERFACE_DEF
		};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		DetailAST parentDetailAST = detailAST.getParent();

		if ((parentDetailAST != null) ||
			AnnotationUtil.containsAnnotation(detailAST, "Deprecated")) {

			return;
		}

		String upgradeFromVersion = getAttributeValue(
			SourceFormatterUtil.UPGRADE_FROM_VERSION);
		String upgradeToVersion = getAttributeValue(
			SourceFormatterUtil.UPGRADE_TO_VERSION);

		try {
			JSONObject upgradeFromJavaClassesJSONObject =
				getJavaClassesJSONObject(upgradeFromVersion);
			JSONObject upgradeToJavaClassesJSONObject =
				getJavaClassesJSONObject(upgradeToVersion);

			List<String> upgradeFromDeprecatedImportNames =
				getDeprecatedImportNames(
					detailAST, upgradeFromJavaClassesJSONObject);
			List<String> upgradeToDeprecatedImportNames =
				getDeprecatedImportNames(
					detailAST, upgradeToJavaClassesJSONObject);

			for (String upgradeToDeprecatedImportName :
					upgradeToDeprecatedImportNames) {

				if (!upgradeFromDeprecatedImportNames.contains(
						upgradeToDeprecatedImportName) &&
					hasUndeprecatedReference(
						detailAST, upgradeToDeprecatedImportName)) {

					log(
						getImportLineNumber(
							detailAST, upgradeToDeprecatedImportName),
						_MSG_DEPRECATED_TYPE_CALL,
						upgradeToDeprecatedImportName, upgradeToVersion);
				}
			}

			_checkDeprecatedConstructors(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject,
				upgradeToDeprecatedImportNames, upgradeToJavaClassesJSONObject,
				upgradeToVersion);
			_checkDeprecatedMethods(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject,
				upgradeToDeprecatedImportNames, upgradeToJavaClassesJSONObject,
				upgradeToVersion);
			_checkDeprecatedTypes(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject,
				upgradeToDeprecatedImportNames, upgradeToJavaClassesJSONObject,
				upgradeToVersion);
			_checkDeprecatedVariables(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject,
				upgradeToDeprecatedImportNames, upgradeToJavaClassesJSONObject,
				upgradeToVersion);
		}
		catch (Exception exception) {
		}
	}

	private void _checkDeprecatedConstructors(
		DetailAST detailAST, List<String> upgradeFromDeprecatedImportNames,
		JSONObject upgradeFromJavaClassesJSONObject,
		List<String> upgradeToDeprecatedImportNames,
		JSONObject upgradeToJavaClassesJSONObject, String upgradeToVersion) {

		List<ConstructorCall> upgradeFromDeprecatedConstructorCalls =
			getDeprecatedConstructorCalls(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject);
		List<ConstructorCall> upgradeToDeprecatedConstructorCalls =
			getDeprecatedConstructorCalls(
				detailAST, upgradeToDeprecatedImportNames,
				upgradeToJavaClassesJSONObject);

		for (ConstructorCall upgradeToDeprecatedConstructorCall :
				upgradeToDeprecatedConstructorCalls) {

			if (!upgradeFromDeprecatedConstructorCalls.contains(
					upgradeToDeprecatedConstructorCall)) {

				log(
					upgradeToDeprecatedConstructorCall.getLineNumber(),
					_MSG_DEPRECATED_CONSTRUCTOR_CALL,
					upgradeToDeprecatedConstructorCall.getTypeName(),
					upgradeToVersion);
			}
		}
	}

	private void _checkDeprecatedMethods(
		DetailAST detailAST, List<String> upgradeFromDeprecatedImportNames,
		JSONObject upgradeFromJavaClassesJSONObject,
		List<String> upgradeToDeprecatedImportNames,
		JSONObject upgradeToJavaClassesJSONObject, String upgradeToVersion) {

		List<MethodCall> upgradeFromDeprecatedMethodCalls =
			getDeprecatedMethodCalls(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject);
		List<MethodCall> upgradeToDeprecatedMethodCalls =
			getDeprecatedMethodCalls(
				detailAST, upgradeToDeprecatedImportNames,
				upgradeToJavaClassesJSONObject);

		for (MethodCall upgradeToDeprecatedMethodCall :
				upgradeToDeprecatedMethodCalls) {

			if (!upgradeFromDeprecatedMethodCalls.contains(
					upgradeToDeprecatedMethodCall)) {

				log(
					upgradeToDeprecatedMethodCall.getLineNumber(),
					_MSG_DEPRECATED_METHOD_CALL,
					upgradeToDeprecatedMethodCall.getName(), upgradeToVersion);
			}
		}
	}

	private void _checkDeprecatedTypes(
		DetailAST detailAST, List<String> upgradeFromDeprecatedImportNames,
		JSONObject upgradeFromJavaClassesJSONObject,
		List<String> upgradeToDeprecatedImportNames,
		JSONObject upgradeToJavaClassesJSONObject, String upgradeToVersion) {

		Map<String, Set<Integer>> upgradeFromDeprecatedTypeNamesMap =
			getDeprecatedTypeNamesMap(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject);
		Map<String, Set<Integer>> upgradeToDeprecatedTypeNamesMap =
			getDeprecatedTypeNamesMap(
				detailAST, upgradeToDeprecatedImportNames,
				upgradeToJavaClassesJSONObject);

		for (Map.Entry<String, Set<Integer>> entry :
				upgradeToDeprecatedTypeNamesMap.entrySet()) {

			String upgradeToTypeName = entry.getKey();

			if (!upgradeFromDeprecatedTypeNamesMap.containsKey(
					upgradeToTypeName)) {

				continue;
			}

			Set<Integer> upgradeToLineNumbers = entry.getValue();

			for (int upgradeToLineNumber : upgradeToLineNumbers) {
				log(
					upgradeToLineNumber, _MSG_DEPRECATED_TYPE_CALL,
					upgradeToTypeName, upgradeToVersion);
			}
		}
	}

	private void _checkDeprecatedVariables(
		DetailAST detailAST, List<String> upgradeFromDeprecatedImportNames,
		JSONObject upgradeFromJavaClassesJSONObject,
		List<String> upgradeToDeprecatedImportNames,
		JSONObject upgradeToJavaClassesJSONObject, String upgradeToVersion) {

		List<VariableCall> upgradeFromDeprecatedVariableCalls =
			getDeprecatedVariableCalls(
				detailAST, upgradeFromDeprecatedImportNames,
				upgradeFromJavaClassesJSONObject);
		List<VariableCall> upgradeToDeprecatedVariableCalls =
			getDeprecatedVariableCalls(
				detailAST, upgradeToDeprecatedImportNames,
				upgradeToJavaClassesJSONObject);

		for (VariableCall upgradeToDeprecatedVariableCall :
				upgradeToDeprecatedVariableCalls) {

			if (!upgradeFromDeprecatedVariableCalls.contains(
					upgradeToDeprecatedVariableCall)) {

				log(
					upgradeToDeprecatedVariableCall.getLineNumber(),
					_MSG_DEPRECATED_FIELD_CALL,
					upgradeToDeprecatedVariableCall.getName(),
					upgradeToVersion);
			}
		}
	}

	private static final String _MSG_DEPRECATED_CONSTRUCTOR_CALL =
		"constructor.call.deprecated";

	private static final String _MSG_DEPRECATED_FIELD_CALL =
		"field.call.deprecated";

	private static final String _MSG_DEPRECATED_METHOD_CALL =
		"method.call.deprecated";

	private static final String _MSG_DEPRECATED_TYPE_CALL =
		"type.call.deprecated";

}