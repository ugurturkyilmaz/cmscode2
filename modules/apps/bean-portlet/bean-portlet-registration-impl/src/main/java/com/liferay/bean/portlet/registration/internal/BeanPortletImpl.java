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

package com.liferay.bean.portlet.registration.internal;

import com.liferay.bean.portlet.extension.BeanPortletMethod;
import com.liferay.bean.portlet.extension.BeanPortletMethodType;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletMode;
import javax.portlet.annotations.ServeResourceMethod;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

/**
 * @author Neil Griffin
 */
public class BeanPortletImpl implements BeanPortlet {

	public BeanPortletImpl(
		boolean asyncSupported,
		Map<BeanPortletMethodType, List<BeanPortletMethod>> beanMethodMap,
		Map<String, List<String>> containerRuntimeOptions,
		Map<String, String> descriptions, String displayCategory,
		Map<String, String> displayNames, int expirationCache,
		Map<String, String> initParams, Map<String, String> keywords,
		Map<String, Set<String>> liferayConfiguration,
		MultipartConfig multipartConfig, String portletClassName,
		Set<PortletDependency> portletDependencies, String portletName,
		Map<String, Preference> preferences, String preferencesValidator,
		String resourceBundle, Map<String, String> securityRoleRefs,
		Map<String, String> shortTitles, Set<String> supportedLocales,
		Map<String, Set<String>> supportedPortletModes,
		Set<QName> supportedProcessingEvents,
		Set<String> supportedPublicRenderParameters,
		Set<QName> supportedPublishingEvents,
		Map<String, Set<String>> supportedWindowStates,
		Map<String, String> titles) {

		_asyncSupported = asyncSupported || _isAsyncSupported(beanMethodMap);
		_beanMethodMap = beanMethodMap;
		_containerRuntimeOptions = containerRuntimeOptions;
		_descriptions = descriptions;
		_displayCategory = displayCategory;
		_displayNames = displayNames;
		_expirationCache = expirationCache;
		_initParams = initParams;
		_keywords = keywords;
		_liferayConfiguration = liferayConfiguration;
		_multipartConfig = multipartConfig;
		_portletClassName = portletClassName;
		_portletDependencies = portletDependencies;
		_portletName = portletName;
		_preferences = preferences;
		_preferencesValidator = preferencesValidator;
		_resourceBundle = resourceBundle;
		_securityRoleRefs = securityRoleRefs;
		_shortTitles = shortTitles;
		_supportedLocales = supportedLocales;
		_supportedPortletModes = supportedPortletModes;
		_supportedProcessingEvents = supportedProcessingEvents;
		_supportedPublicRenderParameters = supportedPublicRenderParameters;
		_supportedPublishingEvents = supportedPublishingEvents;
		_supportedWindowStates = supportedWindowStates;
		_titles = titles;
	}

	public BeanPortletImpl(
		Map<BeanPortletMethodType, List<BeanPortletMethod>> beanMethodMap,
		String displayCategory, Map<String, Set<String>> liferayConfiguration,
		String portletName, Set<QName> supportedProcessingEvents,
		Set<QName> supportedPublishingEvents) {

		this(
			false, beanMethodMap, Collections.emptyMap(),
			Collections.emptyMap(), displayCategory, Collections.emptyMap(), 0,
			Collections.emptyMap(), Collections.emptyMap(),
			liferayConfiguration, MultipartConfig.UNSUPPORTED, null,
			Collections.emptySet(), portletName, Collections.emptyMap(), null,
			null, Collections.emptyMap(), Collections.emptyMap(),
			Collections.emptySet(),
			Collections.singletonMap(
				"text/html", Collections.singleton("view")),
			supportedProcessingEvents, Collections.emptySet(),
			supportedPublishingEvents,
			Collections.singletonMap(
				"text/html",
				new LinkedHashSet<>(
					Arrays.asList("normal", "minimized", "maximized"))),
			Collections.emptyMap());
	}

	@Override
	public Map<BeanPortletMethodType, List<BeanPortletMethod>>
		getBeanMethods() {

		return _beanMethodMap;
	}

	@Override
	public Map<String, List<String>> getContainerRuntimeOptions() {
		return _containerRuntimeOptions;
	}

	@Override
	public Map<String, String> getDescriptions() {
		return _descriptions;
	}

	@Override
	public String getDisplayCategory() {
		return _displayCategory;
	}

	@Override
	public Map<String, String> getDisplayNames() {
		return _displayNames;
	}

	@Override
	public int getExpirationCache() {
		return _expirationCache;
	}

	@Override
	public Map<String, String> getInitParams() {
		return _initParams;
	}

	@Override
	public Map<String, String> getKeywords() {
		return _keywords;
	}

	@Override
	public Map<String, Set<String>> getLiferayConfiguration() {
		return _liferayConfiguration;
	}

	@Override
	public MultipartConfig getMultipartConfig() {
		return _multipartConfig;
	}

	@Override
	public String getPortletClassName() {
		return _portletClassName;
	}

	@Override
	public Set<PortletDependency> getPortletDependencies() {
		return _portletDependencies;
	}

	@Override
	public String getPortletName() {
		return _portletName;
	}

	@Override
	public Map<String, Preference> getPreferences() {
		return _preferences;
	}

	@Override
	public String getPreferencesValidator() {
		return _preferencesValidator;
	}

	@Override
	public String getResourceBundle() {
		return _resourceBundle;
	}

	@Override
	public Map<String, String> getSecurityRoleRefs() {
		return _securityRoleRefs;
	}

	@Override
	public Map<String, String> getShortTitles() {
		return _shortTitles;
	}

	@Override
	public Set<String> getSupportedLocales() {
		return _supportedLocales;
	}

	@Override
	public Map<String, Set<String>> getSupportedPortletModes() {
		return _supportedPortletModes;
	}

	@Override
	public Set<String> getSupportedPublicRenderParameters() {
		return _supportedPublicRenderParameters;
	}

	@Override
	public Map<String, Set<String>> getSupportedWindowStates() {
		return _supportedWindowStates;
	}

	@Override
	public Map<String, String> getTitles() {
		return _titles;
	}

	@Override
	public boolean isAsyncSupported() {
		return _asyncSupported;
	}

	@Override
	public Dictionary<String, Object> toDictionary(BeanApp beanApp) {
		HashMapDictionary<String, Object> dictionary =
			new HashMapDictionary<>();

		// com.liferay

		String displayCategory = getDisplayCategory();

		if (displayCategory != null) {
			dictionary.put(
				"com.liferay.portlet.display-category", displayCategory);
		}

		Map<String, Set<String>> liferayConfiguration =
			getLiferayConfiguration();

		if (liferayConfiguration != null) {
			for (Map.Entry<String, Set<String>> entry :
					liferayConfiguration.entrySet()) {

				Set<String> value = entry.getValue();

				if (value.size() == 1) {
					Iterator<String> iterator = value.iterator();

					dictionary.put(entry.getKey(), iterator.next());

					continue;
				}

				dictionary.put(entry.getKey(), value);
			}
		}

		// javax.portlet.async-supported

		dictionary.put("javax.portlet.async-supported", isAsyncSupported());

		// javax.portlet.container-runtime-options

		Map<String, List<String>> containerRuntimeOptions =
			HashMapBuilder.create(
				beanApp.getContainerRuntimeOptions()
			).putAll(
				getContainerRuntimeOptions()
			).build();

		for (Map.Entry<String, List<String>> entry :
				containerRuntimeOptions.entrySet()) {

			dictionary.put(
				"javax.portlet.container-runtime-option.".concat(
					entry.getKey()),
				entry.getValue());
		}

		// javax.portlet.default-namespace

		String defaultNamespace = beanApp.getDefaultNamespace();

		if (defaultNamespace != null) {
			dictionary.put("javax.portlet.default-namespace", defaultNamespace);
		}

		// javax.portlet.dependency

		Set<PortletDependency> portletDependencies = getPortletDependencies();

		if (!portletDependencies.isEmpty()) {
			List<String> tokenizedPortletDependencies = new ArrayList<>();

			for (PortletDependency portletDependency : portletDependencies) {
				tokenizedPortletDependencies.add(portletDependency.toString());
			}

			dictionary.put(
				"javax.portlet.dependency", tokenizedPortletDependencies);
		}

		// javax.portlet.event-definition

		List<Event> events = beanApp.getEvents();

		List<String> eventDefinitions = new ArrayList<>();

		for (Event event : events) {
			QName eventQName = event.getQName();

			StringBundler eventDefinitionSB = new StringBundler(
				_toNameValuePair(
					eventQName.getLocalPart(), eventQName.getNamespaceURI()));

			String valueType = event.getValueType();

			if (valueType != null) {
				eventDefinitionSB.append(";");
				eventDefinitionSB.append(valueType);
			}

			for (QName aliasQName : event.getAliasQNames()) {
				eventDefinitionSB.append(",");
				eventDefinitionSB.append(
					_toNameValuePair(
						aliasQName.getLocalPart(),
						aliasQName.getNamespaceURI()));
			}

			eventDefinitions.add(eventDefinitionSB.toString());
		}

		if (!eventDefinitions.isEmpty()) {
			dictionary.put("javax.portlet.event-definition", eventDefinitions);
		}

		// javax.portlet.expiration-cache

		dictionary.put("javax.portlet.expiration-cache", getExpirationCache());

		// javax.portlet.init-param

		Map<String, String> initParams = getInitParams();

		for (Map.Entry<String, String> entry : initParams.entrySet()) {
			String value = entry.getValue();

			if (value != null) {
				dictionary.put(
					"javax.portlet.init-param." + entry.getKey(), value);
			}
		}

		// javax.portlet.description

		_putEnglishText(
			getDescriptions(), dictionary, "javax.portlet.description");

		// javax.portlet.display-name

		_putEnglishText(
			getDisplayNames(), dictionary, "javax.portlet.display-name");

		// javax.portlet.info.keywords

		_putEnglishText(
			getKeywords(), dictionary, "javax.portlet.info.keywords");

		// javax.portlet.info.short-title

		_putEnglishText(
			getShortTitles(), dictionary, "javax.portlet.info.short-title");

		// javax.portlet.info.title

		Map<String, String> titles = getTitles();

		if (titles.isEmpty()) {
			dictionary.put("javax.portlet.info.title", getPortletName());
		}
		else {
			_putEnglishText(
				titles, getPortletName(), dictionary,
				"javax.portlet.info.title");
		}

		// javax.portlet.multipart

		if (_multipartConfig.isSupported()) {
			dictionary.put(
				"javax.portlet.multipart.file-size-threshold",
				_multipartConfig.getFileSizeThreshold());

			dictionary.put(
				"javax.portlet.multipart.location",
				_multipartConfig.getLocation());

			dictionary.put(
				"javax.portlet.multipart.max-file-size",
				_multipartConfig.getMaxFileSize());

			dictionary.put(
				"javax.portlet.multipart.max-request-size",
				_multipartConfig.getMaxRequestSize());
		}

		// javax.portlet.portlet-mode

		Set<String> allPortletModes = new HashSet<>(liferayPortletModes);

		allPortletModes.addAll(beanApp.getCustomPortletModes());

		List<String> supportedPortletModes = new ArrayList<>();

		Map<String, Set<String>> supportedPortletModesMap =
			getSupportedPortletModes();

		for (Map.Entry<String, Set<String>> entry :
				supportedPortletModesMap.entrySet()) {

			StringBundler portletModesSB = new StringBundler();

			portletModesSB.append(entry.getKey());
			portletModesSB.append(";");

			Set<String> portletModes = entry.getValue();

			for (String portletMode : portletModes) {
				if (allPortletModes.contains(portletMode)) {
					portletModesSB.append(portletMode);
					portletModesSB.append(",");
				}
			}

			if (portletModesSB.index() > 2) {
				portletModesSB.setIndex(portletModesSB.index() - 1);
			}

			supportedPortletModes.add(portletModesSB.toString());
		}

		if (!supportedPortletModes.isEmpty()) {
			dictionary.put("javax.portlet.portlet-mode", supportedPortletModes);
		}

		// javax.portlet.preferences

		StringBundler portletPreferencesSB = new StringBundler();

		portletPreferencesSB.append("<?xml version=\"1.0\"?>");
		portletPreferencesSB.append("<portlet-preferences>");

		Map<String, Preference> preferences = getPreferences();

		for (Map.Entry<String, Preference> entry : preferences.entrySet()) {
			portletPreferencesSB.append("<preference>");
			portletPreferencesSB.append("<name>");
			portletPreferencesSB.append(entry.getKey());
			portletPreferencesSB.append("</name>");

			Preference preference = entry.getValue();

			for (String value : preference.getValues()) {
				portletPreferencesSB.append("<value>");
				portletPreferencesSB.append(value);
				portletPreferencesSB.append("</value>");
			}

			portletPreferencesSB.append("<read-only>");
			portletPreferencesSB.append(preference.isReadOnly());
			portletPreferencesSB.append("</read-only>");

			portletPreferencesSB.append("</preference>");
		}

		portletPreferencesSB.append("</portlet-preferences>");

		dictionary.put(
			"javax.portlet.preferences", portletPreferencesSB.toString());

		// javax.portlet.preferences-validator

		String preferencesValidator = getPreferencesValidator();

		if (preferencesValidator != null) {
			dictionary.put(
				"javax.portlet.preferences-validator", preferencesValidator);
		}

		// javax.portlet.resource-bundle

		if (Validator.isNotNull(getResourceBundle())) {
			dictionary.put(
				"javax.portlet.resource-bundle", getResourceBundle());
		}

		// javax.portlet.security-role-ref

		StringBundler roleNamesSB = new StringBundler();

		Map<String, String> securityRoleRefs = getSecurityRoleRefs();

		for (Map.Entry<String, String> entry : securityRoleRefs.entrySet()) {
			roleNamesSB.append(entry.getKey());
			roleNamesSB.append(",");
		}

		if (roleNamesSB.index() > 0) {
			roleNamesSB.setIndex(roleNamesSB.index() - 1);

			dictionary.put(
				"javax.portlet.security-role-ref", roleNamesSB.toString());
		}

		// javax.portlet.supported-locale

		if (!getSupportedLocales().isEmpty()) {
			dictionary.put(
				"javax.portlet.supported-locale", getSupportedLocales());
		}

		List<String> supportedPublicRenderParameters = new ArrayList<>();

		for (String identifier : getSupportedPublicRenderParameters()) {
			supportedPublicRenderParameters.add(
				_toNameValuePair(
					identifier,
					_getPublicRenderParameterNamespaceURI(
						beanApp, identifier)));
		}

		// javax.portlet.supported-public-render-parameter

		dictionary.put(
			"javax.portlet.supported-public-render-parameter",
			supportedPublicRenderParameters);

		List<String> supportedWindowStates = new ArrayList<>();

		Map<String, Set<String>> supportedWindowStatesMap =
			getSupportedWindowStates();

		for (Map.Entry<String, Set<String>> entry :
				supportedWindowStatesMap.entrySet()) {

			StringBundler windowStatesSB = new StringBundler();

			windowStatesSB.append(entry.getKey());
			windowStatesSB.append(";");

			Set<String> windowStates = entry.getValue();

			for (String windowState : windowStates) {
				windowStatesSB.append(windowState);
				windowStatesSB.append(",");
			}

			if (windowStatesSB.index() > 2) {
				windowStatesSB.setIndex(windowStatesSB.index() - 1);
			}

			supportedWindowStates.add(windowStatesSB.toString());
		}

		// javax.portlet.window-state

		if (!supportedWindowStatesMap.isEmpty()) {
			dictionary.put("javax.portlet.window-state", supportedWindowStates);
		}

		// javax.portlet.supported-processing-event

		Set<String> supportedProcessingEvents = new HashSet<>();

		for (QName qName : _supportedProcessingEvents) {
			supportedProcessingEvents.add(
				_toNameValuePair(
					qName.getLocalPart(), qName.getNamespaceURI()));
		}

		dictionary.put(
			"javax.portlet.supported-processing-event",
			supportedProcessingEvents);

		// javax.portlet.supported-publishing-event

		Set<String> supportedPublishingEvents = new HashSet<>();

		for (QName qName : _supportedPublishingEvents) {
			supportedPublishingEvents.add(
				_toNameValuePair(
					qName.getLocalPart(), qName.getNamespaceURI()));
		}

		if (!supportedPublishingEvents.isEmpty()) {
			dictionary.put(
				"javax.portlet.supported-publishing-event",
				supportedPublishingEvents);
		}

		if (!supportedProcessingEvents.isEmpty()) {
			dictionary.put(
				"javax.portlet.supported-processing-event",
				supportedProcessingEvents);
		}

		// javax.portlet.listener

		List<String> portletListeners = new ArrayList<>();

		for (Map.Entry<Integer, String> entry : beanApp.getPortletListeners()) {
			String listenerClassName = entry.getValue();

			String listener = StringBundler.concat(
				listenerClassName, StringPool.SEMICOLON, entry.getKey());

			portletListeners.add(listener);
		}

		if (!portletListeners.isEmpty()) {
			dictionary.put("javax.portlet.listener", portletListeners);
		}

		// javax.portlet.version

		dictionary.put("javax.portlet.version", beanApp.getSpecVersion());

		return dictionary;
	}

	protected static final Set<String> liferayPortletModes =
		new HashSet<String>() {
			{
				try {
					for (Field field : LiferayPortletMode.class.getFields()) {
						if (Modifier.isStatic(field.getModifiers()) &&
							(field.getType() == PortletMode.class)) {

							PortletMode portletMode = (PortletMode)field.get(
								null);

							add(portletMode.toString());
						}
					}
				}
				catch (IllegalAccessException illegalAccessException) {
					throw new ExceptionInInitializerError(
						illegalAccessException);
				}
			}
		};

	private static void _putEnglishText(
		Map<String, String> descriptions, Dictionary<String, Object> dictionary,
		String key) {

		_putEnglishText(descriptions, null, dictionary, key);
	}

	private static void _putEnglishText(
		Map<String, String> descriptions, String defaultValue,
		Dictionary<String, Object> dictionary, String key) {

		for (Map.Entry<String, String> entry : descriptions.entrySet()) {
			String locale = entry.getKey();

			if ((locale != null) && locale.startsWith(_ENGLISH_EN)) {
				dictionary.put(key, entry.getValue());

				return;
			}
		}

		if (defaultValue != null) {
			dictionary.put(key, defaultValue);
		}
	}

	private String _getPublicRenderParameterNamespaceURI(
		BeanApp beanApp, String id) {

		Map<String, PublicRenderParameter> publicRenderParameterMap =
			beanApp.getPublicRenderParameters();

		PublicRenderParameter publicRenderParameter =
			publicRenderParameterMap.get(id);

		if (publicRenderParameter == null) {
			return XMLConstants.NULL_NS_URI;
		}

		QName qName = publicRenderParameter.getQName();

		if (qName == null) {
			return XMLConstants.NULL_NS_URI;
		}

		String namespaceURI = qName.getNamespaceURI();

		if (namespaceURI == null) {
			return XMLConstants.NULL_NS_URI;
		}

		return namespaceURI;
	}

	private boolean _isAsyncSupported(
		Map<BeanPortletMethodType, List<BeanPortletMethod>> beanMethodMap) {

		List<BeanPortletMethod> beanPortletMethods = beanMethodMap.get(
			BeanPortletMethodType.SERVE_RESOURCE);

		if (beanPortletMethods != null) {
			for (BeanPortletMethod beanPortletMethod : beanPortletMethods) {
				Method method = beanPortletMethod.getMethod();

				ServeResourceMethod serveResourceMethod = method.getAnnotation(
					ServeResourceMethod.class);

				if ((serveResourceMethod != null) &&
					serveResourceMethod.asyncSupported()) {

					return true;
				}
			}
		}

		return false;
	}

	private String _toNameValuePair(String name, String value) {
		if (Validator.isNull(value)) {
			return name;
		}

		return StringBundler.concat(name, StringPool.SEMICOLON, value);
	}

	private static final String _ENGLISH_EN = LocaleUtil.ENGLISH.getLanguage();

	private final boolean _asyncSupported;
	private final Map<BeanPortletMethodType, List<BeanPortletMethod>>
		_beanMethodMap;
	private final Map<String, List<String>> _containerRuntimeOptions;
	private final Map<String, String> _descriptions;
	private final String _displayCategory;
	private final Map<String, String> _displayNames;
	private final int _expirationCache;
	private final Map<String, String> _initParams;
	private final Map<String, String> _keywords;
	private final Map<String, Set<String>> _liferayConfiguration;
	private final MultipartConfig _multipartConfig;
	private final String _portletClassName;
	private final Set<PortletDependency> _portletDependencies;
	private final String _portletName;
	private final Map<String, Preference> _preferences;
	private final String _preferencesValidator;
	private final String _resourceBundle;
	private final Map<String, String> _securityRoleRefs;
	private final Map<String, String> _shortTitles;
	private final Set<String> _supportedLocales;
	private final Map<String, Set<String>> _supportedPortletModes;
	private final Set<QName> _supportedProcessingEvents;
	private final Set<String> _supportedPublicRenderParameters;
	private final Set<QName> _supportedPublishingEvents;
	private final Map<String, Set<String>> _supportedWindowStates;
	private final Map<String, String> _titles;

}