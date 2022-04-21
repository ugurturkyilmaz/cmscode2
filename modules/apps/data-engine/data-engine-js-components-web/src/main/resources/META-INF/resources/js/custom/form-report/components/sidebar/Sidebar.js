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

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import React, {useContext} from 'react';

import useRequest from '../../hooks/useRequest';
import List from '../list/List';
import Summary from '../summary/Summary';
import {SidebarContext} from './SidebarContext';

import './Sidebar.scss';

const SidebarContent = () => {
	const {
		field,
		formReportRecordsFieldValuesURL,
		portletNamespace,
		summary = {},
		toggleSidebar,
		totalEntries,
		type,
	} = useContext(SidebarContext);

	const {origin} = new URL(formReportRecordsFieldValuesURL);
	const path = formReportRecordsFieldValuesURL.replace(origin, '');

	const endpoint = `${path}&${portletNamespace}fieldName=${field.name}`;

	const {isLoading, response: data = []} = useRequest(endpoint);

	const {icon, label} = field;

	return (
		<>
			<nav className="component-tbar tbar">
				<ClayLayout.ContainerFluid>
					<ul className="tbar-nav">
						<li className="tbar-item">
							<div className="icon">
								<ClayIcon symbol={icon} />
							</div>
						</li>

						<li className="tbar-item tbar-item-expand">
							<div className="tbar-section">
								<div className="field-info">
									<p className="title">{label}</p>

									<p className="description">
										{totalEntries + ' '}

										{Liferay.Language.get(
											'entries'
										).toLowerCase()}
									</p>
								</div>
							</div>
						</li>

						<li className="tbar-item">
							<ClayButton
								className="close-sidebar"
								displayType="secondary"
								monospaced
								onClick={() => toggleSidebar()}
							>
								<ClayIcon
									className="close-button"
									symbol="times-small"
								/>
							</ClayButton>
						</li>
					</ul>
				</ClayLayout.ContainerFluid>
			</nav>

			<div className="sidebar-body">
				{isLoading && (
					<div className="align-items-center d-flex loading-wrapper">
						<ClayLoadingIndicator />
					</div>
				)}

				{!!Object.entries(summary).length && (
					<Summary summary={summary} />
				)}

				<List data={data} type={type}></List>
			</div>
		</>
	);
};

export default function Sidebar() {
	const {field, isOpen, portletNamespace} = useContext(SidebarContext);

	return (
		<>
			{isOpen && (
				<div className="lfr-de__form-report-sidebar-backdrop"></div>
			)}
			<div
				className={
					'sidebar-container lfr-de__form-report-sidebar' +
					(isOpen ? ' open' : '')
				}
				id={`${portletNamespace}sidebar-reports`}
			>
				<div className="sidebar sidebar-light">
					{field && <SidebarContent />}
				</div>
			</div>
		</>
	);
}
