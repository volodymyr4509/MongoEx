(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('ExerciseDialogController', ExerciseDialogController);

    ExerciseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Exercise'];

    function ExerciseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Exercise) {
        var vm = this;

        vm.exercise = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.exercise.id !== null) {
                Exercise.update(vm.exercise, onSaveSuccess, onSaveError);
            } else {
                Exercise.save(vm.exercise, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mongoExApp:exerciseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.last_modified_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
