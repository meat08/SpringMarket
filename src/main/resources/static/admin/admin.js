angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/market';

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                alert('Добавлен новый продукт');
            });
    };
});