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

package com.liferay.segments.asah.connector.internal.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @author André Miranda
 * @author Sarai Díaz
 * @author David Arques
 */
public final class Experiment {

	public String getChannelId() {
		return _channelId;
	}

	public Double getConfidenceLevel() {
		return _confidenceLevel;
	}

	@JsonFormat(
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
		shape = JsonFormat.Shape.STRING, timezone = "UTC"
	)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getDataSourceId() {
		return _dataSourceId;
	}

	public String getDescription() {
		return _description;
	}

	@JsonProperty("dxpExperienceId")
	public String getDXPExperienceId() {
		return _dxpExperienceId;
	}

	@JsonProperty("dxpExperienceName")
	public String getDXPExperienceName() {
		return _dxpExperienceName;
	}

	@JsonProperty("dxpGroupId")
	public Long getDXPGroupId() {
		return _dxpGroupId;
	}

	@JsonProperty("dxpLayoutId")
	public String getDXPLayoutId() {
		return _dxpLayoutId;
	}

	@JsonProperty("dxpSegmentId")
	public String getDXPSegmentId() {
		return _dxpSegmentId;
	}

	@JsonProperty("dxpSegmentName")
	public String getDXPSegmentName() {
		return _dxpSegmentName;
	}

	@JsonProperty("dxpVariants")
	public List<DXPVariant> getDXPVariants() {
		return _dxpVariants;
	}

	@JsonProperty("status")
	public ExperimentStatus getExperimentStatus() {
		return _experimentStatus;
	}

	@JsonProperty("type")
	public ExperimentType getExperimentType() {
		return _experimentType;
	}

	public Goal getGoal() {
		return _goal;
	}

	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
		shape = JsonFormat.Shape.STRING, timezone = "UTC"
	)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getName() {
		return _name;
	}

	public String getPageRelativePath() {
		return _pageRelativePath;
	}

	public String getPageTitle() {
		return _pageTitle;
	}

	public String getPageURL() {
		return _pageURL;
	}

	public String getPublishedDXPVariantId() {
		return _publishedDXPVariantId;
	}

	@JsonFormat(
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
		shape = JsonFormat.Shape.STRING, timezone = "UTC"
	)
	public Date getStartedDate() {
		if (_startedDate == null) {
			return null;
		}

		return new Date(_startedDate.getTime());
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setConfidenceLevel(Double confidenceLevel) {
		_confidenceLevel = confidenceLevel;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDXPExperienceId(String dxpExperienceId) {
		_dxpExperienceId = dxpExperienceId;
	}

	public void setDXPExperienceName(String dxpExperienceName) {
		_dxpExperienceName = dxpExperienceName;
	}

	public void setDXPGroupId(Long dxpGroupId) {
		_dxpGroupId = dxpGroupId;
	}

	public void setDXPLayoutId(String dxpLayoutId) {
		_dxpLayoutId = dxpLayoutId;
	}

	public void setDXPSegmentId(String dxpSegmentId) {
		_dxpSegmentId = dxpSegmentId;
	}

	public void setDXPSegmentName(String dxpSegmentName) {
		_dxpSegmentName = dxpSegmentName;
	}

	public void setDXPVariants(List<DXPVariant> dxpVariants) {
		_dxpVariants = dxpVariants;
	}

	public void setExperimentStatus(ExperimentStatus experimentStatus) {
		_experimentStatus = experimentStatus;
	}

	public void setExperimentType(ExperimentType experimentType) {
		_experimentType = experimentType;
	}

	public void setGoal(Goal goal) {
		_goal = goal;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPageRelativePath(String pageRelativePath) {
		_pageRelativePath = pageRelativePath;
	}

	public void setPageTitle(String pageTitle) {
		_pageTitle = pageTitle;
	}

	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	public void setPublishedDXPVariantId(String publishedDXPVariantId) {
		_publishedDXPVariantId = publishedDXPVariantId;
	}

	public void setStartedDate(Date startedDate) {
		if (startedDate != null) {
			_startedDate = new Date(startedDate.getTime());
		}
	}

	private String _channelId;
	private Double _confidenceLevel;
	private Date _createDate;
	private String _dataSourceId;
	private String _description;
	private String _dxpExperienceId;
	private String _dxpExperienceName;
	private Long _dxpGroupId;
	private String _dxpLayoutId;
	private String _dxpSegmentId;
	private String _dxpSegmentName;
	private List<DXPVariant> _dxpVariants;
	private ExperimentStatus _experimentStatus = ExperimentStatus.DRAFT;
	private ExperimentType _experimentType = ExperimentType.AB;
	private Goal _goal;
	private String _id;
	private Date _modifiedDate;
	private String _name;
	private String _pageRelativePath;
	private String _pageTitle;
	private String _pageURL;
	private String _publishedDXPVariantId;
	private Date _startedDate;

}