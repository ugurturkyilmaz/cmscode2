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

package com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.serializer.JumpToPageDDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.spi.converter.serializer.SPIDDMFormRuleActionSerializer;
import com.liferay.dynamic.data.mapping.spi.converter.serializer.SPIDDMFormRuleSerializerContext;
import com.liferay.petra.lang.HashUtil;

import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class JumpToPageDDMFormRuleAction extends DefaultDDMFormRuleAction {

	public JumpToPageDDMFormRuleAction() {
	}

	public JumpToPageDDMFormRuleAction(String source, String target) {
		super("jump-to-page", target);

		_source = source;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof JumpToPageDDMFormRuleAction)) {
			return false;
		}

		JumpToPageDDMFormRuleAction ddmFormRuleAction =
			(JumpToPageDDMFormRuleAction)object;

		if (super.equals(object) &&
			Objects.equals(_source, ddmFormRuleAction._source)) {

			return true;
		}

		return false;
	}

	public String getSource() {
		return _source;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();

		return HashUtil.hash(hash, _source);
	}

	@Override
	public String serialize(
		SPIDDMFormRuleSerializerContext spiDDMFormRuleSerializerContext) {

		SPIDDMFormRuleActionSerializer spiDDMFormRuleActionSerializer =
			new JumpToPageDDMFormRuleActionSerializer(this);

		return spiDDMFormRuleActionSerializer.serialize(
			spiDDMFormRuleSerializerContext);
	}

	public void setSource(String source) {
		_source = source;
	}

	private String _source;

}