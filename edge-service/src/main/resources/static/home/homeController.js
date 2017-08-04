angular.module('nuntius.home', [
    'ui.router'
]).config(function($stateProvider) {
    $stateProvider.state('home', {
        url: '/home',
        controller: 'HomeCtrl',
        templateUrl: 'home/homeView.html'
    });
})