(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .factory('TrainingExercise', TrainingExercise);

    TrainingExercise.$inject = ['$resource'];

    function TrainingExercise($resource) {
        console.log("TrainingExercise service called");

        //var service = {
        //    getEx: getEx
        //}

        return $resource('api/exercises/number/1', {}, {
            'query': {
                method: 'GET',
                isArray: false
            },
        });

        //function getEx(){
        //    console.log("getEx() called");
        //    var result = $resource('/exercises/number/{number}', {}, {
        //        'query': {
        //        method: 'GET',
        //        isArray: false
        //    },
        //    });
        //
        //    return result;
        //}

    }
})();
