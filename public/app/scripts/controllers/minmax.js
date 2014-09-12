'use strict';

/**
 * @ngdoc function
 * @name graphAlgorithms.controller:AboutCtrl
 * @description
 * # BfsCtrl
 * Controller of the graphAlgorithms
 */
angular.module('graphAlgorithms')
  .controller('MinMaxCtrl', function ($scope, $http, GraphUtils) {

	  $scope.showInitPanel = true;
	  $scope.depth = 2;

	  $http.get('/minmax').success(function(data) {
		  $scope.maxHeuristic = data.nodes[0].score;
		  data.nodes[0].extraLabel = "Node score = " + data.nodes[0].score;
		  data.nodes[0].summary = "Summary = " + data.nodes[0].score;
		  $scope.maxHeuristic = data.nodes[0].score;
		  $scope.graph = data;
		  $scope.initial = data;

          sigma.settings.labelThreshold = 0;
          sigma.settings.sideMargin = 1;
          sigma.settings.edgesPowRatio = 1.1;
          sigma.settings.enableHovering = false;

          $scope.sigma = new sigma({
        	  graph: $scope.graph,
			  container: 'container'
          });
      });

	  $scope.initMinMax = function(){
		  $scope.showInitPanel = !$scope.showInitPanel;
		  $http.get('/minmax/'+$scope.depth).success(function(data) {
			  $scope.step = 0;
			  $scope.graph = data;
		  });

	  }

	  $scope.reInit = function(){
		  $scope.showInitPanel = !$scope.showInitPanel;
		  $scope.sigma.graph.clear();
		  $scope.sigma.refresh();
		  $scope.graph = $scope.initial;
		  $scope.sigma = new sigma({
        	  graph: $scope.graph,
			  container: 'container'
          });
	  }

	  $scope.next = function(){
		  if ($scope.step >= $scope.graph.nodes.length - 1){
			  for (var i in $scope.graph.nodes){
				  var node = $scope.graph.nodes[i];
				  if (node.summary == "Summary = " + $scope.maxHeuristic){
					  var chain = node;
					  while (chain.id != 'INIT'){
						  for (var i in $scope.sigma.graph.edges()){
							  var edge = $scope.sigma.graph.edges()[i];
							  if (edge.target == chain.id){
								  for (var j in $scope.graph.nodes){
									  var n = $scope.graph.nodes[j];
									  if (n.id == edge.source){
										  chain = n;
										  edge.color = "#FF0000";
										  break;
									  }
								  }
								  break;
							  }
						  }
					  }
					  break;
				  }
			  }
			  $scope.sigma.refresh();
			  return;
		  };

		  var node = $scope.graph.nodes[++$scope.step];
		  node.extraLabel = "Node score = " + node.score;
		  node.summary = node.score;
		  var chain = node;
		  while (chain.id != 'INIT'){
			  for (var i in $scope.graph.edges){
				  var edge = $scope.graph.edges[i];
				  if (edge.target == chain.id){
					  for (var j in $scope.graph.nodes){
						  var n = $scope.graph.nodes[j];
						  if (n.id == edge.source){
							  chain = n;
							  node.summary += n.score;
							  break;
						  }
					  }
					  break;
				  }
			  }
		  }

		  if (node.summary > $scope.maxHeuristic){
			  $scope.maxHeuristic = node.summary;
		  }

		  node.summary = "Summary = " + node.summary;

		  $scope.sigma.graph.addNode(node);
		  for (var i in $scope.graph.edges){
			  var edge = $scope.graph.edges[i];
			  if (edge.target == node.id){
				  $scope.sigma.graph.addEdge(edge);
				  break;
			  }
		  }
		  $scope.sigma.refresh();
	  }

  });
