package com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.MineItemPagerAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MineItemAC extends FragmentActivity {
    private List<String> datas;
    Dialog dialog;
    private List<MinePublishShenHeModel.RentListBean> rentListDataSouce = new ArrayList<>();
    private MineItemPagerAdapter adapter;
    String PHPSESSION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_item_ac);
        PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }
    private void initView() {
        String [] s = new String[]{
                "待审核","发布中","租赁中","已下架","未通过"
        };
        datas = new ArrayList<>();
        for (int i = 0; i<s.length;i++){
            datas.add(i,s[i]);
        }
        // 设置fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        MineItemPublishViewpageAdapter  mineItemPublishViewpageAdapter = new MineItemPublishViewpageAdapter(fragmentManager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagers);
        viewPager.setAdapter(mineItemPublishViewpageAdapter);
        mineItemPublishViewpageAdapter.addAll(datas);

        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithViewPager(viewPager);

    }

    public void initSnackBar(String value){
        SnackbarUtils.showShortSnackbar(MineItemAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));

    }

}
