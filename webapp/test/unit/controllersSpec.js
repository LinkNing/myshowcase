"use strict";

ddescribe("Showcase controllers", function() {

	beforeEach(function() {
	  jasmine.addMatchers({
	    toEqualData: function() {
	      return {
	        compare: function(actual, expected) {
	          var result = {
	            pass: angular.equals(actual, expected)
	          };
	          if (result.pass) {
	            result.message = actual + " is equals " + expected;
	          } else {
	            result.message = actual + " is not equals " + expected;
	          }
	          return result;
	        }
	      };
	    }
	  });
	});

	beforeEach(module("showcaseServices"));
	beforeEach(module("showcaseControllers"));

	describe("ListUserCtrl", function() {
		var scope, ctrl, $httpBackend;

	    beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
	      	$httpBackend = _$httpBackend_;
	      	$httpBackend.whenGET("api/users").
			respond([{id : 1, name : "a", password : "1"}, 
					 {id : 2, name : "b", password : "2"}, 
					 {id : 3, name : "c", password : "3"}, 
			]);
			$httpBackend.whenDELETE("api/users/1").respond({});
	
	      	scope = $rootScope.$new();
	      	ctrl = $controller("ListUserCtrl", {$scope: scope});
	    }));
	    
	    afterEach(function() {
			$httpBackend.verifyNoOutstandingExpectation();
       		$httpBackend.verifyNoOutstandingRequest();
       });

		it("should create 'users' model with 3 user", function() {
			$httpBackend.flush();
			expect(scope.users.length).toBe(3);
		});
		
		it("should remove a user whose id is 1", function() {
			scope.remove({id:1});
			$httpBackend.flush();
			expect(scope.users.length).toBe(2);
		});
	});

	describe("ShowUserCtrl", function() {
		var scope, ctrl, $httpBackend;

	    beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
	      $httpBackend = _$httpBackend_;
	      $httpBackend.expectGET("api/users/1").
			respond({id : 1, name : "a", password : "1"});
	
	      scope = $rootScope.$new();
	      ctrl = $controller("ShowUserCtrl", {$scope: scope, $routeParams: {id:1}});
	    }));

		it("should get the user whose id is 1", function() {
		    $httpBackend.flush();
			expect(scope.user).toEqualData({id : 1, name : "a", password : "1"});
		});
	});

	xdescribe("EditUserCtrl", function() {
	});

	xdescribe("SearchUserCtrl", function() {
	});

});