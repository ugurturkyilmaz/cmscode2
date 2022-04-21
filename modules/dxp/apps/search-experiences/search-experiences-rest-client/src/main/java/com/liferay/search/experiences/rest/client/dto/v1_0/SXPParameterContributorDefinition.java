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

package com.liferay.search.experiences.rest.client.dto.v1_0;

import com.liferay.search.experiences.rest.client.function.UnsafeSupplier;
import com.liferay.search.experiences.rest.client.serdes.v1_0.SXPParameterContributorDefinitionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
public class SXPParameterContributorDefinition
	implements Cloneable, Serializable {

	public static SXPParameterContributorDefinition toDTO(String json) {
		return SXPParameterContributorDefinitionSerDes.toDTO(json);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setClassName(
		UnsafeSupplier<String, Exception> classNameUnsafeSupplier) {

		try {
			className = classNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String className;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public String getTemplateVariable() {
		return templateVariable;
	}

	public void setTemplateVariable(String templateVariable) {
		this.templateVariable = templateVariable;
	}

	public void setTemplateVariable(
		UnsafeSupplier<String, Exception> templateVariableUnsafeSupplier) {

		try {
			templateVariable = templateVariableUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String templateVariable;

	@Override
	public SXPParameterContributorDefinition clone()
		throws CloneNotSupportedException {

		return (SXPParameterContributorDefinition)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SXPParameterContributorDefinition)) {
			return false;
		}

		SXPParameterContributorDefinition sxpParameterContributorDefinition =
			(SXPParameterContributorDefinition)object;

		return Objects.equals(
			toString(), sxpParameterContributorDefinition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return SXPParameterContributorDefinitionSerDes.toJSON(this);
	}

}