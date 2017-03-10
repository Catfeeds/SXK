package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RongTokenModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;
import okhttp3.Call;

public class ConversiationAC extends FragmentActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private Uri uri;
    private int mineUserId;
    private String RongToken;
    private String targetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversiation_ac);

        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        final String sName = getIntent().getData().getQueryParameter("title");//获取昵称
        navi_title.setText(sName);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);

//        targetId = getIntent().getData().getQueryParameter("targetId");//获取昵称
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                initRongData();
                targetId =  message.getTargetId();
                return false;
            }
        });
    }

    private void initRongData() {
        mineUserId = (int) SharedPreferencesUtils.getParam(ConversiationAC.this, BaseInterface.USERID, 0);
        LogUtil.e("我的ID" + mineUserId);

        JSONObject js = new JSONObject();
        try {
            js.put("userid", mineUserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RONGYUNDemo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("获取Token", "" + response);
                        Gson gson = new Gson();
                        RongTokenModel rongTokenModel = gson.fromJson(response, RongTokenModel.class);
                        if (rongTokenModel.getCode() == 1) {
                            RongToken = rongTokenModel.getToken();
                            connectRongServer(RongToken);
                        } else if (rongTokenModel.getCode() == 0) {

                        } else if (rongTokenModel.getCode() == 911) {

                        }
                    }
                });
    }

    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                if (RongIM.getInstance() != null) {
                    UserInfo userInfo = new UserInfo(String.valueOf(mineUserId), "", Uri.parse(""));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String userId) {
                            return new UserInfo(String.valueOf(targetId), "adfs", Uri.parse(""));
                        }
                    },true);
                    RongIM.getInstance().startConversation(ConversiationAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(targetId), "adfsas");
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(ConversiationAC.this, "连接服务器失败！请稍后重试！", Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
//                Toast.makeText(ProductDetailsAC.this, "token错误，请检查token和appkey是否正确！", Toast.LENGTH_SHORT).show();
                LogUtil.e("TOKEN不正确！");
//                Log.e(TAG, "token is error ,please check token and appkey");
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
        }
    }
}
