
angular.module('customerWeb', ['ngRoute', 'ngResource', 'ngCookies','factoryIncludeSearchCustomersServices'])
.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider.when('/CustomerWeb/index.html', {
        templateUrl: '/CustomerWeb/partials/form-include-search-customer.html',
        controller: 'FormIncludeSearchCustomersController'
    });

    $routeProvider.otherwise({ redirectTo: '/CustomerWeb/index.html' });
});
