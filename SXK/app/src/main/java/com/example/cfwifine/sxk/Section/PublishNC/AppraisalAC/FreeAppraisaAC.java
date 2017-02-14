package com.example.cfwifine.sxk.Section.PublishNC.AppraisalAC;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Controller.CommunityPublishTopicAC;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishBrandAC;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
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
import java.util.List;

import okhttp3.Call;

public class FreeAppraisaAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RelativeLayout navi;
    private EditText publish_input_edittext;
    private FourGridlayout nineGridlayout;
    private ImageView addPic;
    private ImageView howtoAdd;
    private ImageView publish_publish_max_nine_pic;
    private LinearLayout publish_add_pic_lay;
    private Button publish_appraisa_btn;
    private LinearLayout activity_publish_publish_ac;
    LikeIOSSheetDialog sheetView;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
    ArrayList<String> dataSource;
    Dialog dialog;
    ArrayList<byte[]> uploadDatasource = null;
    private ArrayList<String> urlList=null;
    private int number=0;
    private String content="";
    private List<CommunityTopicListModel.ModuleListBean> topic=null;
    private int ModleID=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_appraisa_ac);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dialog = LoadingUtils.createLoadingDialog(this,"发布中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("免费鉴定");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi = (RelativeLayout) findViewById(R.id.navi);
        publish_input_edittext = (EditText) findViewById(R.id.publish_input_edittext);
        nineGridlayout = (FourGridlayout) findViewById(R.id.results);
        addPic = (ImageView) findViewById(R.id.publish_publish_add_pic_imageview);
        howtoAdd = (ImageView) findViewById(R.id.publish_publish_howto_pic_imageview);
        publish_publish_max_nine_pic = (ImageView) findViewById(R.id.publish_publish_max_nine_pic);
        addPic.setOnClickListener(this);
        howtoAdd.setOnClickListener(this);
        publish_publish_max_nine_pic.setOnClickListener(this);
        publish_add_pic_lay = (LinearLayout) findViewById(R.id.publish_add_pic_lay);
        publish_appraisa_btn = (Button) findViewById(R.id.publish_appraisa_btn);
        activity_publish_publish_ac = (LinearLayout) findViewById(R.id.activity_publish_publish_ac);

        publish_appraisa_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_appraisa_btn:
                submit();
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.publish_publish_max_nine_pic:
                initSnackBar("必须九张图！");
                break;
            case R.id.publish_publish_howto_pic_imageview:

                break;
            case R.id.publish_publish_add_pic_imageview:
                addPic();
                break;
        }
    }
    private void addPic() {
        sheetView = new LikeIOSSheetDialog.Builder(FreeAppraisaAC.this).setTitle("选择照片").addMenu("从手机相册选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                // 安卓6.0权限适配
                if (ContextCompat.checkSelfPermission(FreeAppraisaAC.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FreeAppraisaAC.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                } else {//权限被授予
                    startActivityForResult(MQPhotoPickerActivity.newIntent(FreeAppraisaAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
                }

//                startActivityForResult(MQPhotoPickerActivity.newIntent(PublishPublishAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
            }
        }).addMenu("拍一张", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(FreeAppraisaAC.this, new String[]{Manifest.permission.CAMERA}, 733);
                } else {
                    //有授权，直接开启摄像头
//                    takePhoto();
                    choosePhotoFromCamera();
                }


            }
        }).create();
        sheetView.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
        if (requestCode == REQUEST_CODE) {
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
        } else if (requestCode == 777) {
            if (resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra("images");
                Log.e("回电的数据", "" + mResults);
//                mResults = MQPhotoPickerPreviewActivity.getSelectedImages(data);
            }
        }else if (requestCode == MQConversationActivity.REQUEST_CODE_CAMERA) {
            if (resultCode == RESULT_OK) {
                mResults.add(mCameraPicPath);
            }
        }

        if (mResults.size() != 0) {
            nineGridlayout.setVisibility(View.VISIBLE);
            addPic.setVisibility(View.GONE);
            howtoAdd.setVisibility(View.GONE);

            dataSource = new ArrayList<>();
            for (int i = 0; i < mResults.size(); i++) {
                dataSource.add(mResults.get(i));
            }

            dataSource.add(mResults.size(), "TAG");
            dataSource.add(mResults.size() + 1, "MORE");
            // 初始化九宫格
            initFourGridView();
        } else {
            nineGridlayout.setVisibility(View.GONE);
            addPic.setVisibility(View.VISIBLE);
            howtoAdd.setVisibility(View.VISIBLE);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    private void initFourGridView() {
        FourGridAdapter nineGridAdapter = new FourGridViewAdapter(this, dataSource);
        nineGridlayout.setAdapter(nineGridAdapter);
        nineGridlayout.setOnItemClickListerner(new FourGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了", "" + position);
//                ImageBrowseActivity.startActivity(PublishPublishAC.this,dataSource,position);
                Log.e("dataSource", "" + dataSource);
                int s = dataSource.size() - 2;
                if (position == s) {
                    File file = Environment.getExternalStorageDirectory();
                    if (mResults.size() >= 9) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                    } else {
                        addPic();
                    }
//                    startActivityForResult(MQPhotoPickerActivity.newIntent(PublishPublishAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
                } else if (position == s + 1) {
                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                } else {
                    PreviewPic(position);
//                    startActivityForResult(MQPhotoPickerPreviewActivity.newIntent(PublishPublishAC.this, mResults.size(), mResults, mResults, position, "完成", false), 777);

                }
            }
        });

    }

    // TODO***********************************点击预览照片********************************************
    private void PreviewPic(int position) {
        Intent intent = new Intent(FreeAppraisaAC.this, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images", dataSource);
        intent.putExtra("position", position);
        startActivityForResult(intent, 777);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                startActivityForResult(MQPhotoPickerActivity.newIntent(FreeAppraisaAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
            } else {
                // Permission Denied
                Toast.makeText(FreeAppraisaAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 733) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                choosePhotoFromCamera();
            } else {
                // Permission Denied
                Toast.makeText(FreeAppraisaAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    /**
     * 打开相机
     */
    private String mCameraPicPath;

    private void choosePhotoFromCamera() {
        MQUtils.closeKeyboard(FreeAppraisaAC.this);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(MQUtils.getPicStorePath(this));
        file.mkdirs();
        String path = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
        File imageFile = new File(path);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        mCameraPicPath = path;
        try {
            startActivityForResult(camera, MQConversationActivity.REQUEST_CODE_CAMERA);
        } catch (Exception e) {
            MQUtils.show(this, com.meiqia.meiqiasdk.R.string.mq_photo_not_support);
        }

    }
    public void initSnackBar(String content) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), content, Color.WHITE, Color.parseColor("#16a6ae"));
    }
    private void submit() {
        // validate
        content = publish_input_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            initSnackBar("内容不能为空！");
            return;
        }
        // 判断用户是否选了九张图
        if (mResults.size() != 9) {
            initSnackBar("必须九张图！");
            return;
        }
        dialog.show();
        // 上传图片
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
                        initFriendMomentGetTopicListDataSource();


                    }
                } else {
                    dialog.dismiss();
                    // 上传失败
                    initSnackBar("发布失败！");
                }
            }
        }, null);
    }
    /**
     * 获取模块列表
     */
    private void initFriendMomentGetTopicListDataSource() {
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityGetSectionList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        SnackbarUtils.showShortSnackbar(FreeAppraisaAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("话题列表", "" + response);
                        topic = new ArrayList<CommunityTopicListModel.ModuleListBean>();
                        Gson gson = new Gson();
                        CommunityTopicListModel communityTopicListModel = gson.fromJson(response, CommunityTopicListModel.class);
                        if (communityTopicListModel.getCode() == 1) {
                            topic = communityTopicListModel.getModuleList();
                            for (int i = 0; i<topic.size();i++){
                                if (topic.get(i).getName().toString().trim().equals("免费鉴定")){
                                    ModleID = topic.get(i).getModuleid();
                                }
                            }
                            initReleaseData(urlList);
                        } else if (communityTopicListModel.getCode() == 0) {
                            dialog.dismiss();
                            SnackbarUtils.showShortSnackbar(FreeAppraisaAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (communityTopicListModel.getCode() == 911) {
                            dialog.dismiss();
                            SnackbarUtils.showShortSnackbar(FreeAppraisaAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }
    private void initReleaseData(ArrayList<String> urlList) {
        LogUtil.e("图片列表" + urlList);
        // 图片数组
        JSONArray jsonArray1 = new JSONArray();
        for (int i = 0; i < urlList.size(); i++) {
            String li = urlList.get(i);
            try {
                jsonArray1.put(i, li);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", content);
            jsonObject.put("imgList", jsonArray1);
            jsonObject.put("moduleid", ModleID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CommunityAddTopic)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("请求失败！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("发表结果", "" + response);
                        dialog.dismiss();
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
}
