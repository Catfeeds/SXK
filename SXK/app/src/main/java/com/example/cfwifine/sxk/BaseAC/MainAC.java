package com.example.cfwifine.sxk.BaseAC;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ClassifyFC;
import com.example.cfwifine.sxk.Section.CommunityNC.Controller.CommunFC;
import com.example.cfwifine.sxk.Section.HomeNC.HomeFC;
import com.example.cfwifine.sxk.Section.LoginAC.LoginFC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishFC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPupWindow;

public class MainAC extends BaseAC  {

    private FragmentTabHost tabHost;
    private LayoutInflater inflater;
    private PublishPupWindow publishPupWindow;

    private Class fragments[] = {
            HomeFC.class,
            ClassifyFC.class,
            PublishFC.class,
            CommunFC.class,
            LoginFC.class
            };

    private int images[]={
            R.drawable.home,
            R.drawable.classfiy,
            R.drawable.publish,
            R.drawable.commun,
            R.drawable.mine,
    };

    private String texts[]={
            "首页",
            "分类",
            "发布",
            "社区",
            "我的",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ac);
        initView();
    }

    private void initView() {
        inflater=LayoutInflater.from(this);
        tabHost= (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.content);
        tabHost.getTabWidget().setDividerDrawable(R.color.white);
        int count=fragments.length;
        for(int i=0;i<count;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(texts[i]).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec, fragments[i], null);
//            if (i == 2){
//                tabHost.getCurrentTab();
//                tabHost.setOnTabChangedListener(null);
//            }
        }
        tabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
//                tabHost.setCurrentTab(tabHost.getCurrentTab());
//                if(tabHost.getCurrentTab()==2){
//                    tabHost.getTabWidget().getChildTabViewAt(2).setBackground(getResources().getDrawable(R.drawable.news_unselected));
//                }
                Log.e("当店",""+tabHost.getCurrentTabTag());

//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                publishPupWindow = new PublishPupWindow(MainAC.this,itemsOnClick);
                publishPupWindow.showAtLocation(MainAC.this.findViewById(R.id.activity_main_ac), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

//                // 设置背景模糊
//                BlurBehind.getInstance().withAlpha(50)
//                        .withFilterColor(Color.parseColor("#d3d3d3"))
//                        .setBackground(MainAC.this);



            }
        });


    }
    private Fragment getFragment(int tabId)
    {
        return getSupportFragmentManager().findFragmentByTag("TAB_TAG_" + tabId);
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            publishPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.publish_lay:
                    Log.e("点击了发布",""+v.getId());
                    startActivity(PublishPublishAC.class);
                    break;
                case R.id.care_lay:
                    Log.e("点击了养护",""+v.getId());
                    break;
                case R.id.rec_lay:
                    Log.e("点击了鉴定",""+v.getId());
                    break;
                default:
                    break;
            }


        }

    };


    private View getTabItemView(int i){
        if(i!=2) {
            View view = inflater.inflate(R.layout.tabitem, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setImageResource(images[i]);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(texts[i]);
            return view;
        }else{
//            ImageView imageView = (ImageView)findViewById(R.id.main_image_center);
            ImageView imageView=new ImageView(this);
            imageView.setMaxHeight(100);
            imageView.setMaxHeight(100);
            imageView.setImageResource(images[i]);
            return imageView;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainAC.this, cls);
        startActivity(intent);
    }


}
