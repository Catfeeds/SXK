package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/5.
 */

public class MineItemCompletedGoodsModel {


    /**
     * code : 1
     * total : 4
     * orderList : [{"orderNo":"2017032915585636860","rentid":149,"rent":{"name":"测试租赁商品","imgList":["sxk_userPic_201703170918100","sxk_userPic_201703170918111","sxk_userPic_201703170918122","sxk_userPic_201703170918123","sxk_userPic_201703170918124","sxk_userPic_201703170918135","sxk_userPic_201703170918136","sxk_userPic_201703170918137","sxk_userPic_201703170918138"],"keyword":"破例pls","description":"测试租赁商品","counterPrice":22200,"categoryid":3,"parentid":0,"brandid":3,"color":"白色","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"status":2,"rentid":149,"createtime":1489713491,"updatetime":1489731722,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},"isRisk":2,"tenancy":{"name":"three","value":1},"total":2,"userid":10,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1},"message":"阿婆holy咯","point":0,"commission":0,"share":0,"oddNumber":"1273454856465","backOddNumber":"69816138626","status":5,"orderid":317,"createtime":1490774336,"updatetime":1490774540,"notifyid":85},{"orderNo":"2017030315431456916","rentid":138,"rent":{"name":"质检总局","img":"Fux7o_qm-Q7XVfBnGDMgc14GpeEo","keyword":"最近在哪","marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1,"userid":23},"isRisk":2,"tenancy":{"name":"three","value":1},"total":2,"userid":10,"receiver":{"userid":10,"name":"林峰","mobile":"15555555225","state":"安徽省","city":"宣城市","district":"其他","address":"呵呵红红火火恍恍惚惚","receiverid":139,"createtime":1487561142,"updatetime":1488523959,"isdefault":1},"message":"我是测试免费鉴定的时候结婚，的时候，的时候，的声音好吧，的","oddNumber":"","backOddNumber":"125455686399","status":5,"orderid":294,"createtime":1488526994,"updatetime":1488528902,"notifyid":64},{"orderNo":"2017020616200380707","rentid":114,"rent":{"name":"阿里郎","img":"sxk_userPic_201701201449530","keyword":"阿里郎","marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1,"userid":10},"isRisk":2,"tenancy":{"name":"three","value":1},"total":2,"userid":10,"receiver":{"userid":10,"name":"测试","mobile":"15539910985","state":"河南省","city":"南阳市","district":"邓州市","address":"箱子里","receiverid":125,"createtime":1484125464,"updatetime":1486369193,"isdefault":1},"message":"alumni","oddNumber":"","backOddNumber":"12315467697","status":5,"orderid":182,"createtime":1486369203,"updatetime":1486369672,"notifyid":40}]
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
         * orderNo : 2017032915585636860
         * rentid : 149
         * rent : {"name":"测试租赁商品","imgList":["sxk_userPic_201703170918100","sxk_userPic_201703170918111","sxk_userPic_201703170918122","sxk_userPic_201703170918123","sxk_userPic_201703170918124","sxk_userPic_201703170918135","sxk_userPic_201703170918136","sxk_userPic_201703170918137","sxk_userPic_201703170918138"],"keyword":"破例pls","description":"测试租赁商品","counterPrice":22200,"categoryid":3,"parentid":0,"brandid":3,"color":"白色","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"sale":0,"sort":0,"userid":10,"status":2,"rentid":149,"createtime":1489713491,"updatetime":1489731722,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1}
         * isRisk : 2
         * tenancy : {"name":"three","value":1}
         * total : 2
         * userid : 10
         * receiver : {"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1}
         * message : 阿婆holy咯
         * point : 0
         * commission : 0
         * share : 0
         * oddNumber : 1273454856465
         * backOddNumber : 69816138626
         * status : 5
         * orderid : 317
         * createtime : 1490774336
         * updatetime : 1490774540
         * notifyid : 85
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
        private double point;
        private int commission;
        private int share;
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

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
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
             * name : 测试租赁商品
             * imgList : ["sxk_userPic_201703170918100","sxk_userPic_201703170918111","sxk_userPic_201703170918122","sxk_userPic_201703170918123","sxk_userPic_201703170918124","sxk_userPic_201703170918135","sxk_userPic_201703170918136","sxk_userPic_201703170918137","sxk_userPic_201703170918138"]
             * keyword : 破例pls
             * description : 测试租赁商品
             * counterPrice : 22200
             * categoryid : 3
             * parentid : 0
             * brandid : 3
             * color : 白色
             * condition : 1
             * crowd : 1
             * attachList : [{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
             * sale : 0
             * sort : 0
             * userid : 10
             * status : 2
             * rentid : 149
             * createtime : 1489713491
             * updatetime : 1489731722
             * marketPrice : 1
             * rentPrice : 1
             * risk : 1
             * three : 1
             * seven : 1
             * fiften : 1
             * twentyFive : 1
             */

            private String name;
            private String keyword;
            private String description;
            private int counterPrice;
            private int categoryid;
            private int parentid;
            private int brandid;
            private String color;
            private int condition;
            private int crowd;
            private int sale;
            private int sort;
            private int userid;
            private int status;
            private int rentid;
            private int createtime;
            private int updatetime;
            private int marketPrice;
            private int rentPrice;
            private int risk;
            private int three;
            private int seven;
            private int fiften;
            private int twentyFive;
            private List<String> imgList;
            private List<AttachListBean> attachList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getCounterPrice() {
                return counterPrice;
            }

            public void setCounterPrice(int counterPrice) {
                this.counterPrice = counterPrice;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public int getParentid() {
                return parentid;
            }

            public void setParentid(int parentid) {
                this.parentid = parentid;
            }

            public int getBrandid() {
                return brandid;
            }

            public void setBrandid(int brandid) {
                this.brandid = brandid;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getCondition() {
                return condition;
            }

            public void setCondition(int condition) {
                this.condition = condition;
            }

            public int getCrowd() {
                return crowd;
            }

            public void setCrowd(int crowd) {
                this.crowd = crowd;
            }

            public int getSale() {
                return sale;
            }

            public void setSale(int sale) {
                this.sale = sale;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getRentid() {
                return rentid;
            }

            public void setRentid(int rentid) {
                this.rentid = rentid;
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

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }

            public List<AttachListBean> getAttachList() {
                return attachList;
            }

            public void setAttachList(List<AttachListBean> attachList) {
                this.attachList = attachList;
            }

            public static class AttachListBean {
                /**
                 * attributeName : 测试二
                 * attributeValueList : ["珍珠鱼皮"]
                 */

                private String attributeName;
                private List<String> attributeValueList;

                public String getAttributeName() {
                    return attributeName;
                }

                public void setAttributeName(String attributeName) {
                    this.attributeName = attributeName;
                }

                public List<String> getAttributeValueList() {
                    return attributeValueList;
                }

                public void setAttributeValueList(List<String> attributeValueList) {
                    this.attributeValueList = attributeValueList;
                }
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
             * name : i模式KKK
             * mobile : 13339910985
             * state : 安徽省
             * city : 安庆市
             * district : 枞阳县
             * address : 艰难
             * receiverid : 148
             * createtime : 1489973223
             * updatetime : 1490607073
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
