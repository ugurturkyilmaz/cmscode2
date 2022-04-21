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

package com.liferay.oauth2.provider.web.internal.display.context;

import com.liferay.document.library.util.DLURLHelper;
import com.liferay.oauth2.provider.configuration.OAuth2ProviderConfiguration;
import com.liferay.oauth2.provider.model.OAuth2Application;
import com.liferay.oauth2.provider.model.OAuth2ScopeGrant;
import com.liferay.oauth2.provider.scope.liferay.LiferayOAuth2Scope;
import com.liferay.oauth2.provider.scope.liferay.ScopeLocator;
import com.liferay.oauth2.provider.scope.liferay.spi.ScopeDescriptorLocator;
import com.liferay.oauth2.provider.scope.spi.scope.descriptor.ScopeDescriptor;
import com.liferay.oauth2.provider.scope.spi.scope.matcher.ScopeMatcherFactory;
import com.liferay.oauth2.provider.service.OAuth2ApplicationScopeAliasesLocalService;
import com.liferay.oauth2.provider.service.OAuth2ApplicationService;
import com.liferay.oauth2.provider.service.OAuth2ScopeGrantLocalService;
import com.liferay.oauth2.provider.web.internal.tree.Tree;
import com.liferay.oauth2.provider.web.internal.util.ScopeTreeUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;

/**
 * @author Marta Medio
 */
public class AssignScopesTreeDisplayContext
	extends OAuth2AdminPortletDisplayContext {

	public AssignScopesTreeDisplayContext(
			DLURLHelper dlURLHelper,
			OAuth2ApplicationScopeAliasesLocalService
				oAuth2ApplicationScopeAliasesLocalService,
			OAuth2ApplicationService oAuth2ApplicationService,
			OAuth2ProviderConfiguration oAuth2ProviderConfiguration,
			OAuth2ScopeGrantLocalService oAuth2ScopeGrantLocalService,
			PortletRequest portletRequest,
			ScopeDescriptorLocator scopeDescriptorLocator,
			ScopeLocator scopeLocator, ScopeMatcherFactory scopeMatcherFactory,
			ThemeDisplay themeDisplay)
		throws PortalException {

		super(
			dlURLHelper, oAuth2ApplicationScopeAliasesLocalService,
			oAuth2ApplicationService, oAuth2ProviderConfiguration,
			portletRequest, themeDisplay);

		_scopeDescriptorLocator = scopeDescriptorLocator;
		_scopeLocator = scopeLocator;

		OAuth2Application oAuth2Application = getOAuth2Application();

		_assignedScopeAliases = getAssignedScopeAliases(
			oAuth2Application.getOAuth2ApplicationScopeAliasesId(),
			oAuth2ScopeGrantLocalService);

		Set<String> scopeAliases = new LinkedHashSet<>(
			scopeLocator.getScopeAliases(themeDisplay.getCompanyId()));

		_assignedDeletedScopeAliases = _getAssignedDeletedScopeAliases(
			scopeAliases);

		scopeAliases.addAll(_assignedScopeAliases);

		_scopeAliasesDescriptionsMap = _getScopeAliasesDescriptionsMap(
			scopeAliases);

		_scopeAliasTreeNode = ScopeTreeUtil.getScopeAliasTreeNode(
			scopeAliases, scopeMatcherFactory);
	}

	public Set<String> getAssignedDeletedScopeAliases() {
		return _assignedDeletedScopeAliases;
	}

	public Set<String> getAssignedScopeAliases() {
		return _assignedScopeAliases;
	}

	public Map<String, String> getScopeAliasesDescriptionsMap() {
		return _scopeAliasesDescriptionsMap;
	}

	public Tree.Node<String> getScopeAliasTreeNode() {
		return _scopeAliasTreeNode;
	}

	protected Set<String> getAssignedScopeAliases(
		long oAuth2ApplicationScopeAliasesId,
		OAuth2ScopeGrantLocalService oAuth2ScopeGrantLocalService) {

		Collection<OAuth2ScopeGrant> oAuth2ScopeGrants =
			oAuth2ScopeGrantLocalService.getOAuth2ScopeGrants(
				oAuth2ApplicationScopeAliasesId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Stream<OAuth2ScopeGrant> stream = oAuth2ScopeGrants.stream();

		return stream.map(
			OAuth2ScopeGrant::getScopeAliasesList
		).flatMap(
			Collection::stream
		).collect(
			Collectors.toCollection(HashSet::new)
		);
	}

	private Set<String> _getAssignedDeletedScopeAliases(
		Set<String> scopeAliases) {

		Stream<String> stream = _assignedScopeAliases.stream();

		return stream.filter(
			scopeAlias -> !scopeAliases.contains(scopeAlias)
		).collect(
			Collectors.toCollection(HashSet::new)
		);
	}

	private String _getDescription(String scopeAlias, Locale locale) {
		ScopeDescriptor scopeDescriptor =
			_scopeDescriptorLocator.getScopeDescriptor(
				themeDisplay.getCompanyId());

		if (scopeDescriptor != null) {
			String description = scopeDescriptor.describeScope(
				scopeAlias, locale);

			if (description != null) {
				return description;
			}
		}

		Set<String> descriptions = new LinkedHashSet<>();

		for (LiferayOAuth2Scope liferayOAuth2Scope :
				_scopeLocator.getLiferayOAuth2Scopes(
					themeDisplay.getCompanyId(), scopeAlias)) {

			ScopeDescriptor applicationScopeDescriptor =
				_scopeDescriptorLocator.getScopeDescriptor(
					themeDisplay.getCompanyId(),
					liferayOAuth2Scope.getApplicationName());

			descriptions.add(
				applicationScopeDescriptor.describeScope(
					liferayOAuth2Scope.getScope(), locale));
		}

		if (!descriptions.isEmpty()) {
			Stream<String> stream = descriptions.stream();

			return stream.collect(
				Collectors.joining(StringPool.COMMA_AND_SPACE));
		}

		return StringPool.BLANK;
	}

	private Map<String, String> _getScopeAliasesDescriptionsMap(
		Set<String> scopeAliases) {

		Map<String, String> map = new HashMap<>();

		for (String scopeAlias : scopeAliases) {
			map.put(
				scopeAlias,
				_getDescription(scopeAlias, themeDisplay.getLocale()));
		}

		return map;
	}

	private final Set<String> _assignedDeletedScopeAliases;
	private final Set<String> _assignedScopeAliases;
	private final Map<String, String> _scopeAliasesDescriptionsMap;
	private final Tree.Node<String> _scopeAliasTreeNode;
	private final ScopeDescriptorLocator _scopeDescriptorLocator;
	private final ScopeLocator _scopeLocator;

}