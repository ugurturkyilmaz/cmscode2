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

package com.liferay.portal.search.internal.summary;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.summary.Summary;
import com.liferay.portal.search.summary.SummaryBuilder;
import com.liferay.portal.util.HtmlImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author André de Oliveira
 * @author Bryan Engler
 * @author Ryan Park
 * @author Tibor Lipusz
 */
public class SummaryBuilderImpl implements SummaryBuilder {

	@Override
	public Summary build() {
		return new SummaryImpl(_buildTitle(), _buildContent(), _locale);
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	@Override
	public void setEscape(boolean escape) {
		_escape = escape;
	}

	@Override
	public void setHighlight(boolean highlight) {
		_highlight = highlight;
	}

	@Override
	public void setLocale(Locale locale) {
		_locale = locale;
	}

	@Override
	public void setMaxContentLength(int maxContentLength) {
		_maxContentLength = maxContentLength;
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	private String _buildContent() {
		return _buildText(_content, true);
	}

	private String _buildText(String text, boolean checkMaxLength) {
		if (Validator.isNull(text)) {
			return StringPool.BLANK;
		}

		if (checkMaxLength && (_maxContentLength > 0)) {
			text = _shorten(text, _maxContentLength);
		}

		if (_escape) {
			text = _escape(text);
		}

		if (_highlight) {
			text = _highlight(text);
		}

		return text;
	}

	private String _buildTitle() {
		return _buildText(_title, false);
	}

	private String _escape(String text) {
		text = StringUtil.replace(
			text, _HIGHLIGHT_TAGS, _ESCAPE_SAFE_HIGHLIGHTS);

		text = _html.escape(text);

		return StringUtil.replace(
			text, _ESCAPE_SAFE_HIGHLIGHTS, _HIGHLIGHT_TAGS);
	}

	private String _highlight(String text) {
		return StringUtil.replace(
			text, _HIGHLIGHT_TAGS, HighlightUtil.HIGHLIGHTS);
	}

	private String _shorten(String text, int maxLength) {
		String originalText = text;

		List<Integer> closeTagIndexes = new ArrayList<>();
		List<Integer> openTagIndexes = new ArrayList<>();

		while (text.lastIndexOf(HighlightUtil.HIGHLIGHT_TAG_CLOSE) != -1) {
			closeTagIndexes.add(
				text.lastIndexOf(HighlightUtil.HIGHLIGHT_TAG_CLOSE));

			text = StringUtil.removeLast(
				text, HighlightUtil.HIGHLIGHT_TAG_CLOSE);

			openTagIndexes.add(
				text.lastIndexOf(HighlightUtil.HIGHLIGHT_TAG_OPEN));

			text = StringUtil.removeLast(
				text, HighlightUtil.HIGHLIGHT_TAG_OPEN);
		}

		if (text.length() > maxLength) {
			text = StringUtil.shorten(text, maxLength);
		}
		else {
			return originalText;
		}

		ListUtil.sort(closeTagIndexes);
		ListUtil.sort(openTagIndexes);

		for (int i = 0; i < openTagIndexes.size(); i++) {
			int textEndIndex = text.length();

			if (text.endsWith("...")) {
				textEndIndex = textEndIndex - 3;
			}

			int openTagIndex = openTagIndexes.get(i);

			if (openTagIndex < textEndIndex) {
				text = StringUtil.insert(
					text, HighlightUtil.HIGHLIGHT_TAG_OPEN, openTagIndex);

				textEndIndex =
					textEndIndex + HighlightUtil.HIGHLIGHT_TAG_OPEN.length();

				int closeTagIndex = closeTagIndexes.get(i);

				text = StringUtil.insert(
					text, HighlightUtil.HIGHLIGHT_TAG_CLOSE,
					Math.min(closeTagIndex, textEndIndex));
			}
			else {
				break;
			}
		}

		return text;
	}

	private static final String[] _ESCAPE_SAFE_HIGHLIGHTS = {
		"[@HIGHLIGHT1@]", "[@HIGHLIGHT2@]"
	};

	private static final String[] _HIGHLIGHT_TAGS = {
		HighlightUtil.HIGHLIGHT_TAG_OPEN, HighlightUtil.HIGHLIGHT_TAG_CLOSE
	};

	private String _content;
	private boolean _escape = true;
	private boolean _highlight;
	private final Html _html = new HtmlImpl();
	private Locale _locale;
	private int _maxContentLength;
	private String _title;

}