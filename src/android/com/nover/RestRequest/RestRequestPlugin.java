/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.synconset;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HostnameVerifier;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.AssetManager;
import android.util.Base64;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

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

            RestRequestGet get = new RestRequestGet(urlString, paramsMap, callbackContext);
            cordova.getThreadPool().execute(get);
        } else if (action.equals("post")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);

            RestRequestPost post = new RestRequestPost(urlString, paramsMap, callbackContext);
            cordova.getThreadPool().execute(post);
        } else if (action.equals("put")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);;
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);

            RestRequestPut put = new RestRequestPut(urlString, paramsMap, callbackContext);
            cordova.getThreadPool().execute(put);
        } else if (action.equals("delete")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            HashMap<?, ?> paramsMap = this.getMapFromJSONObject(params);

            RestRequestDelete delete = new RestRequestDelete(urlString, paramsMap, callbackContext);
            cordova.getThreadPool().execute(delete);
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
