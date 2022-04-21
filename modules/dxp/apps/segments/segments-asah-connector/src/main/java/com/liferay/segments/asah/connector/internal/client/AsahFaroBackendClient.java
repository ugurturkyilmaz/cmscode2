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

package com.liferay.segments.asah.connector.internal.client;

import com.liferay.segments.asah.connector.internal.client.model.DXPVariants;
import com.liferay.segments.asah.connector.internal.client.model.Experiment;
import com.liferay.segments.asah.connector.internal.client.model.ExperimentSettings;
import com.liferay.segments.asah.connector.internal.client.model.Individual;
import com.liferay.segments.asah.connector.internal.client.model.IndividualSegment;
import com.liferay.segments.asah.connector.internal.client.model.Results;
import com.liferay.segments.asah.connector.internal.client.model.Topic;
import com.liferay.segments.asah.connector.internal.client.util.OrderByField;

import java.util.List;

/**
 * @author Shinn Lok
 * @author David Arques
 * @author Sarai Díaz
 */
public interface AsahFaroBackendClient {

	/**
	 * Adds an {@link Experiment}.
	 *
	 * @param  companyId the company ID
	 * @param  experiment experiment to be created
	 * @return the created experiment
	 * @review
	 */
	public Experiment addExperiment(long companyId, Experiment experiment);

	/**
	 * Calculates the estimated duration in days for the experiment
	 * <code>experimentId</code> given a {@link ExperimentSettings}
	 *
	 * @param  companyId the company ID
	 * @param  experimentId the ID of the experiment
	 * @param  experimentSettings settings for the calculation
	 * @return estimated duration for the experiment in days
	 * @review
	 */
	public Long calculateExperimentEstimatedDaysDuration(
		long companyId, String experimentId,
		ExperimentSettings experimentSettings);

	/**
	 * Deletes an {@link Experiment}.
	 *
	 * @param  companyId the company ID
	 * @param  experimentId the ID of the experiment to be removed
	 * @review
	 */
	public void deleteExperiment(long companyId, String experimentId);

	/**
	 * Returns the ID of the data source connected to the client.
	 *
	 * @param  companyId the company ID
	 * @return the ID of the data source connected to the client
	 */
	public String getDataSourceId(long companyId);

	/**
	 * Returns the individual matching the primary key for the data source
	 * {@link #getDataSourceId(long)}.
	 *
	 * @param  companyId the company ID
	 * @param  individualPK the primary key of the individual
	 * @return the individual matching the primary key, or {@code null} if no
	 *         matches were found
	 */
	public Individual getIndividual(long companyId, String individualPK);

	/**
	 * Returns the results of an individual that is a member of an individual
	 * segment.
	 *
	 * @param  companyId the company ID
	 * @param  individualSegmentId the individual segment's ID
	 * @param  cur the current page (one-based numbering)
	 * @param  delta the page size
	 * @param  orderByFields the sort fields
	 * @return the results of an individual that is a member of an individual
	 *         segment
	 */
	public Results<Individual> getIndividualResults(
		long companyId, String individualSegmentId, int cur, int delta,
		List<OrderByField> orderByFields);

	/**
	 * Returns the results of an active individual segment with members.
	 *
	 * @param  companyId the company ID
	 * @param  cur the current page (one-based numbering)
	 * @param  delta the page size
	 * @param  orderByFields the sort fields
	 * @return the results of an active individual segment with members
	 */
	public Results<IndividualSegment> getIndividualSegmentResults(
		long companyId, int cur, int delta, List<OrderByField> orderByFields);

	/**
	 * Returns the results for interest terms for the user.
	 *
	 * @param  companyId the company ID
	 * @param  userId the user's ID
	 * @return the results for interest terms for the user
	 */
	public Results<Topic> getInterestTermsResults(
		long companyId, String userId);

	/**
	 * Updates an {@link Experiment}.
	 *
	 * @param  companyId the company ID
	 * @param  experiment experiment to be updated
	 * @review
	 */
	public void updateExperiment(long companyId, Experiment experiment);

	/**
	 * Updates a set of {@link DXPVariants}.
	 *
	 * @param  companyId the company ID
	 * @param  experimentId the experiment ID
	 * @param  dxpVariants list of experiment variants
	 * @review
	 */
	public void updateExperimentDXPVariants(
		long companyId, String experimentId, DXPVariants dxpVariants);

}