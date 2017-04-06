package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller;


import android.content.Intent;
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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Adapter.MineHasReceGoodsListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Model.MineItemHasReceGoodsModel;
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
public class HasReceGoodsFC extends Fragment {

    private View view;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MineHasReceGoodsListAdapter mSheHeAdapter;
    private MineItemRentAC mineItemAC;
    private List<MineItemHasReceGoodsModel.OrderListBean> DataSouce;
    private LinearLayout no_order;

    public HasReceGoodsFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_has_rece_goods_fc, container, false);
            mineItemAC = (MineItemRentAC) getActivity();
            initView();
            initData(3, 0, 0);
        }
        return view;
    }
    private void initView() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        no_order = (LinearLayout) view.findViewById(R.id.no_order);
    }

    private void initData(final int status, int pageNum, int pageSize) {
        if (!mineItemAC.isFinishing()){
            mineItemAC.dialog.show();
        }
        JSONObject order = new JSONObject();
        try {
            order.put("orderid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNum);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
            jsonObject.put("own",1);
            jsonObject.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.MineRent)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mineItemAC.initSnackBar("请求失败！");
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mineItemAC.dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("我的发布-进行中", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        if (status == 3) {
                            // 进行中
                            MineItemHasReceGoodsModel mineItemHasReceGoodsModel = gson.fromJson(response, MineItemHasReceGoodsModel.class);
                            if (mineItemHasReceGoodsModel.getCode() == 1) {
                                if (mineItemHasReceGoodsModel.getTotal() == 0){
                                    no_order.setVisibility(View.VISIBLE);
                                    swiperefresh.setVisibility(View.GONE);
                                }else {
                                    DataSouce = mineItemHasReceGoodsModel.getOrderList();
                                    initSheHeRV();
                                }
                            } else if (mineItemHasReceGoodsModel.getCode() == 0) {
                                mineItemAC.initSnackBar("请求失败！");
                            } else if (mineItemHasReceGoodsModel.getCode() == 911) {
                                mineItemAC.initSnackBar("登录超时，请重新登录");
                            }

                        }

                    }
                });

    }

    private void initSheHeRV() {
//        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.login_turquoise, R.color.login_turquoise, R.color.login_turquoise,
                R.color.login_turquoise);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        DataSouce.clear();
                        initData(3,0,0);
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
//        progressView.setIndicatorColor(0xff16a6ae);
//        hao_recycleview.setFootLoadingView(progressView);

//        TextView textView = new TextView(getActivity());
//        textView.setText("已经到底啦~");
//        textView.setTextColor(getResources().getColor(R.color.black));
//        hao_recycleview.setFootEndView(textView);

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
//                        hao_recycleview.loadMoreEnd();
//                        hao_recycleview.loadMoreComplete();
//
//                    }
//                }, 1000);
//            }
//        });
//        mAdapter = new  (getActivity(), classifySo);
        mSheHeAdapter = new MineHasReceGoodsListAdapter(getActivity(), DataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);
        mSheHeAdapter.setOnItemClickListener(new MineHasReceGoodsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid, String oddNumber, int i) {
                if (i == 1){
                    // 退回
                    Intent intent = new Intent(getActivity(),ReBackGoodsAC.class);
                    intent.putExtra("ORDERID",maintainid);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onResume() {
        initData(3,0,0);
        super.onResume();
    }
}
