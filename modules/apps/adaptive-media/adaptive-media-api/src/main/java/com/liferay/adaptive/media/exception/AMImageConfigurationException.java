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

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Sergio González
 */
public class AMImageConfigurationException extends PortalException {

	public AMImageConfigurationException() {
	}

	public AMImageConfigurationException(String s) {
		super(s);
	}

	public AMImageConfigurationException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public AMImageConfigurationException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * This exception is raised when a configuration with the same name already
	 * exists.
	 */
	public static final class DuplicateAMImageConfigurationNameException
		extends AMImageConfigurationException {

		public DuplicateAMImageConfigurationNameException() {
		}

		public DuplicateAMImageConfigurationNameException(String s) {
			super(s);
		}

		public DuplicateAMImageConfigurationNameException(
			String s, Throwable throwable) {

			super(s, throwable);
		}

		public DuplicateAMImageConfigurationNameException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when a configuration with the same uuid already
	 * exists.
	 */
	public static final class DuplicateAMImageConfigurationUuidException
		extends AMImageConfigurationException {

		public DuplicateAMImageConfigurationUuidException() {
		}

		public DuplicateAMImageConfigurationUuidException(String s) {
			super(s);
		}

		public DuplicateAMImageConfigurationUuidException(
			String s, Throwable throwable) {

			super(s, throwable);
		}

		public DuplicateAMImageConfigurationUuidException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when the height value is not valid.
	 */
	public static final class InvalidHeightException
		extends AMImageConfigurationException {

		public InvalidHeightException() {
		}

		public InvalidHeightException(String s) {
			super(s);
		}

		public InvalidHeightException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public InvalidHeightException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when the name of the configuration is not valid.
	 */
	public static final class InvalidNameException
		extends AMImageConfigurationException {

		public InvalidNameException() {
		}

		public InvalidNameException(String s) {
			super(s);
		}

		public InvalidNameException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public InvalidNameException(Throwable throwable) {
			super(throwable);
		}

	}

	public static final class InvalidStateAMImageConfigurationException
		extends AMImageConfigurationException {

		public InvalidStateAMImageConfigurationException() {
		}

		public InvalidStateAMImageConfigurationException(String s) {
			super(s);
		}

		public InvalidStateAMImageConfigurationException(
			String s, Throwable throwable) {

			super(s, throwable);
		}

		public InvalidStateAMImageConfigurationException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when the uuid of the configuration is not valid.
	 */
	public static final class InvalidUuidException
		extends AMImageConfigurationException {

		public InvalidUuidException() {
		}

		public InvalidUuidException(String s) {
			super(s);
		}

		public InvalidUuidException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public InvalidUuidException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when the height value is not valid.
	 */
	public static final class InvalidWidthException
		extends AMImageConfigurationException {

		public InvalidWidthException() {
		}

		public InvalidWidthException(String s) {
			super(s);
		}

		public InvalidWidthException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public InvalidWidthException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when a configuration does not exist.
	 */
	public static final class NoSuchAMImageConfigurationException
		extends AMImageConfigurationException {

		public NoSuchAMImageConfigurationException() {
		}

		public NoSuchAMImageConfigurationException(String s) {
			super(s);
		}

		public NoSuchAMImageConfigurationException(
			String s, Throwable throwable) {

			super(s, throwable);
		}

		public NoSuchAMImageConfigurationException(Throwable throwable) {
			super(throwable);
		}

	}

	/**
	 * This exception is raised when the configuration does not contain either a
	 * valid height value nor a valid width value.
	 */
	public static final class RequiredWidthOrHeightException
		extends AMImageConfigurationException {

		public RequiredWidthOrHeightException() {
		}

		public RequiredWidthOrHeightException(String s) {
			super(s);
		}

		public RequiredWidthOrHeightException(String s, Throwable throwable) {
			super(s, throwable);
		}

		public RequiredWidthOrHeightException(Throwable throwable) {
			super(throwable);
		}

	}

}