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

package com.liferay.poshi.runner.util;

import com.liferay.poshi.core.util.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Arrays;

/**
 * @author Peter Yoo
 */
public class BufferedProcess extends Process {

	public BufferedProcess(int bufferSize, Process process) {
		_process = process;

		_standardErrorInputStreamBuffer = new InputStreamBuffer(
			bufferSize, _process.getErrorStream());

		_standardErrorInputStreamBuffer.start();

		_standardOutInputStreamBuffer = new InputStreamBuffer(
			bufferSize, _process.getInputStream());

		_standardOutInputStreamBuffer.start();
	}

	@Override
	public void destroy() {
		_process.destroy();
	}

	@Override
	public int exitValue() {
		int exitValue = _process.exitValue();

		ExecUtil.sleep(_MINIMUM_EXECUTION_TIME);

		return exitValue;
	}

	@Override
	public InputStream getErrorStream() {
		return _standardErrorInputStreamBuffer.toInputStream();
	}

	@Override
	public InputStream getInputStream() {
		return _standardOutInputStreamBuffer.toInputStream();
	}

	@Override
	public OutputStream getOutputStream() {
		return _process.getOutputStream();
	}

	@Override
	public int waitFor() throws InterruptedException {
		ExecUtil.sleep(_MINIMUM_EXECUTION_TIME);

		return _process.waitFor();
	}

	private static final long _MINIMUM_EXECUTION_TIME = 10;

	private final Process _process;
	private final InputStreamBuffer _standardErrorInputStreamBuffer;
	private final InputStreamBuffer _standardOutInputStreamBuffer;

	private class InputStreamBuffer extends Thread {

		public InputStreamBuffer(int bufferSize, InputStream inputStream) {
			_inputStream = inputStream;

			_buffer = new byte[bufferSize];
		}

		public void run() {
			try {
				byte[] bytes = new byte[Math.min(256, _buffer.length)];

				int bytesRead = 0;

				_index = 0;

				while (bytesRead != -1) {
					bytesRead = _inputStream.read(bytes);

					if (bytesRead > 0) {
						int spaceAvailable = _buffer.length - _index;

						if (bytesRead > spaceAvailable) {
							int spaceNeeded = bytesRead - spaceAvailable;

							_makeSpace(spaceNeeded);
						}

						synchronized (_buffer) {
							System.arraycopy(
								bytes, 0, _buffer, _index, bytesRead);

							_index += bytesRead;
							_totalBytesRead += bytesRead;
						}
					}
				}
			}
			catch (IOException ioException) {
				throw new RuntimeException(ioException);
			}
		}

		public InputStream toInputStream() {
			byte[] bytes = null;

			if (_totalBytesRead > _buffer.length) {
				String message = StringUtil.combine(
					"[Truncated ",
					String.valueOf(_totalBytesRead - _buffer.length),
					" bytes] \n...");

				byte[] messageBytes = message.getBytes();

				bytes = new byte[messageBytes.length + _index];

				System.arraycopy(
					messageBytes, 0, bytes, 0, messageBytes.length);

				System.arraycopy(
					_buffer, 0, bytes, messageBytes.length, _index);
			}
			else {
				bytes = Arrays.copyOf(_buffer, _index);
			}

			return new ByteArrayInputStream(bytes);
		}

		private void _makeSpace(int spacesNeeded) {
			if ((_index - spacesNeeded) < 0) {
				throw new IllegalArgumentException(
					StringUtil.combine(
						"Unable to shift buffer content left ",
						String.valueOf(spacesNeeded), " spaces"));
			}

			System.arraycopy(
				_buffer, spacesNeeded, _buffer, 0, _index - spacesNeeded);

			_index -= spacesNeeded;
		}

		private final byte[] _buffer;
		private int _index;
		private final InputStream _inputStream;
		private int _totalBytesRead;

	}

}