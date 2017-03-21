package com.example.cfwifine.sxk.Section.HomeNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/20.
 */

public class RentListModel {

    /**
     * code : 1
     * total : 11
     * rentList : [{"name":"测试租赁商品","imgList":["sxk_userPic_201703170918100","sxk_userPic_201703170918111","sxk_userPic_201703170918122","sxk_userPic_201703170918123","sxk_userPic_201703170918124","sxk_userPic_201703170918135","sxk_userPic_201703170918136","sxk_userPic_201703170918137","sxk_userPic_201703170918138"],"keyword":"破例pls","description":"测试租赁商品","counterPrice":22200,"categoryid":3,"parentid":0,"brandid":3,"color":"白色","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"status":2,"rentid":149,"createtime":1489713491,"updatetime":1489731722,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"name":"阿鲁","imgList":["sxk_userPic_201703161803320","sxk_userPic_201703161803331","sxk_userPic_201703161803332","sxk_userPic_201703161803333","sxk_userPic_201703161803344","sxk_userPic_201703161803345","sxk_userPic_201703161803346","sxk_userPic_201703161803347","sxk_userPic_201703161803358"],"keyword":"图","description":"阿里郎","counterPrice":25500,"categoryid":3,"parentid":0,"brandid":3,"color":"阿狸","condition":3,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"status":2,"rentid":148,"createtime":1489658614,"updatetime":1489731751,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"name":"离开","imgList":["sxk_userPic_201703161754320","sxk_userPic_201703161754321","sxk_userPic_201703161754332","sxk_userPic_201703161754333","sxk_userPic_201703161754334","sxk_userPic_201703161754345","sxk_userPic_201703161754346","sxk_userPic_201703161754347","sxk_userPic_201703161754348"],"keyword":"it","description":"还没","counterPrice":45700,"categoryid":3,"parentid":0,"brandid":3,"color":"勾协","condition":3,"crowd":1,"attachList":[],"sale":0,"sort":0,"userid":10,"status":2,"rentid":147,"createtime":1489658073,"updatetime":1489731767,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"name":"离开","imgList":["sxk_userPic_201703161753060","sxk_userPic_201703161753071","sxk_userPic_201703161753072","sxk_userPic_201703161753083","sxk_userPic_201703161753084","sxk_userPic_201703161753085","sxk_userPic_201703161753096","sxk_userPic_201703161753107","sxk_userPic_201703161753108"],"keyword":"it","description":"还没","counterPrice":45700,"categoryid":3,"parentid":0,"brandid":3,"color":"勾协","condition":3,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["鳄鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"status":2,"rentid":146,"createtime":1489657989,"updatetime":1489731780,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1}]
     */

    private int code;
    private int total;
    private List<RentListBean> rentList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RentListBean> getRentList() {
        return rentList;
    }

    public void setRentList(List<RentListBean> rentList) {
        this.rentList = rentList;
    }

    public static class RentListBean {
        /**
         * name : 测试租赁商品
         * imgList : ["sxk_userPic_201703170918100","sxk_userPic_201703170918111","sxk_userPic_201703170918122","sxk_userPic_201703170918123","sxk_userPic_201703170918124","sxk_userPic_201703170918135","sxk_userPic_201703170918136","sxk_userPic_201703170918137","sxk_userPic_201703170918138"]
         * keyword : 破例pls
         * description : 测试租赁商品
         * counterPrice : 22200
         * categoryid : 3
         * parentid : 0
         * brandid : 3
         * color : 白色
         * condition : 1
         * crowd : 1
         * attachList : [{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
         * sale : 0
         * sort : 0
         * userid : 10
         * status : 2
         * rentid : 149
         * createtime : 1489713491
         * updatetime : 1489731722
         * marketPrice : 1
         * rentPrice : 1
         * risk : 1
         * three : 1
         * seven : 1
         * fiften : 1
         * twentyFive : 1
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
        private List<String> imgList;
        private List<AttachListBean> attachList;

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

        public static class AttachListBean {
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
