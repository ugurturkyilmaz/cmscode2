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

package com.liferay.portal.workflow.metrics.rest.client.dto.v1_0;

import com.liferay.portal.workflow.metrics.rest.client.function.UnsafeSupplier;
import com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0.AssigneeMetricSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class AssigneeMetric implements Cloneable, Serializable {

	public static AssigneeMetric toDTO(String json) {
		return AssigneeMetricSerDes.toDTO(json);
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public void setAssignee(
		UnsafeSupplier<Assignee, Exception> assigneeUnsafeSupplier) {

		try {
			assignee = assigneeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Assignee assignee;

	public Long getDurationTaskAvg() {
		return durationTaskAvg;
	}

	public void setDurationTaskAvg(Long durationTaskAvg) {
		this.durationTaskAvg = durationTaskAvg;
	}

	public void setDurationTaskAvg(
		UnsafeSupplier<Long, Exception> durationTaskAvgUnsafeSupplier) {

		try {
			durationTaskAvg = durationTaskAvgUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long durationTaskAvg;

	public Long getOnTimeTaskCount() {
		return onTimeTaskCount;
	}

	public void setOnTimeTaskCount(Long onTimeTaskCount) {
		this.onTimeTaskCount = onTimeTaskCount;
	}

	public void setOnTimeTaskCount(
		UnsafeSupplier<Long, Exception> onTimeTaskCountUnsafeSupplier) {

		try {
			onTimeTaskCount = onTimeTaskCountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long onTimeTaskCount;

	public Long getOverdueTaskCount() {
		return overdueTaskCount;
	}

	public void setOverdueTaskCount(Long overdueTaskCount) {
		this.overdueTaskCount = overdueTaskCount;
	}

	public void setOverdueTaskCount(
		UnsafeSupplier<Long, Exception> overdueTaskCountUnsafeSupplier) {

		try {
			overdueTaskCount = overdueTaskCountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long overdueTaskCount;

	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	public void setTaskCount(
		UnsafeSupplier<Long, Exception> taskCountUnsafeSupplier) {

		try {
			taskCount = taskCountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long taskCount;

	@Override
	public AssigneeMetric clone() throws CloneNotSupportedException {
		return (AssigneeMetric)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssigneeMetric)) {
			return false;
		}

		AssigneeMetric assigneeMetric = (AssigneeMetric)object;

		return Objects.equals(toString(), assigneeMetric.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AssigneeMetricSerDes.toJSON(this);
	}

}