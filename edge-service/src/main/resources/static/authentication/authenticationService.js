angular.module('nuntius.authentication', [])
.factory('AuthenticationService', function AuthenticationService($http, localStorageService, AccountService) {
    var service = {};

    service.requestOauthToken = requestOauthToken;
    service.getOauthTokenFromStorage = getOauthTokenFromStorage;
    service.removeOauthTokenFromStorage = removeOauthTokenFromStorage;
    service.getCurrentAccount = getCurrentAccount;
    service.initAccountWithCurrentAccount = initAccountWithCurrentAccount;
    return service;

    function requestOauthToken(username, password) {

        var success = false;

        payload = "scope=ui" + "&grant_type=password" + "&username="+ username + "&password="+password;

        /*$http.post('uaa/oauth/token', payload,
            {headers: {
                'Authorization': 'Basic YnJvd3Nlcjo=',
                'Content-type': 'application/x-www-form-urlencoded'
            }})
            .then(function successCallback(response) {
                localStorageService.set('token', response.access_token);
                return true;
            },
            function errorCallback(response) {
                removeOauthTokenFromStorage();
            });*/

            $.ajax({
                url: 'uaa/oauth/token',
                datatype: 'json',
                type: 'post',
                headers: {'Authorization': 'Basic YnJvd3Nlcjo='},
                async: false,
                data: payload,
                success: function (data) {
                    localStorageService.set('token', data.access_token);
                    success = true;
                },
                error: function () {
                    removeOauthTokenFromStorage();
                }
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
                .then(function successCallback(response) {
                    account = response.data;
                }, function errorCallback(response) {
                    removeOauthTokenFromStorage();
                });

        }
        return account;
    }

    /**
     * Init with current account
     */

    function initAccountWithCurrentAccount() {

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
                .then(function successCallback(response) {
                    account = response.data;
                    AccountService.initAccount(account);
                }, function errorCallback(response) {
                    removeOauthTokenFromStorage();
                });

        }
        return account;
    }
});
