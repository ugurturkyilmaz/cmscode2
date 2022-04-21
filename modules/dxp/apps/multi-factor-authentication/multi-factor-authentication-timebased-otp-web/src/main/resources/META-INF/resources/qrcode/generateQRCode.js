/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import QRCode from 'qrcode';

export default function generateQRCode(
	containerId,
	{account, algorithm, counter, digits, issuer, secret}
) {
	const url = new URL('otpauth://totp/' + encodeURIComponent(account));

	const params = {
		algorithm,
		counter,
		digits,
		issuer,
		secret,
	};

	url.search = Object.entries(params)
		.map(([key, value]) => `${key}=${encodeURIComponent(value)}`)
		.join('&');

	QRCode.toDataURL(url.toString())
		.then((dataUrl) => {
			const image = document.createElement('img');

			image.setAttribute('src', dataUrl);

			const container = document.getElementById(containerId);

			container.appendChild(image);
		})
		.catch((error) => {
			console.error(error);
		});
}
