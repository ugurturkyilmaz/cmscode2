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

package com.liferay.multi.factor.authentication.fido2.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Arthur Chan
 * @review
 */
@ExtendedObjectClassDefinition(
	category = "multi-factor-authentication",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.multi.factor.authentication.fido2.web.internal.configuration.MFAFIDO2Configuration",
	localization = "content/Language", name = "mfa-fido2-configuration-name"
)
public interface MFAFIDO2Configuration {

	@Meta.AD(
		deflt = "false", description = "mfa-fido2-enabled-description",
		name = "enabled", required = false
	)
	public boolean enabled();

	@Meta.AD(
		deflt = "200", description = "order-description",
		id = "service.ranking", name = "order", required = false
	)
	public int order();

	@Meta.AD(
		deflt = "relying Party", description = "relying-party-name-description",
		name = "relying-party-name", required = false
	)
	public String relyingPartyName();

	/**
	 * Number of allowed credentials(authenticators) per user.
	 */
	@Meta.AD(
		deflt = "1", description = "allowed-credentials-per-user-description",
		name = "allowed-credentials-per-user", required = false
	)
	public int allowedCredentialsPerUser();

	/**
	 * The RelyingParty ID must be equal to the origin's effective domain,
	 * or a registrable domain suffix of the origin's effective domain.
	 * For example an origin of https://login.example.com:1337
	 * can only have rpID as one of the following:
	 * login.example.com or example.com
	 * This is done in order to match the behavior of pervasively deployed
	 * ambient credentials (e.g., cookies,  [RFC6265])
	 */
	@Meta.AD(
		deflt = "localhost", description = "relying-party-id-description",
		name = "relying-party-id", required = false
	)
	public String relyingPartyId();

	/**
	 * The allowed origins that returned authenticator responses will be
	 * compared against. The default is the set containing only the string
	 * <code>"https://" + {@link #getIdentity()}.getId()</code>.
	 */
	@Meta.AD(
		deflt = "https://localhost", description = "origins-description",
		name = "origins", required = false
	)
	public String[] origins();

	/**
	 * If <code>true</code>, the origin matching rule is relaxed to allow any
	 * port number.
	 */
	@Meta.AD(
		deflt = "false", description = "allow-origin-port-description",
		name = "allow-origin-port", required = false
	)
	public boolean allowOriginPort();

	/**
	 * If <code>true</code>, the origin matching rule is relaxed to allow any
	 * subdomain, of any depth, of the values of RelyingPartyBuilder#origins(
	 * Set) origins.
	 */
	@Meta.AD(
		deflt = "false", description = "allow-origin-subdomain-description",
		name = "allow-origin-subdomain", required = false
	)
	public boolean allowOriginSubdomain();

}