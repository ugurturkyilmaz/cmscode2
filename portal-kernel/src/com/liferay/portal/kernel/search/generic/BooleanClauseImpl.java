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

package com.liferay.portal.kernel.search.generic;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;

/**
 * @author Michael C. Han
 */
public class BooleanClauseImpl<T> implements BooleanClause<T> {

	public BooleanClauseImpl(T t, BooleanClauseOccur booleanClauseOccur) {
		_t = t;
		_booleanClauseOccur = booleanClauseOccur;
	}

	@Override
	public BooleanClauseOccur getBooleanClauseOccur() {
		return _booleanClauseOccur;
	}

	@Override
	public T getClause() {
		return _t;
	}

	@Override
	public String toString() {
		return StringBundler.concat("{", _booleanClauseOccur, "(", _t, ")}");
	}

	private final BooleanClauseOccur _booleanClauseOccur;
	private final T _t;

}