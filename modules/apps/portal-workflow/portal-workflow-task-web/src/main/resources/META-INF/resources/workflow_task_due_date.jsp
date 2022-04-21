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
String redirect = ParamUtil.getString(request, "redirect");

WorkflowTask workflowTask = workflowTaskDisplayContext.getWorkflowTask();
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/portal_workflow_task/update_task" var="updateURL" />

<div class="task-action">
	<aui:form action="<%= updateURL %>" method="post" name="updateFm">
		<div class="modal-body task-action-content">
			<div class="modal-item-last">
				<aui:input name="workflowTaskId" type="hidden" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />

				<aui:input bean="<%= workflowTask %>" ignoreRequestValue="<%= true %>" model="<%= WorkflowTask.class %>" name="dueDate" required="<%= true %>" />

				<aui:input cols="55" cssClass="task-content-comment" name="comment" placeholder="comment" rows="1" type="textarea" />
			</div>
		</div>

		<div class="modal-footer">
			<div class="modal-item-last">
				<div class="btn-group">
					<div class="btn-group-item">
						<aui:button name="close" type="cancel" />
					</div>

					<div class="btn-group-item">
						<aui:button name="done" primary="<%= true %>" value="done" />
					</div>
				</div>
			</div>
		</div>
	</aui:form>
</div>

<aui:script use="aui-base">
	var liferayForm = Liferay.Form.get('<portlet:namespace />updateFm');

	var dueDateTimeInput = A.one('#<portlet:namespace />dueDateTime');

	dueDateTimeInput.after('blur', () => {
		liferayForm.formValidator.validate();
	});

	var doneButton = A.one('#<portlet:namespace />done');

	doneButton.on('click', (event) => {
		liferayForm.formValidator.validate();

		if (liferayForm.formValidator.hasErrors()) {
			event.preventDefault();

			return;
		}

		var data = new FormData(
			document.querySelector('#<portlet:namespace />updateFm')
		);

		Liferay.Util.fetch('<%= updateURL.toString() %>', {
			body: data,
			method: 'POST',
		}).then(() => {
			Liferay.Util.getOpener().<portlet:namespace />refreshPortlet(
				'<%= PortalUtil.escapeRedirect(redirect.toString()) %>'
			);
			Liferay.Util.getWindow('<portlet:namespace />updateDialog').destroy();
		});
	});

	var dueDateDateInput = A.one('#<portlet:namespace />dueDate');

	dueDateDateInput.set(
		'maxLength',
		Liferay.AUI.getDateFormat().replace(/%[mdY]/gm, '').length + 8
	);

	var fieldRules = [
		{
			body: function (val, fieldNode, ruleValue) {
				var valid = true;
				var date = A.DataType.Date.parse(
					Liferay.AUI.getDateFormat(),
					dueDateDateInput.get('value')
				);

				if (!date || dueDateTimeInput.get('value') === '') {
					valid = false;
				}

				doneButton.attr('disabled', !valid);

				return valid;
			},
			custom: true,
			errorMessage:
				'<%= LanguageUtil.get(request, "please-enter-a-valid-due-date") %>',
			fieldName: '<portlet:namespace />dueDate',
			validatorName: 'required',
		},
	];

	var oldFieldRules = liferayForm.get('fieldRules');

	if (oldFieldRules) {
		fieldRules = fieldRules.concat(oldFieldRules);
	}

	liferayForm.set('fieldRules', fieldRules);
</aui:script>