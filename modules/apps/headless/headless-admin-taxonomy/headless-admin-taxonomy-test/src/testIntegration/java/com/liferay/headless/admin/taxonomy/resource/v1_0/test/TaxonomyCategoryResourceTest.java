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

package com.liferay.headless.admin.taxonomy.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class TaxonomyCategoryResourceTest
	extends BaseTaxonomyCategoryResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_assetVocabulary = AssetVocabularyLocalServiceUtil.addVocabulary(
			UserLocalServiceUtil.getDefaultUserId(testGroup.getCompanyId()),
			testGroup.getGroupId(), RandomTestUtil.randomString(),
			new ServiceContext());
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"description", "name"};
	}

	@Override
	protected TaxonomyCategory randomTaxonomyCategory() throws Exception {
		TaxonomyCategory taxonomyCategory = super.randomTaxonomyCategory();

		taxonomyCategory.setTaxonomyVocabularyId(
			_assetVocabulary.getVocabularyId());

		return taxonomyCategory;
	}

	@Override
	protected TaxonomyCategory testDeleteTaxonomyCategory_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory
			testDeleteTaxonomyVocabularyTaxonomyCategoryByExternalReferenceCode_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory
			testGetTaxonomyCategoriesRankedPage_addTaxonomyCategory(
				TaxonomyCategory taxonomyCategory)
		throws Exception {

		taxonomyCategory =
			testPostTaxonomyCategoryTaxonomyCategory_addTaxonomyCategory(
				taxonomyCategory);

		AssetEntry assetEntry = AssetTestUtil.addAssetEntry(
			testGroup.getGroupId());

		AssetEntryAssetCategoryRelLocalServiceUtil.
			addAssetEntryAssetCategoryRel(
				assetEntry.getEntryId(),
				GetterUtil.getLong(taxonomyCategory.getId()));

		return taxonomyCategory;
	}

	@Override
	protected TaxonomyCategory testGetTaxonomyCategory_addTaxonomyCategory()
		throws Exception {

		return taxonomyCategoryResource.postTaxonomyVocabularyTaxonomyCategory(
			_assetVocabulary.getVocabularyId(), randomTaxonomyCategory());
	}

	@Override
	protected String
			testGetTaxonomyCategoryTaxonomyCategoriesPage_getParentTaxonomyCategoryId()
		throws Exception {

		TaxonomyCategory taxonomyCategory =
			taxonomyCategoryResource.postTaxonomyVocabularyTaxonomyCategory(
				_assetVocabulary.getVocabularyId(), randomTaxonomyCategory());

		return taxonomyCategory.getId();
	}

	@Override
	protected Long
		testGetTaxonomyVocabularyTaxonomyCategoriesPage_getTaxonomyVocabularyId() {

		return _assetVocabulary.getVocabularyId();
	}

	@Override
	protected TaxonomyCategory
			testGetTaxonomyVocabularyTaxonomyCategoryByExternalReferenceCode_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory testGraphQLTaxonomyCategory_addTaxonomyCategory()
		throws Exception {

		return testPostTaxonomyCategoryTaxonomyCategory_addTaxonomyCategory(
			randomTaxonomyCategory());
	}

	@Override
	protected TaxonomyCategory testPatchTaxonomyCategory_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory
			testPostTaxonomyCategoryTaxonomyCategory_addTaxonomyCategory(
				TaxonomyCategory taxonomyCategory)
		throws Exception {

		return taxonomyCategoryResource.postTaxonomyVocabularyTaxonomyCategory(
			_assetVocabulary.getVocabularyId(), taxonomyCategory);
	}

	@Override
	protected TaxonomyCategory
			testPostTaxonomyVocabularyTaxonomyCategory_addTaxonomyCategory(
				TaxonomyCategory taxonomyCategory)
		throws Exception {

		return testPostTaxonomyCategoryTaxonomyCategory_addTaxonomyCategory(
			taxonomyCategory);
	}

	@Override
	protected TaxonomyCategory testPutTaxonomyCategory_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory
			testPutTaxonomyCategoryPermissionsPage_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	@Override
	protected TaxonomyCategory
			testPutTaxonomyVocabularyTaxonomyCategoryByExternalReferenceCode_addTaxonomyCategory()
		throws Exception {

		return testGetTaxonomyCategory_addTaxonomyCategory();
	}

	private AssetVocabulary _assetVocabulary;

}