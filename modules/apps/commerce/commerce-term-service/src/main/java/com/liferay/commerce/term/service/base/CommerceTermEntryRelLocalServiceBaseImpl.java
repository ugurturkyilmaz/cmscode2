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

package com.liferay.commerce.term.service.base;

import com.liferay.commerce.term.model.CommerceTermEntryRel;
import com.liferay.commerce.term.service.CommerceTermEntryRelLocalService;
import com.liferay.commerce.term.service.CommerceTermEntryRelLocalServiceUtil;
import com.liferay.commerce.term.service.persistence.CTermEntryLocalizationPersistence;
import com.liferay.commerce.term.service.persistence.CommerceTermEntryPersistence;
import com.liferay.commerce.term.service.persistence.CommerceTermEntryRelPersistence;
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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
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
 * Provides the base implementation for the commerce term entry rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.term.service.impl.CommerceTermEntryRelLocalServiceImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.term.service.impl.CommerceTermEntryRelLocalServiceImpl
 * @generated
 */
public abstract class CommerceTermEntryRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CommerceTermEntryRelLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceTermEntryRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceTermEntryRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce term entry rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTermEntryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTermEntryRel the commerce term entry rel
	 * @return the commerce term entry rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceTermEntryRel addCommerceTermEntryRel(
		CommerceTermEntryRel commerceTermEntryRel) {

		commerceTermEntryRel.setNew(true);

		return commerceTermEntryRelPersistence.update(commerceTermEntryRel);
	}

	/**
	 * Creates a new commerce term entry rel with the primary key. Does not add the commerce term entry rel to the database.
	 *
	 * @param commerceTermEntryRelId the primary key for the new commerce term entry rel
	 * @return the new commerce term entry rel
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceTermEntryRel createCommerceTermEntryRel(
		long commerceTermEntryRelId) {

		return commerceTermEntryRelPersistence.create(commerceTermEntryRelId);
	}

	/**
	 * Deletes the commerce term entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTermEntryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTermEntryRelId the primary key of the commerce term entry rel
	 * @return the commerce term entry rel that was removed
	 * @throws PortalException if a commerce term entry rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceTermEntryRel deleteCommerceTermEntryRel(
			long commerceTermEntryRelId)
		throws PortalException {

		return commerceTermEntryRelPersistence.remove(commerceTermEntryRelId);
	}

	/**
	 * Deletes the commerce term entry rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTermEntryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTermEntryRel the commerce term entry rel
	 * @return the commerce term entry rel that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceTermEntryRel deleteCommerceTermEntryRel(
			CommerceTermEntryRel commerceTermEntryRel)
		throws PortalException {

		return commerceTermEntryRelPersistence.remove(commerceTermEntryRel);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceTermEntryRelPersistence.dslQuery(dslQuery);
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
			CommerceTermEntryRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceTermEntryRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.term.model.impl.CommerceTermEntryRelModelImpl</code>.
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

		return commerceTermEntryRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.term.model.impl.CommerceTermEntryRelModelImpl</code>.
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

		return commerceTermEntryRelPersistence.findWithDynamicQuery(
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
		return commerceTermEntryRelPersistence.countWithDynamicQuery(
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

		return commerceTermEntryRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceTermEntryRel fetchCommerceTermEntryRel(
		long commerceTermEntryRelId) {

		return commerceTermEntryRelPersistence.fetchByPrimaryKey(
			commerceTermEntryRelId);
	}

	/**
	 * Returns the commerce term entry rel with the primary key.
	 *
	 * @param commerceTermEntryRelId the primary key of the commerce term entry rel
	 * @return the commerce term entry rel
	 * @throws PortalException if a commerce term entry rel with the primary key could not be found
	 */
	@Override
	public CommerceTermEntryRel getCommerceTermEntryRel(
			long commerceTermEntryRelId)
		throws PortalException {

		return commerceTermEntryRelPersistence.findByPrimaryKey(
			commerceTermEntryRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceTermEntryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceTermEntryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTermEntryRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceTermEntryRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceTermEntryRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTermEntryRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceTermEntryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceTermEntryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTermEntryRelId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceTermEntryRelPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceTermEntryRelLocalService.deleteCommerceTermEntryRel(
			(CommerceTermEntryRel)persistedModel);
	}

	@Override
	public BasePersistence<CommerceTermEntryRel> getBasePersistence() {
		return commerceTermEntryRelPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceTermEntryRelPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce term entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.term.model.impl.CommerceTermEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce term entry rels
	 * @param end the upper bound of the range of commerce term entry rels (not inclusive)
	 * @return the range of commerce term entry rels
	 */
	@Override
	public List<CommerceTermEntryRel> getCommerceTermEntryRels(
		int start, int end) {

		return commerceTermEntryRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce term entry rels.
	 *
	 * @return the number of commerce term entry rels
	 */
	@Override
	public int getCommerceTermEntryRelsCount() {
		return commerceTermEntryRelPersistence.countAll();
	}

	/**
	 * Updates the commerce term entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTermEntryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTermEntryRel the commerce term entry rel
	 * @return the commerce term entry rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceTermEntryRel updateCommerceTermEntryRel(
		CommerceTermEntryRel commerceTermEntryRel) {

		return commerceTermEntryRelPersistence.update(commerceTermEntryRel);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommerceTermEntryRelLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commerceTermEntryRelLocalService =
			(CommerceTermEntryRelLocalService)aopProxy;

		_setLocalServiceUtilService(commerceTermEntryRelLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceTermEntryRelLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceTermEntryRel.class;
	}

	protected String getModelClassName() {
		return CommerceTermEntryRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceTermEntryRelPersistence.getDataSource();

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
		CommerceTermEntryRelLocalService commerceTermEntryRelLocalService) {

		try {
			Field field =
				CommerceTermEntryRelLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceTermEntryRelLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected CommerceTermEntryPersistence commerceTermEntryPersistence;

	protected CommerceTermEntryRelLocalService commerceTermEntryRelLocalService;

	@Reference
	protected CommerceTermEntryRelPersistence commerceTermEntryRelPersistence;

	@Reference
	protected CTermEntryLocalizationPersistence
		cTermEntryLocalizationPersistence;

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