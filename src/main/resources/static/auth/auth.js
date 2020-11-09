angular.module('app').controller('authController', function ($scope, $http, $localStorage, $location, jwtHelper) {
    const contextPath = 'http://localhost:8180/market';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/');
                }
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    };

    $scope.tryToLogout = function () {
        $location.path('/');
        $scope.clearUser();
        if ($scope.user != null) {
            if ($scope.user.username) {
                $scope.user.username = null;
            }
            if ($scope.user.password) {
                $scope.user.password = null;
            }
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        return !!$localStorage.currentUser;
    };

    $scope.registration = function () {
        $location.path('/registration');
    };

    $scope.getUserRole = function() {
        tokenPayload = jwtHelper.decodeToken($localStorage.currentUser.token);
        $scope.userRoles = tokenPayload.role;
    };

    $scope.isUserRole = function(role) {
        $scope.getUserRole();
        for (var i = 0; i < $scope.userRoles.length; i++) {
            if (angular.equals(role, $scope.userRoles[i])) {
                return true;
            }
        }
    };

    $scope.isAdmin = function () {
        return $scope.isUserRole("ROLE_ADMIN");
    };

});