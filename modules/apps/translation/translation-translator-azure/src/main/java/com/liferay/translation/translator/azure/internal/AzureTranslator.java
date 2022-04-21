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

package com.liferay.translation.translator.azure.internal;

import com.liferay.petra.apache.http.components.URIBuilder;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.translation.translator.Translator;
import com.liferay.translation.translator.TranslatorPacket;
import com.liferay.translation.translator.azure.internal.configuration.AzureTranslatorConfiguration;

import java.io.IOException;

import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	configurationPid = "com.liferay.translation.translator.azure.internal.configuration.AzureTranslatorConfiguration",
	service = Translator.class
)
public class AzureTranslator implements Translator {

	@Override
	public boolean isEnabled(long companyId) throws ConfigurationException {
		return _azureTranslatorConfiguration.enabled();
	}

	@Override
	public TranslatorPacket translate(TranslatorPacket translatorPacket)
		throws PortalException {

		try {
			Http.Options options = new Http.Options();

			options.addHeader(
				"Ocp-Apim-Subscription-Key",
				_azureTranslatorConfiguration.subscriptionKey());
			options.addHeader(
				"Ocp-Apim-Subscription-Region",
				_azureTranslatorConfiguration.resourceLocation());
			options.addHeader(
				HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
			options.setBody(
				_getTranslatorPacketPayload(translatorPacket),
				ContentTypes.APPLICATION_JSON, StringPool.UTF8);
			options.setLocation(
				URIBuilder.create(
					"https://api.cognitive.microsofttranslator.com/translate"
				).addParameter(
					"api-version", "3.0"
				).addParameter(
					"from",
					_getLanguageCode(translatorPacket.getSourceLanguageId())
				).addParameter(
					"to",
					_getLanguageCode(translatorPacket.getTargetLanguageId())
				).build(
				).toString());
			options.setPost(true);

			String json = _http.URLtoString(options);

			Http.Response response = options.getResponse();

			if (response.getResponseCode() != 200) {
				throw new PortalException(
					"Response code " + response.getResponseCode());
			}

			Map<String, String> translatedFieldsMap = _getTranslatedFieldsMap(
				translatorPacket.getFieldsMap(),
				JSONFactoryUtil.createJSONArray(json));

			return new TranslatorPacket() {

				@Override
				public long getCompanyId() {
					return translatorPacket.getCompanyId();
				}

				@Override
				public Map<String, String> getFieldsMap() {
					return translatedFieldsMap;
				}

				@Override
				public String getSourceLanguageId() {
					return translatorPacket.getSourceLanguageId();
				}

				@Override
				public String getTargetLanguageId() {
					return translatorPacket.getTargetLanguageId();
				}

			};
		}
		catch (IOException | URISyntaxException exception) {
			throw new PortalException(exception);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_azureTranslatorConfiguration = ConfigurableUtil.createConfigurable(
			AzureTranslatorConfiguration.class, properties);
	}

	private String _getLanguageCode(String languageId) {
		List<String> list = StringUtil.split(languageId, CharPool.UNDERLINE);

		return list.get(0);
	}

	private Map<String, String> _getTranslatedFieldsMap(
		Map<String, String> fieldsMap, JSONArray jsonArray) {

		Map<String, String> translatedFieldsMap = new HashMap<>();

		int i = 0;

		for (String key : fieldsMap.keySet()) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONArray translationsJSONArray = jsonObject.getJSONArray(
				"translations");

			JSONObject translationJSONObject =
				translationsJSONArray.getJSONObject(0);

			translatedFieldsMap.put(
				key, translationJSONObject.getString("text"));

			i++;
		}

		return translatedFieldsMap;
	}

	private String _getTranslatorPacketPayload(
		TranslatorPacket translatorPacket) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Map<String, String> fields = translatorPacket.getFieldsMap();

		fields.forEach(
			(key, value) -> jsonArray.put(JSONUtil.put("Text", value)));

		return jsonArray.toString();
	}

	private volatile AzureTranslatorConfiguration _azureTranslatorConfiguration;

	@Reference
	private Http _http;

}