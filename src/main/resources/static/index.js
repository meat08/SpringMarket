(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage', 'angular-jwt', 'ngAnimate', 'ngSanitize', 'ui.bootstrap'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_form', {
                templateUrl: 'order_form/order_form.html',
                controller: 'orderFormController'
            })
            .when('/order', {
                templateUrl: 'order/order.html',
                controller: 'orderController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            });
    }

    function run($rootScope, $http, $location, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            let publicPages = ['/auth', '/', '/store', '/registration'];
            let restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$localStorage.currentUser) {
                $location.path('/auth');
            }
        });
    }
})();
