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

package com.liferay.digital.signature.web.internal.portlet.action;

import com.liferay.digital.signature.constants.DigitalSignaturePortletKeys;
import com.liferay.digital.signature.manager.DSEnvelopeManager;
import com.liferay.digital.signature.model.DSDocument;
import com.liferay.digital.signature.model.DSEnvelope;
import com.liferay.digital.signature.model.DSRecipient;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author José Abelenda
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DigitalSignaturePortletKeys.COLLECT_DIGITAL_SIGNATURE,
		"javax.portlet.name=" + DigitalSignaturePortletKeys.DIGITAL_SIGNATURE,
		"mvc.command.name=/digital_signature/add_ds_envelope"
	},
	service = MVCResourceCommand.class
)
public class AddDSEnvelopeMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		DSEnvelope dsEnvelope = _dsEnvelopeManager.addDSEnvelope(
			themeDisplay.getCompanyId(), themeDisplay.getSiteGroupId(),
			new DSEnvelope() {
				{
					dsDocuments = _getDSDocuments(resourceRequest);
					dsRecipients = _getDSRecipients(resourceRequest);
					emailBlurb = ParamUtil.getString(
						resourceRequest, "emailMessage");
					emailSubject = ParamUtil.getString(
						resourceRequest, "emailSubject");
					name = ParamUtil.getString(resourceRequest, "envelopeName");
					senderEmailAddress = user.getEmailAddress();
					status = "sent";
				}
			});

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse,
			JSONUtil.put("dsEnvelopeId", dsEnvelope.getDSEnvelopeId()));
	}

	private List<DSDocument> _getDSDocuments(ResourceRequest resourceRequest)
		throws Exception {

		return TransformUtil.transformToList(
			ArrayUtil.toLongArray(
				ParamUtil.getLongValues(resourceRequest, "fileEntryIds")),
			fileEntryId -> {
				FileEntry fileEntry = _dlAppLocalService.getFileEntry(
					fileEntryId);

				return new DSDocument() {
					{
						data = Base64.encode(
							FileUtil.getBytes(fileEntry.getContentStream()));
						dsDocumentId = String.valueOf(fileEntryId);
						fileExtension = fileEntry.getExtension();
						name = fileEntry.getFileName();
					}
				};
			});
	}

	private List<DSRecipient> _getDSRecipients(ResourceRequest resourceRequest)
		throws Exception {

		IntegerWrapper integerWrapper = new IntegerWrapper();

		return JSONUtil.toList(
			JSONFactoryUtil.createJSONArray(
				ParamUtil.getString(resourceRequest, "recipients")),
			recipientJSONObject -> new DSRecipient() {
				{
					dsRecipientId = String.valueOf(integerWrapper.increment());
					emailAddress = recipientJSONObject.getString("email");
					name = recipientJSONObject.getString("fullName");
				}
			});
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DSEnvelopeManager _dsEnvelopeManager;

}