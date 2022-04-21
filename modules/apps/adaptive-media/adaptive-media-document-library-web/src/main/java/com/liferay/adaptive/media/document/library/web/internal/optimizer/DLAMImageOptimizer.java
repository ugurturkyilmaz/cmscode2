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

package com.liferay.adaptive.media.document.library.web.internal.optimizer;

import com.liferay.adaptive.media.constants.AMOptimizeImagesBackgroundTaskConstants;
import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
import com.liferay.adaptive.media.image.configuration.AMImageConfigurationHelper;
import com.liferay.adaptive.media.image.counter.AMImageCounter;
import com.liferay.adaptive.media.image.mime.type.AMImageMimeTypeProvider;
import com.liferay.adaptive.media.image.optimizer.AMImageOptimizer;
import com.liferay.adaptive.media.image.processor.AMImageProcessor;
import com.liferay.adaptive.media.image.size.AMImageSizeProvider;
import com.liferay.adaptive.media.image.validator.AMImageValidator;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageSender;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true, property = "adaptive.media.key=document-library",
	service = AMImageOptimizer.class
)
public class DLAMImageOptimizer implements AMImageOptimizer {

	@Override
	public void optimize(long companyId) {
		Collection<AMImageConfigurationEntry> amImageConfigurationEntries =
			_amImageConfigurationHelper.getAMImageConfigurationEntries(
				companyId);

		int count = _amImageCounter.countExpectedAMImageEntries(companyId);

		int total = count * amImageConfigurationEntries.size();

		AtomicInteger successCounter = new AtomicInteger(0);
		AtomicInteger errorCounter = new AtomicInteger(0);

		for (AMImageConfigurationEntry amImageConfigurationEntry :
				amImageConfigurationEntries) {

			_optimize(
				companyId, amImageConfigurationEntry.getUUID(), total,
				successCounter, errorCounter);
		}
	}

	@Override
	public void optimize(long companyId, String configurationEntryUuid) {
		int total = _amImageCounter.countExpectedAMImageEntries(companyId);

		AtomicInteger successCounter = new AtomicInteger(0);
		AtomicInteger errorCounter = new AtomicInteger(0);

		_optimize(
			companyId, configurationEntryUuid, total, successCounter,
			errorCounter);
	}

	private void _optimize(
		long companyId, String configurationEntryUuid, int total,
		AtomicInteger successCounter, AtomicInteger errorCounter) {

		ActionableDynamicQuery actionableDynamicQuery =
			_dlFileEntryLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property companyIdProperty = PropertyFactoryUtil.forName(
					"companyId");

				dynamicQuery.add(companyIdProperty.eq(companyId));

				Property groupIdProperty = PropertyFactoryUtil.forName(
					"groupId");
				Property repositoryIdProperty = PropertyFactoryUtil.forName(
					"repositoryId");

				dynamicQuery.add(
					groupIdProperty.eqProperty(repositoryIdProperty));

				Property mimeTypeProperty = PropertyFactoryUtil.forName(
					"mimeType");

				dynamicQuery.add(
					mimeTypeProperty.in(
						_amImageMimeTypeProvider.getSupportedMimeTypes()));

				Property sizeProperty = PropertyFactoryUtil.forName("size");

				dynamicQuery.add(
					sizeProperty.le(_amImageSizeProvider.getImageMaxSize()));

				DynamicQuery dlFileVersionDynamicQuery =
					_dlFileVersionLocalService.dynamicQuery();

				dlFileVersionDynamicQuery.setProjection(
					ProjectionFactoryUtil.distinct(
						ProjectionFactoryUtil.property("fileEntryId")));

				dlFileVersionDynamicQuery.add(companyIdProperty.eq(companyId));

				dlFileVersionDynamicQuery.add(
					groupIdProperty.eqProperty(repositoryIdProperty));

				dlFileVersionDynamicQuery.add(
					mimeTypeProperty.in(
						_amImageMimeTypeProvider.getSupportedMimeTypes()));

				Property statusProperty = PropertyFactoryUtil.forName("status");

				dlFileVersionDynamicQuery.add(
					statusProperty.eq(WorkflowConstants.STATUS_IN_TRASH));

				Property fileEntryIdProperty = PropertyFactoryUtil.forName(
					"fileEntryId");

				dynamicQuery.add(
					fileEntryIdProperty.notIn(dlFileVersionDynamicQuery));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(DLFileEntry dlFileEntry) -> {
				FileEntry fileEntry = new LiferayFileEntry(dlFileEntry);

				try {
					_amImageProcessor.process(
						fileEntry.getFileVersion(), configurationEntryUuid);

					_sendStatusMessage(
						successCounter.incrementAndGet(), errorCounter.get(),
						total);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to process file entry " +
								fileEntry.getFileEntryId());
					}

					if (_log.isDebugEnabled()) {
						_log.debug(exception);
					}

					_sendStatusMessage(
						successCounter.get(), errorCounter.incrementAndGet(),
						total);
				}
			});

		try {
			actionableDynamicQuery.performActions();
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}
	}

	private void _sendStatusMessage(int count, int errors, int total) {
		Message message = new Message();

		message.put(
			BackgroundTaskConstants.BACKGROUND_TASK_ID,
			BackgroundTaskThreadLocal.getBackgroundTaskId());

		Class<? extends DLAMImageOptimizer> clazz = getClass();

		message.put(
			AMOptimizeImagesBackgroundTaskConstants.CLASS_NAME,
			clazz.getName());

		message.put(AMOptimizeImagesBackgroundTaskConstants.COUNT, count);
		message.put(AMOptimizeImagesBackgroundTaskConstants.ERRORS, errors);
		message.put(AMOptimizeImagesBackgroundTaskConstants.TOTAL, total);

		message.put("status", BackgroundTaskConstants.STATUS_IN_PROGRESS);

		_backgroundTaskStatusMessageSender.sendBackgroundTaskStatusMessage(
			message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLAMImageOptimizer.class);

	@Reference
	private AMImageConfigurationHelper _amImageConfigurationHelper;

	@Reference(target = "(adaptive.media.key=document-library)")
	private AMImageCounter _amImageCounter;

	@Reference
	private AMImageMimeTypeProvider _amImageMimeTypeProvider;

	@Reference
	private AMImageProcessor _amImageProcessor;

	@Reference
	private AMImageSizeProvider _amImageSizeProvider;

	@Reference
	private AMImageValidator _amImageValidator;

	@Reference
	private BackgroundTaskStatusMessageSender
		_backgroundTaskStatusMessageSender;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileVersionLocalService _dlFileVersionLocalService;

}