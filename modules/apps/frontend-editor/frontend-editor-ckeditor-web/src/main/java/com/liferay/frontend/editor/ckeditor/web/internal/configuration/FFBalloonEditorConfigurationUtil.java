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

package com.liferay.frontend.editor.ckeditor.web.internal.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Diego Nascimento
 */
@Component(
	configurationPid = "com.liferay.frontend.editor.ckeditor.web.internal.configuration.FFBalloonEditorConfiguration",
	immediate = true, service = {}
)
public class FFBalloonEditorConfigurationUtil {

	public static boolean enable() {
		return _ffBalloonEditorConfiguration.enable();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_ffBalloonEditorConfiguration = ConfigurableUtil.createConfigurable(
			FFBalloonEditorConfiguration.class, properties);
	}

	private static volatile FFBalloonEditorConfiguration
		_ffBalloonEditorConfiguration;

}