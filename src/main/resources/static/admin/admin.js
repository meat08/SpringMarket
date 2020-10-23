angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/market';

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                alert('Добавлен новый продукт');
            });
    };

    $scope.fillCategories = function () {
        $http({
            url: contextPath + '/api/v1/products/categories',
            method: 'GET'
        })
            .then(function (response) {
                $scope.CategoriesList = response.data;
            });
    };

    $scope.fillCategories();
});