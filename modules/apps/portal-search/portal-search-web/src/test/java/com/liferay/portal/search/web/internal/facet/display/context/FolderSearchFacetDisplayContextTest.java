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

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.DefaultTermCollector;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.internal.facet.display.context.builder.FolderSearchFacetDisplayContextBuilder;
import com.liferay.portal.search.web.internal.folder.facet.configuration.FolderFacetPortletInstanceConfiguration;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;

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
 */
public class FolderSearchFacetDisplayContextTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.doReturn(
			_facetCollector
		).when(
			_facet
		).getFacetCollector();
	}

	@Test
	public void testEmptySearchResults() throws Exception {
		String facetParam = null;

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts =
				folderSearchFacetDisplayContext.
					getFolderSearchFacetTermDisplayContexts();

		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(), 0,
			folderSearchFacetTermDisplayContexts.size());

		Assert.assertEquals(
			StringPool.BLANK,
			folderSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(folderSearchFacetDisplayContext.isNothingSelected());
		Assert.assertTrue(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithEmptyTermCollectors()
		throws Exception {

		Mockito.when(
			_facetCollector.getTermCollectors()
		).thenReturn(
			Collections.emptyList()
		);

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(null);

		Assert.assertTrue(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithPreviousSelection() throws Exception {
		long folderId = RandomTestUtil.randomLong();
		String title = RandomTestUtil.randomString();

		_addFolder(folderId, title);

		String facetParam = String.valueOf(folderId);

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts =
				folderSearchFacetDisplayContext.
					getFolderSearchFacetTermDisplayContexts();

		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(), 1,
			folderSearchFacetTermDisplayContexts.size());

		FolderSearchFacetTermDisplayContext
			folderSearchFacetTermDisplayContext =
				folderSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			0, folderSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			title, folderSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			folderId, folderSearchFacetTermDisplayContext.getFolderId());
		Assert.assertTrue(folderSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			folderSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, folderSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(folderSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithUnmatchedTermCollector()
		throws Exception {

		Mockito.when(
			_facetCollector.getTermCollectors()
		).thenReturn(
			Arrays.asList(new DefaultTermCollector("0", 200))
		);

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(null);

		Assert.assertTrue(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTerm() throws Exception {
		long folderId = RandomTestUtil.randomLong();
		String title = RandomTestUtil.randomString();

		_addFolder(folderId, title);

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(folderId, count);

		String facetParam = "";

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts =
				folderSearchFacetDisplayContext.
					getFolderSearchFacetTermDisplayContexts();

		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(), 1,
			folderSearchFacetTermDisplayContexts.size());

		FolderSearchFacetTermDisplayContext
			folderSearchFacetTermDisplayContext =
				folderSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, folderSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			title, folderSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			folderId, folderSearchFacetTermDisplayContext.getFolderId());
		Assert.assertFalse(folderSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			folderSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, folderSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(folderSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTermWithPreviousSelection() throws Exception {
		long folderId = RandomTestUtil.randomLong();
		String title = RandomTestUtil.randomString();

		_addFolder(folderId, title);

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(folderId, count);

		String facetParam = String.valueOf(folderId);

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(facetParam);

		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts =
				folderSearchFacetDisplayContext.
					getFolderSearchFacetTermDisplayContexts();

		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(), 1,
			folderSearchFacetTermDisplayContexts.size());

		FolderSearchFacetTermDisplayContext
			folderSearchFacetTermDisplayContext =
				folderSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, folderSearchFacetTermDisplayContext.getFrequency());
		Assert.assertEquals(
			title, folderSearchFacetTermDisplayContext.getDisplayName());
		Assert.assertEquals(
			folderId, folderSearchFacetTermDisplayContext.getFolderId());
		Assert.assertTrue(folderSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(
			folderSearchFacetTermDisplayContext.isFrequencyVisible());

		Assert.assertEquals(
			facetParam, folderSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(folderSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(folderSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testViewPermissionGrantedForSearchResultButDeniedForParentFolder()
		throws Exception {

		List<TermCollector> termCollectors = _addFoldersAndCreateTermCollectors(
			"zeroFolderId", null, "null", "", "   ", "assert", "volatile",
			"alpha");

		_setUpMultipleTermCollectors(termCollectors);

		FolderSearchFacetDisplayContext folderSearchFacetDisplayContext =
			createDisplayContext(null);

		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts =
				folderSearchFacetDisplayContext.
					getFolderSearchFacetTermDisplayContexts();

		String nameFrequencyString = _buildNameFrequencyString(
			folderSearchFacetTermDisplayContexts);

		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(),
			"assert:6|volatile:7|alpha:8", nameFrequencyString);

		Assert.assertEquals(
			termCollectors.toString(), 36,
			_getTotalTermCollectorFrequencyCount(termCollectors));
		Assert.assertEquals(
			folderSearchFacetTermDisplayContexts.toString(), 21,
			_getTotalFolderSearchFacetTermDisplayContextFrequencyCount(
				folderSearchFacetTermDisplayContexts));
	}

	protected FolderSearchFacetDisplayContext createDisplayContext(
			String facetParam)
		throws Exception {

		FolderSearchFacetDisplayContextBuilder
			folderSearchFacetDisplayContextBuilder =
				new FolderSearchFacetDisplayContextBuilder(getRenderRequest());

		folderSearchFacetDisplayContextBuilder.setFacet(_facet);
		folderSearchFacetDisplayContextBuilder.setFolderTitleLookup(
			_folderTitleLookup);
		folderSearchFacetDisplayContextBuilder.setFrequenciesVisible(true);
		folderSearchFacetDisplayContextBuilder.setFrequencyThreshold(0);
		folderSearchFacetDisplayContextBuilder.setMaxTerms(0);
		folderSearchFacetDisplayContextBuilder.setParameterName(
			_facet.getFieldId());
		folderSearchFacetDisplayContextBuilder.setParameterValue(facetParam);

		return folderSearchFacetDisplayContextBuilder.build();
	}

	protected TermCollector createTermCollector(long folderId, int count) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			count
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			String.valueOf(folderId)
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	protected PortletDisplay getPortletDisplay() throws ConfigurationException {
		PortletDisplay portletDisplay = Mockito.mock(PortletDisplay.class);

		Mockito.doReturn(
			Mockito.mock(FolderFacetPortletInstanceConfiguration.class)
		).when(
			portletDisplay
		).getPortletInstanceConfiguration(
			Matchers.any()
		);

		return portletDisplay;
	}

	protected RenderRequest getRenderRequest() throws ConfigurationException {
		RenderRequest renderRequest = Mockito.mock(RenderRequest.class);

		Mockito.doReturn(
			getThemeDisplay()
		).when(
			renderRequest
		).getAttribute(
			WebKeys.THEME_DISPLAY
		);

		return renderRequest;
	}

	protected ThemeDisplay getThemeDisplay() throws ConfigurationException {
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);

		Mockito.doReturn(
			getPortletDisplay()
		).when(
			themeDisplay
		).getPortletDisplay();

		return themeDisplay;
	}

	protected void setUpOneTermCollector(long folderId, int count) {
		Mockito.doReturn(
			Collections.singletonList(createTermCollector(folderId, count))
		).when(
			_facetCollector
		).getTermCollectors();
	}

	private void _addFolder(long folderId, String title) throws Exception {
		Mockito.doReturn(
			title
		).when(
			_folderTitleLookup
		).getFolderTitle(
			folderId
		);
	}

	private List<TermCollector> _addFoldersAndCreateTermCollectors(
			String... folderNames)
		throws Exception {

		List<TermCollector> termCollectors = new ArrayList<>();

		int folderId = 0;

		for (String folderName : folderNames) {
			_addFolder(folderId, folderName);

			int frequency = folderId + 1;

			termCollectors.add(createTermCollector(folderId, frequency));

			folderId++;
		}

		return termCollectors;
	}

	private String _buildNameFrequencyString(
			List<FolderSearchFacetTermDisplayContext>
				folderSearchFacetTermDisplayContexts)
		throws Exception {

		StringBundler sb = new StringBundler(
			folderSearchFacetTermDisplayContexts.size() * 4);

		for (FolderSearchFacetTermDisplayContext
				folderSearchFacetTermDisplayContext :
					folderSearchFacetTermDisplayContexts) {

			sb.append(folderSearchFacetTermDisplayContext.getDisplayName());
			sb.append(StringPool.COLON);
			sb.append(folderSearchFacetTermDisplayContext.getFrequency());
			sb.append(StringPool.PIPE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private int _getTotalFolderSearchFacetTermDisplayContextFrequencyCount(
		List<FolderSearchFacetTermDisplayContext>
			folderSearchFacetTermDisplayContexts) {

		int total = 0;

		for (FolderSearchFacetTermDisplayContext
				folderSearchFacetTermDisplayContext :
					folderSearchFacetTermDisplayContexts) {

			total += folderSearchFacetTermDisplayContext.getFrequency();
		}

		return total;
	}

	private int _getTotalTermCollectorFrequencyCount(
		List<TermCollector> termCollectors) {

		int total = 0;

		for (TermCollector termCollector : termCollectors) {
			total += termCollector.getFrequency();
		}

		return total;
	}

	private void _setUpMultipleTermCollectors(
		List<TermCollector> termCollectors) {

		Mockito.doReturn(
			termCollectors
		).when(
			_facetCollector
		).getTermCollectors();
	}

	@Mock
	private Facet _facet;

	@Mock
	private FacetCollector _facetCollector;

	@Mock
	private FolderTitleLookup _folderTitleLookup;

}