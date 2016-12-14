package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.PublishFriendMomentRecycleAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishPublishAC;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FriendFourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.ImageUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;
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
    private ImageView publish_publish_add_pic_imageview,mostNinePic;
    private LinearLayout publish_add_pic_lay;
    private RecyclerView hot_cate_publish;
    private LinearLayout activity_community_publish_topic_ac;
    PublishFriendMomentRecycleAdapter publishFriendMomentRecycleAdapter;
    private EditText friend_edittext;
    private ArrayList<String> mResults = new ArrayList<>();
    private static final int REQUEST_CODE = 732;
    ArrayList<String> dataSource=null;
    ArrayList<String> filePathList = null;
    ArrayList<String> list = null;
    int s = 0;
    String headUrl = "";
    ArrayList<byte[]> uploadDatasource = null;
    int modelid = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_publish_topic_ac);
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
        LogUtil.e("topicNameList"+topicNameList);
        LogUtil.e("topicModelList"+topicModelList);

        topicListModle = new ArrayList<>();
        for (int i = 0; i < topicNameList.size(); i++) {
            testModel = new TestModel(topicNameList.get(i).toString(), false);
            topicListModle.add(i, testModel);
        }

        initRecycleView();
    }

    private void initRecycleView() {
        hot_cate_publish = (RecyclerView) findViewById(R.id.hot_cate_publish);
        publishFriendMomentRecycleAdapter = new PublishFriendMomentRecycleAdapter(this, topicListModle,topicModelList);
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

        mostNinePic = (ImageView)findViewById(R.id.most_nine_pic);
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
                releaseTopic();
                break;
            case R.id.friend_publish_pic:
                addPic(0);
                break;

        }
    }

    private void releaseTopic() {
        // 先上传图片，再上传数据
        filePathList = new ArrayList<>();
        list = new ArrayList<>();
        if (mResults.size()!=0){
//            for (int i = 0; i < uploadDatasource.size(); i++) {
//                filePathList.add(i, mResults.get(i));
//                // 图片批量上传
//
//            }
            String token = creatTokenLocal();
            uploadImageToQiniu(uploadDatasource.get(s), token);
//            initReleaseFriendMoment(list);
        }else {
            initReleaseFriendMoment(list);
        }
    }

//    private void uploadBitMapToQiniu(byte[] filePath, String token) {
//        UploadManager uploadManager = new UploadManager();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        final String key = "sxk_userPic_" + sdf.format(new Date()) + s;
//        uploadManager.put(filePath,key,token,new UpCompletionHandler(){
//            @Override
//            public void complete(String key, ResponseInfo info, JSONObject response) {
//
//            }
//        },null);

//        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
//
//
//        }, null);
//    }

    private void uploadImageToQiniu(byte[] filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date()) + s;
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub

                if (key.toString().equals(arg0)){
                    headUrl =  arg0;
                    list.add(s, headUrl);
                    Log.e("上传返回值", "" + headUrl);
                    Log.e("上传返回数组", "" + list);
                    try {
                        JSONObject jsonObject = new JSONObject(String.valueOf(arg1));
                        Log.e("上传返回ResponseInfo", "" + jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e("上传返回JSONObject", "" + arg2);
                        s += 1;
                    if (s < mResults.size()) {
                        String token = creatTokenLocal();
                        uploadImageToQiniu(uploadDatasource.get(s), token);
                    } else {
                        // 图片上传完成
                        LogUtil.e("图片上传完成"+list);
                        initReleaseFriendMoment(list);

                    }
                }else {
//                     图片上传失败屌


                }
            }
        }, null);
    }

    private void initReleaseFriendMoment(ArrayList<String> list) {
        String edittext = friend_edittext.getText().toString().trim();
        if (modelid == -1){
            SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), "你还没有选择分类哦!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        JSONObject jsonObjects = new JSONObject();
        try {
            if (list.size()!=0){
                for (int i = 0; i<list.size();i++){
                    jsonObjects.put("image",list.get(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray1 = new JSONArray();
        for (int i = 0;i<list.size();i++){
            try {
                jsonArray1.put(i,jsonObjects);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content",edittext);
            jsonObject.put("imgList",jsonArray1);
            jsonObject.put("moduleid",modelid);
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
                        Gson gson = new Gson();

//                        if (communityHeaderImageModel.getCode() == 1) {

//                        } else if (communityHeaderImageModel.getCode() == 0) {
//                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        } else if (communityHeaderImageModel.getCode() == 911) {
//                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                            classify_online_view.setVisibility(View.GONE);
//                            classify_nonet_view.setVisibility(View.VISIBLE);
//                        }
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
    private void addPic(int s) {
        Intent intent = new Intent(CommunityPublishTopicAC.this, ImagesSelectorActivity.class);
        // 选择数量
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 9);
        // min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
        if (s == 9){
            // show camera or not
            intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, false);
        }else {
            // show camera or not
            intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
        }
        // pass current selected images as the initial value
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
        // start the selector
        startActivityForResult(intent, REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;
                // show results in textview
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                for(String result : mResults) {
                    sb.append(result).append("\n");
                }
            }
        }else if (requestCode == 777){
            if (resultCode == -1){
                mResults = data.getStringArrayListExtra("images");
                Log.e("回电的数据",""+mResults);
            }
        }

        if (mResults.size()!=0){
            results.setVisibility(View.VISIBLE);
            publish_publish_add_pic_imageview.setVisibility(View.GONE);
            mostNinePic.setVisibility(View.GONE);

            dataSource = new ArrayList<>();
            for (int i = 0; i<mResults.size();i++){
                dataSource.add(mResults.get(i));
            }

            LogUtil.e("图片mResults"+mResults);
            /**
             * 压缩图片质量
             */
            uploadDatasource = new ArrayList<>();
            for (int i = 0; i <mResults.size(); i++){
                Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(i).toString())),false);
                byte[] b = ImageFactory.bitmapToByteAAA(bitmap);
                uploadDatasource.add(i,b);
            }


            dataSource.add(mResults.size(),"TAG");
            dataSource.add(mResults.size()+1,"MORE");



            // 初始化九宫格
            initFourGridView();
        }else {
            results.setVisibility(View.GONE);
            publish_publish_add_pic_imageview.setVisibility(View.VISIBLE);
            mostNinePic.setVisibility(View.VISIBLE);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    // TODO***********************************点击选择照片********************************************
    private  void  initFourGridView(){
        FriendFourGridViewAdapter nineGridAdapter = new FriendFourGridViewAdapter(this,dataSource);

        results.setAdapter(nineGridAdapter);
        results.setOnItemClickListerner(new FourGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了",""+position);
//                ImageBrowseActivity.startActivity(PublishPublishAC.this,dataSource,position);
                Log.e("dataSource",""+dataSource);
                int s = dataSource.size()-2;
                if (position == s){
                    addPic(s);
                }else if (position == s+1){
                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                } else{
                    PreviewPic(position);
                }
            }
        });

    }
    // TODO***********************************点击预览照片********************************************
    private void PreviewPic(int position) {
//        Intent intent = new Intent(PublishPublishAC.this, ImageBrowseActivity.class);
//        // 选择数量
//        intent.putExtra("dataSource", dataSource);
//        startActivityForResult(intent, 777);

        Intent intent = new Intent(CommunityPublishTopicAC.this,ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images",dataSource);
        intent.putExtra("position",position);
        startActivityForResult(intent,777);
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
