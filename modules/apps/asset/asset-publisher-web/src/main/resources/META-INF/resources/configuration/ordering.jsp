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

<clay:row
	id='<%= liferayPortletResponse.getNamespace() + "ordering" %>'
>
	<clay:col
		md="6"
	>
		<aui:select label="order-by" name="preferences--orderByColumn1--" value="<%= assetPublisherDisplayContext.getOrderByColumn1() %>" wrapperCssClass="field-inline w80">
			<c:if test="<%= assetPublisherDisplayContext.isOrderingByTitleEnabled() %>">
				<aui:option label="title" />
			</c:if>

			<aui:option label="create-date" value="createDate" />
			<aui:option label="modified-date" value="modifiedDate" />
			<aui:option label="publish-date" value="publishDate" />
			<aui:option label="expiration-date" value="expirationDate" />
			<aui:option label="priority" value="priority" />

			<c:if test="<%= !assetPublisherDisplayContext.isSearchWithIndex() %>">
				<aui:option label="view-count" value="viewCount" />
				<aui:option label="ratings" value="ratings" />
			</c:if>
		</aui:select>

		<%
		String orderByType1 = assetPublisherDisplayContext.getOrderByType1();
		%>

		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
			<liferay-ui:icon
				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType1, "DESC") ? "hide icon order-arrow-up-active" : "icon order-arrow-up-active" %>'
				icon="order-arrow"
				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
				markupView="lexicon"
				message="descending"
				url="javascript:;"
			/>

			<liferay-ui:icon
				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType1, "ASC") ? "hide icon order-arrow-down-active" : "icon order-arrow-down-active" %>'
				icon="order-arrow"
				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
				markupView="lexicon"
				message="ascending"
				url="javascript:;"
			/>

			<aui:input cssClass="order-by-type-field" name="preferences--orderByType1--" type="hidden" value="<%= orderByType1 %>" />
		</aui:field-wrapper>
	</clay:col>

	<clay:col
		md="6"
	>

		<%
		String orderByColumn2 = assetPublisherDisplayContext.getOrderByColumn2();
		%>

		<aui:select label="and-then-by" name="preferences--orderByColumn2--" wrapperCssClass="field-inline w80">
			<aui:option label="title" selected='<%= orderByColumn2.equals("title") %>' />
			<aui:option label="create-date" selected='<%= orderByColumn2.equals("createDate") %>' value="createDate" />
			<aui:option label="modified-date" selected='<%= orderByColumn2.equals("modifiedDate") %>' value="modifiedDate" />
			<aui:option label="publish-date" selected='<%= orderByColumn2.equals("publishDate") %>' value="publishDate" />
			<aui:option label="expiration-date" selected='<%= orderByColumn2.equals("expirationDate") %>' value="expirationDate" />
			<aui:option label="priority" selected='<%= orderByColumn2.equals("priority") %>' value="priority" />

			<c:if test="<%= !assetPublisherDisplayContext.isSearchWithIndex() %>">
				<aui:option label="view-count" selected='<%= orderByColumn2.equals("viewCount") %>' value="viewCount" />
				<aui:option label="ratings" selected='<%= orderByColumn2.equals("ratings") %>' value="ratings" />
			</c:if>
		</aui:select>

		<%
		String orderByType2 = assetPublisherDisplayContext.getOrderByType2();
		%>

		<aui:field-wrapper cssClass="field-label-inline order-by-type-container">
			<liferay-ui:icon
				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType2, "DESC") ? "hide icon order-arrow-up-active" : "icon order-arrow-up-active" %>'
				icon="order-arrow"
				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
				markupView="lexicon"
				message="descending"
				url="javascript:;"
			/>

			<liferay-ui:icon
				cssClass='<%= StringUtil.equalsIgnoreCase(orderByType2, "ASC") ? "hide icon order-arrow-down-active" : "icon order-arrow-down-active" %>'
				icon="order-arrow"
				linkCssClass="btn btn-outline-borderless btn-outline-secondary"
				markupView="lexicon"
				message="ascending"
				url="javascript:;"
			/>

			<aui:input cssClass="order-by-type-field" name="preferences--orderByType2--" type="hidden" value="<%= orderByType2 %>" />
		</aui:field-wrapper>
	</clay:col>
</clay:row>

<liferay-frontend:component
	context='<%=
		HashMapBuilder.<String, Object>put(
			"iconCssClass", ".icon"
		).put(
			"orderingContainerId", liferayPortletResponse.getNamespace() + "ordering"
		).build()
	%>'
	module="js/Ordering"
/>