package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityHeaderImageModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.ComRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.TopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;


public class CommunFC extends Fragment implements View.OnClickListener {

    private ImageView mImg;
    private LinearLayout mGallery;
    private LayoutInflater mInflater;
    private int[] mImgIds;
    // 朋友圈
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private ComRecycleViewAdapter mAdapter;
    private ArrayList<String> listData = new ArrayList<>();
    private int limit = 10;
    String picUrl = "";
    List<CommunityTopicListModel.ModuleListBean> topic;
    List<TopicListModel.TopicListBean> topicList;
    LinearLayout rightLay;
    Dialog mloading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_commun_fc, container, false);
            configurationNaviTitle();
            mloading = LoadingUtils.createLoadingDialog(getActivity(),"正在努力加载中...");
            mloading.show();
            initFriendMomentHeaderPicDataSource();
        }
        return view;
    }

    /**
     * 初始化朋友圈数据
     */
    private void initFriendMomentHeaderPicDataSource() {
        JSONObject picJson = new JSONObject();
        try {
            picJson.put("setupid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityGetPic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(picJson.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("宣传图", "" + response);
                        Gson gson = new Gson();
                        CommunityHeaderImageModel communityHeaderImageModel = gson.fromJson(response, CommunityHeaderImageModel.class);
                        if (communityHeaderImageModel.getCode() == 1) {
                            picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + communityHeaderImageModel.getSetup().getImg().toString();
//                            initFriendMomentView();
                            initFriendMomentGetTopicListDataSource();
                        } else if (communityHeaderImageModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            mloading.dismiss();
                        } else if (communityHeaderImageModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            mloading.dismiss();
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 获取模块列表
     */
    private void initFriendMomentGetTopicListDataSource() {
        JSONObject order = new JSONObject();
        try {
            order.put("sort", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityGetSectionList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("话题列表", "" + response);
                        topic = new ArrayList<CommunityTopicListModel.ModuleListBean>();
                        Gson gson = new Gson();
                        CommunityTopicListModel communityTopicListModel = gson.fromJson(response, CommunityTopicListModel.class);
                        if (communityTopicListModel.getCode() == 1) {
                            topic = communityTopicListModel.getModuleList();
                            initFriendMomentItemListDataSource();
                        } else if (communityTopicListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            mloading.dismiss();
                        } else if (communityTopicListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            mloading.dismiss();
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 获取话题列表
     */
    private void initFriendMomentItemListDataSource() {
        JSONObject picJson = new JSONObject();
        try {
            picJson.put("topicid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", picJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityTopicList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("话题列表————————", "" + response);
                        mloading.dismiss();
                        topicList = new ArrayList<TopicListModel.TopicListBean>();
                        Gson gson = new Gson();
                        TopicListModel topicListModel = gson.fromJson(response, new TypeToken<TopicListModel>() {
                        }.getType());
//                        List<TopicListModel> topicListModels =

                        if (topicListModel.getCode() == 1) {
                            topicList = topicListModel.getTopicList();

                            initFriendMomentView();
                        } else if (topicListModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (topicListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 获取话题
     */
    private void initFriendMomentItemDetailDataSource() {
        JSONObject picJson = new JSONObject();
        try {
            picJson.put("topicid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityGetTopic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(picJson.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("话题详情", "" + response);
                        Gson gson = new Gson();

//                        if (communityHeaderImageModel.getCode() == 1) {

//                        } else if (communityHeaderImageModel.getCode() == 0) {
//                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        } else if (communityHeaderImageModel.getCode() == 911) {
//                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
//                        }
                    }
                });
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        ImageView back = (ImageView) view.findViewById(R.id.left_pic);
        back.setVisibility(View.INVISIBLE);
        TextView title = (TextView) view.findViewById(R.id.navi_title);
        title.setText("社区");
        ImageView rightPubFriendMoment = (ImageView) view.findViewById(R.id.left_pic);
        rightPubFriendMoment.setBackgroundResource(R.drawable.com_pubfriendmoment);

        rightLay = (LinearLayout) view.findViewById(R.id.navi_right_pic_click_lay);
        rightLay.setOnClickListener(this);

//       initHorscrollView();
    }

    private void initFriendMomentView() {

        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        initFriendMomentData();
                        //注意此处
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) view.findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            //            @Override
//            public boolean canScrollVertically() {
//            return false;
//        }
            // SC嵌套ReCV防止数据显示不完整
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);

        // 设置头
//        RecyclerViewHeader recyclerViewHeader = (RecyclerViewHeader)view.findViewById(R.id.headerssss);
//        recyclerViewHeader.attachTo(hao_recycleview,true);


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

                        if (listData.size() >= 3 * limit) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }

                        for (int i = 0; i < limit; i++) {
                            listData.add(i + "");
                        }
                        mAdapter.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();

                    }
                }, 1000);
            }
        });

//        initFriendMomentData();
        mAdapter = new ComRecycleViewAdapter(getActivity(), picUrl, topic, topicList);
        hao_recycleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ComRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int topicid, int postion) {
                // 点赞
                initLikeData(topicid,postion);
                mAdapter.notifyDataSetChanged();
            }
        });
//        hao_recycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(), "click-----position" + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        hao_recycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(), "long click------position" + i, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    private void initLikeData(int topicid, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("topicid",topicid);
            jsonObject.put("like",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityUpdateTopic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("点赞", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response,RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            // 点赞成功 通知数组本地加一
                            String username = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.NICKNAME,""));
                            TopicListModel.TopicListBean.LikeListBean likeListBean = new TopicListModel.TopicListBean.LikeListBean();
                            likeListBean.setNickname(username);
                            topicList.get(position).getLikeList().add(likeListBean);
                            mAdapter.notifyDataSetChanged();

                        } else if (requestStatueModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "你已经点过赞了!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (requestStatueModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }
    ArrayList<String> topicName = null;
    ArrayList<Integer> topicModelID = null;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_right_pic_click_lay:
                topicName = new ArrayList<>();
                topicModelID = new ArrayList<>();
                for (int i = 0; i < topic.size(); i++) {
                    topicName.add(i, topic.get(i).getName());
                    topicModelID.add(i, topic.get(i).getModuleid());
                }
                Intent intent = new Intent(getActivity(), CommunityPublishTopicAC.class);
                intent.putExtra("TOPIC", topicName);
                intent.putExtra("TOPICMODELID", topicModelID);
                startActivity(intent);
                break;
        }

    }
}
