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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetLinkLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.headless.delivery.dto.v1_0.ContentField;
import com.liferay.headless.delivery.dto.v1_0.RenderedContent;
import com.liferay.headless.delivery.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.dto.v1_0.TaxonomyCategoryBrief;
import com.liferay.headless.delivery.dto.v1_0.util.ContentFieldUtil;
import com.liferay.headless.delivery.dto.v1_0.util.CreatorUtil;
import com.liferay.headless.delivery.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.AggregateRatingUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.DisplayPageRendererUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.RelatedContentUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.RenderedContentValueUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.TaxonomyCategoryBriefUtil;
import com.liferay.headless.delivery.internal.resource.v1_0.BaseStructuredContentResourceImpl;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.journal.util.JournalContent;
import com.liferay.journal.util.JournalConverter;
import com.liferay.layout.display.page.LayoutDisplayPageProviderTracker;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.util.GroupUtil;
import com.liferay.portal.vulcan.util.JaxRsLinkUtil;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalService;
import com.liferay.subscription.service.SubscriptionLocalService;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 * @author Víctor Galán
 */
@Component(
	property = "dto.class.name=com.liferay.journal.model.JournalArticle",
	service = {DTOConverter.class, StructuredContentDTOConverter.class}
)
public class StructuredContentDTOConverter
	implements DTOConverter<JournalArticle, StructuredContent> {

	@Override
	public String getContentType() {
		return StructuredContent.class.getSimpleName();
	}

	@Override
	public StructuredContent toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		JournalArticle journalArticle = _journalArticleService.getLatestArticle(
			(Long)dtoConverterContext.getId());

		return toDTO(dtoConverterContext, journalArticle);
	}

	@Override
	public StructuredContent toDTO(
			DTOConverterContext dtoConverterContext,
			JournalArticle journalArticle)
		throws Exception {

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		Group group = _groupLocalService.fetchGroup(
			journalArticle.getGroupId());

		return new StructuredContent() {
			{
				actions = dtoConverterContext.getActions();
				aggregateRating = AggregateRatingUtil.toAggregateRating(
					_ratingsStatsLocalService.fetchStats(
						JournalArticle.class.getName(),
						journalArticle.getResourcePrimKey()));
				assetLibraryKey = GroupUtil.getAssetLibraryKey(group);
				availableLanguages = LocaleUtil.toW3cLanguageIds(
					journalArticle.getAvailableLanguageIds());
				contentFields = _toContentFields(
					_dlAppService, _dlURLHelper, dtoConverterContext,
					_fieldsToDDMFormValuesConverter, journalArticle,
					_journalArticleService, _journalConverter,
					_layoutLocalService);
				contentStructureId = ddmStructure.getStructureId();
				creator = CreatorUtil.toCreator(
					_portal, dtoConverterContext.getUriInfoOptional(),
					_userLocalService.fetchUser(journalArticle.getUserId()));
				customFields = CustomFieldsUtil.toCustomFields(
					dtoConverterContext.isAcceptAllLanguages(),
					JournalArticle.class.getName(), journalArticle.getId(),
					journalArticle.getCompanyId(),
					dtoConverterContext.getLocale());
				dateCreated = journalArticle.getCreateDate();
				dateModified = journalArticle.getModifiedDate();
				datePublished = journalArticle.getDisplayDate();
				description = journalArticle.getDescription(
					dtoConverterContext.getLocale());
				description_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					_filterDescriptionMap(journalArticle.getDescriptionMap()));
				externalReferenceCode =
					journalArticle.getExternalReferenceCode();
				friendlyUrlPath = journalArticle.getUrlTitle(
					dtoConverterContext.getLocale());
				friendlyUrlPath_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					journalArticle.getFriendlyURLMap());
				id = journalArticle.getResourcePrimKey();
				key = journalArticle.getArticleId();
				keywords = ListUtil.toArray(
					_assetTagLocalService.getTags(
						JournalArticle.class.getName(),
						journalArticle.getResourcePrimKey()),
					AssetTag.NAME_ACCESSOR);
				numberOfComments = _commentManager.getCommentsCount(
					JournalArticle.class.getName(),
					journalArticle.getResourcePrimKey());
				relatedContents = RelatedContentUtil.toRelatedContents(
					_assetEntryLocalService, _assetLinkLocalService,
					dtoConverterContext.getDTOConverterRegistry(),
					JournalArticle.class.getName(),
					journalArticle.getResourcePrimKey(),
					dtoConverterContext.getLocale());
				renderedContents = _toRenderedContents(
					ddmStructure, dtoConverterContext, journalArticle);
				siteId = GroupUtil.getSiteId(group);
				subscribed = _subscriptionLocalService.isSubscribed(
					journalArticle.getCompanyId(),
					dtoConverterContext.getUserId(),
					JournalArticle.class.getName(),
					journalArticle.getResourcePrimKey());
				taxonomyCategoryBriefs = TransformUtil.transformToArray(
					_assetCategoryLocalService.getCategories(
						JournalArticle.class.getName(),
						journalArticle.getResourcePrimKey()),
					assetCategory ->
						TaxonomyCategoryBriefUtil.toTaxonomyCategoryBrief(
							assetCategory, dtoConverterContext),
					TaxonomyCategoryBrief.class);
				title = journalArticle.getTitle(
					dtoConverterContext.getLocale());
				title_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					journalArticle.getTitleMap());
				uuid = journalArticle.getUuid();

				setPriority(
					() -> {
						AssetEntry assetEntry =
							_assetEntryLocalService.fetchEntry(
								journalArticle.getModelClassName(),
								journalArticle.getResourcePrimKey());

						if (assetEntry == null) {
							return null;
						}

						return assetEntry.getPriority();
					});
			}
		};
	}

	private Map<Locale, String> _filterDescriptionMap(
		Map<Locale, String> descriptionMap) {

		Set<Map.Entry<Locale, String>> set = descriptionMap.entrySet();

		Stream<Map.Entry<Locale, String>> stream = set.stream();

		return stream.filter(
			entry -> !StringPool.BLANK.equals(entry.getValue())
		).collect(
			Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
		);
	}

	private ContentField[] _toContentFields(
			DLAppService dlAppService, DLURLHelper dlURLHelper,
			DTOConverterContext dtoConverterContext,
			FieldsToDDMFormValuesConverter fieldsToDDMFormValuesConverter,
			JournalArticle journalArticle,
			JournalArticleService journalArticleService,
			JournalConverter journalConverter,
			LayoutLocalService layoutLocalService)
		throws Exception {

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		Fields fields = journalConverter.getDDMFields(
			ddmStructure, journalArticle.getContent());

		DDMFormValues ddmFormValues = fieldsToDDMFormValuesConverter.convert(
			ddmStructure, fields);

		return TransformUtil.transformToArray(
			ddmFormValues.getDDMFormFieldValues(),
			ddmFormFieldValue -> ContentFieldUtil.toContentField(
				ddmFormFieldValue, dlAppService, dlURLHelper,
				dtoConverterContext, journalArticleService, layoutLocalService),
			ContentField.class);
	}

	private RenderedContent[] _toRenderedContents(
		DDMStructure ddmStructure, DTOConverterContext dtoConverterContext,
		JournalArticle journalArticle) {

		Optional<UriInfo> uriInfoOptional =
			dtoConverterContext.getUriInfoOptional();

		if (!uriInfoOptional.isPresent()) {
			return null;
		}

		boolean acceptAllLanguages = dtoConverterContext.isAcceptAllLanguages();
		HttpServletRequest httpServletRequest =
			dtoConverterContext.getHttpServletRequest();
		Locale locale = dtoConverterContext.getLocale();
		UriInfo uriInfo = uriInfoOptional.get();

		RenderedContent[] renderedContents = TransformUtil.transformToArray(
			ddmStructure.getTemplates(),
			ddmTemplate -> new RenderedContent() {
				{
					contentTemplateId = ddmTemplate.getTemplateKey();
					contentTemplateName = ddmTemplate.getName(locale);
					contentTemplateName_i18n = LocalizedMapUtil.getI18nMap(
						acceptAllLanguages, ddmTemplate.getNameMap());
					renderedContentURL = JaxRsLinkUtil.getJaxRsLink(
						"headless-delivery",
						BaseStructuredContentResourceImpl.class,
						"getStructuredContentRenderedContentContentTemplate",
						uriInfo, journalArticle.getResourcePrimKey(),
						ddmTemplate.getTemplateKey());

					setMarkedAsDefault(
						() -> {
							if (Objects.equals(
									ddmTemplate.getTemplateKey(),
									journalArticle.getDDMTemplateKey())) {

								return true;
							}

							return false;
						});
					setRenderedContentValue(
						() -> {
							if (!uriInfoOptional.map(
									UriInfo::getQueryParameters
								).map(
									parameters -> parameters.getFirst(
										"nestedFields")
								).map(
									fields -> fields.contains(
										"renderedContentValue")
								).orElse(
									false
								)) {

								return null;
							}

							return RenderedContentValueUtil.renderTemplate(
								_classNameLocalService,
								_ddmTemplateLocalService, _groupLocalService,
								httpServletRequest, _journalArticleService,
								_journalContent, locale,
								journalArticle.getResourcePrimKey(),
								ddmTemplate.getTemplateKey(), uriInfo);
						});
				}
			},
			RenderedContent.class);

		RenderedContent[] displayPagesRenderedContents =
			DisplayPageRendererUtil.getRenderedContent(
				BaseStructuredContentResourceImpl.class,
				JournalArticle.class.getName(),
				journalArticle.getResourcePrimKey(),
				ddmStructure.getStructureId(), dtoConverterContext,
				journalArticle.getGroupId(), journalArticle,
				_infoItemServiceTracker, _layoutDisplayPageProviderTracker,
				_layoutLocalService, _layoutPageTemplateEntryService,
				"getStructuredContentRenderedContentByDisplayPageDisplayPage" +
					"Key");

		return ArrayUtil.append(renderedContents, displayPagesRenderedContents);
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommentManager _commentManager;

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference
	private FieldsToDDMFormValuesConverter _fieldsToDDMFormValuesConverter;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private JournalArticleService _journalArticleService;

	@Reference
	private JournalContent _journalContent;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private LayoutDisplayPageProviderTracker _layoutDisplayPageProviderTracker;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Reference
	private Portal _portal;

	@Reference
	private RatingsStatsLocalService _ratingsStatsLocalService;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}