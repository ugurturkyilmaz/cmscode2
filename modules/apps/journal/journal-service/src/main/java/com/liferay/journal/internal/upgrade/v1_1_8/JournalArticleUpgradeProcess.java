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

package com.liferay.journal.internal.upgrade.v1_1_8;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Matthew Chan
 */
public class JournalArticleUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select id_, content from JournalArticle where content like " +
					"?")) {

			preparedStatement1.setString(1, "%type=\"radio\"%");

			ResultSet resultSet1 = preparedStatement1.executeQuery();

			while (resultSet1.next()) {
				long id = resultSet1.getLong("id_");

				String content = resultSet1.getString("content");

				content = _convertRadioDynamicElements(content);

				try (PreparedStatement preparedStatement2 =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update JournalArticle set content = ? where id_ " +
								"= ?")) {

					preparedStatement2.setString(1, content);
					preparedStatement2.setLong(2, id);

					preparedStatement2.executeUpdate();
				}
			}
		}
	}

	private String _convertRadioDynamicElements(String content)
		throws Exception {

		Document contentDocument = SAXReaderUtil.read(content);

		contentDocument = contentDocument.clone();

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='radio']");

		List<Node> nodes = xPath.selectNodes(contentDocument);

		for (Node node : nodes) {
			Element element = (Element)node;

			List<Element> dynamicContentElements = element.elements(
				"dynamic-content");

			for (Element dynamicContentElement : dynamicContentElements) {
				String data = String.valueOf(dynamicContentElement.getData());

				data = _removeUnusedChars(data);

				dynamicContentElement.clearContent();

				dynamicContentElement.addCDATA(data);
			}
		}

		return contentDocument.formattedString();
	}

	private String _removeUnusedChars(String data) {
		if ((data != null) && (data.length() > 3)) {
			int start = 0;
			int end = data.length() - 1;

			if ((data.charAt(start) == '[') && (data.charAt(end) == ']') &&
				(data.charAt(start + 1) == '"') &&
				(data.charAt(end - 1) == '"')) {

				data = data.substring(start + 2, end - 1);
			}
		}

		return data;
	}

}