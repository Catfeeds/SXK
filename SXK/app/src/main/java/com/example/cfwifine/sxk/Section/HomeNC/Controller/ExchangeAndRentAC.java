package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;

public class ExchangeAndRentAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView exchange_img;
    private Button exchange_btn_float;
    private RelativeLayout activity_exchange_ac;
    private Button exchange_talkbtn_float;
    private int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_ac);
        initView();
    }

    private void initView() {
        position = getIntent().getIntExtra("JUMPEIGHTITEMDETAIL",-1);

        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        if (position == 0){
            navi_title.setText("交换");
        }else if (position == 1){
            navi_title.setText("租赁");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        exchange_img = (ImageView) findViewById(R.id.exchange_img);
        exchange_btn_float = (Button) findViewById(R.id.exchange_btn_float);
        activity_exchange_ac = (RelativeLayout) findViewById(R.id.activity_exchange_ac);
        exchange_btn_float.setOnClickListener(this);
        exchange_talkbtn_float = (Button) findViewById(R.id.exchange_talkbtn_float);
        exchange_talkbtn_float.setOnClickListener(this);
        if (position == 0){
            exchange_btn_float.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_btn_float:
                Intent intent = new Intent(ExchangeAndRentAC.this, PublishPublishAC.class);
                startActivity(intent);
                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
            case R.id.exchange_talkbtn_float:

                break;
        }
    }
}
