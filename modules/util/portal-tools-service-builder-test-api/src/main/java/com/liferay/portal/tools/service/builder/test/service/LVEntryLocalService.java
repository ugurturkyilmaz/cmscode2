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

package com.liferay.portal.tools.service.builder.test.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.version.VersionService;
import com.liferay.portal.kernel.service.version.VersionServiceListener;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.LVEntry;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationVersion;
import com.liferay.portal.tools.service.builder.test.model.LVEntryVersion;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for LVEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryLocalServiceUtil
 * @generated
 */
@OSGiBeanProperties(
	property = {
		"model.class.name=com.liferay.portal.tools.service.builder.test.model.LVEntry",
		"version.model.class.name=com.liferay.portal.tools.service.builder.test.model.LVEntryVersion"
	}
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LVEntryLocalService
	extends BaseLocalService, PersistedModelLocalService,
			VersionService<LVEntry, LVEntryVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.portal.tools.service.builder.test.service.impl.LVEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the lv entry local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LVEntryLocalServiceUtil} if injection and service tracking are not available.
	 */
	public void addBigDecimalEntryLVEntries(
		long bigDecimalEntryId, List<LVEntry> lvEntries);

	public void addBigDecimalEntryLVEntries(
		long bigDecimalEntryId, long[] lvEntryIds);

	public void addBigDecimalEntryLVEntry(
		long bigDecimalEntryId, long lvEntryId);

	public void addBigDecimalEntryLVEntry(
		long bigDecimalEntryId, LVEntry lvEntry);

	/**
	 * Adds the lv entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LVEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lvEntry the lv entry
	 * @return the lv entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LVEntry addLVEntry(LVEntry lvEntry);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LVEntry checkout(LVEntry publishedLVEntry, int version)
		throws PortalException;

	public void clearBigDecimalEntryLVEntries(long bigDecimalEntryId);

	/**
	 * Creates a new lv entry. Does not add the lv entry to the database.
	 *
	 * @return the new lv entry
	 */
	@Override
	@Transactional(enabled = false)
	public LVEntry create();

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	@Override
	public LVEntry delete(LVEntry publishedLVEntry) throws PortalException;

	public void deleteBigDecimalEntryLVEntries(
		long bigDecimalEntryId, List<LVEntry> lvEntries);

	public void deleteBigDecimalEntryLVEntries(
		long bigDecimalEntryId, long[] lvEntryIds);

	public void deleteBigDecimalEntryLVEntry(
		long bigDecimalEntryId, long lvEntryId);

	public void deleteBigDecimalEntryLVEntry(
		long bigDecimalEntryId, LVEntry lvEntry);

	@Indexable(type = IndexableType.DELETE)
	@Override
	public LVEntry deleteDraft(LVEntry draftLVEntry) throws PortalException;

	/**
	 * Deletes the lv entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LVEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lvEntryId the primary key of the lv entry
	 * @return the lv entry that was removed
	 * @throws PortalException if a lv entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public LVEntry deleteLVEntry(long lvEntryId) throws PortalException;

	/**
	 * Deletes the lv entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LVEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lvEntry the lv entry
	 * @return the lv entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public LVEntry deleteLVEntry(LVEntry lvEntry);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	public LVEntryVersion deleteVersion(LVEntryVersion lvEntryVersion)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryModelImpl</code>.
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

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry fetchDraft(long primaryKey);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry fetchDraft(LVEntry lvEntry);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntryVersion fetchLatestVersion(LVEntry lvEntry);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry fetchLVEntry(long lvEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntryLocalization fetchLVEntryLocalization(
		long lvEntryId, String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntryLocalizationVersion fetchLVEntryLocalizationVersion(
		long lvEntryId, String languageId, int version);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry fetchPublished(long primaryKey);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry fetchPublished(LVEntry lvEntry);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntry> getBigDecimalEntryLVEntries(long bigDecimalEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntry> getBigDecimalEntryLVEntries(
		long bigDecimalEntryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntry> getBigDecimalEntryLVEntries(
		long bigDecimalEntryId, int start, int end,
		OrderByComparator<LVEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBigDecimalEntryLVEntriesCount(long bigDecimalEntryId);

	/**
	 * Returns the bigDecimalEntryIds of the big decimal entries associated with the lv entry.
	 *
	 * @param lvEntryId the lvEntryId of the lv entry
	 * @return long[] the bigDecimalEntryIds of big decimal entries associated with the lv entry
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getBigDecimalEntryPrimaryKeys(long lvEntryId);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry getDraft(long primaryKey) throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry getDraft(LVEntry lvEntry) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns a range of all the lv entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lv entries
	 * @param end the upper bound of the range of lv entries (not inclusive)
	 * @return the range of lv entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntry> getLVEntries(int start, int end);

	/**
	 * Returns the number of lv entries.
	 *
	 * @return the number of lv entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLVEntriesCount();

	/**
	 * Returns the lv entry with the primary key.
	 *
	 * @param lvEntryId the primary key of the lv entry
	 * @return the lv entry
	 * @throws PortalException if a lv entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntry getLVEntry(long lvEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntryLocalization getLVEntryLocalization(
			long lvEntryId, String languageId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntryLocalization> getLVEntryLocalizations(long lvEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntryLocalizationVersion> getLVEntryLocalizationVersions(
		long lvEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntryLocalizationVersion> getLVEntryLocalizationVersions(
			long lvEntryId, String languageId)
		throws PortalException;

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

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LVEntryVersion getVersion(LVEntry lvEntry, int version)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LVEntryVersion> getVersions(LVEntry lvEntry);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasBigDecimalEntryLVEntries(long bigDecimalEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasBigDecimalEntryLVEntry(
		long bigDecimalEntryId, long lvEntryId);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LVEntry publishDraft(LVEntry draftLVEntry) throws PortalException;

	@Override
	public void registerListener(
		VersionServiceListener<LVEntry, LVEntryVersion> versionServiceListener);

	public void setBigDecimalEntryLVEntries(
		long bigDecimalEntryId, long[] lvEntryIds);

	@Override
	public void unregisterListener(
		VersionServiceListener<LVEntry, LVEntryVersion> versionServiceListener);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LVEntry updateDraft(LVEntry draftLVEntry) throws PortalException;

	/**
	 * Updates the lv entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LVEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lvEntry the lv entry
	 * @return the lv entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LVEntry updateLVEntry(LVEntry draftLVEntry) throws PortalException;

	public LVEntryLocalization updateLVEntryLocalization(
			LVEntry draftLVEntry, String languageId, String title,
			String content)
		throws PortalException;

	public List<LVEntryLocalization> updateLVEntryLocalizations(
			LVEntry draftLVEntry, Map<String, String> titleMap,
			Map<String, String> contentMap)
		throws PortalException;

}