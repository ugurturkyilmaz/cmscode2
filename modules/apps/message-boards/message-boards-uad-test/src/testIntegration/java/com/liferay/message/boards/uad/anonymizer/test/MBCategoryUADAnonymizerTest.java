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

package com.liferay.message.boards.uad.anonymizer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.service.MBCategoryLocalService;
import com.liferay.message.boards.uad.test.MBCategoryUADTestUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;
import com.liferay.user.associated.data.test.util.BaseUADAnonymizerTestCase;
import com.liferay.user.associated.data.test.util.WhenHasStatusByUserIdField;

import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(Arquillian.class)
public class MBCategoryUADAnonymizerTest
	extends BaseUADAnonymizerTestCase<MBCategory>
	implements WhenHasStatusByUserIdField<MBCategory> {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	public MBCategory addBaseModelWithStatusByUserId(
			long userId, long statusByUserId)
		throws Exception {

		MBCategory mbCategory =
			MBCategoryUADTestUtil.addMBCategoryWithStatusByUserId(
				_mbCategoryLocalService, userId, statusByUserId);

		_mbCategories.add(mbCategory);

		return mbCategory;
	}

	@Override
	protected MBCategory addBaseModel(long userId) throws Exception {
		return addBaseModel(userId, true);
	}

	@Override
	protected MBCategory addBaseModel(long userId, boolean deleteAfterTestRun)
		throws Exception {

		MBCategory mbCategory = MBCategoryUADTestUtil.addMBCategory(
			_mbCategoryLocalService, userId);

		if (deleteAfterTestRun) {
			_mbCategories.add(mbCategory);
		}

		return mbCategory;
	}

	@Override
	protected UADAnonymizer<MBCategory> getUADAnonymizer() {
		return _uadAnonymizer;
	}

	@Override
	protected boolean isBaseModelAutoAnonymized(long baseModelPK, User user)
		throws Exception {

		MBCategory mbCategory = _mbCategoryLocalService.getMBCategory(
			baseModelPK);

		String userName = mbCategory.getUserName();
		String statusByUserName = mbCategory.getStatusByUserName();

		if ((mbCategory.getUserId() != user.getUserId()) &&
			!userName.equals(user.getFullName()) &&
			(mbCategory.getStatusByUserId() != user.getUserId()) &&
			!statusByUserName.equals(user.getFullName())) {

			return true;
		}

		return false;
	}

	@Override
	protected boolean isBaseModelDeleted(long baseModelPK) {
		if (_mbCategoryLocalService.fetchMBCategory(baseModelPK) == null) {
			return true;
		}

		return false;
	}

	@DeleteAfterTestRun
	private final List<MBCategory> _mbCategories = new ArrayList<>();

	@Inject
	private MBCategoryLocalService _mbCategoryLocalService;

	@Inject(filter = "component.name=*.MBCategoryUADAnonymizer")
	private UADAnonymizer<MBCategory> _uadAnonymizer;

}