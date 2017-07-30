angular.module('nuntius.authentication', [])
.factory('AuthenticationService', function AuthenticationService($http, localStorageService) {
    var service = {};

    service.requestOauthToken = requestOauthToken;
    service.getOauthTokenFromStorage = getOauthTokenFromStorage;
    service.removeOauthTokenFromStorage = removeOauthTokenFromStorage;
    service.getCurrentAccount = getCurrentAccount;
    return service;

    function requestOauthToken(username, password) {

    var success = false;

    $http.post('uaa/oauth/token', { scope: 'ui', username: username, password: password, grant_type: 'password' })
        .success(function (data) {
            localStorage.setItem('token', data.access_token);
            success = true;
        })
        .error(function (data) {
            removeOauthTokenFromStorage();
        });

    return success;
    }

    function getOauthTokenFromStorage() {
    return localStorageService.get('token');
    }

    function removeOauthTokenFromStorage() {
    return localStorageService.remove('token');
    }

    /**
    * Current account
    */

    function getCurrentAccount() {

        var token = getOauthTokenFromStorage();
        var account = null;

        if (token) {
            var req = {
                method: 'GET',
                url: 'accounts/current',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }
            $http(req)
                .success(function (data) {
                    account = data;
                })
                .error(function (data) {
                    removeOauthTokenFromStorage();
                });

        }
        return account;
    }
});
