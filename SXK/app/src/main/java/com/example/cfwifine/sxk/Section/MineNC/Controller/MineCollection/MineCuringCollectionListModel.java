package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import java.util.List;

/**
 * Created by cfwifine on 17/2/28.
 */

public class MineCuringCollectionListModel {

    /**
     * code : 1
     * collection : {"collectionid":10,"userid":10,"list":[7,9,4],"createtime":1488266651,"updatetime":1488266683,"maintainList":[{"name":"小号手袋保养","img":"Shexiangke_jcq::maintain_1484579446052","keyword":"爱思打算","price":1,"description":"<p style=\"text-align: center;\"><img style=\"width: 356px; height: 3443px;\" alt=\"get_maintain_detail.png\" src=\"http://ohqqvdngk.bkt.clouddn.com/1484580966.png\" title=\"1484580966.png\" height=\"3443\" width=\"356\"/><\/p>","classifyid":2,"sort":0,"status":1,"maintainid":7,"createtime":1484032189,"updatetime":1486188007,"sale":8},{"name":"大号包袋养护","img":"Shexiangke_jcq::maintain_1484578603765","keyword":"专业为大型号手袋提供养护服务","price":1,"description":"<p style=\"text-align: center;\"><img style=\"width: 362px; height: 3450px;\" alt=\"get_maintain_detail.png\" src=\"http://ohqqvdngk.bkt.clouddn.com/1484581194.png\" title=\"1484581194.png\" height=\"3450\" width=\"362\"/><\/p>","classifyid":3,"sort":3,"status":1,"maintainid":9,"createtime":1484036964,"updatetime":1484581220},{"name":"中号手袋清洁保养","img":"Shexiangke_jcq::maintain_1484579424620","keyword":"专门为中型手袋提供便利和服务，十年专业养护，请联系后下单","price":1,"description":"<p>养护详情<br/><\/p>","classifyid":3,"sort":5,"status":1,"maintainid":4,"createtime":1481956592,"updatetime":1484713428,"sale":2}]}
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
         * list : [7,9,4]
         * createtime : 1488266651
         * updatetime : 1488266683
         * maintainList : [{"name":"小号手袋保养","img":"Shexiangke_jcq::maintain_1484579446052","keyword":"爱思打算","price":1,"description":"<p style=\"text-align: center;\"><img style=\"width: 356px; height: 3443px;\" alt=\"get_maintain_detail.png\" src=\"http://ohqqvdngk.bkt.clouddn.com/1484580966.png\" title=\"1484580966.png\" height=\"3443\" width=\"356\"/><\/p>","classifyid":2,"sort":0,"status":1,"maintainid":7,"createtime":1484032189,"updatetime":1486188007,"sale":8},{"name":"大号包袋养护","img":"Shexiangke_jcq::maintain_1484578603765","keyword":"专业为大型号手袋提供养护服务","price":1,"description":"<p style=\"text-align: center;\"><img style=\"width: 362px; height: 3450px;\" alt=\"get_maintain_detail.png\" src=\"http://ohqqvdngk.bkt.clouddn.com/1484581194.png\" title=\"1484581194.png\" height=\"3450\" width=\"362\"/><\/p>","classifyid":3,"sort":3,"status":1,"maintainid":9,"createtime":1484036964,"updatetime":1484581220},{"name":"中号手袋清洁保养","img":"Shexiangke_jcq::maintain_1484579424620","keyword":"专门为中型手袋提供便利和服务，十年专业养护，请联系后下单","price":1,"description":"<p>养护详情<br/><\/p>","classifyid":3,"sort":5,"status":1,"maintainid":4,"createtime":1481956592,"updatetime":1484713428,"sale":2}]
         */

        private int collectionid;
        private int userid;
        private int createtime;
        private int updatetime;
        private List<Integer> list;
        private List<MaintainListBean> maintainList;

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

        public List<MaintainListBean> getMaintainList() {
            return maintainList;
        }

        public void setMaintainList(List<MaintainListBean> maintainList) {
            this.maintainList = maintainList;
        }

        public static class MaintainListBean {
            /**
             * name : 小号手袋保养
             * img : Shexiangke_jcq::maintain_1484579446052
             * keyword : 爱思打算
             * price : 1
             * description : <p style="text-align: center;"><img style="width: 356px; height: 3443px;" alt="get_maintain_detail.png" src="http://ohqqvdngk.bkt.clouddn.com/1484580966.png" title="1484580966.png" height="3443" width="356"/></p>
             * classifyid : 2
             * sort : 0
             * status : 1
             * maintainid : 7
             * createtime : 1484032189
             * updatetime : 1486188007
             * sale : 8
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
            private int sale;

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

            public int getSale() {
                return sale;
            }

            public void setSale(int sale) {
                this.sale = sale;
            }
        }
    }
}
