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

package com.liferay.trash.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the TrashVersion service. Represents a row in the &quot;TrashVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersionModel
 * @generated
 */
@ImplementationClassName("com.liferay.trash.model.impl.TrashVersionImpl")
@ProviderType
public interface TrashVersion extends PersistedModel, TrashVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.trash.model.impl.TrashVersionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TrashVersion, Long> VERSION_ID_ACCESSOR =
		new Accessor<TrashVersion, Long>() {

			@Override
			public Long get(TrashVersion trashVersion) {
				return trashVersion.getVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TrashVersion> getTypeClass() {
				return TrashVersion.class;
			}

		};

	public com.liferay.portal.kernel.util.UnicodeProperties
		getTypeSettingsProperties();

	public String getTypeSettingsProperty(String key);

	public String getTypeSettingsProperty(String key, String defaultValue);

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			typeSettingsUnicodeProperties);

}