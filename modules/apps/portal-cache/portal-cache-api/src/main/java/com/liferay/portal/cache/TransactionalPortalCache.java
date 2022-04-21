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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.transactional.TransactionalPortalCacheUtil;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 * @author Edward Han
 */
public class TransactionalPortalCache<K extends Serializable, V>
	extends PortalCacheWrapper<K, V> {

	public TransactionalPortalCache(
		PortalCache<K, V> portalCache, boolean mvcc) {

		super(portalCache);

		_mvcc = mvcc;
	}

	@Override
	public V get(K key) {
		V result = null;

		if (TransactionalPortalCacheUtil.isEnabled()) {
			if (key == null) {
				throw new NullPointerException("Key is null");
			}

			result = TransactionalPortalCacheUtil.get(portalCache, key);

			if (result == TransactionalPortalCacheUtil.getNullHolder()) {
				return null;
			}
		}

		if (result == null) {
			result = portalCache.get(key);
		}

		return result;
	}

	@Override
	public void put(K key, V value) {
		put(key, value, DEFAULT_TIME_TO_LIVE);
	}

	@Override
	public void put(K key, V value, int timeToLive) {
		if (TransactionalPortalCacheUtil.isEnabled()) {
			if (key == null) {
				throw new NullPointerException("Key is null");
			}

			if (value == null) {
				throw new NullPointerException("Value is null");
			}

			if (timeToLive < 0) {
				throw new IllegalArgumentException("Time to live is negative");
			}

			TransactionalPortalCacheUtil.put(
				portalCache, key, value, timeToLive, _mvcc);
		}
		else {
			portalCache.put(key, value, timeToLive);
		}
	}

	@Override
	public void remove(K key) {
		if (TransactionalPortalCacheUtil.isEnabled()) {
			if (key == null) {
				throw new NullPointerException("Key is null");
			}

			TransactionalPortalCacheUtil.put(
				portalCache, key,
				(V)TransactionalPortalCacheUtil.getNullHolder(),
				DEFAULT_TIME_TO_LIVE, _mvcc);
		}
		else {
			portalCache.remove(key);
		}
	}

	@Override
	public void removeAll() {
		if (TransactionalPortalCacheUtil.isEnabled()) {
			TransactionalPortalCacheUtil.removeAll(portalCache, _mvcc);
		}
		else {
			portalCache.removeAll();
		}
	}

	private final boolean _mvcc;

}