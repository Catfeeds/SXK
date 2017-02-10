package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.LoadingUtils;

public class BannerDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private WebView text;
    private LinearLayout activity_user_prctocal_ac;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detail_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("活动详情");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        text = (WebView) findViewById(R.id.text);
        activity_user_prctocal_ac = (LinearLayout) findViewById(R.id.activity_user_prctocal_ac);
        String url = getIntent().getStringExtra("URLINK");
        dialog.show();
        initContent(url);
    }

    final String mimeType = "text/html";
    final String encoding = "utf-8";

    @SuppressLint("NewApi")
    private void initContent(String about) {

        WebSettings webSettings = text.getSettings();
//        webSettings.setUseWideViewPort(false);
//        webSettings.setLoadWithOverviewMode(true);
        text.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        text.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    dialog.dismiss();
                } else {
                    // 加载中
                    dialog.show();
                }

            }
        });
        text.loadUrl(about);
        text.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (text.canGoBack()) {
                text.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
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
}
