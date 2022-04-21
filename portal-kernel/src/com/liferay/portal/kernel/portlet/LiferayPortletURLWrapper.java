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

package com.liferay.portal.kernel.portlet;

import java.util.Set;
import java.util.function.BiConsumer;

import javax.portlet.MutableResourceParameters;
import javax.portlet.filter.PortletURLWrapper;

/**
 * @author Neil Griffin
 */
public class LiferayPortletURLWrapper
	extends PortletURLWrapper implements LiferayPortletURL {

	public LiferayPortletURLWrapper(LiferayPortletURL liferayPortletURL) {
		super(liferayPortletURL);

		_liferayPortletURL = (LiferayPortletURL)super.getWrapped();
	}

	@Override
	public void addParameterIncludedInPath(String name) {
		_liferayPortletURL.addParameterIncludedInPath(name);
	}

	@Override
	public String getCacheability() {
		return _liferayPortletURL.getCacheability();
	}

	@Override
	public String getLifecycle() {
		return _liferayPortletURL.getLifecycle();
	}

	@Override
	public String getParameter(String name) {
		return _liferayPortletURL.getParameter(name);
	}

	@Override
	public Set<String> getParametersIncludedInPath() {
		return _liferayPortletURL.getParametersIncludedInPath();
	}

	@Override
	public long getPlid() {
		return _liferayPortletURL.getPlid();
	}

	@Override
	public String getPortletId() {
		return _liferayPortletURL.getPortletId();
	}

	@Override
	public Set<String> getRemovedParameterNames() {
		return _liferayPortletURL.getRemovedParameterNames();
	}

	@Override
	public String getResourceID() {
		return _liferayPortletURL.getResourceID();
	}

	@Override
	public MutableResourceParameters getResourceParameters() {
		return _liferayPortletURL.getResourceParameters();
	}

	@Override
	public LiferayPortletURL getWrapped() {
		return _liferayPortletURL;
	}

	@Override
	public boolean isAnchor() {
		return _liferayPortletURL.isAnchor();
	}

	@Override
	public boolean isCopyCurrentRenderParameters() {
		return _liferayPortletURL.isCopyCurrentRenderParameters();
	}

	@Override
	public boolean isEncrypt() {
		return _liferayPortletURL.isEncrypt();
	}

	@Override
	public boolean isEscapeXml() {
		return _liferayPortletURL.isEscapeXml();
	}

	@Override
	public boolean isParameterIncludedInPath(String name) {
		return _liferayPortletURL.isParameterIncludedInPath(name);
	}

	@Override
	public boolean isSecure() {
		return _liferayPortletURL.isSecure();
	}

	@Override
	public void setAnchor(boolean anchor) {
		_liferayPortletURL.setAnchor(anchor);
	}

	@Override
	public void setCacheability(String cacheability) {
		_liferayPortletURL.setCacheability(cacheability);
	}

	@Override
	public void setCopyCurrentRenderParameters(
		boolean copyCurrentRenderParameters) {

		_liferayPortletURL.setCopyCurrentRenderParameters(
			copyCurrentRenderParameters);
	}

	@Override
	public void setDoAsGroupId(long doAsGroupId) {
		_liferayPortletURL.setDoAsGroupId(doAsGroupId);
	}

	@Override
	public void setDoAsUserId(long doAsUserId) {
		_liferayPortletURL.setDoAsUserId(doAsUserId);
	}

	@Override
	public void setDoAsUserLanguageId(String doAsUserLanguageId) {
		_liferayPortletURL.setDoAsUserLanguageId(doAsUserLanguageId);
	}

	@Override
	public void setEncrypt(boolean encrypt) {
		_liferayPortletURL.setEncrypt(encrypt);
	}

	@Override
	public void setEscapeXml(boolean escapeXml) {
		_liferayPortletURL.setEscapeXml(escapeXml);
	}

	@Override
	public void setLifecycle(String lifecycle) {
		_liferayPortletURL.setLifecycle(lifecycle);
	}

	@Override
	public void setParameter(String name, String value, boolean append) {
		_liferayPortletURL.setParameter(name, value, append);
	}

	@Override
	public void setParameter(String name, String[] values, boolean append) {
		_liferayPortletURL.setParameter(name, values, append);
	}

	@Override
	public void setPlid(long plid) {
		_liferayPortletURL.setPlid(plid);
	}

	@Override
	public void setPortletId(String portletId) {
		_liferayPortletURL.setPortletId(portletId);
	}

	@Override
	public void setRefererGroupId(long refererGroupId) {
		_liferayPortletURL.setRefererGroupId(refererGroupId);
	}

	@Override
	public void setRefererPlid(long refererPlid) {
		_liferayPortletURL.setRefererPlid(refererPlid);
	}

	@Override
	public void setRemovedParameterNames(Set<String> removedParamNames) {
		_liferayPortletURL.setRemovedParameterNames(removedParamNames);
	}

	@Override
	public void setResourceID(String resourceID) {
		_liferayPortletURL.setResourceID(resourceID);
	}

	@Override
	public void setWindowStateRestoreCurrentView(
		boolean windowStateRestoreCurrentView) {

		_liferayPortletURL.setWindowStateRestoreCurrentView(
			windowStateRestoreCurrentView);
	}

	@Override
	public void visitReservedParameters(BiConsumer<String, String> biConsumer) {
		_liferayPortletURL.visitReservedParameters(biConsumer);
	}

	private final LiferayPortletURL _liferayPortletURL;

}