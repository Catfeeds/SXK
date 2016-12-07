package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 16/12/7.
 */

public class AddressDetailModel {

    /**
     * code : 1
     * receiver : {"userid":10,"name":"王宇","mobile":"15539910985","state":"河南省","city":"郑州市","district":"管城区","address":"好咯默默now","receiverid":105,"createtime":1481111465,"updatetime":1481111465}
     */

    private int code;
    private ReceiverBean receiver;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ReceiverBean getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverBean receiver) {
        this.receiver = receiver;
    }

    public static class ReceiverBean {
        /**
         * userid : 10
         * name : 王宇
         * mobile : 15539910985
         * state : 河南省
         * city : 郑州市
         * district : 管城区
         * address : 好咯默默now
         * receiverid : 105
         * createtime : 1481111465
         * updatetime : 1481111465
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
