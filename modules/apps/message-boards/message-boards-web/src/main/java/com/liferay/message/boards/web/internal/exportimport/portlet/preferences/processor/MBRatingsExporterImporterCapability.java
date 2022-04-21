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

package com.liferay.message.boards.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.portlet.preferences.processor.Capability;

import java.util.Map;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cristina González
 */
@Component(
	immediate = true,
	service = {Capability.class, MBRatingsExporterImporterCapability.class}
)
public class MBRatingsExporterImporterCapability implements Capability {

	@Override
	public PortletPreferences process(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

		parameterMap.put(
			PortletDataHandlerKeys.RATINGS,
			new String[] {Boolean.TRUE.toString()});

		return portletPreferences;
	}

}