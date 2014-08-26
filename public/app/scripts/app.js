'use strict';

/**
 * @ngdoc overview
 * @name graphAlgorithms
 * @description
 * # graphAlgorithms
 *
 * Main module of the application.
 */
angular
  .module('graphAlgorithms', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/views/main.html',
        controller: 'MainCtrl'
      })
      .when('/bfs', {
    	  templateUrl: 'app/views/bfs.html',
          controller: 'BfsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
