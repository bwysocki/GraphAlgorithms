'use strict';

/**
 * @ngdoc function
 * @name graphAlgorithms.controller:AboutCtrl
 * @description
 * # BfsCtrl
 * Controller of the graphAlgorithms
 */
angular.module('graphAlgorithms')
  .controller('BfsCtrl', function ($scope, $http, GraphUtils) {

	  $scope.showInitPanel = true;
	  $scope.startNode = "A";

	  $http.get('/bfs').success(function(data) {
          $scope.graph = data;

          sigma.settings.labelThreshold = 0;
          sigma.settings.sideMargin = 1;
          sigma.settings.edgesPowRatio = 1.1;

          $scope.sigma = new sigma({
        	  graph: $scope.graph,
			  container: 'container'
          });
      });

	  $scope.initBFS = function(){
		  $scope.showInitPanel = !$scope.showInitPanel;

		  $http.get('/bfsSolution/'+$scope.startNode).success(function(data) {
			  $scope.bfsSolution = data;
			  $scope.step = 0;
			  $scope.visibleBfsSolution = GraphUtils.showStepNodePath($scope.step, $scope.bfsSolution, $scope.sigma)
		  });
	  }

	  $scope.reInit = function(){
		  $scope.showInitPanel = !$scope.showInitPanel;
	  }

	  $scope.next = function(){
		  var r = GraphUtils.nextStepNodePath($scope.step, $scope.bfsSolution, $scope.sigma);
		  $scope.visibleBfsSolution = r.solution;
		  $scope.step = r.step;;
	  }

	  $scope.prev = function(){
		  var r = GraphUtils.prevStepNodePath($scope.step, $scope.bfsSolution, $scope.sigma);
		  $scope.visibleBfsSolution = r.solution;
		  $scope.step = r.step;;
	  }

  });
