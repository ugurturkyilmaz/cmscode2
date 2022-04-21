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

package com.liferay.message.boards.internal.search.spi.model.query.contributor;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.service.MBCategoryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseRelatedEntryIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.RelatedEntryIndexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.message.boards.model.MBMessage",
	service = ModelPreFilterContributor.class
)
public class MBMessageModelPreFilterContributor
	implements ModelPreFilterContributor {

	public void addRelatedClassNames(
			BooleanFilter contextFilter, SearchContext searchContext)
		throws Exception {

		_relatedEntryIndexer.addRelatedClassNames(contextFilter, searchContext);
	}

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		addWorkflowStatusFilter(
			booleanFilter, modelSearchSettings, searchContext);

		boolean discussion = GetterUtil.getBoolean(
			searchContext.getAttribute("discussion"));

		booleanFilter.addRequiredTerm("discussion", discussion);

		if (searchContext.isIncludeDiscussions()) {
			try {
				addRelatedClassNames(booleanFilter, searchContext);
			}
			catch (Exception exception) {
				throw new SystemException(exception);
			}
		}

		String classNameId = GetterUtil.getString(
			searchContext.getAttribute(Field.CLASS_NAME_ID));

		if (Validator.isNotNull(classNameId)) {
			booleanFilter.addRequiredTerm(Field.CLASS_NAME_ID, classNameId);
		}

		long threadId = GetterUtil.getLong(
			(String)searchContext.getAttribute("threadId"));

		if (threadId > 0) {
			booleanFilter.addRequiredTerm("threadId", threadId);
		}

		long[] categoryIds = searchContext.getCategoryIds();

		if ((categoryIds != null) && (categoryIds.length > 0) &&
			(categoryIds[0] !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)) {

			TermsFilter categoriesTermsFilter = new TermsFilter(
				Field.CATEGORY_ID);

			for (long categoryId : categoryIds) {
				try {
					mbCategoryService.getCategory(categoryId);
				}
				catch (PortalException portalException) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Unable to get message boards category " +
								categoryId,
							portalException);
					}

					continue;
				}

				categoriesTermsFilter.addValue(String.valueOf(categoryId));
			}

			if (!categoriesTermsFilter.isEmpty()) {
				booleanFilter.add(
					categoriesTermsFilter, BooleanClauseOccur.MUST);
			}
		}
	}

	protected void addWorkflowStatusFilter(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		workflowStatusModelPreFilterContributor.contribute(
			booleanFilter, modelSearchSettings, searchContext);
	}

	@Reference
	protected MBCategoryService mbCategoryService;

	@Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
	protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

	private static final Log _log = LogFactoryUtil.getLog(
		MBMessageModelPreFilterContributor.class);

	private final RelatedEntryIndexer _relatedEntryIndexer =
		new BaseRelatedEntryIndexer();

}