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

package com.liferay.dynamic.data.mapping.service;

import com.liferay.dynamic.data.mapping.model.DDMTemplateLink;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

/**
 * Provides a wrapper for {@link DDMTemplateLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateLinkLocalService
 * @generated
 */
public class DDMTemplateLinkLocalServiceWrapper
	implements DDMTemplateLinkLocalService,
			   ServiceWrapper<DDMTemplateLinkLocalService> {

	public DDMTemplateLinkLocalServiceWrapper() {
		this(null);
	}

	public DDMTemplateLinkLocalServiceWrapper(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	/**
	 * Adds the ddm template link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMTemplateLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmTemplateLink the ddm template link
	 * @return the ddm template link that was added
	 */
	@Override
	public DDMTemplateLink addDDMTemplateLink(DDMTemplateLink ddmTemplateLink) {
		return _ddmTemplateLinkLocalService.addDDMTemplateLink(ddmTemplateLink);
	}

	@Override
	public DDMTemplateLink addTemplateLink(
		long classNameId, long classPK, long templateId) {

		return _ddmTemplateLinkLocalService.addTemplateLink(
			classNameId, classPK, templateId);
	}

	/**
	 * Creates a new ddm template link with the primary key. Does not add the ddm template link to the database.
	 *
	 * @param templateLinkId the primary key for the new ddm template link
	 * @return the new ddm template link
	 */
	@Override
	public DDMTemplateLink createDDMTemplateLink(long templateLinkId) {
		return _ddmTemplateLinkLocalService.createDDMTemplateLink(
			templateLinkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the ddm template link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMTemplateLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmTemplateLink the ddm template link
	 * @return the ddm template link that was removed
	 */
	@Override
	public DDMTemplateLink deleteDDMTemplateLink(
		DDMTemplateLink ddmTemplateLink) {

		return _ddmTemplateLinkLocalService.deleteDDMTemplateLink(
			ddmTemplateLink);
	}

	/**
	 * Deletes the ddm template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMTemplateLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param templateLinkId the primary key of the ddm template link
	 * @return the ddm template link that was removed
	 * @throws PortalException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink deleteDDMTemplateLink(long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.deleteDDMTemplateLink(
			templateLinkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public DDMTemplateLink deleteTemplateLink(DDMTemplateLink templateLink) {
		return _ddmTemplateLinkLocalService.deleteTemplateLink(templateLink);
	}

	@Override
	public DDMTemplateLink deleteTemplateLink(long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.deleteTemplateLink(templateLinkId);
	}

	@Override
	public DDMTemplateLink deleteTemplateLink(long classNameId, long classPK) {
		return _ddmTemplateLinkLocalService.deleteTemplateLink(
			classNameId, classPK);
	}

	@Override
	public void deleteTemplateLinks(long templateId) {
		_ddmTemplateLinkLocalService.deleteTemplateLinks(templateId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _ddmTemplateLinkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _ddmTemplateLinkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ddmTemplateLinkLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _ddmTemplateLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _ddmTemplateLinkLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _ddmTemplateLinkLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _ddmTemplateLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _ddmTemplateLinkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public DDMTemplateLink fetchDDMTemplateLink(long templateLinkId) {
		return _ddmTemplateLinkLocalService.fetchDDMTemplateLink(
			templateLinkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _ddmTemplateLinkLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ddm template link with the primary key.
	 *
	 * @param templateLinkId the primary key of the ddm template link
	 * @return the ddm template link
	 * @throws PortalException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink getDDMTemplateLink(long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.getDDMTemplateLink(templateLinkId);
	}

	/**
	 * Returns a range of all the ddm template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @return the range of ddm template links
	 */
	@Override
	public java.util.List<DDMTemplateLink> getDDMTemplateLinks(
		int start, int end) {

		return _ddmTemplateLinkLocalService.getDDMTemplateLinks(start, end);
	}

	/**
	 * Returns the number of ddm template links.
	 *
	 * @return the number of ddm template links
	 */
	@Override
	public int getDDMTemplateLinksCount() {
		return _ddmTemplateLinkLocalService.getDDMTemplateLinksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _ddmTemplateLinkLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmTemplateLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public DDMTemplateLink getTemplateLink(long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.getTemplateLink(templateLinkId);
	}

	@Override
	public DDMTemplateLink getTemplateLink(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.getTemplateLink(
			classNameId, classPK);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public java.util.List<DDMTemplateLink> getTemplateLinks(long classNameId) {
		return _ddmTemplateLinkLocalService.getTemplateLinks(classNameId);
	}

	@Override
	public java.util.List<DDMTemplateLink> getTemplateLinksByTemplateId(
		long templateId) {

		return _ddmTemplateLinkLocalService.getTemplateLinksByTemplateId(
			templateId);
	}

	/**
	 * Updates the ddm template link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMTemplateLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmTemplateLink the ddm template link
	 * @return the ddm template link that was updated
	 */
	@Override
	public DDMTemplateLink updateDDMTemplateLink(
		DDMTemplateLink ddmTemplateLink) {

		return _ddmTemplateLinkLocalService.updateDDMTemplateLink(
			ddmTemplateLink);
	}

	@Override
	public DDMTemplateLink updateTemplateLink(
			long templateLinkId, long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmTemplateLinkLocalService.updateTemplateLink(
			templateLinkId, templateId);
	}

	@Override
	public DDMTemplateLink updateTemplateLink(
		long classNameId, long classPK, long templateId) {

		return _ddmTemplateLinkLocalService.updateTemplateLink(
			classNameId, classPK, templateId);
	}

	@Override
	public CTPersistence<DDMTemplateLink> getCTPersistence() {
		return _ddmTemplateLinkLocalService.getCTPersistence();
	}

	@Override
	public Class<DDMTemplateLink> getModelClass() {
		return _ddmTemplateLinkLocalService.getModelClass();
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DDMTemplateLink>, R, E>
				updateUnsafeFunction)
		throws E {

		return _ddmTemplateLinkLocalService.updateWithUnsafeFunction(
			updateUnsafeFunction);
	}

	@Override
	public DDMTemplateLinkLocalService getWrappedService() {
		return _ddmTemplateLinkLocalService;
	}

	@Override
	public void setWrappedService(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}