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

package com.liferay.petra.json.web.service.client;

import com.fasterxml.jackson.databind.Module;

import java.security.KeyStore;

import java.util.List;

import org.apache.http.NameValuePair;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface JSONWebServiceClient {

	public void destroy();

	public String doDelete(String url, List<NameValuePair> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doDelete(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doDelete(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doGet(String url, List<NameValuePair> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doGet(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doGet(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPost(String url, List<NameValuePair> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPost(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPost(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(String url, Object object)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(String url, String json)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(
			String url, String json, List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public <T> T doPostToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doPostToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPut(String url, List<NameValuePair> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPut(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPut(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String getHostName();

	public int getHostPort();

	public String getProtocol();

	public void registerModule(Module module);

	public void resetHttpClient();

	public void setHostName(String hostName);

	public void setHostPort(int hostPort);

	public void setKeyStore(KeyStore keyStore);

	public void setLogin(String login);

	public void setMaxAttempts(int maxAttempts);

	public void setOAuthAccessSecret(String oAuthAccessSecret);

	public void setOAuthAccessToken(String oAuthAccessToken);

	public void setOAuthConsumerKey(String oAuthConsumerKey);

	public void setOAuthConsumerSecret(String oAuthConsumerSecret);

	public void setPassword(String password);

	public void setProtocol(String protocol);

	public default void setTrustSelfSignedCertificates(
		boolean trustSelfSignedCertificates) {
	}

}