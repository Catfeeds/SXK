package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCoin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineWallet.AppBarListener;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

public class MineScoinAC extends AppCompatActivity implements View.OnClickListener {

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
    private RecyclerView bozhi_rv;
    private UserInfoModel userInfoModels = null;
    Dialog dialog;
    private List<MineScoreModel.RecordListBean> ScoreDataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_scoin_ac);
        userinfo = getIntent().getStringExtra("USERINFO");
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
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
                    toolbar.setTitle("");
                    toolbar.setTitleTextColor(Color.TRANSPARENT);
                    realTitle.setText("");
                    textview.setAlpha(1);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    Log.e("折叠状态", "" + state.name());
                    toolbar.setTitle("");
                    toolbar.setTitleTextColor(Color.BLUE);
                    realTitle.setText("我的啵值");
                    textview.setAlpha(0);
                } else {
                    //中间状态
                    Log.e("中间状态", "" + state.name());
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
        dd = (userInfoModel.getUser().getScore());
        LogUtil.e("余额为" + String.format("%.2f", dd));
        yue.setText("¥" + String.format("%.2f", dd));
        initBoZhi();
    }

    public void initBoZhi(){
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("recordid",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject JS = new JSONObject();
        try {
            JS.put("pageNo",0);
            JS.put("pageSize",0);
            JS.put("order",js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(MineScoinAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.MineScoin)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(JS.toString())
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
                        Log.e("积分列表", "" + response);
                        Gson gson = new Gson();
                        MineScoreModel mineScoreModel = gson.fromJson(response, MineScoreModel.class);
                        if (mineScoreModel.getCode() == 1) {
                            ScoreDataSource = mineScoreModel.getRecordList();
                           initRecycleView();
                        } else if (mineScoreModel.getCode() == 0) {
                        } else if (mineScoreModel.getCode() == 911) {
                            initSnackBar("您还没有登录哦！");
                        }
                    }
                });
    }

    private void initRecycleView() {
        bozhi_rv = (RecyclerView) findViewById(R.id.bozhi_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bozhi_rv.setLayoutManager(linearLayoutManager);
        MineScoinAdapter mineScoinAdapter = new MineScoinAdapter(this,ScoreDataSource);
        bozhi_rv.setAdapter(mineScoinAdapter);
        bozhi_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(MineScoinAC.this, BaseInterface.PHPSESSION, ""));
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
                        userInfoModels = gson.fromJson(response, UserInfoModel.class);
                        if (userInfoModels.getCode() == 1) {
                            double dd = (userInfoModels.getUser().getScore());
                            LogUtil.e("余额为--" + String.format("%.2f", dd));
                            yue.setText("¥" + String.format("%.2f", dd));
                        } else if (userInfoModels.getCode() == 0) {
                        } else if (userInfoModels.getCode() == 911) {
                            initSnackBar("您还没有登录哦！");
                        }
                    }
                });
    }
    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(MineScoinAC.this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
