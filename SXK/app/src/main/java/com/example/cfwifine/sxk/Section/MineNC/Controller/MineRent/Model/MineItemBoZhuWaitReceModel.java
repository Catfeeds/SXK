package com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/9.
 */

public class MineItemBoZhuWaitReceModel {

    /**
     * code : 1
     * total : 1
     * brandList : [{"orderNo":"2017010318135644858","rentid":7,"rent":{"name":"我是你","img":"FvpCpGbSKJrNGjXg43gjmrOI8_Ia","keyword":"手机哦的","marketPrice":2000000,"rentPrice":200000,"risk":5000,"three":10000,"seven":20000,"fiften":50000,"twentyFive":70000,"userid":4},"isRisk":2,"tenancy":{"name":"twentyFive","value":70000},"total":2070000,"userid":10,"receiver":{"userid":10,"name":"张三","mobile":"15539910985","state":"湖北省","city":"鄂州市","district":"鄂城区","address":"all佛祖JJ","receiverid":113,"createtime":1483091186,"updatetime":1483438299,"isdefault":1},"message":"测试","status":4,"orderid":54,"createtime":1483438436,"updatetime":1483944693,"backOddNumber":"123456789"}]
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
         * orderNo : 2017010318135644858
         * rentid : 7
         * rent : {"name":"我是你","img":"FvpCpGbSKJrNGjXg43gjmrOI8_Ia","keyword":"手机哦的","marketPrice":2000000,"rentPrice":200000,"risk":5000,"three":10000,"seven":20000,"fiften":50000,"twentyFive":70000,"userid":4}
         * isRisk : 2
         * tenancy : {"name":"twentyFive","value":70000}
         * total : 2070000
         * userid : 10
         * receiver : {"userid":10,"name":"张三","mobile":"15539910985","state":"湖北省","city":"鄂州市","district":"鄂城区","address":"all佛祖JJ","receiverid":113,"createtime":1483091186,"updatetime":1483438299,"isdefault":1}
         * message : 测试
         * status : 4
         * orderid : 54
         * createtime : 1483438436
         * updatetime : 1483944693
         * backOddNumber : 123456789
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
        private int status;
        private int orderid;
        private int createtime;
        private int updatetime;
        private String backOddNumber;

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

        public String getBackOddNumber() {
            return backOddNumber;
        }

        public void setBackOddNumber(String backOddNumber) {
            this.backOddNumber = backOddNumber;
        }

        public static class RentBean {
            /**
             * name : 我是你
             * img : FvpCpGbSKJrNGjXg43gjmrOI8_Ia
             * keyword : 手机哦的
             * marketPrice : 2000000
             * rentPrice : 200000
             * risk : 5000
             * three : 10000
             * seven : 20000
             * fiften : 50000
             * twentyFive : 70000
             * userid : 4
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
             * name : twentyFive
             * value : 70000
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
             * name : 张三
             * mobile : 15539910985
             * state : 湖北省
             * city : 鄂州市
             * district : 鄂城区
             * address : all佛祖JJ
             * receiverid : 113
             * createtime : 1483091186
             * updatetime : 1483438299
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