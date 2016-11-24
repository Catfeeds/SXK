package com.example.cfwifine.sxk.BaseAC;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by cfwifine on 16/11/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                // 配置LOG
                .addInterceptor(new LoggerInterceptor("TAG"))
                // 设置可以访问所有的https网站

                .build();
        OkHttpUtils.initClient(okHttpClient);
        // the following line is important
        // 照片选择
        Fresco.initialize(getApplicationContext());

    }
}
