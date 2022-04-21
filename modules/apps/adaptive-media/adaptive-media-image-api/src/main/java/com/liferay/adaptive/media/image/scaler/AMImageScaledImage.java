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

package com.liferay.adaptive.media.image.scaler;

import java.io.InputStream;

/**
 * Represents an image scaled by Adaptive Media.
 *
 * @author Sergio González
 */
public interface AMImageScaledImage {

	/**
	 * Returns the image height.
	 *
	 * @return the image height
	 */
	public int getHeight();

	/**
	 * Returns an <code>InputStream</code> with the image data.
	 *
	 * @return the <code>InputStream</code> with the image data
	 */
	public InputStream getInputStream();

	/**
	 * Returns this image's size in bytes.
	 *
	 * @return this image's size in bytes
	 */
	public long getSize();

	/**
	 * Returns the image width.
	 *
	 * @return the image width
	 */
	public int getWidth();

}