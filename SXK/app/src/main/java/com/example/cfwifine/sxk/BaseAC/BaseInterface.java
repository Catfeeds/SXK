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

    // 意见反馈
    public static String SupportReplay = root + "/app/feedback/addpost";

    // 其他
    // 用户协议
    public static String UserProtocal = root + "/app/agreementsetup/getpost";
    // 问答列表
    public static String AnswerList = root + "/app/question/listpost";
    // 获取问答
    public static String GetAnwer = root + "/app/question/getpost";

    // 图片存储位置
    public static String ClassfiyGetAllHotBrandImgUrl = "http://ohqqvdngk.bkt.clouddn.com/";

    // 商品管理
    public static String ReleaseGoods = root + "/app/rent/addpost";
    // 发布说明图片
    public static String ReleaseDetailPic = root + "/app/rentsetup/getpost";
    // 养护列表
    public static String CuringList = root + "/app/maintain/listpost";
    // 获取养护
    public static String GetCuring = root + "/app/maintain/getpost";
    // 养护下单生成订单
    public static String CuringPayOrderForChange = root + "/app/maintainorder/addpost";
    // 获取养护订单
    public static String GetCuringListOrder = root + "/app/maintainorder/listpost";
    // 养护确认完成
    public static String CuringConfirmDone = root + "/app/maintainorder/setpost";

    // 获取中间设置
    public static String GetCenterSetting = root + "/app/identifysetup/getpost";
    // 租赁列表
    public static String RentList = root + "/app/rent/listpost";
    // 修改租赁
    public static String ChangeRent = root + "/app/rent/setpost";
    // 获取租赁
    public static String GetRentDetail = root + "/app/rent/getpost";
    // 删除租赁
    public static String DelOrder = root + "/app/rent/delpost";


    // 租赁订单列表
    public static String MineRent = root + "/app/rentorder/listpost";
    // 修改租赁订单
    public static String ChangeRentOrder = root + "/app/rentorder/setpost";
    // 租赁评论
    public static String RentComment = root + "/app/rentcomment/addpost";
    // 生成订单
    // 获取租赁



    // 支付
    public static String PayOrder = root + "/app/payment/chargepost";

    // 生成订单
    public static String CreateOrder = root + "/app/rentorder/addpost";


    // 关于啵呗
    public static String AboutBOBEI = root + "/app/aboutsetup/getpost";




    // 储存用户的
    public static String NICKNAME = "NICKNAME";
    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String PHONENUMBER = "PHONENUMBER";
    public static String PHPSESSION = "PHPSESSION";
}
