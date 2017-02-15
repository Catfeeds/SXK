package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter.TwoItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineIMBoKeFC extends Fragment implements View.OnClickListener {


    private View view;
    int[] pic = new int[]{R.drawable.bozhu1, R.drawable.bozhu2, R.drawable.bozhu3,
            R.drawable.bizhu4, R.drawable.bozhu5, R.drawable.bozhu6};
    private ArrayList<Integer> arrList;
    String[] text = new String[]{
            "租用流程", "出租收益", "平台保障", "破损处理", "平台服务费", "长租"
    };
    Button contacts_server;
    private ArrayList<String> infoARR;
    private ArrayList<String> textARR;
    private RecyclerView tops_rv;
    private UserInfoModel userInfoModel=null;

    public MineIMBoKeFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mine_imbo_ke_fc, container, false);
            contacts_server = (Button)view.findViewById(R.id.contacts_server);
            contacts_server.setOnClickListener(this);
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
    private void initMeiQia() {
        String userinfo = getActivity().getIntent().getStringExtra("USERINFO");
        Gson gson = new Gson();
        userInfoModel = gson.fromJson(userinfo, UserInfoModel.class);
        int  sexid = userInfoModel.getUser().getSex();
        String Sex = "";
        if (sexid == 1){
            Sex = "男";
        }else {
            Sex = "女";
        }
        int userid = userInfoModel.getUser().getUserid();
        HashMap<String, String> clientInfo = new HashMap<>();
        clientInfo.put("name", userInfoModel.getUser().getNickname());
        clientInfo.put("avatar", userInfoModel.getUser().getHeadimgurl());
        clientInfo.put("gender",Sex);
        clientInfo.put("tel", userInfoModel.getUser().getMobile());
        clientInfo.put("技能1", "啵呗用户");
        Intent intent = new MQIntentBuilder(getActivity()).setClientInfo(clientInfo).setCustomizedId(String.valueOf(userid)).build();
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.contacts_server:
                initMeiQia();
                break;
        }
    }
}
