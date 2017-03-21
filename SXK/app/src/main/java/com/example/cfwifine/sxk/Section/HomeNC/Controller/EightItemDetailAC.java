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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.EightItemActivityRecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ActivityListModel;
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

public class EightItemDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private HaoRecyclerView activity_recycleview;
    private SwipeRefreshLayout swiperefresh;
    private LinearLayout home_activity_view;
    private LinearLayout activity_eight_item_detail_ac;
    EightItemActivityRecycleAdapter eightItemActivityRecycleAdapter;
    List<ActivityListModel.ActivityListBean> dataSource;
    private int pageSize=10;
    private int pageNum=1;
    private int Total ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_item_detail_ac);
        initView();
        initListData(1,10);
    }

    /**
     * 初始化数据
     */
    private void initListData(int pageNo,int pageSize) {
        if (pageNum == 1){
            dataSource = null;
        }
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeEightItemActivityList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("活动列表", "" + response);
                        LogUtil.e("活动列表"+response);
                        Gson gson = new Gson();
                        ActivityListModel activityListModel = gson.fromJson(response,ActivityListModel.class);
                        if (activityListModel.getCode() == 1) {
                            Total = activityListModel.getTotal();
                            if (pageNum != 1){
                                dataSource.addAll(activityListModel.getActivityList());
                                eightItemActivityRecycleAdapter.notifyDataSetChanged();
                            }else {
                                dataSource = activityListModel.getActivityList();
                                initRecycleView();
                            }

                        } else if (activityListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (activityListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });

    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("活动");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);

        home_activity_view = (LinearLayout) findViewById(R.id.home_activity_view);
        activity_eight_item_detail_ac = (LinearLayout) findViewById(R.id.activity_eight_item_detail_ac);

//        initRecycleView();
    }

    private void initRecycleView() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        initListData(1,10);
                        pageNum = 1;
                        //注意此处
                        activity_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        eightItemActivityRecycleAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        activity_recycleview = (HaoRecyclerView) findViewById(R.id.activity_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this)  {
            //            @Override
//            public boolean canScrollVertically() {
//            return false;
//        }
            // SC嵌套ReCV防止数据显示不完整
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activity_recycleview.setLayoutManager(layoutManager);
        activity_recycleview.setCanloadMore(true);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff16a6ae);
        activity_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        activity_recycleview.setFootEndView(textView);

        activity_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        if (dataSource.size() >= Total) {
                            activity_recycleview.loadMoreEnd();
                            pageNum = 1;
                            return;
                        }
                        pageNum += 1;
                        initListData(pageNum,pageSize);
//                        eightItemActivityRecycleAdapter.notifyDataSetChanged();
                        activity_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        eightItemActivityRecycleAdapter = new EightItemActivityRecycleAdapter(this,dataSource);
        activity_recycleview.setAdapter(eightItemActivityRecycleAdapter);

        eightItemActivityRecycleAdapter.setOnItemClickListener(new EightItemActivityRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(EightItemDetailAC.this,ActivityDetailAC.class);
                intent.putExtra("value",position);
                startActivity(intent);
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
