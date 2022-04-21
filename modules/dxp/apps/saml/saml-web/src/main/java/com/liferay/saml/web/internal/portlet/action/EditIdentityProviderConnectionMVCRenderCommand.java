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

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.constants.SamlWebKeys;
import com.liferay.saml.opensaml.integration.field.expression.handler.registry.UserFieldExpressionHandlerRegistry;
import com.liferay.saml.opensaml.integration.field.expression.resolver.registry.UserFieldExpressionResolverRegistry;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.runtime.configuration.SamlProviderConfiguration;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.web.internal.display.context.AttributeMappingDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/edit_identity_provider_connection"
	},
	service = MVCRenderCommand.class
)
public class EditIdentityProviderConnectionMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			return _render(renderRequest);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	private long _getClockSkew(
		RenderRequest renderRequest, SamlSpIdpConnection samlSpIdpConnection) {

		if (samlSpIdpConnection != null) {
			return ParamUtil.getLong(
				renderRequest, "clockSkew", samlSpIdpConnection.getClockSkew());
		}

		SamlProviderConfiguration samlProviderConfiguration =
			_samlProviderConfigurationHelper.getSamlProviderConfiguration();

		return ParamUtil.getLong(
			renderRequest, "clockSkew", samlProviderConfiguration.clockSkew());
	}

	private String _render(RenderRequest renderRequest) throws Exception {
		long samlSpIdpConnectionId = ParamUtil.getLong(
			renderRequest, "samlSpIdpConnectionId");

		SamlSpIdpConnection samlSpIdpConnection = null;

		if (samlSpIdpConnectionId > 0) {
			samlSpIdpConnection =
				_samlSpIdpConnectionLocalService.getSamlSpIdpConnection(
					samlSpIdpConnectionId);
		}

		renderRequest.setAttribute(
			AttributeMappingDisplayContext.class.getName(),
			new AttributeMappingDisplayContext(
				renderRequest, samlSpIdpConnection,
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY),
				_userFieldExpressionHandlerRegistry));
		renderRequest.setAttribute(
			SamlProviderConfigurationHelper.class.getName(),
			_samlProviderConfigurationHelper);
		renderRequest.setAttribute(
			SamlWebKeys.SAML_CLOCK_SKEW,
			_getClockSkew(renderRequest, samlSpIdpConnection));
		renderRequest.setAttribute(
			SamlWebKeys.SAML_SP_IDP_CONNECTION, samlSpIdpConnection);
		renderRequest.setAttribute(
			UserFieldExpressionResolverRegistry.class.getName(),
			_userFieldExpressionResolverRegistry);

		return "/admin/edit_identity_provider_connection.jsp";
	}

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

	@Reference
	private UserFieldExpressionHandlerRegistry
		_userFieldExpressionHandlerRegistry;

	@Reference
	private UserFieldExpressionResolverRegistry
		_userFieldExpressionResolverRegistry;

}