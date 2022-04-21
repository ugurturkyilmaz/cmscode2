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

package com.liferay.message.boards.demo.data.creator;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Sergio González
 */
@ProviderType
public interface MBMessageDemoDataCreator {

	public MBMessage create(long userId, long categoryId)
		throws IOException, PortalException;

	public MBMessage create(long userId, long categoryId, long parentMessageId)
		throws IOException, PortalException;

	public void delete() throws PortalException;

}