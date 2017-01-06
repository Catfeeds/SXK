package com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller;

import android.content.Context;
import android.net.Uri;
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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Adapter.MineRentingListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Model.MineItemRentingModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Adapter.MineWaitReceGoodsListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model.MineItemWaitReceGoodsModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import okhttp3.Call;


public class WaitReceGoodsFC extends Fragment {

    private View view;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MineWaitReceGoodsListAdapter mSheHeAdapter;
    private MineItemRentAC mineItemAC;
    private List<MineItemWaitReceGoodsModel.BrandListBean> DataSouce;

    public WaitReceGoodsFC() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_wait_rece_goods_fc, container, false);
            mineItemAC = (MineItemRentAC) getActivity();
            initData(2, 0, 0);
        }
        return view;
    }

    private void initData(final int status, int pageNum, int pageSize) {
        mineItemAC.dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("orderid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNum);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
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
                        Log.e("我的发布-租赁中", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        if (status == 2) {
                            // 审核中
                            MineItemWaitReceGoodsModel mineItemWaitReceGoodsModel = gson.fromJson(response, MineItemWaitReceGoodsModel.class);
                            if (mineItemWaitReceGoodsModel.getCode() == 1) {
                                DataSouce = mineItemWaitReceGoodsModel.getBrandList();
                                initSheHeRV();
                            } else if (mineItemWaitReceGoodsModel.getCode() == 0) {
                                mineItemAC.initSnackBar("请求失败！");
                            } else if (mineItemWaitReceGoodsModel.getCode() == 911) {
                                mineItemAC.initSnackBar("登录超时，请重新登录");
                            }

                        }

                    }
                });

    }


    private void initSheHeRV() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.login_turquoise, R.color.login_turquoise, R.color.login_turquoise,
                R.color.login_turquoise);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        DataSouce.clear();
                        initData(2,0,0);
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
        mSheHeAdapter = new MineWaitReceGoodsListAdapter(getActivity(), DataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);
        mSheHeAdapter.setOnItemClickListener(new MineWaitReceGoodsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid,String oddNumber) {
                LogUtil.e("订单id");
                initConfirmReceiveGoods(maintainid,oddNumber);
            }
        });
    }

    /**
     * 确认收货
     * @param maintainid
     * @param oddNumber
     */
    private void initConfirmReceiveGoods(int maintainid, String oddNumber) {
        mineItemAC.dialog.show();
        // 确认订单3
        JSONObject js = new JSONObject();
        try {
            js.put("orderid", maintainid);
            js.put("status", 3);
//            js.put("backOddNumber",oddNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.ChangeRentOrder)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
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
                        Log.e("我的租赁-待收货", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        // 审核中
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            initData(2,0,0);
                            mineItemAC.initSnackBar("收货成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            mineItemAC.initSnackBar("确认失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            mineItemAC.initSnackBar("登录超时，请重新登录");
                        }


                    }
                });


    }

}
