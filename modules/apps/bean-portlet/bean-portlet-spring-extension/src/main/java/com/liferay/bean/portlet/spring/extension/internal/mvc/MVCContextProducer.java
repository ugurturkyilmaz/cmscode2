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

package com.liferay.bean.portlet.spring.extension.internal.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.mvc.MvcContext;
import javax.mvc.locale.LocaleResolver;
import javax.mvc.security.Encoders;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Neil Griffin
 */
@Configuration
public class MVCContextProducer implements ApplicationContextAware {

	@Bean("mvc")
	@Scope("portletRequest")
	public MvcContext getMvcContext() {
		return new MVCContextImpl(
			_configuration, _encoders, _localeResolvers, _portletContext,
			_portletRequest);
	}

	@PostConstruct
	public void postConstruct() {
		Map<String, LocaleResolver> beansOfType =
			_applicationContext.getBeansOfType(LocaleResolver.class);

		_localeResolvers = new ArrayList<>(beansOfType.values());

		Collections.sort(
			_localeResolvers, new LocaleResolverPriorityComparator());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		_applicationContext = applicationContext;
	}

	private ApplicationContext _applicationContext;

	@Autowired
	private javax.ws.rs.core.Configuration _configuration;

	@Autowired
	private Encoders _encoders;

	private List<LocaleResolver> _localeResolvers;

	@Autowired
	private PortletContext _portletContext;

	@Autowired
	private PortletRequest _portletRequest;

	private static class LocaleResolverPriorityComparator
		extends DescendingPriorityComparator<LocaleResolver> {

		private LocaleResolverPriorityComparator() {

			// The Javadoc for javax.mvc.locale.LocaleResolver states "If no
			// priority is explicitly defined, the priority is assumed to be
			// 1000."

			super(1000);
		}

	}

}