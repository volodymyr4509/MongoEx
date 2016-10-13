(function () {
    'use strict';

    angular
        .module('mongoExApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('training', {
                parent: 'app',
                url: '/training',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/training/training.html',
                        controller: 'TrainingController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('training');
                        return $translate.refresh();
                    }]
                }
            })
            .state('training-exercise', {
                parent: 'app',
                url: '/training/{number}',
                data: {
                    authorities: []

                },
                views: {
                    'content@': {
                        templateUrl: 'app/training/training-exercise.html',
                        controller: 'TrainingExerciseController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('training');
                        return $translate.refresh();
                    }]
                }

            });
    }

})();
