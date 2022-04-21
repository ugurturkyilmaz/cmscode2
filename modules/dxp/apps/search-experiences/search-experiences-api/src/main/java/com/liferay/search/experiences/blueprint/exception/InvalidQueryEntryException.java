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

package com.liferay.search.experiences.blueprint.exception;

/**
 * @author André de Oliveira
 */
public class InvalidQueryEntryException extends RuntimeException {

	public static InvalidQueryEntryException at(int index) {
		InvalidQueryEntryException invalidQueryEntryException =
			new InvalidQueryEntryException("Invalid query entry at: " + index);

		invalidQueryEntryException._index = index;

		return invalidQueryEntryException;
	}

	public int getIndex() {
		return _index;
	}

	private InvalidQueryEntryException(String message) {
		super(message);
	}

	private int _index;

}