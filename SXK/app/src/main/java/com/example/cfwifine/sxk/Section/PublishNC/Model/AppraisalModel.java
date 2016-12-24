package com.example.cfwifine.sxk.Section.PublishNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/24.
 */

public class AppraisalModel {

    /**
     * code : 1
     * setup : {"setupid":1,"campaign":"Shexiangke_jcq::identifySetup_campaign1481803686965","help":"Shexiangke_jcq::identifySetup_help_1481803524559","createtime":1481802656,"updatetime":1481803126}
     * genreList : [{"name":"包袋","description":"接发大奖非了节流阀就发1","price":350,"status":1,"genreid":2,"createtime":1482246259,"updatetime":1482247798},{"name":"腕表","description":"放假啦放假了家乐福简答拉分拉分","price":400,"status":1,"genreid":1,"createtime":1482246226,"updatetime":1482246226}]
     */

    private int code;
    private SetupBean setup;
    private List<GenreListBean> genreList;

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

    public List<GenreListBean> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<GenreListBean> genreList) {
        this.genreList = genreList;
    }

    public static class SetupBean {
        /**
         * setupid : 1
         * campaign : Shexiangke_jcq::identifySetup_campaign1481803686965
         * help : Shexiangke_jcq::identifySetup_help_1481803524559
         * createtime : 1481802656
         * updatetime : 1481803126
         */

        private int setupid;
        private String campaign;
        private String help;
        private int createtime;
        private int updatetime;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
        }

        public String getCampaign() {
            return campaign;
        }

        public void setCampaign(String campaign) {
            this.campaign = campaign;
        }

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
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

    public static class GenreListBean {
        /**
         * name : 包袋
         * description : 接发大奖非了节流阀就发1
         * price : 350
         * status : 1
         * genreid : 2
         * createtime : 1482246259
         * updatetime : 1482247798
         */

        private String name;
        private String description;
        private int price;
        private int status;
        private int genreid;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGenreid() {
            return genreid;
        }

        public void setGenreid(int genreid) {
            this.genreid = genreid;
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
