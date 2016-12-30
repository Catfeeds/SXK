package com.example.cfwifine.sxk.Section.MineNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/6.
 */

public class AddressListModel {


    /**
     * code : 1
     * total : 5
     * receiverList : [{"userid":10,"name":"就冷了","mobile":"15559910895","state":"安徽省","city":"安庆市","district":"枞阳县","address":"KKK摸摸摸","receiverid":111,"createtime":1481172865,"updatetime":1481172865,"isdefault":0},{"userid":10,"name":"王宇","mobile":"14444444444","state":"河南省","city":"安阳市","district":"安阳县","address":"来咯哦恶露TMD","receiverid":110,"createtime":1481167773,"updatetime":1481174368,"isdefault":0},{"userid":10,"name":"李琦就","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"宜秀区","address":"来咯兔子","receiverid":109,"createtime":1481167215,"updatetime":1483086603,"isdefault":0},{"userid":10,"name":"李琦","mobile":"15539910985","state":"河南省","city":"郑州市","district":"管城区","address":"来咯哦哦monkKKK","receiverid":104,"createtime":1481111043,"updatetime":1481111043,"isdefault":0},{"userid":10,"name":"董证","mobile":"15539910985","state":"河南省","city":"郑州市","district":"管城区","address":"好个咯啦咯啦咯","receiverid":83,"createtime":1481086071,"updatetime":1481086071,"isdefault":0}]
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
         * userid : 10
         * name : 就冷了
         * mobile : 15559910895
         * state : 安徽省
         * city : 安庆市
         * district : 枞阳县
         * address : KKK摸摸摸
         * receiverid : 111
         * createtime : 1481172865
         * updatetime : 1481172865
         * isdefault : 0
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
        private int isdefault;

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

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }
    }
}
