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

package com.liferay.portal.search.web.internal.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.FacetFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.category.CategoryFacetFactory;
import com.liferay.portal.search.web.facet.BaseJSPSearchFacet;
import com.liferay.portal.search.web.facet.SearchFacet;

import javax.portlet.ActionRequest;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = SearchFacet.class)
public class AssetCategoriesSearchFacet extends BaseJSPSearchFacet {

	@Override
	public String getConfigurationJspPath() {
		return "/facets/configuration/asset_categories.jsp";
	}

	@Override
	public FacetConfiguration getDefaultConfiguration(long companyId) {
		FacetConfiguration facetConfiguration = new FacetConfiguration();

		facetConfiguration.setClassName(getFacetClassName());

		facetConfiguration.setDataJSONObject(
			JSONUtil.put(
				"displayStyle", "list"
			).put(
				"frequencyThreshold", 1
			).put(
				"maxTerms", 10
			).put(
				"showAssetCount", true
			));

		facetConfiguration.setFieldName(getFieldName());
		facetConfiguration.setLabel(getLabel());
		facetConfiguration.setOrder(getOrder());
		facetConfiguration.setStatic(false);
		facetConfiguration.setWeight(1.3);

		return facetConfiguration;
	}

	@Override
	public String getDisplayJspPath() {
		return "/facets/view/asset_categories.jsp";
	}

	@Override
	public String getFacetClassName() {
		return categoryFacetFactory.getFacetClassName();
	}

	@Override
	public String getFieldName() {
		Facet facet = categoryFacetFactory.newInstance(null);

		return facet.getFieldName();
	}

	@Override
	public JSONObject getJSONData(ActionRequest actionRequest) {
		return JSONUtil.put(
			"displayStyle",
			ParamUtil.getString(
				actionRequest, getClassName() + "displayStyleFacet", "list")
		).put(
			"frequencyThreshold",
			ParamUtil.getInteger(
				actionRequest, getClassName() + "frequencyThreshold", 1)
		).put(
			"maxTerms",
			ParamUtil.getInteger(actionRequest, getClassName() + "maxTerms", 10)
		).put(
			"showAssetCount",
			ParamUtil.getBoolean(
				actionRequest, getClassName() + "showAssetCount", true)
		);
	}

	@Override
	public String getLabel() {
		return "any-category";
	}

	@Override
	public String getTitle() {
		return "category";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.search.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected FacetFactory getFacetFactory() {
		return categoryFacetFactory;
	}

	@Reference
	protected CategoryFacetFactory categoryFacetFactory;

}