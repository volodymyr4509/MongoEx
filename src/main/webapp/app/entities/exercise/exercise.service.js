(function() {
    'use strict';
    angular
        .module('mongoExApp')
        .factory('Exercise', Exercise);

    Exercise.$inject = ['$resource', 'DateUtils'];

    function Exercise ($resource, DateUtils) {
        var resourceUrl =  'api/exercises/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.last_modified_date = DateUtils.convertDateTimeFromServer(data.last_modified_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
