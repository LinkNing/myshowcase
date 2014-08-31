var showcaseServices = angular.module('showcaseServices', [ 'ngResource' ]);

showcaseServices.factory('userService', [ '$resource', function($resource) {
    var actions = {
      //get: {method: 'GET'},
      create: {method: 'POST'},
      update: {method: 'PUT'},
      //save: {method: 'POST'},
      //remove: {method: 'DELETE'},
      //query: {method: 'GET', isArray : true},
      //search: {method: 'GET', params: {searchController:"searcher"}, url:"", isArray : true}
      search: {method: 'GET', isArray : true}
    };

	//return $resource('api/users/:searchController:id', {}, actions);
	return $resource('api/users/:id', {}, actions);
} ]);