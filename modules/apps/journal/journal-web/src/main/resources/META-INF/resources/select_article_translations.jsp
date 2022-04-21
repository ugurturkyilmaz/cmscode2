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

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new JournalSelectArticleTranslationsManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, journalDisplayContext) %>"
/>

<aui:form cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:search-container
		id="articleTranslations"
		searchContainer="<%= journalDisplayContext.getArticleTranslationsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.journal.web.internal.util.JournalArticleTranslation"
			keyProperty="languageId"
			modelVar="articleTranslation"
		>
			<liferay-ui:search-container-column-text
				name="language"
				truncate="<%= true %>"
			>
				<liferay-ui:icon
					icon="<%= articleTranslation.getLanguageTag() %>"
					label="<%= true %>"
					markupView="lexicon"
					message="<%= HtmlUtil.escape(LocaleUtil.getLongDisplayName(articleTranslation.getLocale(), Collections.emptySet())) %>"
				/>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="default"
			>
				<c:if test="<%= articleTranslation.isDefault() %>">
					<liferay-ui:icon
						icon="check-circle"
						markupView="lexicon"
					/>
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>