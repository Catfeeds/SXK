package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.CustomDialog.CustomDialog_JoinActivity;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ActivityDetailModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ActivityListModel;
import com.example.cfwifine.sxk.Section.LoginAC.Model.UserLoginModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ActivityDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView activity_detail_iamge;
    private TextView activity_detail_title;
    private TextView activity_detail_address;
    private TextView activity_detail_date;
    private TextView activity_detail_content;
    private Button logon_login_btn;
    private LinearLayout activity_detail_ac;
    List<ActivityDetailModel.ActivityBean> dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ac);
        initView();
        initData();

    }
    ActivityDetailModel activityDetailModel;
    private void initData() {
        int value = getIntent().getIntExtra("value",0);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("activityid",value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeEightItemActivityDetail)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("活动详情", "" + response);
                        LogUtil.e("活动详情"+response);
                        dataSource = new ArrayList<ActivityDetailModel.ActivityBean>();
                        Gson gson = new Gson();
                         activityDetailModel = gson.fromJson(response,ActivityDetailModel.class);
//
                        if (activityDetailModel.getCode() == 1) {
                            setValueForCell(activityDetailModel);
                            logon_login_btn.setVisibility(View.VISIBLE);
                        } else if (activityDetailModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (activityDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });



    }

    private void setValueForCell(ActivityDetailModel activityDetailModel) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + activityDetailModel.getActivity().getImg();
        Glide.with(this)
                .load(picUrl)
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(activity_detail_iamge);
        activity_detail_title.setText(activityDetailModel.getActivity().getName());
        activity_detail_address.setText(activityDetailModel.getActivity().getPlace());
        activity_detail_date.setText(String.valueOf(activityDetailModel.getActivity().getTime()));
        activity_detail_content.setText(activityDetailModel.getActivity().getContent());
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("活动详情");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        activity_detail_iamge = (ImageView) findViewById(R.id.activity_detail_iamge);
        activity_detail_title = (TextView) findViewById(R.id.activity_detail_title);
        activity_detail_address = (TextView) findViewById(R.id.activity_detail_address);
        activity_detail_date = (TextView) findViewById(R.id.activity_detail_date);
        activity_detail_content = (TextView) findViewById(R.id.activity_detail_content);
        logon_login_btn = (Button) findViewById(R.id.logon_login_btn);
        activity_detail_ac = (LinearLayout) findViewById(R.id.activity_detail_ac);
        logon_login_btn.setVisibility(View.GONE);

        logon_login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logon_login_btn:
                CustomDialog_JoinActivity customDialog_joinActivity = new CustomDialog_JoinActivity(this,"","", new CustomDialog_JoinActivity.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(String id) {

                    }
                },R.style.style_dialog);
                customDialog_joinActivity.show();
                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }

}
