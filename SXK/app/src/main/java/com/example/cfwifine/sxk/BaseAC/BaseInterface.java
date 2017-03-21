package com.example.cfwifine.sxk.BaseAC;

/**
 * Created by cfwifine on 16/12/5.
 */

public class BaseInterface {

    // 登录注册相关基本接口
    public static String root = "http://shexiangke.jcq.tbapps.cn";
//    http://shexiangke.boobe.com.cn
//    http://shexiangke.jcq.tbapps.cn
    // 注册
    public static String UserRegister= root + "/app/applogin/registerpost";
    // 登录
    public static String UserLogin = root + "/app/applogin/loginpost";
    // 微信，qq，新浪登录
    public static String LoginUserThird = root + "/app/applogin/authloginpost";
    // 租用流程
    public static String RentWater = root + "/app/processsetup/getpost";
    // 出租收益
    public static String RentInput = root+"/app/profitsetup/getpost";
    // 租赁费用
    public static String RentFee = root + "/app/costsetup/getpost";
    // 平台保障
    public static String PingtaiKeep = root+"/app/guaranteesetup/getpost";
    // 破损处理
    public static String BrokenDeal = root+"/app/damagesetup/getpost";
    // 平台服务费
    public static String PingTaiFuWuFei = root+"/app/chargesetup/getpost";
    // APP指导
    public static String APPIntro = root + "/app/guidesetup/getpost";
    // 长租
    public static String LongRent = root+"/app/longtermsetup/getpost";
    // 平台保障
    public static String PlatformSEC = root + "/app/guaranteesetup/getpost";

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
    // 首页轮播图
    public static String HomeRecycleBanner = root + "/app/advertisesetup/getpost";
    // 首页左上
    public static String HomeLeftThreeBlock = root + "/app/leftsetup/getpost";
    // 首页右上
    public static String HomeRightTopPic = root + "/app/rightupsetup/getpost";
    // 首页右下
    public static String HomeRightBottomPic = root + "/app/rightdownsetup/getpost";
    // 首页精选分类
    public static String HomeSelectedClass = root + "/app/class/listpost";
    // 首页精选分类主图
    public static String HomeClassSelected = root + "/app/class/getpost";
    //  首页热门话题列表
    public static String HomeHotTopList = root + "/app/topic/listpost";
    // 首页获取话题
    public static String HomeHotDetail = root + "/app/topic/getpost";

    // 获取活动列表
    public static String HomeEightItemActivityList = root + "/app/activity/listpost";
    public static String HomeEightItemActivityDetail = root + "/app/activity/getpost";
    public static String HomeActivityJoin = root + "/app/activityjoin/addpost";
    // 交换商品
    public static String HomeExchangeGoodsList = root + "/app/swapsetup/listpost";
    // boobe秀
    public static String HomeBoobeShow = root + "/app/showsetup/listpost";



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
    // 添加鉴定订单
    public static String AddAppraisaOrder = root + "/app/identifyorder/addpost";
    // 鉴定订单列表
    public static String AppraisaOrderList = root + "/app/identifyorder/listpost";
    // 租赁列表
    public static String RentList = root + "/app/rent/listpost";
    // 修改租赁
    public static String ChangeRent = root + "/app/rent/setpost";
    // 获取租赁
    public static String GetRentDetail = root + "/app/rent/getpost";
    // 删除租赁
    public static String DelOrder = root + "/app/rent/delpost";
    // 分享奖励
    public static String SharePresent = root + "/app/rewardsetup/getpost";

    // 租赁订单列表
    public static String MineRent = root + "/app/rentorder/listpost";
    // 修改租赁订单
    public static String ChangeRentOrder = root + "/app/rentorder/setpost";
    // 租赁评论
    public static String RentComment = root + "/app/rentcomment/addpost";
    // 关注列表
    public static String FollowList = root + "/app/follow/getpost";
    // 添加关注
    public static String FollowAdd = root + "/app/follow/addpost";
    // 删除关注
    public static String FollowDel = root + "/app/follow/delpost";
    // 收藏列表
    public static String CollectionList = root + "/app/collection/getpost";
    // 添加收藏
    public static String CollectionADD = root + "/app/collection/addpost";
    // 删除收藏
    public static String CollectionDel = root + "/app/collection/delpost";
    // 养护收藏列表
    public static String CuringCollectionList = root + "/app/collectionmaintain/getpost";
    // 养护收藏添加
    public static String CuringCollectionAdd = root + "/app/collectionmaintain/addpost";
    // 养护收藏删除
    public static String CuringCollectionDel = root + "/app/collectionmaintain/delpost";

    // 支付
    public static String PayOrder = root + "/app/payment/chargepost";
    // 生成订单
    public static String CreateOrder = root + "/app/rentorder/addpost";

    // 关于啵呗
    public static String AboutBOBEI = root + "/app/aboutsetup/getpost";

    // 融云接口
    public static String RONGYUNDemo = root + "/app/rongyun/gettokenpost";

    // 用户充值
    public static String ReCharge = root + "/app/rechargeorder/addpost";
    // 提现
    public static String WithDraw = root + "/app/withdraw/addpost";

    // 我的积分
    public static String MineScoin = root + "/app/record/listpost";

    // 分类二级页面的热门
    public static String ClassifySecondHot = root + "/app/categoryhot/listpost";

    // 获取订单详情
    public static String BuyerAndSellerOrderDetail = root + "/app/rentorder/getpost";

    // 我的钱包列表
    public static String MineWalletList = root+ "/app/wallet/listpost";
    // 商品详情评论列表
    public static String ProductDetailCommentList = root + "/app/rentcomment/listpost";

    // 寄卖
    // 预约寄卖列表
    public static String PurchasePreList = root + "/app/purchase/listpost";
    // 获取寄卖设置图片
    public static String PurchaseSetUpGet = root + "/app/purchasesetup/getpost";
    // 获取寄卖商品
    public static String PurchaseGetDetail = root + "/app/purchase/getpost";
    // 添加寄卖商品
    public static String PurchaseAddGoods = root + "/app/purchase/addpost";
    // 提交寄卖预约
    public static String PurchasePrePost = root + "/app/purchase/setpost";
    // 寄卖协议
    public static String PurchaseProtacl = root + "/app/purchaseagreementsetup/getpost";
    // 删除订单
    public static String PurchaseDeleteOrder = root + "/app/purchase/delpost";

    // 储存用户的
    public static String NICKNAME = "NICKNAME";
    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String PHONENUMBER = "PHONENUMBER";
    public static String PHPSESSION = "PHPSESSION";
    public static String USERID = "USERID";
    public static String PORITA = "PORITA";
    public static String SUCCESS = "SUCCESS";
    public static String KEYWORD = "KEYWORD";

}
