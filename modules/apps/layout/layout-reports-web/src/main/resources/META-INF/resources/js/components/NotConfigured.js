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

import ClayLink from '@clayui/link';
import React, {useContext} from 'react';

import {StoreStateContext} from '../context/StoreContext';

export default function NotConfigured() {
	const {data} = useContext(StoreStateContext);

	const {configureGooglePageSpeedURL, imagesPath} = data;

	const defaultIllustration = `${imagesPath}/issues_configure.svg`;

	return (
		<div className="pb-3 px-3 text-center">
			<img
				alt={Liferay.Language.get(
					'default-page-audit-image-alt-description'
				)}
				className="my-4"
				src={defaultIllustration}
				width="120px"
			/>

			<p className="font-weight-semi-bold mb-2">
				{Liferay.Language.get(
					"check-issues-that-impact-on-your-page's-accessibility-and-seo"
				)}
			</p>

			{configureGooglePageSpeedURL ? (
				<>
					<p className="mb-3 text-secondary">
						{Liferay.Language.get(
							'configure-google-pagespeed-to-run-a-page-audit'
						)}
					</p>

					<ClayLink
						className="btn btn-secondary"
						href={configureGooglePageSpeedURL}
					>
						{Liferay.Language.get('configure')}
					</ClayLink>
				</>
			) : (
				<p className="text-secondary">
					{Liferay.Language.get(
						'connect-with-google-pagespeed-from-site-settings-pages-google-pagespeed'
					)}
				</p>
			)}
		</div>
	);
}
