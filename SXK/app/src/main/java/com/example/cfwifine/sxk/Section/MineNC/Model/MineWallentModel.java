package com.example.cfwifine.sxk.Section.MineNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/3.
 */

public class MineWallentModel {

    /**
     * code : 1
     * total : 1
     * walletList : [{"balance":4,"amount":1,"type":1,"orderid":47,"walletid":1,"createtime":1488534868,"updatetime":1488534868}]
     */

    private int code;
    private int total;
    private List<WalletListBean> walletList;

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

    public List<WalletListBean> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<WalletListBean> walletList) {
        this.walletList = walletList;
    }

    public static class WalletListBean {
        /**
         * balance : 4
         * amount : 1
         * type : 1
         * orderid : 47
         * walletid : 1
         * createtime : 1488534868
         * updatetime : 1488534868
         */

        private int balance;
        private int amount;
        private int type;
        private int orderid;
        private int walletid;
        private int createtime;
        private int updatetime;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getWalletid() {
            return walletid;
        }

        public void setWalletid(int walletid) {
            this.walletid = walletid;
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
