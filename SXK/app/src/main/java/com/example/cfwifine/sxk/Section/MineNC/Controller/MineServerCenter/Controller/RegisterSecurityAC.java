package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineIDIdentify.IDCardUtils;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineIDIdentify.IDIdentifyAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Utils.DFileUtils;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;

public class RegisterSecurityAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private EditText register_sec_name;
    private EditText register_sec_cardnumber;
    private ImageView idcard_pp;
    private ImageView idcard_bb;
    private EditText register_secority;
    private Button idcard_publish_btn;
    private boolean isOne = true;
    private boolean isTwo = false;
    LikeIOSSheetDialog shitView;
    private static final int REQUEST_CODE = 732;
    private static final int TAKE_PHOTO = 733;
    private ArrayList<String> mResults = new ArrayList<>();
    private Bitmap ppBitMap = null;
    private Bitmap bbBitMap = null;
    private ArrayList<String> HEADERPIC = new ArrayList<>();
    Dialog dialog;
    private String name;
    private String cardnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_security_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("注册审核");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        register_sec_name = (EditText) findViewById(R.id.register_sec_name);
        register_sec_cardnumber = (EditText) findViewById(R.id.register_sec_cardnumber);
        idcard_pp = (ImageView) findViewById(R.id.idcard_pp);
        idcard_bb = (ImageView) findViewById(R.id.idcard_bb);
        idcard_pp.setOnClickListener(this);
        idcard_bb.setOnClickListener(this);
        register_secority = (EditText) findViewById(R.id.register_secority);
        idcard_publish_btn = (Button) findViewById(R.id.idcard_publish_btn);
        idcard_publish_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idcard_publish_btn:
                submit();
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.idcard_pp:
                isOne = true;
                isTwo = false;
                initIOSSheetDialog();
                break;
            case R.id.idcard_bb:
                isOne = false;
                isTwo = true;
                initIOSSheetDialog();
                break;
        }
    }
    private void initIOSSheetDialog() {
        shitView = new LikeIOSSheetDialog.Builder(RegisterSecurityAC.this)
                .setTitle("更换头像")
                .addMenu("从手机相册选择", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
                        // 安卓6.0权限适配
                        if (ContextCompat.checkSelfPermission(RegisterSecurityAC.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(RegisterSecurityAC.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_CODE);
                        } else {//权限被授予
                            startActivityForResult(MQPhotoPickerActivity.newIntent(RegisterSecurityAC.this, null, 1, mResults, "完成"), REQUEST_CODE);
                        }
                    }
                }).addMenu("拍一张", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
//                        Toast.makeText(v.getContext(), "拍一张" , Toast.LENGTH_SHORT).show();
                        // 适配安卓6.0 对敏感权限进行动态非配权限
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            //如果没有授权，则请求授权
                            ActivityCompat.requestPermissions(RegisterSecurityAC.this, new String[]{Manifest.permission.CAMERA}, 733);
                        } else {
                            //有授权，直接开启摄像头
                            takePhoto();
                        }


                    }
                }).create();
        shitView.show();
    }
    String mPicDirectory = "SXK";
    private static final int REQUESTCODE_PICK = 0;
    private static final int REQUESTCODE_TAKE = 1;
    private static final int REQUESTCODE_CUTTING = 2;
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";

    private void takePhoto() {
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后照片存储的位置
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        startActivityForResult(takeIntent, TAKE_PHOTO);

    }

    String data1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mResults = MQPhotoPickerActivity.getSelectedImages(data);
                    assert mResults != null;
                    // show results in textview
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                    for (String result : mResults) {
                        sb.append(result).append("\n");
                    }
                }
                if (mResults.size() != 0) {
                    if (isOne) {
                        LogUtil.e("点击的是第一个" + isOne);
                        idcard_pp.setImageBitmap(BitmapFactory.decodeFile(new File(mResults.get(0)).getPath()));
                        ppBitMap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(0).toString())), false);
                    } else if (isTwo) {
                        idcard_bb.setImageBitmap(BitmapFactory.decodeFile(new File(mResults.get(0)).getPath()));
                        bbBitMap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(0).toString())), false);
                        LogUtil.e("点击的是第二个" + isTwo);
                    }

//                    header.setImageBitmap(BitmapFactory.decodeFile(new File(mResults.get(0)).getPath()));
//                    Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(0).toString())), false);
//                    // 上传图片
//                    mloading.show();
//                    String token = creatTokenLocal();
//                    byte[] bytes = ImageFactory.bitmapToByteAAA(bitmap);
//                    uploadImageToQiniuByChar(bytes,token);
                }

                break;
            case TAKE_PHOTO:
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的照片
                if (data != null) {
                    data1 = String.valueOf(data.getData());
                    setPicToView(data);
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private String urlpath;
    Drawable drawable;

    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 获取SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            drawable = new BitmapDrawable(null, photo);
            urlpath = DFileUtils.saveFile(RegisterSecurityAC.this, "temphead.jpg", photo);
            if (isOne) {
                idcard_pp.setImageDrawable(drawable);
                ppBitMap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(urlpath)), false);
            } else if (isTwo) {
                idcard_bb.setImageDrawable(drawable);
                bbBitMap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(urlpath)), false);
            }
            // 新线程后台上传服务器
//            mloading = LoadingUtils.createLoadingDialog(this,"努力上传图片中...");
//            mloading.show();
//            String token = creatTokenLocal();
//            uploadImageToQiniu(urlpath,token);
        }
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW课裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高比
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                startActivityForResult(MQPhotoPickerActivity.newIntent(RegisterSecurityAC.this, null, 1, mResults, "完成"), REQUEST_CODE);
            } else {
                // Permission Denied
                Toast.makeText(RegisterSecurityAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 733) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                takePhoto();
            } else {
                // Permission Denied
                Toast.makeText(RegisterSecurityAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void initSnackBar(String content) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), content, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    private void submit() {
        // validate
        name = register_sec_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            initSnackBar("请输入您的姓名!");
            return;
        }

        cardnumber = register_sec_cardnumber.getText().toString().trim();
        if (TextUtils.isEmpty(cardnumber)) {
            initSnackBar("身份证信息不能为空！");
            return;
        } else {
            IDCardUtils cc = new IDCardUtils();
            try {
                String errInfo = cc.IDCardValidate(cardnumber);
                if (!TextUtils.isEmpty(errInfo)) {
                    initSnackBar(errInfo);
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (ppBitMap == null) {
            initSnackBar("没有上传正面身份证信息！");
            return;
        }
        if (bbBitMap == null) {
            initSnackBar("没有上传背面身份证信息！");
            return;
        }
        String secority = register_secority.getText().toString().trim();
        if (TextUtils.isEmpty(secority)) {
            initSnackBar("请输入保证金(不低于300)!");
            return;
        }

        dialog.show();
        // 判断是否上传图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                String token = creatTokenLocal();
                byte[] bytes = ImageFactory.bitmapToByteAAA(ppBitMap);
                uploadImageToQiniuByChar(bytes, token);
            }
        }).start();
    }

    private void uploadImageToQiniuByChar(byte[] filePath, final String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date());
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub

                if (key.toString().equals(arg0)) {
                    String headPic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    HEADERPIC.add(0, headPic);
                    byte[] bytess = ImageFactory.bitmapToByteAAA(bbBitMap);
                    String tokens = creatTokenLocal();
                    uploadImageToQiniuByCharSecondPic(bytess, tokens);

                } else {
                    dialog.dismiss();
                    initSnackBar("上传失败，请检查网络！");
                }
            }
        }, null);
    }

    private void uploadImageToQiniuByCharSecondPic(byte[] filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date());
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub

                if (key.toString().equals(arg0)) {
                    String headPic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    HEADERPIC.add(1, headPic);
                    LogUtil.e("返回的数组1" + HEADERPIC.toString());
                    initUserInfo(HEADERPIC);
                } else {
                    dialog.dismiss();
                    initSnackBar("上传失败，请检查网络！");
                }
            }
        }, null);
    }

    private void initUserInfo(ArrayList<String> list) {
        LogUtil.e("上传返回的图片素组" + list.toString());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("idNumber", cardnumber);
            jsonObject.put("front", list.get(0));
            jsonObject.put("back", list.get(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject buyer = new JSONObject();
        try {
            buyer.put("seller", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.UpdateUserInfo)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(buyer.toString())
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 dialog.dismiss();
                                 SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 LogUtil.e("个人信息" + response);
                                 dialog.dismiss();
                                 Gson gson = new Gson();
                                 RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                                 if (requestStatueModel.getCode() == 1) {
                                     SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "修改成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                                     finish();
                                 } else if (requestStatueModel.getCode() == 0) {
                                     SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "修改失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                                 } else if (requestStatueModel.getCode() == 911) {
                                     SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                                 }
                             }
                         }
                );
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


}
