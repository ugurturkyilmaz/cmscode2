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

package com.liferay.segments.asah.connector.internal.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Marcellus Tavares
 * @author Sarai Díaz
 * @author David Arques
 */
public class DXPVariants {

	public DXPVariants() {
	}

	public DXPVariants(List<DXPVariant> dxpVariants) {
		_dxpVariants = dxpVariants;
	}

	@JsonProperty("dxpVariants")
	public List<DXPVariant> getDXPVariants() {
		return _dxpVariants;
	}

	public void setDXPVariants(List<DXPVariant> dxpVariants) {
		_dxpVariants = dxpVariants;
	}

	private List<DXPVariant> _dxpVariants;

}