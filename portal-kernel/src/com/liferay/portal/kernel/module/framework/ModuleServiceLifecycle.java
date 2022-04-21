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

package com.liferay.portal.kernel.module.framework;

/**
 * @author Miguel Ángel Pastor Olivar
 */
public interface ModuleServiceLifecycle {

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public String DATABASE_INITIALIZED =
		"(module.service.lifecycle=database.initialized)";

	public String LICENSE_INSTALL =
		"(module.service.lifecycle=license.install)";

	public String PORTAL_INITIALIZED =
		"(module.service.lifecycle=portal.initialized)";

	public String PORTLETS_INITIALIZED =
		"(module.service.lifecycle=portlets.initialized)";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public String SPRING_INITIALIZED =
		"(module.service.lifecycle=spring.initialized)";

	public String SYSTEM_CHECK = "(module.service.lifecycle=system.check)";

}