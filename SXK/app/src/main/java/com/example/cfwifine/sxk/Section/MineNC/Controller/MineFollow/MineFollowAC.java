package com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RongTokenModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Model.FollowListModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.RequestStatueModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;

public class MineFollowAC extends AppCompatActivity implements SlideViewRecycleViewAdapter.IonSlidingViewClickListener, View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private RecyclerView follow_rv;
    private LinearLayout activity_mine_follow_ac;
    private SlideViewRecycleViewAdapter mAdapter;
    Dialog dialog;
    private FollowListModel.FollowBean dataSource = null;
    private int mineUserId = -1;
    private String RongToken;
    private UserInfoModel.UserBean UserDataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_follow_ac);
        dialog = LoadingUtils.createLoadingDialog(this,"加载中...");
        initView();
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("我的关注");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        follow_rv = (RecyclerView) findViewById(R.id.follow_rv);
        activity_mine_follow_ac = (LinearLayout) findViewById(R.id.activity_mine_follow_ac);

        String userinfo = getIntent().getStringExtra("USERINFO");
        Gson gson = new Gson();
        UserInfoModel userInfoModel = gson.fromJson(userinfo,UserInfoModel.class);
        if (userInfoModel.getCode() == 1){
            UserDataSource = userInfoModel.getUser();
            initMyFollow();
        }


    }

    private void initMyFollow() {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.FollowList)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("关注列表", "" + response);
                        Gson gson = new Gson();
                        FollowListModel followListModel = gson.fromJson(response, FollowListModel.class);
                        if (followListModel.getCode() == 1) {
                            dataSource = followListModel.getFollow();
                            setAdapter();
                        } else if (followListModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (followListModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });


    }

    private void initSnackBar(String s) {
        SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), s, Color.WHITE, Color.parseColor("#16a6ae"));
    }

    // 初始化聊天
    private void initRongData(final int userid, final String nickName, final String headImageUrl) {
//        mineUserId = (int) SharedPreferencesUtils.getParam(MineFollowAC.this, BaseInterface.USERID, 0);
//        LogUtil.e("我的ID" + mineUserId);
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("userid", UserDataSource.getUserid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.RONGYUNDemo)
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
                        Log.e("获取Token", "" + response);
                        Gson gson = new Gson();
                        RongTokenModel rongTokenModel = gson.fromJson(response, RongTokenModel.class);
                        if (rongTokenModel.getCode() == 1) {
                            RongToken = rongTokenModel.getToken();
                            connectRongServer(RongToken,userid,nickName,headImageUrl);
                        } else if (rongTokenModel.getCode() == 0) {
                            initSnackBar("请求失败！");
                        } else if (rongTokenModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    private void connectRongServer(String rongToken, final int userid, final String nickName, final String headImageUrl) {
        RongIM.connect(rongToken, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                if (userid == UserDataSource.getUserid()){
                    return;
                }
                if (RongIM.getInstance() != null) {
                    UserInfo userInfo = new UserInfo(String.valueOf(UserDataSource.getUserid()), UserDataSource.getNickname(), Uri.parse(UserDataSource.getHeadimgurl()));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String userId) {
                            return new UserInfo(String.valueOf(userid), nickName, Uri.parse(headImageUrl));
                        }
                    }, true);
                    RongIM.getInstance().startConversation(MineFollowAC.this, Conversation.ConversationType.PRIVATE, String.valueOf(userid), nickName);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                initSnackBar("连接服务器失败！请稍后重试！");
            }

            @Override
            public void onTokenIncorrect() {
                LogUtil.e("TOKEN不正确！");
            }
        });

    }


    private void setAdapter() {
        follow_rv.setLayoutManager(new LinearLayoutManager(this));
        follow_rv.setAdapter(mAdapter = new SlideViewRecycleViewAdapter(this,dataSource));
        follow_rv.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new SlideViewRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int userid, String nickName, String headImageUrl) {
                initRongData(userid,nickName,headImageUrl);
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int userid, int position) {
        initCancelFollow(userid,position);
    }

    private void initCancelFollow(int userid, final int position) {
        dialog.show();
        JSONObject js = new JSONObject();
        try {
            js.put("userid",userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(this, BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.FollowDel)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        initSnackBar("请求出错！");
                        dialog.dismiss();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        dialog.dismiss();
                        Log.e("", "" + response);
                        Gson gson = new Gson();
                        RequestStatueModel requestStatueModel = gson.fromJson(response, RequestStatueModel.class);
                        if (requestStatueModel.getCode() == 1) {
                            mAdapter.removeData(position);
                           initSnackBar("取消关注成功！");
                        } else if (requestStatueModel.getCode() == 0) {
                            initSnackBar("取消关注失败！");
                        } else if (requestStatueModel.getCode() == 911) {
                            initSnackBar("登录超时，请重新登录！");
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            default:
                break;
        }
    }
}
