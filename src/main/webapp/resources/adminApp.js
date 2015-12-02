angular.module('adminApp', [ 'chart.js','ngRoute' ])

.config(function($routeProvider,$locationProvider) {
	
	$routeProvider.when('/upload/', {
		controller : 'UploadController',
		template : '<div></div>'
	}).when('/admin/', {
		controller : 'HomeContentController',
		template : '<div></div>'
	}).when('/userListView/', {
		controller : 'UserListContentController',
		templateUrl : "admin/userListView"
	}).when('/sensorListView/', {
		controller : 'SensorListViewController',
		templateUrl : "admin/sensorListView"
	}).when('/sensorDataListView/:sensorId', {
		controller : 'SensorDataListViewController',
		templateUrl : "admin/sensorDataListView"
	}).otherwise({
		redirectTo : '/admin/'
	});
	
})

.controller('MainContorller', ['$route', '$routeParams', '$location',
function($scope) {
	
}])

.controller('HomeContentController', function($scope) {
	
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
	
	$http.get('admin/sensor/' + $scope.params.sensorId + '/data').success(function(data, status, headers, config) {
		$scope.sensorData = data;
		
		//fill chart data
		fillChartData(data);
		
	}).error(function(data, status, headers, config) {
		console.log(data);
	});
	
})

.controller('SensorListViewController', function($scope,$http,$location) {
	
	$http.get('admin/sensor').success(function(data, status, headers, config) {
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
			$http.get('admin/sensor/' + sensor.id + "/delete").success(function(data, status, headers, config) {
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
			$http.get('admin/sensor/' + sensor.id + "/stop").success(function(data, status, headers, config) {
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
			$http.get('admin/sensor/' + sensor.id + "/start").success(function(data, status, headers, config) {
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

.controller('UploadController', function($scope,$http) {

})

.controller('UserListContentController', function($scope,$http) {
	$http.get('admin/userList').success(function(data, status, headers, config) {
	    $scope.users = data;
	}).error(function(data, status, headers, config) {
	    console.log(data);
	});

	$scope.deleteUser = function(user){
		if(user&&user.id){
			var c = confirm("Do you want to delete User with username : " + user.username);
			if(c){
				$http.get('admin/user/' + user.id + '/delete').success(function(data, status, headers, config) {
					if(angular.isString(data)){
						alert(data);
						return;
					}
					alert(angular.toJson(data));
					$scope.users = data;
				}).error(function(data, status, headers, config) {
					console.log(data);
				});
			}
		}
	}
})

