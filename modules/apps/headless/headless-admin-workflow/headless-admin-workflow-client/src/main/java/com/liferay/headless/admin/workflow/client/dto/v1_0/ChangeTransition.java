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

package com.liferay.headless.admin.workflow.client.dto.v1_0;

import com.liferay.headless.admin.workflow.client.function.UnsafeSupplier;
import com.liferay.headless.admin.workflow.client.serdes.v1_0.ChangeTransitionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class ChangeTransition implements Cloneable, Serializable {

	public static ChangeTransition toDTO(String json) {
		return ChangeTransitionSerDes.toDTO(json);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setComment(
		UnsafeSupplier<String, Exception> commentUnsafeSupplier) {

		try {
			comment = commentUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String comment;

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public void setTransitionName(
		UnsafeSupplier<String, Exception> transitionNameUnsafeSupplier) {

		try {
			transitionName = transitionNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String transitionName;

	public Long getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(Long workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}

	public void setWorkflowTaskId(
		UnsafeSupplier<Long, Exception> workflowTaskIdUnsafeSupplier) {

		try {
			workflowTaskId = workflowTaskIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long workflowTaskId;

	@Override
	public ChangeTransition clone() throws CloneNotSupportedException {
		return (ChangeTransition)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ChangeTransition)) {
			return false;
		}

		ChangeTransition changeTransition = (ChangeTransition)object;

		return Objects.equals(toString(), changeTransition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ChangeTransitionSerDes.toJSON(this);
	}

}