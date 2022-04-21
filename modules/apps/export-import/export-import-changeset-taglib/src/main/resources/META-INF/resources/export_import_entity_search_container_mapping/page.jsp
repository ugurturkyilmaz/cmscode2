<%--
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
--%>

<%@ include file="/export_import_entity_search_container_mapping/init.jsp" %>

<div id="<portlet:namespace /><%= searchContainerMappingId %>">

	<%
	List<com.liferay.portal.kernel.dao.search.ResultRow> resultRows = searchContainer.getResultRows();

	for (com.liferay.portal.kernel.dao.search.ResultRow resultRow : resultRows) {
		StagedModel stagedModel = (StagedModel)resultRow.getObject();

		Map<String, Object> data = HashMapBuilder.<String, Object>put(
			"classNameId", ExportImportClassedModelUtil.getClassNameId(stagedModel)
		).put(
			"groupId", BeanPropertiesUtil.getLong(stagedModel, "groupId")
		).put(
			"rowPK", resultRow.getPrimaryKey()
		).put(
			"uuid", stagedModel.getUuid()
		).build();
	%>

		<div <%= HtmlUtil.buildData(data) %>></div>

	<%
	}
	%>

</div>