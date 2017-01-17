package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.CateListAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.ClassDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.HotDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.ThreeBlockDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeClassSelectedModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeHotDetalListModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ThreeBlockModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;

public class HomeThreeBlockDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private HaoRecyclerView hao_recycleview;
    private SwipeRefreshLayout swiperefresh;
    private ThreeBlockDetailListAdapter mAdapter;
    private ThreeBlockModel threeBlockModel=null;
    private int types=-1;
    private int pageNum=1;
    private int pageSize=10;
    String url = "";
    private List<ThreeBlockModel.SetupBean.RentListBean> threeDataSource;
    private HomeClassSelectedModel classData;
    private List<HomeClassSelectedModel.ClassBean.RentListBean> classDataSource;
    private ClassDetailListAdapter mAdapters;
    private List<HomeHotDetalListModel.TopicBean.RentListBean> hotDataSource;
    private HotDetailListAdapter hotAdapter;
    private int topicid=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_three_block_detail_ac);
        initView();
    }

    private void initView() {
        types = getIntent().getIntExtra("JUMPEIGHTITEMDETAIL",-1);
        // 11 左上 12 右上 13 右下
        if (types == 11){
            url = BaseInterface.HomeLeftThreeBlock;
        }else if (types == 12){
            url = BaseInterface.HomeRightTopPic;
        }else if (types == 13){
            url = BaseInterface.HomeRightBottomPic;
        }else if (types == 14){
            url = BaseInterface.HomeClassSelected;
        }else if (types == 16){
            url = BaseInterface.HomeHotDetail;
        }
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        if (types == 11){
            navi_title.setText("私人订制");
        }else if (types == 12){
            navi_title.setText("啵呗优选");
        }else if (types == 13){
            navi_title.setText("放心租");
        }else if (types == 14){
            navi_title.setText("精选分类");
        }else if (types == 16){
            navi_title.setText("热门专题");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        if (types != 16){
            initData(pageNum, pageSize);
        }else {
            topicid = getIntent().getIntExtra("TOPICID",-1);
            initHotData(topicid);
        }
    }

    private void initHotData(int topicid) {
        JSONObject js = new JSONObject();
        try {
            js.put("topicid",topicid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(url)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("热门数据列表", "" + response);
                        Gson gson = new Gson();
                        HomeHotDetalListModel homeHotDetalListModel = gson.fromJson(response,HomeHotDetalListModel.class);
                        if (homeHotDetalListModel.getCode() == 1) {
                            hotDataSource = homeHotDetalListModel.getTopic().getRentList();
                            initHotRecycleView();
                        } else if (homeHotDetalListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (homeHotDetalListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    public void initHotRecycleView() {

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        注意此处
                        hotDataSource.clear();
                        initHotData(topicid);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        hotAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff16a6ae);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

//                        pageSize += 10;
//                        pageNum += 1;
//                        if (pageNum >= threeDataSource.size()) {
//                            hao_recycleview.loadMoreEnd();
//                            return;
//                        }
//                        initHotData(topicid);
//                        hotAdapter.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();
                        hao_recycleview.loadMoreEnd();
                    }
                }, 1000);
            }
        });
        hotAdapter = new HotDetailListAdapter(this, hotDataSource);
        hao_recycleview.setAdapter(hotAdapter);
        hotAdapter.setOnItemClickListener(new HotDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                Intent intent = new Intent(HomeThreeBlockDetailAC.this,ProductDetailsAC.class);
                intent.putExtra("RENTID",maintainid);
                startActivity(intent);
            }
        });
    }


    public void initData(int pageNum, int pageSize) {

        JSONObject jsonObject = new JSONObject();
        if (types != 14){

            try {
                jsonObject.put("setupid", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            int classid = getIntent().getIntExtra("CLASSID",-1);
            try {
                jsonObject.put("classid",classid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(url)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("数据列表", "" + response);
                        Gson gson = new Gson();
                        if (types != 14){
                            threeBlockModel = gson.fromJson(response, ThreeBlockModel.class);
                            if (threeBlockModel.getCode() == 1) {
                                LogUtil.e("请求成功"+1);
                                threeDataSource = threeBlockModel.getSetup().getRentList();
                                initRecycleView();
                            } else if (threeBlockModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (threeBlockModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        }else {
                            classData = gson.fromJson(response, HomeClassSelectedModel.class);
                            if (classData.getCode() == 1) {
                                LogUtil.e("请求成功"+1);
                                classDataSource = classData.getClassX().getRentList();
                                initClassRecycleView();
                            } else if (classData.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (classData.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        }

                    }
                });
    }

    private void initClassRecycleView() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        注意此处
                        classDataSource.clear();
                        initData(1, 10);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapters.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff16a6ae);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        pageSize += 10;
                        pageNum += 1;
                        if (pageNum >= classDataSource.size()) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }
                        initData(pageNum, pageSize);
                        mAdapters.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        mAdapters = new ClassDetailListAdapter(this, classDataSource);
        hao_recycleview.setAdapter(mAdapters);
        mAdapters.setOnItemClickListener(new ClassDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                Intent intent = new Intent(HomeThreeBlockDetailAC.this,ProductDetailsAC.class);
                intent.putExtra("RENTID",maintainid);
                startActivity(intent);
            }
        });
    }

    public void initRecycleView() {

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        注意此处
                        threeDataSource.clear();
                        initData(1, 10);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff16a6ae);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        pageSize += 10;
                        pageNum += 1;
                        if (pageNum >= threeDataSource.size()) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }
                        initData(pageNum, pageSize);
                        mAdapter.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        mAdapter = new ThreeBlockDetailListAdapter(this, threeDataSource);
        hao_recycleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ThreeBlockDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                Intent intent = new Intent(HomeThreeBlockDetailAC.this,ProductDetailsAC.class);
                intent.putExtra("RENTID",maintainid);
                startActivity(intent);
            }
        });
    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
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
