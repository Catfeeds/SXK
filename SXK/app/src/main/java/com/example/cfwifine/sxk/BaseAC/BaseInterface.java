package com.example.cfwifine.sxk.BaseAC;

/**
 * Created by cfwifine on 16/12/5.
 */

public class BaseInterface {

    // 登录注册相关基本接口
    public static String root = "http://shexiangke.jcq.tbapps.cn";
    // 注册
    public static String UserRegister= root + "/app/applogin/registerpost";
    // 登录
    public static String UserLogin = root + "/app/applogin/loginpost";
    // 验证码
    public static String UserVerifyCode = root + "/app/sms/addpost";
    // 忘记喵喵
    public static String UserForgetPaw = root + "/app/applogin/forgetpost";
    // 添加收货地址
    public static String SettingAddReceiveGoods = root +"/app/receiver/addpost";
    // 删除收货地址
    public static String SettingDelReceiveGoods = root +"/app/receiver/delpost";
    // 修改收货地址
    public static String SettingUpdateReceiveGoods = root +"/app/receiver/setpost";
    // 获取收货地址
    public static String SettingGetReceiveGoods = root +"/app/receiver/getpost";
    // 收货地址列表
    public static String SettingListReceiveGoods = root +"/app/receiver/listpost";


    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String PHPSESSION = "PHPSESSION";
}
