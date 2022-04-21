/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.machine.learning.recommendation.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.machine.learning.recommendation.UserCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.UserCommerceMLRecommendationManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Riccardo Ferrari
 */
@RunWith(Arquillian.class)
public class UserCommerceMLRecommendationManagerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_userCommerceMLRecommendations = _addUserCommerceMLRecommendations();
	}

	@Test
	public void testGetUserCommerceMLRecommendations() throws Exception {
		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendations.get(
				RandomTestUtil.randomInt(
					0, _userCommerceMLRecommendations.size() - 1));

		Stream<UserCommerceMLRecommendation>
			userCommerceMLRecommendationStream =
				_userCommerceMLRecommendations.stream();

		Comparator<UserCommerceMLRecommendation>
			userCommerceMLRecommendationComparator = Comparator.comparingDouble(
				UserCommerceMLRecommendation::getScore);

		List<UserCommerceMLRecommendation>
			expectedUserCommerceMLRecommendations =
				userCommerceMLRecommendationStream.filter(
					recommendation ->
						recommendation.getEntryClassPK() ==
							userCommerceMLRecommendation.getEntryClassPK()
				).sorted(
					userCommerceMLRecommendationComparator.reversed()
				).collect(
					Collectors.toList()
				);

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			() -> {
				_assetResultEquals(
					userCommerceMLRecommendation.getEntryClassPK(), null,
					expectedUserCommerceMLRecommendations);

				return null;
			});
	}

	@Test
	public void testGetUserCommerceMLRecommendationsContextAware()
		throws Exception {

		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendations.get(
				RandomTestUtil.randomInt(
					0, _userCommerceMLRecommendations.size() - 1));

		Stream<UserCommerceMLRecommendation>
			userCommerceMLRecommendationStream =
				_userCommerceMLRecommendations.stream();

		Comparator<UserCommerceMLRecommendation>
			userCommerceMLRecommendationComparator = Comparator.comparingDouble(
				UserCommerceMLRecommendation::getScore);

		List<UserCommerceMLRecommendation>
			expectedUserCommerceMLRecommendations =
				userCommerceMLRecommendationStream.filter(
					recommendation ->
						recommendation.getEntryClassPK() ==
							userCommerceMLRecommendation.getEntryClassPK()
				).filter(
					recommendation -> _filterAssetCategories(
						recommendation.getAssetCategoryIds(),
						userCommerceMLRecommendation.getAssetCategoryIds())
				).sorted(
					userCommerceMLRecommendationComparator.reversed()
				).collect(
					Collectors.toList()
				);

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			() -> {
				_assetResultEquals(
					userCommerceMLRecommendation.getEntryClassPK(),
					userCommerceMLRecommendation.getAssetCategoryIds(),
					expectedUserCommerceMLRecommendations);

				return null;
			});
	}

	private List<UserCommerceMLRecommendation>
			_addUserCommerceMLRecommendations()
		throws Exception {

		List<UserCommerceMLRecommendation> userCommerceMLRecommendations =
			new ArrayList<>();

		for (int i = 0; i < _USER_COUNT; i++) {
			long entryClassPK = RandomTestUtil.randomLong();

			for (int j = 0; j < _RECOMMENDATION_COUNT; j++) {
				Set<Long> assetCategoryIds = new HashSet<>();

				int assetCategoryIdsSize = RandomTestUtil.randomInt(
					1, _MAX_ASSET_CATEGORY_COUNT);

				float score = 1.0F - (j / (float)_RECOMMENDATION_COUNT);

				for (int k = 0; k <= assetCategoryIdsSize; k++) {
					assetCategoryIds.add(
						(long)RandomTestUtil.randomInt(
							1, _MAX_ASSET_CATEGORY_COUNT));
				}

				userCommerceMLRecommendations.add(
					_createUserCommerceMLRecommendation(
						ArrayUtil.toLongArray(assetCategoryIds), entryClassPK,
						score));
			}
		}

		Collections.shuffle(userCommerceMLRecommendations);

		for (UserCommerceMLRecommendation userCommerceMLRecommendation :
				userCommerceMLRecommendations) {

			_userCommerceMLRecommendationManager.
				addUserCommerceMLRecommendation(userCommerceMLRecommendation);
		}

		return userCommerceMLRecommendations;
	}

	private void _assetResultEquals(
			long expectedEntryClassPK, long[] expectedAssetCategoryIds,
			List<UserCommerceMLRecommendation>
				expectedUserCommerceMLRecommendations)
		throws PortalException {

		List<UserCommerceMLRecommendation> userCommerceMLRecommendations =
			_userCommerceMLRecommendationManager.
				getUserCommerceMLRecommendations(
					TestPropsValues.getCompanyId(), expectedEntryClassPK,
					expectedAssetCategoryIds);

		int expectedRecommendationsSize = Math.min(
			10, expectedUserCommerceMLRecommendations.size());

		Assert.assertEquals(
			"Recommendation list size", expectedRecommendationsSize,
			userCommerceMLRecommendations.size());

		for (int i = 0; i < expectedRecommendationsSize; i++) {
			UserCommerceMLRecommendation expectedUserCommerceMLRecommendation =
				expectedUserCommerceMLRecommendations.get(i);

			UserCommerceMLRecommendation userCommerceMLRecommendation =
				userCommerceMLRecommendations.get(i);

			Assert.assertEquals(
				expectedUserCommerceMLRecommendation.getEntryClassPK(),
				userCommerceMLRecommendation.getEntryClassPK());

			Assert.assertEquals(
				expectedUserCommerceMLRecommendation.
					getRecommendedEntryClassPK(),
				userCommerceMLRecommendation.getRecommendedEntryClassPK());

			Assert.assertEquals(
				expectedUserCommerceMLRecommendation.getScore(),
				userCommerceMLRecommendation.getScore(), 0.0);
		}
	}

	private UserCommerceMLRecommendation _createUserCommerceMLRecommendation(
			long[] assetCategoryIds, long entryClassPK, float score)
		throws Exception {

		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendationManager.create();

		userCommerceMLRecommendation.setAssetCategoryIds(assetCategoryIds);
		userCommerceMLRecommendation.setEntryClassPK(entryClassPK);
		userCommerceMLRecommendation.setCompanyId(
			TestPropsValues.getCompanyId());
		userCommerceMLRecommendation.setCreateDate(new Date());
		userCommerceMLRecommendation.setRecommendedEntryClassPK(
			RandomTestUtil.randomLong());
		userCommerceMLRecommendation.setScore(score);

		return userCommerceMLRecommendation;
	}

	private boolean _filterAssetCategories(
		long[] assetCategoryIds, long[] expectedAssetCategoryIds) {

		List<Long> assetCategoryIdList = ListUtil.fromArray(assetCategoryIds);

		List<Long> expectedAssetCategoryIdList = ListUtil.fromArray(
			expectedAssetCategoryIds);

		Stream<Long> expectedAssetCategoryIdStream =
			expectedAssetCategoryIdList.stream();

		return expectedAssetCategoryIdStream.allMatch(
			assetCategoryIdList::contains);
	}

	private static final int _MAX_ASSET_CATEGORY_COUNT = 5;

	private static final int _RECOMMENDATION_COUNT = 11;

	private static final int _USER_COUNT = 4;

	@Inject
	private UserCommerceMLRecommendationManager
		_userCommerceMLRecommendationManager;

	private List<UserCommerceMLRecommendation> _userCommerceMLRecommendations;

}