package com.example.cfwifine.sxk.Section.LoginAC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

public class RegisterNowAC extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now_ac);
        configurationNaviTitle();
    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("注册");
        TextView rightTitle = (TextView)findViewById(R.id.navi_right);
        rightTitle.setText("");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
