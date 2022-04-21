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

package com.liferay.search.experiences.exception;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.search.experiences.problem.Problem;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SXPBlueprintTitleException extends PortalException {

	public SXPBlueprintTitleException() {
	}

	public SXPBlueprintTitleException(List<Problem> problems) {
		_problems = problems;
	}

	public SXPBlueprintTitleException(String msg) {
		super(msg);
	}

	public SXPBlueprintTitleException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public SXPBlueprintTitleException(Throwable throwable) {
		super(throwable);
	}

	public List<Problem> getProblems() {
		return _problems;
	}

	private static final long serialVersionUID = 1L;

	private List<Problem> _problems;

}