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

package com.liferay.multi.factor.authentication.fido2.web.internal.settings.definition;

import com.liferay.multi.factor.authentication.fido2.web.internal.configuration.MFAFIDO2Configuration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	property = "mfa.visibility.configuration.pid=com.liferay.multi.factor.authentication.fido2.web.internal.configuration.MFAFIDO2Configuration",
	service = ConfigurationBeanDeclaration.class
)
public class MFAFIDO2ConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return MFAFIDO2Configuration.class;
	}

}