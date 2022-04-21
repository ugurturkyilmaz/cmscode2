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

package com.liferay.search.experiences.web.internal.power.tools.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.search.experiences.constants.SXPPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 * @author André de Oliveira
 * @author Brian Wing Shun Chan
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-sxp-power-toys",
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/sxp_blueprint_admin/view.jsp",
		"javax.portlet.name=" + SXPPortletKeys.SXP_POWER_TOOLS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class SXPPowerToolsPortlet extends MVCPortlet {

	// TODO Add feature flag

}