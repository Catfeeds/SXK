package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CuringListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CuringComListFC extends Fragment {


    private List<CuringListModel.MaintainListBean> classifySource;
    private View view;
    private ArrayList<CuringListModel.MaintainListBean> classifySo;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private CuringListAdapter mAdapter;
    private List<CuringListModel.ClassifyListBean> classid;
    private CuringAC curingAC;
    private int pos;
    private int POSITION = 0;

    public CuringComListFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_curing_com_list_fc, container, false);
        curingAC = (CuringAC) getActivity();
        curingAC.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.e("当前的tab"+position);
                POSITION = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        initView();


        return view;



    }

    public void initView() {

//        LogUtil.e("分类的id是"+classid.get(POSITION).getClassifyid());
        if (classifySource !=null){
//            classifySo = new ArrayList<>();
//            for (int i = 0; i < classifySource.size(); i++){
//                if (classifySource.get(i).getClassifyid() == classid.get(POSITION).getClassifyid()){
//                    classifySo.add(i,classifySource.get(i));
//                }
//            }
//
//            LogUtil.e("分类后的数据"+classifySo.get(0).getName());

            swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
            swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                    R.color.textBlueDark);

            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //注意此处
//                            CuringAC curingAC = (CuringAC) getActivity();
//                            curingAC.mainListDataSource.clear();
//                            curingAC.classifyDataSource.clear();
//                            curingAC.initData(1,10);
//                            hao_recycleview.refreshComplete();
//                            swiperefresh.setRefreshing(false);
//                            mAdapter.notifyDataSetChanged();
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
            progressView.setIndicatorColor(0xff16a6ae);
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

//                            pageNum += 10;
//                            L.e("pageNum" + pageNum);
//                            if (pageNum >= Total) {
//                                hao_recycleview.loadMoreEnd();
//                                return;
//                            }
//                            CuringAC curingAC = (CuringAC) getActivity();
//                            curingAC.initData(1,pageNum);
//                            mAdapter.notifyDataSetChanged();
//                            hao_recycleview.loadMoreComplete();

                        }
                    }, 1000);
                }
            });
            mAdapter = new CuringListAdapter(getActivity(), classifySource);
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
        }
    }


    // 养护
    public static CuringComListFC getInstance(List<CuringListModel.MaintainListBean> mainListModel) {
        CuringComListFC mf = new CuringComListFC();
//        mf.title = title;
        mf.classifySource = mainListModel;
//        mf.classid = classifyid;
        return mf;
    }

}
