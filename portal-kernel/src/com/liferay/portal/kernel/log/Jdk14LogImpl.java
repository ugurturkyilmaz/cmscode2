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

package com.liferay.portal.kernel.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brian Wing Shun Chan
 */
public class Jdk14LogImpl implements Log {

	public Jdk14LogImpl(Logger log) {
		_log = log;
	}

	@Override
	public void debug(Object msg) {
		_log.log(Level.FINE, msg.toString());
	}

	@Override
	public void debug(Object msg, Throwable throwable) {
		_log.log(Level.FINE, msg.toString(), throwable);
	}

	@Override
	public void debug(Throwable throwable) {
		_log.log(Level.FINE, throwable.getMessage(), throwable);
	}

	@Override
	public void error(Object msg) {
		_log.log(Level.SEVERE, msg.toString());
	}

	@Override
	public void error(Object msg, Throwable throwable) {
		_log.log(Level.SEVERE, msg.toString(), throwable);
	}

	@Override
	public void error(Throwable throwable) {
		_log.log(Level.SEVERE, throwable.getMessage(), throwable);
	}

	@Override
	public void fatal(Object msg) {
		_log.log(Level.SEVERE, msg.toString());
	}

	@Override
	public void fatal(Object msg, Throwable throwable) {
		_log.log(Level.SEVERE, msg.toString(), throwable);
	}

	@Override
	public void fatal(Throwable throwable) {
		_log.log(Level.SEVERE, throwable.getMessage(), throwable);
	}

	public Logger getWrappedLogger() {
		return _log;
	}

	@Override
	public void info(Object msg) {
		_log.log(Level.INFO, msg.toString());
	}

	@Override
	public void info(Object msg, Throwable throwable) {
		_log.log(Level.INFO, msg.toString(), throwable);
	}

	@Override
	public void info(Throwable throwable) {
		_log.log(Level.INFO, throwable.getMessage(), throwable);
	}

	@Override
	public boolean isDebugEnabled() {
		return _log.isLoggable(Level.FINE);
	}

	@Override
	public boolean isErrorEnabled() {
		return _log.isLoggable(Level.SEVERE);
	}

	@Override
	public boolean isFatalEnabled() {
		return _log.isLoggable(Level.SEVERE);
	}

	@Override
	public boolean isInfoEnabled() {
		return _log.isLoggable(Level.INFO);
	}

	@Override
	public boolean isTraceEnabled() {
		return _log.isLoggable(Level.FINEST);
	}

	@Override
	public boolean isWarnEnabled() {
		return _log.isLoggable(Level.WARNING);
	}

	@Override
	public void setLogWrapperClassName(String className) {
	}

	@Override
	public void trace(Object msg) {
		_log.log(Level.FINEST, msg.toString());
	}

	@Override
	public void trace(Object msg, Throwable throwable) {
		_log.log(Level.FINEST, msg.toString(), throwable);
	}

	@Override
	public void trace(Throwable throwable) {
		_log.log(Level.FINEST, throwable.getMessage(), throwable);
	}

	@Override
	public void warn(Object msg) {
		_log.log(Level.WARNING, msg.toString());
	}

	@Override
	public void warn(Object msg, Throwable throwable) {
		_log.log(Level.WARNING, msg.toString(), throwable);
	}

	@Override
	public void warn(Throwable throwable) {
		_log.log(Level.WARNING, throwable.getMessage(), throwable);
	}

	private final Logger _log;

}