package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CuringAC extends AppCompatActivity implements View.OnClickListener {

    SlidingTabLayout tablayout;
    private ViewPager viewPager;
    private ArrayList<CuringBaseFragment> mFagments = new ArrayList<>();
    private String[] mTitles = {"审核中","发布中", "租赁中", "已下架", "未通过"};
    private String[] mRentTitles = {"待收货", "已收货", "已完成","已退回"};
    private String[] mCuringTitles = {"养护中","已完成"};

    private MyPagerAdapter adapter;
    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    Dialog dialog;
    private List<CuringListModel.ClassifyListBean> classifyDataSource;
    private List<CuringListModel.MaintainListBean> mainListDataSource;
    private int VALUE;
    private List<MinePublishShenHeModel.RentListBean> rentListDataSouce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curing_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"正在加载中...");

        VALUE = getIntent().getIntExtra("JUMPPOSITION",0);
        /**
         * VALUE  1 我的发布 2我的租赁 3 我的养护
         */
        initView();
        if (VALUE > 0){
            initTabView();
//            adapter.notifyDataSetChanged();
        }else{
            initData(-1,1,10);
        }
    }

    /**
     * 舒适化养护界面的数据
     * @param classfiyid
     * @param pageNum
     * @param pageSize
     */
    public void initData(final int classfiyid,int pageNum,int pageSize) {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("sort",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageSize",pageSize);
            jsonObject.put("pageNo",pageNum);
            jsonObject.put("order",js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CuringList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("养护列表", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        CuringListModel curingListModel = gson.fromJson(response,CuringListModel.class);
                        if (curingListModel.getCode() == 1) {
                            if (classfiyid == -1){
                                classifyDataSource = curingListModel.getClassifyList();
                                mainListDataSource = curingListModel.getMaintainList();
                                initTabView();

                            }else if (classfiyid == -2){
                                classifyDataSource = curingListModel.getClassifyList();
                                mainListDataSource = curingListModel.getMaintainList();
                                adapter.notifyDataSetChanged();

                            }else {

                            }


                        } else if (curingListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (curingListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });




    }

    public void initMineData(final int status, int pageNum, int pageSize){
        JSONObject order = new JSONObject();
        try {
            order.put("rentid",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo",pageNum);
            jsonObject.put("pageSize",pageSize);
            jsonObject.put("order",order);
            jsonObject.put("own",1);
            jsonObject.put("status",status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RentList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("我的发布", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        if (status == 1){
                            // 审核中
                            MinePublishShenHeModel minePublishShenHeModel = gson.fromJson(response,MinePublishShenHeModel.class);
                            if (minePublishShenHeModel.getCode() == 1) {
                                rentListDataSouce = minePublishShenHeModel.getRentList();
//                                FragmentACuring fragmentACuring = new FragmentACuring();
//                                fragmentACuring.getShenHeInstance(rentListDataSouce,0);

//                                fragmentACuring.initView();

                            } else if (minePublishShenHeModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (minePublishShenHeModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录");
                            }

                        }else if (status == 2){
                            // 发布中
                        }else if (status == 3){
                            // 租赁中
                        }else if (status == 4){
                            // 已下架
                        }else if (status == 5){
                            // 未通过
                        }

                    }
                });

    }

    private void initTabView(){
        tablayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        if (VALUE == 1){
            // 初始化界面
//            FragmentACuring fragmentACuring = new FragmentACuring();
            for (int i = 0; i < mTitles.length; i++) {
//                mFagments.add(fragmentACuring.getShenHeInstance(null,));
                mFagments.add(FragmentACuring.getTestInstance(i));
            }
        }else if (VALUE == 2){
            // 初始化界面
            for (int i = 0; i < mRentTitles.length; i++) {
                mFagments.add(FragmentACuring.getInstance(mRentTitles[i], null,0));
            }
        }else if (VALUE == 3){
            // 初始化界面
            for (int i = 0; i < mCuringTitles.length; i++) {
                mFagments.add(FragmentACuring.getInstance(mCuringTitles[i], null,0));
            }
        }else {
            // 初始化界面
            for (int i = 0; i < classifyDataSource.size(); i++) {
                mFagments.add(FragmentACuring.getInstance(classifyDataSource.get(i).getName(), mainListDataSource,classifyDataSource.get(i).getClassifyid()));

            }
        }


        //getChildFragmentManager() 如果是嵌套在fragment中就要用这个
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtil.e("滚定监听"+position);
            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.e("滚定监听选中"+position);
                initMineData(position+1,1,10);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);


        if (VALUE == 1){
            tablayout.setViewPager(viewPager, mTitles);
        }else if (VALUE == 2){
            tablayout.setViewPager(viewPager, mRentTitles);
        }else if (VALUE == 3){
            tablayout.setViewPager(viewPager, mCuringTitles);
        }else {
            String[] s = new String[classifyDataSource.size()];
            for (int i = 0; i<classifyDataSource.size();i++){
                s[i] = classifyDataSource.get(i).getName();
            }
            tablayout.setViewPager(viewPager, s);
        }

        tablayout.setTextSelectColor(Color.parseColor("#16a6ae"));
        tablayout.setDividerColor(Color.WHITE);
        tablayout.setIndicatorColor(Color.parseColor("#16a6ae"));
        tablayout.setTextUnselectColor(Color.parseColor("#a1a1a1"));
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back_pic.setOnClickListener(this);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setOnClickListener(this);

        if (VALUE == 1){
            navi_title.setText("我的发布");
        }else if (VALUE == 2){
            navi_title.setText("我的租赁");
        }else if (VALUE == 3){
            navi_title.setText("我的养护");
        }else {
            navi_title.setText("养护");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (VALUE==1){
                return mTitles[position];
            }else if (VALUE==2){
                return mRentTitles[position];
            }else if (VALUE==3){
                return mCuringTitles[position];
            }else {
                return classifyDataSource.get(position).getName();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }
    private void initSnackBar(String value){
        SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));

    }
}
