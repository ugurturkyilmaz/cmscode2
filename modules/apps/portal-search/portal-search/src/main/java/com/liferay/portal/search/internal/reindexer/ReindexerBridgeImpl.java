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

package com.liferay.portal.search.internal.reindexer;

import com.liferay.portal.kernel.search.reindexer.ReindexerBridge;
import com.liferay.portal.search.reindexer.Reindexer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(service = ReindexerBridge.class)
public class ReindexerBridgeImpl implements ReindexerBridge {

	@Override
	public void reindex(long companyId, String className, long... classPKs) {
		_reindexer.reindex(companyId, className, classPKs);
	}

	@Reference
	private Reindexer _reindexer;

}