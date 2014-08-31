var app = angular.module("showcaseControllers", [], angular.noop);

app.controller("MainCtrl", function($scope) {
	$scope.menu = [
		{id:1, name:"欢迎", url:"welcome"},
		{id:2, name:"用户列表", url:"users"},
		{id:3, name:"查找用户", url:"users/searcher"},
		{id:4, name:"添加用户", url:"users/editor"},
	];
});

app.controller("ListUserCtrl", ["$scope", "userService", 
function($scope, userService) {
    $scope.users = userService.query();
    
    $scope.remove = function(user){
    	userService.remove({ id: user.id }, function (success) {
		    $scope.users.splice($scope.users.indexOf(user), 1 );
		});
    };
}]);

app.controller("ShowUserCtrl", function($scope, $routeParams, userService) {
    var userId = $routeParams.id;
    
    // $scope.user = userService.get({id: userId});
    userService.get({id: userId}, function(data) {
		$scope.user = data;
	});
});

app.controller("EditUserCtrl", function($scope, $routeParams, userService) {
    $scope.master = {};
    $scope.user = {};
    
    if(angular.isDefined($routeParams.id)){
	    var userId = $routeParams.id;
	    userService.get({id: userId}, function(success){
	        $scope.user = success;
	    	$scope.master = angular.copy($scope.user);
	    });
    }
    
    $scope.save = function(user){
    	userService.save({}, user, function(success){
    	    $scope.user = success;
	   		$scope.master= angular.copy($scope.user);
	    });
    };
    
    $scope.reset = function(){
	    $scope.user = angular.copy($scope.master);
    };
	
	$scope.isUnchanged = function(user) {
	    return angular.equals($scope.user, $scope.master);
	};
    
});

app.controller("SearchUserCtrl", function($scope, userService) {
    $scope.search = function(username){
    	$scope.users = userService.search({username: username});
    };
});