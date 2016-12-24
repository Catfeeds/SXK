package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyBrandModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CateifyAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.ViewHolder;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.TitleItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PublishBrandAC extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private CateifyAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private List<BrandBean> mDatas;
    private TitleItemDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_brand_ac);
        configurationNaviTitle();
//        initIndexView();
        initBrandList();
    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("品牌");
    }

    private void initIndexView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        //initDatas();

        //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过 2016 09 08
        mAdapter = new CateifyAdapter(this, mDatas,brandNameList);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
//        mHeaderAdapter.setHeaderView(R.layout.item_city, "测试头部");

        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new TitleItemDecoration(this, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mDecoration.setColorTitleBg(Color.WHITE);
        mDecoration.setmTitleHeight(100);
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
//        mRv.addItemDecoration(new DividerItemDecoration(PublishBrandAC.this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new CateifyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int brandid) {
                LogUtil.e("点击了"+name);
                SharedPreferencesUtils.setParam(PublishBrandAC.this,"BRANDID",brandid);
                SharedPreferencesUtils.setParam(PublishBrandAC.this,"BRANDNAME",name);
                Intent brand = new Intent();
                brand.putExtra("BRAND",name);
                PublishBrandAC.this.setResult(666,brand);
                finish();
            }
        });

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        initDatas(brandListArray,brandNameList);
    }
    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final ArrayList<String> data, final List<ClassfiyBrandModel.BrandListBean> brandListBeen) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    BrandBean brandBean = new BrandBean();
                    brandBean.setCity(data.get(i).toString());//设置城市名称
                    mDatas.add(brandBean);
                }
                mAdapter.setDatas(mDatas,brandListBeen);
                mHeaderAdapter.notifyDataSetChanged();

                mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(false)//设置需要真实的索引
                        .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 0);

    }

    ArrayList<String> brandListArray;
    String[] brandListString;
    List<ClassfiyBrandModel.BrandListBean> brandNameList;
    // TODO*********************************配置右侧recycleview数据********************************
    private void initBrandList() {
        JSONObject order = new JSONObject();
        try {
            order.put("brandid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGetAllBrand)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(PublishBrandAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("品牌", "" + response);
                        Gson gson = new Gson();
                        ClassfiyBrandModel brandListData = gson.fromJson(response, ClassfiyBrandModel.class);
                        if (brandListData.getCode() == 1) {
                            brandListArray = new ArrayList<String>();
//                            brandListString = new String[]{};
                            brandNameList = brandListData.getBrandList();

                            for (int i = 0; i < brandNameList.size(); i++) {
                                brandListArray.add(brandNameList.get(i).getName());
                            }
//                            initDatas(brandListArray);
                            initIndexView();
                        } else if (brandListData.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(PublishBrandAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (brandListData.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(PublishBrandAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
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
