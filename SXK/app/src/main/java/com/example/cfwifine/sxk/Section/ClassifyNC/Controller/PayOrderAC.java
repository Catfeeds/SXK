package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Dialog.OrderSuccessPupWindow;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.CreateOrderModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductDetailModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineSetting.AddressSettingCommomAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressDetailModel;
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

import okhttp3.Call;

import static com.pingplusplus.android.Pingpp.REQUEST_CODE_PAYMENT;

public class PayOrderAC extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout place_back;
    private RelativeLayout select_address;
    private ImageView product_img;
    private TextView product_name;
    private TextView product_keyword;
    private TextView product_money_everyday;
    private TextView product_money;
    private TextView order_rent;
    private TextView order_deposit;
    private CheckBox freight_insurance_btn;
    private TextView freight_insurance;
    private EditText leaving_message;
    private TextView rule;
    private CheckBox agreement_btn;
    private TextView user_agreement;
    private TextView order_rent_and_other;
    private TextView order_deposit2;
    private TextView commit_order;
    private LikeIOSSheetDialog shitView;
    private String datas;
    private String rentDetail = "";
    private ProductDetailModel productDetailModel;
    Dialog dialog;
    private TextView product_lowest_price;
    private RadioButton product_r1;
    private RadioButton product_r2;
    private RadioButton product_r3;
    private RadioButton product_r4;
    private RadioGroup product_rg;
    private RadioButton checkRadioBtn;
    private String RentDate = "第一次";
    private String ADDRESSS;
    private int RECEIVEDID = -1;
    private TextView product_address;
    private int ordeID;
    private int isChecked = 2;
    private String message;
    private String PAYTYPE = "";
    private double dD;
    private double dS;
    private double dT;
    private double dF;
    private double dM;
    private double dN;
    private double dO;
    private double dP;
    private double dJ;
    private int AgreeIsChecked = 2;
    private OrderSuccessPupWindow orderSuccessPupWindow;
    private String purchaseDetail = "";
    private ImageView rent_money;
    private ImageView rent_yajin;
    private ImageView transi_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        dialog.show();
        SharedPreferencesUtils.setParam(PayOrderAC.this, "DEFAULTADDRESS", "");
        SharedPreferencesUtils.setParam(PayOrderAC.this, "RECRIVEDID", -1);
        initData();
    }

    private void initData() {
        rentDetail = getIntent().getStringExtra("RENTRESPONSE");
        purchaseDetail = getIntent().getStringExtra("PURCHASERESPONSE");
        Gson gson = new Gson();
        if (rentDetail != "") {
            productDetailModel = gson.fromJson(rentDetail.toString(), ProductDetailModel.class);
        } else if (purchaseDetail != "") {
            LogUtil.e("购买详情");
        }
        dialog.dismiss();
        initView();
    }

    private void initView() {
        place_back = (LinearLayout) findViewById(R.id.place_back);
        place_back.setOnClickListener(this);
        select_address = (RelativeLayout) findViewById(R.id.select_address);
        select_address.setOnClickListener(this);
        product_img = (ImageView) findViewById(R.id.product_img);
        product_name = (TextView) findViewById(R.id.product_name);
        product_keyword = (TextView) findViewById(R.id.product_keyword);
        product_money_everyday = (TextView) findViewById(R.id.product_money_everyday);
        product_money = (TextView) findViewById(R.id.product_money);
        order_rent = (TextView) findViewById(R.id.order_rent);
        order_deposit = (TextView) findViewById(R.id.order_deposit);
        freight_insurance = (TextView) findViewById(R.id.freight_insurance);
        leaving_message = (EditText) findViewById(R.id.leaving_message);
        rule = (TextView) findViewById(R.id.rule);
        rule.setOnClickListener(this);
        rent_money = (ImageView) findViewById(R.id.rent_money);
        rent_money.setOnClickListener(this);
        rent_yajin = (ImageView) findViewById(R.id.rent_yajin);
        rent_yajin.setOnClickListener(this);
        transi_money = (ImageView) findViewById(R.id.transi_money);
        transi_money.setOnClickListener(this);
        agreement_btn = (CheckBox) findViewById(R.id.agreement_btn);
        if (agreement_btn.isChecked()) {
            AgreeIsChecked = 1;
        }
        agreement_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AgreeIsChecked = 1;
                } else {
                    AgreeIsChecked = 2;
                }
                LogUtil.e("是否选择" + AgreeIsChecked);
            }
        });


        user_agreement = (TextView) findViewById(R.id.user_agreement);
        user_agreement.setOnClickListener(this);
        order_rent_and_other = (TextView) findViewById(R.id.order_rent_and_other);
        order_deposit2 = (TextView) findViewById(R.id.order_deposit2);
        commit_order = (TextView) findViewById(R.id.commit_order);
        commit_order.setOnClickListener(this);

        product_lowest_price = (TextView) findViewById(R.id.product_lowest_price);
        product_lowest_price.setOnClickListener(this);
        product_r1 = (RadioButton) findViewById(R.id.product_r1);
        product_r1.setOnClickListener(this);
        product_r2 = (RadioButton) findViewById(R.id.product_r2);
        product_r2.setOnClickListener(this);
        product_r3 = (RadioButton) findViewById(R.id.product_r3);
        product_r3.setOnClickListener(this);
        product_r4 = (RadioButton) findViewById(R.id.product_r4);
        product_r4.setOnClickListener(this);
        product_rg = (RadioGroup) findViewById(R.id.product_rg);
        product_rg.setOnClickListener(this);
        checkRadioBtn = (RadioButton) product_rg.findViewById(product_rg.getCheckedRadioButtonId());

        // 默认选中的数据
        dJ = ((productDetailModel.getRent().getThree()));
        order_rent_and_other.setText(String.format("%.2f", dJ / 100));
        order_rent.setText(String.format("%.2f", dJ / 100));
        double market = productDetailModel.getRent().getMarketPrice();
        order_deposit.setText(String.format("%.2f", market / 100));
        order_deposit2.setText(String.format("%.2f", market / 100));

        product_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int pos) {
                checkRadioBtn = (RadioButton) product_rg.findViewById(pos);
                LogUtil.e("选择了第几个" + checkRadioBtn.getText());
                RentDate = checkRadioBtn.getText().toString();
                if (isChecked == 2) {
                    switch (RentDate) {
                        case "3天":
                            dD = ((productDetailModel.getRent().getThree()));
                            order_rent_and_other.setText(String.format("%.2f", dD / 100));
                            order_rent.setText(String.format("%.2f", dD / 100));
                            double market = productDetailModel.getRent().getMarketPrice();
                            order_deposit.setText(String.format("%.2f", market / 100));
                            order_deposit2.setText(String.format("%.2f", market / 100));
                            break;
                        case "7天":
                            dS = ((productDetailModel.getRent().getSeven()));
                            order_rent_and_other.setText(String.format("%.2f", dS / 100));
                            order_rent.setText(String.format("%.2f", dS / 100));
                            double markets = productDetailModel.getRent().getMarketPrice();
                            order_deposit.setText(String.format("%.2f", markets / 100));
                            order_deposit2.setText(String.format("%.2f", markets / 100));
                            break;
                        case "15天":
                            dT = ((productDetailModel.getRent().getFiften()));
                            order_rent_and_other.setText(String.format("%.2f", dT / 100));
                            order_rent.setText(String.format("%.2f", dT / 100));
                            double markett = productDetailModel.getRent().getMarketPrice();
                            order_deposit.setText(String.format("%.2f", markett / 100));
                            order_deposit2.setText(String.format("%.2f", markett / 100));
                            break;
                        case "25天":
                            dF = ((productDetailModel.getRent().getTwentyFive()));
                            order_rent_and_other.setText(String.format("%.2f", dF / 100));
                            order_rent.setText(String.format("%.2f", dF / 100));
                            double marketb = productDetailModel.getRent().getMarketPrice();
                            order_deposit.setText(String.format("%.2f", marketb / 100));
                            order_deposit2.setText(String.format("%.2f", marketb / 100));
                            break;
                    }

                } else {
                    switch (RentDate) {
                        case "3天":
                            dM = ((productDetailModel.getRent().getThree() + productDetailModel.getRent().getRisk()));
                            order_rent_and_other.setText(String.format("%.2f", dM / 100));
                            break;
                        case "7天":
                            dN = ((productDetailModel.getRent().getSeven() + productDetailModel.getRent().getRisk()));
                            order_rent_and_other.setText(String.format("%.2f", dN / 100));
                            break;
                        case "15天":
                            dO = ((productDetailModel.getRent().getFiften() + productDetailModel.getRent().getRisk()));
                            order_rent_and_other.setText(String.format("%.2f", dO / 100));
                            break;
                        case "25天":
                            dP = ((productDetailModel.getRent().getTwentyFive() + productDetailModel.getRent().getRisk()));
                            order_rent_and_other.setText(String.format("%.2f", dP / 100));
                            break;
                    }
                }


            }
        });
        freight_insurance_btn = (CheckBox) findViewById(R.id.freight_insurance_btn);
        if (!freight_insurance_btn.isChecked()) {
            isChecked = 2;
        }
        freight_insurance_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isChecked = 1;
                } else {
                    isChecked = 2;
                }
//                LogUtil.e("是否选择"+isChecked);
                if (isChecked == 1) {
                    if (RentDate.equals("第一次")) {
                        double riskAndMarket = dJ + productDetailModel.getRent().getRisk();
                        order_rent_and_other.setText(String.format("%.2f", riskAndMarket / 100));
                    } else {
                        switch (RentDate) {
                            case "3天":
                                dM = ((productDetailModel.getRent().getThree() + productDetailModel.getRent().getRisk()));
                                order_rent_and_other.setText(String.format("%.2f", dM / 100));
                                break;
                            case "7天":
                                dN = ((productDetailModel.getRent().getSeven() + productDetailModel.getRent().getRisk()));
                                order_rent_and_other.setText(String.format("%.2f", dN / 100));
                                break;
                            case "15天":
                                dO = ((productDetailModel.getRent().getFiften() + productDetailModel.getRent().getRisk()));
                                order_rent_and_other.setText(String.format("%.2f", dO / 100));
                                break;
                            case "25天":
                                dP = ((productDetailModel.getRent().getTwentyFive() + productDetailModel.getRent().getRisk()));
                                order_rent_and_other.setText(String.format("%.2f", dP / 100));
                                break;
                        }
                    }

                } else {
                    if (RentDate.equals("第一次")) {
//                        double riskAndMarket = productDetailModel.getRent().getMarketPrice();
                        order_rent_and_other.setText(String.format("%.2f", dJ / 100));
                    } else {
                        switch (RentDate) {
                            case "3天":
                                dD = ((productDetailModel.getRent().getThree()));
                                order_rent_and_other.setText(String.format("%.2f", dD / 100));
                                break;
                            case "7天":
                                dS = ((productDetailModel.getRent().getSeven()));
                                order_rent_and_other.setText(String.format("%.2f", dS / 100));
                                break;
                            case "15天":
                                dT = ((productDetailModel.getRent().getFiften()));
                                order_rent_and_other.setText(String.format("%.2f", dT / 100));
                                break;
                            case "25天":
                                dF = ((productDetailModel.getRent().getTwentyFive()));
                                order_rent_and_other.setText(String.format("%.2f", dF / 100));
                                break;
                        }
                    }
                }
            }
        });

        product_address = (TextView) findViewById(R.id.product_address);
        product_address.setText("请选择收货地址");
        setValueForView();
        initDefaultAddress();

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
                                product_address.setText("收货人：" + addressDetailModel.getReceiver().getName() + "  " + addressDetailModel.getReceiver().getMobile() + "\n" + addressDetailModel.getReceiver().getState() + addressDetailModel.getReceiver().getCity() + addressDetailModel.getReceiver().getDistrict() + addressDetailModel.getReceiver().getAddress());
                                RECEIVEDID = addressDetailModel.getReceiver().getReceiverid();
                                SharedPreferencesUtils.setParam(PayOrderAC.this, "DEFAULTADDRESS", "");
                            } else {
                                product_address.setText("请选择收货地址");
                            }
                        } else if (addressDetailModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }

                    }
                });


    }

    private void setValueForView() {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + productDetailModel.getRent().getImgList().get(0);
        Glide.with(this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(product_img);
        product_name.setText(productDetailModel.getRent().getName().toString());
        product_keyword.setText(productDetailModel.getRent().getKeyword().toString());
        double dd = (productDetailModel.getRent().getMarketPrice());
        product_money.setText("市场价： ¥ " + String.format("%.2f", dd / 100));
        double ss = productDetailModel.getRent().getRentPrice();
        product_lowest_price.setText("¥ " + String.format("%.2f", ss / 100) + "/天");
        double insurance = productDetailModel.getRent().getRisk();
        freight_insurance.setText(String.format("%.2f", insurance / 100));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_order:
                initCommentOrder();
                break;
            case R.id.select_address:
                Intent intent = new Intent(PayOrderAC.this, AddressSettingCommomAC.class);
                startActivity(intent);
                break;
            case R.id.place_back:
                finish();
                break;
            case R.id.rule:
                Intent intent1 = new Intent(PayOrderAC.this, UserPrctocalAC.class);
                intent1.putExtra("SETJUMPPOSITION", 999);
                startActivity(intent1);
                break;
            case R.id.rent_money:
                Intent intent2 = new Intent(PayOrderAC.this, UserPrctocalAC.class);
                intent2.putExtra("SETJUMPPOSITION", 991);
                startActivity(intent2);
                break;
            case R.id.rent_yajin:
                Intent intent3 = new Intent(PayOrderAC.this, UserPrctocalAC.class);
                intent3.putExtra("SETJUMPPOSITION", 991);
                startActivity(intent3);
                break;
            case R.id.transi_money:
                Intent intent4 = new Intent(PayOrderAC.this, UserPrctocalAC.class);
                intent4.putExtra("SETJUMPPOSITION", 991);
                startActivity(intent4);
                break;
            case R.id.user_agreement:
                Intent intent5 = new Intent(PayOrderAC.this,UserPrctocalAC.class);
                intent5.putExtra("SETJUMPPOSITION",222);
                startActivity(intent5);
                break;
            default:
                break;
        }
    }

    // 提交订单
    private void initCommentOrder() {
        shitView = new LikeIOSSheetDialog.Builder(PayOrderAC.this)
                .setTitle("支付方式")
                .addMenu("支付宝支付", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
                        PAYTYPE = "alipay";
                        useAliPays();
                    }
                }).addMenu("微信支付", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shitView.dismiss();
                        PAYTYPE = "wx";
                        useAliPays();
                    }
                }).addMenu("银联支付", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shitView.dismiss();
                        PAYTYPE = "upacp";
                        useAliPays();
                    }
                }).create();
        shitView.show();
    }

    private void useAliPay(String type) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", ordeID);
            jsonObject.put("type", 1);
            jsonObject.put("channel", type);
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
                        SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
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
//                            SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        } else if (cateModel.getCode() == 0) {
//                            SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
//                        }
                    }
                });


    }

    private void initAlipay() {
        Intent intent = new Intent(PayOrderAC.this, PaymentActivity.class);
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
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        LogUtil.e("支付结果" + str);
        String RESULT = "";
        switch (title) {
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
        MaterialDialog(title);
    }

    private void MaterialDialog(String str) {
        if (str.equals("cancel")) {
            orderSuccessPupWindow = new OrderSuccessPupWindow(this, productDetailModel, itemsOnClick);
            orderSuccessPupWindow.showAtLocation(this.findViewById(R.id.activity_place_order), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else if (str.equals("success")) {
            Intent intent = new Intent(PayOrderAC.this, BuyerAndSellerOrderDetailAC.class);
            intent.putExtra("type", 1);
            intent.putExtra("orderid", ordeID);
            startActivity(intent);
            finish();
        }
//        BaseAnimatorSet bas_in = new FlipVerticalSwingEnter();
//        BaseAnimatorSet bas_out = new FadeExit();
//        final MaterialDialog dialogs = new MaterialDialog(this);
//        dialogs.title("")
//                .titleTextColor(Color.BLACK)
//                .titleTextSize(14)
//                .isTitleShow(false)
//                .content(str)//
//                .contentTextColor(Color.GRAY)
//                .btnNum(1)
//                .btnText("确定")
//                .contentGravity(Gravity.CENTER_HORIZONTAL)
//                .btnTextColor(Color.parseColor("#16a6ae"))
//                .showAnim(bas_in)//
//                .dismissAnim(bas_out)//
//                .show();
//
//        dialogs.setOnBtnClickL(new OnBtnClickL() {
//            @Override
//            public void onBtnClick() {
//                dialogs.dismiss();
//            }
//        });
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
//            orderSuccessPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.cancel_btn:
                    orderSuccessPupWindow.dismiss();
                    break;
                case R.id.pay_btn:
                    orderSuccessPupWindow.dismiss();
                    shitView.show();
                    break;
                default:
                    break;
            }


        }

    };

    // 生成订单的接口
    private void useAliPays() {
        LogUtil.e("receivedid" + RECEIVEDID);
        message = leaving_message.getText().toString().trim();
        if (message.trim().length() >= 250) {
            initSnackBar("留言过长！");
            return;
        }
        if (TextUtils.isEmpty(message)) {
            initSnackBar("您还没有留言！");
            return;
        }
        if (RECEIVEDID == -1) {
            initSnackBar("你还没有选择收货地址！");
            return;
        }
        if (AgreeIsChecked == 2) {
            initSnackBar("你还没有同意啵呗用户协议！");
            return;
        }


        String va = "";
        int pri = 0;
        int total = 0;
        if (isChecked == 1) {
            switch (RentDate) {
                case "第一次":
                    va = "three";
                    pri = productDetailModel.getRent().getThree();
                    total = productDetailModel.getRent().getThree() + productDetailModel.getRent().getMarketPrice() + productDetailModel.getRent().getRisk();
                    break;
                case "3天":
                    va = "three";
                    pri = productDetailModel.getRent().getThree();
                    total = productDetailModel.getRent().getThree() + productDetailModel.getRent().getMarketPrice() + productDetailModel.getRent().getRisk();
                    break;
                case "7天":
                    va = "seven";
                    pri = productDetailModel.getRent().getSeven();
                    total = productDetailModel.getRent().getSeven() + productDetailModel.getRent().getMarketPrice() + productDetailModel.getRent().getRisk();
                    break;
                case "15天":
                    va = "fiften";
                    pri = productDetailModel.getRent().getFiften();
                    total = productDetailModel.getRent().getFiften() + productDetailModel.getRent().getMarketPrice() + productDetailModel.getRent().getRisk();
                    break;
                case "25天":
                    va = "twentyFive";
                    pri = productDetailModel.getRent().getTwentyFive();
                    total = productDetailModel.getRent().getTwentyFive() + productDetailModel.getRent().getMarketPrice() + productDetailModel.getRent().getRisk();
                    break;
            }
        } else {
            switch (RentDate) {
                case "第一次":
                    va = "three";
                    pri = productDetailModel.getRent().getThree();
                    total = productDetailModel.getRent().getThree() + productDetailModel.getRent().getMarketPrice();
                    break;
                case "3天":
                    va = "three";
                    pri = productDetailModel.getRent().getThree();
                    total = productDetailModel.getRent().getThree() + productDetailModel.getRent().getMarketPrice();
                    break;
                case "7天":
                    va = "seven";
                    pri = productDetailModel.getRent().getSeven();
                    total = productDetailModel.getRent().getSeven() + productDetailModel.getRent().getMarketPrice();
                    break;
                case "15天":
                    va = "fiften";
                    pri = productDetailModel.getRent().getFiften();
                    total = productDetailModel.getRent().getFiften() + productDetailModel.getRent().getMarketPrice();
                    break;
                case "25天":
                    va = "twentyFive";
                    pri = productDetailModel.getRent().getTwentyFive();
                    total = productDetailModel.getRent().getTwentyFive() + productDetailModel.getRent().getMarketPrice();
                    break;
            }
        }

        JSONObject js = new JSONObject();
        try {
            js.put("name", va);
            js.put("value", pri);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rentid", productDetailModel.getRent().getRentid());
            jsonObject.put("isRisk", isChecked);
            jsonObject.put("tenancy", js);
            jsonObject.put("total", total);
            jsonObject.put("receiverid", RECEIVEDID);
            jsonObject.put("message", message);
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
                        SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("生成订单", "" + response);
                        Gson gson = new Gson();
                        CreateOrderModel createOrderModel = gson.fromJson(response, CreateOrderModel.class);
                        if (createOrderModel.getCode() == 1) {
                            ordeID = createOrderModel.getOrderid();
                            useAliPay(PAYTYPE);
                        } else if (createOrderModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (createOrderModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ADDRESSS = String.valueOf(SharedPreferencesUtils.getParam(PayOrderAC.this, "DEFAULTADDRESS", ""));
        if (ADDRESSS == "") {
            initDefaultAddress();
//            product_address.setText("请选择收货地址");
        } else {
            product_address.setText(ADDRESSS);
        }
        RECEIVEDID = (int) SharedPreferencesUtils.getParam(PayOrderAC.this, "RECRIVEDID", -1);

    }

    public void initSnackBar(String value) {
        SnackbarUtils.showShortSnackbar(PayOrderAC.this.getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#16a6ae"));
    }

}
