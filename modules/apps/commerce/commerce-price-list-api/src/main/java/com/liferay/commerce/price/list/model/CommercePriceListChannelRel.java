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

package com.liferay.commerce.price.list.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CommercePriceListChannelRel service. Represents a row in the &quot;CommercePriceListChannelRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListChannelRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.price.list.model.impl.CommercePriceListChannelRelImpl"
)
@ProviderType
public interface CommercePriceListChannelRel
	extends CommercePriceListChannelRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.price.list.model.impl.CommercePriceListChannelRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommercePriceListChannelRel, Long>
		COMMERCE_PRICE_LIST_CHANNEL_REL_ID_ACCESSOR =
			new Accessor<CommercePriceListChannelRel, Long>() {

				@Override
				public Long get(
					CommercePriceListChannelRel commercePriceListChannelRel) {

					return commercePriceListChannelRel.
						getCommercePriceListChannelRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommercePriceListChannelRel> getTypeClass() {
					return CommercePriceListChannelRel.class;
				}

			};

	public com.liferay.commerce.product.model.CommerceChannel
			getCommerceChannel()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommercePriceList getCommercePriceList()
		throws com.liferay.portal.kernel.exception.PortalException;

}