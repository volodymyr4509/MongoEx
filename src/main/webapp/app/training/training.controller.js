(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('TrainingController', TrainingController);

    TrainingController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'Exercise'];

    function TrainingController ($scope, Principal, LoginService, $state, Exercise) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.exercises = [];

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();
        loadAllExercises();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function loadAllExercises() {
            Exercise.query(function(result) {
                vm.exercises = result;
                console.log(result);
            });
        }
    }
})();
