package com.nover.RestRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

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

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR, errorResponse);
                    callbackContext.sendPluginResult(r);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callbackContext.error(responseString);
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR);
                    callbackContext.sendPluginResult(r);
                }
            });
        } else if (action.equals("post")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            Context context = this.cordova.getActivity().getApplicationContext();

            RestRequestClient.post(context, urlString, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR, errorResponse);
                    callbackContext.sendPluginResult(r);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callbackContext.error(responseString);
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR);
                    callbackContext.sendPluginResult(r);
                }
            });
        } else if (action.equals("put")) {
            String urlString = args.getString(0);
            JSONObject params = args.getJSONObject(1);
            Context context = this.cordova.getActivity().getApplicationContext();

            RestRequestClient.put(context, urlString, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR, errorResponse);
                    callbackContext.sendPluginResult(r);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callbackContext.error(responseString);
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR);
                    callbackContext.sendPluginResult(r);
                }
            });
        } else if (action.equals("delete")) {
            String urlString = args.getString(0);

            RestRequestClient.delete(urlString, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, response));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR, errorResponse);
                    callbackContext.sendPluginResult(r);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callbackContext.error(responseString);
                    PluginResult r = new PluginResult(PluginResult.Status.ERROR);
                    callbackContext.sendPluginResult(r);
                }
            });
        } else if (action.equals("askForLocationServices")) {
            String title = args.getString(0);
            String message = args.getString(1);
            String cancelText = args.getString(2);
            String okText = args.getString(3);
            showSettingsAlert(title, message, cancelText, okText);
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
            String value = object.getString(key);
            try {
                value = URLEncoder.encode(value, "utf-8");
            } catch (UnsupportedEncodingException ex) {
                Log.d("DEBUG", "Cannot encode string: " + value);
            }
            params.add(key, value);
        }
        return params;
    }

    private void showSettingsAlert(String title, String message, String cancelText, String okText) {
        final Activity activity = this.cordova.getActivity();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // On pressing Settings button
        alertDialog.setPositiveButton(okText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivityForResult(intent, 0);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
