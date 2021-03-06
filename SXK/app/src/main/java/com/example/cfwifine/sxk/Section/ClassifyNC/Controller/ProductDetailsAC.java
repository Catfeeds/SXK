package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ProducetDetailCommentRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.RecyclerBanner;
import com.example.cfwifine.sxk.Section.ClassifyNC.Dialog.CustomDialog_ShareBorad;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.FollowSuccessModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductCommentListModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductDetailModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.PurchasesDetailModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RongTokenModel;
import com.example.cfwifine.sxk.Section.LoginAC.Controller.LoginUseBoobeAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection.MineCollectionModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection.MinePurchaseListModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Model.FollowListModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;

public class ProductDetailsAC extends AppCompatActivity implements View.OnClickListener, RongIM.UserInfoProvider {

    private LinearLayout product_details_share;//产品分享
    private LinearLayout product_details_back;//返回
    private TextView now_num;//轮播图当前页
    private TextView allnum;//轮播图的数量
    private RecyclerBanner banner;//轮播
    private TextView product_name;//产品名
    private TextView product_style;//产品款式
    private TextView product_money_everyday;//每天的价格
    private TextView product_money;//市场价
    private LinearLayout bo_one;//啵一个
    private TextView username;//啵主昵称
    private LinearLayout follow;//关注
    private LinearLayout chat;//聊呗
    private TextView comment_number;//评价条数
    private LinearLayout more_comment;//更多评价
    private RecyclerView product_comment_list_rv;//评价列表
    private TextView product_content;//商品描述
    private ImageView product_details_brand_pic;//品牌图
    private TextView product_details_brand_content;//品牌介绍
    private Button lease_btn;//租呗
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();//轮播图图片集合
    private TextView product_category;//商品类别
    private TextView product_brand;//品牌
    private TextView product_color;//颜色
    private TextView product_condition;//成色
    private TextView product_intended_for_person;//适用人群
    private TextView product_enclosure;//附件
    private int RentID = -1;
    Dialog dialog;
    private ProductDetailModel.RentBean rentDetail = null;
    private CircleImageView headPic;
    private String crowds = "";
    private String RongToken;
    private String TAG;
    private int mineUserId = 0;
    private String PORITE = "";
    private String NICKNAME = "";
    private CustomDialog_ShareBorad customDialog_shareBorad;
    private List<ProductCommentListModel.BrandListBean> CommentDataSource = null;
    private TextView more;
    String appariseStr = "";
    private int PurchaseidID = -1;
    private LinearLayout comment_view;
    private int Types = -1;
    private String URLS = "";
    private PurchasesDetailModel.PurchaseBean purchaseDataSource = null;
    private TextView lowest_price;
    private UMWeb web;
    private FollowListModel.FollowBean followDataSource = null;
    private ImageView heart;
    private String RENTRESPONSE = "";
    private String PURCHASERESPONSE = "";
    private String CollectionUrls;
    private RelativeLayout btn;
    private boolean isFollow = false;
    private String COMURL;
    private ImageView kiss;
    private boolean isCollection = false;
    private String BASEURL;
    private String mPHPSESSION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_ac);
        RentID = getIntent().getIntExtra("RENTID", -1);
        PurchaseidID = getIntent().getIntExtra("PURCHASEID", -1);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        PORITE = (String) SharedPreferencesUtils.getParam(ProductDetailsAC.this, BaseInterface.PORITA, "");
        NICKNAME = (String) SharedPreferencesUtils.getParam(ProductDetailsAC.this, BaseInterface.NICKNAME, "");
        mineUserId = (int) SharedPreferencesUtils.getParam(ProductDetailsAC.this, BaseInterface.USERID, 0);
        mPHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        initView();
        // 产品详情页面分享链接 http://shexiangke.jcq.tbapps.cn/wechat/userpage/getrent/rentid/136

    }

    private void initCollectionData() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Types == 1) {
            COMURL = BaseInterface.CollectionList;
        } else if (Types == 2) {
            COMURL = BaseInterface.PurchaseCollectionList;
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
                        if (Types == 1) {
                            Log.e("收藏列表", "" + response);
                            MineCollectionModel mineCollectionModel = gson.fromJson(response, MineCollectionModel.class);
                            if (mineCollectionModel.getCode() == 1) {
                                if (mineCollectionModel.getCollection().getList().size() != 0) {
                                    for (int i = 0; i < mineCollectionModel.getCollection().getList().size(); i++) {
                                        if (mineCollectionModel.getCollection().getList().get(i) == rentDetail.getRentid()) {
                                            kiss.setImageResource(R.drawable.zuixingc);
                                            isCollection = true;
                                        }
                                    }
                                }
                            } else if (mineCollectionModel.getCode() == 0) {
                            } else if (mineCollectionModel.getCode() == 911) {
                            }
                        } else if (Types == 2) {
                            LogUtil.e("寄卖收藏列表" + response);
                            MinePurchaseListModel minePurchaseListModel = gson.fromJson(response, MinePurchaseListModel.class);
                            if (minePurchaseListModel.getCode() == 1) {
                                if (minePurchaseListModel.getCollection().getList().size() != 0) {
                                    for (int i = 0; i < minePurchaseListModel.getCollection().getList().size(); i++) {
                                        if (minePurchaseListModel.getCollection().getList().get(i) == purchaseDataSource.getPurchaseid()) {
                                            kiss.setImageResource(R.drawable.zuixingc);
                                            isCollection = true;
                                        }
                                    }
                                }
                            } else if (minePurchaseListModel.getCode() == 0) {
                            } else if (minePurchaseListModel.getCode() == 911) {
                            }
                        }
                    }
                });


    }

    private void initMyFollow() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.FollowList)
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
                        Log.e("关注列表", "" + response);
                        Gson gson = new Gson();
                        FollowListModel followListModel = gson.fromJson(response, FollowListModel.class);
                        if (followListModel.getCode() == 1) {
                            followDataSource = followListModel.getFollow();
                            if (followDataSource.getList().size() != 0) {
                                for (int i = 0; i < followDataSource.getList().size(); i++) {
                                    if (Types == 1) {
                                        if (followDataSource.getList().get(i) == rentDetail.getUser().getUserid()) {
                                            heart.setImageResource(R.drawable.chanpingxiangqingxin);
                                            isFollow = true;
                                        }
                                    } else {
                                        if (followDataSource.getList().get(i) == purchaseDataSource.getUser().getUserid()) {
                                            heart.setImageResource(R.drawable.chanpingxiangqingxin);
                                            isFollow = true;
                                        }
                                    }
                                }
                            }
                        } else if (followListModel.getCode() == 0) {
//                            initSnackBar("请求失败！");
                        } else if (followListModel.getCode() == 911) {
//                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    // 初始化商品详情
    private void initDetailData() {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            if (Types == 1) {
                jsonObject.put("rentid", RentID);
                URLS = BaseInterface.GetRentDetail;
            } else {
                jsonObject.put("purchaseid", PurchaseidID);
                URLS = BaseInterface.PurchaseGetDetail;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(ProductDetailsAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(URLS)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错!");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        initMyFollow();
                        initCollectionData();
                        Gson gson = new Gson();
                        if (Types == 1) {
                            Log.e("获取租赁", "" + response);
                            ProductDetailModel productDetailModel = gson.fromJson(response, ProductDetailModel.class);
                            if (productDetailModel.getCode() == 1) {
//                                SharedPreferencesUtils.setParam(ProductDetailsAC.this, "RENTDETAILMODEL", response.toString());
                                RENTRESPONSE = response;
                                rentDetail = productDetailModel.getRent();
                                setValueForView();
                                // 初始化评论列表
                                initCommentDataSource();
                            } else if (productDetailModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (productDetailModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        } else {
                            LogUtil.e("获取寄卖详情" + response);
                            PurchasesDetailModel purchasesDetailModel = gson.fromJson(response, PurchasesDetailModel.class);
                            if (purchasesDetailModel.getCode() == 1) {
                                PURCHASERESPONSE = response;
                                purchaseDataSource = purchasesDetailModel.getPurchase();
                                setValueForPurchaseView();
                            } else if (purchasesDetailModel.getCode() == 0) {
                                initSnackBar("请求失败！");
                            } else if (purchasesDetailModel.getCode() == 911) {
                                initSnackBar("登录超时，请重新登录！");
                            }
                        }

                    }
                });
    }


    private void initView() {
        product_details_share = (LinearLayout) findViewById(R.id.product_details_share);
        product_details_back = (LinearLayout) findViewById(R.id.product_details_back);
        now_num = (TextView) findViewById(R.id.now_num);
        allnum = (TextView) findViewById(R.id.allnum);
        banner = (RecyclerBanner) findViewById(R.id.banner);
        product_name = (TextView) findViewById(R.id.product_name);
        product_style = (TextView) findViewById(R.id.product_style);
        product_money_everyday = (TextView) findViewById(R.id.product_money_everyday);
        product_money = (TextView) findViewById(R.id.product_money);
        bo_one = (LinearLayout) findViewById(R.id.bo_one);
        username = (TextView) findViewById(R.id.username);
        follow = (LinearLayout) findViewById(R.id.follow);
        chat = (LinearLayout) findViewById(R.id.chat);
        comment_number = (TextView) findViewById(R.id.comment_number);
        more_comment = (LinearLayout) findViewById(R.id.more_comment);
        product_comment_list_rv = (RecyclerView) findViewById(R.id.product_comment_list_rv);
        product_content = (TextView) findViewById(R.id.product_content);
        product_details_brand_pic = (ImageView) findViewById(R.id.product_details_brand_pic);
        product_details_brand_content = (TextView) findViewById(R.id.product_details_brand_content);
        lease_btn = (Button) findViewById(R.id.lease_btn);

        bo_one.setOnClickListener(this);
        follow.setOnClickListener(this);
        lease_btn.setOnClickListener(this);
        more = (TextView) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rentDetail != null) {
                    Intent intent = new Intent(ProductDetailsAC.this, CommentDetailAC.class);
                    intent.putExtra("rentid", rentDetail.getRentid());
                    startActivity(intent);
                }
            }
        });

        chat.setOnClickListener(this);
        more_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommentDataSource.size() == 0) {
                    initSnackBar("暂时没有评价！");

                } else {
                    // 评论详情页面
                    if (rentDetail != null) {
                        Intent intent = new Intent(ProductDetailsAC.this, CommentDetailAC.class);
                        intent.putExtra("rentid", rentDetail.getRentid());
                        startActivity(intent);
                    }

                }
            }
        });
        comment_view = (LinearLayout) findViewById(R.id.comment_view);
        if (PurchaseidID != -1) {
            comment_view.setVisibility(View.GONE);
        }
        product_details_share.setOnClickListener(this);
        product_details_back.setOnClickListener(this);
        product_category = (TextView) findViewById(R.id.product_category);
        product_brand = (TextView) findViewById(R.id.product_brand);
        product_color = (TextView) findViewById(R.id.product_color);
        product_condition = (TextView) findViewById(R.id.product_condition);
        product_intended_for_person = (TextView) findViewById(R.id.product_intended_for_person);
        product_enclosure = (TextView) findViewById(R.id.product_enclosure);
        headPic = (CircleImageView) findViewById(R.id.headPic);
        headPic.setOnClickListener(this);
        lowest_price = (TextView) findViewById(R.id.lowest_price);
        heart = (ImageView) findViewById(R.id.heart);
        btn = (RelativeLayout) findViewById(R.id.btn);
        kiss = (ImageView) findViewById(R.id.kiss);
        // Types = 1 租赁  Types = 2 寄卖
        if (RentID != -1) {
            Types = 1;
        } else {
            Types = 2;
        }
        initDetailData();


    }

    private void initCommentDataSource() {
        JSONObject commentid = new JSONObject();
        try {
            commentid.put("commentid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", commentid);
            js.put("rentid", rentDetail.getRentid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ProductDetailCommentList)
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
                        Log.e("商品评价列表", "" + response);
                        Gson gson = new Gson();
                        ProductCommentListModel productCommentListModel = gson.fromJson(response, ProductCommentListModel.class);
                        if (productCommentListModel.getCode() == 1) {
                            CommentDataSource = productCommentListModel.getBrandList();
                            if (CommentDataSource.size() != 0) {
                                int number = productCommentListModel.getTotal();
                                comment_number.setText(String.valueOf(number));
                                more.setText("更多评论");
                                initCommentRecycyleView();
                            } else {
                                more.setVisibility(View.GONE);
                                comment_number.setText("0");
                                product_comment_list_rv.setVisibility(View.GONE);

                            }

                        } else if (productCommentListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (productCommentListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initCommentRecycyleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ProducetDetailCommentRecycleViewAdapter producetDetailCommentRecycleViewAdapter = new ProducetDetailCommentRecycleViewAdapter(this, CommentDataSource, 1);
        product_comment_list_rv.setLayoutManager(linearLayoutManager);
        product_comment_list_rv.setAdapter(producetDetailCommentRecycleViewAdapter);
    }

    private void setValueForView() {
        if (mineUserId == rentDetail.getUser().getUserid()) {
            bo_one.setVisibility(View.GONE);
            follow.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
            chat.setVisibility(View.GONE);
        }
        initBannerData();
        product_name.setText(rentDetail.getName().toString());
        int condition = rentDetail.getCondition();
        String conditions = "";
        switch (condition) {
            case 1:
                conditions = "99成新（未使用）";
                break;
            case 2:
                conditions = "98成新";
                break;
            case 3:
                conditions = "95成新";
                break;
            case 4:
                conditions = "9成新";
                break;
            case 5:
                conditions = "85成新";
                break;
            case 6:
                conditions = "8成新";
                break;
            default:
                break;
        }
        int crowd = rentDetail.getCrowd();
        switch (crowd) {
            case 1:
                crowds = "所有人";
                break;
            case 2:
                crowds = "男士";
                break;
            case 3:
                crowds = "女士";
                break;
        }
        product_style.setText(conditions + "   " + rentDetail.getKeyword());
        double ds = rentDetail.getRentPrice();
        product_money_everyday.setText(" ¥ " + String.format("%.2f", ds / 100) + " /天");
        product_money_everyday.setTextSize(14);
        double dd = rentDetail.getMarketPrice();
        product_money.setText("市场价：¥ " + String.format("%.2f", dd / 100));
        String userNames = rentDetail.getUser().getNickname();
        if (userNames.toString().trim().length() >= 5) {
            username.setText(userNames.substring(0, 5) + "...");
        } else {
            username.setText(userNames);
        }
        String picUrl = rentDetail.getUser().getHeadimgurl();
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(headPic);
        product_content.setText(rentDetail.getDescription());
        product_category.setText(rentDetail.getCategory().getName());//类别
        product_brand.setText(rentDetail.getBrand().getName());//品牌
        product_color.setText(rentDetail.getColor());//颜色
        product_condition.setText(conditions);//成色
        product_intended_for_person.setText(crowds);//适用人群

        for (int i = 0; i < rentDetail.getAttachList().size(); i++) {
            if (rentDetail.getAttachList().get(i).getAttributeName() != null) {
                if (rentDetail.getAttachList().get(i).getAttributeName().toString().trim().equals("相关配件")) {
                    for (int j = 0; j < rentDetail.getAttachList().get(i).getAttributeValueList().size(); j++) {
                        appariseStr += rentDetail.getAttachList().get(i).getAttributeValueList().get(j) + " ";
                    }
                }
            }

        }
        if (appariseStr.trim() == "") {
            product_enclosure.setText("无");
        } else {
            product_enclosure.setText(appariseStr);
        }
        String brandStory = BaseInterface.ClassfiyGetAllHotBrandImgUrl + rentDetail.getBrand().getStory();
        Glide.with(this).load(brandStory).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(product_details_brand_pic);
        product_details_brand_content.setText(rentDetail.getBrand().getDescription().toString());
    }

    private void setValueForPurchaseView() {
        if (mineUserId == purchaseDataSource.getUser().getUserid()) {
            bo_one.setVisibility(View.GONE);
            follow.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
            chat.setVisibility(View.GONE);
        }
        lease_btn.setText("立即购买");
        initBannerData();
        product_name.setText(purchaseDataSource.getName().toString());
        int condition = purchaseDataSource.getCondition();
        String conditions = "";
        switch (condition) {
            case 1:
                conditions = "99成新（未使用）";
                break;
            case 2:
                conditions = "98成新";
                break;
            case 3:
                conditions = "95成新";
                break;
            case 4:
                conditions = "9成新";
                break;
            case 5:
                conditions = "85成新";
                break;
            case 6:
                conditions = "8成新";
                break;
            default:
                break;
        }
        int crowd = purchaseDataSource.getCrowd();
        switch (crowd) {
            case 1:
                crowds = "所有人";
                break;
            case 2:
                crowds = "男士";
                break;
            case 3:
                crowds = "女士";
                break;
        }
        product_style.setText(conditions);
        double dd = purchaseDataSource.getMarketPrice();
        lowest_price.setText("市场价：");
        product_money_everyday.setText(" ¥ " + String.format("%.2f", dd / 100));
        product_money_everyday.setTextSize(14);
        double solePrice = purchaseDataSource.getSellingPrice();
        product_money.setText("售价：¥ " + String.format("%.2f", solePrice / 100));
        username.setText(purchaseDataSource.getUser().getNickname());
        String picUrl = purchaseDataSource.getUser().getHeadimgurl();
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(headPic);
        product_content.setText(purchaseDataSource.getDescription());
        product_category.setText(purchaseDataSource.getCategory().getName());//类别
        product_brand.setText(purchaseDataSource.getBrand().getName());//品牌
        product_color.setText(purchaseDataSource.getColor());//颜色
        product_condition.setText(conditions);//成色
        product_intended_for_person.setText(crowds);//适用人群

        for (int i = 0; i < purchaseDataSource.getAttachList().size(); i++) {
            if (purchaseDataSource.getAttachList().get(i).getAttributeName().toString().trim().equals("相关配件")) {
                for (int j = 0; j < purchaseDataSource.getAttachList().get(i).getAttributeValueList().size(); j++) {
                    appariseStr += purchaseDataSource.getAttachList().get(i).getAttributeValueList().get(j) + " ";
                }
            }
        }
        if (appariseStr.trim() == "") {
            product_enclosure.setText("无");
        } else {
            product_enclosure.setText(appariseStr);
        }
        String brandStory = BaseInterface.ClassfiyGetAllHotBrandImgUrl + purchaseDataSource.getBrand().getStory();
        Glide.with(this).load(brandStory).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(product_details_brand_pic);
        product_details_brand_content.setText(purchaseDataSource.getBrand().getDescription().toString());
    }


    @Override
    public UserInfo getUserInfo(String s) {
        return null;
    }


    // TODO ***************************************初始化轮播图**************************************

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }

    private void initBannerData() {
        if (Types == 1) {
            for (int i = 0; i < rentDetail.getImgList().size(); i++) {
                urls.add(new Entity(BaseInterface.ClassfiyGetAllHotBrandImgUrl + rentDetail.getImgList().get(i)));
            }
        } else {
            for (int i = 0; i < purchaseDataSource.getImgList().size(); i++) {
                urls.add(new Entity(BaseInterface.ClassfiyGetAllHotBrandImgUrl + purchaseDataSource.getImgList().get(i)));
            }
        }
        banner.setDatas(urls, now_num);
    }

    // TODO ***************************************商品评价初始化**************************************
    private void initProductComment() {
        product_comment_list_rv.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(ProductDetailsAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }


    // TODO ***************************************点击监听方法**************************************
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_details_back:
                finish();
                break;
            case R.id.product_details_share:
                // 分享到第三方
                customDialog_shareBorad = new CustomDialog_ShareBorad(this, new CustomDialog_ShareBorad.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(int type) {
                        LogUtil.e("输出的类型为" + type);
                        shareToThreePart(type);
                    }
                }, R.style.style_dialog);
                customDialog_shareBorad.show();

                break;
            case R.id.lease_btn:

                if (TextUtils.isEmpty(mPHPSESSION)) {
//                    Intent tss = new Intent(ProductDetailsAC.this, LoginUseBoobeAC.class);
//                    startActivity(tss);
                    initSnackBar("请先登录！");
                } else {
                    if (Types == 1 && RENTRESPONSE != "") {
                        SharedPreferencesUtils.setParam(this, BaseInterface.NICKNAMES, rentDetail.getUser().getNickname());
                        SharedPreferencesUtils.setParam(this, BaseInterface.PORITAS, rentDetail.getUser().getHeadimgurl());
                        Intent intent = new Intent(ProductDetailsAC.this, PayOrderAC.class);
                        intent.putExtra("RENTRESPONSE", RENTRESPONSE);
                        startActivity(intent);
                    } else if (Types == 2 && PURCHASERESPONSE != "") {
                        SharedPreferencesUtils.setParam(this, BaseInterface.NICKNAMES, purchaseDataSource.getUser().getNickname());
                        SharedPreferencesUtils.setParam(this, BaseInterface.PORITAS, purchaseDataSource.getUser().getHeadimgurl());
                        Intent intent = new Intent(ProductDetailsAC.this, PurchasePayOrderAC.class);
                        intent.putExtra("PURCHASERESPONSE", PURCHASERESPONSE);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.bo_one:
                if (isCollection) {
                    initCancelCollection();
                } else {
                    initCollection();
                }
                break;
            case R.id.follow:
                if (isFollow) {
                    initCancelFollow();
                } else {
                    initFollow();
                }
                break;
            case R.id.chat:
                initRongData();
                break;
            case R.id.more_comment:
//                Toast.makeText(this, "更多评价", Toast.LENGTH_SHORT).show();
                break;


        }
    }


    private void shareToThreePart(int type) {
        SHARE_MEDIA SHARE_TYPE = null;
        switch (type) {
            case 1:
                SHARE_TYPE = SHARE_MEDIA.WEIXIN;
                break;
            case 2:
                SHARE_TYPE = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case 3:
                SHARE_TYPE = SHARE_MEDIA.SINA;
                break;
            case 4:
                SHARE_TYPE = SHARE_MEDIA.QQ;
                break;
        }
        LogUtil.e("输出类型为" + type);
        if (Types == 1) {
            UMImage image = new UMImage(ProductDetailsAC.this, BaseInterface.ClassfiyGetAllHotBrandImgUrl + rentDetail.getImgList().get(0));
            UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
            image.setThumb(thumb);
            image.compressFormat = Bitmap.CompressFormat.PNG;

            web = new UMWeb("http://shexiangke.jcq.tbapps.cn/wechat/userpage/getrent/rentid/" + rentDetail.getRentid());
            web.setTitle(rentDetail.getName().toString());//标题
            web.setThumb(image);  //缩略图
            web.setDescription(rentDetail.getKeyword().toString());//描述
        } else {
            UMImage image = new UMImage(ProductDetailsAC.this, BaseInterface.ClassfiyGetAllHotBrandImgUrl + purchaseDataSource.getImgList().get(0));
            UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
            image.setThumb(thumb);
            image.compressFormat = Bitmap.CompressFormat.PNG;

            web = new UMWeb("http://shexiangke.jcq.tbapps.cn/wechat/userpage/getrent/rentid/" + purchaseDataSource.getPurchaseid());
            web.setTitle(purchaseDataSource.getName().toString());//标题
            web.setThumb(image);  //缩略图
            web.setDescription(purchaseDataSource.getDescription().toString());//描述
        }


        new ShareAction(ProductDetailsAC.this)
                .setPlatform(SHARE_TYPE)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
//        UmengTool.getSignature(ProductDetailsAC.this);
//        UmengTool.getREDICRECT_URL(ProductDetailsAC.this);

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享成功啦！");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享失败！");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享已取消！");
        }
    };


    // 初始化聊天
    private void initRongData() {

        LogUtil.e("我的ID" + mineUserId);
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("userid", mineUserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
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
                LogUtil.e("返回的userID1" + userId);
//                LogUtil.e("返回的userID" + rentDetail.getUser().getUserid());
//                LogUtil.e("返回的userID" + rentDetail.getUser().getUserid());
                if (RongIM.getInstance() != null) {
                    UserInfo userInfo = new UserInfo(String.valueOf(mineUserId), NICKNAME, Uri.parse(PORITE));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String userId) {
                            if (Types == 1) {
                                return new UserInfo(String.valueOf(rentDetail.getUser().getUserid()), rentDetail.getUser().getNickname(), Uri.parse(rentDetail.getUser().getHeadimgurl()));
                            } else {
                                return new UserInfo(String.valueOf(purchaseDataSource.getUser().getUserid()), purchaseDataSource.getUser().getNickname(), Uri.parse(purchaseDataSource.getUser().getHeadimgurl()));
                            }

                        }
                    }, true);
                    if (Types == 1) {
                        RongIM.getInstance().startConversation(ProductDetailsAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(rentDetail.getUser().getUserid()), rentDetail.getUser().getNickname());
                    } else {
                        RongIM.getInstance().startConversation(ProductDetailsAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(purchaseDataSource.getUser().getUserid()), purchaseDataSource.getUser().getNickname());
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(ProductDetailsAC.this, "连接服务器失败！请稍后重试！", Toast.LENGTH_SHORT).show();
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

    private void initCancelCollection() {
        JSONObject js = new JSONObject();
        if (Types == 1) {
            try {
                js.put("rentid", rentDetail.getRentid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BASEURL = BaseInterface.CollectionDel;
        } else if (Types == 2) {
            try {
                js.put("purchaseid", purchaseDataSource.getPurchaseid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BASEURL = BaseInterface.PurchaseDelCollection;
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
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            kiss.setImageResource(R.drawable.mouse_uncollection);
                            isCollection = false;
                            initSnackBar("取消收藏成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("取消收藏失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });

    }

    // 收藏商品
    private void initCollection() {
        JSONObject JS = new JSONObject();
        try {
            if (Types == 1) {
                JS.put("rentid", rentDetail.getRentid());
                CollectionUrls = BaseInterface.CollectionADD;
            } else {
                JS.put("purchaseid", purchaseDataSource.getPurchaseid());
                CollectionUrls = BaseInterface.PurchaseAddCollection;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(CollectionUrls)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(JS.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("收藏商品", "" + response);
                        Gson gson = new Gson();
                        FollowSuccessModel requestStatueModel = gson.fromJson(response, FollowSuccessModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            kiss.setImageResource(R.drawable.zuixingc);
                            isCollection = true;
                            initSnackBar("收藏成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        } else if (requestStatueModel.getCode() == 2003) {
                            initSnackBar(requestStatueModel.getMessage());
                        }
                    }
                });

    }

    private void initCancelFollow() {
        JSONObject js = new JSONObject();
        try {
            if (Types == 1) {
                js.put("userid", rentDetail.getUser().getUserid());
            } else {
                js.put("userid", purchaseDataSource.getUser().getUserid());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.FollowDel)
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
                        Log.e("", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            heart.setImageResource(R.drawable.guanzhu);
                            isFollow = false;
                            initSnackBar("取消关注成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("取消关注失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void initFollow() {
        JSONObject js = new JSONObject();
        try {
            if (Types == 1) {
                js.put("userid", rentDetail.getUser().getUserid());
            } else {
                js.put("userid", purchaseDataSource.getUser().getUserid());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.FollowAdd)
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
                        Log.e("关注", "" + response);
                        Gson gson = new Gson();
                        FollowSuccessModel requestStatueModel = gson.fromJson(response, FollowSuccessModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            heart.setImageResource(R.drawable.chanpingxiangqingxin);
                            isFollow = true;
                            initSnackBar("关注成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        } else if (requestStatueModel.getCode() == 2003) {
                            initSnackBar(requestStatueModel.getMessage());
                        }
                    }
                });


    }
}
