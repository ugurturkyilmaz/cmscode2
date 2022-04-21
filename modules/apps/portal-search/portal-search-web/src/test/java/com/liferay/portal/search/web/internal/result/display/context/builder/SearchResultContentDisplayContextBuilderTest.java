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

package com.liferay.portal.search.web.internal.result.display.context.builder;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.util.AssetRendererFactoryLookup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.internal.result.display.context.SearchResultContentDisplayContext;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portlet.internal.MutableRenderParametersImpl;

import java.util.HashMap;
import java.util.HashSet;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SearchResultContentDisplayContextBuilderTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_setUpAssetEntry();
		setUpAssetRenderer();
		_setUpAssetRendererFactory();
		_setUpPortal();
		_setUpRenderResponse();
		_setUpThemeDisplay();
	}

	@Test
	public void testEditPermission() throws Exception {
		String title = RandomTestUtil.randomString();

		Mockito.doReturn(
			title
		).when(
			_assetRenderer
		).getTitle(
			Mockito.anyObject()
		);

		String editPortletURLString = RandomTestUtil.randomString();

		Mockito.doReturn(
			editPortletURLString
		).when(
			_editPortletURL
		).toString();

		SearchResultContentDisplayContext searchResultContentDisplayContext =
			_buildDisplayContext();

		Assert.assertTrue(
			searchResultContentDisplayContext.hasEditPermission());

		_assertIcon(
			title, editPortletURLString, searchResultContentDisplayContext);
	}

	@Test
	public void testEditPermissionFalse() throws Exception {
		Mockito.doReturn(
			false
		).when(
			_assetRenderer
		).hasEditPermission(
			Mockito.anyObject()
		);

		SearchResultContentDisplayContext searchResultContentDisplayContext =
			_buildDisplayContext();

		Assert.assertFalse(
			searchResultContentDisplayContext.hasEditPermission());

		_assertIconMissing(searchResultContentDisplayContext);

		_assertAssetDisplay(searchResultContentDisplayContext);
	}

	@Test
	public void testVisible() throws Exception {
		SearchResultContentDisplayContext searchResultContentDisplayContext =
			_buildDisplayContext();

		Assert.assertTrue(searchResultContentDisplayContext.isVisible());

		_assertAssetDisplay(searchResultContentDisplayContext);
	}

	@Test
	public void testVisibleFalseFromEntry() throws Exception {
		Mockito.doReturn(
			false
		).when(
			_assetEntry
		).isVisible();

		SearchResultContentDisplayContext searchResultContentDisplayContext =
			_buildDisplayContext();

		Assert.assertFalse(searchResultContentDisplayContext.isVisible());
	}

	@Test
	public void testVisibleFalseFromViewPermission() throws Exception {
		Mockito.doReturn(
			false
		).when(
			_assetRenderer
		).hasViewPermission(
			Mockito.anyObject()
		);

		SearchResultContentDisplayContext searchResultContentDisplayContext =
			_buildDisplayContext();

		Assert.assertFalse(searchResultContentDisplayContext.isVisible());
	}

	protected void setUpAssetRenderer() throws Exception, PortalException {
		Mockito.doReturn(
			_editPortletURL
		).when(
			_assetRenderer
		).getURLEdit(
			Mockito.anyObject(), Mockito.anyObject()
		);

		Mockito.doReturn(
			true
		).when(
			_assetRenderer
		).hasEditPermission(
			Mockito.anyObject()
		);

		Mockito.doReturn(
			true
		).when(
			_assetRenderer
		).hasViewPermission(
			Mockito.anyObject()
		);
	}

	private void _assertAssetDisplay(
		SearchResultContentDisplayContext searchResultContentDisplayContext) {

		Assert.assertSame(
			_assetEntry, searchResultContentDisplayContext.getAssetEntry());

		Assert.assertSame(
			_assetRenderer,
			searchResultContentDisplayContext.getAssetRenderer());

		Assert.assertSame(
			_assetRendererFactory,
			searchResultContentDisplayContext.getAssetRendererFactory());
	}

	private void _assertIcon(
		String editTarget, String urlString,
		SearchResultContentDisplayContext searchResultContentDisplayContext) {

		Assert.assertEquals(
			editTarget, searchResultContentDisplayContext.getIconEditTarget());

		Assert.assertEquals(
			urlString, searchResultContentDisplayContext.getIconURLString());
	}

	private void _assertIconMissing(
		SearchResultContentDisplayContext searchResultContentDisplayContext) {

		Assert.assertNull(
			searchResultContentDisplayContext.getIconEditTarget());

		Assert.assertNull(searchResultContentDisplayContext.getIconURLString());
	}

	private SearchResultContentDisplayContext _buildDisplayContext()
		throws Exception {

		SearchResultContentDisplayContextBuilder
			searchResultContentDisplayContextBuilder =
				new SearchResultContentDisplayContextBuilder();

		searchResultContentDisplayContextBuilder.setAssetEntryId(
			RandomTestUtil.randomLong());
		searchResultContentDisplayContextBuilder.setAssetRendererFactoryLookup(
			_assetRendererFactoryLookup);
		searchResultContentDisplayContextBuilder.setLocale(LocaleUtil.US);
		searchResultContentDisplayContextBuilder.setPermissionChecker(
			_permissionChecker);
		searchResultContentDisplayContextBuilder.setPortal(_portal);
		searchResultContentDisplayContextBuilder.setRenderRequest(
			_renderRequest);
		searchResultContentDisplayContextBuilder.setRenderResponse(
			_renderResponse);
		searchResultContentDisplayContextBuilder.setType(
			RandomTestUtil.randomString());

		return searchResultContentDisplayContextBuilder.build();
	}

	private void _setUpAssetEntry() {
		Mockito.doReturn(
			_assetRenderer
		).when(
			_assetEntry
		).getAssetRenderer();

		Mockito.doReturn(
			true
		).when(
			_assetEntry
		).isVisible();
	}

	private void _setUpAssetRendererFactory() throws Exception {
		Mockito.doReturn(
			_assetEntry
		).when(
			_assetRendererFactory
		).getAssetEntry(
			Mockito.anyLong()
		);

		Mockito.doReturn(
			_assetRendererFactory
		).when(
			_assetRendererFactoryLookup
		).getAssetRendererFactoryByType(
			Mockito.anyString()
		);
	}

	private void _setUpPortal() {
		Mockito.doReturn(
			Mockito.mock(LiferayPortletRequest.class)
		).when(
			_portal
		).getLiferayPortletRequest(
			Mockito.anyObject()
		);

		Mockito.doReturn(
			Mockito.mock(LiferayPortletResponse.class)
		).when(
			_portal
		).getLiferayPortletResponse(
			Mockito.anyObject()
		);
	}

	private void _setUpRenderResponse() {
		Mockito.doReturn(
			_renderPortletURL
		).when(
			_renderResponse
		).createRenderURL();

		Mockito.doReturn(
			new MutableRenderParametersImpl(new HashMap<>(), new HashSet<>())
		).when(
			_renderPortletURL
		).getRenderParameters();
	}

	private void _setUpThemeDisplay() {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setURLCurrent("http://example.com");

		Mockito.when(
			(ThemeDisplay)_renderRequest.getAttribute(WebKeys.THEME_DISPLAY)
		).thenReturn(
			themeDisplay
		);
	}

	@Mock
	private AssetEntry _assetEntry;

	@Mock
	private AssetRenderer<?> _assetRenderer;

	@Mock
	@SuppressWarnings("rawtypes")
	private AssetRendererFactory _assetRendererFactory;

	@Mock
	private AssetRendererFactoryLookup _assetRendererFactoryLookup;

	@Mock
	private PortletURL _editPortletURL;

	@Mock
	private PermissionChecker _permissionChecker;

	@Mock
	private Portal _portal;

	@Mock
	private PortletURL _renderPortletURL;

	@Mock
	private RenderRequest _renderRequest;

	@Mock
	private RenderResponse _renderResponse;

}