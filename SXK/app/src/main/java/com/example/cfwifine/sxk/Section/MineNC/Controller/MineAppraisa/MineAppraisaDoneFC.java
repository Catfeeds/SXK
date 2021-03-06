package com.example.cfwifine.sxk.Section.MineNC.Controller.MineAppraisa;


import android.graphics.Color;
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
import android.widget.LinearLayout;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Dialog.CustomDialog_OrderWarning;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
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
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineAppraisaDoneFC extends Fragment {


    private View view;
    private MineAppraisaAC mineItemAC;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MineItemAppraisaListAdapter mSheHeAdapter;
    private List<MineItemAppraisaModel.OrderListBean> rentListDataSouce;
    private LinearLayout no_order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mine_appraisa_done_fc, container, false);
            mineItemAC = (MineAppraisaAC) getActivity();
            initView();
            initMineData(3,0,0);

        }
        return view;
    }
    private void initView() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        no_order = (LinearLayout) view.findViewById(R.id.no_order);
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
                        initMineData(3, 0, 0);
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
        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(getActivity());
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff69b3e0);
//        hao_recycleview.setFootLoadingView(progressView);

//        TextView textView = new TextView(getActivity());
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
//                    }
//                }, 1000);
//            }
//        });
        mSheHeAdapter = new MineItemAppraisaListAdapter(getActivity(), rentListDataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);
        mSheHeAdapter.setOnItemClickListener(new MineItemAppraisaListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                initDialog(maintainid);
            }
        });


    }

    public void initMineData(final int status, int pageNum, int pageSize){
        if (!mineItemAC.isFinishing()){
            mineItemAC.dialog.show();
        }
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
        OkHttpUtils.postString().url(BaseInterface.AppraisaOrderList)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求失败！");
                        mineItemAC.dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("已完成", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        MineItemAppraisaModel mineItemCuringModel = gson.fromJson(response, MineItemAppraisaModel.class);
                        if (mineItemCuringModel.getCode() == 1) {
                            rentListDataSouce = new ArrayList<MineItemAppraisaModel.OrderListBean>();
                            for (int i = 0; i<mineItemCuringModel.getOrderList().size();i++){
                                if (mineItemCuringModel.getOrderList().get(i).getStatus() == 3){
                                    rentListDataSouce.add(mineItemCuringModel.getOrderList().get(i));
                                }
                            }
                            if (rentListDataSouce.size() == 0){
                                no_order.setVisibility(View.VISIBLE);
                                swiperefresh.setVisibility(View.GONE);
                            }else {
                                initSheHeRV();
                            }
                        } else if (mineItemCuringModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (mineItemCuringModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录");
                        }
                    }
                });

    }
    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }
    private void initDialog(final int maintainid) {
        CustomDialog_OrderWarning customDialog_orderWarning = new CustomDialog_OrderWarning(mineItemAC, new CustomDialog_OrderWarning.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(int id) {
                // rentID
                initDelOrder(maintainid);
            }
        },"删除提示","是否删除该订单？",R.style.style_dialog);
        customDialog_orderWarning.show();
    }
    /**
     * @param maintainid
     */
    private void initDelOrder(final int maintainid) {
        if (!mineItemAC.isFinishing()){
            mineItemAC.dialog.show();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", maintainid);
//            jsonObject.put("status",3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.AppraiasDelete)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 mineItemAC.initSnackBar("请求失败！");
                                 mineItemAC.dialog.dismiss();
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 Log.e("我的待审核", "" + response);
                                 mineItemAC.dialog.dismiss();
                                 Gson gson = new Gson();
                                 RequestStatueModel requestStatueModel = gson.fromJson(response,RequestStatueModel.class);
                                 if (requestStatueModel.getCode() == 1) {
                                     for (int i =0 ; i < rentListDataSouce.size(); i++){
                                         if (rentListDataSouce.get(i).getOrderid() == maintainid){
                                             rentListDataSouce.remove(i);
                                         }
                                     }
                                     mSheHeAdapter.notifyDataSetChanged();
                                     mineItemAC.initSnackBar("删除成功！");
                                 } else if (requestStatueModel.getCode() == 0) {
                                     mineItemAC.initSnackBar("删除失败！");
                                 } else if (requestStatueModel.getCode() == 911) {
                                     mineItemAC.initSnackBar("登录超时，请重新登录");
                                 }

                             }
                         }

                );

    }

}
