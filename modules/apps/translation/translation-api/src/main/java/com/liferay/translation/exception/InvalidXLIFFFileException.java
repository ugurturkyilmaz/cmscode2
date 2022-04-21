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

package com.liferay.translation.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), replaced by {@link XLIFFFileException}
 */
@Deprecated
public class InvalidXLIFFFileException extends PortalException {

	public InvalidXLIFFFileException() {
	}

	public InvalidXLIFFFileException(String msg) {
		super(msg);
	}

	public InvalidXLIFFFileException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public InvalidXLIFFFileException(Throwable throwable) {
		super(throwable);
	}

}