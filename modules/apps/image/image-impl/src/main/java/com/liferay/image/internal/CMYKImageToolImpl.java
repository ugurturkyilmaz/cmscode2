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

package com.liferay.image.internal;

import com.liferay.portal.kernel.concurrent.FutureConverter;
import com.liferay.portal.kernel.exception.ImageResolutionException;
import com.liferay.portal.kernel.image.CMYKImageTool;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageMagick;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.FileImpl;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.Future;

import org.im4java.core.IMOperation;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Guilherme Camacho
 */
@Component(immediate = true, service = CMYKImageTool.class)
public class CMYKImageToolImpl implements CMYKImageTool {

	@Override
	public Future<RenderedImage> convertCMYKtoRGB(
		byte[] bytes, final String type) {

		if (!_imageMagick.isEnabled()) {
			return null;
		}

		File inputFile = _fileImpl.createTempFile(type);
		final File outputFile = _fileImpl.createTempFile(type);

		try {
			_fileImpl.write(inputFile, bytes);

			IMOperation imOperation = new IMOperation();

			imOperation.addRawArgs("-format", "%[colorspace]");
			imOperation.addImage(inputFile.getPath());

			String[] output = _imageMagick.identify(imOperation.getCmdArgs());

			if ((output.length == 1) &&
				StringUtil.equalsIgnoreCase(output[0], "CMYK")) {

				if (_log.isInfoEnabled()) {
					_log.info("The image is in the CMYK colorspace");
				}

				imOperation = new IMOperation();

				imOperation.addRawArgs("-colorspace", "RGB");
				imOperation.addImage(inputFile.getPath());
				imOperation.addImage(outputFile.getPath());

				Future<Object> future = (Future<Object>)_imageMagick.convert(
					imOperation.getCmdArgs());

				return new FutureConverter<RenderedImage, Object>(future) {

					@Override
					protected RenderedImage convert(Object object) {
						RenderedImage renderedImage = null;

						try {
							ImageBag imageBag = _imageTool.read(
								_fileImpl.getBytes(outputFile));

							renderedImage = imageBag.getRenderedImage();
						}
						catch (ImageResolutionException | IOException
									exception) {

							if (_log.isDebugEnabled()) {
								_log.debug(
									"Unable to convert " + type, exception);
							}
						}

						return renderedImage;
					}

				};
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}
		finally {
			_fileImpl.delete(inputFile);
			_fileImpl.delete(outputFile);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CMYKImageToolImpl.class);

	private static final FileImpl _fileImpl = FileImpl.getInstance();

	@Reference
	private ImageMagick _imageMagick;

	@Reference
	private ImageTool _imageTool;

}