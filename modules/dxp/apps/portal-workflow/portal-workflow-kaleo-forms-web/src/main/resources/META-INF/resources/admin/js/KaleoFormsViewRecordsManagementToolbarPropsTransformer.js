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

import {postForm} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {deleteDDLRecordURL},
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteRecords') {
				if (
					confirm(
						Liferay.Language.get(
							'are-you-sure-you-want-to-delete-this'
						)
					)
				) {
					const form = document.getElementById(
						`${portletNamespace}searchContainerForm`
					);

					const searchContainer = document.getElementById(
						otherProps.searchContainerId
					);

					if (form && searchContainer) {
						postForm(form, {
							data: {
								ddlRecordIds: Liferay.Util.listCheckedExcept(
									searchContainer,
									`${portletNamespace}allRowIds`
								),
							},
							url: deleteDDLRecordURL,
						});
					}
				}
			}
		},
	};
}
