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

package com.liferay.change.tracking.internal.conflict;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ResourceBundle;

/**
 * @author Preston Crary
 */
public class DefaultConstraintConflictInfo extends BaseConflictInfo {

	public DefaultConstraintConflictInfo(
		long sourcePrimaryKey, long targetPrimaryKey,
		String uniqueColumnNames) {

		_sourcePrimaryKey = sourcePrimaryKey;
		_targetPrimaryKey = targetPrimaryKey;
		_uniqueColumnNames = uniqueColumnNames;
	}

	@Override
	public String getConflictDescription(ResourceBundle resourceBundle) {
		return LanguageUtil.format(
			resourceBundle, "values-for-x-are-not-unique", _uniqueColumnNames,
			false);
	}

	@Override
	public String getResolutionDescription(ResourceBundle resourceBundle) {
		return LanguageUtil.format(
			resourceBundle, "update-values-to-be-unique", _uniqueColumnNames,
			false);
	}

	@Override
	public long getSourcePrimaryKey() {
		return _sourcePrimaryKey;
	}

	@Override
	public long getTargetPrimaryKey() {
		return _targetPrimaryKey;
	}

	private final long _sourcePrimaryKey;
	private final long _targetPrimaryKey;
	private final String _uniqueColumnNames;

}