package com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Controller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Adapter.MineShenHeListAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;

public class WaitPassFC extends Fragment {

    private View view;
    private MineItemAC mineItemAC;
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MineShenHeListAdapter mSheHeAdapter;
    private List<MinePublishShenHeModel.RentListBean> rentListDataSouce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//		System.out.println("WaitPassFC  onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.wait_pass_fc, container, false);
            mineItemAC = (MineItemAC) getActivity();
            initMineData(1, 0, 0);

        }
        return view;
    }

    private void initSheHeRV() {
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.login_turquoise, R.color.login_turquoise, R.color.login_turquoise,
                R.color.login_turquoise);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //注意此处
                        initMineData(1, 0, 0);
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mSheHeAdapter.notifyDataSetChanged();
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
//        //设置自定义加载中和到底了效果
//        ProgressView progressView = new ProgressView(getActivity());
//        progressView.setIndicatorId(ProgressView.BallPulse);
//        progressView.setIndicatorColor(0xff16a6ae);
//        hao_recycleview.setFootLoadingView(progressView);
//
//        TextView textView = new TextView(getActivity());
//        textView.setText("已经到底啦~");
//        textView.setTextColor(getResources().getColor(R.color.black));
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
//        mAdapter = new  (getActivity(), classifySo);
        mSheHeAdapter = new MineShenHeListAdapter(getActivity(), rentListDataSouce);
        hao_recycleview.setAdapter(mSheHeAdapter);
        mSheHeAdapter.setOnItemClickListener(new MineShenHeListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int maintainid) {
                // rentID
                initDontSail(maintainid);
            }
        });
    }

    /**
     * 下架的订单
     *
     * @param maintainid
     */
    private void initDontSail(int maintainid) {
        mineItemAC.dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rentid", maintainid);
            jsonObject.put("status", 4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(BaseInterface.ChangeRent)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 mineItemAC.initSnackBar("请求失败！");
                                 mineItemAC.dialog.dismiss();
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 Log.e("我的待审核", "" + response);
                                 mineItemAC.dialog.dismiss();
                                 Gson gson = new Gson();
                                 RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                                 if (requestStatueModel.getCode() == 1) {
                                     initMineData(1, 0, 0);
                                     mineItemAC.initSnackBar("下架成功！");
                                 } else if (requestStatueModel.getCode() == 0) {
                                     mineItemAC.initSnackBar("下架失败！");
                                 } else if (requestStatueModel.getCode() == 911) {
                                     mineItemAC.initSnackBar("登录超时，请重新登录");
                                 }
                             }
                         }
                );


    }

    public void initMineData(final int status, int pageNum, int pageSize) {
        mineItemAC.dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("rentid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNum);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
            jsonObject.put("own", 1);
            jsonObject.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(BaseInterface.RentList)
                .addHeader("Cookie", "PHPSESSID=" + mineItemAC.PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mineItemAC.initSnackBar("请求失败！");
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mineItemAC.dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("我的待审核", "" + response);
                        mineItemAC.dialog.dismiss();
                        Gson gson = new Gson();
                        if (status == 1) {
                            // 审核中
                            MinePublishShenHeModel minePublishShenHeModel = gson.fromJson(response, MinePublishShenHeModel.class);
                            if (minePublishShenHeModel.getCode() == 1) {
                                rentListDataSouce = minePublishShenHeModel.getRentList();
                                initSheHeRV();
                            } else if (minePublishShenHeModel.getCode() == 0) {
                                mineItemAC.initSnackBar("请求失败！");
                            } else if (minePublishShenHeModel.getCode() == 911) {
                                mineItemAC.initSnackBar("登录超时，请重新登录");
                            }

                        }

                    }
                });

    }


    @Override
    public void onResume() {
//		System.out.println("WaitPassFC  onResume");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
//		System.out.println("WaitPassFC  onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
//		System.out.println("WaitPassFC  onDestroyView");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onStop() {
//		System.out.println("WaitPassFC  onStop");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onStart() {
//		System.out.println("WaitPassFC  onStart");
        // TODO Auto-generated method stub
        super.onStart();
    }

}
