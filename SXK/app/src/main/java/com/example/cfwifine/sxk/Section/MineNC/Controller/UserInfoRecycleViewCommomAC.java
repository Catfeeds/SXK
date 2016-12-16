package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.MineRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.SettingRecycleViewAdapter;

import java.util.ArrayList;

public class UserInfoRecycleViewCommomAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView setting_rv;
    private LinearLayout activity_setting_ac;
    private RelativeLayout setting_view_lay;
    // setting页面
    ArrayList<String> applicationName = new ArrayList<>();
    ArrayList<Integer> imageView = new ArrayList<>();
    String[] applicationNames = {"常用地址", "分享APP", "清除缓存", "检测更新", "意见反馈",
            "用户协议", "关于啵呗"};
    int[] imageViews = {R.drawable.dizhi, R.drawable.fenxiang, R.drawable.huancun,
            R.drawable.gengxin, R.drawable.fankui, R.drawable.xieyi,
            R.drawable.guanyu};


    // 我的界面通用跳转
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commom_ac);
        initView();
        getPositionView();
    }

    // TODO***************************************初始化界面****************************************
    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back_pic.setOnClickListener(this);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setOnClickListener(this);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
        setting_rv = (RecyclerView) findViewById(R.id.setting_rv);
        setting_rv.setOnClickListener(this);
        activity_setting_ac = (LinearLayout) findViewById(R.id.activity_setting_ac);
        activity_setting_ac.setOnClickListener(this);
        setting_view_lay = (RelativeLayout) findViewById(R.id.setting_view_lay);
        setting_view_lay.setOnClickListener(this);
    }

    /**
     * 根据不同的position来传值
     */
    private void getPositionView() {
        Integer position = getIntent().getIntExtra("JUMPPOSITION", 0);
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                // 设置页面
                initSettingView();
                break;
            default:
                break;
        }
    }

    // TODO***************************************初始化设置界面****************************************
    private void initSettingView() {
        navi_title.setText("设置");
        setting_view_lay.setVisibility(View.VISIBLE);
        initRecycleView();
    }
    private void initRecycleView() {
        for (int i=0;i<imageViews.length;i++) {
            applicationName.add(applicationNames[i]);
            imageView.add(imageViews[i]);
        }
        setting_rv.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        setting_rv.setHasFixedSize(true);
        final SettingRecycleViewAdapter adapter = new SettingRecycleViewAdapter(applicationName,imageView);
        adapter.setOnItemClickListener(new SettingRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                SettingJump(position);
            }
        });
        setting_rv.setAdapter(adapter);
    }
    private void SettingJump(int position){
        switch (position){
            case 0:
                startActivity(AddressSettingCommomAC.class,911);
                break;
            case 4:
                // 意见反馈页面和个人简介是一样的
                startActivity(PersonalIntroAndChangePswAC.class,4);
                break;
            case 5:
                startActivity(UserPrctocalAC.class,-1);
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void startActivity(Class<?> cls,Integer jumpvalue) {
        Intent intent = new Intent(UserInfoRecycleViewCommomAC.this, cls);
        intent.putExtra("SETJUMPPOSITION",jumpvalue);
        startActivity(intent);
    }
}
