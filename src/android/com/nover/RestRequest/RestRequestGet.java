/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.nover;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
 
public class RestRequestGet extends RestRequest implements Runnable {
    public RestRequestGet(String urlString, Map<?, ?> params, CallbackContext callbackContext) {
        super(urlString, params, callbackContext);
    }
    
    @Override
    public void run() {
        try {
            HttpRequest request = HttpRequest.get(this.getUrlString(), this.getParams(), true);
            request.acceptJson();
            request.acceptCharset(CHARSET);
            int code = request.code();
            String body = request.body(CHARSET);
            JSONObject response = new JSONObject();
            response.put("status", code);
            if (code >= 200 && code < 300) {
                response.put("data", body);
                this.getCallbackContext().success(response);
            } else {
                response.put("error", body);
                this.getCallbackContext().error(response);
            }
        } catch (JSONException e) {
            this.respondWithError("There was an error generating the response");
        } catch (HttpRequestException e) {
            this.respondWithError("There was an error with the request");
        }
    }
}