package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ProducetDetailCommentRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductCommentListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
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

public class CommentDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private HaoRecyclerView hao_recycleview;
    private SwipeRefreshLayout swiperefresh;
    private LinearLayout activity_comment_detail_ac;
    private List<ProductCommentListModel.BrandListBean> CommentDataSource = null;
    private int rentid = -1;
    private ProducetDetailCommentRecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail_ac);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("评价详情");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        activity_comment_detail_ac = (LinearLayout) findViewById(R.id.activity_comment_detail_ac);
        rentid = getIntent().getIntExtra("rentid", -1);
        initCommentDataSource();
    }

    private void initCommentDataSource() {
        JSONObject commentid = new JSONObject();
        try {
            commentid.put("commentid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", commentid);
            js.put("rentid", rentid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ProductDetailCommentList)
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
                        Log.e("商品评价列表", "" + response);
                        Gson gson = new Gson();
                        ProductCommentListModel productCommentListModel = gson.fromJson(response, ProductCommentListModel.class);
                        if (productCommentListModel.getCode() == 1) {
                            CommentDataSource = productCommentListModel.getBrandList();
                            if (CommentDataSource.size() != 0) {
                                initCommentRecycyleView();
                            }

                        } else if (productCommentListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (productCommentListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initCommentRecycyleView() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        initCommentDataSource();
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

                        //注意此处
                        hao_recycleview.loadMoreEnd();
                        return;
//                        initCateRentlist(1,pageNums);
//                        mClassiftAdapter.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        mAdapter = new ProducetDetailCommentRecycleViewAdapter(this, CommentDataSource, 2);
        hao_recycleview.setAdapter(mAdapter);

    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
