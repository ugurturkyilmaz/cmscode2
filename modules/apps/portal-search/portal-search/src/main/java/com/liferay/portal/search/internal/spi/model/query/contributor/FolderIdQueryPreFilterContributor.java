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

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.spi.model.query.contributor.QueryPreFilterContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = QueryPreFilterContributor.class)
public class FolderIdQueryPreFilterContributor
	implements QueryPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter fullQueryBooleanFilter, SearchContext searchContext) {

		long[] folderIds = searchContext.getFolderIds();

		if (ArrayUtil.isNotEmpty(folderIds)) {
			folderIds = ArrayUtil.remove(
				folderIds, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}

		if (ArrayUtil.isEmpty(folderIds)) {
			return;
		}

		TermsFilter termsFilter = new TermsFilter(Field.TREE_PATH);

		termsFilter.addValues(ArrayUtil.toStringArray(folderIds));

		fullQueryBooleanFilter.add(termsFilter, BooleanClauseOccur.MUST);
	}

}