/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.sharepoint.rest.oauth2.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuch2TokenEntryException extends NoSuchModelException {

	public NoSuch2TokenEntryException() {
	}

	public NoSuch2TokenEntryException(String msg) {
		super(msg);
	}

	public NoSuch2TokenEntryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuch2TokenEntryException(Throwable throwable) {
		super(throwable);
	}

}