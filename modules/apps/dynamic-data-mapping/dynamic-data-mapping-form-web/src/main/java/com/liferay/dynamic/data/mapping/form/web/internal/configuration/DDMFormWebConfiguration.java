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

package com.liferay.dynamic.data.mapping.form.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Lino Alves
 */
@ExtendedObjectClassDefinition(
	category = "forms", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.dynamic.data.mapping.form.web.internal.configuration.DDMFormWebConfiguration",
	localization = "content/Language", name = "ddm-form-web-configuration-name"
)
public interface DDMFormWebConfiguration {

	@Meta.AD(
		deflt = "1", description = "autosave-interval-description",
		name = "autosave-interval-name", required = false
	)
	public int autosaveInterval();

	@Meta.AD(
		deflt = "enabled-with-warning", name = "csv-export",
		optionLabels = {"enabled", "enabled-with-warning", "disabled"},
		optionValues = {"enabled", "enabled-with-warning", "disabled"},
		required = false
	)
	public String csvExport();

	@Meta.AD(
		deflt = "list", name = "default-display-view",
		optionLabels = {"%list", "%table"},
		optionValues = {"descriptive", "list"}, required = false
	)
	public String defaultDisplayView();

	@Meta.AD(
		deflt = "5", description = "maximum-repetitions-for-upload-fields-help",
		name = "maximum-repetitions-for-upload-fields", required = false
	)
	public int maximumRepetitionsForUploadFields();

	@Meta.AD(
		deflt = "doc, docx, jpeg, jpg, pdf, png, ppt, pptx, tiff, txt, xls, xlsx",
		description = "guest-upload-file-extensions-help",
		name = "guest-upload-file-extensions", required = false
	)
	public String guestUploadFileExtensions();

	@Meta.AD(
		deflt = "25", description = "guest-upload-maximum-file-size-help",
		name = "guest-upload-maximum-file-size", required = false
	)
	public long guestUploadMaximumFileSize();

	@Meta.AD(
		deflt = "5", description = "guest-upload-maximum-submissions-help",
		name = "guest-upload-maximum-submissions", required = false
	)
	public int guestUploadMaximumSubmissions();

}