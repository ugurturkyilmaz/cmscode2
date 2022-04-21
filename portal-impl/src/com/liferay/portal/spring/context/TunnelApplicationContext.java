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

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.bean.LiferayBeanFactory;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author Brian Wing Shun Chan
 */
public class TunnelApplicationContext extends XmlWebApplicationContext {

	@Override
	public void setParent(ApplicationContext applicationContext) {
		if (applicationContext == null) {
			BeanLocatorImpl beanLocatorImpl =
				(BeanLocatorImpl)PortalBeanLocatorUtil.getBeanLocator();

			applicationContext = beanLocatorImpl.getApplicationContext();
		}

		super.setParent(applicationContext);
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		return new LiferayBeanFactory(getInternalParentBeanFactory());
	}

	@Override
	protected void loadBeanDefinitions(
		XmlBeanDefinitionReader xmlBeanDefinitionReader) {

		String[] configLocations = getConfigLocations();

		if (configLocations == null) {
			return;
		}

		for (String configLocation : configLocations) {
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
		TunnelApplicationContext.class);

}