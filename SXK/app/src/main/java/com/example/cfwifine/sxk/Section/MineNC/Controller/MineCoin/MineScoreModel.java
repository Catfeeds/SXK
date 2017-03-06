package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCoin;

import java.util.List;

/**
 * Created by cfwifine on 17/3/1.
 */

public class MineScoreModel {

    /**
     * code : 1
     * total : 1
     * recordList : [{"userid":4,"orderid":262,"type":1,"mode":1,"point":0.02,"recordid":8,"createtime":1488268844,"updatetime":1488268844,"amount":10000}]
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
         * userid : 4
         * orderid : 262
         * type : 1
         * mode : 1
         * point : 0.02
         * recordid : 8
         * createtime : 1488268844
         * updatetime : 1488268844
         * amount : 10000
         */

        private int userid;
        private int orderid;
        private int type;
        private int mode;
        private double point;
        private int recordid;
        private int createtime;
        private int updatetime;
        private int amount;

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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
