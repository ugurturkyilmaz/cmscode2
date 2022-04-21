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

package com.liferay.segments.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceService;
import com.liferay.segments.service.SegmentsExperienceServiceUtil;
import com.liferay.segments.service.persistence.SegmentsEntryPersistence;
import com.liferay.segments.service.persistence.SegmentsEntryRelPersistence;
import com.liferay.segments.service.persistence.SegmentsEntryRolePersistence;
import com.liferay.segments.service.persistence.SegmentsExperiencePersistence;
import com.liferay.segments.service.persistence.SegmentsExperimentFinder;
import com.liferay.segments.service.persistence.SegmentsExperimentPersistence;
import com.liferay.segments.service.persistence.SegmentsExperimentRelPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the segments experience remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.segments.service.impl.SegmentsExperienceServiceImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see com.liferay.segments.service.impl.SegmentsExperienceServiceImpl
 * @generated
 */
public abstract class SegmentsExperienceServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, SegmentsExperienceService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SegmentsExperienceService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SegmentsExperienceServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SegmentsExperienceService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		segmentsExperienceService = (SegmentsExperienceService)aopProxy;

		_setServiceUtilService(segmentsExperienceService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SegmentsExperienceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SegmentsExperience.class;
	}

	protected String getModelClassName() {
		return SegmentsExperience.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				segmentsExperiencePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(
		SegmentsExperienceService segmentsExperienceService) {

		try {
			Field field = SegmentsExperienceServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, segmentsExperienceService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected SegmentsEntryPersistence segmentsEntryPersistence;

	@Reference
	protected SegmentsEntryRelPersistence segmentsEntryRelPersistence;

	@Reference
	protected SegmentsEntryRolePersistence segmentsEntryRolePersistence;

	@Reference
	protected com.liferay.segments.service.SegmentsExperienceLocalService
		segmentsExperienceLocalService;

	protected SegmentsExperienceService segmentsExperienceService;

	@Reference
	protected SegmentsExperiencePersistence segmentsExperiencePersistence;

	@Reference
	protected SegmentsExperimentPersistence segmentsExperimentPersistence;

	@Reference
	protected SegmentsExperimentFinder segmentsExperimentFinder;

	@Reference
	protected SegmentsExperimentRelPersistence segmentsExperimentRelPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

}