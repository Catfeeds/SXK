package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.View.CateoryRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.RecycleViewListener;

import java.util.ArrayList;

public class PublishCateoryAC extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    private ArrayList<String> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_cateory_ac);
        configurationNaviTitle();
        initData();
        initRecycleView();

    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("类别");
    }

    private void initData() {
        listData.clear();
        listData.add("腕表");
        listData.add("包袋");
        listData.add("其他");
    }

    private void initRecycleView() {
        Log.e("",""+listData);
        recyclerView = (RecyclerView)findViewById(R.id.cateory_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CateoryRecycleViewAdapter cateoryRecycleViewAdapter = new CateoryRecycleViewAdapter(listData);
        recyclerView.setAdapter(cateoryRecycleViewAdapter);
        recyclerView.addOnItemTouchListener(new RecycleViewListener(recyclerView, new RecycleViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了一个",""+listData.get(position));
                Intent intent = new Intent(PublishCateoryAC.this,CheckRecycleViewAC.class);
                intent.putExtra("POSITION",listData.get(position));
                startActivity(intent);
                finish();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
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
