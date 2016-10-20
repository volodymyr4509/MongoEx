(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('TrainingExerciseController', TrainingExerciseController);

    TrainingExerciseController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$stateParams', 'Query', 'Exercise'];

    function TrainingExerciseController($scope, Principal, LoginService, $state, $stateParams, Query, Exercise) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.exercise = initExercise($stateParams.number);
        vm.queryBody;
        vm.queryResult = [];
        $scope.saveQuery = saveQuery;

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
            Exercise.getByNumber({number: number}, function (result) {
                vm.exercise = result;
            })
        }

        function saveQuery() {
            Query.post({queryBody: vm.queryBody}, function(result){
                vm.queryResult = result.result;
            });
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
