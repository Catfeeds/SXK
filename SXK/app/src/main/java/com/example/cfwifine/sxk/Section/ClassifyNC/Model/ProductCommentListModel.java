package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/6.
 */

public class ProductCommentListModel {

    /**
     * code : 1
     * total : 26
     * brandList : [{"orderid":44,"rentid":5,"imgList":["1","2","3","4","5","6","7","8","9"],"content":"这是一个测试评论","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","nickname":"mdzz","userid":4,"status":1,"commentid":2,"createtime":1482996740,"updatetime":1482996740},{"orderid":54,"rentid":7,"imgList":["sxk_userPic_201701091451340","sxk_userPic_201701091451341","sxk_userPic_201701091451352","sxk_userPic_201701091451353","sxk_userPic_201701091451364","sxk_userPic_201701091451365","sxk_userPic_201701091451376","sxk_userPic_201701091451377","sxk_userPic_201701091451378"],"content":"but咯哦哦饿了咯女","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170106150922","nickname":"安啦","userid":10,"status":1,"commentid":3,"createtime":1483944693,"updatetime":1483944693},{"orderid":68,"rentid":54,"imgList":["sxk_userPic_201701091649090","sxk_userPic_201701091649101","sxk_userPic_201701091649112","sxk_userPic_201701091649113","sxk_userPic_201701091649114","sxk_userPic_201701091649125","sxk_userPic_201701091649126","sxk_userPic_201701091649127","sxk_userPic_201701091649128"],"content":"我是一个评价！！！","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170106150922","nickname":"安啦","userid":10,"status":1,"commentid":4,"createtime":1483951749,"updatetime":1483951749},{"orderid":74,"rentid":58,"imgList":["sxk_userPic_201702061616240","sxk_userPic_201702061616241","sxk_userPic_201702061616252","sxk_userPic_201702061616253","sxk_userPic_201702061616264","sxk_userPic_201702061616265","sxk_userPic_201702061616276","sxk_userPic_201702061616277","sxk_userPic_201702061616278"],"content":"精忠叫我咯可以一直做","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170203112059","nickname":"测试","userid":10,"status":1,"commentid":5,"createtime":1486368987,"updatetime":1486368987},{"orderid":182,"rentid":114,"imgList":["sxk_userPic_201702061621280","sxk_userPic_201702061621291","sxk_userPic_201702061621292","sxk_userPic_201702061621293","sxk_userPic_201702061621304","sxk_userPic_201702061621305","sxk_userPic_201702061621306","sxk_userPic_201702061621317","sxk_userPic_201702061621318"],"content":"好的不错哦","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170203112059","nickname":"测试","userid":10,"status":1,"commentid":6,"createtime":1486369291,"updatetime":1486369291},{"orderid":169,"rentid":123,"imgList":["sxk_userPic_201702070922410","sxk_userPic_201702070922411","sxk_userPic_201702070922412","sxk_userPic_201702070922423","sxk_userPic_201702070922424","sxk_userPic_201702070922425","sxk_userPic_201702070922436","sxk_userPic_201702070922437","sxk_userPic_201702070922438"],"content":"科技厅哦why","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170203112059","nickname":"测试","userid":10,"status":1,"commentid":7,"createtime":1486430562,"updatetime":1486430562},{"orderid":181,"rentid":123,"imgList":["Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","Fg6UuqnAGh8F6dLsyfRuBRocxMMx","FsRtPdHOlefQ_9gYlPn87dbrJEEY","Fk9DJuO5nSm5iYUqDs926G2q1x-d","FjiyJUsqyTr-6bHrnfzsXR5_iJgO","Fg6UuqnAGh8F6dLsyfRuBRocxMMx","Fq1x4GD_AKZgOpeZQ5YLi4-XqTxl","FsRtPdHOlefQ_9gYlPn87dbrJEEY","Fk9DJuO5nSm5iYUqDs926G2q1x-d"],"content":"Hgjhgjhgjhgjghj","headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","nickname":"Asdfadsf","userid":4,"status":1,"commentid":8,"createtime":1486435821,"updatetime":1486435821}]
     */

    private int code;
    private int total;
    private List<BrandListBean> brandList;

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

    public List<BrandListBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListBean> brandList) {
        this.brandList = brandList;
    }

    public static class BrandListBean {
        /**
         * orderid : 44
         * rentid : 5
         * imgList : ["1","2","3","4","5","6","7","8","9"]
         * content : 这是一个测试评论
         * headimgurl : http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP
         * nickname : mdzz
         * userid : 4
         * status : 1
         * commentid : 2
         * createtime : 1482996740
         * updatetime : 1482996740
         */

        private int orderid;
        private int rentid;
        private String content;
        private String headimgurl;
        private String nickname;
        private int userid;
        private int status;
        private int commentid;
        private int createtime;
        private int updatetime;
        private List<Object> imgList;

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getRentid() {
            return rentid;
        }

        public void setRentid(int rentid) {
            this.rentid = rentid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public int getCommentid() {
            return commentid;
        }

        public void setCommentid(int commentid) {
            this.commentid = commentid;
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

        public List<Object> getImgList() {
            return imgList;
        }

        public void setImgList(List<Object> imgList) {
            this.imgList = imgList;
        }
    }
}
