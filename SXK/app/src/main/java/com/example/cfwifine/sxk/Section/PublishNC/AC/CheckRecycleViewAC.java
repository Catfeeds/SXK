package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.ChengSeRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.SecondCateModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CheckRecycleViewAdapter;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

// 重用上一个界面的view
public class CheckRecycleViewAC extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private ArrayList<TestModel> listData = new ArrayList<>();
    private List<SecondCateModel.CategoryListBean> dataSource = new ArrayList<>();
    TextView title, right;
    int position;
    String value;
    Dialog mloading;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView check_list;
    private LinearLayout classify_online_view;
    private TextView classify_reonline_text;
    private LinearLayout classify_nonet_view;
    private LinearLayout activity_publish_cateory_ac;
    String tit;
    int s = 0;
    String chengse = "";
    private ArrayList<String> attachmentdata;
    private ArrayList<String> baobeifujianData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_recycle_view_ac);
        mloading = LoadingUtils.createLoadingDialog(this, "正在加载中...");
        initView();

        s = getIntent().getIntExtra("CHENGSE", 0);
        if (s == 3) {
            // s == 3 代表是成色页面
            configurationNaviTitle("成色");
            initChengSeData(3);

        } else if (s == 4) {
            // s == 4代表人群
            configurationNaviTitle("适用人群");
            initChengSeData(4);
        } else if (s == 5) {
            // 标题，数据，和附件内容
            baobeifujianData = getIntent().getStringArrayListExtra("BAOBEIFUJIAN");
            SharedPreferencesUtils.setParam(this,"BAOBEIFUJIAN",baobeifujianData.toString());

            attachmentdata = getIntent().getStringArrayListExtra("ATTACHMENT");
            String title = getIntent().getStringExtra("TITLESS");
            configurationNaviTitle(title);
            initChengSeData(5);
        } else {
            position = getIntent().getIntExtra("POSITION", -1);
            SharedPreferencesUtils.setParam(CheckRecycleViewAC.this, "CATEGORYID", position);
            String title = getIntent().getStringExtra("TITLE");
            Log.e("传递的parentid", "" + position);
            configurationNaviTitle(title);
            initData(position);

        }
    }


    String[] list;

    private void initChengSeData(int s) {
        if (s == 3) {
            list = new String[]{"99成新（未使用）", "95新", "9成新", "85成新", "8成新"};
            for (int i = 0; i < list.length; i++) {
                TestModel testModel = new TestModel(list[i], false);
                listData.add(testModel);
            }
            initChengSeRecycleView();
        } else if (s == 5) {
            for (int i = 0; i < attachmentdata.size(); i++) {
                TestModel testModel = new TestModel(attachmentdata.get(i), false);
                listData.add(testModel);
            }
            initChengSeRecycleView();

        } else if (s == 4) {
            list = new String[]{"所有人", "男士", "女士"};
            for (int i = 0; i < list.length; i++) {
                TestModel testModel = new TestModel(list[i], false);
                listData.add(testModel);
            }
            initChengSeRecycleView();
        }
    }

    private void initChengSeRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.check_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ChengSeRecycleViewAdapter chengSeRecycleViewAdapter = new ChengSeRecycleViewAdapter(listData);
        recyclerView.setAdapter(chengSeRecycleViewAdapter);
        chengSeRecycleViewAdapter.setOnItemClickListener(new ChengSeRecycleViewAdapter.OnItemClickListener() {
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

    private void initData(final int value) {
        mloading.show();
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
            jsonObject.put("parentid", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGoodsCateify)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(CheckRecycleViewAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        classify_online_view.setVisibility(View.GONE);
                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("二级分类", "" + response);
                        mloading.dismiss();
                        Gson gson = new Gson();
                        SecondCateModel cateModel = gson.fromJson(response, SecondCateModel.class);
                        if (cateModel.getCode() == 1) {
                            classify_online_view.setVisibility(View.VISIBLE);
                            classify_nonet_view.setVisibility(View.GONE);
                            if (cateModel.getTotal() != 0) {
                                for (int i = 0; i < cateModel.getTotal(); i++) {
                                    TestModel testModel = new TestModel("测试" + i, false);
                                    listData.add(testModel);
                                }
                                dataSource = cateModel.getCategoryList();
                                initRecycleView();
                            }


                        } else if (cateModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CheckRecycleViewAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        } else if (cateModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CheckRecycleViewAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });


    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle(String title) {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        this.title = (TextView) findViewById(R.id.navi_title);
        this.title.setText(title);
        right = (TextView) findViewById(R.id.navi_right);
        right.setText("完成");
        right.setOnClickListener(this);


    }


    private void initRecycleView() {
        Log.e("", "" + listData);
        recyclerView = (RecyclerView) findViewById(R.id.check_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CheckRecycleViewAdapter checkRecycleViewAdapter = new CheckRecycleViewAdapter(listData, dataSource);
        recyclerView.setAdapter(checkRecycleViewAdapter);
        checkRecycleViewAdapter.setOnItemClickListener(new CheckRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String tits) {
                LogUtil.e("点击了" + tits);
                tit = tits;
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
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right:
                if (s == 3) {
                    Intent intent = new Intent();
                    intent.putExtra("CHENGSE", chengse);
                    setResult(667, intent);
                    finish();
                } else if (s == 4) {
                    Intent intent = new Intent();
                    intent.putExtra("CHENGSE", chengse);
                    setResult(668, intent);
                    finish();
                } else if (s == 5) {
                    if (chengse.isEmpty()){
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "您还没有选择哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }else {
                        SharedPreferencesUtils.setParam(this,"FUJIAN",chengse);
                        finish();
                    }
                } else {
                    sendValue();
                }
                break;
            case R.id.classify_reonline_text:
                initData(position);
                break;
            default:
                break;
        }
    }

    private void sendValue() {
        if (tit != null) {
            SharedPreferencesUtils.setParam(this, "RESULT", tit);
            finish();
        } else {
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "您还没有选择哦!", Color.WHITE, Color.parseColor("#16a6ae"));
        }
    }

    private void initView() {
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        check_list = (RecyclerView) findViewById(R.id.check_list);
        classify_online_view = (LinearLayout) findViewById(R.id.classify_online_view);
        classify_reonline_text = (TextView) findViewById(R.id.classify_reonline_text);
        classify_reonline_text.setOnClickListener(this);
        classify_nonet_view = (LinearLayout) findViewById(R.id.classify_nonet_view);
        activity_publish_cateory_ac = (LinearLayout) findViewById(R.id.activity_publish_cateory_ac);
    }
}
