package com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Controller.CommunityPublishTopicAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.meiqia.meiqiasdk.activity.MQPhotoPickerActivity;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_back_goods_ac);
        orderid = getIntent().getIntExtra("ORDERID",-1);
        LogUtil.e("orderid"+orderid);
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
        reback_howto_pic_imageview = (ImageView) findViewById(R.id.reback_howto_pic_imageview);
        reback_max_nine_pic = (ImageView) findViewById(R.id.reback_max_nine_pic);
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

                break;
            case R.id.navi_back:
                finish();
                break;
        }
    }

    private void addPic() {
        sheetView = new LikeIOSSheetDialog.Builder(ReBackGoodsAC.this).setTitle("选择照片").addMenu("从手机相册选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetView.dismiss();
                // 安卓6.0权限适配
                if(ContextCompat.checkSelfPermission(ReBackGoodsAC.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ReBackGoodsAC.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                }else{//权限被授予
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
//                    choosePhotoFromCamera();
                }
            }
        }).create();
        sheetView.show();
    }

    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                //权限被授予
                startActivityForResult(MQPhotoPickerActivity.newIntent(ReBackGoodsAC.this, null, 9, mResults, "完成"), REQUEST_CODE);

            } else
            {
                // Permission Denied
                Toast.makeText(ReBackGoodsAC.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void submit() {
        // validate
        String edt = reback_ordernumber_edt.getText().toString().trim();
        if (TextUtils.isEmpty(edt)) {
            Toast.makeText(this, "  请输入单号", Toast.LENGTH_SHORT).show();
            return;
        }

        String edts = reback_comment_edt.getText().toString().trim();
        if (TextUtils.isEmpty(edts)) {
            Toast.makeText(this, "  请输入评价", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
