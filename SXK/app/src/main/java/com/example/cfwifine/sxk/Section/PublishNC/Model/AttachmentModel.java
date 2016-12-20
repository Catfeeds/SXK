package com.example.cfwifine.sxk.Section.PublishNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/20.
 */

public class AttachmentModel {

    /**
     * code : 1
     * total : 2
     * categoryList : [{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1481683256,"sort":1,"attachList":[{"attributeName":"包袋材质","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋"]}]},{"name":"包袋","img":"Shexiangke_jcq::category_1481253223862","description":"手包，袋子","parentid":0,"status":1,"categoryid":1,"createtime":1481252873,"updatetime":1481684020,"sort":2,"attachList":[]}]
     */

    private int code;
    private int total;
    private List<CategoryListBean> categoryList;

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

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class CategoryListBean {
        /**
         * name : 腕表
         * img : Shexiangke_jcq::category_1481254654160
         * description : 腕表
         * parentid : 0
         * status : 1
         * categoryid : 3
         * createtime : 1481254119
         * updatetime : 1481683256
         * sort : 1
         * attachList : [{"attributeName":"包袋材质","attributeType":1,"attributeValueList":["牛皮","鳄鱼皮","羊皮","珍珠鱼皮","帆布","测试"]},{"attributeName":"相关配件","attributeType":2,"attributeValueList":["盒子","保修卡","说明书","发票","防尘袋"]}]
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
             * attributeName : 包袋材质
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
