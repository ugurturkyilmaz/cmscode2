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

package com.liferay.analytics.reports.journal.internal.info.item.provider;

import com.liferay.analytics.reports.info.item.provider.AnalyticsReportsInfoItemObjectProvider;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = AnalyticsReportsInfoItemObjectProvider.class)
public class JournalArticleAnalyticsReportsInfoItemObjectProvider
	implements AnalyticsReportsInfoItemObjectProvider<JournalArticle> {

	@Override
	public JournalArticle getAnalyticsReportsInfoItemObject(
		InfoItemReference infoItemReference) {

		ClassPKInfoItemIdentifier classPKInfoItemIdentifier =
			(ClassPKInfoItemIdentifier)
				infoItemReference.getInfoItemIdentifier();

		return _journalArticleLocalService.fetchLatestArticle(
			classPKInfoItemIdentifier.getClassPK());
	}

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

}