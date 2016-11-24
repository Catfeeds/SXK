package com.example.cfwifine.sxk.Section.LoginAC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

public class LoginUseBoobeAC extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_use_boobe_ac);
        configurationNaviTitle();
        initView();
    }
    // TODO*********************************配置导界面**********************************************
    private void initView() {
        TextView register = (TextView)findViewById(R.id.login_register_now);
        register.setOnClickListener(this);
        TextView forgetPaw = (TextView)findViewById(R.id.login_forget_password);
        forgetPaw.setOnClickListener(this);
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("登录");
        TextView rightTitle = (TextView)findViewById(R.id.navi_right);
        rightTitle.setText("");
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            case R.id.login_register_now:
                Log.e("点击了注册",""+view.getId());
                startActivity(RegisterNowAC.class);
                break;
            case R.id.login_forget_password:
                startActivity(ForgetPawAC.class);
                Log.e("点击了注册",""+view.getId());
                break;
            default:
                break;
        }
    }
    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(LoginUseBoobeAC.this, cls);
        startActivity(intent);
    }
}
