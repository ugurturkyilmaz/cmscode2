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

package com.liferay.account.internal.model.listener.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.constants.AccountRoleConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountRole;
import com.liferay.account.model.AccountRoleTable;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountRoleLocalService;
import com.liferay.account.service.test.util.AccountEntryTestUtil;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.RequiredRoleException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Drew Brokke
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class RoleModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_company = CompanyTestUtil.addCompany();
	}

	@Test
	public void testAddAccountScopedRole() throws Exception {
		Role role = _roleLocalService.addRole(
			TestPropsValues.getUserId(), AccountRole.class.getName(),
			AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT,
			RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), RoleConstants.TYPE_ACCOUNT,
			null, null);

		AccountRole accountRole =
			_accountRoleLocalService.fetchAccountRoleByRoleId(role.getRoleId());

		Assert.assertNotNull(accountRole);
		Assert.assertEquals(role.getRoleId(), accountRole.getRoleId());
	}

	@Test
	public void testDefaultAccountRoles() throws Exception {
		String[] defaultAccountRoleNames = {
			AccountRoleConstants.REQUIRED_ROLE_NAME_ACCOUNT_ADMINISTRATOR,
			AccountRoleConstants.REQUIRED_ROLE_NAME_ACCOUNT_MEMBER
		};

		for (String roleName : defaultAccountRoleNames) {
			Role role = _roleLocalService.getRole(
				_company.getCompanyId(), roleName);

			DSLQuery dslQuery = DSLQueryFactoryUtil.countDistinct(
				AccountRoleTable.INSTANCE.accountRoleId
			).from(
				AccountRoleTable.INSTANCE
			).where(
				AccountRoleTable.INSTANCE.companyId.eq(
					_company.getCompanyId()
				).and(
					AccountRoleTable.INSTANCE.accountEntryId.eq(
						AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT
					).and(
						AccountRoleTable.INSTANCE.roleId.eq(role.getRoleId())
					)
				)
			);

			Assert.assertEquals(
				1, _accountRoleLocalService.dslQueryCount(dslQuery));
		}
	}

	@Test
	public void testDeleteAccountScopedRoleDeletesAccountRole()
		throws Exception {

		Role role = _roleLocalService.addRole(
			TestPropsValues.getUserId(), AccountRole.class.getName(),
			AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT,
			RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), RoleConstants.TYPE_ACCOUNT,
			null, null);

		Assert.assertNotNull(
			_accountRoleLocalService.fetchAccountRoleByRoleId(
				role.getRoleId()));

		_roleLocalService.deleteRole(role);

		Assert.assertNull(
			_accountRoleLocalService.fetchAccountRoleByRoleId(
				role.getRoleId()));
	}

	@Test
	public void testDeleteCompany() throws Exception {
		Company company = CompanyTestUtil.addCompany();

		List<Long> requiredRoleIds = Stream.of(
			AccountRoleConstants.REQUIRED_ROLE_NAMES
		).map(
			requiredRoleName -> _roleLocalService.fetchRole(
				company.getCompanyId(), requiredRoleName)
		).map(
			Role::getRoleId
		).collect(
			Collectors.toList()
		);

		_companyLocalService.deleteCompany(company);

		for (long requiredRoleId : requiredRoleIds) {
			Assert.assertNull(_roleLocalService.fetchRole(requiredRoleId));
		}
	}

	@Test
	public void testDeleteDefaultAccountRole() throws Exception {
		for (String requiredRoleName :
				AccountRoleConstants.REQUIRED_ROLE_NAMES) {

			try {
				_roleLocalService.deleteRole(
					_roleLocalService.getRole(
						_company.getCompanyId(), requiredRoleName));

				Assert.fail(
					"Allowed to delete default role: " + requiredRoleName);
			}
			catch (ModelListenerException modelListenerException) {
				Throwable throwable = modelListenerException.getCause();

				Assert.assertTrue(throwable instanceof RequiredRoleException);

				String message = throwable.getMessage();

				Assert.assertTrue(
					message.contains(" is a default account role"));
			}
		}
	}

	@Test(expected = ModelListenerException.class)
	public void testDeleteRole() throws Exception {
		AccountEntry accountEntry = AccountEntryTestUtil.addAccountEntry(
			_accountEntryLocalService, WorkflowConstants.STATUS_APPROVED);

		AccountRole accountRole = _accountRoleLocalService.addAccountRole(
			TestPropsValues.getUserId(), accountEntry.getAccountEntryId(),
			RandomTestUtil.randomString(), null, null);

		try {
			_roleLocalService.deleteRole(accountRole.getRoleId());

			Assert.fail(
				"Allowed to delete a role associated with an account role");
		}
		catch (ModelListenerException modelListenerException) {
			Throwable throwable = modelListenerException.getCause();

			Assert.assertTrue(throwable instanceof RequiredRoleException);

			String message = throwable.getMessage();

			Assert.assertTrue(
				message.contains(" is required by account role "));

			throw modelListenerException;
		}
	}

	private static Company _company;

	@Inject
	private AccountEntryLocalService _accountEntryLocalService;

	@Inject
	private AccountRoleLocalService _accountRoleLocalService;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private RoleLocalService _roleLocalService;

}