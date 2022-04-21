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

package com.liferay.portal.background.task.internal;

import com.liferay.portal.background.task.internal.lock.helper.BackgroundTaskLockHelper;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.DelegatingBackgroundTaskExecutor;
import com.liferay.portal.kernel.lock.DuplicateLockException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManager;

/**
 * @author Michael C. Han
 */
public class SerialBackgroundTaskExecutor
	extends DelegatingBackgroundTaskExecutor {

	public SerialBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor,
		LockManager lockManager) {

		super(backgroundTaskExecutor);

		_lockManager = lockManager;

		_backgroundTaskLockHelper = new BackgroundTaskLockHelper(lockManager);
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return new SerialBackgroundTaskExecutor(
			getBackgroundTaskExecutor(), _lockManager);
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Lock lock = null;

		try {
			if (isSerial()) {
				lock = _acquireLock(backgroundTask);
			}

			BackgroundTaskExecutor backgroundTaskExecutor =
				getBackgroundTaskExecutor();

			return backgroundTaskExecutor.execute(backgroundTask);
		}
		finally {
			if (lock != null) {
				_backgroundTaskLockHelper.unlockBackgroundTask(backgroundTask);
			}
		}
	}

	private Lock _acquireLock(BackgroundTask backgroundTask)
		throws DuplicateLockException {

		Lock lock = _backgroundTaskLockHelper.lockBackgroundTask(
			backgroundTask);

		if (!lock.isNew()) {
			throw new DuplicateLockException(lock);
		}

		return lock;
	}

	private final BackgroundTaskLockHelper _backgroundTaskLockHelper;
	private final LockManager _lockManager;

}