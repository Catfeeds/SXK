package com.example.cfwifine.sxk.Section.CommunityNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/13.
 */

public class HomeBannerModel {

    /**
     * code : 1
     * setup : {"setupid":1,"list":[{"link":"http://www.jd.com","img":"Shexiangke_jcq::finance_1484290892049"}],"createtime":1484290260,"updatetime":1484290281}
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
         * list : [{"link":"http://www.jd.com","img":"Shexiangke_jcq::finance_1484290892049"}]
         * createtime : 1484290260
         * updatetime : 1484290281
         */

        private int setupid;
        private int createtime;
        private int updatetime;
        private List<ListBean> list;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * link : http://www.jd.com
             * img : Shexiangke_jcq::finance_1484290892049
             */

            private String link;
            private String img;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
