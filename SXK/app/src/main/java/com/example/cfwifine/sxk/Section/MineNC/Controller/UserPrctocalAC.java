package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Model.RentWaterModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.AboutBoBeiModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserProtocalModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.example.cfwifine.sxk.Utils.XToast;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class UserPrctocalAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private WebView text;
    private LinearLayout activity_user_prctocal_ac;
    Dialog dialog;
    UserProtocalModel userProtocalModel;
    private String RentWater="";
    private String URLS="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_prctocal_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"正在努力加载中...");

        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        text = (WebView) findViewById(R.id.text);
        activity_user_prctocal_ac = (LinearLayout) findViewById(R.id.activity_user_prctocal_ac);

        int value = getIntent().getIntExtra("SETJUMPPOSITION",0);
        if (value == 111){
            // 关于薄被
            navi_title.setText("关于啵呗");
            initAboutBoBei();
        }else if (value == 222){
            // 用户协议
            navi_title.setText("用户协议");
            initUserAdviceView();
        }else if (value == 333){
            navi_title.setText("问答详情");
            String content = getIntent().getStringExtra("CONTENT");
            initContent(content);
        }else if (value == 444){

            int posi = getIntent().getIntExtra("POSI",-1);
            switch (posi){
                case 0:
                    navi_title.setText("租用流程");
                    URLS = BaseInterface.RentWater;
                    break;
                case 1:
                    navi_title.setText("出租收益");
                    URLS = BaseInterface.RentInput;
                    break;
                case 2:
                    navi_title.setText("平台保障");
                    URLS = BaseInterface.PingtaiKeep;
                    break;
                case 3:
                    navi_title.setText("破损处理");
                    URLS = BaseInterface.BrokenDeal;
                    break;
                case 4:
                    navi_title.setText("平台服务费");
                    URLS = BaseInterface.PingTaiFuWuFei;
                    break;
                case 5:
                    navi_title.setText("长租");
                    URLS = BaseInterface.LongRent;
                    break;
                default:
                    break;
            }
            initRentWater();
        }
    }
    // 服务中心的数据
    private void initRentWater() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("setupid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(URLS)
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
                        Log.e("租用流程", "" + response);
                        Gson gson = new Gson();
                        RentWaterModel answerModel = gson.fromJson(response,RentWaterModel.class);
                        if (answerModel.getCode() == 1) {
                            String content = answerModel.getSetup().getContent();
                            initContent(content);
                        } else if (answerModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (answerModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });



    }
    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }
    /**
     * 关于薄被
     */
    private void initAboutBoBei() {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.AboutBOBEI)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        LogUtil.e("关于薄被" + response);
                        Gson gson = new Gson();
                        AboutBoBeiModel aboutBoBeiModel = gson.fromJson(response,AboutBoBeiModel.class);
                        if (aboutBoBeiModel.getCode() == 1) {
                            // 请求成功
                            String abouts = aboutBoBeiModel.getSetup().getContent().toString();
                            LogUtil.e("关于---"+aboutBoBeiModel.getCode());
                            initContent(abouts);
                        } else if (aboutBoBeiModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (aboutBoBeiModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }

    private void initUserAdviceView() {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.UserProtocal)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        LogUtil.e("用户协议" + response);
                        Gson gson = new Gson();
                        userProtocalModel = gson.fromJson(response,UserProtocalModel.class);
                        if (userProtocalModel.getCode() == 1) {
                            // 请求成功
                            String about = userProtocalModel.getSetup().getContent().toString();
                            initContent(about);
                        } else if (userProtocalModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (userProtocalModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });

    }
    final String mimeType = "text/html";
    final String encoding = "utf-8";
    @SuppressLint("NewApi")
    private void initContent(String about) {

        WebSettings webSettings = text.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        try {

            String s =  "<html> \n"+
                    "<head> \n"+
                    "<style type=\"text/css\"> \n"+
                    "body {font-size:30px;}\n"+
                    "</style> \n"+
                    "</head> \n"+
                    "<body>"+
                    "<script type='text/javascript'>"+
                    "window.onload = function(){\n"+
                    "var $img = document.getElementsByTagName('img');\n"+
                    "for(var p in  $img){\n"+
                    " $img[p].style.width = '100%%';\n"+
                    "$img[p].style.height ='auto'\n"+
                    "}\n"+
                    "}"+
                    "</script>"+about+
                    "</body>"+
                    "</html>";
            text.loadDataWithBaseURL("about：blank", s, mimeType,
                    encoding, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }



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
