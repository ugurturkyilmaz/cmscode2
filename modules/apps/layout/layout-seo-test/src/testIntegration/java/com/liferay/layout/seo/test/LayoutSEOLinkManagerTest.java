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

package com.liferay.layout.seo.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.seo.kernel.LayoutSEOLink;
import com.liferay.layout.seo.kernel.LayoutSEOLinkManager;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.test.util.ConfigurationTemporarySwapper;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class LayoutSEOLinkManagerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_layout = _layoutLocalService.getLayout(TestPropsValues.getPlid());

		ServiceContext serviceContext = new ServiceContext();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = _getThemeDisplay();

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		themeDisplay.setRequest(mockHttpServletRequest);

		serviceContext.setRequest(mockHttpServletRequest);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

	@Test
	public void testGetClassicLocalizedLayoutSEOLinksWithDefaultLocale()
		throws Exception {

		_testWithLayoutSEOCompanyConfiguration(
			"default-language-url",
			() -> _testWithSiteDefaultLanguage(
				_layout.getGroupId(), LocaleUtil.US,
				() -> {
					List<LayoutSEOLink> layoutSEOLinks =
						_layoutSEOLinkManager.getLocalizedLayoutSEOLinks(
							_layout, LocaleUtil.US, _CANONICAL_URL,
							_alternateURLs);

					Assert.assertEquals(
						layoutSEOLinks.toString(), _alternateURLs.size() + 2,
						layoutSEOLinks.size());

					_assertCanonicalLayoutSEOLink(
						layoutSEOLinks, _CANONICAL_URL);

					_assertAlternateLayoutSEOLinks(
						layoutSEOLinks, _alternateURLs);
				}));
	}

	@Test
	public void testGetClassicLocalizedLayoutSEOLinksWithNoDefaultLocale()
		throws Exception {

		_testWithLayoutSEOCompanyConfiguration(
			"default-language-url",
			() -> _testWithSiteDefaultLanguage(
				_layout.getGroupId(), LocaleUtil.US,
				() -> {
					List<LayoutSEOLink> layoutSEOLinks =
						_layoutSEOLinkManager.getLocalizedLayoutSEOLinks(
							_layout, LocaleUtil.SPAIN, _CANONICAL_URL,
							_alternateURLs);

					Assert.assertEquals(
						layoutSEOLinks.toString(), _alternateURLs.size() + 2,
						layoutSEOLinks.size());

					_assertCanonicalLayoutSEOLink(
						layoutSEOLinks, _CANONICAL_URL);

					_assertAlternateLayoutSEOLinks(
						layoutSEOLinks, _alternateURLs);
				}));
	}

	@Test
	public void testGetDefaultLocalizedLayoutSEOLinksWithDefaultLocale()
		throws Exception {

		_testWithLayoutSEOCompanyConfiguration(
			"localized-url",
			() -> _testWithSiteDefaultLanguage(
				_layout.getGroupId(), LocaleUtil.US,
				() -> {
					List<LayoutSEOLink> layoutSEOLinks =
						_layoutSEOLinkManager.getLocalizedLayoutSEOLinks(
							_layout, LocaleUtil.US, _CANONICAL_URL,
							_alternateURLs);

					Assert.assertEquals(
						layoutSEOLinks.toString(), _alternateURLs.size() + 2,
						layoutSEOLinks.size());

					_assertAlternateLayoutSEOLinks(
						layoutSEOLinks, _alternateURLs);
					_assertCanonicalLayoutSEOLink(
						layoutSEOLinks, _CANONICAL_URL);
				}));
	}

	@Test
	public void testGetDefaultLocalizedLayoutSEOLinksWithNoDefaultLocale()
		throws Exception {

		_testWithLayoutSEOCompanyConfiguration(
			"localized-url",
			() -> _testWithSiteDefaultLanguage(
				_layout.getGroupId(), LocaleUtil.US,
				() -> {
					List<LayoutSEOLink> layoutSEOLinks =
						_layoutSEOLinkManager.getLocalizedLayoutSEOLinks(
							_layout, LocaleUtil.SPAIN, _CANONICAL_URL,
							_alternateURLs);

					Assert.assertEquals(
						layoutSEOLinks.toString(), _alternateURLs.size() + 2,
						layoutSEOLinks.size());

					_assertAlternateLayoutSEOLinks(
						layoutSEOLinks, _alternateURLs);
					_assertCanonicalLayoutSEOLink(
						layoutSEOLinks, _alternateURLs.get(LocaleUtil.SPAIN));
				}));
	}

	private void _assertAlternateLayoutSEOLink(
		Locale locale, List<LayoutSEOLink> layoutSEOLinks,
		Map<Locale, String> alternateURLs) {

		LayoutSEOLink layoutSEOLink = _getAlternateLayoutSEOLink(
			locale, layoutSEOLinks);

		Assert.assertNotNull(layoutSEOLink);
		Assert.assertEquals(alternateURLs.get(locale), layoutSEOLink.getHref());
		Assert.assertEquals(
			LocaleUtil.toW3cLanguageId(locale), layoutSEOLink.getHrefLang());
		Assert.assertEquals(
			layoutSEOLink.getRelationship(),
			LayoutSEOLink.Relationship.ALTERNATE);
	}

	private void _assertAlternateLayoutSEOLinks(
		List<LayoutSEOLink> layoutSEOLinks, Map<Locale, String> alternateURLs) {

		for (Locale locale : alternateURLs.keySet()) {
			_assertAlternateLayoutSEOLink(
				locale, layoutSEOLinks, alternateURLs);
		}

		_assertXDefaultAlternateLayoutSEOLink(layoutSEOLinks, alternateURLs);
	}

	private void _assertCanonicalLayoutSEOLink(
		List<LayoutSEOLink> layoutSEOLinks, String canonicalURL) {

		LayoutSEOLink layoutSEOLink = _getCanonicalLayoutSEOLink(
			layoutSEOLinks);

		Assert.assertNotNull(canonicalURL);
		Assert.assertEquals(canonicalURL, layoutSEOLink.getHref());
		Assert.assertNull(layoutSEOLink.getHrefLang());
		Assert.assertEquals(
			layoutSEOLink.getRelationship(),
			LayoutSEOLink.Relationship.CANONICAL);
	}

	private void _assertXDefaultAlternateLayoutSEOLink(
		List<LayoutSEOLink> layoutSEOLinks, Map<Locale, String> alternateURLs) {

		LayoutSEOLink layoutSEOLink = _getXDefaultAlternateLayoutSEOLink(
			layoutSEOLinks);

		Assert.assertNotNull(layoutSEOLink);
		Assert.assertEquals(
			alternateURLs.get(LocaleUtil.getDefault()),
			layoutSEOLink.getHref());
		Assert.assertEquals("x-default", layoutSEOLink.getHrefLang());
		Assert.assertEquals(
			layoutSEOLink.getRelationship(),
			LayoutSEOLink.Relationship.ALTERNATE);
	}

	private LayoutSEOLink _getAlternateLayoutSEOLink(
		Locale locale, List<LayoutSEOLink> layoutSEOLinks) {

		for (LayoutSEOLink layoutSEOLink : layoutSEOLinks) {
			String hrefLang = layoutSEOLink.getHrefLang();

			if ((layoutSEOLink.getRelationship() ==
					LayoutSEOLink.Relationship.ALTERNATE) &&
				(hrefLang != null) &&
				Objects.equals(hrefLang, LocaleUtil.toW3cLanguageId(locale))) {

				return layoutSEOLink;
			}
		}

		return null;
	}

	private LayoutSEOLink _getCanonicalLayoutSEOLink(
		List<LayoutSEOLink> layoutSEOLinks) {

		for (LayoutSEOLink layoutSEOLink : layoutSEOLinks) {
			if (layoutSEOLink.getRelationship() ==
					LayoutSEOLink.Relationship.CANONICAL) {

				return layoutSEOLink;
			}
		}

		return null;
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		Company company = _companyLocalService.fetchCompany(
			TestPropsValues.getCompanyId());

		company.setVirtualHostname(RandomTestUtil.randomString());

		themeDisplay.setCompany(company);

		themeDisplay.setLayoutSet(_layout.getLayoutSet());
		themeDisplay.setPermissionChecker(
			PermissionThreadLocal.getPermissionChecker());
		themeDisplay.setPlid(_layout.getPlid());
		themeDisplay.setScopeGroupId(TestPropsValues.getGroupId());
		themeDisplay.setSecure(false);
		themeDisplay.setServerName(company.getVirtualHostname());
		themeDisplay.setServerPort(8080);
		themeDisplay.setSiteGroupId(TestPropsValues.getGroupId());

		return themeDisplay;
	}

	private LayoutSEOLink _getXDefaultAlternateLayoutSEOLink(
		List<LayoutSEOLink> layoutSEOLinks) {

		for (LayoutSEOLink layoutSEOLink : layoutSEOLinks) {
			String hrefLang = layoutSEOLink.getHrefLang();

			if ((hrefLang != null) && Objects.equals(hrefLang, "x-default")) {
				return layoutSEOLink;
			}
		}

		return null;
	}

	private void _testWithLayoutSEOCompanyConfiguration(
			String canonicalURL, UnsafeRunnable<Exception> unsafeRunnable)
		throws Exception {

		try (ConfigurationTemporarySwapper configurationTemporarySwapper =
				new ConfigurationTemporarySwapper(
					_LAYOUT_SEO_CONFIGURATION_PID,
					HashMapDictionaryBuilder.<String, Object>put(
						"canonicalURL", canonicalURL
					).build())) {

			unsafeRunnable.run();
		}
	}

	private void _testWithSiteDefaultLanguage(
			long groupId, Locale locale,
			UnsafeRunnable<Exception> unsafeRunnable)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		UnicodeProperties unicodeProperties = group.getTypeSettingsProperties();

		String oldLanguageId = unicodeProperties.getProperty("languageId");

		try {
			unicodeProperties.setProperty(
				"languageId", LocaleUtil.toLanguageId(locale));

			GroupLocalServiceUtil.updateGroup(
				groupId, unicodeProperties.toString());

			unsafeRunnable.run();
		}
		finally {
			unicodeProperties.setProperty("languageId", oldLanguageId);

			GroupLocalServiceUtil.updateGroup(
				groupId, unicodeProperties.toString());
		}
	}

	private static final String _CANONICAL_URL = "canonicalURL";

	private static final String _LAYOUT_SEO_CONFIGURATION_PID =
		"com.liferay.layout.seo.internal.configuration." +
			"LayoutSEOCompanyConfiguration";

	private final Map<Locale, String> _alternateURLs = HashMapBuilder.put(
		LocaleUtil.GERMAN, "germanURL"
	).put(
		LocaleUtil.SPAIN, "spanishURL"
	).put(
		LocaleUtil.US, _CANONICAL_URL
	).build();

	@Inject
	private CompanyLocalService _companyLocalService;

	private Layout _layout;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private LayoutSEOLinkManager _layoutSEOLinkManager;

}