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

package com.liferay.source.formatter.check;

import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.source.formatter.util.SourceFormatterUtil;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class NewFileCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		List<String> forbiddenDirNames = getAttributeValues(
			_FORBIDDEN_DIR_NAMES_KEY, absolutePath);

		if (forbiddenDirNames.isEmpty()) {
			return content;
		}

		String portalBranchName = getAttributeValue(
			SourceFormatterUtil.GIT_LIFERAY_PORTAL_BRANCH, absolutePath);

		for (String forbiddenDirName : forbiddenDirNames) {
			int pos = absolutePath.indexOf(forbiddenDirName);

			if (pos == -1) {
				continue;
			}

			URL url = SourceFormatterUtil.getPortalGitURL(
				absolutePath.substring(pos), portalBranchName);

			try {
				HttpURLConnection httpURLConnection =
					(HttpURLConnection)url.openConnection();

				httpURLConnection.setConnectTimeout(5000);
				httpURLConnection.setReadTimeout(5000);
				httpURLConnection.setRequestMethod(HttpMethods.HEAD);

				if (httpURLConnection.getResponseCode() !=
						HttpURLConnection.HTTP_OK) {

					addMessage(
						fileName,
						"Do not add new files to '" + forbiddenDirName + "'");
				}

				httpURLConnection.disconnect();
			}
			catch (Exception exception) {
				addMessage(fileName, exception.getMessage());
			}
		}

		return content;
	}

	private static final String _FORBIDDEN_DIR_NAMES_KEY = "forbiddenDirNames";

}