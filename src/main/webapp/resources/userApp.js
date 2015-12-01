angular.module('userApp', [ 'ngRoute' ])

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

.controller('SensorListViewController', function($scope,$http) {

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

})

.controller('HomeContentController', function($scope) {
	
})


.controller('MainContorller', function($scope,$http) {

})

