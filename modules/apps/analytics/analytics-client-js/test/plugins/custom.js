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

import userEvent from '@testing-library/user-event';
import fetchMock from 'fetch-mock';

import AnalyticsClient from '../../src/analytics';

const applicationId = 'Custom';

const googleUrl = 'http://google.com/';

const createCustomAssetElement = () => {
	const customAssetElement = document.createElement('div');

	customAssetElement.dataset.analyticsAssetCategory = 'custom-asset-category';
	customAssetElement.dataset.analyticsAssetId = 'assetId';
	customAssetElement.dataset.analyticsAssetTitle = 'Custom Asset Title 1';
	customAssetElement.dataset.analyticsAssetType = 'custom';
	customAssetElement.innerText =
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit.';

	document.body.appendChild(customAssetElement);

	return customAssetElement;
};

const createCustomAssetElementWithForm = () => {
	const customAssetElement = document.createElement('div');

	customAssetElement.dataset.analyticsAssetCategory = 'custom-asset-category';
	customAssetElement.dataset.analyticsAssetId = 'assetId';
	customAssetElement.dataset.analyticsAssetTitle = 'Custom Asset Title 1';
	customAssetElement.dataset.analyticsAssetType = 'custom';

	setInnerHTML(
		customAssetElement,
		`
		<form><input type="text" /><button type="submit" /></form>
	`
	);

	document.body.appendChild(customAssetElement);

	return customAssetElement;
};

describe('Custom Asset Plugin', () => {
	let Analytics;

	beforeEach(() => {

		// Force attaching DOM Content Loaded event

		Object.defineProperty(document, 'readyState', {
			value: 'loading',
			writable: false,
		});

		fetchMock.mock('*', () => 200);

		Analytics = AnalyticsClient.create();
	});

	afterEach(() => {
		Analytics.reset();
		Analytics.dispose();

		fetchMock.restore();
	});

	describe('assetViewed event', () => {
		it('is fired for every custom asset on the page', async () => {
			const customAssetElement = createCustomAssetElement();

			const domContentLoaded = new Event('DOMContentLoaded');

			await document.dispatchEvent(domContentLoaded);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'assetViewed'
			);

			expect(events.length).toBeGreaterThanOrEqual(1);

			expect(events[0]).toEqual(
				expect.objectContaining({
					applicationId,
					eventId: 'assetViewed',
					properties: expect.objectContaining({
						assetId: 'assetId',
					}),
				})
			);

			document.body.removeChild(customAssetElement);
		});

		it('is fired with formEnabled if there is form element every custom asset on the page', async () => {
			const customAssetElement = createCustomAssetElementWithForm();

			const domContentLoaded = new Event('DOMContentLoaded');

			await document.dispatchEvent(domContentLoaded);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'assetViewed'
			);

			expect(events.length).toBeGreaterThanOrEqual(1);

			expect(events[0]).toEqual(
				expect.objectContaining({
					applicationId,
					eventId: 'assetViewed',
					properties: expect.objectContaining({
						assetId: 'assetId',
						formEnabled: true,
					}),
				})
			);

			document.body.removeChild(customAssetElement);
		});
	});

	describe('assetClicked event', () => {
		it('is fired when clicking an image inside a custom asset', async () => {
			const customAssetElement = createCustomAssetElement();

			const imageInsideCustomAsset = document.createElement('img');

			imageInsideCustomAsset.src = googleUrl;

			customAssetElement.appendChild(imageInsideCustomAsset);

			await userEvent.click(imageInsideCustomAsset);

			expect(Analytics.getEvents()).toEqual([
				expect.objectContaining({
					applicationId,
					eventId: 'assetClicked',
					properties: expect.objectContaining({
						assetId: 'assetId',
						src: googleUrl,
						tagName: 'img',
					}),
				}),
			]);

			document.body.removeChild(customAssetElement);
		});

		it('is fired when clicking a link inside a custom asset', async () => {
			const customAssetElement = createCustomAssetElement();

			const text = 'Link inside a Custom Asset';

			const linkInsideCustomAsset = document.createElement('a');

			linkInsideCustomAsset.href = googleUrl;

			setInnerHTML(linkInsideCustomAsset, text);

			customAssetElement.appendChild(linkInsideCustomAsset);

			await userEvent.click(linkInsideCustomAsset);

			expect(Analytics.getEvents()).toEqual([
				expect.objectContaining({
					applicationId,
					eventId: 'assetClicked',
					properties: expect.objectContaining({
						assetId: 'assetId',
						href: googleUrl,
						tagName: 'a',
						text,
					}),
				}),
			]);

			document.body.removeChild(customAssetElement);
		});

		it('is fired when clicking any other element inside a custom asset', async () => {
			const customAssetElement = createCustomAssetElement();

			const paragraphInsideCustomAsset = document.createElement('p');

			paragraphInsideCustomAsset.href = googleUrl;

			setInnerHTML(
				paragraphInsideCustomAsset,
				'Paragraph inside a Custom Asset'
			);

			customAssetElement.appendChild(paragraphInsideCustomAsset);

			await userEvent.click(paragraphInsideCustomAsset);

			expect(Analytics.getEvents()).toEqual([
				expect.objectContaining({
					applicationId,
					eventId: 'assetClicked',
					properties: expect.objectContaining({
						assetId: 'assetId',
						tagName: 'p',
					}),
				}),
			]);

			document.body.removeChild(customAssetElement);
		});
	});

	describe('assetDownloaded', () => {
		it('is fired when clicking a link inside a custom asset', async () => {
			const customAssetElement = createCustomAssetElement();

			const text = 'Link inside a Custom Asset';

			const linkInsideCustomAsset = document.createElement('a');

			linkInsideCustomAsset.href = '#';

			setInnerHTML(linkInsideCustomAsset, text);

			linkInsideCustomAsset.setAttribute(
				'data-analytics-asset-action',
				'download'
			);

			customAssetElement.appendChild(linkInsideCustomAsset);

			await userEvent.click(linkInsideCustomAsset);

			expect(Analytics.getEvents().length).toEqual(2);

			expect(Analytics.getEvents()[1]).toEqual(
				expect.objectContaining({
					applicationId,
					eventId: 'assetDownloaded',
				})
			);

			document.body.removeChild(customAssetElement);
		});
	});
});
