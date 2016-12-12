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
    // 获取热门品牌
    public static String ClassfiyGetAllHotBrand = root +"/app/brandhot/listpost";
    // 获取商品分类
    public static String ClassfiyGoodsCateify = root + "/app/category/listpost";

    // 首页
    // 获取活动列表
    public static String HomeEightItemActivityList = root + "/app/activity/listpost";
    public static String HomeEightItemActivityDetail = root + "/app/activity/getpost";

    // 社区
    // 获取宣传图
    public static String CommunityGetPic = root + "/app/communitysetup/getpost";
    // 获取模块列表
    public static String CommunityGetSectionList = root + "/app/communitymodule/listpost";
    // 添加话题
    public static String CommunityAddTopic = root + "/app/communitytopic/addpost";
    // 删除话题
    public static String CommunityDeleteTopic = root + "/app/communitytopic/delpost";
    // 修改话题
    public static String CommunityUpdateTopic = root + "/app/communitytopic/setpost";
    // 获取话题
    public static String CommunityGetTopic = root + "/app/communitytopic/getpost";
    // 话题列表
    public static String CommunityTopicList = root + "/app/communitytopic/listpost";


    // 图片存储位置
    public static String ClassfiyGetAllHotBrandImgUrl = "http://ohqqvdngk.bkt.clouddn.com/";

    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String PHONENUMBER = "PHONENUMBER";
    public static String PHPSESSION = "PHPSESSION";
}
