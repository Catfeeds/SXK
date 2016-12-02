package com.example.cfwifine.sxk.Section.MineNC.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.cfwifine.sxk.R;


public class CustomDialog_Sex extends Dialog {
    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String id);
    }
    private ICustomDialogEventListener mCustomDialogEventListener;
    private Context mContext;
    public CustomDialog_Sex(Context context) {
        super(context);
        mContext = context;
    }
    public CustomDialog_Sex(Context context, ICustomDialogEventListener listener,int theme) {
        super(context, theme);
        mContext = context;
        mCustomDialogEventListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_record_sex, null);
        //判断性别按钮
        final RadioButton man_btn= (RadioButton) layout.findViewById(R.id.man_btn);
        RadioButton woman_btn = (RadioButton) layout.findViewById(R.id.wuman_btn);

//        Boolean ischecked = man_btn.isChecked();
//        Log.e("",""+man_btn.isChecked());

        man_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCustomDialogEventListener.customDialogEvent("男");
                }
            }
        });
        woman_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCustomDialogEventListener.customDialogEvent("女");
                }
            }
        });

//        if (man_btn.isChecked()){
//            mCustomDialogEventListener.customDialogEvent("男");
//        }else {
//            mCustomDialogEventListener.customDialogEvent("女");
//        }

//        dismiss();

        Button imagebtn= (Button) layout.findViewById(R.id.imagbtn);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int radioBtnId = radioGroup.getCheckedRadioButtonId();
//                RadioButton radioButton = (RadioButton)layout.findViewById(radioBtnId);
//                Log.e("你选中了",""+radioButton.getText());
//            }
//        });

//                man_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mCustomDialogEventListener.customDialogEvent("男");
//                        dismiss();
//                    }
//                });
//                woman_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mCustomDialogEventListener.customDialogEvent("女");
//                        dismiss();
//                    }
//                });
//        ColorDrawable dw = new ColorDrawable(0xCC3c3c3c);
//        //设置SelectPicPopupWindow弹出窗体的背景
//        layout.setBackgroundDrawable(dw);

//        this.setFeatureDrawableAlpha(ViewGroup.,0.8);

        this.setContentView(layout,new LinearLayout.LayoutParams((int) (getWindow().getWindowManager().getDefaultDisplay().getWidth()*0.85),
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
