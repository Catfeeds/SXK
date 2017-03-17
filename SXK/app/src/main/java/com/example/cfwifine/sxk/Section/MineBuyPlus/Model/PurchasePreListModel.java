package com.example.cfwifine.sxk.Section.MineBuyPlus.Model;

import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductDetailModel;

import java.util.List;

/**
 * Created by cfwifine on 17/3/14.
 */

public class PurchasePreListModel {
    /**
     * code : 1
     * purchaseList : [{"description":"个咯死","imgList":["sxk_userPic_201703141440150","sxk_userPic_201703141440161","sxk_userPic_201703141440162","sxk_userPic_201703141440163","sxk_userPic_201703141440164","sxk_userPic_201703141440175","sxk_userPic_201703141440176","sxk_userPic_201703141440177","sxk_userPic_201703141440188"],"name":"阿里郎","advancePrice":155500,"categoryid":3,"parentid":0,"brandid":3,"color":"红色","condition":3,"crowd":2,"attachList":[],"userid":10,"orderid":0,"status":1,"sort":0,"purchaseid":2,"createtime":1489473618,"updatetime":1489473618,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}},{"description":"个咯死","imgList":["sxk_userPic_201703141441070","sxk_userPic_201703141441071","sxk_userPic_201703141441072","sxk_userPic_201703141441083","sxk_userPic_201703141441084","sxk_userPic_201703141441085","sxk_userPic_201703141441096","sxk_userPic_201703141441097","sxk_userPic_201703141441098"],"name":"阿里郎","advancePrice":155500,"categoryid":3,"parentid":0,"brandid":3,"color":"红色","condition":3,"crowd":2,"attachList":[],"userid":10,"orderid":0,"status":1,"sort":0,"purchaseid":3,"createtime":1489473670,"updatetime":1489473670,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}},{"description":"测试买卖接口","imgList":["sxk_userPic_201703141505360","sxk_userPic_201703141505371","sxk_userPic_201703141505372","sxk_userPic_201703141505373","sxk_userPic_201703141505374","sxk_userPic_201703141505385","sxk_userPic_201703141505386","sxk_userPic_201703141505387","sxk_userPic_201703141505388"],"name":"寄卖商品","advancePrice":20000,"categoryid":3,"parentid":0,"brandid":2,"color":"红色","condition":3,"crowd":2,"attachList":[],"userid":10,"orderid":0,"status":1,"sort":0,"purchaseid":4,"createtime":1489475139,"updatetime":1489475139,"category":{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1484529517,"sort":1,"attachList":[{"attributeName":"测试二","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋","阿萨德公司东方"]},{"attributeName":"测试1","attributeType":2,"attributeValueList":["1","2","3","4","撒的发大水"]}]}}]
     */

    private int code;
    private List<PurchaseListBean> purchaseList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<PurchaseListBean> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<PurchaseListBean> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public static class PurchaseListBean {
        /**
         * description : 个咯死
         * imgList : ["sxk_userPic_201703141440150","sxk_userPic_201703141440161","sxk_userPic_201703141440162","sxk_userPic_201703141440163","sxk_userPic_201703141440164","sxk_userPic_201703141440175","sxk_userPic_201703141440176","sxk_userPic_201703141440177","sxk_userPic_201703141440188"]
         * name : 阿里郎
         * advancePrice : 155500
         * categoryid : 3
         * parentid : 0
         * brandid : 3
         * color : 红色
         * condition : 3
         * crowd : 2
         * attachList : []
         * userid : 10
         * orderid : 0
         * status : 1
         * sort : 0
         * purchaseid : 2
         * createtime : 1489473618
         * updatetime : 1489473618
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
        private CategoryBean category;
        private List<String> imgList;
        private List<CategoryBean.AttachListBean> attachList;

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

        public List<CategoryBean.AttachListBean> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<CategoryBean.AttachListBean> attachList) {
            this.attachList = attachList;
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
    }
}
