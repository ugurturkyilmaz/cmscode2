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

import {fetch} from 'frontend-js-web';

import {updateNetwork} from '../actions/index';
import {SERVICE_NETWORK_STATUS_TYPES} from '../config/constants/serviceNetworkStatusTypes';
import {config} from '../config/index';

/**
 * Returns a new FormData built from the given object.
 * @param {object} body
 * @return {FormData}
 * @review
 */
function getFormData(body) {
	const formData = new FormData();

	Object.entries(body).forEach(([key, value]) => {
		formData.append(`${config.portletNamespace}${key}`, value);
	});

	return formData;
}

/**
 * Performs a POST request to the given url and parses an expected object response.
 * If the response status is over 400, or there is any "error" or "exception"
 * properties on the response object, it rejects the promise with an Error object.
 * @param {string} url
 * @param {object} [body={}]
 * @param {function} onNetworkStatus
 * @param {{ requestGenerateDraft: boolean }} [options={requestGenerateDraft: false}]
 * @private
 * @return {Promise<object>}
 * @review
 */
export default function serviceFetch(
	url,
	{body = {}, method, ...options} = {body: {}},
	onNetworkStatus,
	{requestGenerateDraft = false} = {requestGenerateDraft: false}
) {
	if (requestGenerateDraft) {
		onNetworkStatus(
			updateNetwork({
				status: SERVICE_NETWORK_STATUS_TYPES.savingDraft,
			})
		);
	}

	return fetch(url, {
		...options,
		body: getFormData(body),
		method: method || 'POST',
	})
		.then(
			(response) =>
				new Promise((resolve, reject) => {
					response
						.clone()
						.json()
						.then((body) => resolve([response, body]))
						.catch(() => response.clone().text())
						.then((body) => resolve([response, body]))
						.catch(reject);
				})
		)
		.then(([response, body]) => {
			if (typeof body === 'object') {
				if ('exception' in body) {
					return handleErroredResponse(
						Liferay.Language.get('an-unexpected-error-occurred'),
						onNetworkStatus
					);
				}
				else if ('error' in body) {
					return handleErroredResponse(
						Liferay.Language.get(body.error),
						onNetworkStatus
					);
				}
			}
			else {
				return handleErroredResponse(
					Liferay.Language.get('an-unexpected-error-occurred'),
					onNetworkStatus
				);
			}

			if (response.status >= 400) {
				return handleErroredResponse(
					Liferay.Language.get('an-unexpected-error-occurred'),
					onNetworkStatus
				);
			}

			if (requestGenerateDraft) {
				onNetworkStatus(
					updateNetwork({
						error: null,
						status: SERVICE_NETWORK_STATUS_TYPES.draftSaved,
					})
				);
			}

			return body;
		});
}

/**
 * @param {string} error
 * @param {function} onNetworkStatus
 */
function handleErroredResponse(error, onNetworkStatus) {
	onNetworkStatus(
		updateNetwork({
			error,
			status: SERVICE_NETWORK_STATUS_TYPES.error,
		})
	);

	return Promise.reject(error);
}
