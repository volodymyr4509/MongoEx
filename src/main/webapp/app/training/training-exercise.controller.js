(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('TrainingExerciseController', TrainingExerciseController);

    TrainingExerciseController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$stateParams', 'TrainingExercise'];

    function TrainingExerciseController($scope, Principal, LoginService, $state, $stateParams, TrainingExercise) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.exercise = initExercise($stateParams.number);
        vm.solution = {a: 1, b: 2, c: 3};
        vm.queryResult = {e:4, d:5, f:6};

        $scope.showHintButtonText = 'Show hint';

        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register() {
            $state.go('register');
        }

        function initExercise(number) {
            TrainingExercise.query({number: number}, function (result) {
                vm.exercise = result;
            })
        }

        //function sendQuery() {
        //    TrainingExercise
        //}

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
