package com.example.cfwifine.sxk.Utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by JasonLee on 2015/8/21.
 */
public class MyJsonObjectRequest extends JsonRequest<JSONObject> {

    String stringRequest;

    /**
     * 这里的method必须是Method.POST，也就是必须带参数。
     * 如果不想带参数，可以用JsonObjectRequest，给它构造参数传null。GET方式请求。
     * 如果想带参，又不想自定义使用Request，也可以JsonObjectRequest，给他参数传null，在把参数带在url后面"key1=value1&key2=value2" 这种格式。GET方式请求。
     * @param stringRequest 格式应该是 "key1=value1&key2=value2"
     */

    public MyJsonObjectRequest(String url, String stringRequest,
                               Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url,stringRequest , listener, errorListener);
        this.stringRequest = stringRequest;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}

