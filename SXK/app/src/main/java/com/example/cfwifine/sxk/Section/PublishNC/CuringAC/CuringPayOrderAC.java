package com.example.cfwifine.sxk.Section.PublishNC.CuringAC;

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
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineSetting.AddressSettingCommomAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressDetailModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AppraisasCheckRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringDetailModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.google.gson.Gson;
import com.pingplusplus.android.PaymentActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;

import static com.pingplusplus.android.Pingpp.REQUEST_CODE_PAYMENT;

public class CuringPayOrderAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout place_back;
    private TextView curpayorder_address;
    private RelativeLayout curpayorder_select_address;
    private ImageView curpayorder_img;
    private TextView curpayorder_name;
    private TextView curpayorder_keyword;
    private TextView curpayorder_order_rent;
    private TextView curpayorder_order_deposit;
    private EditText curpayorder_leaving_message;
    private RecyclerView curpayorder_rv;
    private TextView curpayorder_total;
    private TextView commit_order;
    private RelativeLayout activity_place_order;
    private Integer[] picArrList;
    private ArrayList<Integer> picList;
    private ArrayList<TestModel> nameList;
    Dialog dialog;
    private String ADDRESSS;
    private int RECEIVEDID;
    private String curingDetailDataSource;
    private CuringDetailModel curingDetailModel;
    private String PAYTYPE = "";
    private String messagess;
    private int orderID;
    private String datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curing_pay_order_ac);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        SharedPreferencesUtils.setParam(CuringPayOrderAC.this, "DEFAULTADDRESS", "");
        SharedPreferencesUtils.setParam(CuringPayOrderAC.this, "RECRIVEDID", -1);
        curingDetailDataSource = getIntent().getStringExtra("CURINGDETAIL");
        initView();
    }

    private void initView() {
        place_back = (LinearLayout) findViewById(R.id.place_back);
        place_back.setOnClickListener(this);
        curpayorder_address = (TextView) findViewById(R.id.curpayorder_address);
        curpayorder_select_address = (RelativeLayout) findViewById(R.id.curpayorder_select_address);
        curpayorder_select_address.setOnClickListener(this);
        curpayorder_img = (ImageView) findViewById(R.id.curpayorder_img);
        curpayorder_name = (TextView) findViewById(R.id.curpayorder_name);
        curpayorder_keyword = (TextView) findViewById(R.id.curpayorder_keyword);
        curpayorder_order_rent = (TextView) findViewById(R.id.curpayorder_order_rent);
        curpayorder_order_deposit = (TextView) findViewById(R.id.curpayorder_order_deposit);
        curpayorder_leaving_message = (EditText) findViewById(R.id.curpayorder_leaving_message);
        curpayorder_rv = (RecyclerView) findViewById(R.id.curpayorder_rv);
        curpayorder_total = (TextView) findViewById(R.id.curpayorder_total);
        commit_order = (TextView) findViewById(R.id.commit_order);
        commit_order.setOnClickListener(this);
        activity_place_order = (RelativeLayout) findViewById(R.id.activity_place_order);
        initRecycleView();
        // 获取默认收货地址
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
        curpayorder_rv.setLayoutManager(layoutManager);
        AppraisasCheckRecycleViewAdapter appraisasCheckRecycleViewAdapter = new AppraisasCheckRecycleViewAdapter(this, picList, nameList);
        curpayorder_rv.setAdapter(appraisasCheckRecycleViewAdapter);
        appraisasCheckRecycleViewAdapter.setOnItemClickListener(new AppraisasCheckRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positionl, String value) {
                LogUtil.e("选中了" + value);
                if (positionl == 0) {
                    PAYTYPE = "wx";
                } else if (positionl == 1) {
                    PAYTYPE = "alipay";
                } else if (positionl == 2) {
                    PAYTYPE = "1";
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
                        AddressDetailModel addressDetailModel = gson.fromJson(response, AddressDetailModel.class);
                        if (addressDetailModel.getCode() == 1) {
                            L.e("详细信息" + response);
                            if (addressDetailModel.getReceiver() != null) {
                                curpayorder_address.setText("收货人：" + addressDetailModel.getReceiver().getName() + "  " + addressDetailModel.getReceiver().getMobile() + "\n" + addressDetailModel.getReceiver().getState() + addressDetailModel.getReceiver().getCity() + addressDetailModel.getReceiver().getDistrict() + addressDetailModel.getReceiver().getAddress());
                                RECEIVEDID = addressDetailModel.getReceiver().getReceiverid();
                            } else {
                                curpayorder_address.setText("请选择收货地址");
                            }
                            setValueForDetail();

                        } else if (addressDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }

                    }
                });


    }

    private void setValueForDetail() {
        Gson gson = new Gson();
        curingDetailModel = gson.fromJson(curingDetailDataSource, CuringDetailModel.class);
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + curingDetailModel.getMaintain().getImg();
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(curpayorder_img);
        curpayorder_name.setText(curingDetailModel.getMaintain().getName());
        curpayorder_keyword.setText(curingDetailModel.getMaintain().getKeyword());
        curpayorder_order_deposit.setText(String.valueOf((double) (Math.round(curingDetailModel.getMaintain().getPrice()) / 100.0)));
        curpayorder_total.setText(String.valueOf((double) (Math.round(curingDetailModel.getMaintain().getPrice()) / 100.0)));
    }

    private void submit() {
        // validate
        messagess = curpayorder_leaving_message.getText().toString().trim();
        if (TextUtils.isEmpty(messagess)) {
            initSnackBar("您好没有填写留言！");
            return;
        }
        if (TextUtils.isEmpty(PAYTYPE)) {
            initSnackBar("您还没有选择支付方式！");
            return;
        }
        // 支付宝支付，生成订单
        LogUtil.e("支付方式"+PAYTYPE);
        initOrderData();

    }
    // 余额支付
    private void initYUePay() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("orderid",orderID);
            js.put("type",2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.YUEPay)
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
                        L.e("余额支付结果" + response);
                        Gson gson = new Gson();
                        RequestStatueModel createOrderModel = gson.fromJson(response, RequestStatueModel.class);
                        if (createOrderModel.getCode() == 1) {
                            MaterialDialog("支付成功！");
                        } else if (createOrderModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });

    }

    private void initOrderData() {
        if (RECEIVEDID == -1) {
            initSnackBar("您还没有选择收货地址！");
            return;
        }
        JSONObject js = new JSONObject();
        try {
            js.put("maintainid", curingDetailModel.getMaintain().getMaintainid());
            js.put("receiverid", RECEIVEDID);
            js.put("total", curingDetailModel.getMaintain().getPrice());
            js.put("message", messagess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CuringPayOrderForChange)
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
                        L.e("支付返回信息" + response);
                        Gson gson = new Gson();
                        CreateOrderModel createOrderModel = gson.fromJson(response, CreateOrderModel.class);
                        if (createOrderModel.getCode() == 1) {
                            orderID = createOrderModel.getOrderid();
                            if (PAYTYPE == "1"){
                                initYUePay();
                            }else {
                                useAliPay(PAYTYPE);
                            }
                        } else if (createOrderModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }

    /**
     * 支付包支付
     * @param PAYTYPEs
     */
    private void useAliPay(String PAYTYPEs) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", orderID);
            jsonObject.put("type", 2);
            jsonObject.put("channel", PAYTYPEs);
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
                        SnackbarUtils.showShortSnackbar(CuringPayOrderAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
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
        Intent intent = new Intent(CuringPayOrderAC.this, PaymentActivity.class);
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
        LogUtil.e("支付结果"+str);
        String RESULT= "";
        switch (title){
            case "success":
                RESULT = "支付成功！";
                break;
            case "fail":
                RESULT = "支付失败！";
                break;
            case "cancel":
                RESULT = "已取消支付！";
                break;
            case "invalid":
                RESULT = "未检测到支付软件！";
                break;
            default:
                break;
        }
        MaterialDialog(RESULT);
    }

    private void MaterialDialog(final String str) {
        BaseAnimatorSet bas_in = new FlipVerticalSwingEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        final MaterialDialog dialogs = new MaterialDialog(this);
        dialogs.title("")
                .titleTextColor(Color.BLACK)
                .titleTextSize(14)
                .isTitleShow(false)
                .content(str)//
                .contentTextColor(Color.GRAY)
                .btnNum(1)
                .btnText("确定")
                .contentGravity(Gravity.CENTER_HORIZONTAL)
                .btnTextColor(Color.parseColor("#16a6ae"))
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialogs.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                if (str.trim() == "支付成功！"){
                    dialogs.dismiss();
                    finish();
                }else {
                    dialogs.dismiss();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ADDRESSS = String.valueOf(SharedPreferencesUtils.getParam(CuringPayOrderAC.this, "DEFAULTADDRESS", ""));
        if (ADDRESSS == "") {
            curpayorder_address.setText("请选择收货地址");
        } else {
            curpayorder_address.setText(ADDRESSS);
        }
        RECEIVEDID = (int) SharedPreferencesUtils.getParam(CuringPayOrderAC.this, "RECRIVEDID", -1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_back:
                finish();
                break;
            case R.id.curpayorder_select_address:
                Intent intent = new Intent(CuringPayOrderAC.this, AddressSettingCommomAC.class);
                startActivity(intent);
                break;
            case R.id.commit_order:
                submit();
                break;
            default:
                break;
        }
    }

    private void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(CuringPayOrderAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
