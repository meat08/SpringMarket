angular.module('app').controller('registrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8180/market';

    $scope.submitCreateNewUser = function () {
        $http.put(contextPath + '/registration', $scope.newUser)
            .catch(function (error) {
                if (error.status === 400) {
                    alert(error.data.message);
                }
            })
            .then(function (response) {
                if (!angular.isUndefined(response)) {
                    $scope.newUser = null;
                    $location.path('/auth');
                }
            });
    };
});