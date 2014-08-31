var showcaseFilters = angular.module('showcaseFilters', []);

showcaseFilters.filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
});