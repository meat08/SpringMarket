angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/market';

    $scope.getProfile = function () {
        $http({
            url: contextPath + '/api/v1/profiles',
            method: 'GET'
        })
            .then(function (response) {
                $scope.profile = response.data;
            });
    };

    $scope.editProfile = function() {
        $scope.isEdit = !$scope.isEdit;
    };

    $scope.submitEditProfile = function () {
        $http({
            url: contextPath + '/api/v1/profiles',
            method: 'POST',
            params: {
                firstName: $scope.profile ? $scope.profile.firstName : null,
                lastName: $scope.profile ? $scope.profile.lastName : null,
                email: $scope.profile ? $scope.profile.email : null,
                birthday: $scope.profile ? $scope.profile.birthday : null,
                phoneNumber: $scope.profile ? $scope.profile.phoneNumber : null,
                address: $scope.profile ? $scope.profile.address : null,
                password: $scope.profile ? $scope.profile.password : null
            }
        })
        .then(function (response) {
            if (response.data === 'UNAUTHORIZED') {
                alert("Неверный пароль!");
                return;
            }
            $scope.profile = null;
            $scope.getProfile();
            $scope.editProfile();
        });
    };

    $scope.getProfile();
});