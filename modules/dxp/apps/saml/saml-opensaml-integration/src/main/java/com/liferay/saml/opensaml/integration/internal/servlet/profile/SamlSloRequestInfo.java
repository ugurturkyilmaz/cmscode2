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

package com.liferay.saml.opensaml.integration.internal.servlet.profile;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.saml.persistence.model.SamlIdpSpSession;
import com.liferay.saml.persistence.model.SamlPeerBinding;

import java.io.Serializable;

import org.joda.time.DateTime;

import org.opensaml.saml.saml2.core.StatusCode;

/**
 * @author Mika Koivisto
 */
public class SamlSloRequestInfo implements Serializable {

	public static final int REQUEST_STATUS_FAILED = 3;

	public static final int REQUEST_STATUS_INITIATED = 1;

	public static final int REQUEST_STATUS_PENDING = 0;

	public static final int REQUEST_STATUS_SUCCESS = 2;

	public static final int REQUEST_STATUS_TIMED_OUT = 5;

	public static final int REQUEST_STATUS_UNSUPPORTED = 4;

	public String getEntityId() {
		return _samlPeerBinding.getSamlPeerEntityId();
	}

	public DateTime getInitiateTime() {
		return _initiateTime;
	}

	public String getName() {
		return _name;
	}

	public SamlIdpSpSession getSamlIdpSpSession() {
		return _samlIdpSpSession;
	}

	public SamlPeerBinding getSamlPeerBinding() {
		return _samlPeerBinding;
	}

	public int getStatus() {
		return _status;
	}

	public String getStatusCode() {
		return _statusCode;
	}

	public void setInitiateTime(DateTime initiateTime) {
		_initiateTime = initiateTime;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSamlIdpSpSession(SamlIdpSpSession samlIdpSpSession) {
		_samlIdpSpSession = samlIdpSpSession;
	}

	public void setSamlPeerBinding(SamlPeerBinding samlPeerBinding) {
		_samlPeerBinding = samlPeerBinding;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public void setStatusCode(String statusCode) {
		if (statusCode.equals(StatusCode.SUCCESS)) {
			_status = REQUEST_STATUS_SUCCESS;
		}
		else {
			_status = REQUEST_STATUS_FAILED;
		}

		_statusCode = statusCode;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONUtil.put("entityId", getEntityId());

		if (_initiateTime != null) {
			jsonObject.put("initiateTime", _initiateTime.toDate());
		}

		jsonObject.put(
			"name", getName()
		).put(
			"status", getStatus()
		).put(
			"statusCode", getStatusCode()
		);

		return jsonObject;
	}

	private DateTime _initiateTime;
	private String _name;
	private SamlIdpSpSession _samlIdpSpSession;
	private SamlPeerBinding _samlPeerBinding;
	private int _status;
	private String _statusCode;

}