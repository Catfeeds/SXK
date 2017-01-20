package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import java.util.List;

/**
 * Created by cfwifine on 17/1/20.
 */

public class MineCollectionModel {

    /**
     * code : 1
     * collection : {"collectionid":4,"userid":4,"list":[114,112],"createtime":1484901545,"updatetime":1484904254,"rentList":[{"userid":10,"name":"阿里郎","imgList":["sxk_userPic_201701201449530","sxk_userPic_201701201449541","sxk_userPic_201701201449552","sxk_userPic_201701201449553","sxk_userPic_201701201449564","sxk_userPic_201701201449565","sxk_userPic_201701201449576","sxk_userPic_201701201449577","sxk_userPic_201701201449578"],"keyword":"阿里郎","description":"策略咯噢噢噢","counterPrice":25500,"categoryid":3,"brandid":3,"color":"红色","condition":3,"crowd":2,"attachList":[],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":114,"createtime":1484894995,"updatetime":1484895129,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":15,"name":"大宝贝","imgList":["Fmtd-O2YgCFG-hU3dVJ2H45yYxoa","FiOnf80ZZkl_FJSDtnhomwgQw5KH","FhDpAePDO3AKRgS30NdWAX6Na723","Fho6mgrkCUcMYgGD5pWQgl5yOqOc","Fsn3DPf9VRnj8LpaxtIfAS-AZGtk","FnwJoHMOYMRieJU-Gz70UgsVzIMm","FvazQr0ULqIdix1NVJJ2w71SQfOj","FjJhS9eNZg-OzeNYqgY-85wSN_wF","FpDfqsF8gNpenTg8KFIjd4OUl2PA"],"keyword":"明","description":"明明明","counterPrice":933300,"categoryid":12,"brandid":2,"color":"黄","condition":5,"crowd":3,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["1"]},{"attributeName":"相关配件","attributeValueList":["说明书"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":112,"createtime":1484893200,"updatetime":1484893224,"marketPrice":100,"rentPrice":100,"risk":100,"three":1100,"seven":100,"fiften":100,"twentyFive":100}]}
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
         * collectionid : 4
         * userid : 4
         * list : [114,112]
         * createtime : 1484901545
         * updatetime : 1484904254
         * rentList : [{"userid":10,"name":"阿里郎","imgList":["sxk_userPic_201701201449530","sxk_userPic_201701201449541","sxk_userPic_201701201449552","sxk_userPic_201701201449553","sxk_userPic_201701201449564","sxk_userPic_201701201449565","sxk_userPic_201701201449576","sxk_userPic_201701201449577","sxk_userPic_201701201449578"],"keyword":"阿里郎","description":"策略咯噢噢噢","counterPrice":25500,"categoryid":3,"brandid":3,"color":"红色","condition":3,"crowd":2,"attachList":[],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":114,"createtime":1484894995,"updatetime":1484895129,"marketPrice":1,"rentPrice":1,"risk":1,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":15,"name":"大宝贝","imgList":["Fmtd-O2YgCFG-hU3dVJ2H45yYxoa","FiOnf80ZZkl_FJSDtnhomwgQw5KH","FhDpAePDO3AKRgS30NdWAX6Na723","Fho6mgrkCUcMYgGD5pWQgl5yOqOc","Fsn3DPf9VRnj8LpaxtIfAS-AZGtk","FnwJoHMOYMRieJU-Gz70UgsVzIMm","FvazQr0ULqIdix1NVJJ2w71SQfOj","FjJhS9eNZg-OzeNYqgY-85wSN_wF","FpDfqsF8gNpenTg8KFIjd4OUl2PA"],"keyword":"明","description":"明明明","counterPrice":933300,"categoryid":12,"brandid":2,"color":"黄","condition":5,"crowd":3,"attachList":[{"attributeName":"测试二","attributeValueList":["牛皮"]},{"attributeName":"测试1","attributeValueList":["1"]},{"attributeName":"相关配件","attributeValueList":["说明书"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":112,"createtime":1484893200,"updatetime":1484893224,"marketPrice":100,"rentPrice":100,"risk":100,"three":1100,"seven":100,"fiften":100,"twentyFive":100}]
         */

        private int collectionid;
        private int userid;
        private int createtime;
        private int updatetime;
        private List<Integer> list;
        private List<RentListBean> rentList;

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

        public List<RentListBean> getRentList() {
            return rentList;
        }

        public void setRentList(List<RentListBean> rentList) {
            this.rentList = rentList;
        }

        public static class RentListBean {
            /**
             * userid : 10
             * name : 阿里郎
             * imgList : ["sxk_userPic_201701201449530","sxk_userPic_201701201449541","sxk_userPic_201701201449552","sxk_userPic_201701201449553","sxk_userPic_201701201449564","sxk_userPic_201701201449565","sxk_userPic_201701201449576","sxk_userPic_201701201449577","sxk_userPic_201701201449578"]
             * keyword : 阿里郎
             * description : 策略咯噢噢噢
             * counterPrice : 25500
             * categoryid : 3
             * brandid : 3
             * color : 红色
             * condition : 3
             * crowd : 2
             * attachList : []
             * commentList : []
             * sale : 0
             * sort : 0
             * status : 2
             * rentid : 114
             * createtime : 1484894995
             * updatetime : 1484895129
             * marketPrice : 1
             * rentPrice : 1
             * risk : 1
             * three : 1
             * seven : 1
             * fiften : 1
             * twentyFive : 1
             */

            private int userid;
            private String name;
            private String keyword;
            private String description;
            private int counterPrice;
            private int categoryid;
            private int brandid;
            private String color;
            private int condition;
            private int crowd;
            private int sale;
            private int sort;
            private int status;
            private int rentid;
            private int createtime;
            private int updatetime;
            private int marketPrice;
            private int rentPrice;
            private int risk;
            private int three;
            private int seven;
            private int fiften;
            private int twentyFive;
            private List<String> imgList;
            private List<?> attachList;
            private List<?> commentList;

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

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getCounterPrice() {
                return counterPrice;
            }

            public void setCounterPrice(int counterPrice) {
                this.counterPrice = counterPrice;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
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

            public int getSale() {
                return sale;
            }

            public void setSale(int sale) {
                this.sale = sale;
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

            public int getRentid() {
                return rentid;
            }

            public void setRentid(int rentid) {
                this.rentid = rentid;
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

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getRentPrice() {
                return rentPrice;
            }

            public void setRentPrice(int rentPrice) {
                this.rentPrice = rentPrice;
            }

            public int getRisk() {
                return risk;
            }

            public void setRisk(int risk) {
                this.risk = risk;
            }

            public int getThree() {
                return three;
            }

            public void setThree(int three) {
                this.three = three;
            }

            public int getSeven() {
                return seven;
            }

            public void setSeven(int seven) {
                this.seven = seven;
            }

            public int getFiften() {
                return fiften;
            }

            public void setFiften(int fiften) {
                this.fiften = fiften;
            }

            public int getTwentyFive() {
                return twentyFive;
            }

            public void setTwentyFive(int twentyFive) {
                this.twentyFive = twentyFive;
            }

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }

            public List<?> getAttachList() {
                return attachList;
            }

            public void setAttachList(List<?> attachList) {
                this.attachList = attachList;
            }

            public List<?> getCommentList() {
                return commentList;
            }

            public void setCommentList(List<?> commentList) {
                this.commentList = commentList;
            }
        }
    }
}
