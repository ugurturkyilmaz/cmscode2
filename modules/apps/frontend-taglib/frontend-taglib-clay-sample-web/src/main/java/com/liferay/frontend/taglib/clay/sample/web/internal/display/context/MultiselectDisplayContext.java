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

package com.liferay.frontend.taglib.clay.sample.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.MultiselectItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.MultiselectItemListBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.MultiselectLocator;

import java.util.List;

/**
 * @author Kresimir Coko
 */
public class MultiselectDisplayContext {

	public MultiselectLocator getMultiselectLocator() {
		if (_multiselectLocator != null) {
			return _multiselectLocator;
		}

		_multiselectLocator = new MultiselectLocator();

		_multiselectLocator.setLabel("name");
		_multiselectLocator.setValue("data");

		return _multiselectLocator;
	}

	public List<MultiselectItem> getSelectedMultiselectItems() {
		if (_selectedMultiselectItems != null) {
			return _selectedMultiselectItems;
		}

		_selectedMultiselectItems = MultiselectItemListBuilder.add(
			multiselectItem -> {
				multiselectItem.setLabel("one");
				multiselectItem.setValue("1");
			}
		).add(
			multiselectItem -> {
				multiselectItem.setLabel("two");
				multiselectItem.setValue("2");
			}
		).build();

		return _selectedMultiselectItems;
	}

	public List<MultiselectItem>
		getSelectedMultiselectItemsWithCustomProperties() {

		if (_selectedMultiselectItemsWithCustomProperties != null) {
			return _selectedMultiselectItemsWithCustomProperties;
		}

		_selectedMultiselectItemsWithCustomProperties =
			MultiselectItemListBuilder.add(
				multiselectItem -> {
					multiselectItem.put("data", "1");
					multiselectItem.put("name", "one");
				}
			).add(
				multiselectItem -> {
					multiselectItem.put("data", "2");
					multiselectItem.put("name", "two");
				}
			).build();

		return _selectedMultiselectItemsWithCustomProperties;
	}

	public List<MultiselectItem> getSourceMultiselectItems() {
		if (_sourceMultiselectItems != null) {
			return _sourceMultiselectItems;
		}

		_sourceMultiselectItems = MultiselectItemListBuilder.add(
			multiselectItem -> {
				multiselectItem.setLabel("one");
				multiselectItem.setValue("1");
			}
		).add(
			multiselectItem -> {
				multiselectItem.setLabel("two");
				multiselectItem.setValue("2");
			}
		).add(
			multiselectItem -> {
				multiselectItem.setLabel("three");
				multiselectItem.setValue("3");
			}
		).add(
			multiselectItem -> {
				multiselectItem.setLabel("four");
				multiselectItem.setValue("4");
			}
		).build();

		return _sourceMultiselectItems;
	}

	public List<MultiselectItem>
		getSourceMultiselectItemsWithCustomProperties() {

		if (_sourceMultiselectItemsWithCustomProperties != null) {
			return _sourceMultiselectItemsWithCustomProperties;
		}

		_sourceMultiselectItemsWithCustomProperties =
			MultiselectItemListBuilder.add(
				multiselectItem -> {
					multiselectItem.put("data", "1");
					multiselectItem.put("name", "one");
				}
			).add(
				multiselectItem -> {
					multiselectItem.put("data", "2");
					multiselectItem.put("name", "two");
				}
			).add(
				multiselectItem -> {
					multiselectItem.put("data", "3");
					multiselectItem.put("name", "three");
				}
			).add(
				multiselectItem -> {
					multiselectItem.put("data", "4");
					multiselectItem.put("name", "four");
				}
			).build();

		return _sourceMultiselectItemsWithCustomProperties;
	}

	public void setMultiselectLocator(MultiselectLocator multiselectLocator) {
		_multiselectLocator = multiselectLocator;
	}

	public void setSelectedMultiselectItems(
		List<MultiselectItem> selectedItems) {

		_selectedMultiselectItems = selectedItems;
	}

	public void setSelectedMultiselectItemsWithCustomProperties(
		List<MultiselectItem> selectedMultiselectItemsWithCustomProperties) {

		_selectedMultiselectItemsWithCustomProperties =
			selectedMultiselectItemsWithCustomProperties;
	}

	public void setSourceMultiselectItems(
		List<MultiselectItem> sourceMultiselectItems) {

		_sourceMultiselectItems = sourceMultiselectItems;
	}

	public void setSourceMultiselectItemsWithCustomProperties(
		List<MultiselectItem> sourceMultiselectItemsWithCustomProperties) {

		_sourceMultiselectItemsWithCustomProperties =
			sourceMultiselectItemsWithCustomProperties;
	}

	private MultiselectLocator _multiselectLocator;
	private List<MultiselectItem> _selectedMultiselectItems;
	private List<MultiselectItem> _selectedMultiselectItemsWithCustomProperties;
	private List<MultiselectItem> _sourceMultiselectItems;
	private List<MultiselectItem> _sourceMultiselectItemsWithCustomProperties;

}