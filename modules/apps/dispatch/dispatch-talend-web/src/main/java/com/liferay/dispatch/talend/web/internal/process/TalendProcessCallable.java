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

package com.liferay.dispatch.talend.web.internal.process;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.security.Permission;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Igor Beslic
 */
public class TalendProcessCallable
	implements ProcessCallable<TalendProcessOutput> {

	public TalendProcessCallable(
		String[] mainMethodArgs, String jobMainClassFQN) {

		_mainMethodArgs = mainMethodArgs;
		_jobMainClassFQN = jobMainClassFQN;
	}

	@Override
	public TalendProcessOutput call() throws ProcessException {
		PrintStream errPrintStream = System.err;

		TalendProcessOutputWriter errTalendProcessOutputWriter =
			new TalendProcessOutputWriter(20, 20);

		System.setErr(
			new TeePrintStream(
				new TalendProcessOutputStream(errTalendProcessOutputWriter),
				errPrintStream));

		TalendProcessOutputWriter outTalendProcessOutputWriter =
			new TalendProcessOutputWriter(20, 20);

		PrintStream outPrintStream = System.out;

		System.setOut(
			new TeePrintStream(
				new TalendProcessOutputStream(outTalendProcessOutputWriter),
				outPrintStream));

		AtomicInteger exitStatusAtomicInteger = new AtomicInteger();
		RuntimeException runtimeException = new RuntimeException();

		System.setSecurityManager(
			new SecurityManager() {

				@Override
				public void checkExit(int status) {
					exitStatusAtomicInteger.set(status);

					throw runtimeException;
				}

				@Override
				public void checkPermission(Permission perm) {
				}

			});

		ClassLoader classLoader = TalendProcessCallable.class.getClassLoader();

		try {
			Class<?> talendJobClass = classLoader.loadClass(_jobMainClassFQN);

			Method mainMethod = talendJobClass.getMethod(
				"main", String[].class);

			mainMethod.setAccessible(true);

			mainMethod.invoke(null, new Object[] {_mainMethodArgs});
		}
		catch (InvocationTargetException invocationTargetException) {
			Throwable causeThrowable = invocationTargetException.getCause();

			if (causeThrowable == runtimeException) {
				return new TalendProcessOutput(
					exitStatusAtomicInteger.get(),
					outTalendProcessOutputWriter.getOutput(),
					errTalendProcessOutputWriter.getOutput());
			}

			throw new ProcessException(causeThrowable);
		}
		catch (Throwable throwable) {
			throw new ProcessException(throwable);
		}
		finally {
			System.setErr(errPrintStream);
			System.setOut(outPrintStream);
		}

		throw new ProcessException("Talend process did not trigger JVM exit");
	}

	private static final long serialVersionUID = 1L;

	private final String _jobMainClassFQN;
	private final String[] _mainMethodArgs;

	private class TalendProcessOutputStream extends OutputStream {

		@Override
		public void write(int integer) {
			_talendProcessOutputWriter.write(integer);
		}

		private TalendProcessOutputStream(
			TalendProcessOutputWriter talendProcessOutputWriter) {

			_talendProcessOutputWriter = talendProcessOutputWriter;
		}

		private final TalendProcessOutputWriter _talendProcessOutputWriter;

	}

	private class TalendProcessOutputWriter {

		public String getOutput() {
			if (_lines.size() == _totalLinesCount) {
				_lines.add(
					_beginningLinesCount,
					StringBundler.concat(
						"-----------------", StringPool.NEW_LINE,
						"Output was truncated for performance reasons. Check ",
						"the portal log for details.", StringPool.NEW_LINE,
						"-----------------"));
			}

			return StringUtil.merge(_lines, "\n");
		}

		public void write(int integer) {
			if (integer == '\n') {
				if (_lines.size() == _totalLinesCount) {
					_lines.remove(_beginningLinesCount);
				}

				_lines.add(_sb.toString());

				_sb = new StringBundler();
			}
			else {
				_sb.append((char)integer);
			}
		}

		private TalendProcessOutputWriter(
			int beginningLinesCount, int endingLinesCount) {

			_beginningLinesCount = beginningLinesCount;

			_totalLinesCount = beginningLinesCount + endingLinesCount;
		}

		private final int _beginningLinesCount;
		private final List<String> _lines = new LinkedList<>();
		private StringBundler _sb = new StringBundler();
		private final int _totalLinesCount;

	}

	private class TeePrintStream extends PrintStream {

		@Override
		public void close() {
			super.close();

			_printStream.flush();
		}

		@Override
		public void flush() {
			super.flush();

			_printStream.flush();
		}

		@Override
		public void write(byte[] bytes) throws IOException {
			super.write(bytes);

			_printStream.write(bytes);
		}

		@Override
		public void write(byte[] bytes, int offset, int length) {
			super.write(bytes, offset, length);

			_printStream.write(bytes, offset, length);
		}

		@Override
		public void write(int integer) {
			super.write(integer);

			_printStream.write(integer);
		}

		private TeePrintStream(
			OutputStream outputStream, PrintStream printStream) {

			super(outputStream);

			_printStream = printStream;
		}

		private final PrintStream _printStream;

	}

}