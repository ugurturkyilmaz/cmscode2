/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.document.library.opener.onedrive.web.internal.constants;

import com.liferay.document.library.opener.constants.DLOpenerMimeTypes;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Cristina González
 */
public class DLOpenerOneDriveMimeTypesTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetMimeTypeExtension() {
		Assert.assertEquals(
			".docx",
			DLOpenerOneDriveMimeTypes.getMimeTypeExtension(
				DLOpenerMimeTypes.APPLICATION_VND_DOCX));
		Assert.assertEquals(
			".pptx",
			DLOpenerOneDriveMimeTypes.getMimeTypeExtension(
				DLOpenerMimeTypes.APPLICATION_VND_PPTX));
		Assert.assertEquals(
			".xlsx",
			DLOpenerOneDriveMimeTypes.getMimeTypeExtension(
				DLOpenerMimeTypes.APPLICATION_VND_XLSX));
	}

	@Test
	public void testGetOffice365MimeType() {
		Assert.assertEquals(
			DLOpenerMimeTypes.APPLICATION_VND_DOCX,
			DLOpenerOneDriveMimeTypes.getOffice365MimeType(
				DLOpenerMimeTypes.APPLICATION_VND_DOCX));
		Assert.assertEquals(
			DLOpenerMimeTypes.APPLICATION_VND_PPTX,
			DLOpenerOneDriveMimeTypes.getOffice365MimeType(
				DLOpenerMimeTypes.APPLICATION_VND_PPTX));
		Assert.assertEquals(
			DLOpenerMimeTypes.APPLICATION_VND_XLSX,
			DLOpenerOneDriveMimeTypes.getOffice365MimeType(
				DLOpenerMimeTypes.APPLICATION_VND_XLSX));
	}

}