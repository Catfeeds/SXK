package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

/**
 * Created by cfwifine on 17/3/3.
 */

public class BuyerAndSellerOrderDetailModel {

    /**
     * code : 1
     * order : {"orderNo":"2017030315431456916","rentid":138,"rent":{"name":"质检总局","img":"Fux7o_qm-Q7XVfBnGDMgc14GpeEo","keyword":"最近在哪","marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1,"userid":23},"isRisk":2,"tenancy":{"name":"three","value":1},"total":2,"userid":10,"receiver":{"userid":10,"name":"林峰","mobile":"15555555225","state":"安徽省","city":"宣城市","district":"其他","address":"呵呵红红火火恍恍惚惚","receiverid":139,"createtime":1487561142,"updatetime":1488523959,"isdefault":1},"message":"我是测试免费鉴定的时候结婚，的时候，的时候，的声音好吧，的","oddNumber":"","backOddNumber":"","status":2,"orderid":294,"createtime":1488526994,"updatetime":1488527001,"notifyid":64}
     */

    private int code;
    private OrderBean order;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * orderNo : 2017030315431456916
         * rentid : 138
         * rent : {"name":"质检总局","img":"Fux7o_qm-Q7XVfBnGDMgc14GpeEo","keyword":"最近在哪","marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1,"userid":23}
         * isRisk : 2
         * tenancy : {"name":"three","value":1}
         * total : 2
         * userid : 10
         * receiver : {"userid":10,"name":"林峰","mobile":"15555555225","state":"安徽省","city":"宣城市","district":"其他","address":"呵呵红红火火恍恍惚惚","receiverid":139,"createtime":1487561142,"updatetime":1488523959,"isdefault":1}
         * message : 我是测试免费鉴定的时候结婚，的时候，的时候，的声音好吧，的
         * oddNumber :
         * backOddNumber :
         * status : 2
         * orderid : 294
         * createtime : 1488526994
         * updatetime : 1488527001
         * notifyid : 64
         */

        private String orderNo;
        private int rentid;
        private RentBean rent;
        private int isRisk;
        private TenancyBean tenancy;
        private int total;
        private int userid;
        private ReceiverBean receiver;
        private String message;
        private String oddNumber;
        private String backOddNumber;
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

        public int getRentid() {
            return rentid;
        }

        public void setRentid(int rentid) {
            this.rentid = rentid;
        }

        public RentBean getRent() {
            return rent;
        }

        public void setRent(RentBean rent) {
            this.rent = rent;
        }

        public int getIsRisk() {
            return isRisk;
        }

        public void setIsRisk(int isRisk) {
            this.isRisk = isRisk;
        }

        public TenancyBean getTenancy() {
            return tenancy;
        }

        public void setTenancy(TenancyBean tenancy) {
            this.tenancy = tenancy;
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

        public String getOddNumber() {
            return oddNumber;
        }

        public void setOddNumber(String oddNumber) {
            this.oddNumber = oddNumber;
        }

        public String getBackOddNumber() {
            return backOddNumber;
        }

        public void setBackOddNumber(String backOddNumber) {
            this.backOddNumber = backOddNumber;
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

        public static class RentBean {
            /**
             * name : 质检总局
             * img : Fux7o_qm-Q7XVfBnGDMgc14GpeEo
             * keyword : 最近在哪
             * marketPrice : 1
             * rentPrice : 1
             * risk : 1
             * three : 1
             * seven : 1
             * fiften : 1
             * twentyFive : 1
             * userid : 23
             */

            private String name;
            private String img;
            private String keyword;
            private int marketPrice;
            private int rentPrice;
            private int risk;
            private int three;
            private int seven;
            private int fiften;
            private int twentyFive;
            private int userid;

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

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getRentPrice() {
                return rentPrice;
            }

            public void setRentPrice(int rentPrice) {
                this.rentPrice = rentPrice;
            }

            public int getRisk() {
                return risk;
            }

            public void setRisk(int risk) {
                this.risk = risk;
            }

            public int getThree() {
                return three;
            }

            public void setThree(int three) {
                this.three = three;
            }

            public int getSeven() {
                return seven;
            }

            public void setSeven(int seven) {
                this.seven = seven;
            }

            public int getFiften() {
                return fiften;
            }

            public void setFiften(int fiften) {
                this.fiften = fiften;
            }

            public int getTwentyFive() {
                return twentyFive;
            }

            public void setTwentyFive(int twentyFive) {
                this.twentyFive = twentyFive;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }
        }

        public static class TenancyBean {
            /**
             * name : three
             * value : 1
             */

            private String name;
            private int value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class ReceiverBean {
            /**
             * userid : 10
             * name : 林峰
             * mobile : 15555555225
             * state : 安徽省
             * city : 宣城市
             * district : 其他
             * address : 呵呵红红火火恍恍惚惚
             * receiverid : 139
             * createtime : 1487561142
             * updatetime : 1488523959
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
