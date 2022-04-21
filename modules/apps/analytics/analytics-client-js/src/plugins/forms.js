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

import {onReady} from '../utils/events';

const applicationId = 'Form';

/**
 * Returns an identifier for a form element.
 * @param {Object} form The form DOM element
 * @returns {Object} Either form id, name or action.
 */
function getFormKey(form) {
	return (
		form.dataset.analyticsAssetId ||
		form.dataset.analyticsFormId ||
		form.id ||
		form.getAttribute('name') ||
		form.action
	);
}

/**
 * Returns analytics payload with field information.
 * @param {Object} form The field DOM element
 * @returns {Object} The payload with field information
 */
function getFieldPayload({form, name}) {
	return {
		fieldName: name,
		formId: getFormKey(form),
	};
}

/**
 * Returns analytics payload with form information.
 * @param {Object} form The form DOM element
 * @returns {Object} The payload with form information
 */
function getFormPayload(form) {
	const payload = {
		formId: getFormKey(form),
	};

	if (form.dataset.analyticsAssetTitle) {
		Object.assign(payload, {title: form.dataset.analyticsAssetTitle});
	}

	return payload;
}

/**
 * Wether a form is trackable or not.
 * @param {Object} form The form DOM element
 * @returns {boolean} True if the form is trackable.
 */
function isTrackableForm(form) {
	return (
		('analyticsAssetId' in form.dataset ||
			'analyticsFormId' in form.dataset) &&
		!!getFormKey(form)
	);
}

/**
 * Adds an event listener for the blur event and sends analytics information
 * when that event happens.
 * @param {Object} The Analytics client instance
 */
function trackFieldBlurred(analytics) {
	const onBlur = ({target}) => {
		const {form} = target;

		if (!form || !isTrackableForm(form)) {
			return;
		}

		const payload = getFieldPayload(target);

		const blurMark = `${payload.formId}${payload.fieldName}blurred`;
		performance.mark(blurMark);

		const focusMark = `${payload.formId}${payload.fieldName}focused`;
		performance.measure('focusDuration', focusMark, blurMark);

		const perfData = performance.getEntriesByName('focusDuration').pop();

		const focusDuration = ~~perfData.duration;

		Object.assign(payload, {focusDuration});

		analytics.send('fieldBlurred', applicationId, payload);

		performance.clearMarks('focusDuration');
	};

	document.addEventListener('blur', onBlur, true);

	return () => document.removeEventListener('blur', onBlur, true);
}

/**
 * Adds an event listener for the focus event and sends analytics information
 * when that event happens.
 * @param {Object} The Analytics client instance
 */
function trackFieldFocused(analytics) {
	const onFocus = ({target}) => {
		const {form} = target;

		if (!form || !isTrackableForm(form)) {
			return;
		}

		const payload = getFieldPayload(target);

		const focusMark = `${payload.formId}${payload.fieldName}focused`;
		performance.mark(focusMark);

		analytics.send('fieldFocused', applicationId, payload);
	};

	document.addEventListener('focus', onFocus, true);

	return () => document.removeEventListener('focus', onFocus, true);
}

/**
 * Adds an event listener for a form submission and sends information when that
 * event happens.
 * @param {Object} The Analytics client instance
 */
function trackFormSubmitted(analytics) {
	const onSubmit = (event) => {
		const {target} = event;

		if (
			!isTrackableForm(target) ||
			(isTrackableForm(target) && event.defaultPrevented)
		) {
			return;
		}

		analytics.send('formSubmitted', applicationId, getFormPayload(target));
	};

	document.addEventListener('submit', onSubmit, true);

	return () => document.removeEventListener('submit', onSubmit, true);
}

/**
 * Sends information about forms rendered on the page when it was loaded.
 * @param {Object} The Analytics client instance
 */
function trackFormViewed(analytics) {
	return onReady(() => {
		Array.prototype.slice
			.call(document.querySelectorAll('form'))
			.filter((form) => isTrackableForm(form))
			.forEach((form) => {
				const payload = getFormPayload(form);

				analytics.send('formViewed', applicationId, payload);
			});
	});
}

/**
 * Plugin function that registers listener against form events
 * @param {Object} analytics The Analytics client
 */
function forms(analytics) {
	const stopTrackingFieldBlurred = trackFieldBlurred(analytics);
	const stopTrackingFieldFocused = trackFieldFocused(analytics);
	const stopTrackingFormSubmitted = trackFormSubmitted(analytics);
	const stopTrackingFormViewed = trackFormViewed(analytics);

	return () => {
		stopTrackingFieldBlurred();
		stopTrackingFieldFocused();
		stopTrackingFormSubmitted();
		stopTrackingFormViewed();
	};
}

export {forms};
export default forms;
