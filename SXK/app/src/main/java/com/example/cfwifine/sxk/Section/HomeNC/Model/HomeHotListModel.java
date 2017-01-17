package com.example.cfwifine.sxk.Section.HomeNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/17.
 */

public class HomeHotListModel {

    /**
     * code : 1
     * total : 2
     * topicList : [{"name":"选对通勤包 职场更便利","img":"Shexiangke_jcq::finance_1484481721408","rentidList":[7,8],"sort":5,"status":1,"topicid":2,"createtime":1484481115,"updatetime":1484617460},{"name":"经典LV包 让你的秋冬造型更时尚","img":"Shexiangke_jcq::finance_1484481053459","rentidList":[38,30],"sort":4,"status":1,"topicid":1,"createtime":1484480997,"updatetime":1484482319}]
     */

    private int code;
    private int total;
    private List<TopicListBean> topicList;

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

    public List<TopicListBean> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicListBean> topicList) {
        this.topicList = topicList;
    }

    public static class TopicListBean {
        /**
         * name : 选对通勤包 职场更便利
         * img : Shexiangke_jcq::finance_1484481721408
         * rentidList : [7,8]
         * sort : 5
         * status : 1
         * topicid : 2
         * createtime : 1484481115
         * updatetime : 1484617460
         */

        private String name;
        private String img;
        private int sort;
        private int status;
        private int topicid;
        private int createtime;
        private int updatetime;
        private List<Integer> rentidList;

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

        public int getTopicid() {
            return topicid;
        }

        public void setTopicid(int topicid) {
            this.topicid = topicid;
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

        public List<Integer> getRentidList() {
            return rentidList;
        }

        public void setRentidList(List<Integer> rentidList) {
            this.rentidList = rentidList;
        }
    }
}
