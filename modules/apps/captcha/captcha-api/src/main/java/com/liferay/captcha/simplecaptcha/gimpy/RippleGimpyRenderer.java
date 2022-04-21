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

package com.liferay.captcha.simplecaptcha.gimpy;

import com.jhlabs.image.RippleFilter;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

import nl.captcha.gimpy.GimpyRenderer;
import nl.captcha.util.ImageUtil;

/**
 * This modified copy of {@code nl.captcha.gimpy.RippleGimpyRenderer} works with the latest
 * version of {@code com.jhlabs} filters.
 *
 * @author James Childers
 * @author Jorge Díaz
 */
public class RippleGimpyRenderer implements GimpyRenderer {

	@Override
	public void gimp(BufferedImage bufferedImage) {
		RippleFilter rippleFilter = new RippleFilter();

		rippleFilter.setEdgeAction(RippleFilter.CLAMP);
		rippleFilter.setXAmplitude(2.6F);
		rippleFilter.setXWavelength(15);
		rippleFilter.setYAmplitude(1.7F);
		rippleFilter.setYWavelength(5);

		ImageUtil.applyFilter(
			bufferedImage, new BufferedImageFilter(rippleFilter));
	}

}