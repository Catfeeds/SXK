package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.pingplusplus.android.PaymentActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static com.pingplusplus.android.Pingpp.REQUEST_CODE_PAYMENT;

public class PlaceOrderAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout place_back;
    private RelativeLayout select_address;
    private ImageView product_img;
    private TextView product_name;
    private TextView product_keyword;
    private TextView product_money_everyday;
    private TextView product_money;
    private TextView lease_term1;
    private TextView lease_term2;
    private TextView lease_term3;
    private TextView lease_term4;
    private TextView order_rent;
    private TextView order_deposit;
    private ImageView freight_insurance_btn;
    private TextView freight_insurance;
    private EditText leaving_message;
    private TextView rule;
    private ImageView agreement_btn;
    private TextView user_agreement;
    private TextView order_rent_and_other;
    private TextView order_deposit2;
    private TextView commit_order;
    private LikeIOSSheetDialog shitView;
    private String datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        initView();
    }

    private void initView() {
        place_back = (LinearLayout) findViewById(R.id.place_back);
        select_address = (RelativeLayout) findViewById(R.id.select_address);
        product_img = (ImageView) findViewById(R.id.product_img);
        product_name = (TextView) findViewById(R.id.product_name);
        product_keyword = (TextView) findViewById(R.id.product_keyword);
        product_money_everyday = (TextView) findViewById(R.id.product_money_everyday);
        product_money = (TextView) findViewById(R.id.product_money);
        lease_term1 = (TextView) findViewById(R.id.lease_term1);
        lease_term2 = (TextView) findViewById(R.id.lease_term2);
        lease_term3 = (TextView) findViewById(R.id.lease_term3);
        lease_term4 = (TextView) findViewById(R.id.lease_term4);
        order_rent = (TextView) findViewById(R.id.order_rent);
        order_deposit = (TextView) findViewById(R.id.order_deposit);
        freight_insurance_btn = (ImageView) findViewById(R.id.freight_insurance_btn);
        freight_insurance = (TextView) findViewById(R.id.freight_insurance);
        leaving_message = (EditText) findViewById(R.id.leaving_message);
        rule = (TextView) findViewById(R.id.rule);
        agreement_btn = (ImageView) findViewById(R.id.agreement_btn);
        user_agreement = (TextView) findViewById(R.id.user_agreement);
        order_rent_and_other = (TextView) findViewById(R.id.order_rent_and_other);
        order_deposit2 = (TextView) findViewById(R.id.order_deposit2);
        commit_order = (TextView) findViewById(R.id.commit_order);
        commit_order.setOnClickListener(this);

    }

    private void submit() {
        // validate
        String message = leaving_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "给啵主留言（250字以内）", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_order:
                initCommentOrder();
                break;
        }
    }

    // 提交订单
    private void initCommentOrder() {
        shitView = new LikeIOSSheetDialog.Builder(PlaceOrderAC.this)
                .setTitle("支付方式")
                .addMenu("支付宝支付", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
                        useAliPay();
                    }
                }).addMenu("微信支付", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();

                    }
                }).create();
        shitView.show();
    }

    private void useAliPay() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid",50);
            jsonObject.put("type",1);
            jsonObject.put("channel","alipay");
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
                        SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
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

//                        ClassifyCateModel cateModel = gson.fromJson(response, ClassifyCateModel.class);
////                        ClassifyBigImgModel bigImgModel = gson.fromJson(response,ClassifyBigImgModel.class);
//                        if (cateModel.getCode() == 1) {
//
//
//                        } else if (cateModel.getCode() == 911) {
//                            SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        } else if (cateModel.getCode() == 0) {
//                            SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        }
                    }
                });


    }
    private void initAlipay() {
//        new PaymentTask().execute(new PaymentRequest(CHANNEL_ALIPAY));

        Intent intent = new Intent(PlaceOrderAC.this,PaymentActivity.class);
//        String packageName = getPackageName();
//        ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
//        intent.setComponent(componentName);
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
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(PlaceOrderAC.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }


    // 生成订单的接口
    private void useAliPays() {
        JSONObject js = new JSONObject();
        try {
            js.put("name","three");
            js.put("value",10000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(js);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rentid", 7);
            jsonObject.put("isRisk", 2);
            jsonObject.put("tenancy", js);
            jsonObject.put("total", 210000);
            jsonObject.put("receiverid", 112);
            jsonObject.put("message", "测试");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.CreateOrder)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("生成订单", "" + response);
                        Gson gson = new Gson();
//                        ClassifyCateModel cateModel = gson.fromJson(response, ClassifyCateModel.class);
////                        ClassifyBigImgModel bigImgModel = gson.fromJson(response,ClassifyBigImgModel.class);
//                        if (cateModel.getCode() == 1) {
//
//
//                        } else if (cateModel.getCode() == 911) {
//                            SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        } else if (cateModel.getCode() == 0) {
//                            SnackbarUtils.showShortSnackbar(PlaceOrderAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        }
                    }
                });


    }
}
