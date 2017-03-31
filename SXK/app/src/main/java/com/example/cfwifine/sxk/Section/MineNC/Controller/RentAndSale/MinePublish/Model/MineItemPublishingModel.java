package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemPublishingModel {

    /**
     * code : 1
     * total : 1
     * rentList : [{"userid":10,"name":"安啦","imgList":["sxk_userPic_201612291413240","sxk_userPic_201612291413251","sxk_userPic_201612291413262","sxk_userPic_201612291413263","sxk_userPic_201612291413274","sxk_userPic_201612291413275","sxk_userPic_201612291413286","sxk_userPic_201612291413287","sxk_userPic_201612291413298"],"keyword":"JJ错么5啦咯啦咯啦咯啦咯啦咯啦咯啦咯兔兔咯了考虑兔兔突击涂土土图兔兔V领图兔兔图图V领TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤","description":"dzzzz","counterPrice":2000,"categoryid":3,"color":"红色","condition":3,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":30,"createtime":1482992009,"updatetime":1482992150,"marketPrice":99900,"rentPrice":88800,"risk":900,"three":1,"seven":1,"fiften":1,"twentyFive":1}]
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
         * name : 安啦
         * imgList : ["sxk_userPic_201612291413240","sxk_userPic_201612291413251","sxk_userPic_201612291413262","sxk_userPic_201612291413263","sxk_userPic_201612291413274","sxk_userPic_201612291413275","sxk_userPic_201612291413286","sxk_userPic_201612291413287","sxk_userPic_201612291413298"]
         * keyword : JJ错么5啦咯啦咯啦咯啦咯啦咯啦咯啦咯兔兔咯了考虑兔兔突击涂土土图兔兔V领图兔兔图图V领TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤
         * description : dzzzz
         * counterPrice : 2000
         * categoryid : 3
         * color : 红色
         * condition : 3
         * crowd : 2
         * attachList : [{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
         * commentList : []
         * sale : 0
         * sort : 0
         * status : 2
         * rentid : 30
         * createtime : 1482992009
         * updatetime : 1482992150
         * marketPrice : 99900
         * rentPrice : 88800
         * risk : 900
         * three : 1
         * seven : 1
         * fiften : 1
         * twentyFive : 1
         */

        private int userid;
        private String name;
        private String keyword;
        private String description;
        private int counterPrice;
        private int categoryid;
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

        public static class AttachListBean {
            /**
             * attributeName : 相关配件
             * attributeValueList : ["保修卡","防尘袋"]
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
