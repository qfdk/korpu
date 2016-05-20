'use strict';

/**
 * @ngdoc function
 * @name testApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the testApp
 */
angular.module('korpu')
  .controller('DashboardCtrl', ['$scope', '$http',
    function ($scope, $http) {
      $http.get('http://localhost/proxy.php?url=http://localhost:8080/api/v1/getFiles').success(function (data) {

        if (data.dropbox !== undefined) {
          $scope.dropbox = data.dropbox.contents;
        }
        if (data.google !== undefined) {
          $scope.google = data.google.files;
        }
      });
    }]);
