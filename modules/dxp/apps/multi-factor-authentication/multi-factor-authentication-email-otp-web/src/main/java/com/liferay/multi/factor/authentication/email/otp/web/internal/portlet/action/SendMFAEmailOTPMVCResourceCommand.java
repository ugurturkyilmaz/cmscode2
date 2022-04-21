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

package com.liferay.multi.factor.authentication.email.otp.web.internal.portlet.action;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.template.MailTemplate;
import com.liferay.mail.kernel.template.MailTemplateContext;
import com.liferay.mail.kernel.template.MailTemplateContextBuilder;
import com.liferay.mail.kernel.template.MailTemplateFactoryUtil;
import com.liferay.multi.factor.authentication.email.otp.configuration.MFAEmailOTPConfiguration;
import com.liferay.multi.factor.authentication.email.otp.web.internal.constants.MFAEmailOTPPortletKeys;
import com.liferay.multi.factor.authentication.email.otp.web.internal.constants.MFAEmailOTPWebKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthToken;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PwdGenerator;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Arthur Chan
 */
@Component(
	property = {
		"javax.portlet.name=" + MFAEmailOTPPortletKeys.MFA_EMAIL_OTP_VERIFY,
		"mvc.command.name=/mfa_email_otp_verify/send_mfa_email_otp"
	},
	service = MVCResourceCommand.class
)
public class SendMFAEmailOTPMVCResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		HttpServletRequest httpServletRequest =
			_portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(resourceRequest));

		try {
			_authToken.checkCSRFToken(
				httpServletRequest,
				SendMFAEmailOTPMVCResourceCommand.class.getName());
		}
		catch (PrincipalException principalException) {
			throw new PortletException(principalException);
		}

		try {
			return _serveResource(httpServletRequest);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	private void _sendNotificationEmail(
			String fromAddress, String fromName, String toAddress, User toUser,
			String subject, String body,
			MailTemplateContext mailTemplateContext)
		throws Exception {

		MailTemplate subjectMailTemplate =
			MailTemplateFactoryUtil.createMailTemplate(subject, false);
		MailTemplate bodyMailTemplate =
			MailTemplateFactoryUtil.createMailTemplate(body, true);

		MailMessage mailMessage = new MailMessage(
			new InternetAddress(fromAddress, fromName),
			new InternetAddress(toAddress, toUser.getFullName()),
			subjectMailTemplate.renderAsString(
				toUser.getLocale(), mailTemplateContext),
			bodyMailTemplate.renderAsString(
				toUser.getLocale(), mailTemplateContext),
			true);

		Company company = _companyLocalService.getCompany(
			toUser.getCompanyId());

		mailMessage.setMessageId(
			_portal.getMailId(company.getMx(), "user", toUser.getUserId()));

		_mailService.sendEmail(mailMessage);

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"One-time password email sent to user ", toUser.getUserId(),
					" at address ", toAddress));
		}
	}

	private boolean _serveResource(HttpServletRequest httpServletRequest)
		throws Exception {

		HttpSession httpSession = httpServletRequest.getSession();

		Long mfaEmailOTPUserId = (Long)httpSession.getAttribute(
			MFAEmailOTPWebKeys.MFA_EMAIL_OTP_USER_ID);

		if (mfaEmailOTPUserId == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("User ID is not in the session");
			}

			return false;
		}

		User user = _userLocalService.getUserById(mfaEmailOTPUserId);

		MFAEmailOTPConfiguration mfaEmailOTPConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MFAEmailOTPConfiguration.class, user.getCompanyId());

		if (mfaEmailOTPConfiguration == null) {
			return false;
		}

		long mfaEmailOTPSetAtTime = GetterUtil.getLong(
			httpSession.getAttribute(
				MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SET_AT_TIME),
			Long.MIN_VALUE);

		long resendEmailTimeout = mfaEmailOTPConfiguration.resendEmailTimeout();

		if (resendEmailTimeout > 0) {
			long time = mfaEmailOTPSetAtTime + (resendEmailTimeout * 1000);

			if (System.currentTimeMillis() <= time) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Refusing to send email before resend timeout for " +
							"user " + user.getUserId());
				}

				return false;
			}
		}

		String generatedMFAEmailOTP = PwdGenerator.getPassword(
			mfaEmailOTPConfiguration.otpSize());

		httpSession.setAttribute(
			MFAEmailOTPWebKeys.MFA_EMAIL_OTP, generatedMFAEmailOTP);

		httpSession.setAttribute(
			MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SET_AT_TIME,
			System.currentTimeMillis());

		LocalizedValuesMap subjectLocalizedValuesMap =
			mfaEmailOTPConfiguration.emailOTPSentSubject();

		String emailOTPSubject = subjectLocalizedValuesMap.get(
			user.getLocale());

		LocalizedValuesMap bodyLocalizedValuesMap =
			mfaEmailOTPConfiguration.emailOTPSentBody();

		String emailOTPBody = bodyLocalizedValuesMap.get(user.getLocale());

		MailTemplateContextBuilder mailTemplateContextBuilder =
			MailTemplateFactoryUtil.createMailTemplateContextBuilder();

		mailTemplateContextBuilder.put(
			"[$FROM_ADDRESS$]", mfaEmailOTPConfiguration.emailFromAddress());
		mailTemplateContextBuilder.put(
			"[$FROM_NAME$]",
			HtmlUtil.escape(mfaEmailOTPConfiguration.emailFromName()));
		mailTemplateContextBuilder.put(
			"[$ONE_TIME_PASSWORD$]", HtmlUtil.escape(generatedMFAEmailOTP));
		mailTemplateContextBuilder.put(
			"[$PORTAL_URL$]", _portal.getPortalURL(httpServletRequest));
		mailTemplateContextBuilder.put(
			"[$REMOTE_ADDRESS$]", httpServletRequest.getRemoteAddr());
		mailTemplateContextBuilder.put(
			"[$REMOTE_HOST$]",
			HtmlUtil.escape(httpServletRequest.getRemoteHost()));
		mailTemplateContextBuilder.put(
			"[$TO_NAME$]", HtmlUtil.escape(user.getFullName()));

		_sendNotificationEmail(
			mfaEmailOTPConfiguration.emailFromAddress(),
			mfaEmailOTPConfiguration.emailFromName(), user.getEmailAddress(),
			user, emailOTPSubject, emailOTPBody,
			mailTemplateContextBuilder.build());

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendMFAEmailOTPMVCResourceCommand.class);

	@Reference
	private AuthToken _authToken;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private MailService _mailService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}