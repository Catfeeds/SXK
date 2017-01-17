package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.AppraisalAC.AppraisasAC;

public class AppraisaAllAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView free_appraisa;
    private ImageView center_appraisa;
    private LinearLayout activity_appraisa_all_ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraisa_all_ac);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        free_appraisa = (ImageView) findViewById(R.id.free_appraisa);
        center_appraisa = (ImageView) findViewById(R.id.center_appraisa);
        activity_appraisa_all_ac = (LinearLayout) findViewById(R.id.activity_appraisa_all_ac);
        navi_back.setOnClickListener(this);
        navi_title.setText("鉴定");
        free_appraisa.setOnClickListener(this);
        center_appraisa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            case R.id.free_appraisa:

                break;
            case R.id.center_appraisa:
                Intent intent = new Intent(AppraisaAllAC.this,AppraisasAC.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
