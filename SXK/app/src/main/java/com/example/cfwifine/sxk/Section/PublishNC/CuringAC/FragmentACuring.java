package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.CuringListAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;


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


}
