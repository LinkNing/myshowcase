module.exports = function(config){
  config.set({

    basePath : '../',

    files : [
      'app/bower_components/angular/angular.js',
      'app/bower_components/angular-route/angular-route.js',
      'app/bower_components/angular-mocks/angular-mocks.js',
      'app/bower_components/angular-resource/angular-resource.js',
      'app/js/**/*.js',
      'test/unit/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter',
		    'karma-coverage'
            ],

	reporters: ['progress','junit', 'coverage'],

	preprocessors : {'app/js/**/*.js': 'coverage'},

	junitReporter : {
      outputFile: 'target/report/unit.xml',
      suite: 'unit'
    },
	
	coverageReporter: {
		type : 'html',
		dir : 'target/coverage/'
	}

  });
};
