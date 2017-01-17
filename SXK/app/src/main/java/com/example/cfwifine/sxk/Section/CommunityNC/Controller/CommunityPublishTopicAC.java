package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.PublishFriendMomentRecycleAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.ReBackGoodsAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FriendFourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.ImageUtils;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class CommunityPublishTopicAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private FourGridlayout results;
    private ImageView publish_publish_add_pic_imageview, mostNinePic;
    private LinearLayout publish_add_pic_lay;
    private RecyclerView hot_cate_publish;
    private LinearLayout activity_community_publish_topic_ac;
    PublishFriendMomentRecycleAdapter publishFriendMomentRecycleAdapter;
    private EditText friend_edittext;
    private ArrayList<String> mResults = new ArrayList<>();
    private static final int REQUEST_CODE = 732;
    ArrayList<String> dataSource = null;
    ArrayList<String> filePathList = null;
    ArrayList<String> list;
    int s = 0;
    int number = 0;
    ArrayList<byte[]> uploadDatasource = null;
    int modelid = -1;
    Dialog mloading;
    LikeIOSSheetDialog sheetView;
    private String headUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_publish_topic_ac);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initTopicData();
        initView();


    }

    ArrayList<String> topicNameList = null;
    ArrayList<Integer> topicModelList = null;
    ArrayList<TestModel> topicListModle = null;
    TestModel testModel = null;

    private void initTopicData() {
        topicNameList = getIntent().getStringArrayListExtra("TOPIC");
        topicModelList = getIntent().getIntegerArrayListExtra("TOPICMODELID");
        LogUtil.e("topicNameList" + topicNameList);
        LogUtil.e("topicModelList" + topicModelList);
        mloading = LoadingUtils.createLoadingDialog(this, "正在努力发布中...");
        topicListModle = new ArrayList<>();
        for (int i = 0; i < topicNameList.size(); i++) {
            testModel = new TestModel(topicNameList.get(i).toString(), false);
            topicListModle.add(i, testModel);
        }

        initRecycleView();

        uploadDatasource = new ArrayList<>();
    }

    private void initRecycleView() {
        hot_cate_publish = (RecyclerView) findViewById(R.id.hot_cate_publish);
        publishFriendMomentRecycleAdapter = new PublishFriendMomentRecycleAdapter(this, topicListModle, topicModelList);
        hot_cate_publish.setLayoutManager(new GridLayoutManager(this, 3));
        hot_cate_publish.setAdapter(publishFriendMomentRecycleAdapter);
        publishFriendMomentRecycleAdapter.setOnItemClickListener(new PublishFriendMomentRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Integer name, int position) {
                modelid = name;
            }
        });

    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        results = (FourGridlayout) findViewById(R.id.results);
        publish_publish_add_pic_imageview = (ImageView) findViewById(R.id.friend_publish_pic);
        publish_publish_add_pic_imageview.setOnClickListener(this);

        mostNinePic = (ImageView) findViewById(R.id.most_nine_pic);
        publish_add_pic_lay = (LinearLayout) findViewById(R.id.publish_add_pic_lay);

        activity_community_publish_topic_ac = (LinearLayout) findViewById(R.id.activity_community_publish_topic_ac);

        navi_title.setText("社区");
        navi_right.setText("发表");
        navi_right.setTextColor(getResources().getColor(R.color.login_turquoisex));
        navi_back.setOnClickListener(this);
        navi_right_lays.setOnClickListener(this);
        friend_edittext = (EditText) findViewById(R.id.friend_edittext);
        friend_edittext.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right_lays:
                // 点击发表朋友圈
                LogUtil.e("number的值" + number);
                LogUtil.e("uploadData" + uploadDatasource);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        releaseTopic();
                    }
                }).start();
                break;
            case R.id.friend_publish_pic:
                addPic();
                break;

        }
    }

    private void releaseTopic() {
        // 先上传图片，再上传数据
        list = new ArrayList<>();
        s = 0;
        if (number != 0) {
            LogUtil.e("number" + number);
            String token = creatTokenLocal();
            LogUtil.e("数组" + uploadDatasource);
            uploadImageToQiniu(uploadDatasource.get(0), token);
        } else {
            initReleaseFriendMoment(list);
        }
    }

    private void uploadImageToQiniu(byte[] filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date()) + s;
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub

                if (key.toString().equals(arg0)) {
                    headUrl = arg0;
                    list.add(headUrl);
                    Log.e("上传返回值", "" + headUrl);
                    Log.e("上传返回数组", "" + list);
                    try {
                        JSONObject jsonObject = new JSONObject(String.valueOf(arg1));
                        Log.e("上传返回ResponseInfo", "" + jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e("上传返回JSONObject", "" + arg2);

                    if (s < uploadDatasource.size() - 1) {
                        s += 1;
                        String token = creatTokenLocal();
                        uploadImageToQiniu(uploadDatasource.get(s), token);
                    } else {
                        // 图片上传完成
                        LogUtil.e("图片上传完成" + list);
                        initReleaseFriendMoment(list);
                    }
                } else {
//                     图片上传失败屌
                    LogUtil.e("图片上传失败" + list);
                    return;

                }
            }
        }, null);
    }

    private void initReleaseFriendMoment(ArrayList<String> list) {
        String edittext = friend_edittext.getText().toString().trim();
        if (edittext.toString().trim().isEmpty()) {
            SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), "内容不能为空哦!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        if (modelid == -1) {
            SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), "你还没有选择分类哦!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        mloading.show();
        LogUtil.e("图片列表" + list);
        JSONArray jsonArray1 = new JSONArray();

        for (int i = 0; i < list.size(); i++) {

            String li = list.get(i);
            JSONObject jsonObjects = new JSONObject();
            try {
                jsonObjects.put("image", li);
                jsonArray1.put(i, jsonObjects);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", edittext);
            jsonObject.put("imgList", jsonArray1);
            jsonObject.put("moduleid", modelid);
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
                        SnackbarUtils.showShortSnackbar(CommunityPublishTopicAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("发表结果", "" + response);
                        mloading.dismiss();
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            finish();
                        } else if (requestStatueModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(CommunityPublishTopicAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (requestStatueModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(CommunityPublishTopicAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
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


    // TODO*********************************点击发布商品**********************************************
    private void addPic() {
        sheetView = new LikeIOSSheetDialog.Builder(CommunityPublishTopicAC.this).setTitle("选择照片").addMenu("从手机相册选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();

                // 安卓6.0权限适配
                if (ContextCompat.checkSelfPermission(CommunityPublishTopicAC.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CommunityPublishTopicAC.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                } else {//权限被授予
                    startActivityForResult(MQPhotoPickerActivity.newIntent(CommunityPublishTopicAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
                }

            }
        }).addMenu("拍一张", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(CommunityPublishTopicAC.this, new String[]{Manifest.permission.CAMERA}, 733);
                } else {
                    //有授权，直接开启摄像头
                    choosePhotoFromCamera();
                }
            }
        }).create();
        sheetView.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                startActivityForResult(MQPhotoPickerActivity.newIntent(CommunityPublishTopicAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
            } else {
                // Permission Denied
                Toast.makeText(CommunityPublishTopicAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 733) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被授予
                choosePhotoFromCamera();
            } else {
                // Permission Denied
                Toast.makeText(CommunityPublishTopicAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
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
        MQUtils.closeKeyboard(CommunityPublishTopicAC.this);

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
                // show results in textview
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
            if (resultCode == -1) {
                mResults = data.getStringArrayListExtra("images");
                Log.e("回电的数据", "" + mResults);
            }
        }

        if (mResults.size() != 0) {
            results.setVisibility(View.VISIBLE);
            publish_publish_add_pic_imageview.setVisibility(View.GONE);
            mostNinePic.setVisibility(View.GONE);

            dataSource = new ArrayList<>();
            for (int i = 0; i < mResults.size(); i++) {
                dataSource.add(mResults.get(i));
            }

            LogUtil.e("图片mResults" + mResults);
            /**
             * 压缩图片质量
             */
            number = mResults.size();
            s = 1;
            uploadDatasource.clear();
            for (int i = 0; i < mResults.size(); i++) {
                Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(i).toString())), false);
                byte[] b = ImageFactory.bitmapToByteAAA(bitmap);
                uploadDatasource.add(i, b);
            }


            dataSource.add(mResults.size(), "TAG");
            dataSource.add(mResults.size() + 1, "MORE");


            // 初始化九宫格
            initFourGridView();
        } else {
            results.setVisibility(View.GONE);
            publish_publish_add_pic_imageview.setVisibility(View.VISIBLE);
            mostNinePic.setVisibility(View.VISIBLE);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    // TODO***********************************点击选择照片********************************************
    private void initFourGridView() {
        FriendFourGridViewAdapter nineGridAdapter = new FriendFourGridViewAdapter(this, dataSource);

        results.setAdapter(nineGridAdapter);
        results.setOnItemClickListerner(new FourGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了", "" + position);
//                ImageBrowseActivity.startActivity(PublishPublishAC.this,dataSource,position);
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
        Intent intent = new Intent(CommunityPublishTopicAC.this, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images", dataSource);
        intent.putExtra("position", position);
        startActivityForResult(intent, 777);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void submit() {
        // validate
        String edittext = friend_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(edittext)) {
            Toast.makeText(this, "这一刻想说的话...", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
