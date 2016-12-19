package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ActivityDetailModel;
import com.example.cfwifine.sxk.Section.LoginAC.Controller.ForgetPawAC;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.UserInfoRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_Sex;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_birthday;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_nickname;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_phone;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Utils.DFileUtils;
import com.example.cfwifine.sxk.Utils.FileManager;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.example.cfwifine.sxk.Utils.TimeUtils;
import com.example.cfwifine.sxk.Utils.ToastUtil;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;


import android.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class UserInfoAC extends AppCompatActivity implements View.OnClickListener {

    RecyclerView userRV;
    UserInfoRecycleViewAdapter userInfoRecycleViewAdapter;
    ArrayList<String> itemSource = new ArrayList<>();
    String[] itemString = {"昵称", "性别", "生日", "个人简介", "手机号码",
            "修改密码"};
    ArrayList<String> callBackString = new ArrayList<>();
    LikeIOSSheetDialog shitView;

    private String selectDate, selectTime;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private int currentHour, currentMinute, currentDay, selectHour, selectMinute, selectDay;

    private static final int REQUEST_CODE = 732;
    private static final int TAKE_PHOTO = 733;
    private ArrayList<String> mResults = new ArrayList<>();

    Bitmap photo;
    CircleImageView header;
    String mImageCachePath;
    // 更新用户信息的相关信息
    String nickName = "";
    int Sex = 0;
    long Birthday=0;
    String personalIntro="";
    String mobile="";
    private UserInfoModel.UserBean dataSource;
    Dialog mloading;
    String HEADERPIC="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_ac);
        mloading = LoadingUtils.createLoadingDialog(this,"努力上传图片中...");
        /**
         * 0 更新 1请求
         */
        initUserInfo(1, 911);
        initView();
        configurationNaviTitle();
        initRecycleData();

    }

    /**
     * 初始化个人信息,更新个人信息
     */
    JSONObject jsonObject;
    String Url = "";
    /**
     *  0,0更新nickname 0，1 更新性别 02更新生日  04更新手机号码
     */
    private void initUserInfo(final int value, final int ss) {
        jsonObject = new JSONObject();
        if (value == 0) {
            try {
                switch (ss) {
                    case 0:
                        if (!dataSource.getNickname().equals(nickName)) {
                            jsonObject.put("nickname", nickName);
                        } else {
                            initSnackBar("你还没有修改哦！");
                            return;
                        }
                        break;
                    case 1:
                        if (dataSource.getSex() !=Sex) {
                            jsonObject.put("sex", Sex);
                        } else {
                            initSnackBar("你还没有修改哦！");
                            return;
                        }
                        break;
                    case 2:
                        if (dataSource.getBirthday() != Birthday){
                            jsonObject.put("birthday",Birthday);
                        }else {
                            initSnackBar("你还没有修改哟！");
                            return;
                        }
                        break;
                    case 3:
                        jsonObject.put("headimgurl",HEADERPIC);
                        break;
                    default:
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Url = BaseInterface.UpdateUserInfo;
        } else {
            try {
                jsonObject.put("", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Url = BaseInterface.GetUserInfo;
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(Url)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        mloading.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("个人信息", "" + response);
                        LogUtil.e("个人信息" + response);
                        if (value == 1) {
                            Gson gson = new Gson();
                            UserInfoModel userInfoModel = gson.fromJson(response, UserInfoModel.class);
                            if (userInfoModel.getCode() == 1) {
                                // 请求成功
                                dataSource = userInfoModel.getUser();

//                                Glide.with(UserInfoAC.this).load(dataSource.getHeadimgurl()).centerCrop().placeholder(R.drawable.user_header_image_placeholder).into(header);
                                ImageLoader.getInstance().displayImage(dataSource.getHeadimgurl(),header);
                                // 数据请求成功后才可以点击，否则不能点击
                                if (dataSource.getNickname()!=null){
                                    SharedPreferencesUtils.setParam(getApplicationContext(), dataSource.getNickname().toString(), "");
                                }
                            } else if (userInfoModel.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (userInfoModel.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        } else if (value == 0) {
                            mloading.dismiss();
                            Gson gson = new Gson();
                            RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                            if (requestStatueModel.getCode() == 1) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "修改成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (requestStatueModel.getCode() == 0) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "修改失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            } else if (requestStatueModel.getCode() == 911) {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        }
                    }
                });
    }

    private void initView() {
        header = (CircleImageView) findViewById(R.id.userInfoHeaderView);
        header.setOnClickListener(this);
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("个人资料");
        TextView rightTitle = (TextView) findViewById(R.id.navi_right);
        rightTitle.setText("");
    }

    private void initRecycleData() {
        for (int i = 0; i < itemString.length; i++) {
            itemSource.add(itemString[i]);
        }
        initRecycleView();
    }

    // TODO**************************************初始化分栏******************************************
    private void initRecycleView() {
        userRV = (RecyclerView) findViewById(R.id.user_info_recycleview);
        userRV.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        userInfoRecycleViewAdapter = new UserInfoRecycleViewAdapter(itemSource, callBackString);
        userRV.setAdapter(userInfoRecycleViewAdapter);
        userInfoRecycleViewAdapter.setOnItemClickListener(new UserInfoRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        initNickNameDialog();
                        break;
                    case 1:
                        initSexDialog();
                        break;
                    case 2:
                        initBirthdayDialog();
                        break;
                    case 3:
                        initChangePsw(3);
                        break;
                    case 4:
                        initChangePhone();
                        break;
                    case 5:
                        Intent intent = new Intent(UserInfoAC.this, ForgetPawAC.class);
                        intent.putExtra("CHANGEPSW", 5);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    // TODO**************************************自定义弹出框******************************************

    /**
     *  0,0更新nickname 0，1 更新性别 02更新生日  04更新手机号码
     */
    private void initSexDialog() {
        CustomDialog_Sex customDialog_sex = new CustomDialog_Sex(this, new CustomDialog_Sex.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("性别", "" + id);
                if (id.equals("男")) {
                    Sex = 1;
                } else if (id.equals("女")) {
                    Sex = 2;
                }
                initUserInfo(0, 1);
            }
        }, R.style.Dialog);
        customDialog_sex.show();
    }

    /**
     * nickname
     */
    private void initNickNameDialog() {
        CustomDialog_nickname customDialog_nickname = new CustomDialog_nickname(this, new CustomDialog_nickname.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("返回的", "" + id);
                if (!id.isEmpty()) {
                    SharedPreferencesUtils.setParam(getApplicationContext(), BaseInterface.USERNAME, id);
                    nickName = id;
                    initUserInfo(0, 0);
                }else {
                    initSnackBar("你还没有选择哦！");
                }

            }
        }, R.style.Dialog);
        customDialog_nickname.show();
    }

    private void initBirthdayDialog() {
        CustomDialog_birthday customDialog_birthday = new CustomDialog_birthday(this, new CustomDialog_birthday.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(List<Integer> birthdayList) {
                LogUtil.e("生日" + birthdayList);
                if (birthdayList.get(0)!=0){
                    SimpleDateFormat DEFAULT_SDFS = new SimpleDateFormat("yyyy年MM月", Locale.getDefault());
                    String time = String.valueOf(birthdayList.get(0)+"年"+birthdayList.get(1)+"月");
                    long mills = TimeUtils.string2Milliseconds(time,DEFAULT_SDFS)/1000;
                    LogUtil.e("时间戳"+mills);
                    Birthday = mills;
                    initUserInfo(0,2);
                }else {
                    initSnackBar("你还没有选择哦！");
                }
            }
        }, R.style.Dialog);
        customDialog_birthday.show();
    }

    private void initPersonalIntro() {
        Intent intent = new Intent(UserInfoAC.this, PersonalIntroAndChangePswAC.class);
        startActivity(intent);
    }

    private void initChangePhone() {
        CustomDialog_phone customDialog_phone = new CustomDialog_phone(this, new CustomDialog_phone.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("返回", "" + id);


            }
        }, R.style.Dialog);
        customDialog_phone.show();
    }

    private void initChangePsw(int pos) {
        Intent intent = new Intent(UserInfoAC.this, PersonalIntroAndChangePswAC.class);
        intent.putExtra("CHANGEPSW", pos);
        startActivity(intent);
    }

    private void initIOSSheetDialog() {
        shitView = new LikeIOSSheetDialog.Builder(UserInfoAC.this)
                .setTitle("更换头像")
                .addMenu("从手机相册选择", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
                        addPic();
                    }
                }).addMenu("拍一张", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
//                        Toast.makeText(v.getContext(), "拍一张" , Toast.LENGTH_SHORT).show();
                        takePhoto();

                    }
                }).create();
        shitView.show();
    }

    private void addPic() {
        startActivityForResult(MQPhotoPickerActivity.newIntent(UserInfoAC.this, null, 1, mResults, "完成"), REQUEST_CODE);

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
                    for(String result : mResults) {
                        sb.append(result).append("\n");
                    }
                }
                if (mResults.size()!=0){
                    header.setImageBitmap(BitmapFactory.decodeFile(new File(mResults.get(0)).getPath()));
                    Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(0).toString())), false);
                    // 上传图片
                    mloading.show();
                    String token = creatTokenLocal();
                    byte[] bytes = ImageFactory.bitmapToByteAAA(bitmap);
                    uploadImageToQiniuByChar(bytes,token);
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
    // 上传byte
    private void uploadImageToQiniuByChar(byte[] filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date());
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub

                if (key.toString().equals(arg0)) {
                    String headPic = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    HEADERPIC = headPic;
                    initUserInfo(0,3);
                }else {
                    initSnackBar("上传失败，请检查网络！");
                }
            }
        }, null);
    }

    private String urlpath;
    Drawable drawable;
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 获取SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            drawable = new BitmapDrawable(null, photo);
            urlpath = DFileUtils.saveFile(UserInfoAC.this, "temphead.jpg", photo);
            header.setImageDrawable(drawable);
            // 新线程后台上传服务器
            mloading = LoadingUtils.createLoadingDialog(this,"努力上传图片中...");
            mloading.show();
            String token = creatTokenLocal();
            uploadImageToQiniu(urlpath,token);
        }
    }
    private void uploadImageToQiniu(String filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date());
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub
                Log.e("上传返回值",""+arg0);
                if (key == arg0){
                    LogUtil.e("上传的头像地址"+arg0);
                    String headPics = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    HEADERPIC = headPics;
                    initUserInfo(0,3);

                }else {
                    initSnackBar("头像上传失败！请检查网络");
                    mloading.dismiss();
                }

            }
        },null);
    }


    public static String AK = "e6m0BrZSOPhaz6K2TboadoayOp-QwLge2JOQZbXa";
    public static String SK = "RxiQnoa8NqIe7lzSip-RRnBdX9_pwOQmBBPqGWvv";
    public static String SCOPE = "shexiangke-jcq";
    private String creatTokenLocal (){
        Mac mac = new Mac(AK,SK);
        PutPolicy putPolicy = new PutPolicy(SCOPE);
        putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":$(etag)}";
        try {
            String uptoken = putPolicy.token(mac);
            System.out.println("debug:uptoken = "+ uptoken);
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

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW课裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高比
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    private void initSnackBar(String name) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), name, Color.WHITE, Color.parseColor("#16a6ae"));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.userInfoHeaderView:
                initIOSSheetDialog();
                break;
            default:
                break;
        }
    }
}
