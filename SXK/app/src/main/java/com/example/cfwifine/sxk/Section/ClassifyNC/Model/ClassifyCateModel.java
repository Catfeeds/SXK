package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/12.
 */

public class ClassifyCateModel {

    /**
     * code : 1
     * total : 6
     * categoryList : [{"name":"单肩包","img":"Shexiangke_jcq::category_1481267451750","description":"单肩包","parentid":1,"status":1,"categoryid":5,"createtime":1481266906,"updatetime":1481268819,"sort":5},{"name":"手提包","img":"Shexiangke_jcq::category_1481267403769","description":"手提包","parentid":1,"status":1,"categoryid":4,"createtime":1481266862,"updatetime":1481268833,"sort":4},{"name":"公文包","img":"Shexiangke_jcq::category_1481267573293","description":"公文包","parentid":1,"status":1,"categoryid":6,"createtime":1481267030,"updatetime":1481269947,"sort":3},{"name":"钱包","img":"Shexiangke_jcq::category_1481268475652","description":"钱包","parentid":1,"status":1,"categoryid":7,"createtime":1481267936,"updatetime":1481269876,"sort":3},{"name":"包袋","img":"Shexiangke_jcq::category_1481253223862","description":"手包，袋子","parentid":0,"status":1,"categoryid":1,"createtime":1481252873,"updatetime":1481268783,"sort":2},{"name":"腕表","img":"Shexiangke_jcq::category_1481254654160","description":"腕表","parentid":0,"status":1,"categoryid":3,"createtime":1481254119,"updatetime":1481268743,"sort":1}]
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
         * name : 单肩包
         * img : Shexiangke_jcq::category_1481267451750
         * description : 单肩包
         * parentid : 1
         * status : 1
         * categoryid : 5
         * createtime : 1481266906
         * updatetime : 1481268819
         * sort : 5
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
    }
}
