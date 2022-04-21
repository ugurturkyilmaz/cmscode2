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

package com.liferay.knowledge.base.exception;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class KBArticleImportException extends PortalException {

	public KBArticleImportException() {
	}

	public KBArticleImportException(String msg) {
		super(msg);
	}

	public KBArticleImportException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public KBArticleImportException(Throwable throwable) {
		super(throwable);
	}

	public static class MustHaveACategory extends KBArticleImportException {

		public MustHaveACategory(
			AssetCategoryException assetCategoryException) {

			super(assetCategoryException);
		}

	}

}