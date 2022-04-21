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

package com.liferay.change.tracking.internal.search;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.internal.CTServiceRegistry;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.spi.exception.CTEventException;
import com.liferay.change.tracking.spi.listener.CTEventListener;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.model.uid.UIDFactory;

import java.io.Serializable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 * @author André de Oliveira
 */
@Component(service = CTEventListener.class)
public class CTSearchEventListener implements CTEventListener {

	@Override
	public void onAfterCopy(
		long sourceCTCollectionId, long targetCTCollectionId) {

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				try (SafeCloseable safeCloseable =
						CTCollectionThreadLocal.
							setCTCollectionIdWithSafeCloseable(
								targetCTCollectionId)) {

					for (Map.Entry<CTService<?>, List<CTEntry>> ctEntryEntry :
							_getCTEntryEntries(targetCTCollectionId)) {

						_reindex(
							ctEntryEntry.getKey(), ctEntryEntry.getValue());
					}
				}

				return null;
			});
	}

	@Override
	public void onAfterPublish(long ctCollectionId) {
		CTCollection ctCollection = _ctCollectionLocalService.fetchCTCollection(
			ctCollectionId);

		if (ctCollection == null) {
			return;
		}

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				try (SafeCloseable safeCloseable =
						CTCollectionThreadLocal.
							setCTCollectionIdWithSafeCloseable(
								CTConstants.CT_COLLECTION_ID_PRODUCTION)) {

					for (Map.Entry<CTService<?>, List<CTEntry>> ctEntryEntry :
							_getCTEntryEntries(ctCollectionId)) {

						CTService<?> ctService = ctEntryEntry.getKey();

						Indexer<?> indexer = _indexerRegistry.getIndexer(
							ctService.getModelClass());

						if (indexer == null) {
							continue;
						}

						List<CTEntry> ctEntries = ctEntryEntry.getValue();

						List<String> uids = new ArrayList<>(ctEntries.size());

						for (CTEntry ctEntry : ctEntries) {
							uids.add(
								_uidFactory.getUID(
									indexer.getClassName(),
									ctEntry.getModelClassPK(),
									CTConstants.CT_COLLECTION_ID_PRODUCTION));
						}

						_indexWriterHelper.deleteDocuments(
							indexer.getSearchEngineId(),
							ctCollection.getCompanyId(), uids,
							indexer.isCommitImmediately());

						_reindex(
							ctEntryEntry.getKey(), ctEntryEntry.getValue());
					}
				}

				return null;
			});
	}

	@Override
	public void onBeforeRemove(long ctCollectionId) throws CTEventException {
		CTCollection ctCollection = _ctCollectionLocalService.fetchCTCollection(
			ctCollectionId);

		if ((ctCollection == null) ||
			(ctCollection.getStatus() == WorkflowConstants.STATUS_APPROVED)) {

			return;
		}

		for (Map.Entry<CTService<?>, List<CTEntry>> ctEntryEntry :
				_getCTEntryEntries(ctCollectionId)) {

			CTService<?> ctService = ctEntryEntry.getKey();

			Indexer<?> indexer = _indexerRegistry.getIndexer(
				ctService.getModelClass());

			if (indexer == null) {
				continue;
			}

			List<CTEntry> ctEntries = ctEntryEntry.getValue();

			List<String> uids = new ArrayList<>(ctEntries.size());

			for (CTEntry ctEntry : ctEntries) {
				if (ctEntry.getChangeType() !=
						CTConstants.CT_CHANGE_TYPE_DELETION) {

					uids.add(
						_uidFactory.getUID(
							indexer.getClassName(), ctEntry.getModelClassPK(),
							ctEntry.getCtCollectionId()));
				}
			}

			try {
				_indexWriterHelper.deleteDocuments(
					indexer.getSearchEngineId(), ctCollection.getCompanyId(),
					uids, indexer.isCommitImmediately());
			}
			catch (SearchException searchException) {
				throw new CTEventException(searchException);
			}
		}
	}

	private Collection<Map.Entry<CTService<?>, List<CTEntry>>>
		_getCTEntryEntries(long ctCollectionId) {

		Map<Long, Map.Entry<CTService<?>, List<CTEntry>>> ctEntryMap =
			new HashMap<>();

		for (CTEntry ctEntry :
				_ctEntryLocalService.getCTCollectionCTEntries(ctCollectionId)) {

			Map.Entry<CTService<?>, List<CTEntry>> entry =
				ctEntryMap.computeIfAbsent(
					ctEntry.getModelClassNameId(),
					classNameId -> {
						CTService<?> ctService =
							_ctServiceRegistry.getCTService(classNameId);

						if (ctService == null) {
							return null;
						}

						return new AbstractMap.SimpleImmutableEntry<>(
							ctService, new ArrayList<>());
					});

			if (entry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No CTService found for classNameId " +
							ctEntry.getModelClassNameId());
				}
			}
			else {
				List<CTEntry> ctEntries = entry.getValue();

				ctEntries.add(ctEntry);
			}
		}

		return ctEntryMap.values();
	}

	private <T extends CTModel<T>> void _reindex(
			CTService<T> ctService, List<CTEntry> ctEntries)
		throws SearchException {

		Indexer<T> indexer = _indexerRegistry.getIndexer(
			ctService.getModelClass());

		if (indexer == null) {
			return;
		}

		Set<Serializable> primaryKeys = new HashSet<>();

		for (CTEntry ctEntry : ctEntries) {
			if (ctEntry.getChangeType() !=
					CTConstants.CT_CHANGE_TYPE_DELETION) {

				primaryKeys.add(ctEntry.getModelClassPK());
			}
		}

		if (primaryKeys.isEmpty()) {
			return;
		}

		ctService.updateWithUnsafeFunction(
			ctPersistence -> {
				Map<Serializable, T> models = ctPersistence.fetchByPrimaryKeys(
					primaryKeys);

				indexer.reindex(models.values());

				return null;
			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CTSearchEventListener.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	@Reference
	private CTServiceRegistry _ctServiceRegistry;

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

	@Reference
	private UIDFactory _uidFactory;

}