package com.example.cfwifine.sxk.ShareHelper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;


import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by Mr.Yang on 2016/12/28.
 */

public class UmShareLoginUtils {

    private Context mContext;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;

     public void login(Context context,SHARE_MEDIA share_media) {
         this.mContext = context;
         UMShareAPI.get(context).getPlatformInfo((Activity) context,share_media,authListener);
     }

    public void Share(Context context) {
        this.mContext = context;
        mShareListener = new CustomShareListener(context);
        /*无自定按钮的分享面板*/
        mShareAction = new ShareAction((Activity) context).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .withText( "来自友盟自定义分享面板")
                .setCallback(mShareListener);
        mShareAction.open();
    }


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<Context> mActivity;

        private CustomShareListener(Context activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }




    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //得到的用户信息
            String temp = "";
            for (String key : data.keySet()) {
                temp= temp+key +" : "+data.get(key)+"\n";
            }
            Toast.makeText(mContext, temp.toString()+"", Toast.LENGTH_SHORT).show();
            android.util.Log.e("======temp",temp.toString());
            android.util.Log.e("======data",data.toString());

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };
}
