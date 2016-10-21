(function() {
    'use strict';

    angular
        .module('mongoExApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('query', {
            parent: 'entity',
            url: '/query',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mongoExApp.query.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/query/queries.html',
                    controller: 'QueryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('query');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('query-detail', {
            parent: 'entity',
            url: '/query/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mongoExApp.query.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/query/query-detail.html',
                    controller: 'QueryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('query');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Query', function($stateParams, Query) {
                    return Query.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'query',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('query-detail.edit', {
            parent: 'query-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/query/query-dialog.html',
                    controller: 'QueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Query', function(Query) {
                            return Query.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('query.new', {
            parent: 'query',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/query/query-dialog.html',
                    controller: 'QueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                exerciseId: null,
                                queryBody: null,
                                createdDate: null,
                                result: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('query', null, { reload: 'query' });
                }, function() {
                    $state.go('query');
                });
            }]
        })
        .state('query.edit', {
            parent: 'query',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/query/query-dialog.html',
                    controller: 'QueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Query', function(Query) {
                            return Query.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('query', null, { reload: 'query' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('query.delete', {
            parent: 'query',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/query/query-delete-dialog.html',
                    controller: 'QueryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Query', function(Query) {
                            return Query.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('query', null, { reload: 'query' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
