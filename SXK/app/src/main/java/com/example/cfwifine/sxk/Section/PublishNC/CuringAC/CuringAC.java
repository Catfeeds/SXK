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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.flyco.tablayout.SlidingTabLayout;
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
    public ViewPager viewPager;
    private ArrayList<CuringComListFC> mFagments = new ArrayList<>();
    public MyPagerAdapter adapter;
    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    Dialog dialog;
    public List<CuringListModel.ClassifyListBean> classifyDataSource;
    public List<CuringListModel.MaintainListBean> mainListDataSource;
    private List<MinePublishShenHeModel.RentListBean> rentListDataSouce;
    public String[] size=null;
    private ArrayList<CuringListModel.MaintainListBean> DATASOURCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curing_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "正在加载中...");


        initView();

        initData(1, 10);

    }

    /**
     * 舒适化养护界面的数据
     *
     * @param pageNum
     * @param pageSize
     */
    public void initData(int pageNum, int pageSize) {
        dialog.show();
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
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("养护列表", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        CuringListModel curingListModel = gson.fromJson(response, CuringListModel.class);
                        if (curingListModel.getCode() == 1) {
                            classifyDataSource = curingListModel.getClassifyList();
                            mainListDataSource = curingListModel.getMaintainList();
                            LogUtil.e("数据源",mainListDataSource.toString());

                            DATASOURCE = new ArrayList<CuringListModel.MaintainListBean>();
                            for (int i = 0; i<mainListDataSource.size();i++){
                                if (mainListDataSource.get(i).getClassifyid() == classifyDataSource.get(i).getClassifyid()){
                                    // 第一个tab的数据
                                    DATASOURCE.add(i,mainListDataSource.get(i));
                                }

                            }


                            initTabView();
                        } else if (curingListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (curingListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }


    private void initTabView() {
        tablayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        // 初始化界面
        for (int i = 0; i < classifyDataSource.size(); i++) {
            LogUtil.e("我是ID"+classifyDataSource.get(i).getClassifyid());
            mFagments.add(CuringComListFC.getInstance(DATASOURCE,classifyDataSource,classifyDataSource.get(i).getClassifyid()));
        }
        //getChildFragmentManager() 如果是嵌套在fragment中就要用这个
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        size = new String[classifyDataSource.size()];
        for (int i = 0; i < classifyDataSource.size(); i++) {
            size[i] = classifyDataSource.get(i).getName();
        }
        tablayout.setViewPager(viewPager, size);
        tablayout.setTextSelectColor(Color.parseColor("#16a6ae"));
        tablayout.setDividerColor(Color.WHITE);
        tablayout.setIndicatorColor(Color.parseColor("#16a6ae"));
        tablayout.setTextUnselectColor(Color.parseColor("#a1a1a1"));

    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back_pic.setOnClickListener(this);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setOnClickListener(this);
        navi_title.setText("养护");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

            return classifyDataSource.get(position).getName();

        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }

    private void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(CuringAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));

    }
}
