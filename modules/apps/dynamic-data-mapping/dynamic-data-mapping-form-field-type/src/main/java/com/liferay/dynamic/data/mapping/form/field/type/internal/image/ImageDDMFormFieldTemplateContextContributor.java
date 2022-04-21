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

package com.liferay.dynamic.data.mapping.form.field.type.internal.image;

import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.form.field.type.image.ImageDDMFormFieldItemSelectorCriterionContributor;
import com.liferay.dynamic.data.mapping.form.field.type.internal.util.DDMFormFieldTypeUtil;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.DownloadFileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Carlos Lancha
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=image",
	service = {
		DDMFormFieldTemplateContextContributor.class,
		ImageDDMFormFieldTemplateContextContributor.class
	}
)
public class ImageDDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		return HashMapBuilder.<String, Object>put(
			"itemSelectorURL",
			_getItemSelectorURL(
				ddmFormFieldRenderingContext.getHttpServletRequest(),
				ddmFormFieldRenderingContext)
		).put(
			"message",
			_getMessage(
				ddmFormFieldRenderingContext.getLocale(),
				ddmFormFieldRenderingContext.getValue())
		).put(
			"portletNamespace",
			ddmFormFieldRenderingContext.getPortletNamespace()
		).put(
			"predefinedValue",
			DDMFormFieldTypeUtil.getPropertyValue(
				ddmFormField, ddmFormFieldRenderingContext.getLocale(),
				"predefinedValue")
		).put(
			"requiredDescription",
			GetterUtil.getBoolean(
				ddmFormField.getProperty("requiredDescription"), true)
		).put(
			"value",
			getValue(
				DDMFormFieldTypeUtil.getPropertyValue(
					ddmFormFieldRenderingContext, "value"))
		).build();
	}

	protected String getValue(String value) {
		try {
			JSONObject valueJSONObject = _getValueJSONObject(value);

			if ((valueJSONObject == null) || (valueJSONObject.length() <= 0)) {
				return value;
			}

			FileEntry fileEntry = _getFileEntry(valueJSONObject);

			if (fileEntry == null) {
				return value;
			}

			valueJSONObject.put(
				"description", valueJSONObject.getString("alt")
			).put(
				"url",
				_dlURLHelper.getDownloadURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK)
			);

			return valueJSONObject.toString();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return value;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setImageDDMFormFieldItemSelectorCriterionContributor(
		ImageDDMFormFieldItemSelectorCriterionContributor
			imageDDMFormFieldItemSelectorCriterionContributor) {

		_imageDDMFormFieldItemSelectorCriterionContributors.add(
			imageDDMFormFieldItemSelectorCriterionContributor);
	}

	protected void unsetImageDDMFormFieldItemSelectorCriterionContributor(
		ImageDDMFormFieldItemSelectorCriterionContributor
			imageDDMFormFieldItemSelectorCriterionContributor) {

		_imageDDMFormFieldItemSelectorCriterionContributors.remove(
			imageDDMFormFieldItemSelectorCriterionContributor);
	}

	private FileEntry _getFileEntry(JSONObject valueJSONObject) {
		try {
			return _dlAppService.getFileEntryByUuidAndGroupId(
				valueJSONObject.getString("uuid"),
				valueJSONObject.getLong("groupId"));
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to retrieve file entry ", portalException);
			}

			return null;
		}
	}

	private String _getItemSelectorURL(
		HttpServletRequest httpServletRequest,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		if (_itemSelector == null) {
			return null;
		}

		List<ItemSelectorCriterion> itemSelectorCriteria = new ArrayList<>();

		ImageItemSelectorCriterion imageItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new DownloadFileEntryItemSelectorReturnType());

		itemSelectorCriteria.add(imageItemSelectorCriterion);

		for (ImageDDMFormFieldItemSelectorCriterionContributor
				imageDDMFormFieldItemSelectorCriterionContributor :
					_imageDDMFormFieldItemSelectorCriterionContributors) {

			if (!imageDDMFormFieldItemSelectorCriterionContributor.isVisible(
					ddmFormFieldRenderingContext)) {

				continue;
			}

			itemSelectorCriteria.add(
				imageDDMFormFieldItemSelectorCriterionContributor.
					getItemSelectorCriterion(ddmFormFieldRenderingContext));
		}

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest),
			ddmFormFieldRenderingContext.getPortletNamespace() +
				"selectDocumentLibrary",
			itemSelectorCriteria.toArray(new ItemSelectorCriterion[0]));

		return itemSelectorURL.toString();
	}

	private String _getMessage(Locale defaultLocale, String value) {
		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		JSONObject valueJSONObject = _getValueJSONObject(value);

		if ((valueJSONObject == null) || (valueJSONObject.length() <= 0) ||
			Validator.isNull(valueJSONObject.getString("uuid")) ||
			Validator.isNull(valueJSONObject.getLong("groupId"))) {

			return StringPool.BLANK;
		}

		FileEntry fileEntry = _getFileEntry(valueJSONObject);

		if (fileEntry == null) {
			return LanguageUtil.get(
				_getResourceBundle(defaultLocale),
				"the-selected-image-was-deleted");
		}

		if (fileEntry.isInTrash()) {
			return LanguageUtil.get(
				_getResourceBundle(defaultLocale),
				"the-selected-image-was-moved-to-the-recycle-bin");
		}

		return StringPool.BLANK;
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		ResourceBundle portalResourceBundle = _portal.getResourceBundle(locale);

		ResourceBundle moduleResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return new AggregateResourceBundle(
			moduleResourceBundle, portalResourceBundle);
	}

	private JSONObject _getValueJSONObject(String value) {
		try {
			return _jsonFactory.createJSONObject(value);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ImageDDMFormFieldTemplateContextContributor.class);

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLURLHelper _dlURLHelper;

	private final List<ImageDDMFormFieldItemSelectorCriterionContributor>
		_imageDDMFormFieldItemSelectorCriterionContributors =
			new CopyOnWriteArrayList<>();

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}