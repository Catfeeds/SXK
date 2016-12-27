package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringDetailModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class CuringDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView curing_pic;
    private TextView curing_name;
    private TextView curing_descript;
    private TextView curing_price;
    private LinearLayout curing_cell;
    private WebView curing_webview;
    private RecyclerView curing_comment_rv;
    private Button curing_pause_right_now;
    private LinearLayout activity_curing_detail_ac;
    Dialog dialog;
    private CuringDetailModel curingDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curing_detail_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"正在加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("养护");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        curing_pic = (ImageView) findViewById(R.id.curing_pic);
        curing_name = (TextView) findViewById(R.id.curing_name);
        curing_descript = (TextView) findViewById(R.id.curing_descript);
        curing_price = (TextView) findViewById(R.id.curing_price);
        curing_cell = (LinearLayout) findViewById(R.id.curing_cell);
        curing_webview = (WebView) findViewById(R.id.curing_webview);
        curing_comment_rv = (RecyclerView) findViewById(R.id.curing_comment_rv);
        curing_pause_right_now = (Button) findViewById(R.id.curing_pause_right_now);
        activity_curing_detail_ac = (LinearLayout) findViewById(R.id.activity_curing_detail_ac);

        curing_pause_right_now.setOnClickListener(this);

        int maintainid = getIntent().getIntExtra("maintainid",-1);
        LogUtil.e("maintainid"+maintainid);
        initData(maintainid);

    }

    private void initData(int maintainid) {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("maintainid",maintainid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.GetCuring)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(CuringDetailAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("养护详情", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        curingDetailModel = gson.fromJson(response,CuringDetailModel.class);
                        if (curingDetailModel.getCode() == 1) {
                            setValueForDetail();
                        } else if (curingDetailModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CuringDetailAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (curingDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CuringDetailAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    private void setValueForDetail() {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + curingDetailModel.getMaintain().getImg();
        LogUtil.e("图片地址"+picUrl);
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(curing_pic);
        curing_name.setText(curingDetailModel.getMaintain().getName());
        curing_descript.setText(curingDetailModel.getMaintain().getKeyword());
        curing_price.setText("¥ "+String.valueOf(curingDetailModel.getMaintain().getPrice()));
        initContent();

    }
    final String mimeType = "text/html";
    final String encoding = "utf-8";
    @SuppressLint("NewApi")
    private void initContent() {
        WebSettings webSettings = curing_webview.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        try {
            String data = curingDetailModel.getMaintain().getDescription().toString();

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
                    "</script>"+curingDetailModel.getMaintain().getDescription().toString()+
                    "</body>"+
                    "</html>";

            curing_webview.loadDataWithBaseURL("about：blank", s, mimeType,
                    encoding, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.curing_pause_right_now:

                break;
            case R.id.navi_back:
                finish();
                break;
        }
    }
}
