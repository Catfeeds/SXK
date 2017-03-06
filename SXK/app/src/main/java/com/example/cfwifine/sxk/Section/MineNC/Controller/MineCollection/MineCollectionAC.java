package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class MineCollectionAC extends AppCompatActivity implements CollectionSlideViewRecycleViewAdapter.IonSlidingViewClickListener, View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView collection_rv;
    Dialog dialog;
    private MineCollectionModel.CollectionBean dataSource;
    private CollectionSlideViewRecycleViewAdapter mAdapter;
    private LinearLayout goods;
    private LinearLayout curing;
    private Button goods_btn;
    private View goods_line;
    private Button curing_btn;
    private View curing_line;
    private String COMURL = "";
    private MineCuringCollectionListModel.CollectionBean curingDataSource=null;
    private String BASEURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collection_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("我的收藏");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        collection_rv = (RecyclerView) findViewById(R.id.collection_rv);
        goods = (LinearLayout) findViewById(R.id.goods);
        goods.setOnClickListener(this);
        curing = (LinearLayout) findViewById(R.id.curing);
        curing.setOnClickListener(this);
        goods_btn = (Button) findViewById(R.id.goods_btn);
        goods_line = (View) findViewById(R.id.goods_line);
        curing_btn = (Button) findViewById(R.id.curing_btn);
        curing_line = (View) findViewById(R.id.curing_line);
        goods_btn.setClickable(false);
        curing_btn.setClickable(false);
        initCollectionData(1);
    }

    private void initCollectionData(final int types) {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (types == 1) {
            COMURL = BaseInterface.CollectionList;
        } else {
            COMURL = BaseInterface.CuringCollectionList;
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(COMURL)
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
                        Gson gson = new Gson();
                        if (types == 1) {
                            Log.e("收藏列表", "" + response);
                            MineCollectionModel mineCollectionModel = gson.fromJson(response, MineCollectionModel.class);
                            if (mineCollectionModel.getCode() == 1) {
                                dataSource = mineCollectionModel.getCollection();
                                setAdapter(1);
                            } else if (mineCollectionModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (mineCollectionModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        }else {
                            LogUtil.e("养护收藏列表"+response);
                            MineCuringCollectionListModel mineCuringCollectionListModel = gson.fromJson(response,MineCuringCollectionListModel.class);
                            if (mineCuringCollectionListModel.getCode() == 1) {
                                curingDataSource = mineCuringCollectionListModel.getCollection();
                                setAdapter(2);
                            } else if (mineCuringCollectionListModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (mineCuringCollectionListModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        }
                    }
                });


    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void setAdapter(int i) {
        collection_rv.setLayoutManager(new LinearLayoutManager(this));
        if (i == 1){
            collection_rv.setAdapter(mAdapter = new CollectionSlideViewRecycleViewAdapter(this, dataSource,null));
        }else {
            collection_rv.setAdapter(mAdapter = new CollectionSlideViewRecycleViewAdapter(this, null,curingDataSource));
        }
        collection_rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int rentid,int maintainid, int position) {
        initCancelCollection(rentid,maintainid, position);
    }

    private void initCancelCollection(final int rentid, int maintainid, final int position) {
        dialog.show();
        JSONObject js = new JSONObject();
        if (rentid != -1){
            try {
                js.put("rentid", rentid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BASEURL = BaseInterface.CollectionDel;
        }else {
            try {
                js.put("maintainid", maintainid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BASEURL = BaseInterface.CuringCollectionDel;
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BASEURL)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            mAdapter.removeData(position);
                            initSnackBar("取消收藏成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("取消收藏失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.goods:
                clickGoodsCollection();
                break;
            case R.id.curing:
                clickCuringCollection();
                break;
            default:
                break;

        }
    }


    private void clickCuringCollection() {
        curing_btn.setTextColor(getResources().getColor(R.color.login_turquoise));
        curing_line.setBackgroundColor(getResources().getColor(R.color.login_turquoise));
        goods_btn.setTextColor(getResources().getColor(R.color.textGray));
        goods_line.setBackgroundColor(getResources().getColor(R.color.white));

        initCollectionData(2);
    }

    private void clickGoodsCollection() {
        goods_btn.setTextColor(getResources().getColor(R.color.login_turquoise));
        goods_line.setBackgroundColor(getResources().getColor(R.color.login_turquoise));
        curing_btn.setTextColor(getResources().getColor(R.color.textGray));
        curing_line.setBackgroundColor(getResources().getColor(R.color.white));
        initCollectionData(1);
    }
}
