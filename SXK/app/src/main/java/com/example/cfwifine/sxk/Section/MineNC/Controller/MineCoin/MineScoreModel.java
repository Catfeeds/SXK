package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCoin;

import java.util.List;

/**
 * Created by cfwifine on 17/3/1.
 */

public class MineScoreModel {


    /**
     * code : 1
     * total : 14
     * recordList : [{"userid":10,"orderid":27,"amount":1,"score":0.06,"type":3,"mode":1,"point":0,"recordid":39,"createtime":1491037716,"updatetime":1491037716},{"userid":10,"orderid":34,"amount":1,"score":0.06,"type":3,"mode":1,"point":0,"recordid":38,"createtime":1491035746,"updatetime":1491035746},{"userid":10,"orderid":31,"amount":1,"score":0.06,"type":3,"mode":1,"point":0,"recordid":37,"createtime":1490929789,"updatetime":1490929789},{"userid":10,"orderid":320,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":36,"createtime":1490869295,"updatetime":1490869295},{"userid":10,"orderid":28,"amount":1,"score":0.06,"type":3,"mode":1,"point":0,"recordid":35,"createtime":1490865963,"updatetime":1490865963},{"userid":10,"orderid":29,"amount":1,"score":0.06,"type":3,"mode":1,"point":0,"recordid":34,"createtime":1490865944,"updatetime":1490865944},{"userid":10,"orderid":318,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":32,"createtime":1490775916,"updatetime":1490775916},{"userid":10,"orderid":317,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":31,"createtime":1490774540,"updatetime":1490774540},{"userid":10,"orderid":null,"amount":null,"score":0.06,"type":1,"mode":1,"point":0,"recordid":30,"createtime":1490774171,"updatetime":1490774171},{"userid":10,"orderid":314,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":29,"createtime":1490772560,"updatetime":1490772560},{"userid":10,"orderid":297,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":28,"createtime":1490772535,"updatetime":1490772535},{"userid":10,"orderid":313,"amount":2,"score":0.06,"type":1,"mode":1,"point":0,"recordid":27,"createtime":1490759133,"updatetime":1490759133},{"userid":10,"orderid":302,"amount":2,"score":0.04,"type":1,"mode":1,"point":0.02,"recordid":24,"createtime":1488790148,"updatetime":1488790148},{"userid":10,"orderid":294,"amount":2,"score":0.02,"type":1,"mode":1,"point":0.02,"recordid":18,"createtime":1488528902,"updatetime":1488528902}]
     */

    private int code;
    private int total;
    private List<RecordListBean> recordList;

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

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public static class RecordListBean {
        /**
         * userid : 10
         * orderid : 27
         * amount : 1
         * score : 0.06
         * type : 3
         * mode : 1
         * point : 0
         * recordid : 39
         * createtime : 1491037716
         * updatetime : 1491037716
         */

        private int userid;
        private int orderid;
        private int amount;
        private double score;
        private int type;
        private int mode;
        private double point;
        private int recordid;
        private int createtime;
        private int updatetime;

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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
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
    }
}
