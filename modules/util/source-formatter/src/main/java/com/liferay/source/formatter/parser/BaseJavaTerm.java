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

import com.liferay.petra.string.StringBundler;
import com.liferay.source.formatter.check.util.SourceUtil;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public abstract class BaseJavaTerm implements JavaTerm {

	public BaseJavaTerm(
		String name, String content, String accessModifier, int lineNumber,
		boolean isAbstract, boolean isFinal, boolean isStatic) {

		_name = name;
		_content = content;
		_accessModifier = accessModifier;
		_lineNumber = lineNumber;
		_isAbstract = isAbstract;
		_isFinal = isFinal;
		_isStatic = isStatic;
	}

	@Override
	public String getAccessModifier() {
		return _accessModifier;
	}

	@Override
	public String getContent() {
		return _content;
	}

	@Override
	public List<String> getImportNames() {
		JavaClass parentJavaClass = _parentJavaClass;

		while (parentJavaClass.getParentJavaClass() != null) {
			parentJavaClass = parentJavaClass.getParentJavaClass();
		}

		return parentJavaClass.getImportNames();
	}

	@Override
	public int getLineNumber() {
		return _lineNumber;
	}

	@Override
	public int getLineNumber(int pos) {
		return _lineNumber + SourceUtil.getLineNumber(_content, pos) - 1;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPackageName() {
		JavaClass parentJavaClass = _parentJavaClass;

		while (parentJavaClass.getParentJavaClass() != null) {
			parentJavaClass = parentJavaClass.getParentJavaClass();
		}

		return parentJavaClass.getPackageName();
	}

	@Override
	public JavaClass getParentJavaClass() {
		return _parentJavaClass;
	}

	@Override
	public JavaSignature getSignature() {
		return null;
	}

	@Override
	public boolean hasAnnotation() {
		Pattern pattern = Pattern.compile(
			StringBundler.concat(
				"(\\A|\n)", SourceUtil.getIndent(_content), "@"));

		Matcher matcher = pattern.matcher(_content);

		if (matcher.find()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasAnnotation(String... annotations) {
		for (String annotation : annotations) {
			Pattern pattern = Pattern.compile(
				StringBundler.concat(
					"(\\A|\n)", SourceUtil.getIndent(_content), "@", annotation,
					"(\\(|\n)"));

			Matcher matcher = pattern.matcher(_content);

			if (matcher.find()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isAbstract() {
		return _isAbstract;
	}

	@Override
	public boolean isDefault() {
		if (Objects.equals(_accessModifier, JavaTerm.ACCESS_MODIFIER_DEFAULT)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isFinal() {
		return _isFinal;
	}

	@Override
	public boolean isJavaClass() {
		if (this instanceof JavaClass) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isJavaConstructor() {
		if (this instanceof JavaConstructor) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isJavaMethod() {
		if (this instanceof JavaMethod) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isJavaStaticBlock() {
		if (this instanceof JavaStaticBlock) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isJavaVariable() {
		if (this instanceof JavaVariable) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPrivate() {
		if (Objects.equals(_accessModifier, JavaTerm.ACCESS_MODIFIER_PRIVATE)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isProtected() {
		if (Objects.equals(
				_accessModifier, JavaTerm.ACCESS_MODIFIER_PROTECTED)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isPublic() {
		if (Objects.equals(_accessModifier, JavaTerm.ACCESS_MODIFIER_PUBLIC)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isStatic() {
		return _isStatic;
	}

	@Override
	public void setParentJavaClass(JavaClass javaClass) {
		_parentJavaClass = javaClass;
	}

	private final String _accessModifier;
	private final String _content;
	private final boolean _isAbstract;
	private final boolean _isFinal;
	private final boolean _isStatic;
	private final int _lineNumber;
	private final String _name;
	private JavaClass _parentJavaClass;

}