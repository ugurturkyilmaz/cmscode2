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

package com.liferay.asset.list.service;

import com.liferay.asset.list.model.AssetListEntryUsage;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for AssetListEntryUsage. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryUsageLocalServiceUtil
 * @generated
 */
@CTAware
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AssetListEntryUsageLocalService
	extends BaseLocalService, CTService<AssetListEntryUsage>,
			PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.asset.list.service.impl.AssetListEntryUsageLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the asset list entry usage local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AssetListEntryUsageLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the asset list entry usage to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetListEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetListEntryUsage the asset list entry usage
	 * @return the asset list entry usage that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AssetListEntryUsage addAssetListEntryUsage(
		AssetListEntryUsage assetListEntryUsage);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #addAssetListEntryUsage(long, long, long, long, long, String,
	 ServiceContext)}
	 */
	@Deprecated
	public AssetListEntryUsage addAssetListEntryUsage(
			long userId, long groupId, long assetListEntryId, long classNameId,
			long classPK, String portletId, ServiceContext serviceContext)
		throws PortalException;

	public AssetListEntryUsage addAssetListEntryUsage(
			long userId, long groupId, long classNameId, String containerKey,
			long containerType, String key, long plid,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new asset list entry usage with the primary key. Does not add the asset list entry usage to the database.
	 *
	 * @param assetListEntryUsageId the primary key for the new asset list entry usage
	 * @return the new asset list entry usage
	 */
	@Transactional(enabled = false)
	public AssetListEntryUsage createAssetListEntryUsage(
		long assetListEntryUsageId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the asset list entry usage from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetListEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetListEntryUsage the asset list entry usage
	 * @return the asset list entry usage that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public AssetListEntryUsage deleteAssetListEntryUsage(
		AssetListEntryUsage assetListEntryUsage);

	/**
	 * Deletes the asset list entry usage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetListEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetListEntryUsageId the primary key of the asset list entry usage
	 * @return the asset list entry usage that was removed
	 * @throws PortalException if a asset list entry usage with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public AssetListEntryUsage deleteAssetListEntryUsage(
			long assetListEntryUsageId)
		throws PortalException;

	public void deleteAssetListEntryUsages(
		String containerKey, long containerType, long plid);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntryUsageModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntryUsageModelImpl</code>.
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
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long assetListEntryUsageId);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsages(String, long, long)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long classNameId, long classPK, String portletId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long groupId, long classNameId, String containerKey, long containerType,
		String key, long plid);

	/**
	 * Returns the asset list entry usage matching the UUID and group.
	 *
	 * @param uuid the asset list entry usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset list entry usage, or <code>null</code> if a matching asset list entry usage could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetListEntryUsage fetchAssetListEntryUsageByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetEntryListUsagesByPlid(long plid);

	/**
	 * Returns the asset list entry usage with the primary key.
	 *
	 * @param assetListEntryUsageId the primary key of the asset list entry usage
	 * @return the asset list entry usage
	 * @throws PortalException if a asset list entry usage with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetListEntryUsage getAssetListEntryUsage(
			long assetListEntryUsageId)
		throws PortalException;

	/**
	 * Returns the asset list entry usage matching the UUID and group.
	 *
	 * @param uuid the asset list entry usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset list entry usage
	 * @throws PortalException if a matching asset list entry usage could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetListEntryUsage getAssetListEntryUsageByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the asset list entry usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntryUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @return the range of asset list entry usages
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		int start, int end);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsages(long, long, String)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsages(long, long, String, int, int,
	 OrderByComparator)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsages(long, long, String, int)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsages(long, long, String, int, int, int,
	 OrderByComparator)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int type, int start,
		int end, OrderByComparator<AssetListEntryUsage> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long groupId, long classNameId, String key, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		String containerKey, long containerType, long plid);

	/**
	 * Returns all the asset list entry usages matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset list entry usages
	 * @param companyId the primary key of the company
	 * @return the matching asset list entry usages, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsagesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of asset list entry usages matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset list entry usages
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of asset list entry usages
	 * @param end the upper bound of the range of asset list entry usages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching asset list entry usages, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetListEntryUsage> getAssetListEntryUsagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator);

	/**
	 * Returns the number of asset list entry usages.
	 *
	 * @return the number of asset list entry usages
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetListEntryUsagesCount();

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsagesCount(long, long, String)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetListEntryUsagesCount(long assetListEntryId);

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #getAssetListEntryUsagesCount(long, long, String, int)}
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetListEntryUsagesCount(
		long assetListEntryId, long classNameId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetListEntryUsagesCount(
		long groupId, long classNameId, String key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetListEntryUsagesCount(
		long groupId, long classNameId, String key, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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

	/**
	 * Updates the asset list entry usage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetListEntryUsageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetListEntryUsage the asset list entry usage
	 * @return the asset list entry usage that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AssetListEntryUsage updateAssetListEntryUsage(
		AssetListEntryUsage assetListEntryUsage);

	@Override
	@Transactional(enabled = false)
	public CTPersistence<AssetListEntryUsage> getCTPersistence();

	@Override
	@Transactional(enabled = false)
	public Class<AssetListEntryUsage> getModelClass();

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<AssetListEntryUsage>, R, E>
				updateUnsafeFunction)
		throws E;

}