/*global angular*/

/*
 * An HTTP Plugin for PhoneGap.
 */

var exec = require('cordova/exec');

var http = {
    exitApp: function() {
        return exec(null, null, "RestRequestPlugin", "exitApp", []);
    },
    askForLocationServices: function(options) {
        var title = options.title || "Location Services is disabled",
            message = options.message || "Horus needs access to your location. Please turn on Location Services in your device settings.",
            cancelText = options.cancelText || "NO THANKS",
            okText = options.okText || "GO TO SETTINGS";
        return exec(null, null, "RestRequestPlugin", "askForLocationServices", [title, message, cancelText, okText]);
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
