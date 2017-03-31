package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Controller;


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
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Adapter.MineRentOneFCAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Adapter.MineRentTwoFCAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentOneModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentTwoModel;
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
public class TwoFC extends Fragment {


    private View view;
    private MineSaleItem mineItemAC;
    private SwipeRefreshLayout swiperefresh;
    private LinearLayout no_order;
    private HaoRecyclerView hao_recycleview;
    private List<RentTwoModel.PurchaseListBean> DataSouce = null;
    private MineRentTwoFCAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_two_fc, container, false);
            mineItemAC = (MineSaleItem) getActivity();
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
        mAdapter = new MineRentTwoFCAdapter(getActivity(),DataSouce);
        hao_recycleview.setAdapter(mAdapter);

    }

    public void initMineData(int pageNum, int pageSize) {
        mineItemAC.dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("purchaseid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNum);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
            jsonObject.put("own", 1);
            jsonObject.put("status", 4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.PurchasePreList)
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
                        Log.e("我的寄卖-寄送中", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        RentTwoModel rentTwoModel = gson.fromJson(response, RentTwoModel.class);
                        if (rentTwoModel.getCode() == 1) {
                            if (rentTwoModel.getTotal() == 0) {
                                no_order.setVisibility(View.VISIBLE);
                                swiperefresh.setVisibility(View.GONE);
                            } else {
                                DataSouce = rentTwoModel.getPurchaseList();
                                initSheHeRV();
                            }
                        } else if (rentTwoModel.getCode() == 0) {
                            mineItemAC.initSnackBar("请求失败！");
                        } else if (rentTwoModel.getCode() == 911) {
                            mineItemAC.initSnackBar("登录超时，请重新登录");
                        }


                    }
                });

    }

}
