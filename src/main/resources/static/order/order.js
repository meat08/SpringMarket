angular.module('app').controller('orderController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/market';

    $scope.fillOrder = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET',
            params: {
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.OrdersPage = response.data;
                $scope.generatePagination(pageIndex, $scope.OrdersPage.totalPages);
            });
    };

    $scope.generatePagination = function(page, totalPage) {
        $scope.pDisabled = totalPage > 1;
        $scope.totalItems = totalPage * 5;
        $scope.currentPage = page;
        $scope.maxSize = 5;
        $scope.itemsPerPage = 5;
    }

    $scope.fillOrder();
});