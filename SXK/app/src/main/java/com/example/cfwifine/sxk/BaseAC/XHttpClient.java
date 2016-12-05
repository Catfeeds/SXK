package com.example.cfwifine.sxk.BaseAC;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by cfwifine on 16/12/5.
 */

public class XHttpClient {

//        public static void postString(String url, ){
//
//        }



        private String url;
        private String params;
        public XHttpClient(String url, String params){
                this.url = url;
                this.params = params;
        }

        private static void PostString(String Url, String params){
                OkHttpUtils.postString()
                        .url(Url)
                        .addHeader("X-Requested-With","XMLHttpRequest")
                        .addHeader("Content-Type","application/json;chartset=utf-8")
                        .content(params)
                        .build()
                        .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                        Log.e("请求失败",""+e);
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                        Log.e("成功", "" + response);
                                }
                        });
        }



}
