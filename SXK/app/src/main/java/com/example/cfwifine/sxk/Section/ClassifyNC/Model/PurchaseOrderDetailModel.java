package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/27.
 */

public class PurchaseOrderDetailModel {

    /**
     * code : 1
     * order : {"purchase":{"description":"咯人快1他咯破了X5心堤口路绿巨熊而你蛊惑look咳嗽1理论per留胡子T1敏敏her你途虎logo1募股pork","imgList":["sxk_userPic_201703271731020","sxk_userPic_201703271731031","sxk_userPic_201703271731032","sxk_userPic_201703271731033","sxk_userPic_201703271731054","sxk_userPic_201703271731055","sxk_userPic_201703271731066","sxk_userPic_201703271731067","sxk_userPic_201703271731068"],"name":"测试寄卖五","advancePrice":25500,"categoryid":3,"parentid":0,"brandid":3,"color":"红","condition":3,"crowd":3,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":58,"createtime":1490607066,"updatetime":1490607103,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1},"orderNo":"2017032717545769925","total":1,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1},"isRisk":2,"message":"了","point":0,"profit":0,"share":0,"purchaseid":58,"sellerid":10,"userid":10,"oddNumber":"","status":2,"orderid":27,"createtime":1490608497,"updatetime":1490608518,"notifyid":80}
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
         * purchase : {"description":"咯人快1他咯破了X5心堤口路绿巨熊而你蛊惑look咳嗽1理论per留胡子T1敏敏her你途虎logo1募股pork","imgList":["sxk_userPic_201703271731020","sxk_userPic_201703271731031","sxk_userPic_201703271731032","sxk_userPic_201703271731033","sxk_userPic_201703271731054","sxk_userPic_201703271731055","sxk_userPic_201703271731066","sxk_userPic_201703271731067","sxk_userPic_201703271731068"],"name":"测试寄卖五","advancePrice":25500,"categoryid":3,"parentid":0,"brandid":3,"color":"红","condition":3,"crowd":3,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":58,"createtime":1490607066,"updatetime":1490607103,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1}
         * orderNo : 2017032717545769925
         * total : 1
         * receiver : {"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1}
         * isRisk : 2
         * message : 了
         * point : 0
         * profit : 0
         * share : 0
         * purchaseid : 58
         * sellerid : 10
         * userid : 10
         * oddNumber :
         * status : 2
         * orderid : 27
         * createtime : 1490608497
         * updatetime : 1490608518
         * notifyid : 80
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
             * description : 咯人快1他咯破了X5心堤口路绿巨熊而你蛊惑look咳嗽1理论per留胡子T1敏敏her你途虎logo1募股pork
             * imgList : ["sxk_userPic_201703271731020","sxk_userPic_201703271731031","sxk_userPic_201703271731032","sxk_userPic_201703271731033","sxk_userPic_201703271731054","sxk_userPic_201703271731055","sxk_userPic_201703271731066","sxk_userPic_201703271731067","sxk_userPic_201703271731068"]
             * name : 测试寄卖五
             * advancePrice : 25500
             * categoryid : 3
             * parentid : 0
             * brandid : 3
             * color : 红
             * condition : 3
             * crowd : 3
             * attachList : [{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["2"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
             * userid : 10
             * orderid : 0
             * status : 3
             * sort : 0
             * purchaseid : 58
             * createtime : 1490607066
             * updatetime : 1490607103
             * receiver : {"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490607073,"isdefault":1}
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

            public static class AttachListBean {
                /**
                 * attributeName : 测试二
                 * attributeValueList : ["牛皮"]
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
