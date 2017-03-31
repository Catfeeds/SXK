package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/5.
 */

public class MineItemNoSailModel {

    /**
     * code : 1
     * total : 1
     * rentList : [{"userid":10,"name":"看","imgList":["sxk_userPic_201701041631480","sxk_userPic_201701041631481","sxk_userPic_201701041631482","sxk_userPic_201701041631493","sxk_userPic_201701041631494","sxk_userPic_201701041631505","sxk_userPic_201701041631506","sxk_userPic_201701041631517","sxk_userPic_201701041631518"],"keyword":"阿狸","description":"呵呵","counterPrice":222200,"categoryid":3,"color":"了","condition":3,"crowd":1,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":4,"rentid":52,"createtime":1483518706,"updatetime":1483601155}]
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
         * name : 看
         * imgList : ["sxk_userPic_201701041631480","sxk_userPic_201701041631481","sxk_userPic_201701041631482","sxk_userPic_201701041631493","sxk_userPic_201701041631494","sxk_userPic_201701041631505","sxk_userPic_201701041631506","sxk_userPic_201701041631517","sxk_userPic_201701041631518"]
         * keyword : 阿狸
         * description : 呵呵
         * counterPrice : 222200
         * categoryid : 3
         * color : 了
         * condition : 3
         * crowd : 1
         * attachList : [{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
         * commentList : []
         * sale : 0
         * sort : 0
         * status : 4
         * rentid : 52
         * createtime : 1483518706
         * updatetime : 1483601155
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
