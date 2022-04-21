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

package com.liferay.exportimport.kernel.service;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for ExportImportConfiguration. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportConfigurationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ExportImportConfigurationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.portlet.exportimport.service.impl.ExportImportConfigurationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the export import configuration local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ExportImportConfigurationLocalServiceUtil} if injection and service tracking are not available.
	 */
	public ExportImportConfiguration addDraftExportImportConfiguration(
			long userId, int type, Map<String, Serializable> settingsMap)
		throws PortalException;

	public ExportImportConfiguration addDraftExportImportConfiguration(
			long userId, String name, int type,
			Map<String, Serializable> settingsMap)
		throws PortalException;

	/**
	 * Adds the export import configuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExportImportConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exportImportConfiguration the export import configuration
	 * @return the export import configuration that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration addExportImportConfiguration(
		ExportImportConfiguration exportImportConfiguration);

	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration addExportImportConfiguration(
			long userId, long groupId, String name, String description,
			int type, Map<String, Serializable> settingsMap, int status,
			ServiceContext serviceContext)
		throws PortalException;

	public ExportImportConfiguration addExportImportConfiguration(
			long userId, long groupId, String name, String description,
			int type, Map<String, Serializable> settingsMap,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new export import configuration with the primary key. Does not add the export import configuration to the database.
	 *
	 * @param exportImportConfigurationId the primary key for the new export import configuration
	 * @return the new export import configuration
	 */
	@Transactional(enabled = false)
	public ExportImportConfiguration createExportImportConfiguration(
		long exportImportConfigurationId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the export import configuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExportImportConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exportImportConfiguration the export import configuration
	 * @return the export import configuration that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public ExportImportConfiguration deleteExportImportConfiguration(
		ExportImportConfiguration exportImportConfiguration);

	/**
	 * Deletes the export import configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExportImportConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exportImportConfigurationId the primary key of the export import configuration
	 * @return the export import configuration that was removed
	 * @throws PortalException if a export import configuration with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public ExportImportConfiguration deleteExportImportConfiguration(
			long exportImportConfigurationId)
		throws PortalException;

	public void deleteExportImportConfigurations(long groupId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.exportimport.model.impl.ExportImportConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.exportimport.model.impl.ExportImportConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportImportConfiguration fetchExportImportConfiguration(
		long exportImportConfigurationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the export import configuration with the primary key.
	 *
	 * @param exportImportConfigurationId the primary key of the export import configuration
	 * @return the export import configuration
	 * @throws PortalException if a export import configuration with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportImportConfiguration getExportImportConfiguration(
			long exportImportConfigurationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
			Hits hits)
		throws PortalException;

	/**
	 * Returns a range of all the export import configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.exportimport.model.impl.ExportImportConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of export import configurations
	 * @param end the upper bound of the range of export import configurations (not inclusive)
	 * @return the range of export import configurations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
		long groupId, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
		long groupId, int type, int start, int end,
		OrderByComparator<ExportImportConfiguration> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
		long companyId, long groupId, String keywords, int type, int start,
		int end,
		OrderByComparator<ExportImportConfiguration> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ExportImportConfiguration> getExportImportConfigurations(
		long companyId, long groupId, String name, String description, int type,
		boolean andSearch, int start, int end,
		OrderByComparator<ExportImportConfiguration> orderByComparator);

	/**
	 * Returns the number of export import configurations.
	 *
	 * @return the number of export import configurations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getExportImportConfigurationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getExportImportConfigurationsCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getExportImportConfigurationsCount(long groupId, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getExportImportConfigurationsCount(
		long companyId, long groupId, String keywords, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getExportImportConfigurationsCount(
		long companyId, long groupId, String name, String description, int type,
		boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration moveExportImportConfigurationToTrash(
			long userId, long exportImportConfigurationId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration restoreExportImportConfigurationFromTrash(
			long userId, long exportImportConfigurationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<ExportImportConfiguration>
			searchExportImportConfigurations(
				long companyId, long groupId, int type, String keywords,
				int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<ExportImportConfiguration>
			searchExportImportConfigurations(
				long companyId, long groupId, int type, String name,
				String description, boolean andSearch, int start, int end,
				Sort sort)
		throws PortalException;

	/**
	 * Updates the export import configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExportImportConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exportImportConfiguration the export import configuration
	 * @return the export import configuration that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration updateExportImportConfiguration(
		ExportImportConfiguration exportImportConfiguration);

	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration updateExportImportConfiguration(
			long userId, long exportImportConfigurationId, String name,
			String description, Map<String, Serializable> settingsMap,
			ServiceContext serviceContext)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ExportImportConfiguration updateStatus(
			long userId, long exportImportConfigurationId, int status)
		throws PortalException;

}