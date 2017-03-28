package com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Adapter.SearchPurchaseListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.PurchaseDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Controller.HomePurchaiseListAC;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.google.gson.Gson;

import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyRentFC extends Fragment {


    private View view = null;
    private ClassifySearchDetailAC mineItemBuyPlusAC;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private SearchPurchaseListAdapter mSheHeAdapter;
    private RecyclerView recycle;
    private LinearLayout no_result;
    private List<PurchaseListModel.PurchaseListBean> dataSource = null;

    public ClassifyRentFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_wait_receive_goods_fc, container, false);
            mineItemBuyPlusAC = (ClassifySearchDetailAC) getActivity();
            String response = mineItemBuyPlusAC.getIntent().getStringExtra("PURCHASE");
            LogUtil.e("购买的值"+response);
            initView();
            Gson gson = new Gson();
            PurchaseListModel purchaseListModel = gson.fromJson(response,PurchaseListModel.class);
            dataSource = purchaseListModel.getPurchaseList();
            if (purchaseListModel.getTotal() == 0) {
                no_result.setVisibility(View.VISIBLE);
                recycle.setVisibility(View.GONE);
            }else {
                initRV();
            }
        }
        return view;
    }

    private void initView() {
        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        no_result = (LinearLayout) view.findViewById(R.id.no_result);
    }
    public void initRV(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(linearLayoutManager);
        PurchaseDetailListAdapter purchaseDetailListAdapter = new PurchaseDetailListAdapter(getActivity(),dataSource);
        recycle.setAdapter(purchaseDetailListAdapter);
        purchaseDetailListAdapter.setOnItemClickListener(new PurchaseDetailListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                Intent intent = new Intent(getActivity(), ProductDetailsAC.class);
                intent.putExtra("PURCHASEID", maintainid);
                startActivity(intent);
            }
        });

    }
}
