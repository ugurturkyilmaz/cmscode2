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

package com.liferay.depot.internal.model.listener;

import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.service.DepotEntryGroupRelLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.GroupLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = ModelListener.class)
public class DepotEntryModelListener extends BaseModelListener<DepotEntry> {

	@Override
	public void onAfterRemove(DepotEntry depotEntry)
		throws ModelListenerException {

		try {
			for (DepotEntryGroupRel depotEntryGroupRel :
					_depotEntryGroupRelLocalService.getDepotEntryGroupRels(
						depotEntry)) {

				_depotEntryGroupRelLocalService.deleteDepotEntryGroupRel(
					depotEntryGroupRel);
			}

			Group group = _groupLocalService.fetchGroup(
				depotEntry.getGroupId());

			if (group != null) {
				_groupLocalService.deleteGroup(group);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private DepotEntryGroupRelLocalService _depotEntryGroupRelLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

}