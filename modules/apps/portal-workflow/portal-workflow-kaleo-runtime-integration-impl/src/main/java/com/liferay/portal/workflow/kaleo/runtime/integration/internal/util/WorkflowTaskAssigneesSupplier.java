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

package com.liferay.portal.workflow.kaleo.runtime.integration.internal.util;

import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Marcellus Tavares
 */
public class WorkflowTaskAssigneesSupplier
	implements Supplier<List<WorkflowTaskAssignee>> {

	public WorkflowTaskAssigneesSupplier(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	@Override
	public List<WorkflowTaskAssignee> get() {
		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		List<WorkflowTaskAssignee> workflowTaskAssignees = new ArrayList<>(
			kaleoTaskAssignmentInstances.size());

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
				kaleoTaskAssignmentInstances) {

			WorkflowTaskAssignee workflowTaskAssignee =
				new WorkflowTaskAssignee(
					kaleoTaskAssignmentInstance.getAssigneeClassName(),
					kaleoTaskAssignmentInstance.getAssigneeClassPK());

			workflowTaskAssignees.add(workflowTaskAssignee);
		}

		return workflowTaskAssignees;
	}

	private final KaleoTaskInstanceToken _kaleoTaskInstanceToken;

}