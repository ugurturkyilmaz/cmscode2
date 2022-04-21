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

package com.liferay.portal.events;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class LogMemoryUsageAction extends Action {

	@Override
	public void run(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		if (!_log.isDebugEnabled()) {
			return;
		}

		Runtime runtime = Runtime.getRuntime();

		NumberFormat nf = NumberFormat.getInstance();

		String freeMemory = nf.format(runtime.freeMemory());
		String totalMemory = nf.format(runtime.totalMemory());
		String maxMemory = nf.format(runtime.maxMemory());

		_log.debug(
			StringBundler.concat(
				"Memory Usage:\t", freeMemory, "\t", totalMemory, "\t",
				maxMemory));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LogMemoryUsageAction.class);

}