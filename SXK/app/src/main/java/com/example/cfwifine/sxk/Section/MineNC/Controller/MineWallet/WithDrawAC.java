package com.example.cfwifine.sxk.Section.MineNC.Controller.MineWallet;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.CreateOrderModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class WithDrawAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private EditText withdraw_money;
    private EditText card_number;
    private EditText bank;
    private EditText bank_address;
    private EditText name;
    private EditText verify_code;
    private Button send_code;
    private LinearLayout activity_with_draw_ac;
    Dialog mloading;
    private Button withdraw_btn;
    private String moneys;
    private String Cardnumber;
    private String bankString;
    private String address;
    private String nameString;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_ac);
        initView();
    }

    private void initView() {
        mloading = LoadingUtils.createLoadingDialog(this, "加载中...");
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("提现");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        withdraw_money = (EditText) findViewById(R.id.withdraw_money);
        card_number = (EditText) findViewById(R.id.card_number);
        bank = (EditText) findViewById(R.id.bank);
        bank_address = (EditText) findViewById(R.id.bank_address);
        name = (EditText) findViewById(R.id.name);
        verify_code = (EditText) findViewById(R.id.verify_code);
        send_code = (Button) findViewById(R.id.send_code);
        activity_with_draw_ac = (LinearLayout) findViewById(R.id.activity_with_draw_ac);
        send_code.setOnClickListener(this);

        withdraw_btn = (Button) findViewById(R.id.withdraw_btn);
        withdraw_btn.setOnClickListener(this);
    }

    private void sendVerify() {
        String phoneNumber = String.valueOf(SharedPreferencesUtils.getParam(WithDrawAC.this, BaseInterface.PHONENUMBER, ""));
        LogUtil.e("手机号：" + phoneNumber);
        if (phoneNumber.length() != 11) {
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入正确的手机号!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                send_code.setText(l / 1000 + "s后重新发送");
                send_code.setTextColor(Color.parseColor("#16a6ae"));
                send_code.setEnabled(false);
            }

            @Override
            public void onFinish() {
                send_code.setText("重新获取验证码");
                send_code.setEnabled(true);
            }
        }.start();
        JSONObject json = new JSONObject();
        try {
            json.put("mobile", phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(BaseInterface.UserVerifyCode)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "验证码请求异常!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("验证码", "" + response);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_code:
                sendVerify();
                break;
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
            case R.id.withdraw_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        moneys = withdraw_money.getText().toString().trim();
        if (TextUtils.isEmpty(moneys)) {
            initSnackBar("请输入提现金额!");
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return;
        }

        Cardnumber = card_number.getText().toString().trim();
        if (TextUtils.isEmpty(Cardnumber)) {
            initSnackBar("请输入提现卡号！");
//            Toast.makeText(this, , Toast.LENGTH_SHORT).show();
            return;
        }
        if (Cardnumber.length() != 19){
            initSnackBar("请输入正确的银行卡号！");
            return;
        }

        bankString = bank.getText().toString().trim();
        if (TextUtils.isEmpty(bankString)) {
            initSnackBar("请选择开户银行！");
//            Toast.makeText(this, , Toast.LENGTH_SHORT).show();
            return;
        }

        address = bank_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            initSnackBar("请输入所属支行！");
//            Toast.makeText(this, "所属支行", Toast.LENGTH_SHORT).show();
            return;
        }

        nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            initSnackBar("请输入开户人姓名！");
//            Toast.makeText(this, "开户人姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        code = verify_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            initSnackBar("请输入验证码！");
//            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        initWithDrawCash();


    }

    private void initWithDrawCash() {
        double s = Double.valueOf(moneys)*100;
        if (s <=0){
            initSnackBar("请输入正确的金额！");
            return;
        }
        double dd = getIntent().getDoubleExtra("yue",0);
        if (s>dd){
            initSnackBar("账户余额不足！请及时充值！");
            return;
        }
        mloading.show();
        JSONObject js = new JSONObject();
        try {
            js.put("amount",s);
            js.put("cardNumber",Cardnumber);
            js.put("bank",bankString);
            js.put("branch",address);
            js.put("name",nameString);
            js.put("code",code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(WithDrawAC.this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.WithDraw)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mloading.dismiss();
                        initSnackBar("请求出错！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mloading.dismiss();
                        Log.e("提现", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response,RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            MaterialDialog("提现成功！");
                        } else if (requestStatueModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });

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
    static class CheckIdCard {
        /**
         * 校验银行卡卡号
         *
         * @param cardId
         * @return
         */
        public static boolean checkBankCard(String cardId) {
            char bit = getBankCardCheckCode(cardId
                    .substring(0, cardId.length() - 1));
            if (bit == 'N') {
                return false;
            }
            return cardId.charAt(cardId.length() - 1) == bit;
        }

        /**
         * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
         *
         * @param nonCheckCodeCardId
         * @return
         */
        public static char getBankCardCheckCode(String nonCheckCodeCardId) {
            if (nonCheckCodeCardId == null
                    || nonCheckCodeCardId.trim().length() == 0
                    || !nonCheckCodeCardId.matches("\\d+")) {
                // 如果传的不是数据返回N
                return 'N';
            }
            char[] chs = nonCheckCodeCardId.trim().toCharArray();
            int luhmSum = 0;
            for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
                int k = chs[i] - '0';
                if (j % 2 == 0) {
                    k *= 2;
                    k = k / 10 + k % 10;
                }
                luhmSum += k;
            }
            return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
        }
    }



    public void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }
}
