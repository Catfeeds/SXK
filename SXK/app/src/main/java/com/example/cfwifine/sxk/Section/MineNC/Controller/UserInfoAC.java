package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.UserInfoRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_Sex;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_nickname;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.CustomDialog_phone;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.datepicker.DatePicker;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.datepicker.TimePicker;
import com.example.cfwifine.sxk.Utils.FileManager;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.ToastUtil;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;



import android.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;




public class UserInfoAC extends AppCompatActivity implements View.OnClickListener {

    RecyclerView userRV;
    UserInfoRecycleViewAdapter userInfoRecycleViewAdapter;
    ArrayList<String> itemSource = new ArrayList<>();
    String[] itemString = {"昵称", "性别", "生日", "个人简介", "手机号码",
            "修改密码"};
    ArrayList<String> callBackString = new ArrayList<>();



    private DatePicker dp_test;
    private TimePicker tp_test;
    private Button tv_ok;
    private TextView tv_cancel;	//确定、取消button
    private Calendar calendars;
    LikeIOSSheetDialog shitView;

    private String selectDate,selectTime;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private int currentHour,currentMinute,currentDay,selectHour,selectMinute,selectDay;

    private static final int REQUEST_CODE = 732;
    private static final int TAKE_PHOTO = 733;
    private ArrayList<String> mResults = new ArrayList<>();

    Bitmap photo;
    CircleImageView header;
    String mImageCachePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_ac);
        calendars = Calendar.getInstance();
        initView();
        configurationNaviTitle();
        initRecycleData();
        initRecycleView();
    }
    private  void initView(){
        header = (CircleImageView)findViewById(R.id.userInfoHeaderView);
        header.setOnClickListener(this);
    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("个人资料");
        TextView rightTitle = (TextView)findViewById(R.id.navi_right);
        rightTitle.setText("");
    }
    private void initRecycleData() {
        for (int i = 0; i<itemString.length;i++){
            itemSource.add(itemString[i]);
        }
    }

    // TODO**************************************初始化分栏******************************************
    private void initRecycleView() {
        userRV = (RecyclerView)findViewById(R.id.user_info_recycleview);
        userRV.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        userInfoRecycleViewAdapter = new UserInfoRecycleViewAdapter(itemSource,callBackString);
        userRV.setAdapter(userInfoRecycleViewAdapter);
        userInfoRecycleViewAdapter.setOnItemClickListener(new UserInfoRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Log.e("点击了",""+position);
                switch (position){
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
                        initChangePsw(5);
                        break;
                    default:
                        break;
                }
            }
        });


    }
    // TODO**************************************自定义弹出框******************************************
    private void initSexDialog(){

        CustomDialog_Sex customDialog_sex = new CustomDialog_Sex(this, new CustomDialog_Sex.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("性别",""+id);
            }
        },R.style.Dialog);
        customDialog_sex.show();
    }
    private void initNickNameDialog() {

        CustomDialog_nickname customDialog_nickname = new CustomDialog_nickname(this, new CustomDialog_nickname.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("返回的",""+id);
            }
        },R.style.Dialog);
        customDialog_nickname.show();
    }
    private  void  initBirthdayDialog(){
        ToastUtil.show(this,"asfdasdfasdfasdf");
        View view1 = View.inflate(UserInfoAC.this, R.layout.datepicker_layout, null);
        selectDate = calendars.get(Calendar.YEAR) + "年" +( calendars.get(Calendar.MONTH)+1) + "月"
                + calendars.get(Calendar.DAY_OF_MONTH) + "日";
        //选择时间与当前时间的初始化，用于判断用户选择的是否是以前的时间，如果是，弹出toss提示不能选择过去的时间
        selectDay = currentDay = calendars.get(Calendar.DAY_OF_MONTH);
        selectMinute = currentMinute = calendars.get(Calendar.MINUTE);
        selectHour = currentHour = calendars.get(Calendar.HOUR_OF_DAY);

        selectTime = currentHour + ":" + ((currentMinute < 10)?("0"+currentMinute):currentMinute) + "";
        dp_test = (DatePicker)view1.findViewById(R.id.dp_test);
        tp_test = (TimePicker)view1.findViewById(R.id.tp_test);
        tv_ok = (Button) view1.findViewById(R.id.tv_ok);
        tv_cancel = (TextView) view1.findViewById(R.id.tv_cancel);
        //设置滑动改变监听器
        dp_test.setOnChangeListener(dp_onchanghelistener);
        tp_test.setOnChangeListener(tp_onchanghelistener);

        AlertDialog.Builder builder=new AlertDialog.Builder(this).setView(view1);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();

        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(selectDay == currentDay ){	//在当前日期情况下可能出现选中过去时间的情况
                    if(selectHour > currentHour){
                        Toast.makeText(getApplicationContext(), "不能选择未来的时间\n  请重新选择",Toast.LENGTH_LONG ).show();
                    }else if( (selectHour == currentHour) && (selectMinute > currentMinute) ){
                        Toast.makeText(getApplicationContext(), "不能选择未来的时间\n 请重新选择", Toast.LENGTH_LONG).show();
                    }else{

                        Log.e("获取的时间",""+selectDate+selectTime);
                        alertDialog.dismiss();
                    }
                }else{
                    Log.e("获取的时间",""+selectDate+selectTime);
                    alertDialog.dismiss();
                }
            }
        });

        //点击取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                alertDialog.dismiss();
            }
        });
    }
    private void initPersonalIntro(){
        Intent intent = new Intent(UserInfoAC.this,PersonalIntroAndChangePswAC.class);
        startActivity(intent);
    }
    private void initChangePhone(){
        CustomDialog_phone customDialog_phone = new CustomDialog_phone(this, new CustomDialog_phone.ICustomDialogEventListener() {
            @Override
            public void customDialogEvent(String id) {
                Log.e("返回",""+id);
            }
        },R.style.Dialog);
        customDialog_phone.show();
    }
    private void initChangePsw(int pos){
        Intent intent = new Intent(UserInfoAC.this,PersonalIntroAndChangePswAC.class);
        intent.putExtra("CHANGEPSW",pos);
        startActivity(intent);
    }
    private void initIOSSheetDialog(){
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
        Intent intent = new Intent(UserInfoAC.this, ImagesSelectorActivity.class);
        // 选择数量
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
        // min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
        // show camera or not
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
        // pass current selected images as the initial value
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
        // start the selector
        startActivityForResult(intent, REQUEST_CODE);

    }
    String mPicDirectory = "SXK";
    private void takePhoto(){
        /**
         * 在启动拍照之前最好先判断一下sdcard是否可用
         */
        String fileName = "sxk" + ".jpg";
        mImageCachePath = FileManager.getSaveImagePath() + fileName;
        Log.e("路径",""+mImageCachePath);
//        String state = Environment.getExternalStorageState(); //拿到sdcard是否可用的状态码
//        if (state.equals(Environment.MEDIA_MOUNTED)){   //如果可用

//            File file = new File(mPicDirectory);
//            if(!file.exists()) {//目录不存在则创建该目录及其不存在的父目录
//                file.mkdirs();
//            }
            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            Uri imageUri = Uri.fromFile(new File(
                    mImageCachePath));
            //这个参数就是转移保存地址的，对应Value中保存的URI就是指定的保存地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
//        }else {
//            Toast.makeText(UserInfoAC.this,"SD卡不可用",Toast.LENGTH_SHORT).show();
//        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE:
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
                header.setImageBitmap(BitmapFactory.decodeFile(new File(mResults.get(0)).getPath()));
                break;
            case TAKE_PHOTO:
//                //两种方式 获取拍好的图片
//                if (data.getData() != null|| data.getExtras() != null){ //防止没有返回结果
//                    Uri uri =data.getData();
//                    if (uri != null) {
//                        photo = BitmapFactory.decodeFile(uri.getPath()); //拿到图片
//                        Log.e("拍的图",""+photo);
//                        header.setImageBitmap(photo);
//                    }
//                    if (photo == null) {
//                        Bundle bundle =data.getExtras();
//                        if (bundle != null){
//                            photo =(Bitmap) bundle.get("data");
//                        } else {
//                            Toast.makeText(getApplicationContext(), "找不到图片",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    //处理图片
//                    //裁剪图片
//                }


                Uri imageUri=Uri.fromFile(new File(mImageCachePath));
                header.setImageBitmap(ImageFactory.getBitmapFormUri(this,imageUri,false));
//
                Log.e("图片路径",""+mImageCachePath);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    DatePicker.OnChangeListener dp_onchanghelistener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week) {
            selectDay = day;
            selectDate = year + "年" + month + "月" + day + "日";
        }
    };
    TimePicker.OnChangeListener tp_onchanghelistener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10)?("0"+minute):minute) ;
            selectHour = hour;
            selectMinute = minute;
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
