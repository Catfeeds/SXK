package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.HowToReleasePicAC;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FriendFourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;

public class ReBackGoodsAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private FourGridlayout results;
    private ImageView reback_add_pic_imageview;
    private ImageView reback_howto_pic_imageview;
    private ImageView reback_max_nine_pic;
    private LinearLayout reback_add_pic_lay;
    private EditText reback_ordernumber_edt;
    private EditText reback_comment_edt;
    private Button reback_confirm_btn;
    private LinearLayout activity_re_back_goods_ac;
    private int orderid;
    private LikeIOSSheetDialog sheetView;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<String> dataSource;
    Dialog dialog;
    private ArrayList<byte[]> uploadDatasource;
    private ArrayList<String> urlList;
    int number = 0;
    private String edts;
    private String edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_back_goods_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"提交中...");
        orderid = getIntent().getIntExtra("ORDERID", -1);
        LogUtil.e("orderid" + orderid);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("寄回拍照");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        results = (FourGridlayout) findViewById(R.id.results);
        reback_add_pic_imageview = (ImageView) findViewById(R.id.reback_add_pic_imageview);
        reback_add_pic_imageview.setOnClickListener(this);
        reback_howto_pic_imageview = (ImageView) findViewById(R.id.reback_howto_pic_imageview);
        reback_howto_pic_imageview.setOnClickListener(this);
        reback_max_nine_pic = (ImageView) findViewById(R.id.reback_max_nine_pic);
        reback_max_nine_pic.setOnClickListener(this);
        reback_add_pic_lay = (LinearLayout) findViewById(R.id.reback_add_pic_lay);
        reback_ordernumber_edt = (EditText) findViewById(R.id.reback_ordernumber_edt);
        reback_comment_edt = (EditText) findViewById(R.id.reback_comment_edt);
        reback_confirm_btn = (Button) findViewById(R.id.reback_confirm_btn);
        activity_re_back_goods_ac = (LinearLayout) findViewById(R.id.activity_re_back_goods_ac);

        reback_confirm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_confirm_btn:
                submit();
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.reback_add_pic_imageview:
                addPic();
                break;
            case R.id.reback_howto_pic_imageview:
                howToPublish();
                break;
            case R.id.reback_max_nine_pic:
                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "必须九张图!", Color.WHITE, Color.parseColor("#16a6ae"));
                break;
        }
    }

    private void addPic() {
        sheetView = new LikeIOSSheetDialog.Builder(ReBackGoodsAC.this).setTitle("选择照片").addMenu("从手机相册选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                // 安卓6.0权限适配
                if (ContextCompat.checkSelfPermission(ReBackGoodsAC.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReBackGoodsAC.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                } else {//权限被授予
                    startActivityForResult(MQPhotoPickerActivity.newIntent(ReBackGoodsAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
                }

            }
        }).addMenu("拍一张", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(ReBackGoodsAC.this, new String[]{Manifest.permission.CAMERA}, 733);
                } else {
                    //有授权，直接开启摄像头
//                    takePhoto();
                    choosePhotoFromCamera();
                }
            }
        }).create();
        sheetView.show();
    }

    /**
     * 打开相机
     */
    private String mCameraPicPath;

    private void choosePhotoFromCamera() {
        MQUtils.closeKeyboard(ReBackGoodsAC.this);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(MQUtils.getPicStorePath(this));
        file.mkdirs();
        String path = MQUtils.getPicStorePath(this) + "/" + System.currentTimeMillis() + ".jpg";
        File imageFile = new File(path);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        mCameraPicPath = path;
        try {
            startActivityForResult(camera, MQConversationActivity.REQUEST_CODE_CAMERA);
        } catch (Exception e) {
            MQUtils.show(this, com.meiqia.meiqiasdk.R.string.mq_photo_not_support);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // get selected images from selector
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                mResults = MQPhotoPickerActivity.getSelectedImages(data);
                assert mResults != null;
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                for (String result : mResults) {
                    sb.append(result).append("\n");
                }
            }
        } else if (requestCode == MQConversationActivity.REQUEST_CODE_CAMERA) {
            if (resultCode == RESULT_OK) {
                mResults.add(mCameraPicPath);
            }
        } else if (requestCode == 777) {
            if (resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra("images");
                Log.e("回电的数据", "" + mResults);
            }
        }

        if (mResults.size() != 0) {
            results.setVisibility(View.VISIBLE);
            reback_add_pic_imageview.setVisibility(View.GONE);
            reback_max_nine_pic.setVisibility(View.GONE);

            dataSource = new ArrayList<>();
            for (int i = 0; i < mResults.size(); i++) {
                dataSource.add(mResults.get(i));
            }

            LogUtil.e("图片mResults" + mResults);
            /**
             * 压缩图片质量
             */
//            uploadDatasource.clear();
//            for (int i = 0; i < mResults.size(); i++) {
//                Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(i).toString())), false);
//                byte[] b = ImageFactory.bitmapToByteAAA(bitmap);
//                uploadDatasource.add(i, b);
//            }


            dataSource.add(mResults.size(), "TAG");
            dataSource.add(mResults.size() + 1, "MORE");


            // 初始化九宫格
            initFourGridView();
        } else {
            results.setVisibility(View.GONE);
            reback_add_pic_imageview.setVisibility(View.VISIBLE);
            reback_max_nine_pic.setVisibility(View.VISIBLE);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initFourGridView() {
        FriendFourGridViewAdapter nineGridAdapter = new FriendFourGridViewAdapter(this, dataSource);

        results.setAdapter(nineGridAdapter);
        results.setOnItemClickListerner(new FourGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了", "" + position);
                Log.e("dataSource", "" + dataSource);
                int s = dataSource.size() - 2;
                if (position == s) {
                    if (mResults.size() >= 9) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                    } else {
                        addPic();
                    }
                } else if (position == s + 1) {
                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                } else {
                    PreviewPic(position);
                }
            }
        });

    }

    // TODO***********************************点击预览照片********************************************
    private void PreviewPic(int position) {
        Intent intent = new Intent(ReBackGoodsAC.this, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images", dataSource);
        intent.putExtra("position", position);
        startActivityForResult(intent, 777);
    }
    private void howToPublish() {
        Intent intent = new Intent(this, HowToReleasePicAC.class);
        startActivity(intent);
    }
    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //权限被授予
                startActivityForResult(MQPhotoPickerActivity.newIntent(ReBackGoodsAC.this, null, 9, mResults, "完成"), REQUEST_CODE);

            } else {
                // Permission Denied
                Toast.makeText(ReBackGoodsAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (requestCode == 733) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                choosePhotoFromCamera();
            } else {
                // Permission Denied
                Toast.makeText(ReBackGoodsAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void submit() {
        dialog.show();
        // validate
        if (mResults.size()!=9){
            dialog.dismiss();
            initSnackBar("必须九张图！");
            return;
        }
        edt = reback_ordernumber_edt.getText().toString().trim();
        if (TextUtils.isEmpty(edt)) {
            dialog.dismiss();
            initSnackBar("你还没有输入单号！");
            return;
        }
        edts = reback_comment_edt.getText().toString().trim();
        if (TextUtils.isEmpty(edts)) {
            dialog.dismiss();
            initSnackBar("你还没有评价！");
            return;
        }
        /**
         * 开启子线程进行上传，不然会产生卡顿现象
         */
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadNinePic();
            }
        }).start();
    }
    private void uploadNinePic() {

        // 要上传的byte类型的图片数组, 处理后图片大小在100-400K之间
        uploadDatasource = new ArrayList<>();
        for (int i = 0; i < mResults.size(); i++) {
            Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(i).toString())), false);
            byte[] b = ImageFactory.bitmapToByteAAA(bitmap);
            uploadDatasource.add(i, b);
            LogUtil.e("监控图片大小" + ImageFactory.getSizeOfBitmap(bitmap));
        }
        LogUtil.e("准备上传的图片的数组" + uploadDatasource);
        urlList = new ArrayList<>();
        // 开始上传
        number = 0;
        uploadImageToQiniu(uploadDatasource.get(0), creatTokenLocal(), number);

        LogUtil.e("上传完成" + urlList);
    }

    private void uploadImageToQiniu(byte[] filePath, String token, final int s) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date()) + s;
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub
                if (key.toString().equals(arg0)) {
                    // 上传成功！
                    String url = arg0;
                    urlList.add(url);
                    if (number < 8) {
                        number += 1;
                        uploadImageToQiniu(uploadDatasource.get(number), creatTokenLocal(), number);
                    } else {
                        // 上传完成
                        LogUtil.e("图片的数组" + urlList);
                        // 先发送运单号，在发送评论
//                        initReleaseData(urlList);
                        initOrderId();
                    }
                } else {
                    dialog.dismiss();
                    // 上传失败
                    initSnackBar("发布失败！");
                }
            }
        }, null);
    }

    private void initOrderId() {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid",orderid);
            jsonObject.put("backOddNumber",edt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ChangeRentOrder)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            initReleaseData(urlList);
                        } else if (requestStatueModel.getCode() == 0) {
                            dialog.dismiss();
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            dialog.dismiss();
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });

    }

    private void initReleaseData(ArrayList<String> urlList) {
        // 图片数组
        JSONArray jsonArray1 = new JSONArray();
        for (int i = 0; i < urlList.size(); i++) {
            String li = urlList.get(i);
            try {
                jsonArray1.put(i,li);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject js = new JSONObject();
        try {
            js.put("orderid",orderid);
            js.put("imgList",jsonArray1);
            js.put("content",edts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RentComment)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("发布商品", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                           finish();
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });



    }

    public static String AK = "e6m0BrZSOPhaz6K2TboadoayOp-QwLge2JOQZbXa";
    public static String SK = "RxiQnoa8NqIe7lzSip-RRnBdX9_pwOQmBBPqGWvv";
    public static String SCOPE = "shexiangke-jcq";

    private String creatTokenLocal() {
        Mac mac = new Mac(AK, SK);
        PutPolicy putPolicy = new PutPolicy(SCOPE);
        putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":$(etag)}";
        try {
            String uptoken = putPolicy.token(mac);
            System.out.println("debug:uptoken = " + uptoken);
            return uptoken;
        } catch (AuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    private void initSnackBar(String value){
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }


}
