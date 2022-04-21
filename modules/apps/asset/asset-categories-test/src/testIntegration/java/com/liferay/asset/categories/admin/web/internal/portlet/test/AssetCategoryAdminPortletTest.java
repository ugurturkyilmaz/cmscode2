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

package com.liferay.asset.categories.admin.web.internal.portlet.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Rubén Pulido
 */
@RunWith(Arquillian.class)
public class AssetCategoryAdminPortletTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_company = _companyLocalService.getCompany(_group.getCompanyId());

		_user = UserTestUtil.getAdminUser(_company.getCompanyId());
	}

	@Test
	public void testSetCategoryDisplayPageTemplateForSubcategories()
		throws Exception {

		List<AssetCategory> assetCategories = new ArrayList<>();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.addVocabulary(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(), serviceContext);

		AssetCategory parentAssetCategory =
			_assetCategoryLocalService.addCategory(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				assetVocabulary.getVocabularyId(), serviceContext);

		AssetCategory childAssetCategory1 =
			_assetCategoryLocalService.addCategory(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				parentAssetCategory.getCategoryId(),
				HashMapBuilder.put(
					LocaleUtil.US, RandomTestUtil.randomString()
				).build(),
				null, assetVocabulary.getVocabularyId(), null, serviceContext);

		assetCategories.add(childAssetCategory1);

		AssetCategory childAssetCategory2 =
			_assetCategoryLocalService.addCategory(
				null, TestPropsValues.getUserId(), _group.getGroupId(),
				parentAssetCategory.getCategoryId(),
				HashMapBuilder.put(
					LocaleUtil.US, RandomTestUtil.randomString()
				).build(),
				null, assetVocabulary.getVocabularyId(), null, serviceContext);

		assetCategories.add(childAssetCategory2);

		_testSetCategoryDisplayPageTemplate(assetCategories);
	}

	@Test
	public void testSetCategoryDisplayPageTemplateForTopLevelCategories()
		throws Exception {

		List<AssetCategory> assetCategories = new ArrayList<>();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.addVocabulary(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(), serviceContext);

		AssetCategory assetCategory1 = _assetCategoryLocalService.addCategory(
			TestPropsValues.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), assetVocabulary.getVocabularyId(),
			serviceContext);

		assetCategories.add(assetCategory1);

		AssetCategory assetCategory2 = _assetCategoryLocalService.addCategory(
			TestPropsValues.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), assetVocabulary.getVocabularyId(),
			serviceContext);

		assetCategories.add(assetCategory2);

		_testSetCategoryDisplayPageTemplate(assetCategories);
	}

	private void _testSetCategoryDisplayPageTemplate(
			List<AssetCategory> assetCategories)
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				TestPropsValues.getUserId(), _group.getGroupId(), 0, 0, 0,
				RandomTestUtil.randomString(),
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE, 0, true,
				0, 0, 0, WorkflowConstants.STATUS_APPROVED,
				new ServiceContext());

		MockLiferayPortletActionRequest actionRequest =
			new MockLiferayPortletActionRequest();

		actionRequest.addParameter(
			"assetDisplayPageId",
			String.valueOf(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId()));

		Stream<AssetCategory> stream = assetCategories.stream();

		List<String> categoryIds = stream.map(
			AssetCategory::getCategoryId
		).map(
			String::valueOf
		).collect(
			Collectors.toList()
		);

		actionRequest.addParameter(
			"categoryIds",
			categoryIds.toArray(categoryIds.toArray(new String[0])));

		actionRequest.addParameter(
			"displayPageType",
			String.valueOf(AssetDisplayPageConstants.TYPE_SPECIFIC));

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(_company);
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setUser(_user);

		actionRequest.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		ReflectionTestUtil.invoke(
			_portlet, "setCategoryDisplayPageTemplate",
			new Class<?>[] {ActionRequest.class, ActionResponse.class},
			actionRequest, new MockLiferayPortletActionResponse());

		long classNameId = _portal.getClassNameId(
			AssetCategory.class.getName());

		for (AssetCategory assetCategory : assetCategories) {
			AssetDisplayPageEntry assetDisplayPageEntry =
				_assetDisplayPageEntryLocalService.fetchAssetDisplayPageEntry(
					themeDisplay.getScopeGroupId(), classNameId,
					assetCategory.getCategoryId());

			Assert.assertEquals(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
				assetDisplayPageEntry.getLayoutPageTemplateEntryId());
			Assert.assertEquals(
				AssetDisplayPageConstants.TYPE_SPECIFIC,
				assetDisplayPageEntry.getType());
		}
	}

	@Inject
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Inject
	private AssetDisplayPageEntryLocalService
		_assetDisplayPageEntryLocalService;

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Inject
	private Portal _portal;

	@Inject(
		filter = "component.name=com.liferay.asset.categories.admin.web.internal.portlet.AssetCategoryAdminPortlet"
	)
	private Portlet _portlet;

	private User _user;

}