package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import java.util.List;

/**
 * Created by cfwifine on 17/3/24.
 */

public class MinePurchaseListModel {

    /**
     * code : 1
     * collection : {"collectionid":10,"userid":10,"list":[56,55,54,53,52],"createtime":1490322551,"updatetime":1490322624,"purchaseList":[{"description":"恶名你外婆共鸣您敏敏POS明明名额1额吉去啦娃娃惹急我订婚哦容易Win7名字why和你哥微信搜狗你仔细磨破很自信定义一种why","imgList":["sxk_userPic_201703231653570","sxk_userPic_201703231653581","sxk_userPic_201703231653582","sxk_userPic_201703231653583","sxk_userPic_201703231653584","sxk_userPic_201703231653595","sxk_userPic_201703231653596","sxk_userPic_201703231653597","sxk_userPic_201703231653598"],"name":"测试寄卖五","advancePrice":55000,"categoryid":3,"parentid":0,"brandid":4,"color":"红","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":56,"createtime":1490259240,"updatetime":1490259287,"receiver":{"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490259250,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1},{"description":"母鸡","imgList":["sxk_userPic_201703231617200","sxk_userPic_201703231617201","sxk_userPic_201703231617202","sxk_userPic_201703231617213","sxk_userPic_201703231617214","sxk_userPic_201703231617215","sxk_userPic_201703231617226","sxk_userPic_201703231617227","sxk_userPic_201703231617228"],"name":"母鸡","advancePrice":1,"categoryid":1,"parentid":0,"brandid":3,"color":"嘿","condition":3,"crowd":2,"attachList":[],"userid":12,"orderid":0,"status":3,"sort":0,"purchaseid":55,"createtime":1490257041,"updatetime":1490257086,"receiver":{"userid":12,"name":"这几年","mobile":"13477777777","state":"安徽省","city":"安庆市","district":"大观区","address":"的","receiverid":171,"createtime":1489975753,"updatetime":1490257046,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1}]}
     */

    private int code;
    private CollectionBean collection;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CollectionBean getCollection() {
        return collection;
    }

    public void setCollection(CollectionBean collection) {
        this.collection = collection;
    }

    public static class CollectionBean {
        /**
         * collectionid : 10
         * userid : 10
         * list : [56,55,54,53,52]
         * createtime : 1490322551
         * updatetime : 1490322624
         * purchaseList : [{"description":"恶名你外婆共鸣您敏敏POS明明名额1额吉去啦娃娃惹急我订婚哦容易Win7名字why和你哥微信搜狗你仔细磨破很自信定义一种why","imgList":["sxk_userPic_201703231653570","sxk_userPic_201703231653581","sxk_userPic_201703231653582","sxk_userPic_201703231653583","sxk_userPic_201703231653584","sxk_userPic_201703231653595","sxk_userPic_201703231653596","sxk_userPic_201703231653597","sxk_userPic_201703231653598"],"name":"测试寄卖五","advancePrice":55000,"categoryid":3,"parentid":0,"brandid":4,"color":"红","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["珍珠鱼皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]},{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"userid":10,"orderid":0,"status":3,"sort":0,"purchaseid":56,"createtime":1490259240,"updatetime":1490259287,"receiver":{"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490259250,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1},{"description":"母鸡","imgList":["sxk_userPic_201703231617200","sxk_userPic_201703231617201","sxk_userPic_201703231617202","sxk_userPic_201703231617213","sxk_userPic_201703231617214","sxk_userPic_201703231617215","sxk_userPic_201703231617226","sxk_userPic_201703231617227","sxk_userPic_201703231617228"],"name":"母鸡","advancePrice":1,"categoryid":1,"parentid":0,"brandid":3,"color":"嘿","condition":3,"crowd":2,"attachList":[],"userid":12,"orderid":0,"status":3,"sort":0,"purchaseid":55,"createtime":1490257041,"updatetime":1490257086,"receiver":{"userid":12,"name":"这几年","mobile":"13477777777","state":"安徽省","city":"安庆市","district":"大观区","address":"的","receiverid":171,"createtime":1489975753,"updatetime":1490257046,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1}]
         */

        private int collectionid;
        private int userid;
        private int createtime;
        private int updatetime;
        private List<Integer> list;
        private List<PurchaseListBean> purchaseList;

        public int getCollectionid() {
            return collectionid;
        }

        public void setCollectionid(int collectionid) {
            this.collectionid = collectionid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }

        public List<PurchaseListBean> getPurchaseList() {
            return purchaseList;
        }

        public void setPurchaseList(List<PurchaseListBean> purchaseList) {
            this.purchaseList = purchaseList;
        }

        public static class PurchaseListBean {
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
    }
}
