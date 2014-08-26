'use strict';

/**
 * @ngdoc function
 * @name graphAlgorithms.controller:AboutCtrl
 * @description
 * # BfsCtrl
 * Controller of the graphAlgorithms
 */
angular.module('graphAlgorithms')
  .controller('BfsCtrl', function ($scope, $http) {

	  $http.get('http://localhost:8080/bfs').success(function(data) {
          $scope.graph = data;

          sigma.settings.labelThreshold = 0;
          sigma.settings.sideMargin = 1;
          sigma.settings.edgesPowRatio = 1.1;

          var s = new sigma({
        	  graph: $scope.graph,
			  container: 'container'
          });

          console.log(s);


      });


  });
