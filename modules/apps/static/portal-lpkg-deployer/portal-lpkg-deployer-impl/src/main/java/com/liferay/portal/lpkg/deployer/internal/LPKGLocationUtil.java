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

package com.liferay.portal.lpkg.deployer.internal;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import org.osgi.framework.Bundle;

/**
 * @author Matthew Tambara
 */
public class LPKGLocationUtil {

	public static String generateInnerBundleLocation(
		Bundle lpkgBundle, String path) {

		String location = path.concat("?lpkgPath=");

		return location.concat(lpkgBundle.getLocation());
	}

	public static String getLPKGLocation(File lpkgFile) {
		String uriString = String.valueOf(lpkgFile.toURI());

		return StringUtil.replace(
			uriString, CharPool.BACK_SLASH, CharPool.FORWARD_SLASH);
	}

}