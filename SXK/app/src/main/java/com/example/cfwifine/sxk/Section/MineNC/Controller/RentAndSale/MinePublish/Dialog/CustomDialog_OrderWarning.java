package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;


public class CustomDialog_OrderWarning extends Dialog {

    String titles, contents;
    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        void customDialogEvent(int id);
    }
    private ICustomDialogEventListener mCustomDialogEventListener;
    private Context mContext;
    public CustomDialog_OrderWarning(Context context, ICustomDialogEventListener listener, String title, String content, int theme) {
        super(context, theme);
        mContext = context;
        mCustomDialogEventListener = listener;
        titles = title;
        contents = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_order_warning, null);
        TextView title = (TextView) layout.findViewById(R.id.title1);
        title.setText(titles);
        TextView content = (TextView) layout.findViewById(R.id.content);
        content.setText(contents);
        Button btn = (Button) layout.findViewById(R.id.record_nickname_btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialogEventListener.customDialogEvent(2);
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
        this.setContentView(layout, new LinearLayout.LayoutParams((int) (getWindow().getWindowManager().getDefaultDisplay().getWidth() * 0.85),
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Button cancel = (Button) layout.findViewById(R.id.record_nickname_btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }


}
