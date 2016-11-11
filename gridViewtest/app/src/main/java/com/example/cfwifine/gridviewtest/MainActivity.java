package com.example.cfwifine.gridviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyGridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        gridview=(MyGridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyGridAdapter(this));

    }
}
