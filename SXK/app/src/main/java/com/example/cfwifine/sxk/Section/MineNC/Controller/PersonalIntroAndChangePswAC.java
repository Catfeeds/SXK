package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

public class PersonalIntroAndChangePswAC extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_intro_ac);
        Integer pager = getIntent().getIntExtra("CHANGEPSW",0);
        configurationNaviTitle(pager);





    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle(Integer s) {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        LinearLayout changePsw = (LinearLayout)findViewById(R.id.changpswlay);
        LinearLayout personalIntro = (LinearLayout)findViewById(R.id.personalIntroLay);
        TextView title = (TextView)findViewById(R.id.navi_title);
        if (s==3){
            title.setText("个人简介");
            changePsw.setVisibility(View.GONE);
            personalIntro.setVisibility(View.VISIBLE);
        }else {
            title.setText("修改密码");
            changePsw.setVisibility(View.VISIBLE);
            personalIntro.setVisibility(View.GONE);
        }
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
