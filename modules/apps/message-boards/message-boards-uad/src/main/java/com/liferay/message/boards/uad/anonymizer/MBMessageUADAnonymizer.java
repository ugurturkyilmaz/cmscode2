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

package com.liferay.message.boards.uad.anonymizer;

import com.liferay.message.boards.exception.RequiredMessageException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(immediate = true, service = UADAnonymizer.class)
public class MBMessageUADAnonymizer extends BaseMBMessageUADAnonymizer {

	@Override
	public Map<Class<?>, String> getExceptionMessageMap(Locale locale) {
		return HashMapBuilder.<Class<?>, String>put(
			RequiredMessageException.class,
			LanguageUtil.get(
				locale,
				"root-messages-with-multiple-replies-cannot-be-deleted.-" +
					"delete-the-thread-instead")
		).build();
	}

}