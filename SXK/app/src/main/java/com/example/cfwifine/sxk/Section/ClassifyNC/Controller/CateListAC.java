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
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyPurchaseListAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ItemAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RentListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
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
    private int Total = 0;
    private int brandid = -1;
    private int cateid = -1;
    private int hotid = -1;
    private int types = -1;
    private TextView buy;
    private ImageView up0;
    private ImageView up00;
    private ImageView dn00;
    private ImageView d0;
    private LinearLayout top0;
    private TextView down0;
    private LinearLayout buyBt;
    private RecyclerView select_item_rv;
    private LinearLayout select_item;
    private boolean isSelected = true;
    private ArrayList<TestModel> selectItemSource;
    private View dismiss_view;
    int[] firstPicArr = {
        R.drawable.textjimaiuicon,R.drawable.textjizuicon
    };
    int[] secondPicArr = {
            R.drawable.textzuiixn,R.drawable.zuizaofabu
    };
    int[] threePicArr = {
            R.drawable.jizucongdidaogao,R.drawable.jizugaodaodi
    };
    private ArrayList<Integer> picArr=null;
    private int isCheck = 0;
    int type = 1;
    private String URLS="";
    private int purchaseTotal;
    private List<PurchaseListModel.PurchaseListBean> purchaseList = null;
    private ClassifyPurchaseListAdapter mClassifyPurchaseListAdapter;
    private int isNew = -1;
    private String VALUES;
    private String PurchaseValue = "purchaseid";
    private String RentValue = "rentid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_list_ac);
        initView();
//        mCatelistRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mloading = LoadingUtils.createLoadingDialog(CateListAC.this, "加载中...");
        brandid = getIntent().getIntExtra("brandid", -1);
        cateid = getIntent().getIntExtra("cateid", -1);
        hotid = getIntent().getIntExtra("hotid", -1);
        types = getIntent().getIntExtra("TYPE", -1);
        if (types != -1) {
            String list = getIntent().getStringExtra("RENTLIST");
            Gson gson = new Gson();
            RentListModel rentListDatas = gson.fromJson(list, RentListModel.class);
            rentList = rentListDatas.getRentList();
            Total = rentListDatas.getTotal();
            initRecycleView();
            return;
        }

        initCateRentlist(type,1, 10);

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
        mTimeBt.setOnClickListener(this);
        mPriceBt.setOnClickListener(this);
        mHotBt.setOnClickListener(this);
        mClassifyCatelistBack.setOnClickListener(this);
        buy = (TextView) findViewById(R.id.buy);
        up0 = (ImageView) findViewById(R.id.up0);
        up00 = (ImageView) findViewById(R.id.up00);
        dn00 = (ImageView) findViewById(R.id.dn00);
        d0 = (ImageView) findViewById(R.id.d0);
        top0 = (LinearLayout) findViewById(R.id.top0);
        down0 = (TextView) findViewById(R.id.down0);
        buyBt = (LinearLayout) findViewById(R.id.buyBt);
        buyBt.setOnClickListener(this);


        select_item_rv = (RecyclerView) findViewById(R.id.select_item_rv);
        select_item = (LinearLayout) findViewById(R.id.select_item);
        select_item.setOnClickListener(this);
        dismiss_view = (View) findViewById(R.id.dismiss_view);
        dismiss_view.setOnClickListener(this);
    }
    private boolean bt0 = false;
    private boolean bt = false;
    private boolean bt2 = false;
    private boolean bt3 = false;

    // TODO********************************配置租赁列表数据********************************
    //获取包袋租赁列表
    private void initCateRentlist(final int type, final int pageNo, int pageSize) {
        if (pageNo == 1){
            rentList = null;
            purchaseList = null;
        }
        mloading.show();
        JSONObject order = new JSONObject();
        try {
            if (type == 1 ){
                order.put(PurchaseValue, isNew);
            }else if(type == 2){
                order.put(RentValue, isNew);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNo);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
            if (type == 1){
                jsonObject.put("status",3);
            }else {
                jsonObject.put("status",2);
            }

            if (brandid != -1) {
                jsonObject.put("brandid", brandid);
            } else if (cateid != -1) {
                jsonObject.put("categoryid", cateid);
            } else if (hotid != -1) {
                jsonObject.put("hotid", hotid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (type == 1){
            URLS = BaseInterface.PurchasePreList;
        }else if (type == 2){
            URLS = BaseInterface.RentList;
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(CateListAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(URLS)
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
                        Gson gson = new Gson();
                        if (type == 1){
                            Log.e("买卖列表", "" + response);
                            PurchaseListModel purchaseListModel = gson.fromJson(response,PurchaseListModel.class);
                            if (purchaseListModel.getCode() == 1) {
                                purchaseTotal = purchaseListModel.getTotal();
                                if (pageNo != 1){
                                    purchaseList.addAll(purchaseListModel.getPurchaseList());
                                    mClassifyPurchaseListAdapter.notifyDataSetChanged();
                                }else {
                                    purchaseList = purchaseListModel.getPurchaseList();
                                    initRecycleView();
                                }
                            } else if (purchaseListModel.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (purchaseListModel.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }

                        }else {
                            Log.e("租赁列表", "" + response);
                            rentList = new ArrayList<RentListModel.RentListBean>();
                            RentListModel rentListData = gson.fromJson(response, RentListModel.class);
                            if (rentListData.getCode() == 1) {
                                Total = rentListData.getTotal();
                                if (pageNo != 1){
                                    rentList.addAll(rentListData.getRentList());
                                    mClassiftAdapter.notifyDataSetChanged();
                                }else {
                                    rentList = rentListData.getRentList();
                                    initRecycleView();
                                }
                            } else if (rentListData.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (rentListData.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(CateListAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        }
                        mloading.dismiss();

                    }
                });
    }

    // TODO********************************点击监听********************************
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buyBt:
                settingBt2(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, getResources().getColor(R.color.login_turquoise),
                        getResources().getColor(R.color.black), getResources().getColor(R.color.black),
                        getResources().getColor(R.color.black));
                bt0 = !bt0;
                if (bt0 == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 1);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 1);
                }
                isCheck = 1;
                // 选中效果
                if (isSelected) {
                    isSelected = false;
                    select_item.setVisibility(View.VISIBLE);
                    selectItemSource = new ArrayList<>();
                    selectItemSource.add(0, new TestModel("寄卖",false));
                    selectItemSource.add(1, new TestModel("寄租",false));
                    picArr = new ArrayList<>();
                    for (int i=0;i<2;i++){
                        picArr.add(i,firstPicArr[i]);
                    }
                    initSelectedRV();
                } else {
                    isSelected = true;
                    select_item.setVisibility(View.GONE);
                }

                break;
            case R.id.timeBt: //按时间排序
                settingBt2(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, getResources().getColor(R.color.black),
                        getResources().getColor(R.color.login_turquoise),
                        getResources().getColor(R.color.black), getResources().getColor(R.color.black));
                bt = !bt;
                if (bt == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 2);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 2);
                }
                isCheck = 2;
                // 选中效果
                if (isSelected) {
                    isSelected = false;
                    select_item.setVisibility(View.VISIBLE);
                    selectItemSource = new ArrayList<>();
                    selectItemSource.add(0, new TestModel("最新发布",false));
                    selectItemSource.add(1, new TestModel("最早发布",false));
                    picArr = new ArrayList<>();
                    for (int i=0;i<2;i++){
                        picArr.add(i,secondPicArr[i]);
                    }
                    initSelectedRV();
                } else {
                    isSelected = true;
                    select_item.setVisibility(View.GONE);
                }

                break;
            case R.id.priceBt: //按价格排序
                settingBt2(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE, getResources().getColor(R.color.black),
                        getResources().getColor(R.color.black),
                        getResources().getColor(R.color.login_turquoise), getResources().getColor(R.color.black));
                bt2 = !bt2;
                if (bt2 == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 3);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 3);
                }
                isCheck = 3;
                initThree();

                break;
            case R.id.hotBt: //按人气排序
                settingBt2(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, getResources().getColor(R.color.black), getResources().getColor(R.color.black),
                        getResources().getColor(R.color.black), getResources().getColor(R.color.login_turquoise));
                bt3 = !bt3;
                if (bt3 == true) {
                    settingBt(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE, 4);
                } else {
                    settingBt(View.GONE, View.VISIBLE, View.VISIBLE, View.GONE, 4);
                }
                isCheck = 4;
                initThree();
                break;
            case R.id.classify_catelist_back:
                finish();
                break;
            case R.id.dismiss_view:
                select_item.setVisibility(View.GONE);
                isSelected = true;
                break;
        }
    }

    private void initThree() {
        // 选中效果
        if (isSelected) {
            isSelected = false;
            select_item.setVisibility(View.VISIBLE);
            selectItemSource = new ArrayList<>();
            selectItemSource.add(0, new TestModel("从高到低",false));
            selectItemSource.add(1, new TestModel("从低到高",false));
            picArr = new ArrayList<>();
            for (int i=0;i<2;i++){
                picArr.add(i,threePicArr[i]);
            }
            initSelectedRV();
        } else {
            isSelected = true;
            select_item.setVisibility(View.GONE);
        }
    }

    private void initSelectedRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ItemAdapter itemAdapter = new ItemAdapter(this, selectItemSource,picArr);
        select_item_rv.setLayoutManager(linearLayoutManager);
        select_item_rv.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {
                LogUtil.e("点击了第"+isCheck+"个"+position);
                // 寄卖 和 寄租
                if (isCheck == 1&&name != ""){
                    buy.setText(name);
                }
                if (isCheck == 1 && position == 0){
                    type = 1;
                }else if (isCheck == 1 && position == 1){
                    type = 2;
                }
                // 时间  排序
                if (type == 1 && isCheck == 2 && position == 0){
                    PurchaseValue = "purchaseid";
                    isNew = -1;
                }else if (type == 1 && isCheck == 2 && position == 1){
                    PurchaseValue = "purchaseid";
                    isNew = 1;
                }else if (type == 2 && isCheck == 2 && position == 0){
                    RentValue = "rentid";
                    isNew = -1;
                }else if (type == 2 && isCheck == 2 && position == 1){
                    RentValue = "rentid";
                    isNew = 1;
                }
                // 价格排序
                if (type == 1 && isCheck == 3 && position == 0){
                    PurchaseValue = "sellingPrice";
                    isNew = 1;
                }else if (type == 1 && isCheck == 3 && position == 1){
                    PurchaseValue = "sellingPrice";
                    isNew = -1;
                }else if (type == 2 && isCheck == 3 && position == 0){
                    RentValue = "rentPrice";
                    isNew = 1;
                }else if (type == 2 && isCheck == 3 && position == 1){
                    RentValue = "rentPrice";
                    isNew = -1;
                }
                // 人气排序
                if (type == 1 && isCheck == 4 && position == 0){
                    PurchaseValue = "sort";
                    isNew = 1;
                }else if (type == 1 && isCheck == 4 && position == 1){
                    PurchaseValue = "sort";
                    isNew = -1;
                }else if (type == 2 && isCheck == 4 && position == 0){
                    RentValue = "sale";
                    isNew = 1;
                }else if (type == 2 && isCheck == 4 && position == 1){
                    RentValue = "sale";
                    isNew = -1;
                }
                initCateRentlist(type,1,10);
                select_item.setVisibility(View.GONE);
            }
        });
    }

    private void initRecycleView() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.login_turquoise, R.color.login_turquoise, R.color.login_turquoise,
                R.color.login_turquoise);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (type == 1){
                            purchaseList.clear();
                        }else {
                            rentList.clear();
                        }
                        pageNums = 1;
                        //注意此处
                        initCateRentlist(type,1, 10);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        if (type == 1){
                            mClassifyPurchaseListAdapter.notifyDataSetChanged();
                        }else {
                            mClassiftAdapter.notifyDataSetChanged();
                        }

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

                        if (type == 1){
                            if (purchaseList.size() >= purchaseTotal) {
                                hao_recycleview.loadMoreEnd();
                                pageNums = 1;
                                return;
                            }
                        }else {
                            if (rentList.size() >= Total) {
                                hao_recycleview.loadMoreEnd();
                                pageNums = 1;
                                return;
                            }
                        }

                        pageNums += 1;
                        initCateRentlist(type,pageNums, 10);
                        hao_recycleview.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        // 1 是寄卖 2是租赁
        if (type == 1){
            mClassifyPurchaseListAdapter = new ClassifyPurchaseListAdapter(purchaseList,this);
            hao_recycleview.setAdapter(mClassifyPurchaseListAdapter);
            mClassifyPurchaseListAdapter.setOnItemClickLintener(new ClassifyPurchaseListAdapter.OnItemClickLintener() {
                @Override
                public void OnItemClick(View view, int purchaseid) {
                    Intent intent = new Intent(CateListAC.this, ProductDetailsAC.class);
                    intent.putExtra("PURCHASEID", purchaseid);
                    startActivity(intent);
                }
            });
        }else {
            mClassiftAdapter = new ClassifyAllListAdapter(rentList, this);
            hao_recycleview.setAdapter(mClassiftAdapter);
            mClassiftAdapter.setOnItemClickLintener(new ClassifyAllListAdapter.OnItemClickLintener() {
                @Override
                public void OnItemClick(View view, int rentid) {
                    Intent intent = new Intent(CateListAC.this, ProductDetailsAC.class);
                    intent.putExtra("RENTID", rentid);
                    startActivity(intent);
                }
            });
        }

    }


    //弹出框
    private void initSnackBar(String name) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), name, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    //设置导航栏图标显隐性
    private void settingBt(int a, int b, int c, int d, int id) {
        if (id == 1) {
            up0.setVisibility(a);
            d0.setVisibility(b);
            up00.setVisibility(c);
            dn00.setVisibility(d);
        } else if (id == 2) {
            mUp1.setVisibility(a);
            mD1.setVisibility(b);
            mUp11.setVisibility(c);
            mDn11.setVisibility(d);
        } else if (id == 3) {
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

    private void settingBt2(int a, int b, int c, int d, int e, int f, int g, int h) {
        down0.setVisibility(a);
        mDown1.setVisibility(b);
        mDown2.setVisibility(c);
        mDown3.setVisibility(d);
        buy.setTextColor(e);
        mTime.setTextColor(f);
        mPrice.setTextColor(g);
        mHot.setTextColor(h);

    }

}
