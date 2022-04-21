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

package com.liferay.portal.kernel.test.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author Matthew Tambara
 */
public abstract class AbstractTestRule<C, M> implements TestRule {

	@Override
	public Statement apply(Statement statement, Description description) {
		if (description.getMethodName() != null) {
			return createMethodStatement(statement, description);
		}

		return createClassStatement(statement, description);
	}

	protected abstract void afterClass(Description description, C c)
		throws Throwable;

	protected abstract void afterMethod(
			Description description, M m, Object target)
		throws Throwable;

	protected abstract C beforeClass(Description description) throws Throwable;

	protected abstract M beforeMethod(Description description, Object target)
		throws Throwable;

	protected Statement createClassStatement(
		Statement statement, Description description) {

		return new StatementWrapper(statement) {

			@Override
			public void evaluate() throws Throwable {
				C c = beforeClass(description);

				Throwable throwable1 = null;

				try {
					statement.evaluate();
				}
				catch (Throwable throwable2) {
					throwable1 = throwable2;
				}
				finally {
					try {
						afterClass(description, c);
					}
					catch (Throwable throwable2) {
						if (throwable1 != null) {
							throwable2.addSuppressed(throwable1);
						}

						throwable1 = throwable2;
					}
				}

				if (throwable1 != null) {
					throw throwable1;
				}
			}

		};
	}

	protected Statement createMethodStatement(
		Statement statement, Description description) {

		return new StatementWrapper(statement) {

			@Override
			public void evaluate() throws Throwable {
				Object target = inspectTarget(statement);

				M m = beforeMethod(description, target);

				Throwable throwable1 = null;

				try {
					statement.evaluate();
				}
				catch (Throwable throwable2) {
					throwable1 = throwable2;
				}
				finally {
					try {
						afterMethod(description, m, target);
					}
					catch (Throwable throwable2) {
						if (throwable1 != null) {
							throwable2.addSuppressed(throwable1);
						}

						throwable1 = throwable2;
					}
				}

				if (throwable1 != null) {
					throw throwable1;
				}
			}

		};
	}

}