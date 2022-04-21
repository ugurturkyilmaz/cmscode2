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

package com.liferay.commerce.term.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CTermEntryLocalization service. Represents a row in the &quot;CTermEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CTermEntryLocalizationModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.term.model.impl.CTermEntryLocalizationImpl"
)
@ProviderType
public interface CTermEntryLocalization extends CTermEntryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.term.model.impl.CTermEntryLocalizationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CTermEntryLocalization, Long>
		C_TERM_ENTRY_LOCALIZATION_ID_ACCESSOR =
			new Accessor<CTermEntryLocalization, Long>() {

				@Override
				public Long get(CTermEntryLocalization cTermEntryLocalization) {
					return cTermEntryLocalization.getCTermEntryLocalizationId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CTermEntryLocalization> getTypeClass() {
					return CTermEntryLocalization.class;
				}

			};

}