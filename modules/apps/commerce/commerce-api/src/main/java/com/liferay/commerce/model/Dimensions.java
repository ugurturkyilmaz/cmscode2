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

package com.liferay.commerce.model;

import com.liferay.petra.string.StringBundler;

/**
 * @author Andrea Di Giorgi
 */
public class Dimensions {

	public Dimensions(double width, double height, double depth) {
		_width = width;
		_height = height;
		_depth = depth;
	}

	public double getDepth() {
		return _depth;
	}

	public double getHeight() {
		return _height;
	}

	public double getWidth() {
		return _width;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{width=", _width, ", height=", _height, ", depth=", _depth, "}");
	}

	private final double _depth;
	private final double _height;
	private final double _width;

}