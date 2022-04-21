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

package com.liferay.portal.search.similar.results.web.internal.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Kevin Tan
 */
public class SimilarResultsDocumentDisplayContext {

	public String getCategoriesString() {
		return _getNonnullValue(_categoriesString);
	}

	public String getContent() {
		return _getNonnullValue(_content);
	}

	public String getCreationDateString() {
		return _getNonnullValue(_creationDateString);
	}

	public String getCreatorUserName() {
		return _getNonnullValue(_creatorUserName);
	}

	public String getIconId() {
		if (Validator.isNull(_iconId)) {
			return "web-content";
		}

		return _iconId;
	}

	public String getModelResource() {
		return _getNonnullValue(_modelResource);
	}

	public String getThumbnailURLString() {
		return _getNonnullValue(_thumbnailURLString);
	}

	public String getTitle() {
		return _getNonnullValue(_title);
	}

	public String getViewURL() {
		return _getNonnullValue(_viewURL);
	}

	public boolean isTemporarilyUnavailable() {
		return _temporarilyUnavailable;
	}

	public void setCategoriesString(String categoriesString) {
		_categoriesString = categoriesString;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setCreationDateString(String creationDateString) {
		_creationDateString = creationDateString;
	}

	public void setCreatorUserName(String creatorUserName) {
		_creatorUserName = creatorUserName;
	}

	public void setIconId(String iconId) {
		_iconId = iconId;
	}

	public void setModelResource(String modelResource) {
		_modelResource = modelResource;
	}

	public void setTemporarilyUnavailable(boolean temporarilyUnavailable) {
		_temporarilyUnavailable = temporarilyUnavailable;
	}

	public void setThumbnailURLString(String thumbnailURLString) {
		_thumbnailURLString = thumbnailURLString;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setViewURL(String viewURL) {
		_viewURL = viewURL;
	}

	private String _getNonnullValue(String value) {
		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		return value;
	}

	private String _categoriesString;
	private String _content;
	private String _creationDateString;
	private String _creatorUserName;
	private String _iconId;
	private String _modelResource;
	private boolean _temporarilyUnavailable;
	private String _thumbnailURLString;
	private String _title;
	private String _viewURL;

}