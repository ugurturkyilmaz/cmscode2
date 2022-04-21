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

package com.liferay.portal.search.internal.query;

import com.liferay.portal.search.geolocation.GeoDistance;
import com.liferay.portal.search.geolocation.GeoLocationPoint;
import com.liferay.portal.search.query.GeoDistanceQuery;
import com.liferay.portal.search.query.QueryVisitor;
import com.liferay.portal.search.query.geolocation.GeoValidationMethod;

/**
 * @author Michael C. Han
 */
public class GeoDistanceQueryImpl
	extends BaseQueryImpl implements GeoDistanceQuery {

	public GeoDistanceQueryImpl(
		String field, GeoLocationPoint pinGeoLocationPoint,
		GeoDistance geoDistance) {

		_field = field;
		_pinGeoLocationPoint = pinGeoLocationPoint;
		_geoDistance = geoDistance;
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visit(this);
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public GeoDistance getGeoDistance() {
		return _geoDistance;
	}

	@Override
	public GeoValidationMethod getGeoValidationMethod() {
		return _geoValidationMethod;
	}

	@Override
	public Boolean getIgnoreUnmapped() {
		return _ignoreUnmapped;
	}

	@Override
	public GeoLocationPoint getPinGeoLocationPoint() {
		return _pinGeoLocationPoint;
	}

	@Override
	public int getSortOrder() {
		return 100;
	}

	@Override
	public void setGeoValidationMethod(
		GeoValidationMethod geoValidationMethod) {

		_geoValidationMethod = geoValidationMethod;
	}

	@Override
	public void setIgnoreUnmapped(Boolean ignoreUnmapped) {
		_ignoreUnmapped = ignoreUnmapped;
	}

	private static final long serialVersionUID = 1L;

	private final String _field;
	private final GeoDistance _geoDistance;
	private GeoValidationMethod _geoValidationMethod;
	private Boolean _ignoreUnmapped;
	private final GeoLocationPoint _pinGeoLocationPoint;

}