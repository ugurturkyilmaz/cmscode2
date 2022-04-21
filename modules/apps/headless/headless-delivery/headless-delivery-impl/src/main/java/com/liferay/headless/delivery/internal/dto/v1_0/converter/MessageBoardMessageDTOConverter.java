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

package com.liferay.headless.delivery.internal.dto.v1_0.converter;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetLinkLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.headless.delivery.dto.v1_0.MessageBoardMessage;
import com.liferay.headless.delivery.dto.v1_0.util.CreatorUtil;
import com.liferay.headless.delivery.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.AggregateRatingUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.CreatorStatisticsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.RelatedContentUtil;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.message.boards.service.MBMessageService;
import com.liferay.message.boards.service.MBStatsUserLocalService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.ratings.kernel.service.RatingsStatsLocalService;
import com.liferay.subscription.service.SubscriptionLocalService;

import java.util.Optional;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 */
@Component(
	property = "dto.class.name=com.liferay.message.boards.model.MBMessage",
	service = {DTOConverter.class, MessageBoardMessageDTOConverter.class}
)
public class MessageBoardMessageDTOConverter
	implements DTOConverter<MBMessage, MessageBoardMessage> {

	@Override
	public String getContentType() {
		return MessageBoardMessage.class.getSimpleName();
	}

	@Override
	public MessageBoardMessage toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		MBMessage mbMessage = _mbMessageService.getMessage(
			(Long)dtoConverterContext.getId());

		User user = _userLocalService.fetchUser(mbMessage.getUserId());

		return new MessageBoardMessage() {
			{
				actions = dtoConverterContext.getActions();
				aggregateRating = AggregateRatingUtil.toAggregateRating(
					_ratingsStatsLocalService.fetchStats(
						MBMessage.class.getName(), mbMessage.getMessageId()));
				anonymous = mbMessage.isAnonymous();
				articleBody = mbMessage.getBody();
				customFields = CustomFieldsUtil.toCustomFields(
					dtoConverterContext.isAcceptAllLanguages(),
					MBMessage.class.getName(), mbMessage.getMessageId(),
					mbMessage.getCompanyId(), dtoConverterContext.getLocale());
				dateCreated = mbMessage.getCreateDate();
				dateModified = mbMessage.getModifiedDate();
				encodingFormat = mbMessage.getFormat();
				externalReferenceCode = mbMessage.getExternalReferenceCode();
				friendlyUrlPath = mbMessage.getUrlSubject();
				headline = mbMessage.getSubject();
				id = mbMessage.getMessageId();
				keywords = ListUtil.toArray(
					_assetTagLocalService.getTags(
						MBMessage.class.getName(), mbMessage.getMessageId()),
					AssetTag.NAME_ACCESSOR);
				messageBoardSectionId = mbMessage.getCategoryId();
				messageBoardThreadId = mbMessage.getThreadId();
				numberOfMessageBoardAttachments =
					mbMessage.getAttachmentsFileEntriesCount();
				numberOfMessageBoardMessages =
					_mbMessageLocalService.getChildMessagesCount(
						mbMessage.getMessageId(),
						WorkflowConstants.STATUS_APPROVED);
				relatedContents = RelatedContentUtil.toRelatedContents(
					_assetEntryLocalService, _assetLinkLocalService,
					dtoConverterContext.getDTOConverterRegistry(),
					mbMessage.getModelClassName(), mbMessage.getMessageId(),
					dtoConverterContext.getLocale());
				showAsAnswer = mbMessage.isAnswer();
				siteId = mbMessage.getGroupId();
				status = WorkflowConstants.getStatusLabel(
					mbMessage.getStatus());
				subscribed = _subscriptionLocalService.isSubscribed(
					mbMessage.getCompanyId(), dtoConverterContext.getUserId(),
					MBThread.class.getName(), mbMessage.getThreadId());

				setCreator(
					() -> {
						if (mbMessage.isAnonymous()) {
							return null;
						}

						return CreatorUtil.toCreator(
							_portal, dtoConverterContext.getUriInfoOptional(),
							user);
					});
				setCreatorStatistics(
					() -> {
						if (mbMessage.isAnonymous() || (user == null) ||
							user.isDefaultUser()) {

							return null;
						}

						Optional<UriInfo> uriInfoOptional =
							dtoConverterContext.getUriInfoOptional();

						return CreatorStatisticsUtil.toCreatorStatistics(
							mbMessage.getGroupId(),
							String.valueOf(dtoConverterContext.getLocale()),
							_mbStatsUserLocalService,
							uriInfoOptional.orElse(null), user);
					});
				setParentMessageBoardMessageId(
					() -> {
						if (mbMessage.getParentMessageId() == 0L) {
							return null;
						}

						return mbMessage.getParentMessageId();
					});
			}
		};
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private MBMessageService _mbMessageService;

	@Reference
	private MBStatsUserLocalService _mbStatsUserLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RatingsStatsLocalService _ratingsStatsLocalService;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}