(function() {
    'use strict';
    angular
        .module('mongoExApp')
        .factory('Query', Query);

    Query.$inject = ['$resource', 'DateUtils'];

    function Query ($resource, DateUtils) {
        var resourceUrl =  'api/queries/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    }
                    return data;
                }
            },
            'post': {
                method: 'POST'
            },
            'update': { method:'PUT' }
        });
    }
})();
