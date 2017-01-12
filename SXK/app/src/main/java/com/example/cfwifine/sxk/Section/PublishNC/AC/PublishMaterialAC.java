package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.ChengSeAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.ChengSeRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;

import java.util.ArrayList;

public class PublishMaterialAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView check_list;
    private LinearLayout classify_online_view;
    private TextView classify_reonline_text;
    private LinearLayout classify_nonet_view;
    private LinearLayout activity_publish_cateory_ac;
    private RecyclerView recyclerView;
    private String[] list;
    private ArrayList<TestModel> listData;
    private String[] introlist;
    private String chengse="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_material_ac);
        initView();
    }

    private void initView() {
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("成色");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);

        classify_online_view = (LinearLayout) findViewById(R.id.classify_online_view);
        classify_reonline_text = (TextView) findViewById(R.id.classify_reonline_text);
        classify_nonet_view = (LinearLayout) findViewById(R.id.classify_nonet_view);
        activity_publish_cateory_ac = (LinearLayout) findViewById(R.id.activity_publish_cateory_ac);
        navi_right.setText("完成");
        navi_right.setTextColor(getResources().getColor(R.color.login_turquoise));
        navi_right.setOnClickListener(this);
        initChengSeRecycleView();
    }
    private void initChengSeRecycleView() {
        list = new String[]{"99成新（未使用）","98成新", "95成新", "9成新", "85成新", "8成新"};
        listData = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            TestModel testModel = new TestModel(list[i], false);
            listData.add(testModel);
        }
        recyclerView = (RecyclerView) findViewById(R.id.check_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ChengSeAdapter chengSeAdapter = new ChengSeAdapter(listData);
        recyclerView.setAdapter(chengSeAdapter);
        chengSeAdapter.setOnItemClickListener(new ChengSeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String title) {
                LogUtil.e("成色为" + title);
                chengse = title;
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right:
                if (chengse.isEmpty()) {
                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "您还没有选择哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("CHENGSE", chengse);
                    setResult(667, intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
