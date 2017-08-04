angular.module('nuntius.signup', [
    'ui.router'
]).config(function($stateProvider) {
    $stateProvider.state('signup', {
        url: '/signup',
        controller: 'SignupCtrl',
        templateUrl: 'signup/signupView.html'
    });
}).controller('SignupCtrl', function SignupController($location, $http, $q,  AuthenticationService) {
    var vm = this;

    vm.signup = signup;

    function signup() {
        var username = vm.user.username;
        var password = vm.user.password;

        if (username.length < 3 || password.length < 6) {
            alert("Username must be at least 3 characters and password - at least 6. Be tricky!");
            return;
        }

        if(username && password) {
            $http.post('accounts/', {username: username, password: password})
                .then(function successCallback(response) {

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


                }, function errorCallback(response) {
                    if(response.status == 400) {
                        alert("Sorry, account with the same name already exists.");
                    } else {
                        alert("An error during account creation. Please, try again.");
                    }
                })
        }

    }
});