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

package com.liferay.users.admin.web.internal.manager;

import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.AddressService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;

/**
 * @author Samuel Trong Tran
 */
public class AddressContactInfoManager extends BaseContactInfoManager<Address> {

	public AddressContactInfoManager(
		String className, long classPK, AddressLocalService addressLocalService,
		AddressService addressService) {

		_className = className;
		_classPK = classPK;
		_addressLocalService = addressLocalService;
		_addressService = addressService;
	}

	@Override
	protected Address construct(ActionRequest actionRequest) throws Exception {
		String street1 = ParamUtil.getString(actionRequest, "addressStreet1");
		String street2 = ParamUtil.getString(actionRequest, "addressStreet2");
		String street3 = ParamUtil.getString(actionRequest, "addressStreet3");
		String city = ParamUtil.getString(actionRequest, "addressCity");
		String zip = ParamUtil.getString(actionRequest, "addressZip");
		long countryId = ParamUtil.getLong(actionRequest, "addressCountryId");

		if (Validator.isNull(street1) && Validator.isNull(street2) &&
			Validator.isNull(street3) && Validator.isNull(city) &&
			Validator.isNull(zip) && (countryId == 0)) {

			return null;
		}

		long addressId = ParamUtil.getLong(actionRequest, "primaryKey");

		Address address = _addressLocalService.createAddress(addressId);

		address.setCountryId(countryId);
		address.setRegionId(
			ParamUtil.getLong(actionRequest, "addressRegionId"));
		address.setTypeId(ParamUtil.getLong(actionRequest, "addressTypeId"));
		address.setCity(city);
		address.setMailing(
			ParamUtil.getBoolean(actionRequest, "addressMailing"));
		address.setPrimary(
			ParamUtil.getBoolean(actionRequest, "addressPrimary"));
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setStreet3(street3);
		address.setZip(zip);

		return address;
	}

	@Override
	protected Address doAdd(Address address) throws Exception {
		return _addressService.addAddress(
			_className, _classPK, address.getStreet1(), address.getStreet2(),
			address.getStreet3(), address.getCity(), address.getZip(),
			address.getRegionId(), address.getCountryId(), address.getTypeId(),
			address.isMailing(), address.isPrimary(), new ServiceContext());
	}

	@Override
	protected void doDelete(long addressId) throws Exception {
		_addressService.deleteAddress(addressId);
	}

	@Override
	protected void doUpdate(Address address) throws Exception {
		_addressService.updateAddress(
			address.getAddressId(), address.getStreet1(), address.getStreet2(),
			address.getStreet3(), address.getCity(), address.getZip(),
			address.getRegionId(), address.getCountryId(), address.getTypeId(),
			address.isMailing(), address.isPrimary());
	}

	@Override
	protected Address get(long addressId) throws Exception {
		return _addressService.getAddress(addressId);
	}

	@Override
	protected List<Address> getAll() throws Exception {
		return _addressService.getAddresses(_className, _classPK);
	}

	@Override
	protected long getPrimaryKey(Address address) {
		return address.getAddressId();
	}

	@Override
	protected boolean isPrimary(Address address) {
		return address.isPrimary();
	}

	@Override
	protected void setPrimary(Address address, boolean primary) {
		address.setPrimary(primary);
	}

	private final AddressLocalService _addressLocalService;
	private final AddressService _addressService;
	private final String _className;
	private final long _classPK;

}