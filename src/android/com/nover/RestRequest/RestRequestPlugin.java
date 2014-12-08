/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.nover;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestRequestPlugin extends CordovaPlugin {
    private static final String TAG = "RestRequest";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("get")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);
        } else if (action.equals("post")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);
        } else if (action.equals("put")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);;
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);
        } else if (action.equals("delete")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);
        } else {
            return false;
        }
        return true;
    }

    private HashMap<String, Object> getMapFromJSONObject(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator<?> i = object.keys();

        while(i.hasNext()) {
            String key = (String)i.next();
            map.put(key, object.get(key));
        }
        return map;
    }
}
