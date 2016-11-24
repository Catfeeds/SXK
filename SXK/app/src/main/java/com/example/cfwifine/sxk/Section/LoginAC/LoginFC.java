package com.example.cfwifine.sxk.Section.LoginAC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.View.CircleImageView;




public class LoginFC extends Fragment implements View.OnClickListener, PopupWindow.OnDismissListener {

    LoginPupWindow loginPupWindow;
    LinearLayout layouts,layoutx;
    CircleImageView circleImageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_fc, container, false);
        initLoginBtn();


        return view;
    }
    // TODO**************************************登录按钮******************************************
    private void initLoginBtn() {
        circleImageView = (CircleImageView)view.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(this);
        layouts = (LinearLayout)view.findViewById(R.id.login_char_lay);
        layoutx = (LinearLayout)view.findViewById(R.id.login_lay);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.circleImageView:
                circleImageView.setVisibility(View.INVISIBLE);
                layouts.setVisibility(View.INVISIBLE);
                layoutx.setVisibility(View.INVISIBLE);
                loginPupWindow = new LoginPupWindow(getActivity(),itemsOnClick);
                loginPupWindow.showAtLocation(getActivity().findViewById(R.id.activity_main_ac), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                loginPupWindow.setOnDismissListener(this);
                break;
        }
    }
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            loginPupWindow.dismiss();
            switch (v.getId()) {
                case R.id.login_cancel:
                    loginPupWindow.dismiss();


                    break;
                case R.id.login_useboobe:
                    startActivity(LoginUseBoobeAC.class);
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

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    @Override
    public void onDismiss() {
        circleImageView.setVisibility(View.VISIBLE);
        layouts.setVisibility(View.VISIBLE);
        layoutx.setVisibility(View.VISIBLE);
    }
}
