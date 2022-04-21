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

package com.liferay.view.count.service.impl;

import com.liferay.petra.sql.dsl.Table;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.increment.NumberIncrement;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.SQLStateAcceptor;
import com.liferay.portal.kernel.spring.aop.Property;
import com.liferay.portal.kernel.spring.aop.Retry;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.view.count.ViewCountManager;
import com.liferay.view.count.configuration.ViewCountConfiguration;
import com.liferay.view.count.model.ViewCountEntry;
import com.liferay.view.count.model.ViewCountEntryTable;
import com.liferay.view.count.service.ViewCountEntryLocalService;
import com.liferay.view.count.service.base.ViewCountEntryLocalServiceBaseImpl;
import com.liferay.view.count.service.persistence.ViewCountEntryPK;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	configurationPid = "com.liferay.view.count.configuration.ViewCountConfiguration",
	property = "model.class.name=com.liferay.view.count.model.ViewCountEntry",
	service = AopService.class
)
@CTAware
public class ViewCountEntryLocalServiceImpl
	extends ViewCountEntryLocalServiceBaseImpl implements ViewCountManager {

	@Override
	public void deleteViewCount(
		long companyId, long classNameId, long classPK) {

		ViewCountEntryPK viewCountEntryPK = new ViewCountEntryPK(
			companyId, classNameId, classPK);

		ViewCountEntry viewCountEntry =
			viewCountEntryPersistence.fetchByPrimaryKey(viewCountEntryPK);

		if (viewCountEntry != null) {
			viewCountEntryPersistence.remove(viewCountEntry);
		}
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			IdentifiableOSGiService.class, PersistedModelLocalService.class,
			ViewCountEntryLocalService.class, ViewCountManager.class
		};
	}

	@Override
	public long getViewCount(long companyId, long classNameId, long classPK) {
		ViewCountEntry viewCountEntry = null;

		if (isViewCountEnabled(classNameId)) {
			viewCountEntry = viewCountEntryPersistence.fetchByPrimaryKey(
				new ViewCountEntryPK(companyId, classNameId, classPK));
		}

		if (viewCountEntry == null) {
			return 0;
		}

		return viewCountEntry.getViewCount();
	}

	@Override
	public Table<?> getViewCountEntryTable() {
		return ViewCountEntryTable.INSTANCE;
	}

	@BufferedIncrement(incrementClass = NumberIncrement.class)
	@Override
	@Retry(
		acceptor = SQLStateAcceptor.class,
		properties = {
			@Property(
				name = SQLStateAcceptor.SQLSTATE,
				value = SQLStateAcceptor.SQLSTATE_INTEGRITY_CONSTRAINT_VIOLATION + "," + SQLStateAcceptor.SQLSTATE_TRANSACTION_ROLLBACK
			)
		}
	)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void incrementViewCount(
		long companyId, long classNameId, long classPK, int increment) {

		if (isViewCountEnabled(classNameId)) {
			viewCountEntryFinder.incrementViewCount(
				companyId, classNameId, classPK, increment);
		}
	}

	@Override
	@Transactional(enabled = false)
	public boolean isViewCountEnabled() {
		return _enabled;
	}

	@Override
	@Transactional(enabled = false)
	public boolean isViewCountEnabled(long classNameId) {
		if (_disabledClassNameIds.contains(classNameId)) {
			return false;
		}

		return isViewCountEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		ViewCountConfiguration viewCountConfiguration =
			ConfigurableUtil.createConfigurable(
				ViewCountConfiguration.class, properties);

		Set<Long> disabledClassNameIds = new HashSet<>();

		for (String className : viewCountConfiguration.disabledClassNames()) {
			if (Validator.isNotNull(className)) {
				disabledClassNameIds.add(
					_classNameLocalService.getClassNameId(className));
			}
		}

		_disabledClassNameIds = disabledClassNameIds;
		_enabled = viewCountConfiguration.enabled();
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private volatile Set<Long> _disabledClassNameIds;
	private volatile boolean _enabled;

}