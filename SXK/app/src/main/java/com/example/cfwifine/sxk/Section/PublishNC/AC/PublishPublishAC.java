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
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_publish_ac);
        mloading = LoadingUtils.createLoadingDialog(this, "正在发布...");
//        SharedPreferencesUtils.setParam(this, "RESULT", " (必选) ");
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
                String chengse = data.getStringExtra("CHENGSE");
                publish_news_text.setText(chengse);
            }
        } else if (requestCode == 668) {
            if (resultCode == RESULT_OK || data != null) {
                String people = data.getStringExtra("CHENGSE");
                publish_people_text.setText(people);
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
                Intent intentx = new Intent(PublishPublishAC.this, AttachMentAC.class);
                startActivity(intentx);
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
        super.onResume();
    }

    private void check() {
        // validate
        String edittext = publish_input_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(edittext)) {
            initSnackBar("你还没有描述你的宝贝！");
            return;
        }
        // 判断用户是否选了九张图
        if (mResults.size() != 9) {
            initSnackBar("必须九张图！");
            return;
        }
        String name = publish_name_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            initSnackBar("你还没有填写宝贝名称！");
            return;
        }
        String cate = cateoryTxt.getText().toString().trim();
        LogUtil.e("类别" + catetext);
        if (catetext == "" || catetext.trim().equals("(必选)")) {
            initSnackBar("你还没有选择类别！");
            return;
        }
        String brand = publish_brand_text.getText().toString().trim();
        if (brand.equals("（必选）")) {
            initSnackBar("你还没有选择品牌");
            return;
        }
        String color = publish_color_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(color)) {
            initSnackBar("你还没有填写颜色！");
            return;
        }

        String chengse = publish_news_text.getText().toString().trim();
        if (chengse.equals("（必选）")) {
            initSnackBar("你还没有选择成色！");
            return;
        }
        String people = publish_people_text.getText().toString().trim();
        if (people.equals("（必选）")) {
            initSnackBar("你还没有选择适用人群！");
            return;
        }

        // TODO 开始发布商品
        // 先上传图片，再发请求
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
        uploadNinePic();


    }

    private void uploadNinePic() {
        // 要上传的byte类型的图片数组, 处理后图片大小在100-400K之间
        uploadDatasource = new ArrayList<>();
        for (int i = 0; i < mResults.size(); i++) {
            Bitmap bitmap = ImageFactory.getBitmapFormUri(this, Uri.fromFile(new File(mResults.get(i).toString())), false);
            byte[] b = ImageFactory.bitmapToByteAAA(bitmap);
            uploadDatasource.add(i, b);
        }
        LogUtil.e("准备上传的图片的数组"+uploadDatasource);
        urlList = new ArrayList<>();
        // 开始上传
        for (int i = 0;i<uploadDatasource.size();i++){
            uploadImageToQiniu(uploadDatasource.get(i),creatTokenLocal(),i);
        }
        LogUtil.e("上传完成"+urlList);
    }

    private void uploadImageToQiniu(byte[] filePath, String token, final int s) {
        UploadManager uploadManager = new UploadManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        final String key = "sxk_userPic_" + sdf.format(new Date())+s;
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {

            @Override
            public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
                // TODO Auto-generated method stub
                if (key.toString().equals(arg0)) {
                    // 上传成功！
                    String url = BaseInterface.ClassfiyGetAllHotBrandImgUrl + arg0;
                    urlList.add(url);
                } else {
                    // 上传失败
                    initSnackBar("发布失败！");
                }
            }
        }, null);
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
