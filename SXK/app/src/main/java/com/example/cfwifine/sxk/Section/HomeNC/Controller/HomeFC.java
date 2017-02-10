package com.example.cfwifine.sxk.Section.HomeNC.Controller;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.BaseAC.MainAC;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RongTokenModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.HomeBannerModel;
import com.example.cfwifine.sxk.Section.CommunityNC.explosionfield.ExplosionField;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.EightItemRecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.HotTopicAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RecycleAdapter;
import com.example.cfwifine.sxk.Section.HomeNC.Adapter.RecyclerBanner;
import com.example.cfwifine.sxk.Section.HomeNC.CustomDialog.AlphaScrollView;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeClassSelectedModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeHotListModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeSelectedClassModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ThreeBlockModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Section.PublishNC.CuringAC.CuringAC;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;


@SuppressLint("NewApi")
public class HomeFC extends Fragment implements View.OnClickListener {
    private List<ImageView> views = new ArrayList<ImageView>();
    ImageView more;
    RecyclerView recyclerView, hotcycleView, eightRV;
    RecycleAdapter myAdapter;
    HotTopicAdapter hotAdapter;
    EightItemRecycleAdapter eightItemRecycleAdapter;
    private ArrayList<String> listData = new ArrayList<>();
    public ArrayList<String> datasText = null;
    public ArrayList<Integer> datasPic = null;
    Dialog dialog;
    String[] text = new String[]{
            "交换", "租赁", "活动", "鉴定", "养护"
    };
    int[] pic = new int[]{
            R.drawable.home_change, R.drawable.home_rent, R.drawable.home_activity
            , R.drawable.home_anwser, R.drawable.home_care
    };

    RecyclerBanner recyclerBanner;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    private ImageView home_left_pic;
    private ImageView home_right_top_pic;
    private ImageView home_right_bottom_pic;
    private AlphaScrollView home_scrollView;
    private LinearLayout home_search_lay;
    private boolean isFirst = true;
    private List<HomeSelectedClassModel.ClassListBean> classDataSource;
    private List<HomeHotListModel.TopicListBean> hotDataSource;
    private MainAC mainAC;
    private String userinfo=null;
    private UserInfoModel userInfoModel=null;
    private ImageButton homeMessage;
    private int mineUserId=0;
    private String RongToken="";
    private FrameLayout homeToastView;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack;
    FrameLayout mFlCardBack;
    FrameLayout mFlCardFront;
    private ExplosionField mExplosionField;
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
            dialog = LoadingUtils.createLoadingDialog(getActivity(), "加载中...");
            mainAC = (MainAC) getActivity();
            mExplosionField = ExplosionField.attach2Window(getActivity());
            initBannerData();
            initBanner();
            initView();
            initEightItemRV();
            initThreeView();
            initSelectedClassData();
            initHotData();
            // 精选分类
        }

        return view;

    }
    // 初始化热门专题
    private void initHotData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sort",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo",0);
            js.put("pageSize",0);
            js.put("order",jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeHotTopList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("热门分类", "" + response);
                        Gson gson = new Gson();
                        HomeHotListModel homeHotListModel = gson.fromJson(response, HomeHotListModel.class);
                        if (homeHotListModel.getCode() == 1) {
                            hotDataSource = homeHotListModel.getTopicList();
                            initHotTopic();
                        } else if (homeHotListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (homeHotListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initSelectedClassData() {
        JSONObject order = new JSONObject();
        try {
            order.put("sort",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo",0);
            js.put("pageSize",0);
            js.put("order",order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeSelectedClass)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("精选分类", "" + response);
                        Gson gson = new Gson();
                        HomeSelectedClassModel homeSelectedClassModel = gson.fromJson(response, HomeSelectedClassModel.class);
                        if (homeSelectedClassModel.getCode() == 1) {
                            classDataSource = homeSelectedClassModel.getClassList();
                            init();
                        } else if (homeSelectedClassModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (homeSelectedClassModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });

    }

    private void initBannerData() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("setupid", 1);
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
                            for (int i = 0; i < homeBannerModel.getSetup().getList().size(); i++) {
                                urls.add(new Entity(BaseInterface.ClassfiyGetAllHotBrandImgUrl + homeBannerModel.getSetup().getList().get(i).getImg().toString(), homeBannerModel.getSetup().getList().get(i).getLink().toString()));
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
        recyclerBanner = (RecyclerBanner) view.findViewById(R.id.banner);
        recyclerBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
            @Override
            public void onClick(RecyclerBanner.BannerEntity entity) {
                LogUtil.e("网址为" + entity.getLink());
                Intent intent = new Intent(getActivity(),BannerDetailAC.class);
                intent.putExtra("URLINK",entity.getLink());
                startActivity(intent);
            }
        });
        recyclerBanner.setDatas(urls);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_left_pic:
                startActivity(HomeThreeBlockDetailAC.class, 11);
                break;
            case R.id.home_right_top_pic:
                startActivity(HomeThreeBlockDetailAC.class, 12);
                break;
            case R.id.home_right_bottom_pic:
                startActivity(HomeThreeBlockDetailAC.class, 13);
                break;
            case R.id.message:
                initRongMessageList();
                break;
            default:
                break;
        }
    }

    private void initRongMessageList() {
        mineUserId = (int) SharedPreferencesUtils.getParam(getActivity(), BaseInterface.USERID,0);
        LogUtil.e("我的ID"+mineUserId);
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("userid",mineUserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RONGYUNDemo)
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
                        Log.e("获取Token", "" + response);
                        Gson gson = new Gson();
                        RongTokenModel rongTokenModel = gson.fromJson(response, RongTokenModel.class);
                        if (rongTokenModel.getCode() == 1) {
                            RongToken = rongTokenModel.getToken();
                            connectRongServer(RongToken);
                        } else if (rongTokenModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (rongTokenModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
//                LogUtil.e("返回的userID1"+userId);
//                LogUtil.e("返回的userID"+rentDetail.getUser().getUserid());
//                LogUtil.e("返回的userID"+rentDetail.getUser().getUserid());
                if (RongIM.getInstance()!=null){
                    Map<String,Boolean> supportedConversation = new HashMap<String, Boolean>();
                    supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(),false);
                    RongIM.getInstance().startConversationList(getActivity(),supportedConversation);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(getActivity(), "连接服务器失败！请稍后重试！", Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
//                Toast.makeText(ProductDetailsAC.this, "token错误，请检查token和appkey是否正确！", Toast.LENGTH_SHORT).show();
                LogUtil.e("TOKEN不正确！");
//                Log.e(TAG, "token is error ,please check token and appkey");
            }
        });

    }


    private void initView() {
        homeToastView = (FrameLayout)view.findViewById(R.id.home_toast_lay);
//        homeToastView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                flipCard(view);
//            }
//        });
        mFlCardBack = (FrameLayout)view.findViewById(R.id.main_fl_card_back) ;
        mFlCardFront = (FrameLayout)view.findViewById(R.id.main_fl_card_front);
        // 点击实现爆炸效果
        homeToastView.setClickable(true);
        homeToastView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(homeToastView);
                homeToastView.setVisibility(View.GONE);
            }
        });

        homeMessage = (ImageButton)view.findViewById(R.id.message);
        homeMessage.setOnClickListener(this);
        home_search_lay = (LinearLayout) view.findViewById(R.id.home_search_lay);
        home_search_lay.setOnClickListener(this);
        home_scrollView = (AlphaScrollView) view.findViewById(R.id.home_scrollView);
        home_scrollView.setScrollViewListener(new AlphaScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {
                if (isFirst){
                    isFirst = false;
                }
                titleAnim(oldy,y);
            }
        });

        // 设置底部反转动画
        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }
    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.anim_in);

//         设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                homeToastView.setClickable(true);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                homeToastView.setClickable(true);
            }
        });

    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
        new CountDownTimer(20000, 2000) {
            public void onTick(long millisUntilFinished) {
                flipCard(view);
            }

            public void onFinish() {
                mExplosionField.explode(homeToastView);
//                homeToastView.setOnClickListener(null);
                homeToastView.setVisibility(View.GONE);
            }
        }.start();

    }

    //     翻转卡片
    public void flipCard(View view) {
        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(mFlCardFront);
            mLeftInSet.setTarget(mFlCardBack);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutSet.setTarget(mFlCardBack);
            mLeftInSet.setTarget(mFlCardFront);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }
    private void titleAnim(int oldy, int y) {
        if (y < 800) {
            float alpha = 1 - ((float) y) / 800;
            home_search_lay.setAlpha(alpha);
            if (alpha==0)
            {
                home_search_lay.setClickable(false);
            }else
            {
                home_search_lay.setClickable(true);
            }
        } else {
            //下滑显示标题栏
            if (oldy > y) {
                home_search_lay.setAlpha(1);
                home_search_lay.setClickable(true);
            } else {
                home_search_lay.setAlpha(0);
                home_search_lay.setClickable(false);
            }
        }
    }

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;

        public Entity(String url, String link) {
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

    private void initThreeView() {
        home_left_pic = (ImageView) view.findViewById(R.id.home_left_pic);
        home_right_top_pic = (ImageView) view.findViewById(R.id.home_right_top_pic);
        home_right_bottom_pic = (ImageView) view.findViewById(R.id.home_right_bottom_pic);
        home_left_pic.setOnClickListener(this);
        home_right_top_pic.setOnClickListener(this);
        home_right_bottom_pic.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initThreeViewData();
            }
        }).start();
    }

    /**
     * 初始化三个木块的数据
     */
    private void initThreeViewData() {
        initLeftPicData();
        initRightTopData();
        initRightBottom();
    }

    private void initRightBottom() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeRightBottomPic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("三块的数据-右下", "" + response);
                        Gson gson = new Gson();
                        ThreeBlockModel threeBlockModel = gson.fromJson(response, ThreeBlockModel.class);
                        if (threeBlockModel.getCode() == 1) {
                            String leftpic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + threeBlockModel.getSetup().getImg();
                            Glide.with(getActivity()).load(leftpic).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(home_right_bottom_pic);
                        } else if (threeBlockModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (threeBlockModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void initRightTopData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeRightTopPic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("三块的数据-右上", "" + response);
                        Gson gson = new Gson();
                        ThreeBlockModel threeBlockModel = gson.fromJson(response, ThreeBlockModel.class);
                        if (threeBlockModel.getCode() == 1) {
                            String leftpic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + threeBlockModel.getSetup().getImg();
                            Glide.with(getActivity()).load(leftpic).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(home_right_top_pic);
                        } else if (threeBlockModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (threeBlockModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void initLeftPicData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeLeftThreeBlock)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("三块的数据-左", "" + response);
                        Gson gson = new Gson();
                        ThreeBlockModel threeBlockModel = gson.fromJson(response, ThreeBlockModel.class);
                        if (threeBlockModel.getCode() == 1) {
                            String leftpic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + threeBlockModel.getSetup().getImg();
                            Glide.with(getActivity()).load(leftpic).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(home_left_pic);
                        } else if (threeBlockModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (threeBlockModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }


    // TODO ***************************************初始化8个专题***************************************
    private void initEightItemRV() {
        datasText = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            datasText.add(i, text[i]);
        }
        datasPic = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            datasPic.add(i, pic[i]);
        }
        eightRV = (RecyclerView) view.findViewById(R.id.rv_home_item);
        eightItemRecycleAdapter = new EightItemRecycleAdapter(datasPic, datasText);
        eightRV.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        eightRV.setAdapter(eightItemRecycleAdapter);
        eightItemRecycleAdapter.setOnItemClickListener(new EightItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LogUtil.e("点击了第几个" + position);
                JumpForPage(position);
            }
        });
    }

    private void JumpForPage(int position) {
        if (position == 0 || position == 1) {
//            startActivity(ExchangeAndRentAC.class, position);
            userinfo = mainAC.getUserInfo();
            if (userinfo!= null){
                Intent intent = new Intent(getActivity(),ExchangeAndRentAC.class);
                intent.putExtra("JUMPEIGHTITEMDETAIL",position);
                intent.putExtra("USERINFO",userinfo);
                startActivity(intent);
            }

        } else if (position == 2) {
            startActivity(EightItemDetailAC.class, position);
        } else if (position == 3) {
            startActivity(AppraisaAllAC.class, position);
        } else if (position == 4) {
            startActivity(CuringAC.class, position);
        }
    }
    // TODO ***************************************初始化热门专题***************************************
    private void initHotTopic() {
        hotcycleView = (RecyclerView) view.findViewById(R.id.home_hot_recycleView);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        hotcycleView.setLayoutManager(layoutManagers);
        hotAdapter = new HotTopicAdapter(hotDataSource, getActivity());
        hotAdapter.notifyDataSetChanged();
        hotcycleView.setAdapter(hotAdapter);
        hotAdapter.setOnItemClickListener(new HotTopicAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int topicid) {
                Intent intent = new Intent(getActivity(), HomeThreeBlockDetailAC.class);
                intent.putExtra("JUMPEIGHTITEMDETAIL", 16);
                intent.putExtra("TOPICID",topicid);
                startActivity(intent);
            }
        });
    }
    // TODO ***************************************初始化精选分类***************************************

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new RecycleAdapter(classDataSource, getActivity());
        myAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int classid) {
                initClassidData(classid);
            }
        });
    }
    private void initClassidData(final int classid) {
        JSONObject js  = new JSONObject();
        try {
            js.put("classid",classid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.HomeClassSelected)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("精选分类主图", "" + response);
                        Gson gson = new Gson();
                        HomeClassSelectedModel homeClassSelectedModel = gson.fromJson(response, HomeClassSelectedModel.class);
                        if (homeClassSelectedModel.getCode() == 1) {
                            Intent intent = new Intent(getActivity(), HomeThreeBlockDetailAC.class);
                            intent.putExtra("JUMPEIGHTITEMDETAIL", 14);
                            intent.putExtra("CLASSID",classid);
                            startActivity(intent);
                        } else if (homeClassSelectedModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (homeClassSelectedModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }


    private void startActivity(Class<?> cls, Integer jumpvalue) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra("JUMPEIGHTITEMDETAIL", jumpvalue);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
