package com.nover.RestRequest;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RestRequestClient {
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
	}

	public static void post(Context context, String url, JSONObject jsonParams, AsyncHttpResponseHandler responseHandler) {
        try {
            StringEntity entity = new StringEntity(jsonParams.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.post(context, url, entity, "application/json", responseHandler);
        } catch (UnsupportedEncodingException ex) {}
	}

    public static void put(Context context, String url, JSONObject jsonParams, AsyncHttpResponseHandler responseHandler) {
        try {
            StringEntity entity = new StringEntity(jsonParams.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.put(context, url, entity, "application/json", responseHandler);
        } catch (UnsupportedEncodingException ex) {}
    }

    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        client.delete(url, responseHandler);
    }
}