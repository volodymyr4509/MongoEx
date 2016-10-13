(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('TrainingExerciseController', TrainingExerciseController);

    TrainingExerciseController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'TrainingExercise'];

    function TrainingExerciseController($scope, Principal, LoginService, $state, TrainingExercise) {
        var vm = this;
console.log('TrainingExerciseController execution');
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.exercise = null;

        $scope.showHintButtonText = 'Show hint';

        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();
        loadExercise();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register() {
            $state.go('register');
        }

        function loadExercise() {
            console.log("loadExercise");
            $scope.exercise = TrainingExercise.query();
        }

        $scope.toggleHintVisibility = function () {
            $scope.showHint = !$scope.showHint;
            if ($scope.showHint) {
                $scope.showHintButtonText = 'Hide hint';
            } else {
                $scope.showHintButtonText = 'Show hint';
            }
        }

    }
})();
