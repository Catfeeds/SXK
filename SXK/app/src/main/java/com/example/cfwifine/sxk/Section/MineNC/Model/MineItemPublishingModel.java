package com.example.cfwifine.sxk.Section.MineNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemPublishingModel {


    /**
     * code : 1
     * total : 1
     * rentList : [{"userid":10,"name":"测试","imgList":[{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404020"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404021"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404032"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404033"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404044"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404045"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404056"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404057"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404058"}],"keyword":"测试","description":"背时鬼","counterPrice":1000,"categoryid":3,"rentid":14,"color":"红色","condition":3,"crowd":1,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","说明书"]}],"commentList":[],"sale":0,"sort":0,"status":2,"createtime":1482818644,"updatetime":1482819007,"marketPrice":10000,"rentPrice":10000,"risk":10000,"three":10000,"seven":10000,"fiften":10000,"twentyFive":10000}]
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
         * userid : 10
         * name : 测试
         * imgList : [{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404020"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404021"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404032"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404033"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404044"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404045"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404056"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404057"},{"image":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404058"}]
         * keyword : 测试
         * description : 背时鬼
         * counterPrice : 1000
         * categoryid : 3
         * rentid : 14
         * color : 红色
         * condition : 3
         * crowd : 1
         * attachList : [{"attributeName":"相关配件","attributeValueList":["保修卡","说明书"]}]
         * commentList : []
         * sale : 0
         * sort : 0
         * status : 2
         * createtime : 1482818644
         * updatetime : 1482819007
         * marketPrice : 10000
         * rentPrice : 10000
         * risk : 10000
         * three : 10000
         * seven : 10000
         * fiften : 10000
         * twentyFive : 10000
         */

        private int userid;
        private String name;
        private String keyword;
        private String description;
        private int counterPrice;
        private int categoryid;
        private int rentid;
        private String color;
        private int condition;
        private int crowd;
        private int sale;
        private int sort;
        private int status;
        private int createtime;
        private int updatetime;
        private int marketPrice;
        private int rentPrice;
        private int risk;
        private int three;
        private int seven;
        private int fiften;
        private int twentyFive;
        private List<ImgListBean> imgList;
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

        public int getRentid() {
            return rentid;
        }

        public void setRentid(int rentid) {
            this.rentid = rentid;
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

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
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

        public static class ImgListBean {
            /**
             * image : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_201612271404020
             */

            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class AttachListBean {
            /**
             * attributeName : 相关配件
             * attributeValueList : ["保修卡","说明书"]
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
