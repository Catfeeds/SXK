package com.example.cfwifine.sxk.BaseAC;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ConversiationAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
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
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import okhttp3.OkHttpClient;

/**
 * Created by cfwifine on 16/11/16.
 */

public class MyApplication extends Application {
    public static Application instance;
    private Uri uri;
    private Message messag;

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
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        // the following line is important
        // 友盟登录相关
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PlatformConfig.setQQZone("1105783079", "o7ASHZOmcXPspKPx");
        PlatformConfig.setWeixin("wx4bfb2d22ce82d40d", "6c5dcb4c683017363d5c580309ed1eff");
        PlatformConfig.setSinaWeibo("1084074774", "26533dc2809fc7f5d6a0f1c2e0f68920", "http://sns.whalecloud.com/sina2/callback");

        // 初始化融云服务
        RongIM.init(this);
        // 初始化推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
            }
            @Override
            public void onFailure(String s, String s1) {

            }
        });

    }
}
