angular.module('app').controller('orderFormController', function ($scope, $http, $window) {
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

    $scope.submitCreateNewOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders/create',
            method: 'POST',
            params: {
                customerName: $scope.newOrder ? $scope.newOrder.customerName : null,
                customerPhone: $scope.newOrder ? $scope.newOrder.customerPhone : null,
                customerAddress: $scope.newOrder ? $scope.newOrder.customerAddress : null
            }
        })
            .then(function (response) {
                $scope.newOrder = null;
                $window.location.href = '#!/order';
            });
    };

    $scope.cartContentRequest();
});