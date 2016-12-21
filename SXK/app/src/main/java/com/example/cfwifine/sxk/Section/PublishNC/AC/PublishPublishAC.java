package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
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

public class PublishPublishAC extends AppCompatActivity implements View.OnClickListener {

    ImageView addPic, howtoAdd;
    RelativeLayout category, brand, color, material, suitpersonal, file;
    TextView cateoryTxt;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
    ArrayList<String> dataSource;
    FourGridlayout nineGridlayout;
    LikeIOSSheetDialog sheetView;

    File imageFile;
    private EditText publish_input_edittext;
    private ImageView publish_publish_max_nine_pic;
    private EditText publish_name_edittext;
    private EditText publish_price_edittext;
    private EditText publish_keyword_edittext;
    private TextView publish_brand_text;
    private EditText publish_color_edittext;
    private TextView publish_news_text;
    private TextView publish_people_text;
    private TextView publish_user_protocal_text;
    private Button publish_release_btn;
    Dialog mloading;
    ArrayList<byte[]> uploadDatasource = null;
    private ArrayList<String> urlList;

    int number = 0;
    private String descript;
    private String goodsName;
    private String goodsBrand;
    private String goodsColor;
    private String chengse;
    private String people;
    private String goodsprice;
    private String goodsKeyword;
    private int categoryid;
    private int CATEGORYID;
    private int BRANDID;
    private String chengsexxxx;
    private String peoplexxxx;
    private String ATTACHMENT;
    private AttachmentModel.CategoryListBean.AttachListBean SECONDDATA;
    private String FUJIAN;
    private List<AttachmentModel.CategoryListBean.AttachListBean> dataSourcess;
    private String BAOBEIFUJIAN;
    private ArrayList<String> FUJIANARR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_publish_ac);
        mloading = LoadingUtils.createLoadingDialog(this, "正在发布...");
        SharedPreferencesUtils.setParam(this, "RESULT", "");
        SharedPreferencesUtils.setParam(this, "CATEGORYID", 0);
        SharedPreferencesUtils.setParam(this, "BRANDID", 0);
        SharedPreferencesUtils.setParam(this, "BAOBEIFUJIAN", "");
        SharedPreferencesUtils.setParam(this, "SECONDDATA", "");
        SharedPreferencesUtils.setParam(this, "FUJIAN", "");

        configurationNaviTitle();
        initView();
        cateoryTxt.setText("（必选）");

    }

    // TODO*********************************配置界面**********************************************
    public void initView() {
        addPic = (ImageView) findViewById(R.id.publish_publish_add_pic_imageview);
        howtoAdd = (ImageView) findViewById(R.id.publish_publish_howto_pic_imageview);
        addPic.setOnClickListener(this);
        howtoAdd.setOnClickListener(this);

        category = (RelativeLayout) findViewById(R.id.publish_category);
        brand = (RelativeLayout) findViewById(R.id.publish_brand);
        material = (RelativeLayout) findViewById(R.id.publish_material);
        suitpersonal = (RelativeLayout) findViewById(R.id.publish_suitpersonal);
        file = (RelativeLayout) findViewById(R.id.publish_file);
        category.setOnClickListener(this);
        brand.setOnClickListener(this);
        material.setOnClickListener(this);
        suitpersonal.setOnClickListener(this);
        file.setOnClickListener(this);
        cateoryTxt = (TextView) findViewById(R.id.cateory_txt);


        nineGridlayout = (FourGridlayout) findViewById(R.id.results);
        publish_input_edittext = (EditText) findViewById(R.id.publish_input_edittext);
        publish_input_edittext.setOnClickListener(this);
        publish_publish_max_nine_pic = (ImageView) findViewById(R.id.publish_publish_max_nine_pic);
        publish_publish_max_nine_pic.setOnClickListener(this);
        publish_name_edittext = (EditText) findViewById(R.id.publish_name_edittext);
        publish_name_edittext.setOnClickListener(this);
        publish_price_edittext = (EditText) findViewById(R.id.publish_price_edittext);
        publish_price_edittext.setOnClickListener(this);
        publish_keyword_edittext = (EditText) findViewById(R.id.publish_keyword_edittext);
        publish_keyword_edittext.setOnClickListener(this);
        publish_brand_text = (TextView) findViewById(R.id.publish_brand_text);
        publish_brand_text.setOnClickListener(this);
        publish_color_edittext = (EditText) findViewById(R.id.publish_color_edittext);
        publish_color_edittext.setOnClickListener(this);
        publish_news_text = (TextView) findViewById(R.id.publish_news_text);
        publish_news_text.setOnClickListener(this);
        publish_people_text = (TextView) findViewById(R.id.publish_people_text);
        publish_people_text.setOnClickListener(this);
        publish_user_protocal_text = (TextView) findViewById(R.id.publish_user_protocal_text);
        publish_user_protocal_text.setOnClickListener(this);
        publish_release_btn = (Button) findViewById(R.id.publish_release_btn);
        publish_release_btn.setOnClickListener(this);
    }

    private void initData(final int parentid) {
        // 通过传过来的parentid来展示页面

        JSONObject order = new JSONObject();
        try {
            order.put("sort", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
            jsonObject.put("parentid", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(PublishPublishAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGoodsCateify)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(PublishPublishAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        classify_online_view.setVisibility(View.GONE);
//                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("一级附件", "" + response);
                        Gson gson = new Gson();
                        AttachmentModel attachmentModel = gson.fromJson(response, AttachmentModel.class);
                        if (attachmentModel.getCode() == 1) {
                            // 根据穿过来的parentid来取数据
                            for (int i = 0; i < attachmentModel.getCategoryList().size(); i++) {
                                if (attachmentModel.getCategoryList().get(i).getCategoryid() == parentid) {
                                    dataSourcess = attachmentModel.getCategoryList().get(i).getAttachList();
                                }
                            }


                        } else if (attachmentModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(PublishPublishAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (attachmentModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(PublishPublishAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }


    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout) findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.navi_title);
        title.setText("发布商品");
        LinearLayout rights = (LinearLayout) findViewById(R.id.navi_right_pic_click_lay);
        rights.setOnClickListener(this);
    }

    // TODO*********************************点击发布商品**********************************************
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
        } else if (requestCode == 666) {
            if (resultCode == RESULT_OK || data != null) {
                String brand = data.getStringExtra("BRAND");
                LogUtil.e("品牌" + brand);
                publish_brand_text.setText(brand);
            }

        } else if (requestCode == 667) {
            if (resultCode == RESULT_OK || data != null) {
                chengsexxxx = data.getStringExtra("CHENGSE");
                publish_news_text.setText(chengsexxxx);
            }
        } else if (requestCode == 668) {
            if (resultCode == RESULT_OK || data != null) {
                peoplexxxx = data.getStringExtra("CHENGSE");
                publish_people_text.setText(peoplexxxx);
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

    /**
     * 打开相机
     */
    private String mCameraPicPath;

    private void choosePhotoFromCamera() {
        MQUtils.closeKeyboard(PublishPublishAC.this);

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
        mResults.add(mCameraPicPath);
    }


    // TODO***********************************点击选择照片********************************************
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
        Intent intent = new Intent(PublishPublishAC.this, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images", dataSource);
        intent.putExtra("position", position);
        startActivityForResult(intent, 777);
    }


    // TODO*********************************点击如何传图**********************************************
    private void howToPublish() {
        Intent intent = new Intent(this, HowToReleasePicAC.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_right_pic_click_lay:
                howToPublish();
                break;
            case R.id.navi_back:
                SharedPreferencesUtils.setParam(this, "RESULT", "");
                finish();
                break;
            case R.id.publish_publish_add_pic_imageview:
                addPic();
                break;
            case R.id.publish_publish_howto_pic_imageview:
                howToPublish();
                break;
            case R.id.publish_category:
                startActivity(PublishCateoryAC.class, 222);
                break;
            case R.id.publish_brand:
                startActivity(PublishBrandAC.class, 666);
                break;
            case R.id.publish_material:
                Intent intent = new Intent(PublishPublishAC.this, CheckRecycleViewAC.class);
                intent.putExtra("CHENGSE", 3);
                startActivityForResult(intent, 667);
                break;
            case R.id.publish_suitpersonal:
                Intent intents = new Intent(PublishPublishAC.this, CheckRecycleViewAC.class);
                intents.putExtra("CHENGSE", 4);
                startActivityForResult(intents, 668);
                break;
            case R.id.publish_file:
                // 先根据选择的类别来传值
                if (CATEGORYID <= 0) {
                    initSnackBar("你还没有选择类别！");
                    return;
                } else {
                    initData(CATEGORYID);
                    Intent intentx = new Intent(PublishPublishAC.this, AttachMentAC.class);
                    intentx.putExtra("CATEID", CATEGORYID);
                    startActivity(intentx);
                }

                break;
            case R.id.publish_user_protocal_text:
                startActivity(UserPrctocalAC.class, 222);
                break;
            default:
                break;
            case R.id.publish_release_btn:
                check();
                break;
            case R.id.publish_publish_max_nine_pic:
                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "必须九张图!", Color.WHITE, Color.parseColor("#16a6ae"));
                break;

        }
    }


    private void addPic() {
        sheetView = new LikeIOSSheetDialog.Builder(PublishPublishAC.this).setTitle("选择照片").addMenu("从手机相册选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                startActivityForResult(MQPhotoPickerActivity.newIntent(PublishPublishAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
            }
        }).addMenu("拍一张", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                choosePhotoFromCamera();
            }
        }).create();
        sheetView.show();
    }

    private void startActivity(Class<?> cls, int CALLBACKCODE) {
        Intent intent = new Intent(PublishPublishAC.this, cls);
        startActivityForResult(intent, CALLBACKCODE);
    }

    String catetext = "";

    @Override
    protected void onResume() {
        // 回调传至
        catetext = String.valueOf(SharedPreferencesUtils.getParam(this, "RESULT", ""));
        cateoryTxt.setText(catetext);
        CATEGORYID = (int) SharedPreferencesUtils.getParam(this, "CATEGORYID", 0);
        LogUtil.e("回调的取值" + CATEGORYID);
        BRANDID = (int) SharedPreferencesUtils.getParam(this, "BRANDID", 0);
        LogUtil.e("成色" + chengse);
        FUJIAN = String.valueOf(SharedPreferencesUtils.getParam(this, "FUJIAN", ""));
        LogUtil.e("附件参数" + FUJIAN);
        BAOBEIFUJIAN = String.valueOf(SharedPreferencesUtils.getParam(this, "BAOBEIFUJIAN", ""));

        LogUtil.e("附件参数附件参数" + BAOBEIFUJIAN);


        super.onResume();
    }

    private void check() {
        // validate
        descript = publish_input_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(descript)) {
            initSnackBar("你还没有描述你的宝贝！");
            return;
        }
        // 判断用户是否选了九张图
        if (mResults.size() != 9) {
            initSnackBar("必须九张图！");
            return;
        }
        goodsName = publish_name_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(goodsName)) {
            initSnackBar("你还没有填写宝贝名称！");
            return;
        }
        goodsprice = publish_price_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(goodsprice)) {
            initSnackBar("你还没有填写专柜价！");
            return;
        }
        goodsKeyword = publish_keyword_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(goodsKeyword)) {
            initSnackBar("你还没有填写关键词！");
            return;
        }
        String goodsCate = cateoryTxt.getText().toString().trim();
        LogUtil.e("类别" + catetext);
        if (catetext == "" || catetext.trim().equals("(必选)")) {
            initSnackBar("你还没有选择类别！");
            return;
        }
        goodsBrand = publish_brand_text.getText().toString().trim();
        if (goodsBrand.equals("（必选）")) {
            initSnackBar("你还没有选择品牌");
            return;
        }
        goodsColor = publish_color_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(goodsColor)) {
            initSnackBar("你还没有填写颜色！");
            return;
        }

        chengse = publish_news_text.getText().toString().trim();
        if (chengse.equals("（必选）")) {
            initSnackBar("你还没有选择成色！");
            return;
        }
        people = publish_people_text.getText().toString().trim();
        if (people.equals("（必选）")) {
            initSnackBar("你还没有选择适用人群！");
            return;
        }

        // TODO 开始发布商品
        // 先上传图片，再发请求
        uploadNinePic();


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
                    String url = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    urlList.add(url);
                    if (number < 8) {
                        number += 1;
                        uploadImageToQiniu(uploadDatasource.get(number), creatTokenLocal(), number);
                    } else {
                        // 上传完成
                        LogUtil.e("图片的数组" + urlList);
                        initReleaseData(urlList);

                    }
                } else {
                    // 上传失败
                    initSnackBar("发布失败！");
                }
            }
        }, null);
    }

    private void initReleaseData(ArrayList<String> urlList) {
        //        {
//            "name": "string, 必填, 产品名称",
//                "keywrod": "string, 必填, 关键字描述",
//                "imgList": "array, 必填, 图片名称列表，至少一张图片，最多9张",
//                "counterPrice": "integer, 必填, 专柜价, 单位为分",
//                "description": "string, 必填, 产品描述",
//                "categoryid": "integer, 必填，所选择的分类的id, 选择分类后, 根据分类的parentid, 通过调用获取分类接口，获取分类附件所需要的数据, 后面的attachList为必填数据",
//                "brandid": "integer, 必填, 选择的品牌的id",
//                "color": "string, 必填, 产品颜色",
//                "condition": "1、2、3、4、5和6, 数字, 必填, 成色, 对应99成新，98成新, ......",
//                "crowd": "1、2、3, 必填, 数字，人群, 对应所有人，男士和女士",
//                "attachList": [
//            {
//                "attributeName": "属性名",
//                    "attributeValueList": [
//                "属性值1",
//                        "属性值2",
//                        "..."
//                ]
//            },
//            {
//                "attributeName": "属性名"
//            }
//            ]
//        }

//        Gson gson = new Gson();
//         gson.fromJson(BAOBEIFUJIAN,);

//        try {
//            JSONArray arr = new JSONArray(BAOBEIFUJIAN);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        // 附件
//        JSONArray arr = new JSONArray();
////        arr.put("保修啊");
////        arr.put("发票");
//        arr = J;


        JSONObject attributeArr = new JSONObject();
        try {
            attributeArr.put("attributeName", dataSourcess.get(dataSourcess.size()-1).getAttributeName());
            attributeArr.put("attributeValueList", new JSONArray(BAOBEIFUJIAN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(attributeArr);


        // 图片数组
        JSONArray jsonArray1 = new JSONArray();
        for (int i = 0; i < urlList.size(); i++) {
            String li = urlList.get(i);
            JSONObject jsonObjects = new JSONObject();
            try {
                jsonObjects.put("image", li);
                jsonArray1.put(i, jsonObjects);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 附件
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", goodsName);
            jsonObject.put("keyword", goodsKeyword);
            jsonObject.put("imgList", jsonArray1);
            jsonObject.put("counterPrice", Integer.valueOf(goodsprice));
            jsonObject.put("description", descript);
            jsonObject.put("categoryid", CATEGORYID);
            jsonObject.put("brandid", BRANDID);
            jsonObject.put("color", goodsColor);
            // 成色
            int cheng = -1;
            switch (chengsexxxx) {
                case "99成新（未使用）":
                    cheng = 1;
                    break;
                case "95新":
                    cheng = 2;
                    break;
                case "9成新":
                    cheng = 3;
                    break;
                case "85成新":
                    cheng = 4;
                    break;
                case "8成新":
                    cheng = 5;
                    break;
                default:
                    break;
            }
            jsonObject.put("condition", cheng);

            int p = -1;
            switch (peoplexxxx) {
                case "所有人":
                    p = 1;
                    break;
                case "男士":
                    p = 2;
                    break;
                case "女士":
                    p = 3;
                    break;
            }
            jsonObject.put("crowd", p);
            jsonObject.put("attachList", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.e("上传参数结果" + jsonObject.toString());
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ReleaseGoods)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("发布商品", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {


                        } else if (requestStatueModel.getCode() == 0) {
                        } else if (requestStatueModel.getCode() == 911) {
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

    public void initSnackBar(String content) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), content, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
