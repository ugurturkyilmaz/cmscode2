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

import '@testing-library/jest-dom/extend-expect';
import {render, screen} from '@testing-library/react';
import React from 'react';

import FragmentCollectionPreview from '../../src/main/resources/META-INF/resources/js/fragment-collection-preview/FragmentCollectionPreview';

const SAMPLE_CONFIGURATION = {
	fieldSets: [
		{
			fields: [
				{
					label: 'Color',
					name: 'color',
					type: 'select',
					typeOptions: {
						validValues: [{value: 'red'}, {value: 'blue'}],
					},
				},
				{
					label: 'Size',
					name: 'size',
					type: 'select',
					typeOptions: {
						validValues: [{value: 'small'}],
					},
				},
				{
					label: 'Beautiful',
					name: 'beautiful',
					type: 'select',
					typeOptions: {
						validValues: [
							{value: 'yes'},
							{value: 'no'},
							{label: 'A lot', value: 'a-lot'},
						],
					},
				},
				{
					label: 'Unexisting',
					name: 'unexisting',
					type: 'select',
					typeOptions: {
						validValues: [],
					},
				},
				{
					label: 'Content',
					name: 'content',
					type: 'text',
				},
			],
		},
	],
};

describe('FragmentCollectionPreview', () => {
	beforeEach(() => {
		global.fetch.mockReturnValue(
			Promise.resolve({
				text: jest.fn(() => '<div>Sample</div>'),
			})
		);
	});

	it('filters fragments that appear in the blocklist', () => {
		render(
			<FragmentCollectionPreview
				fragmentCollectionKey="BASIC_COMPONENT"
				fragments={[
					{
						configuration: SAMPLE_CONFIGURATION,
						fragmentEntryKey: 'sample-fragment',
						label: 'Sample Fragment',
					},
					{
						configuration: SAMPLE_CONFIGURATION,
						fragmentEntryKey: 'BASIC_COMPONENT-html',
						label: 'HTML',
					},
				]}
				namespace=""
			/>
		);

		expect(
			screen.getByText('Sample Fragment red small yes')
		).toBeInTheDocument();
		expect(
			screen.queryByText('HTML red small yes')
		).not.toBeInTheDocument();
	});

	it('shows a warning if there are no available fragments', () => {
		render(
			<FragmentCollectionPreview
				fragmentCollectionKey=""
				fragments={[]}
				namespace=""
			/>
		);

		expect(screen.getByText('there-are-no-fragments')).toBeInTheDocument();
	});

	it('shows combinations of all select configuration fields', () => {
		render(
			<FragmentCollectionPreview
				fragmentCollectionKey="BASIC_COMPONENT"
				fragments={[
					{
						configuration: SAMPLE_CONFIGURATION,
						fragmentEntryKey: 'sample-fragment',
						label: 'Sample Fragment',
					},
				]}
				namespace=""
			/>
		);

		expect(
			screen.getByText('Sample Fragment red small yes')
		).toBeInTheDocument();
		expect(
			screen.getByText('Sample Fragment blue small yes')
		).toBeInTheDocument();
		expect(
			screen.getByText('Sample Fragment red small no')
		).toBeInTheDocument();
		expect(
			screen.getByText('Sample Fragment blue small no')
		).toBeInTheDocument();
		expect(
			screen.getByText('Sample Fragment red small A lot')
		).toBeInTheDocument();
		expect(
			screen.getByText('Sample Fragment blue small A lot')
		).toBeInTheDocument();
	});

	it('ignores fields that are not select with validValues', () => {
		render(
			<FragmentCollectionPreview
				fragmentCollectionKey="BASIC_COMPONENT"
				fragments={[
					{
						configuration: SAMPLE_CONFIGURATION,
						fragmentEntryKey: 'sample-fragment',
						label: 'Sample Fragment',
					},
				]}
				namespace=""
			/>
		);

		expect(screen.queryByText('Unexisting')).not.toBeInTheDocument();
		expect(screen.queryByText('Content')).not.toBeInTheDocument();
	});

	it('hides variation label if there is only one', () => {
		render(
			<FragmentCollectionPreview
				fragmentCollectionKey="BASIC_COMPONENT"
				fragments={[
					{
						configuration: {
							fieldSets: [
								{
									fields: [
										{
											label: 'Size',
											name: 'size',
											type: 'select',
											typeOptions: {
												validValues: [{value: 'small'}],
											},
										},
									],
								},
							],
						},
						fragmentEntryKey: 'sample-fragment',
						label: 'Sample Fragment',
					},
				]}
				namespace=""
			/>
		);

		const variationTitle = screen.getByText('Sample Fragment small');

		expect(variationTitle.classList.contains('sr-only')).toBe(true);
	});
});
