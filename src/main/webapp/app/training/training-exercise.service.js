(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .factory('TrainingExercise', TrainingExercise);

    TrainingExercise.$inject = ['$resource', '$stateParams'];

    function TrainingExercise($resource, $stateParams) {
        var resourceUrl = 'api/exercises/number/' + $stateParams.number;
        console.log("Make a request: " + resourceUrl);

        return $resource(resourceUrl, {}, {
            'query': {
                method: 'GET',
                isArray: false
            }
        });


    }
})();
