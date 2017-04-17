package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.BaseAC.MainAC;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;

public class ExchangeAndRentAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView exchange_img;
    private Button exchange_btn_float;
    private Button exchange_talkbtn_float;
    private int position = -1;
    private UserInfoModel userInfoModel = null;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
        initUserData();

    }


    public void initUserData() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(ExchangeAndRentAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.GetUserInfo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("初始化个人信息", "" + response);
                        Gson gson = new Gson();
                        userInfoModel = gson.fromJson(response, UserInfoModel.class);
                        if (userInfoModel.getCode() == 1) {
                            initListener();
                        } else if (userInfoModel.getCode() == 0) {
                        } else if (userInfoModel.getCode() == 911) {
                            initSnackBar("您还没有登录哦！");
                        }
                    }
                });

    }

    private void initListener() {
        exchange_btn_float.setOnClickListener(this);
        exchange_talkbtn_float.setOnClickListener(this);
    }

    private void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void initView() {
        position = getIntent().getIntExtra("JUMPEIGHTITEMDETAIL", -1);
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        if (position == 0) {
            navi_title.setText("交换");
        } else if (position == 1) {
            navi_title.setText("租赁");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        exchange_img = (ImageView) findViewById(R.id.exchange_img);
        exchange_btn_float = (Button) findViewById(R.id.exchange_btn_float);

        exchange_talkbtn_float = (Button) findViewById(R.id.exchange_talkbtn_float);

        if (position == 0) {
            exchange_btn_float.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_btn_float:
                Intent intent = new Intent(ExchangeAndRentAC.this, PublishPublishAC.class);
                startActivity(intent);
                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
            case R.id.exchange_talkbtn_float:
                initMeiQia();
                break;
        }
    }

    private void initMeiQia() {
        int sexid = userInfoModel.getUser().getSex();
        String Sex = "";
        if (sexid == 1) {
            Sex = "男";
        } else {
            Sex = "女";
        }
        int userid = userInfoModel.getUser().getUserid();
        HashMap<String, String> clientInfo = new HashMap<>();
        clientInfo.put("name", userInfoModel.getUser().getNickname());
        clientInfo.put("avatar", userInfoModel.getUser().getHeadimgurl());
        clientInfo.put("gender", Sex);
        clientInfo.put("tel", userInfoModel.getUser().getMobile());
        clientInfo.put("技能1", "啵呗用户");
        Intent intent = new MQIntentBuilder(this).setClientInfo(clientInfo).setCustomizedId(String.valueOf(userid)).build();
        startActivity(intent);
    }
}
