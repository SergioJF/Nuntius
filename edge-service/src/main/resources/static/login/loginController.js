angular.module('nuntius.login', [
    'ui.router'
]).config(function($stateProvider) {
    $stateProvider.state('login', {
        url: '/login',
        controller: 'LoginCtrl',
        templateUrl: 'login/loginView.html'
    });
})
.controller('LoginCtrl', function LoginController($location, AuthenticationService) {
    var vm = this;

    vm.login = login;

    function initController() {
        // reset login status
        AuthenticationService.removeOauthTokenFromStorage();
    };

    function login() {

    };
});