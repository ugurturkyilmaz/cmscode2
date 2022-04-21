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
	'liferay-calendar-message-util',
	(A) => {
		var Lang = A.Lang;

		var STR_BLANK = '';

		var TPL_MESSAGE_UPDATE_ALL_INVITED =
			'<p class="calendar-portlet-confirmation-text">' +
			Liferay.Language.get('invited-users-will-be-notified') +
			'</p>';

		Liferay.CalendarMessageUtil = {
			_queueableQuestionUpdateAllInvited(data) {
				var instance = this;

				var answers = data.answers;

				var showNextQuestion = A.bind('run', instance.queue);

				if (answers.cancel) {
					A.soon(showNextQuestion);
				}
				else {
					Liferay.CalendarMessageUtil.confirm(
						TPL_MESSAGE_UPDATE_ALL_INVITED,
						Liferay.Language.get('save-changes'),
						Liferay.Language.get('do-not-change-the-event'),
						showNextQuestion,
						() => {
							answers.cancel = true;

							showNextQuestion();
						}
					);
				}
			},

			_queueableQuestionUpdateRecurring(data) {
				var instance = this;

				var answers = data.answers;

				var showNextQuestion = A.bind('run', instance.queue);

				if (answers.cancel) {
					A.soon(showNextQuestion);
				}
				else {
					Liferay.RecurrenceUtil.openConfirmationPanel(
						'update',
						() => {
							answers.updateInstance = true;

							showNextQuestion();
						},
						() => {
							answers.allFollowing = true;
							answers.updateInstance = true;

							showNextQuestion();
						},
						showNextQuestion,
						() => {
							answers.cancel = true;

							showNextQuestion();
						}
					);
				}
			},

			_queueableQuestionUserCalendarOnly(data) {
				var instance = this;

				var answers = data.answers;

				var showNextQuestion = A.bind('run', instance.queue);

				if (answers.cancel) {
					A.soon(showNextQuestion);
				}
				else {
					var content = [
						'<p class="calendar-portlet-confirmation-text">',
						Lang.sub(
							Liferay.Language.get(
								'you-are-about-to-make-changes-that-will-only-affect-your-calendar-x'
							),
							[Liferay.Util.escapeHTML(data.calendarName)]
						),
						'</p>',
					].join(STR_BLANK);

					Liferay.CalendarMessageUtil.confirm(
						content,
						Liferay.Language.get('save-changes'),
						Liferay.Language.get('do-not-change-the-event'),
						showNextQuestion,
						() => {
							answers.cancel = true;

							showNextQuestion();
						}
					);
				}
			},

			confirm(message, yesButtonLabel, noButtonLabel, yesFn, noFn) {
				var confirmationPanel;

				var getButtonConfig = function (label, callback) {
					return {
						label,
						on: {
							click() {
								if (callback) {
									callback.apply(this, arguments);
								}

								confirmationPanel.hide();
							},
						},
					};
				};

				confirmationPanel = Liferay.Util.Window.getWindow({
					dialog: {
						bodyContent: message,
						height: 250,
						hideOn: [],
						resizable: false,
						toolbars: {
							footer: [
								getButtonConfig(yesButtonLabel, yesFn),
								getButtonConfig(noButtonLabel, noFn),
							],
						},
						width: 700,
					},
					title: Liferay.Language.get('are-you-sure'),
				});

				return confirmationPanel.render().show();
			},

			promptSchedulerEventUpdate(data) {
				var instance = this;

				data.answers = {};

				var queue = new A.AsyncQueue();

				if (data.recurring) {
					queue.add({
						args: [data],
						autoContinue: false,
						context: instance,
						fn: instance._queueableQuestionUpdateRecurring,
						timeout: 0,
					});
				}

				if (data.masterBooking) {
					if (data.hasChild) {
						queue.add({
							args: [data],
							autoContinue: false,
							context: instance,
							fn: instance._queueableQuestionUpdateAllInvited,
							timeout: 0,
						});
					}
				}
				else {
					queue.add({
						args: [data],
						autoContinue: false,
						context: instance,
						fn: instance._queueableQuestionUserCalendarOnly,
						timeout: 0,
					});
				}

				queue.add({
					args: [data],
					autoContinue: false,
					context: instance,
					fn: data.resolver,
					timeout: 0,
				});

				instance.queue = queue;

				queue.run();
			},

			showAlert(containerId, message) {
				Liferay.Util.openToast({
					containerId,
					message,
					type: 'success',
				});
			},

			showErrorMessage(container, errorMessage) {
				Liferay.Util.openToast({
					container,
					message: errorMessage,
					type: 'danger',
				});
			},

			showSuccessMessage(container, message) {
				if (!message) {
					message = Liferay.Language.get(
						'your-request-completed-successfully'
					);
				}

				Liferay.Util.openToast({
					container,
					message,
					type: 'success',
				});
			},
		};
	},
	'',
	{
		requires: ['liferay-util-window'],
	}
);
