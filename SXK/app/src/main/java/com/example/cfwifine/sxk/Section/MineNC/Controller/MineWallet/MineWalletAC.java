package com.example.cfwifine.sxk.Section.MineNC.Controller.MineWallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.BaseAC.MainAC;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class MineWalletAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView backdrop;
    private TextView realTitle;
    private Toolbar toolbar;
    private LinearLayout textview;
    private LinearLayout first;
    private LinearLayout first1;
    private LinearLayout viewss;
    private CollapsingToolbarLayout collapsing_toolbar;
    private AppBarLayout appbar;
    private NestedScrollView scrollView;
    private CoordinatorLayout activity_collapsing_toolbar_layout;
    private String userinfo = "";
    private UserInfoModel userInfoModel = null;
    private TextView yue;
    private double dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_wallet_ac);
        userinfo = getIntent().getStringExtra("USERINFO");
        Gson gson = new Gson();
        userInfoModel = gson.fromJson(userinfo, UserInfoModel.class);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 4.0以上Navigation默认false
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Title默认true
            actionBar.setDisplayShowTitleEnabled(true);
            // Logo默认true
            actionBar.setDisplayUseLogoEnabled(true);
        }

        // 需要放在setSupportActionBar(toolbar)之后设置监听才有效果
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textview = (LinearLayout) findViewById(R.id.textview);
        textview.setOnClickListener(this);
        realTitle = (TextView) findViewById(R.id.realTitle);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setOnClickListener(this);
        first = (LinearLayout) findViewById(R.id.first);
        first.setOnClickListener(this);
        first1 = (LinearLayout) findViewById(R.id.first1);
        first1.setOnClickListener(this);
        viewss = (LinearLayout) findViewById(R.id.viewss);
        viewss.setOnClickListener(this);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setOnClickListener(this);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarListener() {
            @SuppressLint("NewApi")
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    Log.e("展开状态", "" + state.name());
                    viewss.setVisibility(View.VISIBLE);
                    toolbar.setTitle("");
                    toolbar.setTitleTextColor(Color.TRANSPARENT);
                    realTitle.setText("");
                    textview.setAlpha(1);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    Log.e("折叠状态", "" + state.name());
                    viewss.setVisibility(View.GONE);
                    toolbar.setTitle("");
                    toolbar.setTitleTextColor(Color.BLUE);
                    realTitle.setText("我的钱包");
                    textview.setAlpha(0);
                } else {
                    //中间状态
                    Log.e("中间状态", "" + state.name());
                    viewss.setVisibility(View.GONE);
                    toolbar.setTitle("");
                    realTitle.setText("");
                    textview.setAlpha(0);

                }
            }
        });


        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        scrollView.setOnClickListener(this);
        activity_collapsing_toolbar_layout = (CoordinatorLayout) findViewById(R.id.activity_collapsing_toolbar_layout);
        activity_collapsing_toolbar_layout.setOnClickListener(this);
        yue = (TextView) findViewById(R.id.yue);
        yue.setOnClickListener(this);
        dd= (userInfoModel.getUser().getBalance());
        LogUtil.e("余额为"+String.format("%.2f",dd/100));
        yue.setText("¥" + String.format("%.2f",dd/100));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first:
                // 充值
                LogUtil.e("recharge" + 1);
                Intent intent = new Intent(MineWalletAC.this, ReChargeAC.class);
                startActivity(intent);
                break;
            case R.id.first1:
                // 提现
                LogUtil.e("withdrawcash" + 2);
                Intent intent1 = new Intent(MineWalletAC.this,WithDrawAC.class);
                intent1.putExtra("yue",dd);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserData();
    }

    public void initUserData() {
        JSONObject js = new JSONObject();
        try {
            js.put("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(MineWalletAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.GetUserInfo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("初始化个人信息", "" + response);
                        Gson gson = new Gson();
                        userInfoModel = gson.fromJson(response,UserInfoModel.class);
                        if (userInfoModel.getCode() == 1) {
                            double dd= (userInfoModel.getUser().getBalance());
                            LogUtil.e("余额为"+String.format("%.2f",dd/100));
                            yue.setText("¥" + String.format("%.2f",dd/100));
                        } else if (userInfoModel.getCode() == 0) {
                        } else if (userInfoModel.getCode() == 911) {
                            initSnackBar("您还没有登录哦！");
                        }
                    }
                });

    }
    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(MineWalletAC.this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
