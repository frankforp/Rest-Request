/*global angular*/

/*
 * An HTTP Plugin for PhoneGap.
 */

var exec = require('cordova/exec');

var http = {
    exitApp: function() {
        return exec(null, null, "RestRequestPlugin", "exitApp", []);
    },
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
window.restRequest = http;
