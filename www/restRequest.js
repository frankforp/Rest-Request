/*global angular*/

/*
 * An HTTP Plugin for PhoneGap.
 */

var exec = require('cordova/exec');

var http = {
    get: function(url, params, success, failure) {
        return exec(success, failure, "RestRequestPlugin", "get", [url, params]);
    },
    post: function(url, params, success, failure) {
        return exec(success, failure, "RestRequestPlugin", "post", [url, params]);
    },
    put: function(url, params, success, failure) {
        return exec(success, failure, "RestRequestPlugin", "put", [url, params]);
    },
    delete: function(url, params, success, failure) {
        return exec(success, failure, "RestRequestPlugin", "delete", [url, params]);
    }
};

module.exports = http;

if (typeof angular !== "undefined") {
    angular.module('restRequest', []).factory('restRequest', function($timeout, $q) {
        function makePromise(fn, args, async) {
            var deferred = $q.defer();
            
            var success = function(response) {
                if (async) {
                    $timeout(function() {
                        deferred.resolve(response);
                    });
                } else {
                    deferred.resolve(response);
                }
            };
            
            var fail = function(response) {
                if (async) {
                    $timeout(function() {
                        deferred.reject(response);
                    });
                } else {
                    deferred.reject(response);
                }
            };
            
            args.push(success);
            args.push(fail);
            
            fn.apply(http, args);
            
            return deferred.promise;
        }
        
        var restRequest = {
            get: function(url, params) {
                return makePromise(http.get, [url, params], true);
            },
            post: function(url, params) {
                return makePromise(http.post, [url, params], true);
            },
            put: function(url, params) {
                return makePromise(http.get, [url, params], true);
            },
            delete: function(url, params) {
                return makePromise(http.get, [url, params], true);
            },
        };
        return restRequest;
    });
} else {
    window.restRequest = http;
}
