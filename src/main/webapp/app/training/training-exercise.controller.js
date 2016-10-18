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
        vm.queryBody;
        vm.queryResult = {};
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
            TrainingExercise.query({number: number}, function (result) {
                console.log("response: query "  + result);

                vm.exercise = result;
            })
        }

        function saveQuery() {
            TrainingExercise.save({queryBody: vm.queryBody}, function(result){
                console.log("response: save"  + result.result);
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
