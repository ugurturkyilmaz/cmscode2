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

package com.liferay.depot.web.internal.asset.model.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Alejandro Tardín
 */
@RunWith(Arquillian.class)
public class DepotAssetRendererFactoryControllerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

		_depotEntry = _depotEntryLocalService.addDepotEntry(
			HashMapBuilder.put(
				LocaleUtil.getDefault(), "name"
			).build(),
			new HashMap<>(), ServiceContextTestUtil.getServiceContext());
	}

	@Test
	public void testDefaultAssetRendererFactoriesAreSelectableForASiteGroup()
		throws Exception {

		_withGroup(
			_groupLocalService.fetchGroup(TestPropsValues.getGroupId()),
			() -> {
				List<AssetRendererFactory<?>> assetRendererFactories =
					AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
						TestPropsValues.getCompanyId(), true);

				Assert.assertTrue(
					assetRendererFactories.toString(),
					assetRendererFactories.size() > 4);
			});
	}

	@Test
	public void testDefaultAssetRendererFactoriesAreSelectableForNoGroup()
		throws Exception {

		List<AssetRendererFactory<?>> assetRendererFactories =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
				TestPropsValues.getCompanyId(), true);

		Assert.assertTrue(
			assetRendererFactories.toString(),
			assetRendererFactories.size() > 4);
	}

	@Test
	public void testOnlySupportedAssetRendererFactoriesAreSelectableForADepotGroup()
		throws Exception {

		_withGroup(
			_groupLocalService.fetchGroup(_depotEntry.getGroupId()),
			() -> {
				List<AssetRendererFactory<?>> assetRendererFactories =
					AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
						TestPropsValues.getCompanyId(), true);

				Assert.assertEquals(
					assetRendererFactories.toString(), 4,
					assetRendererFactories.size());

				AssetRendererFactory<?> journalArticleAssetRendererFactory =
					assetRendererFactories.get(0);

				Assert.assertEquals(
					journalArticleAssetRendererFactory.getClassName(),
					JournalArticle.class.getName());

				AssetRendererFactory<?> dlFileEntryAssetRendererFactory =
					assetRendererFactories.get(1);

				Assert.assertEquals(
					dlFileEntryAssetRendererFactory.getClassName(),
					DLFileEntry.class.getName());

				AssetRendererFactory<?> journalFolderAssetRendererFactory =
					assetRendererFactories.get(2);

				Assert.assertEquals(
					journalFolderAssetRendererFactory.getClassName(),
					JournalFolder.class.getName());

				AssetRendererFactory<?> dlFolderAssetRendererFactory =
					assetRendererFactories.get(3);

				Assert.assertEquals(
					dlFolderAssetRendererFactory.getClassName(),
					DLFolder.class.getName());
			});
	}

	private void _withGroup(
			Group group, UnsafeRunnable<Exception> unsafeRunnable)
		throws Exception {

		long previousGroupId = GroupThreadLocal.getGroupId();

		GroupThreadLocal.setGroupId(group.getGroupId());

		try {
			unsafeRunnable.run();
		}
		finally {
			GroupThreadLocal.setGroupId(previousGroupId);
		}

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		try {
			unsafeRunnable.run();
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setRequest(
			new MockHttpServletRequest() {

				@Override
				public Object getAttribute(String name) {
					if (StringUtil.equals(name, WebKeys.THEME_DISPLAY)) {
						return new ThemeDisplay() {

							@Override
							public Group getScopeGroup() {
								return group;
							}

						};
					}

					return super.getAttribute(name);
				}

			});

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		try {
			unsafeRunnable.run();
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	@DeleteAfterTestRun
	private DepotEntry _depotEntry;

	@Inject
	private DepotEntryLocalService _depotEntryLocalService;

	@Inject
	private GroupLocalService _groupLocalService;

}