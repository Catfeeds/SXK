package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyHotBrandModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AttachmentBottomAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AttachmentTopAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
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

public class AttachMentAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView attachment_rv;
    private TextView baobei_textview;
    private RecyclerView baobeifujian_rv;
    private LinearLayout activity_attach_ment_ac;
    private List<AttachmentModel.CategoryListBean.AttachListBean> dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_ment_ac);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("附件");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setText("保存");
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);

        baobei_textview = (TextView) findViewById(R.id.baobei_textview);

        activity_attach_ment_ac = (LinearLayout) findViewById(R.id.activity_attach_ment_ac);


        initData(3);
    }

    private void initData(final int parentid) {

//        {
//            "pageNo": 0,
//                "pageSize": 0,
//                "order": {
//            "sort": 1
//        },
//            "parentid": 0
//        }
        // 通过传过来的parentid来展示页面

        JSONObject order = new JSONObject();
        try {
            order.put("sort",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo",0);
            jsonObject.put("pageSize",0);
            jsonObject.put("order",order);
            jsonObject.put("parentid",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(AttachMentAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGoodsCateify)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(AttachMentAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("附件", "" + response);
                        Gson gson = new Gson();
                        AttachmentModel attachmentModel = gson.fromJson(response, AttachmentModel.class);
                        if (attachmentModel.getCode() == 1) {
                            // 根据穿过来的parentid来取数据
                            for (int i = 0; i < attachmentModel.getCategoryList().size();i++){
                                if (attachmentModel.getCategoryList().get(i).getCategoryid() == parentid){
                                    dataSource = attachmentModel.getCategoryList().get(i).getAttachList();
                                }
                            }

//                            bottomDataSource = attachmentModel.getCategoryList().get(dataSource.size()-1).getAttachList().get(0).getAttributeValueList();

                            // 去掉最后一个数组里面的内容
//                            for (int i = 0 ; i<dataSource.size();i++){
//                                dataSource.remove(dataSource.size()-1);
//                            }
                            if (dataSource!=null){
                                initListRecycleView();

                                initCollectionRecycleView();
                            }


                        } else if (attachmentModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(AttachMentAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (attachmentModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(AttachMentAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });


    }

    private void initCollectionRecycleView() {
        baobeifujian_rv = (RecyclerView) findViewById(R.id.baobeifujian_rv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this,3);
        baobeifujian_rv.setLayoutManager(layoutManager);
        AttachmentBottomAdapter attachmentBottomAdapter = new AttachmentBottomAdapter(this,dataSource.get(dataSource.size()-1).getAttributeValueList());
        baobeifujian_rv.setAdapter(attachmentBottomAdapter);

    }

    private void initListRecycleView() {
        attachment_rv = (RecyclerView) findViewById(R.id.attachment_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        attachment_rv.setLayoutManager(layoutManager);
        AttachmentTopAdapter attachmentTopAdapter = new AttachmentTopAdapter(this,dataSource);
        attachment_rv.setAdapter(attachmentTopAdapter);
        attachmentTopAdapter.setOnItemClickListener(new AttachmentTopAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, List<String> name) {
                LogUtil.e("数组"+name);
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
        }
    }
}
