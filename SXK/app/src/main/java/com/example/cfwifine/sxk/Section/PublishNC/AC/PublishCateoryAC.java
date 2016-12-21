package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.app.Dialog;
import android.content.Intent;
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
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifyCateModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CateoryRecycleViewAdapter;
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

public class PublishCateoryAC extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    private List<ClassifyCateModel.CategoryListBean> listData = new ArrayList<>();
    Dialog mloading;
    private LinearLayout classify_online_view;
    private TextView classify_reonline_text;
    private LinearLayout classify_nonet_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_cateory_ac);
        initView();
        mloading = LoadingUtils.createLoadingDialog(this, "正在加载中...");
        configurationNaviTitle();
        initData(0);


    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("类别");
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
                        SnackbarUtils.showShortSnackbar(PublishCateoryAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        classify_online_view.setVisibility(View.GONE);
                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("一级分类", "" + response);
                        mloading.dismiss();
                        Gson gson = new Gson();
                        ClassifyCateModel cateModel = gson.fromJson(response, ClassifyCateModel.class);
                        if (cateModel.getCode() == 1) {
                            classify_online_view.setVisibility(View.VISIBLE);
                            classify_nonet_view.setVisibility(View.GONE);
                            if (value == 0) {
//                                for (int i = 0; i < cateModel.getTotal(); i++) {
//                                    listData.add(i,cateModel.getCategoryList().get(i).getName().toString());
//                                }
                                listData = cateModel.getCategoryList();
                                initRecycleView();
                            } else {
                                LogUtil.e("二级分类" + response);

                            }

                        } else if (cateModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(PublishCateoryAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        } else if (cateModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(PublishCateoryAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });


    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.cateory_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CateoryRecycleViewAdapter cateoryRecycleViewAdapter = new CateoryRecycleViewAdapter(listData);
        recyclerView.setAdapter(cateoryRecycleViewAdapter);
        cateoryRecycleViewAdapter.setOnItemClickListener(new CateoryRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position,String title) {
                LogUtil.e("parentid"+position);
                Intent intent = new Intent(PublishCateoryAC.this, CheckRecycleViewAC.class);
                intent.putExtra("POSITION", position);
                intent.putExtra("TITLE",title);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.classify_reonline_text:
                initData(0);
                break;
            default:
                break;
        }
    }

    private void initView() {
        classify_online_view = (LinearLayout) findViewById(R.id.classify_online_view);
        classify_reonline_text = (TextView) findViewById(R.id.classify_reonline_text);
        classify_reonline_text.setOnClickListener(this);
        classify_nonet_view = (LinearLayout) findViewById(R.id.classify_nonet_view);
    }
}
