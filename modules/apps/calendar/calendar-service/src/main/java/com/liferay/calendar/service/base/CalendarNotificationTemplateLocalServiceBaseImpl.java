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

package com.liferay.calendar.service.base;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.service.CalendarNotificationTemplateLocalService;
import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingFinder;
import com.liferay.calendar.service.persistence.CalendarBookingPersistence;
import com.liferay.calendar.service.persistence.CalendarFinder;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplatePersistence;
import com.liferay.calendar.service.persistence.CalendarPersistence;
import com.liferay.calendar.service.persistence.CalendarResourceFinder;
import com.liferay.calendar.service.persistence.CalendarResourcePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the calendar notification template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl
 * @generated
 */
public abstract class CalendarNotificationTemplateLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CalendarNotificationTemplateLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CalendarNotificationTemplateLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CalendarNotificationTemplateLocalServiceUtil</code>.
	 */

	/**
	 * Adds the calendar notification template to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CalendarNotificationTemplate addCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		calendarNotificationTemplate.setNew(true);

		return calendarNotificationTemplatePersistence.update(
			calendarNotificationTemplate);
	}

	/**
	 * Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	 *
	 * @param calendarNotificationTemplateId the primary key for the new calendar notification template
	 * @return the new calendar notification template
	 */
	@Override
	@Transactional(enabled = false)
	public CalendarNotificationTemplate createCalendarNotificationTemplate(
		long calendarNotificationTemplateId) {

		return calendarNotificationTemplatePersistence.create(
			calendarNotificationTemplateId);
	}

	/**
	 * Deletes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws PortalException if a calendar notification template with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CalendarNotificationTemplate deleteCalendarNotificationTemplate(
			long calendarNotificationTemplateId)
		throws PortalException {

		return calendarNotificationTemplatePersistence.remove(
			calendarNotificationTemplateId);
	}

	/**
	 * Deletes the calendar notification template from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		return calendarNotificationTemplatePersistence.remove(
			calendarNotificationTemplate);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return calendarNotificationTemplatePersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CalendarNotificationTemplate.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return calendarNotificationTemplatePersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return calendarNotificationTemplatePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return calendarNotificationTemplatePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return calendarNotificationTemplatePersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return calendarNotificationTemplatePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarNotificationTemplateId) {

		return calendarNotificationTemplatePersistence.fetchByPrimaryKey(
			calendarNotificationTemplateId);
	}

	/**
	 * Returns the calendar notification template matching the UUID and group.
	 *
	 * @param uuid the calendar notification template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate
		fetchCalendarNotificationTemplateByUuidAndGroupId(
			String uuid, long groupId) {

		return calendarNotificationTemplatePersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the calendar notification template with the primary key.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws PortalException if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate getCalendarNotificationTemplate(
			long calendarNotificationTemplateId)
		throws PortalException {

		return calendarNotificationTemplatePersistence.findByPrimaryKey(
			calendarNotificationTemplateId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			calendarNotificationTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			CalendarNotificationTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"calendarNotificationTemplateId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			calendarNotificationTemplateLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CalendarNotificationTemplate.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"calendarNotificationTemplateId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			calendarNotificationTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			CalendarNotificationTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"calendarNotificationTemplateId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CalendarNotificationTemplate>() {

				@Override
				public void performAction(
						CalendarNotificationTemplate
							calendarNotificationTemplate)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, calendarNotificationTemplate);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CalendarNotificationTemplate.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return calendarNotificationTemplatePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return calendarNotificationTemplateLocalService.
			deleteCalendarNotificationTemplate(
				(CalendarNotificationTemplate)persistedModel);
	}

	@Override
	public BasePersistence<CalendarNotificationTemplate> getBasePersistence() {
		return calendarNotificationTemplatePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return calendarNotificationTemplatePersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the calendar notification templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar notification templates
	 * @param companyId the primary key of the company
	 * @return the matching calendar notification templates, or an empty list if no matches were found
	 */
	@Override
	public List<CalendarNotificationTemplate>
		getCalendarNotificationTemplatesByUuidAndCompanyId(
			String uuid, long companyId) {

		return calendarNotificationTemplatePersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of calendar notification templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar notification templates
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching calendar notification templates, or an empty list if no matches were found
	 */
	@Override
	public List<CalendarNotificationTemplate>
		getCalendarNotificationTemplatesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CalendarNotificationTemplate> orderByComparator) {

		return calendarNotificationTemplatePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the calendar notification template matching the UUID and group.
	 *
	 * @param uuid the calendar notification template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar notification template
	 * @throws PortalException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate
			getCalendarNotificationTemplateByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return calendarNotificationTemplatePersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the calendar notification templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @return the range of calendar notification templates
	 */
	@Override
	public List<CalendarNotificationTemplate> getCalendarNotificationTemplates(
		int start, int end) {

		return calendarNotificationTemplatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of calendar notification templates.
	 *
	 * @return the number of calendar notification templates
	 */
	@Override
	public int getCalendarNotificationTemplatesCount() {
		return calendarNotificationTemplatePersistence.countAll();
	}

	/**
	 * Updates the calendar notification template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarNotificationTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 * @return the calendar notification template that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CalendarNotificationTemplate updateCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		return calendarNotificationTemplatePersistence.update(
			calendarNotificationTemplate);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CalendarNotificationTemplateLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		calendarNotificationTemplateLocalService =
			(CalendarNotificationTemplateLocalService)aopProxy;

		_setLocalServiceUtilService(calendarNotificationTemplateLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CalendarNotificationTemplateLocalService.class.getName();
	}

	@Override
	public CTPersistence<CalendarNotificationTemplate> getCTPersistence() {
		return calendarNotificationTemplatePersistence;
	}

	@Override
	public Class<CalendarNotificationTemplate> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CalendarNotificationTemplate>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(
			calendarNotificationTemplatePersistence);
	}

	protected String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				calendarNotificationTemplatePersistence.getDataSource();

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

	private void _setLocalServiceUtilService(
		CalendarNotificationTemplateLocalService
			calendarNotificationTemplateLocalService) {

		try {
			Field field =
				CalendarNotificationTemplateLocalServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, calendarNotificationTemplateLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected CalendarPersistence calendarPersistence;

	@Reference
	protected CalendarFinder calendarFinder;

	@Reference
	protected CalendarBookingPersistence calendarBookingPersistence;

	@Reference
	protected CalendarBookingFinder calendarBookingFinder;

	protected CalendarNotificationTemplateLocalService
		calendarNotificationTemplateLocalService;

	@Reference
	protected CalendarNotificationTemplatePersistence
		calendarNotificationTemplatePersistence;

	@Reference
	protected CalendarResourcePersistence calendarResourcePersistence;

	@Reference
	protected CalendarResourceFinder calendarResourceFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}