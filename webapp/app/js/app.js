var app = angular.module('showcase', ['ngRoute', "showcaseControllers", "showcaseServices"]);

// 路由时，从上往下解析，注意顺序。如果 '/users/:id' 在 '/users/editor' 之前，则 '/users/editor' 会被解析为 id = editor。
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/welcome', {
		templateUrl : 'partials/welcome.html'
	}).when('/users', {
		templateUrl : 'partials/user/userlist.html',
		controller: "ListUserCtrl"
	}).when('/users/searcher', {
		templateUrl : 'partials/user/searchuser.html',
		controller: "SearchUserCtrl"
	}).when('/users/editor', {
		templateUrl : 'partials/user/edituser.html',
		controller: "EditUserCtrl"
	}).when('/users/:id', {
		templateUrl : 'partials/user/showuser.html',
		controller: "ShowUserCtrl"
	}).when('/users/:id/editor', {
		templateUrl : 'partials/user/edituser.html',
		controller: "EditUserCtrl"
	}).otherwise({
		redirectTo : '/welcome'
	});
} ]);