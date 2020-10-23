angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/market';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                category: $scope.filter ? [$scope.filter.category] : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages);
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

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                console.log('ok');
            });
    }

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.clearFilters = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                if ($scope.filter != null) {
                    $scope.filter = null;
                }
                $scope.fillTable();
            });
    };

    $scope.displayFilters = function() {
        $scope.isShow = !$scope.isShow;
    }

    $scope.fillTable();
    $scope.fillCategories();
});