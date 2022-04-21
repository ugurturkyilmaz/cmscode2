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

package com.liferay.mentions.web.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mentions.constants.MentionsPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.portlet.MockLiferayResourceRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayResourceResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javax.portlet.Portlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class MentionsPortletTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_company = CompanyTestUtil.addCompany();

		User adminUser = UserTestUtil.getAdminUser(_company.getCompanyId());

		_group = GroupTestUtil.addGroup(
			_company.getCompanyId(), adminUser.getUserId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		_layout = _addLayout(_group.getGroupId(), adminUser.getUserId());
	}

	@Test
	public void testServletResponseWithoutQuery() throws Exception {
		_users.add(_addUser("example", _group.getGroupId()));

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayResourceResponse mockLiferayResourceResponse =
			new MockLiferayResourceResponse();

		mvcPortlet.serveResource(
			_getMockLiferayResourceRequest(null), mockLiferayResourceResponse);

		MockHttpServletResponse mockHttpServletResponse =
			(MockHttpServletResponse)
				mockLiferayResourceResponse.getHttpServletResponse();

		Assert.assertEquals(
			ContentTypes.APPLICATION_JSON,
			mockHttpServletResponse.getContentType());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			mockHttpServletResponse.getContentAsString());

		int companyUsersCount = _userLocalService.getCompanyUsersCount(
			_company.getCompanyId());

		Assert.assertEquals(companyUsersCount - 1, jsonArray.length());

		_assertAnyJSONObject(
			jsonArray,
			jsonObject -> Objects.equals(
				jsonObject.getString("screenName"), "example"));
	}

	@Test
	public void testServletResponseWithQueryWithFullScreenName()
		throws Exception {

		_users.add(_addUser("example", _group.getGroupId()));

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayResourceResponse mockLiferayResourceResponse =
			new MockLiferayResourceResponse();

		mvcPortlet.serveResource(
			_getMockLiferayResourceRequest("example"),
			mockLiferayResourceResponse);

		MockHttpServletResponse mockHttpServletResponse =
			(MockHttpServletResponse)
				mockLiferayResourceResponse.getHttpServletResponse();

		Assert.assertEquals(
			ContentTypes.APPLICATION_JSON,
			mockHttpServletResponse.getContentType());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			mockHttpServletResponse.getContentAsString());

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals("example", jsonObject.getString("screenName"));
	}

	@Test
	public void testServletResponseWithQueryWithPartialScreenName()
		throws Exception {

		_users.add(_addUser("example", _group.getGroupId()));

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayResourceResponse mockLiferayResourceResponse =
			new MockLiferayResourceResponse();

		mvcPortlet.serveResource(
			_getMockLiferayResourceRequest("exa"), mockLiferayResourceResponse);

		MockHttpServletResponse mockHttpServletResponse =
			(MockHttpServletResponse)
				mockLiferayResourceResponse.getHttpServletResponse();

		Assert.assertEquals(
			ContentTypes.APPLICATION_JSON,
			mockHttpServletResponse.getContentType());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			mockHttpServletResponse.getContentAsString());

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals("example", jsonObject.getString("screenName"));
	}

	@Test
	public void testServletResponseWithQueryWithWildard() throws Exception {
		_users.add(_addUser("example", _group.getGroupId()));

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayResourceResponse mockLiferayResourceResponse =
			new MockLiferayResourceResponse();

		mvcPortlet.serveResource(
			_getMockLiferayResourceRequest(""), mockLiferayResourceResponse);

		MockHttpServletResponse mockHttpServletResponse =
			(MockHttpServletResponse)
				mockLiferayResourceResponse.getHttpServletResponse();

		Assert.assertEquals(
			ContentTypes.APPLICATION_JSON,
			mockHttpServletResponse.getContentType());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			mockHttpServletResponse.getContentAsString());

		int companyUsersCount = _userLocalService.getCompanyUsersCount(
			_company.getCompanyId());

		Assert.assertEquals(companyUsersCount - 1, jsonArray.length());

		_assertAnyJSONObject(
			jsonArray,
			jsonObject -> Objects.equals(
				jsonObject.getString("screenName"), "example"));
	}

	@Test
	public void testServletResponseWithQueryWithWildcardAndNoResults()
		throws Exception {

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayResourceResponse mockLiferayResourceResponse =
			new MockLiferayResourceResponse();

		mvcPortlet.serveResource(
			_getMockLiferayResourceRequest(""), mockLiferayResourceResponse);

		MockHttpServletResponse mockHttpServletResponse =
			(MockHttpServletResponse)
				mockLiferayResourceResponse.getHttpServletResponse();

		Assert.assertEquals(
			ContentTypes.APPLICATION_JSON,
			mockHttpServletResponse.getContentType());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			mockHttpServletResponse.getContentAsString());

		int companyUsersCount = _userLocalService.getCompanyUsersCount(
			_company.getCompanyId());

		Assert.assertEquals(companyUsersCount - 1, jsonArray.length());
	}

	private Layout _addLayout(long groupId, long userId) throws Exception {
		String name = RandomTestUtil.randomString();

		String friendlyURL =
			StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(name);

		return _layoutLocalService.addLayout(
			userId, groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			name, null, RandomTestUtil.randomString(),
			LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			ServiceContextTestUtil.getServiceContext());
	}

	private User _addUser(String screenName, long... groupIds)
		throws Exception {

		User adminUser = UserTestUtil.getAdminUser(_company.getCompanyId());

		return UserTestUtil.addUser(
			_company.getCompanyId(), adminUser.getUserId(), screenName,
			LocaleUtil.getDefault(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), groupIds,
			ServiceContextTestUtil.getServiceContext());
	}

	private void _assertAnyJSONObject(
		JSONArray jsonArray, Predicate<JSONObject> predicate) {

		for (int i = 0; i < jsonArray.length(); i++) {
			if (predicate.test(jsonArray.getJSONObject(i))) {
				return;
			}
		}

		Assert.assertFalse(jsonArray.toString(), false);
	}

	private MockLiferayResourceRequest _getMockLiferayResourceRequest(
			String query)
		throws Exception {

		ThemeDisplay themeDisplay = _getThemeDisplay();

		MockLiferayResourceRequest mockLiferayResourceRequest =
			new MockLiferayResourceRequest();

		mockLiferayResourceRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);
		mockLiferayResourceRequest.setParameter(
			"discussionPortletId", themeDisplay.getPpid());

		if (query != null) {
			mockLiferayResourceRequest.setParameter("query", query);
		}

		return mockLiferayResourceRequest;
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(_group.getCompanyId()));
		themeDisplay.setLayout(_layout);
		themeDisplay.setPpid(MentionsPortletKeys.MENTIONS);
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(
			UserTestUtil.getAdminUser(_company.getCompanyId()));

		return themeDisplay;
	}

	@DeleteAfterTestRun
	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private Layout _layout;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject(filter = "javax.portlet.name=" + MentionsPortletKeys.MENTIONS)
	private Portlet _portlet;

	@Inject
	private UserLocalService _userLocalService;

	private final List<User> _users = new ArrayList<>();

}