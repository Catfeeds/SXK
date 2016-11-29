package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyLeftRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishBrandAC;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CityBean;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.View.CityAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.DividerItemDecoration;
import com.example.cfwifine.sxk.Section.PublishNC.View.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.RecycleViewListener;
import com.example.cfwifine.sxk.Section.PublishNC.View.ViewHolder;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.example.cfwifine.sxk.View.MyGridAdapter;
import com.example.cfwifine.sxk.View.MyGridView;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class ClassifyFC extends Fragment implements View.OnClickListener {

    View view;
    RecyclerView leftRecycleView,rightRecycleView;
    ArrayList <TestModel> datalist = new ArrayList<>();

    MyGridView myGridView;

    // 配置右侧indexbar
    private CityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;
    private TitleItemDecoration mDecoration;
    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;


    RelativeLayout relativeLayout ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_classify_fc, container, false);
            configurationNaviTitle();
            initData();
            initleftRecycleView();
            initRightRecycleView();
            relativeLayout = (RelativeLayout)view.findViewById(R.id.classify_header);
//            initGridView();
            initView();

        }


        return view;
    }
    FrameLayout frameLayout,frameLayouts;
    private void initView() {
        frameLayout = (FrameLayout)view.findViewById(R.id.classify_brand_frame);
        frameLayouts = (FrameLayout)view.findViewById(R.id.classify_brand_frames);
        frameLayout.setVisibility(View.VISIBLE);
        frameLayouts.setVisibility(View.GONE);
    }

    private void initGridView() {
//        myGridView=(MyGridView) view.findViewById(R.id.classify_gridView);
//        myGridView.setAdapter(new MyGridAdapter(getActivity()));
    }

    private void initData() {
        for (int i = 0;i<3;i++){
            if (i == 0){
                TestModel testModel = new TestModel("测试"+i,true);
                datalist.add(testModel);
            }else {
                TestModel testModel = new TestModel("测试" + i, false);
                datalist.add(testModel);
            }
        }
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        ImageView back = (ImageView) view.findViewById(R.id.navi_back_pic);
        back.setVisibility(View.INVISIBLE);
        TextView title = (TextView)view.findViewById(R.id.navi_title);
        title.setText("分类");
    }
    // TODO*********************************配置左侧recycleview************************************
    private void initleftRecycleView(){
        leftRecycleView = (RecyclerView)view.findViewById(R.id.classify_left_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftRecycleView.setLayoutManager(linearLayoutManager);
        ClassifyLeftRecycleViewAdapter classifyLeftRecycleViewAdapter = new ClassifyLeftRecycleViewAdapter(datalist);
        leftRecycleView.setAdapter(classifyLeftRecycleViewAdapter);
        leftRecycleView.addOnItemTouchListener(new RecycleViewListener(leftRecycleView, new RecycleViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了一个",""+datalist.get(position).getText());
                if (position == 0){
                    frameLayouts.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);

                }else  {

                    frameLayout.setVisibility(View.GONE);
                    frameLayouts.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    // TODO*********************************配置右侧recycleview************************************
    @SuppressLint("NewApi")
    private  void  initRightRecycleView(){
        rightRecycleView = (RecyclerView)view.findViewById(R.id.classify_right_recycleview);
        rightRecycleView.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));
        //initDatas();
        //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过
        mAdapter = new CityAdapter(getActivity(), mDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
//                holder.setText(R.id.tvCity, (String) o);
//                Drawable drawable =
                holder.setImageDrawable(R.id.header_pic,(Drawable) o);

            }
        };



        mHeaderAdapter.setHeaderView(R.layout.header_complex,null);


        rightRecycleView.setAdapter(mHeaderAdapter);
        rightRecycleView.addItemDecoration(mDecoration = new TitleItemDecoration(getActivity(), mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mDecoration.setColorTitleBg(Color.WHITE);
        mDecoration.setmTitleHeight(128);
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        rightRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        // 重写点击事件方法
        rightRecycleView.addOnItemTouchListener(new RecycleViewListener(rightRecycleView, new RecycleViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position != 0) {
                    SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "选中了" + mDatas.get(position - 1).getCity(), Color.WHITE, Color.parseColor("#16a6ae"));
                }else {
                    SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "点击了header", Color.WHITE, Color.parseColor("#16a6ae"));
                }


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));



        //使用indexBar
        mTvSideBarHint = (TextView) view.findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) view.findViewById(R.id.indexBar);//IndexBar

        initDatas(getResources().getStringArray(R.array.provinces));
    }
    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAdapter.setDatas(mDatas);
                mHeaderAdapter.notifyDataSetChanged();

                mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(true)//设置需要真实的索引
                        .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 0);

    }

    @Override
    public void onClick(View view) {

    }
}
