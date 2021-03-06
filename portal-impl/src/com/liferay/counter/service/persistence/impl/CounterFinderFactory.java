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

package com.liferay.counter.service.persistence.impl;

import com.liferay.counter.kernel.service.persistence.CounterFinder;
import com.liferay.portal.dao.orm.hibernate.SessionFactoryImpl;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

import javax.sql.DataSource;

/**
 * @author Shuyang Zhou
 */
public class CounterFinderFactory {

	public static CounterFinder createCounterFinder(
		DataSource dataSource, SessionFactory sessionFactory) {

		if (sessionFactory instanceof SessionFactoryImpl) {
			SessionFactoryImpl sessionFactoryImpl =
				(SessionFactoryImpl)sessionFactory;

			sessionFactoryImpl.setSessionFactoryClassLoader(
				PortalClassLoaderUtil.getClassLoader());
		}

		CounterFinderImpl counterFinderImpl = null;

		if (PropsValues.COUNTER_DATA_CENTER_COUNT > 1) {
			counterFinderImpl = new MultiDataCenterCounterFinderImpl(
				PropsValues.COUNTER_DATA_CENTER_COUNT,
				PropsValues.COUNTER_DATA_CENTER_DEPLOYMENT_ID);
		}
		else {
			counterFinderImpl = new CounterFinderImpl();
		}

		counterFinderImpl.setDataSource(dataSource);
		counterFinderImpl.setSessionFactory(sessionFactory);

		return counterFinderImpl;
	}

}