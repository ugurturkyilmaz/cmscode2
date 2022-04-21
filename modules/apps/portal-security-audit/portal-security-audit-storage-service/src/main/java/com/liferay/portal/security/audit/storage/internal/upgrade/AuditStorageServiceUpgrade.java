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

package com.liferay.portal.security.audit.storage.internal.upgrade;

import com.liferay.portal.kernel.upgrade.BaseSQLServerDatetimeUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.security.audit.storage.internal.upgrade.v1_0_1.SchemaUpgradeProcess;
import com.liferay.portal.security.audit.storage.internal.upgrade.v1_0_1.util.AuditEventTable;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeServiceModuleRelease;

import org.osgi.service.component.annotations.Component;

/**
 * @author Samuel Ziemer
 */
@Component(service = UpgradeStepRegistrator.class)
public class AuditStorageServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeServiceModuleRelease baseUpgradeServiceModuleRelease =
				new BaseUpgradeServiceModuleRelease() {

					@Override
					protected String getNamespace() {
						return "Audit";
					}

					@Override
					protected String getNewBundleSymbolicName() {
						return "com.liferay.portal.security.audit.storage." +
							"service";
					}

					@Override
					protected String getOldBundleSymbolicName() {
						return "audit-portlet";
					}

				};

			baseUpgradeServiceModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		registry.register("1.0.0", "1.0.1", new SchemaUpgradeProcess());

		registry.register(
			"1.0.1", "2.0.0",
			new BaseSQLServerDatetimeUpgradeProcess(
				new Class<?>[] {AuditEventTable.class}));

		registry.register("2.0.0", "2.0.1", new DummyUpgradeStep());
	}

}