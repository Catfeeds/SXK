package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Model.FollowListModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.SlideViewRecycleViewAdapter;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class MineCollectionAC extends AppCompatActivity implements CollectionSlideViewRecycleViewAdapter.IonSlidingViewClickListener, View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView follow_rv;
    private LinearLayout activity_mine_follow_ac;
    Dialog dialog;
    private MineCollectionModel.CollectionBean dataSource;
    private CollectionSlideViewRecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collection_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("我的收藏");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        follow_rv = (RecyclerView) findViewById(R.id.follow_rv);
        activity_mine_follow_ac = (LinearLayout) findViewById(R.id.activity_mine_follow_ac);

        initCollectionData();
    }

    private void initCollectionData() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CollectionList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("收藏列表", "" + response);
                        Gson gson = new Gson();
                        MineCollectionModel mineCollectionModel = gson.fromJson(response, MineCollectionModel.class);
                        if (mineCollectionModel.getCode() == 1) {
                            dataSource = mineCollectionModel.getCollection();
                            setAdapter();
                        } else if (mineCollectionModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (mineCollectionModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void setAdapter() {
        follow_rv.setLayoutManager(new LinearLayoutManager(this));
        follow_rv.setAdapter(mAdapter = new CollectionSlideViewRecycleViewAdapter(this,dataSource));
        follow_rv.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int userid, int position) {
//        initCancelFollow(userid,position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
