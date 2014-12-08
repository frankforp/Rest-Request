package com.nover;

import com.loopj.android.http.*;

public class RestRequestClient {
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void requestJSONHeader() {
		client.addHeader("Content-Type", "application/json");
	}

	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
	}

    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(url, params, responseHandler);
    }

    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        client.delete(url, responseHandler);
    }
}