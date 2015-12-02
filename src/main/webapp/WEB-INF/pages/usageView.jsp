<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="section">
	<h1>Billing & Pay !</h1>

	<div class="section" id="getting-started">
		<table border="1" class="index-table docutils">
			<colgroup>
				<col width="50%">
				<col width="50%">
			</colgroup>
			<thead valign="bottom">
				<tr class="row-odd">
					<th class="head">Credit(Per Request)</th>
					<th class="head">Usage(Per Request)</th>
				</tr>
			</thead>
			<tbody valign="top">
				<tr class="row-even">
					<td>
						<p>{{usage.credit}}</p>
					</td>
					<td>
						<p>{{usage.dataCount}}</p>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<div class="section" id="getting-started">

		<form class="form-horizontal">
			<div class="form-group">
				<label for="lastName" class="col-sm-4 control-label">Package:</label>
				<div class="col-sm-5">
					<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-default" ng-click="paymentInfo.amount=99;paymentInfo.credit=1000000;">1M</button>
						<button type="button" class="btn btn-default" ng-click="paymentInfo.amount=199;paymentInfo.credit=3000000;">3M</button>
						<button type="button" class="btn btn-default" ng-click="paymentInfo.amount=299;paymentInfo.credit=4000000;">5M</button>
						<button type="button" class="btn btn-default" ng-click="paymentInfo.amount=499;paymentInfo.credit=10000000;">10M</button>
						<button type="button" class="btn btn-default" ng-click="paymentInfo.amount=999;paymentInfo.credit=20000000;">20M</button>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="lastName" class="col-sm-4 control-label">Account:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="lastName"
						placeholder="Account"  ng-model="paymentInfo.account">
				</div>
			</div>
			<div class="form-group">
				<label for="firstName" class="col-sm-4 control-label">Credit
					Card:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="firstName"
						placeholder="Credit Card" ng-model="paymentInfo.creditCard">
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-4 control-label">Amount($):</label>
				<div class="col-sm-5">
					<input type="number" class="form-control" min="0" id="address"
						placeholder="0" value="0" ng-disabled="true" ng-model="paymentInfo.amount">
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-4 control-label">Request:</label>
				<div class="col-sm-5">
					<input type="number" class="form-control" min="0" id="address"
						placeholder="0" value="0" ng-disabled="true" ng-model="paymentInfo.credit">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-4">
					<button type="button" class="btn btn-default" ng-disabled="!paymentInfo.credit&&!paymentInfo.creditCard&&!paymentInfo.account" ng-click="pay()">Pay!</button>
				</div>
			</div>
		</form>

	</div>
</div>