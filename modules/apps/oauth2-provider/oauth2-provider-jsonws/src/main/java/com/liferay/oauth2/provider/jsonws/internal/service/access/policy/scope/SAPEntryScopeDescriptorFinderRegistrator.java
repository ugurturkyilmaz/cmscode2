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

package com.liferay.oauth2.provider.jsonws.internal.service.access.policy.scope;

import com.liferay.oauth2.provider.jsonws.internal.configuration.OAuth2JSONWSConfiguration;
import com.liferay.oauth2.provider.jsonws.internal.constants.OAuth2JSONWSConstants;
import com.liferay.oauth2.provider.scope.spi.scope.descriptor.ScopeDescriptor;
import com.liferay.oauth2.provider.scope.spi.scope.finder.ScopeFinder;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.oauth2.provider.jsonws.internal.configuration.OAuth2JSONWSConfiguration",
	immediate = true, service = SAPEntryScopeDescriptorFinderRegistrator.class
)
public class SAPEntryScopeDescriptorFinderRegistrator {

	public List<SAPEntryScope> getRegisteredSAPEntryScopes(long companyId) {
		return new ArrayList<>(_registeredSAPEntryScopes.get(companyId));
	}

	public void register(long companyId) {
		try {
			List<SAPEntryScope> sapEntryScopes = _loadSAPEntryScopes(companyId);

			SAPEntryScopeDescriptorFinder sapEntryScopeDescriptorFinder =
				new SAPEntryScopeDescriptorFinder(
					sapEntryScopes, _defaultScopeDescriptor);

			_scopeDescriptorServiceRegistrations.compute(
				companyId,
				(key, serviceRegistration) -> {
					if (serviceRegistration != null) {
						serviceRegistration.unregister();
					}

					return _bundleContext.registerService(
						ScopeDescriptor.class, sapEntryScopeDescriptorFinder,
						_buildScopeDescriptorProperties(companyId));
				});

			Dictionary<String, Object> properties =
				HashMapDictionaryBuilder.<String, Object>put(
					"companyId", String.valueOf(companyId)
				).put(
					"osgi.jaxrs.name", OAuth2JSONWSConstants.APPLICATION_NAME
				).put(
					"sap.scope.finder", Boolean.TRUE
				).build();

			_scopeFinderServiceRegistrations.compute(
				companyId,
				(key, serviceRegistration) -> {
					if (serviceRegistration != null) {
						serviceRegistration.unregister();
					}

					serviceRegistration = _bundleContext.registerService(
						ScopeFinder.class, sapEntryScopeDescriptorFinder,
						properties);

					_registeredSAPEntryScopes.put(companyId, sapEntryScopes);

					return serviceRegistration;
				});
		}
		catch (Exception exception) {
			_log.error(
				"Unable to register SAP entry scope descriptor finder for " +
					"company " + companyId,
				exception);
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_bundleContext = bundleContext;

		OAuth2JSONWSConfiguration oAuth2JSONWSConfiguration =
			ConfigurableUtil.createConfigurable(
				OAuth2JSONWSConfiguration.class, properties);

		_removeSAPEntryOAuth2Prefix =
			oAuth2JSONWSConfiguration.removeSAPEntryOAuth2Prefix();

		_sapEntryOAuth2Prefix =
			oAuth2JSONWSConfiguration.sapEntryOAuth2Prefix();

		_companyLocalService.forEachCompanyId(
			companyId -> register(companyId),
			ArrayUtil.toLongArray(_scopeFinderServiceRegistrations.keySet()));
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(&(osgi.jaxrs.name=*)(sap.scope.finder=true))"
	)
	protected void addJaxRsApplicationName(
		ServiceReference<ScopeFinder> serviceReference) {

		_jaxRsApplicationNames.add(
			GetterUtil.getString(
				serviceReference.getProperty("osgi.jaxrs.name")));

		for (Map.Entry<Long, ServiceRegistration<ScopeDescriptor>> entry :
				_scopeDescriptorServiceRegistrations.entrySet()) {

			ServiceRegistration<ScopeDescriptor> serviceRegistration =
				entry.getValue();

			try {
				serviceRegistration.setProperties(
					_buildScopeDescriptorProperties(entry.getKey()));
			}
			catch (IllegalStateException illegalStateException) {
				if (_log.isDebugEnabled()) {
					_log.debug(illegalStateException);
				}

				// Concurrent unregistration from register(long)

			}
		}
	}

	@Deactivate
	protected void deactivate() {
		for (ServiceRegistration<ScopeFinder> serviceRegistration :
				_scopeFinderServiceRegistrations.values()) {

			serviceRegistration.unregister();
		}

		_scopeFinderServiceRegistrations.clear();

		for (ServiceRegistration<ScopeDescriptor> serviceRegistration :
				_scopeDescriptorServiceRegistrations.values()) {

			serviceRegistration.unregister();
		}

		_scopeDescriptorServiceRegistrations.clear();
	}

	protected void removeJaxRsApplicationName(
		ServiceReference<ScopeFinder> serviceReference) {

		_jaxRsApplicationNames.remove(
			GetterUtil.getString(
				serviceReference.getProperty("osgi.jaxrs.name")));

		for (Map.Entry<Long, ServiceRegistration<ScopeDescriptor>> entry :
				_scopeDescriptorServiceRegistrations.entrySet()) {

			ServiceRegistration<ScopeDescriptor> serviceRegistration =
				entry.getValue();

			try {
				serviceRegistration.setProperties(
					_buildScopeDescriptorProperties(entry.getKey()));
			}
			catch (IllegalStateException illegalStateException) {
				if (_log.isDebugEnabled()) {
					_log.debug(illegalStateException);
				}

				// Concurrent unregistration from register(long)

			}
		}
	}

	private HashMapDictionary<String, Object> _buildScopeDescriptorProperties(
		long companyId) {

		return HashMapDictionaryBuilder.<String, Object>put(
			"companyId", String.valueOf(companyId)
		).put(
			"osgi.jaxrs.name", _jaxRsApplicationNames.toArray(new String[0])
		).build();
	}

	private boolean _isOAuth2ExportedSAPEntry(SAPEntry sapEntry) {
		return StringUtil.startsWith(sapEntry.getName(), _sapEntryOAuth2Prefix);
	}

	private List<SAPEntryScope> _loadSAPEntryScopes(long companyId) {
		List<SAPEntryScope> sapEntryScopes = new ArrayList<>();

		for (SAPEntry sapEntry :
				_sapEntryLocalService.getCompanySAPEntries(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			if (_isOAuth2ExportedSAPEntry(sapEntry)) {
				sapEntryScopes.add(
					new SAPEntryScope(sapEntry, _parseScope(sapEntry)));
			}
		}

		return sapEntryScopes;
	}

	private String _parseScope(SAPEntry sapEntry) {
		String sapEntryName = sapEntry.getName();

		if (!_removeSAPEntryOAuth2Prefix) {
			return sapEntryName;
		}

		return sapEntryName.substring(_sapEntryOAuth2Prefix.length());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SAPEntryScopeDescriptorFinderRegistrator.class);

	private BundleContext _bundleContext;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(target = "(default=true)")
	private ScopeDescriptor _defaultScopeDescriptor;

	private final Set<String> _jaxRsApplicationNames =
		Collections.newSetFromMap(new ConcurrentHashMap<>());
	private final Map<Long, List<SAPEntryScope>> _registeredSAPEntryScopes =
		new ConcurrentHashMap<>();
	private boolean _removeSAPEntryOAuth2Prefix = true;

	@Reference
	private SAPEntryLocalService _sapEntryLocalService;

	private String _sapEntryOAuth2Prefix = "OAUTH2_";
	private final Map<Long, ServiceRegistration<ScopeDescriptor>>
		_scopeDescriptorServiceRegistrations = new ConcurrentHashMap<>();
	private final Map<Long, ServiceRegistration<ScopeFinder>>
		_scopeFinderServiceRegistrations = new ConcurrentHashMap<>();

}