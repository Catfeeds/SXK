package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

public class HowToReleasePicAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private LinearLayout activity_how_to_release_pic_ac;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_release_pic_ac);
        initView();
    }

    private void initView() {
        int type = getIntent().getIntExtra("TYPE", 0);
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setText("");
        pic = (ImageView) findViewById(R.id.pic);
        if (type == 1) {
            navi_title.setText("发布流程");
            pic.setImageResource(R.drawable.howtorelease);
        } else if (type == 2) {
            navi_title.setText("如何发布");
            pic.setImageResource(R.drawable.howto);
        }

        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        activity_how_to_release_pic_ac = (LinearLayout) findViewById(R.id.activity_how_to_release_pic_ac);

        navi_back.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;

        }
    }
}
