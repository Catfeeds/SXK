package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter.MineItemCuringListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter.MineItemDoneListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemCuringModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemDoneMoel;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineItemDoneFC extends Fragment {


    private View view;
    private MineItemCuringAC mineItemCuringAC;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MineItemDoneListAdapter mSheHeAdapter;
    private List<MineItemDoneMoel.OrderListBean> rentListDataSouce;

    public MineItemDoneFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mine_item_done_fc, container, false);
            mineItemCuringAC = (MineItemCuringAC) getActivity();
            initMineData(3,0,0);
        }

        return view;
    }
    private void initSheHeRV() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        initMineData(3,0,0);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mSheHeAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) view.findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
//		//设置自定义加载中和到底了效果
//		ProgressView progressView = new ProgressView(getActivity());
//		progressView.setIndicatorId(ProgressView.BallPulse);
//		progressView.setIndicatorColor(0xff69b3e0);
//		hao_recycleview.setFootLoadingView(progressView);
//
//		TextView textView = new TextView(getActivity());
//		textView.setText("已经到底啦~");
//		textView.setTextColor(getResources().getColor(R.color.black));
//		hao_recycleview.setFootEndView(textView);
//
//		hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
//			@Override
//			public void onLoadMore() {
//				new Handler().postDelayed(new Runnable() {
//					public void run() {
//
////                        pageNum += 10;
////                        L.e("pageNum" + pageNum);
////                        if (pageNum >= Total) {
////                            hao_recycleview.loadMoreEnd();
////                            return;
////                        }
////                        CuringAC curingAC = (CuringAC) getActivity();
////                        curingAC.initData(-2,1,pageNum);
////                        mAdapter.notifyDataSetChanged();
////                        hao_recycleview.loadMoreComplete();
//
//					}
//				}, 1000);
//			}
//		});
//        mAdapter = new  (getActivity(), classifySo);
        mSheHeAdapter = new MineItemDoneListAdapter(getActivity(),rentListDataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);

    }
    public void initMineData(final int status, int pageNum, int pageSize){
        mineItemCuringAC.dialog.show();
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
            jsonObject.put("status",status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.GetCuringListOrder)
                .addHeader("Cookie", "PHPSESSID=" + mineItemCuringAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mineItemCuringAC.initSnackBar("请求失败！");
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mineItemCuringAC.dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("已完成", "" + response);
                        mineItemCuringAC.dialog.dismiss();
                        Gson gson = new Gson();

                        // 养护中
                        MineItemDoneMoel mineItemDoneMoel = gson.fromJson(response, MineItemDoneMoel.class);
                        if (mineItemDoneMoel.getCode() == 1) {
                            rentListDataSouce = mineItemDoneMoel.getOrderList();
                            initSheHeRV();
                        } else if (mineItemDoneMoel.getCode() == 0) {
                            mineItemCuringAC.initSnackBar("请求失败！");
                        } else if (mineItemDoneMoel.getCode() == 911) {
                            mineItemCuringAC.initSnackBar("登录超时，请重新登录");
                        }



                    }
                });

    }

}
