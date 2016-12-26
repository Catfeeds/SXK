package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

/**
 * Created by cfwifine on 16/10/28.
 */

public class EditTextPupWindow extends PopupWindow implements OnClickListener {
    private final EditText commentBtn;
    private final Button sendBtn;
    private final TextView dismissView;
    private View mMenueView;
    public static liveCommentResult liveCommentResult = null;

    public interface EditTextEventListener {
         void editTextEvent(String content);
    }

    private EditTextEventListener mEditTextEventListener;

    @SuppressLint("NewApi")
    public EditTextPupWindow(Activity context, OnClickListener itemsOnclick, EditTextEventListener editTextEventListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenueView = inflater.inflate(R.layout.edittext_layout, null);
//        liveCommentResult = commentResult;
        mEditTextEventListener = editTextEventListener;

        dismissView = (TextView) mMenueView.findViewById(R.id.comment_dismiss);
        dismissView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        commentBtn = (EditText) mMenueView.findViewById(R.id.commment_input);
        commentBtn.requestFocus();



        sendBtn = (Button) mMenueView.findViewById(R.id.comment_send_btn);
        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String contentx = commentBtn.getText().toString().trim();
                LogUtil.e("评论详情"+contentx);
                if (contentx.length() !=  0){
                    mEditTextEventListener.editTextEvent(contentx);
                    dismiss();
                }
            }
        });

        InputMethodManager im = (InputMethodManager) commentBtn.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

        this.setContentView(mMenueView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.take_photo_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffff);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 设置背景模
        mMenueView.setAlpha((float) 0.95);


    }

    public interface liveCommentResult {
        void onResult(boolean confirmed, String comment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_dismiss:
                dismiss();
                break;
        }
    }
}
