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

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link AddressLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AddressLocalService
 * @generated
 */
public class AddressLocalServiceWrapper
	implements AddressLocalService, ServiceWrapper<AddressLocalService> {

	public AddressLocalServiceWrapper() {
		this(null);
	}

	public AddressLocalServiceWrapper(AddressLocalService addressLocalService) {
		_addressLocalService = addressLocalService;
	}

	/**
	 * Adds the address to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param address the address
	 * @return the address that was added
	 */
	@Override
	public com.liferay.portal.kernel.model.Address addAddress(
		com.liferay.portal.kernel.model.Address address) {

		return _addressLocalService.addAddress(address);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #addAddress(String, long, String, long, String, String,
	 String, String, String, String, String, long, long, long,
	 boolean, boolean, String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Address addAddress(
			long userId, java.lang.String className, long classPK,
			java.lang.String street1, java.lang.String street2,
			java.lang.String street3, java.lang.String city,
			java.lang.String zip, long regionId, long countryId, long typeId,
			boolean mailing, boolean primary, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.addAddress(
			userId, className, classPK, street1, street2, street3, city, zip,
			regionId, countryId, typeId, mailing, primary, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Address addAddress(
			java.lang.String externalReferenceCode, long userId,
			java.lang.String className, long classPK, java.lang.String name,
			java.lang.String description, java.lang.String street1,
			java.lang.String street2, java.lang.String street3,
			java.lang.String city, java.lang.String zip, long regionId,
			long countryId, long typeId, boolean mailing, boolean primary,
			java.lang.String phoneNumber, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.addAddress(
			externalReferenceCode, userId, className, classPK, name,
			description, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary, phoneNumber, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Address copyAddress(
			long addressId, java.lang.String className, long classPK,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.copyAddress(
			addressId, className, classPK, serviceContext);
	}

	/**
	 * Creates a new address with the primary key. Does not add the address to the database.
	 *
	 * @param addressId the primary key for the new address
	 * @return the new address
	 */
	@Override
	public com.liferay.portal.kernel.model.Address createAddress(
		long addressId) {

		return _addressLocalService.createAddress(addressId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the address from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param address the address
	 * @return the address that was removed
	 */
	@Override
	public com.liferay.portal.kernel.model.Address deleteAddress(
		com.liferay.portal.kernel.model.Address address) {

		return _addressLocalService.deleteAddress(address);
	}

	/**
	 * Deletes the address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param addressId the primary key of the address
	 * @return the address that was removed
	 * @throws PortalException if a address with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address deleteAddress(long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.deleteAddress(addressId);
	}

	@Override
	public void deleteAddresses(
		long companyId, java.lang.String className, long classPK) {

		_addressLocalService.deleteAddresses(companyId, className, classPK);
	}

	@Override
	public void deleteCountryAddresses(long countryId) {
		_addressLocalService.deleteCountryAddresses(countryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteRegionAddresses(long regionId) {
		_addressLocalService.deleteRegionAddresses(regionId);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _addressLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _addressLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _addressLocalService.dynamicQuery();
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

		return _addressLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.AddressModelImpl</code>.
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

		return _addressLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.AddressModelImpl</code>.
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

		return _addressLocalService.dynamicQuery(
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

		return _addressLocalService.dynamicQueryCount(dynamicQuery);
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

		return _addressLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.kernel.model.Address fetchAddress(
		long addressId) {

		return _addressLocalService.fetchAddress(addressId);
	}

	/**
	 * Returns the address with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the address's external reference code
	 * @return the matching address, or <code>null</code> if a matching address could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address
		fetchAddressByExternalReferenceCode(
			long companyId, java.lang.String externalReferenceCode) {

		return _addressLocalService.fetchAddressByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchAddressByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Address fetchAddressByReferenceCode(
		long companyId, java.lang.String externalReferenceCode) {

		return _addressLocalService.fetchAddressByReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the address with the matching UUID and company.
	 *
	 * @param uuid the address's UUID
	 * @param companyId the primary key of the company
	 * @return the matching address, or <code>null</code> if a matching address could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address
		fetchAddressByUuidAndCompanyId(java.lang.String uuid, long companyId) {

		return _addressLocalService.fetchAddressByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _addressLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the address with the primary key.
	 *
	 * @param addressId the primary key of the address
	 * @return the address
	 * @throws PortalException if a address with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address getAddress(long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.getAddress(addressId);
	}

	/**
	 * Returns the address with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the address's external reference code
	 * @return the matching address
	 * @throws PortalException if a matching address could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address
			getAddressByExternalReferenceCode(
				long companyId, java.lang.String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.getAddressByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the address with the matching UUID and company.
	 *
	 * @param uuid the address's UUID
	 * @param companyId the primary key of the company
	 * @return the matching address
	 * @throws PortalException if a matching address could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Address getAddressByUuidAndCompanyId(
			java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.getAddressByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address>
		getAddresses() {

		return _addressLocalService.getAddresses();
	}

	/**
	 * Returns a range of all the addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.AddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of addresses
	 * @param end the upper bound of the range of addresses (not inclusive)
	 * @return the range of addresses
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address> getAddresses(
		int start, int end) {

		return _addressLocalService.getAddresses(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address> getAddresses(
		long companyId, java.lang.String className, long classPK) {

		return _addressLocalService.getAddresses(companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address> getAddresses(
		long companyId, java.lang.String className, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Address> orderByComparator) {

		return _addressLocalService.getAddresses(
			companyId, className, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns the number of addresses.
	 *
	 * @return the number of addresses
	 */
	@Override
	public int getAddressesCount() {
		return _addressLocalService.getAddressesCount();
	}

	@Override
	public int getAddressesCount(
		long companyId, java.lang.String className, long classPK) {

		return _addressLocalService.getAddressesCount(
			companyId, className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _addressLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _addressLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _addressLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address>
		getTypeAddresses(
			long companyId, java.lang.String className, long classPK,
			long[] typeIds) {

		return _addressLocalService.getTypeAddresses(
			companyId, className, classPK, typeIds);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address>
		getTypeAddresses(
			long companyId, java.lang.String className, long classPK,
			long[] typeIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Address> orderByComparator) {

		return _addressLocalService.getTypeAddresses(
			companyId, className, classPK, typeIds, start, end,
			orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.portal.kernel.model.Address> searchAddresses(
				long companyId, java.lang.String className, long classPK,
				java.lang.String keywords,
				java.util.LinkedHashMap<java.lang.String, java.lang.Object>
					params,
				int start, int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.searchAddresses(
			companyId, className, classPK, keywords, params, start, end, sort);
	}

	/**
	 * Updates the address in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param address the address
	 * @return the address that was updated
	 */
	@Override
	public com.liferay.portal.kernel.model.Address updateAddress(
		com.liferay.portal.kernel.model.Address address) {

		return _addressLocalService.updateAddress(address);
	}

	@Override
	public com.liferay.portal.kernel.model.Address updateAddress(
			long addressId, java.lang.String street1, java.lang.String street2,
			java.lang.String street3, java.lang.String city,
			java.lang.String zip, long regionId, long countryId, long typeId,
			boolean mailing, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.updateAddress(
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary);
	}

	@Override
	public com.liferay.portal.kernel.model.Address updateAddress(
			long addressId, java.lang.String name, java.lang.String description,
			java.lang.String street1, java.lang.String street2,
			java.lang.String street3, java.lang.String city,
			java.lang.String zip, long regionId, long countryId, long typeId,
			boolean mailing, boolean primary, java.lang.String phoneNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressLocalService.updateAddress(
			addressId, name, description, street1, street2, street3, city, zip,
			regionId, countryId, typeId, mailing, primary, phoneNumber);
	}

	@Override
	public AddressLocalService getWrappedService() {
		return _addressLocalService;
	}

	@Override
	public void setWrappedService(AddressLocalService addressLocalService) {
		_addressLocalService = addressLocalService;
	}

	private AddressLocalService _addressLocalService;

}