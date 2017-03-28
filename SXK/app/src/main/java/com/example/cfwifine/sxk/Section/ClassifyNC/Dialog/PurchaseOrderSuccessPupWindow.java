package com.example.cfwifine.sxk.Section.ClassifyNC.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductDetailModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.PurchasesDetailModel;

/**
 * Created by cfwifine on 16/10/28.
 */

public class PurchaseOrderSuccessPupWindow extends PopupWindow implements OnClickListener {
    private final ImageView picView;
    private final TextView names;
    private final TextView keyWord;
    private final TextView prices;
    private final Button cancelbtn,paybtn;
    private PurchasesDetailModel.PurchaseBean productDetail=null;
    private View mMenueView;

    @SuppressLint("NewApi")
    public PurchaseOrderSuccessPupWindow(Activity context, PurchasesDetailModel.PurchaseBean productDetailModel, OnClickListener itemsOnclick) {
        super(context);
        this.productDetail = productDetailModel;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenueView = inflater.inflate(R.layout.purchase_order_success_layout, null);
        picView = (ImageView)mMenueView.findViewById(R.id.goods_pic);
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + productDetail.getImgList().get(0);
        Glide.with(context).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(picView);
        names = (TextView)mMenueView.findViewById(R.id.goods_name);
        names.setText(productDetail.getName());
        keyWord =(TextView)mMenueView.findViewById(R.id.goods_keyword);
        if (productDetail.getDescription().trim().length()>15){
            keyWord.setText(productDetail.getDescription().trim().substring(0,15)+"...");
        }else {
            keyWord.setText(productDetail.getDescription().trim());
        }

        prices = (TextView)mMenueView.findViewById(R.id.goods_price);
        double price = productDetail.getSellingPrice();
        prices.setText(String.format("%.2f",price/100));

        cancelbtn = (Button)mMenueView.findViewById(R.id.cancel_btn);
        cancelbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        paybtn = (Button)mMenueView.findViewById(R.id.pay_btn);
        paybtn.setOnClickListener(itemsOnclick);

        this.setContentView(mMenueView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.setTouchable(true);
//        this.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.take_photo_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffff);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 设置背景模糊
        mMenueView.setAlpha((float) 1);
    }

    @Override
    public void onClick(View view) {

    }
}
