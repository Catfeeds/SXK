package com.example.cfwifine.sxk.Section.MineBuyPlus.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Adapter.PurchasePreListAdapter;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Model.PurchasePreListModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineInfo.UserPrctocalAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineSetting.AddressSettingCommomAC;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class OrderPurchaseAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private LinearLayout purchase_add_goods;
    private LinearLayout purchase_add_address;
    private CheckBox agreement_btn;
    private TextView user_agreement;
    private Button confirm_buy;
    private LinearLayout activity_order_purchase_ac;
    private RecyclerView orderlist_rv;
    Dialog dialog;
    private List<PurchasePreListModel.PurchaseListBean> dataSource = null;
    private int RECEIVEDID = -1;
    private String ADDRESSS = "";
    private TextView address_text;
    private boolean isCheck = false;
    private PurchasePreListModel purchasePreListModel = null;
    private PurchasePreListAdapter purchasePreListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_purchase_ac);
        dialog = LoadingUtils.createLoadingDialog(this, "加载中...");
        SharedPreferencesUtils.setParam(OrderPurchaseAC.this, "DEFAULTADDRESS", "");
        SharedPreferencesUtils.setParam(OrderPurchaseAC.this, "RECRIVEDID", -1);
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("预约啵呗寄卖");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        purchase_add_goods = (LinearLayout) findViewById(R.id.purchase_adds_goods);
        purchase_add_goods.setOnClickListener(this);
        purchase_add_address = (LinearLayout) findViewById(R.id.purchase_add_address);
        purchase_add_address.setOnClickListener(this);
        agreement_btn = (CheckBox) findViewById(R.id.agreement_btn);
        if (!agreement_btn.isChecked()) {
            isCheck = false;
        }
        agreement_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isCheck = true;
                } else {
                    isCheck = false;
                }
            }
        });
        user_agreement = (TextView) findViewById(R.id.user_agreement);
        user_agreement.setOnClickListener(this);
        confirm_buy = (Button) findViewById(R.id.confirm_buy);
        confirm_buy.setOnClickListener(this);
        activity_order_purchase_ac = (LinearLayout) findViewById(R.id.activity_order_purchase_ac);
        orderlist_rv = (RecyclerView) findViewById(R.id.orderlist_rv);
        address_text = (TextView) findViewById(R.id.address_text);
        initRecycleViewData(1);

    }


    // 初始化recycleview的数据
    private void initRecycleViewData(final int i) {
        dialog.show();
        JSONObject order = new JSONObject();
        try {
            order.put("purchaseid", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("own", 1);
            js.put("status", 1);
            js.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.PurchasePreList)
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
                        LogUtil.e("预约商品列表" + response);
                        Gson gson = new Gson();
                        purchasePreListModel = gson.fromJson(response, PurchasePreListModel.class);
                        if (purchasePreListModel.getCode() == 1) {
                            if (i == 1){
                                if (purchasePreListModel.getPurchaseList().size() == 0) {
                                    orderlist_rv.setVisibility(View.GONE);
                                } else {
                                    dataSource = purchasePreListModel.getPurchaseList();
                                    initRecycleView();
                                }
                            }else if (i==2){
                                if (purchasePreListModel.getPurchaseList().size() == 0) {
                                    orderlist_rv.setVisibility(View.GONE);
                                } else {
                                    orderlist_rv.setVisibility(View.VISIBLE);
                                    dataSource = purchasePreListModel.getPurchaseList();
                                    initRecycleView();
                                }
                            }

                        } else if (purchasePreListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (purchasePreListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderlist_rv.setLayoutManager(new GridLayoutManager(this, 1));
        purchasePreListAdapter = new PurchasePreListAdapter(this, dataSource);
        orderlist_rv.setAdapter(purchasePreListAdapter);
        purchasePreListAdapter.setOnItemClickLitener(new PurchasePreListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int purchaseid) {
                LogUtil.e("预约ID" + purchaseid);
                Intent intent = new Intent(OrderPurchaseAC.this, PublishPurchaseAC.class);
                intent.putExtra("purchaseid", purchaseid);
                startActivity(intent);
            }
        });
        purchasePreListAdapter.setOnLongItemClickListener(new PurchasePreListAdapter.OnLongItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int purchaseid) {

                for (int i = 0; i < dataSource.size(); i++) {
                    if (dataSource.get(i).getPurchaseid() == purchaseid) {
                        initDialog("是否删除" + dataSource.get(i).getName() + "?", purchaseid);
                    }
                }
            }
        });
    }

    private void initDialog(String str, final int purchaseid) {
        BaseAnimatorSet bas_in = new FlipVerticalSwingEnter();
        BaseAnimatorSet bas_out = new FadeExit();
        final MaterialDialog dialogs = new MaterialDialog(this);
        dialogs.title("预约寄卖商品")
                .titleTextColor(Color.BLACK)
                .titleTextSize(16)
                .isTitleShow(false)
                .content(str)//
                .contentTextColor(Color.BLACK)
                .contentTextSize(18)
                .btnNum(2)
                .btnText("我再想想", "删除订单")
                .contentGravity(Gravity.CENTER_HORIZONTAL)
                .btnTextColor(Color.GRAY, Color.parseColor("#16a6ae"))
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();
        dialogs.setCanceledOnTouchOutside(false);
        dialogs.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                // 取消
                dialogs.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                // 删除订单
                deletePurchaseGoods(purchaseid);
                dialogs.dismiss();
            }
        });
    }

    private void deletePurchaseGoods(final int purchaseid) {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("purchaseid", purchaseid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.PurchaseDeleteOrder)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        initSnackBar("删除失败！");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        LogUtil.e("预约商品提交" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            for (int i = 0; i < dataSource.size(); i++) {
                                if (dataSource.get(i).getPurchaseid() == purchaseid) {
                                    dataSource.remove(i);
                                    purchasePreListAdapter.notifyDataSetChanged();
                                }
                            }
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    public void initSnackBar(String content) {
        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), content, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        RECEIVEDID = (int) SharedPreferencesUtils.getParam(OrderPurchaseAC.this, "RECRIVEDID", -1);
        ADDRESSS = String.valueOf(SharedPreferencesUtils.getParam(OrderPurchaseAC.this, "DEFAULTADDRESS", ""));
        if (RECEIVEDID == -1) {
            address_text.setText("添加地址");
        } else {
            address_text.setText(ADDRESSS);
        }
        initRecycleViewData(2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.purchase_adds_goods:
                Intent intent = new Intent(OrderPurchaseAC.this, PublishPurchaseAC.class);
                startActivity(intent);
                break;
            case R.id.purchase_add_address:
                Intent intent1 = new Intent(OrderPurchaseAC.this, AddressSettingCommomAC.class);
                startActivity(intent1);
                break;
            case R.id.confirm_buy:
                submitPurchaseGoodsList();
                break;
            case R.id.user_agreement:
                Intent inet = new Intent(OrderPurchaseAC.this, UserPrctocalAC.class);
                inet.putExtra("SETJUMPPOSITION", 432);
                startActivity(inet);
                break;

            default:
                break;
        }
    }

    private void submitPurchaseGoodsList() {
        if (purchasePreListModel.getPurchaseList().size() == 0) {
            initSnackBar("请先添加要寄卖的商品。");
            return;
        }
        if (RECEIVEDID == -1) {
            initSnackBar("请选择回寄地址！");
            return;
        }
        if (!isCheck) {
            initSnackBar("你还没有同意寄卖协议！");
            return;
        }
        ArrayList<Integer> purchaseidArr = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            purchaseidArr.add(i, dataSource.get(i).getPurchaseid());
        }
        // 图片数组
        JSONArray purchaseidList = new JSONArray();
        for (int i = 0; i < purchaseidArr.size(); i++) {
            int li = purchaseidArr.get(i);
            try {
                purchaseidList.put(i, li);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject js = new JSONObject();
        try {
            js.put("purchaseidList", purchaseidList);
            js.put("status", 2);
            js.put("receiverid", RECEIVEDID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.e("预约报名的" + js.toString());
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.PurchasePrePost)
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
                        LogUtil.e("预约商品提交" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            initRecycleViewData(1);
                            initSnackBar("预约商品成功！请等待审核！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }
}
