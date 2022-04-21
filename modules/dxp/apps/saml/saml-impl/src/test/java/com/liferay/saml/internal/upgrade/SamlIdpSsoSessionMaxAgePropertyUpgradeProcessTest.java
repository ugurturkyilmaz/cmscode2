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

package com.liferay.saml.internal.upgrade;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PropsImpl;
import com.liferay.saml.internal.constants.LegacySamlPropsKeys;
import com.liferay.saml.internal.upgrade.v1_0_0.SamlIdpSsoSessionMaxAgePropertyUpgradeProcess;
import com.liferay.saml.runtime.configuration.SamlProviderConfiguration;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Tomas Polesovsky
 */
@RunWith(PowerMockRunner.class)
public class SamlIdpSsoSessionMaxAgePropertyUpgradeProcessTest
	extends PowerMockito {

	@BeforeClass
	public static void setUpClass() {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testSamlProviderPropertyMapping() throws Exception {
		long samlIdpSsoSessionMaxAge = RandomTestUtil.randomLong();

		SamlProviderConfiguration samlProviderConfiguration =
			ConfigurableUtil.createConfigurable(
				SamlProviderConfiguration.class,
				HashMapBuilder.<String, Object>put(
					LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE,
					samlIdpSsoSessionMaxAge
				).build());

		Assert.assertEquals(
			samlIdpSsoSessionMaxAge,
			samlProviderConfiguration.sessionMaximumAge());
	}

	@Test
	public void testUpgradeWithDefaultProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = mock(ConfigurationAdmin.class);

		Configuration configuration = mock(Configuration.class);

		when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[] {configuration}
		);

		Props props = mock(Props.class);

		when(
			props.get(LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_MAX_AGE)
		).thenReturn(
			null
		);

		SamlIdpSsoSessionMaxAgePropertyUpgradeProcess
			samlIdpSsoSessionMaxAgePropertyUpgradeProcess =
				new SamlIdpSsoSessionMaxAgePropertyUpgradeProcess(
					configurationAdmin, props);

		samlIdpSsoSessionMaxAgePropertyUpgradeProcess.doUpgrade();

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE, "86400000");

		Configuration verifyConfiguration = Mockito.verify(
			configuration, Mockito.times(1));

		verifyConfiguration.update(Matchers.eq(properties));
	}

	@Test
	public void testUpgradeWithEmptyProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = mock(ConfigurationAdmin.class);

		Configuration configuration = mock(Configuration.class);

		when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[] {configuration}
		);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE, "0");

		when(
			configuration.getProperties()
		).thenReturn(
			properties
		);

		Props props = mock(Props.class);

		when(
			props.get(LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_MAX_AGE)
		).thenReturn(
			""
		);

		SamlIdpSsoSessionMaxAgePropertyUpgradeProcess
			samlIdpSsoSessionMaxAgePropertyUpgradeProcess =
				new SamlIdpSsoSessionMaxAgePropertyUpgradeProcess(
					configurationAdmin, props);

		samlIdpSsoSessionMaxAgePropertyUpgradeProcess.doUpgrade();

		properties = new Hashtable<>();

		properties.put(
			LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE, "86400000");

		Configuration verifyConfiguration = Mockito.verify(
			configuration, Mockito.times(1));

		verifyConfiguration.update(Matchers.eq(properties));
	}

	@Test
	public void testUpgradeWithProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = mock(ConfigurationAdmin.class);

		Configuration configuration = mock(Configuration.class);

		when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[] {configuration}
		);

		Props props = mock(Props.class);

		String samlIdpSsoSessionMaxAge = String.valueOf(
			RandomTestUtil.randomInt());

		when(
			props.get(LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_MAX_AGE)
		).thenReturn(
			samlIdpSsoSessionMaxAge
		);

		SamlIdpSsoSessionMaxAgePropertyUpgradeProcess
			samlIdpSsoSessionMaxAgePropertyUpgradeProcess =
				new SamlIdpSsoSessionMaxAgePropertyUpgradeProcess(
					configurationAdmin, props);

		samlIdpSsoSessionMaxAgePropertyUpgradeProcess.doUpgrade();

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE,
			samlIdpSsoSessionMaxAge);

		Configuration verifyConfiguration = Mockito.verify(
			configuration, Mockito.times(1));

		verifyConfiguration.update(Matchers.eq(properties));
	}

	@Test
	public void testUpgradeWithPropertyAlreadyOverwritten() throws Exception {
		ConfigurationAdmin configurationAdmin = mock(ConfigurationAdmin.class);

		Configuration configuration = mock(Configuration.class);

		when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[] {configuration}
		);

		Dictionary<String, Object> properties = new Hashtable<>();

		String samlIdpSsoSessionMaxAge = String.valueOf(
			RandomTestUtil.randomInt());

		properties.put(
			LegacySamlPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE,
			samlIdpSsoSessionMaxAge);

		when(
			configuration.getProperties()
		).thenReturn(
			properties
		);

		Props props = mock(Props.class);

		when(
			props.get(LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_MAX_AGE)
		).thenReturn(
			String.valueOf(RandomTestUtil.randomInt())
		);

		SamlIdpSsoSessionMaxAgePropertyUpgradeProcess
			samlIdpSsoSessionMaxAgePropertyUpgradeProcess =
				new SamlIdpSsoSessionMaxAgePropertyUpgradeProcess(
					configurationAdmin, props);

		samlIdpSsoSessionMaxAgePropertyUpgradeProcess.doUpgrade();

		Configuration verifyConfiguration = Mockito.verify(
			configuration, Mockito.never());

		verifyConfiguration.update(Mockito.any(Dictionary.class));
	}

}