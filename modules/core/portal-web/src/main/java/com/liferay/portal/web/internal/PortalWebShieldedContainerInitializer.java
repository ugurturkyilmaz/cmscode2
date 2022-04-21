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

package com.liferay.portal.web.internal;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.servlet.PortletSessionListenerManager;
import com.liferay.portal.kernel.servlet.SerializableSessionAttributeListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.servlet.PortalSessionListener;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.web.internal.session.replication.SessionReplicationFilter;
import com.liferay.shielded.container.Ordered;
import com.liferay.shielded.container.ShieldedContainerInitializer;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Shuyang Zhou
 */
@Ordered(2)
public class PortalWebShieldedContainerInitializer
	implements ShieldedContainerInitializer {

	@Override
	public void initialize(ServletContext servletContext)
		throws ServletException {

		if (PropsValues.PORTLET_SESSION_REPLICATE_ENABLED) {
			FilterRegistration.Dynamic dynamic = servletContext.addFilter(
				SessionReplicationFilter.class.getName(),
				new SessionReplicationFilter());

			dynamic.setAsyncSupported(true);

			dynamic.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST), false, "/*");
		}

		DocumentBuilderFactory documentBuilderFactory =
			DocumentBuilderFactory.newInstance();

		try (InputStream inputStream = servletContext.getResourceAsStream(
				ShieldedContainerInitializer.SHIELDED_CONTAINER_WEB_XML)) {

			DocumentBuilder documentBuilder =
				documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(inputStream);

			Element rootElement = document.getDocumentElement();

			// Filter mappings

			Map<String, Element> filterMappingMap = new HashMap<>();

			_forEachChildElement(
				rootElement, "filter-mapping",
				filerMappingElement -> filterMappingMap.put(
					_getChildText(filerMappingElement, "filter-name"),
					filerMappingElement));

			// Filters

			_forEachChildElement(
				rootElement, "filter",
				filterElement -> {
					String filterName = _getChildText(
						filterElement, "filter-name");

					FilterRegistration.Dynamic dynamic =
						servletContext.addFilter(
							filterName,
							_getChildText(filterElement, "filter-class"));

					dynamic.setAsyncSupported(
						GetterUtil.getBoolean(
							_getChildText(filterElement, "async-supported")));

					_forEachChildElement(
						filterElement, "init-param",
						initParamElement -> dynamic.setInitParameter(
							_getChildText(initParamElement, "param-name"),
							_getChildText(initParamElement, "param-value")));

					Element filterMappingElement = filterMappingMap.get(
						filterName);

					if (filterMappingElement != null) {
						dynamic.addMappingForUrlPatterns(
							EnumSet.of(
								DispatcherType.valueOf(
									_getChildText(
										filterMappingElement, "dispatcher"))),
							false,
							_getChildText(filterMappingElement, "url-pattern"));
					}
				});

			// Listeners

			_forEachChildElement(
				rootElement, "listener-class",
				listenerClassElement -> servletContext.addListener(
					listenerClassElement.getTextContent()));

			if (PropsValues.SESSION_VERIFY_SERIALIZABLE_ATTRIBUTE) {
				servletContext.addListener(
					SerializableSessionAttributeListener.class);
			}

			servletContext.addListener(PortalSessionListener.class);
			servletContext.addListener(PortletSessionListenerManager.class);

			// Servlet mappings

			Map<String, List<String>> servletMappingMap = new HashMap<>();

			_forEachChildElement(
				rootElement, "servlet-mapping",
				servletMappingElement -> {
					List<String> urlPatterns =
						servletMappingMap.computeIfAbsent(
							_getChildText(
								servletMappingElement, "servlet-name"),
							servletName -> new ArrayList<>());

					urlPatterns.add(
						_getChildText(servletMappingElement, "url-pattern"));
				});

			Set<String> portalServletURLPatterns = new HashSet<>();

			for (List<String> urlPatterns : servletMappingMap.values()) {
				portalServletURLPatterns.addAll(urlPatterns);
			}

			servletContext.setAttribute(
				WebKeys.PORTAL_SERVLET_URL_PATTERNS,
				Collections.unmodifiableSet(portalServletURLPatterns));

			// Servlets

			_forEachChildElement(
				rootElement, "servlet",
				servletElement -> {
					String servletName = _getChildText(
						servletElement, "servlet-name");

					ServletRegistration.Dynamic dynamic =
						servletContext.addServlet(
							servletName,
							_getChildText(servletElement, "servlet-class"));

					String loadOnStartup = _getChildText(
						servletElement, "load-on-startup");

					if (loadOnStartup != null) {
						dynamic.setLoadOnStartup(
							GetterUtil.getInteger(loadOnStartup));
					}

					dynamic.setAsyncSupported(
						GetterUtil.getBoolean(
							_getChildText(servletElement, "async-supported")));

					_forEachChildElement(
						servletElement, "init-param",
						initParamElement -> dynamic.setInitParameter(
							_getChildText(initParamElement, "param-name"),
							_getChildText(initParamElement, "param-value")));

					List<String> urlPatterns = servletMappingMap.get(
						servletName);

					if (urlPatterns != null) {
						dynamic.addMapping(urlPatterns.toArray(new String[0]));
					}
				});
		}
		catch (Exception exception) {
			throw new ServletException(exception);
		}
	}

	private void _forEachChildElement(
			Element parentElement, String childTagName,
			UnsafeConsumer<Element, Exception> childElementConsumer)
		throws Exception {

		NodeList childNodeList = parentElement.getElementsByTagName(
			childTagName);

		for (int i = 0; i < childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);

			childElementConsumer.accept((Element)childNode);
		}
	}

	private String _getChildText(Node node, String childTagName) {
		Element element = (Element)node;

		NodeList childrenNodeList = element.getElementsByTagName(childTagName);

		if (childrenNodeList.getLength() == 0) {
			return null;
		}

		Node childNode = childrenNodeList.item(0);

		return childNode.getTextContent();
	}

}