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

package com.liferay.content.dashboard.web.internal.model;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * @author David Arques
 */
public class AssetCategoryMetric {

	public AssetCategoryMetric(
		AssetVocabularyMetric assetVocabularyMetric, String key, String name,
		long value) {

		_assetVocabularyMetric = Optional.ofNullable(
			assetVocabularyMetric
		).orElse(
			AssetVocabularyMetric.empty()
		);
		_key = key;
		_name = name;
		_value = value;
	}

	public AssetCategoryMetric(String key, String name, long value) {
		this(AssetVocabularyMetric.empty(), key, name, value);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssetCategoryMetric)) {
			return false;
		}

		AssetCategoryMetric assetCategoryMetric = (AssetCategoryMetric)object;

		if (Objects.equals(
				_assetVocabularyMetric,
				assetCategoryMetric._assetVocabularyMetric) &&
			Objects.equals(_key, assetCategoryMetric._key) &&
			Objects.equals(_value, assetCategoryMetric._value)) {

			return true;
		}

		return false;
	}

	public AssetVocabularyMetric getAssetVocabularyMetric() {
		return _assetVocabularyMetric;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public long getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_assetVocabularyMetric, _key, _value);
	}

	public JSONObject toJSONObject(String vocabularyName) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (!ListUtil.isEmpty(
				_assetVocabularyMetric.getAssetCategoryMetrics())) {

			jsonObject.put("categories", _assetVocabularyMetric.toJSONArray());
		}

		return jsonObject.put(
			"key", _key
		).put(
			"name", _name
		).put(
			"value", _value
		).put(
			"vocabularyName", vocabularyName
		);
	}

	private AssetVocabularyMetric _assetVocabularyMetric;
	private final String _key;
	private final String _name;
	private final long _value;

}