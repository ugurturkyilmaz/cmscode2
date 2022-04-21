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
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.util.AssetRendererFactoryLookup;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentHelper;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.search.internal.summary.SummaryBuilderFactoryImpl;
import com.liferay.portal.search.web.internal.display.context.PortletURLFactory;
import com.liferay.portal.search.web.internal.display.context.SearchResultPreferences;
import com.liferay.portal.search.web.internal.result.display.context.SearchResultSummaryDisplayContext;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.FastDateFormatFactoryImpl;

import java.util.Collections;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Lino Alves
 * @author André de Oliveira
 */
public class SearchResultSummaryDisplayContextBuilderTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpAssetRenderer();
		_setUpGroupLocalService();
		_setUpLocaleThreadLocal();
		_setUpPropsUtil();
		_setUpUser();
		_setUpUserLocalService();

		themeDisplay = _createThemeDisplay();
	}

	@Test
	public void testClassFieldsWithoutAssetTagsOrCategories() throws Exception {
		PortletURL portletURL = Mockito.mock(PortletURL.class);

		Mockito.doReturn(
			portletURL
		).when(
			portletURLFactory
		).getPortletURL();

		String entryClassName = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomLong();

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			entryClassName);

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(_createDocument(entryClassName, entryClassPK));

		Assert.assertEquals(
			entryClassName, searchResultSummaryDisplayContext.getClassName());
		Assert.assertEquals(
			entryClassPK, searchResultSummaryDisplayContext.getClassPK());
		Assert.assertEquals(
			portletURL, searchResultSummaryDisplayContext.getPortletURL());
	}

	@Test
	public void testCreationDate() throws Exception {
		String entryClassName = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomLong();

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			entryClassName);

		Document document = _createDocument(entryClassName, entryClassPK);

		_assertCreationDateMissing(document);

		document.addKeyword(Field.CREATE_DATE, "20180425171442");

		_assertCreationDate("Apr 25, 2018 5:14 PM", document);

		_assertCreationDate(LocaleUtil.BRAZIL, "25/04/2018 17:14", document);
		_assertCreationDate(LocaleUtil.CHINA, "2018-4-25 下午5:14", document);
		_assertCreationDate(LocaleUtil.GERMANY, "25.04.2018 17:14", document);
		_assertCreationDate(LocaleUtil.HUNGARY, "2018.04.25. 17:14", document);
		_assertCreationDate(LocaleUtil.ITALY, "25-apr-2018 17.14", document);
		_assertCreationDate(LocaleUtil.JAPAN, "2018/04/25 17:14", document);
		_assertCreationDate(
			LocaleUtil.NETHERLANDS, "25-apr-2018 17:14", document);
		_assertCreationDate(LocaleUtil.SPAIN, "25-abr-2018 17:14", document);
	}

	@Test
	public void testNoStagingLabel() throws Exception {
		String entryClassName = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomLong();

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			entryClassName);

		_whenGroupLocalServiceGetGroup(false);

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(_createDocument(entryClassName, entryClassPK));

		Assert.assertEquals(
			_SUMMARY_TITLE,
			searchResultSummaryDisplayContext.getHighlightedTitle());
	}

	@Test
	public void testResultIsTemporarilyUnavailable() throws Exception {
		_ruinAssetRendererFactoryLookup();

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(Mockito.mock(Document.class));

		Assert.assertTrue(
			searchResultSummaryDisplayContext.isTemporarilyUnavailable());
	}

	@Test
	public void testStagingLabel() throws Exception {
		String entryClassName = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomLong();

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			entryClassName);

		_whenGroupLocalServiceGetGroup(true);
		_whenLanguageGet("staged");

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(_createDocument(entryClassName, entryClassPK));

		Assert.assertEquals(
			_SUMMARY_TITLE + " (staged)",
			searchResultSummaryDisplayContext.getHighlightedTitle());
	}

	@Test
	public void testTagsURLDownloadAndUserPortraitFromResult()
		throws Exception {

		long userId = RandomTestUtil.randomLong();

		AssetEntry assetEntry = _createAssetEntryWithTagsPresent(userId);

		String className = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomLong();

		_whenAssetEntryLocalServiceFetchEntry(
			className, entryClassPK, assetEntry);

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			className);

		String urlDownload = RandomTestUtil.randomString();

		_whenAssetRendererGetURLDownload(assetRenderer, urlDownload);

		_whenIndexerRegistryGetIndexer(className, _createIndexer());

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(_createDocument(className, entryClassPK));

		_assertAssetRendererURLDownloadVisible(
			urlDownload, searchResultSummaryDisplayContext);

		_assertTagsVisible(entryClassPK, searchResultSummaryDisplayContext);

		_assertUserPortraitVisible(userId, searchResultSummaryDisplayContext);
	}

	@Test
	public void testUserPortraitFromResultButTagsAndURLDownloadFromRoot()
		throws Exception {

		long userId = RandomTestUtil.randomInt(2, Integer.MAX_VALUE);

		AssetEntry assetEntry = _createAssetEntry(userId);

		long rootUserId = userId - 1;

		AssetEntry rootAssetEntry = _createAssetEntryWithTagsPresent(
			rootUserId);

		String className = RandomTestUtil.randomString();

		long entryClassPK = RandomTestUtil.randomInt(2, Integer.MAX_VALUE);

		long rootEntryClassPK = entryClassPK - 1;

		_whenAssetEntryLocalServiceFetchEntry(
			className, entryClassPK, assetEntry);

		_whenAssetEntryLocalServiceFetchEntry(
			className, rootEntryClassPK, rootAssetEntry);

		_whenAssetRendererFactoryGetAssetRenderer(entryClassPK, assetRenderer);

		AssetRenderer<?> rootAssetRenderer = Mockito.mock(AssetRenderer.class);

		_whenAssetRendererFactoryGetAssetRenderer(
			rootEntryClassPK, rootAssetRenderer);

		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			className);

		String rootURLDownload = RandomTestUtil.randomString();

		_whenAssetRendererGetURLDownload(rootAssetRenderer, rootURLDownload);

		_whenIndexerRegistryGetIndexer(className, _createIndexer());

		Document document = _createDocument(className, entryClassPK);

		document.addKeyword(Field.ROOT_ENTRY_CLASS_PK, rootEntryClassPK);

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(document);

		_assertAssetRendererURLDownloadVisible(
			rootURLDownload, searchResultSummaryDisplayContext);

		_assertTagsVisible(rootEntryClassPK, searchResultSummaryDisplayContext);

		_assertUserPortraitVisible(userId, searchResultSummaryDisplayContext);
	}

	protected SearchResultSummaryDisplayContext build(Document document)
		throws Exception {

		SearchResultSummaryDisplayContextBuilder
			searchResultSummaryDisplayContextBuilder =
				_createSearchResultSummaryDisplayContextBuilder();

		searchResultSummaryDisplayContextBuilder.setDocument(document);

		return searchResultSummaryDisplayContextBuilder.build();
	}

	protected void setUpAssetRenderer() throws Exception {
		Mockito.doReturn(
			_SUMMARY_CONTENT
		).when(
			assetRenderer
		).getSearchSummary(
			(Locale)Matchers.any()
		);

		Mockito.doReturn(
			_SUMMARY_TITLE
		).when(
			assetRenderer
		).getTitle(
			(Locale)Matchers.any()
		);
	}

	@Mock
	protected AssetEntryLocalService assetEntryLocalService;

	@Mock
	protected AssetRenderer<?> assetRenderer;

	@Mock
	protected AssetRendererFactory<?> assetRendererFactory;

	@Mock
	protected AssetRendererFactoryLookup assetRendererFactoryLookup;

	protected FastDateFormatFactory fastDateFormatFactory =
		new FastDateFormatFactoryImpl();

	@Mock
	protected Group group;

	@Mock
	protected GroupLocalService groupLocalService;

	@Mock
	protected IndexerRegistry indexerRegistry;

	@Mock
	protected Language language;

	protected Locale locale = LocaleUtil.US;

	@Mock
	protected PermissionChecker permissionChecker;

	@Mock
	protected PortletURLFactory portletURLFactory;

	protected ThemeDisplay themeDisplay;

	@Mock
	protected User user;

	@Mock
	protected UserLocalService userLocalService;

	private void _assertAssetRendererURLDownloadVisible(
		String urlDownload,
		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext) {

		Assert.assertTrue(
			searchResultSummaryDisplayContext.
				isAssetRendererURLDownloadVisible());

		Assert.assertEquals(
			urlDownload,
			searchResultSummaryDisplayContext.getAssetRendererURLDownload());
	}

	private void _assertCreationDate(
			Locale locale1, String expectedCreationDateString,
			Document document)
		throws Exception {

		locale = locale1;

		_assertCreationDate(expectedCreationDateString, document);
	}

	private void _assertCreationDate(
			String expectedCreationDateString, Document document)
		throws Exception {

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(document);

		Assert.assertEquals(
			expectedCreationDateString,
			searchResultSummaryDisplayContext.getCreationDateString());

		Assert.assertTrue(
			searchResultSummaryDisplayContext.isCreationDateVisible());
	}

	private void _assertCreationDateMissing(Document document)
		throws Exception {

		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext =
			build(document);

		Assert.assertNull(
			searchResultSummaryDisplayContext.getCreationDateString());

		Assert.assertFalse(
			searchResultSummaryDisplayContext.isCreationDateVisible());
	}

	private void _assertTagsVisible(
		long entryClassPK,
		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext) {

		Assert.assertTrue(
			searchResultSummaryDisplayContext.isAssetCategoriesOrTagsVisible());

		Assert.assertEquals(
			entryClassPK, searchResultSummaryDisplayContext.getClassPK());
	}

	private void _assertUserPortraitVisible(
		long userId,
		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext) {

		Assert.assertTrue(
			searchResultSummaryDisplayContext.isUserPortraitVisible());

		Assert.assertEquals(
			userId, searchResultSummaryDisplayContext.getAssetEntryUserId());
	}

	private AssetEntry _createAssetEntry(long userId) {
		AssetEntry assetEntry = Mockito.mock(AssetEntry.class);

		Mockito.doReturn(
			assetRenderer
		).when(
			assetEntry
		).getAssetRenderer();

		Mockito.doReturn(
			assetRendererFactory
		).when(
			assetEntry
		).getAssetRendererFactory();

		Mockito.doReturn(
			userId
		).when(
			assetEntry
		).getUserId();

		return assetEntry;
	}

	private AssetEntry _createAssetEntryWithTagsPresent(long rootUserId) {
		AssetEntry rootAssetEntry = _createAssetEntry(rootUserId);

		Mockito.doReturn(
			new String[] {RandomTestUtil.randomString()}
		).when(
			rootAssetEntry
		).getTagNames();

		return rootAssetEntry;
	}

	private Document _createDocument(String entryClassName, long entryClassPK) {
		Document document = new DocumentImpl();

		DocumentHelper documentHelper = new DocumentHelper(document);

		documentHelper.setEntryKey(entryClassName, entryClassPK);

		return document;
	}

	private Indexer<?> _createIndexer() throws Exception {
		Indexer<?> indexer = Mockito.mock(Indexer.class);

		Mockito.doReturn(
			new Summary(LocaleUtil.US, null, null)
		).when(
			indexer
		).getSummary(
			Mockito.any(), Mockito.anyString(), Mockito.any(), Mockito.any()
		);

		return indexer;
	}

	private SearchResultSummaryDisplayContextBuilder
		_createSearchResultSummaryDisplayContextBuilder() {

		SearchResultSummaryDisplayContextBuilder
			searchResultSummaryDisplayContextBuilder =
				new SearchResultSummaryDisplayContextBuilder();

		searchResultSummaryDisplayContextBuilder.setAssetEntryLocalService(
			assetEntryLocalService);
		searchResultSummaryDisplayContextBuilder.setAssetRendererFactoryLookup(
			assetRendererFactoryLookup);
		searchResultSummaryDisplayContextBuilder.setFastDateFormatFactory(
			fastDateFormatFactory);
		searchResultSummaryDisplayContextBuilder.setGroupLocalService(
			groupLocalService);
		searchResultSummaryDisplayContextBuilder.setIndexerRegistry(
			indexerRegistry);
		searchResultSummaryDisplayContextBuilder.setLanguage(language);
		searchResultSummaryDisplayContextBuilder.setLocale(locale);
		searchResultSummaryDisplayContextBuilder.setPortletURLFactory(
			portletURLFactory);
		searchResultSummaryDisplayContextBuilder.setResourceActions(
			Mockito.mock(ResourceActions.class));
		searchResultSummaryDisplayContextBuilder.setSearchResultPreferences(
			Mockito.mock(SearchResultPreferences.class));
		searchResultSummaryDisplayContextBuilder.setSearchResultViewURLSupplier(
			Mockito.mock(SearchResultViewURLSupplier.class));
		searchResultSummaryDisplayContextBuilder.setSummaryBuilderFactory(
			new SummaryBuilderFactoryImpl());
		searchResultSummaryDisplayContextBuilder.setThemeDisplay(themeDisplay);
		searchResultSummaryDisplayContextBuilder.setUserLocalService(
			userLocalService);

		return searchResultSummaryDisplayContextBuilder;
	}

	private ThemeDisplay _createThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(Mockito.mock(Company.class));
		themeDisplay.setPermissionChecker(permissionChecker);
		themeDisplay.setUser(Mockito.mock(User.class));

		return themeDisplay;
	}

	private void _ruinAssetRendererFactoryLookup() {
		Mockito.doThrow(
			RuntimeException.class
		).when(
			assetRendererFactoryLookup
		).getAssetRendererFactoryByClassName(
			Mockito.anyString()
		);
	}

	private void _setUpGroupLocalService() {
		Mockito.doReturn(
			group
		).when(
			groupLocalService
		).fetchGroup(
			Mockito.anyLong()
		);
	}

	private void _setUpLocaleThreadLocal() {
		LocaleThreadLocal.setThemeDisplayLocale(LocaleUtil.US);
	}

	private void _setUpPropsUtil() {
		PropsTestUtil.setProps(Collections.emptyMap());
	}

	private void _setUpUser() throws Exception {
		Mockito.doReturn(
			RandomTestUtil.randomString()
		).when(
			user
		).getPortraitURL(
			Mockito.any()
		);

		Mockito.doReturn(
			RandomTestUtil.randomLong()
		).when(
			user
		).getPortraitId();
	}

	private void _setUpUserLocalService() {
		Mockito.doReturn(
			user
		).when(
			userLocalService
		).fetchUser(
			Mockito.anyLong()
		);
	}

	private void _whenAssetEntryLocalServiceFetchEntry(
		String className, long classPK, AssetEntry assetEntry) {

		Mockito.doReturn(
			assetEntry
		).when(
			assetEntryLocalService
		).fetchEntry(
			className, classPK
		);
	}

	private void _whenAssetRendererFactoryGetAssetRenderer(
			long entryClassPK, AssetRenderer<?> assetRenderer)
		throws Exception {

		Mockito.doReturn(
			assetRenderer
		).when(
			assetRendererFactory
		).getAssetRenderer(
			entryClassPK
		);
	}

	private void
		_whenAssetRendererFactoryLookupGetAssetRendererFactoryByClassName(
			String className) {

		Mockito.doReturn(
			assetRendererFactory
		).when(
			assetRendererFactoryLookup
		).getAssetRendererFactoryByClassName(
			className
		);
	}

	private void _whenAssetRendererGetURLDownload(
		AssetRenderer<?> assetRenderer, String urlDownload) {

		Mockito.doReturn(
			urlDownload
		).when(
			assetRenderer
		).getURLDownload(
			themeDisplay
		);
	}

	private void _whenGroupLocalServiceGetGroup(boolean stagingGroup)
		throws Exception {

		Mockito.doReturn(
			stagingGroup
		).when(
			group
		).isStagingGroup();
	}

	private void _whenIndexerRegistryGetIndexer(
		String className, Indexer<?> indexer) {

		Mockito.doReturn(
			indexer
		).when(
			indexerRegistry
		).getIndexer(
			className
		);
	}

	private void _whenLanguageGet(String string) {
		Mockito.doReturn(
			string
		).when(
			language
		).get(
			Mockito.any(HttpServletRequest.class), Mockito.anyString()
		);
	}

	private static final String _SUMMARY_CONTENT =
		RandomTestUtil.randomString();

	private static final String _SUMMARY_TITLE = RandomTestUtil.randomString();

}