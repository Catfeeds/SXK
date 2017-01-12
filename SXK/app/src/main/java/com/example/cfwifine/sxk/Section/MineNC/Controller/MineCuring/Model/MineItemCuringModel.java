package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/10.
 */

public class MineItemCuringModel {

    /**
     * code : 1
     * total : 2
     * orderList : [{"orderNo":"2017011209125123846","maintainid":8,"maintain":{"name":"sdfgdsfgdsfg","img":"Shexiangke_jcq::maintain_1484036913360","keyword":"dfsgdfsgds","price":1},"total":1,"userid":10,"receiver":{"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484180044,"isdefault":1},"message":"测试接口","status":2,"orderid":8,"createtime":1484183571,"updatetime":1484183577,"notifyid":15},{"orderNo":"2017011118384935495","maintainid":4,"maintain":{"name":"中号手袋清洁保养","img":"Shexiangke_jcq::maintain_1481957114116","keyword":"专门为中型手袋提供便利和服务，十年专业养护，请联系后下单","price":1},"total":1,"userid":10,"receiver":{"userid":10,"name":"张三","mobile":"15539910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"河南省南阳市","receiverid":113,"createtime":1483091186,"updatetime":1484125993,"isdefault":1},"message":"可以了","status":2,"orderid":5,"createtime":1484131129,"updatetime":1484131140,"notifyid":14}]
     */

    private int code;
    private int total;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * orderNo : 2017011209125123846
         * maintainid : 8
         * maintain : {"name":"sdfgdsfgdsfg","img":"Shexiangke_jcq::maintain_1484036913360","keyword":"dfsgdfsgds","price":1}
         * total : 1
         * userid : 10
         * receiver : {"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484180044,"isdefault":1}
         * message : 测试接口
         * status : 2
         * orderid : 8
         * createtime : 1484183571
         * updatetime : 1484183577
         * notifyid : 15
         */

        private String orderNo;
        private int maintainid;
        private MaintainBean maintain;
        private int total;
        private int userid;
        private ReceiverBean receiver;
        private String message;
        private int status;
        private int orderid;
        private int createtime;
        private int updatetime;
        private int notifyid;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getMaintainid() {
            return maintainid;
        }

        public void setMaintainid(int maintainid) {
            this.maintainid = maintainid;
        }

        public MaintainBean getMaintain() {
            return maintain;
        }

        public void setMaintain(MaintainBean maintain) {
            this.maintain = maintain;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public ReceiverBean getReceiver() {
            return receiver;
        }

        public void setReceiver(ReceiverBean receiver) {
            this.receiver = receiver;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
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

        public int getNotifyid() {
            return notifyid;
        }

        public void setNotifyid(int notifyid) {
            this.notifyid = notifyid;
        }

        public static class MaintainBean {
            /**
             * name : sdfgdsfgdsfg
             * img : Shexiangke_jcq::maintain_1484036913360
             * keyword : dfsgdfsgds
             * price : 1
             */

            private String name;
            private String img;
            private String keyword;
            private int price;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }

        public static class ReceiverBean {
            /**
             * userid : 10
             * name : 测试
             * mobile : 15539910985
             * state : 河南省
             * city : 南阳市
             * district : 邓州市
             * address : 箱子里
             * receiverid : 125
             * createtime : 1484125464
             * updatetime : 1484180044
             * isdefault : 1
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
}
