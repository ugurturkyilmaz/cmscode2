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

package com.liferay.portal.xml;

import com.liferay.petra.string.StringPool;
import com.liferay.petra.xml.Dom4jUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.Visitor;

import java.io.IOException;
import java.io.Writer;

import java.util.List;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author Brian Wing Shun Chan
 */
public class NodeImpl implements Node {

	public NodeImpl(org.dom4j.Node node) {
		_node = node;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public String asXML() {
		return _node.asXML();
	}

	@Override
	public Node asXPathResult(Element parent) {
		ElementImpl parentElementImpl = (ElementImpl)parent;

		org.dom4j.Node node = _node.asXPathResult(
			parentElementImpl.getWrappedElement());

		if (node == null) {
			return null;
		}

		if (node instanceof org.dom4j.Element) {
			return new ElementImpl((org.dom4j.Element)node);
		}

		return new NodeImpl(node);
	}

	@Override
	public String compactString() throws IOException {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		OutputFormat outputFormat = OutputFormat.createCompactFormat();

		XMLWriter xmlWriter = new XMLWriter(
			unsyncByteArrayOutputStream, outputFormat);

		xmlWriter.write(_node);

		return unsyncByteArrayOutputStream.toString(StringPool.UTF8);
	}

	@Override
	public Node detach() {
		org.dom4j.Node node = _node.detach();

		if (node == null) {
			return null;
		}

		if (node instanceof org.dom4j.Element) {
			return new ElementImpl((org.dom4j.Element)node);
		}

		return new NodeImpl(node);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NodeImpl)) {
			return false;
		}

		NodeImpl nodeImpl = (NodeImpl)object;

		org.dom4j.Node node = nodeImpl.getWrappedNode();

		return _node.equals(node);
	}

	@Override
	public String formattedString() throws IOException {
		return Dom4jUtil.toString(_node);
	}

	@Override
	public String formattedString(String indent) throws IOException {
		return Dom4jUtil.toString(_node, indent);
	}

	@Override
	public String formattedString(String indent, boolean expandEmptyElements)
		throws IOException {

		return Dom4jUtil.toString(_node, indent, expandEmptyElements);
	}

	@Override
	public String formattedString(
			String indent, boolean expandEmptyElements, boolean trimText)
		throws IOException {

		return Dom4jUtil.toString(_node, indent, expandEmptyElements, trimText);
	}

	@Override
	public Document getDocument() {
		org.dom4j.Document document = _node.getDocument();

		if (document == null) {
			return null;
		}

		return new DocumentImpl(document);
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public Element getParent() {
		org.dom4j.Element element = _node.getParent();

		if (element == null) {
			return null;
		}

		return new ElementImpl(element);
	}

	@Override
	public String getPath() {
		return _node.getPath();
	}

	@Override
	public String getPath(Element context) {
		ElementImpl contextElementImpl = (ElementImpl)context;

		return _node.getPath(contextElementImpl.getWrappedElement());
	}

	@Override
	public String getStringValue() {
		return _node.getStringValue();
	}

	@Override
	public String getText() {
		return _node.getText();
	}

	@Override
	public String getUniquePath() {
		return _node.getUniquePath();
	}

	@Override
	public String getUniquePath(Element context) {
		ElementImpl contextElementImpl = (ElementImpl)context;

		return _node.getUniquePath(contextElementImpl.getWrappedElement());
	}

	public org.dom4j.Node getWrappedNode() {
		return _node;
	}

	@Override
	public boolean hasContent() {
		return _node.hasContent();
	}

	@Override
	public int hashCode() {
		return _node.hashCode();
	}

	@Override
	public boolean isReadOnly() {
		return _node.isReadOnly();
	}

	@Override
	public boolean matches(String xPathExpression) {
		return _node.matches(xPathExpression);
	}

	@Override
	public Number numberValueOf(String xPathExpression) {
		return _node.numberValueOf(xPathExpression);
	}

	@Override
	public List<Node> selectNodes(String xPathExpression) {
		return SAXReaderImpl.toNewNodes(_node.selectNodes(xPathExpression));
	}

	@Override
	public List<Node> selectNodes(
		String xPathExpression, String comparisonXPathExpression) {

		return SAXReaderImpl.toNewNodes(
			_node.selectNodes(xPathExpression, comparisonXPathExpression));
	}

	@Override
	public List<Node> selectNodes(
		String xPathExpression, String comparisonXPathExpression,
		boolean removeDuplicates) {

		return SAXReaderImpl.toNewNodes(
			_node.selectNodes(
				xPathExpression, comparisonXPathExpression, removeDuplicates));
	}

	@Override
	public Object selectObject(String xPathExpression) {
		Object object = _node.selectObject(xPathExpression);

		if (object == null) {
			return null;
		}
		else if (object instanceof List<?>) {
			return SAXReaderImpl.toNewNodes((List<org.dom4j.Node>)object);
		}

		return object;
	}

	@Override
	public Node selectSingleNode(String xPathExpression) {
		org.dom4j.Node node = _node.selectSingleNode(xPathExpression);

		if (node == null) {
			return null;
		}

		if (node instanceof org.dom4j.Element) {
			return new ElementImpl((org.dom4j.Element)node);
		}

		return new NodeImpl(node);
	}

	@Override
	public void setName(String name) {
		_node.setName(name);
	}

	@Override
	public void setText(String text) {
		_node.setText(text);
	}

	@Override
	public boolean supportsParent() {
		return _node.supportsParent();
	}

	@Override
	public String toString() {
		return _node.toString();
	}

	@Override
	public String valueOf(String xPathExpression) {
		return _node.valueOf(xPathExpression);
	}

	@Override
	public void write(Writer writer) throws IOException {
		_node.write(writer);
	}

	private final org.dom4j.Node _node;

}