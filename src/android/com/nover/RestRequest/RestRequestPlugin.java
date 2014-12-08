package com.nover;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Iterator;

import org.apache.cordova.*;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestRequestPlugin extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("get")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            RequestParams reqParams = this.toRequestParams(params);

            RestRequestClient.get(urlString, reqParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }
            });
        } else if (action.equals("post")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            RequestParams reqParams = this.toRequestParams(params);

            RestRequestClient.post(urlString, reqParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }
            });
        } else if (action.equals("put")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);;
            RequestParams reqParams = this.toRequestParams(params);

            RestRequestClient.put(urlString, reqParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }
            });
        } else if (action.equals("delete")) {
            String urlString = args.getString(0);

            RestRequestClient.delete(urlString, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }
            });
        } else {
            return false;
        }
        return true;
    }

    private RequestParams toRequestParams(JSONObject object) throws JSONException {
        RequestParams params = new RequestParams();
        Iterator<?> i = object.keys();

        while(i.hasNext()) {
            String key = (String)i.next();
            params.add(key, object.getString(key));
        }
        return params;
    }
}
