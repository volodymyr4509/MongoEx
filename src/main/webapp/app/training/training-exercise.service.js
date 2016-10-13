(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .factory('TrainingExercise', TrainingExercise);

    TrainingExercise.$inject = ['$resource'];

    function TrainingExercise($resource) {

        var service = $resource('api/exercises/number/:number', {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });

        return service;
    }
})();
