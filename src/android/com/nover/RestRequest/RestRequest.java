/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.nover;

import org.apache.cordova.CallbackContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Iterator;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
 
public abstract class RestRequest {
    protected static final String TAG = "RestRequest";
    protected static final String CHARSET = "UTF-8";
    
    private String urlString;
    private Map<?, ?> params;
    private CallbackContext callbackContext;
    
    public RestRequest(String urlString, Map<?, ?> params, CallbackContext callbackContext) {
        this.urlString = urlString;
        this.params = params;
        this.callbackContext = callbackContext;
    }
    
    protected String getUrlString() {
        return this.urlString;
    }
    
    protected Map<?, ?> getParams() {
        return this.params;
    }
    
    protected CallbackContext getCallbackContext() {
        return this.callbackContext;
    }
    
    protected void respondWithError(int status, String msg) {
        try {
            JSONObject response = new JSONObject();
            response.put("status", status);
            response.put("error", msg);
            this.callbackContext.error(response);
        } catch (JSONException e) {
            this.callbackContext.error(msg);
        }
    }
    
    protected void respondWithError(String msg) {
        this.respondWithError(500, msg);
    }
}
