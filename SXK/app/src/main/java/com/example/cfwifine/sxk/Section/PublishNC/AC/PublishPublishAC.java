package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerPreviewActivity;
import com.meiqia.meiqiasdk.util.MQUtils;

import java.io.File;
import java.util.ArrayList;

public class PublishPublishAC extends AppCompatActivity implements View.OnClickListener {

    ImageView addPic,howtoAdd;
    RelativeLayout category,brand,color,material,suitpersonal,file;
    TextView cateoryTxt;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
    ArrayList<String> dataSource;
    FourGridlayout nineGridlayout;
    LikeIOSSheetDialog sheetView;

    File imageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_publish_ac);
        SharedPreferencesUtils.setParam(this,"RESULT"," (必选) ");
        configurationNaviTitle();
        initView();
    }
    // TODO*********************************配置界面**********************************************
    public void initView(){
        addPic = (ImageView)findViewById(R.id.publish_publish_add_pic_imageview);
        howtoAdd = (ImageView )findViewById(R.id.publish_publish_howto_pic_imageview);
        addPic.setOnClickListener(this);
        howtoAdd.setOnClickListener(this);

        category = (RelativeLayout)findViewById(R.id.publish_category);
        brand = (RelativeLayout)findViewById(R.id.publish_brand);
        material = (RelativeLayout)findViewById(R.id.publish_material);
        suitpersonal = (RelativeLayout)findViewById(R.id.publish_suitpersonal);
        file = (RelativeLayout)findViewById(R.id.publish_file);
        category.setOnClickListener(this);
        brand.setOnClickListener(this);
        material.setOnClickListener(this);
        suitpersonal.setOnClickListener(this);
        file.setOnClickListener(this);
        cateoryTxt = (TextView)findViewById(R.id.cateory_txt);


        nineGridlayout = (FourGridlayout)findViewById(R.id.results);
    }


    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("发布商品");
        LinearLayout rights = (LinearLayout)findViewById(R.id.navi_right_pic_click_lay);
        rights.setOnClickListener(this);
    }
    // TODO*********************************点击发布商品**********************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = MQPhotoPickerActivity.getSelectedImages(data);
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
//                mResults = MQPhotoPickerPreviewActivity.getSelectedImages(data);
            }
        }

        if (mResults.size()!=0){
            nineGridlayout.setVisibility(View.VISIBLE);
            addPic.setVisibility(View.GONE);
            howtoAdd.setVisibility(View.GONE);

            dataSource = new ArrayList<>();
            for (int i = 0; i<mResults.size();i++){
                dataSource.add(mResults.get(i));
            }
            dataSource.add(mResults.size(),"TAG");
            dataSource.add(mResults.size()+1,"MORE");
            // 初始化九宫格
            initFourGridView();
        }else {
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
    private  void  initFourGridView(){
        FourGridAdapter nineGridAdapter = new FourGridViewAdapter(this,dataSource);
        nineGridlayout.setAdapter(nineGridAdapter);
        nineGridlayout.setOnItemClickListerner(new FourGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了",""+position);
//                ImageBrowseActivity.startActivity(PublishPublishAC.this,dataSource,position);
                Log.e("dataSource",""+dataSource);
                int s = dataSource.size()-2;
                if (position == s){
                    File file =  Environment.getExternalStorageDirectory();
                    if (mResults.size()>=9){
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }else {
                        addPic();
                    }
//                    startActivityForResult(MQPhotoPickerActivity.newIntent(PublishPublishAC.this, null, 9, mResults, "完成"), REQUEST_CODE);
                }else if (position == s+1){
                    SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "最多只能选择九张哦!", Color.WHITE, Color.parseColor("#16a6ae"));
                } else{
                    PreviewPic(position);
//                    startActivityForResult(MQPhotoPickerPreviewActivity.newIntent(PublishPublishAC.this, mResults.size(), mResults, mResults, position, "完成", false), 777);

                }
            }
        });

    }
    // TODO***********************************点击预览照片********************************************
    private void PreviewPic(int position) {
        Intent intent = new Intent(PublishPublishAC.this,ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images",dataSource);
        intent.putExtra("position",position);
        startActivityForResult(intent,777);
    }


    // TODO*********************************点击如何传图**********************************************
    private void howToPublish() {
        Intent intent = new Intent(this,HowToReleasePicAC.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_right_pic_click_lay:
                howToPublish();
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.publish_publish_add_pic_imageview:
                addPic();
                break;
            case R.id.publish_publish_howto_pic_imageview:
                howToPublish();
                break;
            case R.id.publish_category:
                startActivity(PublishCateoryAC.class);
                break;
            case R.id.publish_brand:
                startActivity(PublishBrandAC.class);
                break;
            case R.id.publish_material:
                startActivity(CheckRecycleViewAC.class);
                break;
            case R.id.publish_suitpersonal:
                startActivity(CheckRecycleViewAC.class);
                break;
            case R.id.publish_file:

                break;

            default:
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

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(PublishPublishAC.this, cls);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        // 回调传至
        String s = String.valueOf(SharedPreferencesUtils.getParam(this,"RESULT",""));
        cateoryTxt.setText(s);
        super.onResume();
    }
}
