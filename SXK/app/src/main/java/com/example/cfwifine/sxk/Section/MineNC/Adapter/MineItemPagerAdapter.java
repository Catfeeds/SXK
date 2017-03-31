package com.example.cfwifine.sxk.Section.MineNC.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model.MinePublishShenHeModel;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;

public class MineItemPagerAdapter extends PagerAdapter {

    private final Context content;
    private List<MinePublishShenHeModel.RentListBean> rentListDataSouce = new ArrayList<>();
    private List<String> mItems = new ArrayList<>();
    private RecyclerView rv;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;

    public MineItemPagerAdapter(MineItemAC mineItemAC, List<MinePublishShenHeModel.RentListBean> rentListDataSouces) {
        content = mineItemAC;
        rentListDataSouce = rentListDataSouces;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_page, container, false);

//        LogUtil.e("审核数据的大小"+rentListDataSouce.size());
//
//
//        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
//        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
//                R.color.textBlueDark);
//
//        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        //注意此处
////                        CuringAC curingAC = (CuringAC) getActivity();
////                        curingAC.initData(-2,1,10);
//                        hao_recycleview.refreshComplete();
//                        swiperefresh.setRefreshing(false);
//                        mSheHeAdapter.notifyDataSetChanged();
//                    }
//                }, 1000);
//
//            }
//        });
//        hao_recycleview = (HaoRecyclerView) view.findViewById(R.id.hao_recycleview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(content) {
//            @Override
//            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
//                super.onMeasure(recycler, state, widthSpec, expandSpec);
//            }
//        };
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        hao_recycleview.setLayoutManager(layoutManager);
//        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(content);
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff69b3e0);
//        hao_recycleview.setFootLoadingView(progressView);
//
//        TextView textView = new TextView(content);
//        textView.setText("已经到底啦~");
//        textView.setTextColor(content.getResources().getColor(R.color.black));
//        hao_recycleview.setFootEndView(textView);
//
//        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//
////                        pageNum += 10;
////                        L.e("pageNum" + pageNum);
////                        if (pageNum >= Total) {
////                            hao_recycleview.loadMoreEnd();
////                            return;
////                        }
////                        CuringAC curingAC = (CuringAC) getActivity();
////                        curingAC.initData(-2,1,pageNum);
////                        mAdapter.notifyDataSetChanged();
////                        hao_recycleview.loadMoreComplete();
//
//                    }
//                }, 1000);
//            }
//        });
////        mAdapter = new  (getActivity(), classifySo);
//        mSheHeAdapter = new MineShenHeListAdapter(content,rentListDataSouce);
//        hao_recycleview.setAdapter(mSheHeAdapter);


        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        return mItems.get(position);
    }

    public String getColorItem(int position) {
        return mItems.get(position);
    }

    public void addAll(List<String> items) {
        mItems = new ArrayList<>(items);
    }
    public void flushData(List<MinePublishShenHeModel.RentListBean> data) {
        rentListDataSouce = data;
//        hao_recycleview.setAdapter(mSheHeAdapter);
//        mSheHeAdapter.notifyDataSetChanged();
    }


}
