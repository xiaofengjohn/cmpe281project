angular.module('virtualSensorApp',[])

.controller('MainContorller', function($scope,$interval,$http) {
	
	$scope.sensor = {};
	
	$scope.dataList = [];
	
	var stop;
	
	$scope.addSensor = function(){
		if($scope.sensor.id){
			stop = $interval(function() {
	            
				$http.get('sensor/status/' + $scope.sensor.id).success(function(data) {
					if(angular.isArray(data)){
						if(data[0]=='ON'){
							
							$scope.sensor.status = 'ON';
							
							$scope.sensor.running = true;
							
							var sensorData = {'name':'sensor test','temperature':Math.random()*5 + 35,'bloodpressure':Math.random()*70 + 60,'heartrate':Math.random()*50 + 60,'sensorId':$scope.sensor.id};
							
							$http.post('sensor/uploaddata',angular.toJson(sensorData) ).success(function(data) {
								$scope.dataList.splice(0,0,sensorData)
							}).error(function(data) {
								console.log(data);
							});
							
						}else{
							$scope.sensor.status = 'OFF';
						}
					}
				}).error(function(data) {
					console.log(data);
				});
				
	          }, 10000);
		}
	}
	
})