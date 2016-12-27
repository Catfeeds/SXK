package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.ComRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Controller.EditTextPupWindow;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CuringListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.MineShenHeListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.MinePublishShenHeModel;
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

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
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
    List<MinePublishShenHeModel.RentListBean> rentListBeen;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private CuringListAdapter mAdapter;
    private int limit = 10;
    private int Total = 0;
    private int pageNum = 10;
    private MineShenHeListAdapter mSheHeAdapter;
    private List<MinePublishShenHeModel.RentListBean> datax;
    private int id;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_layout_a;
    }

    @Override
    public void initView() {


        LogUtil.e("我是求"+id);



        if (classifySource !=null){
            classifySo = new ArrayList<>();
            for (int i = 0; i < classifySource.size(); i++){
                if (classifySource.get(i).getClassifyid() == classid){
                    classifySo.add(i,classifySource.get(i));
                }
            }
            swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
            swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                    R.color.textBlueDark);

            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //注意此处
                            CuringAC curingAC = (CuringAC) getActivity();
                            curingAC.initData(-2,1,10);
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
            //设置自定义加载中和到底了效果
            ProgressView progressView = new ProgressView(getActivity());
            progressView.setIndicatorId(ProgressView.BallPulse);
            progressView.setIndicatorColor(0xff69b3e0);
            hao_recycleview.setFootLoadingView(progressView);

            TextView textView = new TextView(getActivity());
            textView.setText("已经到底啦~");
            textView.setTextColor(getResources().getColor(R.color.black));
            hao_recycleview.setFootEndView(textView);

            hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
                @Override
                public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            pageNum += 10;
                            L.e("pageNum" + pageNum);
                            if (pageNum >= Total) {
                                hao_recycleview.loadMoreEnd();
                                return;
                            }
                            CuringAC curingAC = (CuringAC) getActivity();
                            curingAC.initData(-2,1,pageNum);
                            mAdapter.notifyDataSetChanged();
                            hao_recycleview.loadMoreComplete();

                        }
                    }, 1000);
                }
            });
            mAdapter = new CuringListAdapter(getActivity(), classifySo);
            hao_recycleview.setAdapter(mAdapter);
           mAdapter.setOnItemClickListener(new CuringListAdapter.OnItemClickListener() {
               @Override
               public void OnItemClick(View view, int maintainid) {
                   LogUtil.e("maintainid"+maintainid);
                    Intent intent = new Intent(getActivity(),CuringDetailAC.class);
                    intent.putExtra("maintainid",maintainid);
                    startActivity(intent);
               }
           });





//            recyclerView = (RecyclerView)view.findViewById(R.id.curing_rv);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(layoutManager);
//            CuringListAdapter curingListAdapter = new CuringListAdapter(getActivity(),classifySo);
//            recyclerView.setAdapter(curingListAdapter);
//            curingListAdapter.setOnItemClickListener(new CuringListAdapter.OnItemClickListener() {
//                @Override
//                public void OnItemClick(View view, int maintainid) {
//                    LogUtil.e("maintainid"+maintainid);
//                    Intent intent = new Intent(getActivity(),CuringDetailAC.class);
//                    intent.putExtra("maintainid",maintainid);
//                    startActivity(intent);
//                }
//            });
//            LogUtil.e("选中类TAB"+title);



        }
    }

    private void initSheHeRV() {
        LogUtil.e("返回的在"+datax.size());
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
//                        CuringAC curingAC = (CuringAC) getActivity();
//                        curingAC.initData(-2,1,10);
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
        ProgressView progressView = new ProgressView(getActivity());
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff69b3e0);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(getActivity());
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

//                        pageNum += 10;
//                        L.e("pageNum" + pageNum);
//                        if (pageNum >= Total) {
//                            hao_recycleview.loadMoreEnd();
//                            return;
//                        }
//                        CuringAC curingAC = (CuringAC) getActivity();
//                        curingAC.initData(-2,1,pageNum);
//                        mAdapter.notifyDataSetChanged();
//                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });
//        mAdapter = new  (getActivity(), classifySo);
        mSheHeAdapter = new MineShenHeListAdapter(getActivity(),rentListBeen);
        hao_recycleview.setAdapter(mSheHeAdapter);
//        mSheHeAdapter.setOnItemClickListener(new CuringListAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int maintainid) {
//                LogUtil.e("maintainid"+maintainid);
//                Intent intent = new Intent(getActivity(),CuringDetailAC.class);
//                intent.putExtra("maintainid",maintainid);
//                startActivity(intent);
//            }
//        });
    }

    // 养护
    public static FragmentACuring getInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }

    public static FragmentACuring getTestInstance(int id) {
        FragmentACuring mf = new FragmentACuring();
        mf.id = id;

        return mf;
    }

    // 审核中
    public  FragmentACuring getShenHeInstance(List<MinePublishShenHeModel.RentListBean> rentListBeen, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.rentListBeen = rentListBeen;
        if (mf.rentListBeen != null){
            LogUtil.e("审核中的数据"+mf.rentListBeen.size());
            datax=mf.rentListBeen;
//            initSheHeRV();
        }
        return mf;
    }
    // 发布中
    public static FragmentACuring getPublishInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }
    // 租赁中
    public static FragmentACuring getRentingInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }
    // 已下架
    public static FragmentACuring getNoSaleInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }
    // 未通过
    public static FragmentACuring getRejectInstance(String title, List<CuringListModel.MaintainListBean> mainListModel, int classifyid) {
        FragmentACuring mf = new FragmentACuring();
        mf.title = title;
        mf.classifySource = mainListModel;
        mf.classid = classifyid;
        return mf;
    }
}
