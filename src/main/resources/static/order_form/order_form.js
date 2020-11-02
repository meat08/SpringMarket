angular.module('app').controller('orderFormController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8180/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        })
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.getProfileForForm = function () {
        $http({
            url: contextPath + '/api/v1/profiles',
            method: 'GET'
        })
            .then(function (response) {
                $scope.profileForm = response.data;
                $scope.profileForm.customerName = $scope.profileForm.firstName + " " + $scope.profileForm.lastName;
            });
    };

    $scope.submitCreateNewOrder = function () {
        console.log($scope.profileForm);
        $http({
            url: contextPath + '/api/v1/orders/create',
            method: 'POST',
            params: {
                customerName: $scope.profileForm ? $scope.profileForm.customerName : null,
                customerPhone: $scope.profileForm ? $scope.profileForm.phoneNumber : null,
                customerAddress: $scope.profileForm ? $scope.profileForm.address : null
            }
        })
            .then(function (response) {
                $scope.newOrder = null;
                $location.path('/order');
            });
    };

    $scope.cartContentRequest();
    $scope.getProfileForForm();
});