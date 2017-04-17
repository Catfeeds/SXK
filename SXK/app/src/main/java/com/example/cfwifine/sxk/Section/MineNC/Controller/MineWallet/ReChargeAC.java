package com.example.cfwifine.sxk.Section.MineNC.Controller.MineWallet;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.CreateOrderModel;
import com.example.cfwifine.sxk.Section.PublishNC.AC.AttachMentAC;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.AppraisasCheckRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.AppraisalAC.AppraisasAC;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
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

public class ReChargeAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView recharge_rv;
    private Integer[] picArrList = null;
    private ArrayList<Integer> picList;
    private ArrayList<TestModel> nameList;
    private String PAYTYPE = "";
    private Button recharge_btn;
    private EditText amount;
    Dialog dialog;
    private int orderID;
    private String datas;
    private String amountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_charge_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }


    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("账单");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        recharge_rv = (RecyclerView) findViewById(R.id.recharge_rv);
        initRecycleView();
        recharge_btn = (Button) findViewById(R.id.recharge_btn);
        recharge_btn.setOnClickListener(this);
        amount = (EditText) findViewById(R.id.amount);
        amount.setOnClickListener(this);
    }

    private void initRecycleView() {
        picArrList = new Integer[]{
                R.drawable.weixin, R.drawable.zhifubao, R.drawable.yinlianpic
        };
        picList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            picList.add(i, picArrList[i]);
        }
        String[] name = new String[]{
                "微信支付", "支付宝", "银联"
        };
        nameList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TestModel testModel = new TestModel(name[i], false);
            nameList.add(i, testModel);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recharge_rv.setLayoutManager(layoutManager);
        AppraisasCheckRecycleViewAdapter appraisasCheckRecycleViewAdapter = new AppraisasCheckRecycleViewAdapter(this, picList, nameList);
        recharge_rv.setAdapter(appraisasCheckRecycleViewAdapter);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
            case R.id.recharge_btn:
                submit();
                break;
        }
    }

    private void submit() {
        if (TextUtils.isEmpty(PAYTYPE)){
            initSnackBar("你还没有选择充值方式！");
            return;
        }
        amountString = amount.getText().toString().trim();
        if (TextUtils.isEmpty(amountString)) {
            initSnackBar("请输入充值金额!");
            return;
        }
        initOrderId();


    }

    private void initOrderId() {
        LogUtil.e("充值金额"+Double.valueOf(amountString)*100);
        double s = Double.valueOf(amountString)*100;
        if (s <=0){
            initSnackBar("请输入正确的金额！");
            return;
        }
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("amount",Integer.valueOf((int) s));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(ReChargeAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ReCharge)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("充值", "" + response);
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
            jsonObject.put("type", 5);
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
        Intent intent = new Intent(ReChargeAC.this, PaymentActivity.class);
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

    private void MaterialDialog(String str) {
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
//                dialogs.dismiss();
                finish();
            }
        });
    }


    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(ReChargeAC.this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }


}
