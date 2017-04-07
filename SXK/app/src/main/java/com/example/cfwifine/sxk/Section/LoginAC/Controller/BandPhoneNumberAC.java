package com.example.cfwifine.sxk.Section.LoginAC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.LoginAC.Model.UserLoginModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class BandPhoneNumberAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RelativeLayout navi;
    private EditText register_phonenumber;
    private EditText register_password;
    private LinearLayout register_look_password;
    private EditText register_verification_code;
    private Button register_send_verification_btn;
    private EditText register_code;
    private LinearLayout invite_code;
    private Button register_register_btn;
    private TextView register_user_protocal;
    private LinearLayout activity_login_use_boobe_ac;
    private String NICKNAME;
    private String OPENID;
    private String ICONURL;
    private boolean isLook = false;
    private ImageView eye_pic;
    private LinearLayout show_invite_code_view;
    Dialog dialog;
    private int LOGINTYPE;
    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_phone_number_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        NICKNAME = getIntent().getStringExtra("NICKNAME");
        OPENID = getIntent().getStringExtra("OPENID");
        ICONURL = getIntent().getStringExtra("ICONURL");
        LOGINTYPE = getIntent().getIntExtra("LOGINTYPE",-1);
        initView();
    }

    private void initView() {
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("绑定手机号");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi = (RelativeLayout) findViewById(R.id.navi);
        register_phonenumber = (EditText) findViewById(R.id.register_phonenumber);
        register_phonenumber.setInputType(InputType.TYPE_CLASS_PHONE);
        register_password = (EditText) findViewById(R.id.register_password);
        register_look_password = (LinearLayout) findViewById(R.id.register_look_password);
        register_look_password.setOnClickListener(this);
        register_verification_code = (EditText) findViewById(R.id.register_verification_code);
        register_send_verification_btn = (Button) findViewById(R.id.register_send_verification_btn);
        register_code = (EditText) findViewById(R.id.register_code);
        invite_code = (LinearLayout) findViewById(R.id.invite_code);
        register_register_btn = (Button) findViewById(R.id.register_register_btn);
        register_user_protocal = (TextView) findViewById(R.id.register_user_protocal);
        register_user_protocal.setOnClickListener(this);
        activity_login_use_boobe_ac = (LinearLayout) findViewById(R.id.activity_login_use_boobe_ac);
        register_send_verification_btn.setOnClickListener(this);
        register_register_btn.setOnClickListener(this);
        eye_pic = (ImageView) findViewById(R.id.eye_pic);
        eye_pic.setOnClickListener(this);
        eye_pic.setImageResource(R.drawable.eye);
        show_invite_code_view = (LinearLayout) findViewById(R.id.show_invite_code_view);
        show_invite_code_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.register_send_verification_btn:
                sendVerify();
                break;
            case R.id.register_register_btn:
                submit();
                break;
            case R.id.eye_pic:

                if(isLook){
                    eye_pic.setImageResource(R.drawable.yanjingss);
                    //如果选中，显示密码
                    register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isLook = false;
                }else{
                    eye_pic.setImageResource(R.drawable.eye);
                    //否则隐藏密码
                    register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isLook = true;
                }
                break;
            case R.id.show_invite_code_view:
                if (isShow){
                    invite_code.setVisibility(View.GONE);
                    isShow = false;
                }else {
                    invite_code.setVisibility(View.VISIBLE);
                    isShow = true;
                }
                break;
            case R.id.register_user_protocal:
                Intent intent1 = new Intent(BandPhoneNumberAC.this, UserPrctocalAC.class);
                intent1.putExtra("SETJUMPPOSITION", 222);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    private void sendVerify() {
        if (register_phonenumber.getText().toString().trim().length() != 11){
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入正确的手机号!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                register_send_verification_btn.setText(l/1000+"s后重新发送");
                register_send_verification_btn.setTextColor(Color.parseColor("#16a6ae"));
                register_send_verification_btn.setEnabled(false);
            }
            @Override
            public void onFinish() {
                register_send_verification_btn.setText("重新获取验证码");
                register_send_verification_btn.setEnabled(true);
            }
        }.start();
        JSONObject json = new JSONObject();
        try {
            json.put("mobile",register_phonenumber.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(BaseInterface.UserVerifyCode)
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Content-Type","application/json;chartset=utf-8")
                .content(json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "验证码请求异常!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("验证码",""+response);
                    }
                });
    }
    public void initSnackBar(String s){
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }
    private void submit() {
        // validate
        String phonenumber = register_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            initSnackBar("请输入手机号码！");
            return;
        }

        String password = register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            initSnackBar("请输入密码（至少6位数）!");
            return;
        }

        String code = register_verification_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            initSnackBar("请输入手机验证码!");
            return;
        }

//        String codes = register_code.getText().toString().trim();
//        if (TextUtils.isEmpty(codes)) {
//            Toast.makeText(this, "    请输入邀请码", Toast.LENGTH_SHORT).show();
//            return;
//        }
        loginWithWX(NICKNAME,ICONURL,OPENID);


    }
    private void loginWithWX(String nickName, String iconUrl, String openid) {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("openid", openid);
            js.put("nickname", nickName);
            js.put("headimgurl", iconUrl);
            js.put("pf", LOGINTYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.LoginUserThird)
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

                        Log.e("登录", "" + response);
                        Gson gson = new Gson();
                        UserLoginModel requestStatueModel = gson.fromJson(response, UserLoginModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            SharedPreferencesUtils.setParam(BandPhoneNumberAC.this, BaseInterface.PHPSESSION, requestStatueModel.getPHPSESSID());
                            initPhoneNumber();
//                            initSnackBar("登录成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            dialog.dismiss();
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            dialog.dismiss();
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void initPhoneNumber() {
        String inviteCode = register_code.getText().toString().trim();
        JSONObject js = new JSONObject();
        try {
            js.put("mobile", register_phonenumber.getText().toString().trim());
            js.put("code", register_verification_code.getText().toString().trim());
            js.put("password",register_password.getText().toString().trim());
            if (!TextUtils.isEmpty(inviteCode)){
                js.put("invitationCode",inviteCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.UpdateUserInfo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("注册", "" + response);
                        Gson gson = new Gson();
                        UserLoginModel requestStatueModel = gson.fromJson(response, UserLoginModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            initSnackBar("绑定成功！");
                            SharedPreferencesUtils.setParam(BandPhoneNumberAC.this,BaseInterface.SUCCESS,"SUCCESS");
                            SharedPreferencesUtils.setParam(BandPhoneNumberAC.this, BaseInterface.PHONENUMBER, register_phonenumber.getText().toString().trim());
                            finish();
                        } else if (requestStatueModel.getCode() == 0) {
                            dialog.dismiss();
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            dialog.dismiss();
                            initSnackBar("登录超时，请重新登录！");
                        }else if (requestStatueModel.getCode() == 2003){
                            initSnackBar("该手机号已使用！");
                        }
                    }
                });
    }


}
