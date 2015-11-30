angular.module('adminApp', [ 'ngRoute' ])

.config(function($routeProvider,$locationProvider) {
	
	$routeProvider.when('/admin/', {
		controller : 'HomeContentController',
		template : '<div></div>'
	}).when('/userListView/', {
		controller : 'UserListContentController',
		templateUrl : "admin/userListView"
	}).otherwise({
		redirectTo : '/admin/'
	});
	
})

.controller('MainContorller', ['$route', '$routeParams', '$location',
function($scope) {
	
}])

.controller('HomeContentController', function($scope) {
	
})

.controller('UserListContentController', function($scope,$http) {
	$http.get('admin/userList').success(function(data, status, headers, config) {
	    $scope.users = data;
	}).error(function(data, status, headers, config) {
	    console.log(data);
	});
})