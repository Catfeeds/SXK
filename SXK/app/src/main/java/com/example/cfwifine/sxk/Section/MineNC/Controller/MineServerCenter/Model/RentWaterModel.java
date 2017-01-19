package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Model;

/**
 * Created by cfwifine on 17/1/19.
 */

public class RentWaterModel {

    /**
     * code : 1
     * setup : {"setupid":1,"content":"fdafsd","createtime":1484497144,"updatetime":1484497154}
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
         * content : fdafsd
         * createtime : 1484497144
         * updatetime : 1484497154
         */

        private int setupid;
        private String content;
        private int createtime;
        private int updatetime;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
