'use strict';

/* Controllers */
var korpu = angular.module('korpu', []);

korpu.controller('FileListCtrl', ['$scope', '$http',
  function ($scope, $http) {
    $http.get('http://localhost/proxy.php?url=http://localhost:8080/api/v1/getFiles').success(function (data) {
      $scope.google = data.google.files;
      $scope.dropbox = data.dropbox.contents;

    });
  }]);