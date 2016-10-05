(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('ExerciseDetailController', ExerciseDetailController);

    ExerciseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Exercise'];

    function ExerciseDetailController($scope, $rootScope, $stateParams, previousState, entity, Exercise) {
        var vm = this;

        vm.exercise = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mongoExApp:exerciseUpdate', function(event, result) {
            vm.exercise = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
