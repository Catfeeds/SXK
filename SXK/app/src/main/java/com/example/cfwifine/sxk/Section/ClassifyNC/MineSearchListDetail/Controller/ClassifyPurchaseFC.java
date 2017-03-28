package com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.PurchaseDetailListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RentListAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Controller.HomeThreeBlockDetailAC;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.RentListModel;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyPurchaseFC extends Fragment {


    private View view;
    private ClassifySearchDetailAC mineItemBuyPlusAC;
    private RecyclerView recycle;
    private LinearLayout no_result;
    private List<RentListModel.RentListBean> dataSource = null;


    public ClassifyPurchaseFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mines_wait_receive_goods_fc, container, false);
            mineItemBuyPlusAC = (ClassifySearchDetailAC)getActivity();
            String response = mineItemBuyPlusAC.getIntent().getStringExtra("RENTLIST");
            initView();
            Gson gson = new Gson();
            RentListModel rentListModel = gson.fromJson(response,RentListModel.class);
            dataSource = rentListModel.getRentList();
            if (rentListModel.getTotal() == 0) {
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
    private void initRV(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(linearLayoutManager);
        RentListAdapter rentListAdapter = new RentListAdapter(getActivity(),dataSource);
        recycle.setAdapter(rentListAdapter);
        rentListAdapter.setOnItemClickListener(new RentListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                Intent intent = new Intent(getActivity(), ProductDetailsAC.class);
                intent.putExtra("RENTID", maintainid);
                startActivity(intent);
            }
        });
    }

}
