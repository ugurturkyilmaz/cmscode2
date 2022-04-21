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

package com.liferay.dynamic.data.mapping.internal.search.util;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalService;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true, service = DDMFormInstanceRecordBatchReindexer.class
)
public class DDMFormInstanceRecordBatchReindexerImpl
	implements DDMFormInstanceRecordBatchReindexer {

	@Override
	public void reindex(long formInstanceId, long companyId) {
		BatchIndexingActionable batchIndexingActionable =
			indexerWriter.getBatchIndexingActionable();

		batchIndexingActionable.setAddCriteriaMethod(
			dynamicQuery -> {
				Property formInstanceIdProperty = PropertyFactoryUtil.forName(
					"formInstanceId");

				dynamicQuery.add(formInstanceIdProperty.eq(formInstanceId));
			});
		batchIndexingActionable.setCompanyId(companyId);
		batchIndexingActionable.setPerformActionMethod(
			(DDMFormInstanceRecord ddmFormInstanceRecord) ->
				batchIndexingActionable.addDocuments(
					indexerDocumentBuilder.getDocument(ddmFormInstanceRecord)));

		batchIndexingActionable.performActions();
	}

	@Reference
	protected DDMFormInstanceRecordLocalService formInstanceRecordLocalService;

	@Reference(
		target = "(indexer.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord)"
	)
	protected IndexerDocumentBuilder indexerDocumentBuilder;

	@Reference(
		target = "(indexer.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord)"
	)
	protected IndexerWriter<DDMFormInstanceRecord> indexerWriter;

}