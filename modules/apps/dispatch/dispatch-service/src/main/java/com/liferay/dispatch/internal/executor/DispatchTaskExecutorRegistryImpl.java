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

package com.liferay.dispatch.internal.executor;

import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorRegistry;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Matija Petanjek
 * @author Joe Duffy
 * @author Igor Beslic
 */
@Component(service = DispatchTaskExecutorRegistry.class)
public class DispatchTaskExecutorRegistryImpl
	implements DispatchTaskExecutorRegistry {

	@Override
	public DispatchTaskExecutor fetchDispatchTaskExecutor(
		String dispatchTaskExecutorType) {

		return _dispatchTaskExecutors.get(dispatchTaskExecutorType);
	}

	@Override
	public String fetchDispatchTaskExecutorName(
		String dispatchTaskExecutorType) {

		return _dispatchTaskExecutorNames.get(dispatchTaskExecutorType);
	}

	@Override
	public Set<String> getDispatchTaskExecutorTypes() {
		return _dispatchTaskExecutors.keySet();
	}

	@Override
	public boolean isHiddenInUI(String type) {
		if (!_dispatchTaskExecutorNames.containsKey(type)) {
			return true;
		}

		return false;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDispatchTaskExecutor(
		DispatchTaskExecutor dispatchTaskExecutor,
		Map<String, Object> properties) {

		String dispatchTaskExecutorType = (String)properties.get(
			_KEY_DISPATCH_TASK_EXECUTOR_TYPE);

		_validateDispatchTaskExecutorProperties(
			dispatchTaskExecutor, dispatchTaskExecutorType);

		if (!GetterUtil.getBoolean(
				properties.get(_KEY_DISPATCH_TASK_EXECUTOR_HIDDEN_IN_UI))) {

			_dispatchTaskExecutorNames.put(
				dispatchTaskExecutorType,
				(String)properties.get(_KEY_DISPATCH_TASK_EXECUTOR_NAME));
		}

		_dispatchTaskExecutors.put(
			dispatchTaskExecutorType, dispatchTaskExecutor);
	}

	protected void removeDispatchTaskExecutor(
		DispatchTaskExecutor dispatchTaskExecutor,
		Map<String, Object> properties) {

		String dispatchTaskExecutorType = (String)properties.get(
			_KEY_DISPATCH_TASK_EXECUTOR_TYPE);

		_dispatchTaskExecutorNames.remove(dispatchTaskExecutorType);
		_dispatchTaskExecutors.remove(dispatchTaskExecutorType);
	}

	private void _validateDispatchTaskExecutorProperties(
		DispatchTaskExecutor dispatchTaskExecutor,
		String dispatchTaskExecutorType) {

		if (!_dispatchTaskExecutors.containsKey(dispatchTaskExecutorType)) {
			return;
		}

		DispatchTaskExecutor curDispatchTaskExecutor =
			_dispatchTaskExecutors.get(dispatchTaskExecutorType);

		Class<?> clazz1 = curDispatchTaskExecutor.getClass();

		Class<?> clazz2 = dispatchTaskExecutor.getClass();

		_log.error(
			StringBundler.concat(
				_KEY_DISPATCH_TASK_EXECUTOR_TYPE, " property must have unique ",
				"value. The same value is found in ", clazz1.getName(), " and ",
				clazz2.getName(), StringPool.PERIOD));
	}

	private static final String _KEY_DISPATCH_TASK_EXECUTOR_HIDDEN_IN_UI =
		"dispatch.task.executor.hidden-in-ui";

	private static final String _KEY_DISPATCH_TASK_EXECUTOR_NAME =
		"dispatch.task.executor.name";

	private static final String _KEY_DISPATCH_TASK_EXECUTOR_TYPE =
		"dispatch.task.executor.type";

	private static final Log _log = LogFactoryUtil.getLog(
		DispatchTaskExecutorRegistryImpl.class);

	private final Map<String, String> _dispatchTaskExecutorNames =
		new HashMap<>();
	private final Map<String, DispatchTaskExecutor> _dispatchTaskExecutors =
		new HashMap<>();

}