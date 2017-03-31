package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Controller.MinePurchaseItems;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.MineItemRentAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Controller.MineSaleItem;

public class MineRentPartAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private LinearLayout mine_publish;
    private LinearLayout mine_rent;
    private LinearLayout activity_mine_rent_part_ac;
    private int MTYPE = -1;
    private TextView mine_publish_txt;
    private TextView mine_publish_etxt;
    private TextView mine_rent_txt;
    private TextView mine_rent_etxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_rent_part_ac);
        MTYPE = getIntent().getIntExtra("JUMPPOSITION", -1);
        // 1 我的租赁 2 我的买卖
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        mine_publish_txt = (TextView) findViewById(R.id.mine_publish_txt);
        mine_publish_etxt = (TextView) findViewById(R.id.mine_publish_etxt);
        mine_rent_txt = (TextView) findViewById(R.id.mine_rent_txt);
        mine_rent_etxt = (TextView) findViewById(R.id.mine_rent_etxt);
        if (MTYPE == 1) {
            navi_title.setText("我的租赁");
        } else if (MTYPE == 2) {
            navi_title.setText("我的买卖");
            mine_publish_txt.setText("我的寄卖");
            mine_publish_etxt.setText("My Sale");
            mine_rent_txt.setText("我的买入");
            mine_rent_etxt.setText("My Goods");
        }
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        mine_publish = (LinearLayout) findViewById(R.id.mine_publish);
        mine_publish.setOnClickListener(this);
        mine_rent = (LinearLayout) findViewById(R.id.mine_rent);
        mine_rent.setOnClickListener(this);
        activity_mine_rent_part_ac = (LinearLayout) findViewById(R.id.activity_mine_rent_part_ac);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.mine_publish:
                if (MTYPE == 1){
                    Intent inte = new Intent(MineRentPartAC.this, MineItemAC.class);
                    startActivity(inte);
                }else {
                    Intent inte = new Intent(MineRentPartAC.this, MineSaleItem.class);
                    startActivity(inte);
                }

                break;
            case R.id.mine_rent:
                if (MTYPE == 1){
                    Intent intent = new Intent(MineRentPartAC.this, MineItemRentAC.class);
                    startActivity(intent);
                }else {
                    Intent inte = new Intent(MineRentPartAC.this, MinePurchaseItems.class);
                    startActivity(inte);
                }

                break;
            default:
                break;
        }
    }
}
