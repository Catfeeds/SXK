package com.example.cfwifine.sxk.Section.PublishNC.AppraisalAC;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.CreateOrderModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.MineNC.Controller.AddressSettingCommomAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressDetailModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.CheckRecycleViewAC;
import com.example.cfwifine.sxk.Section.PublishNC.AC.PublishBrandAC;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AppraisasCheckRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.CuringAC.CuringPayOrderAC;
import com.example.cfwifine.sxk.Section.PublishNC.CuringAC.WarningAC;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AppraisalModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.pingplusplus.android.PaymentActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

import static com.pingplusplus.android.Pingpp.REQUEST_CODE_PAYMENT;

public class AppraisasAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private ImageView appraisal_head_pic;
    private TextView appraisal_txt;
    private RelativeLayout appraisal_cateory;
    private TextView appraisal_cateory_txt;
    private RelativeLayout appraisal_brand;
    private RecyclerView appraisal_rv;
    private TextView pay;
    private LinearLayout activity_appraisal;
    Dialog dialog;
    private AppraisalModel appraisalModel= null;
    private ArrayList<TestModel> nameList;
    private Integer[] picArrList;
    private ArrayList<Integer> picList;
    private ArrayList<String> appCateList;
    private String appraisaCate;
    private int BRANDID=-1;
    private String BRANDNAME="";
    private TextView appraisal_total_money;
    private LinearLayout appraisal_selected_address;
    private String ADDRESSS="";
    private int RECEIVEDID=-1;
    private TextView address;
    private String PAYTYPE="";
    private int APPRAISACATEID=-1;
    private int TOTAL;
    private String datas;
    private AddressDetailModel addressDetailModel;
    private int orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraisal);
        SharedPreferencesUtils.setParam(this, "APPRAISACATE", "");
        SharedPreferencesUtils.setParam(this, "BRANDID", -1);
        SharedPreferencesUtils.setParam(this, "BRANDNAME", "");
        SharedPreferencesUtils.setParam(this, "DEFAULTADDRESS", "");
        SharedPreferencesUtils.setParam(this, "RECRIVEDID", -1);
        SharedPreferencesUtils.setParam(this,"APPRAISACATEID",-1);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("中检鉴定");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right.setText("设置");
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        navi_right_lays.setOnClickListener(this);
        appraisal_head_pic = (ImageView) findViewById(R.id.appraisal_head_pic);
        appraisal_txt = (TextView) findViewById(R.id.appraisal_txt);
        appraisal_cateory = (RelativeLayout) findViewById(R.id.appraisal_cateory);
        appraisal_cateory.setOnClickListener(this);
        appraisal_cateory_txt = (TextView) findViewById(R.id.appraisal_cateory_txt);
        appraisal_brand = (RelativeLayout) findViewById(R.id.appraisal_brand);
        appraisal_brand.setOnClickListener(this);
        appraisal_rv = (RecyclerView) findViewById(R.id.appraisal_rv);
        pay = (TextView) findViewById(R.id.pay);
        pay.setOnClickListener(this);
        activity_appraisal = (LinearLayout) findViewById(R.id.activity_appraisal);
        initRecycleView();
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        initRecycleData();
        appraisal_total_money = (TextView) findViewById(R.id.appraisal_total_money);
        appraisal_total_money.setOnClickListener(this);
        appraisal_selected_address = (LinearLayout) findViewById(R.id.appraisal_selected_address);
        appraisal_selected_address.setOnClickListener(this);
        address = (TextView) findViewById(R.id.address);
        initDefaultAddress();
    }

    private void initRecycleView() {
        picArrList = new Integer[]{
                R.drawable.weixin, R.drawable.zhifubao, R.drawable.yue
        };
        picList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            picList.add(i, picArrList[i]);
        }
        String[] name = new String[]{
                "微信支付", "支付宝", "余额支付"
        };
        nameList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TestModel testModel = new TestModel(name[i], false);
            nameList.add(i, testModel);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        appraisal_rv.setLayoutManager(layoutManager);
        AppraisasCheckRecycleViewAdapter appraisasCheckRecycleViewAdapter = new AppraisasCheckRecycleViewAdapter(this, picList, nameList);
        appraisal_rv.setAdapter(appraisasCheckRecycleViewAdapter);
        appraisasCheckRecycleViewAdapter.setOnItemClickListener(new AppraisasCheckRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positionl, String value) {
                if (positionl == 0) {
                    PAYTYPE = "wx";
                } else if (positionl == 1) {
                    PAYTYPE = "alipay";
                } else if (positionl == 2) {
                    PAYTYPE = "upacp";
                }
            }
        });
    }

    // 获取收货地址
    private void initDefaultAddress() {
        JSONObject js = new JSONObject();
        try {
            js.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.SettingGetReceiveGoods)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Gson gson = new Gson();
                        addressDetailModel = gson.fromJson(response, AddressDetailModel.class);
                        if (addressDetailModel.getCode() == 1) {
                            L.e("详细信息" + response);
                            if (addressDetailModel.getReceiver() != null) {
                                RECEIVEDID = addressDetailModel.getReceiver().getReceiverid();
                                ADDRESSS = addressDetailModel.getReceiver().getName() + "  " + addressDetailModel.getReceiver().getMobile() + "\n" + addressDetailModel.getReceiver().getState() + addressDetailModel.getReceiver().getCity() + addressDetailModel.getReceiver().getDistrict() + addressDetailModel.getReceiver().getAddress();
                                address.setText(ADDRESSS);
                            } else {
                                address.setText("请选择收货地址");
                            }
                        } else if (addressDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }

    private void initRecycleData() {
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("setupid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.GetCenterSetting)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(AppraisasAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        dialog.dismiss();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("中间鉴定详情", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        appraisalModel = gson.fromJson(response, AppraisalModel.class);
                        if (appraisalModel.getCode() == 1) {
                            setValueForDetail();
                        } else if (appraisalModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(AppraisasAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (appraisalModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(AppraisasAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });
    }

    private void setValueForDetail() {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + appraisalModel.getSetup().getCampaign();
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(appraisal_head_pic);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appraisaCate = String.valueOf(SharedPreferencesUtils.getParam(this, "APPRAISACATE", ""));
        if (!appraisaCate.isEmpty()) {
            appraisal_txt.setText(appraisaCate);
            for (int i = 0; i < appraisalModel.getGenreList().size(); i++) {
                if (appraisaCate.trim().equals(appraisalModel.getGenreList().get(i).getName())) {
                    appraisal_total_money.setText("¥ " + String.valueOf((double) (Math.round(appraisalModel.getGenreList().get(i).getPrice()) / 100.0)));
                    TOTAL = appraisalModel.getGenreList().get(i).getPrice();
                }
            }
        }
        BRANDID = (int) SharedPreferencesUtils.getParam(this, "BRANDID", 0);
        BRANDNAME = String.valueOf(SharedPreferencesUtils.getParam(this, "BRANDNAME", ""));
        if (!BRANDNAME.isEmpty()) {
            appraisal_cateory_txt.setText(BRANDNAME);
        }
        initDefaultAddress();
        if (ADDRESSS == ""){
            address.setText("请输入收货地址");
        }
//        ADDRESSS = String.valueOf(SharedPreferencesUtils.getParam(this, "DEFAULTADDRESS", ""));
//        if (ADDRESSS != "") {
//            address.setText(ADDRESSS);
//        }
//        } else {
//            address.setText(ADDRESSS);
//        }
        RECEIVEDID = (int) SharedPreferencesUtils.getParam(this, "RECRIVEDID", -1);
        APPRAISACATEID = (int) SharedPreferencesUtils.getParam(this,"APPRAISACATEID",0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right_lays:
                if (appraisalModel.getSetup().getHelp() != null) {
                    Intent intent = new Intent(this, WarningAC.class);
                    intent.putExtra("WARNING", appraisalModel.getSetup().getHelp().toString());
                    startActivity(intent);
                }
                break;
            case R.id.appraisal_cateory:
                appCateList = new ArrayList<>();
                if (appraisalModel != null){
                    if (appraisalModel.getGenreList().size() != 0) {
                        for (int i = 0; i < appraisalModel.getGenreList().size(); i++) {
                            appCateList.add(i, appraisalModel.getGenreList().get(i).getName());
                        }
                        Intent intent = new Intent(AppraisasAC.this, CheckRecycleViewAC.class);
                        intent.putExtra("CHENGSE", 6);
                        intent.putStringArrayListExtra("APPRAISACATE", appCateList);
                        startActivity(intent);
                    } else {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "暂时还没有分类!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                }

                break;
            case R.id.appraisal_brand:
                Intent intent = new Intent(this, PublishBrandAC.class);
                startActivity(intent);
                break;
            case R.id.appraisal_selected_address:
                Intent intent1 = new Intent(this, AddressSettingCommomAC.class);
                startActivity(intent1);
                break;
            case R.id.pay:
                LogUtil.e("ceshi"+RECEIVEDID);
                LogUtil.e("ceshis"+APPRAISACATEID);
                createPayOrder();
                break;
            default:
                break;
        }
    }
    //
    private void createPayOrder() {
        if (APPRAISACATEID == -1){
            initSnackBar("你还没有选择类别!");
            return;
        }
        if (BRANDID == -1){
            initSnackBar("你还没有选择品牌！");
            return;
        }
        if (RECEIVEDID == -1){
            initSnackBar("地址不能为空！");
            return;
        }
        if (PAYTYPE == ""){
            initSnackBar("你还没有选择支付方式！");
            return;
        }
//        if (PAYTYPE == "alipay"){
            initOrder();
//        }



    }

    private void initOrder() {
        JSONObject js = new JSONObject();
        try {
            js.put("setupid",1);
            js.put("brandid",BRANDID);
            js.put("genreid",appraisalModel.getGenreList().get(APPRAISACATEID).getGenreid());
            js.put("total",TOTAL);
            js.put("receiverid",RECEIVEDID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.AddAppraisaOrder)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        L.e("支付返回信息" + response);
                        Gson gson = new Gson();
                        CreateOrderModel createOrderModel = gson.fromJson(response, CreateOrderModel.class);
                        if (createOrderModel.getCode() == 1) {
                            orderID = createOrderModel.getOrderid();
                            useAliPay(PAYTYPE);
                        } else if (createOrderModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }
    private void useAliPay(String PAYTYPE) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", orderID);
            jsonObject.put("type", 3);
            jsonObject.put("channel", PAYTYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.PayOrder)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("生成订单", "" + response);
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            datas = jsonObject1.optString("info");
                            initAlipay();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    private void initAlipay() {
        Intent intent = new Intent(AppraisasAC.this, PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, datas.toString());
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);

    }

    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(AppraisasAC.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }


    public void initSnackBar(String value){
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
