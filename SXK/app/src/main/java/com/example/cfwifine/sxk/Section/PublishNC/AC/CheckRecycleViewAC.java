package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.View.CheckRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.RecycleViewListener;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.ToastUtil;
import com.example.cfwifine.sxk.Utils.XToast;

import java.util.ArrayList;

// 重用上一个界面的view
public class CheckRecycleViewAC extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private ArrayList<TestModel> listData = new ArrayList<>();
    TextView title,right;
    String position;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_cateory_ac);
        position = getIntent().getStringExtra("POSITION");
        Log.e("position",""+position);
        configurationNaviTitle();



//        initData();
        for (int i = 0;i<10;i++){
            TestModel testModel = new TestModel("测试"+i,false);
            listData.add(testModel);
        }

        initRecycleView();



    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        title = (TextView)findViewById(R.id.navi_title);
        title.setText(position);
        right = (TextView)findViewById(R.id.navi_right);
        right.setText("完成");
        right.setOnClickListener(this);


    }


    private void initRecycleView() {
        Log.e("",""+listData);
        recyclerView = (RecyclerView)findViewById(R.id.cateory_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final CheckRecycleViewAdapter checkRecycleViewAdapter = new CheckRecycleViewAdapter(listData);
        recyclerView.setAdapter(checkRecycleViewAdapter);
        recyclerView.addOnItemTouchListener(new RecycleViewListener(recyclerView, new RecycleViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了一个",""+listData.get(position));
                value = listData.get(position).getText();


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
            case R.id.navi_right:
                sendValue();
                break;
            default:
                break;
        }
    }

    private void sendValue() {
//        Intent intent = new Intent();
//        intent.putExtra("result",value);
//        setResult(1001,intent);
//        finish();
        Log.e("value",""+value);
        if (value!=null) {
            Log.e("穿值", "" + value);
            SharedPreferencesUtils.setParam(this, "RESULT", value);
            finish();
        }else {
            XToast.show(CheckRecycleViewAC.this,"您还没有选择哦！");

        }

    }
}
