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
    // 获取用户信息
    public static String GetUserInfo = root + "/app/user/getpost";
    // 修改用户信息
    public static String UpdateUserInfo = root + "/app/user/setpost";
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

    // 品牌
    // 获取所有品牌
    public static String ClassfiyGetAllBrand = root +"/app/brand/listpost";
    public static String ClassfiyGetAllHotBrand = root +"/app/brandhot/listpost";

    // 首页
    // 获取活动列表
    public static String HomeEightItemActivityList = root + "/app/activity/listpost";
    public static String HomeEightItemActivityDetail = root + "/app/activity/getpost";

    // 图片存储位置
    public static String ClassfiyGetAllHotBrandImgUrl = "http://ohqqvdngk.bkt.clouddn.com/";

    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String PHONENUMBER = "PHONENUMBER";
    public static String PHPSESSION = "PHPSESSION";
}
