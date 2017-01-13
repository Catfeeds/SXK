package com.example.cfwifine.sxk.Section.HomeNC.Controller;


import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.HomeBannerModel;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.EightItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.HotTopicAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RecyclerBanner;
import com.example.cfwifine.sxk.Section.MineNC.Controller.AddressSettingCommomAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_publish_success;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
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


@SuppressLint("NewApi")
public class HomeFC extends Fragment {
    private List<ImageView> views = new ArrayList<ImageView>();
    ImageView more;
    RecyclerView recyclerView,hotcycleView,eightRV;
    RecycleAdapter myAdapter;
    HotTopicAdapter hotAdapter;
    EightItemRecycleAdapter eightItemRecycleAdapter;
    private ArrayList<String> listData = new ArrayList<>();
    public ArrayList<String> datasText = null;
    public ArrayList<Integer> datasPic = null;
    Dialog dialog;
    String [] text = new String[]{
            "交换","租赁","活动","鉴定","养护"
    };
    int[] pic = new int[]{
            R.drawable.home_change,R.drawable.home_rent,R.drawable.home_activity
            ,R.drawable.home_anwser,R.drawable.home_care
    };

    RecyclerBanner recyclerBanner;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
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
            dialog = LoadingUtils.createLoadingDialog(getActivity(),"加载中...");
            initBannerData();
            initBanner();
//            initHorscrollView();
            initEightItemRV();
            init();
            initHotTopic();
        }
        return view;

    }

    private void initBannerData() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("setupid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeRecycleBanner)
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
                        Log.e("轮播图", "" + response);
                        Gson gson = new Gson();
                        HomeBannerModel homeBannerModel = gson.fromJson(response, HomeBannerModel.class);
                        if (homeBannerModel.getCode() == 1) {
                            for (int i = 0; i<homeBannerModel.getSetup().getList().size();i++){
                                urls.add(new Entity(BaseInterface.ClassfiyGetAllHotBrandImgUrl+homeBannerModel.getSetup().getList().get(i).getImg().toString(),homeBannerModel.getSetup().getList().get(i).getLink().toString()));
                                initBanner();
                            }

                        } else if (homeBannerModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (homeBannerModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void initBanner() {
        recyclerBanner = (RecyclerBanner)view.findViewById(R.id.banner);
        recyclerBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
            @Override
            public void onClick(RecyclerBanner.BannerEntity entity) {
                LogUtil.e("网址为"+entity.getLink());
            }
        });
        recyclerBanner.setDatas(urls);
    }
    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;
        public Entity(String url,String link) {
            this.url = url;
            this.link = link;
        }

        @Override
        public String getUrl() {
            return url;
        }
        @Override
        public String getLink() {
            return link;
        }
    }

    // TODO ***************************************初始化8个专题***************************************
    private void initEightItemRV(){
        datasText = new ArrayList<>();
        for (int i= 0;i<text.length;i++){
            datasText.add(i,text[i]);
        }
        datasPic = new ArrayList<>();
        for (int i =0;i<text.length;i++){
            datasPic.add(i,pic[i]);
        }
        eightRV = (RecyclerView)view.findViewById(R.id.rv_home_item);
        eightItemRecycleAdapter = new EightItemRecycleAdapter(datasPic,datasText);
        eightRV.setLayoutManager(new GridLayoutManager(getActivity(),5));
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
