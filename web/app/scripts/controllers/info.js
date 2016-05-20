'use strict';

/**
 * @ngdoc function
 * @name testApp.controller:InfoCtrl
 * @description
 * # InfoCtrl
 * Controller of the testApp
 */
angular.module('korpu')
    .controller('InfoCtrl', function ($scope, $http) {
        $http.get('http://localhost/proxy.php?url=http://localhost:8080/api/v1/info').success(function (data) {
                    $scope.dropbox_nom = data.dropbox.display_name;
                    $scope.dropbox_country = data.dropbox.country;
                    $scope.dropbox_email = data.dropbox.email;
                    $scope.dropbox_quotaInfo = data.dropbox.quota_info.quota;
                    
                    $scope.google_nom = data.google.user.displayName;
                    $scope.google_country = data.google.languageCode;
                    $scope.google_email = data.google.user.emailAddress;
                    $scope.google_quotaInfo = data.google.quotaBytesTotal;
        });
    });
    
    // tintin milou
    // plugin go plus
    
    