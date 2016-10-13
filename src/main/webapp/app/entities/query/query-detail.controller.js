(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('QueryDetailController', QueryDetailController);

    QueryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Query'];

    function QueryDetailController($scope, $rootScope, $stateParams, previousState, entity, Query) {
        var vm = this;

        vm.query = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mongoExApp:queryUpdate', function(event, result) {
            vm.query = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
