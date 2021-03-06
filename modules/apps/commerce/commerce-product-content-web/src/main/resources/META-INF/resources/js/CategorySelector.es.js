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

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './CategorySelector.soy';

/**
 * CategorySelector is a temporary Component wrapping the existing
 * AUI module liferay-commerce-frontend-asset-categories-selector
 */
class CategorySelector extends Component {

	/**
	 * Updates the calculated rule fields for `queryValues` and `categoryIdsTitles`
	 * every time a new category entry is added or removed to the selection
	 * @protected
	 */

	onEntriesChanged_() {
		this.rule.categoryIdsTitles = this.categoriesSelector_.entries.values.map(
			(element) => element.value
		);
		this.rule.queryValues = this.categoriesSelector_.entries.keys.join(',');
	}

	/**
	 * @inheritDoc
	 */

	rendered() {
		AUI().use('liferay-commerce-frontend-asset-categories-selector', () => {
			const config = {
				categoryIds: this.rule.queryValues || '',
				categoryTitles: this.rule.categoryIdsTitles || [],
				contentBox: this.element,
				eventName: this.eventName,
				groupIds: this.groupIds,
				hiddenInput: `#${this.refs.hiddenInput.getAttribute('id')}`,
				portletURL: this.categorySelectorURL,
				vocabularyIds: this.vocabularyIds,
			};

			this.categoriesSelector_ = new Liferay.AssetTaglibCategoriesSelector(
				config
			);

			const entries = this.categoriesSelector_.entries;

			entries.after('add', this.onEntriesChanged_, this);
			entries.after('remove', this.onEntriesChanged_, this);

			this.categoriesSelector_.render();
			this.element.parentNode.removeAttribute('tabindex');
		});
	}
}

CategorySelector.STATE = {

	/**
	 * Portlet ID used for selecting categories.
	 * For this component it's required because is the only way
	 * to select categories.
	 */

	categorySelectorURL: Config.string().value(''),

	/**
	 * Name of the event that will be dispatched when the
	 * category selector dialog is closed
	 */

	eventName: Config.string().value(''),

	/**
	 * Array of group ids (sites) where categories will be searched.
	 * It defaults to an empty array, which is the current site.
	 */

	groupIds: Config.string().value(''),

	/**
	 * Id of the hidden input used to pass the selected categories
	 */

	hiddenInput: Config.string().value(''),

	/**
	 * Number used for avoiding conflicts between different
	 * instances of the component/portlet.
	 */

	index: Config.number().value(0),

	/**
	 * String used for avoiding conflicts between different
	 * instances of the component/portlet.
	 */

	namespace: Config.string().value(''),

	/**
	 * Existing information of the form.
	 * @prop {string[]} queryValues Categories that are already selected.
	 *  This property is updated as the user selects new categories.
	 * @prop {string[]} categoryIdsTitles Titles of the categories that are
	 * 	already selected. It is kept in sync with `queryValues`
	 */

	rule: Config.object().value({}),

	/**
	 * Ids of the vocabularies parents of the selected categories.
	 * Vocabularies are super groups which group a set of categories.
	 */

	vocabularyIds: Config.string().value(''),
};

Soy.register(CategorySelector, templates);

export {CategorySelector};
export default CategorySelector;
