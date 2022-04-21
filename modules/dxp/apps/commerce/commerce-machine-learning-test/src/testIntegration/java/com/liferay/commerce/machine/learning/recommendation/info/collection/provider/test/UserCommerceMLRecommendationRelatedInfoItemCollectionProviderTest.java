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

package com.liferay.commerce.machine.learning.recommendation.info.collection.provider.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.test.util.CommerceAccountTestUtil;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.machine.learning.recommendation.UserCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.UserCommerceMLRecommendationManager;
import com.liferay.commerce.product.constants.CommerceChannelConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.test.util.context.TestCommerceContext;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Riccardo Ferrari
 */
@RunWith(Arquillian.class)
public class UserCommerceMLRecommendationRelatedInfoItemCollectionProviderTest
	extends BaseItemCollectionProviderTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId());

		_commerceChannel = CommerceChannelLocalServiceUtil.addCommerceChannel(
			null, _group.getGroupId(), "Test Channel",
			CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
			_commerceCurrency.getCode(), _serviceContext);

		_commerceCatalog = addCommerceCatalog();
		_cpDefinitions = _addCPDefinitions();
		_userCommerceMLRecommendations = _addUserCommerceMLRecommendations();
	}

	@Override
	@Test
	public void testGetRelatedItemsInfoPage() throws Exception {
		int cpDefinitionIndex = RandomTestUtil.randomInt(
			1, _cpDefinitions.size() - 1);

		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendations.get(
				cpDefinitionIndex * RECOMMENDATION_COUNT);

		ServiceContextThreadLocal.pushServiceContext(
			_getServiceContext(userCommerceMLRecommendation.getEntryClassPK()));

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS, 5, TimeUnit.SECONDS,
			() -> {
				_testGetRelatedItemsInfoPage(
					_cpDefinitions.get(cpDefinitionIndex),
					RECOMMENDATION_COUNT);

				return null;
			});
	}

	@Test
	public void testGetRelatedItemsInfoPageNoCategories() throws Exception {
		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendations.get(0);

		ServiceContextThreadLocal.pushServiceContext(
			_getServiceContext(userCommerceMLRecommendation.getEntryClassPK()));

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS, 5, TimeUnit.SECONDS,
			() -> {
				_testGetRelatedItemsInfoPage(_cpDefinitions.get(0), 10);

				return null;
			});
	}

	@Test
	public void testInfoCollectionProvider() throws Exception {
		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendations.get(0);

		ServiceContextThreadLocal.pushServiceContext(
			_getServiceContext(userCommerceMLRecommendation.getEntryClassPK()));

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS, 5, TimeUnit.SECONDS,
			() -> {
				_testInfoCollectionProvider();

				return null;
			});
	}

	@Override
	protected String getInfoItemCollectionProviderName() {
		return StringBundler.concat(
			"com.liferay.commerce.machine.learning.internal.recommendation.",
			"info.collection.provider.",
			"UserCommerceMLRecommendationRelatedInfoItemCollectionProvider");
	}

	@Override
	protected CPDefinition getRandomRelatedItemObject() throws Exception {
		return null;
	}

	private List<CPDefinition> _addCPDefinitions() throws Exception {
		List<CPDefinition> cpDefinitions = new ArrayList<>();

		for (int i = 0; i < (_ACCOUNT_COUNT * PRODUCT_COUNT); i++) {
			cpDefinitions.add(
				CPTestUtil.addCPDefinition(_commerceCatalog.getGroupId()));
		}

		return cpDefinitions;
	}

	private List<UserCommerceMLRecommendation>
			_addUserCommerceMLRecommendations()
		throws Exception {

		List<UserCommerceMLRecommendation> userCommerceMLRecommendations =
			new ArrayList<>();

		for (int i = 0; i < _ACCOUNT_COUNT; i++) {
			CommerceAccount commerceAccount =
				CommerceAccountTestUtil.addBusinessCommerceAccount(
					TestPropsValues.getUserId(), RandomTestUtil.randomString(),
					RandomTestUtil.randomString() + "@liferay.com",
					RandomTestUtil.randomString(), _serviceContext);

			for (int j = 0; j < PRODUCT_COUNT; j++) {
				long[] assetCategoryIds = null;

				if ((i > 0) || (j > 0)) {
					CPDefinition cpDefinition = _cpDefinitions.get(
						(i * PRODUCT_COUNT) + j);

					AssetCategory assetCategory =
						CPTestUtil.addCategoryToCPDefinitions(
							_commerceCatalog.getGroupId(),
							cpDefinition.getCPDefinitionId());

					assetCategoryIds = new long[] {
						assetCategory.getCategoryId()
					};
				}

				for (int k = 0; k < RECOMMENDATION_COUNT; k++) {
					CPDefinition recommendedCPDefinition =
						CPTestUtil.addCPDefinition(
							_commerceCatalog.getGroupId());

					UserCommerceMLRecommendation userCommerceMLRecommendation =
						_userCommerceMLRecommendationManager.create();

					userCommerceMLRecommendation.setEntryClassPK(
						commerceAccount.getCommerceAccountId());
					userCommerceMLRecommendation.setScore(1.0F - (k / 10.0F));
					userCommerceMLRecommendation.setRecommendedEntryClassPK(
						recommendedCPDefinition.getCPDefinitionId());
					userCommerceMLRecommendation.setCreateDate(new Date());
					userCommerceMLRecommendation.setCompanyId(
						TestPropsValues.getCompanyId());
					userCommerceMLRecommendation.setAssetCategoryIds(
						assetCategoryIds);

					userCommerceMLRecommendations.add(
						_userCommerceMLRecommendationManager.
							addUserCommerceMLRecommendation(
								userCommerceMLRecommendation));
				}
			}
		}

		return userCommerceMLRecommendations;
	}

	private ServiceContext _getServiceContext(long commerceAccountId)
		throws Exception {

		User user = TestPropsValues.getUser();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _commerceChannel, user, _group,
			_commerceAccountLocalService.getCommerceAccount(commerceAccountId),
			null);

		mockHttpServletRequest.setAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

		_serviceContext.setRequest(mockHttpServletRequest);

		return _serviceContext;
	}

	private void _testGetRelatedItemsInfoPage(
		CPDefinition cpDefinition, int expectedItemCount) {

		RelatedInfoItemCollectionProvider<CPDefinition, CPDefinition>
			relatedInfoItemCollectionProvider =
				infoItemServiceTracker.getInfoItemService(
					RelatedInfoItemCollectionProvider.class,
					getInfoItemCollectionProviderName());

		Assert.assertNotNull(relatedInfoItemCollectionProvider);

		CollectionQuery collectionQuery = new CollectionQuery();

		collectionQuery.setRelatedItemObject(cpDefinition);

		InfoPage<CPDefinition> relatedItemsInfoPage =
			relatedInfoItemCollectionProvider.getCollectionInfoPage(
				collectionQuery);

		Assert.assertNotNull(relatedItemsInfoPage);

		List<? extends CPDefinition> pageItems =
			relatedItemsInfoPage.getPageItems();

		Assert.assertEquals(
			pageItems.toString(), expectedItemCount, pageItems.size());
	}

	private void _testInfoCollectionProvider() {
		InfoCollectionProvider<CPDefinition> infoCollectionProvider =
			infoItemServiceTracker.getInfoItemService(
				InfoCollectionProvider.class,
				getInfoItemCollectionProviderName());

		Assert.assertNotNull(infoCollectionProvider);

		InfoPage<CPDefinition> infoPage =
			infoCollectionProvider.getCollectionInfoPage(new CollectionQuery());

		Assert.assertNotNull(infoPage);

		List<? extends CPDefinition> pageItems = infoPage.getPageItems();

		Assert.assertEquals(pageItems.toString(), 10, pageItems.size());
	}

	private static final int _ACCOUNT_COUNT = 2;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	private CommerceCatalog _commerceCatalog;
	private CommerceChannel _commerceChannel;
	private CommerceCurrency _commerceCurrency;
	private List<CPDefinition> _cpDefinitions;
	private Group _group;
	private ServiceContext _serviceContext;

	@Inject
	private UserCommerceMLRecommendationManager
		_userCommerceMLRecommendationManager;

	private List<UserCommerceMLRecommendation> _userCommerceMLRecommendations;

}