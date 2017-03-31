package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.EditTextUtil;


public class CustomDialog_inputOrderNumber extends Dialog{

    EditText editText;

    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        void customDialogEvent(String id);
    }

    public interface ICustomSaveEventListener {
        public void customSaveEvent(String id);
    }

    private ICustomDialogEventListener mCustomDialogEventListener;
    private ICustomSaveEventListener customSaveEventListener;
    private Context mContext;

    public CustomDialog_inputOrderNumber(Context context) {
        super(context);
        mContext = context;
    }

    public CustomDialog_inputOrderNumber(Context context, ICustomDialogEventListener listener, int theme) {
        super(context, theme);
        mContext = context;
        mCustomDialogEventListener = listener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_input_ordernumber, null);
        editText = (EditText) layout.findViewById(R.id.record_nickname_edt);
        EditTextUtil.setPricePoint(editText, mContext);
        Button btn = (Button) layout.findViewById(R.id.record_nickname_btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
//                try {
//                    if (Float.parseFloat(editText.getText().toString()) > 30.0 && Float.parseFloat(editText.getText().toString()) < 150.0) {
                        mCustomDialogEventListener.customDialogEvent(String.valueOf(editText.getText()));
                        dismiss();
//                    } else {
//                        XToast.show("您输入的数字不合法");
//                    }
//                }catch (NumberFormatException e){
//                    e.printStackTrace();
//                }
            }
        });
        Button imagebtn = (Button) layout.findViewById(R.id.imagbtn);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        this.setContentView(layout,new LinearLayout.LayoutParams((int) (getWindow().getWindowManager().getDefaultDisplay().getWidth()*0.85),
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Button cancel = (Button)layout.findViewById(R.id.record_nickname_btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }




}
