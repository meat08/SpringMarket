angular.module('app').controller('registrationController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8180/market';

    $scope.submitCreateNewUser = function () {
        $http.post(contextPath + '/registration', $scope.newUser)
            .then(function (response) {
                $scope.newUser = null;
                $window.location.href = '#!/auth';
            });
    };
});