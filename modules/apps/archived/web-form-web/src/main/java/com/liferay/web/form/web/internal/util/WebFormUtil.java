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

package com.liferay.web.form.web.internal.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoRowLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * @author Daniel Weisser
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class WebFormUtil {

	public static ExpandoTable addTable(long companyId, String tableName)
		throws PortalException {

		try {
			ExpandoTableLocalServiceUtil.deleteTable(
				companyId, WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException noSuchTableException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(noSuchTableException);
			}
		}

		return ExpandoTableLocalServiceUtil.addTable(
			companyId, WebFormUtil.class.getName(), tableName);
	}

	public static ExpandoTable checkTable(
			long companyId, String tableName, PortletPreferences preferences)
		throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, WebFormUtil.class.getName(), tableName);
		}
		catch (NoSuchTableException noSuchTableException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(noSuchTableException);
			}

			expandoTable = addTable(companyId, tableName);

			int i = 1;

			String fieldLabel = preferences.getValue(
				"fieldLabel" + i, StringPool.BLANK);
			String fieldType = preferences.getValue(
				"fieldType" + i, StringPool.BLANK);

			while ((i == 1) || Validator.isNotNull(fieldLabel)) {
				if (!StringUtil.equalsIgnoreCase(fieldType, "paragraph")) {
					ExpandoColumnLocalServiceUtil.addColumn(
						expandoTable.getTableId(), fieldLabel,
						ExpandoColumnConstants.STRING);
				}

				i++;

				fieldLabel = preferences.getValue(
					"fieldLabel" + i, StringPool.BLANK);
				fieldType = preferences.getValue(
					"fieldType" + i, StringPool.BLANK);
			}
		}

		return expandoTable;
	}

	public static String getFileName(
		ThemeDisplay themeDisplay, String portletId, String dataRootDir) {

		StringBuffer sb = new StringBuffer(8);

		sb.append(dataRootDir);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(themeDisplay.getScopeGroupId());
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(portletId);
		sb.append(".csv");

		return sb.toString();
	}

	public static String getNewDatabaseTableName(String portletId) {
		long formId = CounterLocalServiceUtil.increment(
			WebFormUtil.class.getName());

		return portletId + StringPool.UNDERLINE + formId;
	}

	public static int getTableRowsCount(long companyId, String tableName) {
		return ExpandoRowLocalServiceUtil.getRowsCount(
			companyId, WebFormUtil.class.getName(), tableName);
	}

	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if ((s == null) || (delimiter == null)) {
			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			s = s.concat(delimiter);
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List<String> nodeValues = new ArrayList<>();

		if (delimiter.equals("\n") || delimiter.equals("\r")) {
			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			}
			catch (IOException ioException) {
				_log.error(ioException);
			}
		}
		else {
			int offset = 0;

			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(new String(s.substring(offset, pos)));

				offset = pos + delimiter.length();

				pos = s.indexOf(delimiter, offset);
			}
		}

		return nodeValues.toArray(new String[0]);
	}

	public static boolean validate(
			String currentFieldValue, Map<String, String> fieldsMap,
			String validationScript)
		throws Exception {

		boolean validationResult = false;

		Context context = Context.enter();

		StringBundler sb = new StringBundler();

		sb.append("currentFieldValue = String('");
		sb.append(HtmlUtil.escapeJS(currentFieldValue));
		sb.append("');\n");

		sb.append("var fieldsMap = {};\n");

		for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {
			sb.append("fieldsMap['");
			sb.append(entry.getKey());
			sb.append("'] = '");

			String value = StringUtil.replace(
				entry.getValue(), new String[] {"\r\n", "\r", "\n"},
				new String[] {"\\n", "\\n", "\\n"});

			sb.append(HtmlUtil.escapeJS(value));

			sb.append("';\n");
		}

		sb.append("function validation(currentFieldValue, fieldsMap) {\n");
		sb.append(validationScript);
		sb.append("}\n");
		sb.append("internalValidationResult = validation(currentFieldValue, ");
		sb.append("fieldsMap);");

		String script = sb.toString();

		try {
			Scriptable scope = context.initStandardObjects();

			Object jsFieldsMap = Context.javaToJS(fieldsMap, scope);

			ScriptableObject.putProperty(scope, "jsFieldsMap", jsFieldsMap);

			context.evaluateString(scope, script, "Validation Script", 1, null);

			Object object = ScriptableObject.getProperty(
				scope, "internalValidationResult");

			if (object instanceof Boolean) {
				validationResult = (Boolean)object;
			}
			else {
				throw new Exception("The script must return a boolean value");
			}
		}
		catch (Exception exception) {
			String msg = StringBundler.concat(
				"The following script has execution errors:\n",
				validationScript, "\n", exception.getMessage());

			_log.error(msg);

			throw new Exception(msg, exception);
		}
		finally {
			Context.exit();
		}

		return validationResult;
	}

	private static final Log _log = LogFactoryUtil.getLog(WebFormUtil.class);

}