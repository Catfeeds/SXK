package com.example.cfwifine.sxk.Section.LoginAC;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.cfwifine.sxk.R;

/**
 * Created by cfwifine on 16/10/28.
 */

public class LoginPupWindow extends PopupWindow implements OnClickListener {

    private View mMenueView;
    private ImageButton logincancel;
    private LinearLayout useboobe,usewechat,usesina,useqq;
     Activity contexts;


    @SuppressLint("NewApi")
    public LoginPupWindow(Activity context, OnClickListener itemsOnclick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenueView = inflater.inflate(R.layout.login_layout,null);

        logincancel = (ImageButton)mMenueView.findViewById(R.id.login_cancel);
        useboobe = (LinearLayout)mMenueView.findViewById(R.id.login_useboobe);
        usewechat = (LinearLayout)mMenueView.findViewById(R.id.login_usewexin);
        usesina = (LinearLayout)mMenueView.findViewById(R.id.login_usesina);
        useqq = (LinearLayout)mMenueView.findViewById(R.id.login_useqq);
        logincancel.setOnClickListener(itemsOnclick);
        useboobe.setOnClickListener(itemsOnclick);
        useqq.setOnClickListener(itemsOnclick);
        usesina.setOnClickListener(itemsOnclick);
        usewechat.setOnClickListener(itemsOnclick);

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
            case R.id.login_cancel:
                dismiss();
                break;
            case R.id.login_useboobe:

                break;
            default:
                break;
        }
    }

}
