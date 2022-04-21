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

package com.liferay.wiki.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.wiki.model.WikiPage;

/**
 * @author Norbert Kocsis
 */
public class WikiPageUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long wikiPageClassNameId = PortalUtil.getClassNameId(
				WikiPage.class.getName());

			runSQL(
				StringBundler.concat(
					"update WikiPage set createDate = (select createDate from ",
					"AssetEntry where AssetEntry.classNameId = ",
					wikiPageClassNameId,
					" and AssetEntry.classPK = WikiPage.resourcePrimKey)"));
		}
	}

}