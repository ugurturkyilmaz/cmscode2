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

package com.liferay.portal.workflow.kaleo.metrics.integration.internal.model.listener;

import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.workflow.kaleo.metrics.integration.internal.helper.IndexerHelper;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.metrics.model.DeleteNodeRequest;
import com.liferay.portal.workflow.metrics.search.index.NodeWorkflowMetricsIndexer;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoTaskModelListener extends BaseKaleoModelListener<KaleoTask> {

	@Override
	public void onAfterCreate(KaleoTask kaleoTask) {
		KaleoDefinitionVersion kaleoDefinitionVersion =
			getKaleoDefinitionVersion(kaleoTask.getKaleoDefinitionVersionId());

		if (Objects.isNull(kaleoDefinitionVersion)) {
			return;
		}

		_nodeWorkflowMetricsIndexer.addNode(
			_indexerHelper.createAddNodeRequest(
				kaleoDefinitionVersion, kaleoTask));
	}

	@Override
	public void onAfterRemove(KaleoTask kaleoTask) {
		DeleteNodeRequest.Builder builder = new DeleteNodeRequest.Builder();

		_nodeWorkflowMetricsIndexer.deleteNode(
			builder.companyId(
				kaleoTask.getCompanyId()
			).nodeId(
				kaleoTask.getKaleoTaskId()
			).build());
	}

	@Reference
	private IndexerHelper _indexerHelper;

	@Reference
	private NodeWorkflowMetricsIndexer _nodeWorkflowMetricsIndexer;

}