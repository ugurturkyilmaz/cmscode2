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
	'liferay-commerce-frontend-asset-tag-selector',
	(A) => {
		var Lang = A.Lang;

		var AArray = A.Array;

		var MAP_INVALID_CHARACTERS = AArray.hash([
			'"',
			'#',
			'%',
			'&',
			'*',
			'+',
			',',
			'/',
			':',
			';',
			'<',
			'=',
			'>',
			'?',
			'@',
			'[',
			"'",
			'\\',
			'\n',
			'\r',
			']',
			'`',
			'{',
			'|',
			'}',
			'~',
		]);

		var TPL_DUPLICATE_ALERT =
			'<div class="help-block">{duplicate} {tag}: {tagName}</div>';

		var TPL_MAX_LENGTH_ALERT = '<div class="help-block">{message}</div>';

		/**
		 * OPTIONS
		 *
		 * Required
		 * hiddenInput {string}: The hidden input used to pass in the current tags.
		 * tagNames (string): The current tags.
		 *
		 */

		var AssetTaglibTagsSelector = A.Component.create({
			ATTRS: {
				allowAddEntry: {
					value: true,
				},

				allowAnyEntry: {
					value: true,
				},

				autoHighlight: {
					value: false,
				},

				dataSource: {
					valueFn() {
						var instance = this;

						return instance._getTagsDataSource();
					},
				},

				eventName: {
					validator: Lang.isString,
				},

				groupIds: {
					setter: '_setGroupIds',
					validator: Lang.isString,
				},

				guid: {
					value: '',
				},

				hiddenInput: {
					setter(value) {
						var instance = this;

						return A.one(value + instance.get('guid'));
					},
				},

				matchKey: {
					value: 'value',
				},

				maxLength: {
					value: 75,
				},

				portletURL: {
					validator: Lang.isString,
				},

				schema: {
					value: {
						resultFields: ['text', 'value'],
					},
				},

				tagNames: {
					setter(value) {
						if (Lang.isString(value)) {
							value = value.split(',');
						}

						return value;
					},
					value: '',
				},
			},

			EXTENDS: A.TextboxList,

			NAME: 'tagselector',

			prototype: {
				_addEntries() {
					var instance = this;

					var text = Lang.String.escapeHTML(
						instance.inputNode.val()
					).trim();

					if (text) {
						if (text.indexOf(',') > -1) {
							var items = text.split(',');

							items.forEach((item) => {
								instance.entries.add(item, {});
							});
						}
						else {
							instance.entries.add(text, {});
						}
					}

					Liferay.Util.focusFormField(instance.inputNode);
				},

				_bindTagsSelector() {
					var instance = this;

					var form = instance.inputNode.get('form');

					instance._submitFormListener = A.Do.before(
						instance._addEntries,
						form,
						'submit',
						instance
					);

					instance
						.get('boundingBox')
						.on('keypress', instance._onKeyPress, instance);

					instance
						.get('boundingBox')
						.after('paste', instance._onPaste, instance);
				},

				_checkDuplicateTag(object) {
					var instance = this;

					var tag = !object.value ? object : object.value;

					if (!instance.entries.containsKey(tag)) {
						return;
					}

					var message = Lang.sub(TPL_DUPLICATE_ALERT, {
						duplicate: Liferay.Language.get('duplicate'),
						tag: Liferay.Language.get('tag'),
						tagName: tag,
					});

					instance._showError(message);
				},

				_checkMaxLengthTag(object) {
					var instance = this;

					var tag = !object.value ? object : object.value;

					var maxLength = instance.get('maxLength');

					if (!tag.length || tag.length <= maxLength) {
						return;
					}

					var message = Lang.sub(TPL_MAX_LENGTH_ALERT, {
						message: Lang.sub(
							Liferay.Language.get(
								'please-enter-no-more-than-x-characters'
							),
							[maxLength]
						),
					});

					instance._showError(message);

					return new A.Do.Halt();
				},

				_getTagsDataSource() {
					var instance = this;

					var AssetTagSearch = Liferay.Service.bind(
						'/assettag/search'
					);

					AssetTagSearch._serviceQueryCache = {};

					var serviceQueryCache = AssetTagSearch._serviceQueryCache;

					var dataSource = new Liferay.Service.DataSource({
						on: {
							request(event) {
								var term = decodeURIComponent(event.request);

								var key = term;

								if (term === '*') {
									term = '';
								}

								var serviceQueryObj = serviceQueryCache[key];

								if (!serviceQueryObj) {
									serviceQueryObj = {
										end: 20,
										groupIds: instance.get('groupIds'),
										name: '%' + term + '%',
										start: 0,
										tagProperties: '',
									};

									serviceQueryCache[key] = serviceQueryObj;
								}

								event.request = serviceQueryObj;
							},
						},
						source: AssetTagSearch,
					}).plug(A.Plugin.DataSourceCache, {
						max: 500,
					});

					return dataSource;
				},

				_onAddEntryClick(event) {
					var instance = this;

					event.domEvent.preventDefault();

					instance._addEntries();
				},

				_onKeyPress(event) {
					var instance = this;

					var charCode = event.charCode;

					if (!A.UA.gecko || event._event.charCode) {
						if (charCode === '44') {
							event.preventDefault();

							instance._addEntries();
						}
						else if (
							MAP_INVALID_CHARACTERS[
								String.fromCharCode(charCode)
							]
						) {
							event.halt();
						}
					}
				},

				_onPaste(event) {
					var instance = this;

					var pastedText = (
						event._event.clipboardData || window.clipboardData
					).getData('text');

					if (pastedText.indexOf(',') !== -1) {
						requestAnimationFrame(() => {
							instance.addEntries();
						});
					}
				},

				_renderIcons() {
					var instance = this;

					var contentBox = instance.get('contentBox');

					var buttonGroup = [];

					if (instance.get('portletURL')) {
						buttonGroup.unshift({
							label: Liferay.Language.get('select'),
							on: {
								click: A.bind('_showSelectPopup', instance),
							},
							title: Liferay.Language.get('select-tags'),
						});
					}

					if (instance.get('allowAddEntry')) {
						buttonGroup.unshift({
							label: Liferay.Language.get('add'),
							on: {
								click: A.bind('_onAddEntryClick', instance),
							},
							title: Liferay.Language.get('add-tags'),
						});
					}

					instance.icons = new A.Toolbar({
						children: [buttonGroup],
					}).render(contentBox);

					var iconsBoundingBox = instance.icons.get('boundingBox');

					instance.entryHolder.placeAfter(iconsBoundingBox);
				},

				_setGroupIds(value) {
					return value.split(',');
				},

				_showError(message) {
					var instance = this;

					var contentBox = instance.get('contentBox');

					var toolbar = instance.icons.get('contentBox');

					contentBox.addClass('has-error');

					var alertNode = toolbar.insertBefore(message, toolbar);

					A.later(
						5000,
						instance,
						() => {
							alertNode.remove();

							contentBox.removeClass('has-error');
						},
						{},
						false
					);
				},

				_showSelectPopup(event) {
					var instance = this;

					event.domEvent.preventDefault();

					var uri = Lang.sub(
						decodeURIComponent(instance.get('portletURL')),
						{
							selectedTagNames: instance.entries.keys.join(),
						}
					);

					const openerWindow = Liferay.Util.getOpener();

					openerWindow.Liferay.Util.openSelectionModal({
						buttonAddLabel: Liferay.Language.get('done'),
						multiple: true,
						onSelect: (selectedItems) => {
							if (selectedItems?.length) {
								instance.entries.each((item) => {
									instance.entries.remove(item);
								});

								selectedItems.map((item) => {
									instance.add(item.value);
								});
							}
						},
						title: Liferay.Language.get('tags'),
						url: uri,
					});
				},

				_updateHiddenInput(_event) {
					var instance = this;

					var hiddenInput = instance.get('hiddenInput');

					hiddenInput.val(instance.entries.keys.join());
				},

				addEntries() {
					var instance = this;

					instance._addEntries();
				},

				bindUI() {
					var instance = this;

					AssetTaglibTagsSelector.superclass.bindUI.apply(
						instance,
						arguments
					);

					instance._bindTagsSelector();

					var entries = instance.entries;

					entries.after('add', instance._updateHiddenInput, instance);
					entries.after(
						'remove',
						instance._updateHiddenInput,
						instance
					);

					A.Do.before(
						instance._checkDuplicateTag,
						instance.entries,
						'add',
						instance
					);
					A.Do.before(
						instance._checkMaxLengthTag,
						instance.entries,
						'add',
						instance
					);
				},

				renderUI() {
					var instance = this;

					AssetTaglibTagsSelector.superclass.renderUI.apply(
						instance,
						arguments
					);

					instance._renderIcons();

					instance.inputNode.addClass('lfr-tag-selector-input');

					instance._overlayAlign.node = instance.entryHolder;
				},

				syncUI() {
					var instance = this;

					AssetTaglibTagsSelector.superclass.syncUI.apply(
						instance,
						arguments
					);

					var tagNames = instance.get('tagNames');

					tagNames.forEach(instance.add, instance);
				},
			},
		});

		Liferay.AssetTaglibTagsSelector = AssetTaglibTagsSelector;
	},
	'',
	{
		requires: [
			'aui-io-plugin-deprecated',
			'aui-live-search-deprecated',
			'aui-template-deprecated',
			'aui-textboxlist-deprecated',
			'datasource-cache',
			'liferay-service-datasource',
		],
	}
);
