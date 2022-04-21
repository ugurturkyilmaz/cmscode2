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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PropsImpl;
import com.liferay.saml.internal.upgrade.v1_0_0.SamlKeyStorePropertiesUpgradeProcess;

import java.util.Dictionary;
import java.util.Hashtable;

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
 * @author Carlos Sierra
 */
@RunWith(PowerMockRunner.class)
public class SamlKeyStorePropertiesUpgradeProcessTest extends PowerMockito {

	@BeforeClass
	public static void setUpClass() {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testUpgradeWithEmptyProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = spy(
			mock(ConfigurationAdmin.class));

		PrefsProps prefsProps = mock(PrefsProps.class);

		when(
			prefsProps.getString("saml.keystore.manager.impl")
		).thenReturn(
			""
		);

		SamlKeyStorePropertiesUpgradeProcess
			samlKeyStorePropertiesUpgradeProcess =
				new SamlKeyStorePropertiesUpgradeProcess(
					configurationAdmin, prefsProps);

		samlKeyStorePropertiesUpgradeProcess.doUpgrade();

		verifyZeroInteractions(configurationAdmin);
	}

	@Test
	public void testUpgradeWithNullProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = spy(
			mock(ConfigurationAdmin.class));

		PrefsProps prefsProps = mock(PrefsProps.class);

		when(
			prefsProps.getString("saml.keystore.manager.impl")
		).thenReturn(
			null
		);

		SamlKeyStorePropertiesUpgradeProcess
			samlKeyStorePropertiesUpgradeProcess =
				new SamlKeyStorePropertiesUpgradeProcess(
					configurationAdmin, prefsProps);

		samlKeyStorePropertiesUpgradeProcess.doUpgrade();

		verifyZeroInteractions(configurationAdmin);
	}

	@Test
	public void testUpgradeWithProperty() throws Exception {
		ConfigurationAdmin configurationAdmin = spy(
			mock(ConfigurationAdmin.class));

		Configuration configuration = spy(mock(Configuration.class));

		when(
			configurationAdmin.getConfiguration(
				Mockito.anyString(), Matchers.eq(StringPool.QUESTION))
		).thenReturn(
			configuration
		);

		PrefsProps prefsProps = mock(PrefsProps.class);

		String samlKeyStoreManagerImpl = RandomTestUtil.randomString();

		when(
			prefsProps.getString("saml.keystore.manager.impl")
		).thenReturn(
			samlKeyStoreManagerImpl
		);

		SamlKeyStorePropertiesUpgradeProcess
			samlKeyStorePropertiesUpgradeProcess =
				new SamlKeyStorePropertiesUpgradeProcess(
					configurationAdmin, prefsProps);

		samlKeyStorePropertiesUpgradeProcess.doUpgrade();

		ConfigurationAdmin verifyConfigurationAdmin = Mockito.verify(
			configurationAdmin, Mockito.times(1));

		verifyConfigurationAdmin.getConfiguration(
			Matchers.eq(
				"com.liferay.saml.runtime.configuration." +
					"SamlKeyStoreManagerConfiguration"),
			Matchers.eq(StringPool.QUESTION));

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			"KeyStoreManager.target",
			"(component.name=" + samlKeyStoreManagerImpl + ")");

		Configuration verifyConfiguration = Mockito.verify(
			configuration, Mockito.times(1));

		verifyConfiguration.update(Matchers.eq(properties));
	}

}