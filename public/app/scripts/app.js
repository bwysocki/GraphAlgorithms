'use strict';

/**
 * @ngdoc overview
 * @name graphAlgorithms
 * @description
 * # graphAlgorithms
 *
 * Main module of the application.
 */
var app = angular
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
	      .when('/dfs', {
	    	  templateUrl: 'app/views/dfs.html',
	          controller: 'DfsCtrl'
	      })
	      .when('/minmax', {
	    	  templateUrl: 'app/views/minmax.html',
	          controller: 'MinMaxCtrl'
	      })
	      .otherwise({
	        redirectTo: '/'
	      });
  });

app.controller("MenuController", function($scope, $location) {
	  $scope.getClass = function(path) {
		  	if ($location.path() == path) {
		      return "active"
		    } else {
		      return ""
		    }
		}
});

app.factory('GraphUtils', function(){

	var refreshSigma = function(step, solutionString, solution, sigma){
		  var nodes = sigma.graph.nodes();
		  var edges = sigma.graph.edges();

		  var e = solutionString.replace(/-/g,'').replace(/ /g,'');

		  for (var i = 0; i < nodes.length; i++){
			  if (solutionString.indexOf(nodes[i].id) > -1){
				  nodes[i].color = "#FF0000";
				  nodes[i].size = 4;
			  }else{
				  nodes[i].color = "#000000";
				  nodes[i].size = 3;
			  }
		  }

		  var edge = solution[step].PATH;
		  for (var j = 0; j < edges.length; j++){
			  if (edges[j].id == edge){
				  edges[j].color = "#FF0000";
			  }else{
				  edges[j].color = "#000000";
			  }
		  }

		  sigma.refresh();
	  }

	return {
		nextStepNodePath : function(step, solution, sigma){
			if (step < solution.length-1)step++;
			return {
				step : step,
				solution : this.showStepNodePath(step, solution, sigma)

			}
		},
		prevStepNodePath : function(step, solution, sigma){
			if (step != 0) step--;
			return {
				step : step,
				solution : this.showStepNodePath(step, solution, sigma)

			}
		},
		showStepNodePath : function(step, solution, sigma){
			  var solutionAsString = "";
			  for (var i = 0; i <= step; i++){
				  solutionAsString += solution[i].NODE;
				  if (i != solution.length-1){
					  solutionAsString += " - ";
				  }
			  }
			  refreshSigma(step, solutionAsString, solution, sigma);
			  return solutionAsString;
		  }
	};
});
