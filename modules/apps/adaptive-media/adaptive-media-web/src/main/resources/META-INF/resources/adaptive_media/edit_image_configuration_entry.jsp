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

<%@ include file="/adaptive_media/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

boolean configurationEntryEditable = GetterUtil.getBoolean(request.getAttribute(AMWebKeys.CONFIGURATION_ENTRY_EDITABLE));
AMImageConfigurationEntry amImageConfigurationEntry = (AMImageConfigurationEntry)request.getAttribute(AMWebKeys.CONFIGURATION_ENTRY);

boolean automaticUuid = false;

String configurationEntryUuid = ParamUtil.getString(request, "uuid", (amImageConfigurationEntry != null) ? amImageConfigurationEntry.getUUID() : StringPool.BLANK);

if (amImageConfigurationEntry == null) {
	automaticUuid = Validator.isNull(configurationEntryUuid);
}
else {
	automaticUuid = configurationEntryUuid.equals(FriendlyURLNormalizerUtil.normalize(amImageConfigurationEntry.getName()));
}

automaticUuid = ParamUtil.getBoolean(request, "automaticUuid", automaticUuid);

renderResponse.setTitle((amImageConfigurationEntry != null) ? amImageConfigurationEntry.getName() : LanguageUtil.get(request, "new-image-resolution"));
%>

<div class="container-view">
	<div class="sheet sheet-lg">
		<portlet:actionURL name="/adaptive_media/edit_image_configuration_entry" var="editImageConfigurationEntryURL">
			<portlet:param name="mvcRenderCommandName" value="/adaptive_media/edit_image_configuration_entry" />
		</portlet:actionURL>

		<react:component
			module="adaptive_media/js/EditAdaptiveMedia"
			props='<%=
				HashMapBuilder.<String, Object>put(
					"actionUrl", editImageConfigurationEntryURL
				).put(
					"amImageConfigurationEntry", amImageConfigurationEntry
				).put(
					"automaticUuid", automaticUuid
				).put(
					"configurationEntryEditable", configurationEntryEditable
				).put(
					"configurationEntryUuid", configurationEntryUuid
				).put(
					"namespace", liferayPortletResponse.getNamespace()
				).put(
					"redirect", redirect
				).build()
			%>'
		/>
	</div>
</div>