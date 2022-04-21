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

AUI.add(
	'liferay-workflow-web',
	(A) => {
		var WorkflowWeb = {
			_doToggleDefinitionLinkEditionMode(namespace) {
				var instance = this;

				instance._toggleElementVisibility(namespace);

				instance._switchEditMode(namespace);

				instance._removeFormGroupClass(namespace);
			},

			_duplicationDialog: null,

			_forms: {},

			_getClickedButtonName(event, namespace) {
				var button = event.target;

				var buttonId = button.get('id');

				var buttonType = buttonId.replace(namespace, '');

				return buttonType;
			},

			_getDefinitionLinkNodeNamespace(definitionLinkNode) {
				var definitionLinkNodeInput = definitionLinkNode.one(
					'input[name$=namespace]'
				);

				var definitionLinkNamespace = definitionLinkNodeInput.val();

				return definitionLinkNamespace;
			},

			_getElementsByIds() {
				var elements = [];

				var element;

				for (var index in arguments) {
					element = document.getElementById(arguments[index]);

					if (element) {
						elements.push(element);
					}
				}

				return elements;
			},

			_getOpenDefinitionLinkNode() {
				var listEditMode = A.all('input[name$=editMode][value=true]');

				var definitionLink;

				if (listEditMode.size() === 1) {
					var node = listEditMode.item(0);

					definitionLink = node.ancestor('.workflow-definition-form');
				}

				return definitionLink;
			},

			_hasDefinitionLinkChanged(definitionLinkNode) {
				var select = definitionLinkNode.one('select');

				var currentValue = select.val();

				var workflowAssignedValue = definitionLinkNode.one(
					'input[name$=workflowAssignedValue]'
				);

				var savedValue = workflowAssignedValue.val();

				var changed = false;

				if (currentValue !== savedValue) {
					changed = true;
				}

				return changed;
			},

			_removeFormGroupClass(namespace) {
				var formContainer = document.getElementById(
					namespace + 'formContainer'
				);

				var formGroup = formContainer.querySelector('.form-group');

				if (formGroup) {
					formGroup.classList.remove('form-group');
				}
			},

			_resetLastValue(namespace) {
				var formContainerNode = A.one(
					'#' + namespace + 'formContainer'
				);

				var workflowAssignedValueNode = formContainerNode.one(
					'input[name$=workflowAssignedValue]'
				);

				var selectNode = formContainerNode.one('select');

				selectNode.val(workflowAssignedValueNode.val());
			},

			_switchEditMode(namespace) {
				var formContainerNode = A.one(
					'#' + namespace + 'formContainer'
				);

				var inputEditModeNode = formContainerNode.one(
					'input[name$=editMode]'
				);

				var editMode = inputEditModeNode.val();

				var boolEditMode = editMode === 'true' || editMode === true;

				inputEditModeNode.val(!boolEditMode);
			},

			_toggleElementVisibility(namespace) {
				var instance = this;

				var saveCancelGroupId = namespace + 'saveCancelGroup';

				var editButtonId = namespace + 'editButton';

				var formContainerId = namespace + 'formContainer';

				var definitionLabelId = namespace + 'definitionLabel';

				var elementsList = instance._getElementsByIds(
					saveCancelGroupId,
					editButtonId,
					formContainerId,
					definitionLabelId
				);

				for (var index in elementsList) {
					var element = elementsList[parseInt(index, 10)];

					if (element.classList.contains('d-none')) {
						element.classList.remove('d-none');
					}
					else {
						element.classList.add('d-none');
					}
				}
			},

			confirmBeforeDuplicateDialog(
				_event,
				actionUrl,
				title,
				randomId,
				portletNamespace
			) {
				var instance = this;

				var form = A.one('#' + portletNamespace + randomId + 'form');

				if (form && !instance._forms[randomId]) {
					instance._forms[randomId] = form;
				}
				else if (!form && instance._forms[randomId]) {
					form = instance._forms[randomId];
				}

				if (form) {
					form.setAttribute('action', actionUrl);
					form.setAttribute('method', 'POST');
				}

				var duplicationDialog = instance._duplicationDialog;

				if (duplicationDialog) {
					duplicationDialog.destroy();
				}

				var dialog = Liferay.Util.Window.getWindow({
					dialog: {
						bodyContent: form,
						height: 325,
						toolbars: {
							footer: [
								{
									cssClass: 'btn btn-secondary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('cancel'),
									on: {
										click() {
											if (form) {
												form.reset();
											}

											dialog.destroy();
										},
									},
								},
								{
									cssClass: 'btn btn-primary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('duplicate'),
									on: {
										click() {
											if (form) {
												submitForm(form);
											}

											dialog.hide();
										},
									},
								},
							],
							header: [
								{
									cssClass: 'close',
									discardDefaultButtonCssClasses: true,
									labelHTML:
										'<svg class="lexicon-icon" focusable="false"><use href="' +
										Liferay.ThemeDisplay.getPathThemeImages() +
										'/clay/icons.svg#times" /><title>' +
										Liferay.Language.get('close') +
										'</title></svg>',
									on: {
										click() {
											if (form) {
												form.reset();
											}

											dialog.destroy();
										},
									},
								},
							],
						},
						width: 500,
					},
					title,
				});

				instance._duplicationDialog = dialog;
			},

			openConfirmDeleteDialog(title, message, actionUrl) {
				var dialog = Liferay.Util.Window.getWindow({
					dialog: {
						bodyContent: message,
						destroyOnHide: true,
						height: 200,
						resizable: false,
						toolbars: {
							footer: [
								{
									cssClass: 'btn btn-primary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('delete'),
									on: {
										click() {
											window.location.assign(actionUrl);
										},
									},
								},
								{
									cssClass: 'btn btn-secondary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('cancel'),
									on: {
										click() {
											dialog.destroy();
										},
									},
								},
							],
							header: [
								{
									cssClass: 'close',
									discardDefaultButtonCssClasses: true,
									labelHTML:
										'<svg class="lexicon-icon" focusable="false"><use href="' +
										Liferay.ThemeDisplay.getPathThemeImages() +
										'/clay/icons.svg#times" /><title>' +
										Liferay.Language.get('close') +
										'</title></svg>',
									on: {
										click(event) {
											dialog.destroy();

											event.domEvent.stopPropagation();
										},
									},
								},
							],
						},
						width: 600,
					},
					title,
				});
			},

			previewBeforeRevertDialog(event, renderUrl, actionUrl, title) {
				var dialog = Liferay.Util.Window.getWindow({
					dialog: {
						destroyOnHide: true,
						modal: true,
						toolbars: {
							footer: [
								{
									cssClass: 'btn btn-secondary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('cancel'),
									on: {
										click() {
											dialog.destroy();
										},
									},
								},
								{
									cssClass: 'btn btn-primary',
									discardDefaultButtonCssClasses: true,
									label: Liferay.Language.get('restore'),
									on: {
										click() {
											window.location.assign(actionUrl);
										},
									},
								},
							],
							header: [
								{
									cssClass: 'close',
									discardDefaultButtonCssClasses: true,
									labelHTML:
										'<svg class="lexicon-icon" focusable="false"><use href="' +
										Liferay.ThemeDisplay.getPathThemeImages() +
										'/clay/icons.svg#times" /><title>' +
										Liferay.Language.get('close') +
										'</title></svg>',
									on: {
										click(event) {
											dialog.destroy();

											event.domEvent.stopPropagation();
										},
									},
								},
							],
						},
					},
					title,
					uri: renderUrl,
				});
			},

			saveWorkflowDefinitionLink(event, namespace) {
				var formContainer = document.getElementById(
					namespace + 'formContainer'
				);

				var form = formContainer.querySelector('.form');

				submitForm(form);
			},

			showActionUndoneSuccessMessage() {
				var successMessage = Liferay.Language.get('action-undone');

				Liferay.Util.openToast({
					container: document.querySelector('.portlet-column'),
					message: successMessage,
					type: 'success',
				});
			},

			showDefinitionImportSuccessMessage(namespace) {
				var undo = Liferay.Language.get('undo');

				var undoEvent = "'" + namespace + "undoDefinition'";

				var undoLink =
					'<a href="javascript:;" onclick=Liferay.fire(' +
					undoEvent +
					'); class="alert-link">' +
					undo +
					'</a>';

				var successMessage =
					Liferay.Language.get('definition-imported-successfully') +
					'&nbsp;';

				successMessage += undoLink;

				Liferay.Util.openToast({
					container: document.querySelector('.lfr-alert-container'),
					message: successMessage,
				});
			},

			toggleDefinitionLinkEditionMode(event, namespace) {
				var instance = this;

				var buttonName = instance._getClickedButtonName(
					event,
					namespace
				);

				var openDefinitionLinkNode = instance._getOpenDefinitionLinkNode();

				var openDefinitionLinkNamespace;

				if (buttonName === 'cancelButton') {
					instance._doToggleDefinitionLinkEditionMode(namespace);

					instance._resetLastValue(namespace);
				}
				else if (!openDefinitionLinkNode) {
					instance._doToggleDefinitionLinkEditionMode(namespace);
				}
				else if (
					!instance._hasDefinitionLinkChanged(openDefinitionLinkNode)
				) {
					openDefinitionLinkNamespace = instance._getDefinitionLinkNodeNamespace(
						openDefinitionLinkNode
					);

					instance._doToggleDefinitionLinkEditionMode(
						openDefinitionLinkNamespace
					);

					instance._doToggleDefinitionLinkEditionMode(namespace);
				}
				else {
					openDefinitionLinkNamespace = instance._getDefinitionLinkNodeNamespace(
						openDefinitionLinkNode
					);

					if (
						confirm(
							Liferay.Language.get(
								'you-have-unsaved-changes-do-you-want-to-proceed-without-saving'
							)
						)
					) {
						instance._doToggleDefinitionLinkEditionMode(
							openDefinitionLinkNamespace
						);

						instance._resetLastValue(openDefinitionLinkNamespace);

						instance._doToggleDefinitionLinkEditionMode(namespace);
					}
				}
			},
		};

		Liferay.WorkflowWeb = WorkflowWeb;
	},
	'',
	{
		requires: ['liferay-util-window'],
	}
);
