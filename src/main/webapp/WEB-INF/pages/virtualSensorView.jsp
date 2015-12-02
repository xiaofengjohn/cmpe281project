<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" ng-app="virtualSensorApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  		<base href=" <%=basePath%>"/> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  		<link href="resources/css" rel="stylesheet" type="text/css"/>
  		<title>Mobile Health Sensor, Group #1</title>
  		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="resources/bootstrap-custom.css" type="text/css"/>
       	<link rel="stylesheet" href="resources/icomoon.css" type="text/css"/>
       	<link rel="stylesheet" href="resources/font-awesome.css" type="text/css"/>
       	<link rel="stylesheet" href="resources/tipsy.css" type="text/css"/>
   		<link rel="stylesheet" href="resources/docs.css" type="text/css"/>
   		<link rel="stylesheet" href="resources/pygments.css" type="text/css"/>

		<script type="text/javascript">
		    var DOCUMENTATION_OPTIONS = {
		     URL_ROOT:    '#',
		     VERSION:     '3.0',
		     COLLAPSE_INDEX: false,
		     FILE_SUFFIX: '',
		     HAS_SOURCE:  false,
		    };
		</script>
  
	    <script type="text/javascript" src="resources/jquery.js"></script>
	    <script type="text/javascript" src="resources/underscore.js"></script>
	    <script type="text/javascript" src="resources/doctools.js"></script>
	    <script type="text/javascript" src="resources/bootstrap.js"></script>
	    <script type="text/javascript" src="resources/jquery.tipsy.js"></script>
	    <script type="text/javascript" src="resources/jquery.cookie.js"></script>
	    <script type="text/javascript" src="resources/navbar.js"></script>
	    <script type="text/javascript" src="resources/angular.js"></script>
	    <script type="text/javascript" src="resources/angular-route.js"></script>
	    <script type="text/javascript" src="resources/virtualSensorApp.js"></script>

	</head>
	<body ng-controller="MainContorller">
		<header id="header-db" class="row" role="navigation">
			<div class="header-content">
				<a class="icon-menu expand-toc-icon pull-left"></a>
				<div class="logo pull-left">
					<h1>Virtual Sensor</h1>
				</div>
				
			</div>
		</header>
	
		
	
		<div class="content">
			<div class="main-column pull-left">
				<div class="document">
					<div class="documentwrapper">
						<div class="bodywrapper">
							<div class="body">
								<div class="section" style="width: 460px;" ng-show='!sensor.running'>
									<h1>Add Sensor Here!</h1>
									<div class="section" id="getting-started">

										<form class="form-horizontal">
											<div class="form-group">
												<label for="sensorID" class="col-sm-2 control-label">Sensor ID</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="sensorID"
														name="sensorID" placeholder="Sensor ID" ng-model='sensor.id'/>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-2">
													<button type="button" class="btn btn-default" ng-click='addSensor()'>Submit</button>
												</div>
											</div>
										</form>

									</div>
								</div>
								
								<div class="section" style="width: 900px;" ng-show='sensor.running'>
									<h1>Sensor {{sensor.id}} status {{sensor.status}}</h1>
									<div class="section" id="getting-started">

										<div class="col-sm-10" ng-repeat='d in dataList'>
											<span>{{'temperature : ' + (d.temperature|number:2)}}</span>
											<span>{{'blood pressure : ' + (d.bloodpressure|number:2)}}</span>
											<span>{{'heart rate : ' + (d.heartrate|number:2)}}</span>
										</div>

									</div>
								</div>
								
								<div class="footer">
									<div class="copyright">
										<p>© Group #1, Health Mobile Sensor, 2015.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
</body></html>