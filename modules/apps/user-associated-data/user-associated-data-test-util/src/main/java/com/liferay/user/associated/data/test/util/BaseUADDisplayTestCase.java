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

package com.liferay.user.associated.data.test.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.user.associated.data.display.UADDisplay;

import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Noah Sherrill
 */
public abstract class BaseUADDisplayTestCase<T> {

	@Before
	public void setUp() throws Exception {
		user = UserTestUtil.addUser();

		_uadDisplay = getUADDisplay();
	}

	@Test
	public void testCount() throws Exception {
		addBaseModel(user.getUserId());

		Assert.assertEquals(1, _uadDisplay.count(user.getUserId()));
	}

	@Test
	public void testGetAllByStatusByUserId() throws Exception {
		Assume.assumeTrue(this instanceof WhenHasStatusByUserIdField);

		WhenHasStatusByUserIdField<T> whenHasStatusByUserIdField =
			(WhenHasStatusByUserIdField)this;

		T baseModel = whenHasStatusByUserIdField.addBaseModelWithStatusByUserId(
			TestPropsValues.getUserId(), user.getUserId());

		List<T> baseModels = _uadDisplay.getRange(
			user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(baseModels.toString(), 1, baseModels.size());

		Assert.assertEquals(baseModel, baseModels.get(0));
	}

	@Test
	public void testGetAllWithNoBaseModel() throws Exception {
		Assert.assertEquals(0, _uadDisplay.count(user.getUserId()));
	}

	@Test
	public void testGetTypeName() {
		Assert.assertTrue(
			"The type name should not be null",
			Validator.isNotNull(_uadDisplay.getTypeName(LocaleUtil.US)));
	}

	protected abstract BaseModel<?> addBaseModel(long userId) throws Exception;

	protected abstract UADDisplay<T> getUADDisplay();

	@DeleteAfterTestRun
	protected User user;

	private UADDisplay<T> _uadDisplay;

}