(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('exercise', {
            parent: 'app',
            url: '/exercise',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/exercise/exercise.html',
                    controller: 'ExerciseController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('exercise');
                    return $translate.refresh();
                }]
            }
        });
    }

})();
