package com.example.cfwifine.sxk.Section.MineNC.Controller.MineAppraisa;

import java.util.List;

/**
 * Created by cfwifine on 17/1/13.
 */

public class MineItemAppraisaModel {


    /**
     * code : 1
     * total : 3
     * orderList : [{"orderNo":"2017011316005017817","setup":{"campaign":"Shexiangke_jcq::identifySetup_campaign1481803686965"},"brand":{"brandid":3,"name":"Louis Vuitton 路易威登"},"genre":{"name":"腕表","price":1},"total":1,"userid":10,"receiver":{"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484278760,"isdefault":1},"status":3,"orderid":9,"createtime":1484294450,"updatetime":1491377351,"notifyid":20},{"orderNo":"2017011314304237173","setup":{"campaign":"Shexiangke_jcq::identifySetup_campaign1481803686965"},"brand":{"brandid":2,"name":"CHANEL 香奈儿"},"genre":{"name":"腕表","price":1},"total":1,"userid":10,"receiver":{"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484278760,"isdefault":1},"status":3,"orderid":8,"createtime":1484289042,"updatetime":1491377384,"notifyid":19},{"orderNo":"2017011314301774789","setup":{"campaign":"Shexiangke_jcq::identifySetup_campaign1481803686965"},"brand":{"brandid":3,"name":"Louis Vuitton 路易威登"},"genre":{"name":"包袋","price":1},"total":1,"userid":10,"receiver":{"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484278760,"isdefault":1},"status":2,"orderid":7,"createtime":1484289017,"updatetime":1484289025,"notifyid":18}]
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
         * orderNo : 2017011316005017817
         * setup : {"campaign":"Shexiangke_jcq::identifySetup_campaign1481803686965"}
         * brand : {"brandid":3,"name":"Louis Vuitton 路易威登"}
         * genre : {"name":"腕表","price":1}
         * total : 1
         * userid : 10
         * receiver : {"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1484278760,"isdefault":1}
         * status : 3
         * orderid : 9
         * createtime : 1484294450
         * updatetime : 1491377351
         * notifyid : 20
         */

        private String orderNo;
        private SetupBean setup;
        private BrandBean brand;
        private GenreBean genre;
        private int total;
        private int userid;
        private ReceiverBean receiver;
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

        public SetupBean getSetup() {
            return setup;
        }

        public void setSetup(SetupBean setup) {
            this.setup = setup;
        }

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public GenreBean getGenre() {
            return genre;
        }

        public void setGenre(GenreBean genre) {
            this.genre = genre;
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

        public static class SetupBean {
            /**
             * campaign : Shexiangke_jcq::identifySetup_campaign1481803686965
             */

            private String campaign;

            public String getCampaign() {
                return campaign;
            }

            public void setCampaign(String campaign) {
                this.campaign = campaign;
            }
        }

        public static class BrandBean {
            /**
             * brandid : 3
             * name : Louis Vuitton 路易威登
             */

            private int brandid;
            private String name;

            public int getBrandid() {
                return brandid;
            }

            public void setBrandid(int brandid) {
                this.brandid = brandid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class GenreBean {
            /**
             * name : 腕表
             * price : 1
             */

            private String name;
            private int price;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
             * updatetime : 1484278760
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
