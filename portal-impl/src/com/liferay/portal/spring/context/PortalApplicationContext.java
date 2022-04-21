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

package com.liferay.portal.spring.context;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.bean.LiferayBeanFactory;
import com.liferay.portal.util.PropsValues;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * <p>
 * This web application context will first load bean definitions in the
 * contextConfigLocation parameter in web.xml. Then, the context will load bean
 * definitions specified by the property "spring.configs" in portal.properties.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Tomas Polesovsky
 */
public class PortalApplicationContext extends XmlWebApplicationContext {

	public static final String PARENT_APPLICATION_CONTEXT =
		PortalApplicationContext.class.getName() +
			"#PARENT_APPLICATION_CONTEXT";

	@Override
	public ApplicationContext getParent() {
		return _parentApplicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);

		_parentApplicationContext =
			(ApplicationContext)servletContext.getAttribute(
				PARENT_APPLICATION_CONTEXT);

		setParent(_parentApplicationContext);
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		return new LiferayBeanFactory(getInternalParentBeanFactory());
	}

	@Override
	protected void loadBeanDefinitions(
		XmlBeanDefinitionReader xmlBeanDefinitionReader) {

		try {
			super.loadBeanDefinitions(xmlBeanDefinitionReader);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}
		}

		xmlBeanDefinitionReader.setResourceLoader(
			new PathMatchingResourcePatternResolver());

		if (PropsValues.SPRING_CONFIGS == null) {
			return;
		}

		for (String configLocation : PropsValues.SPRING_CONFIGS) {
			try {
				xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
			}
			catch (Exception exception) {
				Throwable throwable = exception.getCause();

				if (throwable instanceof FileNotFoundException) {
					if (_log.isWarnEnabled()) {
						_log.warn(throwable.getMessage());
					}
				}
				else {
					_log.error(exception);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalApplicationContext.class);

	private ApplicationContext _parentApplicationContext;

}