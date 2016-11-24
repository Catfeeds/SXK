package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.View.BlurBehind;

/**
 * Created by cfwifine on 16/10/28.
 */

public class PublishPupWindow extends PopupWindow implements View.OnClickListener {
    private final LinearLayout publishBtn,careBtn,recBtn;
    private final ImageButton publishcancel;
    private View mMenueView;

    @SuppressLint("NewApi")
    public PublishPupWindow(Activity context, OnClickListener itemsOnclick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenueView = inflater.inflate(R.layout.publish_layout,null);



        publishBtn = (LinearLayout)mMenueView.findViewById(R.id.publish_lay);
        careBtn = (LinearLayout)mMenueView.findViewById(R.id.care_lay);
        recBtn = (LinearLayout)mMenueView.findViewById(R.id.rec_lay);
        publishcancel = (ImageButton)mMenueView.findViewById(R.id.publish_cancel);
        publishcancel.setOnClickListener(this);
        publishBtn.setOnClickListener(itemsOnclick);
        careBtn.setOnClickListener(itemsOnclick);
        recBtn.setOnClickListener(itemsOnclick);
        this.setContentView(mMenueView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.take_photo_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffff);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);



        // 设置背景模糊

        mMenueView.setAlpha((float) 0.95);
//        BlurBehind.getInstance().withAlpha(50)
//                .withFilterColor(Color.parseColor("#0075c0"))
//                .setBackground(context);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.publish_cancel:
                dismiss();
                break;
        }
    }
}
