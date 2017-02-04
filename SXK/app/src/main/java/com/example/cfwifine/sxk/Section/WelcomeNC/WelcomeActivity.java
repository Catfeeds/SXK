package com.example.cfwifine.sxk.Section.WelcomeNC;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.cfwifine.sxk.BaseAC.MainAC;
import com.example.cfwifine.sxk.R;


public class WelcomeActivity extends AppCompatActivity {

    private static final int Time = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private boolean isFirstIn = false;
    String username;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    //切换到主页面
                    allFlipper.setDisplayedChild(1);
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                default:
                    break;
            }
        }
    };


    private ViewFlipper allFlipper;
    private RelativeLayout splashLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        allFlipper = (ViewFlipper) findViewById(R.id.allFlipper);
        SharedPreferences sharedPreferences = getSharedPreferences("Ucoon", MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, Time);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, Time);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }

    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainAC.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(WelcomeActivity.this, WelcomeAC.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);
    }
}
