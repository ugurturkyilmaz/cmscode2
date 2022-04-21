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

package com.liferay.headless.delivery.internal.dto.v1_0.mapper;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.headless.delivery.dto.v1_0.PageElement;
import com.liferay.headless.delivery.internal.dto.v1_0.util.PageWidgetInstanceDefinitionUtil;
import com.liferay.layout.util.structure.FragmentStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = LayoutStructureItemMapper.class)
public class FragmentLayoutStructureItemMapper
	extends BaseStyledLayoutStructureItemMapper {

	@Override
	public String getClassName() {
		return FragmentStyledLayoutStructureItem.class.getName();
	}

	@Override
	public PageElement getPageElement(
		long groupId, LayoutStructureItem layoutStructureItem,
		boolean saveInlineContent, boolean saveMappingConfiguration) {

		FragmentStyledLayoutStructureItem fragmentStyledLayoutStructureItem =
			(FragmentStyledLayoutStructureItem)layoutStructureItem;

		FragmentEntryLink fragmentEntryLink =
			_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
				fragmentStyledLayoutStructureItem.getFragmentEntryLinkId());

		if (fragmentEntryLink == null) {
			return null;
		}

		JSONObject editableValuesJSONObject = null;

		try {
			editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
				fragmentEntryLink.getEditableValues());
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}

			return null;
		}

		String portletId = editableValuesJSONObject.getString("portletId");

		JSONObject itemConfigJSONObject =
			fragmentStyledLayoutStructureItem.getItemConfigJSONObject();

		if (Validator.isNull(portletId)) {
			return new PageElement() {
				{
					definition =
						_pageFragmentInstanceDefinitionMapper.
							getPageFragmentInstanceDefinition(
								fragmentStyledLayoutStructureItem,
								toFragmentStyle(
									itemConfigJSONObject.getJSONObject(
										"styles"),
									saveMappingConfiguration),
								getFragmentViewPorts(itemConfigJSONObject),
								saveInlineContent, saveMappingConfiguration);
					type = Type.FRAGMENT;
				}
			};
		}

		String instanceId = editableValuesJSONObject.getString("instanceId");

		return new PageElement() {
			{
				definition =
					PageWidgetInstanceDefinitionUtil.
						toPageWidgetInstanceDefinition(
							fragmentEntryLink,
							toFragmentStyle(
								itemConfigJSONObject.getJSONObject("styles"),
								saveMappingConfiguration),
							getFragmentViewPorts(
								itemConfigJSONObject.getJSONObject("style")),
							PortletIdCodec.encode(portletId, instanceId),
							_widgetInstanceMapper);
				type = Type.WIDGET;
			}
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentLayoutStructureItemMapper.class);

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private PageFragmentInstanceDefinitionMapper
		_pageFragmentInstanceDefinitionMapper;

	@Reference
	private WidgetInstanceMapper _widgetInstanceMapper;

}