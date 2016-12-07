package com.example.cfwifine.sxk.Section.LoginAC.Controller;

import android.content.Intent;
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

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.LoginAC.Model.UserLoginModel;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

public class LoginUseBoobeAC extends AppCompatActivity implements View.OnClickListener {

    private static final int LOGON_SUCESS = 110;
    private EditText login_phonenumber_editext;
    private EditText login_password_edittext;
    private Button logon_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_use_boobe_ac);
        configurationNaviTitle();
        initView();
    }

    // TODO*********************************配置导界面**********************************************
    private void initView() {
        TextView register = (TextView) findViewById(R.id.login_register_now);
        register.setOnClickListener(this);
        TextView forgetPaw = (TextView) findViewById(R.id.login_forget_password);
        forgetPaw.setOnClickListener(this);

        login_phonenumber_editext = (EditText) findViewById(R.id.login_phonenumber_editext);
        login_password_edittext = (EditText) findViewById(R.id.login_password_edittext);
        logon_login_btn = (Button) findViewById(R.id.logon_login_btn);
        logon_login_btn.setOnClickListener(this);
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("登录");
        TextView rightTitle = (TextView) findViewById(R.id.navi_right);
        rightTitle.setText("");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.login_register_now:
                Log.e("点击了注册", "" + view.getId());
//                startActivity(RegisterNowAC.class);
                Intent intent = new Intent(this,RegisterNowAC.class);
                startActivityForResult(intent,LOGON_SUCESS);
                break;
            case R.id.login_forget_password:
                startActivity(ForgetPawAC.class);
                Log.e("点击了注册", "" + view.getId());
                break;
            default:
                break;
            case R.id.logon_login_btn:
                submit();
                break;
        }
    }
    ArrayList<String> userInfo = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGON_SUCESS){
            if (data!=null){
                userInfo = data.getStringArrayListExtra("LOGINSUCESS");
                String username = userInfo.get(0).toString().trim();
                String password = userInfo.get(1).toString().trim();
                LoginUseRe(username,password);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // TODO*********************************登录请求**********************************************
    private void LoginUseRe(String username,String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("mobile",username);
            json.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.UserLogin)
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Content-Type","application/json;chartset=utf-8")
                .content(json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("登录成功",""+response);
                        Gson gson = new Gson();
                        UserLoginModel userLoginModel = gson.fromJson(response,UserLoginModel.class);
                        if (userLoginModel.getCode() ==1){
                            SharedPreferencesUtils.setParam(getApplicationContext(),BaseInterface.PHPSESSION,userLoginModel.getPHPSESSID());
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                            finish();
                        }else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(LoginUseBoobeAC.this, cls);
        startActivity(intent);
    }

    private void submit() {
        // validate
        String phonenumber = login_phonenumber_editext.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
//            Toast.makeText(this, "    请输入手机号码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入手机号码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        String password = login_password_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "    请输入密码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入密码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        // TODO validate success, do something
        LoginUseRe(phonenumber,password);

    }
}
