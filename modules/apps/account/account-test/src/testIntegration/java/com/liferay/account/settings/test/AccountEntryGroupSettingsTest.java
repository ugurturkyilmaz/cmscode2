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

package com.liferay.account.settings.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.exception.AccountEntryTypeException;
import com.liferay.account.settings.AccountEntryGroupSettings;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Drew Brokke
 */
@RunWith(Arquillian.class)
public class AccountEntryGroupSettingsTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testGetDefaultAllowedTypes() {
		_assertAllowedTypes(
			AccountConstants.ACCOUNT_ENTRY_TYPES_DEFAULT_ALLOWED_TYPES);
	}

	@Test
	public void testSetAllowedTypes() throws Exception {
		String[] expectedAllowedTypes = {
			AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS
		};

		_setAllowedTypes(expectedAllowedTypes);

		_assertAllowedTypes(expectedAllowedTypes);
	}

	@Test(expected = AccountEntryTypeException.class)
	public void testSetAllowedTypesEmptyArray() throws Exception {
		_setAllowedTypes(new String[0]);
	}

	@Test(expected = AccountEntryTypeException.class)
	public void testSetAllowedTypesGuestType() throws Exception {
		_setAllowedTypes(
			new String[] {AccountConstants.ACCOUNT_ENTRY_TYPE_GUEST});
	}

	@Test(expected = AccountEntryTypeException.class)
	public void testSetAllowedTypesInvalidType() throws Exception {
		_setAllowedTypes(
			new String[] {AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS, "foo"});
	}

	@Test
	public void testSetAllowedTypesNull() throws Exception {
		_setAllowedTypes(
			new String[] {AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS});
		_setAllowedTypes(null);

		_assertAllowedTypes(
			AccountConstants.ACCOUNT_ENTRY_TYPES_DEFAULT_ALLOWED_TYPES);
	}

	private void _assertAllowedTypes(String[] expectedAllowedTypes) {
		String[] allowedTypes = _getAllowedTypes();

		Assert.assertArrayEquals(
			Arrays.toString(allowedTypes),
			ArrayUtil.sortedUnique(expectedAllowedTypes),
			ArrayUtil.sortedUnique(allowedTypes));
	}

	private String[] _getAllowedTypes() {
		return _accountEntryGroupSettings.getAllowedTypes(_group.getGroupId());
	}

	private void _setAllowedTypes(String[] allowedTypes) throws Exception {
		_accountEntryGroupSettings.setAllowedTypes(
			_group.getGroupId(), allowedTypes);

		// Force async operations to complete before returning

		ConfigurationTestUtil.saveConfiguration(
			RandomTestUtil.randomString(), null);
	}

	@Inject
	private AccountEntryGroupSettings _accountEntryGroupSettings;

	private Group _group;

}