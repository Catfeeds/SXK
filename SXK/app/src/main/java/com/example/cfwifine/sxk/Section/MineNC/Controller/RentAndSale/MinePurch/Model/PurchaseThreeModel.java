package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/30.
 */

public class PurchaseThreeModel {

    /**
     * code : 1
     * total : 2
     * orderList : [{"purchase":{"description":"恶名你外婆共鸣您敏敏POS明明名额1额吉去啦娃娃惹急我订婚哦容易Win7名字why和你哥微信搜狗你仔细磨破很自信定义一种why","imgList":["sxk_userPic_201703231653570","sxk_userPic_201703231653581","sxk_userPic_201703231653582","sxk_userPic_201703231653583","sxk_userPic_201703231653584","sxk_userPic_201703231653595","sxk_userPic_201703231653596","sxk_userPic_201703231653597","sxk_userPic_201703231653598"],"name":"测试寄卖五","advancePrice":55000,"categoryid":3,"parentid":0,"brandid":4,"color":"红","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":56,"createtime":1490259240,"updatetime":1490259287,"receiver":{"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490259250,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1},"orderNo":"2017032810012616176","total":1,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1},"isRisk":2,"message":"测试","point":0,"profit":0,"share":0,"purchaseid":56,"sellerid":10,"userid":10,"oddNumber":"123123123123","status":4,"orderid":28,"createtime":1490666486,"updatetime":1490865963,"notifyid":81}]
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
         * purchase : {"description":"恶名你外婆共鸣您敏敏POS明明名额1额吉去啦娃娃惹急我订婚哦容易Win7名字why和你哥微信搜狗你仔细磨破很自信定义一种why","imgList":["sxk_userPic_201703231653570","sxk_userPic_201703231653581","sxk_userPic_201703231653582","sxk_userPic_201703231653583","sxk_userPic_201703231653584","sxk_userPic_201703231653595","sxk_userPic_201703231653596","sxk_userPic_201703231653597","sxk_userPic_201703231653598"],"name":"测试寄卖五","advancePrice":55000,"categoryid":3,"parentid":0,"brandid":4,"color":"红","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":56,"createtime":1490259240,"updatetime":1490259287,"receiver":{"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490259250,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1}
         * orderNo : 2017032810012616176
         * total : 1
         * receiver : {"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1}
         * isRisk : 2
         * message : 测试
         * point : 0
         * profit : 0
         * share : 0
         * purchaseid : 56
         * sellerid : 10
         * userid : 10
         * oddNumber : 123123123123
         * status : 4
         * orderid : 28
         * createtime : 1490666486
         * updatetime : 1490865963
         * notifyid : 81
         */

        private PurchaseBean purchase;
        private String orderNo;
        private int total;
        private ReceiverBeanX receiver;
        private int isRisk;
        private String message;
        private int point;
        private int profit;
        private int share;
        private int purchaseid;
        private int sellerid;
        private int userid;
        private String oddNumber;
        private int status;
        private int orderid;
        private int createtime;
        private int updatetime;
        private int notifyid;

        public PurchaseBean getPurchase() {
            return purchase;
        }

        public void setPurchase(PurchaseBean purchase) {
            this.purchase = purchase;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ReceiverBeanX getReceiver() {
            return receiver;
        }

        public void setReceiver(ReceiverBeanX receiver) {
            this.receiver = receiver;
        }

        public int getIsRisk() {
            return isRisk;
        }

        public void setIsRisk(int isRisk) {
            this.isRisk = isRisk;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getPurchaseid() {
            return purchaseid;
        }

        public void setPurchaseid(int purchaseid) {
            this.purchaseid = purchaseid;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getOddNumber() {
            return oddNumber;
        }

        public void setOddNumber(String oddNumber) {
            this.oddNumber = oddNumber;
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

        public static class PurchaseBean {
            /**
             * description : 恶名你外婆共鸣您敏敏POS明明名额1额吉去啦娃娃惹急我订婚哦容易Win7名字why和你哥微信搜狗你仔细磨破很自信定义一种why
             * imgList : ["sxk_userPic_201703231653570","sxk_userPic_201703231653581","sxk_userPic_201703231653582","sxk_userPic_201703231653583","sxk_userPic_201703231653584","sxk_userPic_201703231653595","sxk_userPic_201703231653596","sxk_userPic_201703231653597","sxk_userPic_201703231653598"]
             * name : 测试寄卖五
             * advancePrice : 55000
             * categoryid : 3
             * parentid : 0
             * brandid : 4
             * color : 红
             * condition : 1
             * crowd : 1
             * attachList : [{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
             * userid : 10
             * orderid : 0
             * status : 3
             * sort : 0
             * purchaseid : 56
             * createtime : 1490259240
             * updatetime : 1490259287
             * receiver : {"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490259250,"isdefault":1}
             * risk : 1
             * marketPrice : 1
             * sellingPrice : 1
             */

            private String description;
            private String name;
            private int advancePrice;
            private int categoryid;
            private int parentid;
            private int brandid;
            private String color;
            private int condition;
            private int crowd;
            private int userid;
            private int orderid;
            private int status;
            private int sort;
            private int purchaseid;
            private int createtime;
            private int updatetime;
            private ReceiverBean receiver;
            private int risk;
            private int marketPrice;
            private int sellingPrice;
            private List<String> imgList;
            private List<AttachListBean> attachList;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAdvancePrice() {
                return advancePrice;
            }

            public void setAdvancePrice(int advancePrice) {
                this.advancePrice = advancePrice;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getPurchaseid() {
                return purchaseid;
            }

            public void setPurchaseid(int purchaseid) {
                this.purchaseid = purchaseid;
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

            public ReceiverBean getReceiver() {
                return receiver;
            }

            public void setReceiver(ReceiverBean receiver) {
                this.receiver = receiver;
            }

            public int getRisk() {
                return risk;
            }

            public void setRisk(int risk) {
                this.risk = risk;
            }

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getSellingPrice() {
                return sellingPrice;
            }

            public void setSellingPrice(int sellingPrice) {
                this.sellingPrice = sellingPrice;
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

            public static class ReceiverBean {
                /**
                 * userid : 10
                 * name : per倪
                 * mobile : 15536610985
                 * state : 安徽省
                 * city : 安庆市
                 * district : 太湖县
                 * address : iOS哦PK
                 * receiverid : 147
                 * createtime : 1489973186
                 * updatetime : 1490259250
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

        public static class ReceiverBeanX {
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
