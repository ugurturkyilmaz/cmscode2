<%--
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
--%>

<%@ include file="/init.jsp" %>

<meta content="<%= (String)request.getAttribute(AnalyticsWebKeys.ANALYTICS_CLIENT_READABLE_CONTENT) %>" name="data-analytics-readable-content" />

<script data-senna-track="temporary" type="text/javascript">
	var runMiddlewares = function () {
		<liferay-util:dynamic-include key="/dynamic_include/top_head.jsp#analytics" />
	};

	var analyticsClientChannelId =
		'<%= (String)request.getAttribute(AnalyticsWebKeys.ANALYTICS_CLIENT_CHANNEL_ID) %>';
	var analyticsClientGroupIds = <%= (String)request.getAttribute(AnalyticsWebKeys.ANALYTICS_CLIENT_GROUP_IDS) %>;
</script>

<script data-senna-track="permanent" id="liferayAnalyticsScript" type="text/javascript">
	(function (u, c, a, m, o, l) {
		o = 'script';
		l = document;
		a = l.createElement(o);
		m = l.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = u;
		a.onload = c;
		m.parentNode.insertBefore(a, m);
	})('https://analytics-js-cdn.liferay.com', () => {
		var config = <%= (String)request.getAttribute(AnalyticsWebKeys.ANALYTICS_CLIENT_CONFIG) %>;

		var dxpMiddleware = function (request) {
			request.context.canonicalUrl = themeDisplay.getCanonicalURL();
			request.context.channelId = analyticsClientChannelId;
			request.context.groupId = themeDisplay.getScopeGroupIdOrLiveGroupId();

			return request;
		};

		Analytics.create(config, [dxpMiddleware]);

		if (themeDisplay.isSignedIn()) {
			Analytics.setIdentity({
				email: themeDisplay.getUserEmailAddress(),
				name: themeDisplay.getUserName(),
			});
		}

		runMiddlewares();

		Analytics.send('pageViewed', 'Page');

		<c:if test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.JAVASCRIPT_SINGLE_PAGE_APPLICATION_ENABLED)) %>">
			Liferay.on('endNavigate', (event) => {
				Analytics.dispose();

				var groupId = themeDisplay.getScopeGroupIdOrLiveGroupId();

				if (
					!themeDisplay.isControlPanel() &&
					analyticsClientGroupIds.indexOf(groupId) >= 0
				) {
					Analytics.create(config, [dxpMiddleware]);

					if (themeDisplay.isSignedIn()) {
						Analytics.setIdentity({
							email: themeDisplay.getUserEmailAddress(),
							name: themeDisplay.getUserName(),
						});
					}

					runMiddlewares();

					Analytics.send('pageViewed', 'Page', {page: event.path});
				}
			});
		</c:if>
	});
</script>