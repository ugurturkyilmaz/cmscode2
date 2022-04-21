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

package com.liferay.document.library.video.internal.converter;

import com.liferay.document.library.kernel.util.VideoConverter;
import com.liferay.document.library.video.internal.configuration.DLVideoFFMPEGVideoConverterConfiguration;
import com.liferay.petra.io.AutoDeleteFileInputStream;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	configurationPid = "com.liferay.document.library.video.internal.configuration.DLVideoFFMPEGVideoConverterConfiguration",
	service = {DLVideoFFMPEGVideoConverter.class, VideoConverter.class}
)
public class DLVideoFFMPEGVideoConverter implements VideoConverter {

	@Override
	public InputStream generateVideoPreview(File file, String containerType)
		throws Exception {

		File destinationFile = FileUtil.createTempFile(containerType);

		Properties videoProperties = PropsUtil.getProperties(
			PropsKeys.DL_FILE_ENTRY_PREVIEW_VIDEO, false);

		_runFFMPEGCommand(
			Arrays.asList(
				"ffmpeg", "-y", "-i", file.getAbsolutePath(), "-b:v",
				String.valueOf(
					_getVideoBitRate(videoProperties, containerType)),
				"-vf",
				String.format(
					"scale=min(%d\\,iw):-2",
					GetterUtil.getInteger(
						PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH)),
				"-r",
				String.valueOf(
					_getVideoFrameRate(videoProperties, containerType)),
				destinationFile.getAbsolutePath()));

		return new AutoDeleteFileInputStream(destinationFile);
	}

	@Override
	public InputStream generateVideoThumbnail(File file, String format)
		throws Exception {

		try {
			File destinationFile = FileUtil.createTempFile(format);

			_runFFMPEGCommand(
				Arrays.asList(
					"ffmpeg", "-y", "-i", file.getAbsolutePath(), "-vf",
					String.format(
						"thumbnail,scale=%d:%d/dar",
						PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH,
						PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH),
					"-frames:v", "1", destinationFile.getAbsolutePath()));

			return new AutoDeleteFileInputStream(destinationFile);
		}
		catch (Exception exception) {
			String message = exception.getMessage();

			if (message.contains("FFMPEG command")) {
				BufferedImage bufferedImage = new BufferedImage(
					PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH,
					PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_HEIGHT,
					BufferedImage.TYPE_INT_RGB);

				try (UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
						new UnsyncByteArrayOutputStream()) {

					_imageTool.write(
						bufferedImage, format, unsyncByteArrayOutputStream);

					return new ByteArrayInputStream(
						unsyncByteArrayOutputStream.toByteArray());
				}
			}

			throw exception;
		}
	}

	@Override
	public boolean isEnabled() {
		return _dlVideoFFMPEGVideoConverterConfiguration.enabled();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		modified(properties);
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		_dlVideoFFMPEGVideoConverterConfiguration =
			ConfigurableUtil.createConfigurable(
				DLVideoFFMPEGVideoConverterConfiguration.class, properties);
	}

	private void _consumeProcessInputStream(InputStream inputStream)
		throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream));

		while (bufferedReader.ready()) {
			bufferedReader.readLine();
		}
	}

	private int _getVideoBitRate(
		Properties videoProperties, String videoContainer) {

		int videoBitRate = GetterUtil.getInteger(
			videoProperties.getProperty(
				StringBundler.concat(
					PropsKeys.DL_FILE_ENTRY_PREVIEW_VIDEO_BIT_RATE, "[",
					videoContainer, "]")),
			_VIDEO_BIT_RATE_DEFAULT);

		if (videoBitRate > _VIDEO_BIT_RATE_MAX) {
			videoBitRate = _VIDEO_BIT_RATE_MAX;
		}

		return videoBitRate;
	}

	private int _getVideoFrameRate(
		Properties videoProperties, String videoContainer) {

		int denominator = GetterUtil.getInteger(
			videoProperties.getProperty(
				StringBundler.concat(
					PropsKeys.
						DL_FILE_ENTRY_PREVIEW_VIDEO_FRAME_RATE_DENOMINATOR,
					StringPool.OPEN_BRACKET, videoContainer,
					StringPool.CLOSE_BRACKET)));

		int numerator = GetterUtil.getInteger(
			videoProperties.getProperty(
				StringBundler.concat(
					PropsKeys.DL_FILE_ENTRY_PREVIEW_VIDEO_FRAME_RATE_NUMERATOR,
					"[", videoContainer, "]")));

		return numerator / denominator;
	}

	private void _runFFMPEGCommand(List<String> ffmpegCommand)
		throws Exception {

		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);

		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();

		InputStream inputStream = process.getInputStream();

		while (true) {
			try {
				_consumeProcessInputStream(inputStream);

				if (!process.waitFor(5, TimeUnit.SECONDS)) {
					continue;
				}

				if (process.exitValue() != 0) {
					throw new Exception(
						StringBundler.concat(
							"FFMPEG command ",
							StringUtil.merge(ffmpegCommand, StringPool.SPACE),
							" failed with exit status ", process.exitValue()));
				}

				return;
			}
			catch (InterruptedException interruptedException) {
				if (_log.isDebugEnabled()) {
					_log.debug(interruptedException);
				}
			}
		}
	}

	private static final int _VIDEO_BIT_RATE_DEFAULT = 250000;

	private static final int _VIDEO_BIT_RATE_MAX = 1200000;

	private static final Log _log = LogFactoryUtil.getLog(
		DLVideoFFMPEGVideoConverter.class);

	private volatile DLVideoFFMPEGVideoConverterConfiguration
		_dlVideoFFMPEGVideoConverterConfiguration;

	@Reference
	private ImageTool _imageTool;

}