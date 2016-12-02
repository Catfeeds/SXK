package com.example.cfwifine.sxk.Section.MineNC.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;


public class CustomDialog_Time extends Dialog {
    EditText editText;

    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String id);
    }


    private ICustomDialogEventListener mCustomDialogEventListener;
    private Context mContext;

    public CustomDialog_Time(Context context) {
        super(context);
        mContext = context;
    }

    public CustomDialog_Time(Context context, ICustomDialogEventListener listener, int theme) {
        super(context, theme);
        mContext = context;
        mCustomDialogEventListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_time, null);
        editText = (EditText) layout.findViewById(R.id.edit);
        EditTextUtil.setPricePoint(editText, mContext);
        Button btn = (Button) layout.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mCustomDialogEventListener.customDialogEvent(String.valueOf(editText.getText()));
                dismiss();

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

    }


}
