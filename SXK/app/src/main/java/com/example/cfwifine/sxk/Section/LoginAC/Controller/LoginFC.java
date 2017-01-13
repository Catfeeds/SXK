package com.example.cfwifine.sxk.Section.LoginAC.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.BaseAC.MainAC;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.MineRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineAppraisa.MineItemAppraisaAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemCuringAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.MineItemRentAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.UserInfoAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.UserInfoRecycleViewCommomAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.UserInfoModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.google.gson.Gson;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

import java.util.ArrayList;
import java.util.HashMap;


public class LoginFC extends Fragment implements View.OnClickListener, PopupWindow.OnDismissListener {

    LoginPupWindow loginPupWindow;
    LinearLayout layouts, layoutx;
    CircleImageView circleImageView;
    RecyclerView mRecycles;
    ArrayList<String> applicationName = new ArrayList<>();
    ArrayList<Integer> imageView = new ArrayList<>();
    String[] applicationNames = {"我的钱包", "我的啵值", "分享奖励", "我的信用", "服务中心",
            "我的买卖", "我的收藏", "联系客服", "设置"};
    int[] imageViews = {R.drawable.mine_vallet, R.drawable.mine_integral, R.drawable.mine_share,
            R.drawable.mine_credit, R.drawable.mine_service_center, R.drawable.mine_business,
            R.drawable.mine_collection, R.drawable.mine_customer_service, R.drawable.mine_setting};
    private ImageView mHeadportrait;
    private ImageView mSex;
    private TextView mPerssonaldata;
    private TextView mFollow;
    private TextView mIdentity;
    private RelativeLayout mPublishs;
    private RelativeLayout mRents;
    private RelativeLayout mCare;
    private RelativeLayout mRecognize;
    RelativeLayout loginView;
    ScrollView loginSucView;
    private MainAC mainAC;
    private TextView username;
    private String userinfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (view == null){
        view = inflater.inflate(R.layout.fragment_login_fc, container, false);
        mainAC = (MainAC) getActivity();
        mainAC.initUserData();
        initView();
        initLoginBtn();
        initLoginOk();

        LogUtil.e("是否登录" + mainAC.isLogin());
//        }

        return view;
    }


    // TODO**************************************初始化界面******************************************
    private void initView() {
        loginView = (RelativeLayout) view.findViewById(R.id.login_view);
        loginSucView = (ScrollView) view.findViewById(R.id.login_success_view);

        if (mainAC.isLogin()) {
            loginSucView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.GONE);
        } else {
            loginView.setVisibility(View.VISIBLE);
            loginSucView.setVisibility(View.GONE);
        }

    }

    // TODO**************************************登录按钮******************************************
    private void initLoginBtn() {
        circleImageView = (CircleImageView) view.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(this);
        layouts = (LinearLayout) view.findViewById(R.id.login_char_lay);
        layoutx = (LinearLayout) view.findViewById(R.id.login_lay);

    }

    // TODO**************************************登录成功******************************************
    private void initLoginOk() {
        mRecycles = (RecyclerView) view.findViewById(R.id.mine_recycleView);
        username = (TextView) view.findViewById(R.id.username);
        username.setOnClickListener(this);
        mSex = (ImageView) view.findViewById(R.id.mine_sex);
        mHeadportrait = (ImageView) view.findViewById(R.id.headportrait);
        mPerssonaldata = (TextView) view.findViewById(R.id.mine_perssonal_data);
        mFollow = (TextView) view.findViewById(R.id.mine_follow);
        mIdentity = (TextView) view.findViewById(R.id.mine_id_auth);
        mPublishs = (RelativeLayout) view.findViewById(R.id.mine_publishs);
        mRents = (RelativeLayout) view.findViewById(R.id.mine_rents);
        mCare = (RelativeLayout) view.findViewById(R.id.mine_care);
        mRecognize = (RelativeLayout) view.findViewById(R.id.mine_recognize);

        mHeadportrait.setOnClickListener(this);
        mPerssonaldata.setOnClickListener(this);
        mFollow.setOnClickListener(this);
        mIdentity.setOnClickListener(this);
        mPublishs.setOnClickListener(this);
        mRents.setOnClickListener(this);
        mCare.setOnClickListener(this);
        mRecognize.setOnClickListener(this);

        for (int i = 0; i < 9; i++) {
            applicationName.add(applicationNames[i]);
            imageView.add(imageViews[i]);
        }

        mRecycles.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            // SC嵌套ReCV防止数据显示不完整
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        });
//        mRecycles.setHasFixedSize(true);

        final MineRecycleViewAdapter adapter = new MineRecycleViewAdapter(applicationName, imageView);
        adapter.setOnItemClickListener(new MineRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                jump(position);
            }
        });
        mRecycles.setAdapter(adapter);

        setValueForUserInfoView();

    }

    //登录成功后初始化个人信息
    private void setValueForUserInfoView() {
        userinfo = mainAC.getUserInfo();
        if (userinfo!=null){
            Gson gson = new Gson();
            UserInfoModel userInfoModel = gson.fromJson(userinfo, UserInfoModel.class);
            String picUrl = userInfoModel.getUser().getHeadimgurl();
            Glide.with(getActivity()).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(mHeadportrait);
            username.setText(userInfoModel.getUser().getNickname());
        }
    }

    private void jump(int position) {
        // 根据position跳转
        if (position == 7) {
            initMeiQiaView();
        } else {
            startActivity(UserInfoRecycleViewCommomAC.class, position);
        }


    }

    /**
     * 初始化美恰服务
     */
    private void initMeiQiaView() {
        HashMap<String, String> clientInfo = new HashMap<>();
        clientInfo.put("name", "证");
        clientInfo.put("avatar", "http://pic1a.nipic.com/2008-10-27/2008102715429376_2.jpg");
        clientInfo.put("gender", "男");
        clientInfo.put("tel", "1300000000");
        clientInfo.put("技能1", "休刊");
        Intent intent = new MQIntentBuilder(getActivity()).setClientInfo(clientInfo).build();
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circleImageView:
                circleImageView.setVisibility(View.INVISIBLE);
                layouts.setVisibility(View.INVISIBLE);
                layoutx.setVisibility(View.INVISIBLE);
                loginPupWindow = new LoginPupWindow(getActivity(), itemsOnClick);
                loginPupWindow.showAtLocation(getActivity().findViewById(R.id.activity_main_ac), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                loginPupWindow.setOnDismissListener(this);
                break;
            case R.id.headportrait:
                Toast.makeText(getContext(), "设置头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_perssonal_data:
                Toast.makeText(getContext(), "个人资料", Toast.LENGTH_SHORT).show();
                startActivity(UserInfoAC.class, 111);
                break;
            case R.id.mine_follow:
                Toast.makeText(getContext(), "我的关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_id_auth:
                Toast.makeText(getContext(), "身份认证", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_publishs:
                // 我的发布
                startActivity(MineItemAC.class, 1);
                break;
            case R.id.mine_rents:
                // 我的租赁
                startActivity(MineItemRentAC.class, 1);
                break;
            case R.id.mine_care:
                // 我的养护
                startActivity(MineItemCuringAC.class, 1);
                break;
            case R.id.mine_recognize:
                // 我的鉴定
                startActivity(MineItemAppraisaAC.class,1);
                break;
            default:
                break;

        }
    }


    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            loginPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.login_cancel:
                    loginPupWindow.dismiss();
                    break;
                case R.id.login_useboobe:
                    loginView.setVisibility(View.GONE);
                    loginSucView.setVisibility(View.VISIBLE);
                    startActivity(LoginUseBoobeAC.class, 111);
                    break;
                case R.id.login_usewexin:

                    break;
                case R.id.login_usesina:

                    break;
                case R.id.login_useqq:
                    break;
                default:
                    break;
            }


        }

    };

    private void startActivity(Class<?> cls, Integer jumpvalue) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra("JUMPPOSITION", jumpvalue);
        startActivity(intent);
    }

    @Override
    public void onDismiss() {
        circleImageView.setVisibility(View.VISIBLE);
        layouts.setVisibility(View.VISIBLE);
        layoutx.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainAC.initUserData();
        userinfo = mainAC.getUserInfo();
//        initView();
    }
}
