package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.cfwifine.sxk.R;

/**
 * Created by cfwifine on 16/10/28.
 */

public class PublishHowToPupWindow extends PopupWindow implements OnClickListener {


    private ImageView cancelBtn;
    private View mMenueView;

    @SuppressLint("NewApi")
    public PublishHowToPupWindow(Activity context, OnClickListener itemsOnclick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenueView = inflater.inflate(R.layout.howtopublish_layout,null);
        cancelBtn = (ImageView)mMenueView.findViewById(R.id.publish_window_cancel_img);
        cancelBtn.setOnClickListener(itemsOnclick);

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
        ColorDrawable dw = new ColorDrawable(0xE6000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.publish_window_cancel_img:
                dismiss();
                break;
        }
    }
}
