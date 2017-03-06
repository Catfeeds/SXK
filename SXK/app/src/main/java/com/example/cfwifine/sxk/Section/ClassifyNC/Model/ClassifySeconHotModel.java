package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/3/1.
 */

public class ClassifySeconHotModel {

    /**
     * code : 1
     * hotList : [{"name":"PRADA 普拉达","img":"Shexiangke_jcq::brand_1481078270786","description":"","status":1,"brandid":1,"createtime":1481077856,"updatetime":1481623817,"story":"Shexiangke_jcq::brand_story_1481624345961","hotid":10,"sort":5},{"name":"Dior 迪奥","img":"Shexiangke_jcq::brand_1481111844805","description":"Dior迪奥,由法国时装设计师克丽斯汀·迪奥(Christian Dior)于1946年创于巴黎,品牌设计不断创新却始终保持高贵优雅的风格品位,演绎时尚魅惑,自信活力.在时装、珠宝及手表、香水、彩妆和护肤领域,迪奥是优雅、卓越与奢华的完美呈现","status":1,"brandid":4,"createtime":1481111336,"updatetime":1481623694,"story":"Shexiangke_jcq::brand_story_1481623973217","hotid":7,"sort":0},{"name":"Louis Vuitton 路易威登","img":"Shexiangke_jcq::brand_1481111669409","description":"自1854年以来，代代相传至今的路易威登，以卓越品质、杰出创意和精湛工艺成为时尚旅行艺术的象征。产品系列包括:都市手袋，旅行用品，小型皮具，围巾配饰，鞋履，成衣，腕表，高级珠宝及个性化定制服务等。","status":1,"brandid":3,"createtime":1481111177,"updatetime":1481623761,"story":"Shexiangke_jcq::brand_story_1481624320336","hotid":8,"sort":0},{"name":"CHANEL 香奈儿","img":"Shexiangke_jcq::brand_1481085386923","description":"香奈儿品牌,秉承创始人嘉柏丽尔\u2022香奈儿女士划时代的创新理念与前瞻创意,成为现代女性美学的风向标。无论时尚精品、香水与美容品、腕表与高级珠宝,都致力于为女性塑造自由、优雅、与众不同的风格。","status":1,"brandid":2,"createtime":1481080425,"updatetime":1481623775,"story":"Shexiangke_jcq::brand_story_1481624334377","hotid":9,"sort":0}]
     */

    private int code;
    private List<HotListBean> hotList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<HotListBean> getHotList() {
        return hotList;
    }

    public void setHotList(List<HotListBean> hotList) {
        this.hotList = hotList;
    }

    public static class HotListBean {
        /**
         * name : PRADA 普拉达
         * img : Shexiangke_jcq::brand_1481078270786
         * description :
         * status : 1
         * brandid : 1
         * createtime : 1481077856
         * updatetime : 1481623817
         * story : Shexiangke_jcq::brand_story_1481624345961
         * hotid : 10
         * sort : 5
         */

        private String name;
        private String img;
        private String description;
        private int status;
        private int brandid;
        private int createtime;
        private int updatetime;
        private String story;
        private int hotid;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBrandid() {
            return brandid;
        }

        public void setBrandid(int brandid) {
            this.brandid = brandid;
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

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public int getHotid() {
            return hotid;
        }

        public void setHotid(int hotid) {
            this.hotid = hotid;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
