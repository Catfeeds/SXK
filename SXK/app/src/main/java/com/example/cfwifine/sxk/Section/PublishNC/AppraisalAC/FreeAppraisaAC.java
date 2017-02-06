package com.example.cfwifine.sxk.Section.PublishNC.AppraisalAC;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishBrandAC;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.ImageBrowseActivity;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;
import com.meiqia.meiqiasdk.util.MQUtils;

import java.io.File;
import java.util.ArrayList;

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
    private TextView appraisal_txt;
    private RelativeLayout appraisal_cateory;
    private TextView appraisal_cateory_txt;
    private RelativeLayout appraisal_brand;
    private Button publish_appraisa_btn;
    private LinearLayout activity_publish_publish_ac;
    LikeIOSSheetDialog sheetView;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
    ArrayList<String> dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_appraisa_ac);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
        appraisal_txt = (TextView) findViewById(R.id.appraisal_txt);
        appraisal_cateory = (RelativeLayout) findViewById(R.id.appraisal_cateory);
        appraisal_cateory_txt = (TextView) findViewById(R.id.appraisal_cateory_txt);
        appraisal_brand = (RelativeLayout) findViewById(R.id.appraisal_brand);
        appraisal_brand.setOnClickListener(this);
        publish_appraisa_btn = (Button) findViewById(R.id.publish_appraisa_btn);
        activity_publish_publish_ac = (LinearLayout) findViewById(R.id.activity_publish_publish_ac);

        publish_appraisa_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_appraisa_btn:

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
            case R.id.appraisal_brand:
                Intent intent = new Intent(this, PublishBrandAC.class);
                startActivity(intent);
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
        String edittext = publish_input_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(edittext)) {

            return;
        }

        // TODO validate success, do something


    }
}
