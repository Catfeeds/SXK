package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;

public class PublishPublishAC extends AppCompatActivity implements View.OnClickListener {

    PublishHowToPupWindow publishHowToPupWindow;
    ImageView addPic,howtoAdd;
    RelativeLayout category,brand,color,material,suitpersonal,file;
    TextView cateoryTxt;
    private static final int REQUEST_CODE = 732;
    private ArrayList<String> mResults = new ArrayList<>();
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
    private void addPic() {
        Intent intent = new Intent(PublishPublishAC.this, ImagesSelectorActivity.class);
        // 选择数量
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 9);
        // min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
        // show camera or not
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // TODO*******************************************************************************

    // TODO*********************************点击如何传图**********************************************
    private void howToPublish() {
        publishHowToPupWindow = new PublishHowToPupWindow(PublishPublishAC.this,itemsOnClick);
        publishHowToPupWindow.showAtLocation(PublishPublishAC.this.findViewById(R.id.activity_publish_publish_ac), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
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
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            publishHowToPupWindow.dismiss();
            switch (v.getId()) {
//                case R.id.:
//                    publishHowToPupWindow.dismiss();
//                    break;
                default:
                    break;
            }
        }
    };
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
