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

package com.liferay.portal.web.internal.session.replication;

import com.liferay.petra.io.Deserializer;
import com.liferay.petra.io.Serializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpSessionWrapper;
import com.liferay.portal.kernel.util.TransientValue;

import java.io.Serializable;

import java.nio.ByteBuffer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

/**
 * @author Dante Wang
 */
public class SessionReplicationHttpSessionWrapper extends HttpSessionWrapper {

	public SessionReplicationHttpSessionWrapper(HttpSession httpSession) {
		super(httpSession);
	}

	@Override
	public Object getAttribute(String name) {
		Object value = super.getAttribute(name);

		Set<String> scrubbedNames = (Set<String>)super.getAttribute(
			_SCRUBBED_NAMES_NAME);

		if ((value == null) || (scrubbedNames == null) ||
			!scrubbedNames.contains(name)) {

			return value;
		}

		if (value instanceof String) {
			return _transientValues.get(value);
		}

		Deserializer deserializer = new Deserializer(
			ByteBuffer.wrap((byte[])value));

		try {
			return deserializer.readObject();
		}
		catch (Exception exception) {
			_log.error("Unable to deserialize object", exception);

			return null;
		}
	}

	@Override
	public void removeAttribute(String name) {
		if (!_transientValues.isEmpty()) {
			Object value = super.getAttribute(name);

			if (value instanceof String) {
				String string = (String)value;

				if (string.startsWith(_TRANSIENT_VALUE_PREFIX)) {
					_transientValues.remove((String)value);
				}
			}
		}

		super.removeAttribute(name);

		Set<String> scrubbedNames = (Set<String>)super.getAttribute(
			_SCRUBBED_NAMES_NAME);

		if (scrubbedNames != null) {
			scrubbedNames.remove(name);

			super.setAttribute(_SCRUBBED_NAMES_NAME, scrubbedNames);
		}
	}

	@Override
	public void setAttribute(String name, Object value) {
		Object originalValue = value;

		if (value instanceof TransientValue) {
			TransientValue<?> transientValue = (TransientValue<?>)value;

			if (transientValue.isNull()) {
				value = null;
			}
			else {
				value = _TRANSIENT_VALUE_PREFIX + value;

				_transientValues.put((String)value, transientValue);
			}
		}
		else if (value instanceof Serializable) {
			Class<?> clazz = value.getClass();

			if (!_safeClassLoaders.contains(clazz.getClassLoader())) {
				Serializer serializer = new Serializer();

				serializer.writeObject((Serializable)value);

				ByteBuffer byteBuffer = serializer.toByteBuffer();

				value = byteBuffer.array();
			}
		}

		super.setAttribute(name, value);

		if (originalValue != value) {
			Set<String> scrubbedNames = (Set<String>)super.getAttribute(
				_SCRUBBED_NAMES_NAME);

			if (scrubbedNames == null) {
				scrubbedNames = Collections.newSetFromMap(
					new ConcurrentHashMap<>());
			}

			scrubbedNames.add(name);

			super.setAttribute(_SCRUBBED_NAMES_NAME, scrubbedNames);
		}
	}

	private static final String _SCRUBBED_NAMES_NAME =
		SessionReplicationHttpSessionWrapper.class.getName() +
			"._SCRUBBED_NAMES_NAME";

	private static final String _TRANSIENT_VALUE_PREFIX = "TRANSIENT_VALUE_";

	private static final Log _log = LogFactoryUtil.getLog(
		SessionReplicationHttpSessionWrapper.class);

	private static final Set<ClassLoader> _safeClassLoaders = new HashSet<>(
		Arrays.asList(
			String.class.getClassLoader(), HttpSession.class.getClassLoader(),
			Logger.class.getClassLoader()));

	private final Map<String, TransientValue<?>> _transientValues =
		new ConcurrentHashMap<>();

}