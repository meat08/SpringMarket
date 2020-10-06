(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage', 'ui.grid','ui.grid.pagination'])
        .config(config)
        .run();

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            });
    }
})();
