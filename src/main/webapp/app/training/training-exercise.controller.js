(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('TrainingExerciseController', TrainingExerciseController);

    TrainingExerciseController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'TrainingExercise'];

    function TrainingExerciseController($scope, Principal, LoginService, $state, TrainingExercise) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.exercise;
        $scope.number = 2;


        vm.showHint = false;
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
            console.log("training exercise controller. load exercise");
            $scope.exercise = TrainingExercise.query();
        }

        $scope.toggleHintVisibility = function() {
            $scope.showHint = !$scope.showHint;
            if($scope.showHint){
                $scope.showHintButtonText = 'Hide hint';
            }else{
                $scope.showHintButtonText = 'Show hint';
            }
        }

    }
})();
