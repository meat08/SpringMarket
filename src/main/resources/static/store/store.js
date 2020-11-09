angular.module('app').controller('storeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8180/market';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                category: $scope.filter && !angular.isUndefined($scope.filter.category) ? [$scope.filter.category] : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.generatePagination(pageIndex, $scope.ProductsPage.totalPages);
                $scope.alerts = [];
            });
    };

    $scope.fillCategories = function () {
        $http({
            url: contextPath + '/api/v1/categories',
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
                $scope.addCartAlert();
            });
    }

    $scope.addCartAlert = function() {
        $scope.alerts.push({type: 'success', msg: 'Товар добавлен в корзину!'});
    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.isUserLoggedIn = function () {
        return !!$localStorage.currentUser;
    };

    $scope.generatePagination = function(page, totalPage) {
        $scope.pDisabled = totalPage > 1;
        $scope.totalItems = totalPage * 5;
        $scope.currentPage = page;
        $scope.maxSize = 5;
        $scope.itemsPerPage = 5;
    }

    $scope.openFilter = function() {
        $scope.isFilterOpen = true;
        document.getElementById("filter").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    $scope.closeFilter = function() {
        $scope.isFilterOpen = false;
        document.getElementById("filter").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
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
        if ($scope.isFilterOpen) {
            $scope.closeFilter();
        } else {
            $scope.openFilter();
        }
    };

    $scope.fillTable();
    $scope.fillCategories();
});