package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter.ThreeItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter.TwoItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Model.AnswerModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineIMBoZhuFC extends Fragment implements View.OnClickListener {


    private RecyclerView top_rv;
    private RecyclerView question_rv;
    private Button contact_server;
    private View view;
    ArrayList<String> questionDataSource = new ArrayList<>();
    private List<AnswerModel.QuestionListBean> answerDataSource;
    Dialog dialog;
    int[] pic = new int[]{R.drawable.bozhu1, R.drawable.bozhu2, R.drawable.bozhu3,
            R.drawable.bizhu4, R.drawable.bozhu5, R.drawable.bozhu6};
    private ArrayList<Integer> arrList;
    String[] text = new String[]{
            "租用流程", "租赁费用", "注册审核", "破损处理", "APP指导", "平台保障"
    };
    private ArrayList<String> infoARR;
    private ArrayList<String> textARR;
    private String RentWater = "";
    private UserInfoModel userInfoModel=null;

    public MineIMBoZhuFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine_imbo_zhu_fc, container, false);
            dialog = LoadingUtils.createLoadingDialog(getActivity(), "加载中...");
            contact_server = (Button)view.findViewById(R.id.contact_server);
            contact_server.setOnClickListener(this);
            initTopRV();
            initAnwserData();
        }

        return view;
    }


    private void initAnwserData() {
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.AnswerList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("问答列表", "" + response);
                        Gson gson = new Gson();
                        AnswerModel answerModel = gson.fromJson(response, AnswerModel.class);
                        if (answerModel.getCode() == 1) {
                            answerDataSource = answerModel.getQuestionList();
                            initBottomRV();
                        } else if (answerModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (answerModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    // 底部
    private void initBottomRV() {
        question_rv = (RecyclerView) view.findViewById(R.id.question_rv);
        ThreeItemRecycleAdapter threeItemRecycleAdapter = new ThreeItemRecycleAdapter(answerDataSource);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        question_rv.setLayoutManager(layoutManager);
        question_rv.setAdapter(threeItemRecycleAdapter);
        threeItemRecycleAdapter.setOnItemClickListener(new ThreeItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String content) {
                Intent intent = new Intent(getActivity(), UserPrctocalAC.class);
                intent.putExtra("SETJUMPPOSITION", 333);
                intent.putExtra("CONTENT", content);
                startActivity(intent);
            }
        });
    }

    // 头部
    private void initTopRV() {
        arrList = new ArrayList<>();
        textARR = new ArrayList<>();
        infoARR = new ArrayList<>();

        for (int i = 0; i < pic.length; i++) {
            arrList.add(i, pic[i]);
            infoARR.add(i, text[i] + "明细");
            textARR.add(i, text[i]);
        }

        top_rv = (RecyclerView) view.findViewById(R.id.top_rv);
        TwoItemRecycleAdapter twoItemRecycleAdapter = new TwoItemRecycleAdapter(arrList, textARR, infoARR);
        top_rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        top_rv.setAdapter(twoItemRecycleAdapter);
        twoItemRecycleAdapter.setOnItemClickListener(new TwoItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (position != 2){
                    Intent intent = new Intent(getActivity(), UserPrctocalAC.class);
                    intent.putExtra("SETJUMPPOSITION", 666);
                    intent.putExtra("POSI", position);
                    startActivity(intent);
                }else {
                    Intent intents = new Intent(getActivity(),RegisterSecurityAC.class);
                    startActivity(intents);
                }
            }
        });
    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
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
            case R.id.contact_server:
                initMeiQia();
                break;
        }
    }
}
