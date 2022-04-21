<%--
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
--%>

<%@ include file="/init.jsp" %>

<blockquote>
	<p>Buttons communicate an action to happen on user interaction.</p>
</blockquote>

<h3>TYPES</h3>

<table class="table">
	<thead>
		<tr>
			<th>TYPE</th>
			<th>USAGE</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td>
				<clay:row
					cssClass="flex-md-nowrap mb-2"
				>
					<clay:col><clay:button label="Primary" /></clay:col>
					<clay:col><clay:button aria-label="Workflow" icon="workflow" /></clay:col>
				</clay:row>

				<clay:row
					cssClass="flex-md-nowrap"
				>
					<clay:col><clay:button disabled="<%= true %>" label="Primary" /></clay:col>
					<clay:col><clay:button aria-label="Workflow" disabled="<%= true %>" icon="workflow" /></clay:col>
				</clay:row>
			</td>
			<td>
				<strong>Primary</strong>: The primary button is always use for the most important actions. There can't be two primary actions together or near by.
			</td>
		</tr>
		<tr>
			<td>
				<clay:row
					cssClass="flex-md-nowrap mb-2"
				>
					<clay:col><clay:button displayType="secondary" label="Secondary" /></clay:col>
					<clay:col><clay:button aria-label="Wiki" displayType="secondary" icon="wiki" /></clay:col>
				</clay:row>

				<clay:row
					cssClass="flex-md-nowrap"
				>
					<clay:col><clay:button disabled="<%= true %>" displayType="secondary" label="Secondary" /></clay:col>
					<clay:col><clay:button aria-label="Wiki" disabled="<%= true %>" displayType="secondary" icon="wiki" /></clay:col>
				</clay:row>
			</td>
			<td>
				<strong>Secondary</strong>: The secondary button is always use for the secondary actions. There can be several secondary actions near by.
			</td>
		</tr>
		<tr>
			<td>
				<clay:row
					cssClass="flex-md-nowrap mb-2"
				>
					<clay:col><clay:button displayType="borderless" label="Borderless" /></clay:col>
					<clay:col><clay:button aria-label="Page Template" displayType="borderless" icon="page-template" /></clay:col>
				</clay:row>

				<clay:row
					cssClass="flex-md-nowrap"
				>
					<clay:col><clay:button disabled="<%= true %>" displayType="borderless" label="Borderless" /></clay:col>
					<clay:col><clay:button aria-label="Page Template" disabled="<%= true %>" displayType="borderless" icon="page-template" /></clay:col>
				</clay:row>
			</td>
			<td>
				<strong>Borderless</strong>: Use in those cases as toolbars where the secondary button would be too heavy for the pattern design. In this way the design gets cleaner.
			</td>
		</tr>
		<tr>
			<td>
				<clay:row
					cssClass="flex-md-nowrap mb-2"
				>
					<clay:col><clay:button displayType="link" label="Link" /></clay:col>
					<clay:col><clay:button aria-label="Add Role" displayType="link" icon="add-role" /></clay:col>
				</clay:row>

				<clay:row
					cssClass="flex-md-nowrap"
				>
					<clay:col><clay:button disabled="<%= true %>" displayType="link" label="Link" /></clay:col>
					<clay:col><clay:button aria-label="Add Role" disabled="<%= true %>" displayType="link" icon="add-role" /></clay:col>
				</clay:row>
			</td>
			<td>
				<strong>Link</strong>: Used for many Cancel actions.
			</td>
		</tr>
	</tbody>
</table>

<h3>VARIATIONS</h3>

<clay:row
	cssClass="text-center"
>
	<clay:col
		md="2"
	>
		<clay:button
			icon="share"
			label="Share"
		/>

		<div>Icon and Text Button</div>
	</clay:col>

	<clay:col
		md="2"
	>
		<clay:button
			displayType="secondary"
			icon="indent-less"
			monospaced="<%= true %>"
		/>

		<div>Monospaced Button</div>
	</clay:col>

	<clay:col
		md="4"
	>
		<clay:button
			block="<%= true %>"
			label="Button"
		/>

		<div>Block Level Button</div>
	</clay:col>

	<clay:col
		md="2"
	>
		<clay:button
			displayType="secondary"
			icon="plus"
			monospaced="<%= true %>"
		/>

		<div>Plus Button</div>
	</clay:col>

	<clay:col
		md="2"
	>
		<clay:button
			displayType="borderless"
			icon="ellipsis-v"
			monospaced="<%= true %>"
		/>

		<div>Action Button</div>
	</clay:col>
</clay:row>