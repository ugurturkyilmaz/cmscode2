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

package com.liferay.portal.monitoring.internal.servlet.filter;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.monitoring.DataSampleFactory;
import com.liferay.portal.kernel.monitoring.DataSampleThreadLocal;
import com.liferay.portal.kernel.monitoring.PortalMonitoringControl;
import com.liferay.portal.kernel.monitoring.PortletMonitoringControl;
import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.ServiceMonitoringControl;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.monitoring.internal.statistics.portal.PortalRequestDataSample;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"after-filter=Absolute Redirects Filter", "dispatcher=FORWARD",
		"dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Monitoring Filter", "url-pattern=/c/*",
		"url-pattern=/group/*", "url-pattern=/user/*", "url-pattern=/web/*"
	},
	service = {Filter.class, PortalMonitoringControl.class}
)
public class MonitoringFilter
	extends BaseFilter implements PortalMonitoringControl {

	@Override
	public boolean isFilterEnabled() {
		if (!super.isFilterEnabled()) {
			return false;
		}

		if (!_monitorPortalRequest &&
			!_portletMonitoringControl.isMonitorPortletActionRequest() &&
			!_portletMonitoringControl.isMonitorPortletEventRequest() &&
			!_portletMonitoringControl.isMonitorPortletRenderRequest() &&
			!_portletMonitoringControl.isMonitorPortletResourceRequest() &&
			!_serviceMonitoringControl.isMonitorServiceRequest()) {

			return false;
		}

		return true;
	}

	@Override
	public boolean isMonitorPortalRequest() {
		return _monitorPortalRequest;
	}

	@Override
	public void setMonitorPortalRequest(boolean monitorPortalRequest) {
		_monitorPortalRequest = monitorPortalRequest;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		PortalRequestDataSample portalRequestDataSample = null;

		_incrementProcessFilterCount();

		if (_monitorPortalRequest) {
			portalRequestDataSample =
				(PortalRequestDataSample)
					_dataSampleFactory.createPortalRequestDataSample(
						_portal.getCompanyId(httpServletRequest),
						_getGroupId(httpServletRequest),
						httpServletRequest.getHeader(HttpHeaders.REFERER),
						httpServletRequest.getRemoteAddr(),
						httpServletRequest.getRemoteUser(),
						httpServletRequest.getRequestURI(),
						GetterUtil.getString(
							httpServletRequest.getRequestURL()),
						httpServletRequest.getHeader(HttpHeaders.USER_AGENT));

			DataSampleThreadLocal.initialize();
		}

		try {
			if (portalRequestDataSample != null) {
				portalRequestDataSample.prepare();
			}

			processFilter(
				MonitoringFilter.class.getName(), httpServletRequest,
				httpServletResponse, filterChain);

			if (portalRequestDataSample != null) {
				portalRequestDataSample.capture(RequestStatus.SUCCESS);

				portalRequestDataSample.setGroupId(
					_getGroupId(httpServletRequest));
				portalRequestDataSample.setStatusCode(
					httpServletResponse.getStatus());
			}
		}
		catch (Exception exception) {
			if (portalRequestDataSample != null) {
				portalRequestDataSample.capture(RequestStatus.ERROR);
			}

			if (exception instanceof IOException) {
				throw (IOException)exception;
			}
			else if (exception instanceof ServletException) {
				throw (ServletException)exception;
			}
			else {
				throw new ServletException(
					"Unable to execute request", exception);
			}
		}
		finally {
			if (portalRequestDataSample != null) {
				DataSampleThreadLocal.addDataSample(portalRequestDataSample);
			}

			if (_decrementProcessFilterCount() == 0) {
				Message message = new Message();

				message.setPayload(DataSampleThreadLocal.getDataSamples());

				_messageBus.sendMessage(DestinationNames.MONITORING, message);

				_processFilterCount.remove();
			}
		}
	}

	@Reference(unbind = "-")
	protected void setDataSampleFactory(DataSampleFactory dataSampleFactory) {
		_dataSampleFactory = dataSampleFactory;
	}

	@Reference(unbind = "-")
	protected final void setPortletMonitoringControl(
		PortletMonitoringControl portletMonitoringControl) {

		_portletMonitoringControl = portletMonitoringControl;
	}

	@Reference(unbind = "-")
	protected void setServiceMonitoringControl(
		ServiceMonitoringControl serviceMonitoringControl) {

		_serviceMonitoringControl = serviceMonitoringControl;
	}

	private int _decrementProcessFilterCount() {
		AtomicInteger processFilterCount = _processFilterCount.get();

		return processFilterCount.decrementAndGet();
	}

	private long _getGroupId(HttpServletRequest httpServletRequest) {
		long groupId = ParamUtil.getLong(httpServletRequest, "groupId");

		if (groupId > 0) {
			return groupId;
		}

		Layout layout = (Layout)httpServletRequest.getAttribute(WebKeys.LAYOUT);

		if (layout != null) {
			return layout.getGroupId();
		}

		long plid = ParamUtil.getLong(httpServletRequest, "p_l_id");

		if ((plid > 0) && (_layoutLocalService != null)) {
			try {
				layout = _layoutLocalService.getLayout(plid);

				groupId = layout.getGroupId();
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to retrieve layout " + plid, portalException);
				}
			}
		}

		return groupId;
	}

	private void _incrementProcessFilterCount() {
		AtomicInteger processFilterCount = _processFilterCount.get();

		processFilterCount.incrementAndGet();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MonitoringFilter.class);

	private static final ThreadLocal<AtomicInteger> _processFilterCount =
		new CentralizedThreadLocal<>(
			MonitoringFilter.class + "._processFilterCount",
			AtomicInteger::new);

	private DataSampleFactory _dataSampleFactory;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile LayoutLocalService _layoutLocalService;

	@Reference
	private MessageBus _messageBus;

	private boolean _monitorPortalRequest;

	@Reference
	private Portal _portal;

	private PortletMonitoringControl _portletMonitoringControl;
	private ServiceMonitoringControl _serviceMonitoringControl;

}