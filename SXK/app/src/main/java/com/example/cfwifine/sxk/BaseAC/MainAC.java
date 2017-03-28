package com.example.cfwifine.sxk.BaseAC;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.cfwifine.sxk.Section.HomeNC.Controller.HomeFC;
import com.example.cfwifine.sxk.Section.LoginAC.Controller.LoginFC;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Controller.PublishBuyPlusAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishFC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPupWindow;
import com.example.cfwifine.sxk.Section.PublishNC.CuringAC.CuringAC;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

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
    private UserInfoModel userInfoModel;
    private String USERINFO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ac);
        initView();
        // 初始化个人信息，如果登录超时 LoginFC展示为登录界面   否则则展示个人中心界面
//        initUserData();

    }

    public void initUserData() {
        JSONObject js = new JSONObject();
        try {
            js.put("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(MainAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.GetUserInfo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("似乎断网了w(ﾟДﾟ)w");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("初始化个人信息", "" + response);
                        Gson gson = new Gson();
                        userInfoModel = gson.fromJson(response,UserInfoModel.class);
                        if (userInfoModel.getCode() == 1) {
                            //
                            USERINFO = response;
                            if (userInfoModel.getUser().getNickname()!=null){
                                SharedPreferencesUtils.setParam(MainAC.this, BaseInterface.NICKNAME,userInfoModel.getUser().getNickname());
                            }
                            if (userInfoModel.getUser().getHeadimgurl()!=null){
                                SharedPreferencesUtils.setParam(MainAC.this, BaseInterface.PORITA,userInfoModel.getUser().getHeadimgurl());
                            }
                            SharedPreferencesUtils.setParam(MainAC.this, BaseInterface.USERID,userInfoModel.getUser().getUserid());

                        } else if (userInfoModel.getCode() == 0) {
                        } else if (userInfoModel.getCode() == 911) {
                            SharedPreferencesUtils.setParam(MainAC.this,BaseInterface.PHPSESSION,"");
                        }
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

//    public boolean isLogin(){
//        if (userInfoModel == null){
//            return false;
//        }
//        if (userInfoModel.getCode()==1){
//            return true;
//        }else if (userInfoModel.getCode() ==911){
//            return false;
//        }else if (userInfoModel.getCode() == 0){
//            return false;
//        }
//        return false;
//    }
    public String getUserInfo(){
        return USERINFO;
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

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            publishPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.publish_lay:
                    startActivity(PublishPublishAC.class);
                    break;
                case R.id.care_lay:
                    startActivity(CuringAC.class);
//                    startActivity(FreeAppraisaAC.class);
                    break;
                case R.id.rec_lay:
                    startActivity(PublishBuyPlusAC.class);
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
    private void initSnackBar(String value){
        SnackbarUtils.showShortSnackbar(MainAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
