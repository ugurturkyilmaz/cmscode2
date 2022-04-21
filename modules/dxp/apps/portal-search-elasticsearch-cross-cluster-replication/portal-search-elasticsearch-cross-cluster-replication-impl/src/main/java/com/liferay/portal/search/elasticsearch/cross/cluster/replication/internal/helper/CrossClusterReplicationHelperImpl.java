/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.helper;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.ccr.CrossClusterReplicationConfigurationHelper;
import com.liferay.portal.search.ccr.CrossClusterReplicationHelper;
import com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.ccr.FollowInfoCCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.FollowInfoCCRResponse;
import com.liferay.portal.search.engine.adapter.ccr.FollowInfoStatus;
import com.liferay.portal.search.engine.adapter.ccr.PauseFollowCCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.PutFollowCCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.UnfollowCCRRequest;
import com.liferay.portal.search.engine.adapter.cluster.UpdateSettingsClusterRequest;
import com.liferay.portal.search.engine.adapter.index.CloseIndexRequest;
import com.liferay.portal.search.engine.adapter.index.DeleteIndexRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	enabled = false, immediate = true,
	service = CrossClusterReplicationHelper.class
)
public class CrossClusterReplicationHelperImpl
	implements CrossClusterReplicationHelper {

	@Override
	public void addRemoteCluster(
		String remoteClusterAlias, String remoteClusterSeedNodeTransportAddress,
		String localClusterConnectionId) {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Adding remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId));
		}

		try {
			_updateSettings(
				localClusterConnectionId, remoteClusterAlias,
				remoteClusterSeedNodeTransportAddress);
		}
		catch (RuntimeException runtimeException) {
			_log.error(
				StringBundler.concat(
					"Unable to add the remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId),
				runtimeException);
		}
	}

	@Override
	public void deleteRemoteCluster(
		String remoteClusterAlias, String localClusterConnectionId) {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Removing remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId));
		}

		try {
			_updateSettings(localClusterConnectionId, remoteClusterAlias, null);
		}
		catch (RuntimeException runtimeException) {
			_log.error(
				StringBundler.concat(
					"Unable to remove the remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId),
				runtimeException);
		}
	}

	@Override
	public void follow(String indexName) {
		if (!crossClusterReplicationConfigurationHelper.
				isCrossClusterReplicationEnabled()) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not following index " + indexName +
						" because cross-cluster replication is not enabled");
			}

			return;
		}

		for (String localClusterConnectionId :
				crossClusterReplicationConfigurationHelper.
					getLocalClusterConnectionIds()) {

			follow(
				crossClusterReplicationConfigurationWrapper.
					getRemoteClusterAlias(),
				indexName, localClusterConnectionId);
		}
	}

	@Override
	public void follow(
		String remoteClusterAlias, String indexName,
		String localClusterConnectionId) {

		if (_isFollowingActive(localClusterConnectionId, indexName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"The ", indexName,
						" index is already being followed for connection ",
						localClusterConnectionId));
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Executing follow request for the ", indexName,
					" index with connection ", localClusterConnectionId));
		}

		try {
			_putFollow(remoteClusterAlias, indexName, localClusterConnectionId);
		}
		catch (RuntimeException runtimeException) {
			_log.error(
				StringBundler.concat(
					"Unable to follow the ", indexName, " index in the ",
					remoteClusterAlias, " cluster for connection ",
					localClusterConnectionId),
				runtimeException);
		}
	}

	@Override
	public void unfollow(String indexName) {
		if (!crossClusterReplicationConfigurationHelper.
				isCrossClusterReplicationEnabled()) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not unfollowing index " + indexName +
						" because cross-cluster replication is not enabled");
			}

			return;
		}

		for (String localClusterConnectionId :
				crossClusterReplicationConfigurationHelper.
					getLocalClusterConnectionIds()) {

			unfollow(indexName, localClusterConnectionId);
		}
	}

	@Override
	public void unfollow(String indexName, String localClusterConnectionId) {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Executing unfollow requests for the ", indexName,
					" index with connection ", localClusterConnectionId));
		}

		try {
			_pauseFollow(indexName, localClusterConnectionId);

			_closeIndex(indexName, localClusterConnectionId);

			_unfollow(indexName, localClusterConnectionId);

			_deleteIndex(indexName, localClusterConnectionId);
		}
		catch (RuntimeException runtimeException) {
			_log.error(
				StringBundler.concat(
					"Unable to unfollow the ", indexName,
					" index for connection ", localClusterConnectionId),
				runtimeException);
		}
	}

	@Reference
	protected CrossClusterReplicationConfigurationHelper
		crossClusterReplicationConfigurationHelper;

	@Reference
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference
	protected SearchEngineAdapter searchEngineAdapter;

	private void _closeIndex(String indexName, String connectionId) {
		CloseIndexRequest closeIndexRequest = new CloseIndexRequest(indexName);

		closeIndexRequest.setConnectionId(connectionId);

		searchEngineAdapter.execute(closeIndexRequest);
	}

	private void _deleteIndex(String indexName, String connectionId) {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		deleteIndexRequest.setConnectionId(connectionId);

		searchEngineAdapter.execute(deleteIndexRequest);
	}

	private boolean _isFollowingActive(String connectionId, String indexName) {
		try {
			FollowInfoCCRRequest followInfoCCRRequest =
				new FollowInfoCCRRequest(indexName);

			followInfoCCRRequest.setConnectionId(connectionId);

			FollowInfoCCRResponse followInfoCCRResponse =
				searchEngineAdapter.execute(followInfoCCRRequest);

			FollowInfoStatus followInfoStatus =
				followInfoCCRResponse.getFollowInfoStatus();

			if (followInfoStatus == FollowInfoStatus.ACTIVE) {
				return true;
			}
		}
		catch (RuntimeException runtimeException) {
			if (_log.isDebugEnabled()) {
				_log.debug(runtimeException);
			}
		}

		return false;
	}

	private void _pauseFollow(String indexName, String connectionId) {
		PauseFollowCCRRequest pauseFollowCCRRequest = new PauseFollowCCRRequest(
			indexName);

		pauseFollowCCRRequest.setConnectionId(connectionId);

		searchEngineAdapter.execute(pauseFollowCCRRequest);
	}

	private void _putFollow(
		String remoteClusterAlias, String indexName, String connectionId) {

		PutFollowCCRRequest putFollowRequest = new PutFollowCCRRequest(
			remoteClusterAlias, indexName, indexName);

		putFollowRequest.setConnectionId(connectionId);
		putFollowRequest.setWaitForActiveShards(1);

		searchEngineAdapter.execute(putFollowRequest);
	}

	private void _unfollow(String indexName, String connectionId) {
		UnfollowCCRRequest unfollowCCRRequest = new UnfollowCCRRequest(
			indexName);

		unfollowCCRRequest.setConnectionId(connectionId);

		searchEngineAdapter.execute(unfollowCCRRequest);
	}

	private void _updateSettings(
		String connectionId, String remoteClusterAlias,
		String remoteClusterSeedNodeTransportAddress) {

		UpdateSettingsClusterRequest updateSettingsClusterRequest =
			new UpdateSettingsClusterRequest();

		updateSettingsClusterRequest.setConnectionId(connectionId);
		updateSettingsClusterRequest.setPersistentSettings(
			HashMapBuilder.put(
				"cluster.remote." + remoteClusterAlias + ".seeds",
				remoteClusterSeedNodeTransportAddress
			).build());

		searchEngineAdapter.execute(updateSettingsClusterRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CrossClusterReplicationHelperImpl.class);

}