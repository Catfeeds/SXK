package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/3.
 */

public class ProductDetailModel {


    /**
     * code : 1
     * rent : {"name":"寄租","imgList":["sxk_userPic_201703271608220","sxk_userPic_201703271608221","sxk_userPic_201703271608232","sxk_userPic_201703271608233","sxk_userPic_201703271608244","sxk_userPic_201703271608245","sxk_userPic_201703271608246","sxk_userPic_201703271608257","sxk_userPic_201703271608258"],"keyword":"测试","description":"礼物🎁","counterPrice":2800,"categoryid":3,"parentid":0,"brandid":3,"color":"红","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"nickname":"肥仔","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","status":2,"rentid":157,"createtime":1490602104,"updatetime":1490602129,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]},"brand":{"name":"Louis Vuitton 路易威登","img":"Shexiangke_jcq::brand_1481111669409","description":"自1854年以来，代代相传至今的路易威登，以卓越品质、杰出创意和精湛工艺成为时尚旅行艺术的象征。产品系列包括:都市手袋，旅行用品，小型皮具，围巾配饰，鞋履，成衣，腕表，高级珠宝及个性化定制服务等。","status":1,"brandid":3,"createtime":1481111177,"updatetime":1481623761,"story":"Shexiangke_jcq::brand_story_1481624320336"},"user":{"mobile":"15539910985","userid":10,"createtime":1480922278,"updatetime":1489632334,"nickname":"肥仔","sex":1,"birthday":694195200,"profile":"哈哈","role":4,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","buyer":{"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"},"balance":4,"seller":{"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"},"score":0.06}}
     */

    private int code;
    private RentBean rent;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RentBean getRent() {
        return rent;
    }

    public void setRent(RentBean rent) {
        this.rent = rent;
    }

    public static class RentBean {
        /**
         * name : 寄租
         * imgList : ["sxk_userPic_201703271608220","sxk_userPic_201703271608221","sxk_userPic_201703271608232","sxk_userPic_201703271608233","sxk_userPic_201703271608244","sxk_userPic_201703271608245","sxk_userPic_201703271608246","sxk_userPic_201703271608257","sxk_userPic_201703271608258"]
         * keyword : 测试
         * description : 礼物🎁
         * counterPrice : 2800
         * categoryid : 3
         * parentid : 0
         * brandid : 3
         * color : 红
         * condition : 1
         * crowd : 1
         * attachList : [{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
         * sale : 0
         * sort : 0
         * userid : 10
         * nickname : 肥仔
         * headimgurl : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545
         * status : 2
         * rentid : 157
         * createtime : 1490602104
         * updatetime : 1490602129
         * marketPrice : 1
         * rentPrice : 1
         * risk : 1
         * three : 1
         * seven : 1
         * fiften : 1
         * twentyFive : 1
         * category : {"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}
         * brand : {"name":"Louis Vuitton 路易威登","img":"Shexiangke_jcq::brand_1481111669409","description":"自1854年以来，代代相传至今的路易威登，以卓越品质、杰出创意和精湛工艺成为时尚旅行艺术的象征。产品系列包括:都市手袋，旅行用品，小型皮具，围巾配饰，鞋履，成衣，腕表，高级珠宝及个性化定制服务等。","status":1,"brandid":3,"createtime":1481111177,"updatetime":1481623761,"story":"Shexiangke_jcq::brand_story_1481624320336"}
         * user : {"mobile":"15539910985","userid":10,"createtime":1480922278,"updatetime":1489632334,"nickname":"肥仔","sex":1,"birthday":694195200,"profile":"哈哈","role":4,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","buyer":{"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"},"balance":4,"seller":{"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"},"score":0.06}
         */

        private String name;
        private String keyword;
        private String description;
        private int counterPrice;
        private int categoryid;
        private int parentid;
        private int brandid;
        private String color;
        private int condition;
        private int crowd;
        private int sale;
        private int sort;
        private int userid;
        private String nickname;
        private String headimgurl;
        private int status;
        private int rentid;
        private int createtime;
        private int updatetime;
        private int marketPrice;
        private int rentPrice;
        private int risk;
        private int three;
        private int seven;
        private int fiften;
        private int twentyFive;
        private CategoryBean category;
        private BrandBean brand;
        private UserBean user;
        private List<String> imgList;
        private List<AttachListBeanX> attachList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCounterPrice() {
            return counterPrice;
        }

        public void setCounterPrice(int counterPrice) {
            this.counterPrice = counterPrice;
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

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRentid() {
            return rentid;
        }

        public void setRentid(int rentid) {
            this.rentid = rentid;
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

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getRentPrice() {
            return rentPrice;
        }

        public void setRentPrice(int rentPrice) {
            this.rentPrice = rentPrice;
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
        }

        public int getThree() {
            return three;
        }

        public void setThree(int three) {
            this.three = three;
        }

        public int getSeven() {
            return seven;
        }

        public void setSeven(int seven) {
            this.seven = seven;
        }

        public int getFiften() {
            return fiften;
        }

        public void setFiften(int fiften) {
            this.fiften = fiften;
        }

        public int getTwentyFive() {
            return twentyFive;
        }

        public void setTwentyFive(int twentyFive) {
            this.twentyFive = twentyFive;
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
             * name : Louis Vuitton 路易威登
             * img : Shexiangke_jcq::brand_1481111669409
             * description : 自1854年以来，代代相传至今的路易威登，以卓越品质、杰出创意和精湛工艺成为时尚旅行艺术的象征。产品系列包括:都市手袋，旅行用品，小型皮具，围巾配饰，鞋履，成衣，腕表，高级珠宝及个性化定制服务等。
             * status : 1
             * brandid : 3
             * createtime : 1481111177
             * updatetime : 1481623761
             * story : Shexiangke_jcq::brand_story_1481624320336
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
             * attributeValueList : ["珍珠鱼皮"]
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
