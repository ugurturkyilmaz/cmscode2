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

package com.liferay.portal.vulcan.internal.jaxrs.validation;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.ContextProviderUtil;

import java.io.IOException;

import java.lang.reflect.Method;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.validation.AbstractValidationInterceptor;

/**
 * @author Javier Gamarra
 */
@Provider
public class BeanValidationInterceptor
	extends AbstractValidationInterceptor implements ContainerRequestFilter {

	public BeanValidationInterceptor() {
		super("pre-invoke");
	}

	public BeanValidationInterceptor(String phase) {
		super(phase);
	}

	@Override
	public void filter(ContainerRequestContext containerRequestContext)
		throws IOException {

		Message message = PhaseInterceptorChain.getCurrentMessage();

		InterceptorChain interceptorChain = message.getInterceptorChain();

		interceptorChain.add(this);
	}

	public void handleMessage(Message message) throws Fault {
		Object serviceObject = getServiceObject(message);

		if (serviceObject == null) {
			return;
		}

		Method method = getServiceMethod(message);

		if (method == null) {
			return;
		}

		List<Object> arguments = MessageContentsList.getContentsList(message);

		handleValidation(message, serviceObject, method, arguments);
	}

	@Override
	protected Object getServiceObject(Message message) {
		return ContextProviderUtil.getMatchedResource(message);
	}

	@Override
	protected void handleValidation(
		Message message, Object resource, Method method,
		List<Object> arguments) {

		if (ListUtil.isEmpty(arguments)) {
			return;
		}

		ValidationUtil.validateArguments(resource, method, arguments.toArray());
	}

}