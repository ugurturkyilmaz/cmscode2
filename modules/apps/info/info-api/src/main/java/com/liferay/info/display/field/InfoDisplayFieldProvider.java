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

package com.liferay.info.display.field;

import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jürgen Kappler
 * @deprecated As of Cavanaugh (7.4.x)
 */
@Deprecated
@ProviderType
public interface InfoDisplayFieldProvider {

	public Set<InfoDisplayField> getContributorInfoDisplayFields(
		Locale locale, String... classNames);

	public Map<String, Object> getContributorInfoDisplayFieldsValues(
			String className, Object displayObject, Locale locale)
		throws PortalException;

}