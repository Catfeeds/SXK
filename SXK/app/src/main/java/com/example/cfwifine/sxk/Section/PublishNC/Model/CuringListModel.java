package com.example.cfwifine.sxk.Section.PublishNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/22.
 */

public class CuringListModel {

    /**
     * code : 1
     * classifyList : [{"name":"腕表养护","description":"这是一个测试1","sort":5,"status":1,"classifyid":3,"createtime":1481886145,"updatetime":1481887785},{"name":"测试类别01","description":"","sort":0,"status":1,"classifyid":1,"createtime":1481871677,"updatetime":1481871677},{"name":"包袋养护","description":"","sort":0,"status":1,"classifyid":2,"createtime":1481886136,"updatetime":1481886136}]
     * total : 1
     * maintainList : [{"name":"中号手袋清洁保养","img":"Shexiangke_jcq::maintain_1481957114116","keyword":"专门为中型手袋提供便利和服务，十年专业养护，请联系后下单","price":48000,"description":"<p>啥的发送到阿萨德发的，机房老大费到拉萨，2113<\/p><p><img class=\"loadingclass\" id=\"loading_iwzt5nxr\" src=\"/static/backend/ueditor/1.4.3/themes/default/images/spacer.gif\" title=\"正在上传...\"/><\/p><p><img src=\"http://ohqqvdngk.bkt.clouddn.com/1482376883.png\" title=\"1482376883.png\" alt=\"咖啡厅.png\"/><\/p><p>爱是快乐的积分卡拉斯加打开了房间爱快乐圣诞节看了按掉发生的积分卡加速度快了房间爱离开就<\/p>","classifyid":3,"sort":5,"status":1,"maintainid":4,"createtime":1481956592,"updatetime":1482376892}]
     */

    private int code;
    private int total;
    private List<ClassifyListBean> classifyList;
    private List<MaintainListBean> maintainList;

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

    public List<ClassifyListBean> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyListBean> classifyList) {
        this.classifyList = classifyList;
    }

    public List<MaintainListBean> getMaintainList() {
        return maintainList;
    }

    public void setMaintainList(List<MaintainListBean> maintainList) {
        this.maintainList = maintainList;
    }

    public static class ClassifyListBean {
        /**
         * name : 腕表养护
         * description : 这是一个测试1
         * sort : 5
         * status : 1
         * classifyid : 3
         * createtime : 1481886145
         * updatetime : 1481887785
         */

        private String name;
        private String description;
        private int sort;
        private int status;
        private int classifyid;
        private int createtime;
        private int updatetime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getClassifyid() {
            return classifyid;
        }

        public void setClassifyid(int classifyid) {
            this.classifyid = classifyid;
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
    }

    public static class MaintainListBean {
        /**
         * name : 中号手袋清洁保养
         * img : Shexiangke_jcq::maintain_1481957114116
         * keyword : 专门为中型手袋提供便利和服务，十年专业养护，请联系后下单
         * price : 48000
         * description : <p>啥的发送到阿萨德发的，机房老大费到拉萨，2113</p><p><img class="loadingclass" id="loading_iwzt5nxr" src="/static/backend/ueditor/1.4.3/themes/default/images/spacer.gif" title="正在上传..."/></p><p><img src="http://ohqqvdngk.bkt.clouddn.com/1482376883.png" title="1482376883.png" alt="咖啡厅.png"/></p><p>爱是快乐的积分卡拉斯加打开了房间爱快乐圣诞节看了按掉发生的积分卡加速度快了房间爱离开就</p>
         * classifyid : 3
         * sort : 5
         * status : 1
         * maintainid : 4
         * createtime : 1481956592
         * updatetime : 1482376892
         */

        private String name;
        private String img;
        private String keyword;
        private int price;
        private String description;
        private int classifyid;
        private int sort;
        private int status;
        private int maintainid;
        private int createtime;
        private int updatetime;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getClassifyid() {
            return classifyid;
        }

        public void setClassifyid(int classifyid) {
            this.classifyid = classifyid;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getMaintainid() {
            return maintainid;
        }

        public void setMaintainid(int maintainid) {
            this.maintainid = maintainid;
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
    }
}
