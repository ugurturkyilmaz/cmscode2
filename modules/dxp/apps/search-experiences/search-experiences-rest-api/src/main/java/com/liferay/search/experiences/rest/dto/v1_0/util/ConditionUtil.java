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

package com.liferay.search.experiences.rest.dto.v1_0.util;

import com.liferay.search.experiences.rest.dto.v1_0.Condition;
import com.liferay.search.experiences.rest.dto.v1_0.Contains;
import com.liferay.search.experiences.rest.dto.v1_0.Equals;
import com.liferay.search.experiences.rest.dto.v1_0.In;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Petteri Karttunen
 */
public class ConditionUtil {

	public static Condition unpack(Condition condition) {
		_unpack(condition);

		return condition;
	}

	private static void _unpack(Condition condition) {
		if (condition == null) {
			return;
		}

		Condition[] allConditions = condition.getAllConditions();

		if (allConditions != null) {
			Arrays.setAll(
				allConditions, i -> UnpackUtil.unpack(allConditions[i]));
		}

		Condition[] anyConditions = condition.getAnyConditions();

		if (anyConditions != null) {
			Arrays.setAll(
				anyConditions, i -> UnpackUtil.unpack(anyConditions[i]));
		}

		_unpack(condition.getContains());
		_unpack(condition.getEquals());
		_unpack(condition.getIn());
		_unpack(condition.getNot());
	}

	private static void _unpack(Contains contains) {
		if (contains == null) {
			return;
		}

		_unpackValue(contains::setValue, contains::getValue);
	}

	private static void _unpack(Equals equals) {
		if (equals == null) {
			return;
		}

		_unpackValue(equals::setValue, equals::getValue);
	}

	private static void _unpack(In in) {
		if (in == null) {
			return;
		}

		_unpackValue(in::setValue, in::getValue);
	}

	private static void _unpackValue(
		Consumer<Object> consumer, Supplier<Object> supplier) {

		Object value = supplier.get();

		if (value != null) {
			consumer.accept(UnpackUtil.unpack(value));
		}
	}

}