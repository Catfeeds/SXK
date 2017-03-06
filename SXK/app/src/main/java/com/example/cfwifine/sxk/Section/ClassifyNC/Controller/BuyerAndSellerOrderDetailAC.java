package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.BuyerAndSellerOrderDetailModel;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_and_seller_order_detail_ac);
        orderID = getIntent().getIntExtra("orderid", -1);
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
        initOrderDetail();


    }

    private void initOrderDetail() {
        JSONObject js = new JSONObject();
        try {
            js.put("orderid", orderID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.BuyerAndSellerOrderDetail)
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
                        buyerAndSellerOrderDetailModel = gson.fromJson(response, BuyerAndSellerOrderDetailModel.class);
                        if (buyerAndSellerOrderDetailModel.getCode() == 1) {
                            setValueForDetail();
                        } else if (buyerAndSellerOrderDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (buyerAndSellerOrderDetailModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }

    private void setValueForDetail() {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + buyerAndSellerOrderDetailModel.getOrder().getRent().getImg();
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
        if (buyerAndSellerOrderDetailModel.getOrder().getIsRisk() == 1){
            double yunxian = buyerAndSellerOrderDetailModel.getOrder().getRent().getRisk();
            freight_insurance.setText(String.format("%.2f", yunxian / 100));
        }else {
            freight_insurance.setText("0.00");
        }
        double yajin = buyerAndSellerOrderDetailModel.getOrder().getRent().getMarketPrice();
        order_deposit.setText(String.format("%.2f", yajin / 100));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lease_btn:

                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
