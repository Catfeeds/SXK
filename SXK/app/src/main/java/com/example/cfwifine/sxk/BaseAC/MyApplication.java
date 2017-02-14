package com.example.cfwifine.sxk.BaseAC;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

/**
 * Created by cfwifine on 16/11/16.
 */

public class MyApplication extends Application {
    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar)
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        // the following line is important
        // 友盟登录相关
        UMShareAPI.get(this);
//        Config.DEBUG = true;
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
        PlatformConfig.setWeixin("wx4bfb2d22ce82d40d", "6c5dcb4c683017363d5c580309ed1eff");
        PlatformConfig.setSinaWeibo("1084074774", "26533dc2809fc7f5d6a0f1c2e0f68920");
        PlatformConfig.setQQZone("1105855252", "zOjjoUfd6sC1MTlh");


        // 初始化融云服务
        RongIM.init(this);

    }
}
