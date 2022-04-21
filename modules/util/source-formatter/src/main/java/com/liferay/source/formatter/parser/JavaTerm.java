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

package com.liferay.source.formatter.parser;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public interface JavaTerm {

	public static final String ACCESS_MODIFIER_DEFAULT = "default";

	public static final String ACCESS_MODIFIER_PRIVATE = "private";

	public static final String ACCESS_MODIFIER_PROTECTED = "protected";

	public static final String ACCESS_MODIFIER_PUBLIC = "public";

	public static final String[] ACCESS_MODIFIERS = {
		ACCESS_MODIFIER_DEFAULT, ACCESS_MODIFIER_PRIVATE,
		ACCESS_MODIFIER_PROTECTED, ACCESS_MODIFIER_PUBLIC
	};

	public String getAccessModifier();

	public String getContent();

	public List<String> getImportNames();

	public int getLineNumber();

	public int getLineNumber(int pos);

	public String getName();

	public String getPackageName();

	public JavaClass getParentJavaClass();

	public JavaSignature getSignature();

	public boolean hasAnnotation();

	public boolean hasAnnotation(String... annotations);

	public boolean isAbstract();

	public boolean isDefault();

	public boolean isFinal();

	public boolean isJavaClass();

	public boolean isJavaConstructor();

	public boolean isJavaMethod();

	public boolean isJavaStaticBlock();

	public boolean isJavaVariable();

	public boolean isPrivate();

	public boolean isProtected();

	public boolean isPublic();

	public boolean isStatic();

	public void setParentJavaClass(JavaClass javaClass);

}