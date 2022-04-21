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

package com.liferay.users.admin.web.internal.display.context;

import com.liferay.portal.kernel.model.Organization;

/**
 * @author Drew Brokke
 */
public class OrganizationScreenNavigationDisplayContext {

	public String getActionName() {
		return _actionName;
	}

	public String getBackURL() {
		return _backURL;
	}

	public String getFormLabel() {
		return _formLabel;
	}

	public String getJspPath() {
		return _jspPath;
	}

	public Organization getOrganization() {
		return _organization;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public String getRedirect() {
		return _redirect;
	}

	public boolean isShowControls() {
		return _showControls;
	}

	public boolean isShowTitle() {
		return _showTitle;
	}

	public void setActionName(String actionName) {
		_actionName = actionName;
	}

	public void setBackURL(String backURL) {
		_backURL = backURL;
	}

	public void setFormLabel(String formLabel) {
		_formLabel = formLabel;
	}

	public void setJspPath(String jspPath) {
		_jspPath = jspPath;
	}

	public void setOrganization(Organization organization) {
		_organization = organization;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	public void setShowControls(boolean showControls) {
		_showControls = showControls;
	}

	public void setShowTitle(boolean showTitle) {
		_showTitle = showTitle;
	}

	private String _actionName;
	private String _backURL;
	private String _formLabel;
	private String _jspPath;
	private Organization _organization;
	private long _organizationId;
	private String _redirect;
	private boolean _showControls;
	private boolean _showTitle;

}