package com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Adapter.MineItemPublishViewpageAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Adapter.MineItemRentViewpageAdapter;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MineItemRentAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private List<String> datas;
    Dialog dialog;
    String PHPSESSION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_item_rent_ac);
        PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
    }
    private void initView() {
        String[] s = new String[]{
                "啵客待收", "进行中", "啵主确认", "已完成"
        };
        datas = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            datas.add(i, s[i]);
        }
        // 设置fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        MineItemRentViewpageAdapter mineItemRentViewpageAdapter = new MineItemRentViewpageAdapter(fragmentManager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagers);
        viewPager.setAdapter(mineItemRentViewpageAdapter);
        mineItemRentViewpageAdapter.addAll(datas);

        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithViewPager(viewPager);

        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("我的租赁");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
    }

    public void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(MineItemRentAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));

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
