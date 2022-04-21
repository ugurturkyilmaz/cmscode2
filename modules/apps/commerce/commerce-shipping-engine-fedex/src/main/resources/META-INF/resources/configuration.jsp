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

<%
FedExCommerceShippingEngineGroupServiceConfiguration fedExCommerceShippingEngineGroupServiceConfiguration = (FedExCommerceShippingEngineGroupServiceConfiguration)request.getAttribute(FedExCommerceShippingEngineGroupServiceConfiguration.class.getName());

long commerceChannelId = ParamUtil.getLong(request, "commerceChannelId");

String[] serviceTypes = StringUtil.split(fedExCommerceShippingEngineGroupServiceConfiguration.serviceTypes());
%>

<portlet:actionURL name="/commerce_shipping_methods/edit_commerce_shipping_method_fedex_configuration" var="editCommerceShippingMethodConfigurationActionURL" />

<aui:form action="<%= editCommerceShippingMethodConfigurationActionURL %>" method="post" name="fm">
	<aui:input name="commerceChannelId" type="hidden" value="<%= commerceChannelId %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<commerce-ui:panel>
		<aui:input name="settings--url--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.url() %>" />

		<aui:input name="settings--key--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.key() %>" />

		<aui:input name="settings--password--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.password() %>" />

		<aui:input label="account-number" name="settings--accountNumber--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.accountNumber() %>" />

		<aui:input label="meter-number" name="settings--meterNumber--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.meterNumber() %>" />

		<aui:select label="dropoff-type" name="settings--dropoffType--">

			<%
			for (String dropoffType : FedExCommerceShippingEngineConstants.DROPOFF_TYPES) {
			%>

				<aui:option label="<%= FedExCommerceShippingEngineConstants.getDropoffTypeLabel(dropoffType) %>" selected="<%= dropoffType.equals(fedExCommerceShippingEngineGroupServiceConfiguration.dropoffType()) %>" value="<%= dropoffType %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input checked="<%= fedExCommerceShippingEngineGroupServiceConfiguration.useResidentialRates() %>" label="use-residential-rates" name="settings--useResidentialRates--" type="checkbox" />

		<aui:input checked="<%= fedExCommerceShippingEngineGroupServiceConfiguration.useDiscountedRates() %>" label="use-discounted-rates" name="settings--useDiscountedRates--" type="checkbox" />

		<commerce-ui:info-box
			title="service-types"
		>

			<%
			for (String serviceType : FedExCommerceShippingEngineConstants.SERVICE_TYPES) {
			%>

				<div>
					<aui:input checked="<%= ArrayUtil.contains(serviceTypes, serviceType) %>" id='<%= "settings--serviceTypes" + serviceType + "--" %>' label="<%= FedExCommerceShippingEngineConstants.getServiceTypeLabel(serviceType) %>" name="settings--serviceTypes--" type="checkbox" value="<%= serviceType %>" />
				</div>

			<%
			}
			%>

		</commerce-ui:info-box>

		<aui:select label="packing-type" name="settings--packingType--">

			<%
			for (String packingType : FedExCommerceShippingEngineConstants.PACKING_TYPES) {
			%>

				<aui:option label="<%= packingType %>" selected="<%= packingType.equals(fedExCommerceShippingEngineGroupServiceConfiguration.packingType()) %>" value="<%= packingType %>" />

			<%
			}
			%>

		</aui:select>

		<commerce-ui:info-box
			title="max-weight"
		>
			<aui:input label="" name="settings--maxWeightPounds--" suffix="lb" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxWeightPounds() %>" />

			<aui:input label="" name="settings--maxWeightKilograms--" suffix="kg" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxWeightKilograms() %>" />
		</commerce-ui:info-box>

		<commerce-ui:info-box
			title="max-size"
		>
			<aui:input label="" name="settings--maxmaxSizeInches--" suffix="in" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxSizeInches() %>" />

			<aui:input label="" name="settings--maxSizeCentimeters--" suffix="cm" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxSizeCentimeters() %>" />
		</commerce-ui:info-box>
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>