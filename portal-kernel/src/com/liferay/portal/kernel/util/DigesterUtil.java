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

package com.liferay.portal.kernel.util;

import java.io.InputStream;

import java.nio.ByteBuffer;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Connor McKay
 */
public class DigesterUtil {

	public static String digest(ByteBuffer byteBuffer) {
		return _digester.digest(byteBuffer);
	}

	public static String digest(InputStream inputStream) {
		return _digester.digest(inputStream);
	}

	public static String digest(String text) {
		return _digester.digest(text);
	}

	public static String digest(String algorithm, ByteBuffer byteBuffer) {
		return _digester.digest(algorithm, byteBuffer);
	}

	public static String digest(String algorithm, InputStream inputStream) {
		return _digester.digest(algorithm, inputStream);
	}

	public static String digest(String algorithm, String... text) {
		return _digester.digest(algorithm, text);
	}

	public static String digestBase64(ByteBuffer byteBuffer) {
		return _digester.digestBase64(byteBuffer);
	}

	public static String digestBase64(InputStream inputStream) {
		return _digester.digestBase64(inputStream);
	}

	public static String digestBase64(String text) {
		return _digester.digestBase64(text);
	}

	public static String digestBase64(String algorithm, ByteBuffer byteBuffer) {
		return _digester.digestBase64(algorithm, byteBuffer);
	}

	public static String digestBase64(
		String algorithm, InputStream inputStream) {

		return _digester.digestBase64(algorithm, inputStream);
	}

	public static String digestBase64(String algorithm, String... text) {
		return _digester.digestBase64(algorithm, text);
	}

	public static String digestHex(ByteBuffer byteBuffer) {
		return _digester.digestHex(byteBuffer);
	}

	public static String digestHex(InputStream inputStream) {
		return _digester.digestHex(inputStream);
	}

	public static String digestHex(String text) {
		return _digester.digestHex(text);
	}

	public static String digestHex(String algorithm, ByteBuffer byteBuffer) {
		return _digester.digestHex(algorithm, byteBuffer);
	}

	public static String digestHex(String algorithm, InputStream inputStream) {
		return _digester.digestHex(algorithm, inputStream);
	}

	public static String digestHex(String algorithm, String... text) {
		return _digester.digestHex(algorithm, text);
	}

	public static byte[] digestRaw(ByteBuffer byteBuffer) {
		return _digester.digestRaw(byteBuffer);
	}

	public static byte[] digestRaw(String text) {
		return _digester.digestRaw(text);
	}

	public static byte[] digestRaw(String algorithm, ByteBuffer byteBuffer) {
		return _digester.digestRaw(algorithm, byteBuffer);
	}

	public static byte[] digestRaw(String algorithm, InputStream inputStream) {
		return _digester.digestRaw(algorithm, inputStream);
	}

	public static byte[] digestRaw(String algorithm, String... text) {
		return _digester.digestRaw(algorithm, text);
	}

	public static Digester getDigester() {
		return _digester;
	}

	public void setDigester(Digester digester) {
		_digester = digester;
	}

	private static Digester _digester;

}