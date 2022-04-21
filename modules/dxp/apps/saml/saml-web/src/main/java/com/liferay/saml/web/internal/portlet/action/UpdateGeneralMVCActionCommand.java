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

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.constants.SamlProviderConfigurationKeys;
import com.liferay.saml.runtime.configuration.SamlConfiguration;
import com.liferay.saml.runtime.configuration.SamlProviderConfiguration;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.metadata.LocalEntityManager;
import com.liferay.saml.util.PortletPropsKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.saml.runtime.configuration.SamlConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/update_general"
	},
	service = MVCActionCommand.class
)
public class UpdateGeneralMVCActionCommand extends BaseMVCActionCommand {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_samlConfiguration = ConfigurableUtil.createConfigurable(
			SamlConfiguration.class, properties);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties unicodeProperties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		boolean enabled = GetterUtil.getBoolean(
			unicodeProperties.getProperty(PortletPropsKeys.SAML_ENABLED),
			_samlProviderConfigurationHelper.isEnabled());
		String samlEntityId = unicodeProperties.getProperty(
			PortletPropsKeys.SAML_ENTITY_ID);

		if (enabled &&
			!StringUtil.equalsIgnoreCase(
				_localEntityManager.getLocalEntityId(), samlEntityId)) {

			SessionErrors.add(actionRequest, "entityIdInUse");

			return;
		}

		if (Validator.isNotNull(samlEntityId) &&
			(samlEntityId.length() > 1024)) {

			SessionErrors.add(actionRequest, "entityIdTooLong");

			return;
		}

		if (enabled &&
			(_localEntityManager.getLocalEntityCertificate() == null)) {

			SessionErrors.add(actionRequest, "certificateInvalid");

			return;
		}

		String samlRole = unicodeProperties.getProperty(
			PortletPropsKeys.SAML_ROLE);

		SamlProviderConfiguration samlProviderConfiguration =
			_samlProviderConfigurationHelper.getSamlProviderConfiguration();

		if (samlProviderConfiguration.enabled() && enabled &&
			!StringUtil.equalsIgnoreCase(
				samlProviderConfiguration.role(), samlRole)) {

			SessionErrors.add(actionRequest, "roleInUse");

			return;
		}

		if (!_validateRoleSelection(enabled, samlRole)) {
			SessionErrors.add(actionRequest, "idpRoleNotConfigurable");

			return;
		}

		_samlProviderConfigurationHelper.updateProperties(unicodeProperties);

		actionResponse.setRenderParameter(
			"mvcRenderCommandName", "/admin/view");
		actionResponse.setRenderParameter("tabs1", "general");
	}

	private boolean _validateRoleSelection(boolean enabled, String samlRole) {
		if (_samlConfiguration.idpRoleConfigurationEnabled()) {
			return true;
		}

		if (!_samlProviderConfigurationHelper.isRoleIdp() &&
			samlRole.equals(SamlProviderConfigurationKeys.SAML_ROLE_IDP)) {

			return false;
		}

		if (!_samlProviderConfigurationHelper.isEnabled() && enabled &&
			samlRole.equals(SamlProviderConfigurationKeys.SAML_ROLE_IDP)) {

			return false;
		}

		return true;
	}

	@Reference
	private LocalEntityManager _localEntityManager;

	private SamlConfiguration _samlConfiguration;

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

}