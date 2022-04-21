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

package com.liferay.adaptive.media.exception;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Adolfo Pérez
 */
public class AMRuntimeException extends SystemException {

	public AMRuntimeException() {
	}

	public AMRuntimeException(String s) {
		super(s);
	}

	public AMRuntimeException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public AMRuntimeException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * This exception is thrown when a value serialized as a <code>String</code>
	 * cannot be converted by an {@link com.liferay.adaptive.media.AMAttribute}.
	 */
	public static final class AMAttributeFormatException
		extends AMRuntimeException {

		public AMAttributeFormatException() {
		}

		public AMAttributeFormatException(String s) {
			super(s);
		}

		public AMAttributeFormatException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public AMAttributeFormatException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is thrown when there is a processor configuration error.
	 */
	public static final class InvalidConfiguration extends AMRuntimeException {

		public InvalidConfiguration() {
		}

		public InvalidConfiguration(String s) {
			super(s);
		}

		public InvalidConfiguration(String s, Throwable throwable) {
			super(s, throwable);
		}

		public InvalidConfiguration(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception wraps {@link java.io.IOException} instances. Since it is a
	 * system error, it is reasonable to wrap it inside a runtime exception.
	 */
	public static final class IOException extends AMRuntimeException {

		public IOException() {
		}

		public IOException(String s) {
			super(s);
		}

		public IOException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public IOException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception wraps {@link java.io.UnsupportedEncodingException}
	 * instances. Since it is a system error, it is reasonable to wrap it inside
	 * a runtime exception.
	 */
	public static final class UnsupportedEncodingException
		extends AMRuntimeException {

		public UnsupportedEncodingException() {
		}

		public UnsupportedEncodingException(String s) {
			super(s);
		}

		public UnsupportedEncodingException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public UnsupportedEncodingException(Throwable throwable) {
			super(throwable);
		}

	}

}