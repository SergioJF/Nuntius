angular.module('nuntius', [
    'nuntius.authentication',
    'nuntius.login',
    'nuntius.signup',
    'nuntius.account',
    'ngRoute',
    'LocalStorageModule'])
.config(function nuntiusConfig($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            controller: 'HomeCtrl',
            templateUrl: 'home/homeView.html',
            controllerAs: 'vm'
        })
        .when('/login', {
            controller: 'LoginCtrl',
            templateUrl: 'login/loginView.html',
            controllerAs: 'vm'
        })

        .when('/signup', {
            controller: 'SignupCtrl',
            templateUrl: 'signup/signupView.html',
            controllerAs: 'vm'
        })

        .otherwise({redirectTo: '/login'});
})
.run(function ($rootScope, $location, localStorageService, $http) {

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/signup']) === -1;
        var loggedIn = localStorageService.get('token');
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
});