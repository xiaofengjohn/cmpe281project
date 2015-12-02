angular.module('userApp', ['chart.js','ngRoute'])

.config(function($routeProvider,$locationProvider) {
	
	$routeProvider.when('/user/', {
		controller : 'HomeContentController',
		template : '<div></div>'
	}).when('/addSensorView/', {
		controller : 'AddSensorViewController',
		templateUrl : "user/addSensorView"
	}).when('/sensorListView/', {
		controller : 'SensorListViewController',
		templateUrl : "user/sensorListView"
	}).when('/sensorDataListView/:sensorId', {
		controller : 'SensorDataListViewController',
		templateUrl : "user/sensorDataListView"
	}).when('/usageView/', {
		controller : 'UsageViewController',
		templateUrl : "user/usageView"
	}).otherwise({
		redirectTo : '/user/'
	});
	
})

.controller('AddSensorViewController', function($scope,$http,$location) {

	$scope.sensor = {};

	$scope.addSensor = function(){
		if($scope.sensor.sensorName){
			$http.post('user/sensor/',$scope.sensor.sensorName ).success(function(data, status, headers, config) {
				$location.path("/sensorListView/");
			}).error(function(data, status, headers, config) {
				console.log(data);
			});
		}
	}

})

.controller('SensorListViewController', function($scope,$http,$location) {

	$http.get('user/sensor').success(function(data, status, headers, config) {
		$scope.sensors = data;
	}).error(function(data, status, headers, config) {
		console.log(data);
	});

	$scope.deleteSensor = function(sensor){
		if(!sensor){
			return;
		}
		var c = confirm("Do you need to delete sensor with sensor id : " + sensor.id);
		if(c){
			$http.get('user/sensor/' + sensor.id + "/delete").success(function(data, status, headers, config) {
				$scope.sensors = data;
			}).error(function(data, status, headers, config) {
				console.log(data);
			});
		}
	}
	
	$scope.stopSensor = function(sensor){
		if(!sensor){
			return;
		}
		var c = confirm("Do you need to stop sensor with sensor id : " + sensor.id);
		if(c){
			$http.get('user/sensor/' + sensor.id + "/stop").success(function(data, status, headers, config) {
				$scope.sensors = data;
			}).error(function(data, status, headers, config) {
				console.log(data);
			});
		}
	}
	
	$scope.startSensor = function(sensor){
		if(!sensor){
			return;
		}
		var c = confirm("Do you need to start sensor with sensor id : " + sensor.id);
		if(c){
			$http.get('user/sensor/' + sensor.id + "/start").success(function(data, status, headers, config) {
				$scope.sensors = data;
			}).error(function(data, status, headers, config) {
				console.log(data);
			});
		}
	}

	$scope.viewData = function(sensor){
		if(!sensor){
			return;
		}
		$location.path('/sensorDataListView/' + sensor.id);
	}

})

.controller('SensorDataListViewController', function($scope,$routeParams,$http,$interval) {

	$scope.params = $routeParams;
	
	$scope.chart = {};
	
	$scope.chart.labels = [];
	for(var i = 1;i<=30;i++){
		$scope.chart.labels.push(i);
	}
	
	$scope.chart.series = ["Temperature","Blood Pressure","Heart Rate"];
	
	var fillChartData = function(data){
		if(!data||data.length==0){
			return;
		}
		$scope.chart.data = [];
		$scope.chart.data[0] = [];
		$scope.chart.data[1] = [];
		$scope.chart.data[2] = [];
		
		for(var i=0;i<(data.length>30?30:data.length);i++){
			$scope.chart.data[0].splice(0,0,data[i].temperature);
			$scope.chart.data[1].splice(0,0,data[i].bloodpressure);
			$scope.chart.data[2].splice(0,0,data[i].heartrate);
		}
	}

	$http.get('user/sensor/' + $scope.params.sensorId + '/data').success(function(data, status, headers, config) {
		$scope.sensorData = data;
		
		//fill chart data
		fillChartData(data);
		
	}).error(function(data, status, headers, config) {
		console.log(data);
	});
	
	var stop = $interval(function() {
        
		$http.get('user/sensor/' + $scope.params.sensorId + '/data').success(function(data, status, headers, config) {
			$scope.sensorData = data;
			
			//fill chart data
			fillChartData(data);
			
		}).error(function(data, status, headers, config) {
			console.log(data);
		});
		
     }, 10000);
	
	

})

.controller('UsageViewController', function($scope,$http) {
	
	$scope.paymentInfo = {};
	
	$scope.usage = {};
	
	$http.get('user/usage/').success(function(data, status, headers, config) {
		$scope.usage = data;
	}).error(function(data, status, headers, config) {
		console.log(data);
	});
	
	$scope.pay = function(){
		if($scope.paymentInfo){
			$http.get('user/credit/' + $scope.paymentInfo.credit).success(function(data, status, headers, config) {
				$scope.usage = data;
				$scope.paymentInfo = {};
			}).error(function(data, status, headers, config) {
				console.log(data);
			});
		}
	}
	
})

.controller('HomeContentController', function($scope) {
	
})

.controller('MainContorller', function($scope,$http) {

})

