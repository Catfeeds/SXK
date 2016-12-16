package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class PersonalIntroAndChangePswAC extends AppCompatActivity implements View.OnClickListener {

    Integer pager = -1;
    int number = -1;
    private EditText edittext_personal_intro;
    private Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_intro_ac);
        initView();
        pager = getIntent().getIntExtra("CHANGEPSW", 0);
        number = getIntent().getIntExtra("SETJUMPPOSITION", 0);


        configurationNaviTitle();
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        LinearLayout changePsw = (LinearLayout) findViewById(R.id.changpswlay);
        LinearLayout personalIntro = (LinearLayout) findViewById(R.id.personalIntroLay);
        TextView title = (TextView) findViewById(R.id.navi_title);
        if (pager == 3) {
            title.setText("个人简介");
            changePsw.setVisibility(View.GONE);
            personalIntro.setVisibility(View.VISIBLE);
        } else if (number == 4) {
            title.setText("意见反馈");
            changePsw.setVisibility(View.GONE);
            personalIntro.setVisibility(View.VISIBLE);
            edittext_personal_intro.setHint("请输入你的宝贵意见！");
            edittext_personal_intro.setMaxLines(500);

        } else {
            title.setText("修改密码");
            changePsw.setVisibility(View.VISIBLE);
            personalIntro.setVisibility(View.GONE);
        }
        TextView rightTitle = (TextView) findViewById(R.id.navi_right);
        rightTitle.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
            case R.id.submit_btn:
                // 用户意见反馈页面
                if (number == 4) {
                    initUserAdviceView();
                } else if (pager == 3) {
                    initUserIntroView();
                }

                break;
        }
    }

    /**
     * 修改用户信息
     */
    private void initUserIntroView() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nickname", edittext_personal_intro.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String Url = BaseInterface.UpdateUserInfo;
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(Url)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("个人信息", "" + response);
                        LogUtil.e("个人信息" + response);
                        Gson gson = new Gson();
                        UserInfoModel userInfoModel = gson.fromJson(response, UserInfoModel.class);
                        if (userInfoModel.getCode() == 1) {
                            // 请求成功
                            // 数据请求成功后才可以点击，否则不能点击
                            finish();
                        } else if (userInfoModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (userInfoModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }

    /**
     * 用户反馈界面
     */
    private void initUserAdviceView() {
        String userAdvice = edittext_personal_intro.getText().toString().trim();
        if (TextUtils.isEmpty(userAdvice)) {
            initSnackBar("你还没有输入你的意见！");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", userAdvice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.SupportReplay)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("意见反馈" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            // 请求成功
                            initSnackBar("反馈成功！");
                            finish();
                        } else if (requestStatueModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (requestStatueModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });

    }

    private void initView() {
        edittext_personal_intro = (EditText) findViewById(R.id.edittext_personal_intro);
        submit_btn = (Button) findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(this);
    }

    private void initSnackBar(String name) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), name, Color.WHITE, Color.parseColor("#16a6ae"));
    }
}
