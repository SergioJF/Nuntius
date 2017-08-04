angular.module('nuntius.login', [
    'ui.router'
]).config(function($stateProvider) {
    $stateProvider.state('login', {
        url: '/login',
        controller: 'LoginCtrl',
        templateUrl: 'login/loginView.html'
    });
})
.controller('LoginCtrl', function LoginController($location, $q, AuthenticationService, AccountService) {
    var vm = this;

    vm.login = login;


    function login() {

        var username = vm.username;
        var password = vm.password;

        var questions = [
            AuthenticationService.requestOauthToken(username, password),
            AuthenticationService.initAccountWithCurrentAccount()
        ]

        var promises = [];

        angular.forEach(questions, function(question) {
            promises.push(question);
        });

        $q.all(promises).then(function(responses) {
            console.log(responses);
            $location.path('/home');
        });

    };
});