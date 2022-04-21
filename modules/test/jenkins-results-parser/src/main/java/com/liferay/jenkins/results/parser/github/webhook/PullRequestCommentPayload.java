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

package com.liferay.jenkins.results.parser.github.webhook;

import com.liferay.jenkins.results.parser.PullRequest;
import com.liferay.jenkins.results.parser.PullRequestFactory;

import org.json.JSONObject;

/**
 * @author Peter Yoo
 */
public class PullRequestCommentPayload extends PullRequestPayload {

	public PullRequestCommentPayload(JSONObject jsonObject) {
		super(jsonObject);

		comment = new PullRequest.Comment(jsonObject.getJSONObject("comment"));
	}

	public PullRequest.Comment getComment() {
		return comment;
	}

	@Override
	public PullRequest getPullRequest() {
		if (pullRequest == null) {
			pullRequest = PullRequestFactory.newPullRequest(
				get("issue/pull_request/html_url"));
		}

		return pullRequest;
	}

	protected PullRequest.Comment comment;

}