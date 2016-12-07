package com.example.cfwifine.sxk.Section.MineNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/6.
 */

public class AddressListModel {

    /**
     * code : 1
     * total : 1
     * receiverList : [{"userid":4,"name":"杨伟康","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":3,"createtime":1480931696,"updatetime":1480931696},{"userid":4,"name":"杨伟康","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":4,"createtime":1480932170,"updatetime":1480932170},{"userid":4,"name":"杨伟康","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":5,"createtime":1480950875,"updatetime":1480950875},{"userid":4,"name":"杨伟康","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":6,"createtime":1480955124,"updatetime":1480955124},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":7,"createtime":1480987562,"updatetime":1480987562},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":8,"createtime":1480987562,"updatetime":1480987562},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":9,"createtime":1480987562,"updatetime":1480987562},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":10,"createtime":1480987562,"updatetime":1480987562},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":11,"createtime":1480987562,"updatetime":1480987562},{"userid":4,"name":"曹操","mobile":"15659568279","state":"福建省","city":"厦门市","district":"思明区","address":"软件园二期","receiverid":12,"createtime":1480987563,"updatetime":1480987563}]
     */

    private int code;
    private int total;
    private List<ReceiverListBean> receiverList;

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

    public List<ReceiverListBean> getReceiverList() {
        return receiverList;
    }

    public void setReceiverList(List<ReceiverListBean> receiverList) {
        this.receiverList = receiverList;
    }

    public static class ReceiverListBean {
        /**
         * userid : 4
         * name : 杨伟康
         * mobile : 15659568279
         * state : 福建省
         * city : 厦门市
         * district : 思明区
         * address : 软件园二期
         * receiverid : 3
         * createtime : 1480931696
         * updatetime : 1480931696
         */

        private int userid;
        private String name;
        private String mobile;
        private String state;
        private String city;
        private String district;
        private String address;
        private int receiverid;
        private int createtime;
        private int updatetime;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getReceiverid() {
            return receiverid;
        }

        public void setReceiverid(int receiverid) {
            this.receiverid = receiverid;
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
