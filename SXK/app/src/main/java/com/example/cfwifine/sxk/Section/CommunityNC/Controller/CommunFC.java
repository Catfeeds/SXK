package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityHeaderImageModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.ComRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.TopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.Image;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
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

    private List<List<Image>> imagesList;

    private String[][] images = new String[][]{
            {"http://image.tianjimedia.com/uploadImages/2013/305/33E65M598VXC.jpg", "250", "250"}
            , {"http://img15.3lian.com/2015/a1/11/d/121.jpg", "640", "960"}
            , {"http://image.tianjimedia.com/uploadImages/2014/238/38/JRN83IK657XC_1000x500.jpg", "250", "250"}
            , {"http://image.tianjimedia.com/uploadImages/2014/238/38/JRN83IK657XC_1000x500.jpg", "250", "250"}
            , {"http://img1.3lian.com/2015/w21/11/d/81.jpg", "250", "250"}
            , {"http://image.tianjimedia.com/uploadImages/2014/290/45/WVZ917J06LF5.jpg", "250", "250"}
            , {"http://image84.360doc.com/DownloadImg/2015/04/0209/51898729_18.jpg", "250", "250"}
            , {"http://image.fvideo.cn/www/uploadfilen/2009/02/26/16/img042728804.jpg", "250", "250"}
            , {"http://img1.3lian.com/2015/a1/110/d/180.jpg", "1280", "800"}
    };
    String picUrl = "";
    List<CommunityTopicListModel.ModuleListBean> topic;
    List<TopicListModel.TopicListBean> topicList;
    LinearLayout rightLay;

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
//            initFriendMomentView();
            initFriendMomentHeaderPicDataSource();
//            initFriendMomentGetTopicListDataSource();
//            initFriendMomentItemListDataSource();


        }
        // Inflate the layout for this fragment
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
                        } else if (communityHeaderImageModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
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
                        } else if (communityTopicListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
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
            jsonObject.put("pageNo",0);
            jsonObject.put("pageSize",0);
            jsonObject.put("order",picJson);
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
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("话题列表————————", "" + response);
                        topicList = new ArrayList<TopicListModel.TopicListBean>();
                        Gson gson = new Gson();
                        TopicListModel topicListModel = gson.fromJson(response,new TypeToken<TopicListModel>() {}.getType());
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

        rightLay = (LinearLayout)view.findViewById(R.id.navi_right_pic_click_lay);
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
                }, 3000);

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
                }, 2000);
            }
        });

//        initFriendMomentData();
        mAdapter = new ComRecycleViewAdapter(getActivity(), picUrl,topic,topicList);
        hao_recycleview.setAdapter(mAdapter);


        hao_recycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "click-----position" + i, Toast.LENGTH_SHORT).show();
            }
        });


        hao_recycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "long click------position" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

//    private void initFriendMomentData() {
//        listData.clear();
//        for (int i = 0; i < limit; i++) {
//            listData.add(i + "");
//        }
//        imagesList = new ArrayList<>();
//        //这里单独添加一条单条的测试数据，用来测试单张的时候横竖图片的效果
//        ArrayList<Image> singleList = new ArrayList<>();
//        singleList.add(new Image(images[8][0], Integer.parseInt(images[8][1]), Integer.parseInt(images[8][2])));
//        imagesList.add(singleList);
//        //从一到9生成9条朋友圈内容，分别是1~9张图片
//        for (int i = 0; i < 9; i++) {
//            ArrayList<Image> itemList = new ArrayList<>();
//            for (int j = 0; j <= i; j++) {
//                itemList.add(new Image(images[j][0], Integer.parseInt(images[j][1]), Integer.parseInt(images[j][2])));
//            }
//            imagesList.add(itemList);
//        }
//    }


    ArrayList<String> topicName = null;
    ArrayList<Integer> topicModelID = null;
    @Override
    public void onClick(View view) {
//        int id = view.getId();
//        String content = (String) view.getTag();
//        for (int i = 0; i < mImgIds.length; i++) {
//            if (i == id) {
//                Log.e("你点击了", "" + content);
//            }
//        }
        switch (view.getId()){
            case R.id.navi_right_pic_click_lay:
                topicName = new ArrayList<>();
                topicModelID = new ArrayList<>();
                for (int i = 0;i<topic.size();i++){
                    topicName.add(i,topic.get(i).getName());
                    topicModelID.add(i,topic.get(i).getModuleid());
                }
                Intent intent = new Intent(getActivity(),CommunityPublishTopicAC.class);
                intent.putExtra("TOPIC",topicName);
                intent.putExtra("TOPICMODELID",topicModelID);
                startActivity(intent);
                break;
        }

    }
}
