package com.example.cfwifine.sxk.Section.HomeNC.Controller;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.EightItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.HotTopicAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.AddressSettingCommomAC;
import com.example.cfwifine.sxk.Utils.LogUtil;


import java.util.ArrayList;
import java.util.List;


@SuppressLint("NewApi")
public class HomeFC extends Fragment {
    private List<ImageView> views = new ArrayList<ImageView>();
    ImageView more;
    RecyclerView recyclerView,hotcycleView,eightRV;
    RecycleAdapter myAdapter;
    HotTopicAdapter hotAdapter;
    EightItemRecycleAdapter eightItemRecycleAdapter;
    private ArrayList<String> listData = new ArrayList<>();

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    public ArrayList<String> datasText = null;
    public ArrayList<Integer> datasPic = null;
    String [] text = new String[]{
            "租赁","交换","鉴定","养护","上新","活动","顾问","分享"
    };
    int[] pic = new int[]{
            R.drawable.home_rent,R.drawable.home_change,R.drawable.home_recognize,R.drawable.home_care
            ,R.drawable.home_new,R.drawable.home_activity,R.drawable.home_anwser,R.drawable.home_share
    };



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
            initEightItemRV();
            init();
            initHotTopic();
        }
        return view;

    }
    // TODO ***************************************初始化8个专题***************************************
    private void initEightItemRV(){
        datasText = new ArrayList<>();
        for (int i= 0;i<8;i++){
            datasText.add(i,text[i]);
        }
        datasPic = new ArrayList<>();
        for (int i =0;i<8;i++){
            datasPic.add(i,pic[i]);
        }
        eightRV = (RecyclerView)view.findViewById(R.id.rv_home_item);
        eightItemRecycleAdapter = new EightItemRecycleAdapter(datasPic,datasText);
        eightRV.setLayoutManager(new GridLayoutManager(getActivity(),4));
        eightRV.setAdapter(eightItemRecycleAdapter);
        eightItemRecycleAdapter.setOnItemClickListener(new EightItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LogUtil.e("点击了第几个"+position);
                JumpForPage(position);
            }
        });
    }

    private void JumpForPage(int position) {
        startActivity(EightItemDetailAC.class,position);
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

    private void startActivity(Class<?> cls,Integer jumpvalue) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra("JUMPEIGHTITEMDETAIL",jumpvalue);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



}
