package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter.TwoItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineIMBoKeFC extends Fragment {


    private View view;
    int[] pic = new int[]{R.drawable.bozhu1, R.drawable.bozhu2, R.drawable.bozhu3,
            R.drawable.bizhu4, R.drawable.bozhu5, R.drawable.bozhu6};
    private ArrayList<Integer> arrList;
    String[] text = new String[]{
            "租用流程", "出租收益", "平台保障", "破损处理", "平台服务费", "长租"
    };
    private ArrayList<String> infoARR;
    private ArrayList<String> textARR;
    private RecyclerView tops_rv;

    public MineIMBoKeFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mine_imbo_ke_fc, container, false);
            initTopRV();
        }

        return view;
    }
    private void initTopRV() {
        arrList = new ArrayList<>();
        textARR = new ArrayList<>();
        infoARR = new ArrayList<>();

        for (int i = 0; i < pic.length; i++) {
            arrList.add(i, pic[i]);
            infoARR.add(i, text[i] + "明细");
            textARR.add(i, text[i]);
        }

        tops_rv = (RecyclerView) view.findViewById(R.id.tops_rv);
        TwoItemRecycleAdapter twoItemRecycleAdapter = new TwoItemRecycleAdapter(arrList, textARR, infoARR);
        tops_rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tops_rv.setAdapter(twoItemRecycleAdapter);
        twoItemRecycleAdapter.setOnItemClickListener(new TwoItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), UserPrctocalAC.class);
                intent.putExtra("SETJUMPPOSITION", 444);
                intent.putExtra("POSI", position);
                startActivity(intent);
            }
        });
    }


}
