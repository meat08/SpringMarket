angular.module('app').controller('storeController', function ($scope, uiGridConstants, $http, i18nService) {
        const contextPath = 'http://localhost:8180/market';
        let paginationOptions = {
            pageNumber: 1,
            pageSize: 5,
            sort: null
        };
        let sc = this;

     sc.fillTable = function(pageNumber,size) {
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
                sc.gridOptions.data = response.data.content;
                sc.gridOptions.totalItems = response.data.totalElements;
            });
    }

    sc.updateTable = function () {
        sc.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
        // sc.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
        sc.gridApi.core.refresh();
    }

    sc.clearFilters = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                if ($scope.filterProduct != null) {
                    $scope.filterProduct = null;
                }
                sc.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
            });
    };

     sc.displayFilters = function() {
         sc.isShow = !sc.isShow;
     }

    sc.gridOptions = {
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
            sc.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged(
                $scope,
                function (newPage, pageSize) {
                    paginationOptions.pageNumber = newPage;
                    paginationOptions.pageSize = pageSize;
                    sc.fillTable(newPage,pageSize)
                });
        }
    };
    i18nService.setCurrentLang('ru');
    sc.fillTable(paginationOptions.pageNumber, paginationOptions.pageSize);
});