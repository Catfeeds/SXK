package com.example.cfwifine.sxk.Section.ClassifyNC.Dialog;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.OnWheelChangedListener;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.WheelView;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.adapters.ArrayWheelAdapter;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.CityModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.DistrictModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.ProvinceModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.service.XmlParserHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class CustomDialog_ShareBorad extends AlertDialog{

    private Context context;
    private Button mBtnConfirm,mCancelBtn;
    private LinearLayout WeiXin;
    private LinearLayout WeiXinCircle;
    private LinearLayout QQs;
    private LinearLayout Sina;


    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        public void customDialogEvent(int type);
    }
    private ICustomDialogEventListener mCustomDialogEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.shareborad_selector_item, null);
        mCancelBtn = (Button)view.findViewById(R.id.share_btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        WeiXin = (LinearLayout)view.findViewById(R.id.weixin);
        WeiXinCircle = (LinearLayout)view.findViewById(R.id.weixin_circle);
        QQs = (LinearLayout)view.findViewById(R.id.qq);
        Sina = (LinearLayout)view.findViewById(R.id.sina);
        WeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDialogEventListener.customDialogEvent(1);
            }
        });
        WeiXinCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDialogEventListener.customDialogEvent(2);
            }
        });
        QQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDialogEventListener.customDialogEvent(3);
            }
        });
        Sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDialogEventListener.customDialogEvent(4);
            }
        });

        this.setContentView(view);
        this.getWindow().setGravity(Gravity.BOTTOM);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.setWindowAnimations(R.style.Animation_Bottom_Rising);
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }
    public CustomDialog_ShareBorad(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public CustomDialog_ShareBorad(Context context, ICustomDialogEventListener listener, int theme) {
        super(context, theme);
        this.context = context;
        mCustomDialogEventListener = listener;
    }
}
