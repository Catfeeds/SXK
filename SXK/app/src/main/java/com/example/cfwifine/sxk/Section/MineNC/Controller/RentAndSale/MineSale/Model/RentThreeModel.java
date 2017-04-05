package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/30.
 */

public class RentThreeModel {


    /**
     * code : 1
     * total : 2
     * purchaseList : [{"description":"测试寄卖二","imgList":["sxk_userPic_201703311107220","sxk_userPic_201703311107231","sxk_userPic_201703311107232","sxk_userPic_201703311107233","sxk_userPic_201703311107244","sxk_userPic_201703311107245","sxk_userPic_201703311107246","sxk_userPic_201703311107247","sxk_userPic_201703311107258"],"name":"测试寄卖的","advancePrice":36800,"categoryid":3,"parentid":0,"brandid":3,"color":"白色","condition":1,"crowd":1,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]}],"userid":10,"orderid":31,"status":5,"sort":0,"purchaseid":63,"createtime":1490929644,"updatetime":1490929789,"receiver":{"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490929648,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1,"oddNumber":"123124234123412","category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}},{"description":"测试寄卖一","imgList":["sxk_userPic_201703231508130","sxk_userPic_201703231508141","sxk_userPic_201703231508152","sxk_userPic_201703231508153","sxk_userPic_201703231508154","sxk_userPic_201703231508155","sxk_userPic_201703231508166","sxk_userPic_201703231508167","sxk_userPic_201703231508168"],"name":"测试寄卖一","advancePrice":25000,"categoryid":3,"parentid":0,"brandid":1,"color":"白色","condition":1,"crowd":3,"attachList":[],"userid":10,"orderid":29,"status":5,"sort":0,"purchaseid":50,"createtime":1490252897,"updatetime":1490865944,"receiver":{"userid":10,"name":"i模式KKK","mobile":"13339910985","state":"安徽省","city":"安庆市","district":"枞阳县","address":"艰难","receiverid":148,"createtime":1489973223,"updatetime":1490252996,"isdefault":1},"risk":1,"marketPrice":1,"sellingPrice":1,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}}]
     */

    private int code;
    private int total;
    private List<PurchaseListBean> purchaseList;

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

    public List<PurchaseListBean> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<PurchaseListBean> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public static class PurchaseListBean {
        /**
         * description : 测试寄卖二
         * imgList : ["sxk_userPic_201703311107220","sxk_userPic_201703311107231","sxk_userPic_201703311107232","sxk_userPic_201703311107233","sxk_userPic_201703311107244","sxk_userPic_201703311107245","sxk_userPic_201703311107246","sxk_userPic_201703311107247","sxk_userPic_201703311107258"]
         * name : 测试寄卖的
         * advancePrice : 36800
         * categoryid : 3
         * parentid : 0
         * brandid : 3
         * color : 白色
         * condition : 1
         * crowd : 1
         * attachList : [{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["撒的发大水"]}]
         * userid : 10
         * orderid : 31
         * status : 5
         * sort : 0
         * purchaseid : 63
         * createtime : 1490929644
         * updatetime : 1490929789
         * receiver : {"userid":10,"name":"per倪","mobile":"15536610985","state":"安徽省","city":"安庆市","district":"太湖县","address":"iOS哦PK","receiverid":147,"createtime":1489973186,"updatetime":1490929648,"isdefault":1}
         * risk : 1
         * marketPrice : 1
         * sellingPrice : 1
         * oddNumber : 123124234123412
         * category : {"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}
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
        private String oddNumber;
        private CategoryBean category;
        private List<String> imgList;
        private List<AttachListBeanX> attachList;

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

        public String getOddNumber() {
            return oddNumber;
        }

        public void setOddNumber(String oddNumber) {
            this.oddNumber = oddNumber;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public List<AttachListBeanX> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<AttachListBeanX> attachList) {
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
             * updatetime : 1490929648
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

        public static class CategoryBean {
            /**
             * name : 腕表
             * img : Shexiangke_jcq::category_1481254654160
             * description : 腕表
             * parentid : 0
             * status : 1
             * categoryid : 3
             * createtime : 1481254119
             * updatetime : 1484529517
             * sort : 1
             * attachList : [{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]
             */

            private String name;
            private String img;
            private String description;
            private int parentid;
            private int status;
            private int categoryid;
            private int createtime;
            private int updatetime;
            private int sort;
            private List<AttachListBean> attachList;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getParentid() {
                return parentid;
            }

            public void setParentid(int parentid) {
                this.parentid = parentid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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
                 * attributeType : 1
                 * attributeValueList : ["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]
                 */

                private String attributeName;
                private int attributeType;
                private List<String> attributeValueList;

                public String getAttributeName() {
                    return attributeName;
                }

                public void setAttributeName(String attributeName) {
                    this.attributeName = attributeName;
                }

                public int getAttributeType() {
                    return attributeType;
                }

                public void setAttributeType(int attributeType) {
                    this.attributeType = attributeType;
                }

                public List<String> getAttributeValueList() {
                    return attributeValueList;
                }

                public void setAttributeValueList(List<String> attributeValueList) {
                    this.attributeValueList = attributeValueList;
                }
            }
        }

        public static class AttachListBeanX {
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
}
