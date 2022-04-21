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

<%@ include file="/init.jsp" %>

<aui:input name="preferences--metadataFields--" type="hidden" />

<%

// Left list

List<KeyValuePair> leftList = new ArrayList<>();

String[] metadataFields = assetPublisherDisplayContext.getMetadataFields();

for (String metadataField : metadataFields) {
	leftList.add(new KeyValuePair(metadataField, LanguageUtil.get(request, metadataField)));
}

// Right list

List<KeyValuePair> rightList = new ArrayList<>();

String[] allMetadataFields = {"author", "categories", "create-date", "expiration-date", "modified-date", "priority", "publish-date", "tags", "view-count"};

for (String metadataField : allMetadataFields) {
	if (!ArrayUtil.contains(metadataFields, metadataField)) {
		rightList.add(new KeyValuePair(metadataField, LanguageUtil.get(request, metadataField)));
	}
}

rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
%>

<liferay-ui:input-move-boxes
	leftBoxName="currentMetadataFields"
	leftList="<%= leftList %>"
	leftTitle="current"
	rightBoxName="availableMetadataFields"
	rightList="<%= rightList %>"
	rightTitle="available"
/>