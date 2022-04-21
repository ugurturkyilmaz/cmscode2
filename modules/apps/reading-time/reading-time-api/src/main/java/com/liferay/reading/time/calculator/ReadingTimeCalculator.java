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

package com.liferay.reading.time.calculator;

import com.liferay.portal.kernel.model.GroupedModel;

import java.time.Duration;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Alejandro Tardín
 */
public interface ReadingTimeCalculator {

	public Optional<Duration> calculate(GroupedModel groupedModel);

	public Optional<Duration> calculate(
		String content, String contentType, Locale locale);

}