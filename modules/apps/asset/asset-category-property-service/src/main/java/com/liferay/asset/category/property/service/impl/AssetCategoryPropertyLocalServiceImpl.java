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

package com.liferay.asset.category.property.service.impl;

import com.liferay.asset.category.property.exception.CategoryPropertyKeyException;
import com.liferay.asset.category.property.exception.CategoryPropertyValueException;
import com.liferay.asset.category.property.exception.DuplicateCategoryPropertyException;
import com.liferay.asset.category.property.model.AssetCategoryProperty;
import com.liferay.asset.category.property.service.base.AssetCategoryPropertyLocalServiceBaseImpl;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
@Component(
	property = "model.class.name=com.liferay.asset.category.property.model.AssetCategoryProperty",
	service = AopService.class
)
public class AssetCategoryPropertyLocalServiceImpl
	extends AssetCategoryPropertyLocalServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long userId, long categoryId, String key, String value)
		throws PortalException {

		validate(key, value);

		if (hasCategoryProperty(categoryId, key)) {
			throw new DuplicateCategoryPropertyException(
				"A category property already exists with the key " + key);
		}

		User user = _userLocalService.getUser(userId);

		long categoryPropertyId = counterLocalService.increment();

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.create(categoryPropertyId);

		categoryProperty.setCompanyId(user.getCompanyId());
		categoryProperty.setUserId(user.getUserId());
		categoryProperty.setUserName(user.getFullName());
		categoryProperty.setCategoryId(categoryId);
		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		return assetCategoryPropertyPersistence.update(categoryProperty);
	}

	@Override
	public void deleteCategoryProperties(long entryId) {
		List<AssetCategoryProperty> categoryProperties =
			assetCategoryPropertyPersistence.findByCategoryId(entryId);

		for (AssetCategoryProperty categoryProperty : categoryProperties) {
			deleteCategoryProperty(categoryProperty);
		}
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteCategoryProperty(AssetCategoryProperty categoryProperty) {
		assetCategoryPropertyPersistence.remove(categoryProperty);
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.findByPrimaryKey(
				categoryPropertyId);

		deleteCategoryProperty(categoryProperty);
	}

	@Override
	public AssetCategoryProperty fetchCategoryProperty(
		long categoryId, String key) {

		return assetCategoryPropertyPersistence.fetchByCA_K(categoryId, key);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties() {
		return assetCategoryPropertyPersistence.findAll();
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId) {
		return assetCategoryPropertyPersistence.findByCategoryId(entryId);
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(long categoryPropertyId)
		throws PortalException {

		return assetCategoryPropertyPersistence.findByPrimaryKey(
			categoryPropertyId);
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(
			long categoryId, String key)
		throws PortalException {

		return assetCategoryPropertyPersistence.findByCA_K(categoryId, key);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
		long groupId, String key) {

		return assetCategoryPropertyFinder.findByG_K(groupId, key);
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.findByPrimaryKey(
				categoryPropertyId);

		String categoryPropertyKey = categoryProperty.getKey();

		if (!categoryPropertyKey.equals(key) &&
			hasCategoryProperty(categoryProperty.getCategoryId(), key)) {

			throw new DuplicateCategoryPropertyException(
				"A category property already exists with the key " + key);
		}

		validate(key, value);

		if (userId != 0) {
			User user = _userLocalService.getUser(userId);

			categoryProperty.setUserId(userId);
			categoryProperty.setUserName(user.getFullName());
		}

		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		return assetCategoryPropertyPersistence.update(categoryProperty);
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		return updateCategoryProperty(0, categoryPropertyId, key, value);
	}

	protected boolean hasCategoryProperty(long categoryId, String key) {
		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.fetchByCA_K(categoryId, key);

		if (categoryProperty != null) {
			return true;
		}

		return false;
	}

	protected void validate(String key, String value) throws PortalException {
		if (!_assetHelper.isValidWord(key)) {
			throw new CategoryPropertyKeyException("Invalid key " + key);
		}

		int keyMaxLength = ModelHintsUtil.getMaxLength(
			AssetCategoryProperty.class.getName(), "key");

		if (key.length() > keyMaxLength) {
			throw new CategoryPropertyKeyException(
				"Maximum length of key exceeded");
		}

		if (!_assetHelper.isValidWord(value)) {
			throw new CategoryPropertyValueException("Invalid value " + value);
		}

		int valueMaxLength = ModelHintsUtil.getMaxLength(
			AssetCategoryProperty.class.getName(), "value");

		if (value.length() > valueMaxLength) {
			throw new CategoryPropertyValueException(
				"Maximum length of value exceeded");
		}
	}

	@Reference
	private AssetHelper _assetHelper;

	@Reference
	private UserLocalService _userLocalService;

}