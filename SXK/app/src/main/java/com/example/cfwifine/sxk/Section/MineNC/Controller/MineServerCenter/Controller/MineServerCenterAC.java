package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter.MineItemCuringViewpageAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter.MineItemServerCenterViewpageAdapter;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.ArrayList;

public class MineServerCenterAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerTabLayout recycler_tab_layout;
    private LinearLayout header;
    private ViewPager viewpagers;
    private LinearLayout activity_mine_item_ac;
    private ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_server_center_ac);
        initView();
    }

    private void initView() {
        String[] s = new String[]{
                "我是啵主", "我是啵客"
        };
        datas = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            datas.add(i, s[i]);
        }
        // 设置fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        MineItemServerCenterViewpageAdapter mineItemServerCenterViewpageAdapter = new MineItemServerCenterViewpageAdapter(fragmentManager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagers);
        viewPager.setAdapter(mineItemServerCenterViewpageAdapter);
        mineItemServerCenterViewpageAdapter.addAll(datas);

        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithViewPager(viewPager);

        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("服务中心");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        recycler_tab_layout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        header = (LinearLayout) findViewById(R.id.header);
        viewpagers = (ViewPager) findViewById(R.id.viewpagers);
        activity_mine_item_ac = (LinearLayout) findViewById(R.id.activity_mine_item_ac);
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
