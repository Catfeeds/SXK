package com.example.cfwifine.sxk.Section.MineNC.Controller.MineAppraisa;

import android.app.Dialog;
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
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter.MineItemCuringListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemCuringModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
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

public class MineItemAppraisaAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private HaoRecyclerView hao_recycleview;
    private SwipeRefreshLayout swiperefresh;
    private MineItemAppraisaListAdapter mSheHeAdapter;
    Dialog dialog;
    private List<MineItemAppraisaModel.OrderListBean> rentListDataSouce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_item_appraisa_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }

    private void initSheHeRV() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        initMineData(2, 0, 0);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mSheHeAdapter.notifyDataSetChanged();
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
		progressView.setIndicatorColor(0xff69b3e0);
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

//                        pageNum += 10;
//                        L.e("pageNum" + pageNum);
//                        if (pageNum >= Total) {
//                            hao_recycleview.loadMoreEnd();
//                            return;
//                        }
//                        CuringAC curingAC = (CuringAC) getActivity();
//                        curingAC.initData(-2,1,pageNum);
//                        mAdapter.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();

					}
				}, 1000);
			}
		});
        mSheHeAdapter = new MineItemAppraisaListAdapter(this, rentListDataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);


    }

    public void initMineData(final int status, int pageNum, int pageSize){
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("orderid",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo",pageNum);
            jsonObject.put("pageSize",pageSize);
            jsonObject.put("order",order);
//            jsonObject.put("status","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.AppraisaOrderList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求失败！");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("鉴定中", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        MineItemAppraisaModel mineItemCuringModel = gson.fromJson(response, MineItemAppraisaModel.class);
                        if (mineItemCuringModel.getCode() == 1) {
                            rentListDataSouce = mineItemCuringModel.getOrderList();
                            initSheHeRV();
                        } else if (mineItemCuringModel.getCode() == 0) {
                          initSnackBar("请求失败！");
                        } else if (mineItemCuringModel.getCode() == 911) {
                           initSnackBar("登录超时，请重新登录");
                        }



                    }
                });

    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }


    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("我的鉴定");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        initMineData(2,0,0);
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
