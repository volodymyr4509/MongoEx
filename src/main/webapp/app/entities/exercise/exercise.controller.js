(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('ExerciseController', ExerciseController);

    ExerciseController.$inject = ['$scope', '$state', 'Exercise'];

    function ExerciseController ($scope, $state, Exercise) {
        var vm = this;
        
        vm.exercises = [];

        loadAll();

        function loadAll() {
            Exercise.query(function(result) {
                vm.exercises = result;
            });
        }
    }
})();
