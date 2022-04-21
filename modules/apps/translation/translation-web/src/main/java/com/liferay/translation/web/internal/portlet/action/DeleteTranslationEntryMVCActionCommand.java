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

package com.liferay.translation.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.translation.constants.TranslationPortletKeys;
import com.liferay.translation.service.TranslationEntryService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + TranslationPortletKeys.TRANSLATION,
		"mvc.command.name=/translation/delete_translation_entry"
	},
	service = MVCActionCommand.class
)
public class DeleteTranslationEntryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long translationEntryId = ParamUtil.getLong(
			actionRequest, "translationEntryId");

		if (translationEntryId > 0) {
			_translationEntryService.deleteTranslationEntry(translationEntryId);
		}
		else {
			long[] deleteTranslationEntryIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");

			for (long deleteTranslationEntryId : deleteTranslationEntryIds) {
				_translationEntryService.deleteTranslationEntry(
					deleteTranslationEntryId);
			}
		}
	}

	@Reference
	private TranslationEntryService _translationEntryService;

}