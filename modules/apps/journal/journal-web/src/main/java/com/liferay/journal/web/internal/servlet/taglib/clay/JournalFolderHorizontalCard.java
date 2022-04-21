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

package com.liferay.journal.web.internal.servlet.taglib.clay;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseHorizontalCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.web.internal.constants.JournalWebConstants;
import com.liferay.journal.web.internal.servlet.taglib.util.JournalFolderActionDropdownItems;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.TrashHelper;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Eudaldo Alonso
 */
public class JournalFolderHorizontalCard extends BaseHorizontalCard {

	public JournalFolderHorizontalCard(
		BaseModel<?> baseModel, String displayStyle,
		RenderRequest renderRequest, RenderResponse renderResponse,
		RowChecker rowChecker, TrashHelper trashHelper) {

		super(baseModel, renderRequest, rowChecker);

		_displayStyle = displayStyle;
		_renderResponse = renderResponse;
		_trashHelper = trashHelper;

		_folder = (JournalFolder)baseModel;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		JournalFolderActionDropdownItems folderActionDropdownItems =
			new JournalFolderActionDropdownItems(
				_folder, PortalUtil.getLiferayPortletRequest(renderRequest),
				PortalUtil.getLiferayPortletResponse(_renderResponse),
				_trashHelper);

		try {
			return folderActionDropdownItems.getActionDropdownItems();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return null;
	}

	@Override
	public String getDefaultEventHandler() {
		return JournalWebConstants.JOURNAL_ELEMENTS_DEFAULT_EVENT_HANDLER;
	}

	@Override
	public String getHref() {
		return PortletURLBuilder.createRenderURL(
			_renderResponse
		).setParameter(
			"displayStyle", _displayStyle
		).setParameter(
			"folderId", _folder.getFolderId()
		).setParameter(
			"groupId", _folder.getGroupId()
		).buildString();
	}

	@Override
	public String getIcon() {
		return "folder";
	}

	@Override
	public String getInputName() {
		return rowChecker.getRowIds() + JournalFolder.class.getSimpleName();
	}

	@Override
	public String getInputValue() {
		return String.valueOf(_folder.getFolderId());
	}

	@Override
	public String getTitle() {
		return HtmlUtil.escape(_folder.getName());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalFolderHorizontalCard.class);

	private final String _displayStyle;
	private final JournalFolder _folder;
	private final RenderResponse _renderResponse;
	private final TrashHelper _trashHelper;

}