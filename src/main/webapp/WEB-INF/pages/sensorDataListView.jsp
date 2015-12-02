<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="section">

	<!-- chart -->
	<h5>Open Chart</h5><input type="checkbox" ng-model="chart.open">
	<canvas id="bar" class="chart chart-line" chart-data="chart.data"
  			chart-labels="chart.labels" chart-legend="true" chart-series="chart.series" ng-show="chart.open">
	</canvas>
	<br>
	<!-- chart end -->
	
	<h1>Sensor Data List</h1>
	<div class="section" id="getting-started">
		<table border="1" class="index-table docutils">
			<colgroup>
				<col width="20%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<thead valign="bottom">
				<tr class="row-odd">
					<th class="head">Record Name</th>
					<th class="head">Temperature</th>
					<th class="head">Blood Pressure</th>
					<th class="head">Heart Rate</th>
					<th class="head">Sensor ID</th>
				</tr>
			</thead>
			<tbody valign="top">
				<tr class="row-even" ng-repeat="d in sensorData">
					<td>
						<p>{{d.name}}</p>
					</td>
					<td>
						<p>{{d.temperature | number:2}}</p>
					</td>
					<td>
						<p>{{d.bloodpressure | number:2}}</p>
					</td>
					<td>
						<p>{{d.heartrate}}</p>
					</td>
					<td>
						<p>{{d.sensorId}}</p>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
</div>