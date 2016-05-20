angular.module('korpu')
  .controller('LoginCtrl', ['$scope', '$http',
    function ($scope, $http) {
      $http.post('http://localhost/proxy.php?url=http://localhost:8080/api/v1/login').success(function (data) {
      });
    }]);