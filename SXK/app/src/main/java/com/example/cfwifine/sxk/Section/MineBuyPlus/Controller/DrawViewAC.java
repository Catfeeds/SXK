package com.example.cfwifine.sxk.Section.MineBuyPlus.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.ScreenUtil;

public class DrawViewAC extends AppCompatActivity {


    private RelativeLayout activity_draw_view_ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view_ac);
        initView();
    }

    private void initView() {
        activity_draw_view_ac = (RelativeLayout) findViewById(R.id.activity_draw_view_ac);
        final DrawView view = new DrawView(this);
        view.setMinimumHeight(ScreenUtil.getScreenHeightPix(this));
        view.setMinimumWidth(ScreenUtil.getScreenWidthPix(this));
        //通知view组件重绘
        view.invalidate();
        activity_draw_view_ac.addView(view);

    }
}
