package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/3.
 */

public class ProductDetailModel {


    /**
     * code : 1
     * rent : {"userid":4,"name":"111","imgList":["Fg6UuqnAGh8F6dLsyfRuBRocxMMx","Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","FsRtPdHOlefQ_9gYlPn87dbrJEEY","Fk9DJuO5nSm5iYUqDs926G2q1x-d","FjiyJUsqyTr-6bHrnfzsXR5_iJgO","Fg6UuqnAGh8F6dLsyfRuBRocxMMx","Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","Fk9DJuO5nSm5iYUqDs926G2q1x-d","FsRtPdHOlefQ_9gYlPn87dbrJEEY"],"keyword":"11","description":"Adds","counterPrice":1100,"categoryid":12,"brandid":2,"color":"Xxx","condition":3,"crowd":3,"attachList":[{"attributeName":"相关配件","attributeValueList":["说明书"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":108,"createtime":1484806426,"updatetime":1484806644,"marketPrice":100,"rentPrice":100,"risk":100,"three":100,"seven":100,"fiften":1100,"twentyFive":1100,"category":{"name":"石英表","img":"Shexiangke_jcq::category_1482212302556","description":"测试","parentid":3,"sort":1,"attachList":[],"status":1,"categoryid":12,"createtime":1482212307,"updatetime":1482212307},"brand":{"name":"CHANEL 香奈儿","img":"Shexiangke_jcq::brand_1481085386923","description":"香奈儿品牌,秉承创始人嘉柏丽尔\u2022香奈儿女士划时代的创新理念与前瞻创意,成为现代女性美学的风向标。无论时尚精品、香水与美容品、腕表与高级珠宝,都致力于为女性塑造自由、优雅、与众不同的风格。","status":1,"brandid":2,"createtime":1481080425,"updatetime":1481623775,"story":"Shexiangke_jcq::brand_story_1481624334377"},"user":{"mobile":"15659568279","password":"$2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG","userid":4,"createtime":1480911994,"updatetime":1484896430,"nickname":"mdzz","role":1,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","sex":2,"birthday":959788800,"profile":"室内设计"}}
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
         * userid : 4
         * name : 111
         * imgList : ["Fg6UuqnAGh8F6dLsyfRuBRocxMMx","Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","FsRtPdHOlefQ_9gYlPn87dbrJEEY","Fk9DJuO5nSm5iYUqDs926G2q1x-d","FjiyJUsqyTr-6bHrnfzsXR5_iJgO","Fg6UuqnAGh8F6dLsyfRuBRocxMMx","Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","Fk9DJuO5nSm5iYUqDs926G2q1x-d","FsRtPdHOlefQ_9gYlPn87dbrJEEY"]
         * keyword : 11
         * description : Adds
         * counterPrice : 1100
         * categoryid : 12
         * brandid : 2
         * color : Xxx
         * condition : 3
         * crowd : 3
         * attachList : [{"attributeName":"相关配件","attributeValueList":["说明书"]}]
         * commentList : []
         * sale : 0
         * sort : 0
         * status : 2
         * rentid : 108
         * createtime : 1484806426
         * updatetime : 1484806644
         * marketPrice : 100
         * rentPrice : 100
         * risk : 100
         * three : 100
         * seven : 100
         * fiften : 1100
         * twentyFive : 1100
         * category : {"name":"石英表","img":"Shexiangke_jcq::category_1482212302556","description":"测试","parentid":3,"sort":1,"attachList":[],"status":1,"categoryid":12,"createtime":1482212307,"updatetime":1482212307}
         * brand : {"name":"CHANEL 香奈儿","img":"Shexiangke_jcq::brand_1481085386923","description":"香奈儿品牌,秉承创始人嘉柏丽尔\u2022香奈儿女士划时代的创新理念与前瞻创意,成为现代女性美学的风向标。无论时尚精品、香水与美容品、腕表与高级珠宝,都致力于为女性塑造自由、优雅、与众不同的风格。","status":1,"brandid":2,"createtime":1481080425,"updatetime":1481623775,"story":"Shexiangke_jcq::brand_story_1481624334377"}
         * user : {"mobile":"15659568279","password":"$2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG","userid":4,"createtime":1480911994,"updatetime":1484896430,"nickname":"mdzz","role":1,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","sex":2,"birthday":959788800,"profile":"室内设计"}
         */

        private int userid;
        private String name;
        private String keyword;
        private String description;
        private int counterPrice;
        private int categoryid;
        private int brandid;
        private String color;
        private int condition;
        private int crowd;
        private int sale;
        private int sort;
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
        private List<AttachListBean> attachList;
        private List<?> commentList;

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

        public List<AttachListBean> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<AttachListBean> attachList) {
            this.attachList = attachList;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }

        public static class CategoryBean {
            /**
             * name : 石英表
             * img : Shexiangke_jcq::category_1482212302556
             * description : 测试
             * parentid : 3
             * sort : 1
             * attachList : []
             * status : 1
             * categoryid : 12
             * createtime : 1482212307
             * updatetime : 1482212307
             */

            private String name;
            private String img;
            private String description;
            private int parentid;
            private int sort;
            private int status;
            private int categoryid;
            private int createtime;
            private int updatetime;
            private List<?> attachList;

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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public List<?> getAttachList() {
                return attachList;
            }

            public void setAttachList(List<?> attachList) {
                this.attachList = attachList;
            }
        }

        public static class BrandBean {
            /**
             * name : CHANEL 香奈儿
             * img : Shexiangke_jcq::brand_1481085386923
             * description : 香奈儿品牌,秉承创始人嘉柏丽尔•香奈儿女士划时代的创新理念与前瞻创意,成为现代女性美学的风向标。无论时尚精品、香水与美容品、腕表与高级珠宝,都致力于为女性塑造自由、优雅、与众不同的风格。
             * status : 1
             * brandid : 2
             * createtime : 1481080425
             * updatetime : 1481623775
             * story : Shexiangke_jcq::brand_story_1481624334377
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
             * mobile : 15659568279
             * password : $2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG
             * userid : 4
             * createtime : 1480911994
             * updatetime : 1484896430
             * nickname : mdzz
             * role : 1
             * headimgurl : http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP
             * sex : 2
             * birthday : 959788800
             * profile : 室内设计
             */

            private String mobile;
            private String password;
            private int userid;
            private int createtime;
            private int updatetime;
            private String nickname;
            private int role;
            private String headimgurl;
            private int sex;
            private int birthday;
            private String profile;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
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
        }

        public static class AttachListBean {
            /**
             * attributeName : 相关配件
             * attributeValueList : ["说明书"]
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
