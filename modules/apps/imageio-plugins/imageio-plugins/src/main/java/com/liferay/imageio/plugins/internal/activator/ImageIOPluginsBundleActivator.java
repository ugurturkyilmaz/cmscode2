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

package com.liferay.imageio.plugins.internal.activator;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

import org.monte.media.jpeg.CMYKJPEGImageReaderSpi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Adolfo Pérez
 */
public class ImageIOPluginsBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) {
		_register(ImageReaderSpi.class, _imageReaderSpiSet);
		_register(ImageWriterSpi.class, _imageWriterSpiSet);

		orderImageReaderSpis();
	}

	@Override
	public void stop(BundleContext bundleContext) {
		_unregister(_imageReaderSpiSet);
		_unregister(_imageWriterSpiSet);
	}

	protected void orderImageReaderSpis() {
		IIORegistry defaultIIORegistry = IIORegistry.getDefaultInstance();

		ImageReaderSpi firstImageReaderSpi = null;
		ImageReaderSpi secondImageReaderSpi = null;

		Iterator<ImageReaderSpi> iterator =
			defaultIIORegistry.getServiceProviders(ImageReaderSpi.class, true);

		while (iterator.hasNext()) {
			ImageReaderSpi imageReaderSpi = iterator.next();

			if (imageReaderSpi instanceof CMYKJPEGImageReaderSpi) {
				secondImageReaderSpi = imageReaderSpi;
			}
			else {
				String[] formatNames = imageReaderSpi.getFormatNames();

				if (ArrayUtil.contains(formatNames, "jpg", true) ||
					ArrayUtil.contains(formatNames, "jpeg", true)) {

					firstImageReaderSpi = imageReaderSpi;
				}
			}
		}

		if ((firstImageReaderSpi != null) && (secondImageReaderSpi != null)) {
			defaultIIORegistry.setOrdering(
				ImageReaderSpi.class, firstImageReaderSpi,
				secondImageReaderSpi);
		}
	}

	private <T> void _register(Class<T> clazz, Set<T> registeredProviders) {
		IIORegistry iioRegistry = IIORegistry.getDefaultInstance();

		Iterator<T> iterator = ServiceRegistry.lookupProviders(
			clazz, ImageIOPluginsBundleActivator.class.getClassLoader());

		while (iterator.hasNext()) {
			registeredProviders.add(iterator.next());
		}

		iioRegistry.registerServiceProviders(registeredProviders.iterator());
	}

	private <T> void _unregister(Set<T> registeredProviders) {
		IIORegistry iioRegistry = IIORegistry.getDefaultInstance();

		for (T provider : registeredProviders) {
			iioRegistry.deregisterServiceProvider(provider);
		}

		registeredProviders.clear();
	}

	private final Set<ImageReaderSpi> _imageReaderSpiSet = new HashSet<>();
	private final Set<ImageWriterSpi> _imageWriterSpiSet = new HashSet<>();

}