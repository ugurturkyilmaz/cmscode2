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

package com.liferay.portal.instance.lifecycle.internal;

import com.liferay.portal.instance.lifecycle.Clusterable;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.instance.lifecycle.PortalInstanceLifecycleManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.LocaleThreadLocal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = PortalInstanceLifecycleManager.class)
public class PortalInstanceLifecycleListenerManagerImpl
	implements PortalInstanceLifecycleManager {

	@Override
	public void preunregisterCompany(Company company) {
		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			preunregisterCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Override
	public void registerCompany(Company company) {
		_companies.add(company);

		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			registerCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Override
	public void unregisterCompany(Company company) {
		_companies.remove(company);

		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			unregisterCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addPortalInstanceLifecycleListener(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener) {

		_portalInstanceLifecycleListeners.add(portalInstanceLifecycleListener);

		if (_companies.isEmpty()) {
			return;
		}

		Iterator<Company> iterator = _companies.iterator();

		while (iterator.hasNext()) {
			Company company = iterator.next();

			if (_companyLocalService.fetchCompanyById(company.getCompanyId()) ==
					null) {

				unregisterCompany(company);
			}
		}

		_companyLocalService.forEachCompany(
			company -> registerCompany(
				portalInstanceLifecycleListener, company),
			new ArrayList<Company>(_companies));
	}

	protected void preunregisterCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		try {
			portalInstanceLifecycleListener.portalInstancePreunregistered(
				company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to preunregister portal instance " + company,
					exception);
			}
		}
	}

	protected void registerCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		Long companyId = CompanyThreadLocal.getCompanyId();
		Locale siteDefaultLocale = LocaleThreadLocal.getSiteDefaultLocale();

		try {
			CompanyThreadLocal.setCompanyId(company.getCompanyId());
			LocaleThreadLocal.setSiteDefaultLocale(null);

			portalInstanceLifecycleListener.portalInstanceRegistered(company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to register portal instance " + company, exception);
			}
		}
		finally {
			CompanyThreadLocal.setCompanyId(companyId);
			LocaleThreadLocal.setSiteDefaultLocale(siteDefaultLocale);
		}
	}

	protected void removePortalInstanceLifecycleListener(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		_portalInstanceLifecycleListeners.remove(
			portalInstanceLifecycleListener);
	}

	protected void unregisterCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		try {
			portalInstanceLifecycleListener.portalInstanceUnregistered(company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to unregister portal instance " + company,
					exception);
			}
		}
	}

	@Reference
	protected ClusterMasterExecutor clusterMasterExecutor;

	private static final Log _log = LogFactoryUtil.getLog(
		PortalInstanceLifecycleListenerManagerImpl.class);

	private final Set<Company> _companies = new CopyOnWriteArraySet<>();

	@Reference
	private CompanyLocalService _companyLocalService;

	private final Set<PortalInstanceLifecycleListener>
		_portalInstanceLifecycleListeners = new CopyOnWriteArraySet<>();

}