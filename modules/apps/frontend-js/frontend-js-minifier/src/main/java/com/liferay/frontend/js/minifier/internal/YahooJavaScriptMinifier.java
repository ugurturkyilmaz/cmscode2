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

package com.liferay.frontend.js.minifier.internal;

import com.liferay.frontend.js.minifier.internal.configuration.YahooJavaScriptMinifierConfiguration;
import com.liferay.petra.io.unsync.UnsyncStringReader;
import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.minifier.JavaScriptMinifier;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

import java.util.Map;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Carlos Sierra Andrés
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Component(
	configurationPid = "com.frontend.js.minifier.configuration.YahooJavaScriptMinifierConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	service = JavaScriptMinifier.class
)
@Deprecated
public class YahooJavaScriptMinifier implements JavaScriptMinifier {

	@Override
	public String compress(String resourceName, String content) {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			JavaScriptCompressor javaScriptCompressor =
				new JavaScriptCompressor(
					new UnsyncStringReader(content),
					new JavaScriptErrorReporter());

			javaScriptCompressor.compress(
				unsyncStringWriter,
				_yahooJavaScriptMinifierConfiguration.jsLineBreak(),
				_yahooJavaScriptMinifierConfiguration.jsMunge(),
				_yahooJavaScriptMinifierConfiguration.jsVerbose(),
				_yahooJavaScriptMinifierConfiguration.jsPreserveAllSemicolons(),
				_yahooJavaScriptMinifierConfiguration.jsDisableOptimizations());
		}
		catch (Exception exception) {
			_log.error("Unable to minify JavaScript:\n" + content, exception);

			unsyncStringWriter.append(content);
		}

		return unsyncStringWriter.toString();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_yahooJavaScriptMinifierConfiguration =
			ConfigurableUtil.createConfigurable(
				YahooJavaScriptMinifierConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		YahooJavaScriptMinifier.class);

	private volatile YahooJavaScriptMinifierConfiguration
		_yahooJavaScriptMinifierConfiguration;

	private static class JavaScriptErrorReporter implements ErrorReporter {

		@Override
		public void error(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (line < 0) {
				_log.error(message);
			}
			else {
				_log.error(
					StringBundler.concat(
						line, ": ", lineOffset, ": ", message));
			}
		}

		@Override
		public EvaluatorException runtimeError(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			error(message, sourceName, line, lineSource, lineOffset);

			return new EvaluatorException(message);
		}

		@Override
		public void warning(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (!_log.isWarnEnabled()) {
				return;
			}

			if (line < 0) {
				_log.warn(message);
			}
			else {
				_log.warn(
					StringBundler.concat(
						line, ": ", lineOffset, ": ", message));
			}
		}

	}

}