package com.example.cfwifine.sxk.Section.MineNC.CustomDialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.OnWheelScrollListener;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.WheelView;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.adapters.NumericWheelAdapter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/8.
 */

public class CustomDialog_birthday extends AlertDialog {
    private Context context;
    NumericWheelAdapter numericWheelAdapter1;
    private int curYear = 1996;
    private int curMonth =1;
    private WheelView year;
    private WheelView month;
    private Button tv_ok;
    private List<Integer> birthdayList = new ArrayList<Integer>();
    private int n_years = 0;
    private int n_month = 0;

    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        public void customDialogEvent(List<Integer> birthdayList);
    }
    private ICustomDialogEventListener mCustomDialogEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View view1 = View.inflate(context, R.layout.datepicker_layout, null);
        year = (WheelView) view1.findViewById(R.id.year);
        month = (WheelView) view1.findViewById(R.id.month);

        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);

        numericWheelAdapter1=new NumericWheelAdapter(context,1950, norYear);
//        numericWheelAdapter1.setLabel("");
        numericWheelAdapter1.setTextColor(Color.BLACK);
        numericWheelAdapter1.setTextSize(13);
        year.setViewAdapter(numericWheelAdapter1);
        year.setCyclic(true);//是否可循环滑动
        year.addScrollingListener(scrollListener);
        NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(context,1, 12, "%02d");
//        numericWheelAdapter2.setLabel("");
        numericWheelAdapter2.setTextColor(Color.BLACK);
        numericWheelAdapter2.setTextSize(13);
        month.setViewAdapter(numericWheelAdapter2);
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);
        year.setVisibleItems(5);//设置显示行数
        month.setVisibleItems(5);
        year.setCurrentItem(curYear - 1950);
        month.setCurrentItem(curMonth - 1);

        this.setContentView(view1);

        tv_ok = (Button) view1.findViewById(R.id.tv_ok);
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                birthdayList.add(n_years);
                birthdayList.add(n_month);
                mCustomDialogEventListener.customDialogEvent(birthdayList);
                birthdayList.clear();
                dismiss();
            }
        });
    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            n_years = year.getCurrentItem()+1950;//年
            n_month = month.getCurrentItem() + 1;//月

        }

    };


    public CustomDialog_birthday(Context context, ICustomDialogEventListener listener, int theme) {
        super(context, theme);
        this.context = context;
        mCustomDialogEventListener = listener;
    }
}
