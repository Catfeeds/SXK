package com.example.cfwifine.sxk.Section.MineNC.Controller.MineSetting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Dialog.CustomDialog_ShareBorad;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.SettingRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.PersonalIntroAndChangePswAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineSetting.glide.GlideCatchUtil;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserInfoRecycleViewCommomAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView setting_rv;
    private LinearLayout activity_setting_ac;
    private RelativeLayout setting_view_lay;
    // setting页面
    ArrayList<String> applicationName = new ArrayList<>();
    ArrayList<Integer> imageView = new ArrayList<>();
    String[] applicationNames = {"常用地址", "分享APP", "清除缓存", "检测更新", "意见反馈",
            "用户协议", "关于啵呗"};
    int[] imageViews = {R.drawable.dizhi, R.drawable.fenxiang, R.drawable.huancun,
            R.drawable.gengxin, R.drawable.fankui, R.drawable.xieyi,
            R.drawable.guanyu};
    private CustomDialog_ShareBorad customDialog_shareBorad;


    // 我的界面通用跳转
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commom_ac);
        initView();
        getPositionView();
    }

    // TODO***************************************初始化界面****************************************
    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back_pic.setOnClickListener(this);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setOnClickListener(this);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setOnClickListener(this);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
        setting_rv = (RecyclerView) findViewById(R.id.setting_rv);
        setting_rv.setOnClickListener(this);
        activity_setting_ac = (LinearLayout) findViewById(R.id.activity_setting_ac);
        activity_setting_ac.setOnClickListener(this);
        setting_view_lay = (RelativeLayout) findViewById(R.id.setting_view_lay);
        setting_view_lay.setOnClickListener(this);
    }

    /**
     * 根据不同的position来传值
     */
    private void getPositionView() {
        Integer position = getIntent().getIntExtra("JUMPPOSITION", 0);
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                // 设置页面
                initSettingView();
                break;
            default:
                break;
        }
    }

    // TODO***************************************初始化设置界面****************************************
    private void initSettingView() {
        navi_title.setText("设置");
        setting_view_lay.setVisibility(View.VISIBLE);
        initRecycleView();
    }
    private void initRecycleView() {
        for (int i=0;i<imageViews.length;i++) {
            applicationName.add(applicationNames[i]);
            imageView.add(imageViews[i]);
        }
        setting_rv.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        setting_rv.setHasFixedSize(true);
        final SettingRecycleViewAdapter adapter = new SettingRecycleViewAdapter(applicationName,imageView);
        adapter.setOnItemClickListener(new SettingRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                SettingJump(position);
            }
        });
        setting_rv.setAdapter(adapter);
    }
    private void SettingJump(int position){
        switch (position){
            case 0:
                startActivity(AddressSettingCommomAC.class,911);
                break;
            case 4:
                // 意见反馈页面和个人简介是一样的
                startActivity(PersonalIntroAndChangePswAC.class,4);
                break;
            case 5:
                startActivity(UserPrctocalAC.class,222);
                break;
            case 6:
                startActivity(UserPrctocalAC.class,111);
                break;
            case 2:
                clearCache();
                break;
            case 1:
                // 分享到APP
                // 分享到第三方
                customDialog_shareBorad = new CustomDialog_ShareBorad(this, new CustomDialog_ShareBorad.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(int type) {
                        LogUtil.e("输出的类型为"+type);
                        shareToThreePart(type);
                    }
                },R.style.style_dialog);
                customDialog_shareBorad.show();
                break;
            case 3:
                MaterialDialog();
                break;
            default:
                break;
        }
    }

    private void MaterialDialog() {
        BaseAnimatorSet bas_in = new FlipVerticalSwingEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        final MaterialDialog dialogs = new MaterialDialog(this);
        dialogs.title("检测更新")
                .titleTextColor(Color.BLACK)
                .titleTextSize(14)
                .isTitleShow(false)
                .content("当前版本已经是最新版本！")//
                .contentTextColor(Color.GRAY)
                .btnNum(1)
                .btnText("确定")
                .contentGravity(Gravity.CENTER_HORIZONTAL)
                .btnTextColor(Color.parseColor("#16a6ae"))
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialogs.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialogs.dismiss();
            }
        });
    }
    private void shareToThreePart(int type) {
        SHARE_MEDIA SHARE_TYPE = null;
        switch (type){
            case 1:
                SHARE_TYPE = SHARE_MEDIA.WEIXIN;
                break;
            case 2:
                SHARE_TYPE = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case 3:
                SHARE_TYPE = SHARE_MEDIA.SINA;
                break;
            case 4:
                SHARE_TYPE = SHARE_MEDIA.QQ;
                break;
        }
        LogUtil.e("输出类型为"+type);
        UMImage image = new UMImage(UserInfoRecycleViewCommomAC.this, R.mipmap.ic_launcher);
        UMImage thumb =  new UMImage(this, R.mipmap.ic_launcher);
        image.setThumb(thumb);
        image.compressFormat = Bitmap.CompressFormat.PNG;

        new ShareAction(UserInfoRecycleViewCommomAC.this)
                .setPlatform(SHARE_TYPE)
                .withText("亲们快来下载啵呗吧！")
                .withTitle("啵呗")
                .withMedia(image)
                .withTargetUrl("http://shexiangke.jcq.tbapps.cn/wechat/userpage/getrent/rentid/137")
                .setCallback(umShareListener)
                .share();
//        UmengTool.getSignature(ProductDetailsAC.this);
//        UmengTool.getREDICRECT_URL(ProductDetailsAC.this);

    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA platform) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享成功啦！");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享失败！");
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            customDialog_shareBorad.dismiss();
            initSnackBar("分享已取消！");
        }
    };
    public void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(UserInfoRecycleViewCommomAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void clearCache() {
//        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("确定清除？")
//                .setContentText("将清理缓存图片以及相关商品信息！")
//                .setCancelText("取消操作")
//                .setConfirmText("清理缓存")
//                .showCancelButton(true)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        // reuse previous dialog instance, keep widget user state, reset them if you need
//                        sDialog.setTitleText("取消操作")
//                                .setContentText("请及时清理缓存哦！")
//                                .setConfirmText("确定")
//                                .showCancelButton(false)
//                                .setCancelClickListener(null)
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                    }
//                })
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.setTitleText("缓存已清理")
//                                .setContentText("一共清理缓存"+"M")
//                                .setConfirmText("确定")
//                                .showCancelButton(false)
//                                .setCancelClickListener(null)
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                    }
//                })
//                .show();
        // 计算缓存大小
        String cacheSize = GlideCatchUtil.getInstance().getCacheSize();
//        GlideCatchUtil.getInstance().cleanCatchDisk();
        GlideCatchUtil.getInstance().clearCacheDiskSelf();
        GlideCatchUtil.getInstance().clearCacheMemory();
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText("缓存已清理！");
        sweetAlertDialog.setContentText("共清理缓存"+cacheSize);
        sweetAlertDialog.getProgressHelper().setBarColor(R.color.login_turquoise);
        sweetAlertDialog.showCancelButton(false);
        sweetAlertDialog.show();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void startActivity(Class<?> cls,Integer jumpvalue) {
        Intent intent = new Intent(UserInfoRecycleViewCommomAC.this, cls);
        intent.putExtra("SETJUMPPOSITION",jumpvalue);
        startActivity(intent);
    }
}
