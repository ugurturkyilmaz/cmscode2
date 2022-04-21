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

package com.liferay.asset.entry.rel.service.impl;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.base.AssetEntryAssetCategoryRelLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "model.class.name=com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel",
	service = AopService.class
)
public class AssetEntryAssetCategoryRelLocalServiceImpl
	extends AssetEntryAssetCategoryRelLocalServiceBaseImpl {

	@Override
	public AssetEntryAssetCategoryRel addAssetEntryAssetCategoryRel(
		long assetEntryId, long assetCategoryId) {

		return assetEntryAssetCategoryRelLocalService.
			addAssetEntryAssetCategoryRel(assetEntryId, assetCategoryId, 0);
	}

	@Override
	public AssetEntryAssetCategoryRel addAssetEntryAssetCategoryRel(
		long assetEntryId, long assetCategoryId, int priority) {

		long assetEntryAssetCategoryRelId = counterLocalService.increment();

		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel =
			assetEntryAssetCategoryRelPersistence.create(
				assetEntryAssetCategoryRelId);

		assetEntryAssetCategoryRel.setAssetEntryId(assetEntryId);
		assetEntryAssetCategoryRel.setAssetCategoryId(assetCategoryId);
		assetEntryAssetCategoryRel.setPriority(priority);

		return assetEntryAssetCategoryRelPersistence.update(
			assetEntryAssetCategoryRel);
	}

	@Override
	public void deleteAssetEntryAssetCategoryRel(
		long assetEntryId, long assetCategoryId) {

		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel =
			assetEntryAssetCategoryRelPersistence.fetchByA_A(
				assetEntryId, assetCategoryId);

		if (assetEntryAssetCategoryRel != null) {
			assetEntryAssetCategoryRelPersistence.remove(
				assetEntryAssetCategoryRel);
		}

		_reindex(assetEntryId);
	}

	@Override
	public void deleteAssetEntryAssetCategoryRelByAssetCategoryId(
		long assetCategoryId) {

		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			assetEntryAssetCategoryRelPersistence.findByAssetCategoryId(
				assetCategoryId);

		assetEntryAssetCategoryRels.forEach(
			assetEntryAssetCategoryRel -> {
				assetEntryAssetCategoryRelPersistence.remove(
					assetEntryAssetCategoryRel);

				_reindex(assetEntryAssetCategoryRel.getAssetEntryId());
			});
	}

	@Override
	public void deleteAssetEntryAssetCategoryRelByAssetEntryId(
		long assetEntryId) {

		assetEntryAssetCategoryRelPersistence.removeByAssetEntryId(
			assetEntryId);

		_reindex(assetEntryId);
	}

	@Override
	public AssetEntryAssetCategoryRel fetchAssetEntryAssetCategoryRel(
		long assetEntryId, long assetCategoryId) {

		return assetEntryAssetCategoryRelPersistence.fetchByA_A(
			assetEntryId, assetCategoryId);
	}

	@Override
	public long[] getAssetCategoryPrimaryKeys(long assetEntryId) {
		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			getAssetEntryAssetCategoryRelsByAssetEntryId(assetEntryId);

		return ListUtil.toLongArray(
			assetEntryAssetCategoryRels,
			AssetEntryAssetCategoryRel::getAssetCategoryId);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetCategoryId(long assetCategoryId) {

		return assetEntryAssetCategoryRelPersistence.findByAssetCategoryId(
			assetCategoryId);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetCategoryId(
			long assetCategoryId, int start, int end) {

		return assetEntryAssetCategoryRelPersistence.findByAssetCategoryId(
			assetCategoryId, start, end);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetCategoryId(
			long assetCategoryId, int start, int end,
			OrderByComparator<AssetEntryAssetCategoryRel> orderByComparator) {

		return assetEntryAssetCategoryRelPersistence.findByAssetCategoryId(
			assetCategoryId, start, end, orderByComparator);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetEntryId(long assetEntryId) {

		return assetEntryAssetCategoryRelPersistence.findByAssetEntryId(
			assetEntryId);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetEntryId(
			long assetEntryId, int start, int end) {

		return assetEntryAssetCategoryRelPersistence.findByAssetEntryId(
			assetEntryId, start, end);
	}

	@Override
	public List<AssetEntryAssetCategoryRel>
		getAssetEntryAssetCategoryRelsByAssetEntryId(
			long assetEntryId, int start, int end,
			OrderByComparator<AssetEntryAssetCategoryRel> orderByComparator) {

		return assetEntryAssetCategoryRelPersistence.findByAssetEntryId(
			assetEntryId, start, end, orderByComparator);
	}

	@Override
	public int getAssetEntryAssetCategoryRelsCount(long assetEntryId) {
		return assetEntryAssetCategoryRelPersistence.countByAssetEntryId(
			assetEntryId);
	}

	@Override
	public int getAssetEntryAssetCategoryRelsCountByAssetCategoryId(
		long assetCategoryId) {

		return assetEntryAssetCategoryRelPersistence.countByAssetCategoryId(
			assetCategoryId);
	}

	@Override
	public long[] getAssetEntryPrimaryKeys(long assetCategoryId) {
		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			getAssetEntryAssetCategoryRelsByAssetCategoryId(assetCategoryId);

		return ListUtil.toLongArray(
			assetEntryAssetCategoryRels,
			AssetEntryAssetCategoryRel::getAssetEntryId);
	}

	private void _reindex(long assetEntryId) {
		if (assetEntryId <= 0) {
			return;
		}

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			assetEntryId);

		if (assetEntry == null) {
			return;
		}

		try {
			Indexer<Object> indexer = IndexerRegistryUtil.getIndexer(
				assetEntry.getClassName());

			if (indexer == null) {
				return;
			}

			AssetRenderer<?> assetRenderer = assetEntry.getAssetRenderer();

			if (assetRenderer == null) {
				return;
			}

			Object assetObject = assetRenderer.getAssetObject();

			if (assetObject == null) {
				return;
			}

			indexer.reindex(assetObject);
		}
		catch (SearchException searchException) {
			_log.error("Unable to reindex asset entry", searchException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryAssetCategoryRelLocalServiceImpl.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}