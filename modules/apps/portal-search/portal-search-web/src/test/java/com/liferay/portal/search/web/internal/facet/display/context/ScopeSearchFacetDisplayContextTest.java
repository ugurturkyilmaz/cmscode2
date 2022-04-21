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

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.internal.facet.display.context.builder.ScopeSearchFacetDisplayContextBuilder;
import com.liferay.portal.search.web.internal.site.facet.configuration.SiteFacetPortletInstanceConfiguration;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
 * @author André de Oliveira
 */
public class ScopeSearchFacetDisplayContextTest {

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
		String parameterValue = "0";

		ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext =
			createDisplayContext(parameterValue);

		List<ScopeSearchFacetTermDisplayContext>
			scopeSearchFacetTermDisplayContexts =
				scopeSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			scopeSearchFacetTermDisplayContexts.toString(), 0,
			scopeSearchFacetTermDisplayContexts.size());

		Assert.assertEquals(
			parameterValue, scopeSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(scopeSearchFacetDisplayContext.isNothingSelected());
		Assert.assertTrue(scopeSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testEmptySearchResultsWithPreviousSelection() throws Exception {
		long groupId = RandomTestUtil.randomLong();
		String name = RandomTestUtil.randomString();

		_addGroup(groupId, name);

		String parameterValue = String.valueOf(groupId);

		ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext =
			createDisplayContext(parameterValue);

		List<ScopeSearchFacetTermDisplayContext>
			scopeSearchFacetTermDisplayContexts =
				scopeSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			scopeSearchFacetTermDisplayContexts.toString(), 1,
			scopeSearchFacetTermDisplayContexts.size());

		ScopeSearchFacetTermDisplayContext scopeSearchFacetTermDisplayContext =
			scopeSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(0, scopeSearchFacetTermDisplayContext.getCount());
		Assert.assertEquals(
			name, scopeSearchFacetTermDisplayContext.getDescriptiveName());
		Assert.assertEquals(
			groupId, scopeSearchFacetTermDisplayContext.getGroupId());
		Assert.assertTrue(scopeSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(scopeSearchFacetTermDisplayContext.isShowCount());

		Assert.assertEquals(
			parameterValue, scopeSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(scopeSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(scopeSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTerm() throws Exception {
		long groupId = RandomTestUtil.randomLong();
		String name = RandomTestUtil.randomString();

		_addGroup(groupId, name);

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(groupId, count);

		String parameterValue = "0";

		ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext =
			createDisplayContext(parameterValue);

		List<ScopeSearchFacetTermDisplayContext>
			scopeSearchFacetTermDisplayContexts =
				scopeSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			scopeSearchFacetTermDisplayContexts.toString(), 1,
			scopeSearchFacetTermDisplayContexts.size());

		ScopeSearchFacetTermDisplayContext scopeSearchFacetTermDisplayContext =
			scopeSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, scopeSearchFacetTermDisplayContext.getCount());
		Assert.assertEquals(
			name, scopeSearchFacetTermDisplayContext.getDescriptiveName());
		Assert.assertEquals(
			groupId, scopeSearchFacetTermDisplayContext.getGroupId());
		Assert.assertFalse(scopeSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(scopeSearchFacetTermDisplayContext.isShowCount());

		Assert.assertEquals(
			parameterValue, scopeSearchFacetDisplayContext.getParameterValue());
		Assert.assertTrue(scopeSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(scopeSearchFacetDisplayContext.isRenderNothing());
	}

	@Test
	public void testOneTermWithPreviousSelection() throws Exception {
		long groupId = RandomTestUtil.randomLong();
		String name = RandomTestUtil.randomString();

		_addGroup(groupId, name);

		int count = RandomTestUtil.randomInt();

		setUpOneTermCollector(groupId, count);

		String parameterValue = String.valueOf(groupId);

		ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext =
			createDisplayContext(parameterValue);

		List<ScopeSearchFacetTermDisplayContext>
			scopeSearchFacetTermDisplayContexts =
				scopeSearchFacetDisplayContext.getTermDisplayContexts();

		Assert.assertEquals(
			scopeSearchFacetTermDisplayContexts.toString(), 1,
			scopeSearchFacetTermDisplayContexts.size());

		ScopeSearchFacetTermDisplayContext scopeSearchFacetTermDisplayContext =
			scopeSearchFacetTermDisplayContexts.get(0);

		Assert.assertEquals(
			count, scopeSearchFacetTermDisplayContext.getCount());
		Assert.assertEquals(
			name, scopeSearchFacetTermDisplayContext.getDescriptiveName());
		Assert.assertEquals(
			groupId, scopeSearchFacetTermDisplayContext.getGroupId());
		Assert.assertTrue(scopeSearchFacetTermDisplayContext.isSelected());
		Assert.assertTrue(scopeSearchFacetTermDisplayContext.isShowCount());

		Assert.assertEquals(
			parameterValue, scopeSearchFacetDisplayContext.getParameterValue());
		Assert.assertFalse(scopeSearchFacetDisplayContext.isNothingSelected());
		Assert.assertFalse(scopeSearchFacetDisplayContext.isRenderNothing());
	}

	protected ScopeSearchFacetDisplayContext createDisplayContext(
			String parameterValue)
		throws ConfigurationException {

		ScopeSearchFacetDisplayContextBuilder
			scopeSearchFacetDisplayContextBuilder =
				new ScopeSearchFacetDisplayContextBuilder(getRenderRequest());

		scopeSearchFacetDisplayContextBuilder.setFacet(_facet);
		scopeSearchFacetDisplayContextBuilder.setFrequenciesVisible(true);
		scopeSearchFacetDisplayContextBuilder.setGroupLocalService(
			_groupLocalService);
		scopeSearchFacetDisplayContextBuilder.setParameterValue(parameterValue);

		return scopeSearchFacetDisplayContextBuilder.build();
	}

	protected Group createGroup(long groupId, String name) throws Exception {
		Group group = Mockito.mock(Group.class);

		Mockito.doReturn(
			name
		).when(
			group
		).getDescriptiveName(
			Mockito.<Locale>any()
		);

		Mockito.doReturn(
			groupId
		).when(
			group
		).getGroupId();

		return group;
	}

	protected TermCollector createTermCollector(long groupId, int count) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			count
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			String.valueOf(groupId)
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	protected PortletDisplay getPortletDisplay() throws ConfigurationException {
		PortletDisplay portletDisplay = Mockito.mock(PortletDisplay.class);

		Mockito.doReturn(
			Mockito.mock(SiteFacetPortletInstanceConfiguration.class)
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

	protected void setUpOneTermCollector(long groupId, int count) {
		Mockito.doReturn(
			Collections.singletonList(createTermCollector(groupId, count))
		).when(
			_facetCollector
		).getTermCollectors();
	}

	private void _addGroup(long groupId, String name) throws Exception {
		Mockito.doReturn(
			createGroup(groupId, name)
		).when(
			_groupLocalService
		).fetchGroup(
			groupId
		);
	}

	@Mock
	private Facet _facet;

	@Mock
	private FacetCollector _facetCollector;

	@Mock
	private GroupLocalService _groupLocalService;

}