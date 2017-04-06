package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.TagAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Dialog.TagsView.FlowTagLayout;
import com.example.cfwifine.sxk.Section.ClassifyNC.Dialog.TagsView.OnTagSelectListener;
import com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Controller.ClassifySearchDetailAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyHotBrandModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RentListModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
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

public class ClassifySearchAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private EditText navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private LinearLayout activity_classify_search_ac;
    private FlowTagLayout hot_flow_tag;
    private FlowTagLayout history_flow_tag;
    private TagAdapter<String> mSizeTagAdapter;
    private ClassfiyHotBrandModel hotBrandListData = null;
    private TextView hot_view;
    private LinearLayout history_view;
    Dialog dialog;
    private List<RentListModel.RentListBean> rentList = null;
    private String[] mHistoryArr;
    private ArrayList<String> mArrr;
    private TagAdapter<String> mHistoryTagAdapter;
    private TextView clear_history;
    private int types = -1;
    private int rentTotal = 0;
    private List<PurchaseListModel.PurchaseListBean> purchaseList = null;
    private int purchaseTotal = 0;
    private String RENTRESPONESE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_search_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "搜索中...");
        types = getIntent().getIntExtra("type",-1);
        if (types != 1){
            String hotBrandList = getIntent().getStringExtra("HOTBRANDLIST");
            Gson gson = new Gson();
            hotBrandListData = gson.fromJson(hotBrandList, ClassfiyHotBrandModel.class);
        }
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (EditText) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
        activity_classify_search_ac = (LinearLayout) findViewById(R.id.activity_classify_search_ac);

        navi_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (navi_title.getText().toString().trim().length() == 0) {
                        initSnackBar("请输入有效的搜索信息！");
                        return false;
                    } else {
                        initSearchData(navi_title.getText().toString().trim());
                        save();
                    }
                }
                return false;
            }
        });

        hot_flow_tag = (FlowTagLayout) findViewById(R.id.hot_flow_tag);
        hot_flow_tag.setOnClickListener(this);
        hot_view = (TextView) findViewById(R.id.hot_view);



        history_flow_tag = (FlowTagLayout) findViewById(R.id.history_flow_tag);
        history_flow_tag.setOnClickListener(this);
        history_view = (LinearLayout) findViewById(R.id.history_view);
        clear_history = (TextView) findViewById(R.id.clear_history);
        clear_history.setOnClickListener(this);

        if (types == 1){
            hot_view.setVisibility(View.GONE);
            hot_flow_tag.setVisibility(View.GONE);
            initHistory();
        }else {
            initHot();
            initHistory();
        }

    }

    private  void  initHot(){
        if (hotBrandListData.getHotList().size() == 0) {
            hot_view.setVisibility(View.INVISIBLE);
        }

        mSizeTagAdapter = new TagAdapter<>(this);
        hot_flow_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        hot_flow_tag.setAdapter(mSizeTagAdapter);
        hot_flow_tag.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    int select = -1;
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        select = i;
                    }
                    Intent intent = new Intent(ClassifySearchAC.this, CateListAC.class);
                    intent.putExtra("brandid", hotBrandListData.getHotList().get(select).getBrandid());
                    startActivity(intent);
                } else {
                    Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        initSizeData();
    }
    private void initHistory(){
        mHistoryTagAdapter = new TagAdapter<>(this);
        history_flow_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        history_flow_tag.setAdapter(mHistoryTagAdapter);
        history_flow_tag.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                    }
                    initSearchData(sb.toString().trim());
                }
            }
        });

        initHistoryData();
    }


    private void initHistoryData() {
        update();
    }

    private void save() {
        String text = navi_title.getText().toString().trim();
        String oldText = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.KEYWORD, ""));
        if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
            SharedPreferencesUtils.setParam(this, BaseInterface.KEYWORD, text + "," + oldText);
        }
        // 重新加载数据
        update();
    }

    public void update() {
        String history = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.KEYWORD, ""));
        LogUtil.e("输出的字段0" + history);
        mHistoryArr = history.split(",");

        if (mHistoryArr.toString().trim() == "") {
            history_view.setVisibility(View.INVISIBLE);
        }
        mArrr = new ArrayList<String>();
        mArrr.clear();
        String value ="";
        for (int i = 0; i < mHistoryArr.length; i++) {
            if (i<=15){
                value += mHistoryArr[i]+",";
                if (!mHistoryArr[i].toString().trim().equals("")) {
                    mArrr.add(i, mHistoryArr[i]);
                }
            }
        }
        SharedPreferencesUtils.setParam(this,BaseInterface.KEYWORD,"");
        SharedPreferencesUtils.setParam(this,BaseInterface.KEYWORD,value);
        mHistoryTagAdapter.clearAndAddAll(mArrr);

    }


    private void initSizeData() {
        List<String> dataSource = new ArrayList<>();
        if (hotBrandListData != null && hotBrandListData.getCode() == 1) {
            if (hotBrandListData.getHotList().size()>=30){
                for (int i = 0; i < 30; i++) {
                    dataSource.add(i, hotBrandListData.getHotList().get(i).getName());
                }
            }else {
                for (int i = 0; i < hotBrandListData.getHotList().size(); i++) {
                    dataSource.add(i, hotBrandListData.getHotList().get(i).getName());
                }
            }

        }
        mSizeTagAdapter.onlyAddAll(dataSource);
    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void initSearchData(final String trim) {
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("rentid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", order);
            js.put("name", trim);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RentList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("搜索1", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        RentListModel rentListData = gson.fromJson(response, RentListModel.class);
                        if (rentListData.getCode() == 1) {
                            rentList = rentListData.getRentList();
                            rentTotal = rentListData.getTotal();
                            RENTRESPONESE = response;
                            initSearchPurchaseData(trim);
                        } else if (rentListData.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (rentListData.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }
    private void initSearchPurchaseData(String trim) {
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("purchaseid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", order);
            js.put("name", trim);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.PurchasePreList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("搜索2", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        PurchaseListModel purchaseListModel = gson.fromJson(response, PurchaseListModel.class);
                        if (purchaseListModel.getCode() == 1) {
                            purchaseList = purchaseListModel.getPurchaseList();
                            purchaseTotal = purchaseListModel.getTotal();
                            if (rentTotal == 0 && purchaseTotal == 0) {
                                initSnackBar("暂时没有搜索到该商品！");
                            } else {
                                Intent intent = new Intent(ClassifySearchAC.this, ClassifySearchDetailAC.class);
                                intent.putExtra("TYPE", 3);
                                intent.putExtra("PURCHASE",response);
                                intent.putExtra("RENTLIST",RENTRESPONESE);
                                startActivity(intent);
                            }
                        } else if (purchaseListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (purchaseListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_right_lays:
                if (navi_title.getText().toString().trim().length() == 0) {
                    initSnackBar("请输入有效的搜索信息！");
                    return;
                } else {
                    initSearchData(navi_title.getText().toString().trim());
                    save();
                }
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.clear_history:
                SharedPreferencesUtils.setParam(this, BaseInterface.KEYWORD, "");
//                mHistoryTagAdapter.notifyDataSetChanged();
                mArrr.clear();
                mHistoryTagAdapter.clearAndAddAll(mArrr);
                break;
            default:
                break;
        }
    }


}
