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

package com.liferay.portal.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalService;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.LayoutSetBranchPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout set branch local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl
 * @generated
 */
public abstract class LayoutSetBranchLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, LayoutSetBranchLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutSetBranchLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LayoutSetBranchLocalServiceUtil</code>.
	 */

	/**
	 * Adds the layout set branch to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSetBranchLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSetBranch the layout set branch
	 * @return the layout set branch that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutSetBranch addLayoutSetBranch(LayoutSetBranch layoutSetBranch) {
		layoutSetBranch.setNew(true);

		return layoutSetBranchPersistence.update(layoutSetBranch);
	}

	/**
	 * Creates a new layout set branch with the primary key. Does not add the layout set branch to the database.
	 *
	 * @param layoutSetBranchId the primary key for the new layout set branch
	 * @return the new layout set branch
	 */
	@Override
	@Transactional(enabled = false)
	public LayoutSetBranch createLayoutSetBranch(long layoutSetBranchId) {
		return layoutSetBranchPersistence.create(layoutSetBranchId);
	}

	/**
	 * Deletes the layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSetBranchLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSetBranchId the primary key of the layout set branch
	 * @return the layout set branch that was removed
	 * @throws PortalException if a layout set branch with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutSetBranch deleteLayoutSetBranch(long layoutSetBranchId)
		throws PortalException {

		return layoutSetBranchPersistence.remove(layoutSetBranchId);
	}

	/**
	 * Deletes the layout set branch from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSetBranchLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSetBranch the layout set branch
	 * @return the layout set branch that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutSetBranch deleteLayoutSetBranch(
			LayoutSetBranch layoutSetBranch)
		throws PortalException {

		return layoutSetBranchPersistence.remove(layoutSetBranch);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return layoutSetBranchPersistence.dslQuery(dslQuery);
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
			LayoutSetBranch.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return layoutSetBranchPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutSetBranchModelImpl</code>.
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

		return layoutSetBranchPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutSetBranchModelImpl</code>.
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

		return layoutSetBranchPersistence.findWithDynamicQuery(
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
		return layoutSetBranchPersistence.countWithDynamicQuery(dynamicQuery);
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

		return layoutSetBranchPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LayoutSetBranch fetchLayoutSetBranch(long layoutSetBranchId) {
		return layoutSetBranchPersistence.fetchByPrimaryKey(layoutSetBranchId);
	}

	/**
	 * Returns the layout set branch with the primary key.
	 *
	 * @param layoutSetBranchId the primary key of the layout set branch
	 * @return the layout set branch
	 * @throws PortalException if a layout set branch with the primary key could not be found
	 */
	@Override
	public LayoutSetBranch getLayoutSetBranch(long layoutSetBranchId)
		throws PortalException {

		return layoutSetBranchPersistence.findByPrimaryKey(layoutSetBranchId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(layoutSetBranchLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutSetBranch.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutSetBranchId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			layoutSetBranchLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LayoutSetBranch.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutSetBranchId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(layoutSetBranchLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutSetBranch.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutSetBranchId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutSetBranchPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return layoutSetBranchLocalService.deleteLayoutSetBranch(
			(LayoutSetBranch)persistedModel);
	}

	@Override
	public BasePersistence<LayoutSetBranch> getBasePersistence() {
		return layoutSetBranchPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutSetBranchPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the layout set branches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutSetBranchModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout set branches
	 * @param end the upper bound of the range of layout set branches (not inclusive)
	 * @return the range of layout set branches
	 */
	@Override
	public List<LayoutSetBranch> getLayoutSetBranchs(int start, int end) {
		return layoutSetBranchPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layout set branches.
	 *
	 * @return the number of layout set branches
	 */
	@Override
	public int getLayoutSetBranchsCount() {
		return layoutSetBranchPersistence.countAll();
	}

	/**
	 * Updates the layout set branch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSetBranchLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSetBranch the layout set branch
	 * @return the layout set branch that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutSetBranch updateLayoutSetBranch(
		LayoutSetBranch layoutSetBranch) {

		return layoutSetBranchPersistence.update(layoutSetBranch);
	}

	/**
	 * Returns the layout set branch local service.
	 *
	 * @return the layout set branch local service
	 */
	public LayoutSetBranchLocalService getLayoutSetBranchLocalService() {
		return layoutSetBranchLocalService;
	}

	/**
	 * Sets the layout set branch local service.
	 *
	 * @param layoutSetBranchLocalService the layout set branch local service
	 */
	public void setLayoutSetBranchLocalService(
		LayoutSetBranchLocalService layoutSetBranchLocalService) {

		this.layoutSetBranchLocalService = layoutSetBranchLocalService;
	}

	/**
	 * Returns the layout set branch persistence.
	 *
	 * @return the layout set branch persistence
	 */
	public LayoutSetBranchPersistence getLayoutSetBranchPersistence() {
		return layoutSetBranchPersistence;
	}

	/**
	 * Sets the layout set branch persistence.
	 *
	 * @param layoutSetBranchPersistence the layout set branch persistence
	 */
	public void setLayoutSetBranchPersistence(
		LayoutSetBranchPersistence layoutSetBranchPersistence) {

		this.layoutSetBranchPersistence = layoutSetBranchPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.LayoutSetBranch",
			layoutSetBranchLocalService);

		_setLocalServiceUtilService(layoutSetBranchLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.LayoutSetBranch");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutSetBranchLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LayoutSetBranch.class;
	}

	protected String getModelClassName() {
		return LayoutSetBranch.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutSetBranchPersistence.getDataSource();

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
		LayoutSetBranchLocalService layoutSetBranchLocalService) {

		try {
			Field field =
				LayoutSetBranchLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, layoutSetBranchLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = LayoutSetBranchLocalService.class)
	protected LayoutSetBranchLocalService layoutSetBranchLocalService;

	@BeanReference(type = LayoutSetBranchPersistence.class)
	protected LayoutSetBranchPersistence layoutSetBranchPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}