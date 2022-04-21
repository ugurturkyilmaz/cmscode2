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

package com.liferay.portal.kernel.security.permission;

import com.liferay.portal.kernel.exception.ResourceActionsException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.xml.Document;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Daeyoung Song
 */
public class ResourceActionsUtil {

	public static void check(String portletName) {
		_resourceActions.check(portletName);
	}

	public static String getAction(
		HttpServletRequest httpServletRequest, String action) {

		return _resourceActions.getAction(httpServletRequest, action);
	}

	public static String getAction(Locale locale, String action) {
		return _resourceActions.getAction(locale, action);
	}

	public static String getCompositeModelName(String... classNames) {
		return _resourceActions.getCompositeModelName(classNames);
	}

	public static String getCompositeModelNameSeparator() {
		return _resourceActions.getCompositeModelNameSeparator();
	}

	public static List<String> getModelNames() {
		return _resourceActions.getModelNames();
	}

	public static List<String> getModelPortletResources(String name) {
		return _resourceActions.getModelPortletResources(name);
	}

	public static String getModelResource(
		HttpServletRequest httpServletRequest, String name) {

		return _resourceActions.getModelResource(httpServletRequest, name);
	}

	public static String getModelResource(Locale locale, String name) {
		return _resourceActions.getModelResource(locale, name);
	}

	public static List<String> getModelResourceActions(String name) {
		return _resourceActions.getModelResourceActions(name);
	}

	public static List<String> getModelResourceGroupDefaultActions(
		String name) {

		return _resourceActions.getModelResourceGroupDefaultActions(name);
	}

	public static List<String> getModelResourceGuestDefaultActions(
		String name) {

		return _resourceActions.getModelResourceGuestDefaultActions(name);
	}

	public static List<String> getModelResourceGuestUnsupportedActions(
		String name) {

		return _resourceActions.getModelResourceGuestUnsupportedActions(name);
	}

	public static String getModelResourceNamePrefix() {
		return _resourceActions.getModelResourceNamePrefix();
	}

	public static List<String> getModelResourceOwnerDefaultActions(
		String name) {

		return _resourceActions.getModelResourceOwnerDefaultActions(name);
	}

	public static Double getModelResourceWeight(String name) {
		return _resourceActions.getModelResourceWeight(name);
	}

	public static List<String> getPortletModelResources(String portletName) {
		return _resourceActions.getPortletModelResources(portletName);
	}

	public static List<String> getPortletNames() {
		return _resourceActions.getPortletNames();
	}

	public static List<String> getPortletResourceActions(String name) {
		return _resourceActions.getPortletResourceActions(name);
	}

	public static List<String> getPortletResourceGroupDefaultActions(
		String name) {

		return _resourceActions.getPortletResourceGroupDefaultActions(name);
	}

	public static List<String> getPortletResourceGuestDefaultActions(
		String name) {

		return _resourceActions.getPortletResourceGuestDefaultActions(name);
	}

	public static List<String> getPortletResourceGuestUnsupportedActions(
		String name) {

		return _resourceActions.getPortletResourceGuestUnsupportedActions(name);
	}

	public static List<String> getPortletResourceLayoutManagerActions(
		String name) {

		return _resourceActions.getPortletResourceLayoutManagerActions(name);
	}

	public static String getPortletRootModelResource(String portletName) {
		return _resourceActions.getPortletRootModelResource(portletName);
	}

	public static ResourceActions getResourceActions() {
		return _resourceActions;
	}

	public static List<String> getResourceActions(String name) {
		return _resourceActions.getResourceActions(name);
	}

	public static List<String> getResourceActions(
		String portletResource, String modelResource) {

		return _resourceActions.getResourceActions(
			portletResource, modelResource);
	}

	public static List<String> getResourceGuestUnsupportedActions(
		String portletResource, String modelResource) {

		return _resourceActions.getResourceGuestUnsupportedActions(
			portletResource, modelResource);
	}

	public static List<Role> getRoles(
		long companyId, Group group, String modelResource, int[] roleTypes) {

		return _resourceActions.getRoles(
			companyId, group, modelResource, roleTypes);
	}

	public static boolean isPortalModelResource(String modelResource) {
		return _resourceActions.isPortalModelResource(modelResource);
	}

	public static boolean isRootModelResource(String modelResource) {
		return _resourceActions.isRootModelResource(modelResource);
	}

	public static void populateModelResources(
			ClassLoader classLoader, String... sources)
		throws ResourceActionsException {

		_resourceActions.populateModelResources(classLoader, sources);
	}

	public static void populateModelResources(Document document)
		throws ResourceActionsException {

		_resourceActions.populateModelResources(document);
	}

	public static void populatePortletResource(
			Portlet portlet, ClassLoader classLoader, String... sources)
		throws ResourceActionsException {

		_resourceActions.populatePortletResource(portlet, classLoader, sources);
	}

	public static void populatePortletResources(
			ClassLoader classLoader, String... sources)
		throws ResourceActionsException {

		_resourceActions.populatePortletResources(classLoader, sources);
	}

	public void setResourceActions(ResourceActions resourceActions) {
		_resourceActions = resourceActions;
	}

	private static ResourceActions _resourceActions;

}