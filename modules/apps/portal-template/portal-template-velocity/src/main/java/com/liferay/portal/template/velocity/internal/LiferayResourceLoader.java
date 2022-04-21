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

package com.liferay.portal.template.velocity.internal;

import com.liferay.portal.kernel.io.ReaderInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceLoader;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class LiferayResourceLoader extends ResourceLoader {

	@Override
	public long getLastModified(Resource resource) {
		if (_log.isDebugEnabled()) {
			_log.debug("Get last modified for " + resource.getName());
		}

		return 0;
	}

	@Override
	public InputStream getResourceStream(String source)
		throws ResourceNotFoundException {

		InputStream inputStream = _getResourceInputStream(source);

		if (inputStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to find " + source);
			}

			throw new ResourceNotFoundException(source);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Successfully found " + source);
		}

		return inputStream;
	}

	@Override
	public void init(ExtendedProperties extendedProperties) {
		int resourceModificationCheckInterval = GetterUtil.getInteger(
			extendedProperties.get("resourceModificationCheckInterval"), 60);

		setModificationCheckInterval(resourceModificationCheckInterval);

		_templateResourceLoader =
			(TemplateResourceLoader)extendedProperties.get(
				VelocityTemplateResourceLoader.class.getName());
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		if (_log.isDebugEnabled()) {
			_log.debug("Check modified status for " + resource.getName());
		}

		return false;
	}

	@Override
	public boolean resourceExists(String resourceName) {
		InputStream inputStream = null;

		try {
			inputStream = _getResourceInputStream(resourceName);

			if (inputStream != null) {
				inputStream.close();
			}
		}
		catch (IOException ioException) {
			if (_log.isDebugEnabled()) {
				_log.debug(ioException);
			}
		}
		catch (ResourceNotFoundException resourceNotFoundException) {
			if (_log.isDebugEnabled()) {
				_log.debug(resourceNotFoundException);
			}
		}

		if (inputStream != null) {
			return true;
		}

		return false;
	}

	private InputStream _getResourceInputStream(String source)
		throws ResourceNotFoundException {

		if (_log.isDebugEnabled()) {
			_log.debug("Get resource for " + source);
		}

		try {
			TemplateResource templateResource =
				_templateResourceLoader.getTemplateResource(source);

			return new ReaderInputStream(templateResource.getReader());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayResourceLoader.class);

	private TemplateResourceLoader _templateResourceLoader;

}