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

package com.liferay.saml.web.internal.struts;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserEmailAddressException.MustNotUseCompanyMx;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.constants.SamlWebKeys;
import com.liferay.saml.persistence.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.exception.AuthnAgeException;
import com.liferay.saml.runtime.exception.EntityInteractionException;
import com.liferay.saml.runtime.exception.SubjectException;
import com.liferay.saml.runtime.servlet.profile.WebSsoProfile;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true, property = "path=/portal/saml/acs",
	service = StrutsAction.class
)
public class AssertionConsumerServiceAction extends BaseSamlStrutsAction {

	@Override
	public boolean isEnabled() {
		if (samlProviderConfigurationHelper.isRoleSp()) {
			return super.isEnabled();
		}

		return false;
	}

	@Override
	@Reference(unbind = "-")
	public void setSamlProviderConfigurationHelper(
		SamlProviderConfigurationHelper samlProviderConfigurationHelper) {

		super.setSamlProviderConfigurationHelper(
			samlProviderConfigurationHelper);
	}

	@Override
	protected String doExecute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			_webSsoProfile.processResponse(
				httpServletRequest, httpServletResponse);
		}
		catch (EntityInteractionException entityInteractionException) {
			HttpServletRequest originalHttpServletRequest =
				_portal.getOriginalServletRequest(httpServletRequest);

			HttpSession httpSession = originalHttpServletRequest.getSession();

			httpSession.setAttribute(
				com.liferay.saml.web.internal.constants.SamlWebKeys.
					SAML_SSO_ERROR_ENTITY_ID,
				entityInteractionException.getEntityId());
			httpSession.setAttribute(
				SamlWebKeys.SAML_SUBJECT_NAME_ID,
				entityInteractionException.getNameIdValue());

			Throwable causeThrowable = entityInteractionException.getCause();

			String error = StringPool.BLANK;

			if (causeThrowable instanceof AuthnAgeException) {
				error = AuthnAgeException.class.getSimpleName();
			}
			else if (causeThrowable instanceof ContactNameException) {
				error = ContactNameException.class.getSimpleName();
			}
			else if (causeThrowable instanceof SubjectException) {
				error = SubjectException.class.getSimpleName();
			}
			else if (causeThrowable instanceof UserEmailAddressException) {
				if (causeThrowable instanceof MustNotUseCompanyMx) {
					error = MustNotUseCompanyMx.class.getSimpleName();
				}
				else {
					error = UserEmailAddressException.class.getSimpleName();
				}
			}
			else if (causeThrowable instanceof UserScreenNameException) {
				error = UserScreenNameException.class.getSimpleName();
			}
			else {
				Class<?> clazz = causeThrowable.getClass();

				error = clazz.getSimpleName();
			}

			httpSession.setAttribute(SamlWebKeys.SAML_SSO_ERROR, error);

			String redirect = ParamUtil.getString(
				httpServletRequest, "RelayState");

			redirect = _portal.escapeRedirect(redirect);

			if (Validator.isNull(redirect)) {
				redirect = _portal.getHomeURL(httpServletRequest);
			}

			try {
				httpServletResponse.sendRedirect(redirect);

				return null;
			}
			catch (IOException ioException) {
				throw new SystemException(ioException);
			}
		}

		return null;
	}

	@Reference
	private Portal _portal;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

	@Reference
	private WebSsoProfile _webSsoProfile;

}