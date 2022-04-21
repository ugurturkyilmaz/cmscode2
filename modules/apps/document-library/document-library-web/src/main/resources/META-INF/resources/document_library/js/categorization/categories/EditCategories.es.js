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

import {useModal} from '@clayui/modal';
import React, {useContext, useEffect, useState} from 'react';

import EditCategoriesContext from './EditCategoriesContext.es';
import EditCategoriesModal from './EditCategoriesModal.es';

function EditCategories(props) {
	const [fileEntries, setFileEntries] = useState();
	const [selectAll, setSelectAll] = useState();
	const [folderId, setFolderId] = useState();
	const [showModal, setShowModal] = useState();
	const {namespace} = useContext(EditCategoriesContext);

	const handleOnClose = () => {
		setShowModal(false);
	};

	const {observer, onClose} = useModal({
		onClose: handleOnClose,
	});

	useEffect(() => {
		const bridgeComponentId = `${namespace}EditCategoriesComponent`;

		if (!Liferay.component(bridgeComponentId)) {
			Liferay.component(
				bridgeComponentId,
				{
					open: (fileEntries, selectAll, folderId) => {
						setFileEntries(fileEntries);
						setSelectAll(selectAll);
						setFolderId(folderId);
						setShowModal(true);
					},
				},
				{
					destroyOnNavigate: true,
				}
			);
		}

		return () => {
			Liferay.destroyComponent(bridgeComponentId);
		};
	}, [namespace]);

	return (
		<>
			{showModal && (
				<EditCategoriesModal
					{...props}
					fileEntries={fileEntries}
					folderId={folderId}
					observer={observer}
					onModalClose={onClose}
					selectAll={selectAll}
				/>
			)}
		</>
	);
}

export default function ({context, props}) {
	return (
		<EditCategoriesContext.Provider value={context}>
			<EditCategories {...props} />
		</EditCategoriesContext.Provider>
	);
}
