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

<style type="text/css">
	.geomap {
		margin: 10px 0;
	}

	.geomap svg {
		height: 500px !important;
		width: 100%;
	}
</style>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<chart:area-spline
				config="<%= chartSampleDisplayContext.getAreaSplineChartConfig() %>"
				id="area-spline"
			/>
		</clay:col>

		<clay:col>
			<chart:area-step
				config="<%= chartSampleDisplayContext.getAreaStepChartConfig() %>"
				id="area-step"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<chart:line
				config="<%= chartSampleDisplayContext.getLineChartConfig() %>"
				id="line"
			/>
		</clay:col>

		<clay:col>
			<chart:scatter
				config="<%= chartSampleDisplayContext.getScatterChartConfig() %>"
				id="scatter"
			/>
		</clay:col>

		<clay:col>
			<chart:spline
				config="<%= chartSampleDisplayContext.getSplineChartConfig() %>"
				id="spline"
			/>
		</clay:col>

		<clay:col>
			<chart:step
				config="<%= chartSampleDisplayContext.getStepChartConfig() %>"
				id="step"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<chart:bar
				config="<%= chartSampleDisplayContext.getBarChartConfig() %>"
				id="bar"
			/>
		</clay:col>

		<clay:col>
			<chart:combination
				config="<%= chartSampleDisplayContext.getCombinationChartConfig() %>"
				id="combination"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<chart:donut
				config="<%= chartSampleDisplayContext.getDonutChartConfig() %>"
				id="donut"
			/>
		</clay:col>

		<clay:col>
			<chart:pie
				config="<%= chartSampleDisplayContext.getPieChartConfig() %>"
				id="pie"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<chart:gauge
				config="<%= chartSampleDisplayContext.getGaugeChartConfig() %>"
				id="gauge"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col
			cssClass="geomap"
		>
			<chart:geomap
				config="<%= chartSampleDisplayContext.getGeomapConfig1() %>"
				id="geomap-default-colors"
			/>
		</clay:col>

		<clay:col
			cssClass="geomap"
		>
			<chart:geomap
				config="<%= chartSampleDisplayContext.getGeomapConfig2() %>"
				id="gemomap-custom-colors"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col
			cssClass="polling-interval"
		>
			<chart:line
				componentId="polling-interval-line-chart"
				config="<%= chartSampleDisplayContext.getPollingIntervalLineChartConfig() %>"
				id="polling-interval-line-chart"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col
			cssClass="predictive"
		>
			<chart:predictive
				componentId="predictive-chart"
				config="<%= chartSampleDisplayContext.getPredictiveChartConfig() %>"
				id="predictive-chart"
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<aui:script>
	Liferay.componentReady('polling-interval-line-chart').then((chart) => {
		chart.data = function () {
			return Promise.resolve([
				{
					data: [
						Math.random() * 100,
						Math.random() * 100,
						Math.random() * 100,
					],
					id: 'data1',
				},
				{
					data: [
						Math.random() * 100,
						Math.random() * 100,
						Math.random() * 100,
					],
					id: 'data2',
				},
			]);
		};
	});

	Liferay.componentReady('predictive-chart').then((chart) => {
		var oldData = chart.data.slice();

		setTimeout(() => {
			var newData = {
				data: [
					[230, 230, 230],
					[20, 20, 20],
					[120, 120, 120],
					[450, 450, 450],
					[70, 70, 70],
					[280, 280, 280],
					[60, 60, 60],
					[140, 140, 140],
					[220, 245, 305],
					[240, 275, 295],
					[200, 235, 325],
					[110, 145, 235],
				],
				id: 'data3',
			};

			chart.data = new Promise((resolve, reject) => {
				oldData.push(newData);
				resolve(oldData);
			});
		}, 4000);
	});
</aui:script>