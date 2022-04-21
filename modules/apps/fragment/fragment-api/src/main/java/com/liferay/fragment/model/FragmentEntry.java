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

package com.liferay.fragment.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the FragmentEntry service. Represents a row in the &quot;FragmentEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.fragment.model.impl.FragmentEntryImpl")
@ProviderType
public interface FragmentEntry extends FragmentEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.fragment.model.impl.FragmentEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FragmentEntry, Long>
		FRAGMENT_ENTRY_ID_ACCESSOR = new Accessor<FragmentEntry, Long>() {

			@Override
			public Long get(FragmentEntry fragmentEntry) {
				return fragmentEntry.getFragmentEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FragmentEntry> getTypeClass() {
				return FragmentEntry.class;
			}

		};

	public FragmentEntry fetchDraftFragmentEntry();

	public String getContent();

	public int getGlobalUsageCount();

	public String getImagePreviewURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public String getTypeLabel();

	public int getUsageCount();

	public void populateZipWriter(
			com.liferay.portal.kernel.zip.ZipWriter zipWriter, String path)
		throws Exception;

	public void setImagePreviewURL(String imagePreviewURL);

}