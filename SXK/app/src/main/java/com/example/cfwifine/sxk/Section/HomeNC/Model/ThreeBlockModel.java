package com.example.cfwifine.sxk.Section.HomeNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/16.
 */

public class ThreeBlockModel {


    /**
     * code : 1
     * setup : {"setupid":1,"img":"Shexiangke_jcq::finance_1484491270118","rentidList":[38,30,7],"createtime":1484490213,"updatetime":1484491248,"rentList":[{"userid":10,"name":"黯然报名","imgList":["sxk_userPic_201612301425110","sxk_userPic_201612301425111","sxk_userPic_201612301425122","sxk_userPic_201612301425123","sxk_userPic_201612301425234","sxk_userPic_201612301425245","sxk_userPic_201612301425256","sxk_userPic_201612301425267","sxk_userPic_201612301425278"],"keyword":"啦啦叫我","description":"Jack了分开","counterPrice":20000,"categoryid":3,"color":"红色","condition":4,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":38,"createtime":1483079129,"updatetime":1483079474,"marketPrice":33300,"rentPrice":22200,"risk":100,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":10,"name":"安啦","imgList":["sxk_userPic_201612291413240","sxk_userPic_201612291413251","sxk_userPic_201612291413262","sxk_userPic_201612291413263","sxk_userPic_201612291413274","sxk_userPic_201612291413275","sxk_userPic_201612291413286","sxk_userPic_201612291413287","sxk_userPic_201612291413298"],"keyword":"JJ错么5啦咯啦咯啦咯啦咯啦咯啦咯啦咯兔兔咯了考虑兔兔突击涂土土图兔兔V领图兔兔图图V领TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤","description":"dzzzz","counterPrice":2000,"categoryid":3,"color":"红色","condition":3,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":30,"createtime":1482992009,"updatetime":1482992150,"marketPrice":99900,"rentPrice":88800,"risk":900,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":4,"name":"我是你","imgList":["FvpCpGbSKJrNGjXg43gjmrOI8_Ia","FoeRnSv-kK_9h4QqvpEAmPljMvW6","FoeRnSv-kK_9h4QqvpEAmPljMvW6","FqQSZBySEPyyaVHv06i-pCOrXJG0","FqQSZBySEPyyaVHv06i-pCOrXJG0","FvpCpGbSKJrNGjXg43gjmrOI8_Ia","FtiN9E4vZeu1kJyKUX7xAT8mbymx","FtiN9E4vZeu1kJyKUX7xAT8mbymx","Fgd2seqopQad5rex1Y2bdo4zaiX7"],"keyword":"手机哦的","description":"黄家驹","counterPrice":15600,"categoryid":3,"brandid":2,"color":"再见","condition":2,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡"]}],"commentList":[],"sort":0,"status":2,"rentid":7,"createtime":1482284824,"updatetime":1482804374,"marketPrice":2000000,"rentPrice":200000,"risk":5000,"three":10000,"seven":20000,"fiften":50000,"twentyFive":70000,"sale":0}]}
     */

    private int code;
    private SetupBean setup;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SetupBean getSetup() {
        return setup;
    }

    public void setSetup(SetupBean setup) {
        this.setup = setup;
    }

    public static class SetupBean {
        /**
         * setupid : 1
         * img : Shexiangke_jcq::finance_1484491270118
         * rentidList : [38,30,7]
         * createtime : 1484490213
         * updatetime : 1484491248
         * rentList : [{"userid":10,"name":"黯然报名","imgList":["sxk_userPic_201612301425110","sxk_userPic_201612301425111","sxk_userPic_201612301425122","sxk_userPic_201612301425123","sxk_userPic_201612301425234","sxk_userPic_201612301425245","sxk_userPic_201612301425256","sxk_userPic_201612301425267","sxk_userPic_201612301425278"],"keyword":"啦啦叫我","description":"Jack了分开","counterPrice":20000,"categoryid":3,"color":"红色","condition":4,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":38,"createtime":1483079129,"updatetime":1483079474,"marketPrice":33300,"rentPrice":22200,"risk":100,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":10,"name":"安啦","imgList":["sxk_userPic_201612291413240","sxk_userPic_201612291413251","sxk_userPic_201612291413262","sxk_userPic_201612291413263","sxk_userPic_201612291413274","sxk_userPic_201612291413275","sxk_userPic_201612291413286","sxk_userPic_201612291413287","sxk_userPic_201612291413298"],"keyword":"JJ错么5啦咯啦咯啦咯啦咯啦咯啦咯啦咯兔兔咯了考虑兔兔突击涂土土图兔兔V领图兔兔图图V领TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤TUT兔兔吐了咯啦咯啦土土图兔兔图图T恤","description":"dzzzz","counterPrice":2000,"categoryid":3,"color":"红色","condition":3,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}],"commentList":[],"sale":0,"sort":0,"status":2,"rentid":30,"createtime":1482992009,"updatetime":1482992150,"marketPrice":99900,"rentPrice":88800,"risk":900,"three":1,"seven":1,"fiften":1,"twentyFive":1},{"userid":4,"name":"我是你","imgList":["FvpCpGbSKJrNGjXg43gjmrOI8_Ia","FoeRnSv-kK_9h4QqvpEAmPljMvW6","FoeRnSv-kK_9h4QqvpEAmPljMvW6","FqQSZBySEPyyaVHv06i-pCOrXJG0","FqQSZBySEPyyaVHv06i-pCOrXJG0","FvpCpGbSKJrNGjXg43gjmrOI8_Ia","FtiN9E4vZeu1kJyKUX7xAT8mbymx","FtiN9E4vZeu1kJyKUX7xAT8mbymx","Fgd2seqopQad5rex1Y2bdo4zaiX7"],"keyword":"手机哦的","description":"黄家驹","counterPrice":15600,"categoryid":3,"brandid":2,"color":"再见","condition":2,"crowd":2,"attachList":[{"attributeName":"相关配件","attributeValueList":["保修卡"]}],"commentList":[],"sort":0,"status":2,"rentid":7,"createtime":1482284824,"updatetime":1482804374,"marketPrice":2000000,"rentPrice":200000,"risk":5000,"three":10000,"seven":20000,"fiften":50000,"twentyFive":70000,"sale":0}]
         */

        private int setupid;
        private String img;
        private int createtime;
        private int updatetime;
        private List<Integer> rentidList;
        private List<RentListBean> rentList;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public List<RentListBean> getRentList() {
            return rentList;
        }

        public void setRentList(List<RentListBean> rentList) {
            this.rentList = rentList;
        }

        public static class RentListBean {
            /**
             * userid : 10
             * name : 黯然报名
             * imgList : ["sxk_userPic_201612301425110","sxk_userPic_201612301425111","sxk_userPic_201612301425122","sxk_userPic_201612301425123","sxk_userPic_201612301425234","sxk_userPic_201612301425245","sxk_userPic_201612301425256","sxk_userPic_201612301425267","sxk_userPic_201612301425278"]
             * keyword : 啦啦叫我
             * description : Jack了分开
             * counterPrice : 20000
             * categoryid : 3
             * color : 红色
             * condition : 4
             * crowd : 2
             * attachList : [{"attributeName":"相关配件","attributeValueList":["保修卡","防尘袋"]}]
             * commentList : []
             * sale : 0
             * sort : 0
             * status : 2
             * rentid : 38
             * createtime : 1483079129
             * updatetime : 1483079474
             * marketPrice : 33300
             * rentPrice : 22200
             * risk : 100
             * three : 1
             * seven : 1
             * fiften : 1
             * twentyFive : 1
             * brandid : 2
             */

            private int userid;
            private String name;
            private String keyword;
            private String description;
            private int counterPrice;
            private int categoryid;
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
            private int brandid;
            private List<String> imgList;
            private List<AttachListBean> attachList;
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

            public int getBrandid() {
                return brandid;
            }

            public void setBrandid(int brandid) {
                this.brandid = brandid;
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

            public List<?> getCommentList() {
                return commentList;
            }

            public void setCommentList(List<?> commentList) {
                this.commentList = commentList;
            }

            public static class AttachListBean {
                /**
                 * attributeName : 相关配件
                 * attributeValueList : ["保修卡","防尘袋"]
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
