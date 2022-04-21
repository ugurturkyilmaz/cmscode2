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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Address. This utility wraps
 * <code>com.liferay.portal.service.impl.AddressLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AddressLocalService
 * @generated
 */
public class AddressLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.AddressLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static Address addAddress(Address address) {
		return getService().addAddress(address);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #addAddress(String, long, String, long, String, String,
	 String, String, String, String, String, long, long, long,
	 boolean, boolean, String, ServiceContext)}
	 */
	@Deprecated
	public static Address addAddress(
			long userId, String className, long classPK, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, long typeId, boolean mailing,
			boolean primary, ServiceContext serviceContext)
		throws PortalException {

		return getService().addAddress(
			userId, className, classPK, street1, street2, street3, city, zip,
			regionId, countryId, typeId, mailing, primary, serviceContext);
	}

	public static Address addAddress(
			String externalReferenceCode, long userId, String className,
			long classPK, String name, String description, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, long typeId, boolean mailing,
			boolean primary, String phoneNumber, ServiceContext serviceContext)
		throws PortalException {

		return getService().addAddress(
			externalReferenceCode, userId, className, classPK, name,
			description, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary, phoneNumber, serviceContext);
	}

	public static Address copyAddress(
			long addressId, String className, long classPK,
			ServiceContext serviceContext)
		throws PortalException {

		return getService().copyAddress(
			addressId, className, classPK, serviceContext);
	}

	/**
	 * Creates a new address with the primary key. Does not add the address to the database.
	 *
	 * @param addressId the primary key for the new address
	 * @return the new address
	 */
	public static Address createAddress(long addressId) {
		return getService().createAddress(addressId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static Address deleteAddress(Address address) {
		return getService().deleteAddress(address);
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
	public static Address deleteAddress(long addressId) throws PortalException {
		return getService().deleteAddress(addressId);
	}

	public static void deleteAddresses(
		long companyId, String className, long classPK) {

		getService().deleteAddresses(companyId, className, classPK);
	}

	public static void deleteCountryAddresses(long countryId) {
		getService().deleteCountryAddresses(countryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteRegionAddresses(long regionId) {
		getService().deleteRegionAddresses(regionId);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Address fetchAddress(long addressId) {
		return getService().fetchAddress(addressId);
	}

	/**
	 * Returns the address with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the address's external reference code
	 * @return the matching address, or <code>null</code> if a matching address could not be found
	 */
	public static Address fetchAddressByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return getService().fetchAddressByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchAddressByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	public static Address fetchAddressByReferenceCode(
		long companyId, String externalReferenceCode) {

		return getService().fetchAddressByReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the address with the matching UUID and company.
	 *
	 * @param uuid the address's UUID
	 * @param companyId the primary key of the company
	 * @return the matching address, or <code>null</code> if a matching address could not be found
	 */
	public static Address fetchAddressByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchAddressByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the address with the primary key.
	 *
	 * @param addressId the primary key of the address
	 * @return the address
	 * @throws PortalException if a address with the primary key could not be found
	 */
	public static Address getAddress(long addressId) throws PortalException {
		return getService().getAddress(addressId);
	}

	/**
	 * Returns the address with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the address's external reference code
	 * @return the matching address
	 * @throws PortalException if a matching address could not be found
	 */
	public static Address getAddressByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().getAddressByExternalReferenceCode(
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
	public static Address getAddressByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getAddressByUuidAndCompanyId(uuid, companyId);
	}

	public static List<Address> getAddresses() {
		return getService().getAddresses();
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
	public static List<Address> getAddresses(int start, int end) {
		return getService().getAddresses(start, end);
	}

	public static List<Address> getAddresses(
		long companyId, String className, long classPK) {

		return getService().getAddresses(companyId, className, classPK);
	}

	public static List<Address> getAddresses(
		long companyId, String className, long classPK, int start, int end,
		OrderByComparator<Address> orderByComparator) {

		return getService().getAddresses(
			companyId, className, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns the number of addresses.
	 *
	 * @return the number of addresses
	 */
	public static int getAddressesCount() {
		return getService().getAddressesCount();
	}

	public static int getAddressesCount(
		long companyId, String className, long classPK) {

		return getService().getAddressesCount(companyId, className, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<Address> getTypeAddresses(
		long companyId, String className, long classPK, long[] typeIds) {

		return getService().getTypeAddresses(
			companyId, className, classPK, typeIds);
	}

	public static List<Address> getTypeAddresses(
		long companyId, String className, long classPK, long[] typeIds,
		int start, int end, OrderByComparator<Address> orderByComparator) {

		return getService().getTypeAddresses(
			companyId, className, classPK, typeIds, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<Address> searchAddresses(
				long companyId, String className, long classPK, String keywords,
				java.util.LinkedHashMap<String, Object> params, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchAddresses(
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
	public static Address updateAddress(Address address) {
		return getService().updateAddress(address);
	}

	public static Address updateAddress(
			long addressId, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId, long typeId,
			boolean mailing, boolean primary)
		throws PortalException {

		return getService().updateAddress(
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary);
	}

	public static Address updateAddress(
			long addressId, String name, String description, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, long typeId, boolean mailing,
			boolean primary, String phoneNumber)
		throws PortalException {

		return getService().updateAddress(
			addressId, name, description, street1, street2, street3, city, zip,
			regionId, countryId, typeId, mailing, primary, phoneNumber);
	}

	public static AddressLocalService getService() {
		return _service;
	}

	private static volatile AddressLocalService _service;

}