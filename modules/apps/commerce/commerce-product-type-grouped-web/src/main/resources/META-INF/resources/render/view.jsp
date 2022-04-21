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
GroupedCPTypeHelper groupedCPTypeHelper = (GroupedCPTypeHelper)request.getAttribute("groupedCPTypeHelper");

CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long commerceAccountId = CommerceUtil.getCommerceAccountId((CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT));
%>

<div class="container-fluid product-detail" id="<portlet:namespace /><%= cpDefinitionId %>ProductContent">
	<div class="row">
		<div class="product-detail-header">
			<div class="col-lg-6 col-md-7">
				<div class="row">
					<div class="col-2 col-lg-2 col-md-3">
						<div id="<portlet:namespace />thumbs-container">

							<%
							for (CPMedia imageCPMedia : cpContentHelper.getImages(cpDefinitionId, themeDisplay)) {
								String thumbnailURL = imageCPMedia.getThumbnailURL();
							%>

								<div class="card thumb" data-url="<%= HtmlUtil.escapeAttribute(thumbnailURL) %>">
									<img class="center-block img-fluid" src="<%= HtmlUtil.escapeAttribute(thumbnailURL) %>" />
								</div>

							<%
							}
							%>

						</div>
					</div>

					<div class="col-10 col-lg-10 col-md-9 full-image">

						<%
						String defaultImageFileURL = cpContentHelper.getDefaultImageFileURL(commerceAccountId, cpCatalogEntry.getCPDefinitionId());
						%>

						<c:if test="<%= Validator.isNotNull(defaultImageFileURL) %>">
							<img class="center-block img-fluid" id="<portlet:namespace />full-image" src="<%= HtmlUtil.escapeAttribute(defaultImageFileURL) %>" />
						</c:if>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-5">
				<h1><%= HtmlUtil.escape(cpCatalogEntry.getName()) %></h1>

				<c:choose>
					<c:when test="<%= cpSku != null %>">
						<h4 class="sku"><%= HtmlUtil.escape(cpSku.getSku()) %></h4>

						<div class="price-container w-50"><commerce-ui:price CPCatalogEntry="<%= cpCatalogEntry %>" namespace="<%= liferayPortletResponse.getNamespace() %>" /></div>

						<div class="subscription-info"><commerce-ui:product-subscription-info CPInstanceId="<%= cpSku.getCPInstanceId() %>" /></div>

						<div class="availability"><%= HtmlUtil.escape(cpContentHelper.getAvailabilityLabel(request)) %></div>

						<div class="availabilityEstimate"><%= HtmlUtil.escape(cpContentHelper.getAvailabilityEstimateLabel(request)) %></div>

						<div class="stockQuantity"><%= HtmlUtil.escape(cpContentHelper.getStockQuantityLabel(request)) %></div>
					</c:when>
					<c:otherwise>
						<h4 class="sku" data-text-cp-instance-sku=""></h4>

						<div class="price-container w-50" data-text-cp-instance-price=""></div>

						<div class="subscription-info" data-text-cp-instance-subscription-info="" data-text-cp-instance-subscription-info-show></div>

						<div class="subscription-info" data-text-cp-instance-delivery-subscription-info="" data-text-cp-instance-delivery-subscription-info-show></div>

						<div class="availability" data-text-cp-instance-availability=""></div>

						<div class="availabilityEstimate" data-text-cp-instance-availability-estimate=""></div>

						<div class="stockQuantity" data-text-cp-instance-stock-quantity=""></div>
					</c:otherwise>
				</c:choose>

				<div class="row">
					<div class="col-md-12">
						<div class="options">
							<%= cpContentHelper.renderOptions(renderRequest, renderResponse) %>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<commerce-ui:compare-checkbox
							CPCatalogEntry="<%= cpCatalogEntry %>"
						/>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<liferay-util:dynamic-include key="com.liferay.commerce.product.content.web#/add_to_cart#" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">

			<%
			for (CPDefinitionGroupedEntry cpDefinitionGroupedEntry : groupedCPTypeHelper.getCPDefinitionGroupedEntry(cpDefinitionId)) {
				CProduct cProduct = cpDefinitionGroupedEntry.getEntryCProduct();

				CPDefinition cProductCPDefinition = CPDefinitionLocalServiceUtil.getCPDefinition(cProduct.getPublishedCPDefinitionId());
			%>

				<div class="row">
					<div class="col-md-4">
						<img class="img-fluid" src="<%= cProductCPDefinition.getDefaultImageThumbnailSrc(commerceAccountId) %>" />
					</div>

					<div class="col-md-8">
						<h5>
							<%= HtmlUtil.escape(cProductCPDefinition.getName(LocaleUtil.toLanguageId(locale))) %>
						</h5>

						<h6>
							<liferay-ui:message arguments="<%= cpDefinitionGroupedEntry.getQuantity() %>" key="quantity-x" />
						</h6>
					</div>
				</div>

			<%
			}
			%>

		</div>
	</div>

	<%
	List<CPDefinitionSpecificationOptionValue> cpDefinitionSpecificationOptionValues = cpContentHelper.getCPDefinitionSpecificationOptionValues(cpDefinitionId);
	List<CPMedia> cpMedias = cpContentHelper.getCPMedias(cpDefinitionId, themeDisplay);
	List<CPOptionCategory> cpOptionCategories = cpContentHelper.getCPOptionCategories(company.getCompanyId());
	%>

	<div class="row">
		<div class="product-detail-body">
			<div class="nav-tabs-centered">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item" role="presentation">
						<a aria-controls="<portlet:namespace />description" aria-expanded="true" class="active nav-link" data-toggle="tab" href="#<portlet:namespace />description" role="tab">
							<%= LanguageUtil.get(resourceBundle, "description") %>
						</a>
					</li>

					<c:if test="<%= cpContentHelper.hasCPDefinitionSpecificationOptionValues(cpDefinitionId) %>">
						<li class="nav-item" role="presentation">
							<a aria-controls="<portlet:namespace />specification" aria-expanded="false" class="nav-link" data-toggle="tab" href="#<portlet:namespace />specification" role="tab">
								<%= LanguageUtil.get(resourceBundle, "specifications") %>
							</a>
						</li>
					</c:if>

					<c:if test="<%= !cpMedias.isEmpty() %>">
						<li class="nav-item" role="presentation">
							<a aria-controls="<portlet:namespace />attachments" aria-expanded="false" class="nav-link" data-toggle="tab" href="#<portlet:namespace />attachments" role="tab">
								<%= LanguageUtil.get(resourceBundle, "attachments") %>
							</a>
						</li>
					</c:if>
				</ul>

				<div class="tab-content">
					<div class="active tab-pane" id="<portlet:namespace />description">
						<p><%= cpCatalogEntry.getDescription() %></p>
					</div>

					<c:if test="<%= cpContentHelper.hasCPDefinitionSpecificationOptionValues(cpDefinitionId) %>">
						<div class="tab-pane" id="<portlet:namespace />specification">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">

									<%
									for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : cpDefinitionSpecificationOptionValues) {
										CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
									%>

										<tr>
											<td><%= HtmlUtil.escape(cpSpecificationOption.getTitle(locale)) %></td>
											<td><%= HtmlUtil.escape(cpDefinitionSpecificationOptionValue.getValue(locale)) %></td>
										</tr>

									<%
									}
									%>

								</table>
							</div>

							<%
							for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
								List<CPDefinitionSpecificationOptionValue> categorizedCPDefinitionSpecificationOptionValues = cpContentHelper.getCategorizedCPDefinitionSpecificationOptionValues(cpDefinitionId, cpOptionCategory.getCPOptionCategoryId());
							%>

								<c:if test="<%= !categorizedCPDefinitionSpecificationOptionValues.isEmpty() %>">
									<div class="table-responsive">
										<table class="table table-bordered table-striped">
											<tr>
												<th><%= HtmlUtil.escape(cpOptionCategory.getTitle(locale)) %></th>
												<th></th>
											</tr>

											<%
											for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : categorizedCPDefinitionSpecificationOptionValues) {
												CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
											%>

												<tr>
													<td><%= HtmlUtil.escape(cpSpecificationOption.getTitle(locale)) %></td>
													<td><%= HtmlUtil.escape(cpDefinitionSpecificationOptionValue.getValue(locale)) %></td>
												</tr>

											<%
											}
											%>

										</table>
									</div>
								</c:if>

							<%
							}
							%>

						</div>
					</c:if>

					<c:if test="<%= !cpMedias.isEmpty() %>">
						<div class="tab-pane" id="<portlet:namespace />attachments">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">

									<%
									for (CPMedia cpMedia : cpMedias) {
									%>

										<tr>
											<td>
												<span><%= HtmlUtil.escape(cpMedia.getTitle()) %></span>

												<span>
													<aui:icon cssClass="icon-monospaced" image="download" markupView="lexicon" url="<%= cpMedia.getDownloadURL() %>" />
												</span>
											</td>
										</tr>

									<%
									}
									%>

								</table>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script>
	window.document.addEventListener('DOMContentLoaded', () => {
		var thumbElements = window.document.querySelectorAll('.thumb');

		Array.from(thumbElements).forEach((thumbElement) => {
			thumbElement.addEventListener('click', (event) => {
				window.document
					.querySelector('#<portlet:namespace />full-image')
					.setAttribute(
						'src',
						event.currentTarget.getAttribute('data-url')
					);
			});
		});
	});
</aui:script>

<liferay-portlet:actionURL name="/cp_content_web/check_cp_instance" portletName="com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet" var="checkCPInstanceURL">
	<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionId) %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>" />
</liferay-portlet:actionURL>

<aui:script use="liferay-commerce-product-content">
	var productContent = new Liferay.Portlet.ProductContent({
		checkCPInstanceActionURL: '<%= HtmlUtil.escapeJS(checkCPInstanceURL) %>',
		cpDefinitionId: <%= cpDefinitionId %>,
		fullImageSelector: '#<portlet:namespace />full-image',
		namespace: '<portlet:namespace />',
		productContentAuthToken:
			'<%= AuthTokenUtil.getToken(request, plid, CPPortletKeys.CP_CONTENT_WEB) %>',
		productContentSelector:
			'#<portlet:namespace /><%= cpDefinitionId %>ProductContent',
		thumbsContainerSelector: '#<portlet:namespace />thumbs-container',
		viewAttachmentURL:
			'<%= HtmlUtil.escapeJS(String.valueOf(cpContentHelper.getViewAttachmentURL(liferayPortletRequest, liferayPortletResponse))) %>',
	});

	Liferay.component(
		'<portlet:namespace /><%= cpDefinitionId %>ProductContent',
		productContent
	);
</aui:script>