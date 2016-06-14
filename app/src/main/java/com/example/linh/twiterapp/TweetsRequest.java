package com.example.linh.twiterapp;

/**
 * Created by linh on 6/14/2016.
 */
import android.util.Base64;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.StringRequest;

public class TweetsRequest extends StringRequest {


    public TweetsRequest(String url, Response.Listener<String> listener, ErrorListener errorListener) {
        super(Method.GET,url ,listener, errorListener);
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q","linh");
        return params;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        String auth = "Bearer " + TwitterValues.ACCESS_TOKEN;
        headers.put("Authorization", auth);
        return headers;
    }
}