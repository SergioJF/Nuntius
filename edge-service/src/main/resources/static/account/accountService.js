angular.module('nuntius.account', [])
.factory('AccountService', function AccountService() {
    var service = {};

    service.initAccount = initAccount;
    return service;

    function initAccount(account) {
        user = new User(account.name, account.lastSeen, account.mail, account.icon);
    }

    function User(username, lastSeen, mail, icon) {

        var seen = new Date(lastSeen);

        this.login = username;
        this.lastSeen = (seen.getDate()  + "/" + seen.getMonth() + 1)  + "/" + seen.getFullYear();;
        this.mail = mail;
        this.icon = icon;

    }


});
