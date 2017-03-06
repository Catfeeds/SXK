package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Dialog;
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
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyAllListAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RentListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;

public class CateListAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mClassifyCatelistBack;
    private TextView mDown1;
    private ImageView mUp1;
    private ImageView mDn11;
    private ImageView mD1;
    private LinearLayout mTop1;
    private LinearLayout mTimeBt;
    private TextView mTextView2;
    private ImageView mUp2;
    private ImageView mDn22;
    private ImageView mD2;
    private LinearLayout mTop2;
    private TextView mDown2;
    private LinearLayout mPriceBt;
    private ImageView mUp3;
    private ImageView mDn33;
    private ImageView mD3;
    private LinearLayout mTop3;
    private TextView mDown3;
    private LinearLayout mHotBt;
//    private RecyclerView mCatelistRecyclerview;
    private ImageView mUp11;
    private ImageView mUp22;
    private ImageView mUp33;
    private TextView mTime;
    private TextView mPrice;
    private TextView mHot;
    Dialog mloading;

    List<RentListModel.RentListBean> rentList;
    private ClassifyAllListAdapter mAllListAdapter;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private ClassifyAllListAdapter mClassiftAdapter;
    private int pageNums = 10;
    private int Total= 0;
    private int brandid = -1;
    private int cateid = -1;
    private int hotid = -1;
    private int types = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_list_ac);
        initView();
//        mCatelistRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mloading = LoadingUtils.createLoadingDialog(CateListAC.this,"加载中...");
        brandid = getIntent().getIntExtra("brandid",-1);
        cateid = getIntent().getIntExtra("cateid",-1);
        hotid = getIntent().getIntExtra("hotid",-1);
        types = getIntent().getIntExtra("TYPE",-1);
        if (types != -1){
            String list = getIntent().getStringExtra("RENTLIST");
            Gson gson = new Gson();
            RentListModel rentListDatas = gson.fromJson(list, RentListModel.class);
            rentList = rentListDatas.getRentList();
            Total = rentListDatas.getTotal();
            initRecycleView();
            return;
        }

        initCateRentlist(1,10);

    }


    private void initView() {
        mClassifyCatelistBack = (LinearLayout) findViewById(R.id.classify_catelist_back);
        mDown1 = (TextView) findViewById(R.id.down1);
        mUp1 = (ImageView) findViewById(R.id.up1);
        mDn11 = (ImageView) findViewById(R.id.dn11);
        mD1 = (ImageView) findViewById(R.id.d1);
        mTop1 = (LinearLayout) findViewById(R.id.top1);
        mUp2 = (ImageView) findViewById(R.id.up2);
        mDn22 = (ImageView) findViewById(R.id.dn22);
        mD2 = (ImageView) findViewById(R.id.d2);
        mTop2 = (LinearLayout) findViewById(R.id.top2);
        mDown2 = (TextView) findViewById(R.id.down2);
        mUp3 = (ImageView) findViewById(R.id.up3);
        mDn33 = (ImageView) findViewById(R.id.dn33);
        mD3 = (ImageView) findViewById(R.id.d3);
        mTop3 = (LinearLayout) findViewById(R.id.top3);
        mDown3 = (TextView) findViewById(R.id.down3);
        mTimeBt = (LinearLayout) findViewById(R.id.timeBt);
        mPriceBt = (LinearLayout) findViewById(R.id.priceBt);
        mHotBt = (LinearLayout) findViewById(R.id.hotBt);
        mUp11 = (ImageView) findViewById(R.id.up11);
        mUp22 = (ImageView) findViewById(R.id.up22);
        mUp33 = (ImageView) findViewById(R.id.up33);
        mTime = (TextView) findViewById(R.id.time);
        mPrice = (TextView) findViewById(R.id.price);
        mHot = (TextView) findViewById(R.id.hot);
//        mCatelistRecyclerview = (RecyclerView) findViewById(R.id.catelistRecyclerview);
//        mCatelistRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mTimeBt.setOnClickListener(this);
        mPriceBt.setOnClickListener(this);
        mHotBt.setOnClickListener(this);
        mClassifyCatelistBack.setOnClickListener(this);
    }

    private boolean bt = false;
    private boolean bt2 = false;
    private boolean bt3 = false;


    // TODO********************************配置租赁列表数据********************************
    //获取包袋租赁列表
    private void initCateRentlist(int pageNo, int pageSize) {
        mloading.show();
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNo);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
            if (brandid != -1){
                jsonObject.put("brandid",brandid);
            }else if (cateid != -1){
                jsonObject.put("categoryid",cateid);
            }else if (hotid != -1){
                jsonObject.put("hotid",hotid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(CateListAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RentList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("租赁列表", "" + response);
                        mloading.dismiss();
                        rentList = new ArrayList<RentListModel.RentListBean>();
                        Gson gson = new Gson();
                        RentListModel rentListData = gson.fromJson(response, RentListModel.class);
                        if (rentListData.getCode() == 1) {
                            rentList = rentListData.getRentList();
                            Total = rentListData.getTotal();
                            initRecycleView();
                        } else if (rentListData.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (rentListData.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }

    // TODO********************************点击监听********************************
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.timeBt: //按时间排序
                settingBt2(View.VISIBLE,View.INVISIBLE,View.INVISIBLE,getResources().getColor(R.color.login_turquoise),
                        getResources().getColor(R.color.login_phone_numbercolor),getResources().getColor(R.color.login_phone_numbercolor));
                bt = !bt;
                if (bt == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 1);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 1);
                }
                initAdapter();
                break;
            case R.id.priceBt: //按价格排序
                settingBt2(View.INVISIBLE,View.VISIBLE,View.INVISIBLE,getResources().getColor(R.color.login_phone_numbercolor),
                        getResources().getColor(R.color.login_turquoise),getResources().getColor(R.color.login_phone_numbercolor));
                bt2 = !bt2;
                if (bt2 == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 2);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 2);
                }
                initAdapter();
                break;
            case R.id.hotBt: //按人气排序
                settingBt2(View.INVISIBLE,View.INVISIBLE,View.VISIBLE,getResources().getColor(R.color.login_phone_numbercolor),
                        getResources().getColor(R.color.login_phone_numbercolor),getResources().getColor(R.color.login_turquoise));
                bt3 = !bt3;
                if (bt3 == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 3);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 3);
                }
                initAdapter();
                break;
            case R.id.classify_catelist_back:
                finish();
                break;
        }
    }

    private void initRecycleView() {
        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.login_turquoise, R.color.login_turquoise, R.color.login_turquoise,
                R.color.login_turquoise);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        initCateRentlist(1,10);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mClassiftAdapter.notifyDataSetChanged();
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

                        pageNums += 10;
                        if (pageNums >= Total) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }
                        initCateRentlist(1,pageNums);
                        mClassiftAdapter.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        mClassiftAdapter = new ClassifyAllListAdapter(rentList,this);
        hao_recycleview.setAdapter(mClassiftAdapter);
        mClassiftAdapter.setOnItemClickLintener(new ClassifyAllListAdapter.OnItemClickLintener() {
            @Override
            public void OnItemClick(View view, int rentid) {
                Intent intent = new Intent(CateListAC.this,ProductDetailsAC.class);
                intent.putExtra("RENTID",rentid);
                startActivity(intent);
            }
        });
    }

    // TODO********************************加载适配器********************************
    //加载适配器
    private void initAdapter() {

        if (rentList != null) {
//            mAllListAdapter = new ClassifyAllListAdapter(rentList,this);
//            mCatelistRecyclerview.setAdapter(mAllListAdapter);
            mClassiftAdapter.notifyDataSetChanged();
//            mAllListAdapter.setOnItemClickLintener(new ClassifyAllListAdapter.OnItemClickLintener() {
//                @Override
//                public void OnItemClick(View view, int position) {
//                        Toast.makeText(CateListAC.this,"选择"+position, Toast.LENGTH_SHORT).show();
//                    initSnackBar("选择" + position);
//                    Intent intent = new Intent(CateListAC.this,ProductDetailsAC.class);
//                    startActivity(intent);
//                }
//            });
        }
    }


    //弹出框
    private void initSnackBar(String name) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), name, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    //设置导航栏图标显隐性
    private void settingBt(int a, int b, int c, int d, int id) {
        if (id == 1) {
            mUp1.setVisibility(a);
            mD1.setVisibility(b);
            mUp11.setVisibility(c);
            mDn11.setVisibility(d);
        } else if (id == 2) {
            mUp2.setVisibility(a);
            mD2.setVisibility(b);
            mUp22.setVisibility(c);
            mDn22.setVisibility(d);
        } else {
            mUp3.setVisibility(a);
            mD3.setVisibility(b);
            mUp33.setVisibility(c);
            mDn33.setVisibility(d);
        }

    }

    private void settingBt2(int a, int b, int c, int d,int e,int f) {
        mDown1.setVisibility(a);
        mDown2.setVisibility(b);
        mDown3.setVisibility(c);
        mTime.setTextColor(d);
        mPrice.setTextColor(e);
        mHot.setTextColor(f);

    }

}
