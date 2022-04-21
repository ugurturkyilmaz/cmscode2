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

package com.liferay.message.boards.uad.exporter;

import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.message.boards.uad.constants.MBUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the message boards thread UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link MBThreadUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseMBThreadUADExporter
	extends DynamicQueryUADExporter<MBThread> {

	@Override
	public Class<MBThread> getTypeClass() {
		return MBThread.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return mbThreadLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return MBUADConstants.USER_ID_FIELD_NAMES_MB_THREAD;
	}

	@Override
	protected String toXmlString(MBThread mbThread) {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.message.boards.model.MBThread");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>threadId</column-name><column-value><![CDATA[");
		sb.append(mbThread.getThreadId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(mbThread.getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(mbThread.getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rootMessageUserId</column-name><column-value><![CDATA[");
		sb.append(mbThread.getRootMessageUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPostByUserId</column-name><column-value><![CDATA[");
		sb.append(mbThread.getLastPostByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(mbThread.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(mbThread.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(mbThread.getTitle());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected MBThreadLocalService mbThreadLocalService;

}