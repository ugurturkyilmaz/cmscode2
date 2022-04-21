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

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal.osgi.commands;

import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.util.TensorFlowProcessHolder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = {
		"osgi.command.function=" + TensorFlowAssetAutoTagProviderOSGiCommands.RESET_PROCESS_COUNTER,
		"osgi.command.scope=" + TensorFlowAssetAutoTagProviderOSGiCommands.SCOPE
	},
	service = TensorFlowAssetAutoTagProviderOSGiCommands.class
)
public class TensorFlowAssetAutoTagProviderOSGiCommands {

	public static final String RESET_PROCESS_COUNTER = "resetProcessCounter";

	public static final String SCOPE = "tensorFlowAssetAutoTagProvider";

	public void resetProcessCounter() {
		TensorFlowProcessHolder.resetCounter();
	}

}