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

package com.liferay.content.dashboard.web.internal.data.provider;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyConstants;
import com.liferay.content.dashboard.web.internal.model.AssetCategoryMetric;
import com.liferay.content.dashboard.web.internal.model.AssetVocabularyMetric;
import com.liferay.content.dashboard.web.internal.search.request.ContentDashboardSearchContextBuilder;
import com.liferay.content.dashboard.web.internal.searcher.ContentDashboardSearchRequestBuilderFactory;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.FilterAggregation;
import com.liferay.portal.search.aggregation.bucket.FilterAggregationResult;
import com.liferay.portal.search.aggregation.bucket.IncludeExcludeClause;
import com.liferay.portal.search.aggregation.bucket.Order;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author David Arques
 */
public class ContentDashboardDataProvider {

	public ContentDashboardDataProvider(
		Aggregations aggregations,
		ContentDashboardSearchContextBuilder
			contentDashboardSearchContextBuilder,
		ContentDashboardSearchRequestBuilderFactory
			contentDashboardSearchRequestBuilderFactory,
		Locale locale, Queries queries, ResourceBundle resourceBundle,
		Searcher searcher) {

		_aggregations = aggregations;
		_locale = locale;
		_queries = queries;
		_resourceBundle = resourceBundle;
		_searcher = searcher;

		_searchRequestBuilder =
			contentDashboardSearchRequestBuilderFactory.builder(
				contentDashboardSearchContextBuilder.build());
	}

	public AssetVocabularyMetric getAssetVocabularyMetric(
		List<AssetVocabulary> assetVocabularies) {

		if (ListUtil.isEmpty(assetVocabularies)) {
			return AssetVocabularyMetric.empty();
		}

		if (assetVocabularies.size() == 1) {
			return _getAssetVocabularyMetric(assetVocabularies.get(0));
		}

		return _getAssetVocabularyMetric(
			assetVocabularies.get(0), assetVocabularies.get(1));
	}

	private Map<String, String> _getAssetCategoryTitlesMap(
		AssetVocabulary assetVocabulary, Locale locale) {

		List<AssetCategory> categories = assetVocabulary.getCategories();

		Stream<AssetCategory> stream = categories.stream();

		return stream.collect(
			Collectors.toMap(
				entry -> String.valueOf(entry.getCategoryId()),
				entry -> entry.getTitle(locale)));
	}

	private String _getAssetVocabularyField(AssetVocabulary assetVocabulary) {
		if ((assetVocabulary != null) &&
			(assetVocabulary.getVisibilityType() ==
				AssetVocabularyConstants.VISIBILITY_TYPE_INTERNAL)) {

			return Field.ASSET_INTERNAL_CATEGORY_IDS;
		}

		return Field.ASSET_CATEGORY_IDS;
	}

	private AssetVocabularyMetric _getAssetVocabularyMetric(
		AssetVocabulary assetVocabulary) {

		Map<String, String> assetCategoryTitlesMap = _getAssetCategoryTitlesMap(
			assetVocabulary, _locale);

		return _toAssetVocabularyMetric(
			assetCategoryTitlesMap, assetVocabulary,
			_getBuckets(
				_getTermsAggregation(
					assetVocabulary, assetCategoryTitlesMap.keySet(),
					"categories"),
				"categories"));
	}

	private AssetVocabularyMetric _getAssetVocabularyMetric(
		AssetVocabulary assetVocabulary, AssetVocabulary childAssetVocabulary) {

		Map<String, String> assetCategoryTitlesMap = _getAssetCategoryTitlesMap(
			assetVocabulary, _locale);

		Map<String, String> childAssetCategoryTitlesMap =
			_getAssetCategoryTitlesMap(childAssetVocabulary, _locale);

		FilterAggregation childFilterAggregation = _aggregations.filter(
			"childNoneCategory",
			_getFilterBooleanQuery(
				childAssetCategoryTitlesMap.keySet(),
				_getAssetVocabularyField(childAssetVocabulary),
				assetCategoryTitlesMap.keySet(),
				_getAssetVocabularyField(assetVocabulary)));

		childFilterAggregation.addChildAggregation(
			_getTermsAggregation(
				assetVocabulary, assetCategoryTitlesMap.keySet(),
				"childCategories"));

		TermsAggregation childTermsAggregation = _getTermsAggregation(
			childAssetVocabulary, childAssetCategoryTitlesMap.keySet(),
			"childCategories");

		FilterAggregation filterAggregation = _aggregations.filter(
			"noneCategory",
			_getFilterBooleanQuery(
				assetCategoryTitlesMap.keySet(),
				_getAssetVocabularyField(assetVocabulary),
				childAssetCategoryTitlesMap.keySet(),
				_getAssetVocabularyField(childAssetVocabulary)));

		filterAggregation.addChildAggregation(childTermsAggregation);

		TermsAggregation termsAggregation = _getTermsAggregation(
			assetVocabulary, assetCategoryTitlesMap.keySet(), "categories");

		termsAggregation.addChildAggregation(
			_getTermsAggregation(
				childAssetVocabulary, childAssetCategoryTitlesMap.keySet(), 0,
				"childCategories"));

		SearchResponse searchResponse = _searcher.search(
			_searchRequestBuilder.addAggregation(
				childFilterAggregation
			).addAggregation(
				childTermsAggregation
			).addAggregation(
				filterAggregation
			).addAggregation(
				termsAggregation
			).size(
				0
			).build());

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)searchResponse.getAggregationResult(
				"categories");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		TermsAggregationResult childTermsAggregationResult =
			(TermsAggregationResult)searchResponse.getAggregationResult(
				"childCategories");

		AssetVocabularyMetric childAssetVocabularyMetric =
			_toAssetVocabularyMetric(
				childAssetCategoryTitlesMap, childAssetVocabulary,
				childTermsAggregationResult.getBuckets());

		if (buckets.isEmpty()) {
			return childAssetVocabularyMetric;
		}

		List<AssetCategoryMetric> childAssetCategoryMetrics =
			childAssetVocabularyMetric.getAssetCategoryMetrics();

		if (childAssetCategoryMetrics.isEmpty()) {
			return _toAssetVocabularyMetric(
				assetCategoryTitlesMap, assetVocabulary, buckets);
		}

		Stream<AssetCategoryMetric> stream = childAssetCategoryMetrics.stream();

		List<AssetCategoryMetric> assetCategoryMetrics =
			_toAssetCategoryMetrics(
				assetCategoryTitlesMap, buckets,
				stream.map(
					AssetCategoryMetric::getKey
				).collect(
					Collectors.toSet()
				),
				childAssetCategoryTitlesMap, childAssetVocabulary,
				_getChildNoneAssetCategoryMetricCounts(
					(FilterAggregationResult)
						searchResponse.getAggregationResult(
							"childNoneCategory")),
				"childCategories");

		FilterAggregationResult filterAggregationResult =
			(FilterAggregationResult)searchResponse.getAggregationResult(
				"noneCategory");

		if (filterAggregationResult.getDocCount() > 0) {
			assetCategoryMetrics.add(
				_getNoneAssetCategoryMetric(
					assetVocabulary, childAssetVocabulary,
					childAssetCategoryTitlesMap, filterAggregationResult));
		}

		return new AssetVocabularyMetric(
			String.valueOf(assetVocabulary.getVocabularyId()),
			assetVocabulary.getTitle(_locale), assetCategoryMetrics);
	}

	private Collection<Bucket> _getBuckets(
		TermsAggregation termsAggregation, String termsAggregationName) {

		SearchResponse searchResponse = _searcher.search(
			_searchRequestBuilder.addAggregation(
				termsAggregation
			).size(
				0
			).build());

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)searchResponse.getAggregationResult(
				termsAggregationName);

		return termsAggregationResult.getBuckets();
	}

	private Map<String, Long> _getChildNoneAssetCategoryMetricCounts(
		FilterAggregationResult filterAggregationResult) {

		if (filterAggregationResult.getDocCount() == 0) {
			return Collections.emptyMap();
		}

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)
				filterAggregationResult.getChildAggregationResult(
					"childCategories");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		Stream<Bucket> stream = buckets.stream();

		return stream.collect(
			Collectors.toMap(Bucket::getKey, Bucket::getDocCount));
	}

	private BooleanQuery _getFilterBooleanQuery(
		Set<String> mustNotAssetCategoryIds, String mustNotAssetVocabularyField,
		Set<String> shouldAssetCategoryIds, String shouldAssetVocabularyField) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		TermsQuery mustNotTermsQuery = _queries.terms(
			mustNotAssetVocabularyField);

		mustNotTermsQuery.addValues(
			(Object[])ArrayUtil.toStringArray(mustNotAssetCategoryIds));

		booleanQuery.addMustNotQueryClauses(mustNotTermsQuery);

		TermsQuery shouldTermsQuery = _queries.terms(
			shouldAssetVocabularyField);

		shouldTermsQuery.addValues(
			(Object[])ArrayUtil.toStringArray(shouldAssetCategoryIds));

		booleanQuery.addShouldQueryClauses(shouldTermsQuery);

		booleanQuery.setMinimumShouldMatch(1);

		return booleanQuery;
	}

	private AssetCategoryMetric _getNoneAssetCategoryMetric(
		AssetVocabulary assetVocabulary, AssetVocabulary childAssetVocabulary,
		Map<String, String> childAssetCategoryTitlesMap,
		FilterAggregationResult filterAggregationResult) {

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)
				filterAggregationResult.getChildAggregationResult(
					"childCategories");

		return new AssetCategoryMetric(
			_toAssetVocabularyMetric(
				childAssetCategoryTitlesMap, childAssetVocabulary,
				termsAggregationResult.getBuckets()),
			"none",
			ResourceBundleUtil.getString(
				_resourceBundle, "no-x-specified",
				assetVocabulary.getTitle(_locale)),
			filterAggregationResult.getDocCount());
	}

	private TermsAggregation _getTermsAggregation(
		AssetVocabulary assetVocabulary, Set<String> assetCategoryIds,
		int minDocCount, String termsAggregationName) {

		TermsAggregation termsAggregation = _getTermsAggregation(
			assetVocabulary, assetCategoryIds, termsAggregationName);

		termsAggregation.setMinDocCount(minDocCount);

		return termsAggregation;
	}

	private TermsAggregation _getTermsAggregation(
		AssetVocabulary assetVocabulary, Set<String> assetCategoryIds,
		String termsAggregationName) {

		TermsAggregation termsAggregation = _aggregations.terms(
			termsAggregationName, _getAssetVocabularyField(assetVocabulary));

		termsAggregation.addOrders(Order.key(true));

		termsAggregation.setIncludeExcludeClause(
			new IncludeExcludeClauseImpl(
				assetCategoryIds.toArray(new String[0]), new String[0]));

		return termsAggregation;
	}

	private List<AssetCategoryMetric> _toAssetCategoryMetrics(
		Map<String, String> assetCategoryTitlesMap, Collection<Bucket> buckets,
		Set<String> childAssetCategoryMetricKeys,
		Map<String, String> childAssetCategoryTitlesMap,
		AssetVocabulary childAssetVocabulary,
		Map<String, Long> childNoneAssetCategoryMetricCounts,
		String termsAggregationName) {

		Stream<Bucket> stream = buckets.stream();

		return stream.map(
			bucket -> {
				TermsAggregationResult termsAggregationResult =
					(TermsAggregationResult)bucket.getChildAggregationResult(
						termsAggregationName);

				return new AssetCategoryMetric(
					_toAssetVocabularyMetric(
						childAssetCategoryTitlesMap, childAssetVocabulary,
						termsAggregationResult.getBuckets(),
						childAssetCategoryMetricKeys,
						childNoneAssetCategoryMetricCounts.get(
							bucket.getKey())),
					bucket.getKey(),
					assetCategoryTitlesMap.get(bucket.getKey()),
					bucket.getDocCount());
			}
		).collect(
			Collectors.toList()
		);
	}

	private AssetVocabularyMetric _toAssetVocabularyMetric(
		Map<String, String> assetCategoryTitlesMap,
		AssetVocabulary assetVocabulary, Collection<Bucket> buckets) {

		Stream<Bucket> stream = buckets.stream();

		return new AssetVocabularyMetric(
			String.valueOf(assetVocabulary.getVocabularyId()),
			assetVocabulary.getTitle(_locale),
			stream.map(
				bucket -> new AssetCategoryMetric(
					bucket.getKey(),
					assetCategoryTitlesMap.get(bucket.getKey()),
					bucket.getDocCount())
			).collect(
				Collectors.toList()
			));
	}

	private AssetVocabularyMetric _toAssetVocabularyMetric(
		Map<String, String> assetCategoryTitlesMap,
		AssetVocabulary assetVocabulary, Collection<Bucket> buckets,
		Set<String> childAssetCategoryMetricKeys,
		Long noneAssetCategoryMetricCount) {

		Stream<Bucket> stream = buckets.stream();

		List<AssetCategoryMetric> assetCategoryMetrics = stream.filter(
			bucket -> childAssetCategoryMetricKeys.contains(bucket.getKey())
		).map(
			bucket -> new AssetCategoryMetric(
				bucket.getKey(), assetCategoryTitlesMap.get(bucket.getKey()),
				bucket.getDocCount())
		).collect(
			Collectors.toList()
		);

		if (noneAssetCategoryMetricCount != null) {
			assetCategoryMetrics.add(
				new AssetCategoryMetric(
					"none",
					ResourceBundleUtil.getString(
						_resourceBundle, "no-x-specified",
						assetVocabulary.getTitle(_locale)),
					noneAssetCategoryMetricCount));
		}

		return new AssetVocabularyMetric(
			String.valueOf(assetVocabulary.getVocabularyId()),
			assetVocabulary.getTitle(_locale), assetCategoryMetrics);
	}

	private final Aggregations _aggregations;
	private final Locale _locale;
	private final Queries _queries;
	private final ResourceBundle _resourceBundle;
	private final Searcher _searcher;
	private final SearchRequestBuilder _searchRequestBuilder;

	private static class IncludeExcludeClauseImpl
		implements IncludeExcludeClause {

		public IncludeExcludeClauseImpl(
			String includeRegex, String excludeRegex) {

			_includeRegex = includeRegex;
			_excludeRegex = excludeRegex;
		}

		public IncludeExcludeClauseImpl(
			String[] includedValues, String[] excludedValues) {

			_includedValues = includedValues;
			_excludedValues = excludedValues;
		}

		@Override
		public String[] getExcludedValues() {
			return _excludedValues;
		}

		@Override
		public String getExcludeRegex() {
			return _excludeRegex;
		}

		@Override
		public String[] getIncludedValues() {
			return _includedValues;
		}

		@Override
		public String getIncludeRegex() {
			return _includeRegex;
		}

		private String[] _excludedValues;
		private String _excludeRegex;
		private String[] _includedValues;
		private String _includeRegex;

	}

}