package com.example.cfwifine.sxk.Section.HomeNC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.CateListAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.PurchaseDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Model.PurchasePreListModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
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

public class HomePurchaiseListAC extends AppCompatActivity implements View.OnClickListener {

    private int types = -1;
    private String url;
    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private int topicid = -1;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;

    Dialog dialog;
    private PurchaseListModel purchasePreListModel;
    private List<PurchaseListModel.PurchaseListBean> dataSource = null;
    private PurchaseDetailListAdapter hotAdapter;
    private int pageSize = 10;
    int pageNum = 1;
    private int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_purchaise_list_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
    }

    private void initView() {
        types = getIntent().getIntExtra("JUMPEIGHTITEMDETAIL", -1);
        // 11 左上 12 右上 13 右下
        if (types == 18) {
            url = BaseInterface.PurchasePreList;
        }
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        if (types == 18) {
            navi_title.setText("寄售");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        initHotData(1, 10);
    }

    private void initHotData(final int pageNum, int pageSize) {
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("purchaseid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", pageNum);
            js.put("pageSize", pageSize);
//            js.put("own", 1);
            js.put("status", 3);
            js.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 说明是第一次刷新
        if (pageNum == 1){
            dataSource = null;
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
                                 dialog.dismiss();
                                 LogUtil.e("寄卖商品列表" + response);
                                 Gson gson = new Gson();
                                 purchasePreListModel = gson.fromJson(response, PurchaseListModel.class);
                                 if (purchasePreListModel.getCode() == 1) {
                                     Total = purchasePreListModel.getTotal();
                                     if (pageNum != 1){
                                         dataSource.addAll(purchasePreListModel.getPurchaseList());
                                         hotAdapter.notifyDataSetChanged();
                                     }else {
                                         dataSource = purchasePreListModel.getPurchaseList();
                                         initHotRecycleView();
                                     }
                                 } else if (purchasePreListModel.getCode() == 0) {
                                     initSnackBar("请求失败！");
                                 } else if (purchasePreListModel.getCode() == 911) {
                                     initSnackBar("登录超时，请重新登录！");
                                 }
                             }
                         }

                );
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
                        dataSource.clear();
                        pageNum = 1;
                        initHotData(1, 10);
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
        hao_recycleview.setCanloadMore(true);
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
                        if (dataSource.size() >= Total) {
                            hao_recycleview.loadMoreEnd();
                            pageNum = 1;
                            return;
                        }
                        pageNum += 1;
                        LogUtil.e("打印的数量"+pageNum);
                        initHotData(pageNum, pageSize);
                        hao_recycleview.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        hotAdapter = new PurchaseDetailListAdapter(this, dataSource);
        hao_recycleview.setAdapter(hotAdapter);

        hotAdapter.setOnItemClickListener(new PurchaseDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int purchaseid) {
                Intent intent = new Intent(HomePurchaiseListAC.this, ProductDetailsAC.class);
                intent.putExtra("PURCHASEID", purchaseid);
                startActivity(intent);
            }
        });
    }


//    public void initData(int pageNum, int pageSize) {
//
//        JSONObject jsonObject = new JSONObject();
//        if (types != 14) {
//
//            try {
//                jsonObject.put("setupid", 1);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
//            int classid = getIntent().getIntExtra("CLASSID", -1);
//            try {
//                jsonObject.put("classid", classid);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
//        OkHttpUtils.postString().url(url)
//                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
//                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader("Content-Type", "application/json;chartset=utf-8")
//                .content(jsonObject.toString())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        initSnackBar("请求出错！");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("数据列表", "" + response);
//                        Gson gson = new Gson();
//                        if (types != 14) {
//                            threeBlockModel = gson.fromJson(response, ThreeBlockModel.class);
//                            if (threeBlockModel.getCode() == 1) {
//                                LogUtil.e("请求成功" + 1);
//                                threeDataSource = threeBlockModel.getSetup().getRentList();
//                                initRecycleView();
//                            } else if (threeBlockModel.getCode() == 0) {
//                                initSnackBar("请求失败！");
//                            } else if (threeBlockModel.getCode() == 911) {
//                                initSnackBar("登录超时，请重新登录！");
//                            }
//                        } else {
//                            classData = gson.fromJson(response, HomeClassSelectedModel.class);
//                            if (classData.getCode() == 1) {
//                                LogUtil.e("请求成功" + 1);
//                                classDataSource = classData.getClassX().getRentList();
//                                initClassRecycleView();
//                            } else if (classData.getCode() == 0) {
//                                initSnackBar("请求失败！");
//                            } else if (classData.getCode() == 911) {
//                                initSnackBar("登录超时，请重新登录！");
//                            }
//                        }
//
//                    }
//                });
//    }
//
//    private void initClassRecycleView() {
//        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
//        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
//                R.color.textBlueDark);
//
//        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
////                        注意此处
//                        classDataSource.clear();
//                        initData(1, 10);
//                        hao_recycleview.refreshComplete();
//                        swiperefresh.setRefreshing(false);
//                        mAdapters.notifyDataSetChanged();
//                    }
//                }, 1000);
//
//            }
//        });
//        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
//            @Override
//            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
//                super.onMeasure(recycler, state, widthSpec, expandSpec);
//            }
//        };
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        hao_recycleview.setLayoutManager(layoutManager);
//        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(this);
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff16a6ae);
//        hao_recycleview.setFootLoadingView(progressView);
//
//        TextView textView = new TextView(this);
//        textView.setText("已经到底啦~");
//        textView.setTextColor(getResources().getColor(R.color.black));
//        hao_recycleview.setFootEndView(textView);
//
//        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//
//                        pageSize += 10;
//                        if (pageSize >= classDataSource.size()) {
//                            hao_recycleview.loadMoreEnd();
//                            return;
//                        }
//                        initData(1, pageSize);
//                        mAdapters.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();
//
//                    }
//                }, 1000);
//            }
//        });
//        mAdapters = new ClassDetailListAdapter(this, classDataSource);
//        hao_recycleview.setAdapter(mAdapters);
//        mAdapters.setOnItemClickListener(new ClassDetailListAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int maintainid) {
//                Intent intent = new Intent(HomePurchaiseListAC.this, ProductDetailsAC.class);
//                intent.putExtra("RENTID", maintainid);
//                startActivity(intent);
//            }
//        });
//    }
//
//    public void initRecycleView() {
//
//        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
//        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
//                R.color.textBlueDark);
//
//        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
////                        注意此处
//                        threeDataSource.clear();
//                        initData(1, 10);
//                        hao_recycleview.refreshComplete();
//                        swiperefresh.setRefreshing(false);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }, 1000);
//
//            }
//        });
//        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
//            @Override
//            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
//                super.onMeasure(recycler, state, widthSpec, expandSpec);
//            }
//        };
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        hao_recycleview.setLayoutManager(layoutManager);
//        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(this);
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff16a6ae);
//        hao_recycleview.setFootLoadingView(progressView);
//
//        TextView textView = new TextView(this);
//        textView.setText("已经到底啦~");
//        textView.setTextColor(getResources().getColor(R.color.black));
//        hao_recycleview.setFootEndView(textView);
//
//        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//
//                        pageSize += 10;
//                        if (pageNum >= threeDataSource.size()) {
//                            hao_recycleview.loadMoreEnd();
//                            return;
//                        }
//                        initData(1, pageSize);
//                        mAdapter.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();
//
//                    }
//                }, 1000);
//            }
//        });
//        mAdapter = new ThreeBlockDetailListAdapter(this, threeDataSource);
//        hao_recycleview.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new ThreeBlockDetailListAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int maintainid) {
//                Intent intent = new Intent(HomeThreeBlockDetailAC.this, ProductDetailsAC.class);
//                intent.putExtra("RENTID", maintainid);
//                startActivity(intent);
//            }
//        });
//    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
        }
    }

}
