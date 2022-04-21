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

package com.liferay.portal.output.stream.container.internal;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.output.stream.container.OutputStreamContainer;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactory;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactoryTracker;
import com.liferay.portal.output.stream.container.constants.OutputStreamContainerConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true, service = OutputStreamContainerFactoryTracker.class
)
public class OutputStreamContainerFactoryTrackerImpl
	implements OutputStreamContainerFactoryTracker {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #getOutputStreamContainerFactory(String)}
	 */
	@Deprecated
	@Override
	public OutputStreamContainerFactory getOutputStreamContainerFactory() {
		return _outputStreamContainerFactory;
	}

	@Override
	public OutputStreamContainerFactory getOutputStreamContainerFactory(
		String outputStreamContainerFactoryName) {

		if (outputStreamContainerFactoryName == null) {
			return _outputStreamContainerFactory;
		}

		OutputStreamContainerFactory outputStreamContainerFactory =
			_outputStreamContainerFactories.getService(
				outputStreamContainerFactoryName);

		if (outputStreamContainerFactory == null) {
			throw new IllegalArgumentException(
				"No output stream container factory registered with name " +
					outputStreamContainerFactoryName);
		}

		return outputStreamContainerFactory;
	}

	@Override
	public Set<String> getOutputStreamContainerFactoryNames() {
		return _outputStreamContainerFactories.keySet();
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #runWithSwappedLog(Runnable, String, OutputStream)}
	 */
	@Deprecated
	@Override
	public void runWithSwappedLog(Runnable runnable, String outputStreamHint) {
		OutputStreamContainerFactory outputStreamContainerFactory =
			getOutputStreamContainerFactory(null);

		OutputStreamContainer outputStreamContainer =
			outputStreamContainerFactory.create(outputStreamHint);

		runWithSwappedLog(
			runnable, outputStreamContainer.getDescription(),
			outputStreamContainer.getOutputStream());
	}

	@Override
	public void runWithSwappedLog(
		Runnable runnable, String outputStreamName, OutputStream outputStream) {

		if (_log.isInfoEnabled()) {
			_log.info("Using " + outputStreamName + " as output");
		}

		Writer writer = _writerThreadLocal.get();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			outputStream, Charset.forName("UTF-8"));

		_writerThreadLocal.set(outputStreamWriter);

		try {
			runnable.run();
		}
		finally {
			_writerThreadLocal.set(writer);

			try {
				outputStreamWriter.flush();
			}
			catch (IOException ioException) {
				_log.error(ioException.getLocalizedMessage());
			}
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #runWithSwappedLog(Runnable, String, OutputStream)}
	 */
	@Deprecated
	@Override
	public void runWithSwappedLog(
		Runnable runnable, String outputStreamHint,
		String outputStreamContainerName) {

		OutputStreamContainerFactory outputStreamContainerFactory =
			getOutputStreamContainerFactory(outputStreamContainerName);

		OutputStreamContainer outputStreamContainer =
			outputStreamContainerFactory.create(outputStreamHint);

		runWithSwappedLog(
			runnable, outputStreamContainer.getDescription(),
			outputStreamContainer.getOutputStream());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		PatternLayout.Builder build = PatternLayout.newBuilder();

		build.withPattern("%level - %m%n");

		PatternLayout patternLayout = build.build();

		_writerAppender = WriterAppender.createAppender(
			patternLayout, null, new ThreadLocalWriter(), "WriterAppender",
			false, false);

		_writerAppender.start();

		Logger rootLogger = (Logger)LogManager.getRootLogger();

		rootLogger.addAppender(_writerAppender);

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>put(
				"name", "console"
			).put(
				"service.ranking:Integer", "100"
			).build();

		_serviceRegistrations.add(
			bundleContext.registerService(
				OutputStreamContainerFactory.class,
				new ConsoleOutputStreamContainerFactory(), dictionary));

		dictionary.put(
			"name", OutputStreamContainerConstants.FACTORY_NAME_DUMMY);
		dictionary.put("service.ranking:Integer", "-100");

		_serviceRegistrations.add(
			bundleContext.registerService(
				OutputStreamContainerFactory.class,
				new DummyOutputStreamContainerFactory(), dictionary));

		dictionary.put("name", "temp_file");
		dictionary.remove("service.ranking:Integer");

		_serviceRegistrations.add(
			bundleContext.registerService(
				OutputStreamContainerFactory.class,
				new TempFileOutputStreamContainerFactory(), dictionary));

		_outputStreamContainerFactories =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, OutputStreamContainerFactory.class, "name");
	}

	@Deactivate
	protected void deactivate() {
		if (_outputStreamContainerFactories != null) {
			_outputStreamContainerFactories.close();
		}

		for (ServiceRegistration<?> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}

		_serviceRegistrations.clear();

		Logger rootLogger = (Logger)LogManager.getRootLogger();

		if (rootLogger != null) {
			rootLogger.removeAppender(_writerAppender);
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OutputStreamContainerFactoryTrackerImpl.class);

	private ServiceTrackerMap<String, OutputStreamContainerFactory>
		_outputStreamContainerFactories;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile OutputStreamContainerFactory _outputStreamContainerFactory;

	private final List<ServiceRegistration<?>> _serviceRegistrations =
		new ArrayList<>();
	private WriterAppender _writerAppender;
	private final ThreadLocal<Writer> _writerThreadLocal = new ThreadLocal<>();

	private class ThreadLocalWriter extends Writer {

		@Override
		public void close() throws IOException {
			Writer writer = _writerThreadLocal.get();

			if (writer != null) {
				writer.close();
			}
		}

		@Override
		public void flush() throws IOException {
			Writer writer = _writerThreadLocal.get();

			if (writer != null) {
				writer.flush();
			}
		}

		@Override
		public void write(char[] chars, int offset, int length)
			throws IOException {

			Writer writer = _writerThreadLocal.get();

			if (writer != null) {
				writer.write(chars, offset, length);
			}
		}

	}

}