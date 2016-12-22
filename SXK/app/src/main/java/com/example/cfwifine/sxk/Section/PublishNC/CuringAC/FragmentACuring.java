package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CuringListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class FragmentACuring extends CuringBaseFragment {
    private String title;
    private List<CuringListModel.MaintainListBean> classifySource;
    private int classid;
    private TextView txt;
    private RecyclerView recyclerView;
    Dialog dialog;
    private List<CuringListModel.MaintainListBean> classifySo;
    List<CuringListModel.MaintainListBean> classifyidModel;
    @Override
    public int getLayoutID() {
        return R.layout.fragment_layout_a;
    }

    @Override
    public void initView() {
//        LogUtil.e("传递的分类ID"+classifyid);
//        txt = (TextView) view.findViewById(R.id.txt);
//        txt.setText("FragmentACuring:" + classifySource);
        classifySo = new ArrayList<>();
        for (int i = 0; i < classifySource.size(); i++){
            if (classifySource.get(i).getClassifyid() == classid){
                classifySo.add(i,classifySource.get(i));
            }
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.curing_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CuringListAdapter curingListAdapter = new CuringListAdapter(getActivity(),classifySo);
        recyclerView.setAdapter(curingListAdapter);
        curingListAdapter.setOnItemClickListener(new CuringListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                LogUtil.e("maintainid"+maintainid);
                Intent intent = new Intent(getActivity(),CuringDetailAC.class);
                intent.putExtra("maintainid",-1);
                startActivity(intent);
            }
        });
        LogUtil.e("选中类TAB"+title);

    }


    public static FragmentACuring getInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }


}
