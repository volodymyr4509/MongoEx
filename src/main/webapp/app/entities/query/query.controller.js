(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .controller('QueryController', QueryController);

    QueryController.$inject = ['$scope', '$state', 'Query'];

    function QueryController ($scope, $state, Query) {
        var vm = this;
        
        vm.queries = [];

        loadAll();

        function loadAll() {
            Query.query(function(result) {
                vm.queries = result;
            });
        }
    }
})();
