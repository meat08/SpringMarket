angular.module('app').controller('storeController', function ($scope, uiGridConstants, $http) {
        const contextPath = 'http://localhost:8180/market';
        let paginationOptions = {
            pageNumber: 1,
            pageSize: 5,
            sort: null
        };

    //из-за пагинации нормально не работают фильтры (не могу нормально обновить таблицу)
    //если после применения фильтров кликнуть на любую кнопку в меню пагинации - отобразит нормально
    $scope.fillTable = function(pageNumber,size) {
        pageNumber = pageNumber > 0 ? pageNumber - 1:0;
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: pageNumber,
                s: size,
                name: $scope.filterProduct != null ? $scope.filterProduct.title : '',
                min: $scope.filterProduct != null ? $scope.filterProduct.min : '',
                max: $scope.filterProduct != null ? $scope.filterProduct.max : ''
            }
        })
            .then(function(response){
                $scope.gridOptions.data = response.data.content;
                console.log($scope.gridOptions.data);
                $scope.gridOptions.totalItems = response.data.totalElements;
            });
    }

    $scope.updateTable = function () {
        $scope.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
        // $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
        console.log($scope.gridApi)
    }

    $scope.clearFilter = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                if ($scope.filterProduct != null) {
                    $scope.filterProduct = null;
                }
                $scope.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
            });
    };

    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:true,
        useExternalPagination: true,
        enableFiltering: false,
        columnDefs: [
            { field: 'name', name: 'Наименование' },
            { field: 'price', name: 'Цена', type: 'number' }
        ],
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged(
                $scope,
                function (newPage, pageSize) {
                    paginationOptions.pageNumber = newPage;
                    paginationOptions.pageSize = pageSize;
                    $scope.fillTable(newPage,pageSize)
                });
        }
    };

    $scope.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
});