package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.RecyclerBanner;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout product_details_share;//产品分享
    private LinearLayout product_details_back;//返回
    private TextView now_num;//轮播图当前页
    private TextView allnum;//轮播图的数量
    private RecyclerBanner banner;//轮播
    private TextView product_name;//产品名
    private TextView product_style;//产品款式
    private TextView product_money_everyday;//每天的价格
    private TextView product_money;//市场价
    private ImageView bo_one;//啵一个
    private TextView username;//啵主昵称
    private ImageView follow;//关注
    private ImageView chat;//聊呗
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_ac);
        initView();
        initBannerData();//初始化轮播图
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
        bo_one = (ImageView) findViewById(R.id.bo_one);
        username = (TextView) findViewById(R.id.username);
        follow = (ImageView) findViewById(R.id.follow);
        chat = (ImageView) findViewById(R.id.chat);
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
        chat.setOnClickListener(this);
        more_comment.setOnClickListener(this);
        product_details_share.setOnClickListener(this);
        product_details_back.setOnClickListener(this);
        product_category = (TextView) findViewById(R.id.product_category);
        product_brand = (TextView) findViewById(R.id.product_brand);
        product_color = (TextView) findViewById(R.id.product_color);
        product_condition = (TextView) findViewById(R.id.product_condition);
        product_intended_for_person = (TextView) findViewById(R.id.product_intended_for_person);
        product_enclosure = (TextView) findViewById(R.id.product_enclosure);
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
        urls.add(new Entity("https://img.alicdn.com/imgextra/i3/2286172057/TB2ZR2NsFXXXXXaXFXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i4/2286172057/TB2tinRsFXXXXcAXpXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i1/2286172057/TB28SCjemiJ.eBjSszfXXa4bVXa_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i1/2286172057/TB2ghDdjFXXXXahXXXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i3/2286172057/TB20_98jFXXXXcrXXXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i4/2286172057/TB2QCm5jFXXXXcVXXXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i4/2286172057/TB2cmjhjFXXXXXTXXXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i3/2286172057/TB2VXvfjFXXXXaJXXXXXXXXXXXX_!!2286172057.jpg"));
        urls.add(new Entity("https://img.alicdn.com/imgextra/i2/2286172057/TB2cezljFXXXXXnXXXXXXXXXXXX_!!2286172057.jpg"));

        banner.setDatas(urls, now_num);

    }

    // TODO ***************************************商品评价初始化**************************************
    private void initProductComment() {
        product_comment_list_rv.setLayoutManager(new LinearLayoutManager(this));

    }



    // TODO ***************************************点击监听方法**************************************
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_details_back:
                finish();
                break;
            case R.id.product_details_share:
                Toast.makeText(this, "分享到第三方", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lease_btn:
//                Toast.makeText(this, "租呗", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailsAC.this,PlaceOrderAC.class);
                startActivity(intent);
                break;
            case R.id.bo_one:
                Toast.makeText(this, "啵一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.follow:
                Toast.makeText(this, "关注啵主", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat:
                Toast.makeText(this, "跟啵主聊天", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_comment:
                Toast.makeText(this, "更多评价", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // TODO ***************************************商品参数设置**************************************
    private void setCommodityparameter() {
        product_category.setText("");//类别
        product_brand.setText("");//品牌
        product_color.setText("");//颜色
        product_condition.setText("");//成色
        product_intended_for_person.setText("");//适用人群
        product_enclosure.setText("");//附件
    }
}
