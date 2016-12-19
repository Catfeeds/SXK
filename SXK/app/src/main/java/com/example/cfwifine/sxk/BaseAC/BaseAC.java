package com.example.cfwifine.sxk.BaseAC;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.NetReceiver;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BaseAC extends AppCompatActivity {

    NetReceiver mReceiver = new NetReceiver();
    IntentFilter mFilter = new IntentFilter();
    //    File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 检查网络连接
        checkNetConnect();
//        // 配置ImageLoader
        initImageLoader();
        // 配置导航头
//        translucentStatusBar();
        // 初始化美恰
        inITMeIQia();
    }

    private void inITMeIQia() {
        MQConfig.init(this, "8a2358a00969823007f4ea328ad95bfa", new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
                Toast.makeText(BaseAC.this, "初始化客服成功！", Toast.LENGTH_SHORT).show();
                MQConfig.isShowClientAvatar = true;
                MQConfig.ui.rightChatBubbleColorResId = R.color.login_turquoise;
                MQConfig.ui.leftChatBubbleColorResId = R.color.grrray;
                MQConfig.ui.leftChatTextColorResId = R.color.black;
                MQConfig.ui.rightChatTextColorResId = R.color.white;
            }
            @Override
            public void onFailure(int code, String message) {
                Toast.makeText(BaseAC.this, "初始化客服失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private  void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public void initImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)    //设置图片的质量
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)    //设置图片的缩放类型，该方法可以有效减少内存的占用
                .cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheExtraOptions(480, 300)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .denyCacheImageMultipleSizesInMemory()
                // .memoryCache(new LruMemoryCache((int) (6 * 1024 * 1024)))
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize((int) (2 * 1024 * 1024))
                .memoryCacheSizePercentage(13)
                .discCacheSize(100 * 1024 * 1024).discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(defaultOptions).writeDebugLogs() // Remove
                .build();
                // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .cacheInMemory()
//                .cacheOnDisc()
//                .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration
//                .Builder(getApplicationContext())
////                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
////                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
////                .threadPoolSize(3)//线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
////                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
////                .memoryCacheSize(2 * 1024 * 1024)
////                .discCacheSize(50 * 1024 * 1024)
////                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
////                .discCacheFileCount(100) //缓存的文件数量
////                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
//                .defaultDisplayImageOptions(options)
////                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
////                .writeDebugLogs() // Remove for release app
//                .build();//开始构建
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config);//全局初始化此配置
    }

    public void checkNetConnect(){
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver,mFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
