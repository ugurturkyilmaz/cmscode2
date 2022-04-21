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

package com.liferay.portal.workflow.metrics.model;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Feliphe Marinho
 */
public class UpdateTaskRequest {

	public Map<Locale, String> getAssetTitleMap() {
		return _assetTitleMap;
	}

	public Map<Locale, String> getAssetTypeMap() {
		return _assetTypeMap;
	}

	public List<Assignment> getAssignments() {
		return _assignments;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public long getTaskId() {
		return _taskId;
	}

	public long getUserId() {
		return _userId;
	}

	public static class Builder {

		public UpdateTaskRequest.Builder assetTitleMap(
			Map<Locale, String> assetTitleMap) {

			_updateTaskRequest._assetTitleMap = assetTitleMap;

			return this;
		}

		public UpdateTaskRequest.Builder assetTypeMap(
			Map<Locale, String> assetTitleMap) {

			_updateTaskRequest._assetTypeMap = assetTitleMap;

			return this;
		}

		public UpdateTaskRequest.Builder assignments(
			List<Assignment> assignments) {

			_updateTaskRequest._assignments = assignments;

			return this;
		}

		public UpdateTaskRequest.Builder assignments(
			UnsafeSupplier<List<Assignment>, Exception>
				assignmentsUnsafeSupplier) {

			try {
				_updateTaskRequest._assignments =
					assignmentsUnsafeSupplier.get();
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception);
				}
			}

			return this;
		}

		public UpdateTaskRequest build() {
			return _updateTaskRequest;
		}

		public UpdateTaskRequest.Builder companyId(long companyId) {
			_updateTaskRequest._companyId = companyId;

			return this;
		}

		public UpdateTaskRequest.Builder modifiedDate(Date modifiedDate) {
			_updateTaskRequest._modifiedDate = modifiedDate;

			return this;
		}

		public UpdateTaskRequest.Builder taskId(long taskId) {
			_updateTaskRequest._taskId = taskId;

			return this;
		}

		public UpdateTaskRequest.Builder userId(long userId) {
			_updateTaskRequest._userId = userId;

			return this;
		}

		private final UpdateTaskRequest _updateTaskRequest =
			new UpdateTaskRequest();

	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateTaskRequest.class);

	private Map<Locale, String> _assetTitleMap;
	private Map<Locale, String> _assetTypeMap;
	private List<Assignment> _assignments;
	private long _companyId;
	private Date _modifiedDate;
	private long _taskId;
	private long _userId;

}