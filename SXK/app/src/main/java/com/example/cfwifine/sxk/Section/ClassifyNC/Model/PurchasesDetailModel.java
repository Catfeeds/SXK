package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/23.
 */

public class PurchasesDetailModel {
    /**
     * code : 1
     * purchase : {"description":"寄卖测试四","imgList":["sxk_userPic_201703231452580","sxk_userPic_201703231452591","sxk_userPic_201703231452592","sxk_userPic_201703231453003","sxk_userPic_201703231453004","sxk_userPic_201703231453005","sxk_userPic_201703231453016","sxk_userPic_201703231453017","sxk_userPic_201703231453018"],"name":"寄卖测试四","advancePrice":200600,"categoryid":3,"parentid":0,"brandid":4,"color":"白色","condition":3,"crowd":3,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":48,"createtime":1490251982,"updatetime":1490252049,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490251991,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]},"brand":{"name":"Dior 迪奥","img":"Shexiangke_jcq::brand_1481111844805","description":"Dior迪奥,由法国时装设计师克丽斯汀·迪奥(Christian Dior)于1946年创于巴黎,品牌设计不断创新却始终保持高贵优雅的风格品位,演绎时尚魅惑,自信活力.在时装、珠宝及手表、香水、彩妆和护肤领域,迪奥是优雅、卓越与奢华的完美呈现","status":1,"brandid":4,"createtime":1481111336,"updatetime":1481623694,"story":"Shexiangke_jcq::brand_story_1481623973217"},"user":{"mobile":"15539910985","userid":10,"createtime":1480922278,"updatetime":1489632334,"nickname":"肥仔","sex":1,"birthday":694195200,"profile":"哈哈","role":4,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","buyer":{"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"},"balance":4,"seller":{"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"},"score":0.06}}
     */

    private int code;
    private PurchaseBean purchase;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PurchaseBean getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseBean purchase) {
        this.purchase = purchase;
    }

    public static class PurchaseBean {
        /**
         * description : 寄卖测试四
         * imgList : ["sxk_userPic_201703231452580","sxk_userPic_201703231452591","sxk_userPic_201703231452592","sxk_userPic_201703231453003","sxk_userPic_201703231453004","sxk_userPic_201703231453005","sxk_userPic_201703231453016","sxk_userPic_201703231453017","sxk_userPic_201703231453018"]
         * name : 寄卖测试四
         * advancePrice : 200600
         * categoryid : 3
         * parentid : 0
         * brandid : 4
         * color : 白色
         * condition : 3
         * crowd : 3
         * attachList : [{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
         * userid : 10
         * orderid : 0
         * status : 3
         * sort : 0
         * purchaseid : 48
         * createtime : 1490251982
         * updatetime : 1490252049
         * receiver : {"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490251991,"isdefault":1}
         * risk : 1
         * marketPrice : 1
         * sellingPrice : 1
         * category : {"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}
         * brand : {"name":"Dior 迪奥","img":"Shexiangke_jcq::brand_1481111844805","description":"Dior迪奥,由法国时装设计师克丽斯汀·迪奥(Christian Dior)于1946年创于巴黎,品牌设计不断创新却始终保持高贵优雅的风格品位,演绎时尚魅惑,自信活力.在时装、珠宝及手表、香水、彩妆和护肤领域,迪奥是优雅、卓越与奢华的完美呈现","status":1,"brandid":4,"createtime":1481111336,"updatetime":1481623694,"story":"Shexiangke_jcq::brand_story_1481623973217"}
         * user : {"mobile":"15539910985","userid":10,"createtime":1480922278,"updatetime":1489632334,"nickname":"肥仔","sex":1,"birthday":694195200,"profile":"哈哈","role":4,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","buyer":{"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"},"balance":4,"seller":{"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"},"score":0.06}
         */

        private String description;
        private String name;
        private int advancePrice;
        private int categoryid;
        private int parentid;
        private int brandid;
        private String color;
        private int condition;
        private int crowd;
        private int userid;
        private int orderid;
        private int status;
        private int sort;
        private int purchaseid;
        private int createtime;
        private int updatetime;
        private ReceiverBean receiver;
        private int risk;
        private int marketPrice;
        private int sellingPrice;
        private CategoryBean category;
        private BrandBean brand;
        private UserBean user;
        private List<String> imgList;
        private List<AttachListBeanX> attachList;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAdvancePrice() {
            return advancePrice;
        }

        public void setAdvancePrice(int advancePrice) {
            this.advancePrice = advancePrice;
        }

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public int getBrandid() {
            return brandid;
        }

        public void setBrandid(int brandid) {
            this.brandid = brandid;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getCondition() {
            return condition;
        }

        public void setCondition(int condition) {
            this.condition = condition;
        }

        public int getCrowd() {
            return crowd;
        }

        public void setCrowd(int crowd) {
            this.crowd = crowd;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getPurchaseid() {
            return purchaseid;
        }

        public void setPurchaseid(int purchaseid) {
            this.purchaseid = purchaseid;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public ReceiverBean getReceiver() {
            return receiver;
        }

        public void setReceiver(ReceiverBean receiver) {
            this.receiver = receiver;
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(int sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public List<AttachListBeanX> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<AttachListBeanX> attachList) {
            this.attachList = attachList;
        }

        public static class ReceiverBean {
            /**
             * userid : 10
             * name : i模式KKK
             * mobile : 13339910985
             * state : 安徽省
             * city : 安庆市
             * district : 枞阳县
             * address : 艰难
             * receiverid : 148
             * createtime : 1489973223
             * updatetime : 1490251991
             * isdefault : 1
             */

            private int userid;
            private String name;
            private String mobile;
            private String state;
            private String city;
            private String district;
            private String address;
            private int receiverid;
            private int createtime;
            private int updatetime;
            private int isdefault;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getReceiverid() {
                return receiverid;
            }

            public void setReceiverid(int receiverid) {
                this.receiverid = receiverid;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public int getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(int isdefault) {
                this.isdefault = isdefault;
            }
        }

        public static class CategoryBean {
            /**
             * name : 腕表
             * img : Shexiangke_jcq::category_1481254654160
             * description : 腕表
             * parentid : 0
             * status : 1
             * categoryid : 3
             * createtime : 1481254119
             * updatetime : 1484529517
             * sort : 1
             * attachList : [{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]
             */

            private String name;
            private String img;
            private String description;
            private int parentid;
            private int status;
            private int categoryid;
            private int createtime;
            private int updatetime;
            private int sort;
            private List<AttachListBean> attachList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getParentid() {
                return parentid;
            }

            public void setParentid(int parentid) {
                this.parentid = parentid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<AttachListBean> getAttachList() {
                return attachList;
            }

            public void setAttachList(List<AttachListBean> attachList) {
                this.attachList = attachList;
            }

            public static class AttachListBean {
                /**
                 * attributeName : 测试二
                 * attributeType : 1
                 * attributeValueList : ["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]
                 */

                private String attributeName;
                private int attributeType;
                private List<String> attributeValueList;

                public String getAttributeName() {
                    return attributeName;
                }

                public void setAttributeName(String attributeName) {
                    this.attributeName = attributeName;
                }

                public int getAttributeType() {
                    return attributeType;
                }

                public void setAttributeType(int attributeType) {
                    this.attributeType = attributeType;
                }

                public List<String> getAttributeValueList() {
                    return attributeValueList;
                }

                public void setAttributeValueList(List<String> attributeValueList) {
                    this.attributeValueList = attributeValueList;
                }
            }
        }

        public static class BrandBean {
            /**
             * name : Dior 迪奥
             * img : Shexiangke_jcq::brand_1481111844805
             * description : Dior迪奥,由法国时装设计师克丽斯汀·迪奥(Christian Dior)于1946年创于巴黎,品牌设计不断创新却始终保持高贵优雅的风格品位,演绎时尚魅惑,自信活力.在时装、珠宝及手表、香水、彩妆和护肤领域,迪奥是优雅、卓越与奢华的完美呈现
             * status : 1
             * brandid : 4
             * createtime : 1481111336
             * updatetime : 1481623694
             * story : Shexiangke_jcq::brand_story_1481623973217
             */

            private String name;
            private String img;
            private String description;
            private int status;
            private int brandid;
            private int createtime;
            private int updatetime;
            private String story;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getBrandid() {
                return brandid;
            }

            public void setBrandid(int brandid) {
                this.brandid = brandid;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public String getStory() {
                return story;
            }

            public void setStory(String story) {
                this.story = story;
            }
        }

        public static class UserBean {
            /**
             * mobile : 15539910985
             * userid : 10
             * createtime : 1480922278
             * updatetime : 1489632334
             * nickname : 肥仔
             * sex : 1
             * birthday : 694195200
             * profile : 哈哈
             * role : 4
             * headimgurl : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545
             * buyer : {"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"}
             * balance : 4
             * seller : {"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"}
             * score : 0.06
             */

            private String mobile;
            private int userid;
            private int createtime;
            private int updatetime;
            private String nickname;
            private int sex;
            private int birthday;
            private String profile;
            private int role;
            private String headimgurl;
            private BuyerBean buyer;
            private int balance;
            private SellerBean seller;
            private double score;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public BuyerBean getBuyer() {
                return buyer;
            }

            public void setBuyer(BuyerBean buyer) {
                this.buyer = buyer;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public SellerBean getSeller() {
                return seller;
            }

            public void setSeller(SellerBean seller) {
                this.seller = seller;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public static class BuyerBean {
                /**
                 * name : ghjn
                 * idNumber : 43138119870827275X
                 * front : buyer_front_1486370551
                 * back : buyer_back_1486370551
                 */

                private String name;
                private String idNumber;
                private String front;
                private String back;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIdNumber() {
                    return idNumber;
                }

                public void setIdNumber(String idNumber) {
                    this.idNumber = idNumber;
                }

                public String getFront() {
                    return front;
                }

                public void setFront(String front) {
                    this.front = front;
                }

                public String getBack() {
                    return back;
                }

                public void setBack(String back) {
                    this.back = back;
                }
            }

            public static class SellerBean {
                /**
                 * name : 接口
                 * idNumber : 411381199301107614
                 * front : seller_front_1488251082
                 * back : seller_back_1488251082
                 */

                private String name;
                private String idNumber;
                private String front;
                private String back;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIdNumber() {
                    return idNumber;
                }

                public void setIdNumber(String idNumber) {
                    this.idNumber = idNumber;
                }

                public String getFront() {
                    return front;
                }

                public void setFront(String front) {
                    this.front = front;
                }

                public String getBack() {
                    return back;
                }

                public void setBack(String back) {
                    this.back = back;
                }
            }
        }

        public static class AttachListBeanX {
            /**
             * attributeName : 测试二
             * attributeValueList : ["牛皮"]
             */

            private String attributeName;
            private List<String> attributeValueList;

            public String getAttributeName() {
                return attributeName;
            }

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public List<String> getAttributeValueList() {
                return attributeValueList;
            }

            public void setAttributeValueList(List<String> attributeValueList) {
                this.attributeValueList = attributeValueList;
            }
        }
    }
}
