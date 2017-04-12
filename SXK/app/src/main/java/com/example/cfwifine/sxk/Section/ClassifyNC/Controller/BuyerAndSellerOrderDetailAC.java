package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.BuyerAndSellerOrderDetailModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.PurchaseOrderDetailModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RongTokenModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;

public class BuyerAndSellerOrderDetailAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private TextView product_address;
    private LinearLayout select_address;
    private ImageView product_img;
    private TextView product_name;
    private TextView product_keyword;
    private TextView product_lowest_price;
    private TextView product_money_everyday;
    private TextView product_money;
    private TextView order_rent;
    private TextView order_deposit;
    private TextView freight_insurance;
    private Button lease_btn;
    private RelativeLayout activity_buyer_and_seller_order_detail_ac;
    private int orderID = -1;
    private BuyerAndSellerOrderDetailModel buyerAndSellerOrderDetailModel = null;
    private TextView rent_day;
    private int mineUserId = -1;
    Dialog dialog;
    private String RongToken;
    private String NICKNAME = "";
    private String PORITE = "";
    private int TYPES = -1;
    private String URLS = "";
    private PurchaseOrderDetailModel purchaseOrderDetailModel = null;
    private TextView market_price_txt;
    private LinearLayout rent_time;
    private RelativeLayout rent_view;
    private RelativeLayout rent_views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_and_seller_order_detail_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        orderID = getIntent().getIntExtra("orderid", -1);
        // 1 寄租 2 寄卖 3 租赁中详情
        TYPES = getIntent().getIntExtra("type", -1);
        NICKNAME = (String) SharedPreferencesUtils.getParam(BuyerAndSellerOrderDetailAC.this, BaseInterface.NICKNAME, "");
        PORITE = (String) SharedPreferencesUtils.getParam(BuyerAndSellerOrderDetailAC.this, BaseInterface.PORITA, "");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("订单详情");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        product_address = (TextView) findViewById(R.id.product_address);
        select_address = (LinearLayout) findViewById(R.id.select_address);
        product_img = (ImageView) findViewById(R.id.product_img);
        product_name = (TextView) findViewById(R.id.product_name);
        product_keyword = (TextView) findViewById(R.id.product_keyword);
        product_lowest_price = (TextView) findViewById(R.id.product_lowest_price);
        product_money_everyday = (TextView) findViewById(R.id.product_money_everyday);
        product_money = (TextView) findViewById(R.id.product_money);
        order_rent = (TextView) findViewById(R.id.order_rent);
        order_deposit = (TextView) findViewById(R.id.order_deposit);
        freight_insurance = (TextView) findViewById(R.id.freight_insurance);
        lease_btn = (Button) findViewById(R.id.lease_btn);
        activity_buyer_and_seller_order_detail_ac = (RelativeLayout) findViewById(R.id.activity_buyer_and_seller_order_detail_ac);
        lease_btn.setOnClickListener(this);
        rent_day = (TextView) findViewById(R.id.rent_day);
        rent_day.setOnClickListener(this);
        market_price_txt = (TextView) findViewById(R.id.market_price_txt);
        rent_time = (LinearLayout) findViewById(R.id.rent_time);
        rent_view = (RelativeLayout) findViewById(R.id.rent_view);
        rent_views = (RelativeLayout) findViewById(R.id.rent_views);
        if (TYPES == 1){
            lease_btn.setText("联系对方");
        }
        initOrderDetail();


    }

    private void initOrderDetail() {
        JSONObject js = new JSONObject();
        try {
            js.put("orderid", orderID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (TYPES == 1) {
            URLS = BaseInterface.BuyerAndSellerOrderDetail;
        } else if (TYPES == 2) {
            URLS = BaseInterface.PurchaseOrderDetail;
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(URLS)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("生成订单详情", "" + response);
                        Gson gson = new Gson();
                        if (TYPES == 1) {
                            buyerAndSellerOrderDetailModel = gson.fromJson(response, BuyerAndSellerOrderDetailModel.class);
                            if (buyerAndSellerOrderDetailModel.getCode() == 1) {
                                setValueForDetail();
                            } else if (buyerAndSellerOrderDetailModel.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (buyerAndSellerOrderDetailModel.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        } else if (TYPES == 2) {
                            LogUtil.e("寄卖订单相亲会" + response);
                            purchaseOrderDetailModel = gson.fromJson(response, PurchaseOrderDetailModel.class);
                            if (purchaseOrderDetailModel.getCode() == 1) {
                                setValueForPurchaseView();
                            } else if (purchaseOrderDetailModel.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (purchaseOrderDetailModel.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        }


                    }
                });


    }

    private void setValueForPurchaseView() {
        rent_time.setVisibility(View.GONE);
        rent_view.setVisibility(View.GONE);
        rent_views.setVisibility(View.GONE);
        market_price_txt.setText("售价：");
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + purchaseOrderDetailModel.getOrder().getPurchase().getImgList().get(0);
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(product_img);
        product_address.setText("收货人：" + purchaseOrderDetailModel.getOrder().getReceiver().getName() + purchaseOrderDetailModel.getOrder().getReceiver().getMobile() + "\n" + "收货地址：" + purchaseOrderDetailModel.getOrder().getReceiver().getState() + purchaseOrderDetailModel.getOrder().getReceiver().getCity() + purchaseOrderDetailModel.getOrder().getReceiver().getDistrict() + purchaseOrderDetailModel.getOrder().getReceiver().getAddress());
        product_name.setText(purchaseOrderDetailModel.getOrder().getPurchase().getName());
        if (purchaseOrderDetailModel.getOrder().getPurchase().getDescription().trim().length() > 15) {
            product_keyword.setText(purchaseOrderDetailModel.getOrder().getPurchase().getDescription().substring(0, 10) + "...");
        } else {
            product_keyword.setText(purchaseOrderDetailModel.getOrder().getPurchase().getDescription());
        }
        double dd = purchaseOrderDetailModel.getOrder().getPurchase().getSellingPrice();
        product_lowest_price.setText("￥" + String.format("%.2f", dd / 100));
        double ds = purchaseOrderDetailModel.getOrder().getPurchase().getMarketPrice();
        product_money.setText("市场价：￥" + String.format("%.2f", ds / 100));
//        rent_day.setText(purchaseOrderDetailModel.getOrder().getPurchase().getRisk());
//        double rentMoney = purchaseOrderDetailModel.getOrder().getPurchase().getSellingPrice();
//        order_rent.setText("售价：" + String.format("%.2f", rentMoney / 100));
        if (purchaseOrderDetailModel.getOrder().getIsRisk() == 1) {
            double yunxian = purchaseOrderDetailModel.getOrder().getPurchase().getRisk();
            freight_insurance.setText(String.format("%.2f", yunxian / 100));
        } else {
            freight_insurance.setText("0.00");
        }
//        double yajin = purchaseOrderDetailModel.getOrder().getRent().getMarketPrice();
//        order_deposit.setText(String.format("%.2f", yajin / 100));
    }

    private void setValueForDetail() {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + buyerAndSellerOrderDetailModel.getOrder().getRent().getImgList().get(0);
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(product_img);
        product_address.setText("收货人：" + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getName() + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getMobile() + "\n" + "收货地址：" + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getState() + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getCity() + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getDistrict() + buyerAndSellerOrderDetailModel.getOrder().getReceiver().getAddress());
        product_name.setText(buyerAndSellerOrderDetailModel.getOrder().getRent().getName());
        product_keyword.setText(buyerAndSellerOrderDetailModel.getOrder().getRent().getKeyword());
        double dd = buyerAndSellerOrderDetailModel.getOrder().getRent().getRentPrice();
        product_lowest_price.setText("￥" + String.format("%.2f", dd / 100));
        double ds = buyerAndSellerOrderDetailModel.getOrder().getRent().getMarketPrice();
        product_money.setText("市场价：￥" + String.format("%.2f", ds / 100));
        rent_day.setText(buyerAndSellerOrderDetailModel.getOrder().getTenancy().getName());
        double rentMoney = buyerAndSellerOrderDetailModel.getOrder().getTenancy().getValue();
        order_rent.setText(String.format("%.2f", rentMoney / 100));
        if (buyerAndSellerOrderDetailModel.getOrder().getIsRisk() == 1) {
            double yunxian = buyerAndSellerOrderDetailModel.getOrder().getRent().getRisk();
            freight_insurance.setText(String.format("%.2f", yunxian / 100));
        } else {
            freight_insurance.setText("0.00");
        }
        double yajin = buyerAndSellerOrderDetailModel.getOrder().getRent().getMarketPrice();
        order_deposit.setText(String.format("%.2f", yajin / 100));
    }

    // 初始化聊天
    private void initRongData() {
        mineUserId = (int) SharedPreferencesUtils.getParam(BuyerAndSellerOrderDetailAC.this, BaseInterface.USERID, 0);
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

        final String nickName = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.NICKNAMES, ""));
        final String headUrl = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PORITAS, ""));


        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                LogUtil.e("返回的userID1" + userId);
                if (RongIM.getInstance() != null) {
                    UserInfo userInfo = new UserInfo(String.valueOf(mineUserId), NICKNAME, Uri.parse(PORITE));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String userId) {
                            if (TYPES == 1) {
                                return new UserInfo(String.valueOf(buyerAndSellerOrderDetailModel.getOrder().getRent().getUserid()), nickName, Uri.parse(headUrl));
                            } else {
                                return new UserInfo(String.valueOf(purchaseOrderDetailModel.getOrder().getSellerid()), nickName, Uri.parse(headUrl));
                            }
                        }
                    }, true);
                    if (TYPES == 1) {
                        RongIM.getInstance().startConversation(BuyerAndSellerOrderDetailAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(buyerAndSellerOrderDetailModel.getOrder().getRent().getUserid()), nickName);
                    } else {
                        RongIM.getInstance().startConversation(BuyerAndSellerOrderDetailAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(purchaseOrderDetailModel.getOrder().getSellerid()), nickName);
                    }

                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(BuyerAndSellerOrderDetailAC.this, "连接服务器失败！请稍后重试！", Toast.LENGTH_SHORT).show();
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


    public void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(BuyerAndSellerOrderDetailAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lease_btn:
                initRongData();
                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
