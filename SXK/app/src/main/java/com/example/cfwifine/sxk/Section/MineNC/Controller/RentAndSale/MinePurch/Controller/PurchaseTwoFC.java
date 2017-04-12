package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Controller;


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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Adapter.MinePurchaseTwoFCAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Model.PurchaseTwoModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
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
public class PurchaseTwoFC extends Fragment {


    private View view;
    private MinePurchaseItems mineItemAC;
    private SwipeRefreshLayout swiperefresh;
    private LinearLayout no_order;
    private HaoRecyclerView hao_recycleview;
    private List<PurchaseTwoModel.OrderListBean> DataSouce = null;
    private MinePurchaseTwoFCAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_purchase_two_fc, container, false);
            mineItemAC = (MinePurchaseItems) getActivity();
            initView();
            initMineData(0, 0);
        }
        return view;

    }

    private void initView() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        no_order = (LinearLayout) view.findViewById(R.id.no_order);
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
//                        //注意此处
                        DataSouce.clear();
                        initMineData(0,0);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
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
        mAdapter = new MinePurchaseTwoFCAdapter(getActivity(),DataSouce);
        hao_recycleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MinePurchaseTwoFCAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int type, int maintainid) {
                if (type == 1){
                    // 详情

                }else if (type == 2){
                    // 确认收货
                    initDialog(maintainid);
                }
            }
        });

    }
    private void initDialog(final int maintainid) {
        CustomDialog_OrderWarning customDialog_orderWarning = new CustomDialog_OrderWarning(mineItemAC, new CustomDialog_OrderWarning.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(int id) {
                // rentID
                initConfirmReceiveGoods(maintainid);
            }
        },"确认收货","是否确认收货？",R.style.style_dialog);
        customDialog_orderWarning.show();
    }
    /**
     * 确认收货
     * @param maintainid
     */
    private void initConfirmReceiveGoods(final int maintainid) {
        if (!mineItemAC.isFinishing()){
            mineItemAC.dialog.show();
        }
        // 确认订单3
        JSONObject js = new JSONObject();
        try {
            js.put("orderid", maintainid);
            js.put("status", 4);
//            js.put("backOddNumber",oddNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.PurchaseConfirmReceiveGoods)
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
                        Log.e("确认收货", "" + response);
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
                            mAdapter.notifyDataSetChanged();
                            mineItemAC.initSnackBar("收货成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            mineItemAC.initSnackBar("确认失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            mineItemAC.initSnackBar("登录超时，请重新登录");
                        }


                    }
                });
    }

    public void initMineData(int pageNum, int pageSize) {
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
            jsonObject.put("own", 1);
            jsonObject.put("status", 3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.PurchaseBuyInOrder)
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
                        Log.e("我的买入-待收货", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        PurchaseTwoModel purchaseTwoModel = gson.fromJson(response, PurchaseTwoModel.class);
                        if (purchaseTwoModel.getCode() == 1) {
                            if (purchaseTwoModel.getTotal() == 0) {
                                no_order.setVisibility(View.VISIBLE);
                                swiperefresh.setVisibility(View.GONE);
                            } else {
                                DataSouce = purchaseTwoModel.getOrderList();
                                initSheHeRV();
                            }
                        } else if (purchaseTwoModel.getCode() == 0) {
                            mineItemAC.initSnackBar("请求失败！");
                        } else if (purchaseTwoModel.getCode() == 911) {
                            mineItemAC.initSnackBar("登录超时，请重新登录");
                        }
                    }
                });

    }

}
