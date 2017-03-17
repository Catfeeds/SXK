package com.example.cfwifine.sxk.Section.MineBuyPlus.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;

public class PublishBuyPlusAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    Dialog dialog;
    private RelativeLayout purchase_beginpurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_buy_plus_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("啵呗寄卖");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
//        purchase_home_pic = (ImageView) findViewById(R.id.purchase_home_pic);
        purchase_beginpurchase = (RelativeLayout) findViewById(R.id.purchase_beginpurchase);
        purchase_beginpurchase.setOnClickListener(this);
    }

    //    private void initPicData() {
//        JSONObject js = new JSONObject();
//        try {
//            js.put("setupid",1);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(PublishBuyPlusAC.this, BaseInterface.PHPSESSION, ""));
//        OkHttpUtils.postString().url(BaseInterface.PurchaseSetUpGet)
//                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
//                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader("Content-Type", "application/json;chartset=utf-8")
//                .content(js.toString())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        dialog.dismiss();
//                        initSnackBar("请求出错！");
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        dialog.dismiss();
//                        Log.e("啵被买卖图片设置", "" + response);
//                        Gson gson = new Gson();
////                        MineScoreModel mineScoreModel = gson.fromJson(response, MineScoreModel.class);
////                        if (mineScoreModel.getCode() == 1) {
////
////                        } else if (mineScoreModel.getCode() == 0) {
////                        } else if (mineScoreModel.getCode() == 911) {
////                            initSnackBar("您还没有登录哦！");
////                        }
//                    }
//                });
//
//
//
//
//
//
//    }
    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.purchase_beginpurchase:
                Intent intent = new Intent(PublishBuyPlusAC.this,OrderPurchaseAC.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
