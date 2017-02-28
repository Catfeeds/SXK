package com.example.cfwifine.sxk.Section.LoginAC.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

public class RegisterNowAC extends AppCompatActivity implements View.OnClickListener {

    private EditText register_phonenumber;
    private EditText register_password;
    private LinearLayout register_look_password;
    private EditText register_verification_code;
    private Button register_send_verification_btn;
    private Button register_register_btn;
    private TextView register_user_protocal;
    private static final int LOGON_SUCESS = 110;
    private ImageView look_passs;
    private boolean isLook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now_ac);
        configurationNaviTitle();
        initView();
    }


    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("注册");
        TextView rightTitle = (TextView) findViewById(R.id.navi_right);
        rightTitle.setText("");
    }

    // TODO*********************************配置注册界面**********************************************
    private void initView() {

        register_phonenumber = (EditText) findViewById(R.id.register_phonenumber);
        register_phonenumber.setOnClickListener(this);
        register_password = (EditText) findViewById(R.id.register_password);
        register_password.setOnClickListener(this);
        register_look_password = (LinearLayout) findViewById(R.id.register_look_password);
        register_look_password.setOnClickListener(this);
        register_verification_code = (EditText) findViewById(R.id.register_verification_code);
        register_verification_code.setOnClickListener(this);
        register_send_verification_btn = (Button) findViewById(R.id.register_send_verification_btn);
        register_send_verification_btn.setOnClickListener(this);
        register_register_btn = (Button) findViewById(R.id.register_register_btn);
        register_register_btn.setOnClickListener(this);
        register_user_protocal = (TextView) findViewById(R.id.register_user_protocal);
        register_user_protocal.setOnClickListener(this);
        look_passs = (ImageView) findViewById(R.id.look_passs);
        look_passs.setOnClickListener(this);
        look_passs.setImageResource(R.drawable.eye);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;

            case R.id.register_send_verification_btn:
                sendVerifyCode();
                break;
            case R.id.register_register_btn:
                submit();
                break;
            case R.id.register_user_protocal:
                Intent intent1 = new Intent(RegisterNowAC.this, UserPrctocalAC.class);
                intent1.putExtra("SETJUMPPOSITION", 222);
                startActivity(intent1);
                break;
            case R.id.look_passs:
                if(isLook){
                    look_passs.setImageResource(R.drawable.yanjingss);
                    //如果选中，显示密码
                    register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isLook = false;
                }else{
                    look_passs.setImageResource(R.drawable.eye);
                    //否则隐藏密码
                    register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isLook = true;
                }
                break;

            default:
                break;
        }
    }

    // TODO*********************************用户验证码**********************************************
    private void sendVerifyCode() {
        if (register_phonenumber.getText().toString().trim().length() != 11) {
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入正确的手机号!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                register_send_verification_btn.setText(l / 1000 + "s后重新发送");
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
            json.put("mobile", register_phonenumber.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(BaseInterface.UserVerifyCode)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "验证码请求异常!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("验证码", "" + response);
                    }
                });
    }

    // TODO*********************************用户注册**********************************************
    private void submit() {
        // validate
        final String phonenumber = register_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
//            Toast.makeText(this, "    请输入手机号码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入手机号码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        final String password = register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "    请输入密码（至少6位数）", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入密码（请输入6-15个字符）!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        String code = register_verification_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
//            Toast.makeText(this, "    请输入手机验证码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入手机验证码！", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        final JSONObject JSON = new JSONObject();
        try {
            JSON.put("mobile", phonenumber);
            JSON.put("password", password);
            JSON.put("code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Register
        OkHttpUtils.postString()
                .url(BaseInterface.UserRegister)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(JSON.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("错误", "" + e);
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求异常!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("成功", "" + response);
                        try {
                            JSONObject newjson = new JSONObject(response);
                            int code = newjson.optInt("code");
                            if (code == 1) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "注册成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                                ArrayList<String> data = new ArrayList<String>();
                                data.add(phonenumber);
                                data.add(password);
                                Intent intent = getIntent();
                                intent.putStringArrayListExtra("LOGINSUCESS", data);
                                setResult(LOGON_SUCESS, intent);
                                finish();
                            } else if (code == 2003) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "该手机号已经注册过哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "注册失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
