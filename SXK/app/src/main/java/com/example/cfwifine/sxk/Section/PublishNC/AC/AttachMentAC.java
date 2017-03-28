package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AttachmentBottomAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AttachmentTopAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ArrayList<TestModel> listData;
    private ArrayList<String> StringList;
    private ArrayList<String> nameList;
    private String material = "";
    private int FUJIANPOSITION = -1;
    private AttachmentTopAdapter attachmentTopAdapter = null;
    private String FUJIAN;
    private int ATTRIBUTENAMEPOSITION = -1;
    private String FUJIANNAME = "";
    private JSONArray jsonArray = null;
    private HashMap<Integer, Map<String,String>> map;
    Map<Integer, String> mapMap = new HashMap<>();
    private JSONObject js;
    private JSONObject jss=null;
    private JSONArray finalJsonArr=null;
    private JSONArray xxx= new JSONArray();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_ment_ac);
        SharedPreferencesUtils.setParam(this, "FUJIANPOSITION", -1);
        SharedPreferencesUtils.setParam(this, "FUJIAN", "");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("附件");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right.setText("保存");
        navi_right.setTextColor(getResources().getColor(R.color.login_turquoise));
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);

        baobei_textview = (TextView) findViewById(R.id.baobei_textview);

        activity_attach_ment_ac = (LinearLayout) findViewById(R.id.activity_attach_ment_ac);

        int cateoryid = getIntent().getIntExtra("CATEID", 0);
        LogUtil.e("测试id" + cateoryid);
        initData(cateoryid);




    }

    private void initData(final int parentid) {
        // 通过传过来的parentid来展示页面

        JSONObject order = new JSONObject();
        try {
            order.put("sort", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
            jsonObject.put("parentid", 0);
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
                            for (int i = 0; i < attachmentModel.getCategoryList().size(); i++) {
                                if (attachmentModel.getCategoryList().get(i).getCategoryid() == parentid) {
                                    dataSource = attachmentModel.getCategoryList().get(i).getAttachList();
                                }
                            }
                            if (dataSource.size() != 0) {
                                listData = new ArrayList<TestModel>();
                                for (int i = 0; i < dataSource.size(); i++) {
                                    if (dataSource.get(i).getAttributeName().trim().equals("相关配件")) {
                                        for (int j = 0; j<dataSource.get(i).getAttributeValueList().size();j++){
                                            TestModel testModel = new TestModel(dataSource.get(i).getAttributeValueList().get(j), false);
                                            listData.add(j, testModel);
                                        }
                                        dataSource.remove(i);


                                    }
                                }
                                initListRecycleView();
                                initCollectionRecycleView();
                            }else {
                                baobei_textview.setVisibility(View.GONE);
                                navi_right.setOnClickListener(null);
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
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        baobeifujian_rv.setLayoutManager(layoutManager);
        AttachmentBottomAdapter attachmentBottomAdapter = new AttachmentBottomAdapter(this, listData);
        baobeifujian_rv.setAdapter(attachmentBottomAdapter);
        attachmentBottomAdapter.setOnItemClickListener(new AttachmentBottomAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ArrayList<TestModel> check) {
                StringList = new ArrayList<String>();
                for (int i = 0; i < check.size(); i++) {
                    if (check.get(i).getState()) {
                        StringList.add(check.get(i).getText());
                    }
                }
                JSONArray yy = new JSONArray(StringList);
                JSONObject x = new JSONObject();
                try {
                    x.put("attributeName","相关配件");
                    x.put("attributeValueList",yy);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if (FUJIANPOSITION == -1){
                        xxx.put(0,x);
                    }else {
                        xxx.put(dataSource.size(),x);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("选中的有", "" + xxx);
            }
        });

    }

    private void initListRecycleView() {
        attachment_rv = (RecyclerView) findViewById(R.id.attachment_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        attachment_rv.setLayoutManager(layoutManager);
        attachmentTopAdapter = new AttachmentTopAdapter(this, dataSource, FUJIANPOSITION);
        attachment_rv.setAdapter(attachmentTopAdapter);
        attachmentTopAdapter.setOnItemClickListener(new AttachmentTopAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, List<String> name, String attributeName, int position) {
                LogUtil.e("数组" + name);
                ATTRIBUTENAMEPOSITION = position;
                nameList = new ArrayList<>();
                for (int i = 0; i < name.size(); i++) {
                    nameList.add(i, name.get(i));
                }
                Intent intent = new Intent(AttachMentAC.this, CheckRecycleViewAC.class);
                intent.putStringArrayListExtra("ATTACHMENT", nameList);
                intent.putStringArrayListExtra("BAOBEIFUJIAN", StringList);
                intent.putExtra("TITLESS", attributeName);
                intent.putExtra("CHENGSE", 5);
                startActivity(intent);
//                finish();
            }
        });


    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        FUJIANNAME = String.valueOf(SharedPreferencesUtils.getParam(this, "FUJIAN", ""));
        FUJIANPOSITION = (int) SharedPreferencesUtils.getParam(this, "FUJIANPOSITION", -1);
        if (attachmentTopAdapter != null) {
            LogUtil.e("包袋材质" + FUJIANPOSITION);
//            attachmentTopAdapter.notifyDataSetChanged();
            if (FUJIANPOSITION != -1) {
                dataSource.get(ATTRIBUTENAMEPOSITION).getAttributeValueList().set(FUJIANPOSITION,dataSource.get(ATTRIBUTENAMEPOSITION).getAttributeValueList().get(FUJIANPOSITION));
//                attachmentTopAdapter.notifyDataSetChanged();
                attachmentTopAdapter.setData(ATTRIBUTENAMEPOSITION, FUJIANPOSITION);
                for (int i = 0; i<dataSource.size();i++){
                    if (ATTRIBUTENAMEPOSITION == i){
                        JSONArray arr = new JSONArray();
                        arr.put(dataSource.get(i).getAttributeValueList().get(FUJIANPOSITION));
                        JSONObject arrs = new JSONObject();
                        try {
                            arrs.put("attributeName",dataSource.get(i).getAttributeName());
                            arrs.put("attributeValueList",arr);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            xxx.put(i,arrs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        LogUtil.e("输出的数组"+xxx);


                    }
                }


            }
        }


        super.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right:

                if (xxx == null) {
                    SnackbarUtils.showShortSnackbar(AttachMentAC.this.getWindow().getDecorView(), "你还没有选择！", Color.WHITE, Color.parseColor("#16a6ae"));
                    return;
                } else {
                    SharedPreferencesUtils.setParam(this, "FINALLYARR", xxx.toString());
                    finish();
                }
                break;

        }
    }
}
