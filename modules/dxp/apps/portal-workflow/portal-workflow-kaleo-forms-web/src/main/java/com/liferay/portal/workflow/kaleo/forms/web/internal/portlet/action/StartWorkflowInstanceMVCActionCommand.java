/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.forms.web.internal.portlet.action;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsPortletKeys;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + KaleoFormsPortletKeys.KALEO_FORMS_ADMIN,
		"mvc.command.name=/kaleo_forms_admin/start_workflow_instance"
	},
	service = MVCActionCommand.class
)
public class StartWorkflowInstanceMVCActionCommand
	extends BaseKaleoFormsMVCActionCommand {

	/**
	 * Starts a <code>WorkflowInstance</code> (in
	 * <code>com.liferay.portal.kernel</code>) if the user has the
	 * <code>SUBMIT</code> permission. This method also updates the
	 * <code>DDLRecord</code> (in the
	 * <code>com.liferay.dynamic.data.lists.api</code> module).
	 *
	 * @param  actionRequest the request from which to get the request
	 *         parameters
	 * @param  actionResponse the response to receive the render parameters
	 * @throws Exception if an exception occurred
	 */
	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(),
			portal.getUploadPortletRequest(actionRequest));

		checkKaleoProcessPermission(serviceContext, ActionKeys.SUBMIT);

		DDLRecord ddlRecord = updateDDLRecord(serviceContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), KaleoProcess.class.getName(),
			ddlRecord.getRecordId(), ddlRecord, serviceContext);
	}

}