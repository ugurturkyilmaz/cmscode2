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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.CustomField;
import com.liferay.headless.delivery.client.dto.v1_0.RelatedContent;
import com.liferay.headless.delivery.client.dto.v1_0.TaxonomyCategoryBrief;
import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class WikiPageSerDes {

	public static WikiPage toDTO(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToDTO(json);
	}

	public static WikiPage[] toDTOs(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToDTOs(json);
	}

	public static String toJSON(WikiPage wikiPage) {
		if (wikiPage == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (wikiPage.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(wikiPage.getActions()));
		}

		if (wikiPage.getAggregateRating() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"aggregateRating\": ");

			sb.append(String.valueOf(wikiPage.getAggregateRating()));
		}

		if (wikiPage.getContent() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"content\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getContent()));

			sb.append("\"");
		}

		if (wikiPage.getCreator() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(wikiPage.getCreator()));
		}

		if (wikiPage.getCustomFields() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append("[");

			for (int i = 0; i < wikiPage.getCustomFields().length; i++) {
				sb.append(String.valueOf(wikiPage.getCustomFields()[i]));

				if ((i + 1) < wikiPage.getCustomFields().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (wikiPage.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(wikiPage.getDateCreated()));

			sb.append("\"");
		}

		if (wikiPage.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(wikiPage.getDateModified()));

			sb.append("\"");
		}

		if (wikiPage.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getDescription()));

			sb.append("\"");
		}

		if (wikiPage.getEncodingFormat() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"encodingFormat\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getEncodingFormat()));

			sb.append("\"");
		}

		if (wikiPage.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (wikiPage.getHeadline() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"headline\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getHeadline()));

			sb.append("\"");
		}

		if (wikiPage.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(wikiPage.getId());
		}

		if (wikiPage.getKeywords() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"keywords\": ");

			sb.append("[");

			for (int i = 0; i < wikiPage.getKeywords().length; i++) {
				sb.append("\"");

				sb.append(_escape(wikiPage.getKeywords()[i]));

				sb.append("\"");

				if ((i + 1) < wikiPage.getKeywords().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (wikiPage.getNumberOfAttachments() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfAttachments\": ");

			sb.append(wikiPage.getNumberOfAttachments());
		}

		if (wikiPage.getNumberOfWikiPages() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfWikiPages\": ");

			sb.append(wikiPage.getNumberOfWikiPages());
		}

		if (wikiPage.getParentWikiPageId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentWikiPageId\": ");

			sb.append(wikiPage.getParentWikiPageId());
		}

		if (wikiPage.getRelatedContents() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"relatedContents\": ");

			sb.append("[");

			for (int i = 0; i < wikiPage.getRelatedContents().length; i++) {
				sb.append(String.valueOf(wikiPage.getRelatedContents()[i]));

				if ((i + 1) < wikiPage.getRelatedContents().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (wikiPage.getSiteId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(wikiPage.getSiteId());
		}

		if (wikiPage.getSubscribed() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subscribed\": ");

			sb.append(wikiPage.getSubscribed());
		}

		if (wikiPage.getTaxonomyCategoryBriefs() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taxonomyCategoryBriefs\": ");

			sb.append("[");

			for (int i = 0; i < wikiPage.getTaxonomyCategoryBriefs().length;
				 i++) {

				sb.append(
					String.valueOf(wikiPage.getTaxonomyCategoryBriefs()[i]));

				if ((i + 1) < wikiPage.getTaxonomyCategoryBriefs().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (wikiPage.getTaxonomyCategoryIds() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"taxonomyCategoryIds\": ");

			sb.append("[");

			for (int i = 0; i < wikiPage.getTaxonomyCategoryIds().length; i++) {
				sb.append(wikiPage.getTaxonomyCategoryIds()[i]);

				if ((i + 1) < wikiPage.getTaxonomyCategoryIds().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (wikiPage.getViewableBy() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"viewableBy\": ");

			sb.append("\"");

			sb.append(wikiPage.getViewableBy());

			sb.append("\"");
		}

		if (wikiPage.getWikiNodeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"wikiNodeId\": ");

			sb.append(wikiPage.getWikiNodeId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(WikiPage wikiPage) {
		if (wikiPage == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (wikiPage.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(wikiPage.getActions()));
		}

		if (wikiPage.getAggregateRating() == null) {
			map.put("aggregateRating", null);
		}
		else {
			map.put(
				"aggregateRating",
				String.valueOf(wikiPage.getAggregateRating()));
		}

		if (wikiPage.getContent() == null) {
			map.put("content", null);
		}
		else {
			map.put("content", String.valueOf(wikiPage.getContent()));
		}

		if (wikiPage.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(wikiPage.getCreator()));
		}

		if (wikiPage.getCustomFields() == null) {
			map.put("customFields", null);
		}
		else {
			map.put("customFields", String.valueOf(wikiPage.getCustomFields()));
		}

		if (wikiPage.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(wikiPage.getDateCreated()));
		}

		if (wikiPage.getDateModified() == null) {
			map.put("dateModified", null);
		}
		else {
			map.put(
				"dateModified",
				liferayToJSONDateFormat.format(wikiPage.getDateModified()));
		}

		if (wikiPage.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(wikiPage.getDescription()));
		}

		if (wikiPage.getEncodingFormat() == null) {
			map.put("encodingFormat", null);
		}
		else {
			map.put(
				"encodingFormat", String.valueOf(wikiPage.getEncodingFormat()));
		}

		if (wikiPage.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(wikiPage.getExternalReferenceCode()));
		}

		if (wikiPage.getHeadline() == null) {
			map.put("headline", null);
		}
		else {
			map.put("headline", String.valueOf(wikiPage.getHeadline()));
		}

		if (wikiPage.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(wikiPage.getId()));
		}

		if (wikiPage.getKeywords() == null) {
			map.put("keywords", null);
		}
		else {
			map.put("keywords", String.valueOf(wikiPage.getKeywords()));
		}

		if (wikiPage.getNumberOfAttachments() == null) {
			map.put("numberOfAttachments", null);
		}
		else {
			map.put(
				"numberOfAttachments",
				String.valueOf(wikiPage.getNumberOfAttachments()));
		}

		if (wikiPage.getNumberOfWikiPages() == null) {
			map.put("numberOfWikiPages", null);
		}
		else {
			map.put(
				"numberOfWikiPages",
				String.valueOf(wikiPage.getNumberOfWikiPages()));
		}

		if (wikiPage.getParentWikiPageId() == null) {
			map.put("parentWikiPageId", null);
		}
		else {
			map.put(
				"parentWikiPageId",
				String.valueOf(wikiPage.getParentWikiPageId()));
		}

		if (wikiPage.getRelatedContents() == null) {
			map.put("relatedContents", null);
		}
		else {
			map.put(
				"relatedContents",
				String.valueOf(wikiPage.getRelatedContents()));
		}

		if (wikiPage.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put("siteId", String.valueOf(wikiPage.getSiteId()));
		}

		if (wikiPage.getSubscribed() == null) {
			map.put("subscribed", null);
		}
		else {
			map.put("subscribed", String.valueOf(wikiPage.getSubscribed()));
		}

		if (wikiPage.getTaxonomyCategoryBriefs() == null) {
			map.put("taxonomyCategoryBriefs", null);
		}
		else {
			map.put(
				"taxonomyCategoryBriefs",
				String.valueOf(wikiPage.getTaxonomyCategoryBriefs()));
		}

		if (wikiPage.getTaxonomyCategoryIds() == null) {
			map.put("taxonomyCategoryIds", null);
		}
		else {
			map.put(
				"taxonomyCategoryIds",
				String.valueOf(wikiPage.getTaxonomyCategoryIds()));
		}

		if (wikiPage.getViewableBy() == null) {
			map.put("viewableBy", null);
		}
		else {
			map.put("viewableBy", String.valueOf(wikiPage.getViewableBy()));
		}

		if (wikiPage.getWikiNodeId() == null) {
			map.put("wikiNodeId", null);
		}
		else {
			map.put("wikiNodeId", String.valueOf(wikiPage.getWikiNodeId()));
		}

		return map;
	}

	public static class WikiPageJSONParser extends BaseJSONParser<WikiPage> {

		@Override
		protected WikiPage createDTO() {
			return new WikiPage();
		}

		@Override
		protected WikiPage[] createDTOArray(int size) {
			return new WikiPage[size];
		}

		@Override
		protected void setField(
			WikiPage wikiPage, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setActions(
						(Map)WikiPageSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "aggregateRating")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setAggregateRating(
						AggregateRatingSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "content")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setContent((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "customFields")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setCustomFields(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> CustomFieldSerDes.toDTO((String)object)
						).toArray(
							size -> new CustomField[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "encodingFormat")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setEncodingFormat((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					wikiPage.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "headline")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setHeadline((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "keywords")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setKeywords(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "numberOfAttachments")) {

				if (jsonParserFieldValue != null) {
					wikiPage.setNumberOfAttachments(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "numberOfWikiPages")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setNumberOfWikiPages(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "parentWikiPageId")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setParentWikiPageId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "relatedContents")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setRelatedContents(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> RelatedContentSerDes.toDTO((String)object)
						).toArray(
							size -> new RelatedContent[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "subscribed")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setSubscribed((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategoryBriefs")) {

				if (jsonParserFieldValue != null) {
					wikiPage.setTaxonomyCategoryBriefs(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TaxonomyCategoryBriefSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new TaxonomyCategoryBrief[size]
						));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategoryIds")) {

				if (jsonParserFieldValue != null) {
					wikiPage.setTaxonomyCategoryIds(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "viewableBy")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setViewableBy(
						WikiPage.ViewableBy.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "wikiNodeId")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setWikiNodeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}