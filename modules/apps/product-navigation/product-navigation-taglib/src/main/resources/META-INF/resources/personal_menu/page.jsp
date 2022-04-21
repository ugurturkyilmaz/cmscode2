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

<%@ include file="/personal_menu/init.jsp" %>

<%
String namespace = StringUtil.randomId() + StringPool.UNDERLINE;

long color = (long)request.getAttribute("liferay-product-navigation:personal-menu:color");
String label = (String)request.getAttribute("liferay-product-navigation:personal-menu:label");
String size = (String)request.getAttribute("liferay-product-navigation:personal-menu:size");
User user2 = (User)request.getAttribute("liferay-product-navigation:personal-menu:user");

String userStickerCssClasses = "sticker";

if (size != null) {
	userStickerCssClasses += " sticker-" + size;
}

String impersonateStickerCssClasses = "sticker";

if (size != null) {
	impersonateStickerCssClasses += " sticker-sm";
}
%>

<style type="text/css">
	.dropdown-menu-personal-menu {
		max-height: none;
	}

	.dropdown-menu-personal-menu .dropdown-item-indicator {
		padding-right: 0.5rem;
	}

	div.personal-menu-dropdown .btn {
		border-radius: 5000px;
	}

	div.personal-menu-dropdown .dropdown-item {
		color: #6B6C7E;
	}
</style>

<div class="personal-menu-dropdown" id="<%= namespace %>personal_menu_dropdown">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(label) %>">
			<div><%= label %></div>
		</c:when>
		<c:otherwise>
			<button aria-expanded="true" aria-haspopup="true" class="btn btn-unstyled dropdown-toggle" id="<%= namespace %>personal_menu_dropdown_toggle" ref="triggerButton" type="button">
				<span class="<%= userStickerCssClasses %>">
					<liferay-ui:user-portrait
						cssClass="sticker-user-icon"
						size="<%= size %>"
						user="<%= user2 %>"
					/>

					<c:if test="<%= themeDisplay.isImpersonated() %>">
						<span class="<%= impersonateStickerCssClasses %> sticker-bottom-right sticker-circle sticker-outside sticker-user-icon" id="impersonate-user-sticker">
							<span class="sticker-overlay">
								<aui:icon id="impersonate-user-icon" image="user" markupView="lexicon" />
							</span>
						</span>
					</c:if>
				</span>
			</button>
		</c:otherwise>
	</c:choose>

	<%
	ResourceURL resourceURL = PortletURLFactoryUtil.create(request, PersonalMenuPortletKeys.PERSONAL_MENU, PortletRequest.RESOURCE_PHASE);

	resourceURL.setParameter("currentURL", themeDisplay.getURLCurrent());
	resourceURL.setParameter("portletId", themeDisplay.getPpid());
	resourceURL.setResourceID("/product_navigation_personal_menu/get_personal_menu_items");

	Map<String, Object> props = HashMapBuilder.<String, Object>put(
		"color", color
	).put(
		"isImpersonated", themeDisplay.isImpersonated()
	).put(
		"itemsURL", resourceURL.toString()
	).put(
		"label", label
	).put(
		"size", size
	).build();

	if (user2.getPortraitId() > 0) {
		props.put("userPortraitURL", user2.getPortraitURL(themeDisplay));
	}
	%>

	<react:component
		module="personal_menu/js/PersonalMenu.es"
		props="<%= props %>"
	/>
</div>