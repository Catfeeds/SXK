package com.example.cfwifine.sxk.Section.HomeNC;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.CircleViewImage.ADInfo;
import com.example.cfwifine.sxk.CircleViewImage.CycleViewPager;
import com.example.cfwifine.sxk.CircleViewImage.ViewFactory;
import com.example.cfwifine.sxk.R;


import java.util.ArrayList;
import java.util.List;


@SuppressLint("NewApi")
public class HomeFC extends Fragment {
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;
    ImageView more;
    RecyclerView recyclerView,hotcycleView;
    RecycleAdapter myAdapter;
    HotTopicAdapter hotAdapter;
    private ArrayList<String> listData = new ArrayList<>();

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home_fc, container, false);
//            initBanner();
//            initHorscrollView();
            init();
            initHotTopic();
        }
        return view;

    }
    // TODO ***************************************初始化热门专题***************************************
    private void initHotTopic() {
        hotcycleView = (RecyclerView)view.findViewById(R.id.home_hot_recycleView);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        hotcycleView.setLayoutManager(layoutManagers);
        hotAdapter = new HotTopicAdapter(listData,getActivity());
        hotAdapter.notifyDataSetChanged();
        hotcycleView.setAdapter(hotAdapter);
    }





    // TODO ***************************************初始化精选分类***************************************

    private void init() {
        for (int i = 0; i < 2; i++) {
            listData.add(i + "");
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


//        RelativeLayout lay = (RelativeLayout)view.findViewById(R.id.home_classify_lay);
//
//
//        WindowManager windowManager = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
//        int width = windowManager.getDefaultDisplay().getWidth();
//        int height = windowManager.getDefaultDisplay().getHeight();
////
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lay.getLayoutParams();
//        params.width = width;
//        params.height = 812;
//        lay.setLayoutParams(params);


        myAdapter = new RecycleAdapter(listData,getActivity());
        myAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(myAdapter);
    }
//    private void initHorscrollView() {
//        mInflater = LayoutInflater.from(getActivity());
//        initHorscrollViewData();
//        initHorscrollViewView();
//    }
//    @SuppressLint("NewApi")
//    private void initHorscrollViewView() {
//        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.id_horscrollview);
//        horizontalScrollView.setOnScrollChangeListener(this);
//
//        more = (ImageView)view.findViewById(R.id.id_more);
//        mGallery = (LinearLayout) view.findViewById(R.id.id_gallerys);
//
//        for (int i = 0; i < mImgIds.length; i++)
//        {
//
//            View view = mInflater.inflate(R.layout.activity_index_gallery_item,
//                    mGallery, false);
//            ImageView img = (ImageView) view
//                    .findViewById(R.id.id_index_gallery_item_image);
//            img.setImageResource(mImgIds[i]);
//            TextView txt = (TextView) view
//                    .findViewById(R.id.id_index_gallery_item_text);
//            txt.setText("我是图");
//            // 自适应屏幕
//            RelativeLayout lay = (RelativeLayout)view.findViewById(R.id.change_size);
//            WindowManager windowManager = (WindowManager)getActivity().getSystemService(getActivity().WINDOW_SERVICE);
//            int width = windowManager.getDefaultDisplay().getWidth();
//            int height = windowManager.getDefaultDisplay().getHeight();
//
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)lay.getLayoutParams();
//            params.width = width/3;
//            params.height = 400;
//            lay.setLayoutParams(params);
//
//            // 点击事件
//            view.setId(i);
//            view.setTag("美女第"+i+"位");
//            view.setOnClickListener(this);
//            mGallery.addView(view);
//        }
//    }
//
//    private void initHorscrollViewData() {
//        mImgIds = new int[] { R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder,
//                R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder,
//                R.drawable.home_placeholder, R.drawable.home_placeholder };
//    }


    // TODO ***************************************初始化轮播图***************************************
    @SuppressLint("NewApi")
    private void initBanner() {

        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        cycleViewPager = (CycleViewPager)getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);


        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();

    }
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(getActivity(),
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
//                startActivity(DetailInfoActivity.class);
            }

        }

    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
