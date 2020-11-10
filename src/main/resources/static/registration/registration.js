angular.module('app').controller('registrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8180/market';

    $scope.submitCreateNewUser = function () {
        $http.put(contextPath + '/registration', $scope.newUser)
            .then(function (response) {
                $scope.newUser = null;
                $location.path('/auth');
            });
    };
});