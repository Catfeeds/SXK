package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller;

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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Dialog.CustomDialog_OrderWarning;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Adapter.MineWaitReceGoodsListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Model.MineItemWaitReceGoodsModel;
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
    private List<MineItemWaitReceGoodsModel.OrderListBean> DataSouce;
    private LinearLayout no_order;

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
            initView();
            initData(2, 0, 0);
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
                        Log.e("播客待收", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        if (status == 2) {
                            // 审核中
                            MineItemWaitReceGoodsModel mineItemWaitReceGoodsModel = gson.fromJson(response, MineItemWaitReceGoodsModel.class);
                            if (mineItemWaitReceGoodsModel.getCode() == 1) {
                                if (mineItemWaitReceGoodsModel.getTotal() == 0){
                                    no_order.setVisibility(View.VISIBLE);
                                    swiperefresh.setVisibility(View.GONE);
                                }else {
                                    DataSouce = mineItemWaitReceGoodsModel.getOrderList();
                                    initSheHeRV();
                                }
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
                LogUtil.e("订单id"+maintainid);
//                LogUtil.e("订单id"+DataSouce.get(0).getOrderid());
//                for (int i =0 ; i < DataSouce.size(); i++){
//                    if (DataSouce.get(i).getOrderid() == maintainid){
//                        LogUtil.e("订单id"+DataSouce.get(i).getOrderid());
//                    }
//                }

                initDialog(maintainid, oddNumber);
            }
        });
    }
    private void initDialog(final int maintainid, final String oddNumber) {
        CustomDialog_OrderWarning customDialog_orderWarning = new CustomDialog_OrderWarning(mineItemAC, new CustomDialog_OrderWarning.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(int id) {
                // rentID
                initConfirmReceiveGoods(maintainid,oddNumber);
            }
        },"确认收货","是否确认收货？",R.style.style_dialog);
        customDialog_orderWarning.show();
    }
    /**
     * 确认收货
     * @param maintainid
     * @param oddNumber
     */
    private void initConfirmReceiveGoods(final int maintainid, String oddNumber) {
        if (!mineItemAC.isFinishing()){
            mineItemAC.dialog.show();
        }
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
                            for (int i =0 ; i < DataSouce.size(); i++){
                                if (DataSouce.get(i).getOrderid() == maintainid){
                                    DataSouce.remove(i);
                                }
                            }
                            mSheHeAdapter.notifyDataSetChanged();
                            mineItemAC.initSnackBar("收货成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            mineItemAC.initSnackBar("确认失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            mineItemAC.initSnackBar("登录超时，请重新登录");
                        } else if (requestStatueModel.getCode() == 2003) {
                            mineItemAC.initSnackBar(requestStatueModel.getMsg());
                        }


                    }
                });
    }

}
