package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection.MineCollectionModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection.MineCuringCollectionListModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection.MinePurchaseListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CuringListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class CuringComListFC extends Fragment {


    private List<CuringListModel.ClassifyListBean> classifySource;
    private View view;
    private ArrayList<CuringListModel.MaintainListBean> classifySo;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private CuringListAdapter mAdapter = null;
    private List<CuringListModel.MaintainListBean> maintainListBeen;
    private CuringAC curingAC;
    private int pos;
    private int POSITION = 0;
    private CuringListModel curingListModel;
    private int classid;
    private int pageNum = 1;
    private int pageSize = 10;
    private int Total;

    public CuringComListFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_curing_com_list_fc, container, false);
        curingAC = (CuringAC) getActivity();
        initData(classid, pageNum, pageSize);
        return view;
    }

    /**
     * 舒适化养护界面的数据
     *
     * @param pageNum
     * @param pageSize
     */
    public void initData(int classifyid, final int pageNum, int pageSize) {
        if (pageNum == 1){
            maintainListBeen = null;
        }
        if (!curingAC.isFinishing()){
            curingAC.dialog.show();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("sort", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pageNo", pageNum);
            jsonObject.put("order", js);
            jsonObject.put("classifyid", classifyid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.CuringList)
                .addHeader("Cookie", "PHPSESSID=" + curingAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(curingAC.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        curingAC.dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("养护分类列表" + response);
                        curingAC.dialog.dismiss();
                        Gson gson = new Gson();
                        curingListModel = gson.fromJson(response, CuringListModel.class);
                        if (curingListModel.getCode() == 1) {
                            Total = curingListModel.getTotal();
                            if (pageNum != 1){
                                maintainListBeen.addAll(curingListModel.getMaintainList());
                                mAdapter.notifyDataSetChanged();
                            }else {
                                maintainListBeen = curingListModel.getMaintainList();
                                initView();
                            }

                        } else if (curingListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(curingAC.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (curingListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(curingAC.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }


    public void initView() {

        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        maintainListBeen.clear();
                        initData(classid, 1, 10);
                        pageNum = 1;
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) view.findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(curingAC) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
        //设置自定义加载中和到底了效果

        hao_recycleview.setCanloadMore(true);
        if (!curingAC.isFinishing()){
            ProgressView progressView = new ProgressView(curingAC);
            progressView.setIndicatorId(ProgressView.BallPulse);
            progressView.setIndicatorColor(0xff16a6ae);
            hao_recycleview.setFootLoadingView(progressView);
            TextView textView = new TextView(curingAC);
            textView.setText("已经到底啦~");
            textView.setTextColor(getResources().getColor(R.color.black));
            hao_recycleview.setFootEndView(textView);
        }


        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {


                        if (maintainListBeen.size()>=Total) {
                            hao_recycleview.loadMoreEnd();
                            pageNum = 1;
                            return;
                        }
                        pageNum+=1;
                        initData(classid, pageNum, pageSize);
                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
        mAdapter = new CuringListAdapter(curingAC, maintainListBeen);
        hao_recycleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CuringListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                LogUtil.e("maintainid" + maintainid);
                Intent intent = new Intent(curingAC, CuringDetailAC.class);
                intent.putExtra("maintainid", maintainid);
                startActivity(intent);
            }
        });
    }


    // 养护
    public static CuringComListFC getInstance(List<CuringListModel.MaintainListBean> mainListModel, List<CuringListModel.ClassifyListBean> classifyDataSource, int classifyid) {
        CuringComListFC mf = new CuringComListFC();
//        mf.title = title;
        mf.classifySource = classifyDataSource;
        mf.maintainListBeen = mainListModel;
        mf.classid = classifyid;
        return mf;
    }

}
