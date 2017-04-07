package com.example.cfwifine.sxk.Section.MineBuyPlus.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.Controller.BannerDetailAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DrawViewAC extends AppCompatActivity implements ZXingScannerView.ResultHandler, View.OnClickListener {


    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ZXingScannerView zxingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view_ac);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back_pic.setOnClickListener(this);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("扫描商品");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
        zxingView = (ZXingScannerView) findViewById(R.id.zxingView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zxingView.setResultHandler(this); // 设置处理结果回调
        zxingView.startCamera(); // 打开摄像头
    }

    @Override
    protected void onPause() {
        super.onPause();
        zxingView.stopCamera(); // 活动失去焦点的时候关闭摄像头
    }

    @Override
    public void handleResult(Result result) { // 实现回调接口，将数据回传并结束活动
//        Intent data = new Intent();
//        data.putExtra("text", result.getText());
//        setResult(RESULT_OK, data);
//        finish();
        Intent intent = new Intent(DrawViewAC.this, BannerDetailAC.class);
        intent.putExtra("SCANURL",result.getText());
        intent.putExtra("SETJUMPPOSITION",888);
        startActivity(intent);
        finish();
        LogUtil.e("回调扫描结果" + result.getText());
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
