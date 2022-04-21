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

package com.liferay.headless.admin.content.internal.resource.v1_0;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.headless.admin.content.resource.v1_0.PageDefinitionResource;
import com.liferay.headless.delivery.dto.v1_0.PageDefinition;
import com.liferay.layout.page.template.constants.LayoutPageTemplateActionKeys;
import com.liferay.layout.page.template.constants.LayoutPageTemplateConstants;
import com.liferay.layout.page.template.importer.LayoutPageTemplatesImporter;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.events.ThemeServicePreAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.servlet.DummyHttpServletResponse;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.ThemeUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Pavel Savinov
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/page-definition.properties",
	scope = ServiceScope.PROTOTYPE, service = PageDefinitionResource.class
)
public class PageDefinitionResourceImpl extends BasePageDefinitionResourceImpl {

	@Override
	public Response postSitePageDefinitionPreview(
			Long siteId, PageDefinition pageDefinition)
		throws Exception {

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(contextUser);

		if (!_fragmentPortletResourcePermission.contains(
				permissionChecker, siteId,
				FragmentActionKeys.MANAGE_FRAGMENT_ENTRIES) &&
			!_layoutPageTemplatePortletResourcePermission.contains(
				permissionChecker, siteId,
				LayoutPageTemplateActionKeys.ADD_LAYOUT_PAGE_TEMPLATE_ENTRY) &&
			!_layoutPermission.contains(
				permissionChecker, siteId, false,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				ActionKeys.ADD_LAYOUT)) {

			throw new NotAuthorizedException(
				Response.noContent(
				).build());
		}

		Map<Locale, String> nameMap = Collections.singletonMap(
			contextAcceptLanguage.getPreferredLocale(), StringUtil.randomId());

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			contextHttpServletRequest);

		Layout layout = _layoutLocalService.addLayout(
			contextUser.getUserId(), siteId, false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			_portal.getClassNameId(PageDefinition.class), 0, nameMap, nameMap,
			Collections.emptyMap(), Collections.emptyMap(),
			Collections.emptyMap(), LayoutConstants.TYPE_CONTENT,
			StringPool.BLANK, true, false, Collections.emptyMap(), 0,
			serviceContext);

		LayoutStructure layoutStructure = new LayoutStructure();

		layoutStructure.addRootLayoutStructureItem();

		_fragmentEntryLinkLocalService.
			deleteLayoutPageTemplateEntryFragmentEntryLinks(
				siteId, layout.getPlid());

		ContextResolver<ObjectMapper> contextResolver =
			_providers.getContextResolver(
				ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);

		ObjectMapper objectMapper = contextResolver.getContext(
			ObjectMapper.class);

		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();

		FilterProvider filterProvider = simpleFilterProvider.addFilter(
			"Liferay.Vulcan", SimpleBeanPropertyFilter.serializeAll());

		ObjectWriter objectWriter = objectMapper.writer(filterProvider);

		try {
			_layoutPageTemplatesImporter.importPageElement(
				layout, layoutStructure, layoutStructure.getMainItemId(),
				objectWriter.writeValueAsString(
					pageDefinition.getPageElement()),
				0);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import page element", exception);
			}

			return Response.serverError(
			).status(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR
			).entity(
				"Unable to post page definition preview"
			).build();
		}

		contextHttpServletRequest = DynamicServletRequest.addQueryString(
			contextHttpServletRequest, "p_l_id=" + layout.getPlid(), false);

		contextHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay(layout));

		layout.includeLayoutContent(
			contextHttpServletRequest, contextHttpServletResponse);

		StringBundler sb =
			(StringBundler)contextHttpServletRequest.getAttribute(
				WebKeys.LAYOUT_CONTENT);

		if (sb == null) {
			return Response.noContent(
			).build();
		}

		LayoutSet layoutSet = layout.getLayoutSet();

		ServletContext servletContext = ServletContextPool.get(
			StringPool.BLANK);

		if (contextHttpServletRequest.getAttribute(WebKeys.CTX) == null) {
			contextHttpServletRequest.setAttribute(WebKeys.CTX, servletContext);
		}

		Document document = Jsoup.parse(
			ThemeUtil.include(
				servletContext, contextHttpServletRequest,
				contextHttpServletResponse, "portal_normal.ftl",
				layoutSet.getTheme(), false));

		_layoutLocalService.deleteLayout(layout.getPlid(), serviceContext);

		Element bodyElement = document.body();

		bodyElement.html(sb.toString());

		return Response.ok(
			document.html()
		).build();
	}

	private ThemeDisplay _getThemeDisplay(Layout layout) throws Exception {
		ServicePreAction servicePreAction = new ServicePreAction();

		HttpServletResponse httpServletResponse =
			new DummyHttpServletResponse();

		servicePreAction.servicePre(
			contextHttpServletRequest, httpServletResponse, false);

		ThemeServicePreAction themeServicePreAction =
			new ThemeServicePreAction();

		themeServicePreAction.run(
			contextHttpServletRequest, httpServletResponse);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)contextHttpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		themeDisplay.setLayout(layout);
		themeDisplay.setScopeGroupId(layout.getGroupId());
		themeDisplay.setSiteGroupId(layout.getGroupId());

		return themeDisplay;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PageDefinitionResourceImpl.class);

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference(
		target = "(resource.name=" + FragmentConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _fragmentPortletResourcePermission;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference(
		target = "(resource.name=" + LayoutPageTemplateConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission
		_layoutPageTemplatePortletResourcePermission;

	@Reference
	private LayoutPageTemplatesImporter _layoutPageTemplatesImporter;

	@Reference
	private LayoutPermission _layoutPermission;

	@Reference
	private Portal _portal;

	@Context
	private Providers _providers;

}