package com.example.cfwifine.sxk.Section.LoginAC;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

public class ForgetPawAC extends AppCompatActivity implements View.OnClickListener {

    private EditText forgetpaw_phonenumber_edittexts;
    private EditText forgetpaw_verify_code_edittext;
    private Button forgetpaw_sendVerifycode_btn;
    private EditText forgetpaw_newpassword_edittext;
    private EditText forgetpaw_confirmpaw_edittext;
    private Button forgetpaw_submit_btn;
    private static final int LOGON_SUCESS = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_paw_ac);
        initView();
        configurationNaviTitle();
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("忘记密码");
        TextView rightTitle = (TextView) findViewById(R.id.navi_right);
        rightTitle.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.forgetpaw_sendVerifycode_btn:
                sendVerify();
                break;
            case R.id.forgetpaw_submit_btn:
                submit();
                break;
        }
    }
    // TODO*********************************发送验证码**********************************************
    private void sendVerify() {
        if (forgetpaw_phonenumber_edittexts.getText().toString().trim().length() != 11){
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入正确的手机号!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                forgetpaw_sendVerifycode_btn.setText(l/1000+"s后重新发送");
                forgetpaw_sendVerifycode_btn.setTextColor(Color.parseColor("#16a6ae"));
                forgetpaw_sendVerifycode_btn.setEnabled(false);
            }
            @Override
            public void onFinish() {
                forgetpaw_sendVerifycode_btn.setText("重新获取验证码");
                forgetpaw_sendVerifycode_btn.setEnabled(true);
            }
        }.start();
        JSONObject json = new JSONObject();
        try {
            json.put("mobile",forgetpaw_phonenumber_edittexts.getText().toString().trim());
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
    // TODO*********************************初始化界面**********************************************
    private void initView() {
        forgetpaw_phonenumber_edittexts = (EditText) findViewById(R.id.forgetpaw_phonenumber_edittext);
        forgetpaw_verify_code_edittext = (EditText) findViewById(R.id.forgetpaw_verify_code_edittext);
        forgetpaw_sendVerifycode_btn = (Button) findViewById(R.id.forgetpaw_sendVerifycode_btn);
        forgetpaw_newpassword_edittext = (EditText) findViewById(R.id.forgetpaw_newpassword_edittext);
        forgetpaw_confirmpaw_edittext = (EditText) findViewById(R.id.forgetpaw_confirmpaw_edittext);
        forgetpaw_submit_btn = (Button) findViewById(R.id.forgetpaw_submit_btn);

        forgetpaw_sendVerifycode_btn.setOnClickListener(this);
        forgetpaw_submit_btn.setOnClickListener(this);
    }
    // TODO*********************************提交验证**********************************************
    private void submit() {
        // validate
        final String pnonenumber = forgetpaw_phonenumber_edittexts.getText().toString().trim();
        if (TextUtils.isEmpty(pnonenumber)) {
//            Toast.makeText(this, "    请输入手机号", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入手机号!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        String verifycode = forgetpaw_verify_code_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(verifycode)) {
//            Toast.makeText(this, "    请输入验证码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入验证码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        String newpassword = forgetpaw_newpassword_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(newpassword)) {
//            Toast.makeText(this, "    请输入新密码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入新密码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        final String confirpassword = forgetpaw_confirmpaw_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(confirpassword)) {
//            Toast.makeText(this, "    请输入确认密码", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入确认密码!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        if (!newpassword.equals(confirpassword)){
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "两次输入的密码不一致哦!", Color.WHITE, Color.parseColor("#16a6ae"));
        }else {
            // TODO validate success, do something
            final JSONObject JSON = new JSONObject();
            try {
                JSON.put("mobile",pnonenumber);
                JSON.put("password",confirpassword);
                JSON.put("code",verifycode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Register
            OkHttpUtils.postString()
                    .url(BaseInterface.UserForgetPaw)
                    .addHeader("X-Requested-With","XMLHttpRequest")
                    .addHeader("Content-Type","application/json;chartset=utf-8")
                    .content(JSON.toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("错误",""+e);
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求异常!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("成功", "" + response);
                            try {
                                JSONObject newjson = new JSONObject(response);
                                int code = newjson.optInt("code");
                                if (code == 1){
                                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "密码修改成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                                    ArrayList<String> data = new ArrayList<String>();
                                    data.add(pnonenumber);
                                    data.add(confirpassword);
                                    Intent intent = getIntent();
                                    intent.putStringArrayListExtra("LOGINSUCESS",data);
                                    setResult(LOGON_SUCESS,intent);
                                    finish();
                                }else {
                                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "密码修改失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

        }


    }
}
