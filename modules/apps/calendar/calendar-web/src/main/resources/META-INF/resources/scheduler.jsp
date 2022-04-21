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

<%
String activeView = ParamUtil.getString(request, "activeView", sessionClicksDefaultView);
long date = ParamUtil.getLong(request, "date", System.currentTimeMillis());
String editCalendarBookingURL = ParamUtil.getString(request, "editCalendarBookingURL");
String filterCalendarBookings = ParamUtil.getString(request, "filterCalendarBookings", null);
boolean hideAgendaView = ParamUtil.getBoolean(request, "hideAgendaView");
boolean hideDayView = ParamUtil.getBoolean(request, "hideDayView");
boolean hideMonthView = ParamUtil.getBoolean(request, "hideMonthView");
boolean hideWeekView = ParamUtil.getBoolean(request, "hideWeekView");
String permissionsCalendarBookingURL = ParamUtil.getString(request, "permissionsCalendarBookingURL");
boolean readOnly = ParamUtil.getBoolean(request, "readOnly");
boolean showSchedulerHeader = ParamUtil.getBoolean(request, "showSchedulerHeader", true);
String viewCalendarBookingURL = ParamUtil.getString(request, "viewCalendarBookingURL");
%>

<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>

<%@ include file="/event_recorder.jspf" %>

<aui:script use="aui-toggler,json,liferay-calendar-list,liferay-scheduler">
	var calendarContainer = Liferay.component(
		'<portlet:namespace />calendarContainer'
	);

	var remoteServices = Liferay.component('<portlet:namespace />remoteServices');

	var showMoreStrings = {
		close: '<liferay-ui:message key="close" />',
		showMore: '<liferay-ui:message key="show-x-more" />',
	};

	<c:if test="<%= !hideDayView %>">
		window.<portlet:namespace />dayView = new Liferay.SchedulerDayView({
			headerViewConfig: {
				eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
				strings: showMoreStrings,
			},
			height: 700,
			isoTime: <%= useIsoTimeFormat %>,
			readOnly: <%= readOnly %>,
			strings: {
				allDay: '<liferay-ui:message key="all-day" />',
			},
		});
	</c:if>

	<c:if test="<%= !hideWeekView %>">
		window.<portlet:namespace />weekView = new Liferay.SchedulerWeekView({
			headerViewConfig: {
				displayDaysInterval: A.DataType.DateMath.WEEK_LENGTH,
				eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
				strings: showMoreStrings,
			},
			height: 700,
			isoTime: <%= useIsoTimeFormat %>,
			readOnly: <%= readOnly %>,
			strings: {
				allDay: '<liferay-ui:message key="all-day" />',
			},
		});
	</c:if>

	<c:if test="<%= !hideMonthView %>">
		window.<portlet:namespace />monthView = new Liferay.SchedulerMonthView({
			eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
			height: 'auto',
			isoTime: <%= useIsoTimeFormat %>,
			readOnly: <%= readOnly %>,
			strings: showMoreStrings,
		});
	</c:if>

	<c:if test="<%= !hideAgendaView %>">
		window.<portlet:namespace />agendaView = new Liferay.SchedulerAgendaView({
			daysCount: 31,
			height: 700,
			isoTime: <%= useIsoTimeFormat %>,
			readOnly: <%= readOnly %>,
			strings: {
				noEvents: '<liferay-ui:message key="no-events" />',
			},
		});
	</c:if>

	<c:if test="<%= !readOnly && (defaultCalendar != null) %>">
		var width = Math.min(window.innerWidth, 550);

		window.<portlet:namespace />eventRecorder = new Liferay.SchedulerEventRecorder({
			bodyTemplate: new A.Template(
				A.one('#<portlet:namespace />eventRecorderBodyTpl').html()
			),
			calendarContainer: calendarContainer,
			calendarId: <%= defaultCalendar.getCalendarId() %>,
			color: '<%= ColorUtil.toHexString(defaultCalendar.getColor()) %>',
			duration: <%= defaultDuration %>,
			editCalendarBookingURL: '<%= HtmlUtil.escapeJS(editCalendarBookingURL) %>',
			headerTemplate: new A.Template(
				A.one('#<portlet:namespace />eventRecorderHeaderTpl').html()
			),
			permissionsCalendarBookingURL:
				'<%= HtmlUtil.escapeJS(permissionsCalendarBookingURL) %>',
			popover: {
				width: width,
			},
			portletNamespace: '<portlet:namespace />',
			remoteServices: remoteServices,
			showHeader: <%= showSchedulerHeader %>,
			strings: {
				'description-hint': '<liferay-ui:message key="description-hint" />',
			},
			viewCalendarBookingURL: '<%= HtmlUtil.escapeJS(viewCalendarBookingURL) %>',
		});
	</c:if>

	var views = [];

	<c:if test="<%= !hideDayView %>">
		views.push(window.<portlet:namespace />dayView);
	</c:if>

	<c:if test="<%= !hideWeekView %>">
		views.push(window.<portlet:namespace />weekView);
	</c:if>

	<c:if test="<%= !hideMonthView %>">
		views.push(window.<portlet:namespace />monthView);
	</c:if>

	<c:if test="<%= !hideAgendaView %>">
		views.push(window.<portlet:namespace />agendaView);
	</c:if>

	window.<portlet:namespace />scheduler = new Liferay.Scheduler({
		activeView:
			window['<portlet:namespace /><%= HtmlUtil.escapeJS(activeView) %>View'],
		ariaLabels: {
			agenda: '<liferay-ui:message key="agenda-view" />',
			calendar: '<liferay-ui:message key="calendar-views" />',
			day: '<liferay-ui:message key="day-view" />',
			month: '<liferay-ui:message key="month-view" />',
			next: '<liferay-ui:message key="next" />',
			previous: '<liferay-ui:message key="previous" />',
			today: '<liferay-ui:message key="today-view" />',
			week: '<liferay-ui:message key="week-view" />',
			year: '<liferay-ui:message key="year-view" />',
		},
		boundingBox: '#<portlet:namespace />scheduler',
		calendarContainer: calendarContainer,

		<%
		java.util.Calendar nowJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);
		%>

		currentTime: new Date(
			<%= nowJCalendar.get(java.util.Calendar.YEAR) %>,
			<%= nowJCalendar.get(java.util.Calendar.MONTH) %>,
			<%= nowJCalendar.get(java.util.Calendar.DAY_OF_MONTH) %>,
			<%= nowJCalendar.get(java.util.Calendar.HOUR_OF_DAY) %>,
			<%= nowJCalendar.get(java.util.Calendar.MINUTE) %>
		),
		currentTimeFn: A.bind(remoteServices.getCurrentTime, remoteServices),

		<%
		java.util.Calendar dateJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);

		dateJCalendar.setTimeInMillis(date);
		%>

		date: new Date(
			<%= dateJCalendar.get(java.util.Calendar.YEAR) %>,
			<%= dateJCalendar.get(java.util.Calendar.MONTH) %>,
			<%= dateJCalendar.get(java.util.Calendar.DAY_OF_MONTH) %>
		),

		<c:if test="<%= !themeDisplay.isSignedIn() || ((defaultCalendar != null) && !CalendarPermission.contains(themeDisplay.getPermissionChecker(), defaultCalendar, CalendarActionKeys.MANAGE_BOOKINGS)) %>">
			disabled: true,
		</c:if>

		eventRecorder: window.<portlet:namespace />eventRecorder,
		eventsPerPage: <%= eventsPerPage %>,
		filterCalendarBookings:
			window['<%= HtmlUtil.escapeJS(filterCalendarBookings) %>'],
		firstDayOfWeek: <%= weekStartsOn %>,
		items: A.Object.values(calendarContainer.get('availableCalendars')),
		maxDaysDisplayed: <%= maxDaysDisplayed %>,
		portletNamespace: '<portlet:namespace />',
		preventPersistence: <%= ParamUtil.getBoolean(request, "preventPersistence") %>,
		remoteServices: remoteServices,
		render: true,
		showAddEventBtn: <%= ParamUtil.getBoolean(request, "showAddEventBtn") %>,
		showHeader: <%= showSchedulerHeader %>,
		strings: {
			agenda: '<liferay-ui:message key="agenda" />',
			currentDate: '<liferay-ui:message key="current-date" />',
			day: '<liferay-ui:message key="day" />',
			month: '<liferay-ui:message key="month" />',
			today: '<liferay-ui:message key="today" />',
			week: '<liferay-ui:message key="week" />',
			year: '<liferay-ui:message key="year" />',
		},

		<%
		java.util.Calendar todayJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);
		%>

		todayDate: new Date(
			<%= todayJCalendar.get(java.util.Calendar.YEAR) %>,
			<%= todayJCalendar.get(java.util.Calendar.MONTH) %>,
			<%= todayJCalendar.get(java.util.Calendar.DAY_OF_MONTH) %>
		),
		views: views,
	});

	var destroySchedulers = function (event) {
		if (event.portletId === '<%= portletDisplay.getId() %>') {
			window.<portlet:namespace />scheduler.destroy();

			Liferay.detach('destroyPortlet', destroySchedulers);
		}
	};

	Liferay.on('destroyPortlet', destroySchedulers);
</aui:script>